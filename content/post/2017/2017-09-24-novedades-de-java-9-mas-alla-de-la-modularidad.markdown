---
pid: 264
title: "Novedades de Java 9, más allá de la modularidad"
url: "/2017/09/novedades-de-java-9-mas-alla-de-la-modularidad/"
date: 2017-09-24T10:00:00+02:00
updated: 2017-10-07T12:15:00+02:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java","planeta-codigo", "programacion"]
series: ["java-platform"]
summary: "La modularidad introducida en Java 9 no es la única novedad incorporada en esta nueva versión. Aunque haya llegado a eclipsar al resto hay otra buena colección de mejoras que sin duda facilitarán la programación con uno de los lenguajes más empleados por los programadores aún después de más de 20 años."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

La última versión mayor de Java fue publicada en el año 2014, hace ya mucho tiempo para la época actual donde las cosas avanzan a un ritmo muy rápido. [Java 8 introdujo en el lenguaje notables cambios][blogbitix-17] como las expresiones _lambda_ dotándolo de capacidades funcionales y mayor expresividad en menos líneas de código junto con referencias a métodos e interfaces funcionales que permite crear implementaciones anónimas de interfaces con una _lambda_, se añaden los _streams_ como nueva forma de iterar sobre las colecciones, interfaces con métodos por defecto o estáticos que aumentan la compatibilidad hacia atrás que siempre se le ha dado gran importancia en la plataforma o una nueva API para fechas que solventa las deficiencias de la anterior. Grandes cambios en el lenguaje tan importantes como los que supusieron Java 5.

Después de algunos aplazamientos principalmente por implementar la modularización de la forma correcta sin que en un futuro suponga un problema ha sido publicada en septiembre de 2017 la versión de Java 9. La característica más llamativa es [la modularización de la plataforma con Java 9][blogbitix-263] que supone grandes mejoras como una mejor encapsulación de los paquetes, interfaces entre módulos bien definidas y dependencias explícitas que proporcionan optimización al usarse sólo los módulos que se necesitan, mayor seguridad al ser menor la superficie de ataque y configuración confiable al comprobar las dependencias al compilar o iniciarse la máquina virtual.

Pero Java 9 además de los módulos incorpora en la plataforma otros cambios destacables. Esta no es una lista exhaustiva pero si contiene muchas de ellas.

