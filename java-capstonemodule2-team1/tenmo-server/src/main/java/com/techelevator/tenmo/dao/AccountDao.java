package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.ViewTransferDto;

import java.util.List;

public interface AccountDao {
    List<Account> getAccountByUserId(int userId);
    Transfer transferMoneySend(Transfer transfer);

    List<ViewTransferDto> viewTransferByUserId(int userId);
    List<ViewTransferDto> viewPendingTransferByUserId(int userId);

    public Transfer getTransferById(int id);

    Transfer requestMoney(Transfer transfer);

    Transfer approvedTransfer(Transfer transfer);

    void rejectTransfer(Transfer transfer);
}
