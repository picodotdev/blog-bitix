SELECT p.* FROM products_categories AS p
    INNER JOIN products_categories_hierachy AS h ON p.product_id = h.ancestor_id
    WHERE h.descendant_id = 7;