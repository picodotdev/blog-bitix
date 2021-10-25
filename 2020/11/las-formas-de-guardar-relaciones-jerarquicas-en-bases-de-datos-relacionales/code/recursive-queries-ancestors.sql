WITH RECURSIVE ancestors AS (
    SELECT c.* FROM products_categories c WHERE c.category_id = 7
    UNION ALL
    SELECT c.* AS depth FROM ancestors a 
        INNER JOIN products_categories c ON a.parent_id = c.category_id
)
SELECT * FROM ancestors ORDER BY category_id;