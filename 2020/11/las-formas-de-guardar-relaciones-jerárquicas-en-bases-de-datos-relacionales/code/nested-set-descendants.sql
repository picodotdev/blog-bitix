SELECT p2.* FROM products_categories AS p1
    INNER JOIN products_categories_hierachy AS p2 ON p2.left BETWEEN p1.left AND p1.right
    WHERE p1.category_id = 5;