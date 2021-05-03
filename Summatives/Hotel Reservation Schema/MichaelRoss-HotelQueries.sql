USE HotelDatabase;

-- 1. Write a query that returns a list of reservations that end in July 2023,
-- including the name of the guest, the room number(s), and the reservation dates.
-- -- -- -- --
SELECT
    Guest.FirstName,
    Guest.LastName,
    RoomReservation.RoomNumber,
    Reservation.StartDate,
    Reservation.EndDate
FROM Guest
INNER JOIN Reservation ON Guest.GuestId = Reservation.GuestId
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
WHERE Reservation.EndDate BETWEEN '2023-07-01' AND '2023-07-31';
-- -- -- -- --
-- Results: 4 rows returned
-- Michael  Ross     205    2023-06-28  2023-07-02
-- Walter   Holaway  204    2023-07-13  2023-07-14
-- Wilfred  Vise     401    2023-07-18  2023-07-21
-- Bettyann Seery    303    2023-07-28  2023-07-29



-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi,
-- displaying the guest's name, the room number, and the dates of the reservation.
-- -- -- -- --
SELECT
    Guest.FirstName,
    Guest.LastName,
    RoomReservation.RoomNumber,
    Reservation.StartDate,
    Reservation.EndDate
FROM Guest
INNER JOIN Reservation ON Reservation.GuestId = Guest.GuestId
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
INNER JOIN RoomAmenity ON RoomReservation.RoomNumber = RoomAmenity.RoomNumber
INNER JOIN Amenity ON RoomAmenity.AmenityId = Amenity.AmenityId
WHERE Amenity.`Name` = 'Jacuzzi';
-- -- -- -- --
-- Results: 11 rows returned
-- Karie    Yang        201     2023-03-06  2023-03-07
-- Bettyann Seery       203     2023-02-05  2023-02-10
-- Karie    Yang        203     2023-09-13  2023-09-15
-- Michael  Ross        205     2023-06-28  2023-07-02
-- Wilfred  Vise        207     2023-04-23  2023-04-24
-- Walter   Holaway     301     2023-04-09  2023-04-13
-- Mack     Simmer      301     2023-11-22  2023-11-25
-- Bettyann Seery       303     2023-07-28  2023-07-29
-- Duane    Cullison    305     2023-02-22  2023-02-24
-- Bettyann Seery       305     2023-08-30  2023-09-01
-- Michael  Ross        307     2023-03-17  2023-03-20



-- 3. Write a query that returns all the rooms reserved for a specific guest, including
-- the guest's name, the room(s) reserved, the starting date of the reservation, and how many
-- people were included in the reservation. (Choose a guest's name from the existing data.)
-- -- -- -- --
SELECT
    Guest.FirstName,
    Guest.LastName,
    RoomReservation.RoomNumber,
    Reservation.StartDate,
    Reservation.NumAdults + Reservation.NumChildren
FROM Guest
INNER JOIN Reservation ON Guest.GuestId = Reservation.GuestId
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
WHERE Guest.FirstName = 'Zachery' AND Guest.LastName = 'Luechtefeld';
-- -- -- -- --
-- Results: 1 row returned
-- Zachery Luechtefeld  202 2023-03-29  4



-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each
-- reservation. The results should include all rooms, whether or not there is a reservation
-- associated with the room.
-- -- -- -- --
SELECT
    RoomReservation.RoomNumber,
    Reservation.ReservationId,
    Reservation.TotalRoomCost
FROM RoomReservation
LEFT OUTER JOIN Reservation ON RoomReservation.ReservationId = Reservation.ReservationId;
-- -- -- -- --
-- Results: 26 rows returned
-- 201  4       199.99
-- 202  7       349.98
-- 203  2       999.95
-- 203  21      399.98
-- 204  16      184.99
-- 205  15      699.96
-- 206  12      599.96
-- 206  23      449.97
-- 207  10      174.99
-- 208  13      599.96
-- 208  20      149.99
-- 301  9       799.96
-- 301  24      599.97
-- 302  6       924.95
-- 302  25      699.96
-- 303  18      199.99
-- 304  14      184.99
-- 305  3       349.98
-- 305  19      349.98
-- 306  NULL    NULL
-- 307  5       524.97
-- 308  1       299.98
-- 401  11      1199.97
-- 401  17      1259.97
-- 401  22      1199.97
-- 402  NULL    NULL



-- 5. Write a query that returns all the rooms accommodating at least three guests and that
-- are reserved on any date in April 2023.
-- -- -- -- --
SELECT
    RoomReservation.RoomNumber,
    Reservation.NumAdults,
    Reservation.NumChildren,
    Reservation.StartDate
FROM RoomReservation
INNER JOIN Reservation ON RoomReservation.ReservationId = Reservation.ReservationId
WHERE (Reservation.NumAdults + Reservation.NumChildren) >= 3
AND Reservation.StartDate BETWEEN '2023-04-01' AND '2023-04-30';
-- -- -- -- --
-- Results: 0 rows returned



-- 6. Write a query that returns a list of all guest names and the number of reservations per
-- guest, sorted starting with the guest with the most reservations and then by the guest's last name.
-- -- -- -- --
SELECT
    Guest.FirstName,
    Guest.LastName,
    COUNT(Reservation.GuestId)
FROM Guest
INNER JOIN Reservation ON Guest.GuestId = Reservation.GuestId
GROUP BY Guest.LastName, Guest.FirstName
ORDER BY COUNT(Reservation.GuestId) DESC,
         Guest.LastName ASC;
-- -- -- -- --
-- Results: 11 rows returned
-- Mack	    Simmer	        4
-- Bettyann	Seery	        3
-- Duane	Cullison	    2
-- Walter	Holaway         2
-- Aurore	Lipton	        2
-- Michael	Ross	        2
-- Maritza	Tilton	        2
-- Joleen	Tison	        2
-- Wilfred	Vise	        2
-- Karie	Yang	        2
-- Zachery  Luechtefeld	    1




-- 7. Write a query that displays the name, address, and phone number of a guest based on their
-- phone number. (Choose a phone number from the existing data.)
-- -- -- -- --
SELECT
    Guest.FirstName,
    Guest.LastName,
    Guest.Address,
    Guest.Phone
FROM Guest
WHERE Guest.Phone = '(478) 277-9632';
-- -- -- -- --
-- Results: returned 1 row
-- Bettyann Seery   750 Wintergreen Dr.     (478) 277-9632
