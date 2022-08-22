/*
//SELECT order_id,
       product_id,
       quantity,
       unit_price,
       quantity * unit_price AS LineItemPrice
FROM order_items WHERE order_id = 2 ORDER BY LineItemPrice DESC;

USE sql_inventory;
SELECT DISTINCT
    name,
    unit_price,
    unit_price * 1.1 AS new_price
FROM sql_store.products

SELECT * FROM products
WHERE quantity_in_stock IN (49, 38, 72);

USE sql_store;
SELECT * FROM customers WHERE birth_date BETWEEN '1990-01-01' AND '2000-01-01';

SELECT * FROM sql_store.orders WHERE shipped_date IS NULL;

HOW LIMIT WORKS:
SELECT * FROM your_table LIMIT 0, 10 ===>will display the first 10 results from the database.
SELECT * FROM your_table LIMIT 5, 5 ---> will show records 6, 7, 8, 9, and 10
First digit if start from (NOT) including.

SELECT * FROM sql_store.customers ORDER BY points DESC LIMIT 6, 3;


REGEX BASICS:
^ beginning with
$ ending with
| logical or
[abcd] any of these
[a-f] any of these

//SELECT * FROM customers WHERE last_name REGEXP '[a-h]e';

//USE sql_store;
SELECT * FROM sql_store.customers WHERE address LIKE '%trail%' OR '%avenue%';

//USE sql_store;
SELECT * FROM customers WHERE first_name REGEXP 'elka|ambur';

//USE sql_store;
SELECT * FROM customers WHERE last_name REGEXP 'EY$|ON$';

//SELECT * from sql_store.customers WHERE last_name REGEXP '^MY|^SE';

//SELECT * FROM sql_store.customers WHERE last_name REGEXP 'b[r|u]';


*/