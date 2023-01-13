---
pid: 626
type: "post"
title: "Novedades de Java 18"
url: "/2022/03/novedades-de-java-18/"
date: 2022-03-31T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-18.webp"
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "Las novedades de Java 18 no añaden nada en el lenguaje pero sí incorpora algunas relevantes en la plataforma como el uso de UTF-8 por defecto, una utilidad de línea de comandos para disponer de un servidor web simple, poder añadir fragmentos de código en los comentarios de la documentación Javadoc así como el marcado para su eliminación en futuras versiones de la finalización de objetos. También publican otras novedades en incubación, nuevas versiones en vistas previa de otras características se publicarán de forma definitiva en siguientes versiones y otros numerosos cambios menores."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las mejoras incrementales se siguen añadiendo en cada nueva versión lanzada cada seis meses. En marzo de 2022 ha sido publicada la versión de Java 18 como siguiente versión después de Java 17 que es una versión con soporte extendido o LTS. Seguramente no muchos van a poder utilizar esta nueva versión por restricciones en los proyectos que seguirán usando versiones ya consideradas bastante antiguas incluyendo Java 8 y más en los casos que utilizan versiones anteriores. Por otro lado, otra parte de usuarios quizá esperen a la siguiente versión LTS para actualizar las aplicaciones en el caso de que ya estén utilizando Java 17.

La lista de características de Java 18 no es muy amplia pero hay algunas destacadas.

{{< tableofcontents >}}

### Introducción

Con el paso de los meses muchas librerías y entornos de desarrollo irán publicando nuevas versiones compatibles con Java 18 y añadiendo soporte. Cuatro de las características más destacada son la utilización de la codificación de caracteres UTF-8 por defecto, la inclusión de un pequeño servidor web, soporte para añadir fragmentos de código en la documentación Javadoc y finalmente marcar para ser eliminada en una siguiente versión la finalización de objetos. Otras mejoras son nuevas vistas previas de características que están disponibles para obtener comentarios pero que en una siguiente versión podrían cambiar.

