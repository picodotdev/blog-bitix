---
pid: 654
type: "post"
title: "Novedades de Java 19"
url: "/2022/09/novedades-de-java-19/"
date: 2022-09-28T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-19.webp"
imagePost: "image:java-19.webp"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "Las versiones de Java más confiables por su soporte extendido son las LTS, las no LTS dan la oportunidad de probar e ir adaptándose a las novedades que se publicarán de forma definitiva en las LTS. Por ello las empresas seguramente prefieran ir cambiando de versiones de LTS a LTS, los usuarios y desarrolladores a nivel individual o las empresas que alguna novedad supone un cambio importante opten por usar una no LTS. La versión de Java 19 es una no LTS pero incorpora una novedad muy importante aún en vista previa, los _virtual threads_ que permitirán a las aplicaciones pasar de usar miles a millones de _threads_ y usar programación estructurada más sencilla en vez de programación asíncrona o concurrente para resolver problemas de concurrencia."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una nueva versión de Java que llega ya al número 19, no es una versión LTS con lo que carece de soporte extendido pero que junto con Java 18 incorpora varias novedades desde la última versión LTS alguna muy destacada y esperada que va a suponer un cambio de paradigma en la programación concurrente.

Las novedades no son muy numerosas ya que en el periodo de seis meses da tiempo a ir incorporando algunas que ya se publican aunque sea en modo de vista previa y aunque pueden cambiar cuando lleguen a la versión final permite a aquellos aventurados experimentar y adaptarse a ellas en futuras versiones e incluso utilizarlas si así lo desean en su versión de vista previa.

{{< tableofcontents >}}

### Introducción

En esta nueva versión de Java las novedades sin contar las incorporadas en vista previa son muy pocas la única es el _port_ a la arquitectura de procesadores RISC-V que aunque es una prometedora arquitectura de procesadores con la interesante propiedad de no tener costes de licencias por implementarla que probablemente vaya ganando cuota de mercado a día de hoy es muy minoritaria, tanto en el escritorio como en los servidores.

Sin embargo, en las características en vista previa hay una muy destacada y relevante que son los _threads_ virtuales que supone un cambio de paradigma en la programación concurrente que proporciona las ventajas de la programación estructurada sin la complejidad de la programación asíncrona o concurrente.

Como complemento a los _threads_ virtuales hay otra ayuda con la concurrencia estructurada, dado que los _threads_ virtuales van a permitir crear varios órdenes de magnitud de _threads_ de lo que era posible hasta ahora, de pasar a únicamente utilizar miles de _threads_ a poder utilizar millones de _threads_.

Otras novedades en vista previa son actualizaciones a características en vistas previas anteriores como los _Record Patterns_, _Foreign Function & Memory API_ para integración de código nativo, el _Vector API_ para aprovechar las instrucciones vectoriales de los procesadores, _Pattern Matching for switch_ y _Structured Concurrency_ que está relacionado con los *threads virtuales* y lo complementa.

