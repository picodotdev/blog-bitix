INSERT INTO products_categories (category_id, name, parent_id) VALUES
    (1, 'ARM', null);
INSERT INTO products_categories_hierarchy (ancestor, descendant)
    SELECT h.ancestor_id, 13 FROM products_categories_hierarchy AS h
        WHERE h.descendant_id = 5
    UNION ALL
        SELECT 13, 13;