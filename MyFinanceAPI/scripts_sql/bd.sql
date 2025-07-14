CREATE DATABASE IF NOT EXISTS MyFinanceAPI;

USE MyFinanceAPI;

CREATE TABLE category (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE transactions (
	id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(500),
    value FLOAT NOT NULL,
    type VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    date DATETIME NOT NULL,
    
    FOREIGN KEY (category_id) REFERENCES category(id)
);