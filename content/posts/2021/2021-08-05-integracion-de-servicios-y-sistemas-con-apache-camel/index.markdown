---
pid: 590
type: "post"
title: "Integración de servicios y sistemas con Apache Camel"
url: "/2021/08/integracion-de-servicios-y-sistemas-con-apache-camel/"
date: 2021-08-05T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:apache-camel.svg"
tags: ["java", "planeta-codigo"]
summary: "Apache Camel es una librería específica para realizar tareas de integración que ya proporciona e implementa múltiples protocolos de comunicación, formatos de datos, componentes y patrones de integración. Ya tiene implementada toda esta funcionalidad que no hay que implementar en el caso de una aplicación con código propio. Al ser una librería es posible integrarlo en cualquier tipo de aplicación, en el artículo se muestra cómo utilizarlo en una aplicación de Spring Boot en un ejemplo."
---

{{% post %}}

{{< logotype image1="apache-camel.svg" image2="java.svg" >}}

Las empresas y organizaciones con cierta cantidad de años de vida con mucha probabilidad tiene una gran cantidad de servicios y sistemas con diferentes tecnologías, protocolos de comunicación y formatos de datos. Algunos de esos servicios y sistemas también tendrán varios años de vida, de entre ellos habrá alguno que ya puede ser considerado como obsoleto por la tecnología que utiliza, que ya no recibe soporte de nuevas características y solo es modificado en caso tareas de mantenimiento o en caso de un problema grave de seguridad. Estos servicios heredados aún con su antigüedad siguen siendo importantes por el servicio que prestan.

Sustituir esos servicios o sistemas heredados por otros nuevos a veces no es lo más adecuado ya que intervienen otros factores como el coste de tiempo requerido para desarrollar los nuevos sistemas que reemplacen a los antiguos, el coste económico, la disponibilidad de trabajadores que lo hagan y también por fiabilidad, cambiar un sistema con sus defectos y limitaciones pero que funciona por uno nuevo que no estará exento de sus propios problemas y defectos es un riesgo para el servicio prestado.

En vez de sustituir servicios y sistemas por unos nuevos una opción que se suele utilizar es proporcionar una integración. [Apache Camel][apache-camel] es una herramienta específica para realizar tareas de integración, que también se puede utilizar aún cuando no sea para un servicio o sistema heredado.

{{< tableofcontents >}}

## La librería Apache Camel

Apache Camel es una librería ligera destinada a realizar tareas de integración entre servicios y sistemas. La de utilizar esta librería sobre realizar una integración con código propio específico para cada integración es que Apache Camel ya proporciona una buena cantidad de funcionalidades sin necesidad de tener que implementarlas.

A diferencia de las herramientas _Enterprise Service Bus_ o ESB que también sin utilizadas para realizar tareas de integración entre sistemas heterogéneos y que suelen ser herramientas grandes y pesadas, Apache Camel es simplemente una librería muy ligera que es posible utilizarla embebida dentro de otras aplicaciones, por ejemplo dentro de una aplicación de [Spring Boot][spring-boot].

Apache Camel soporta multitud de protocolos de comunicación como HTTP, FTP o JMS, formatos de datos como JSON, XML o CSV e  integración con servicios como AWS, [Consul][consul] o Twitter entre muchos otros. También ya tiene implementados multitud de patrones de integración como _choice_, _filter_, _muticast_, _circuit breaker_ o _bulkhead_. Otra de sus funcionalidades es que soporta realizar pruebas unitarias.

{{< amazon
    linkids="https://amzn.to/49caCmt"
    asins="1617292931"
    titles="Camel in Action" >}}

### Conceptos de Apache Camel

Apache Camel utiliza varios conceptos. La integración o funcionalidades desarrolladas se modelan como un flujo, ruta o _route_ que comienza a partir de un origen o _consumer_ y se envía a un destino o _producer_. En este flujo se tratan mensajes o _Exchange_ que contiene además de los datos del mensaje o _payload_ metadatos como cabeceras asociadas. En los diferentes pasos del flujo el _Exchange_ puede sufrir transformaciones con los procesadores o _processor_ y en el que se aplican los diferentes patrones de integración o _integration patterns_.

