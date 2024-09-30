package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Product;

import com.techelevator.model.User;

@Component
public class JdbcProductDao implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT product_id, name, description, price " +
                "FROM products;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                products.add(mapRowToProduct(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    private Product mapRowToProduct(SqlRowSet rowSet) {
        Product product = new Product();
        product.setProductId(rowSet.getInt("product_id"));
        product.setName(rowSet.getString("name"));
        product.setDescription(rowSet.getString("description"));
        product.setPrice(rowSet.getDouble("price"));
        return product;
    }
}
