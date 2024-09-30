package com.techelevator;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        if (getBalance() - (amountToWithdraw + 2) < 0) {
            return getBalance();
        }

        else if (getBalance() - amountToWithdraw < 150) {
            return super.withdraw(amountToWithdraw + 2);
        }

        return super.withdraw(amountToWithdraw);
    }
}
