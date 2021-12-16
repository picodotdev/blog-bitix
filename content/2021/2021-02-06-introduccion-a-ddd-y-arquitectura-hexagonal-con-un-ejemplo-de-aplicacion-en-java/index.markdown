---
pid: 553
type: "post"
title: "Introducción a DDD y arquitectura hexagonal con un ejemplo de aplicación en Java"
url: "/2021/02/introduccion-a-ddd-y-arquitectura-hexagonal-con-un-ejemplo-de-aplicacion-en-java/"
date: 2021-02-07T01:00:00+01:00
updated: 2021-02-07T14:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:hexagonal-architecture.png"
tags: ["planeta-codigo", "programacion"]
summary: "La arquitectura de una aplicación define la estructura, organización y relación entre los componentes de la misma. En aplicaciones complejas utilizar DDD y arquitectura hexagonal son una opción recomendada. Hay varios libros técnicos dedicados a cada uno de ellos. En este artículo hago una introducción a DDD y arquitectura hexagonal y proporciono un ejemplo con el código fuente con el que implementar, analizar y ejecutar los conceptos teóricos en los que se basan."
---

{{% post %}}

Al desarrollar una aplicación hay que elegir una arquitectura de software. A lo largo del tiempo para las aplicaciones se han seguido diferentes tipos de arquitectura. Arquitectura _spaghetti_ que es más bien la ausencia de arquitectura donde cualquier cosa se hace en cualquier lado, no se separan conceptos lo que da a lugar a problemas de mantenimiento y las aplicaciones son propensas a errores. La arquitectura por capas define varias capas cada una con una responsabilidad, la capa de presentación, la capa de lógica de negocio y la capa de persistencia, las capas superiores solo hacen uso de las capas inferiores.

* [Tipos de arquitecturas de aplicaciones de software][blogbitix-537]

Posteriormente, apareció el concepto de inyección de dependencias e inversión de control que se usa actualmente en la mayoría de los casos con independencia del lenguaje de programación, transformando la arquitectura por capas en la arquitectura hexagonal. Por otro lado, en aplicaciones complejas con mucha lógica de negocio se suele aplicar _Domain Driven Design_ o DDD dividiendo la aplicación en _bounded context_ y utilizando otros conceptos. La arquitectura hexagonal se complementa muy bien con DDD ya que sirve para independizar el dominio de los detalles externos de infraestructura tanto los que son usados para acceder al dominio como los usados por el dominio para acceder al exterior.

{{< tableofcontents >}}

### _Domain-Driven Design_

_Domain-driven design o DDD_ es un concepto que propone que la estructura y lenguaje en el código del software (nombres de clases, métodos y variables) debería reflejar el dominio de negocio. Por ejemplo, en un software de eventos debería haber clases para el concepto de evento, recinto, tipos de entrada métodos como crear evento, cambiar fecha de evento, cancelar o posponer entre otros. DDD intenta evitar la existencia de modelos anémicos con poca lógica de negocio o conceptos exclusivamente técnicos. DDD define dos tipos de patrones, los estratégicos que hacen referencia a aspectos de negocio y los patrones tácticos más relacionados con detalles de implementación.

Patrones estratégicos:

* Lenguaje ubicuo o _ubiquitious language_: son las definiciones de los conceptos aceptadas por todas las personas involucradas en un _boundd context_, personas tanto técnicas como de negocio. Son los expertos de negocio las que proporcionan el lenguaje ubicuo.
* Subdominio: en las aplicaciones complejas estas se dividen en varias áreas funcionales. En una aplicación de comercio electrónico diferentes áreas son catálogo, inventario, compras, pagos, envíos o identidad.
* _Bounded Context_: es el área de la aplicaicón en el que aplican el conjunto de definiciones del lenguaje ubicuo.
* _Context Map_: define las relaciones entre los diferentes _bounded context_.

Patrones tácticos:

