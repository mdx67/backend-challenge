CREATE TABLE stores (
  store_id varchar(50) PRIMARY KEY,
  address varchar(255) NOT NULL,
  name varchar(255) NOT NULL
);

CREATE TABLE orders (
  order_id varchar(50) PRIMARY KEY,
  address varchar(255) NOT NULL,
  confirmation_date TIMESTAMP NOT NULL,
  status varchar(50)
);

CREATE TABLE order_item (
  order_item_id varchar(50) PRIMARY KEY,
  order_id varchar(50) REFERENCES orders (order_id),
  description varchar(255) NOT NULL,
  amount integer NOT NULL,
  quantity integer NOT NULL
);

CREATE TABLE payments (
  payment_id varchar(50) PRIMARY KEY,
  order_id varchar(50) REFERENCES orders (order_id),
  status varchar(50) NOT NULL,
  credit_card_number varchar(255) NOT NULL,
  payment_date TIMESTAMP
);
