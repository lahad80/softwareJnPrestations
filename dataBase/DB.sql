--  SOURCE C:/Users/lahad/Desktop/programme_java/jnPrestationsProject/dataBase/DB.sql;

-- CREATE DATABASE testJnPrestations;

USE testJnPrestations;



CREATE TABLE MyBusinesses (
	id INT NOT NULL AUTO_INCREMENT,
	billNumber MEDIUMINT UNSIGNED NOT NULL,
	legalName VARCHAR (50) NOT NULL,
	address VARCHAR (50) NOT NULL,
	zipCode VARCHAR(5) NOT NULL,
	town VARCHAR (30) NOT NULL,
	phoneNumber VARCHAR (10) NOT NULL,
	email VARCHAR (30) NOT NULL,
	siren VARCHAR (30) NOT NULL,
	
	CONSTRAINT pk_mybus PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE EstateAgencies (
	id INT NOT NULL AUTO_INCREMENT,
	legalName VARCHAR (50) NOT NULL,
	address VARCHAR (50) NOT NULL,
	zipCode VARCHAR(5) NOT NULL,
	town VARCHAR (30) NOT NULL,
	phoneNumber VARCHAR (10),
	email VARCHAR (30),
	siren VARCHAR (30),
	
	CONSTRAINT pk_estateAg PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE Properties (
	id INT NOT NULL auto_increment,
	address VARCHAR (50) default NULL,
	service VARCHAR (255) NOT NULL,
	basicFee DOUBLE DEFAULT NULL ,
	estateAgency_id int NOT NULL ,
	CONSTRAINT pk_prop PRIMARY KEY (id),
	CONSTRAINT fk_prop_estateAg FOREIGN KEY (estateAgency_id) REFERENCES EstateAgencies(id) ON DELETE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE Invoices (
	id INT NOT NULL auto_increment,
	invNumber INT ,
	dateOfIssue VARCHAR(15) NOT NULL,
	period VARCHAR (15) NOT NULL,
	status VARCHAR (255),
	property_id INT ,
	CONSTRAINT pk_inv PRIMARY KEY (id),
	CONSTRAINT fk_inv_prop FOREIGN KEY (property_id) REFERENCES Properties (id) ON DELETE CASCADE
)
ENGINE = InnoDB;