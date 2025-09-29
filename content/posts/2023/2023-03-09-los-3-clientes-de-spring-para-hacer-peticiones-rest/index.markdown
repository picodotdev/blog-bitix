---
pid: 678
type: "post"
title: "Los 3 clientes de Spring para hacer peticiones REST"
url: "/2023/03/los-3-clientes-de-spring-para-hacer-peticiones-rest/"
date: 2023-03-09T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:spring.svg"
tags: ["java", "planeta-codigo"]
summary: "El proyecto Spring ofrece hasta 3 clientes o formas diferentes para realizar peticiones a servicios REST. La ventaja de estos clientes es que no requieren de dependencias adicionales si se usa Spring y están integradas con el ecosistema de Spring y Spring Boot."
---

{{% post %}}

{{< logotype image1="spring.svg" >}}

El proyecto de [Spring][spring] ofrece un ecosistema de proyectos y librerías que cubren con casi seguridad cualquier necesidad de una aplicación, y en caso de necesidad siempre es posible recurrir a librerías de terceros. Este es el caso de las librerías [OkHttp][okhttp] y [Retrofit][retrofit] que son una alternativa a las funcionalidades que ofrece Spring.

Spring es un proyecto que ofrece una enorme cantidad de funcionalidades, prácticamente cualquier cosa que necesite una aplicación Java lo proporciona de alguna. Esto tiene la ventaja de que eligiendo Spring como _framework_ en caso de necesitar en un futuro una nueva funcionalidad es posible elegir la que ofrezca con la ventaja de estar integrada con el ecosistema del _framework_.

En los microservicios o servicios web que hacen uso de peticiones REST es necesario un cliente _http_ para hacer peticiones a los _endpoints_ de los servicios. aunque desde la versión 11 de Java en el JDK se ofrece un cliente _http_ que puede ser suficiente para muchos casos de uso sin necesidad de librerías adicionales, soporta HTTP/2 y comunicación asíncrona, el cliente del JDK aunque suficiente para muchos casos es algo más básico que otras opciones.

En Java hay varios clientes _http_, desde el mencionado HttpClient incorporado en Java hasta de terceros como el también conocido [Apache HttpCommons][apache-commons] y OkHttp. Spring ofrece tres clientes REST.

Independientemente del cliente que se use es deseable poder configurar algunas propiedades del cliente como _timeouts_ de las peticiones, _logging_, métricas, autenticación y filtros o interceptores para funcionalidades comunes a todas las peticiones.

* [Funcionalidades que necesitan las aplicaciones basadas en microservicios y herramientas que las proporcionan][blogbitix-516]
* [Crear de forma sencilla un cliente de un servicio REST o HTTP con Retrofit][blogbitix-569]
* [Implementar tolerancia a fallos con Resilience4j][blogbitix-425]

{{< tableofcontents >}}

## Clientes REST de Spring

El cliente REST más antiguo que ha ofrecido Spring ha sido [RestTemplate](spring-framework:org/springframework/web/client/RestTemplate.html) siendo superado por [WebClient](spring-framework:org/springframework/web/reactive/function/client/WebClient.html) y otra forma de obtener un cliente REST usando una simple interfaz de Java.

Una alternativa de los clientes REST de WebClient y Http Interface con librerías de terceros son OkHttp y Retrofit dependiendo si se quieren añadir dependencias adicionales y si las funcionalidades ofrecidas por los clientes de Spring son suficientes.

Los ejemplos de código hacen peticiones al _endpoint_ del siguiente sencillo servicio que únicamente devuelve un mensaje recibiendo un parámetro en la ruta de la URL de forma opcional.

{{< code file="DefaultController.java" language="java" options="" >}}
{{< code file="build.gradle" language="groovy" options="" >}}

### RestTemplate

RestTemplate sigue siendo una forma sencilla de hacer peticiones REST que aún existe por mantener la compatibilidad con el código existente y que aún no se ha actualizado a otros clientes. Está en modo mantenimiento y tiene algunas limitaciones como ser un cliente síncrono y no soportar _streaming_.

{{< code file="Main-1.java" language="java" options="" >}}

### WebClient

WebClient es el sustituto moderno de RestTemplate que ofrece Spring incorporando varias mejoras. Soporta comunicación E/S no bloqueante, comunicación asíncrona y *streaming*, una API con un estilo funcional fluído. Para el código nuevo lo aconsejable es utilizar este cliente.

Este cliente necesita de un cliente _http_ que es el que realiza realmente las peticiones _http_, entre los soportados está el cliente _http_ de Java. El cliente soporta filtros con los que implementar interceptores para funcionalidades comunes independientemente de la petición.

{{< code file="Main-2.java" language="java" options="" >}}

### Http Interface

Retrofit es una librería que permite invocar un servicio REST definiendo el servicio mediante una interfaz de Java, se ha añadido en Spring 6 y Spring Boot 3 para lo que antes había que recurrir al [cliente Feign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/) o como una librería de terceros a Retrofit.

En esencia _Http Interface_ permite invocar un servicio REST igual que invocar un método de una interfaz. El desarrollador crea la interfaz que representa el servicio REST y añaden a los métodos anotaciones que permite a la librería construir una implementación que invocada realiza las peticiones al servicio REST. La implementación de la interfaz se crea a través de la clase [HttpServiceProxyFactory](spring-framework:org/springframework/web/service/invoker/HttpServiceProxyFactory.html)

{{< code file="Main-3.java" language="java" options="" >}}
{{< code file="MessageService.java" language="java" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringRestClients" command="./gradlew run" %}}

{{< reference >}}
* [Spring WebClient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client)
* [REST Clients](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#rest-client-access)
* [HTTP Interface in Spring 6](https://www.baeldung.com/spring-6-http-interface)
* [Spring 5 WebClient](https://www.baeldung.com/spring-5-webclient)
* [Spring WebClient Filters](https://www.baeldung.com/spring-webclient-filters)
{{< /reference >}}

{{% /post %}}