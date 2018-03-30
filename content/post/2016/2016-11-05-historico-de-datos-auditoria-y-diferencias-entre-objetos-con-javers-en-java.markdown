---
pid: 191
title: "Histórico de datos, auditoría y diferencias entre objetos con Javers en Java"
url: "/2016/11/historico-de-datos-auditoria-y-diferencias-entre-objetos-con-javers-en-java/"
aliases: ["/2016/11/historico-de-datos-y-diferencias-con-javers-en-java/", "/2016/11/historico-de-datos-y-diferencias-entre-objetos-con-javers-en-java/"]
date: 2016-11-05T11:00:00+01:00
rss: true
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
summary: "Por seguridad, por auditoría o histórico de datos una aplicación puede requerir no solo guardar los valores actuales de los datos que maneja sino también las versiones anteriores y los cambios en cada valor de los mismos. En Java hay una librería llamada Javers que nos proporciona funcionalidades como comparación, _snapshots_, persistencia y un lenguaje de consulta para hacer búsquedas."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.png" title1="Java" >}}

Por necesidades de negocio y requerimientos funcionales puede ser necesario guardar un histórico de ciertos datos de una aplicación en vez de solo la última versión de los datos. Tener solo la última versión de algunos datos puede no ser lo que se necesita. Por ejemplo, supongamos que una aplicación maneja una entidad de dominio producto y esta tiene un campo que es el precio y queremos guardar los cambios que se hacen a esta entidad para conocer el cambio de precio que han sufrido los productos. Otras necesidades pueden ser por auditoría o seguridad para saber que usuario ha hecho que cambios en los datos, para disponer de versiones anteriores de una entidad, comparar dos versiones de la misma entidad o lanzar consultas para obtener información de los cambios que se han producido.

Una librería que en Java nos ofrece toda esta información de auditoría es [Javers][javers] con la posibilidad de persistirla en diferentes sistemas, en las tradicionales base de datos relacionales o en una base de datos no SQL como [MongoDB][mongodb]. En la documentación encontramos como podemos [comparar dos objetos](http://javers.org/documentation/diff-examples/), [persistir cambios](http://javers.org/documentation/repository-examples/) o [lanzar consultas](http://javers.org/documentation/jql-examples/).

Javers diferencia dos tipos de objetos [Entities](http://javers.org/documentation/diff-examples/#compare-entities) o [ValueObjects](http://javers.org/documentation/diff-examples/#compare-valueobjects). Los ValueObjects son objetos _java bean_ tradicionales de Java que no tenen identificador asignado y no son persistibles pero se pueden usar para hacer comparaciones entre dos objetos según las propiedades de los _java beans_. Los objetos _java bean_ Entities tienen una propiedad que representa el identificativo de la entidad y las comparaciones se pueden hacer entre diferentes versiones del mismo.

En el siguiente ejemplo muestro como hacer comparaciones, como hacer cambios y persistirlos, como lanzar una consulta para obtener los cambios que se han producido u obtener _snapshots_ de versiones anteriores y como persistir estos cambios en una base de datos [PostgreSQL][postgresql] en la que utilizaré [Docker][docker]. En la primera sección del ejemplo se comparan dos objetos y obtienen sus diferencias, posteriormente se persisten varios cambios y finalmente se realiza una consulta para obtener los cambios que haya habido en la propiedad _price_.

{{< gist picodotdev 37deac8aa12db051e08a8253695e5fb4 "Main.java" >}}
{{< gist picodotdev 37deac8aa12db051e08a8253695e5fb4 "Category.java" >}}
{{< gist picodotdev 37deac8aa12db051e08a8253695e5fb4 "Product.java" >}}
{{< gist picodotdev 37deac8aa12db051e08a8253695e5fb4 "System.out" >}}
{{< gist picodotdev 37deac8aa12db051e08a8253695e5fb4 "docker-compose.yml" >}}

La información se persistirán en varias tablas en este caso en un base de datos relacional PostgreSQL que Javers creará al iniciarse la aplicación, ejecutada la aplicación encontraremos datos.

{{< gist picodotdev 37deac8aa12db051e08a8253695e5fb4 "tables.sql" >}}

{{% code git="blog-ejemplos/tree/master/HolaMundoJavers" command="./gradlew run" %}}

{{% /post %}}
