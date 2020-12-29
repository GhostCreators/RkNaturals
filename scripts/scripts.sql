CREATE TABLE products (
	prod_id serial PRIMARY KEY,
	name character varying ( 50 )  NOT NULL,
	type character varying(50) NOT NULL,
	prize character varying(50) NOT NULL,
	stock integer NOT NULL,
	description character varying(255)
);

CREATE TABLE user_details (
	email VARCHAR ( 255 ) PRIMARY KEY,
	username VARCHAR ( 50 )  NOT NULL,
	password VARCHAR ( 50 ) NOT NULL
);
CREATE TABLE cart_items (
	email VARCHAR ( 255 ) NOT NULL,
	prod_id integer NOT NULL,
	quantity integer,
	PRIMARY KEY(email,prod_id)
);

ALTER TABLE cart_items
	ADD CONSTRAINT cart_items_user_details_fk
	FOREIGN KEY (email)
	REFERENCES user_details(email)
	ON DELETE CASCADE;

insert into user_details(username,password,email) values('prudhvi','1234','pbeerelly');
insert into products values('1','green soap','soap',10,10,'a soap with herbal properties');
insert into cart_items values('pbeerelly','1',10);