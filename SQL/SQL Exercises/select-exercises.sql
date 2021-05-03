USE PersonalTrainer;

-- Select all rows and columns from the Exercise table.
-- 64 rows
--------------------
SELECT *
FROM Exercise;

-- Select all rows and columns from the Client table.
-- 500 rows
--------------------
SELECT *
FROM Client;

-- Select all columns from Client where the City is Metairie.
-- 29 rows
--------------------
SELECT *
FROM Client
WHERE Client.City = "Metairie";

-- Is there a Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'?
-- nope
--------------------
SELECT
	Client.FirstName,
    Client.LastName
FROM Client
WHERE Client.ClientId = "818u7faf-7b4b-48a2-bf12-7a26c92de20c";

-- How many rows in the Goal table?
-- 17 rows
--------------------
SELECT *
FROM Goal;

-- Select Name and LevelId from the Workout table.
-- 26 rows
--------------------
SELECT
	Workout.Name,
    Workout.LevelId,
    Workout.Notes
FROM Workout;

-- Select Name, LevelId, and Notes from Workout where LevelId is 2.
-- 11 rows
--------------------
SELECT
	Workout.Name,
    Workout.LevelId,
    Workout.Notes
FROM Workout
WHERE Workout.LevelId = 2;

-- Select FirstName, LastName, and City from Client 
-- where City is Metairie, Kenner, or Gretna.
-- 77 rows
--------------------
SELECT
	Client.FirstName,
    Client.LastName,
    Client.City
FROM CLIENT
WHERE Client.City = "Metairie"
   OR Client.City = "Kenner"
   OR Client.City = "Gretna";

-- Select FirstName, LastName, and BirthDate from Client
-- for Clients born in the 1980s.
-- 72 rows
--------------------
SELECT
	Client.FirstName,
    Client.LastName,
    Client.BirthDate
FROM Client
WHERE YEAR(Client.BirthDate) = 1980
   OR YEAR(Client.BirthDate) = 1981
   OR YEAR(Client.BirthDate) = 1982
   OR YEAR(Client.BirthDate) = 1983
   OR YEAR(Client.BirthDate) = 1984
   OR YEAR(Client.BirthDate) = 1985
   OR YEAR(Client.BirthDate) = 1986
   OR YEAR(Client.BirthDate) = 1987
   OR YEAR(Client.BirthDate) = 1988
   OR YEAR(Client.BirthDate) = 1989;

-- Write the query above in a different way. 
-- If you used BETWEEN, you can't use it again.
-- If you didn't use BETWEEN, use it!
-- Still 72 rows
--------------------
SELECT
	Client.FirstName,
    Client.LastName,
    Client.BirthDate
FROM Client
WHERE YEAR(Client.BirthDate) BETWEEN 1980 AND 1989;

-- How many rows in the Login table have a .gov EmailAddress?
-- 17 rows
--------------------
SELECT *
FROM Login
WHERE Login.EmailAddress LIKE "%.gov";

-- How many Logins do NOT have a .com EmailAddress?
-- 122 rows
--------------------
SELECT *
FROM Login
WHERE Login.EmailAddress NOT LIKE "%.com";

-- Select first and last name of Clients without a BirthDate.
-- 37 rows
--------------------
SELECT
	Client.FirstName,
    Client.LastName
FROM Client
WHERE Client.BirthDate IS NULL;

-- Select the Name of each ExerciseCategory that has a parent.
-- (ParentCategoryId value is not null)
-- 12 rows
--------------------
SELECT
	ExerciseCategory.Name
FROM ExerciseCategory
WHERE ExerciseCategory.ParentCategoryId IS NOT NULL;

-- Select Name and Notes of each level 3 Workout that
-- contains the word 'you' in its Notes.
-- 4 rows
--------------------
SELECT
	Workout.Name,
    Workout.Notes
FROM Workout
WHERE Workout.LevelId = 3
AND Workout.Notes LIKE "%you%";

-- Select FirstName, LastName, City from Clients who have a LastName
-- that starts with L,M, or N and who live in LaPlace.
-- 5 rows
--------------------
SELECT
	Client.FirstName,
    Client.LastName,
    Client.City
FROM Client
WHERE (Client.LastName LIKE "L%" OR Client.LastName LIKE "M%" OR Client.LastName LIKE "N%")
	AND Client.City = "LaPlace";

-- Select InvoiceId, Description, Price, Quantity, ServiceDate 
-- and the line item total, a calculated value, where the line item total
-- is between 15 and 25 dollars.
-- 667 rows
--------------------
SELECT
	InvoiceLineItem.InvoiceId,
	InvoiceLineItem.Description,
	InvoiceLineItem.Price,
	InvoiceLineItem.Quantity,
	InvoiceLineItem.ServiceDate,
	InvoiceLineItem.InvoiceId
FROM InvoiceLineItem
WHERE (InvoiceLineItem.Price * InvoiceLineItem.Quantity) BETWEEN 15 AND 25;

-- Does the Client, Estrella Bazely, have a Login? 
-- If so, what is her email address?
-- This requires two queries:
-- First, select a Client record for Estrella Bazely. Does it exist?
-- Second, if it does, select a Login record that matches its ClientId.
--------------------
SELECT
	Client.ClientId
From Client
WHERE Client.FirstName = "Estrella" AND Client.LastName = "Bazely";

SELECT
    Login.EmailAddress
FROM Login
WHERE Login.clientId IN (
	SELECT
		Client.ClientId
	From Client
	WHERE Client.FirstName = "Estrella" AND Client.LastName = "Bazely");

-- What are the Goals of the Workout with the Name 'This Is Parkour'?
-- You need three queries!:
-- 1. Select the WorkoutId from Workout where Name equals 'This Is Parkour'.
-- 2. Select GoalId from WorkoutGoal where the WorkoutId matches the WorkoutId
--    from your first query.
-- 3. Select everything from Goal where the GoalId is one of the GoalIds from your
--    second query.
-- 1 row, 3 rows, 3 rows
--------------------
SELECT
	Workout.WorkoutId
FROM Workout
WHERE Workout.Name = "This Is Parkour";

SELECT
	WorkoutGoal.GoalId
FROM WorkoutGoal
WHERE WorkoutGoal.WorkoutId IN (

	SELECT
		Workout.WorkoutId
	FROM Workout
	WHERE Workout.Name = "This Is Parkour");

SELECT *
FROM Goal
WHERE Goal.GoalId IN (

	SELECT
		WorkoutGoal.GoalId
	FROM WorkoutGoal
	WHERE WorkoutGoal.WorkoutId IN (
    
		SELECT
			Workout.WorkoutId
		FROM Workout
		WHERE Workout.Name = "This Is Parkour"));
