---
pid: 569
type: "post"
title: "Crear de forma sencilla un cliente de un servicio REST o HTTP con Retrofit"
url: "/2021/04/crear-de-forma-sencilla-un-cliente-de-un-servicio-rest-o-http-con-retrofit/"
date: 2021-04-23T21:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "La implementación de un servicio REST o HTTP es solo una parte, el otro lado es crear un cliente de ese u otro servicio que permita invocarlo, proporcionar parámetros y obtener las respuestas. Con la librería Retrofit implementar un cliente de un servicio en Java es una tarea bastante sencilla sencilla que utiliza una simple interfaz a la que se le añaden varias anotaciones que le indican a Retrofit cómo construir una implementación a partir de la interfaz. El código que hace uso de la clase que implementa la interfaz del servicio con Retrofit no es diferente de usar una clase que implementa una interfaz."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

En el lenguaje de programación Java hay varias librerías que permiten hacer peticiones a un servicio que utilice el protocolo HTTP. Varias de las librerías más conocidas son [OkHttp][okhttp], [Apache HttpComponents][apache-httpcomponents] e incluso [en el JDK en la versión 11 se ha incorporado un cliente HTTP que soporta HTTP/2][blogbitix-350].

Estas librerías cumplen su función y ofrecen total flexibilidad en su uso, sin embargo, requieren hacer las peticiones HTTP de forma explícita lo que supone un código repetitivo y tedioso incluyendo hacer las [conversiones de objetos a JSON y de JSON a objetos Java][blogbitix-378] en las peticiones y respuestas.

{{< tableofcontents >}}

### La librería Retrofit para crear un cliente de un servicio REST o HTTP

[Retrofit][retrofit] es una librería que simplifica en gran medida el construir clientes HTTP de una API REST o realizar un cliente de un servicio REST. Con Retrofit basta con crear una interfaz Java que represente el servicio y decorarla con las anotaciones que proporciona Retrofit. También es posible utilizar Retrofit para [servicios implementados con GraphQL][blogbitix-275] que aunque no están basados en REST si utilizan el protocolo HTTP.

Retrofit utiliza como librería para realizar las peticiones OkHttp y es compatible con varias librerías para realizar las conversiones de datos de JSON a objetos y de objetos a JSON, entre ellas [Jackson][jackson], [Gson][gson] y JSON-B. También soporta realizar las peticiones de forma síncrona o asíncrona.

Esta es una interfaz de Java que representa un sencillo servicio REST de una petición _GET_. Las anotaciones instruyen a Retrofit como a partir de esta interfaz crear el cliente del servicio REST, al proporcionar esta interfaz Retrofit devuelve una instancia de la interfaz que al invocar a los métodos internamente realiza las peticiones HTTP.

{{< code file="Service.java" language="java" options="" >}}

Al proporcionar a Retrofit la interfaz este crea una instancia que implementa la interfaz pero que internamente implementa el cliente HTTP del servicio. Aparte de la interfaz para obtener la instancia del servicio se ha de proporcionar la URL base donde se ubica el servicio asi como otros objetos relacionados como interceptores.

{{< code file="Main-buildService.java" language="java" options="" >}}

#### Anotaciones de Retrofit

Las anotaciones de Retrofit en la interfaz de Java describen el servicio como variables en el _path_ de la URL, parámetros, para realizar conversiones a JSON o el método HTTP a invocar o cabeceras HTTP.

* _HTTP_, _GET_, _POST_, _PUT_, _PATCH_, _DELETE_, _OPTIONS_ y _HEAD_: estas anotaciones indican el método HTTP que se realiza.
* _Path_, _Query_: la anotación _Path_ sustituye una variable en el _path_ de la URL por el valor del argumento anotado. La anotación _Query_ añade un argumento en la _query_ de la URL.
* _Headers_, _Header_: la anotación _Headers_ permite especificar una colección de cabeceras HTTP a incluir en la petición. La anotación _Header_ añade una cabecera a partir del valor de un argumento en la firma del método.
* _Body_: la anotación _Body_ transforma el argumento como los datos a incluir como JSON en cuerpo de la petición utilizando la librería que implementa la conversión de objetos a JSON.

#### Aplicar funcionalidades transversales con interceptores

Algunas funcionalidades comunes al crear un cliente de un servicio REST son obtener trazas de las peticiones que se están realizando, realizar autenticación, generar métricas o [trazabilidad con Sleuth][blogbitix-396]. Estas son funcionalidades transversales a todos los métodos de la interfaz del servicio REST que se implementan usando interceptores.

Un interceptor es una clase que implementa una interfaz de la librería OkHttp que es invocada al realizarse una petición HTTP. OkHttp soporta métricas añadiendo un _EventListener_ al construir la instancia del cliente de OkHttp.

OkHttp proporciona una implementación de interceptor que emite trazas cuando se realiza una petición útil para observar en el _log_ qué peticiones se están realizando y que códigos de estado se están devolviendo. Esta es una implementación propia de un interceptor para emitir las trazas que se realizan con el cliente.

{{< code file="Main-interceptor.java" language="java" options="" >}}

### Servicio de ejemplo con Retrofit

Un controlador como el siguiente de un servicio REST definido con [Spring Framework][spring-framework] sencillo que únicamente devuelve un mensaje en función de los parámetros recibidos a través de una en la petición, una variable en el _path_ de la petición y un parámetro en la _query_.

{{< code file="RestService.java" language="java" options="" >}}

El cliente del servicio construido por Retrofit se realiza a partir de la definición de la interfaz, el cliente es un objeto que implementa esa interfaz y en código Java no de su uso es simplemente invocar sus métodos y proporcionar los parámetros. La implementación del cliente contiene el código necesario para transformar las invocaciones de los métodos de la interfaz en peticiones al servicio REST.

{{< code file="Main.java" language="java" options="" >}}

Esta es la salida del programa en la consola donde se ven las trazas del interceptor de OkHttp con los datos de la petición y las respuesta del servicio junto con el mensaje de respuesta del servicio.

{{< code file="System.out" language="plaintext" options="" >}}

En el archivo de construcción hay que incluir la dependencia de Retrofit.

{{< code file="build.gradle" language="groovy" options="" >}}

El código que utiliza el cliente realiza peticiones HTTP de modo que al hacer pruebas unitarias es necesario utilizar un [servidor _mock_ para devolver las respuestas simuladas del servicio real a las peticiones HTTP][blogbitix-589] sin necesidad de que este esté disponible, esto elimina dependencias del entorno de pruebas haciéndolo más sencillo, también el servidor _mock_ permite el desarrollo sin necesidad del servicio real.

{{% sourcecode git="blog-ejemplos/tree/master/JavaRetrofit" command="./gradlew run" %}}

{{% /post %}}
