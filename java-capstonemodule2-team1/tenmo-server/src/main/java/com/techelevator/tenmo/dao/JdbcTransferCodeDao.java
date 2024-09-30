package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferCodeDao implements TransferCodeDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferCodeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<TransferStatus> getTransferStatus() {
        List<TransferStatus> transferStatuses = new ArrayList<>();
        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_status;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                TransferStatus transferStatus = mapRowToTransferStatus(results);
                transferStatuses.add(transferStatus);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transferStatuses;
    }

    @Override
    public List<TransferType> getTransferType() {
        List<TransferType> transferTypes = new ArrayList<>();
        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_type;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                TransferType transferType = mapRowToTransferType(results);
                transferTypes.add(transferType);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transferTypes;
    }

    private TransferType mapRowToTransferType(SqlRowSet results) {
        TransferType transferType = new TransferType();
        transferType.setTransfer_type_id(results.getInt("transfer_type_id"));
        transferType.setTransfer_type(results.getString("transfer_type_desc"));
        return transferType;
    }

    private TransferStatus mapRowToTransferStatus(SqlRowSet results) {
        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setTransfer_status_id(results.getInt("transfer_status_id"));
        transferStatus.setTransfer_status(results.getString("transfer_status_desc"));
        return transferStatus;
    }
}