* Entidad o _entity_: es la representación en el software de un objeto que se puede identificar de forma inequívoca del resto de instancias. Normalmente es una instancia de una clase con un identificativo asociado.
* _Value Object_: es un objeto que no tiene identidad propia, como una fecha o importe.
* Agregado o _aggregate_: es una entidad que mantiene las invariantes de un conjunto de entidades y _value objects_ al hacerse modificaciones. El resto de agregados y entidades únicamente tiene una referencia al agregado pero no a las entidades agrupadas en él.
* Servicio o _service_: contiene lógica de negocio sin estado con una función específica de dominio. Se utiliza un servicio cuando la lógica de negocio parece estar fuera de lugar en una entidad o _value object_.
* Factorías: objetos cuya responsabilidad es crear objetos.
* Repositorio o _repository_: abstrae a las entidades y agregados de los detalles de persistencia y búsqueda en las bases de datos.
* Eventos de dominio o _domain events_: son notificaciones de que en el sistema ha sucedido algo, como que se ha creado un nuevo producto o se ha realizado una compra y que alguien está interesado en ser notificado.

Hay varios libros de referencia sobre DDD, dos de ellos son [Domain-Driven Design: Tackling Complexity in the Heart of Software](https://amzn.to/3cOp5en) y [Domain-Driven Design Distilled](https://amzn.to/2YUbR7y) que explican más en detalle los patrones estratégicos. El libro [Implementing Domain-Driven Design](https://amzn.to/3oPD8Tm) explica más en detalle los patrones tácticos.

{{< amazon
    linkids="c544ebe228faa80db01bf2fc99ae9078,680dc168e873e6558051b99d115cca0b,0c0434c4b1c647c9aad0334f51990282"
    asins="0321125215,0134434420,0321834577" >}}

### Arquitectura hexagonal

La arquitectura hexagonal aísla e independiza al modelo de dominio de los elementos externos con el objetivo de que aunque los elementos externos cambien estos no afecten al modelo y de que se puedan hacer cambios en el dominio los elementos externos requieran los menores cambios necesarios.

La arquitectura hexagonal ubica los diferentes elementos de código en uno de los siguientes módulos:

* Infraestructura: son los elementos externos con los que se comunica la aplicación, tanto de entrada como de salida. Puntos de entrada son una API con REST o [GraphQL][graphql], mensajería con [RabbitMQ][rabbitmq] o mediante línea de comandos. Puntos de salida son una base de datos relacional con [PostgreSQL][postgresql], no relacional con [MongoDB][mongodb], o también envío de mensajes con RabbitMQ. A los puntos de entrada se les denomina puertos y a los puntos de salida adaptadores.
* Puertos: una aplicación puede ofrecer diferentes formas de comunicación al mismo tiempo, ya sea con una API REST o GraphQL, mensajes o mediante línea de comandos.
* Adaptadores: igualmente una aplicación puede utilizar diferentes bases de datos o sistemas de comunicación con el exterior.
* Aplicación: son los servicios que definen la API pública del dominio e independiza al dominio de cualesquiera elementos de infraestructura actuales o en el futuro.
* Dominio: contiene la lógica de negocio de la aplicación. Esta puede ser implementada usando los principios de DDD.

En las relaciones de los elementos algo ubicado en infraestructura puede hacer uso de elementos de aplicación, los elementos de aplicación de elementos de dominio pero desde dominio no se permite hacer uso de elementos de infraestructura ni de aplicación, esto desacopla el dominio de los cambios que se produzcan en infraestructura, por ejemplo por añadir una base de datos diferente o añadir un nuevo puerto. La forma que tienen los elementos de dominio de hacer uso de elementos de infraestructura es haciendo uso de la inversión de dependencias, es decir, en vez de que dominio tenga dependencias de infraestructura consiguiendo que infraestructura dependa de dominio. Analizando los _import_ de las clases Java es fácil reconocer cuando se está violando las reglas de dependencias, en caso de que dominio tenga un _import_ de una clase de infraestructura o aplicación hay una dependencia que no debería permitirse. Hay herramientas para validar estas restricciones, [hacer análisis estático de código con PMD][blogbitix-297].

Una gran ventaja de la arquitectura hexagonal es que los puertos y adaptadores no hace falta implementarlos al mismo tiempo que el dominio, el dominio solo ha de definir la interfaz que necesita y con posterioridad es posible realizar la implementación del adaptador en concreto que realice la persistencia. Esto permite retrasar la toma decisiones hasta tener más conocimiento de la solución más adecuada.

{{< image
    gallery="true"
    image1="image:hexagonal-architecture.png" optionsthumb1="650x450" title1="Diagrama de la arquitectura hexagonal"
    caption="Diagrama de la arquitectura hexagonal" source="Libro Implementing Domain-Driven Design">}}

### Ejemplo de aplicación Java con DDD y arquitectura hexagonal

En el siguiente ejemplo de implementación en Java de una aplicación con DDD y arquitectura hexagonal me baso en parte en los siguientes artículos relacionados o estos sirven como ejemplos de implementación de sus temas específicos.

* [Implementar un bus de eventos de dominio en Java][blogbitix-520]
* [Implementar un bus de comandos y consultas en Java][blogbitix-523]
* [Ventajas de usar un tipo específico para los identificadores de las entidades en vez de un tipo básico][blogbitix-437]
* [Generar en el dominio los identificativos de las entidades aplicando DDD antes de persistirlas en la base de datos][blogbitix-493]
* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]
* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]
* [Programación orientada a aspectos con AspectJ, Spring AOP y la clase Proxy][blogbitix-461]

