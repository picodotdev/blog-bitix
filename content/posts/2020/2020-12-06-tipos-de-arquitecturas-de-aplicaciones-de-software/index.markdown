---
pid: 537
type: "post"
title: "Tipos de arquitecturas de aplicaciones de software"
url: "/2020/12/tipos-de-arquitecturas-de-aplicaciones-de-software/"
date: 2020-12-06T00:00:00+01:00
updated: 2020-12-06T13:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:hexagonal-architecture-1040.webp"
imagePost: "image:hexagonal-architecture.webp"
tags: ["programacion", "planeta-codigo"]
summary: "Con el paso de los años las arquitectura recomendadas han cambiado. En el caso de las aplicaciones web pasando por la arquitectura _spaghetti_, a la por capas y finalmente con una mezcla de hexagonal, _domain-driven-design_ o DDD y dependiendo del caso siendo RESTful, utilizando CQRS, estándo dirigida por eventos o _event-driven_ y en los casos en los que su aplicación es útil usando _event sourcing_."
---

{{% post %}}

{{< tableofcontents >}}

### ¿Qué es arquitectura en una aplicación de software?

La arquitectura en una aplicación de software son los aspectos comunes a las diferentes implementaciones concretas. La arquitectura de software define la estructura y comportamiento de los elementos relevantes, balanceando las necesidades de sus interesados, aplica decisiones de forma racional, definiendo estilos de arquitectura aplicables a múltiples aplicaciones con necesidades similares, está influenciada por su entorno de aplicación, influencia la estructura de los equipos, está presente en cada sistema y tiene un ámbito particular.