* [Oracle JDK 9 Documentation](https://docs.oracle.com/javase/9/index.html)
* [What’s New in Oracle JDK 9](https://docs.oracle.com/javase/9/whatsnew/toc.htm)
* [Guías descargables con documentación del JDK 9](https://docs.oracle.com/javase/9/javase-docs.htm)
* [Varios _screencasts_ sobre varias de las novedades de Java 9](https://www.oracle.com/java/java9-screencasts.html)
* [Java Magazine Sep/Oct 2017](http://www.javamagazine.mozaicreader.com/SeptOct2017)

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="264"
        image1="tabla-novedades-java-9.jpg" thumb1="tabla-novedades-java-9-thumb.jpg" title1="Tabla de novedades de Java 9"
        caption="Tabla de novedades de Java 9" >}}
</div>

<div class="media" style="text-align: center;">
    <img src="assets/images/posts/2017/264/duke-java-9.png" width="300" alt="Duke Java 9" title="Duke Java 9">
</div>

### Métodos factoría para colecciones

Aún Java no incorpora en el lenguaje una forma de definir como literales elementos tan comunes como listas, conjuntos o mapas. Como alternativa se proporcionan métodos factoría estáticos para crear este tipo de estructuras de datos usando métodos por defecto en sus respectivas interfaces. Además, estos métodos crean colecciones inmutables.

Aparte de definir este tipo de colecciones de una forma mucho más sencilla que hasta Java 8, las colecciones además son significativamente más eficientes. En el caso de Java 8 un _Set_ con dos elementos de capacidad 3 requiere un objeto _wrapper_ para la colección inmodificable, 1 _HashSet_, 1 _HashMap_, 1 Object[] de longitud 3, 2 dos nodos uno para cada elemento requiriendo en total unos 152 bytes. En el caso de Set creado con Java 9 requiere solo 20 bytes que comparados con los 152 es una mejora significativa más al tener en cuenta que este tipo de estructuras de datos son utilizadas de forma numerosa en cualquier programa. Aún estando hablando de bytes multiplicado por cada uso en algunos casos la reducción de memoria puede ser apreciable.

{{< gist picodotdev 543bdd4dad7311efbc80a298db328b73 "Collections.java" >}}

<div class="media media-video" style="text-align: center;">
    <iframe width="640" height="360" src="//players.brightcove.net/1460825906/VkKNQZg6x_default/index.html?videoId=5582422289001" allowfullscreen frameborder="0"></iframe>
</div>

### Mejoras en la clase _Optional_

Los métodos [or()](https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html#or-java.util.function.Supplier-) y [ifPresentOrElse()](https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html#ifPresentOrElse-java.util.function.Consumer-java.lang.Runnable-) así como [stream()](https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html#stream--) mejoran la experiencia de uso en esta clase que contiene o no un objeto. El método _or()_ en caso de no contener el _Optional_ un objeto permite proporcionar un _Optional_ alternativo. Los métodos _ifPresent()_ y _ifPresentOrElse()_ permiten realizar una acción con el objeto del opcional si está presente u otra acción con un valor vacío si no está presente. El método _stream()_ convierte el _Optional_ en un _stream_ de cero o un elemento.

### Mejoras en la API de _streams_

Los nuevos métodos de los _streams_ [dropWhile()](https://docs.oracle.com/javase/9/docs/api/java/util/stream/Stream.html#dropWhile-java.util.function.Predicate-), [takeWhile()](https://docs.oracle.com/javase/9/docs/api/java/util/stream/Stream.html#takeWhile-java.util.function.Predicate-) permiten descartar o tomar elementos del _stream_ mientras se comprueba una condición. El método [ofNullable()](https://docs.oracle.com/javase/9/docs/api/java/util/stream/Stream.html#ofNullable-T-) devuelve un _stream_ de un elemento o vacío dependiendo de si el objeto es _null_ o no. Los métodos [iterate()](https://docs.oracle.com/javase/9/docs/api/java/util/stream/Stream.html#iterate-T-java.util.function.Predicate-java.util.function.UnaryOperator-) permiten generar un secuencia de valores similar a un bucle _for_.

### REPL con jshell

Otra de las características destacables es la incorporación de [la herramienta JShell][blogbitix-265] para evaluar código siguiendo el patrón _Read-Evaluate-Print-Loop_ o REPL para hacer pruebas de código sin la necesidad de un IDE, una herramienta de construcción o toda la infraestructura de un proyecto. Esta herramienta es el comando _jshell_.

Se pueden introducir expresiones que son evaluadas y comandos precedidos por el caracter _/_. Soporta asistencia de código con la tecla tabulador.

* [Guía de usuario de jshell](https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm#JSHEL-GUID-630F27C8-1195-4989-9F6B-2C51D46F52C8)

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="264"
        image1="jshell.png" thumb1="jshell-thumb.png" title1="jshell"
        caption="jshell" >}}
</div>

<div class="media media-video" style="text-align: center;">
    <iframe width="640" height="360" src="//players.brightcove.net/1460825906/VkKNQZg6x_default/index.html?videoId=5582429016001" allowfullscreen frameborder="0"></iframe>
</div>

### jlink para generar _runtimes_ mínimos

Java 8 proporciona varios _runtimes_ reducidos con los _compact profiles_ que contienen algunas clases menos de las que incluye el JDK completo. Sin embargo, estos _runtimes_ están preconfigurados y las aplicaciones deben optar por el que ofrezca todas las clases que necesita.

En Java 9 se proporciona [jlink](http://openjdk.java.net/jeps/282) que un sustituto más capaz que los _compact profiles_. Permite generar _runtimes_ aprovechando la nueva modularidad del JDK con únicamente los módulos que necesite la aplicación.

Esto es especialmente útil para los contenedores de [Docker][docker] y los entornos _cloud_ ya que permite generar imágenes de contenedores con un tamaño significativamente menor. Por ejemplo, una imagen de Docker basada en la distribución [Alpine Linux][alpine-linux] con el JDK completo ocupa unos 360 MiB, con _jlink_ si una aplicación solo necesita del módulo _java.base_ se puede generar un _runtime_ con únicamente ese módulo, con este _runtime_ adaptado la imagen del contenedor tiene un tamaño mucho menor, en este caso de únicamente de unos 40 MiB.

<div class="media media-video" style="text-align: center;">
    <iframe width="640" height="360" src="//players.brightcove.net/1460825906/VkKNQZg6x_default/index.html?videoId=5582437011001" allowfullscreen frameborder="0"></iframe>
</div>

### Concurrencia

Se añade un _framework_ con un conjunto de clases para programación reactiva de publicación-subscripción con las clases [Flow](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.html), [Flow.Processor](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Processor.html), [Flow.Subscriber](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscriber.html), [Flow.Publisher](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Processor.html) y [Flow.Subscription](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Processor.html). La clase _Subsription_ posee dos métodos: [cancel()](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscription.html#cancel--) y [request()](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscription.html#request-long-) para dejar de recibir mensajes y solicitar recibir _n_ mensajes en la siguientes llamadas de [onNext​()](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscriber.html#onNext-T-).

El método [copy()](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/CompletableFuture.html#copy--) de la clase [CompletableFuture](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/CompletableFuture.html) permite obtener un copia completándose con el mismo valor cuando la operación se completa normalmente.

### Variable Handles

Una de las justificaciones de la modularidad es el uso que hasta ahora se le ha dado a la famosa clase interna del JDK _sun.misc.Unsafe_. Para proporcionar parte de la funcionalidad de esta clase en una API pública se introduce la clase [VarHandle](https://docs.oracle.com/javase/9/docs/api/java/lang/invoke/VarHandle.html) para referenciar a variables estáticas y no estáticas así como a _arrays_. Estas instancias se obtienen mediante la clase [MethodHandle.Lookup](https://docs.oracle.com/javase/9/docs/api/java/lang/invoke/MethodHandles.Lookup.html). Una vez obtenida una instancia de _VarHandle_ se pueden realizar operaciones de bajo nivel sobre la variable que referencia como operaciones atómicas comparar y establecer pero sin la sobrecarga de rendimiento con las clases equivalentes del paquete [java.util.concurrent.atomic](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/atomic/package-summary.html).

Para la mayoría de los programadores esto no les afectará directamente pero sí será una mejora para los desarrolladores de librerías importantes muy populares que si usan la clase _Unsafe_ y deberían aprovechar estas nuevas capacidades para dejar de usar la clase interna del JDK en la medida de lo posible.

### Actualizaciones en la API para procesos

Ahora es posible obtener el identificador del proceso o _pid_ con el método [pid()](https://docs.oracle.com/javase/9/docs/api/java/lang/Process.html#pid--) y los procesos hijos y descendientes con los métodos [children​()](https://docs.oracle.com/javase/9/docs/api/java/lang/Process.html#children--) y [descendants​()](https://docs.oracle.com/javase/9/docs/api/java/lang/Process.html#descendants--) respectivamente.

### StackWalker

La clase [StackWalker](https://docs.oracle.com/javase/9/docs/api/java/lang/StackWalker.html) permite obtener un _stream_ secuencial de [StackWalker.StackFrames](https://docs.oracle.com/javase/9/docs/api/java/lang/StackWalker.StackFrame.html) del _thread_ actual para procesar la pila de llamadas o _stacktrace_.

{{< gist picodotdev 543bdd4dad7311efbc80a298db328b73 "StackWalker.java" >}}

### Strings compactos

Internamente los la clase [String](https://docs.oracle.com/javase/9/docs/api/java/lang/String.html) contiene un array de _char_, cada _char_ se representa en formato con la codificación UTF-8 ocupando 16 bits o 2 bytes por cada caracter. Para cadenas en aquellos lenguajes como inglés los caracteres pueden ser representados usando un único _byte_.

Una buena parte de la memoria ocupada en la JVM por cualquier aplicación es debido a las cadenas de modo que tiene sentido compactar aquellas cadenas en las que sea posible representándolas con un único _byte_ por caracter.

Lo mejor de todo es que esta optimización será transparente para los programadores y para las aplicaciones proporcionando una reducción en el uso de la memoria y aumento del rendimiento, también en el recolector de basura.

* [Compact Strings In Java 9](https://www.javagists.com/compact-strings-java-9)

### Recolector de basura G1 por defecto

Se cambia el recolector de basura por defecto al llamado _G1_ optimizado para una balance adecuado entre alto rendimiento y baja latencia. En los siguientes artículos se explica de forma más detallada. Al igual que los _string_ compactos para la mayoría de los programadores será un cambio transparente que no tenga repercusión en la forma de programar las aplicaciones.

* [Getting Started with the G1 Garbage Collector](http://www.oracle.com/technetwork/tutorials/tutorials-1876574.html)
* [Case for Defaulting to G1 Garbage Collector in Java 9](https://www.infoq.com/articles/Make-G1-Default-Garbage-Collector-in-Java-9)

<div class="media media-video" style="text-align: center;">
    <iframe width="640" height="360" src="//players.brightcove.net/1460825906/VkKNQZg6x_default/index.html?videoId=5582428191001" allowfullscreen frameborder="0"></iframe>
</div>

### Identificador para variables _

El identificador _\__ queda reservado para en un futuro usarlo en parámetros a los que no se les dé un uso y no sean relevantes como por ejemplo en las _lambdas_. También se evaluará en el [proyecto Amber](http://openjdk.java.net/projects/amber/) usar este identificador para tipos diamante parciales como _Foo\<String, \_\>_ o en referencias a métodos _foo.\<String, \_\>bar()_.

* [Project Amber: The Future of Java Exposed](http://blog.takipi.com/project-amber-the-future-of-java-exposed/)

### Métodos privados en interfaces

Ahora se pueden crear métodos privados en interfaces como utilidad a las implementaciones de los métodos por defecto.

### Mejor _try-with-resource_

Ahora las variables finales o efectivamente finales pueden ser colocadas en los bloques _try-with-resource_ simplificando  algunos usos.

{{< gist picodotdev 543bdd4dad7311efbc80a298db328b73 "TryWithResources.java" >}}

### Javadoc

Ahora la documentación Javadoc se genera con marcado de HTML 5 e incluye un cuadro de búsqueda para encontrar más fácilmente tipos y métodos.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="264"
        image1="javadoc.png" thumb1="javadoc-thumb.png" title1="Javadoc"
        caption="Javadoc" >}}
</div>

### Archivos Jar multiversión

Los desarrolladores de librerías para dar soporte a varias versiones de Java debían optar entre generar un artefacto para cada versión o un único archivo _jar_ limitándose a usar la mínima versión soportada y sin aprovechar las nuevas capacidades de siguientes versiones. Esto es un impedimento para el uso de nuevas versiones.

Con Java 9 se puede generar un único archivo _jar_ con algunas clases para una o varias versiones de Java. Por ejemplo, en un archivo _jar_ con las clases _A_, _B_, _C_ y _D_ compatibles con Java 6 el desarrollador ahora puede decidir que para la versión 9 la clase _A_ y _B_ sean unas optimizadas para esta versión. Esto se consigue con una estructura específica de directorios en el archivo _jar_, ubicándose la clase optimizada para Java 9 _A_ en _META-INF/versions/9/A.class_ y para Java 10 en _META-INF/versions/10/A.class_.

{{< gist picodotdev 543bdd4dad7311efbc80a298db328b73 "Multirelease.out" >}}

* [JEP 238: Multi-Release JAR Files](http://openjdk.java.net/jeps/238)

### Nuevo modelo de publicación

A partir de la publicación de Java 9 se cambia el modelo de publicación de nuevas versiones optando por una basada en calendario en vez de una por características a incluir. El caso de versiones que han de incluir las características previstas ocasiona el problema que si una se retrasa provoca un retraso en la versión. Con el modelo basado en fechas fijas preestablecidas la versión se liberará con aquellas características que estén listas en la fecha planificada de publicación sin ser retrasadas por aquellas que no.

Se ha optado por producir una nueva versión cada seis meses con el [OpenJDK][openjdk] y [licencia GPL][gpl] para satisfacer las necesidades de los desarrolladores y una versión con soporte de largo plazo cada tres años para satisfacer las necesidades de tiempo de soporte prolongado de las empresas, _Oracle JDK_.

<div class="media media-video" style="text-align: center;">
    <iframe width="640" height="360" src="//players.brightcove.net/1460825906/VkKNQZg6x_default/index.html?videoId=5582439790001" allowfullscreen frameborder="0"></iframe>
</div>

Para finalizar un par de libros, [Java 9 Revealed](http://amzn.to/2g0qu6t) y [Java 9 Modularity Revealed](http://amzn.to/2fY3wwT) que explican detalladamente las novedades de la modularidad, las novedades incluídas en este artículo y algunas otras más.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=1484225910&linkId=9c7874501bb32fa3318e285022e0207a"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=1484227123&linkId=59d96101c25f9c16c4427b8ee9daef1a"></iframe>
</div>

Y unos buenos vídeos sobre las nuevas características de Java 9, uno en español y otro en inglés.

<div class="media media-video" style="text-align: center;">
	<iframe width="640" height="360" src="https://www.youtube.com/embed/CkmB86pCV6A" frameborder="0" allowfullscreen></iframe>
    <iframe width="640" height="360" src="https://www.youtube.com/embed/9EiLRD95zDg" frameborder="0" allowfullscreen></iframe>
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [The best Java 9 Language and API improvements](https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/)
* [New Java 9 features at a glance](https://jaxenter.com/new-features-in-java-9-137344.html)
* [Modern Java Recipes](http://amzn.to/2ylsidI)
* [Try-With-Resource Enhancements in Java 9](https://dzone.com/articles/try-with-resources-enhancement-in-java-9)
* [Revista Java Magazine Jul/Aug 2017](http://www.javamagazine.mozaicreader.com/JulyAug2017)
* [Java 9 and IntelliJ IDEA](https://dzone.com/articles/java-9-and-intellij-idea)
* [Java 9 más allá de la modularidad](https://www.autentia.com/2017/09/13/java-9-mas-alla-de-la-modularidad/)
* [JShell la herramienta REPL incorporada en Java 9][blogbitix-265]
{{% /reference %}}

{{% /post %}}
