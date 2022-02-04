---
pid: 591
type: "post"
title: "Introducción y ejemplo de contract testing con Pact"
url: "/2021/08/introduccion-y-ejemplo-de-contract-testing-con-pact/"
date: 2021-08-12T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:pact.svg"
tags: ["java", "planeta-codigo"]
summary: "Al realizar un cambio en un API hay que ser consciente de que los cambios sean compatibles hacia atrás, de lo contrario algunos clientes de la API es probable que dejen de funcionar o tengan un comportamiento erróneo. Para asegurar que los cambios sean compatibles hacia atrás se realizan pruebas unitarias automatizadas de contrato, en Java una opción es Pact para pruebas de contrato de APIs REST."
---

{{% post %}}

{{< logotype image1="pact.svg" image2="java.svg" >}}

Las aplicaciones que ofrecen una API establecen un contrato con los consumidores, los consumidores al usar la API crean una dependencia. Para que un cambio API sea compatible hacia atrás no debe requerir cambios en los consumidores, si el cambio en la API requiere cambios en los consumidores estos corren el riesgo de dejar de funcionar correctamente. Los cambios no compatibles hacia atrás son un problema ya que requieren coordinar el cambio con los consumidores, los desarrolladores de la API tienen control sobre el proveedor pero en algunos casos no sobre los consumidores que deben ser adaptados por sus propietarios.

Idealmente todos los cambios deberían ser compatibles hacia atrás, sin embargo, en ocasiones no queda más alternativa que introducir un cambio no compatible. Para evitar el problema una opción es versionar la API de tal modo que los nuevos consumidores utilicen la nueva API y los consumidores de una versión anterior tengan un tiempo para adaptarse a la nueva API, durante un tiempo la API antigua y la nueva funcionan simultáneamente, pasado un tiempo y cuando los consumidores hayan pasado a usar la nueva API la versión antigua se elimina.

REST también es una forma de API en este caso ofrecida a través del protocolo HTTP y habitualmente con JSON con formato de datos, al hacer cambios en una API REST el principio de que el cambio sea compatible hacia atrás se aplica. En REST la API está formada por las direcciones de los _endpoints_, los parámetros de consulta, las cabeceras de la petición y de respuesta, los códigos de estado de respuesta  y los datos devueltos así como el formato de datos devueltos.

Cambios compatibles hacia atrás son añadir un nuevo campo aceptado en la petición si no es obligatorio o devuelto en la respuesta o un nuevo parámetro de consulta o un nuevo _endpoint_. Cambios no compatibles son por el contrario eliminar un campo en la respuesta o eliminar un _endpoint_. Para posibilitar cambios en una API también se suele utilizar el patrón primero expandir luego contraer o _expand-contract_ con la cual primero se aplican cambios que añaden cosas y posteriormente cuando dejan de usarse se eliminan los que ya no se utilizan, este mismo patrón es aplicable a otras áreas como por ejemplo cambios en las bases de datos.

Para garantizar que los cambios realizados en una API no introduzcan problemas de compatibilidad hacia atrás se realizan pruebas de contrato. Son especialmente útiles cuando el equipo encargado de la parte productora es distinto del equipo de la parte consumidora ya sea en una misma empresa o de empresas diferentes.

{{< tableofcontents >}}

### Las pruebas de contrato

En el caso de las API con REST para garantizar que tanto el consumidor y el productor son compatibles a veces se realizan pruebas de integración o pruebas _end-to-end_ o E2E, sin embargo, estas son costosas de realizar en tiempo y esfuerzo requerido. Para simplificar y automatizar estas pruebas de integración una opción es realizar pruebas de contrato.

Las pruebas de contrato consisten en primera instancia en que el consumidor define las interacciones que necesita, las codifica en un servidor _mock_ que imita las respuestas del productor, realiza las pruebas unitarias y se genera un contrato con las interacciones requeridas para la parte productora.

Con el contrato generado por el consumidor las interacciones se reproducen en la parte productora, se comparan las respuestas del productor con las requeridas por el consumidor y si coinciden el productor cumple el contrato que requiere el consumidor.

