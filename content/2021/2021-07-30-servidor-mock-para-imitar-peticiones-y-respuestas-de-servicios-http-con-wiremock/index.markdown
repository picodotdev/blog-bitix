---
pid: 589
type: "post"
title: "Servidor mock para imitar peticiones y respuestas de servicios HTTP con WireMock"
url: "/2021/07/servidor-mock-para-imitar-peticiones-y-respuestas-de-servicios-http-con-wiremock/"
date: 2021-07-30T12:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Los microservicios aportan varias ventajas pero también algunos inconvenientes que si no son manejados generan sus propios problemas. Una dificultad de los servicios por las dependencias entre ellos es poder desarrollarlos y probarlos en local, algunos microservicios son complejos con dependencia sobre bases de datos, sistemas de envío de mensajes u otros servicios. Si un microservicio necesita iniciar en local o en entorno todas sus dependencias el desarrollo se vuelve complejo y lento. Para facilitar el desarrollo una opción es utilizar un servidor _mock_ que imite las respuestas para las peticiones que se necesite de uno o varios servicios."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una aplicación diseñada como una colección de microservicios se compone de múltiples de ellos, unos microservicios son consumidos por otros y a su vez un microservicio consume otros uno o varios.

Algunas aplicaciones son diseñadas para ofrecer su funcionalidad a través de un API desde el primer momento por su independencia de los clientes que hagan uso de ella. Tener un API permite dar soporte a los múltiples clientes ya sean directamente desde el navegador web, una aplicación nativa de un dispositivo como un teléfono inteligente o incluso para ofrecer a tercera partes de modo que realicen integraciones y automatizaciones según sus necesidades.

Las ventajas de los microservicios son varios como los anteriores junto a algunos otros adicionales, sin embargo, añaden otros problemas, principalmente el mayor número de elementos que los hacen más complejos comparado con una aplicación monolítica.

{{< tableofcontents >}}

### El servidor _mock_

Muchas aplicaciones se basan en [microservicios REST haciendo uso del protocolo HTTP y JSON][blogbitix-178] como formato de datos. Un servidor _mock_ es simplemente un servidor web que en caso de los microservicios es utilizado para programar las respuestas para las peticiones que se le hagan según el _endpoint_ invocado, variables en el _path_, parámetros o cabeceras. Las respuestas programadas incluyen el código de estado, cabeceras devueltas y datos del cuerpo.

Un servidor de imitación o _mock_ facilita el desarrollo de los microservicios de forma independiente y las pruebas. El servidor _mock_ elimina la dependencia de un servicio real junto con todo el entorno de ejecución que necesite que en el caso de algunos llega a ser notablemente complejo si incluye base de datos, sistemas de mensajería u otros servicios. El servicio es sustituido por una imitación que devuelve las respuestas programadas para cada una de las peticiones.

Otro caso de uso de un servidor _mock_ es permitir realizar pruebas de código o convertir pruebas de integración en unitarias. Otro uso de un servidor _mock_ es que permite centrarse en el desarrollo de un servicio sin necesidad de usar servicios reales incluso antes de que estos estén implementados si su interfaz está definida.

Uno de los potenciales riesgos de utilizar un servidor _mock_ es que este no se ajuste a la realidad del servicio real cuando este contenga cambios incompatibles. Un servidor _mock_ permite simular las respuestas de un servicio HTTP lo que facilita las pruebas unitarias de la parte cliente, sin embargo, esto no asegura que el servidor al realizar en las pruebas de integración o en producción cumpla con el contrato que el cliente espera de su API. Para asegurar que el servidor soporta las peticiones esperadas por la parte cliente y devuelve los datos esperados otra forma de pruebas son las [pruebas de contrato o _contract testing_ con Pact][blogbitix-591], una herramienta de pruebas de contrato que soporta el lenguaje Java entre otros.

### Opciones de servidores _mock_

Como cualquier otro tipo de herramienta hay múltiples opciones entre las que elegir. La principal característica de todo servidor _mock_ es permitir programar las respuestas según las peticiones, sus diferencias está en el lenguaje de programación en el que están implementadas y su entorno de ejecución necesario así como su tipo de licencia. Algunas ofrecen programar las respuestas a través de una API del lenguaje de programación para el que están destinadas.

Hay muchas opciones de servidor _mock_ algunas conocidas son [MockServer][mockserver], [WireMock][wiremock], [Imposter][imposter] o [Prism][prism]. Varias implementadas con JavaScript, otras en Java y algunas incluso se ofrecen en forma de software como servicio para delegar el mantenimiento de la herramienta en una tercera parte.

### Características de WireMock

WireMock es una opción bastante conocida de servidor para hacer _mocking_. Ofrece bastante flexibilidad en la forma de aprovisionar las respuestas programadas ya sea a través de un archivo de configuración, peticiones REST una vez iniciado el servidor _mock_ o de forma programática mediante una API de Java. También es bastante flexible en su forma de ejecución pudiendo ser como una aplicación Java independiente, de forma embebida como parte de una aplicación Java como sería el caso de querer utilizarlo para realizar pruebas unitarias o como un contenedor de [Docker][docker].

