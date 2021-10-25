SELECT p1.*, p2.*, p3.*, p4.* FROM products_categories p1
    LEFT OUTER JOIN products_categories p2 ON p2.parent_id = p1.category_id
    LEFT OUTER JOIN products_categories p3 ON p3.parent_id = p2.category_id
    LEFT OUTER JOIN products_categories p4 ON p4.parent_id = p3.category_id
    WHERE p1.category_id = 5;