--DROP DATABASE IF EXISTS corporation;
--CREATE DATABASE corporation;

DROP TABLE IF EXISTS companies;
CREATE TABLE companies (
	id SERIAL PRIMARY KEY,
	company_name VARCHAR(500) NOT NULL UNIQUE,
	phone VARCHAR(100) NOT NULL,
	address VARCHAR(1000) NOT NULL,
	city VARCHAR(100) NOT NULL,
	region VARCHAR(200) NOT NULL,
	zip VARCHAR(50) NOT NULL,
	country VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id SERIAL,
	firstname VARCHAR(500) NOT NULL,
	lastname VARCHAR(500) NOT NULL,
	phone VARCHAR(100),
	email VARCHAR(100) NOT NULL,
	password VARCHAR(500) NOT NULL,
	description VARCHAR(5000)
);

INSERT INTO companies (company_name, phone, address, city, region, zip, country)
VALUES
	('Toyota', '(0565) 28-2121', '1 Toyota-Cho', 'Toyota City', 'Aichi Prefecture', '471-8571', 'Japan'),
	('Lexus', '+81 800-200-7584', 'Takatsujichō, ３−８−１', 'Nagoya', 'Aichi Prefecture', '466-0057', 'Japan'),
	('Daihatsu', '072-751-8811', '1-1 Daihatsu-cho', 'Ikeda-shi', 'Osaka', '563-0044', 'Japan');

INSERT INTO users (firstname, lastname, phone, email, password, description)
VALUES
	('John', 'Weak', '+1-894-111-1113', 'john.w@gmail.com', 'OneShotOneLife', 'Serious man'),
	('Obi-Wan', 'Kenobi', '+1-354-543-3453', 'obi.one@mail.com', 'LittleYoda123', 'Jedi'),
	('Sarah', 'oConnor', '+1-435-3452-7355', 'sarrah_o_connor@yahoo.com', 'SkynetWatchingYou#@553', 'mother');




