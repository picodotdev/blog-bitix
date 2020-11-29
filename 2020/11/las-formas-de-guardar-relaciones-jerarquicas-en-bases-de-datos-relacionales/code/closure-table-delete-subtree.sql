DELETE FROM products_categories_hierarchy
    WHERE descendant_id IN (SELECT descendant_id
        FROM products_categories_hierarchy
        WHERE ancestor_id = 2
    );