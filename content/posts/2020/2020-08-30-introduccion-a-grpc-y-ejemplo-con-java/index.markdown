---
pid: 512
type: "post"
title: "Introducción a gRPC y ejemplo con Java"
url: "/2020/08/introduccion-a-grpc-y-ejemplo-con-java/"
date: 2020-08-30T11:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:grpc.png"
tags: ["java", "planeta-codigo"]
summary: "Para crear una API expuesta de forma externa o para ofrecer un servicio a otros servicios en una arquitectura de microservicios ha varias opciones. Tres de las opciones son REST, GraphQL y gRPC cada una con sus características que la hacen mas adecuadas según los requerimientos de la aplicación. gRPC es especialmente adecuada para servicios que requieran un alto rendimiento y solo necesite consumirse de forma interna. gRPC es una implementación de llamada a procedimiento remoto o RPC agnóstica del lenguaje de programación de alto rendimiento al emplear un formato de intercambio de datos binario más eficiente que JSON."
---

{{% post %}}

{{< logotype image1="grpc.png" image2="java.svg" >}}

Desarrollar una API con interfaz REST o [GraphQL][graphql] es la opción empleada mayoritariamente en los casos que hay que proporcionar datos y acceso a operaciones de forma programática a otra aplicación. Las API REST se basan en los elementos sobre los cuales está construida la web como el protocolo HTTP y su semántica junto con las operaciones de creación, modificación, lectura y eliminación organizando las operaciones alrededor de los recursos que se exponen mediante URLs utilizando JSON como formato de intercambio de datos basado en texto. GraphQL tiene importantes diferencias con REST al utilizar un esquema para definir el formato de los datos y la posibilidad de realizar varias consultas en la misma petición pero igualmente se basa en el protocolo HTTP y JSON.

* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]
* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]

Hace un tiempo ya comentaba una opción alternativa a las API REST y son las API RPC usando llamadas a métodos remotos. Las API REST son una buena opción aunque en casos que se necesite alto rendimiento imponen cierta sobrecarga en la comunicación al tener que hacer múltiples peticiones para obtener todos los datos necesarios de los diferentes _endpoints_ y por utilizar el formato de intercambio de datos JSON que impone cierto procesamiento tanto para procesarlo como para generarlo.

Uno de los problemas que presentaban las llamadas a métodos remotos es que solían estar encadenados a un lenguaje de programación en concreto como RMI con Java, pero hay algunas opciones de RPC más modernas que eliminan esta restricción y ofrecen mayor rendimiento y tipado seguro. Hace un tiempo ya comentaba sobre [Apache Thrift][apache-thrift] otra de ellas similar es gRPC auspiciada originalmente por Google.

* [Introducción y ejemplo de API RPC con Apache Thrift][blogbitix-72]

### Qué es gRPC

