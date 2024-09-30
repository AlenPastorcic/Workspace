package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionDao implements CollectionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Collection> getCollections() {
        List<Collection> collection = new ArrayList<>();

        String sql = "SELECT * " + "FROM collection;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            Collection collections = mapRowToCollection(rowSet);
            collection.add(collections);

        }

        return collection;

    }

    @Override
    public Collection getCollectionById(int collectonId) {
        Collection collection = null;
        String sql = "SELECT * " +
                "FROM collection " +
                "WHERE collection_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, collectonId);
        if (rowSet.next()) {
            collection = mapRowToCollection(rowSet);
        }

        return collection;

    }

    @Override
    public List<Collection> getCollectionsByName(String name, boolean useWildCard) {
        List<Collection> Collection = new ArrayList<>();
        SqlRowSet rowSet;

        if (useWildCard) {
            String sql = "SELECT * " +
                    "FROM collection " +
                    "WHERE collection_name ILIKE ?;";
            rowSet = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
        } else {
            String sql = "SELECT * " +
                    "FROM collection " +
                    "WHERE collection_name ILIKE ?;";
            rowSet = jdbcTemplate.queryForRowSet(sql, name);
        }

        while (rowSet.next()) {
            Collection collection = mapRowToCollection(rowSet);
            Collection.add(collection);
        }

        return Collection;
    }

        private Collection mapRowToCollection (SqlRowSet rowSet){
            Collection collection = new Collection();
            collection.setId(rowSet.getInt("collection_Id"));
            collection.setName(rowSet.getString("collection_name"));
            return collection;
        }



}
