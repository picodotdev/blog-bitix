---
pid: 645
type: "post"
title: "Opciones de arquitectura para emitir trazas en una aplicación Java"
url: "/2022/07/opciones-de-arquitectura-para-emitir-trazas-en-una-aplicacion-java/"
date: 2022-07-28T19:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Incluso para emitir trazas que en principio es algo sencillo y que se da por hecho surgen varias opciones de arquitectura o diseño. Dos de esas decisiones son acoplarse o no a la librería de _logging_ que se use e inyectar de forma estática o por el constructor la instancia de la clase con la que se emiten trazas. Estas decisiones conviene recogerlas en un documento de _Architecture Decision Record_ por si en un futuro hay que revisar las decisiones tomadas con anterioridad o para que una persona en un futuro tenga el contexto y un registro de las decisiones que se han tomado, el contexto, opciones evaluadas, argumentos y decisiones tomadas."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una de las cosas más esenciales de una aplicación es generar trazas para tener observabilidad de que está realizando la aplicación y un registro que permita consultar qué ha pasado en cualquier momento que sea necesario, que lo será. Las trazas no son solo por observabilidad a veces también por cumplimiento de requerimientos de seguridad y auditoría.

Por tanto, usar una librería de _logging_ es una de las dependencias básicas, en Java las librerías de logging más utilizadas son [log4j 2][log4j], [SLF4J][slf4j] y las propias [clases del JDK para logging](javadoc17:java.logging/java/util/logging/package-summary.html) del paquete conocido como JUL.

Además de la librería en concreto que usada hay otras decisiones de arquitectura que tomar al aplicar en una aplicación. Independientemente de la opción que se use de todas estas opciones, argumentos y decisiones lo ideal es registrar en un documento que se suele denominar _Architecture Decision Record_ de modo que en un futuro u otra persona pueda tener el contexto de qué y por qué se tomaron en su momento algunas decisiones.

* [Funcionalidades que necesitan las aplicaciones basadas en microservicios y herramientas que las proporcionan][blogbitix-516]
* [Las clases para logging del paquete JUL incluidas en la API de Java][blogbitix-618]
* [Documentación para registrar las decisiones de arquitectura en software e infraestructura][blogbitix-547]

En un [artículo publicado en el sitio web de Martin Fowller sobre la observabilidad en el dominio](https://martinfowler.com/articles/domain-oriented-observability.html) hablan sobre cómo diseñar las clases para añadir esta funcionalidad al código. En este artículo comento sobre la trazabilidad de una aplicación pero lo mismo es aplicable a las métricas como se comenta en el sitio web de Fowller.

Después de leer el artículo de Fowller y este artículo, ¿qué opción usas? ¿alguna otra opción o variante? ¿qué opinas?

{{< tableofcontents >}}

## Opciones

Estas cuatro opciones tratan dos asuntos diferentes. Las dos primeras tratan de si crear una abstracción o usar la librería de _logging_ directamente sin ninguna abstracción.

La tercera y la cuarta tratan otro aspecto que es como inyectar el _logger_ en las clases que emitan trazas, en realidad no son excluyentes y como comento se puede optar por una u otra forma de inyectar el _logger_ según la clase.

### Acoplarse a la librería que emite los _logs_

En esta opción las clases de la librería de _logging_ se consideran como parte de la plataforma y el código se acopla a ellas.

{{< code file="Service-1.java" language="java" options="" >}}

### Abstracción sobre la librería que emite los _logs_

Para no acoplarse a una librería en concreto de *logging* se crea una abstracción que independice de la librería de modo que si esta cambia no haga falta el código sino simplemente la implementación de la abstracción.

{{< code file="Service-2.java" language="java" options="" >}}

### Inyección estática del _logger_

La forma habitual que ponen de ejemplo las librerías de _logging_ para inyectar la instancia de la clase de *logger* se realizan mediante una factoría e inicialización estática.

{{< code file="Service-3.java" language="java" options="" >}}

### Inyección por constructor del _logger_

En vez de inyectar de forma estática el _logger_, la instancia se inyecta en el constructor como cualquier otro colaborador de la clase.

{{< code file="Service-4.java" language="java" options="" >}}

## Argumentos

Evitar el acoplamiento es algo deseable dado que permite flexibilidad y realizar cambios afectando a una una pequeña parte del código. Con una abstracción es posible cambiar de una implementación a otra y los cambios solo afectan a la abstracción y no al resto del código.

El inconveniente de la abstracción es que requiere crearla y mantenerla y que posiblemente esa abstracción ofrece un subconjunto pequeño de toda la funcionalidad que ofrece la librería de _logging_ que se use. Aún así el subconjunto de la funcionalidad es aquel que realmente se usa.

Incluso aunque el JDK incluye clases de _logging_ y el acoplamiento sea con las clases del JDK por flexibilidad se puede desear crea la abstracción por la posibilidad de cambiar la librería de _logging_ a otra.

La librería SL4J precisamente es una fachada o abstracción sobre otras librerías de _logging_ que permite cambiar de una implementación de librería a _logging_ a otra sin necesidad de cambios en el código de modo que en vez de crear una abstracción propia la abstracción a la que acoplarse puede ser esta.

Otro aspecto a considerar es cómo inyectar la dependencia del logger, esto es independiente de si usa o no una abstracción propia o de otra librería como la de SLF4J.

La inyección estática es simple pero no permite o dificulta hacer pruebas unitarias sobre el logger que es un aspecto de la observabilidad que tal vez se desea probar, por ejemplo por motivos de seguridad se desea que el código emita trazas de inicios de sesión o acciones relevantes que permite tener un log en caso de querer auditar con posterioridad. A veces el único efecto observable de un flujo de código es que se emite una traza.

La inyección como una dependencia del _logger_ como cualquier otro colaborador permite realizar teses unitarios de forma más fácil. La dependencia se añade simplemente en el constructor de la clase. Para las clases que son construidas con el contenedor de dependencias es simple y no requiere más que añadir de forma explícita el colaborador en el constructor, crear una variable y realizar la asignación en la implementación del constructor, estos algo más complejo que la inyección estática pero aporta otras ventajas.

Para las clases que no son construidas con el contenedor de dependencias tener que pasar de forma explícita el _logger_ es una molestia, posiblemente hace el código más complicado además de ser un aspecto de la aplicación no relacionado con la lógica de negocio.

Estas opciones no son excluyentes, en aquellas clases que son creadas por el contenedor dejar al contenedor inyectar la dependencia del _logger_ y el aquellos puntos de la aplicación como clases de dominio no construidas por el contenedor o en clases con métodos estáticos utilizar una inyección estática, sabiendo que en la inyección estática las pruebas unitarias con más complicadas.

## Implementación de inyección por constructor con Spring

Utilizando [Spring][spring] y [Spring Boot][spring-boot] la inyección por constructor es posible conseguirla fácilmente con la posibilidad de [Crear un bean según el contexto donde se inyecte con Spring][blogbitix-666] de modo que para las clases que están gestionadas por el contenedor de dependencias se aprovechan sus funcionalidades en la implementación.

{{< reference >}}
* [Logging Without a Static Logger](https://www.javacodegeeks.com/2019/03/logging-without-static-logger.html)
{{< /reference >}}

{{% /post %}}
