package com.techelevator;

public class BankAccount {

    String accountHolderName;
    String accountNumber;
    int balance;

    public BankAccount(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(String accountHolderName, String accountNumber, int balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public int deposit(int amountToDeposit) {
        if (amountToDeposit > 0) {
            balance += amountToDeposit;
        }
        return balance;

    }

    public int withdraw(int amountToWithdraw) {
        if (amountToWithdraw > 0) {
            balance = balance - amountToWithdraw;
        }
        return balance;
    }




}
