---
pid: 535
type: "post"
title: "Las formas de guardar relaciones jerárquicas en bases de datos relacionales"
url: "/2020/11/las-formas-de-guardar-relaciones-jerarquicas-en-bases-de-datos-relacionales/"
date: 2020-11-27T15:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:hierarchy-comparation.png"
tags: ["planeta-codigo", "programacion"]
summary: "Para implementar relaciones jerárquicas en base de datos relacionales hay varias soluciones conocidas. En este artículo comento las más conocidas con sus desventajas y cual elegir en función de si la base de datos soporta consultas recursivas o en caso de que no las soporte."
---

{{% post %}}

{{< logotype image1="postgresql.svg" >}}

Las bases de datos relacionales guardan los datos en tablas, filas y columnas. Los datos de unas tablas se relacionan con los de otras a través de las claves primarias y claves foráneas. Este modelo relacional permite guardar datos y es suficiente para muchos casos, habitualmente utilizando la tercera forma normal de [las 6+2 formas normales de las bases de datos relacionales][blogbitix-299].

Sin embargo, el modelo relacional en principio no se ajusta a guardar relaciones jerárquicas, hay varias soluciones para implementar este tipo de relaciones en los datos en una base de datos relacional. Algunas soluciones aunque válidas en la teoría tiene importantes limitaciones en su uso práctico. Algunas solución depende de que la base de datos ofrezcan el soporte para implementarla en caso contrario hay que elegir otra opción.

