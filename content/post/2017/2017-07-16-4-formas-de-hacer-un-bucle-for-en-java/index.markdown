---
pid: 247
title: "4 formas de hacer un bucle for en Java"
url: "/2017/07/4-formas-de-hacer-un-bucle-for-en-java/"
date: 2017-07-16T12:30:00+02:00
updated: 2017-07-19T22:30:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Hasta Java 5 para hacer un bucle desde 0 a N elementos había que usar una variable para mantener un contador, hacer una comparación para comprobar si se había llegado al límite e incrementar la variable en la siguiente ejecución. El código era bastante verboso y dado que los bucles son una construcción básica de cualquier lenguaje de programación es empleada numerosas veces en cualquier algoritmo. Antes de Java 5 un bucle _for_ de 0 a 5 y de una colección se realizaba de la siguiente manera:

{{< code file="For.java" language="Java" options="" >}}
{{< code file="Iterator.java" language="Java" options="" >}}

En Java 5 el bucle _for_ se enriqueció notablemente, con el _foreach_ se puede recorrer una colección y cualquier objeto que implemente la interfaz [Iterable](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html). Con el bucle _foreach_ una [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) se recorre de la siguiente manera.

{{< code file="Foreach.java" language="Java" options="" >}}

Pero esto es para las colecciones si se quiere hacer un bucle un número fijo de veces como en el primer caso de 0 a 5 conociendo que para usar el _foreach_ basta que le indiquemos un objeto que implemente la interfaz _Iterable_ podemos usar la siguiente expresión y su implementación que tiene la ventaja de no tener que incluir la expresión de comparación y el incremento de la variable, la clase _Counter_ implementa la interfaz _Iterable_ y devuelve un _Iterator_ sobre los valores del rango indicado:

{{< code file="CounterIterable.java" language="Java" options="" >}}
{{< code file="Counter.java" language="Java" options="" >}}

En Java 8 con la introducción de los [Stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) y de [IntStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html) podemos usar el método [range](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html#range-int-int-) y [rangeClosed](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html#rangeClosed-int-int-) para obtener un _Stream_ de enteros y hacer un bucle con un comportamiento similar a los anteriores.

{{< code file="Stream.java" language="Java" options="" >}}

Los _Stream_ de Java 8 están muy bien para simplificar algunas operaciones complejas pero para un bucle _for_ sencillo tiene sus inconvenientes como ofuscar significativamente el _stacktrace_ en caso de producirse alguna excepción. Se puede usar cualquier opción pero la primera con el tradicional bucle _for_ sea la menos recomendable teniendo a nuestra disposición la clase _Counter_ con Java 5 o los _Stream_ y _lambdas_ con Java 8.

El siguiente programa muestra las cuatro opciones, su salida en la consola sería el siguiente:

{{< code file="Main.java" language="Java" options="" >}}

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="247"
        image1="bucles-java.png" thumb1="bucles-java-thumb.png" title1="4 formas de hacer un bucle en Java"
        caption="4 formas de hacer un bucle en Java" >}}
</div>

{{< sourcecode git="blog-ejemplos/tree/master/JavaForeach" command="./gradlew run" >}}

{{% /post %}}
