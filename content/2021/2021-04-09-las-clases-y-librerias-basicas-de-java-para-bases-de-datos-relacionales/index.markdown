---
pid: 566
type: "post"
title: "Las clases y librerías básicas de Java para bases de datos relacionales"
url: "/2021/04/las-clases-y-librerias-basicas-de-java-para-bases-de-datos-relacionales/"
date: 2021-04-09T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Las aplicaciones suelen delegar el guardado del estado y de los datos que tratan en sistemas especializados en almacenar datos. Las bases de datos relacionales son sistemas que han probado su eficacia y utilizad durante las últimas décadas de la computación. Aún con la aparición de múltiples bases de datos NoSQL alternativas las bases de datos relacionales se seguirán utilizando o incluso combinando diferentes tipos de bases de datos en un mismo sistema. Java proporciona desde sus primeras versiones el paquete _java.sql_ con varias clases para el acceso a bases de datos relacionales, aunque el acceso a las bases de datos relacionales se suele utilizar a través de librerías es útil conocer estas clases de Java que constituyen los conceptos fundamentales de acceso a base de datos y que las librerías internamente son las que usan."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

La mayoría de las aplicaciones usan una base de datos para guardar persistir, consultar datos o guardar el estado de la aplicación. Las bases de datos son los sistemas especializados en guardar la información de las aplicaciones.

Dentro de la categoría de bases de datos las relacionales son unas de las más utilizadas por sus propiedades [ACID][acid] y el potente lenguaje de consultas SQL. Las bases de datos relacionales siguen siendo adecuadas para muchos usos y propósitos aún con la aparición de las bases de datos NoSQL también útiles cuando existe ciertos requerimientos.

{{< tableofcontents >}}

### Clases del JDK para acceso a base de datos relacionales

Java ofrece soporte para las bases de datos relacionales desde prácticamente las primeras versiones del JDK hasta día de hoy incorporando un conjunto de clases en el paquete _java.sql_ en la denominada API en Java de [Java Database Connectivity][wikipedia-jdbc] o JDBC.

Las principales clases de la API de JDBC son las clases [Statement](javadoc11:java.sql/java/sql/Statement.html) y [PreparedStatement](javadoc11:java.sql/java/sql/PreparedStatement.html) que representan la sentencia SQL ya sea de inserción, actualización, eliminación o consulta así como las sentencias DDL para la creación de tablas, índices, campos o procedimientos almacenados. Normalmente se utiliza la clase _PreparedStatement_ ya que tiene beneficios en cuanto a rendimiento al ejecutarse de forma repetida y utilizada correctamente permite evitar el grave problema de seguridad de _SQL injection_ común en las aplicaciones que construyen de forma dinámica con concatenaciones sentencias SQL utilizando datos procedentes de fuentes no confiables como parámetros de una petición HTTP, de JSON u otras fuentes externas a la aplicación.

La clase [ResultSet](javadoc11:java.sql/java/sql/ResultSet.html) es la clase que proporciona el acceso a los datos cuando se ejecuta una sentencia SQL de consulta, la clase se itera en los resultados y se obtienen los datos según los nombres o índices asignados en la consulta para las columnas.

La clase [Connection](javadoc11:java.sql/java/sql/Connection.html) representa una conexión a una base de datos, hay que crear una conexión ya que las bases de datos trabajan con una arquitectura de cliente y servidor, la aplicación actúa de cliente y la base de datos actúa de servidor. A través de la clase _Connection_ se inicia una transacción y finaliza con el _commit_ o el _rollback_. Para crear la conexión de la base de datos se proporcionan las credenciales de usuario y contraseña.

Las bases de datos relacionales soportan transacciones para proporcionar las propiedades ACID para lo que se utilizan las transacciones. Atomicidad donde un grupo de operaciones individuales se ejecutan todas o ninguna, consistencia mediante la cual los cambios son válidos según las reglas incluyendo restricciones, cambios en cascada, y disparadores, aislamiento donde los cambios de una transacción no se ven afectados por los cambios realizados en otras transacciones concurrentes y finalmente durabilidad que garantiza que en caso de completarse la transacción perdura en el tiempo aún cuando el sistema sufra un fallo posterior.

Crear una conexión a una base de datos es costoso, para evitar incurrir en este tiempo de creación y destrucción de conexiones o limitar el número de conexiones que una aplicación utiliza como máximo las aplicaciones utilizan un _pool_ de conexiones. Las conexiones se crean al iniciar la aplicación o bajo demanda según se van necesitando más hasta el límite máximo definido. Cuando la aplicación necesita una conexión la obtiene de forma rápida del _pool_ de conexiones y cuando termina de utilizarla la devuelve al _pool_ de conexiones para que sea reutilizada en posteriores usos.

