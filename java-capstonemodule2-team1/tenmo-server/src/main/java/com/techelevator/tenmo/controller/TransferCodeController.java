package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferCodeDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/TransferCode/")
@RestController
public class TransferCodeController {
    private TransferCodeDao transferCodeDao;

    public TransferCodeController(TransferCodeDao transferCodeDao){
        this.transferCodeDao = transferCodeDao;
    }
    @GetMapping("Status")
    public List<TransferStatus> getTransferStatus(){
        return transferCodeDao.getTransferStatus();
    }

    @GetMapping("Type")
    public List<TransferType> getTransferType(){
        return transferCodeDao.getTransferType();
    }
}
