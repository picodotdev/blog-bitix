---
pid: 520
type: "post"
title: "Implementar un bus de eventos de dominio en Java"
url: "/2020/10/implementar-un-bus-de-eventos-de-dominio-en-java/"
date: 2020-10-09T16:00:00+02:00
updated: 2020-10-10T18:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:outbox-pattern.png"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Un bus de eventos es el mecanismo por el que los eventos de dominio de DDD son publicados, son tratados y enviados a sus receptores de forma directa, mediante un _middleware_ u de otra forma. El concepto bus de eventos para eventos de dominio se materializa de forma muy sencilla en código, simplemente una interfaz con un método. Cambiando la implementación de la interfaz un bus de eventos envía los eventos a un sistema de mensajería como RabbitMQ, persiste los eventos en base de datos como parte del _outbox pattern_ o simplemente los imprime en la consola como en el ejemplo del artículo."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

En la teoría de _Domain Driven Design_ o DDD se mencionan los eventos de dominio. Un evento de dominio es una notificación de algo que ha sucedido en el sistema en lo que algunas partes del mismo están interesadas en ser notificadas. Dado que un evento es algo que ha sucedido suelen tener un nombre con un verbo en pasado como _OrderCreated_, otra de sus propiedades es que no indican que acción se ha de realizar como sería el caso de un nombre con _SendBuyerOrderEmail_. Las mismas características de las aplicaciones que se comunican con mensajes se aplican a las aplicaciones que generan eventos de dominio, la comunicación en el sistema se realiza de forma desacoplada, el emisor y el receptor no se conocen y de forma desincronizada ni el emisor ni el receptor necesitan de la disponibilidad del otro si se realiza con un _middleware_.

En la teoría de _Domain Driven Design_ esta forma de comunicación es útil para comunicar diferentes _bounded context_. Un _bounded context_ se encarga de una área funcional de la aplicación con alta cohesión y su propio lenguaje de dominio. El lenguaje de dominio son los conceptos que aplican en un _bounded context_ y cuales son las propiedades relevantes dentro del mismo, el término usuario en un supuesto _bounded context_ de registro y adminisitración de la cuenta es probable no sea el mismo que en el _bounded context_ de órdenes de compra, en el primero el usuario puede tener varias direcciones y en el segundo solo interesa una, por otro lado en el _bounded context_ de inventario ni siquiera exista el término usuario o signifique otra cosa totalmente diferente.

Los _bounded context_ y eventos de dominio se adaptan especialmente bien a las aplicaciones con arquitectura basada en microservicios. Cada microservicio puede ser uno o varios _bounded context_ con un área funcional bien definida y estos comunicarse mediante eventos con eventos de dominio. La forma de emitir estos eventos de dominio es con un bus de eventos. La [implementación un bus de comandos y consultas][blogbitix-523] para [CQRS][wikipedia-cqrs] es muy similar a la implementación de un bus de eventos.

{{< tableofcontents >}}

### Transaccionalidad y eventos de dominio

En las aplicaciones un concepto importante es la transaccionalidad, es un requerimiento que se impone al sistema para su buen funcionamiento. Una transacción consiste en que un conjunto de acciones o cambios funciona todo de forma completa o no funciona nada pero nunca de forma parcial. Si la lógica de una aplicación emite varios eventos según se ejecuta y en la mitad del proceso algo falla las acciones desencadenadas por los eventos emitidos deben deshacerse o de lo contrario posiblemente se produzca un mal funcionamiento o una inconsistencia de datos.

