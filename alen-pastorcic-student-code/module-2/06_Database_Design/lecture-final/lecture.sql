START TRANSACTION;

DROP TABLE IF EXISTS sale, art, artist, customer CASCADE;

-- Customer table


CREATE TABLE customer
(
	customerId serial,
	firstName varchar(64) not null,
	lastName varchar(64) not null,
	address varchar(100) not null,
	phone varchar(11) null,
	
	CONSTRAINT pk_customer PRIMARY KEY(customerId)
);

-- Artist table
CREATE TABLE artist
(
	artistId serial,
	name varchar(64) not null,
	isDead boolean not null,
	bio varchar(256),
	
	CONSTRAINT pk_artist PRIMARY KEY (artistId)
);

SELECT *
FROM artist;

-- Art table

CREATE TABLE art
(
	artId serial,
	title varchar(64),
	artistId int not null,
	price money not null,
	isOnSale boolean not null,
	
	CONSTRAINT pk_art PRIMARY KEY(artId),
	CONSTRAINT fk_art_artist FOREIGN KEY (artistId) REFERENCES artist (artistId)
);

-- Sale table
CREATE TABLE sale
(
	customerId int not null,
	artId int not null,
	purchaseDate timestamp not null,
	price money not null,
	boughtOrSold boolean not null,
	CONSTRAINT pk_sale PRIMARY KEY (customerId, artId, purchaseDate),
	CONSTRAINT fk_sale_customer FOREIGN KEY (customerId) REFERENCES customer (customerId),
	CONSTRAINT fk_sale_art FOREIGN KEY (artId) REFERENCES art (artId)
);

INSERT INTO artist (name, isDead, bio)
VALUES ('Vincent Van Gogh', true, 'An old dead guy');




-- ROLLBACK;
COMMIT;

-- SELECT * FROM customer;
-- SELECT * FROM artist;