* [Características de Java 18](https://openjdk.java.net/projects/jdk/18/)
* [Notas de publicación de Java 18](https://jdk.java.net/18/release-notes)
* [Documentación de Java 18](https://docs.oracle.com/en/java/javase/18/)
* [Documentación Javadoc de Java 18](javadoc18:index.html)
* [JavaMagazine: Java 18 is here: 9 JEPs with core library improvements and updates](https://blogs.oracle.com/javamagazine/post/java-jdk-18-generally-available)

Las mejoras incluidas en esta versión son:

* 400: [UTF-8 by Default](https://openjdk.java.net/jeps/400)
* 408: [Simple Web Server](https://openjdk.java.net/jeps/408)
* 413: [Code Snippets in Java API Documentation](https://openjdk.java.net/jeps/413)
* 416: [Reimplement Core Reflection with Method Handles](https://openjdk.java.net/jeps/416)
* 417: [Vector API (Third Incubator)](https://openjdk.java.net/jeps/417)
* 418: [Internet-Address Resolution SPI](https://openjdk.java.net/jeps/418)
* 419: [Foreign Function & Memory API (Second Incubator)](https://openjdk.java.net/jeps/419)
* 420: [Pattern Matching for switch (Second Preview)](https://openjdk.java.net/jeps/420)
* 421: [Deprecate Finalization for Removal](https://openjdk.java.net/jeps/421)

En los siguientes vídeos hace una explicación de estas nuevas características.

{{< youtube
    video="Nu225G7pMHw" >}}
{{< youtube
    video="5GfE1-vEl1A" >}}

Con el nuevo calendario de publicaciones se observa claramente que los desarrolladores pueden predecir de forma más mucho fiable cuando se publicará una nueva versión. Se observa que el tiempo entre versiones se ha reducido considerablemente respecto a lo que tardaron las versiones Java 6 y 7 y se observa la incorporación de novedades de forma constante.

{{< image
    gallery="true"
    image1="image:java-releases.webp" optionsthumb1="650x450" title1="Publicaciones de Java"
    caption="Publicaciones de Java" >}}

### Nuevas características

#### Codificación de caracteres UTF-8 por defecto

Las operaciones que hacían entrada y salida como en el caso del sistema de archivos se utilizaban por defecto si no se indicaba uno explícitamente la codificación del sistema operativo. Dado que cada sistema operativo es capaz de definir su codificación de caracteres esto creaba algunas inconsistencias cuando las aplicaciones se ejecutan en diferentes sistemas operativos. Ahora en caso de que no se indique una codificación de caracteres se utiliza UTF-8 ofreciendo una mejor consistencia entre las diferentes plataformas.

#### Servidor web simple

Se añade una utilidad de línea de comandos y una API para iniciar un sencillo servidor web para archivos estáticos. La finalidad es que no haya que recurrir a utilidades externas al JDK y sirva para hacer pruebas. No es su intención reemplazar servidores web más completos como [Apache][apache-httpd] o [Nginx][nginx] ni ofrece funcionalidades dinámicas como un contenedor de _servlets_ como [Tomcat][tomcat].

Dado que es un servidor web simple tiene algunas limitaciones, como que solo soporta el protocolo HTTP/1.1, no soporta el protocolo seguro HTTPS, solo soporta operaciones idempotentes de los verbos HEAD y GET devolviendo el resto de versos un código de error 501 _Not Implemented_ o 405 _Not Allowed Response_. Algunos MIME types son configurados de forma automática como los archivos con extensión _html_ que son servidos con la cabecera de contenido _text/html_.

{{< code file="jwebserver.sh" language="java" options="" >}}
{{< code file="jwebserver.out" language="java" options="" >}}

Además de la línea de comandos se ofrece una API para iniciar el servidor web mediante código.

* [Working with the Simple Web Server](https://inside.java/2021/12/06/working-with-the-simple-web-server/)

#### Fragmentos de código en comentarios Javadoc

Algunos de los comentarios de Javadoc incluyen ejemplos de código. Hasta ahora los fragmentos de código insertaban con el _taglet_ _@code_ que tenía varias deficiencias como no ofrecer resaltado de sintaxis, no poder incluir fragmentos de código de archivos existentes con lo que puede quedar obsoleto y no poder hacer pruebas unitarias sobre este código con lo que podría no funcionar.

Se añade el _taglet_ _@snippet_ que permite incluir fragmentos o _snippets_ de código en los comentarios de Javadoc, con resaltado de sintaxis y soporte para acceder a los _taglets_ para procesar el código de la anotación. La anotación permite incluir en los comentarios de Javadoc regiones de archivos externos y aplicar transformaciones en el contenido a incorporar en el Javadoc. Pudiendo añadir fragmentos de código de archivos externos y pudiendo realizar transformaciones no hace falta copiar código y pegar código en los comentarios Javadoc sino que los fragmentos de código se extraen directamente de los archivos de código fuente.

Además se ofrece una API para acceder a esos fragmentos de código pudiendo hacer pruebas unitarias del código incluido en el Javadoc, esto no es solo para los desarrolladores del JDK sino también para cualquier programador que añada fragmentos de su código Java en la documentación Javadoc.

Los fragmentos de código no están limitados a archivos de código Java sino que también es posible incluir otros formatos utilizados comúnmente en aplicaciones Java como archivos _properties_,  _html_ u otros. En esta guía se detalla como usar los fragmentos de código con ejemplos de código.

* [Programmer's Guide to Snippets](https://docs.oracle.com/en/java/javase/18/code-snippet/index.html)

#### Reimplementación de la reflexión con manejadores de métodos

Se reimplementan varias funcionalidades relacionadas con la reflexión utilizando los manejadores de eventos con la intención de facilitar el mantenimiento del JDK. Los _MethodHandlers_ ofrecen una alternativa a la reflexión con mejor rendimiento a la vez que es más legible. Es un cambio interno más para los desarrolladores del JDK que para los usuarios.

* [MethodHandles](javadoc18:java.base/java/lang/invoke/MethodHandles.html)
* [MethodHandle](javadoc18:java.base/java/lang/invoke/MethodHandle.html)

#### Proveedor para resolver direcciones IP

Hasta ahora el JDK utilizaba el mecanismo del sistema operativo para resolver direcciones IP. Este es compartido por todas las aplicaciones y la operación de resolución es bloqueante que es un problema para la incorporación de los _threads_ ligeros del [project Loom](http://openjdk.java.net/projects/loom/). Se ha añadido un mecanismo extensible para proporcionar otras formas de resolución y soportar nuevos protocolos como DNS sobre QUIC, TLS y HTTPS. Ahora las aplicaciones tiene más control sobre el mecanismo de resolución y es útil en el contexto de pruebas automatizadas.

### Nuevas características en vista previa

Como ya es habitual en el JDK se van incluyendo algunas características en modo vista previa que pueden ser utilizadas pero que en una siguiente versión y el código que las usen deberá ser modificado. Se ofrecen para que los usuarios puedan experimentar con ellas y los desarrolladores obtengan comentarios y si es necesario hacer cambios en siguientes versiones.

#### Vector API

Se proporciona una nueva versión de la Vector API que permite aprovechar las instrucciones SIMD de los procesadores con una API de Java común  para todas las arquitecturas de procesadores. Se aprovechan las características disponibles de los procesadores y en aquellos que no tengan alguna se ofrece un modo de funcionamiento degradado.

En esta nueva versión se soportan las instrucciones _Scalar Vector Extension_ (SVE) de la plataforma [ARM][arm]. También se mejora el rendimiento de las operaciones que aceptan enmascaramiento en las arquitecturas hardware que soportan enmascaramiento.

#### _Foreign Function & Memory API_

Esta API permite acceder a memoria de procesos externos y utilizar librerías programadas en otros lenguajes como una alternativa más fácil de usar, de mayor rendimiento, más general para diferentes arquitecturas y más simple que la anterior alternativa con JNI. En esta nueva versión se mejora la API para soportar y facilitar operaciones con diferentes tipos y accesos a memoria.

### _Pattern Matching_ para las sentencias _switch_

El _parttern matching_ en las sentencias _switch_ permite al compilador comprobar de la expresión del _switch_ contra los casos de la expresión del _switch_. En esta nueva revisión de esta característica el compilador lanza un error en caso de que un patrón domine al siguiente, sea más general siempre se cumpla antes que uno menos general. Ahora también el compilador es más preciso al comprobar la completitud de todos casos del _switch_ en las clases _sealed_.

### Otros cambios

#### Finalización de objetos marcado para ser eliminado

Java es un lenguaje que ha ofrecido recolección de basura desde las primeras versiones. Los programadores nunca han tenido que liberar la memoria de los objetos de forma explícita sino que de esta tarea se encarga la máquina virtual de Java. Pero también Java desde las primeras versiones ha ofrecido un mecanismo para la finalización de objetos que el tiempo ha demostrado que tiene varios fallos de diseño que no lo hacen útil y se desaconseja su utilización.

Algunos fallos de su diseño son que tiene una latencia impredecible y arbitraria sin garantía de que la finalización del objeto se realice. Las acciones de finalización no tienen ninguna restricción pudiendo revivir una referencia de objeto. Siempre está activo para cada instancia de una clase se use o no, ni se puede cancelar. Tampoco hay un orden predefinido en un entorno multihilo de modo que la ejecución de la finalización se puede ejecutar en cualquier orden.

Por los defectos anteriores la eliminación de la finalización de objetos en Java ha sido algo pendiente de realizar, en esta versión de Java aunque no se elimina se marca para la eliminación en versiones futuras de Java de modo que los que lo usen tengan tiempo de actualizar sus librerías y aplicaciones.

Esto solo impactará a algunos usuarios que realicen tareas avanzadas en las que necesiten la finalización de objetos, no es algo que en circunstancias normales se use. La alternativa a la finalización de objetos es usar la sentencia _try-with-resources_ y en los objetos que tengan un ámbito mayor de vida utilizar la clase [Cleaner](javadoc18:java.base/java/lang/ref/Cleaner.html) que permite asociar acciones de finalización a una referencia de objeto cuando esta se convierte en una [referencia _phantom_](javadoc18:java.base/java/lang/ref/PhantomReference.html).

#### Otros cambios

Los anteriores son los cambios más destacados de Java 18 pero otro buen número de cambios menores e internos en el JDK. En las notas de publicación se incluye una lista detallada de todos.

Estos son artículos que también hacen resúmenes de las novedades de Java 18.

* [The Arrival of Java 18](https://blogs.oracle.com/java/post/the-arrival-of-java-18)
* [Oracle Announces Java 18](https://www.oracle.com/news/announcement/oracle-releases-java-18-2022-03-22/)
* [JDK 18: The new features in Java 18](https://www.infoworld.com/article/3630510/jdk-18-the-new-features-in-java-18.html)
* [Java 18 Features Support (IntelliJ)](https://blog.jetbrains.com/idea/2022/03/java-18-features-support/)
* [JDK 18 Security Enhancements](https://seanjmullan.org/blog/2022/03/23/jdk18)
* [Episode 23 “Java 18 is Here!”](https://inside.java/2022/03/22/podcast-023/)
* [Java 18 Brings New Features to Accelerate Software Development](https://www.itprotoday.com/programming-languages/java-18-brings-new-features-accelerate-software-development)

{{% /post %}}
