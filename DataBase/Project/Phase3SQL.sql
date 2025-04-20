DROP DATABASE IF EXISTS Concrete;

CREATE DATABASE Concrete;
USE Concrete;

DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS Driver;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Manager;

CREATE TABLE Manager (
    Manager_ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(32) NOT NULL,
    Phone VARCHAR(32) NOT NULL,
    Address VARCHAR(32) NOT NULL
);

CREATE TABLE Customer (
    SSN INT PRIMARY KEY AUTO_INCREMENT,
    Manager_ID INT DEFAULT NULL,
    Name VARCHAR(32) NOT NULL,
    Phone VARCHAR(32) NOT NULL,
    Address VARCHAR(32) NOT NULL,
    FOREIGN KEY (Manager_ID) REFERENCES Manager(Manager_ID) ON DELETE SET NULL
);

CREATE TABLE Driver (
    Driver_ID INT PRIMARY KEY AUTO_INCREMENT,
    Manager_ID INT,
    Name VARCHAR(100),
    Phone VARCHAR(15),
    Address VARCHAR(255),
    FOREIGN KEY (Manager_ID) REFERENCES Manager(Manager_ID) ON DELETE SET NULL
);


CREATE TABLE Car (
    Reg_Number INT PRIMARY KEY AUTO_INCREMENT,
    Car_Type VARCHAR(100),
    Car_Color VARCHAR(50),
    Driver_ID INT,
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID) ON DELETE SET NULL
);

CREATE TABLE Product (
    Product_Name VARCHAR(32) PRIMARY KEY,
    Price DECIMAL(10, 2) NOT NULL CHECK (Price >= 0)
);

CREATE TABLE Orders (
    Order_ID INT PRIMARY KEY AUTO_INCREMENT,
    Customer_ID INT,
    Manager_ID INT,
    Driver_ID INT,
    Car_ID INT,
    Product_Name VARCHAR(32),
    Quantity INT NOT NULL CHECK (Quantity > 0),
    Order_date DATE NOT NULL,
    Status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (Customer_ID) REFERENCES Customer(SSN) ON DELETE CASCADE,
    FOREIGN KEY (Manager_ID) REFERENCES Manager(Manager_ID) ON DELETE SET NULL,
    FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID) ON DELETE SET NULL,
    FOREIGN KEY (Car_ID) REFERENCES Car(Reg_Number) ON DELETE SET NULL,
    FOREIGN KEY (Product_Name) REFERENCES Product(Product_Name) ON DELETE CASCADE
);

