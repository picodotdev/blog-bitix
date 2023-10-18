---
pid: 690
type: "post"
title: "Novedades y nuevas características de Java 21"
url: "/2023/10/novedades-y-nuevas-caracteristicas-de-java-21/"
date: 2023-10-19T18:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-21.webp"
imagePost: "image:java-21.webp"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "Java 21 como versión LTS es una versión especial. Los *virtual threads* junto con las _sequenced collections_ y las nuevas posibilidades de _pattern matching_ quizá son las novedades más destacadas de esta versión. Otras novedades interesantes en el futuro siguen en modo vista previa."
---

{{% post %}}

{{< logotype image1="java.svg">}}

El mes de septiembre de 2023 se ha publicado Java 21, una versión relevante por dos motivos. Un motivo es que es una versión LTS que proporciona un soporte prolongado en el tiempo de actualizaciones de seguridad de 8 años y otro motivo es que incorpora la característica de _virtual threads_.

Además se incorporan otras características en vista previa o actualizaciones de características en vista previa de versiones anteriores. El modelo de desarrollo con calendario fijo sigue incorporando novedades en el lenguaje y la plataforma a un ritmo asombrosamente rápido comparado con la época de Java 5 a Java 8.

Todas las novedades de Java 21 se suman a las novedades desde Java 17 que fue la anterior versión LTS. Las versiones LTS son versiones especiales por su soporte de seguridad extendido lo que las hace deseables como hitos en los que migrar a una nueva versión.

{{< tableofcontents >}}

## Introducción

Quizá la novedad de la plataforma más destacada son los _virtual threads_ que permite pasar de crear miles de _threads_ a millones de _threads_ y sin necesidad de cambios en el lenguaje Java.

Otras novedades destacadas son las _sequenced collections_ que proporcionan uniformidad en la nomenclatura de métodos en las diferentes clases de colecciones que por motivos históricos tenían diferencias,esto da uniformidad al trabajar con colecciones.

En el lenguaje también se incorporan novedades como los _record patterns_ que permiten desestructurar los datos de los _records_, crear patrones en las expresiones _switch_ y evitar _casts_.

En siguientes versiones la _structured concurrency_ y los _scoped values_ será otra novedad importante para simplificar la programación concurrente, con un estilo de programación imperativo pero con los beneficios de la concurrencia.

Otros cambios son menos relevantes para la mayoría de programadores como la Generational ZGC, Vector API en los contextos que lo usen.

Las novedades en vista previa están disponibles para aquellos que quieran usarlas y no les importe que haya que realizar cambios en el código se hay alguna modificación hasta la versión final.

Los siguientes artículos proporcionan una información detallada de las novedades y los enlaces a cada uno de los JEPs una información mucho más detallada.

* [Características de Java 21](https://openjdk.org/projects/jdk/21/)
* [Notas de publicación de Java 21](https://www.oracle.com/java/technologies/javase/21u-relnotes.html)
* [Documentación de Java 21](https://docs.oracle.com/en/java/javase/21/)
* [Documentación Javadoc de Java 21](https://docs.oracle.com/en/java/javase/21/docs/api/index.html)
* [Blog Oracle: The Arrival of Java 21](https://blogs.oracle.com/java/post/the-arrival-of-java-21)
* [Java Magazine: Java 21 is here: Virtual threads, string templates, and more](https://blogs.oracle.com/javamagazine/post/java-21-now-available)

Las novedades incluidas en esta versión son:

* 430: [String Templates (Preview)](https://openjdk.org/jeps/430)
* 431: [Sequenced Collections](https://openjdk.org/jeps/431)
* 439: [Generational ZGC](https://openjdk.org/jeps/439)
* 440: [Record Patterns](https://openjdk.org/jeps/440)
* 441: [Pattern Matching for switch](https://openjdk.org/jeps/441)
* 442: [Foreign Function & Memory API (Third Preview)](https://openjdk.org/jeps/442)
* 443: [Unnamed Patterns and Variables (Preview)](https://openjdk.org/jeps/443)
* 444: [Virtual Threads](https://openjdk.org/jeps/444)
* 445: [Unnamed Classes and Instance Main Methods (Preview)](https://openjdk.org/jeps/445)
* 446: [Scoped Values (Preview)](https://openjdk.org/jeps/446)
* 448: [Vector API (Sixth Incubator)](https://openjdk.org/jeps/448)
* 449: [Deprecate the Windows 32-bit x86 Port for Removal](https://openjdk.org/jeps/449)
* 451: [Prepare to Disallow the Dynamic Loading of Agents](https://openjdk.org/jeps/451)
* 452: [Key Encapsulation Mechanism API](https://openjdk.org/jeps/452)
* 453: [Structured Concurrency (Preview)](https://openjdk.org/jeps/453)

Las novedades de las anteriores versiones desde la anterior versión LTS fueron las siguientes: 

* [Novedades de Java 20][blogbitix-682]
* [Novedades de Java 19][blogbitix-654]
* [Novedades de Java 18][blogbitix-626]
* [Novedades y nuevas características de Java 17][blogbitix-597]

Este ha sido el calendario de publicaciones de cada versión de Java desde la inicial 1.0 hasta la versión 21, junto con las principales novedades de cada versión.

## Vídeos y artículos

Los siguiente artículos y vídeos proporcionan información adicional y explican detalladamente con ejemplos las novedades de esta versión de Java.

* [Java 21, the Next LTS Release, Delivers Virtual Threads, Record Patterns and Pattern Matching](https://www.infoq.com/news/2023/09/java21-released/)
* [Java version history](https://en.wikipedia.org/wiki/Java_version_history)

{{< youtube
    video="5jIkRqBuSBs" >}}
{{< youtube
    video="GYKgVwc_MvQ" >}}

{{% /post %}}