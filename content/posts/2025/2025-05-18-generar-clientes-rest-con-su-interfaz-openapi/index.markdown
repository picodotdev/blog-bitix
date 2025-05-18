---
pid: 715
type: "post"
title: "Generar clientes REST con su interfaz OpenAPI"
url: "/2025/05/generar-clientes-rest-con-su-interfaz-openapi/"
date: 2025-05-18T10:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Para hacer uso de una interfaz REST es necesario crear un cliente en el mismo lenguaje de programación de la aplicación. Dada una interfaz REST compuesta por sus _endpoints_, parámetros, _headers_ y _payloads_ de entrada y de salida asi como sus códigos de estado de respuesta es posible automatizar con un generador de código la creación de un cliente para cualquiera de los lenguajes que se necesite y el generador soporte."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una de las características más destacadas de REST es que utiliza el protocolo http de la web y habitualmente JSON como formato para los datos. Por estas propiedades de REST hace que sean muy fáciles de consumir por los clientes, los clientes pueden utilizar un lenguaje diferente del que emplea el servidor, los servicios pueden ser reemplazados manteniendo el contrato de la interfaz del servicio REST, al mismo tiempo que hay muchas herramientas de infraestructura web para soportar esos servicios por ejemplo para implementar seguridad, cacheo, enrutado entre otras funcionalidades.

Dado que los servicios REST están destinados a ser consumidos por los clientes estos van a necesitar implementar la interfaz del servicio, cosa que hay que hacer en cada cliente. Esta integración en cada cliente puede hacer la tarea de integración repetitiva para la que además se necesita la definición del servicio REST.

Para la definición del servicio REST está la especificación OpenAPI y para la integración en los clientes hay generadores de clientes para diferentes lenguajes, usando diferentes librerías cliente REST y librerías de JSON.

* [Cómo documentar una API REST con Swagger implementada con Spring Boot][blogbitix-584]
* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]
* [Los niveles de madurez REST, ejemplo con HATEOAS y documentación con Swagger de un servicio con Spring Boot][blogbitix-587]

## La especificación de OpenAPI

OpenAPI es una especificación que define la sintaxis, propiedades y tipos del lenguaje. La especificación recoge en un documento la definición completa de un servicio REST.

Incluye los _endpoints_ y sus URLs, los parámetros en el _path_ y _query_, el body de la petición y el _body_ de la petición y la respuesta incluyendo sus propiedades, estructura y tipos, los códigos de estado de las respuesta y finalmente opcionalmente documentación en prosa a diferentes niveles de la especificación.

La especificación OpenAPI es simplemente un documento de texto en formato YAML que sigue un esquema. Como editor que ofrece resaltado de sintaxis y detección de errores se puede usar [Swagger Editor][swagger-editor].

En vez de generar el documento a través de una aproximación _api first_, usando [Spring][spring] es posible obtener el mismo documento a través de una aproximación _code first_ mediante la cual el documento se genera a partir del código de la implementación del servidor, en Spring con anotaciones.

* [OpenAPI specification](https://swagger.io/specification/)

## Generadores de clientes REST a partir de la especificación OpenAPI

Tener el documento de la especificación REST ya aporta una buena cantidad de valor como documentación y como documento de referencia.

Pero además tener la especificación permite generar los clientes lo que ahorra una enorme cantidad de tiempo en la implementación de los mismos, no solo en la creación sino también cuando hay que hacerles cambios.

Para Java hay dos generadores, swagger codegen y openapi generator, el primero es desarrollado por una compañía y el segundo con un modelo de desarrollo más comunitario. Ambos son muy parecidos ya que en realidad openapi generator es un _fork_ por parte de los desarrolladores originales de ambos.

Los dos generadores permiten crear clientes para diferentes lenguajes como Java, JavaScript, TypeScript, C#, Kotlin, Swift. En el ámbito de Java es posible configurar la generación de clientes, seleccionando el cliente http, Retrofit junto con diferentes librerías para el procesado de JSON por ejemplo [Jackson][jackson].

* [Swagger Codegen][swagger-codegen]
* [OpenAPI Generator][openapi-generator]
* [Swagger Codegen Fork: Q&A](https://openapi-generator.tech/docs/fork-qna)

## Ejemplo de especificación OpenAPI

Este es un ejemplo de especificación OpenAPI con un recurso y diferentes métodos http para las diferentes operaciones del recurso, como creación, actualización, obtención y eliminación.

{{< code file="catalog.yaml" language="yaml" options="" >}}

## Cliente generado con openapi-generator

El cliente en este ejemplo se genera usando Gradle usando las librerías [OkHttp][okhttp], [Retrofit][retrofit], Jackson y las anotaciones de Jakarta junto con una configuración para Gradle y el _plugin_ para _openapi-generator_.

{{< code file="build.gradle.kts" language="kotlin" options="" >}}

El código generado del cliente queda en la carpeta _openapi-generator/client/build/generate/openapi/src_ junto con las entidades devueltas en la respuesta.

{{< code file="CatalogClient.java" language="java" options="" >}}
{{< code file="ClientEvent.java" language="java" options="" >}}

Finalmente, el uso del cliente es el siguiente.

{{< code file="Main.java" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/openapi-generator" command="./gradlew run" %}}

{{% /post %}}

