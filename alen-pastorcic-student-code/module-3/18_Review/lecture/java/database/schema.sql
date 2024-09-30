BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, products, orders;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE products (
	product_id SERIAL,
	name varchar(32) NOT NULL UNIQUE,
	description varchar(256) NOT NULL,
	price numeric(4,2) NOT NULL,
	CONSTRAINT PK_product PRIMARY KEY (product_id)
);

CREATE TABLE orders (
	order_id SERIAL,
	user_id int NOT NULL,
	product_id int NOT NULL,
	qty int NOT NULL,
	CONSTRAINT PK_order PRIMARY KEY (order_id),
	CONSTRAINT FK_order_product FOREIGN KEY(product_id) REFERENCES products(product_id),
	CONSTRAINT FK_order_user FOREIGN KEY(user_id) REFERENCES users(user_id)
);


COMMIT TRANSACTION;
