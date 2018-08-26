---
pid: 284
title: "Definir nuevos tipos de datos escalares en GraphQL"
url: "/2017/12/definir-nuevos-tipos-de-datos-escalares-en-graphql/"
date: 2017-12-01T10:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
series: ["graphql"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="graphql.svg" title1="GraphQL" width1="200" >}}

[GraphQL es una alternativa a una interfaz REST][blogbitix-275] con las ventajas de permitir al consumidor obtener únicamente los datos que requiere y realizar varias consultas en una misma petición.

[GraphQL][graphql] por defecto soporta un conjunto de tipos escalares en los datos entre los que están varios numéricos, cadenas, booleanos, enumerados además de los tipos o estructuras de datos definidos en la interfaz del servicio. Sin embargo, si es necesario es posible definir nuevos tipos de datos escalares como podría ser el caso de un tipo de dato para representar una fecha fecha y otro de importe monetario.

El objeto en Java que representa una fecha con Java 8 sería [LocalDate](https://docs.oracle.com/javase/9/docs/api/java/time/LocalDate.html) y la clase para el importe monetario podría ser un [BigDecimal](https://docs.oracle.com/javase/9/docs/api/java/math/BigDecimal.html) o alguna de [la librería JavaMoney][blogbitix-90].

Para que GraphQL soporte un nuevo tipo de dato escalar es necesario implementar una clase que realice la conversión. Esta clase se encarga de realizar la conversión entre el escalar añadido a una representación a devolver en las respuestas de las peticiones y la conversión entre la representación en consultas al tipo de dato hay que proporcionar al servicio. La clase debe implementar la interfaz [Coercing](https://github.com/graphql-java/graphql-java/blob/master/src/main/java/graphql/schema/Coercing.java) y construyendo un objeto [GraphQLScalarType](https://github.com/graphql-java/graphql-java/blob/master/src/main/java/graphql/schema/GraphQLScalarType.java) proporcionárselo a GraphQL en la definición del servicio.

{{< gist picodotdev ae1583aaa3182006533adad61ee73921 "LocalDateCoercing.java" >}}

Al definir el esquema se proporciona con el método _scalars_ una lista con los tipos de datos escalares adicionales, en este caso una instancia de _GraphQLScalarType_ con una instancia de _LocalDateCoercing_. Además en el descriptor del esquema hay que declarar el nuevo escalar con la palabra clave _scalar_.

{{< gist picodotdev ae1583aaa3182006533adad61ee73921 "Main.java" >}}
{{< gist picodotdev ae1583aaa3182006533adad61ee73921 "library.graphqls" >}}
{{< gist picodotdev ae1583aaa3182006533adad61ee73921 "LibraryRepository.java" >}}

Añadiendo al tipo _Book_ una fecha de publicación usando este nuevo tipo escalar al realizar una consulta y devolver el dato se realiza la conversión.

{{< gist picodotdev ae1583aaa3182006533adad61ee73921 "curl.sh" >}}

{{% code git="blog-ejemplos/tree/master/GraphQL" command="./gradlew run" note="Requiere Java 9+ o Docker" %}}

{{% /post %}}
