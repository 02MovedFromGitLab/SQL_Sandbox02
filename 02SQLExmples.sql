/*
1-Inner Joins:

--> Join orders and customer table making sure that ID's match
SELECT * FROM orders JOIN customers ON orders.customer_id = customers.customer_id;
--> then only columns you want with diambiguation for duplicate columns
SELECT orders.customer_id, order_id, first_name, last_name FROM orders
    JOIN customers ON orders.customer_id = customers.customer_id;
--> Using Aliases to simplify
SELECT o.customer_id, order_id, first_name, last_name FROM orders o
    JOIN customers c ON o.customer_id = o.customer_id;

--> Exercise ==> inner join order_items and products on product_id, displaying wanted cols, order by descending order id.
SELECT order_id, pr.product_id, oi.quantity, oi.unit_price FROM sql_store.order_items oi
JOIN sql_store.products pr ON oi.product_id = pr.product_id
ORDER BY order_id DESC;

2-Joining across multiple databases.
--> just fully qualify the db schema that is not currently highlighted.
SELECT * FROM order_items oi JOIN sql_inventory.products p
ON oi.product_id = p.product_id;

3-Self Joins adding alias with AS:

USE sql_hr;
SELECT e.employee_id, e.first_name, e.last_name, m.first_name AS manager
FROM employees e JOIN employees m ON e.reports_to = m.employee_id;

4- How to join more than 2 tables:

--> REMEMBER broad first and then more explicit:

SELECT * FROM orders o JOIN customers c ON o.customer_id = c.customer_id
JOIN order_statuses os ON o.status = os.order_status_id;
--to-->
SELECT o.order_id, o.order_date, c.first_name, c.last_name, os.name AS status FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_statuses os ON o.status = os.order_status_id;

EXERCISE:

First:
SELECT * FROM payments p
    JOIN clients c ON p.client_id = c.client_id
    JOIN payment_methods pm ON p.payment_method = pm.payment_method_id ;

Then:
USE sql_invoicing;
SELECT p.date, p.invoice_id, c.name, p.amount, pm.name FROM payments p
    JOIN clients c ON p.client_id = c.client_id
    JOIN payment_methods pm ON p.payment_method = pm.payment_method_id ;

5- Joining a table with a composite primary Key (orderItems) with other tables.

USE sql_store;
SELECT * FROM order_items oi
JOIN order_item_notes oin
    ON oi.order_id = oin.order_Id
           AND oi.product_id = oin.product_id;

6- Implicit JOIN syntax NOT RECOMMENDED

USE EXPLICIT JOIN:==>

USE sql_store;
SELECT * FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id;

--> Becomes:
USE sql_store;
SELECT * FROM orders o, customers c
WHERE o.customer_id = c.customer_id;

7- OUTER JOINS

# Inner Join--> ONLY shows customers with order in system
USE sql_store;
SELECT c.customer_id, c.first_name, o.order_id
FROM customers c JOIN orders o on c.customer_id = o.customer_id
ORDER BY c.customer_id ASC;

# Outer Join LEFT (allcustomers)--> Shows all customers whether they have an order or not
USE sql_store;
SELECT c.customer_id, c.first_name, o.order_id
FROM customers c LEFT JOIN orders o on c.customer_id = o.customer_id
ORDER BY c.customer_id ASC;

# Outer Join RIGHT (allorders/same as inner)
USE sql_store;
SELECT c.customer_id, c.first_name, o.order_id
FROM customers c RIGHT JOIN orders o on c.customer_id = o.customer_id
ORDER BY c.customer_id ASC;

EXERCISE:

# OUTER JOIN to show all products and there order items whether or not they have been ordered:
# First a standard innerjoin showing only products that have been ordered and are in oi:
USE sql_store;
SELECT * FROM products p
JOIN order_items oi ON p.product_id = oi.product_id;

#Now with an LEFT JOIN to include all products regardless of being in order_items:
USE sql_store;
SELECT p.product_id, p.name, oi.quantity FROM products p
LEFT JOIN order_items oi on p.product_id = oi.product_id;

8- OUTER JOIN between multiple tables

# Double left join ensures 1 all customers even if order null,
# 2 all shippers even if shipper null.

USE sql_store;
SELECT c.customer_id, c.first_name, o.order_id, s.name AS shipper
FROM customers c
    LEFT JOIN orders o on c.customer_id = o.customer_id
    LEFT JOIN shippers s ON o.shipper_id = s.shipper_id
ORDER BY c.customer_id ASC;

EXERCISE

USE sql_store;
SELECT o.order_date, o.order_id, c.first_name AS customer, s.name AS shipper, os.name AS status
FROM orders o
    JOIN customers c ON o.customer_id = c.customer_id
    LEFT JOIN shippers s ON o.shipper_id = s.shipper_id
    JOIN order_statuses os on o.status = os.order_status_id
    ORDER BY shipper ASC;
























*/