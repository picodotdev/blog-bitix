---
pid: 531
type: "post"
title: "Herramienta para ejecutar consultas SQL en la base de datos de producción"
url: "/2020/11/herramienta-para-ejecutar-consultas-sql-en-la-base-de-datos-de-produccion/"
date: 2020-11-13T17:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:sqlpad.png"
tags: ["software-libre", "programacion"]
summary: "No siempre las aplicaciones proporcionan los datos que se necesitan. A veces para obtener cierta información de forma puntual o para corregir un dato que no se puede hacer desde la aplicación es necesario lanzar una consulta SQL a la base de datos relacional. Esto no es lo ideal, simplemente en ocasiones es lo más simple y rápido. Por otro lado, para tareas de análisis algunos usuarios necesitan una forma de tener acceso a los datos y obtener gráficas para analizarlos. En el artículo comento varias herramientas para tener acceso a las diferentes bases de datos ya sean de producción o del entorno de QA."
---

{{% post %}}

Sí, es cierto, lanzar consultas SQL directamente en la base de datos de producción o una réplica no es lo ideal ni debería ser habitual, sin embargo, a veces tener acceso a los datos de la base de datos de producción es la forma más rápida y sencilla de obtener información para resolver un _bug_ del que ni siquiera un [sistema centralizado de almacenamiento de trazas como ELK][blogbitix-517] proporciona información relevante o simplemente porque alguien del departamento financiero, _marketing_ u otro departamento solicita una información que necesita.

En ambos casos del _bug_ o de alguien que necesita información en ocasiones tienen la mala costumbre de necesitar ser atendidos con rapidez. Si para obtener la información se necesita un proceso que involucra a varias personas, cada una con sus propias tareas y prioridades, el proceso es lento y el demandante de esa información acabará poco contento quejándose del departamento de IT. El proceso también será lento si es complicado o sólo determinadas personas tienen los permisos necesarios pare realizarlo o con riesgos si las herramientas que se usan otorgan gran poder pero requieren gran responsabilidad.

Proporcionando una herramienta a modo de sírvete tú mismo uno se sorprende de las consultas que alguien del departamento financiero o de _marketing_ son capaces de hacer ellos mismos para obtener la información que necesitan si tienen el interés de aprender algo de SQL. Lo cual es beneficioso en varios sentidos, ellos son más autónomos en su trabajo y las personas de IT no se ven interrumpidas para atender este tipo de tareas, sólo cuando la consulta tiene cierta dificultad.

Si además como en los microservicios hay múltiples bases de datos para varios de ellos tener acceso a todas las bases de datos es más complicado de gestionar. Por otro lado, al decir la base de datos de producción también es válido para tener acceso a las bases de datos del entorno de QA.

{{< tableofcontents >}}

### Herramienta para obtener y modificar datos de una base de datos relacional

[SQLPad][sqlpad] es una pequeña herramienta que actúa como cliente de una base de datos relacional con la que es posible lanzar consultas SQL mediante una interfaz web. Esta herramienta hace muy sencillo el acceso a la base de datos de producción, entorno de QA o a una de sus réplicas, dado que utiliza una interfaz web no es necesario instalar ni configurar ninguna herramienta adicional, solo requiere un navegador web que todo ordenador ya posee.

Simplemente es necesario seleccionar la base de datos de la que se quieren los datos y ejecutar la consulta SQL deseada para obtener los datos como resultado. Permite explorar los esquemas de la base de datos, las tablas, los campos y cuales son sus tipos.

SQLPad tiene una licencia de software libre y soporta las bases de datos relacionales más populares como [Postgres][postgresql], [MySQL][mysql], [SQL Server][microsoft-sqlserver], [Cassandra][cassandra], [Google BigQuery][google-bigquery], [SQLite][sqllite] entre otras.

{{< image
    gallery="true"
    image1="image:sqlpad.png" optionsthumb1="600x450" title1="SQLPad"
    caption="SQLPad" >}}

Otra funcionalidad habitual es poder exportar los datos en formato CSV o xlsx para procesarlos con algún comando o programa en el equipo en local o simplemente para hacer algún tipo de tratamiento con el programa ofimático de hoja de cálculo.

{{< code file="data.csv" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:onlyoffice-csv.png" optionsthumb1="300x200" title1="OnlyOffice CSV"
    caption="OnlyOffice CSV" >}}

Aunque SQLPad no ofrece la potencia de otras herramientas específicas para la tarea permite visualizar los datos de forma básica en gráficas. Por ejemplo obtener un gráfico de barras de una agrupación de datos.

