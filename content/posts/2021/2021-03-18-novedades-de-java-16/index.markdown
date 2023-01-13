---
pid: 560
type: "post"
title: "Novedades de Java 16"
url: "/2021/03/novedades-de-java-16/"
date: 2021-03-18T19:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:java-16.webp"
imagePost: "image:java-16.webp"
tags: ["java", "planeta-codigo"]
series: ["java-platform"]
summary: "En Java 16 los cambios en el lenguaje no son tan notables que en versiones anteriores, aún así  el calendario se sigue manteniendo y en cada versión se incluyen mejoras incrementales destacables. Se publica la versión final y lista para producción de algunas características y se añaden nuevas en forma de vista previa."
---

{{% post %}}

Las nuevas versiones se van sucediendo de forma periódica y puntual según la cadencia de seis meses del adoptado calendario de publicación. En esta ocasión la versión 16 de Java publicada el 16 de marzo de 2021, que incluye mejoras incrementales destacando soporte para nuevas plataformas, inclusión de forma final de características que anteriormente estaban marcadas como vista previa y algunas nuevas características destacables que entran en forma de vista previa y nuevas revisiones de vistas previas anteriores.

La versión 16 de Java será la última antes de la siguiente versión de soporte a largo plazo o LTS, la versión 17 será una versión LTS que sucederá a la anterior LTS publicada de Java 11. La versión 11 fue publicada en septiembre de 2018 y la 17 será publicada en septiembre de 2021.

{{< tableofcontents >}}

### Introducción

No hay cambios importantes en el lenguaje, el más destacado es la inclusión de _pattern matching_ para el operador _instanceof_, la incorporación de forma final de los _Records_, soporte para las plataformas Windows/AArch64, distribuciones como Alpine basadas en _musl_ y soporte para la comunicación mediante canales _Unix-Domain Socket_.

En los cambios que entran en modo vista previa destacan la Vector API que permite la computación en paralelo aprovechando las instrucciones vectoriales en los procesadores como AVX en x86 y Neon en ARM para acelerar las operaciones de una instrucción múltiples datos o SIMD. Aparte de los destacados hay números cambios más realizados en las APIs de las clases y el añadido de algunas funcionalidades menores, que en las notas de publicación están documentados.

