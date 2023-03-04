---
pid: 597
type: "post"
title: "Novedades y nuevas características de Java 17"
url: "/2021/09/novedades-y-nuevas-caracteristicas-de-java-17/"
date: 2021-09-16T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-17.webp"
imagePost: "image:java-17.webp"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "La versión Java 17 sucede a Java 11 como versión LTS, por ello es una versión más importante que las no LTS anteriores. Incorpora todas las mejoras incluidas en todas las no LTS previas más otras adicionales dede Java 16 publicada seis meses antes. Como versión LTS ofrece un soporte de correcciones de errores, fallos y alertas de seguridad  por un periodo de cinco años hasta septiembre de 2026 más un periodo adicional de tres años hasta 2029. La versión 6 del _framework_ de Spring y Spring Boot 3 se tendrán como requisito mínimo Java 17 y Jakarta 9."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

El ciclo de publicación de una nueva versión de Java cada seis meses está siendo un éxito en la evolución del lenguaje, algunas características no tienen un gran impacto en la plataforma o el lenguaje pero otras sí suponen un gran mejora como las  lambdas de Java 8, los módulos de Java 9, inferencia de tipos de Java 10, Java 11 como primera versión LTS con soporte a largo plazo, expresiones _switch_ de Java 12 en vista previa, bloques de texto de Java 13 en vista previa, excepciones _NullPointerException_ más útiles y _Records_ de Java 14, _Sealed Classes_  de Java 15 y encapsulación más fuerte de clases internas del JDK en Java 16 por mencionar simplemente una característica destacada de cada una de ellas.

Java 17 al ser una versión LTS es más importante que las versiones no LTS anteriores. Las versiones LTS son más atractivas para ciertas organizaciones por sus periodos de soporte extendidos.

{{< tableofcontents >}}

## Introducción

