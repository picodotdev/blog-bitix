---
pid: 511
type: "post"
title: "Añadir descripciones y documentar los campos de GraphQL"
url: "/2020/08/anadir-descripciones-y-documentar-los-campos-de-graphql/"
date: 2020-08-28T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:graphiql-documentation.webp"
tags: ["java", "planeta-codigo"]
series: ["graphql"]
summary: "A diferencia de una API basada REST una API basada GraphQL posee un esquema en el que quedan definidos los tipos, propiedades y tipos de esas propiedades. Para suplir las carencias de una API basada en REST se suele utilizar #Swagger como documentación y entorno de pruebas. GraphQL incluye la documentación en el propio código fuente y ofrece un IDE sin necesidad de herramientas adicionales. En el propio esquema de la API basada en GraphQL se pueden añadir descripciones a los tipos y propiedades para mayor detalle."
---

{{% post %}}

{{< logotype image1="graphql.svg" >}}

Una de las mayores dificultades de usar una API es disponer una documentación clara y completa para conocer los _endpoints_ que dispone, los parámetros que acepta, sus tipos y las estructuras de datos devueltas, los códigos de estado y error que devuelve asi como un entorno para proba la API en ejecución. Una API REST no ofrece ningún soporte para suplir sus carencias de documentar toda esta información y se suele recurrir a alguna otra herramienta como [Swagger][swagger].

En [GraphQL][GraphQL] mucho de esto se proporciona en la definición del esquema de GraphQL donde quedan definidos los tipos de los que se compone el esquema, las propiedades de esos tipos así como el tipos de datos de esas propiedades, por otro lado el editor [GraphiQL][graphiql] ofrece un pequeño IDE para probar la API con asistencia de código y ver su documentación.

Uno de los problemas de la documentación es que sea inconsistente con la realidad del código, la ventaja de documentar la API en el propio código fuente es que es más fácil actualizar la documentación y que el código y a documentación sea consistente en todo momento. Otra utilidad común con el paso del tiempo y nuevas versiones es documentar en el propio esquema los campos cuyo uso está desaconsejado u es obsoleto con la directiva _@deprecated_.

Aunque la propia información del esquema ya es una gran ayuda para poder utilizar una API de GraphQL par mayor documentación es necesario añadir una descripción más detallada o una aclaración sobre una propiedad. En el propio esquema de GraphQL a los tipos y propiedades se les puede añadir una descripción.

Para añadir descripciones basta con incluir un comentario en la linea anterior al tipo o propiedad en el propio esquema de la API. Esto es lo único necesario para que en GrapiQL las descripciones añadidas aparezcan al explorar el esquema de la API.

{{< code file="library.graphqls" language="graphqls" options="" >}}

Una vez añadidos los comentarios tanto en [el IDE de GraphiQL][blogbitix-340] como [realizando introspección][blogbitix-412] se obtienen las descripciones de los campos.

{{< code file="publications.query" language="graphql" options="" >}}
{{< code file="instrospection.query" language="graphql" options="" >}}
{{< code file="introspection.json" language="json" options="" >}}

{{< image
    gallery="true"
    image1="image:graphiql-documentation.webp" optionsthumb1="300x200" title1="Editor GraphiQL mostrando la descripción de los campos"
    image2="image:graphiql-type.webp" optionsthumb2="300x200" title2="Consulta e GraphiQL para inspeccionar un tipo"
    caption="Editor GraphiQL mostrando la descripción de los campos" >}}

Poner nombres semánticos y significativos a los tipos y propiedades es una gran ayuda, para mayor detalle, aclaraciones y puntualizaciones están las descripciones que se pueden añadir en GraphQL.

{{< reference >}}
* [How do I add a description to a field in “GraphQL schema language”](https://stackoverflow.com/questions/39962867/how-do-i-add-a-description-to-a-field-in-graphql-schema-language)
{{< /reference >}}

{{% /post %}}
