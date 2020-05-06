-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-03-12 13:26:19.423

-- tables
-- Table: Airline
CREATE TABLE Airline (
    name varchar  NOT NULL,
    phone varchar  NOT NULL,
    web varchar  NOT NULL,
    information varchar  NULL,
    CONSTRAINT Airline_pk PRIMARY KEY (name)
);

-- Table: Auction
CREATE TABLE Auction (
    id serial  NOT NULL,
    ticket int  NOT NULL,
    dueDate TIMESTAMP  NOT NULL,
    CONSTRAINT Auction_pk PRIMARY KEY (id)
);

-- Table: Bid
CREATE TABLE Bid (
    id serial  NOT NULL,
    amount real  NOT NULL,
    bidder varchar  NOT NULL,
    auction int  NOT NULL,
    CONSTRAINT Bid_pk PRIMARY KEY (id)
);

-- Table: Bidder
CREATE TABLE Bidder (
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

-- Table: Flight
CREATE TABLE Flight (
    id serial  NOT NULL,
    airline varchar  NOT NULL,
    takeOffDate TIMESTAMP  NOT NULL,
    duration int  NOT NULL,
    source varchar  NOT NULL,
    destiny varchar  NOT NULL,
    CONSTRAINT Flight_pk PRIMARY KEY (id)
);

-- Table: Layover
CREATE TABLE Layover (
    id serial  NOT NULL,
    flight int  NULL,
    airport varchar  NOT NULL,
    CONSTRAINT Layover_pk PRIMARY KEY (id)
);


-- Table: Ticket
CREATE TABLE Ticket (
    id serial  NOT NULL,
    type varchar  NOT NULL,
    flight int  NULL,
    price real  NOT NULL,
    bagType varchar  NOT NULL,
    CONSTRAINT Ticket_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Auction_Ticket (table: Auction)
ALTER TABLE Auction ADD CONSTRAINT Auction_Ticket
    FOREIGN KEY (ticket)
    REFERENCES Ticket (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Bid_Auction (table: Bid)
ALTER TABLE Bid ADD CONSTRAINT Bid_Auction
    FOREIGN KEY (auction)
    REFERENCES Auction (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Bid_Bidder (table: Bid)
ALTER TABLE Bid ADD CONSTRAINT Bid_Bidder
    FOREIGN KEY (bidder)
    REFERENCES Bidder (mail)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Flight_Airline (table: Flight)
ALTER TABLE Flight ADD CONSTRAINT Flight_Airline
    FOREIGN KEY (airline)
    REFERENCES Airline (name)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Layover_Flight (table: Layover)
ALTER TABLE Layover ADD CONSTRAINT Layover_Flight
    FOREIGN KEY (flight)
    REFERENCES Flight (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;


-- Reference: Ticket_Flight (table: Ticket)
ALTER TABLE Ticket ADD CONSTRAINT Ticket_Flight
    FOREIGN KEY (flight)
    REFERENCES Flight (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.