Cada base de datos utiliza un protocolo diferente de comunicación con los clientes por lo que es necesario un componente que abstrae de las peculiaridades de cada base de datos y proporcione un marco común de trabajo independiente de cada base de datos. Cada base de datos requiere de un [Driver](javadoc11:java.sql/java/sql/Driver.html) compatible también con la versión de la base de datos. Generalmente, son los desarrolladores de la propia base de datos los que proporcionan un _driver_ específico adecuado para el acceso a la base de datos desde Java que cumple con las APIs de JDBC.

En el [tutorial sobre SQL con Java](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) se explican los conceptos básicos y fundamentales para usar bases de datos relacionales.

### Ejemplo de conexión y consulta a un base de datos relacional con la API de Java

En este ejemplo de código se muestra el uso de las clases fundamentales de Java para usar una base de datos relacional. El primer paso es establecer una conexión con la base de datos, en este caso usando la base de datos H2 en memoria.

Posteriormente se ejecuta una sentencia DDL para crear una tabla, se insertan varias filas con la sentencia _insert_ y se obtienen los datos de la tabla con una sentencia _select_. Después de las inserciones se realiza un _commit_ que completa una transacción en la base de datos, si en vez del _commit_ se hiciese un _rollback_ al obtener los datos con la consulta posterior la tabla aparecería vacía.

Las clases _Connection_, _Statemente_, _PreparedStatement_ y _ResultSet_ al finalizar su uso hay que invocar su método _close_ para liberar los recursos que tienen reservados, especialmente en el caso de las conexiones ya que son un recurso limitado. Estas clases implementan la interfaz [AutoCloseable](javadoc11:java.base/java/lang/AutoCloseable.html) con lo que son adecuadas para las sentencias _try-with-resources_ de Java.

#### Establecer la conexión a la base de datos

Por defecto después de cada sentencia Java emite un _commit_, esto no es lo deseado en el caso de querer agrupar la ejecución de varias sentencias en una transacción, para evitarlo hay que usar la opción _setAutoCommit_ a _false_.

{{< code file="Main-1.java" language="java" options="" >}}

#### Ejecutar una sentencia con _Statement_

{{< code file="Main-2.java" language="java" options="" >}}

#### Ejecutar una sentencia con _PreparedStatement_

{{< code file="Main-3.java" language="java" options="" >}}

#### Ejecutar una consulta

{{< code file="Main-4.java" language="java" options="" >}}

#### Cerrar la conexión de forma explícita

{{< code file="Main-5.java" language="java" options="" >}}

#### Resultado 

El resultado del programa en la terminal es el siguiente.

{{< code file="System.out" language="plaintext" options="" >}}

#### Dependencia con el _driver_ de la base de datos

En el archivo de construcción hay quu añadir la dependencia que contiene el _driver_ para la base de datos a conectarse.

{{< code file="build.gradle" language="groovy" options="" >}}

### El problema de seguridad de _SQL injection_

El problema de seguridad de _SQL injection_ es un grave problema de seguridad que afecta a las aplicaciones que construyen sentencias de forma dinámica a partir de datos provenientes de origen no confiable. Un origen no confiable es cualquier dato proveniente de forma externa a la aplicación, en el caso de las aplicaciones web o servicios REST es un parámetro de la petición o un dato de un JSON.

El _SQL injection_ es un grave problema de seguridad ya que permite al atacante tener acceso a datos de la base de datos, obtener acceso con una cuenta de otro usuario o realizar operaciones sobre los datos de forma no autorizada. El problema se produce en la construcción de forma dinámica de la sentencia SQL mediante la concatenación de cadenas y datos de origen no confiable.

Las siguientes sentencias SQL sufren del problema de _SQL injection_, en la primera un atacante puede obtener un dato de cualquier columna de la tabla y con la segunda ejecutar una sentencia en este caso para eliminar todas las filas de cualquier tabla.

En la primera cambiando el valor de _column_ se obtiene el dato de cualquier columna de la tabla, por ejemplo el campo _password_.

{{< code file="sql-injection-1.sql" language="sql" options="" >}}

En esta sentencia se finaliza la sentencia original con _;_ y se inicia otra lo que provoca la eliminación de una tabla, con un valor para _user\_id_ especialmente construido para ejecutar la sentencia maliciosa de eliminación de la tabla.

{{< code file="sql-injection-2.sql" language="sql" options="" >}}

La solución al problema de seguridad de _SQL injection_ en Java es no construir la sentencia de forma dinámica mediante concatenación de cadenas utilizando la clase _PreparedStatement_ con argumentos para los datos que se inserten en la sentencia SQL.