[gRPC][grpc] es una implementación de llamada a procedimiento remoto o _Remote Procedure Call_ (RPC) de alto rendimiento. Las invocaciones RPC permiten hacer llamadas a funciones remotas como si se tratase de llamadas a funciones locales ocultando en gran parte la dificultad en la comunicación por la red subyacente. Los servicios remotos se define en archivos descriptores o _Interface Description Language_ (IDL) en los que se incluyen las operaciones que ofrece el servicio así como las propiedades de los argumentos que recibe y devuelve como respuesta esta descripción basándose en [Protocol Buffers][protocol-buffers]. Los [tipos soportados](https://developers.google.com/protocol-buffers/docs/proto3#scalar) para las propiedades incluyen varios numéricos, de coma flotante, boleano, _string_ y byte.

gRPC al contrario que RMI que era específico de Java es agnóstico del lenguaje de programación en el que se implemente el servicio, a partir del archivo descriptor del servicio se genera unos archivos que sirven como base tanto para realizar la implementación en el lenguaje para la parte servidor como de la parte cliente del servicio pudiendo el servidor y cliente estar implementado en diferentes lenguajes. Los lenguajes soportados por gRPC están los más populares como Java, C#, C++, Dart, Go, Kotlin, Node/JavaScript, PHP, Python o Ruby.

#### Características de gRPC

* Alto rendimiento con seguridad: gRPC tiene alto rendimiento al usar _protobuf_ y HTTP/2 que son multiplexados, requieren una única conexión TCP, utilizan un formato de datos binario y posibilitan la comunicación bidireccional. Los mensajes al tener un esquema son más seguros de procesar.
* Comunicación bidireccional: esto permite a la parte cliente y servidor enviar datos simultáneamente en ambas direcciones.
* Balanceo de carga: incorpora balanceo de carga para seleccionar la instancia del servicio servidor a la que enviar el tráfico.
* Compresión selectiva: si se envían datos en formato texto e imagen se puede deshabilitar la compresión para las imágenes.
* Generación de código servidor y cliente: a partir del esquema se generan artefactos en cualquiera de los lenguajes soportado con los cuales crear la implementación del servidor y cliente rápidamente.

#### Las diferencias entre gRPC y REST

* Formato de intercambio de dato (Protobuf contra JSON): esa es una de las diferencias principales entre REST y gRPC, los mensajes REST contiene datos en formato JSON habitualmente mientras gRPC hace uso de Protobuf. Protobuf es una mejor forma de codificar datos estructurados, tiene mejor compresión y es más eficiente que JSON.
* Tipado fuerte contra serialización: en REST que normalmente se usa JSON no hay ningún mecanismo pra coordinar el formato de los datos intercambiados en las peticiones y respuestas que hay que tener en cuenta especialmente cuando se hacen cambios en la API para mantener la compatibilidad con los clientes o requiere actualizar los clientes de forma coordinada a la nueva versión lo que suele ser muy difícil. Por otro lado el formato JSON ha de ser convertir tanto en el servidor como en el cliente a estructuras de datos del lenguaje en el que estén implementados, la serialización es otro paso que añade la posibilidad de errores así como sobrecarga en el rendimiento.
* Mensajes contra recursos y verbos: REST está estrechamente basado en la semántica del protocolo HTTP, en lógica de negocio compleja es difícil trasladar la lógica de negocio y sus operaciones a los recursos y verbos que emplea REST. El modelo de gRPC traslada directamente los conceptos a los lenguajes de programación como interfaces, métodos y estructuras de datos.
* _Streaming_ contra Petición-Respuesta: REST solo soporta el único modelo petición-respuesta disponible en HTTP/1. gRPC hace uso de las capacidades de HTTP/2 y permite enviar y recibir información de forma constante tanto en el servidor como en el cliente.
* gRPC se basa en HTTP/2: HTTP/2 es un protocolo binario más eficiente y seguro de procesar, REST puede usarse con HTTP/1 pero si se usa junto a HTTP/2 obtiene algunos de sus beneficios.

#### Desventajas de gRPC

* No hay soporte para los navegadores web de modo que no puede ser usado en ellos como servicios expuestos al exterior. Para no imponer su uso su aplicación son para servicios que sean consumidos de forma interna y ofrecer una API basada en REST o GraphQL de forma externa.
* No hay _endpoints_ basados en URLs de modo que las peticiones y respuestas no pueden ser probadas con las herramientas [Postman][postman] o el comando _curl_.
* No hay códigos de estados predefinidos, cada situación de error es específica de cada método ofrecido por el servicio.

GraphQL tiene algunas similitudes con gRPC, igual que él utiliza un esquema para definir la interfaz del servicio y define las estructuras de datos que utilizan el cliente y el servidor ni emplea recursos ni verbos del protocolo HTTP. Pero al igual que REST utiliza JSON como formato para intercambiar los datos.

Estas [presentaciones en formato vídeo sobre gRPC](https://www.grpc.io/docs/talks/) contienen una introducción y explican algunos detalles con más profundidad.

{{< youtube
    video="OZ_Qmklc4zE" >}}
{{< youtube
    video="S7WIYLcPS1Y" >}}

### Ejemplo de servicio gRPC con Java

La construcción de un servicio o API basada en gRPC comienza con la definición del servicio en un archivo descriptor que incluye tanto las operaciones disponibles así como las estructuras de datos que incluye los nombres de los campos y tipos que reciben y devuelven como respuestas las llamadas a funciones remotas. El archivo descriptor del servicio sigue el [formato de Protocol Buffers](https://developers.google.com/protocol-buffers/docs/proto3).

En este ejemplo el servicio _HelloWorld_ tiene dos operaciones _HelloMessage_ y _HelloStream_ que reciben y devuelven dos estructuras de datos _HelloRequest_ y _HelloResponse_. La operación _HelloStream_ muestra como enviar el cliente un número indeterminado de mensajes al cliente que los va procesando según los envía el servidor. El esquema también contiene algunas opciones utilizadas por gRPC para personalizar la generación de las clases que sirven como base para realizar la implementación.

{{< code file="HelloWorld.proto" language="proto" options="" >}}

Una vez escrita la definición del servicio hay que generar las clases que sirven como base para realizar la implementación, [gRPC ofrece un _plugin_](https://github.com/google/protobuf-gradle-plugin) para [Gradle][gradle] sin necesidad de instalar ninguna herramienta adicional en el sistema. En el archivo de construcción además de las dependencias se indican algunas [propiedades para personalizar la compilación del archivo _proto_](https://github.com/google/protobuf-gradle-plugin) como el directorio en el que ubicar los archivos de código fuente generados y algunas opciones para que el IDE de [IntelliJ IDEA][intellij] reconozca ese directorio como ubicación de código fuente. En la documentación de Protocol Buffers hay una [guía del lenguaje](https://developers.google.com/protocol-buffers/docs/overview) y un [tutorial para Java](https://developers.google.com/protocol-buffers/docs/javatutorial).

{{< code file="build.gradle" language="groovy" options="" >}}

El siguiente paso es generar los artefactos para Java con la siguiente tarea, los artefactos se ubican en el directorio _src/generated_.

{{< code file="gradle-build.sh" language="bash" options="" >}}

Una vez generador los artefactos hay que realizar su implementación. En esta caso son muy sencillos, en casos más complejos seguramente se utilicen junto a [Spring][spring] para inyectarles dependencias que necesiten como otros servicios de la capa de aplicación que le permita obtener o persistir datos en la base de datos.

{{< code file="HelloWorldService.java" language="java" options="" >}}

gRPC necesita de una parte que actúa como servidor y otra parte que actúa como cliente, el servidor se inicia en un puerto para la comunicación por red y el cliente se conecta al puerto y dirección IP donde se inicia una de las instancias del servidor. Estas clases para el servidor y cliente son clases Java normales que hace un uso de algunas de las clases de gRPC.

{{< code file="HelloWorldServer.java" language="java" options="" >}}
{{< code file="HelloWorldClient.java" language="java" options="" >}}

Con Gradle se inicia programa que inicia servidor en la máquina local y el cliente que realiza varias peticiones e imprime en la salida sus respuestas.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="gradle-run.sh" language="bash" options="" >}}

Este es un ejemplo muy básico y no incluye varias necesidades habituales que se necesitan para implementar servicios y una API con grado de producción como balanceo de carga con múltiples instancias del servicio, tolerancia a fallos en caso de que una instancia deje de funcionar, descubrimiento y registro de servicios para que los clientes conozcan las ubicaciones de las instancias de los servidores, métricas, autenticación o como [evolucionar los servicios cuando estos requieran cambios](https://docs.microsoft.com/en-us/aspnet/core/grpc/versioning?view=aspnetcore-3.1). Para implementar en un servicio gRPC varias de estas funcionalidades hay que usar herramientas de las que he escrito y mostrado en otros artículos de forma específica como [Spring Boot][spring-boot], [Consul][Consul], [Traefik][traefik], [Resilience4j][resilience4j], [Nomad][nomad] que no tienen nada de diferentes al aplicarlas por el hecho de que el servicio esté basado en gRPC.

* [Aplicación Java autocontenida con Spring Boot][blogbitix-103]
* [Información y métricas de la aplicación con Spring Boot Actuator][blogbitix-113]
* [Registro y descubrimiento de servicios con Spring Cloud y Consul][blogbitix-206]
* [Introducción a Nomad para gestionar aplicaciones y microservicios][blogbitix-398]
* [Implementar tolerancia a fallos con Resilience4j][blogbitix-425]
* [Monitorizar una aplicación Java de Spring Boot con Micrometer, Prometheus y Grafana][blogbitix-366]
* [Microservicios con Spring Cloud, Consul, Nomad y Traefik][blogbitix-436]
* [Arquitectura de referencia de Consul, Vault y Nomad para un centro de datos][blogbitix-508]

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoGRPC" command="./gradlew run" %}}

{{< reference >}}
* [gRPC: The state of winning API for Microservices](https://medium.com/@omoletoye/grpc-the-state-of-winning-api-for-microservices-18d9b6bd8196)
* [Understanding gRPC](https://www.vineethweb.com/post/grpc/)
* [API design: Understanding gRPC, OpenAPI and REST and when to use them](https://cloud.google.com/blog/products/api-management/understanding-grpc-openapi-and-rest-and-when-to-use-them)
* [Cloud-native communication patterns, gRPC](https://docs.microsoft.com/en-us/dotnet/architecture/cloud-native/grpc)
* [Evaluating Performance of REST vs. gRPC](https://medium.com/@EmperorRXF/evaluating-performance-of-rest-vs-grpc-1b8bdf0b22da)
{{< /reference >}}

{{% /post %}}
