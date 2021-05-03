USE ConsumerComplaints;

SELECT DateReceived, Product, Company, State
FROM Complaint
WHERE State = 'LA'
AND Product = 'Mortgage' OR Product = 'Debt collection';