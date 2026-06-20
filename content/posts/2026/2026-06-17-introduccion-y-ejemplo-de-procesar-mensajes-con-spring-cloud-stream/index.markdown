---
pid: 733
type: "post"
title: "Introducción y ejemplo de procesar mensajes con Spring Cloud Stream"
url: "/2026/06/introduccion-y-ejemplo-de-procesar-mensajes-con-spring-cloud-stream/"
date: 2026-06-17T22:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["java", "planeta-codigo"]
summary: "Las aplicaciones distribuidas emplean comunicación basada en mensajes para lograr un intercambio asíncrono y desacoplado entre servicios. Spring Kafka ofrece una integración directa con Apache Kafka mediante la anotación KafkaListener y KafkaTemplate, sin embargo, cuando los mensajes fluyen entre múltiples topics, esta aproximación de bajo nivel se vuelve compleja. Spring Cloud Stream resuelve este problema simplificando el modelo de desarrollo al usar las interfaces funcionales de Java, Consumer, Function y Supplier para modelar consumidores, procesadores y productores de forma declarativa y altamente configurable."
---

{{% post %}}

{{< logotype image1="spring.svg" >}}

Las aplicaciones distribuidas suelen emplear mecanismos de comunicación basados en mensajes. La comunicación basada en mensajes tiene la particularidad deseable en algunos casos de ser una forma de comunicación asíncrona y desacoplada entre el productor del mensaje y el consumidor del mensaje. Esto hace a las aplicaciones procesadores de un flujo o _stream_ constante de mensajes.

