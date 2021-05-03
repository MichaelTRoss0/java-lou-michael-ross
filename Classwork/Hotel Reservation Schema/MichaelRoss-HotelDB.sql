DROP DATABASE IF EXISTS HotelDatabase;

CREATE DATABASE HotelDatabase;

USE HotelDatabase;

-- Tables
CREATE TABLE Room (
	RoomNumber CHAR(3) PRIMARY KEY,
    
    `Type` VARCHAR(10) NOT NULL,
    AdaAccessible VARCHAR(3) NOT NULL,
    StdOccupancy TINYINT NOT NULL,
    MaxOccupancy TINYINT NOT NULL,
    BasePrice DEC(5,2) NOT NULL,
    ExtraPerson DEC(2,0) NULL
);

CREATE TABLE Amenity (
	AmenityId INT PRIMARY KEY AUTO_INCREMENT,
    
	`Name` VARCHAR(12) NOT NULL,
    ExtraCost DEC(2,0) NULL
);

CREATE TABLE RoomAmenity (
	RoomNumber CHAR(3) NOT NULL,
    AmenityId INT NOT NULL,
    PRIMARY KEY (RoomNumber, AmenityId),
    FOREIGN KEY fk_RoomAmenity_RoomNumber (RoomNumber)
		REFERENCES Room(RoomNumber),
	FOREIGN KEY fk_RoomAmenity_AmenityId (AmenityId)
		REFERENCES Amenity(AmenityId)
);

CREATE TABLE Guest (
	GuestId INT PRIMARY KEY AUTO_INCREMENT,
    
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    Address VARCHAR(30) NOT NULL,
    City VARCHAR(30) NOT NULL,
    State CHAR(2) NOT NULL,
    Zip CHAR(5) NOT NULL,
    Phone CHAR(14) NOT NULL
);

CREATE TABLE Reservation (
	ReservationId INT PRIMARY KEY AUTO_INCREMENT,
    
    GuestId INT NOT NULL,
    NumAdults TINYINT NOT NULL,
    NumChildren TINYINT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    TotalRoomCost DEC(6,2) NOT NULL,
    FOREIGN KEY fk_Reservation_GuestId (GuestId)
		REFERENCES Guest(GuestId)
);

CREATE TABLE RoomReservation (
	RoomNumber CHAR(3) NOT NULL,
    ReservationId INT NOT NULL,
    PRIMARY KEY pk_RoomReservation (RoomNumber, ReservationId),
    FOREIGN KEY fk_RoomReservation_RoomNumber (RoomNumber)
		REFERENCES Room(RoomNumber),
	FOREIGN KEY fk_RoomReservation_ReservationId (ReservationId)
		REFERENCES Reservation(ReservationId)
);