Una parte importante de Apache Camel que lo hacen fácil de utilizar son los _endpoints_ que son URLs compuestas de un esquema, contexto y opciones. Un ejemplo de endpoint es el siguiente del [componente RabbitMQ](https://camel.apache.org/components/latest/rabbitmq-component.html) _rabbitmq:exchange_  para tomar como fuente o destino colas de mensajes o del [componente File](https://camel.apache.org/components/latest/file-component.html) _file:misc/_ para el sistema de archivos.

Los flujos se modelan con un lenguaje de dominio específico o DSL ya sea definiéndolo con código Java o en un archivo con formato XML. Al utilizar código Java se gana el soporte del entorno integrado de desarrollo, asistencia de código y detección de errores de compilación.

Al igual que en una aplicación de Spring existe el _ApplicationContext_, Apache Camel posee un contexto a modo de registro con todos los objetos de la instancia de Camel.

La colección de componentes de Apache Camel es muy numerosa.

* [Componentes de Apache Camel](https://camel.apache.org/components/latest/index.html)
* [Otros componentes de Apache Camel](https://camel.apache.org/components/latest/others/index.html)
* [Lenguajes de expresiones de Apache Camel](https://camel.apache.org/components/latest/languages/index.html)

Los formatos de datos que soporta también son muy numerosos.

* [Formatos de datos soportados por Apache Camel](https://camel.apache.org/components/latest/dataformats/index.html)

También soporta los patrones de integración identificados en el libro [Enterprise Integration Patterns](https://amzn.to/2WQDe4G) que ya han demostrado su utilidad para solventar y simplificar los problemas a los que están dirigidos.

* [Patrones de integración de Apache Camel](https://camel.apache.org/components/latest/eips/enterprise-integration-patterns.html)

{{< amazon
    linkids="https://amzn.to/47SG48b"
    asins="0321127420"
    titles="Patterns of Enterprise Application Architecture" >}}

### Patrones de integración

Algunos de los patrones básicos que soporta Apache Camel son _choice_ para elegir rutas alternativas a las que dirigir los mensajes, _filter_ para descartar los mensajes que no cumplan alguna condición, _multicast_ para enviar un mensaje a varios destinos, _recipient list_ para enviar a varios destinos de forma dinámica o _wire tap_ para inspeccionar los mensajes sin alterar su flujo normal. Esos son solo unos pocos patrones de integración soportados.

{{< image
    gallery="true"
    image1="image:cbr.webp" optionsthumb1="300x200" title1="Patrón content based router"
    image2="image:filter.webp" optionsthumb2="300x200" title2="patrón filter" >}}
{{< image
    gallery="true"
    image1="image:multicast.webp" optionsthumb1="300x200" title1="Patrón multicast"
    image2="image:recipient-list.webp" optionsthumb2="300x200" title2="Patrón recipient list"
    caption="Diferentes patrones de integración" >}}

## Ejemplo básico con Apache Camel

Apache Camel al ser una librería es muy fácil de integrarlo en cualquier tipo de aplicación, en este ejemplo se utiliza Spring Boot. El ejemplo consiste en dos rutas, una que simplemente muestra en la salida los mensajes que se envía, la otra ruta lee los archivos CSV de un directorio que contienen listas de productos en diferentes columnas, filtra los productos que no tienen un importe superior a una cantidad, los transforma y les añade el IVA y finalmente los muestra en la salida, cada vez que en el directorio se añade un CSV se procesa.

Esta es la definición de varias rutas con su DSL en código Java que se definen en las clases que implementan la interfaz _RouteBuilder_, utilizando Spring definiéndose como un componente son añadidos de forma automática al contexto de Apache Camel. En la ruta _HelloWorldRoute_ simplemente tomo como fuente lo que llega al _endpoint_ de nombre _direct:helloworld_ y lo dirige a la salida del sistema con _stream:out_ sin ningún procesamiento adicional entre el origen y el destino.

{{< code file="HelloWorldRoute.java" language="java" options="" >}}

Al inicio del programa se envía al consumidor de la ruta _helloworld_ diez [UUID](javadoc11:java.base/java/util/UUID.html).

{{< code file="Main.java" language="java" options="" >}}

La siguiente ruta es algo más compleja y muestra varias de las capacidades de Apache Camel. Monitoriza un directorio con un archivo en formato CSV, cuando este se crea o está presente al iniciar la aplicación la ruta lo toma como fuente de datos e inicia su procesamiento en el flujo.

Primeramente se procesan los datos transformándolos en objetos Java de tipo _Book_ que son simplemente objetos POJO con una propiedad por cada columna del CSV. Al procesar los datos se obtiene una lista de objetos de tipo Book, con la operación _split_, la lista de divide en objetos individuales en el flujo.

Posteriormente, se aplica una condición sobre los objetos, según si el objeto cumple la condición o no se envían a un destino u otro. Según el destino al que están dirigidos se establece un con una cabecera que se transmiten como metadato al mismo tiempo que los datos.

Finalmente, los mensajes llegan al destino _direct:books-stream-out_, se aplica un filtro sobre la cabecera anterior, si la cumple se aplica un procesamiento al mensaje para aplicar el IVA sobre el precio del libro y una transformación que cambia el tipo del mensaje de _Book_ a una cadena _String_, para terminar la cadena se envía a _stream:out_ para imprimirlo en la salida de la aplicación.

Una vez procesado el CSV con éxito Apache Camel lo mueve a una carpeta oculta _.camel_, si el mismo archivo es vuelto a copiar en la capeta se procesa de nuevo.

{{< code file="BooksRoute.java" language="java" options="" >}}
{{< code file="Book.java" language="java" options="" >}}
{{< code file="books.csv" language="plain" options="" >}}

Esta es la salida del programa.

{{< code file="System.out" language="plain" options="" >}}

Para su ejecución se utiliza la herramienta de construcción [Gradle][gradle] con el siguiente archivo donde se definen las dependencias del proyecto. La librería de Apache Camel para Spring Boot proporciona la funcionalidad de la que la aplicación se mantenga en funcionamiento tal como ocurre cuando se utiliza la dependencia de Spring para desarrollar aplicaciones web.

{{< code file="build.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoApacheCamel" command="./gradlew run" %}}

{{% /post %}}
