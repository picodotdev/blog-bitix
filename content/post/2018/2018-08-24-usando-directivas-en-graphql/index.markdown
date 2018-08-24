---
pid: 341
title: "Usando directivas en GraphQL"
url: "/2018/08/usando-directivas-en-graphql/"
date: 2018-08-24T08:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
series: ["graphql"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="graphql.svg" title1="GraphQL" width1="200" >}}

En [GraphQL][graphql] construyendo la consulta adecuada se recuperan exclusivamente los datos solicitados.

Por ejemplo, si de un libro solo se requieren las propiedades el _id_, _title_ y _date_ de entre todas las que tienen la consulta sería en este caso la siguiente para una consulta que devuelve los datos de un conjunto de libros.

{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "query-1.graphql" >}}

Si se desea recuperar solo el _id_ y _title_ sin el _date_ la consulta debe ser diferente.

{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "query-2.graphql" >}}
{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "library.graphqls" >}}

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="image-1.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Consulta id, title y date"
        image2="image-2.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Consulta id y title"
        caption="Consultas diferentes que devuelven diferentes datos" >}}
</div>

Esto obliga en principio a tener dos consultas diferentes según los datos recuperar. En principio, ya que las [directivas _skip_ e _include_](https://graphql.org/learn/queries/#directives) definidas en la especificación de GraphQL permiten tener la misma consulta y parametrizar si un determinado dato ha de incluirse o no en el resultado.

Las directivas se definen en el lenguaje de consulta de GraphQL con el caracter _@_ y pueden recibir parámetros. La directiva _skip_ permite omitir un dato según el valor de un booleano, si es _true_ se omite y si es _false_ se incluye, el comportamiento de _include_ es el mismo pero con el valor contrario del booleano, si es _true_ se incluye y si es _false_ se omite.

Esta sería la consulta parametrizada para obtener los datos de los libros omitiendo o incluyendo su fecha en función de una variable utilizando la directiva _include_. Cuando el valor de la variable es _true_ se incluye el dato fecha, cuando el valor de la variable es _false_ no se incluye.

{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "query-3.graphql" >}}
{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "variables-1.graphql" >}}
{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "variables-2.graphql" >}}

Utilizando [el editor GraphiQL][blogbitix-340] para construir y ejecutar consultas de una API de GraphQL se obtienen los resultados.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="image-3.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Consulta con directiva include"
        image2="image-4.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Consulta con directiva include"
        caption="Misma consulta con directiva include que devuelve diferentes datos" >}}
</div>

Con un comando _curl_ se realizan las mismas consultas.

{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "curl-1.sh" >}}
{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "data-1.json" >}}

{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "curl-2.sh" >}}
{{< gist picodotdev 5a60d7e3d64c62e04fb850e824469257 "data-2.json" >}}

{{% code git="blog-ejemplos/tree/master/GraphQL" command="./gradlew run" %}}

{{% /post %}}
