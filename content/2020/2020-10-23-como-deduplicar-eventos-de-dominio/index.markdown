---
pid: 524
type: "post"
title: "Cómo deduplicar eventos de dominio"
url: "/2020/10/como-deduplicar-eventos-de-dominio/"
date: 2020-10-23T16:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Las aplicaciones distribuidas utilizan la comunicación de mensajes para notificar de la ocurrencia de ciertos eventos en el sistema que los interesados reciben. En el envío y recepción de mensajes pueden ocurrir dos situaciones que hay que manejar, una es garantizar que cada mensaje se envíe al menos una vez para lo que se emplea el patrón _outbox pattern_ y la segunda es no procesar un evento recibido por duplicado para lo que se emplea deduplicación de mensajes."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

El patrón _outbox pattern_ garantiza que los eventos de dominio se envíen al menos una vez, pero que se envíe una vez no impide que sean enviados varias veces. La deduplicación de eventos permite evitar procesar el mismo evento varias veces si se envía repetido. Una forma de conseguir de duplicación de eventos es asignando a cada evento un identificativo único y que la parte receptora de los eventos compruebe si el evento recibido ha sido ya procesado. La parte receptora para determinar si un evento ha sido ya procesado guarda los identificativos de los ya procesados.

Los servicios de una aplicación pueden [utilizar comunicación con mensajes mediante RabbitMQ][blogbitix-210]. La parte receptora lee los mensajes y los procesa en la misma transacción que el resto de operaciones guardando en la base de datos el identificativo del evento procesado, de modo que si es recibido varias veces la parte receptora lo deduplica. Si la transacción falla el evento no se marca como recibido y el sistema de mensajería lo mantiene para enviarlo de nuevo hasta que se procese correctamente, si la transacción se completa pero el mensaje del evento no se notifica como procesado correctamente en el sistema de mensajería el sistema de mensajería lo enviará de nuevo pero la parte receptora lo deduplica. Si la parte receptora completa la transacción y notifica como procesado el mensaje en el sistema de mensajería el sistema de mensajería ya no lo enviará de nuevo.

Con el paso del tiempo y dependiendo del volumen de eventos procesados el número de eventos marcados como procesados en la base de datos si es tan grande como para suponer un problema de rendimiento para saber si un evento ya ha sido procesado se puede eliminar de forma periódica aquellos que ya se estimen que ya no van a volver a llegar pasado un tiempo, puede ser tan simple como eliminar todos los eventos ya procesados de hace más de un mes o la fecha más adecuada que se estime.

### Ejemplo de implementación de deduplicación de eventos de dominio

Para asignar un identificativo a cada mensaje se puede utilizar un identificador único universal, en Java estos se generan con la clase [UUID](javadoc11:java.base/java/util/UUID.html). El mensaje además de los datos que incluya incluye este identificativo del mensaje.

{{< code file="Event.java" language="java" options="" >}}
{{< code file="OrderCreated.java" language="java" options="" >}}

En el ejemplo de los artículos relacionados [eventos de dominio en agregados][blogbitix-520] y [bus de comandos y consultas][blogbitix-523] cuando se realiza una orden de compra se emite un mensaje para notificar a otros servicios, en el ejemplo el evento de orden de compra creada se utiliza para actualizar el inventario de productos.

Si en el contexto del inventario se recibe por duplicado un mensaje de orden creada resulta en que el inventario de los productos se resta en cada recepción de evento provocando un error en la información de inventario. Para evitarlo hay que implementar deduplicación de mensajes.

Para deduplicar los mensajes creo un repositorio que almacena los eventos ya procesados correctamente y permite comprobar si un mensaje recibido ya se ha procesado, esta implementación almacena los mensajes en memoria pero a partir de la interfaz cualquier otra implementación sería posible como una base de datos relacional que persista los eventos en la misma transacción que los cambios que provoca el mensaje.

{{< code file="EventRepository.java" language="java" options="" >}}
{{< code file="MemoryEventRepository.java" language="java" options="" >}}

El servicio de aplicación que recibe los mensajes de orden creada obtiene el identificativo del mensaje recibido, comprueba si ya se ha procesado, si ya se ha procesado no se realiza ninguna acción, si no se ha procesado con anterioridad se procesa y se guarda el identificativo del mensaje para no procesarlo de nuevo.

{{< code file="OrderCreatedCommandHandler.java" language="java" options="" >}}

Implementada la deduplicación para simular en el ejemplo en envío por duplicado el mensaje en el bus de eventos de dominio se realiza la operación de envío de eventos dos veces de modo que cada mensaje se envía por duplicado. En la salida del programa con la deduplicación implementada se observa que el contexto de inventario deduplica el segundo mensaje y emite una traza en la consola.

{{< code file="SpringEventBus.java" language="java" options="" >}}

Sin deduplicación de mensajes si un evento se recibe por duplicado se procesa dos veces, en este caso el inventario se reduce dos veces.

{{< code file="System.out-1" language="plaintext" options="" >}}

Implementando deduplicación de mensajes los mensajes duplicados se detectan y se ignoran, el inventario solo se reduce una vez.

{{< code file="System.out-2" language="plaintext" options="" >}}

De _Domain Driven Design_ hay varios libros, el libro de referencia sobre la teoría de DDD son [Domain-Driven Design: Tackling Complexity in the Heart of Software](https://amzn.to/33JmDkv), [Domain-Driven Design Distilled](https://amzn.to/34HkDbA), [Patterns, Principles, and Practices of Domain-Driven Design](https://amzn.to/3ojRzQy) otros más prácticos son [Implementing Domain-Driven Design](https://amzn.to/34yeDSk) y [Domain-Driven Design in PHP: A Highly Practical Guide](https://amzn.to/2SJe2HW).

{{< amazon
    linkids="5df04454342df14dfcc78687544c9d67,fc00596717d15f5b160a896fa5ce565a,1103b1d87d34d4da91354c2b3d680aba,00c494ddc45b9304145ac8e2733eb072,82d6a16b683b54c2ab34c1e51f63acfb"
    asins="0321125215,0134434420,1118714709,0321834577,1787284948" >}}

{{% sourcecode git="blog-ejemplos/tree/master/EventBus" command="./gradlew run" %}}

{{% /post %}}
