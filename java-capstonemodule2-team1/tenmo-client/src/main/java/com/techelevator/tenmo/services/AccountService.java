package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AccountService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private User currentUser = null;
    private String authToken = null;
    private TransferStatus[] transferStatuses;
    private TransferType[] transferTypes;

    public AccountService(String baseUrl) {
        this.baseUrl = baseUrl;
        transferStatuses = getTransfersStatuses();
        transferTypes = getTransfersTypes();
    }

    public  void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Account[] getAccounts(){
            Account[] accounts = null;
            try {
                ResponseEntity<Account[]> response = restTemplate.exchange(baseUrl + "Account/" + currentUser.getId(), HttpMethod.GET, makeAuthEntity(), Account[].class);
                accounts = response.getBody();
            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            }
            return accounts;
    }

    public User[] getUser(){
        User[] users = null;
        try {
            ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "User/", HttpMethod.GET, makeAuthEntity(), User[].class);
            users = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;

    }

    public User getUserById(int id){
        User user = null;
        try {
            ResponseEntity<User> response = restTemplate.exchange(baseUrl + "User/" + id, HttpMethod.GET, makeAuthEntity(), User.class);
            user = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;

    }

    public Transfer sendTransfer(Transfer newTransfer){
        Transfer returnTransfer = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<Transfer> entity = new HttpEntity<>(newTransfer, headers);
        try{
            returnTransfer = restTemplate.postForObject(baseUrl + "Account/Transfer", entity, Transfer.class );
        } catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return  returnTransfer;
    }

    public Transfer approvedTransfer(Transfer transfer){
        Transfer returnTransfer = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        try{
            returnTransfer = restTemplate.postForObject(baseUrl + "Account/Transfer/Approve", entity, Transfer.class );
        } catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return  returnTransfer;
    }

    public boolean rejectTransfer(Transfer transfer){
        boolean success = false;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        try{
            restTemplate.put(baseUrl + "Account/Transfer/Reject", entity );
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return  success;
    }

    public ViewTransferDto[] getTransfers(int userId){
        ViewTransferDto[] viewTransferDtos = null;
        try {
            ResponseEntity<ViewTransferDto[]> response = restTemplate.exchange(baseUrl + "Account/Transfer/View/" + userId, HttpMethod.GET, makeAuthEntity(), ViewTransferDto[].class);
            viewTransferDtos = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return viewTransferDtos;
    }

    public ViewTransferDto[] getPendingTransfers(int userId){
        ViewTransferDto[] viewTransferDtos = null;
        try {
            ResponseEntity<ViewTransferDto[]> response = restTemplate.exchange(baseUrl + "Account/Transfer/View/Pending/" + userId, HttpMethod.GET, makeAuthEntity(), ViewTransferDto[].class);
            viewTransferDtos = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return viewTransferDtos;
    }

    private TransferStatus[] getTransfersStatuses(){
        TransferStatus[] transferStatuses = null;
        try {
            ResponseEntity<TransferStatus[]> response = restTemplate.exchange(baseUrl + "TransferCode/Status", HttpMethod.GET, makeAuthEntity(), TransferStatus[].class);
            transferStatuses = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferStatuses;
    }

    public String getTransferStatusById(int id){
        for(TransferStatus ts : transferStatuses){
            if(ts.getTransfer_status_id() == id){
                return ts.getTransfer_status();
            }
        }
        return null;
    }

    private TransferType[] getTransfersTypes(){
        TransferType[] transferTypes = null;
        try {
            ResponseEntity<TransferType[]> response = restTemplate.exchange(baseUrl + "TransferCode/Type", HttpMethod.GET, makeAuthEntity(), TransferType[].class);
            transferTypes = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferTypes;
    }

    public String getTransferTypeById(int id){
        for(TransferType tp : transferTypes){
            if(tp.getTransfer_type_id() == id){
                return tp.getTransfer_type();
            }
        }
        return null;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    public Transfer requestTransfer(Transfer newTransfer){
        Transfer returnTransfer = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<Transfer> entity = new HttpEntity<>(newTransfer, headers);
        try{
            returnTransfer = restTemplate.postForObject(baseUrl + "Account/Transfer/Request", entity, Transfer.class );
        } catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return  returnTransfer;
    }


}
