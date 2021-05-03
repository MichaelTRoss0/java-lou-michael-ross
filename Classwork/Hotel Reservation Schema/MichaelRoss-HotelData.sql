USE HotelDatabase;

-- Insertions go below this line
INSERT INTO Room (RoomNumber, `Type`, AdaAccessible, StdOccupancy, MaxOccupancy, BasePrice, ExtraPerson) VALUES
    ('201', 'Double', 'No',  2, 4, 199.99, 10),
    ('202', 'Double', 'Yes', 2, 4, 174.99, 10),
    ('203', 'Double', 'No',  2, 4, 199.99, 10),
    ('204', 'Double', 'Yes', 2, 4, 174.99, 10),
    ('205', 'Single', 'No',  2, 2, 174.99, NULL),
    ('206', 'Single', 'Yes', 2, 2, 149.99, NULL),
    ('207', 'Single', 'No',  2, 2, 174.99, NULL),
    ('208', 'Single', 'Yes', 2, 2, 149.99, NULL),
    ('301', 'Double', 'No',  2, 4, 199.99, 10),
    ('302', 'Double', 'Yes', 2, 4, 174.99, 10),
    ('303', 'Double', 'No',  2, 4, 199.99, 10),
    ('304', 'Double', 'Yes', 2, 4, 174.99, 10),
    ('305', 'Single', 'No',  2, 2, 174.99, NULL),
    ('306', 'Single', 'Yes', 2, 2, 149.99, NULL),
    ('307', 'Single', 'No',  2, 2, 174.99, NULL),
    ('308', 'Single', 'Yes', 2, 2, 149.99, NULL),
    ('401', 'Suite',  'Yes', 3, 8, 399.99, 20),
    ('402', 'Suite',  'Yes', 3, 8, 399.99, 20);
    
INSERT INTO Amenity (`Name`, ExtraCost) VALUES
    ('Microwave', 0),       -- Id = 1
    ('Refrigerator', 0),    -- Id = 2
    ('Jacuzzi', 25),        -- Id = 3
    ('Oven', 0);            -- Id = 4

INSERT INTO RoomAmenity (RoomNumber, AmenityId) VALUES
    ('201', 1),
    ('201', 3),
    ('202', 2),
    ('203', 1),
    ('203', 3),
    ('204', 2),
    ('205', 1),
    ('205', 2),
    ('205', 3),
    ('206', 1),
    ('206', 2),
    ('207', 1),
    ('207', 2),
    ('207', 3),
    ('208', 1),
    ('208', 2),
    ('301', 1),
    ('301', 3),
    ('302', 2),
    ('303', 1),
    ('303', 3),
    ('304', 2),
    ('305', 1),
    ('305', 2),
    ('305', 3),
    ('306', 1),
    ('306', 2),
    ('307', 1),
    ('307', 2),
    ('307', 3),
    ('308', 1),
    ('308', 2),
    ('401', 1),
    ('401', 2),
    ('401', 4),
    ('402', 1),
    ('402', 2),
    ('402', 4);

