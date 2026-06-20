---
pid: 732
type: "post"
title: "Evitar los NullPointerException en Java con JSpecify"
url: "/2026/06/evitar-los-nullpointerexception-en-java-con-jspecify/"
date: 2026-06-05T19:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["java"]
summary: "Si llevas tiempo programando en Java, sabrás de sobra que el NullPointerException es uno de esos errores que aparece cuando menos lo esperas y que no en vano se le conoce como el *error del billón de dólares*: omnipresente, frustrante y, en teoría, completamente evitable. A diferencia de lenguajes más modernos como Kotlin, Java nunca fue diseñado para garantizar la seguridad frente a nulos a nivel del sistema de tipos, aunque el ecosistema ha evolucionado si no para resolver el problema para mitigarlo. Desde la llegada de Optional en Java 8 hasta la reciente adopción de JSpecify por parte de Spring, existen hoy herramientas y estándares para detectar y mitigar los NPE en tiempo de compilación, que vale la pena conocer."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Si hay un error que causa numerosos problemas en el lenguaje de programación es el de NullPointerException o NPE está en las primeras posiciones. Esto es debido a que el lenguaje de programación Java y su compilador hasta el momento no están diseñados para evitarlo.

El SDK de Java si ha añadido algunas clases y métodos para mitigar el problema de los NPE.

El siguiente artículo de Java Code Geeks explica muy bien muchos aspectos del problema, artículo que recomiendo leer. En este artículo explicaré un poco los detalles y proporciono un ejemplo de código ejecutable con la aplicación de la solución.

