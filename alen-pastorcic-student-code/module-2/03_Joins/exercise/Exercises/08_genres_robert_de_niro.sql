-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later, sorted alphabetically.
-- (6 rows)

SELECT genre_name
FROM genre
JOIN movie_genre ON genre.genre_id = movie_genre.genre_id
JOIN movie ON movie_genre.movie_id = movie.movie_id
JOIN movie_actor ON movie.movie_id = movie_actor.movie_id
WHERE actor_id = 380 AND release_date >= '1/1/2010'
GROUP BY genre_name
ORDER BY genre_name;
