-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-03-02 02:22:32.911

-- tables
-- Table: Flight
CREATE TABLE Flight (
    id int  NOT NULL,
    airline varchar  NOT NULL,
    takeOffDate date  NOT NULL,
    duration int  NOT NULL,
    source varchar  NOT NULL,
    destiny varchar  NOT NULL,
    CONSTRAINT Flight_pk PRIMARY KEY (id)
);

-- Table: Layover
CREATE TABLE Layover (
    flight int  NOT NULL,
    airport varchar  NOT NULL,
    CONSTRAINT Layover_pk PRIMARY KEY (flight,airport)
);

-- Table: Payment
CREATE TABLE Payment (
    id int  NOT NULL,
    "user" varchar  NOT NULL,
    type varchar  NOT NULL,
    info varchar  NOT NULL,
    CONSTRAINT Payment_pk PRIMARY KEY (id,"user")
);

-- Table: Ticket
CREATE TABLE Ticket (
    id int  NOT NULL,
    type varchar  NOT NULL,
    flight int  NULL,
    price real  NOT NULL,
    bagType varchar  NOT NULL,
    CONSTRAINT Ticket_pk PRIMARY KEY (id)
);

-- Table: User
CREATE TABLE "User" (
    mail varchar  NOT NULL,
    password varchar  NOT NULL,
    names varchar  NOT NULL,
    lastNames varchar  NOT NULL,
    username varchar  NOT NULL,
    document varchar  NOT NULL,
    documentType varchar  NOT NULL,
    balance real  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (mail)
);

-- End of file.

