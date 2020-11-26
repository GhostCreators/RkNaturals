CREATE TABLE products (
	prod_id serial PRIMARY KEY,
	name character varying ( 50 )  NOT NULL,
	type character varying(50) NOT NULL,
	prize character varying(50) NOT NULL,
	stock integer NOT NULL,
	description character varying(255)
);

CREATE TABLE user_details (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL
	
);
CREATE TABLE cart_items (
	user_id integer NOT NULL,
	prod_id integer NOT NULL,
	quantity integer,
	PRIMARY KEY(user_id,prod_id)
);

ALTER TABLE cart_items
	ADD CONSTRAINT cart_items_user_details_fk
	FOREIGN KEY (user_id)
	REFERENCES user_details(user_id)
	ON DELETE CASCADE;


CREATE TABLE address (
	address_id serial NOT NULL,
	details character varying ( 255 )  NOT NULL,
	landmark character varying(50) NOT NULL,
	pincode character varying(50) NOT NULL,
	phonenumber character varying(50)
);
insert into user_details(username,password,email) values('prudhvi','1234','pbeerelly');