* [JSpecify vs. Kotlin’s Built-in Null Safety: Can Annotations Ever Match a Type System?](https://www.javacodegeeks.com/2026/03/jspecify-vs-kotlins-built-in-null-safety-can-annotations-ever-match-a-type-system.html)

{{< tableofcontents >}}

## Qué son los punteros o referencias a objetos

Lo que en el lenguaje de programación C se llaman punteros en Java se conoce como referencias a objetos. C trabaja a más bajo nivel, Java oculta en gran parte la complejidad para el programador de referencia de objetos y desreferenciarlos. En cualquier caso es útil como es el funcionamiento a bajo nivel de C para comprender el problema de los NullPointerExceptions en Java.

Un puntero o una referencia a un objeto es una variable que contiene la dirección de memoria a una estructura de datos u objeto.

## El problema de los NullPointerException

Lo que en C provoca un error fatal en el programa lo que puede provocar su finalización abrupta, en Java provoca un NullPointerException o NPE tratable de alguna forma, por ejemplo emitiendo una línea de log.

Un NullPointerException es un error en tiempo de ejecución y un error en la implementación del programa. En un programa perfectamente implementado nunca debería darse un NullPointerException. Sin embargo, los desarrolladores cometen errores y este es uno de los errores más numerosos en los programas. Pero no todo es culpa de los desarrolladores al igual que el compilador valida los tipos en el código fuente del programa al compilarlo, el mismo compilador puede ayudar a evitarlos. Al mismo tiempo, el lenguaje Java podría haberse diseñado y con la ayuda del compilador para que los programas Java estuvieran libres de NullPointerException. Como han hecho lenguajes posteriores como Kotlin. Introducir un cambio a nivel del lenguaje provocaría romper la compatibilidad hacia atrás cosa que Java se caracteriza en evitarlo.

El problema de los NullPointerException en Java surge cuando se intenta acceder a un miembro de una variable cuya referencia es null, no se puede invocar el método del objeto.

{{< code file="Car.java" language="java" options="" >}}

A los NPE se le conoce también como el problema del billón de dólares por su frecuencia en la mayoría de programas.

## Soluciones

Hasta que los diseñadores del lenguaje Java decidan incorporar alguna sintaxis para evitar los NullPointerException en este momento hay varias opciones para mitigar el problema.

### Optional

La clase [Optional](javadoc:java.base/java/util/Optional.html) introducida en Java 8 es una clase que referencia un objeto o no referencia ningún objeto. Tiene métodos para conocer si referencia o no un objeto y métodos orientados a la programación funcional. Además es una clase que usa los tipos genéricos de Java introducidos en Java 5.

Sustituyendo el tipo de las variables que puedan ser null se mitigan los los NullPointerException.

{{< code file="CarOptional.java" language="java" options="" >}}

El problema de los Optional es usar sustituir en todas las variables el tipo a Optional. En código existente y _legacy_ esto significa mucho trabajo y propenso a introducir errores. Otra desventaja de los Optional es que hace más incómodo trabajar con objetos requiriendo el uso de sus métodos funcionales, y es innecesario cuando la referencia no hay posibilidad de que sea nula.

Entre sus ventajas está que el tipo Optional comunica de forma clara al leer el código que la referencia puede ser nula.

### JSpecify

Dada la frecuencia de los NullPointerException varias organizaciones han acordado poner en común en el estándar de [JSpecify][jspecify] una solución que use una combinación de anotaciones y la ayuda del compilador.

A fecha de hoy solo incluye anotaciones para tratar los null aunque en el futuro podría incluir alguna anotación más para el análisis estático de código.

JSpecify no evita los NPE en tiempo de ejecución simplemente hace que el compilador muestre una advertencia o error cuando se detecte un caso potencial de NPE, las anotaciones es información para el compilador. Se trata de detectar los casos en los que se usa una variable que puede ser nula

[Spring Framework][spring-framework] 6.x anotó su API pública con las anotaciones _@Nullable_ y _@NonNull_ de JSpecify, lo que permite que [IntelliJ][intellij] y [NullAway][nullaway] propaguen el análisis de nulabilidad a través de las dependencias de Spring sin configuración extra.

## Las anotaciones de JSpecify

La especificación se compone de las siguientes 4 anotaciones. Dos para anotar un tipo de datos y dos para anotar ámbitos completos.

Las 4 anotaciones _core_:

| Anotación     | Ámbito                 | Descripción                                                     |
|---------------|------------------------|-----------------------------------------------------------------|
| @Nullable     | Type                   | El tipo **puede** ser null                                      |
| @NonNull      | Type                   | El tipo **nunca** será null                                     |
| @NullMarked   | Package, Class, Method | Todo en el scope es non-null por defecto, (útil en migraciones) |
| @NullUnmarked | Nested scope           | Revierte el NullMarked                                          |

Una limitación de JSpecify es que las anotaciones solo tienen efecto si todo el código que interactúa con esa API también está anotado. En código _legacy_ no anotado, el _checker_ no puede hacer las comprobaciones y el comportamiento es como si no se usa JSpecify.

## El checker

Las anotaciones por sí mismas simplemente añaden información adicional a los tipos, es necesario hacer uso de un _checker_ para que en tiempo de compilación el compilador emita mensajes de error cuando se haga uso de una variable que potencialmente genere un NPE.

IntelliJ soporta las anotaciones de JSpecify, proporciona indicaciones en caso de detectar un potencial NullPointerException. Con la combinación de [Error Prone][error-prone] y su plugin de checker NullAway se añade al compilador la capacidad de hacer honor a las anotaciones de JSpecify generando errores de compilación.

* [Análisis estático de código Java con Spotless y Error Prone][blogbitix-731]

Este es el error que da el compilador cuando detecta un uso de valor null en una variable que está anotada como no nula.

{{< code file="System.out" language="plain" options="" >}}

En caso de querer ignorar el error se puede hacer con la anotacinón _@SuppressWarnings_.

{{< code file="supresswarnings-nullaway.java" language="java" options="" >}}

## Cómo lo soluciona Kotlin

El lenguaje basado en la JVM [Kotlin][kotlin] más moderno que Java ha sido diseñado desde el principio para resolver el problema de los NullPointerExceptions. Para ello Kotlin tiene dos tipos distintos en el sistema de tipos para una misma clase. 

Un tipo nullable y otro no nullable. El tipo nullable es potencialmente causante de un NPE, un tipo no nullable no puede causar NPE ya que su referencia siempre apuntará a una instancia del objeto. El tipo nullable se especifica con el sufijo _?_.

{{< code file="Name-1.kt" language="kotlin" options="" >}}

El compilador se encarga de verificar y forzar al programador a añadir comprobaciones para evitar NPE en los tipos nullables.

{{< code file="Name-2.kt" language="kotlin" options="" >}}

## El ejemplo

El ejemplo de Spotless, Error Prone y JSpecify se puede probar con el siguiente código.

{{< code file="Main.java" language="java" options="" >}}

{{< code file="build.gradle.kts" language="kotlin" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoSpotless" command="./gradlew run" %}}

{{% /post %}}
