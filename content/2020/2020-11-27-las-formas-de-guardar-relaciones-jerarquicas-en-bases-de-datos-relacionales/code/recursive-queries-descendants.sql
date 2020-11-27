WITH RECURSIVE descendants AS (
    SELECT p.* FROM products_categories p WHERE p.category_id = 5
    UNION ALL
    SELECT p.*FROM descendants d INNER JOIN products_categories p ON d.category_id = p.parent_id
) 
SELECT * FROM descendants p ORDER BY category_id;