* [Características de Java 19](https://openjdk.java.net/projects/jdk/19/)
* [Notas de publicación de Java 19](https://jdk.java.net/19/release-notes)
* [Documentación de Java 19](https://docs.oracle.com/en/java/javase/19/)
* [Documentación Javadoc de Java 19](https://docs.oracle.com/en/java/javase/19/docs/api/index.html)

Las mejoras incluidas en esta versión son:

* 405: [Record Patterns (Preview)](https://openjdk.org/jeps/405)
* 422: [Linux/RISC-V Port](https://openjdk.org/jeps/422)
* 424: [Foreign Function & Memory API (Preview)](https://openjdk.org/jeps/424)
* 425: [Virtual Threads (Preview)](https://openjdk.org/jeps/425)
* 426: [Vector API (Fourth Incubator)](https://openjdk.org/jeps/426)
* 427: [Pattern Matching for switch (Third Preview)](https://openjdk.org/jeps/427)
* 428: [Structured Concurrency (Incubator)](https://openjdk.org/jeps/428)

Artículos relacionados:

* [JDK 19 Release - Sip of Java](https://inside.java/2022/09/20/sip066/)
* [The Arrival of Java 19!](https://inside.java/2022/09/20/the-arrival-of-java-19/)
* [Episode 26 "Java 19 is Here!"](https://inside.java/2022/09/20/podcast-026/)

{{< youtube
    video="vvXmO2ZMGsk" >}}

### Nuevas características

#### Linux/RISC-V Port

RISC-V es una es una arquitectura con un conjunto de instrucciones de código abierto, además de un diseño eficiente y moderno no requiere pagar licencias que lo hace interesante para muchas empresas y fabricantes al contrario de otras arquitecturas como ARM que requiere pagar licencias. RISC-V ya es soportado por varias herramientas de desarrollo importantes y hay una disponibilidad de hardware que va en aumento. Por estos motivos, una versión que permite usar Java en procesadores RISC-V es valiosa.

El _port_ de Java para RISC-V incluye el intérprete el compilador JIT para el cliente y servidor y una implementación para todos los recolectores de basura soportados incluyendo ZGC y Shenandoah.

### Nuevas características en vista previa

#### _Record Patterns_

El _patern matching_ permite eliminar algunos _cast_ explícitos, esto ya ha sido aplicado en expresiones como condiciones. Ahora el _pattern matching_ se aplica a los _records_ que permite una forma de desestructurar este tipo de clases para obtener los elementos de _record_ en variables disponibles en el ámbito de uso de la expresión.

{{< code file="pattern-matching.java" language="java" options="" >}}

Al escribir la expresión de _pattern matching_ para un _record_ permite al mismo tiempo desestructurar los elementos y extraerlos a variables. La expresión resultante para desestructurar los elementos es muy verbosa pero hace más simple el acceso posterior a las variables de _record_.

{{< code file="record-patterns.java" language="java" options="" >}}

#### _Foreign Function & Memory API_

Esta característica facilita el uso del código nativo de forma eficiente y más sencilla que anteriormente con JNI, en esta nueva vista previa es un refinamiento de las versione anteriores incorporando los comentarios y sugerencias desde la última vista previa.

Para muchos desarrolladores esta característica es irrelevante o solo se benefician de forma indirecta pero para otros que trabajan con software embebido o que desarrollan librerías Java que hacen uso de código nativo implementado en otros lenguajes supone una gran mejora.

#### _Virtual Threads_

Un sistema operativo como Linux solo es capaz de ofrecer a las aplicaciones una cantidad limitada de _threads_ ya que su consumo de recursos es elevado en el sistema y el cambio de contexto de unos a otros penaliza el rendimiento.

Para solventar esta limitación Java implementa dentro del JVM los _threads_ virtuales que son más ligeros en recursos que los _threads_ del sistema operativo y el cambio de contexto de uno a otro no supone tanta penalización en el rendimiento. Los _threads_ virtuales permiten a las aplicaciones poder usar un mayor cantidad de hilos pasando de unos miles a varios millones sin ningún problema. Para las aplicaciones que usan gran cantidad de hilos como las de servidor de un hilo por petición permite escalar a un número significativamente mayor de peticiones y un mejor rendimiento.

Adicionalmente, una de las mejoras cosas es que el uso de los _threads_ virtuales no requiere una nueva API ya que la implementación la realiza usando la misma clase [Thread](javadoc19:java.base/java/lang/Thread.html) que ha existido siempre en Java desde las versión 1.0 de Java.

La solución habitual a la escasez de _threads_ y la limitación del sistema operativo para aprovechar el hardware ha sido la programación asíncrona, está sin embargo es más difícil de desarrollar, entender y depurar. Otra opción de algunos lenguajes han sido las _coroutines_ como en [Kotlin][kotlin] y otros lenguajes.

Los _threads_ virtuales y la concurrencia estructurada promete la simplicidad de la programación estructurada con la ventaja de proporcionar un rendimiento similar que la programación asíncrona.

* [Project Loom: Fibers and Continuations for the Java Virtual Machine](https://cr.openjdk.java.net/~rpressler/loom/Loom-Proposal.html)

{{< youtube
    video="6dpHdo-UnCg" >}}

{{< youtube
    video="I9hQvJO39uM" >}}

#### _Structured Concurrency_

Los _threads_ virtuales permiten dedicar un _thread_ a cada tarea con lo que se prevé que las aplicaciones aprovechen poder utilizar una mayor número de _threads_, pero gestionarlos sigue siendo un problema ya que los hilos siguen siendo independientes y su depuración complicada.

Si los _threads_ virtuales permiten utilizar _threads_ en abundancia la concurrencia estructurada asegura que los _threads_ estén relacionados correctamente y de forma robusta además de mostrar los _threads_ en herramientas de depuración de forma que sean entendidos por los desarrolladores.

La clase [StructuredTaskScope](javadoc19:/jdk.incubator.concurrent/jdk/incubator/concurrent/StructuredTaskScope.html) permite estructurar una tarea ejecutada en varios hilos, tanto en los casos en que una tarea se divide en varias operaciones como en los casos de que por cada petición entrante se emplea un hilo como en un servidor.

{{< code file="structured-concurrency-1.java" language="java" options="" >}}
{{< code file="structured-concurrency-2.java" language="java" options="" >}}

#### _Pattern Matching for switch_

El _pattern matching_ en las expresiones _switch_ han sido mejorados en esta nueva vista previa.

* Las _guard patterns_ son reemplazados con cláusulas _when_ en los bloques _switch_.
* La semántica del _switch_ en los casos en que es _null_ la expresión se ajusta a la semántica existente que ha existido en los _switch_ lanzando un _NullPointerException_.

{{< code file="pattern-matching-switch.java" language="java" options="" >}}

Por otro lado todo, ahora en los casos de un _switch_ es posible usar el label _null_ para seleccionar la rama cuando la expresión del _switch_ toma un valor nulo, el comportamiento anterior era que si la expresión _switch_ era _null_ producía un _NullPointerException_ lo que requería utilizar un _guard clause_ antes del _switch_ para evitar el NPE.

Desde Java 16 los bloques switch permiten dos estilos de grupos uno mediante etiquetas con _:_ y otro de una única consecuencia con _->_ donde la continuación en cascada no es posible. En el primer caso los múltiples labels se escriben <code>case l1: case l2:</code> cuando en el estilo del segundo se escriben <code>case l1, l2 -></code>.

Soportar la etiqueta _null_ permite ahora escribir un switch de la siguiente forma.

{{< code file="switch-null-label.java" language="java" options="" >}}

### Otras novedades

#### Interpolación de cadenas (_String Templates_)

En un futuro es posible que Java incorpore al lenguaje la interpolación de cadenas con la [JEP 430: String Templates](https://openjdk.org/jeps/430). Facilitará la construcción de cadenas con una mezcla de cadenas y valores de variables que produzcan la cadena resultante deseada. La construcción de cadenas es algo muy utilizado en todos los programas con lo que la interpolación de cadenas será una mejora en la la construcción de cadenas que hace el código más legible.

Un problema de la interpolación de cadenas es que en ciertas cadenas hay posibilidad de crear un problema de seguridad y por ello es peligrosa, una forma de este problema es el _SQL injection_. La interpolación de cadenas que se incorporará en Java solucionará al mismo tiempo que la legibilidad del código los problemas que pueden ocurrir con el _SQL injection_ creando una clase _TemplateString_ para las cadenas con interpolación y unos _policies_ específicos para cada lenguaje como SQL o JSON según el formato resultante que garantizará una cadena resultante bien formada y evitará el problema de _injection_.

Estos son algunos ejemplos que se están evaluando para implementar la interpolación de cadenas. El _STR_ sería el _policy_ que determina como convertir el [TemplateString](https://cr.openjdk.java.net/~jlaskey/templates/docs/api/java.base/java/lang/template/TemplatedString.html) en un _String_. Además será posible implementar nuevos _policies_ haciendo una implementación de la interfaz funcional [TemplateProcessor](https://cr.openjdk.java.net/~jlaskey/templates/docs/api/java.base/java/lang/template/TemplateProcessor.html).

{{< code file="string-interpolation.java" language="java" options="" >}}

Personalmente prefiero la sintaxis <code>${...}</code> en vez de <code>\\{...}</code> ya que la primera es la opción utilizada en otros lenguajes y no me gusta mucho ese _syntactic sugar_ (o _rat poison_) para usar los _TemplateProcessor_ que me parece nada coherente con el lenguaje. Utilizar el _$_ en vez de _\\_ es una alternativa que han evaluado pero que de momento han descartado para evitar conflictos con código heredado. También han evaluado otro tipo de alternativas, en cualquier caso hasta la versión final y que esta sea incorporada en el lenguaje si lo es es muy posible que haya cambios.

{{< youtube
    video="HiHgAh7wWPc" >}}

{{% /post %}}


