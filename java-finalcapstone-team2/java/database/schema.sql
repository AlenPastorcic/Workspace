BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, menuItems, orders, events, userOrders, menuItemOrders, attendees, menus;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- Menu Item Table

CREATE TABLE menuItems
(
	item_id serial NOT NULL,
	title varchar(64) NOT NULL,
	description varchar(64) NOT NULL,
	img_url varchar(128) NOT NULL,
	
	CONSTRAINT pk_menuItems PRIMARY KEY (item_id)
);

-- Order Table 

CREATE TABLE orders
(
	order_id serial NOT NULL,
	menu_item_id int NOT NULL,
	user_id int NOT NULL,
	comment varchar(64) NOT NULL,
	
	CONSTRAINT pk_order PRIMARY KEY (order_id),
	CONSTRAINT fk_order_menu_item FOREIGN KEY (menu_item_id) REFERENCES menuItems (item_id)
);
 -- Menu Item Orders table 
 
CREATE TABLE menuItemOrders (
	order_id int,
	menu_item_id int,

	CONSTRAINT pk_menuItemOrders PRIMARY KEY (order_id, menu_item_id),
	CONSTRAINT fk_menuItemOrder_orders FOREIGN KEY (order_id) REFERENCES orders (order_id),
	CONSTRAINT fk_menuItemOrder_menuItems FOREIGN KEY (menu_item_id) REFERENCES menuItems (item_id)

);


-- Event Table

CREATE TABLE events (
	event_id SERIAL,
	title varchar(24) NOT NULL,
	description varchar(200) NOT NULL,
	event_date timestamp NOT NULL,
	location varchar(36) NOT NULL,
	img_url varchar(80),
	host_id int NOT NULL,
	chef_id int,
	
	CONSTRAINT pk_event PRIMARY KEY (event_id),
	CONSTRAINT fk_event_host FOREIGN KEY (host_id) REFERENCES users (user_id),
	CONSTRAINT fk_event_chef FOREIGN KEY (chef_id) REFERENCES users (user_id)
	
);

CREATE TABLE userOrders (
	order_id int,
	user_id int,

	CONSTRAINT pk_userOrder PRIMARY KEY (order_id, user_id),
	CONSTRAINT fk_userOrder_order FOREIGN KEY (order_id) REFERENCES orders (order_id),
	CONSTRAINT fk_userOrder_user FOREIGN KEY (user_id) REFERENCES users (user_id)

);


CREATE TABLE attendees (
	event_id int,
	user_id int,

	CONSTRAINT pk_attendees PRIMARY KEY (event_id, user_id),
	CONSTRAINT fk_attendees_events FOREIGN KEY (event_id) REFERENCES events (event_id),
	CONSTRAINT fk_attendees_users FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE menus (
	event_id int,
	item_id int,

	CONSTRAINT pk_menus PRIMARY KEY (event_id, item_id),
	CONSTRAINT fk_menus_events FOREIGN KEY (event_id) REFERENCES events (event_id),
	CONSTRAINT fk_menus_menuItems FOREIGN KEY (item_id) REFERENCES menuItems (item_id)
)


COMMIT TRANSACTION;


--ROLLBACK;