El bus de comandos y consultas permiten implementar en la aplicación CQRS, utilizar una base de datos para las modificaciones y otra base de datos para las consultas, y asincronía. El bus de eventos permite implementar consistencia eventual en el caso de que la aplicación forme parte de otro conjunto de microservicios y actualizar la base de datos de consulta en CQRS.

La programación orientada a aspectos permite aplicar funcionalidades transversales de forma transparente a diferentes elementos, por ejemplo para añadir la transaccionalidad en una base de datos relacional.

En la aplicación de ejemplo con [Spring Boot][spring-boot] y Java se usa como puerto una API REST y como adaptador una interfaz que representaría base de datos relacional pero que en este caso para simplificar se implementa en un repositorio en memoria. Se utiliza [Gradle][gradle] como herramienta de construcción.

#### Estructura de directorios y paquetes

Esta es la estructura de directorios ubicando los artefactos de REST e implementación de repositorios en el paquete de infraestructura y otras dependencias e implementaciones del _framework_ Spring. Los buses de comandos y consultas con las implementaciones de los casos de uso en el paquete de aplicación, estos llaman a los artefactos del dominio como repositorios y entidades. En infraestrucutra están los puertos y adaptadores dependientes de librerías y _frameworks_ aislados del dominio como los elementos que componen la interfaz REST, varios elementos de Spring y las implementaciones de los repositorios que estarían acoplados a una base de datos relacional o no sql.

{{< code file="tree.sh" language="plain" options="" >}}

#### Entidades y _value objects_

En el dominio de catálogo hay eventos y habría varios _value objects_ para representar ciertas propiedades del dominio.

Para la persistencia se puede hacer uso de [Hibernate][hibernate], una librerías ORM para Java, como alternativa o al mismo tiempo también es posible usar [jOOQ][jooq]. Las anotaciones de Hibernate requieren añadir _imports_ que realmente son de infraestructura en las entidades de dominio, esta es una violación de la regla general de dependencias para evitarlo se puede realizar el mapeado con archivos _hbm_ en formato XML, aunque por mayor sencillez se suele implementar con anotaciones. Como en este caso la persistencia solo se hace en memoria no están incluidas las anotaciones en la entidad.

Una entidad.

{{< code file="Event.java" language="java" options="" >}}

Y varios _value objects_.

{{< code file="EventId.java" language="java" options="" >}}
{{< code file="EventDate.java" language="java" options="" >}}
{{< code file="EventSchedule.java" language="java" options="" >}}

#### Eventos de dominio

Cuando suceden cosas relevantes en el dominio se lanzan eventos de dominio para notificar a los interesados y que actúen en consecuencia. En este caso que un evento se ha creado, ha cambiado de fecha para notificar a los asistentes y realizar otra serie de acciones o ha sido cancelado que también desencadenará otras acciones.

