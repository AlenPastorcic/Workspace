-- 7. The genres of movies that Christopher Lloyd has appeared in, sorted alphabetically.
-- (8 rows) Hint: DISTINCT will prevent duplicate values in your query results.

SELECT genre_name
FROM genre
JOIN movie_genre ON genre.genre_id = movie_genre.genre_id
JOIN movie ON movie_genre.movie_id = movie.movie_id
JOIN movie_actor ON movie.movie_id = movie_actor.movie_id
WHERE actor_id = 1062
GROUP BY genre_name
ORDER BY genre_name;

