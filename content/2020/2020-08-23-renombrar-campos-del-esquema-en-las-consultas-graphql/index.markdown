---
pid: 510
type: "post"
title: "Renombrar campos del esquema en las consultas GraphQL"
url: "/2020/08/renombrar-campos-del-esquema-en-las-consultas-graphql/"
date: 2020-08-23T11:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:graphql.svg"
tags: ["planeta-codigo", "programacion"]
series: ["graphql"]
---

{{% post %}}

{{< logotype image1="graphql.svg">}}

Las APIs devuelven información habitualmente en formato JSON si es reciente y si la API es algo más antigua quizá en XML. En cualquiera de los formatos la información está estructurada en propiedades con un nombre del campo y su valor. El nombre del campo es el que le asignó el diseñador de la API en el momento de crearla. En el momento de consumir la API quizá por adaptarla a su uso, para añadirle más semántica al caso de uso específico o por mayor claridad interese cambiar el nombre de los campos.

En una API que está siendo usada cambiar el nombre a un campo devuelto o recibido implica cambios incompatibles con versiones anteriores y hace que los consumidores de la API deban modificarse para tener en cuenta el nuevo nombre del campo. En una API REST un cambio de nombre a un campo implica modificar las clases DTO o el código para tener en cuenta el nuevo nombre de campo en la respuesta o en la petición.

[GraphQL][graphql] ofrece soporte para crear sinónimos o _aliases_ de los nombres de los campos, es decir, usar un nombre aunque en la API el nombre del campo sea distinto. Esta funcionalidad de alias hacen que un campo con un nuevo nombre solo implique cambiar únicamente las consultas sin que el resto del código requiera cambios, esto es mucho más simple, rápido y seguro que en REST. Los alias también pueden aplicarse al nombre de la consulta.

La siguiente API de GraphQL define la siguiente entidad _Book_ que posee los campos _id_, _title_, _author_ e _isbn_. En este caso hay varias versiones del mismo campo ISBN obtenido de diferentes formas, de forma individual en cada entidad de _Book_ con _isbn_ y de forma _batched_ más eficientemente con _batchedIsbn_ y de forma _batched_ con un _DataLoader_ con _dataLoaderIsbn_.

{{< code file="library.graphqls" language="graphqls" options="" >}}

Esta es una consulta con el campo _dataLoaderIsbn_.

{{< code file="curl-1.sh" language="bash" options="" >}}

Con la siguiente consulta el campo _dataLoaderIsbn_ es renombrado a _isbn_ entre los datos de la respuesta. Para realizar los renombrados hay que seguir el formato _alias:name_ como nombre de campo en la consulta. Con esta funcionalidad variando la consulta se puede pedir cualquiera de los campos _isbn_ sin tener que modificar el código que procesa el JSON de respuesta. Si cambia un nombre de campo en la API hay que modificar las consultas pero no el código que procesa las respuestas.

{{< code file="curl-2.sh" language="bash" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/GraphQL" command="./gradlew run" >}}

{{% /post %}}
