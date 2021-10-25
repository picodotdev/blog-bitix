---
pid: 247
type: "post"
title: "4 formas de hacer un bucle for en Java"
url: "/2017/07/4-formas-de-hacer-un-bucle-for-en-java/"
date: 2017-07-16T12:30:00+02:00
updated: 2020-06-02T17:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "La forma habitual en Java de hacer un bucle es con una sentencia _for_ o _while_ pero con el añadido de los iteradores en Java 5 no hace falta tener una variable para conservar el índice del bucle. Ya en Java 8 se han añadido los _streams_ que ofrecen otras nuevas formas de iterar sobre los elementos de una colección en este último caso con técnicas propias de lenguajes funcionales."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Hasta Java 5 para hacer un bucle desde 0 a N elementos había que usar una variable para mantener un contador, hacer una comparación para comprobar si se había llegado al límite e incrementar la variable en la siguiente ejecución. El código era bastante verboso y dado que los bucles son una construcción básica de cualquier lenguaje de programación es empleada numerosas veces en cualquier algoritmo.

Algunos de estos ejemplos de bucles son utilizables a partir de Java 5, en versiones más recientes se han añadido muchas otras novedades como [las _lambdas_, _streams_, métodos en interfaces y nueva API para fechas][blogbitix-17] en Java 8, [la modularidad, _try-with-resource_ mejorado, jlink o un nuevo modelo de publicación][blogbitix-263]  en Java 9, [inferencia de tipos para variables locales][blogbitix-306] en Java 10, [un cliente HTTP][blogbitix-350] en Java 11 y otras [novedades en el lenguaje y la plataforma Java][blogbitix-serie-java-platform].

Los bucles son uno de [los tipos de sentencias y estructuras de control de flujo básicas de Java][blogbitix-494] que permiten repetir la ejecución de un bloque de sentencias mientras se cumpla la expresión de condición de repetición, en cada iteración del bucle se evalúa la expresión de condición y en el momento que no se cumple se continua con la siguiente sentencia del programa.

{{< tableofcontents >}}

### Bucle for

Antes de Java 5 un bucle _for_ de 0 a 5 y de una colección se realizaba de la siguiente manera manteniendo una variable normalmente de nombre _i_ que hace de contador y _j_ si el bucle _for_ está anidado en otro. Además de la variable de contador requiere establecer la condición que permita salir del bucle cuando se llegue al final de la iteración, la condición es muy importante para no crear un bucle infinito.

{{< code file="For.java" language="java" options="" >}}
{{< code file="Iterator.java" language="java" options="" >}}

### Bucle foreach

En Java 5 el bucle _for_ se enriqueció notablemente, el bucle _foreach_ es un bucle _for_ mejorado con el que se puede recorrer una colección y cualquier objeto que implemente la interfaz [Iterable](javadoc8:java/lang/Iterable.html). Este bucle tiene la ventaja de que no hay que mantener una variable que haga de contador ni requiere establecer una condición para comprobar si se ha llegado al final de la iteración, esto evita la posibilidad de crear un bucle infinito. Con el bucle _foreach_ una [Collection](javadoc8:java/util/Collection.html) se recorre de la siguiente manera.

{{< code file="Foreach.java" language="java" options="" >}}

### Bucle con Iterable

Pero el _forearch_ es para las colecciones si se quiere hacer un bucle de un número fijo de iteraciones como en el primer caso, de 0 a 5, conociendo que para usar el _foreach_ basta que le indiquemos un objeto que implemente la interfaz _Iterable_ podemos usar la siguiente expresión y su implementación que tiene la ventaja de no tener que incluir el valor inicial del contador, la expresión de condición y el incremento o decremento de la variable. La clase _Counter_ implementa la interfaz _Iterable_ y devuelve un _Iterator_ sobre los valores del rango indicado.

{{< code file="CounterIterable.java" language="java" options="" >}}
{{< code file="Counter.java" language="java" options="" >}}

### Bucle con streams

En Java 8 con la introducción de los [Stream](javadoc8:java/util/stream/Stream.html) y de [IntStream](javadoc8:java/util/stream/IntStream.html) podemos usar el método [range](javadoc8:java/util/stream/IntStream.html#range-int-int-) y [rangeClosed](javadoc8:java/util/stream/IntStream.html#rangeClosed-int-int-) para obtener un _Stream_ de enteros y hacer un bucle con un comportamiento similar a los anteriores.

{{< code file="Stream.java" language="java" options="" >}}

Los _Stream_ de Java 8 están muy bien para simplificar algunas operaciones complejas pero para un bucle _for_ sencillo tiene sus inconvenientes como ofuscar significativamente el _stacktrace_ en caso de producirse alguna excepción. Se puede usar cualquier opción pero la primera con el tradicional bucle _for_ sea la menos recomendable teniendo a nuestra disposición la clase _Counter_ con Java 5 o los _Stream_ y _lambdas_ con Java 8.

### Cómo salir de un bucle inmediatamente o continuar con la siguiente iteración

Cuando se hace un bucle hay que definir una condición que es evaluada en cada iteración del bucle, una vez se cumple la condición el bucle termina y se continua con la siguiente sentencia del programa.

En ocasiones sin embargo se desea terminar un bucle inmediatamente sin evaluar la condición de fin de bucle. Java posee la palabra reservada _break_ que dentro de un bucle permite finalizarlo inmediatamente. El siguiente bucle imprime los números 0, 1 y 2, al evaluar la condición _if_ como cierta para el valor 3 de la variable contador _i_ se ejecuta la sentencia _break_ y se termina el bucle.

{{< code file="For-break.java" language="java" options="" >}}

Al mimo tiempo en otras ocasiones se desea no terminar con el bucle sino continuar con la siguiente iteración del mismo, para ello se utiliza la palabra reservada _continue_. En este caso el bucle imprime los números 0, 1, 2 y 4, al evaluar la condición _if_ como cierta para el valor 3 de la variable contador _i_ se ejecuta la sentencia _continue_ y se continua el bucle en la siguiente iteración.

{{< code file="For-continue.java" language="java" options="" >}}

### Ejemplo con los distintos tipos de bucle

El siguiente programa muestra las cuatro opciones, su salida en la consola sería el siguiente:

{{< code file="Main.java" language="java" options="" >}}

Para cualquiera de las formas de hacer el bucle _for_ el comportamiento es el mismo, iterar un número finito de veces o sobre los elementos de una colección. Elegir cual usar entre los diferentes tipos de bucles depende del caso y de las preferencias personales pero también considerando la legibilidad y expresividad del código fuente.

{{< code file="System.out" language="plaintext" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/JavaForeach" command="./gradlew run" >}}

{{% /post %}}
