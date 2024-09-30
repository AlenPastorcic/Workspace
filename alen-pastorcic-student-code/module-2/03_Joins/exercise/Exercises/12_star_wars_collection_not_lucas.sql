-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas, sorted alphabetically.
-- (5 rows)

SELECT title
FROM movie
JOIN collection ON collection.collection_id = movie.collection_id
WHERE collection_name = 'Star Wars Collection' AND director_id != 1
ORDER by title;