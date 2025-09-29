---
pid: 515
type: "post"
title: "Novedades de Java 15"
url: "/2020/09/novedades-de-java-15/"
date: 2020-09-18T14:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-15.webp"
imagePost: "image:java-15.webp"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "Algunas novedades de anteriores versiones que en la versión de Java 15 pasan a calificarse con el grado de producción y otras características como una segunda versión preliminar. Sin grandes cambios en el lenguaje tan destacables de versiones anteriores como las _lambdas_ de Java 8 o los módulos de Java 9, en Java 15 se añaden las _sealed classes_."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

En septiembre del 2020 se ha publicado la versión 15 de Java siguiendo el calendario propuesto desde la versión de Java 9 de una nueva versión cada seis meses y de una versión de soporte a largo plazo cada tres años, la primera LTS ha sido Java 11 a la que sucederá Java 17 cómo LTS en septiembre de 2021.

Con este calendario de publicaciones tan frecuente las novedades son varias en cada nueva versión aunque algunas son simplemente versiones preliminares que pueden cambiar ligeramente en siguientes versiones destinadas a evaluar y probar las funcionalidades que se incorporarán de forma definitiva con posterioridad. Aunque sean versiones preliminares se pueden usar con normalidad, simplemente hay que tener en cuenta que en versiones posteriores requieran modificaciones en el código fuente o lo que es lo mismo no se garantiza la compatibilidad hacia atrás hasta que sean declaradas como una versión definitiva.

Dado que ahora las versiones de Java son mucho más numerosas y frecuentes se hace más necesario utilizar [la herramienta SDKMAN][blogbitix-489] para instalar al mismo tiempo varias versiones del JDK de Java y poder cambiar de una a otra con facilidad.

{{< tableofcontents >}}

## Introducción