{{< code file="sql-injection-prepared-statement.java" language="java" options="" >}}

### Librerías de persistencia en Java

Habitualmente no se utilizan directamente las clases de la API de Java sino que se utilizan otras librerías de más alto nivel. Una de las más conocidas es [Hibernate][hibernate], es un ORM que proporciona acceso a los datos con una correspondencia entre el modelo relacional de las bases de datos y el modelo de objetos de Java. La aplicación trabaja con objetos y relaciones entre los objetos e Hibernate se encarga de transformar esos objetos en el modelo relacional de la base de datos, la aplicación no ejecuta sentencias SQL de forma directa sino que es Hibernate el encargado de emitir las sentencias adecuadas según los cambios realizados en los datos. Es la implementación más utilizada de ORM en Java para la especificación JPA.

* [Aplicación de ejemplo con Hibernate, jOOQ y Liquibase](https://github.com/picodotdev/blog-ejemplos/tree/master/PlugInTapestry)

[Spring Data][spring-data] es una capa de abstracción para el acceso a datos ya sean de un modelo relacional, de bases de datos NoSQL o algunos otros sistemas de datos. Spring Data hace más sencillo el acceso a los datos utilizando de forma subyacente JDBC o JPA. Spring Data proporciona algunas clases e interfaces que la aplicación implementa.

[jOOQ][jooq] también es otra librería de acceso a bases de datos relacionales, proporciona DSL para la construcción de sentencias SQL mediante un API Java. A diferencia de JPA con su lenguaje JPQL, jOOQ soporta características avanzadas del lenguaje SQL como [_windows functions_](https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/window-functions/). Otra ventaja de jOOQ es que al utilizar su DSL el compilador de Java realiza validación de tipos y comprobaciones en la sintaxis de la construcción de la SQL.

* [Alternativa a Hibernate u ORM y ejemplo de jOOQ][blogbitix-82]

Algunas aplicaciones combinan el uso de varias de estas librerías en la misma aplicación según el caso, por ejemplo utilizando Hibernate para el modelo de escritura y jOOQ o Spring Data para el modelo de lectura. También es posible utilizar jOOQ para generar las sentencias SQL y posteriormente ejecutarlas con Hibernate o Spring Data.

[Liquibase][liquibase] es otra librería de utilidad que permite lanzar _scripts_ de migración con sentencias SQL para realizar cambios en la base de datos como modificar el esquema de tablas, insertar, actualizar o eliminar datos. Es necesario si los cambios en el código en una nueva versión de la aplicación requiere cambios en el esquema de la base de datos.

Estas no son las únicas librerías existentes pero sí son de las más conocidas y utilizadas.

{{< image
    gallery="false"
    image1="logotype:hibernate.svg" optionsthumb1="200x150" title1="Hibernate"
    image2="logotype:spring.svg" optionsthumb2="200x150" title2="Spring"
    image3="logotype:jooq.png" optionsthumb3="200x150" title3="jOOQ" >}}

### Bases de datos relacionales

Las bases de datos de software libre más utilizadas son [PostgreSQL][postgresql], [MariaDB][mariadb] y [MySQL][mysql] que rivalizan con la base de datos [Oracle][oracle-database] comercial. PostgreSQL es adecuada incluso para organizaciones y proyectos de gran tamaño.

Otras bases de datos relevantes son [H2][h2] una base de datos implementada en Java con características avanzadas que es posible utilizar para los teses de integración al no requerir de un servidor y ser posible ejecutarla en memoria. Para realizar las [pruebas de integración utilizando la misma base de datos que en producción][blogbitix-490] es posible utilizar [Testcontainers][testcontainers] que utiliza [Docker][docker] para iniciar una instancia de la base de datos en un contenedor.

La conexión a la base de datos por seguridad requiere de un usuario y contraseña que la aplicación ha de conocer, para aún mayor seguridad es posible [generar las credenciales de conexión a la base de datos de forma dinámica por Vault en una aplicación de Spring](https://picodotdev.github.io/blog-bitix/2019/08/utilizar-credenciales-de-conexion-a-la-base-de-datos-generadas-por-vault-en-una-aplicacion-de-spring/).

{{< image
    gallery="false"
    image1="logotype:postgresql.svg" optionsthumb1="200x150" title1="PostgreSQL"
    image2="logotype:mariadb.svg" optionsthumb2="200x150" title2="MariaDB"
    image3="logotype:mysql.svg" optionsthumb3="200x150" title3="MySQL" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaSql" command="./gradlew run" %}}

{{< reference >}}
* [Introducción y características de Docker](https://picodotdev.github.io/blog-bitix/2014/10/introduccion-y-caracteristicas-de-docker/)
{{< /reference >}}

{{% /post %}}