En el libro [SQL Antipatterns](https://amzn.to/2G2oRN1) se trata con detalle las diferentes las formas de guardar relaciones jerárquicas en bases de datos relacionales entre otros diversos aspectos y buenas prácticas.

{{< tableofcontents >}}

### Ejemplo de relación jerárquica

Las relaciones jerárquicas establecen unas normas en las relaciones de los elementos, los elementos se organizan en niveles en el que los elementos se relacionan como nivel por encima, por debajo o en el mismo nivel que otro. El número de niveles que tiene una estructura jerárquica es indefinido pudiendo tener cualquier profundidad.

Esta es una estructura pequeña jerárquica de productos con dos niveles de profundidad.

{{< code file="hierarchy.txt" language="plaintext" options="" >}}

### Formas de guardar relaciones jerárquicas

#### Adjacency List

La solución de listas adyacentes o _adjacency list_ se basa en añadir un campo adicional a la tabla en el que cada fila guarda el identificador del elemento por encima en la estructura jerárquica.

Esta solución utiliza un modelo de datos sencillo, es fácil de añadir nuevos elementos a la estructura jerárquica además de mantener la integridad referencial.

{{< code file="adjacency-list-data.sql" language="sql" options="" >}}
{{< code file="adjacency-list-data.sql" language="plaintext" options="" >}}

Sus desventajas son que las consultas para obtener los ascendientes o descendientes de un elemento son complicadas e ineficientes además de no soportar cualesquiera niveles de profundidad.

Las consultas para obtener los ascendientes y los descendientes son las siguientes.

{{< code file="adjacency-list-ancestors.sql" language="sql" options="" >}}
{{< code file="adjacency-list-ancestors.out" language="plaintext" options="" >}}
{{< code file="adjacency-list-descendants.sql" language="sql" options="" >}}
{{< code file="adjacency-list-ancestors.out" language="plaintext" options="" >}}

Para este caso y con consultas recursivas el ejemplo se puede probar con [Docker][docker] y la base de datos PostgreSQL.

{{< code file="docker-run.sh" language="plaintext" options="" >}}
{{< code file="insert-data.sql" language="sql" options="" >}}

#### Consultas recursivas

Las otras implementaciones están originadas a la limitación del modelo relacional y del lenguaje SQL. En las últimas versiones de las bases de datos el lenguaje SQL soporta consultas recursivas o _recursive queries_ con las que implementar la sencilla solución de _adjacency list_ sin sus desventajas. [MySQL][mysql] soporta consultas recursivas desde la versión 8 y [PostgreSQL][postgresql] ya era posible en la versión 9.

Las consultas para obtener los ascendientes y los descendientes son las siguientes.

{{< code file="recursive-queries-ancestors.sql" language="sql" options="" >}}
{{< code file="recursive-queries-ancestors.out" language="plaintext" options="" >}}
{{< code file="recursive-queries-descendants.sql" language="sql" options="" >}}
{{< code file="recursive-queries-descendants.out" language="plaintext" options="" >}}

#### Closure Table

Cuando la base de datos no soporta consultas recursivas una solución alternativa es la de _closure table_, esta se basa en guardar las relaciones entre los nodos en una tabla adicional, se guardan la relación de un nodo con todos sus descendientes o las de un nodo con todos sus ascendientes además de consigo mismo.

{{< code file="closure-table-data-1.sql" language="sql" options="" >}}
{{< code file="closure-table-data-1.out" language="plaintext" options="" >}}
{{< code file="closure-table-data-2.sql" language="sql" options="" >}}
{{< code file="closure-table-data-2.out" language="plaintext" options="" >}}

{{< image
    gallery="true"
    image1="image:closure-table-hierarchy.png" optionsthumb1="300x200" title1="Esquema de las relaciones entre los nodos"
    caption="Esquema de las relaciones entre los nodos" >}}

En este caso las consultas de búsqueda son eficientes, las de inserción son sencillas y hay integridad referencial. Esta solución permite a la misma fila formar parte de varias estructuras jerárquicas al mismo tiempo. Su desventaja es que requiere una tabla adicional.

Las consultas para obtener los ascendientes, los descendientes son las siguientes.

{{< code file="closure-table-ancestors.sql" language="sql" options="" >}}
{{< code file="closure-table-descendants.sql" language="sql" options="" >}}

La de inserción.

{{< code file="closure-table-insert.sql" language="sql" options="" >}}

Y para eliminar una rama del árbol.

{{< code file="closure-table-delete-subtree.sql" language="sql" options="" >}}

### Otras implementaciones

Estas otras soluciones son válidas para implementar estructuras jerárquicas en bases de datos relacionales. aunque en algunos aspectos con desventajas sobre las recomendadas de consultas recursivas si están soportadas por la base de datos o la solución de _closure table_.

#### Path Enumeration

La solución _path enumeration_ se basa en añadir una columna que contiene en una cadena la colección de identificadores de sus ascendientes incluido el propio nodo.

{{< code file="path-enumeration-data.sql" language="sql" options="" >}}
{{< code file="path-enumeration-data.out" language="plaintext" options="" >}}

Su desventaja es que al igual que la solución de _nested set_ no ofrece integridad referencial en los identificadores incluidos en la cadena que guarda la jerarquía. Sin embargo, las consultas para obtener los ascendientes, descendientes y realizar una inserción son sencillas.

Las consultas para obtener los ascendientes y los descendientes.

{{< code file="path-enumeration-ancestors.sql" language="sql" options="" >}}
{{< code file="path-enumeration-descendants.sql" language="sql" options="" >}}

#### Nested Set

La solución de _nested set_ es una solución a las limitaciones del modelo relacional para guardar relaciones jerárquicas. Se basa en añadir a la tabla dos nuevos campos con el rango de nodos contenidos en el nivel inferior.

Esta solución también tiene un modelo de datos sencillo, no requiere tablas adicionales, es fácil de añadir un nuevo nodo a la jerarquía y las consultas para buscar los ascendientes y descendientes son rápidas.

{{< code file="nested-set-data.sql" language="sql" options="" >}}
{{< code file="nested-set-data.out" language="plaintext" options="" >}}

Sus desventajas es que no mantiene la integridad referencial. Es más adecuada cuando la estructura de datos no cambia con frecuencia ya que las inserciones y eliminaciones requieren actualizar los valores de los rangos de gran cantidad de nodos. Si se inserta un nuevo nodo en la categoría de procesadores, por ejemplo ARM, su _left_ y _right_ serán 14 y 15 y a todos los _right_ mayor o igual que 14 hay que sumarle más 2.

Para hacer eficientes las consultas que busque nodos inmediatamente inferiores a un determinado nodo requiere añadir una una columna adicional para guardar el nivel del nodo.

Las consultas para obtener los ascendientes, los descendientes y de inserción de un nodo son las siguientes.

{{< code file="nested-set-ancestors.sql" language="sql" options="" >}}
{{< code file="nested-set-descendants.sql" language="sql" options="" >}}

### ¿Cúal es la mejor solución para estructuras jerárquicas en bases de datos relacionales?

Si la base de datos soporta consultas recursivas la mejor solución para estructuras jerárquicas en bases de datos relacionales es la de consultas recursivas. Si la base de datos no soporta consultas recursivas la solución de _closure table_ ofrece integridad referencial y es sencilla.

_Nested set_ es adecuada solo sin la estructura de datos jerárquica no cambia con frecuencia y la de _adjacency list_ sin consultas recursivas es adecuada si el rendimiento no es significativo para la aplicación y el nivel de profundidad máximo de la estructura es conocido y está limitado a unos pocos.

Esta es una pequeña comparación entre cada una de las soluciones en su dificultad de implementación y si soporta integridad referencial.

{{< image
    gallery="true"
    image1="image:hierarchy-comparation.png" optionsthumb1="600x450" title1="Comparación entre las diferentes soluciones"
    caption="Comparación entre las diferentes soluciones" >}}

{{< reference >}}
* [Adjacency list](https://en.wikipedia.org/wiki/Adjacency_list)
* [Nested set model](https://en.wikipedia.org/wiki/Nested_set_model)
* [Hierarchical and recursive queries in SQL](https://en.wikipedia.org/wiki/Hierarchical_and_recursive_queries_in_SQL)
{{< /reference >}}

{{% /post %}}
