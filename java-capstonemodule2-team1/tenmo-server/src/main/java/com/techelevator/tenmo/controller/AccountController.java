package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.ViewTransferDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequestMapping("/Account/")
@RestController
public class AccountController {
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;
    }
    @GetMapping
    public String hello(){
        return "Hello from account";
    }
    @GetMapping("{id}")
    public List<Account> getAccounts(@PathVariable int id){
        return accountDao.getAccountByUserId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("Transfer")
    public Transfer sendTransfer(@RequestBody Transfer transfer){
        return accountDao.transferMoneySend(transfer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("Transfer/Approve")
    public Transfer approveTransfer(@RequestBody Transfer transfer){
        return accountDao.approvedTransfer(transfer);
    }

    @PutMapping("Transfer/Reject")
    public void rejectTransfer(@RequestBody Transfer transfer){
        accountDao.rejectTransfer(transfer);
    }

    @GetMapping("Transfer/View/{userId}")
    public List<ViewTransferDto> getAllTransfer(@PathVariable int userId){
        return accountDao.viewTransferByUserId(userId);
    }

    @GetMapping("Transfer/View/Pending/{userId}")
    public List<ViewTransferDto> getPendingTransfer(@PathVariable int userId){
        return accountDao.viewPendingTransferByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("Transfer/Request")
    public Transfer requestTransfer(@RequestBody Transfer transfer){
        return accountDao.requestMoney(transfer);
    }

}
