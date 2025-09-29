CREATE TABLE products_categories (
    category_id integer NOT NULL,
    name character varying(255) NOT NULL,
    parent_id integer
);

INSERT INTO products_categories (category_id, name, parent_id) VALUES
    (1, 'Componentes', null),
    (2, 'Placas base', 1),
    (3, 'ATX', 2),
    (4, 'ITX', 2),
    (5, 'Procesadores', 1),
    (6, 'Intel', 5),
    (7, 'AMD', 5),
    (8, 'Memoria RAM', 1),
    (9, 'Almacenamiento SSD', 1),
    (10, 'Tarjetas gráficas', 1),
    (11, 'NVIDIA', 10),
    (12, 'AMD', 10);