Entre las muchas librerías que ofrece el framework de Spring está la integración con el sistema de mensajería de Kafka. Esta librería permite el consumo y producción de mensajes de los topics de Kafka. Con la anotación [KafkaListener](https://docs.spring.io/spring-kafka/reference/kafka/receiving-messages/listener-annotation.html) se define el punto de entrada en el código de los mensaje de Kafka, con la clase [KafkaTemplate](https://docs.spring.io/spring-kafka/api/org/springframework/kafka/core/KafkaTemplate.html) permite hacer el envío de mensajes de Kafka.

* [Introducción, conceptos y uso básico del broker de mensajes Apache Kafka][blogbitix-672]

Algunas aplicaciones no solo escriben y leen de un _topic_ de Kafka, los mensajes siguen un flujo de procesamiento entre varios _topics_. Por otro lado, la librería de Spring Kafka ofrece una integración a bajo nivel que se vuelve compleja en integraciones con varios consumidores y productores. Spring Cloud Stream simplifica el modelo de desarrollo de Spring Kafka, al mismo tiempo lo hace más configurable.

{{< tableofcontents >}}

## Spring Cloud Stream

[Spring Cloud Stream][spring-cloud-stream] permite en las aplicaciones de Spring utilizar las interfaces funcionales de Java como [Consumer](javadoc:java.base/java/util/function/Consumer.html), [Function](javadoc:java.base/java/util/function/Function.html) y [Supplier](javadoc:java.base/java/util/function/Supplier.html) para modelar consumidores, procesadores y productores de mensajes.

Otra de sus propiedades es que permite la integración con diferentes tipos de sistemas de mensajería como [Kafka][apache-kafka] o [RabbitMQ][rabbitmq] con cambios mínimos en el código.

* [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream)
* [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)

### Conceptos

### Binding

Las instancias de interfaces funcionales que procesan mensajes de Kafka se denominan _bindings_. No son más que un _bean_ de Spring que implementa una de las interfaces funcionales.

Estas definiciones de _bean_ junto con cierta configuración para definir de que _topics_ leen los mensajes y en cual se escriben permiten el procesado de los mensajes.

{{< code file="UppercaseFunction.java" language="java" options="" >}}
{{< code file="Beans.java" language="java" options="" >}}

### Binding names

El nombre del método de la interfaz funcional determina el nombre del _binding_. Con cierta convención en los nombres se establecen las propiedades de configuración específica en cada uno de los _bindings_. El nombre del _binding_ está determinado por el nombre de la funció, _in_ y _out_ corresponding al tipo del _binding_ y finalmente un índice que suele ser 0 para la función típica con un solo elemento de entrada y de salida.

{{< code file="application-1.yml" language="yaml" options="" >}}
{{< code file="binding-names.txt" language="plain" options="" >}}

Es posible dar un nombre personalizado al _binding_ y establecer las propiedades de configuración usando ese nuevo nombre.

{{< code file="application-2.yml" language="yaml" options="" >}}

### Binder

Los _binder_ son los sistemas de mensajería con los que se integran los _bindings_. Hay varios, uno de ellos es Kafka pero también están RabbitMQ y [Apache Pulsar][apache-pulsar].

## Propiedades de configuración

Buena parte del uso de Spring Cloud Streams se realiza en las propiedades de configuración. Se pueden establecer propiedades de configuración que afectan a todos los consumidores y productores o un consumidor o productor específico. Y propiedades específicas del _binder_.

* [Spring Cloud Stream, Configuration Options](https://docs.spring.io/spring-cloud-stream/reference/kafka/kafka-binder/config-options.html)
* [Kafka Connect Configs](https://kafka.apache.org/41/configuration/kafka-connect-configs/)

La lista completa de propiedades que permiten los consumidores y productores de Kafka son las siguientes.

* [Kafka Consumer Configs](https://kafka.apache.org/41/configuration/consumer-configs/)
* [Kafka Producer Configs](https://kafka.apache.org/41/configuration/producer-configs/)

### Binder

Con la siguientes propiedades se especifica la configuración de conexión a los _brokers_ de Kafka.

{{< code file="application-3.yml" language="yaml" options="" >}}

### Consumer

En el consumidor es posible querer especificar el _serializer_ y _deserializer_ de los mensajes, la configuración de procesamiento en _batches_ o el grupo del consumidor en caso de varias instancias.

{{< code file="application-4.yml" language="yaml" options="" >}}

### Producer

En el productor también es posible especificar el serializer para la _key_ y _value_ del mensaje de Kafka, usando las propiedades de Kafka para los productores. Hay otras propiedades que definen Spring Cloud Stream.

## Gestión de errores

Por defecto, la gestión de errores al procesar un mensaje es loggearlo y descartar. Es posible implementar handler personalizados en caso de errores, basta con definir un consumer de ErrorMessage.

{{< code file="CustomErrorHandler.java" language="java" options="" >}}
{{< code file="application-5.yml" language="yaml" options="" >}}

En caso de no querer descartar un mensaje cuando falla su procesamiento es posible enviarlo a un _topic_ especial con el patrón _dead-letter-queue_ para su revisión y posterior procesamiento. La siguiente configuración crea un _topic_ automáticamente para el consumidor.

{{< code file="application-6.yml" language="yaml" options="" >}}

Con la siguiente propiedad se configura el número máximo de intentos de procesado antes de enviarlo al _topic_ _dead-letter-queue_. En este ejemplo el procesado se realiza 3 veces.

{{< code file="application-7.yml" language="yaml" options="" >}}

## Observabilidad

Para implementar métricas de observabilidad hay que añadir las siguientes dependencias y la aplicación se autoconfigura.

{{< code file="build.gradle.kts" language="kotlin" options="" >}}

## Pruebas unitarias

Spring Cloud Stream ofrece la posibilidad de probar de forma unitaria a los consumidores y productores. Ya sea la función directamente o como un binding de Spring Cloud Stream.

{{< code file="UppercaseFunctionTest.java" language="java" options="" >}}

{{< youtube
    video="oTTfaynD1Xc" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringCloudStream" command="./gradlew run" %}}

{{< reference >}}
* [Introduction to Spring Cloud Stream](https://www.baeldung.com/spring-cloud-stream)
{{< /reference >}}

{{% /post %}}