Las pruebas de contrato permiten convertir las pruebas de integración en pruebas unitarias, para ello separa las pruebas del consumidor y las pruebas de productor. Una herramienta de pruebas de contrato es Pact.

### La herramienta Pact

[Pact][pact] es una herramienta para realizar pruebas de contrato que soporta el lenguaje Java con la librería [JUnit][junit] para realizar pruebas unitarias entre otros lenguajes.

Pact en la parte consumidor también hace las funciones de servidor sin embargo adicionalmente [el servidor _mock_ de WireMock][blogbitix-589] permite guardar esas interacciones y realizar las pruebas para la parte productora. Esto permite detectar problemas de que un cambio introduzca problemas de incompatibilidad y poder probar de forma desacoplada el consumidor y productor.

{{< image
    gallery="true"
    image1="image:pact-summary.png" optionsthumb1="650x450" title1="Pruebas de contrato con Pact"
    caption="Pruebas de contrato con Pact" >}}

{{< youtube
    video="IetyhDr48RI" >}}

### Ejemplo de _contract testing_ con Pact

Este ejemplo consiste  en un _endpoint_ REST programado usando [Spring Boot][spring-boot] que acepta un argumento opcional en la ruta y un parámetro de consulta. La respuesta consiste simplemente en un mensaje en forma de cadena que varía según la cabecera _Accept-Language_.

{{< code file="RestService.java" language="java" options="" >}}

El consumidor del servicio está implementado usando [la librería Retrofit para crear el cliente][blogbitix-569] que abstrae de las llamadas HTTP.

{{< code file="Service.java" language="java" options="" >}}
{{< code file="ServiceClient.java" language="java" options="" >}}

#### Pruebas unitarias del consumidor

En los casos de prueba se codifican las interacciones esperadas por el cliente que son proporcionadas por Pact en un servidor _mock_, las pruebas unitarias usan el cliente HTTP con la dirección del servidor _mock_ de Pact que es proporcionado como un parámetro en los métodos de _test_.

{{< code file="ServiceConsumerPactTest.java" language="java" options="" >}}

#### El documento del contrato generado por el consumidor

Al finalizar las pruebas unitarias del consumidor Pact genera en el directorio _build/pact_ un archivo con las interacciones y sus datos que ha requerido el consumidor en sus pruebas unitarias.

{{< code file="serviceConsumer-serviceProvider.json" language="json" options="" >}}

#### Pruebas unitarias del proveedor

Este archivo es usado para realizar las pruebas unitarias de contrato de la parte proveedora, Pact lee el archivo de interacciones del consumidor y las lanza contra la parte proveedora comprobando los resultados devueltos.

{{< code file="ServiceProviderPactTest.java" language="java" options="" >}}

Estas son las dependencias necesarias a incluir en el archivo de construcción [Gradle][gradle].

{{< code file="build.gradle" language="groovy" options="" >}}

Para este ejemplo por sencillez las interacciones del contrato generador por el consumidor es proporcionado a la parte proveedora a través del sistema de archivos. Pact proporciona un servidor [Pact Broker](https://docs.pact.io/pact_broker/docker_images) donde los consumidores comparten los contratos y de donde los proveedores los obtienen para comprobarlos funcionando como un repositorio de los contratos. Se ofrece la opción de ejecutar Pact Broker mediante [contenedores Docker con un archivo de Docker Compose][blogbitix-87].

* [Dockerised Pact Broker](https://github.com/pact-foundation/pact-broker-docker)
* [Pact Broker docker compose](https://github.com/DiUS/pact_broker-docker/blob/master/docker-compose.yml)
* [How To Publish Pact Contract To Pact Broker](https://www.softwaretestinghelp.com/publish-pact-contract-to-pact-broker/)

{{% sourcecode git="blog-ejemplos/tree/master/JavaPact" command="./gradlew test" %}}

{{< reference >}}
* [How to Test Java Microservices with Pact](https://blogs.oracle.com/javamagazine/how-to-test-java-microservices-with-pact)
* [Consumer-Driven Contract Testing using Pact Java](https://blog.testproject.io/2020/05/27/consumer-driven-contract-testing-using-pact-java/)
{{< /reference >}}

{{% /post %}}