* [Características de Java 15](https://openjdk.java.net/projects/jdk/15/)
* [Notas de publicación de Java 15](https://www.oracle.com/java/technologies/javase/15u-relnotes.html)
* [Documentación de Java 15](https://docs.oracle.com/en/java/javase/15/)
* [Documentación Javadoc de Java 15](javadoc15:index.html)
* [JavaMagazine: Inside Java 15: Fourteen JEPs in five buckets](https://blogs.oracle.com/javamagazine/inside-java-15-fourteen-jeps-in-five-buckets)

Esta es la lista de novedades de Java 15, algunas son cambios que no tiene gran impacto en el lenguaje ni la plataforma al ser más cambios internos que reimplementan y modernizan código existente, otras son versiones preliminares y segundas versiones preliminares no definitivas, algunas características en versiones anteriores se marcan como públicas dejando de ser preliminares, otras que se marcan como obsoletas desaconsejándose su uso y otras ya marcadas como obsoletas anteriormente son eliminadas.

* 339: [Edwards-Curve Digital Signature Algorithm (EdDSA)](https://openjdk.java.net/jeps/339)
* 360: [Sealed Classes (Preview)](https://openjdk.java.net/jeps/360)
* 371: [Hidden Classes](https://openjdk.java.net/jeps/371)
* 372: [Remove the Nashorn JavaScript Engine](https://openjdk.java.net/jeps/372)
* 373: [Reimplement the Legacy DatagramSocket API](https://openjdk.java.net/jeps/373)
* 374: [Disable and Deprecate Biased Locking](https://openjdk.java.net/jeps/374)
* 375: [Pattern Matching for instanceof (Second Preview)](https://openjdk.java.net/jeps/375)
* 377: [ZGC: A Scalable Low-Latency Garbage Collector](https://openjdk.java.net/jeps/377)
* 378: [Text Blocks](https://openjdk.java.net/jeps/378)
* 379: [Shenandoah: A Low-Pause-Time Garbage Collector](https://openjdk.java.net/jeps/379)
* 381: [Remove the Solaris and SPARC Ports](https://openjdk.java.net/jeps/381)
* 383: [Foreign-Memory Access API (Second Incubator)](https://openjdk.java.net/jeps/383)
* 384: [Records (Second Preview)](https://openjdk.java.net/jeps/384)
* 385: [Deprecate RMI Activation for Removal](https://openjdk.java.net/jeps/385)

## Nuevas características

### Algoritmo de firma digital Edwards-Curve (EdDSA)

El algoritmo de firma digital EdDSA o _Edwards-Curve Digital Signature Algorithm_ (EdDSA) es demandado por mejorar la seguridad y el rendimiento comparado con otros algoritmos de firma, ya está implementado en otras librerías de criptografía como [OpenSSL][openssl]. Este esquema de firma es opcional en TLS 1.3 pero es uno de los tres permitidos. Añadir este algoritmo permite usar EdDSA en Java sin recurrir a librerías de terceras partes.

### Bloques de texto

En Java embeber en el código un trozo de código HTML, XML, SQL o JSON en un literal como un String requiere editarlo de forma significativa con caracteres de escape y concatenación para que el código compile. La cadena transformada resultante es poco legible y difícil de mantener.

Un bloque de texto HTML en código Java requiere de múltiples caracteres de escape y concatenaciones de cadenas.

{{< code file="TextBlocks-1.java" language="java" options="" >}}

Usando bloques de texto se eliminan los caracteres de escape y las concatenaciones. El código resultante es mucho más legible y fácil de mantener.

{{< code file="TextBlocks-2.java" language="java" options="" >}}

### Clases ocultas

Se añaden clases ocultas o _hidden classes_ que son clases que no pueden usarse directamente por otras clases. Su intención es que sean usadas por _frameworks_ que generan clases en tiempo de ejecución y las usan de forma indirecta con _reflection_.

### Reimplementación de la antigua API DatagramSocket

Se reemplazan las implementaciones de bajo nivel para la comunicación por red [java.net.DatagramSocket](javadoc15:java.base/java/net/DatagramSocket.html) y [java.net.MulticastSocket](javadoc15:java.base/java/net/MulticastSocket.html) con una implementación mas simple y moderna que es más fácil de mantener, depurar y fácil de adaptar a [los _threads_ virtuales del proyecto Loom][blogbitix-485].

### Recolectores de basura ZGC y Shenandoah

Se califican como versión de producción los recolectores de basura _ZGC_ y _Shenandoah_ que ofrecen tiempos de pausa bajos aunque se mantiene como recolector de basura por defecto _G1_. Se soportan todas las plataformas comunes, Linux/x86_64, Linux/aarch64, Windows y macOS. El recolector de basura _ZGC_ se activa con la opción de la máquina virtual _-XX:+UseZGC_ y _Shenandoah_ con _-XX:+UseShenandoahGC_.

* [El recolector de basura de Java, qué hace y cómo funciona en cada versión][blogbitix-463]

## Nuevas características en vista previa

### _Sealed Classes_

En Java las clases permiten la reutilización de código mediante la herencia, los métodos de una clase son heredados por las subclases que la extiendan. Sin embargo, en ocasiones la jerarquía de clases sirve para modelar el dominio sin querer permitir que sea extendido por cuales quiera otras clases.

En Java toda clase puede ser extendida por defecto la única forma de no permitir extender una clase es utilizando la palabra reservada _final_. Sin embargo, esto impide la extensión de la clase completamente.

Las clases _sealed_ especifican de forma explícita que clases tiene permitido la extensión y herencia. Las clases _sealed_ son más restrictivas que el comportamiento por defecto de permitir a cualquier clase la extensión pero más permisivo que si se utiliza la palabra clave _final_ que impide a cualquier clase la extensión.

Se introduce una nueva palabra reservada _sealed_. La declaración de la clase _sealed_ se realiza con el siguiente sintaxis, en este ejemplo la clase _Shape_ solo puede ser extendida por las clases _Circle_, _Rectangle_ y _Square_.

{{< code file="SealedClasses.java" language="java" options="" >}}

### _Pattern Matching_ para _instanceof_

Se mantiene en la categoría de funcionalidad preliminar esta funcionalidad ya publicada en Java 14 que permite eliminar algunos _cast_ de tipos explícitos.

{{< code file="IfPatternMatching-1.java" language="java" options="" >}}

El operador _instanceof_ permite renombrar la variable y dentro de la rama usarla sin necesidad de realizar el _cast_, esto simplifica el código y evita posibles errores.

{{< code file="IfPatternMatching-2.java" language="java" options="" >}}

### _Records_

Los _records_ son clases inmutables con unas convenciones implícitas que no requieren escribir mucho del código considerado ceremonial en las clases de datos Java que hacen al lenguaje _verboso_ para estas clases simples.

Escribir clases portadoras de datos en Java requieren una buena cantidad de código de bajo valor, repetitivo, propenso a errores para especificar constructores, métodos de acceso a propiedades e [implementar correctamente los métodos _equals_, _hashCode_ y _toString_][blogbitix-199].

La siguiente clase _record_ es equivalente al POJO tradicional de muchas más líneas de código.

{{< code file="Records.java" language="java" options="" >}}

## Otras características incorporadas y cambios

Otras especificaciones que no tienen tanto impacto desde el punto de vista del programador y en el lenguaje son las siguientes. Algunas eliminan y marcan como desaconsejado su uso.

Entre las más destacables está _Foreign-Memory Access_ que permite a los programas Java acceder de forma segura y eficiente a memoria externa fuera de la memoria _heap_ de Java. También a destacar el soporte de Unicode 13.0 que añade unos 5K nuevos caracteres o el soporte para el algoritmo de _hash_ SHA-3 en el apartado de seguridad.

{{< reference >}}
* [JDK 15 Release Notes](https://jdk.java.net/15/release-notes)
* [Inside Java 15: Fourteen JEPs in five buckets](https://blogs.oracle.com/javamagazine/inside-java-15-fourteen-jeps-in-five-buckets)
* [The Arrival of Java 15](https://blogs.oracle.com/java-platform-group/the-arrival-of-java-15)
* [Java 15 and IntelliJ IDEA ](https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/)
{{< /reference >}}

{{% /post %}}