* [What is a software architecture?](https://www.ibm.com/developerworks/rational/library/feb06/eeles/index.html)

Según alguna de la organización de estándar IEEE la definición de arquitectura de software es:

> Architecture is the fundamental **organization** of a **system** embodied in its **components**, their **relationships** to each other, and to the **environment**, and the principles guiding its design and evolution.

El estándar define los siguientes términos de la definición:

> A **system** is a collection of components organized to accomplish a specific function or set of functions. The term system encompasses individual applications, systems in the traditional sense, subsystems, systems of systems, product lines, product families, whole enterprises, and other aggregations of interest. A system exists to fulfill one or more **missions** in its environment.

> The **environment**, or context, determines the setting and circumstances of developmental, operational, political, and other influences upon that system.

> A **mission** is a use or operation for which a system is intended by one or more **stakeholders** to meet some set of objectives.

> A **stakeholder** is an individual, team, or organization (or classes thereof) with interests in, or concerns relative to, a system.

### Tipos de arquitecturas

#### Arquitectura Spaghetti

En los inicios de las aplicaciones web con la aparición de las páginas programadas en en lado del servidor con _servlets_ y JSP en Java, ASP de [Microsoft][microsoft] y [PHP][php] la arquitectura de las mismas se caracterizaba por que no había arquitectura. En el mismo _servlet_ o JSP, ASP o PHP el mismo código tenía diferentes responsabilidades sin ninguna separación entre ellas.

En el mismo archivo se codificaba desde el acceso a la base de datos como a la generación del resultado, la lógica para obtener los parámetros de una petición y la lógica de negocio.

Esta no arquitectura de las aplicaciones _spaghetti_ se denomina así porque  mezcla todo tipo de funcionalidades que genera dificultades en la programación, legibilidad y mantenimiento cuando las aplicaciones empiezan a tener un tamaño mediano.

{{< code file="spaghetti.jsp" language="html" options="" >}}

#### Arquitectura por capas

Para suplir las carencias de la arquitectura _spaghetti_ surge la arquitectura por capas. En la arquitectura por capas cada capa tiene una responsabilidad definida, una capa se encarga de la visualización de los datos y la interacción con el usuario, otra capa se encarga de la lógica de negocio y otra capa del acceso a la base de datos. Esta distribución de responsabilidades pone cierto orden a la organización del código y estructura de las aplicaciones. Las capas superiores dependen de las capas inferiores, ya sea en un modelo estricto donde la superior depende únicamente de la inmediatamente inferior o en un modelo más permisivo donde una capa superior puede depender de cualquiera de las inferiores.

La aparición de los _frameworks_ de desarrollo facilitan la creación de los componentes que constituyen la aplicación utilizando el patrón modelo-vista-controlador o MVC basados en acciones como [Struts][struts], de componentes como JSF y [Apache Tapestry][tapestry] u otros similares en otros lenguajes.

{{< image
    gallery="true"
    image1="image:layer-architecture.svg" optionsthumb1="300x200" title1="Arquitectura por capas"
    caption="Arquitectura por capas" >}}

#### Arquitectura hexagonal

En la arquitectura hexagonal o también conocida como puertos-adaptadores aísla las entradas y salidas de la aplicación de la lógica interna de la aplicación. Este aislamiento de las partes exteriores hace que la aplicación no requiera prácticamente ningún cambio que esté influenciado por cambios externos ya sea una nueva base de datos para persistir los datos o un nuevo tipo de cliente como un dispositivo móvil.

Cada entrada ya sea desde una petición REST o desde la linea de comandos o salida ya sea a una base de datos o un sistema de mensajería es un tipo diferente de puerto para su interacción. Una gran ventaja de la arquitectura hexagonal es que los puertos y adaptadores son fácilmente desarrollables para un entorno de pruebas hasta tener los definitivos, la aplicación se puede desarrollar y probar antes de que los clientes y los sistemas de persistencia existan.

Una de las tecnologías que habilitan la arquitectura hexagonal es la inversión de dependencias o IoC mediante la cual se consigue que al contrario que en la arquitectura por capas donde una capa superior depende de la interior la capa inferior dependa de la superior. Si en una aplicación se está utilizando inversión de dependencias es probable que se esté utilizando una arquitectura hexagonal más que una por capas.

Si en la arquitectura por capas la capa de lógica de negocio depende de la capa de acceso a base de datos, en la arquitectura hexagonal es la capa de acceso a base de datos la que depende de la capa de lógica de negocio. Este cambio de dependencias es lo que le da nombre a la inversión de dependencias. La inversión de dependencias se consigue creando en la capa de lógica de negocio una interfaz que es la que implementa la capa de acceso a datos.

En la _Figura 1_ el paquete A depende del paquete B para invertir la dependencia se crea una interfaz de la que depende el paquete A unícamente depende, en la _Figura 2_ es el paquete B el que depende de esa interfaz al implementarla.

{{< image
    gallery="true"
    image1="image:hexagonal-architecture.webp" optionsthumb1="300x200" title1="Arquitectura hexagonal"
    image2="image:dependency-inversion.webp" optionsthumb2="300x200" title2="Inversión de dependencias"
    caption="Arquitectura hexagonal e inversión de dependencias" source="herbertograca.com" >}}

### Metodologías relacionadas

#### _Domain Driven Design_

La metodología _domain-driven-design_ o DDD promueve que la aplicación ha de estar desarrollada basándose en los aspectos del negocio y del dominio de la aplicación, esto afecta tanto al lenguaje compartido denominado lenguaje ubicuo tanto en la aplicación como por todos _stakeholders_ de cada contexto de la aplicación, también afecta a como se modulariza en subdominios la aplicación con las diferentes funcionalidades. El diseño estratégico define los aspectos de análisis de la aplicación el diseño estratégico los de implementación con los agregados, entidades, _value objects_, repositorios o eventos de dominio.

La arquitectura hexagonal se complementa perfectamente con la metodología y principios de DDD ya que ambos casos tienen interés en aislar la lógica de negocio de las partes exteriores de la aplicación de modo que un cambio en el exterior no suponga cambios importantes en la lógica de negocio.

* [Introducción a DDD y arquitectura hexagonal con un ejemplo de aplicación en Java][blogbitix-553]

#### APIs, REST o GraphQL

Con la aparición de múltiples dispositivos ya sean navegadores, teléfonos inteligentes, aplicaciones nativas de teléfonos inteligentes, tabletas o incluso otras aplicaciones, para dar soporte a todos estos clientes las aplicaciones se desarrollan desde el primer momento con el objetivo de ofrecer una API que todos los dispositivos comparten y posteriormente cada dispositivo adapta su interfaz gráfica a sus necesidades.

Muchas aplicaciones utilizan una API denominada REST basada en los principios de la web y la semántica del protocolo HTTP y como formato de intercambio de datos se suele utilizar JSON. Las aplicaciones basadas en REST que siguen los principios de HATEOAS permiten a los clientes obtener en los los datos devueltos las ubicaciones de los recursos relacionados.

Otra forma de implementación de un API es mediante [GraphQL][graphql] que a diferencia de REST no se basa en los principios del protocolo HTTP y la semántica de la web aunque lo utiliza. A diferencia de REST que siempre se devuelven los mismos datos GrapqhQL permite realizar consultas indicando únicamente los datos deseados como respuesta.

#### CQRS

La metodología CQRS separa las operaciones de consulta que solicitan datos de las operaciones que modifican datos o comandos incluso utilizando diferentes bases de datos. El modelo necesario para la consulta puede ser distinto del necesario para la modificación y variar en diferentes casos de uso, por eso la necesidad o ventaja de separar el modelo de consulta del modelo de modificación. El modelo de consulta puede estar desnormalizado para ser más sencillo o eficiente a las necesidades de consulta que utilizando un único modelo para ambas operaciones.

El modelo de consulta se actualiza de forma asíncrona con el modelo de modificación utilizando consistencia eventual. Para manejar esta consistencia eventual en la interfaz del usuario hay varias opciones, una de ellas mostrar los datos en la interfaz como si se hubieran modificado inmediatamente aún estándo pendientes de modificarse. Otra posibilidad es mostrar la fecha y hora de la última actualización de los datos o simplemente indicar al usuario que su petición ha sido aceptada y su procesamiento requiere algo de tiempo en procesarse.

#### _Event-Driven_

En las aplicaciones dirigidas por eventos o _event-driven_ las aplicaciones reaccionan a los eventos que se producen en el sistema. El procesamiento de un evento devuelve una respuesta o genera nuevos eventos en el sistema.

* [Event-driven architecture](https://en.wikipedia.org/wiki/Event-driven_architecture)

#### _Event Sourcing_

En vez de realizar modificaciones sobre los datos existentes simplemente se añaden nuevos eventos en el sistema, el estado actual del sistema se obtiene al procesar todos los eventos.

* [Event Sourcing](https://martinfowler.com/eaaDev/EventSourcing.html)

{{< reference >}}
* [DDD, Hexagonal, Onion, Clean, CQRS, ... How I put it all together](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/)
{{< /reference >}}

{{% /post %}}
