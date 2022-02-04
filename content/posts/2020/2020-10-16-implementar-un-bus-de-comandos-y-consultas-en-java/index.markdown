---
pid: 523
type: "post"
title: "Implementar un bus de comandos y consultas en Java"
url: "/2020/10/implementar-un-bus-de-comandos-y-consultas-en-java/"
date: 2020-10-16T17:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Un bus de comandos y consultas permite separar en una aplicación las operaciones de modificación y operaciones de obtención de datos. Esto permite si es requerido dos bases de datos diferentes utilizando CQRS, una base de datos para operaciones de modificación y una base de datos para operaciones de consulta. Aún teniendo solo una base de datos para ambas operaciones un bus de comandos y eventos permite independizar a la aplicación de las interfaces con las que se use ya sea REST, GraphQL, línea de comandos o mensajería como RabbitQM y crear manejadores de operaciones siguiendo los principios SOLID de diseño."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las aplicaciones entre sus tareas están la de realizar operaciones de modificación y operaciones de lectura de la base de datos. Un comando representa la solicitud de una operación de modificación, tiene la característica de que no devuelven datos pero modifican datos. Las consultas representan la solicitud de información de la base de datos, a diferencia de los comandos devuelven datos pero no realizan cambios en la base de datos de modo que son idempotentes y se pueden repetir cuantas veces se quiera.

A medida que una aplicación crece necesita nuevos comandos y consultas, estando en una o varias clases de servicio estas requieren modificarse al añadir nuevos comandos o consultas, al mismo tiempo las clases de servicio tendrán el conjunto completo de todas las dependencias que necesiten todas las operaciones cuando muchas de las operaciones solo necesitan un pequeño conjunto de dependencias. La organización del código con servicios suele originar clases con múltiples responsabilidades convirtiéndose en un potencial problema de mantenimiento.

Separar los comandos y consultas permite aplicar [CQRS][wikipedia-cqrs], en el que las operaciones de consulta se lanzan contra una base de datos especializada en consultas y los comandos se lanzan contra otra base de datos. Tener dos base de datos permite escalar a cada base de datos de forma independiente según sus necesidades pero añade una gran complejidad al sistema ya que la base de datos que recibe los comandos ha de ser replicada en la base de datos de consultas. Una forma de replicar los datos en las bases de datos es mediante [eventos de dominio con un bus de eventos][blogbitix-522] y consistencia eventual e [implementando deduplicación de eventos de dominio][blogbitix-524].

Un bus de comandos y consultas es una infraestructura que permite añadir nuevos comandos y consultas aplicando dos de los [principios SOLID][wikipedia-solid]. La _S_ de responsabilidad única haciendo que cada comando y consulta tenga una única responsabilidad y la _O_ de abierto a extensión y cerrado a modificación.

{{< tableofcontents >}}

### Interfaces del bus de comandos y consultas

Un bus de comandos y un bus de eventos son simplemente la definición de esta interfaz que tiene un único método a implementar. La interfaz del bus de comandos no devuelve datos y la interfaz del bus de consultas si devuelve datos.

{{< code file="CommandBus.java" language="java" options="" >}}
{{< code file="QueryBus.java" language="java" options="" >}}

Ambas interfaces reciben un argumento que contiene los datos necesarios para ejecutar el comando y consulta. Todos los comandos y argumentos heredan de estas clases. Estas clases hacen de objeto de transferencia de datos o DTO entre la capa de interfaz de la infraestructura y la capa de aplicación de dominio.

{{< code file="Command.java" language="java" options="" >}}
{{< code file="Query.java" language="java" options="" >}}

Los comandos y consultas permiten independizar a la aplicación de la interfaz que se use para acceder a la aplicación. La aplicación puede ser accedida a través de una interfaz REST, una interfaz [GraphQL][graphql], con [RabbitMQ][rabbitmq], línea de comandos. Esta independencia de la interfaz con la que se accede a la aplicación permite soportar varias interfaces de acceso o cambiar a otra en el futuro sin requerir grandes cambios o ninguno en la capa de aplicación ni de dominio.

* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]
* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]

### Implementación de bus de comandos y consultas

