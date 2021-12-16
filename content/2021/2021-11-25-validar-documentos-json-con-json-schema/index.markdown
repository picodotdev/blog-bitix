---
pid: 610
type: "post"
title: "Validar documentos JSON con JSON Schema"
url: "/2021/01/validar-documentos-json-con-json-schema/"
date: 2021-11-25T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Los documentos JSON son una forma de intercambiar información entre aplicaciones. Como en cualquier intercambio de información es conveniente validar los datos recibidos antes de realizar ninguna acción. En Java dependiendo de la librería o _framework_ utilizada aunque los datos se transmiten en formato JSON estos son transformados y recibidos como objetos Java en los cuales se realizan validaciones de tipos y conversiones de tipos y restricciones a los valores con Bean Validation o Spring Validation. La especificación JSON Schema permite definir un esquema para los documentos JSON independiente del lenguaje con la que realizar validaciones y realizar las validaciones a través de implementaciones en los diferentes lenguajes de programación incluido Java."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una tarea fundamental en toda aplicación es validar los datos de entrada. Validar los datos evita errores al procesar los datos, generar datos erróneos como resultado o realizar acciones en base a datos no válidos con consecuencias como realizar acciones no deseadas o crear inconsistencias en la base de datos. También hay que validar los datos por motivos de seguridad.

Los datos de entrada de un programa se proporcionan en función de la naturaleza de la aplicación, en una aplicación web o REST es a través de los datos de la petición, en una aplicación que procesa mensajes de servicios como [Kafka][apache-kafka] o [RabbitMQ][rabbitmq] los datos se incluyen en los datos del mensaje y en una aplicación de procesos _batch_ los datos quizá estén en archivos.

* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]
* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]
* [Integración de servicios y sistemas con Apache Camel][blogbitix-590]

Los formatos más comunes para el intercambio de datos son XML, JSON y CSV. El formato de documentos XML permite comprobar está bien formado en cuanto a balanceo de etiquetas junto a otros requerimientos, los esquemas XML permiten validar además si un documento XML cumple con el esquema incluyendo las etiquetas requeridas. JSON es una especificación que de por si no define ningún esquema, esto hace que los documentos JSON puedan tener cualesquiera datos mientras utilicen una sintaxis correcta o tengan un formato correcto. Sin embargo, en la validación de datos el que un documento JSON tenga una sintaxis correcta no es suficiente que se considere válido. Un esquema permite definir que un documento incluya ciertas propiedades, que estás se ajusten a unos valores predeterminados, que cumplan ciertas reglas de validación como un tipo o rango de valores.

La especificación [JSON Schema][json-schema] es el equivalente para los documentos JSON de XML Schema para los documentos XML. JSON Schema permite validar que un documento JSON se ajusta a un esquema conteniendo los datos y valores definidos en el esquema.

{{< tableofcontents >}}

### La especificación JSON Schema

La [especificación de JSON Schema](https://json-schema.org/specification.html) tiene varias definiciones formales y versiones. En la [guía de inicio paso a paso](https://json-schema.org/learn/getting-started-step-by-step.html) se incluye una descripción más sencilla y práctica para un primer inicio.

Un esquema de JSON contiene a qué versión de la especificación se ajusta, el identificador o la ubicación del esquema, un título, una descripción y el tipo de objeto del documento raíz. Además define qué propiedades junto con sus tipos ha de contener el documento JSON al que se aplica, cuáles de esas propiedades son requeridas y las validaciones sobre los datos como restricciones en los valores de los datos o elementos de un _array_. Además de propiedades un documento permite la anidación de estructuras en las que también se definen que propiedades contienen y cuáles son requeridas. Finalmente, un esquema JSON permite referenciar un esquema JSON externo.

En el siguiente esquema _$schema_ define la versión del esquema que implementa, _$id_ define el identificador del esquema. Las propiedades _title_, _description_ proporcionan una descripción del esquema y _type_ el tipo de objeto raíz. En la propiedad _properties_ se definen las propiedades del documento JSON y en la propiedad _required_ cuáles de esas propiedades son requeridas. En la propiedad _dimensions_ están las estructuras JSON anidadas. Y con la propiedad _$ref_ se referencia otro esquema JSON.

{{< code file="product.schema.json" language="json" options="" >}}
{{< code file="geographical-location.schema.json" language="json" options="" >}}
{{< code file="product.json" language="json" options="" >}}
{{< code file="product-invalid.json" language="json" options="" >}}

### Librerías JSON Schema en Java

Hay varias [librerías Java que implementan validación de JSON](https://json-schema.org/implementations.html#validator-java) con la especificación de JSON Schema, junto a otras implementaciones en otros lenguajes. De entre las implementaciones Java una de ellas es [JSON Schema Validator](https://github.com/networknt/json-schema-validator) de [networknt](https://github.com/networknt/) en la que los errores que se detectan son devueltos en una estructura de datos en vez de lanzar una excepción en caso de que la validación falle.

Otra de sus funcionalidades es que permite hacer una correspondencia entre los identificadores de los esquemas JSON a recursos locales, útil en caso de que los esquemas no estén publicados en sus direcciones, en aplicaciones en las que no tengan conexión a internet o no se desea que estas realicen conexiones externas.

Hay que tener en cuenta que varias de estas librerías están implementadas por personas sin seguramente el respaldo de una organización, hay que tenerlo en cuenta como criterio de decisión en el caso de añadir como dependencia de un proyecto una de las implementaciones.

### Ejemplo con Java de validar un JSON con JSON Schema

Este es un ejemplo que a partir de un documento JSON se valida que cumple el esquema contra el que se valida. En el caso de que el documento JSON no cumpla el esquema se devuelven los errores como resultado del método de validación, en el caso del ejemplo los errores son emitidos a la salida estándar donde se aprecia que en el caso de la validación del JSON inválido faltan las tres propiedades requeridas.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

La librería de JSON Schema Validator además de su propia dependencia requiere incluir otras en el archivo de construcción, en este caso de [Gradle][gradle].

{{< code file="build.gradle" language="groovy" options="" >}}

### Otras formas de validación con Bean Validation y Spring Validation

Otra forma de validar un JSON es cargarlo en un objeto Java y validar el objeto con [Bean Validation][beanvalidation] o [Spring Validation][spring-validation]. La diferencia en este caso respeto a JSON Schema es que Bean Validation y Spring Validation es una solución específica de Java, requiere cargar los datos en objetos y más importante no se define ningún esquema sino que el esquema está implícito en las validaciones ya se definan con anotaciones o con validadores personalizados.

* [Validar objetos con Spring Validation, ejemplo registros de jOOQ][blogbitix-110]

{{% sourcecode git="blog-ejemplos/tree/master/JsonSchema" command="./gradlew run" %}}

{{% /post %}}
