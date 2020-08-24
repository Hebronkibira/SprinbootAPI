--DROP TABLE IF EXISTS customers;
 
CREATE TABLE if not exists customers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  pin VARCHAR(250) DEFAULT NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  customer_id VARCHAR(250) DEFAULT NOT NULL
);

CREATE TABLE if not exists accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id VARCHAR(250) DEFAULT NOT NULL,
  account_no VARCHAR(250) NOT NULL,
  balance DOUBLE(100) NOT NULL
);

CREATE TABLE if not exists transactions (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  transaction_id VARCHAR(250) DEFAULT NOT NULL,
  customer_id VARCHAR(250) DEFAULT NOT NULL,
  account_no VARCHAR(250) NOT NULL,
  amount DOUBLE(100) NOT NULL,
  transaction_type VARCHAR(250) NOT NULL,
  debit_or_Credit VARCHAR(250) NOT NULL,
  balance DOUBLE(100) NOT NULL
);

CREATE TABLE if not exists webusers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) DEFAULT NOT NULL,
  password VARCHAR(250) DEFAULT NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  employee_id  VARCHAR(250) DEFAULT NULL,
  customer_id VARCHAR(250) DEFAULT NOT NULL
);


