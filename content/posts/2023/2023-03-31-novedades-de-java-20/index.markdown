---
pid: 682
type: "post"
title: "Novedades de Java 20"
url: "/2023/03/novedades-de-java-20/"
date: 2023-03-31T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-20.webp"
imagePost: "image:java-20.webp"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "La cadencia de una nueva versión de Java cada 6 meses continúa. En Java 20 no hay novedades en el lenguaje solo nuevas vistas previas de funcionalidades que se publicarán en un futuro en su versión definitiva. En cualquier caso la versión incorpora otra serie de cambios más pequeños que mejoran la seguridad, el rendimiento del JDK y corrigen errores. La siguiente versión Java 21 será una versión LTS que la hace más destacada por su más prolongado tiempo de soporte y porque incorpora todas las novedades desde la anterior LTS para aquellos que migran de LTS a LTS."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

En marzo de 2023 se ha publicado la versión 20 de Java. En general las novedades no añaden nada al lenguaje simplemente incorporan nuevos JEP que definen nuevas vistas previas de anteriores funcionalidades.

Estos JEP permiten experimentar, probar y modificarlas antes de publicarse como versiones definitivas en posteriores versiones de Java. En Java 20 no se añade nada nuevo en el lenguaje pero hay varios nuevos JEPs que sí serán importantes novedades en el lenguaje.

Después de cambiar el ciclo de publicación de tres a dos años para las versiones LTS la versión del JDK 21 será una LTS. Las versiones LTS son versiones especiales por su periodo prolongado de soporte que en un ámbito empresarial es algo deseado por lo que se suele esperar a estas versiones, momento en el cual se obtienen todas las novedades de las versiones no LTS.

{{< tableofcontents >}}

## Introducción

Entre las novedades de Java 20 aunque son todo vistas previas cada una de estas novedades serán muy destacadas cuando se incorporen de forma definitiva. A destacar están los _virtual threads_, _structured concurrency_, _scoped values_ que sustituirán a los _ThreadLocal_ que se han usado hasta ahora para compartir datos entre _threads_. En cada una de las nuevas versiones de los JEP en el apartado de _history_ de detallan los cambios de estas nuevas vistas previas sobre la anterior.

Más allá de los JEPs el JDK 20 incorpora otro conjunto de novedades destacadas y cambios relativos a la seguridad, marcar como _deprecated_ varios métodos no tan destacados como los cambios en el lenguaje tienen relevancia en los casos que apliquen.

* [Características de Java 20](https://openjdk.org/projects/jdk/20/)
* [Notas de publicación de Java 20](https://jdk.java.net/20/release-notes)
* [Documentación de Java 20](https://docs.oracle.com/en/java/javase/20/)
* [Documentación Javadoc de Java 20](https://docs.oracle.com/en/java/javase/20/docs/api/index.html)
* [JavaMagazine: Java 20 is here: Strengthening Java’s foundation for the future](https://blogs.oracle.com/javamagazine/post/java-20-now-available)

Las novedades incluidas en esta versión son:

* 429: [Scoped Values (Incubator)](https://openjdk.org/jeps/429)
* 432: [Record Patterns (Second Preview)](https://openjdk.org/jeps/432)
* 433: [Pattern Matching for switch (Fourth Preview)](https://openjdk.org/jeps/433)
* 434: [Foreign Function & Memory API (Second Preview)](https://openjdk.org/jeps/434)
* 436: [Virtual Threads (Second Preview)](https://openjdk.org/jeps/436)
* 437: [Structured Concurrency (Second Incubator)](https://openjdk.org/jeps/437)
* 438: [Vector API (Fifth Incubator)](https://openjdk.org/jeps/438)

Con cada nueva versión los medios publican artículos que detallan cada una de las novedades.

* [Oracle Releases Java 20](https://www.oracle.com/news/announcement/oracle-releases-java-20-2023-03-21/)
* [Java 20 sneak peek](https://blogs.oracle.com/javamagazine/post/java-20-preview)
* [The Arrival of Java 20!](https://inside.java/2023/03/21/the-arrival-of-java-20/)
* [JDK 20: The new features in Java 20](https://www.infoworld.com/article/3676699/jdk-20-the-new-features-in-java-20.html)
* [Java 20 Guide](https://nipafx.dev/java-20-guide/)
* [Java 20 Delivers Features for Projects Amber, Loom and Panama ](https://www.infoq.com/news/2023/03/java20-released/)
* [Java 20: A Sneak Peek on the Panama FFM API](https://www.javacodegeeks.com/2022/12/java-20-a-sneak-peek-on-the-panama-ffm-api-second-preview.html)

No incluyo ejemplos de código ya que todas estas novedades ya las he mencionado en versiones anteriores de novedades de Java y en los artículos de esta lista se muestran de forma detallada.

## Otros cambios

Entre la lista de cambios adicionales en el JDK está el soporte para unicode 15.

Para algunos algoritmos de criptografía versiones optimizadas de las funciones para las instrucciones AVX512, AVX, AVX2, funciones _intrinsic_ optimizadas por un humano en vez del compilador que permite mejorar el rendimiento es secciones críticas del código.

Se ha cambiado el comportamiento de los métodos _Thread.suspend()_, _Thread.resume()_, _Thread.stop()_ que ahora lanzan una excepción. Estos métodos han sido problemáticos en su uso en ambientes concurrentes y se está haciendo un esfuerzo por eliminarlos para evitar sus problemas.

Se elimina el soporte para generar _bytecode_ _-source/-target/--release_, con la política definida en [JEP 182](https://openjdk.org/jeps/182) se soporta únicamente 1+3 anteriores versiones. Por ejemplo, en Java 9 se soportan las versiones 9, 8, 7, 6.

Se actualiza la base de datos de zonas horarias a _2022c_, afecta en casos en los que se usen zonas horarias de antes de 1970. Las zonas horarias cambian más a menudo de que en un principio se puede pensar y la base de datos de zonas horarias se ha de actualizar de forma periódica, actualizar la base de datos de zonas horarias requiere actualizar el JDK o cambios adicionales y por ello es un motivo de preferir usar una librería.

## Novedades futuras

Como ocurre con las versiones en vista previa tardan en publicarse como versión definitiva. Entre los cambios futuros posibles y que se incorporen en el lenguaje están los siguientes.

Los [universal generics](https://openjdk.org/jeps/8261529) permitirán crear colecciones de tipos primitivos. Esto permitirá simplificar los algoritmos que manejan genéricos y reducir el número de métodos a crear para soportar los tipos primitivos.

Los [sequenced collections](https://openjdk.org/jeps/431) tiene el objetivo de proporcionar unos métodos unificados de manejo de colecciones más universal, ahora varias clases de colecciones tiene métodos diferentes para operaciones similares lo que ocasiona cierta inconsistencia.

Los [string templates](https://openjdk.org/jeps/430) permite realizar interpolación de cadenas en el lenguaje.

{{< youtube
    video="" >}}

{{% /post %}}