Ofrece una potente definición de correspondencia entre las peticiones realizadas a través de las URLs, métodos, cabeceras, _cookies_ con diferentes estrategias, también ofrece soporte para generar respuestas en formato JSON o XML pudiendo utilizar plantillas para crear respuestas dinámicas según la petición de entrada.  Otras características que ofrece es soporte para HTTPS, hacer de intermediario o _proxy_ entre la aplicación y el servicio real para peticiones que no están programadas, permitir grabar las respuestas obtenidas por la funcionalidad de _proxy_, simular errores como tiempos de respuesta elevados y crear flujos de peticiones con escenarios que dependen de estado e interacciones previas.

Es simple de iniciar y configurar, tiene una documentación suficiente para aprender sus conceptos básicos, configuración junto la [documentación completa de la API REST](http://wiremock.org/docs/api/) y empezar a usarlo en poco tiempo, está implementado en Java que lo hace adecuado si es el entorno de ejecución utilizado para los microservicios.

### Ejemplo de prueba de WireMock

De forma oficial el proyecto ofrece un archivo _jar_ ejecutable que inicia el servidor web de WireMock de forma independiente, una vez iniciado expone una API REST a través de la cual es posible aprovisionar las respuestas, el aprovisionamiento y configuración también es posible realizarlo mediante parámetros de inicio. A partir de este archivo _jar_ ejecutable es posible [crear una imagen de Docker][blogbitix-51] con el servicio para ejecutarlo en forma de contenedor o [en pruebas unitarias o de integración con Testcontainers][blogbitix-490], alguna persona ya ha creado una [imagen de Docker de WireMock](https://github.com/rodolpheche/wiremock-docker).

#### Como aplicación independiente

Este es el comando de inicio de WireMock como aplicación independiente.

{{< code file="wiremock.sh" language="bash" options="" >}}
{{< code file="wiremock.out" language="plain" options="" >}}

Por defecto el servidor _mock_ se inicia en el puerto 8080. Con las siguientes peticiones REST es posible aprovisionar manualmente las respuestas, estas peticiones utilizan la API REST de WireMock. También es posible realizar el aprovisionamiento con archivos de configuración creando una carpeta en el directorio de trabajo de nombre _mappings_ creando archivos con extensión _json_ con el contenido del JSON de cada uno de los _mappings_.

{{< code file="curl-wiremock-provision.sh" language="bash" options="" >}}

Una vez aprovisionado el servidor _mock_ con las respuestas deseadas al realizar peticiones al servidor de WireMock si estás coinciden se devuelven las respuestas, la respuestas incluye el código de estado, las cabeceras y los datos de respuesta tal como fueron aprovisionados. En este caso las peticiones se hacen con el comando _curl_ que simulan las peticiones de una aplicación.

{{< code file="curl-wiremock-request.sh" language="bash" options="" >}}

En caso de que la petición no coincida con una aprovisionada se devuelve en error 404.

{{< code file="curl-wiremock-nomatch.sh" language="bash" options="" >}}

Modificando la aplicación para que las peticiones las haga al servidor de WireMock la aplicación es posible desarrollarla o probarla sin necesidad del servicio real y sus dependencias.

#### Embebido en una aplicación para hacer pruebas unitarias

En el caso de utilizar WireMock para realizar pruebas unitarias el servidor de WireMock ha de iniciarse y aprovisionarse en el contexto de las pruebas, en este caso para las [pruebas unitarias con teses de Junit5][blogbitix-410].

En este ejemplo se crea [una interfaz de un cliente de una API REST con Retrofit][blogbitix-569], a partir de esta interfaz Retrofit permite crear una instancia de un objeto que a través de sus métodos y parámetros permite hacer llamadas al servicio REST mediante código Java eliminando los detalles de que en realidad hace una petición HTTP.

{{< code file="Service.java" language="java" options="" >}}

La aplicación en su código crea una instancia del cliente del servicio REST e invoca sus métodos de llamada, dado que el cliente realiza operaciones de red al ejecutarlo al hacer la prueba unitaria si el servicio no está iniciado la comunicación fallará produciendo errores.

{{< code file="Main.java" language="java" options="" >}}

Es en este punto donde entra WireMock que permite simular ese servicio, en este ejemplo de prueba unitaria se inicia el servidor de WireMock, se aprovisiona con la petición esperada y respuesta desea a devolver. Se ejercita el código que se desea probar en este caso el método _getMessage_ de la clase _Main_ que en su implementación hace uso del cliente del servicio REST con Retrofit y que en la prueba invocará al servidor de WireMock. Finalmente, se comprueba que la respuesta del clase _Main_ coincida con la esperada.

{{< code file="MainTest.java" language="java" options="" >}}

Este el archivo de [Gradle][gradle] con las dependencias.

{{< code file="build.gradle" language="groovy" options="" >}}

En este caso WireMock se ha usado de forma directa, en el caso de utilizar [Spring Boot][spring-boot] uno de los proyectos de [Spring proporciona librerías para facilitar y hacer compatible el uso de WireMock con JUnit y Spring](https://cloud.spring.io/spring-cloud-contract/reference/html/project-features.html#features-wiremock).

{{% sourcecode git="blog-ejemplos/tree/master/JavaWireMock" command="./gradlew test" %}}

{{% /post %}}
