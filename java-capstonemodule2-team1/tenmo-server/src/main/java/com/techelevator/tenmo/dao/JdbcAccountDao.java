package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.ViewTransferDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getAccountByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Account account = mapRowToAccount(results);
                accounts.add(account);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return accounts;
    }

    public Account getAccountByAccountId(int accountId) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE account_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            if (results.next()) {
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }

    @Override
    public Transfer getTransferById(int id){
        Transfer transfer = null;
        String sql = "SELECT transfer_id,transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "from transfer " +
                "where transfer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfer;
    }

    @Transactional
    @Override
    public Transfer transferMoneySend(Transfer transfer) {
        Transfer newTransfer = null;
        Account fromAccount = getAccountByUserId(transfer.getAccountFromId()).get(0);
        Account toAccount = getAccountByUserId(transfer.getAccountToId()).get(0);
        BigDecimal amount = transfer.getAmount();

        BigDecimal initialMoney = fromAccount.getBalance();
        if(initialMoney.compareTo(fromAccount.subtractBalance(amount)) > 0){
            toAccount.addBalance(amount);
        }else{
            throw new DaoException("Invalid Amount!!!!");
        }
        String sql = "UPDATE account" +
                " SET balance = ? where account_id = ?;";
        String sqlTransfer = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES(?,?,?,?,?)" +
                " RETURNING transfer_id;";
        try {
            int numRows = jdbcTemplate.update(sql, fromAccount.getBalance().doubleValue(), fromAccount.getAccount_id());
            if(numRows == 0){
                throw new DaoException(DaoException.NO_DATA_AFFECTED);
            }
            numRows = jdbcTemplate.update(sql, toAccount.getBalance().doubleValue(), toAccount.getAccount_id());
            if(numRows == 0){
                throw new DaoException(DaoException.NO_DATA_AFFECTED);
            }
            int newTransferId = jdbcTemplate.queryForObject(sqlTransfer, int.class,
                    2,2,fromAccount.getAccount_id(), toAccount.getAccount_id(), amount.doubleValue());
            newTransfer = getTransferById(newTransferId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newTransfer;
    }

    @Transactional
    @Override
    public Transfer approvedTransfer(Transfer transfer) {
        Transfer newTransfer = null;
        Account fromAccount = getAccountByAccountId(transfer.getAccountToId());
        Account toAccount = getAccountByAccountId(transfer.getAccountFromId());
        BigDecimal amount = transfer.getAmount();

        BigDecimal initialMoney = fromAccount.getBalance();
        if(initialMoney.compareTo(fromAccount.subtractBalance(amount)) > 0){
            toAccount.addBalance(amount);
        }else{
            throw new DaoException("Invalid Amount!!!!");
        }
        String sql = "UPDATE account" +
                " SET balance = ? where account_id = ?;";
        String sqlTransfer = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES(?,?,?,?,?)" +
                " RETURNING transfer_id;";
        String sqlUpdateRequest = "UPDATE transfer " +
                "SET transfer_status_id = ? " +
                "Where transfer_id = ?;";
        try {
            int numRows = jdbcTemplate.update(sql, fromAccount.getBalance().doubleValue(), fromAccount.getAccount_id());
            if(numRows == 0){
                throw new DaoException(DaoException.NO_DATA_AFFECTED);
            }
            numRows = jdbcTemplate.update(sql, toAccount.getBalance().doubleValue(), toAccount.getAccount_id());
            if(numRows == 0){
                throw new DaoException(DaoException.NO_DATA_AFFECTED);
            }
            int newTransferId = jdbcTemplate.queryForObject(sqlTransfer, int.class,
                    2,2,fromAccount.getAccount_id(), toAccount.getAccount_id(), amount.doubleValue());
            jdbcTemplate.update(sqlUpdateRequest, 2, transfer.getTransferId());
            newTransfer = getTransferById(newTransferId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newTransfer;
    }

    @Override
    public void rejectTransfer(Transfer transfer) {
        String sqlUpdateRequest = "UPDATE transfer " +
                "SET transfer_status_id = ? " +
                "Where transfer_id = ?;";
        try {
            jdbcTemplate.update(sqlUpdateRequest, 3, transfer.getTransferId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public List<ViewTransferDto> viewTransferByUserId(int userId) {
        List<ViewTransferDto> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id,transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "from transfer " +
                "where (account_from = ? OR account_to = ?) AND transfer_status_id = ?;";
        int accountId = getAccountByUserId(userId).get(0).getAccount_id();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, accountId, 2);
            while (results.next()) {
               Transfer transfer = mapRowToTransfer(results);
               ViewTransferDto viewTransferDto = new ViewTransferDto();
               viewTransferDto.setTransfer(transfer);
               viewTransferDto.setFromName(getUserNameByAccountId(transfer.getAccountFromId()));
               viewTransferDto.setToName(getUserNameByAccountId(transfer.getAccountToId()));
               viewTransferDto.setAccountId(accountId);
               transfers.add(viewTransferDto);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }

    @Override
    public List<ViewTransferDto> viewPendingTransferByUserId(int userId) {
        List<ViewTransferDto> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id,transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "from transfer " +
                "where account_to = ? and transfer_type_id = ? and transfer_status_id = ?;";
        int accountId = getAccountByUserId(userId).get(0).getAccount_id();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, 1, 1);
            while (results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                ViewTransferDto viewTransferDto = new ViewTransferDto();
                viewTransferDto.setTransfer(transfer);
                viewTransferDto.setFromName(getUserNameByAccountId(transfer.getAccountFromId()));
                viewTransferDto.setToName(getUserNameByAccountId(transfer.getAccountToId()));
                viewTransferDto.setAccountId(accountId);
                transfers.add(viewTransferDto);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }
    
    private String getUserNameByAccountId(int accountId){
        String name = null;
        String sql = "SELECT username " +
                "from tenmo_user where user_id =  " +
                "(Select user_id from account where account_id = ?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            if (results.next()) {
                name = results.getString("username");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return name;
        
    }

    @Override
    public Transfer requestMoney(Transfer transfer) {
        Transfer newTransfer = null;
        Account fromAccount = getAccountByUserId(transfer.getAccountFromId()).get(0);
        Account toAccount = getAccountByUserId(transfer.getAccountToId()).get(0);
        BigDecimal amount = transfer.getAmount();

        String sqlTransfer = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES(?,?,?,?,?)" +
                " RETURNING transfer_id;";
        try {
            int newTransferId = jdbcTemplate.queryForObject(sqlTransfer, int.class,
                    1,1,fromAccount.getAccount_id(), toAccount.getAccount_id(), amount.doubleValue());
            newTransfer = getTransferById(newTransferId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newTransfer;
    }






    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(new BigDecimal(rs.getDouble("balance")));
        return account;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFromId(rs.getInt("account_from"));
        transfer.setAccountToId(rs.getInt("account_to"));
        transfer.setAmount(new BigDecimal(rs.getDouble("amount")));
        return transfer;
    }

}
