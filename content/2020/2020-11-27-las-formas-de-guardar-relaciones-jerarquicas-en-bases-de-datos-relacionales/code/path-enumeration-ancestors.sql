SELECT * FROM products_categories AS p
    WHERE '1,5,7,' LIKE p.path || '%';