---
pid: 716
type: "post"
title: "Novedades y nuevas características de Java 25"
url: "/2025/09/novedades-y-nuevas-características-de-java-25/"
date: 2025-09-19T09:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "Habitualmente publicaba un artículo por cada versión de Java, pero teniendo en cuenta que para producción es recomendable usar preferentemente las versiones LTS estoy prefiriendo publicar uno en cada versión LTS con el acumulado de características de la versión anterior. En este caso de Java 25 como nueva LTS que sucede a Java 21."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Java destaca por ser un lenguaje en el que la compatibilidad hacia atrás es importante, por ello migrar de una versión anterior a una nueva no suele dar excesivos problemas, aunque no fue así en el caso de Java 8 a Java 11 por cambios relevantes en el lenguaje y eliminación de clases en el JDK. Los problemas suelen estar en las librerías de las aplicaciones más que en el lenguaje en sí.

Entre las dos LTS, de Java 21 a Java 25 ha habido 3 versiones no LTS algunas con nuevas características en modo de previsualización sujetas a cambios hasta su versión definitiva y actualizaciones de esas características en su versión final. También en todas estas nuevas versiones hasta Java 25 se han publicado varias características en su versión definitiva.

En septiembre de 2025 se ha publicado la versión 25 del lenguaje de programación Java y su SDK, siguiendo el calendario que se estableció hace tiempo con el que las releases de Java son más frecuentes y previsibles.

{{< tableofcontents >}}

## Introducción

Java 25 es especial al ser una versión LTS, esto es de soporte prolongado más adecuado para entornos en el que la seguridad es más relevante que tener las últimas características del lenguaje, y especialmente dirigida a entorno empresariales y aplicaciones cuyo soporte en parches de seguridad es una característica deseada y prioritaria.

El soporte de esta versión 25 llegará hasta septiembre del 2030 para cuya fecha y con algunos años de antelación ya se habrá publicado una nueva versión LTS. El soporte de Java 21, la anterior LTS, tiene de soporte hasta septiembre de 2028 para cuya fecha las aplicaciones en producción es recomendable que migren a la siguiente LTS por seguridad.

