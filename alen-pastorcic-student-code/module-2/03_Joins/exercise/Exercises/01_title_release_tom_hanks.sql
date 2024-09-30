-- 1. The titles and release dates of movies that Tom Hanks has appeared in.
-- Order the results by release date, newest to oldest.
-- (47 rows)

SELECT title, release_date
FROM movie
JOIN movie_actor ON movie_actor.movie_id = movie.movie_id
WHERE actor_id = 31
ORDER BY release_date DESC;