La implementación de la interfaz del bus de comandos y consultas reciben clases concretas _Command_ y _Query_, para aplicar los principios SOLID se necesita un manejador por cada clase _Command_ y _Query_ admitido por los buses. Esta clase manejador es la que contiene la lógica de dominio para proporcionar la funcionalidad del comando y consulta, contiene las dependencias de los servicios de dominio o repositorios de las entidades y hace uso de los métodos de las dependencias que necesita.

{{< code file="CommandHandler.java" language="java" options="" >}}
{{< code file="QueryHandler.java" language="java" options="" >}}

Las clases de DTO para los comandos y consultas que contienen los datos y sirve para el envío de la solicitud de la operación al bus.

{{< code file="CreateOrderCommand.java" language="java" options="" >}}
{{< code file="GetOrderQuery.java" language="java" options="" >}}

Las clases manejadores de consultas y comandos tienen la ventaja de seguir los principios SOLID, pero al mismo tiempo, si se puede considerar un inconveniente, es que en una aplicación grande el número de comandos y consultas es grande lo que requiere un gran número de manejadores, cada operación requiere dos clases, la del comando o consulta y la del manejador en vez de simplemente una llamada a un método con sus argumentos.

{{< code file="CreateOrderCommandHandler.java" language="java" options="" >}}
{{< code file="GetOrderQueryHandler.java" language="java" options="" >}}

Con la interfaz del bus de comandos y consultas, las clases concretas de comandos y consultas y los manejadores de cada comando y consulta, el bus de comandos y consultas consiste en tener una relación entre clase concreta de comando o consulta y manejador de esa clase de comando o consulta.

Utilizando la inyección de dependencias de [Spring][spring] se permite recibir en el constructor una lista de clases que heredan de una clase o implementan una interfaz, Spring busca estas clases que además están anotadas con la anotación [@Component](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html). El constructor guarda en un mapa la relación de manejadores con su clase que maneja, buscando por reflexión qué clase de comando o consulta maneja. El método que implementa la interfaz del bus simplemente busca en el mapa el manejador de la clase recibida y le delega su tratamiento.

{{< code file="SpringCommandBus.java" language="java" options="" >}}
{{< code file="SpringQueryBus.java" language="java" options="" >}}

El siguiente código envía un comando y una consulta al bus de consultas y eventos. El comando crea una orden de compra y el segundo obtiene la orden de compra creada.

{{< code file="Main.java" language="java" options="" >}}

En la salida del programa se observa como se procesa el comando de creación de la orden, la creación de la orden provoca el lanzamiento de un evento de dominio _OrderCreated_, el manejador de este evento de dominio en el dominio de inventario realiza la actualización del _stock_ de los productos de la orden, en caso de no haber suficiente _stock_ se emite un evento de dominio _OrderOversold_, el manejador de evento de dominio _OrderOversoldCommandHandler_ podría marcar la orden como sobrevendida o realizar algún proceso con ella. Este lanzamiento de eventos de dominio muestra como funciona la consistencia eventual con el inventario de los productos.

{{< code file="System.out" language="plain" options="" >}}

Estas son las clases que manejan los eventos de dominio que son de interés para el _bounded context_ de inventario.

{{< code file="InventorySpringEventBusListener.java" language="java" options="" >}}
{{< code file="OrderCreatedCommand.java" language="java" options="" >}}
{{< code file="OrderCreatedCommandHandler.java" language="java" options="" >}}

De _Domain Driven Design_ hay varios libros, el libro de referencia sobre la teoría de DDD son [Domain-Driven Design: Tackling Complexity in the Heart of Software](https://amzn.to/33JmDkv), [Domain-Driven Design Distilled](https://amzn.to/34HkDbA), otros más prácticos son [Implementing Domain-Driven Design](https://amzn.to/34yeDSk) y [Domain-Driven Design in PHP: A Highly Practical Guide](https://amzn.to/2SJe2HW).

{{< amazon
    linkids="5df04454342df14dfcc78687544c9d67,fc00596717d15f5b160a896fa5ce565a,1103b1d87d34d4da91354c2b3d680aba,00c494ddc45b9304145ac8e2733eb072,82d6a16b683b54c2ab34c1e51f63acfb"
    asins="0321125215,0134434420,1118714709,0321834577,1787284948" >}}

{{% sourcecode git="blog-ejemplos/tree/master/EventBus" command="./gradlew run" %}}

{{% /post %}}
