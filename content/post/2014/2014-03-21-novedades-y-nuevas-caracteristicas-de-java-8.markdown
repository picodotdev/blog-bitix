---
pid: 17
title: "Novedades y nuevas características de Java 8"
url: "/2014/03/novedades-y-nuevas-caracteristicas-de-java-8/"
date: 2014-03-21T16:03:27+01:00
updated: 2016-03-12T12:00:00+01:00
sharing: true
comments: true
tags: ["java", "programacion", "software", "planeta-linux", "planeta-codigo"]
summary: "La octava versión de Java añade numerosas novedades importantes al lenguaje. Algunas de las más destacadas son las expresiones lambdas y los streams que dotan al lenguaje de características de programación funcional pero también lo hacen en muchas construcciones de código comunes más expresivo, menos extenso y más legible. Pero hay otras novedades relevantes que detallo en el artículo como los métodos por defecto en interfaces o la nueva API para fechas."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="java.png" title="Java" >}}

El 18 de marzo de 2014 se publicó la nueva versión de la plataforma [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html), Java 7 fue liberada el en julio de 2011. Java 8 incorporará varias novedades siguiendo la tendencia de otros lenguajes como la incorporación de lambdas o closures o una demandada nueva API para el manejo de fechas entre otras muchas características más. La [lista completa de novedades](http://openjdk.java.net/projects/jdk8/features) es bastante amplia, a continuación pondré lo que me ha parecido más destacable:

* Se añaden las expresiones lambda o closures y se adapta la API para usarlas en los sitios que se pueda.
* Se definen nuevos perfiles compactos para dispositivos que no necesitan toda la API Java, esto es una alternativa a la modularización. Inicialmente la modularización era una característica que estaba planificada para Java 8 pero finalmente se ha aplazado a posteriores versiones.
* Incluido nuevo [motor de Javascript Nashorn](http://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/index.html) que implementa ECMAscript 5.1 y es más rápido que su predecesor Rhino.
* Eliminación de espacio de memoria Permanent Generation (PermGen) que pasa a llamarse Metaspace. Su tamaño
será dinámico pero no evitará fugas de memoria.
* Soporte para la creación de una máquina virtual menor a 3 MB.
* Acceso mediante reflection a los nombres de los parámetros. Mejorará la legibilidad del código que use reflection y permitirá mejorar el soporte en los IDE.
* Ordenación en java.util.Arrays de forma paralela.
* Operaciones masivas sobre colecciones tales como filter/map/reduce (streams) de forma serializada o paralela.
* Soporte de forma estándar para la codificación y decodificación Base64.
* Nueva API para Date y Time.
* Varias mejoras de seguridad.
* Se incorporan en la máquina virtual Java HotSpot características que estaban en JRockit, convergiendo ambas máquinas virtuales. Se incorpora Mission Control.
* Mejoras en JDBC la base para el acceso en bases de datos en Java.
* [Otras novedades](http://openjdk.java.net/projects/jdk8/features).

Java 8 representa una evolución notable en este lenguaje de programación al mismo nivel o más del que supuso la versión Java 5. Aunque tengas varios años de experiencia quizá aún no has tenido oportunidad de usar todas las novedades que se han incorporado en cada versión en proyectos reales, probablemente porque los caminos de migración de las empresas son lentos y muchas siguen usando versiones antiguas en sus aplicaciones. Para conocer y aprovechar todas estas novedades de Java recomiendo los siguientes libros que son de lo mejor que he encontrado como documentación, el primer libro es <a href="http://www.amazon.es/gp/product/0131872486/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=0131872486&linkCode=as2&tag=blobit-21">Thinking in Java</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=0131872486" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> que nos introduce en este lenguaje de programación desde el inicio, aunque ya conozcas o uses Java el libro <a href="http://www.amazon.es/gp/product/B00B8V09HY/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00B8V09HY&linkCode=as2&tag=blobit-21">Effective Java</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00B8V09HY" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> contiene gran cantidad de información y consejos para usar Java de una forma más efectiva haciendo un repaso de las características del lenguaje que aunque no esté actualizado con las novedades que introduce Java 8 casi todo sigue siendo aplicable, el último libro es <a href="http://www.amazon.es/gp/product/1617291994/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=1617291994&linkCode=as2&tag=blobit-21">Java 8 in Action</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=1617291994" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> que explica en mucho más detalle que lo hecho en este artículo cada una de las nuevas características de Java 8 y complementa el libro Effective Java pero aprovechando las novedades introducidas en el lenguaje.

<div class="media-amazon" style="text-align: center;">
    <iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=0131872486&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
    <iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00B8V09HY&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
    <iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=1617291994&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
</div>

A continuación algunas características de Java explicadas con un poco más de detalle.

### Streams

Los streams no son un nuevo tipo de colección son una nueva forma de recorrer las colecciones distinta a los [Iterator](http://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html). La ventaja de los streams es que pueden procesarse de forma serializada o paralela y proporcionan un estilo de operaciones más funcionales. Un flujo consiste un una fuente (una colección), varias operaciones intermedias (de filtrado o transformación) y una operación final que produce un resultado (suma, cuenta...). Los streams son lazy de modo que las operaciones solo se realizan cuando se llama a la operación final, también son eficientes no necesitando en algunos casos procesar todos los elementos del stream para devolver el resultado final.

Tradicionalmente en la API de colecciones la iteración sobre los elementos debíamos proporcionarla de forma externa. Con Java 8 podemos expresarla de forma interna, de la siguiente manera.

{{< gist picodotdev 9689477 "Stream.java" >}}

### Lambda

Esta es una de las principales novedades y que más se estaba echando de menos en Java de otros lenguajes como [Groovy](http://groovy.codehaus.org/) o [Python](https://www.python.org/). Las expresiones lambda son funciones que no está asociadas a un determinado nombre y que pueden pasarse como argumento a otras funciones. Tienen el siguiente aspecto:

{{< gist picodotdev 9689477 "Lambda.java" >}}

El uso de expresiones lambdas junto con el stream API proporciona a Java 8 características de programación funcional, pero sobre todo hace el código más sencillo, menos extenso, más expresivo y más legible. En las expresiones lambda de Java podemos seguir aprovechándonos de la compilación estática y del tipado fuerte.

### Referencias de métodos

Todo el código desarrollado previamente a Java 8 no hace uso de las lambdas, pero con las [referencias a métodos](http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html) podemos usar esos métodos ya implementados como si se tratasen de funciones lambdas. Hay diferentes formas de hacer referencias a métodos:

* A métodos estáticos
* A un método de una instancia concreta
* A un método de instancia de una instancia arbitraria de un tipo
* A un constructor

{{< gist picodotdev 9689477 "ReferenciaMetodos.java" >}}

### Interfaces funcionales

Una interfaz funcional es aquella que solo tiene un método abstracto (sin implementación). Algunos ejemplos de interfaces funcionales son [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html), [ActionListener](https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html), [Comparator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html) y [Callable](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html). Para definir una interfaz funcional se puede usar la anotación [@FunctionalInterface](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html) y pueden representarse con una expresión lambda. En el siguiente ejemplo puede apreciarse que con las interfaces funcionales y las lambdas podemos hacer lo mismo de forma más clara, menos verbosa y con código más legible.

{{< gist picodotdev 9689477 "InterfazFuncional-1.java" >}}

En Java 8 podemos hacer:

{{< gist picodotdev 9689477 "InterfazFuncional-2.java" >}}

Java 8 icorpora varias interfaces funcionales que puede ser usadas en expresiones lambda, entre ellas están:

* [Predicate](http://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html): función que retorna un booleano dado un argumento.
* [Function](http://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html): función que retorna un valor dado un argumento.
* [Supplier](http://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html): producen un tipo, al contrario que las funciones no tienen argumento
* [Consumer](http://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html): representa una operación a realizarse en un argumento.

### Métodos por defecto en interfaces

Hasta ahora las interfaces en Java solo podían definir métodos pero no sus implementaciones. El problema con las interfaces es que cuando se modifican se rompen todas las clases que las usan. Esto se ha resuelto de tal forma que se puedan añadir nuevos métodos con implementación a las interfaces y ha sido necesario para incorporar las lambdas a interfaces existentes como List. En Java 8 las interfaces podrán incorporar implementaciones para algunos de sus métodos, teniendo así algo parecido a herencia múltiple.

{{< gist picodotdev 9689477 "MetodosDefault.java" >}}

### Métodos estáticos en interfaces

Además de definir métodos por defecto en las interfaces a partir de ahora podemos definir métodos estáticos. Definiendo métodos estáticos en las interfaces evitaremos tener que crear clases de utilidad. Podremos incluir en un mismo tipo (la interfaz) todos los métodos relacionados.

{{< gist picodotdev 9689477 "MetodosStatic.java" >}}

### Mejoras en la programación asíncrona

Los procesadores actuales están aumentando su capacidad de proceso más a base de concurrencia proporcionando más núcleos que a base de hacerlos más rápidos en Ghz o [instrucciones por ciclo](https://es.wikipedia.org/wiki/Instrucciones_por_ciclo), los lenguajes de programación tienen que adaptarse para facilitar su aprovechamiendo de forma fácil. Desde las primeras versiones de Java ya se incluía soporte para trabajar con hilos o threads sin embargo su programación es propensa a errores y difícil de depurar. Java 7 con el nuevo soporte conocido como [Fork/Join](https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html) mejoraba la situación.

Por otra parte para maximizar el aprovechamiento de los recursos disponibles cuando surgen bloqueos de entrada/salida como acceso a disco o comunicación por red surge la programación asíncrona. Para ello Java 8 mejora el soporte existente desde Java 7 con la clase [Future](http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Future.html) que permite recoger el resultado de una operación mientras se realiza otra al mismo tiempo, en Java 8 se proporciona la clase [CompletableFuture](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html) que implementa la interfaz Future. La clase CompletableFuture soporta el uso de lambdas, hace más fácil la programación concurrente y programación asíncrona que el Fork/Join y el Future respectivamente permitiendo igualmente ejecutar tareas de forma concurrente, combinar el resultado de dos tareas que se ejecutan al mismo tiempo, realizar otra tarea al terminar una prevía o por supuesto esperar a que todas las tareas concurrentes finalicen.

### Anotaciones en cualquier uso de tipos

Con la introducción de anotaciones en Java 5 podemos usar anotaciones en la declaración de los tipos (definición de clases, interfaces, propiedades, métodos, ...). Desde ahora podremos usar las anotaciones en el uso de cualquier tipo como por ejemplo expresiones new, casts, cláusulas implements y cláusulas throws.

### Java Time

Trabajar con fechas, horas y diferentes zonas horarias en Java tradicionalmente ha sido molesto, para tratar de mejorar la situación podíamos usar la librería JodaTime. Finalmente, después de mucho tiempo esperando a que se hiciera se va a proporcionar una mejor [API en el JDK 8 para trabajar con fechas, horas y zonas horarias](https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html).

<div class="media" style="text-align: center;">
	<a href="assets/images/posts/17/componentes-jpse8.png" title="Componentes Java Platform Standard Edition 8" data-gallery><img src="assets/images/posts/17/componentes-jpse8-thumb.png"></a>
</div>

### Futuro Java 9

Viendo el pasado reciente de Java hablar del futuro puede ser precipitado, quizá la característica más comentada sea la modularización con el proyecto conocido como [Jigsaw](http://openjdk.java.net/projects/jigsaw/), pero ha sido abandonada para Java 8 según tengo entendido por ser difícil incorporarla manteniendo la compatibilidad hacia atrás, por el momento dispondremos de una solución intermedia con los compact profiles.

Otro de los puntos donde parece que se pondrá énfasis es en el soporte para entornos en la nube que son en estos momentos la tendencia hacia la que evolucionan las aplicaciones.

### Pasado en Java 5, 6 y 7

Las versiones de Java 5, 6 y 7 también incluyeron varias novedades y dado que en el ámbito empresarial la tecnología se adopta de forma lenta es posible que mucha gente (incluido yo mismo) aún desconozca cuales eran algunas de las principales novedades y características de versiones anteriores.

#### Java 7

* Project coin
* Strings en switchs
* Gestión automática de recursos en sentencias try-catch
* Multicatch
* Rethrow de excepciones más precisos
* Operador diamante <>
* Literales binarios
* Guiones bajos en literales numéricos
* Fork/Join y utilidades de concurrencia
* Mejoras en trabajo con archivos y carpetas

Más en [Java 7 New Features and Enhancements](http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html) y [Java 7 a look back](http://www.javacodegeeks.com/2013/10/java7-a-look-back.html)

#### Java 6

* Lenguajes de scripting
* API para el compilador
* Nuevas anotaciones
* Nueva API para XML (StaX)

Más en [Java 6 New Features and Enhancements](http://www.oracle.com/technetwork/java/javase/features-141434.html) y [Introduction to java 6 newfeatures](http://www.javabeat.net/introduction-to-java-6-0-new-features-part-i/)

#### Java 5

* Generics
* Bucle for mejorado
* Autoboxing/unboxing
* Enums
* Varargs
* Static import
* Anotaciones

Más en [Java 5 New Features and Enhancements](http://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html)

### Presentación

Finalmente, una presentación muy interesante en forma de [vídeo de Youtube](https://www.youtube.com/watch?v=FTfAP29TjUk) en español y la [presentación de la que se habla en formato pdf](http://www.javahispano.org/storage/documentacion/2013-JUG-Madrid.pdf) que fue publicada en [JavaHispano](http://www.javahispano.org/portada/2014/3/10/video-y-presentacion-de-la-charla-sobre-java-8.html), en él se habla de muchas de las novedades y de algunas futuras en Java 9.

<div class="video-post" style="text-align: center;">
	<iframe width="640" height="360" src="https://www.youtube.com/embed/FTfAP29TjUk?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<hr>

En los [tutoriales de Java](http://docs.oracle.com/javase/tutorial/) hay algunos de estos puntos más ampliados y algunos apartados no comentados en esta entrada. Otros artículos muy completos son [Java 8 Tutorial](http://winterbe.com/posts/2014/03/16/java-8-tutorial/) y [Everything about Java 8](http://www.techempower.com/blog/2013/03/26/everything-about-java-8/), en el apartado de referencia al final de la entrada hay algún enlace más digno de lectura.

Java es usado ampliamente en entornos empresariales ya sean públicos o privados, pequeños o grandes que con las [especificaciones de Java EE 7][blogbitix-131] proporcionan a las aplicaciones un conjunto de funcionalidades estadarizadas con las que tendremos posibilidad de elegir la implementación que mejor consideremos no encadenándonos a un determinado vendedor.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [JDK 8](http://openjdk.java.net/projects/jdk8/)
* [Java 8](http://www.javacodegeeks.com/2013/10/java8.html)
* [Java 8 Tutorial](http://winterbe.com/posts/2014/03/16/java-8-tutorial/)
* [Java 8 Explained Default Methods](http://zeroturnaround.com/rebellabs/java-8-explained-default-methods/)
* [Introduction to Java lambdas](http://www.javacodegeeks.com/2013/10/introduction-to-java-lambdas.html)
* [Java 8 revealed, lambdas, default methods and bulk data operations](http://zeroturnaround.com/rebellabs/java-8-revealed-lambdas-default-methods-and-bulk-data-operations/)
* [Lambdas coming to a Java 8 near you](http://www.javacodegeeks.com/2013/07/lambdas-coming-to-a-java-8-near-you.html)
* [Java 8 default methods](http://viralpatel.net/blogs/java-8-default-methods-tutorial/)
* [Introducción y nuevas características de Java EE 7][blogbitix-131]
* [Novedades relevantes Java 8](http://unpocodejava.wordpress.com/2014/01/22/novedades-relevantes-java-8/)
* [The optional type API](http://www.javacodegeeks.com/2014/03/the-optional-type-api.html)
* [Everything about Java 8](http://www.techempower.com/blog/2013/03/26/everything-about-java-8/)
* [8 new features for java 8](http://www.javacodegeeks.com/2014/03/8-new-features-for-java-8.html)
* [Happy 8th birthday Java](http://www.javacodegeeks.com/2014/03/happy-8th-birthday-java.html)
* [Compiling lambda expressions scala vs Java 8](http://www.javacodegeeks.com/2014/01/compiling-lambda-expressions-scala-vs-java-8.html)
* [Java 8 PermGen to metaspace](http://java.dzone.com/articles/java-8-permgen-metaspace)
{{% /reference %}}

{{% /post %}}
