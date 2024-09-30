START TRANSACTION;

DROP TABLE IF EXISTS sale, art, artist, customer CASCADE;


-- CUSTOMER TABLE
CREATE TABLE customer 
(
	customerId serial, 
	firstName varchar(64) not null,
	lastName varchar(43) not null,
	address varchar(100) not null,
	phoneNumber varchar(11) null,
	
	CONSTRAINT pk_customer PRIMARY KEY(customerId)
);

-- ARTIST TABLE
CREATE TABLE artist
(
	artistId serial, 
	name varchar(64) not null,
	isDead boolean not null,
	bio varchar(256),
	
	CONSTRAINT pk_artist PRIMARY KEY (artistId)
);


-- ART TABLE
CREATE TABLE painting
(
	paintingId serial,
	title varchar(64),
	artistId int not null,
	price money not null,
	isOnSale boolean not null,
	
	CONSTRAINT pk_painting PRIMARY KEY(paintingId),
	CONSTRAINT fk_paintting_artist FOREIGN KEY (artistId) REFERENCES artist (artistId)
); 



-- SALES TABLE
CREATE TABLE sale
(
	customerId int not null,
	paintingId int not null,
	purchaseDate timestamp not null,
	price money not null,
	boughtOrSold boolean not null
	
	CONSTRAINT pk_sale PRIMARY KEY (customerId, artId, purchaseDate),
	CONSTRAINT fk_sale customer FOREIGN KEY (customerId) REFERENCES customer (customerId),
	CONSTRAINT fk_sale painting FOREIGN KEY (artId) REFERENCES painting (paintingId)
);


-- ROLLBACK;
COMMIT;

SELECT * FROM customer;
SELECT * FROM artist;






INSTERT INTO artist(name, isDead, bio)
VALUES ('Vincent Van Gogh', true, 'An old dead guy');