CREATE TABLE DatabaseManager (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

# Insert sample data
INSERT INTO DatabaseManager (username, password) VALUES ('admin', '123');

# Insert sample data into Manager
INSERT INTO Manager (Manager_ID, Name, Phone, Address) VALUES
(1, 'Abdallah Issa', '0599-343-283', 'Ramallah'),
(2, 'Mahmoud Rami', '0598-949-003', 'al-Quds'),
(3, 'Ahmad Khalid', '0593-752-113', 'Hebron'),
(4, 'Muhannad Ahmad', '0592-243-656', 'Nablus'),
(5, 'Ismail Issa', '0599-334-445', 'Tulkarm'),
(6, 'Ali Mousa', '0599-645-424', 'Salfit'),
(7, 'Issa Sami', '0595-332-303', 'Jenin'),
(8, 'Aysar Khawajeh', '0595-332-303', 'Bethlehem'),
(9, 'Mohammad Issam', '0593-121-004', 'Jericho'),
(10, 'Mohammad Abdo','0595-321-929' , 'Qalqilya'),
(11, 'Anas Issa', '0599-343-253', 'Ramallah'),
(12, 'Ahmad Rami', '0598-949-703', 'al-Quds'),
(13, 'Khalid Khalid', '0593-752-163', 'Hebron'),
(14, 'Karme Ahmad', '0592-243-646', 'Nablus'),
(15, 'Hanna Issa', '0599-334-545', 'Tulkarm'),
(16, 'Elias Mousa', '0599-645-324', 'Salfit'),
(17, 'Osama Sami', '0595-332-433', 'Jenin'),
(18, 'Ehab Khawajeh', '0595-332-223', 'Bethlehem'),
(19, 'Ayham Issam', '0593-121-233', 'Jericho'),
(20, 'Ahmad Abdo','0595-321-254' , 'Qalqilya');


-- Insert sample data into Customer
INSERT INTO Customer (Manager_ID, Name, Phone, Address) VALUES
(null, 'Mahmoud Basem', '0598-233-5568', 'Ramallah'),
(null, 'Sameh Ahmad', '0599-876-432', 'Hebron'),
(null, 'Anas Aziz', '0569-654-934', 'Nablus'),
(null, 'Ibrahim Khalid', '0593-313-198', 'Ramallah'),
(null, 'Anas Mhna', '0599-113-331', 'Tulkarm'),
(null, 'Issam Kher-Aldeen', '0569-113-411', 'al-Quds'),
(null, 'Azzam Hamed', '0590-114-411', 'Salfit'),
(null, 'Omar Mahmoud', '0597-552-422', 'Bethlehem'),
(null, 'Mohammad Shaheen', '0596-222-423', 'Jericho'),
(null, 'Thabet Saed', '0592-133-532', 'Jenin');

-- Insert sample data into Driver
INSERT INTO Driver (Manager_ID, Name, Phone, Address) VALUES
(3, 'Omar Hamed', '0599-313-411', 'Hebron'),
(3, 'Aysam mustafa', '0597-134-688', 'Hebron'),
(1, 'Jamal Mahmoud', '0562-456-079', 'Ramallah'),
(1, 'Ahmad Hamouda', '0599-346-785', 'Ramallah'),
(2, 'Mohammad Saleh', '0591-111-242', 'al-Quds'),
(2, 'Daoud Hamouda', '0595-323-113', 'al-Quds'),
(4, 'Ibrahim Tamer', '0567-533-663', 'Nablus'),
(4, 'Fahd Adel', '0596-232-111', 'Nablus'),
(6, 'Khalil Ibrahim', '0599-131-533', 'Salfit'),
(6, 'Samer Odeh', '0566-145-003', 'Salfit'),
(5, 'Issa Safi', '0560-343-757', 'Tulkarm'),
(5, 'Ahmad Ahed', '0560-441-110', 'Tulkarm'),
(8, 'Bashar Jaber', '0594-765-421', 'Bethlehem'),
(8, 'Shadi Issam', '0591-322-144', 'Bethlehem'),
(7, 'Ahmad Al-Ahmad', '0593-567-134', 'Jenin'),
(7, 'Sultan Fahd', '0566-521-414', 'Jenin'),
(9, 'Saeed Mahmoud', '0561-890-932', 'Jericho'),
(9, 'Abdallah Anas', '0599-131-521', 'Jericho'),
(10,'Ahmad Ashkar','0594-434-343','Qalqilya');



-- Insert sample data into Car
INSERT INTO Car (Car_Type, Car_Color, Driver_ID) VALUES
('Volvo', 'Red', 1),
('Man', 'Blue', 2),
('Mercedec', 'Black', 3),
('Toyota', 'White', 4),
('Toyota', 'Red', 5),
('Man', 'White', 6),
('Mercedec', 'Blue', 7),
('Toyota', 'Black', 8),
('Man', 'Green', 9),
('Mercedec', 'White', 10),
('Volvo', 'Yellow', 11),
('Volvo', 'Black', 3),
('Volvo', 'Red', 13),
('Man', 'Blue', 14),
('Man', 'Green', 15),
('Mercedec', 'White', 17),
('Toyota', 'Black', 19);



-- Insert sample data into Product
INSERT INTO Product (Product_Name, Price) VALUES
('Concrete 200', 100.50),
('Concrete 250', 120.75),
('Concrete 300', 150.00),
('Concrete 350', 200.25),
('Concrete 400', 250);

# Insert sample data into Orders
INSERT INTO Orders (Customer_ID, Product_Name, Quantity, Order_date) VALUES
(1, 'Concrete 200', 10, '2025-04-01'),
(2, 'Concrete 400', 5, '2025-04-02'),
(3, 'Concrete 350', 20, '2025-04-03'),
(4, 'Concrete 250', 15, '2025-04-04'),
(5, 'Concrete 300', 25, '2025-04-05'),
(6, 'Concrete 350', 30, '2025-04-06'),
(7, 'Concrete 200', 35, '2025-04-07'),
(8, 'Concrete 400', 40, '2025-04-08');

-- Verify the changes
SELECT * FROM Manager;
SELECT * FROM Driver;
SELECT * FROM Customer;
SELECT * FROM Car;
SELECT * FROM Product;
SELECT * FROM Orders;
SELECT * FROM DatabaseManager;