Java 17 sucede a como versión LTS en septiembre de 2021 a Java 11 que fué publicada hace tres años en septiembre de 2018. Java 17 según la [hoja de ruta de Java](https://www.oracle.com/java/technologies/java-se-support-roadmap.html) tiene un soporte de cinco años hasta septiembre de 2026 y tres años más hasta septiembre de 2029 en el periodo de soporte extendido para los clientes que paguen ese soporte.

Este calendario de soporte claro y simple permite planificar la estrategia de actualización de forma anticipada de las aplicaciones que usen versiones que han dejado de estar soportadas. Las actualizaciones incluyen correcciones de errores, fallos y alertas de seguridad, actualizaciones legales, regulación y de impuestos asi como certificación con otros productos. El software empresarial por ciclo de vida prefiere usar versiones LTS por su soporte extendido, con la publicación de Java 17 está es la versión recomendada. 

En el caso de pasar de la versión 11, anterior LTS, o previas, la versión 17 incluye las numerosas novedades de las versiones no LTS y previa LTS a esta, de la 12 a la 16.

* [Características de Java 17](https://openjdk.java.net/projects/jdk/17/)
* [Notas de publicación de Java 17](https://www.oracle.com/java/technologies/javase/17-all-relnotes.html)
* [Documentación de Java 17](https://docs.oracle.com/en/java/javase/17/)
* [Documentación Javadoc de Java 17](javadoc17:index.html)
* [JavaMagazine: Java 17 is here: 14 JEPs with exciting new language and JVM features](https://blogs.oracle.com/javamagazine/java-jdk-17-generally-available)

[Spring][spring] ha anunciado que la versión 6 de este _framework_ junto a [Spring Boot][spring-boot] 3 ampliamente usados se basarán en Java 17 y [Jakarta EE][jakartaee] 9. Estas versiones serán la base mínima requerida de la siguiente generación de aplicaciones Java.

Spring 6 y Spring Boot 3 se publicará a finales del 2022 cuando Java 17 incluso ya tenga un par de versiones sucesoras. Los entornos integrados de desarrollo como [IntelliJ IDEA][intellij] han sido actualizados para proporcionar soporte desde el primer momento a las nuevas características de Java 17.

* [Java 17 and IntelliJ IDEA](https://blog.jetbrains.com/idea/2021/09/java-17-and-intellij-idea/)
* [A Java 17 and Jakarta EE 9 baseline for Spring Framework 6](https://spring.io/blog/2021/09/02/a-java-17-and-jakarta-ee-9-baseline-for-spring-framework-6)

Las mejoras incluidas en esta versión son:

* 306: [Restore Always-Strict Floating-Point Semantics](https://openjdk.java.net/jeps/306)
* 356: [Enhanced Pseudo-Random Number Generators](https://openjdk.java.net/jeps/356)
* 382: [New macOS Rendering Pipeline](https://openjdk.java.net/jeps/382)
* 391: [macOS/AArch64 Port](https://openjdk.java.net/jeps/391)
* 398: [Deprecate the Applet API for Removal](https://openjdk.java.net/jeps/398)
* 403: [Strongly Encapsulate JDK Internals](https://openjdk.java.net/jeps/403)
* 406: [Pattern Matching for switch (Preview)](https://openjdk.java.net/jeps/406)
* 407: [Remove RMI Activation](https://openjdk.java.net/jeps/407)
* 409: [Sealed Classes](https://openjdk.java.net/jeps/409)
* 410: [Remove the Experimental AOT and JIT Compiler](https://openjdk.java.net/jeps/410)
* 411: [Deprecate the Security Manager for Removal](https://openjdk.java.net/jeps/411)
* 412: [Foreign Function & Memory API (Incubator)](https://openjdk.java.net/jeps/412)
* 414: [Vector API (Second Incubator)](https://openjdk.java.net/jeps/414)
* 415: [Context-Specific Deserialization Filters](https://openjdk.java.net/jeps/415)

{{< youtube
    video="BW35_IGDseI" >}}

## Nuevas características

### Restaurar la semántica estricta de coma flotante

El [estándar IEEE 754][wikipedia-ieee-754] especifica cómo realizar operaciones de coma flotante y cómo almacenar valores de coma flotante en varios formatos incluyendo en precisión simple (en 32 bits usado en el _float_ de Java) y precisión doble (en 64 bits usado en el _double_ de Java).

Algunos dispositivos hardware como CPU proporcionan precisiones extendidas con mayor precisión o mayor rango del exponente. En esas arquitecturas con precisiones extendidas puede ser más eficiente usarlas para los resultados intermedios evitando operaciones de redondeo o desbordamientos que de otra manera ocurrirían. Esto hace las operaciones más eficientes pero puede ocasionar resultados diferentes en esas arquitecturas. La precisión extendida en las máquinas x86 con la arquitectura x87 por sus peculiaridades era necesaria ser usada para evitar penalizaciones de rendimiento.

Antes de las versiones a la JVM 1.2 los cálculos de coma flotante requerían ser estrictas empleando la misma precisión tal como define el estándar IEEE 754 para todos los cálculos intermedios. La semántica estricta de coma flotante era costosa en el hardware x87 por ello en la versión de la JVM 1.2 se cambió la semántica estricta por defecto por una semántica con precisión extendida produciendo resultados posiblemente más precisos pero a riesgo de resultados menos repetibles entre diferentes arquitecturas.

Dado que los cálculos de coma flotante mediante x87 ya no son necesarios ni tienen penalización de rendimiento en los procesadores x86 que soportan el conjunto de instrucciones SSE2 introducidas en el Pentium 4 y están al mismo tiempo presentes en los procesadores de AMD y sucesores de Intel, Java 17 de nuevo hace que todas operaciones de coma flotante sean estrictas recuperando de forma efectiva el comportamiento de las versiones anteriores a la JVM 1.2.

* [Strictfp](https://en.wikipedia.org/wiki/Strictfp)
* [Formato en coma flotante de simple precisión](https://es.wikipedia.org/wiki/Formato_en_coma_flotante_de_simple_precisi%C3%B3n)
* [x87](https://en.wikipedia.org/wiki/X87)
* [SSE2](https://en.wikipedia.org/wiki/SSE2)
* [What Every Computer Scientist Should Know About Floating-Point Arithmetic](https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html)

### Generadores de números pseudo-aleatorios mejorado

En la API de Java hay varias clases que permiten la obtención de números pseudo-aleatorios, estas clases contienen diferentes métodos con código repetido en varias de las implementaciones.

* [Formas de generar un número aleatorio en un rango con Java][blogbitix-588]

Se proporciona una nueva interfaz [RandomGenerator](javadoc17:java.base/java/util/random/RandomGenerator.html) que proporciona una API uniforme para los generadores de números aleatorios existentes. _RandomGenerator_ proporciona métodos para _ints_, _longs_, y _doubles_ con sus variaciones de parámetros. También se proporciona la factoría [RandomGeneratorFactory](javadoc17:java.base/java/util/random/RandomGeneratorFactory.html) para localizar y construir instancias que implementan la interfaz _RandomGenerator_ usando [la API de ServiceLoader.Provider][blogbitix-94] para registrar las implementaciones.

{{< code file="RandomGeneratorFactory.java" language="java" options="" >}}

### Nuevo _pipeline_ de _renderizado_ para macOS

Se implementa un _pipeline_ de renderizado interno de Java 2D para macOS usando [Apple Metal][apple-metal] como alternativa al _pipeline_ existente que usa el obsoleto Apple OpenGL API estando preparado para cuando [Apple][apple] lo elimine.

Las funcionalidades proporcionadas en el _pipeline_ usando Apple Metal API son equivalentes a las existentes en OpenGL con un rendimiento tan bueno o mejor. Ambos _pipelines_ coexistirán hasta que el _pipeline_ OpenGL se considere obsoleto.

### Portado a macOS/AArch64

En Java 16 se implementó el portado a AArch64 para [Windows][windows], ahora se hace lo equivalente para [macOS][macos]. Dado que ARM es una plataforma que será más común con el tiempo junto con el anuncio de Apple de su plan a largo plazo de transición de la arquitectura x64 a AArch64 se espera una demanda amplia para el portado de macOS/AArch64 del JDK.

* [Mac transition to Apple silicon](https://en.wikipedia.org/wiki/Mac_transition_to_Apple_Silicon)

### Marcado como obsoleto para eliminación la Applet API

Se marca como obsoleta para eliminación la de los Applet. A día de hoy es irrelevante dado que todos los vendedores de navegadores web han eliminado el soporte para los complementos de Java o han anunciado planes para hacerlo. 

Anteriormente en Java 9 con la Applet API fué marcada como deprecada aunque no para eliminación.

La alternativa similar a los Applets es utilizar _Java Web Start_, que permitía descargar y lanzar aplicaciones Java como aplicaciones de escritorio sin utilizar el navegador como entorno de ejecución. En Java 9 _Java Web Start_ fué marcado como obsoleto y en Java 11 el su soporte fué eliminado. La alternativa equivalente a los Applets y _Java Web Start_ es [OpenWebStart][openwebstart].

### Encapsulación fuerte de las clases internas del JDK

Se encapsula de forma fuerte impidiendo su uso de todos los elementos internos del JDK de los paquetes _java.*_, _sun.*_, _com.sun.*_, _jdk.*_ y _org.*_ , exceptuando [ciertas APIs críticas](https://openjdk.java.net/jeps/260#Description) como _sun.misc.Unsafe_. En el JDK 16 se cambió el comportamiento por defecto de permitido a fuerte aún siguiendo siendo posible utilizar la opción para relajar la encapsulación. Ya no será posible relajar la encapsulación de los elementos internos mediante la opción de línea de comandos _--illegal-access=permit_ como era posible en el JDK 9 hasta el JDK 16.

La encapsulación fuerte permite mejorar la seguridad y el mantenimiento del JDK que era uno de los objetivos primarios del proyecto Jigsaw con la incorporación de los módulos. Se aconseja a los desarrolladores migrar del uso de elementos internos  a APIs estándar de modo que tanto los desarrolladores de librerías como sus usuarios puedan actualizar sin complicaciones a futuras versiones de Java.

### Eliminación de RMI Activation

Se elimina el mecanismo de activación de RMI mientras se conserva el resto de RMI. El mecanismo de llamada a procedimiento remoto RMI de Java es una tecnología obsoleta prefiriéndose REST para la integración de sistemas distribuidos, RMI también ha sido superado y mejorado por [gRPC][rpc].

* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]
* [Introducción a gRPC y ejemplo con Java][blogbitix-512]

### Clases _sealed_

Las clases _sealed_ fueron propuestas en Java 15 en modo vista previa, en Java 16 fueron propuestas de nuevo con algunos cambios. Las clases _sealed_ son incorporadas de forma final sin cambios respecto a Java 16. Las clases _sealed_ permite limitar que que clases tienen permitido heredar de una clase definida como _sealed_.

{{< code file="sealed-classes.java" language="java" options="" >}}

### Eliminar el compilador experimental AOT y JIT

Se elimina el soporte experimental de compilación _ahead-of-time_ o _AOT_ y el compilador _just-in-time_ o JIT implementados en Java. Este compilador ha sido usado poco desde su introducción suponiendo un esfuerzo de mantenimiento significativo. Se mantiene la interfaz experimental de compilador JVM basado en Java o JVMCI para que los desarrolladores continúen construyendo externamente versiones del compilador para la compilación JIT.

La herramienta _jaotc_ fue incorporada en el JDK 9 de forma experimental, usa el compilador Graal que está escrito a su vez en Java. El compilador Graal fue incorporado como compilador JIT experimental en el JDK 10. Los desarrolladores que deseen usar el compilador Graal para realizar compilación AOT o JIT pueden usar [GraalVM][graalvm].

### Marcado como obsoleto para eliminación el _Security Manager_

El _Security Manager_ está presente desde la versión de Java 1.0. No ha sido la forma de añadir seguridad en el código Java en el lado cliente  desde hace muchos años y ha sido raramente usado para añadir seguridad en el código de lado del servidor. Se marca como obsoleto el _Security Manager_ en consonancia con la Applet API heredada.

El control de acceso se basa en el principio de menor privilegio que era viable en la reducida librería de clases de Java 1.0 pero con el rápido crecimiento de los paquetes _java.*_ y _javax.*_ ha originado docenas de permisos y cientos de comprobaciones de permisos en todo el JDK. Esto supone un área grande a mantener seguro.

Ahora se considera que la seguridad es mejor implementarla proporcionando integridad a bajo nivel en la plataforma Java, por ejemplo fortaleciendo los límites de los módulos para prevenir acceso a detalles del JDK, y aislando el entorno de ejecución de Java completo de recursos sensibles mediante mecanismos ajenos al proceso o _out-of-process_ como los contenedores y virtualización.

Se marca como obsoleto el _Security Manager_ y eliminan algunas de sus capacidades a lo largo de varias versiones y simultáneamente se crean APIs alternativas para ciertas tareas como bloquear la llamada a _System::exit_ u otros casos de uso considerados suficientemente importantes para tener reemplazos.

En la JEP 411 hay una descripción detallada de las motivaciones de esta eliminación además de su poco uso se proporciona varias deficiencias como un modelo de permisos frágil, un modelo de programación complicado que desincentiva su uso y bajo rendimiento. Tampoco es eficaz para evitar la mayoría de [problemas de seguridad más importantes identificados en 2020](https://cwe.mitre.org/top25/archive/2020/2020_cwe_top25.html).

### Filtros de deserialización específicos para cada contexto

Esto permite a las aplicaciones configurar un filtro de deserialización específico según el contexto y seleccionado dinámicamente mediante una factoría de filtros de la JVM para cada operación de deserialización.

## Nuevas características en vista previa

Además de las características anteriores se incorporan otras en modo experimental que también se pueden usar pero que podrían cambiar en el futuro.

### _Pattern Matching_ para los _switch_

Se mejoran las sentencias y expresiones _switch_ de dos formas:

* Se extienden las etiquetas _case_ para incluir patrones adicionalmente a constantes.
* Se añaden dos nuevos tipos de patrones: patrones de protección o _guarded patterns_ y patrones con paréntesis o _parenthesized patterns_.

Este es un ejemplo de sentencia _if-else_ de varios niveles con expresiones booleanas usando el operador _instanceof_, gracias a que el operador _instanceof_ soporta _pattern matching_ se evita los _cast_ de _Object_ al tipo del _instanceof_, sin embargo, el código de la sentencia _if-else_ sigue siendo de difícil lectura.

{{< code file="switch-pattern-matching-1.java" language="java" options="" >}}

Este es un ejemplo de expresión _switch_ que usa _pattern matching_ para las etiquetas _case_ que sustituye una expresión _if-else_ anterior.

{{< code file="switch-pattern-matching-2.java" language="java" options="" >}}

La etiqueta _case_ también soporta la comprobación del valor _null_ que ha de ser especificado explícitamente, para mantener la compatibilidad hacia atrás el _default_ _case_ no cumple con el valor _null_.

{{< code file="switch-pattern-matching-3.java" language="java" options="" >}}

En el primer caso de esta expresión _switch_ se hace uso de una patrón de protección y de un patrón con paréntesis.

{{< code file="switch-pattern-matching-4.java" language="java" options="" >}}

Un patrón de protección tiene la forma _p && (e)_ donde _p_ es un patrón y _e_ es una expresión booleana, este patrón incluye la unión de todas las variables definidas en el patrón _p_ y la expresión _e_. Una valor cumple con un patrón de protección si primero cumple el patrón _p_ y segundo la expresión _e_ se evalúa como verdadero, si el valor no cumple _p_ no se evalúa la expresión _e_.

Una patrón con paréntesis tiene la forma _(p)_, donde _p_ es un patrón. Una patrón con paréntesis introduce las variables de patrón  que son introducidas por el subatrón _p_. Un valor cumple el patrón parametrizado _(p)_ su cumple el patrón _p_.

### _Foreign Function_ & _Memory_ API

La _Foreign Function & Memory API_ o FFM API permite que los programas Java puedan interoperar con código y datos fuera del entorno de ejecución de Java, invocar eficientemente funciones externas (fuera de la JVM) y acceder de forma segura memoria externa (no gestionada por la JVM).

La FFM API permite a los programa Java llamar a librerías nativas y procesar datos nativos sin la fragilidad y peligro de JNI. Esta es una revisión introducida inicialmente en Java 14 y revisada en Java 15 y 16.

* [Ejemplo de JNI, usar código en C desde Java][blogbitix-217]

La API en el módulo [jdk.incubator.foreign](javadoc17:jdk.incubator.foreign/module-summary.html) define varias clases e interfaces para que el código cliente en librerías puedan:

* Solicitar memoria externa.
* Manipular y acceder a estructuras de memoria externas.
* Gestionar el ciclo de vida de los recursos externos.
* Invocar funciones externas.

### Vector API

Se añade la Vector API para expresar computaciones vectoriales que son compiladas en tiempo de ejecución en las instrucciones vectoriales de las arquitecturas de CPU soportadas, esto permite conseguir un rendimiento superior al equivalente con las computaciones escalares.

La Vector API fue integrada en Java 16 en forma de incubación, en esta nueva revisión se incorporan mejoras en respuesta a los comentarios así como a mejoras de rendimiento y otras mejoras significativas en la implementación.

### Adelante más rápido

Como última nota se está proponiendo lanzar una versión LTS cada dos años en vez de cada tres lo que permitirá a aquellos usuarios que prefieren el soporte extendido tener disponible una versión LTS cada menos tiempo proporcionando más oportunidades de actualización. Por otro lado, hace más atractivas las versiones no-LTS pudiendo los desarrolladores comenzar con una versión no LTS sabiendo que en dos años habrá una versión LTS que poder utilizar en producción.

* [Moving Java Forward Even Faster](https://mreinhold.org/blog/forward-even-faster)

{{< youtube
    video="Twwpk6vub1M" >}}
{{< youtube
    video="KVXbWCwOLg4" >}}

{{< reference >}}
* [A practical look at JEP-412 in JDK17 with libsodium](https://blog.arkey.fr/2021/09/04/a-practical-look-at-jep-412-in-jdk17-with-libsodium/)
* [Expect lifetime support](https://www.oracle.com/support/lifetime-support/)
* [Oracle Lifetime Support Policies Resources](https://www.oracle.com/support/lifetime-support/resources.html)
{{< /reference >}}

{{% /post %}}
