SELECT p2.* FROM products_categories AS p1
    INNER JOIN products_categories AS p2 ON p1.left BETWEEN p2.left AND p2.right
    WHERE p1.category_id = 7;