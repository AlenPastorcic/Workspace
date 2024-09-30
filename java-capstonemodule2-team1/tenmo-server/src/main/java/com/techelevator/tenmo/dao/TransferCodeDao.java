package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;

import java.util.List;

public interface TransferCodeDao {
    List<TransferStatus> getTransferStatus();
    List<TransferType> getTransferType();

}