* [Características de Java 16](https://openjdk.java.net/projects/jdk/16/)
* [Notas de publicación de Java 16](https://www.oracle.com/java/technologies/javase/16-all-relnotes.html)
* [Documentación de Java 16](https://docs.oracle.com/en/java/javase/16/)
* [Documentación Javadoc de Java 16](javadoc16:index.html)
* [JavaMagazine: From the vector API to records to elastic metaspace, there’s a lot packed into Java 16](https://blogs.oracle.com/javamagazine/java-champion-favorite-java16-records-vector-arm64-github)

Las mejoras incluídas en esta versión son:

* 338: [Vector API (Incubator)](https://openjdk.java.net/jeps/338)
* 347: [Enable C++14 Language Features](https://openjdk.java.net/jeps/347)
* 357: [Migrate from Mercurial to Git](https://openjdk.java.net/jeps/357)
* 369: [Migrate to GitHub](https://openjdk.java.net/jeps/369)
* 376: [ZGC: Concurrent Thread-Stack Processing](https://openjdk.java.net/jeps/376)
* 380: [Unix-Domain Socket Channels](https://openjdk.java.net/jeps/380)
* 386: [Alpine Linux Port](https://openjdk.java.net/jeps/386)
* 387: [Elastic Metaspace](https://openjdk.java.net/jeps/387)
* 388: [Windows/AArch64 Port](https://openjdk.java.net/jeps/388)
* 389: [Foreign Linker API (Incubator)](https://openjdk.java.net/jeps/389)
* 390: [Warnings for Value-Based Classes](https://openjdk.java.net/jeps/390)
* 392: [Packaging Tool](https://openjdk.java.net/jeps/392)
* 393: [Foreign-Memory Access API (Third Incubator)](https://openjdk.java.net/jeps/393)
* 394: [Pattern Matching for instanceof](https://openjdk.java.net/jeps/394)
* 395: [Records](https://openjdk.java.net/jeps/395)
* 396: [Strongly Encapsulate JDK Internals by Default](https://openjdk.java.net/jeps/396)
* 397: [Sealed Classes (Second Preview)](https://openjdk.java.net/jeps/397)

{{< youtube
    video="L0tOTN7Dkso" >}}

### Nuevas características

#### Habilitar características de C++14

Hasta el JDK 15, las características usadas por el código C++ en el JDK ha estado limitado a los estándares de C++98/02. Con el JDK 11, el código fué actualizado para soportar nuevas versiones del estándar C++, aunque hasta ahora no han sido usadas nuevas características. Esto incluye poder construirse con versiones recientes de varios compiladores que soportan características de C++11/14.

Ahora se permiten formalmente cambios en el código C++ del JDK para aprovechar las características del lenguaje C++14.

* [C++14 wikipedia](https://en.wikipedia.org/wiki/C%2B%2B14)
* [C++14](https://isocpp.org/wiki/faq/cpp14)

#### Migración de Mercurial a Git

Se migra como herramienta de control de versiones de [Mercurial][mercurial-scm] a [Git][git]. Esto incluye todos los repositorio de los proyectos de OpenJDK preservando el historial, incluyendo las etiquetas y formateando los mensajes de _commits_ según las convenciones de Git.

#### Migración a GitHub

Relacionada con la migración de Mercurial a Git es la migración a la plataforma [GitHub][github] como proveedor de hospedaje para los repositorios. Los diferentes proyectos del OpenJDK están accesibles en GiHub.

* [OpenJDK GitHub](https://github.com/openjdk/)

Los beneficios de esta migración son tiempos más reducidos en el clonado y obtención de cambios, mayor disponibilidad de los repositorios, posibilidad de interactuar con los repositorios mediante listas de correo, herramientas de línea de comandos y navegadores web.

Usar un proveedor de hospedaje externo libera a los contribuidores de implementar y administrar el servicio. Las tres razones principales de usar un proveedor externo son el rendimiento, acceso a una API para automatizar las tareas y acceso a una mayor comunidad que proporcione incorpore nuevos contribuidores.

La razón de usar GitHub es que destaca en las tres razones principales de usar un proveedor externo.

#### ZGC: procesado concurrente de pila del _thread_

El recolector de basura ZGC tiene como objetivo hacer que las pausas de recolección de basura sean una cosa del pasado. Muchas de las operaciones del recolector de basura ya se han hecho ya escalables al tamaño de la memoria y del _metaespace_.

Las únicas tareas que son realizadas en puntos seguros del recolector de basura son un subconjunto de procesado raíz. Una de ellas son las pilas de trazas de excepciones. Estas tareas de procesado raíz son problemáticas escalan con el número de hilos, en sistemas con muchos hilos estas tareas se convierten en un problema.

Para conseguir el objetivo de que las pausas seguras del recolector de basura no excedan de un milisegundo, incluso en máquinas grandes, se ha de mover este procesado por hilo incluyendo la inspección de la pila de trazas a una fase concurrente.

Después de los cambios nada significativo se hará dentro de las pausas seguras del recolector de basura.

* [El recolector de basura de Java, qué hace y cómo funciona en cada versión][blogbitix-463]

#### Canales de comunicación Unix

Se ha añadido soporte para la comunicación mediante _sockets_ Unix a las APIs del paquete [java.nio.channels](javadoc16:java.base/java/nio/channels/package-summary.html). Los _sockets_ Unix con una forma de comunicación entre procesos o IPC en la misma máquina. Son similares a los _sockets_ TCP/IP en la mayoría de aspectos excepto que son resueltos por nombres del sistema de archivos en vez de direcciones IP y números de puertos. La comunicación mediante _sockets_ Unix es más segura y eficiente que las conexiones TCP/IP.

* Las comunicaciones son para procesos en el mismo sistema, las aplicaciones que no tiene intención de aceptar conexiones remotas pueden mejorar la seguridad con los _sockets_ Unix.
* Los _sockets_ Unix están protegidos por los mecanismo de seguridad del sistema de archivos.
* Los _sockets_ Unix son más rápidos de establecer y ofrecen mayor tasa de transferencia.

Los _sockets_ Unix son una característica implementada en la mayoría de sistemas Unix y son soportados en Windows 10 y Windows Server 2019.

Los elementos de la API son [java.net.UnixDomainSocketAddress](javadoc16:java.base/java/net/UnixDomainSocketAddress.html), [java.net.StandardProtocolFamily](javadoc16:java.base/java/net/StandardProtocolFamily.html), [SocketChannel](javadoc16:java.base/java/nio/channels/SocketChannel.html) y [ServerSocketChannel](javadoc16:java.base/java/nio/channels/ServerSocketChannel.html).

Más detalles en [JEP-380: Unix domain socket channels](https://inside.java/2021/02/03/jep380-unix-domain-sockets-channels/)

{{< image
    gallery="true"
    image1="image:unix-sockets.webp" optionsthumb1="650x450" title1="Unix Sockets"
    caption="Unix Sockets" >}}

#### Portado a Alpine Linux

Se ha portado el JDK a Alpine Linux y otras distribuciones que usan [musl][musl-libc] como librería C en las arquitecturas x64 y AArch64. _musl_ es una implementación para los sistemas basados en Linux de la librería estándar con su funcionalidad descrita en los estándares de ISO C y POSIX. Varias distribuciones Linux incluyendo [Alpine Linux][alpine-linux] y [OpenWrt][openwrt] están basadas en _musl_, mientras que otras proporcionan soporte de forma opcional como [Arch Linux][archlinux].

La distribución Alpine Linux es una distribución ampliamente adoptada en los entornos en la nube, microservicios y contenedores debido a su pequeño tamaño de imagen. La imagen base de Alpine Linux por ejemplo es de unos 6 MB lo que la hace atractiva por sus tiempos de arranque, ancho de banda más bajos y mayor seguridad al incluir menos componentes. Habilitar Java para funcionar en estos entornos permite ejecutar de forma nativa [Tomcat][tomcat], [Jetty][jetty], [Spring][spring] y otros _frameworks_ populares.

Complementandlo con _jlink_ para reducir el tamaño en tiempo de ejecución del JDK un usuario puede crear imágenes más pequeñas. Por ejemplo, si la aplicación depende solo del módulo _java.base_ entonces una imagen de [Docker][docker] con Alpine Linux y un _runtime_ de Java con solo ese módulo y la máquina virtual de servidor cabe en tan solo 38 MB.

* [Imágenes de Docker con Alpine Linux][blogbitix-228]

#### Metaspace elásctico

La memoria no usada de _metaespace_ es retornada al sistema operativo con más rapidez reduciendo el tamaño del _metaspace_ y simplificando el código de _metaspace_ con la intención de reducir los costes de mantenimiento.

#### Portado a Windows/AArch64

Con la disponibilidad del nuevo hardware de consumo y servidor basado en la arquitectura AArch64 (ARM64), Windows/AArch64 se ha convertido en una plataforma importante debido a la demanda de los usuarios. Se ha portado el JDK a Windows/AArch64 continuando el trabajo anterior para la portabilidad de Linux/AArch64 (JEP 237) y en un futuro se hará lo equivalente para macOS.

#### Mensajes de advertencias para clases valor

Las clases envoltorio o _wrapper_ de los tipos primitivos se designan como [clases valor](javadoc16:java.base/java/lang/doc-files/ValueBased.html), ahora se emiten nuevos mensajes de advertencia en sus constructores marcados como _deprecated_ desde Java 9 y candidatos a ser eliminados en futuras versiones.

Las clases _wrapper_ de primitivos incluyen las _Integer_, _Float_, _Double_, etc., estas clases satisfacen la mayor parte de los requerimientos de las clases valor con la excepción de que exponen los constructores marcados como obsoletos.

Esto es un objetivo para el [proyecto Valhalla](https://openjdk.java.net/projects/valhalla/) de implementar clases primitivas que mejoran el modelo de programación de Java.

* [Value-based Classes](javadoc16:java.base/java/lang/doc-files/ValueBased.html)
* [Integer(int value)](javadoc16:https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Integer.html#%3Cinit%3E(int))

#### _Packaging Tool_

Se proporciona una herramienta para empaquetar aplicaciones Java que genera instaladores nativos para las diferentes plataformas. La herramienta _jpackage_ fue incorporada en Java 14 y ahora pasa a considerarse con la categoría de listo para producción, su API está en el [jdk.jpackage](javadoc16:jdk.jpackage/module-summary.html).

Soporta los formatos de empaquetado nativos de la plataforma para tener una experiencia de instalación natural. Estos formatos incluyen archivos _msi_ y _exe_ en windows, _pkg_ y _dmg_ en macOS, _deb_ y _rpm_ en Linux. Permite especificar en tiempo de empaquetado parámetros a usar en tiempo de ejecución. Se puede invocar de forma directa desde la línea de comandos y de forma programática mediante su API.

#### _Pattern Matching_ para _instanceof_

Se mejora el soporte del lenguaje para soportar _pattern matching_ en el operador _instanceof_. Esto evita la necesidad de realizar _cast_ de forma explícita simplificando el código, más legible y seguro.

{{< code file="instanceof-pattern-matching-1.java" language="java" options="" >}}

El uso de _pattern matching_ reduce la necesidad de hacer _cast_ de forma explícita siendo particularmente útil en los métodos de igualdad con _equals_.

{{< code file="instanceof-pattern-matching-2.java" language="java" options="" >}}

#### _Records_

Las clases _Record_  son un nuevo tipo de clases en el lenguaje Java, ayudan a modelar agregados de datos con menos ceremonia que las clases normales. Son clases que actúan como contenedores para datos inmutables, pueden ser considerados como tuplas. La declaración de un _record_ mayormente consiste en la declaración de su estado. No es su objetivo resolver los problemas de las clases mutables que usan las convenciones de nombres de los JavaBeans.

Se publicaron como primera vista previa en Java 14 y una segunda versión en Java 15.

Las siguientes dos versiones de la clase _Point_ son equivalentes.

{{< code file="records-1.java" language="java" options="" >}}
{{< code file="records-2.java" language="java" options="" >}}

{{< youtube
    video="tLHUqXeiC4w" >}}

#### Encapsulación fuerte del clases internas del JDK por defecto

Se cambia el comportamiento por defecto de la encapsulación de permitido a fuerte para las clases internas del JDK aunque siguiendo posible relajar el comportamiento de la encapsulación con la opción de línea de comandos _--illegal-access=permit_, la encapsulación fuerte hace una excepción para ciertas clases internas como _sun.misc.Unsafe_  que siguen siendo posible utilizarlas. Esto permite continuar con la encapsulación y mantenibilidad del JDK que era el objetivo del proyecto _Jigsaw_ materializado en la incorporación de los módulos en Java 9. Se promueve el uso de APIs estándares para actualizaciones a futuras versiones sin problemas.

A lo largo de los años los desarrolladores han estado usando clases internas del JDK de los paquetes _java.*_, _sun.*_. Nunca se ha aconsejado pero nada impedía su uso hasta la aparición de los módulos. El uso de las clases de estos paquetes supone un problema de seguridad y mantenibilidad en el JDK pero impedir de forma drástica su uso haría que muchas librerías y aplicaciones que las usan dejasen de funcionar.

Ahora se encapsula de forma más fuerte las API internas que aplica tanto en tiempo de compilación como de ejecución, incluyendo los intentos del código de acceder a elementos mediante _reflection_.

### Nuevas características en vista previa

#### Vector API

El objetivo es proporcionar una API para aprovechar las instrucciones vectoriales hardware de las arquitecturas de CPU que permiten conseguir un mejor rendimiento a las computaciones equivalentes escalares. Las instrucciones vectoriales son conocidas como [Single Instruction Multiple Data](https://en.wikipedia.org/wiki/SIMD) (SIMD) que permiten mayor paralelismo al aplicar la misma operación a múltiples datos en un único ciclo de reloj de la CPU.

Su objetivo es proporcionar una API clara y concisa, agnóstica de la plataforma, de alto rendimiento para las arquitecturas x64 y AArch64 y con soporte de degradado en caso de no estar las instrucciones disponibles.

La API de vectorial es muy útil para funcionalidases como procesado de imágenes, procesado de sonido, gráficos inteligencia artificial que necesitan realizar la misma operación sobre muchos datos.

{{< youtube
    video="HARDCbSog0c" >}}

#### Foreign Linker API

El objetivo es ofrecer una API con tipado estático y código Java para acceso a código nativo. Junto con _Foreign-Memory Access API_ simplifica considerablemente el proceso propenso a errores de enlazado a una librería nativa.

Sus objetivos son facilidad de uso reemplazando JNI con un modelo de desarrollo superior basado puramente en Java, soportar C en las plataformas x64 y AArch64, soportar otras plataformas como 32-bit x86 y otros lenguajes distintos de C como C++ o Fortran y ofrecer un alto rendimiento comparable o mejor que JNI.

* [Ejemplo de JNI, usar código en C desde Java][blogbitix-217]

{{< youtube
    video="DjiKlOE9ibY" >}}

#### Foreign-Memory Access API

Su objetivo es proporcionar a los programas Java una forma segura y eficiente de acceder a memoria fuera de la memoria de la máquina virtual de Java.

{{< youtube
    video="Edls8HIREk4" >}}

#### _Sealed Classes_

Las clases selladas o _sealed classes_ permiten al autor de la clase o interfaz controlar que código es responsable de implementarlo. Proporcionan una forma de restringir el uso de la superclase distinta de los modificadores de acceso. En un futuro puede ser la base para implementar _pattern matching_ para un análisis exhaustivo de patrones.

* [Los modificadores de acceso de clases, propiedades y métodos en Java][blogbitix-458]

{{< code file="sealed-classes.java" language="java" options="" >}}

{{% /post %}}
