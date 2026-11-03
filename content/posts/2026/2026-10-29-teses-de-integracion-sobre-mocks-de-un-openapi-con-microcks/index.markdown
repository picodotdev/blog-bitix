---
pid: 739
type: "post"
title: "Teses de integración sobre mocks de un OpenAPI con Microcks"
url: "/2026/10/teses-de-integracion-sobre-mocks-de-un-openapi-con-microcks/"
date: 2026-10-29T22:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["java", "planeta-codigo", "software-libre"]
summary: "Los tests unitarios son rápidos, pero se ejecutan en un entorno mínimo y asilado que no es el de producción. Con Testcontainers, los tests de integración pueden usar las mismas herramientas que en producción, como PostgreSQL o Kafka. En este artículo vemos cómo probar los clientes REST de un microservicio, es decir, los adaptadores de salida de una arquitectura hexagonal, con Microcks. Microcks es un servidor mock de la CNCF que genera las respuestas directamente a partir de la definición OpenAPI o AsyncAPI de un servicio, y que permite empezar a desarrollar sin tener el servicio real ni un entorno _sandbox_."
---

{{% post %}}

{{< logotype image1="microcks.svg" >}}

Los teses unitarios son los más numerosos por su rapidez de ejecución y probar el objeto bajo prueba de forma aislada y controlada. Sin embargo, los teses unitarios se ejecutan en un entorno controlado sintético que no refleja el entorno de ejecución real. Los teses de integración prueban el objeto bajo prueba con elementos adicionales que representa de forma más fiable el entorno en el que se ejecutan en el entorno de producción.

Los teses de integración han sido a veces limitados por la dificultad de simular el entorno de producción. Ahora gracias a los contenedores y con [Testcontainers][testcontainers] es posible crear teses de integración que usen las mismas herramientas de producción como una base de datos relacional [PostgresSQL][postgresql] o un sistema de mensajería como [Apache Kafka][apache-kafka].

Con Testcontainers es posible probar de forma completa el código de la aplicación incluida esa parte que se relaciona con el sistema externo. Por ejemplo, probar el acceso a una base de datos relacional y lanzar SQLs o enviar y recibir mensajes de Kafa y [Spring Cloud Stream][spring-cloud-stream].

Una parte habitual en los microservicios es probar las peticiones REST que realiza a otros microservicios. Para esto se necesita un servidor ficticio o _mock_ con respuestas preprogramadas que simulen las respuestas y que permitan validar el código de infraestructura y adaptador de salida en una arquitectura hexagonal que realiza las peticiones al sistema externo.

* [Servidor mock para imitar peticiones y respuestas de servicios HTTP con WireMock][blogbitix-589]

{{< tableofcontents >}}

## Microcks

[Microcks][microcks] es un servidor adoptado en la [CNCF][cncf-landscape] que permite preprogramar respuestas de servicios REST y sistemas de mensajes proporcionando simplemente la definición del documento [OpenAPI][openapi] o AsynAPI. A partir del documento OpenAPI para cada _endpoint_ de la API se programa una respuesta válida según la definición de la API.

Hay varias formas de usar Mickrocks, una de ellas es en teses de integración con Testcontainers y [Spring Boot][spring-boot] para probar un cliente de una API. Esto tiene la ventaja de que teniendo disponible la definición del OpenAPI es posible empezar a desarrollar el cliente sin necesidad de tener la parte servidora real o un entorno _sandbox_ para el mismo.

Otra forma de ejecución es mediante un contenedor que arranque el servidor de Microcks. El propio servidor permite cargar varios ejemplos de APIs con su definición de OpenAPI como los de _petstore_ y _pastry_.

Microcks adicionalmente permite probar API en [GraphQL][graphql], [gRPC][grpc] y los mencionados [AsyncAPI][asyncapi]. Otras opciones solo soportan HTTP preprogramando las peticiones y respuestas a más bajo nivel sin partir del documento OpenAPI.

### Contenedor de Microcks

