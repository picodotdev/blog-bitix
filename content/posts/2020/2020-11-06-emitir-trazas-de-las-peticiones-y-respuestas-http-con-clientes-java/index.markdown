---
pid: 529
type: "post"
title: "Emitir trazas de las peticiones y respuestas HTTP con clientes Java"
url: "/2020/11/emitir-trazas-de-las-peticiones-y-respuestas-http-con-clientes-java/"
aliases: ["/2020/11/emitir-trazas-de-las-peticiones-y-respuestas-http/"]
date: 2020-11-06T17:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "En las peticiones HTTP que se hacen unos microservicios a otros o a recursos externos son varias las cosas por las que una petición HTTP es capaz de fallar. El fallo es un código de estado distinto al correcto 200, ya sea petición invalida 400, un recurso no encontrado 404, credenciales requeridas 401, credenciales inválidas 403 o un error interno de servidor 500 entre otros códigos de estado, otras posibilidades son fallos de conexión de red. Añadir trazas de las peticiones que hacen los microservicios permite obtener información con la que averiguar cual es el motivo de fallo, si además emiten las trazas con las cabeceras y parámetros en formato de la herramienta _curl_ es fácil probar y reproducir el mismo error o la misma acción."
---

{{% post %}}

{{< logotype image1="java.svg">}}

Hay varios aspectos de la aplicación en los que es muy útil añadir trazas, los más comunes son añadir trazas en determinados puntos del código que permiten averiguar cuál ha sido el camino seguido en el procesamiento, también ciertos valores de variables. Las trazas es un aspecto importante en la monitorización de una aplicación.

Otra información interesante a emitir en las trazas son las sentencias SQL que genera una aplicación ya sea en el momento de desarrollo para detectar problemas de rendimiento ocasionados por un 1+N al usar algún ORM como JPA o Hibernate. También por una consulta que tiene mal rendimiento ya que no hace uso de índices lo que hace que el motor de la base de datos tenga acceder a demasiados registros o hace demasiadas _joins_. Conocer la SQL exacta o consultas con mal rendimiento que se están ejecutando permite optimizarlas.

En este artículo comento otro caso habitual en el que es interesante añadir trazas, las peticiones HTTP a otros sistemas.

* [La librería Log4j para emitir trazas en aplicaciones Java][blogbitix-334]
* [Ofuscar datos sensibles en las trazas con Log4j][blogbitix-383]
* [Identificar todas las trazas de una petición en una aplicación web Java con Log4j][blogbitix-336]
* [Trazabilidad en servicios distribuidos con Sleuth y Zipkin][blogbitix-518]
* [Centralizar y consultar las trazas de las aplicaciones con Elasticsearch, Logstash y Kibana][blogbitix-517]

{{< tableofcontents >}}

### Añadir trazas de las peticiones HTTP

Una de las características que definen a los microservicios es que unos se comunican con otros. Pueden estar basados en REST o en [GraphQL][graphql] en ambos casos utilizando el protocolo HTTP, también pueden estar basados en RPC con [gRPC][grpc].

* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]
* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]
* [Introducción a gRPC y ejemplo con Java][blogbitix-512]

En el caso de los microservicios que utilizan HTTP es interesante que emitan en las trazas la petición HTTP que están realizando ya que las comunicaciones entre microservicios son un punto de fallo común, servicios que no están respondiendo con los datos esperados, el código de estado, cabeceras de respuesta y que a qué recurso se está llamando, con qué datos y cabeceras en la petición. Además, si las trazas se emiten en el formato de la herramienta _curl_ hace muy fácil probar la misma consulta que hace la aplicación.

Dependiendo de la librería que se utilice para hacer las peticiones HTTP depende de como instrumentalizadas para añadirles el soporte para que emitan trazas, los clientes HTTP más populares son [el cliente HTTP de Java][blogbitix-268] añadido entre las [novedades de Java 9][blogbitix-264], [WebClient][spring-webclient] de [Spring][spring], [Retrofit][retrofit] y [OkHttp][okhttp].

A continuación están los ejemplos de cómo añadir trazas en cada una de estas librerías, todos consisten básicamente añadir un interceptor que es llamado cuando se realiza una petición y se recibe la respuestas, en este interceptor es posible emitir las trazas de las peticiones HTTP.

Estas son las dependencias de librerías para los cliente.

{{< code file="build.gradle" language="groovy" options="" >}}

#### Trazas con el cliente Java

El cliente de Java en la versión de Java 9 no incluye en su API un soporte sencillo para añadir trazas al contrario de las otras librerías, sin embargo, aún no ofrececiendo este soporte como el cliente está incluido en el JDK sigue siendo una buena opción para reducir el número de dependencias.

Una posibilidad para no tener que crear una implementación propia para añadirle trazabilidad al cliente HTTP de Java es utilizar la librería [interceptable-http-client](https://github.com/raphw/interceptable-http-client) que precisamente proporciona la implementación, esta librería es del mismo autor que otras conocidas como [Byte Buddy][bytebuddy].

{{< code file="Main-HttpClient.java" language="java" options="" >}}
{{< code file="System.out-HttpClient" language="plain" options="" >}}

#### Trazas con Spring WebClient

El cliente WebClient de Spring posee una API que permite realizar peticiones HTTP con pocas líneas de código.

{{< code file="Main-WebClient.java" language="java" options="" >}}
{{< code file="System.out-WebClient" language="plain" options="" >}}

#### Trazas con Retrofit

Lo interesante del [cliente Retrofit es que convierte una interfaz REST de un servicio en una interfaz de Java][blogbitix-569], se está usando un objeto que implementa una interfaz pero que de forma subyacente se hacen peticiones HTTP. La librería cliente de HTTP que utiliza Retrofit es [OkHttp][okhttp].

El _interceptor_ se añade sobre el cliente OkHttp que luego es utilizado para construir el cliente del servicio con Retrofit.

{{< code file="Main-Retrofit.java" language="java" options="" >}}
{{< code file="System.out-Retrofit" language="plain" options="" >}}

#### Trazas con OkHttp

OkHttp es otra de las librerías para realizar peticiones HTTP populares en Java. Para añadir trazas hay que crear un interceptor y añadirlo al cliente.

{{< code file="Main-OkHttp.java" language="java" options="" >}}
{{< code file="System.out-OkHttp" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/HttpClientLog" command="./gradlew run" %}}

{{< reference >}}
* [Log your RestTemplate Request and Response without destroying the body](https://objectpartners.com/2018/03/01/log-your-resttemplate-request-and-response-without-destroying-the-body/)
* [Logging with Retrofit 2](https://stackoverflow.com/a/33256827)
* [OkHttpClient Logging Configuration With Interceptors](https://dzone.com/articles/okhttpclient-logging-configuration-with-intercepto)
* [Apache HttpClient Interceptor](https://www.concretepage.com/apache-api/apache-httpclient-interceptor)
{{< /reference >}}

{{% /post %}}