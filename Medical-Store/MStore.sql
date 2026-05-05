CREATE DATABASE Medical_Store;
USE Medical_Store;

CREATE TABLE Customer (
    c_Id INT AUTO_INCREMENT PRIMARY KEY,
    c_Name VARCHAR(50) NOT NULL,
    c_MobileNo VARCHAR(13) NOT NULL UNIQUE, 
    buy_Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    left_Amount DECIMAL(10,2) DEFAULT 0.00
);

CREATE TABLE Medicine (
    m_Id INT AUTO_INCREMENT PRIMARY KEY,
    m_Name VARCHAR(100) NOT NULL,
    m_Price DECIMAL(10,2) NOT NULL,
    m_Stock INT DEFAULT 0
);

CREATE TABLE Purchase (
    c_Id INT,
    m_Id INT,
    quantity INT NOT NULL,
    total_Price DECIMAL(10,2) NOT NULL,
    purchase_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (c_Id) REFERENCES Customer(c_Id) ON DELETE CASCADE,
    FOREIGN KEY (m_Id) REFERENCES Medicine(m_Id) ON DELETE CASCADE,
    PRIMARY KEY (c_Id, m_Id, purchase_Date)  -- ensures uniqueness per customer + medicine + time
);


-- Delete the wrongly added customer
DELETE FROM Customer;

-- Reset the auto-increment so the next customer starts from 1
ALTER TABLE Customer AUTO_INCREMENT = 1;