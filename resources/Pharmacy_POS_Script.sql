-- Create the database
CREATE DATABASE IF NOT EXISTS pharmacy_pos;
USE pharmacy_pos;

-- Create the product_categories table
CREATE TABLE IF NOT EXISTS product_categories (
                                                  category_id INT PRIMARY KEY AUTO_INCREMENT,
                                                  category_name VARCHAR(255) NOT NULL
);

-- Create the suppliers table
CREATE TABLE IF NOT EXISTS suppliers (
                                         supplier_id INT PRIMARY KEY AUTO_INCREMENT,
                                         supplier_name VARCHAR(255) NOT NULL,
                                         email VARCHAR(255),
                                         phone_number VARCHAR(15) CHECK (phone_number REGEXP '^[0-9]{11}$')
);

-- Create the products table
CREATE TABLE IF NOT EXISTS products (
                                        product_id INT PRIMARY KEY AUTO_INCREMENT,
                                        product_name VARCHAR(255) NOT NULL,
                                        unit_price DECIMAL(10, 2) NOT NULL,
                                        category_id INT NOT NULL,
                                        supplier_id INT NOT NULL,
                                        quantity INT NOT NULL,
                                        lower_limit INT,
                                        status ENUM('active', 'discontinued') DEFAULT 'active',
                                        FOREIGN KEY (category_id) REFERENCES product_categories(category_id),
                                        FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Create the batches table
CREATE TABLE IF NOT EXISTS batches (
                                       batch_id INT PRIMARY KEY AUTO_INCREMENT,
                                       product_id INT NOT NULL,
                                       expiry_date DATE NOT NULL,
                                       quantity INT NOT NULL,
                                       FOREIGN KEY (product_id) REFERENCES products (product_id)
);

-- Create the customers table
CREATE TABLE IF NOT EXISTS customers (
                                         customer_id INT PRIMARY KEY AUTO_INCREMENT,
                                         customer_name VARCHAR(255) NOT NULL,
                                         customer_email VARCHAR(255)
);

-- Create the sales table
CREATE TABLE IF NOT EXISTS sales (
                                     sale_id INT PRIMARY KEY AUTO_INCREMENT,
                                     customer_id INT,
                                     sale_date DATE,
                                     total_amount DECIMAL(10, 2) NOT NULL,
                                     FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Create the sale_items table
CREATE TABLE IF NOT EXISTS sale_items (
                                          sale_item_id INT PRIMARY KEY AUTO_INCREMENT,
                                          sale_id INT,
                                          product_id INT,
                                          quantity INT NOT NULL,
                                          item_total DECIMAL(10, 2) NOT NULL,
                                          FOREIGN KEY (sale_id) REFERENCES sales(sale_id),
                                          FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
                                     user_id INT PRIMARY KEY AUTO_INCREMENT,
                                     name VARCHAR(255) NOT NULL,
                                     username VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     role ENUM('manager', 'sales_assistant') NOT NULL
);

-- Insert mock data into product_categories table
INSERT INTO product_categories (category_name)
VALUES
    ('Medicine'),
    ('First Aid');

-- Insert mock data into suppliers table
INSERT INTO suppliers (supplier_name, email, phone_number)
VALUES
    ('Supplier 1', 'supplier1@example.com', '12345678901'),
    ('Supplier 2', 'supplier2@example.com', '23456789012');

-- Insert mock data into products table
INSERT INTO products (product_name, unit_price, category_id, supplier_id, quantity, lower_limit)
VALUES
    ('Aspirin', 5.99, 1, 1, 100, 10),
    ('Cough Syrup', 8.99, 1, 1, 50, 5),
    ('Bandages', 3.49, 2, 2, 200, 20);

-- Insert mock data into batches table
INSERT INTO batches (product_id, expiry_date, quantity)
VALUES
    (1, '2023-12-31', 100),
    (2, '2023-11-30', 50),
    (3, '2024-02-29', 200);

-- Insert mock data into customers table
INSERT INTO customers (customer_name, customer_email)
VALUES
    ('John Doe', 'john.doe@example.com'),
    ('Jane Smith', 'jane.smith@example.com');

-- Insert mock data into sales and sale_items tables
INSERT INTO sales (customer_id, sale_date, total_amount)
VALUES
    (1, '2023-11-23', 15.98);

INSERT INTO sale_items (sale_id, product_id, quantity, item_total)
VALUES
    (1, 1, 2, 11.98),
    (1, 2, 1, 4.00);

-- Insert mock data into users table
INSERT INTO users (name, username, password, role)
VALUES
    ('Ali','manager1', 'password1', 'manager'),
    ('Hamza','assistant1', 'password2', 'sales_assistant');

-- Stored procedure to add a new product
DELIMITER //
CREATE PROCEDURE AddProduct(
    IN product_name_param VARCHAR(255),
    IN unit_price_param DECIMAL(10, 2),
    IN category_id_param INT,
    IN supplier_name_param VARCHAR(255),
    IN quantity_param INT,
    IN lower_limit_param INT
)
BEGIN
    DECLARE supplier_id_param INT;

    -- Check if the supplier exists
    SELECT supplier_id INTO supplier_id_param
    FROM suppliers
    WHERE supplier_name = supplier_name_param;

-- If supplier doesn't exist, insert into suppliers table
    IF supplier_id_param IS NULL THEN
        INSERT INTO suppliers (supplier_name)
        VALUES (supplier_name_param);

        SET supplier_id_param = LAST_INSERT_ID();
    END IF;

    -- Insert into products table
    INSERT INTO products (product_name, unit_price, category_id, supplier_id, quantity, lower_limit)
    VALUES (product_name_param, unit_price_param, category_id_param, supplier_id_param, quantity_param, lower_limit_param);
END //
DELIMITER ;

-- Stored procedure to get product information
DELIMITER //
CREATE PROCEDURE GetProductInfo(
    IN product_id_param INT
)
BEGIN
    SELECT
        p.product_id,
        p.product_name,
        p.unit_price,
        c.category_name,
        s.supplier_name,
        p.quantity,
        p.lower_limit
    FROM
        products p
            JOIN
        product_categories c ON p.category_id = c.category_id
            JOIN
        suppliers s ON p.supplier_id = s.supplier_id
    WHERE
            p.product_id = product_id_param;
END //
DELIMITER ;

-- Stored procedure to authenticate users
DELIMITER //
CREATE PROCEDURE AuthenticateUser(
    IN username_param VARCHAR(255),
    IN password_param VARCHAR(255)
)
BEGIN
    SELECT
        user_id,
        role
    FROM
        users
    WHERE
            username = username_param AND password = password_param;
END //
DELIMITER ;

-- Stored procedure to add a new supplier
DELIMITER //
CREATE PROCEDURE AddSupplier(
    IN supplier_name_param VARCHAR(255),
    IN email_param VARCHAR(255),
    IN phone_number_param VARCHAR(15)
)
BEGIN
    INSERT INTO suppliers (supplier_name, email, phone_number)
    VALUES (supplier_name_param, email_param, phone_number_param);
END //
DELIMITER ;