Las aplicaciones suelen trabajar con bases de datos y estas son las que proporcionan la transaccionalidad pero si los eventos se envían con un _middleware_ de mensajería la transaccionalidad ya no afecta a un único sistema sino que afecta a dos, la base de datos relacional y el _middleware_ de mensajería. Alguna solución a la transaccionalidad de dos sistemas diferentes como una [transacción en dos fases](https://es.wikipedia.org/wiki/Commit_de_dos_fases) tiene sus propios problemas en cuanto a escalabilidad y complejidad.

Una solución es utilizar únicamente la base de datos. Los eventos se guardan en una tabla en la misma transacción que el resto de datos, de tal modo que si la transacción finaliza correctamente los eventos generados están almacenados y si la transacción falla no se guarda ninguno. Una vez los eventos están guardados otro proceso se encarga de emitirlos. Nuevamente este proceso que emite los eventos puede fallar y ocasionar que un evento sea lanzado dos veces pero al menos garantiza que si se ha generado un evento se emita al menos una vez en el sistema. Este es el patón _outbox pattern_.

{{< image
    gallery="true"
    image1="image:outbox-pattern.png" optionsthumb1="650x450" title1="Outbox Pattern"
    caption="Outbox Pattern" >}}

### Deduplicación de eventos

Para resolver el problema de eventos duplicados se suele optar por hacer el tratamiento del evento idempotente o deduplicando de eventos. La deduplicación se suele hacer asignando a cada evento un identificador único y luego en la parte receptora comprobar si ese evento ya ha sido procesado.

* [Cómo deduplicar eventos de dominio][blogbitix-524]

### Consistencia eventual

Otro problema es que todos los cambios que origina una petición no se aplican al mismo tiempo, un _bounded context_ o microservicio hace los cambios de su ámbito y emite un evento que origina otros cambios en otras entidades, _bounded context_ o microservicios. Esto hace que el sistema por un tiempo más o menos largo está en un estado inconsistente. Pero en un sistema distribuido si es posible esto es más sencillo que utilizar un transacción en dos fases de dos sistemas diferentes.

### Implementar un bus de eventos en Java

Un bus de eventos no es nada complicado, esta interfaz es lo que define un bus de eventos, básicamente consiste en un método para publicar un mensaje con un argumento que representa los datos del evento. Los otros dos métodos son de utilidad, en este caso se implementan en la interfaz como métodos _default_ permitido con [las novedades de Java 8][blogbitix-17].

{{< code file="EventBus.java" language="java" options="" >}}

En una arquitectura hexagonal que separa el dominio de los detalles de implementación externos la interfaz del bus de eventos junto con las clases de los eventos están en la capa de dominio y la implementación del bus de eventos está en la capa de infraestructura. La implementación del bus de eventos es la que determina si el evento se publica utilizando [eventos con Spring](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#context-functionality-events-generics), [eventos con Guava][blogbitix-422], [utilizar RabbitMQ][blogbitix-210] o los guarda en base de datos para que sean emitidos por otro elemento sea el que los publique en [RabbitMQ][rabbitmq] con el _outbox pattern_, el proceso publicador en RabbitMQ puede ser [una tarea programada con Quartz][blogbitix-497] ejecutada de forma periódica que busca y publica los eventos pendientes de publicar. En este código la implementación simplemente los imprime en la terminal y en otra implementación se utiliza el mecanismo de envío de eventos en la misma aplicación de Spring.

{{< code file="ConsoleEventBus.java" language="java" options="" >}}
{{< code file="SpringEventBus.java" language="java" options="" >}}

### Como generar y lanzar un evento de dominio

En _Domain Driven Design_ un agregado es una entidad que se encarga de mantener la consistencia e invariantes de negocio con las otras entidades con las que está relacionada y que mantiene. Por ejemplo, en una orden de compra compuesta de varios productos las líneas de compra están gestionadas por el agregado de la orden de compra, las lineas de compra no tienen sentido por si mismas fuera de una orden de compra. Si existe una regla de negocio que no permita más de tres lineas de compra o con un importe mayor de 3000€, el agregado de orden de compra o un servicio de dominio se encargaría de que su estado cumpla las reglas de negocio.

El agregado al contener lógica de negocio puede generar eventos de dominio. Una de las soluciones que se suele optar es que el agregado almacene los eventos de dominio que ha generado y posteriormente sean recolectados para ser emitidos en el bus de eventos. Esto facilita las pruebas unitarias y los eventos no se emiten inmediatamente lo que permite implementar el patrón _outbox pattern_.

Esta es la implementación de un agregado que representa una orden de compra con varias líneas de compra. Al crearse una orden se crea el agregado y emite un evento indicado el suceso, este evento es de interés para un _bounded context_ de inventario para mantener el _stock_ de productos actualizado.

{{< code file="Order.java" language="java" options="hl_lines=32" >}}
{{< code file="OrderCreated.java" language="java" options="" >}}

Las clases de evento de dominio heredan de una clase que representa a todos los eventos de dominio.

{{< code file="Event.java" language="java" options="" >}}
{{< code file="EventId.java" language="java" options="" >}}

La siguiente clase es el contenedor que almacena los eventos, los agregados crean una instancia de esta clase. Otra alternativa de implementación es mediante herencia en vez de composición como en este ejemplo. Con el método _publish_ se publican los eventos que contiene en el bus, una vez publicados los eventos se eliminan de la colección.

{{< code file="EventCollection.java" language="java" options="" >}}
{{< code file="AggregateRoot.java" language="java" options="" >}}

Los servicios de dominio contienen la funcionalidad que requiere dependencias o que engloba a varios agregados. Cada agregado suele tener asociado una clase repositorio que se encarga de las operaciones de persistencia, la implementación de los repositorios se realiza en la capa de infraestructura lo que permite cambiar el sistema de persistencia sin afectar a la capa de dominio. Los servicios hacen uso de los repositorios que necesiten. El servicio de dominio de órdenes de compra se encarga de invocar la operación de persistencia del agregado y de la operación de emitir los eventos.

{{< code file="OrderService.java" language="java" options="" >}}
{{< code file="OrderRepository.java" language="java" options="" >}}
{{< code file="MemoryOrderRepository.java" language="java" options="" >}}

Con la implementación del bus de eventos que imprime en la consola se muestra un mensaje cuando se crear una orden de compra.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="CreateOrderCommandHandler.java" language="java" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

### Otras preguntas

Si un evento incluye el identificativo de la entidad este ha de ser generado con anterioridad. Habitualmente se delega en la base de datos el generar el identificativo con un autonumérico en el momento de inserción, hacerlo en el momento de la persistencia impide conocer su identificativo con antelación. La solución es [generar su identificativo antes de crear la entidad][blogbitix-493].

En este punto se llegan a otras preguntas:

* ¿Qué eventos hay que lanzar? Esto requiere análisis, hay que descubrirlo con el experto de dominio.
* ¿Cómo se deduplican los eventos? Asignando a cada evento un identificador único el receptor sabe si ha procesado ese eventos guardando los identificadores de eventos que ha procesado. Si un evento procesado se recibe de nuevo se descarta. Los identificadores de los eventos pueden ser un [UUID](javadoc11:java.base/java/util/UUID.html) y los consumidores pasado un tiempo eliminar o archivar los eventos procesados considerando que pasado ese tiempo de horas, días o semanas ya no va a llegar duplicado.
* ¿Qué información han de incluir los eventos? Por lo menos las propiedades que permitan conocer las entidades de su causa.
* ¿Qué ocurre con los eventos fuera de orden? Los _middleware_ de mensajería como RabbitMQ ofrecen algún tipo de mecanismo para que los mensajes se procesen en orden que son recibidos aún con alguna limitación.
* ¿Qué ocurre si la consistencia eventual no es posible?
* ¿Qué casos son idempotentes? ¿aunque sean idempotentes les afecta el fuera de orden?
* ¿Qué ocurre si en el futuro se modifican los eventos existentes o se añaden nuevos?

Dependiendo de la lógica de negocio los problemas con mensajes pueden ser difíciles de reproducir, analizar y corregir ya que al igual que en los casos de concurrencia intervienen factores como el orden de los hechos.

De _Domain Driven Design_ hay varios libros, el libro de referencia sobre la teoría de DDD son [Domain-Driven Design: Tackling Complexity in the Heart of Software](https://amzn.to/33JmDkv), [Domain-Driven Design Distilled](https://amzn.to/34HkDbA), otros más prácticos son [Implementing Domain-Driven Design](https://amzn.to/34yeDSk) y [Domain-Driven Design in PHP: A Highly Practical Guide](https://amzn.to/2SJe2HW).

{{< amazon
    linkids="5df04454342df14dfcc78687544c9d67,fc00596717d15f5b160a896fa5ce565a,1103b1d87d34d4da91354c2b3d680aba,00c494ddc45b9304145ac8e2733eb072,82d6a16b683b54c2ab34c1e51f63acfb"
    asins="0321125215,0134434420,1118714709,0321834577,1787284948" >}}

{{% sourcecode git="blog-ejemplos/tree/master/EventBus" command="./gradlew run" %}}

{{< reference >}}
* [A better domain events pattern](https://lostechies.com/jimmybogard/2014/05/13/a-better-domain-events-pattern/)
* [(Un) Reliability in messaging: idempotency and de-duplication](https://lostechies.com/jimmybogard/2013/06/03/un-reliability-in-messaging-idempotency-and-de-duplication/)
* [Eventos de dominio: diseño e implementación](https://docs.microsoft.com/es-es/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/domain-events-design-implementation)
* [In DDD, is a Domain Service essentially just a Facade and/or Mediator Pattern?](https://softwareengineering.stackexchange.com/questions/362781/in-ddd-is-a-domain-service-essentially-just-a-facade-and-or-mediator-pattern)
* [Handling Distributed Transactions in the Microservice world](https://medium.com/swlh/handling-transactions-in-the-microservice-world-c77b275813e0)
* [Event-Driven Data Management for Microservices](https://www.nginx.com/blog/event-driven-data-management-microservices/)
* [Handling Domain Events: Missing Part](http://www.kamilgrzybek.com/design/handling-domain-events-missing-part/)
* [How to publish and handle Domain Events](http://www.kamilgrzybek.com/design/how-to-publish-and-handle-domain-events/)
* [The Outbox Pattern](http://www.kamilgrzybek.com/design/the-outbox-pattern/)
* [Domain Driven Design Crash Course](https://vaadin.com/learn/tutorials/ddd)
{{< /reference >}}

{{% /post %}}
