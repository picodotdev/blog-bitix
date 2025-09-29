SELECT * FROM products_categories AS p
    WHERE p.path LIKE '1,5,' || '%';