{{< code file="EventCreatedDomainEvent.java" language="java" options="" >}}
{{< code file="EventDateRescheduledDomainEvent.java" language="java" options="" >}}
{{< code file="EventCancelledDomainEvent.java" language="java" options="" >}}

El evento se registra en la entidad de dominio, es lanzado al bus de eventos por la clase de uso y sería manejado por sus interesados, en este caso la implementación de bus simplemente emite una traza en la terminal, una de sus implementaciones plausibles es hacer que los emita a un sistema de mensajes como RabbitMQ o implementarlo con el _outbox pattern_.

{{< code file="SpringEventBus.java" language="java" options="" >}}

#### Aplicación y casos de uso

La capa de aplicación es la interfaz que ofrece el dominio a sus consumidores, en esta implementación la interfaz se proporciona mediante un _command bus_ y el _query bus_ que hacen de intermediario entre la capa de aplicación y los casos de uso. Los casos de uso se abstraen de la utilización de los _command bus_ y _query bus_ y utilizan los servicios de dominio, entidades y _value objects_.

En esta implementación los comandos exponen los objetos de dominio, esto hace que el código que use esta capa de aplicación dependa de las entidades de dominio pero permite obtener la comprobación de tipos del compilador y hacer uso de las comprobaciones de los _value objects_. Otra forma de implementarlo hubiese sido que la clase comando únicamente tuviese propiedades de tipo primitivo lo que evita evitar el acoplamiento de código con las entidades de de dominio fuera del dominio pero por el contrario requiere añadir complejidad al tener que crear objetos DTO para devolver estos en vez de las entidades de dominio. Ambas implementaciones son válidas de forma general, cual aplicar depende según el caso o preferencia de que ventajas conservar.

Dos de las operaciones implementadas son obtener un evento y crear un evento, operaciones que son consumidas desde un puerto que a su vez las ofrece mediante una interfaz REST.

Los casos de uso de operaciones que realizan modificaciones en el modelo en el caso de utilizare una base de datos relacional son la ubicación para demarcar el ámbito de la transacción, con un aspecto que les aplique la anotación [Transactional](spring-framework:org/springframework/transaction/annotation/Transactional.html).

{{< code file="GetEventUseCase.java" language="java" options="" >}}
{{< code file="CreateEventUseCase.java" language="java" options="" >}}

#### Puertos y adaptadores

La interfaz REST es un puerto, punto de entrada o interfaz de la aplicación. Cuando se realiza una petición REST se construye y se envía al bus de consultas o comandos el objeto que representa la consulta o comando para el dominio. 

{{< code file="EventController.java" language="java" options="" >}}
{{< code file="GetEventQuery.java" language="java" options="" >}}
{{< code file="CreateEventCommand.java" language="java" options="" >}}

El manejador del comando o consulta recibe los datos y lo delega al caso de uso.

{{< code file="GetEventQueryHandler.java" language="java" options="" >}}
{{< code file="CreateEventCommandHandler.java" language="java" options="" >}}

El repositorio es un adaptador, punto de salida o interfaz con un sistema externo, como es una base de datos. Para que el dominio no dependa de nada de infraestructura, el dominio crea una interfaz que se implementa en infraestructura, invirtiendo de esta forma la dependencia, con el objetivo deseado de que sea infraestructura quien dependa de dominio y no al revés.

{{< code file="EventRepository.java" language="java" options="" >}}
{{< code file="InMemoryEventRepository.java" language="java" options="" >}}

#### Ejecución del ejemplo

Los _endpoints_ para la interfaz REST, para la operación de modificación y para la operación de consulta son los siguientes.

{{< code file="curl.sh" language="bash" options="" >}}

Este es el archivo de Gradle para la construcción y ejecución del ejemplo.

{{< code file="build.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/DomainDrivenDesignHexagonalArchitecture" command="./gradlew run" %}}

{{< reference >}}
* [Domain-driven design](https://en.wikipedia.org/wiki/Domain-driven_design)
* [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
{{< /reference >}}

{{% /post %}}