En la [guía de inicio de Microcks](https://microcks.io/documentation/tutorials/getting-started/) se muestra y explica como probar Microcks utilizando [Docker][docker].

Lo primero es iniciar el contenedor de Microscks.

{{< code file="docker-run.sh" language="bash" options="" >}}

Accediendo a _http://localhost:8585_ con el navegador se carga al panel de administración.

{{< image
    gallery="true"
    image1="image:microcks-1.webp" optionsthumb1="650x300" title1="Consola de Microcks"
    caption="Consola de Microcks" >}}

Desde _Microcks Hub_ es posible cargar un ejemplo de OpenAPI.

{{< image
    gallery="true"
    image1="image:microcks-2.webp" optionsthumb1="200x150" title1="Ejemplos de APIs en el Hub de Microcks"
    image2="image:microcks-3.webp" optionsthumb2="200x150" title2="Ejemplos de APIs en el Hub de Microcks"
    image3="image:microcks-4.webp" optionsthumb3="200x150" title3="Ejemplos de APIs en el Hub de Microcks"
    caption="" >}}
{{< image
    gallery="true"
    image1="image:microcks-5.webp" optionsthumb1="200x150" title1="Ejemplos de APIs en el Hub de Microcks"
    caption="Ejemplos de APIs en el Hub de Microcks" >}}

En _APIs | Services_ es posible inspeccionar las APIs cargadas como _mocks_.

{{< image
    gallery="true"
    image1="image:microcks-6.webp" optionsthumb1="200x150" title1="Ejemplo de mock de un OpenAPI en Microcks"
    image2="image:microcks-7.webp" optionsthumb2="200x150" title2="Ejemplo de mock de un OpenAPI en Microcks"
    image3="image:microcks-8.webp" optionsthumb3="200x150" title3="Ejemplo de mock de un OpenAPI en Microcks" >}}
{{< image
    gallery="true"
    image1="image:microcks-9.webp" optionsthumb1="200x150" title1=""
    caption="Ejemplo de mock de un OpenAPI en Microcks" >}}

Con el comando _curl_ se realiza la petición REST a la API mockeada.

{{< code file="curl-pastry-request.sh" language="bash" options="" >}}

Este es la respuesta.

{{< code file="pastry-response.out" language="json" options="" >}}

### Test de integración con Testcontainers y Spring Boot

Una forma alternativa muy útil a un servidor autónomo es realizar las pruebas como teses de integración del servicio. En la definición del contenedor de Testcontainers se añade el OpenAPI para que Microcks cree el _mock_, el proprio OpenAPI tiene instrucciones para definir el compoeramiento del _mock_ con ejemplos de valores y código para su comportamiento. Tras crear el contenedor es posible obtener la URL base del servidor al que añadir el _path_ del _endpoint_ al que enviar las peticiones.

Utilizando [RestClient](spring-framework:org/springframework/web/client/RestClient.html) el siguinte código hace una petición al servicio REST desde un test de integración que arranca Microcks con Testcontainers y obtiene el base URL del servidor de Mickrocks. La propiedad de configuración _pastry.api.base-url_ se configura dinámicamente.

{{< code file="PastryClientTest.java" language="java" options="" >}}
{{< code file="PastryClient.java" language="java" options="" >}}
{{< code file="application.yml" language="yaml" options="" >}}
{{< code file="Pastry.java" language="java" options="" >}}
{{< code file="build.gradle.kts" language="kotlin" options="" >}}

## Otras opciones

Otras opciones populares de servidores mock para peticiones REST son [WireMock][wiremock], [Mock Server][mockserver] y [Prism][prism]. Sin embargo, estas opciones solo soportan el protocolo REST y no soportan el resto de protocolos que soporta Microcks. Otro aspecto relevante es que se programan con peticiones de ejemplo y no a partir de la definición del OpenAPI.

{{% sourcecode git="blog-ejemplos/tree/master/JavaMicrocks" command="./gradlew test" %}}

{{% /post %}}
