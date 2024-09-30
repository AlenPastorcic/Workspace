package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private final AccountService accountService = new AccountService(API_BASE_URL);
    private AuthenticatedUser currentUser;




    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
        accountService.setCurrentUser(currentUser.getUser());
        String token = authenticationService.getToken();
        if(token != null){
            accountService.setAuthToken(token);
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }
    private String formatCurrency(BigDecimal bd){
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(bd);
    }
	private void viewCurrentBalance() {
        BigDecimal currentBalance = getUserBalance();
        System.out.println("```\n" +
                "Your current account balance is: "+ formatCurrency(currentBalance)+"\n" +
                "```");

	}
    private BigDecimal getUserBalance(){
        BigDecimal currentBalance = new BigDecimal("0");
        Account[] accounts = accountService.getAccounts();
        for(Account acc : accounts){
            currentBalance = currentBalance.add(acc.getBalance());
        }
        return currentBalance;
    }

	private void viewTransferHistory() {
        System.out.println("```\n" +
                "-------------------------------------------\n" +
                "Transfers\n");
        System.out.printf("%-10s %-20s %10s%n", "ID", "From/To", "Amount");
        System.out.println("---------------------------------------------");
        int userId = currentUser.getUser().getId();
        ViewTransferDto[] viewTransferDtos = accountService.getTransfers(userId);
        for(ViewTransferDto dto : viewTransferDtos) {
            Transfer transfer = dto.getTransfer();
            int transferId = transfer.getTransferId();
            String fromTo = transfer.getAccountFromId() == dto.getAccountId() ? "To: " : "From: ";
            String name = transfer.getAccountFromId() == dto.getAccountId() ? dto.getToName() : dto.getFromName();
            String amount = formatCurrency(transfer.getAmount());
            System.out.printf("%-10s %-20s %10s%n", transferId, fromTo + name, amount);
            System.out.println();
        }
        System.out.println("---------\n");

        viewTransferDetails(viewTransferDtos);
	}
    private void viewTransferDetails(ViewTransferDto[] viewTransferDtos) {
        int viewId = consoleService.promptForMenuSelection("Please enter transfer ID to view details (0 to cancel): ");
        if(viewId == 0){
            return;
        }
        for(ViewTransferDto dto : viewTransferDtos){
            int transferId = dto.getTransfer().getTransferId();
            if(transferId == viewId){
                int id = transferId;
                Transfer transfer = dto.getTransfer();
                String fromName = dto.getFromName();
                String toName = dto.getToName();
                int typeId = transfer.getTransferTypeId();
                String type = accountService.getTransferTypeById(typeId);
                int statusId = transfer.getTransferStatusId();
                String status = accountService.getTransferStatusById(statusId);
                String amount = formatCurrency(transfer.getAmount());
                System.out.println("--------------------------------------------\n" +
                        "Transfer Details\n" +
                        "--------------------------------------------\n" +
                        " Id: "+ id +"\n" +
                        " From: "+fromName+"\n" +
                        " To: "+toName+" \n" +
                        " Type: "+type +"\n" +
                        " Status: "+status+"\n" +
                        " Amount: "+amount+"\n" +
                        "```");
                return;
            }
        }
        System.out.println("Invalid TransferId, please choose from the list.");
        viewTransferDetails(viewTransferDtos);

    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
        System.out.println("```\n" +
                "-------------------------------------------\n" +
                "Pending Transfers\n");
        System.out.printf("%-10s %-20s %10s%n", "ID", "To", "Amount");
        System.out.println("---------------------------------------------");
        int userId = currentUser.getUser().getId();
        Map<Integer, Transfer> transferIdsMapRequestTransfer = new HashMap<>();
        ViewTransferDto[] viewTransferDtos = accountService.getPendingTransfers(userId);
        for(ViewTransferDto dto : viewTransferDtos) {
            Transfer transfer = dto.getTransfer();
            int transferId = transfer.getTransferId();
            transferIdsMapRequestTransfer.put(transferId, transfer);
            String name = dto.getFromName();
            String amount = formatCurrency(transfer.getAmount());
            System.out.printf("%-10s %-20s %10s%n", transferId, name, amount);
            System.out.println();
        }
        System.out.println("---------\n");

        approveOrRejectPendingTransfer(transferIdsMapRequestTransfer);

	}

    private void approveOrRejectPendingTransfer(Map<Integer,Transfer> transferIdsMapRequestTransfer){
        int transferIDSelection = consoleService.promptForInt("Please enter transfer ID to approve/reject (0 to cancel): ");
        if(transferIDSelection == 0){
            return;
        }
        if(!transferIdsMapRequestTransfer.containsKey(transferIDSelection)){
            System.out.println("Invalid Transfer ID!!!");
            approveOrRejectPendingTransfer(transferIdsMapRequestTransfer);
            return;
        }
        Transfer requestTransfer = transferIdsMapRequestTransfer.get(transferIDSelection);
        User requestUser = accountService.getUserById(requestTransfer.getAccountFromId());
        System.out.println();
        System.out.println("1: Approve\n" +
                "2: Reject\n" +
                "0: Don't approve or reject\n" +
                "---------");
        int menuSelect = consoleService.promptForMenuSelection("Please choose an option:");

        if(menuSelect == 0){
            viewPendingRequests();
            return;
        }else if(menuSelect == 1){
                BigDecimal userBalance = getUserBalance();
                if(requestTransfer.getAmount().compareTo(userBalance) > 0){
                    System.out.println("You don't have enough money to approve that amount!!!");
                    viewPendingRequests();
                    return;
                }
                Transfer returnTransfer = accountService.approvedTransfer(requestTransfer);
                System.out.println("!!!Transfer Approved!!!");
                System.out.println("Transfer to " + returnTransfer.getAccountToId());
                System.out.println("Transfer amount " + formatCurrency(returnTransfer.getAmount()));
                System.out.println("Your balance: " + formatCurrency(getUserBalance()));
                return;
        }else if(menuSelect == 2){
            if(accountService.rejectTransfer(requestTransfer)){
                System.out.println("Rejected!");
            }else{
                System.out.println("Error Reject Transfer, please try again!");
            }
            viewPendingRequests();
            return;
        }

//        for(User user : users){
//            if(user.getId() == userIDSelection){
//                BigDecimal amount = checkAmount("Send");
//                Transfer transfer = new Transfer();
//                transfer.setAccountFromId(currentUser.getUser().getId());
//                transfer.setAccountToId(user.getId());
//                transfer.setAmount(amount);
//                Transfer returnTransfer = accountService.sendTransfer(transfer);
//
//                System.out.println("!!!Transfer Successful!!!");
//                System.out.println("Transfer to " + returnTransfer.getAccountToId());
//                System.out.println("Transfer amount " + formatCurrency(returnTransfer.getAmount()));
//                System.out.println("Your balance: " + formatCurrency(getUserBalance()));
//                return;
//            }
//        }
        System.out.println("!!!Invalid UserId!!!");
        sendBucks();
    }

	private void sendBucks() {
        User[] users = accountService.getUser();
        viewUser(users);
        int userIDSelection = consoleService.promptForMenuSelection("Enter ID of user you are sending to (0 to cancel):");
        if(userIDSelection == 0){
            return;
        }
        if(userIDSelection == currentUser.getUser().getId()){
            System.out.println("!!!Transfer to yourself is not allowed!!!");
            sendBucks();
        }
        for(User user : users){
            if(user.getId() == userIDSelection){
                BigDecimal amount = checkAmount("Send");
                Transfer transfer = new Transfer();
                transfer.setAccountFromId(currentUser.getUser().getId());
                transfer.setAccountToId(user.getId());
                transfer.setAmount(amount);
                Transfer returnTransfer = accountService.sendTransfer(transfer);

                System.out.println("!!!Transfer Successful!!!");
                System.out.println("Transfer to " + returnTransfer.getAccountToId());
                System.out.println("Transfer amount " + formatCurrency(returnTransfer.getAmount()));
                System.out.println("Your balance: " + formatCurrency(getUserBalance()));
                return;
            }
        }
        System.out.println("!!!Invalid UserId!!!");
        sendBucks();
	}
    private BigDecimal checkAmount(String requestType){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount: ");
        String amountString = scan.nextLine();
        BigDecimal amount = null;
        try{
           amount = new BigDecimal(amountString);
        }catch (NumberFormatException e){
            System.out.println("!!!Invalid input!!!");
            return checkAmount(requestType);

        }
        BigDecimal zero = new BigDecimal("0");
        if(amount.compareTo(zero)<=0){
            System.out.println("Amount should be a positive number!!!");
            return checkAmount(requestType);
        }
        BigDecimal userBalance = getUserBalance();
        if(amount.compareTo(userBalance) > 0 && requestType.equals("Send")){
            System.out.println("You are too broke for that amount!!!");
            return checkAmount(requestType);
        }

        return amount;
    }

	private void requestBucks() {
        User[] users = accountService.getUser();
        viewUser(users);
        int userIDSelection = consoleService.promptForMenuSelection("Enter ID of user you are requesting from (0 to cancel):");
        if(userIDSelection == 0){
            return;
        }
        if(userIDSelection == currentUser.getUser().getId()){
            System.out.println("!!!Request to yourself is not allowed!!!");
            requestBucks();
        }
        for(User user : users){
            if(user.getId() == userIDSelection){
                BigDecimal amount = checkAmount("Request");
                Transfer transfer = new Transfer();
                transfer.setAccountFromId(currentUser.getUser().getId());
                transfer.setAccountToId(user.getId());
                transfer.setAmount(amount);
                Transfer returnTransfer = accountService.requestTransfer(transfer);

                System.out.println("!!!Request Successful!!!");
                System.out.println("Request to " + returnTransfer.getAccountToId());
                System.out.println("Request amount " + formatCurrency(returnTransfer.getAmount()));
                return;
            }
        }
        System.out.println("!!!Invalid UserId!!!");
        requestBucks();

	}




    private void viewUser(User[] users){
        System.out.println("```\n" +
                "-------------------------------------------\n" +
                "Users\n" +
                "ID\t\t\tName\n" +
                "-------------------------------------------\n");
        for(User user : users) {
            if(user.equals(currentUser.getUser())){
                continue;
            }
            int id = user.getId();
            String name = user.getUsername();
            System.out.println(
                    id + "\t\t" + name + "\n");
        }

        System.out.println("\n---------");

    }
}
