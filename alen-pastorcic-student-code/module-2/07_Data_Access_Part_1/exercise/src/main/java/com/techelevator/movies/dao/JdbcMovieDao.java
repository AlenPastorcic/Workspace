package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import com.techelevator.movies.model.Genre;
import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movie = new ArrayList<>();

        String sql = "SELECT * " + "FROM movie;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            Movie movie1 = mapRowToMovie(rowSet);
            movie.add(movie1);

        }

        return movie;

    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        String sql = "SELECT * " +
                "FROM movie " +
                "WHERE movie_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, movieId);
        if (rowSet.next()) {
            movie = mapRowToMovie(rowSet);
        }

        return movie;

    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {
        List<Movie> movie = new ArrayList<>();
        SqlRowSet rowSet;

        if (useWildCard) {
            String sql = "SELECT * " +
                    "FROM movie " +
                    "WHERE title ILIKE ?;";
            rowSet = jdbcTemplate.queryForRowSet(sql, "%" + title + "%");
        } else {
            String sql = "SELECT * " +
                    "FROM movie " +
                    "WHERE title ILIKE ?;";
            rowSet = jdbcTemplate.queryForRowSet(sql, title);
        }

        while (rowSet.next()) {
            Movie titles = mapRowToMovie(rowSet);
            movie.add(titles);
        }

        return movie;
    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear,
                                                              int endYear, boolean useWildCard) {
        return null;
    }

    private Movie mapRowToMovie(SqlRowSet rowSet) {
        Movie movie = new Movie();
        movie.setId(rowSet.getInt("movie_Id"));
        movie.setTitle(rowSet.getString("title"));
        movie.setOverview(rowSet.getString("overview"));
        movie.setTagline(rowSet.getString("tagline"));
        movie.setPosterPath(rowSet.getString("poster_path"));
        movie.setHomePage(rowSet.getString("home_page"));
        if (rowSet.getDate("release_date")!= null) {
            movie.setReleaseDate(rowSet.getDate("release_date").toLocalDate());
        }
        movie.setLengthMinutes(rowSet.getInt("length_minutes"));
        movie.setDirectorId(rowSet.getInt("director_id"));
        movie.setCollectionId(rowSet.getInt("collection_id"));

        return movie;


    }
}