INSERT INTO Guest (FirstName, LastName, Address, City, State, Zip, Phone) VALUES
    ('Michael', 'Ross', '1337 Letter Lane', 'Numbersville', 'KY', '02468', '(555) 420-6969'),      -- Id = 1
    ('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '(291) 553-0508'), -- Id = 2
    ('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '(478) 277-9632'),      -- Id = 3
    ('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '(308) 494-0198'),       -- Id = 4
    ('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '(214) 730-0298'),   -- Id = 5
    ('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '(377) 507-0974'),      -- Id = 6
    ('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '(814) 485-2615'),         -- Id = 7
    ('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '(279) 491-0960'),        -- Id = 8
    ('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '(446) 396-6785'),    -- Id = 9
    ('Wilfred', 'Vise', '77 West Surrey Street, ', 'Oswego', 'NY', '13126', '(834) 727-1001'),     -- Id = 10
    ('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '(446) 351-6860'),              -- Id = 11
    ('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '(231) 893-2755');           -- Id = 12

INSERT INTO Reservation (GuestId, NumAdults, NumChildren, StartDate, EndDate, TotalRoomCost) VALUES
    (2, 1, 0,   '2023-02-02',  '2023-02-04', 299.98),    -- Id = 1
    (3, 2, 1,   '2023-02-05',  '2023-02-10', 999.95),    -- Id = 2
    (4, 2, 0,   '2023-02-22',  '2023-02-24', 349.98),    -- Id = 3
    (5, 2, 2,   '2023-03-06',  '2023-03-07', 199.99),    -- Id = 4   
    (1, 1, 1,   '2023-03-17',  '2023-03-20', 524.97),    -- Id = 5
    (6, 3, 0,   '2023-03-18',  '2023-03-23', 924.95),    -- Id = 6
    (7, 2, 2,   '2023-03-29',  '2023-03-31', 349.98),    -- Id = 7
    (8, 2, 0,   '2023-03-31',  '2023-04-05', 874.95),    -- Id = 8
    (9, 1, 0,   '2023-04-09',  '2023-04-13', 799.96),    -- Id = 9
    (10, 1, 1,  '2023-04-23',  '2023-04-24', 174.99),    -- Id = 10
    (11, 2, 4,  '2023-05-30',  '2023-06-02', 1199.97),   -- Id = 11
    (12, 2, 0,  '2023-06-10',  '2023-06-14', 599.96),    -- Id = 12
    (12, 1, 0,  '2023-06-10',  '2023-06-14', 599.96),    -- Id = 13
    (6, 3, 0,   '2023-06-17',  '2023-06-18', 184.99),    -- Id = 14
    (1, 2, 0,   '2023-06-28',  '2023-07-02', 699.96),    -- Id = 15
    (9, 3, 1,   '2023-07-13',  '2023-07-14', 184.99),    -- Id = 16
    (10, 4, 2,  '2023-07-18',  '2023-07-21', 1259.97),   -- Id = 17
    (3, 2, 1,   '2023-07-28',  '2023-07-29', 199.99),    -- Id = 18
    (3, 1, 0,   '2023-08-30',  '2023-09-01', 349.98),    -- Id = 19
    (2, 2, 0,   '2023-09-16',  '2023-09-17', 149.99),    -- Id = 20
    (5, 2, 2,   '2023-09-13',  '2023-09-15', 399.98),    -- Id = 21
    (4, 2, 2,   '2023-11-22',  '2023-11-25', 1199.97),   -- Id = 22
    (2, 2, 0,   '2023-11-22',  '2023-11-25', 449.97),    -- Id = 23
    (2, 2, 2,   '2023-11-22',  '2023-11-25', 599.97),    -- Id = 24
    (11, 2, 0,  '2023-12-24',  '2023-12-28', 699.96);    -- Id = 25

INSERT INTO RoomReservation (RoomNumber, ReservationId) VALUES
    ('308', 1),
    ('203', 2),
    ('305', 3),
    ('201', 4),
    ('307', 5),
    ('302', 6),
    ('202', 7),
    ('304', 8),
    ('301', 9),
    ('207', 10),
    ('401', 11),
    ('206', 12),
    ('208', 13),
    ('304', 14),
    ('205', 15),
    ('204', 16),
    ('401', 17),
    ('303', 18),
    ('305', 19),
    ('208', 20),
    ('203', 21),
    ('401', 22),
    ('206', 23),
    ('301', 24),
    ('302', 25);

SET SQL_SAFE_UPDATES = 0;

DELETE FROM RoomReservation
WHERE RoomReservation.ReservationId IN (
    SELECT
        Reservation.GuestId
    FROM Reservation
    WHERE Reservation.GuestId  = 8);

DELETE FROM Reservation
WHERE Reservation.GuestId = 8;

DELETE FROM Guest
WHERE Guest.GuestId = 8;

SET SQL_SAFE_UPDATES = 1;
