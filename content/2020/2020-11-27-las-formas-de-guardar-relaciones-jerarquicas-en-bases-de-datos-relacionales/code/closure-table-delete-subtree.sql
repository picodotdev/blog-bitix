DELETE FROM products_categories_hierachy
    WHERE descendant_id IN (SELECT descendant_id
        FROM products_categories_hierachy
        WHERE ancestor_id = 2
    );