* [Oracle Java SE Support Roadmap](https://www.oracle.com/java/technologies/java-se-support-roadmap.html)

Todas estas nuevas características están detalladas en las notas de las versiones cuyos enlaces anoto a continuación.

* [JDK 25](https://openjdk.org/projects/jdk/25/)
* [JDK 24](https://openjdk.org/projects/jdk/24/)
* [JDK 23](https://openjdk.org/projects/jdk/23/)
* [JDK 22](https://openjdk.org/projects/jdk/22/)
* [Oracle Releases Java 25](https://www.oracle.com/news/announcement/oracle-releases-java-25-2025-09-16/)
* [Oracle Releases Java 24](https://www.oracle.com/es/news/announcement/oracle-releases-java-24-2025-03-18/)
* [Oracle Releases Java 23](https://www.oracle.com/news/announcement/oracle-releases-java-23-2024-09-17/)
* [Oracle Releases Java 22](https://www.oracle.com/news/announcement/oracle-releases-java-22-2024-03-19/)
* [JDK 25 Release Notes issues](https://www.oracle.com/java/technologies/javase/25-relnote-issues.html)
* [JDK 24 Release Notes issues](https://www.oracle.com/java/technologies/javase/24-relnote-issues.html)
* [JDK 23 Release Notes issues](https://www.oracle.com/java/technologies/javase/23-relnote-issues.html)
* [JDK 22 Release Notes issues](https://www.oracle.com/java/technologies/javase/22-relnote-issues.html)
* [JDK 25 Release Notes](https://jdk.java.net/25/release-notes)
* [JDK 24 Release Notes](https://jdk.java.net/24/release-notes)
* [JDK 23 Release Notes](https://jdk.java.net/23/release-notes)
* [JDK 22 Release Notes](https://jdk.java.net/22/release-notes)

## Nuevas características de Java

Una de las características destacadas de Java 21 fueron los virtual threads.

* [Novedades y nuevas características de Java 21][blogbitix-690]

Desde Java 21 las características que destaco son las siguientes en sus versiones finales y no previsualización o experimentales, o alguna en previsualización destacada. Me centro más en las que tienen un impacto desde el punto de vista del desarrollador, también hay muchas novedades en cuanto al recolector de basura, seguridad con nuevos algoritmos y mejor soporte de Unicode.

Cada una incorpora muchas actualizaciones en modo preview y menos en su versión definitiva, entre las 4 desde Java 22 hasta Java 25, proporcionan una mejora incremental notable sobre Jav 21.

En las notas de publicación de cada una de las características las siguientes y otras están explicadas más extensamente y mejor.

### Java 22

* [Unnamed Variables & Patterns](https://openjdk.org/jeps/456), los nombres de las variables que son requeridas pero no son usadas ahora pueden nombrarse como «_» lo que mejora la legibilidad del código en varios aspectos.

### Java 23

* [Graal JIT now Available](https://openjdk.org/jeps/474), el compilador de Graal JIT ahora es incluido como parte del JDK.
* [Markdown Documentation Comments](https://openjdk.org/jeps/467), los comentarios pueden ser escritos usando el formato Markdown en vez de solo como una mezcla de HTML y anotaciones.
* String Templates, eliminada y esta es la razón por la que no es recomendable usar características en modo preview ni definitivas para aplicaciones en producción.

### Java 24

* [Ahead-of-Time Class Loading & Linking](https://openjdk.org/jeps/483), proporciona una mejora en el tiempo de arranque de las aplicaciones mediante el uso de una cache para la carga de claes y enlazado. Los usuarios de Spring Boot pueden usar esta característica mediante Class Data Sharing y con el soporte de Buildpacks o el archivo Dockerfile. En la descripción del JEP la mejora del tiempo de arranque llega hasta el 40%.
    * [Class Data Sharing](https://docs.spring.io/spring-boot/how-to/class-data-sharing.html)
    * [Dockerfiles](https://docs.spring.io/spring-boot/reference/packaging/container-images/dockerfiles.html#packaging.container-images.dockerfiles.cds)
* [Stream Gatherers](https://openjdk.org/jeps/485), permiten aplicar operaciones de transformación al usar stream que eran difícil de aplicar sin estos nuevos añadidos a la API.
* [Class-File API](https://openjdk.org/jeps/484), proporcionan una API standard para parsear, generar y transformar archivos class de Java. Será muy útil para las librerías que hacen manipulación de código en tiempo de compilación y ejecución.

### Java 25

* [Flexible Constructor Bodies](https://openjdk.org/jeps/513), la llamada a super en el constructor ya no es necesario que sea la primera línea, esto permite poner delante validaciones sobre los argumentos del constructor.
* [Scoped Values](https://openjdk.org/jeps/506), permite compartir datos inmutables entre threads. Son más eficientes que las variables thread-local y de razonar. Útil al usar virtual threads y structured concurrency.
* [Ahead-of-Time Command-Line Ergonomics](https://openjdk.org/jeps/515), hace más fácil usar las caches ahead-of-time.


{{< reference >}}
* [New Features in Java 25](https://www.baeldung.com/java-25-features)
* [Java 25, the Next LTS Release, Delivers Finalized Features and Focus on Performance and Runtime ](https://www.infoq.com/news/2025/09/java25-released/)
* [Here’s Java 25, Ready to Perform to the Limit ](https://hanno.codes/2025/09/16/heres-java-25/)
* [JDK 24 and JDK 25: What We Know So Far](https://www.infoq.com/news/2025/02/java-24-so-far/)
* [JDK 22: The new features in Java 22](https://www.infoworld.com/article/3708329/jdk-22-the-new-features-in-java-22.html)
* [Java 22 Overview](https://www.baeldung.com/java-22-overview)
* [How to Use Java 21’s Virtual Threads in Real-World Web Applications](https://www.javacodegeeks.com/2025/05/how-to-use-java-21s-virtual-threads-in-real-world-web-applications.html)
* [Java 25 LTS and IntelliJ IDEA](https://blog.jetbrains.com/idea/2025/09/java-25-lts-and-intellij-idea/)
{{< /reference >}}

{{% /post %}}