{{< code file="docker-compose-up.sh" language="bash" options="" >}}
{{< image
    gallery="true"
    image1="image:sqlpad-visualization-1.png" optionsthumb1="300x200" title1="SQLPad Visualization"
    image2="image:sqlpad-visualization-2.png" optionsthumb2="300x200" title2="SQLPad Visualization"
    caption="SQLPad Visualization" >}}

Permite además guardar las consultas que sirve para compartir entre diferentes miembros de un equipo aquellas que son habituales o proporcionarles a personas que no saben SQL la consulta que deben ejecutar para obtener los datos que necesitan de forma periódica.

{{< image
    gallery="true"
    image1="image:sqlpad-queries.png" optionsthumb1="300x200" title1="Consultas compartidas en SQLPad"
    caption="Consultas compartidas en SQLPad" >}}

Una herramienta que proporciona acceso a los datos de producción requiere autenticación y permisos para otorgar únicamente el acceso a las bases de datos o conceder acceso de solo lectura a los datos. Los usuarios con el rol de administrador puede crear conexiones a bases de datos, crear usuarios y definir qué conexiones pueden utilizar cada usuario con una fecha de expiración para cada usuario. Para crear una conexión de solo lectura hay que crear un usuario en la base de datos que únicamente tenga permisos de solo lectura, posteriormente en SQLPad para definir una conexión de solo lectura se usa este usuario. Del mismo modo con los permisos del usuario de conexión de la base de datos es posible limitar las tablas a las que se tienen acceso u otros permisos que permita la base de datos.

SQLPad ofrece un contenedor de [Docker][docker] y un archivo de [Docker Compose][docker-compose] con el que iniciar la herramienta. El siguiente archivo de Docker Compose define un contenedor con una base de datos PostgreSQL a la que se conecta e inicializa el contenedor de SQLPad. Los archivos del [código fuente completo del ejemplo](https://github.com/sqlpad/sqlpad/tree/master/docker-examples/postgres-docker-compose) incluye algunos adicionales, SQLPad se accede con la siguiente URL _http:\/\/localhost:3000/_ que solicita una credenciales _admin@sqlpad.com/admin_.

{{< code file="docker-compose.yml" language="bash" options="" >}}
{{< code file="docker-compose-up.sh" language="bash" options="" >}}

### Herramientas para visualizar datos

Obtener los datos y descargarlos en formato csv o xlsx permite extraer información, para comprender mejor un gran volumen de datos se utilizan diferentes tipos de gráficas.

SQLPad tiene una funcionalidad básica para representar los datos en formato gráfico pero no es una herramienta especializada en esta tarea. 

[Redash], [Metabase][metabase], [Apache Superset][apache-superset] son herramientas dedicadas a construir gráficas a partir de datos de la categoría inteligencia empresarial o _business intelligence_. Permiten crear paneles de control con la información que se desee y ver la evolución y tendencia de los datos. Esta información sirve como apoyo para tomar decisiones basadas en los datos.

Algunas herramientas tienen un coste si se usan con el _hosting_ que ofrecen pero son gratuitas si se hospeda en infraestructura propia.

{{< image
    gallery="true"
    image1="image:redash.png" optionsthumb1="300x200" title1="Redash"
    image2="image:metabase.png" optionsthumb2="300x200" title2="Metabase"
    image3="image:apache-superset.png" optionsthumb3="300x200" title3="Apache Superset"
    caption="Redash, Metabase y Apache Superset" >}}

### Cómo proteger los datos

Además de limitar a que bases de datos tiene permisos de acceso cada usuario algunos datos almacenados son sensibles, por motivos de seguridad si se tratan de contraseñas si no se utiliza [una forma correcta de guardar contraseñas con _salted-password-hashing_ en la base de datos][blogbitix-75], datos personales protegidos por leyes que de no custodiar correctamente los datos en caso de filtración suponen sanciones como importantes multas económicas o datos de tarjetas de crédito, códigos de seguridad, fechas de expiración o cuentas bancarias.

Una forma de proteger los datos aún teniendo acceso a ellos es cifrarlos es utilizando [Vault][vault] con su funcionalidad de [protección de datos](https://www.hashicorp.com/products/vault/data-protection). Vault permite actuar como servicio para cifrar los datos a guardar en la base de datos y descifrar los datos al recuperarlos de modo que aún teniendo acceso a la base de datos no supone ningún problema de seguridad. Aún así siempre es recomendable otorgar a los usuarios los permisos mínimos que necesiten o limitar únicamente a ciertos usuarios los permisos.

{{< image
    gallery="true"
    image1="image:vault-encryption.png" optionsthumb1="300x200" title1="Vault Encryption"
    caption="Vault Encryption" >}}

{{< reference >}}
* [Artículos de la serie Docker][blogbitix-serie-docker]
{{< /reference >}}

{{% /post %}}
