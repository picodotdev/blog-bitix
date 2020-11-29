SELECT c.* FROM products_categories AS p
    INNER JOIN products_categories_hierarchy AS h ON p.product_id = h.descendant_od
    WHERE h.ancestor_id = 1;