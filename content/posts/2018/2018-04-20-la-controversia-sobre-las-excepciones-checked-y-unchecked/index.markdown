---
pid: 313
type: "post"
title: "La controversia sobre las excepciones checked y unchecked"
url: "/2018/04/la-controversia-sobre-las-excepciones-checked-y-unchecked/"
aliases: ["/2018/04/la-controversia-sobre-las-excepciones-checked-y-uncheked/"]
date: 2018-04-20T17:00:00+02:00
updated: 2018-04-20T17:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las excepciones son una forma de gestionar las condiciones de error que se dan en los programas. En el lenguaje C se utiliza el valor de retorno de la función para determinar la condición de error que se ha producido, el problema es que comprobar el valor de retorno puede ignorarse y la gestión de errores está mezclada con la tarea del programa.

El lenguaje Java utiliza un [mecanismo de excepciones][blogbitix-270], las excepciones son objetos que se lanzan cuando se produce una condición de error. Todas las excepciones en Java heredan de [Throwable](javadoc10:java/lang/Throwable.html) subdividiéndose en [Error](javadoc10:java/lang/Error.html) y [Exception](javadoc10:java/lang/Exception.html), las primeras son condiciones de error del sistema y las segundas condiciones de error del programa. A su vez las _Exception_ pueden ser _checked_ si heredan de esta y son aquellas que el compilador fuerza a que sean capturadas no pudiendo ignorarse, han de capturarse en una construcción _try catch_ o declarar que el método puede lanzar la excepción no capturada. Las excepciones _unchecked_ heredan de [RuntimeException](javadoc10:java/lang/RuntimeException.html) que heredan a su vez de _Exception_ pero tienen la particularidad de que no es necesario capturarlas ni declararlas como que se pueden lanzar debido a que se consideran condiciones de error en la programación como un acceso a un _array_ fuera de rango que produce un [ArrayIndexOutOfBounds](javadoc10:java/lang/ArrayIndexOutOfBoundsException.html), el conocido [NullPointerException](javadoc10:java/lang/NullPointerException.html) cuando se utiliza una referencia nula, otro es [ArithmeticException](javadoc10:java/lang/ArithmeticException.html) que se produce al dividir por 0.

Algunas ventajas de las excepciones son:

* Separar el código que gestiona los errores del código con el caso principal del programa.
* Propagar errores hacia arriba en la pila de llamadas.
* Agrupar y diferenciar entre diferentes tipos de errores.

### Los problemas de las excepciones _checked_

Hay una cierta polémica sobre si las excepciones _checked_ son una buena idea. Entre los motivos que se alegan en contra de su uso están que cambiar la firma de un método añadiendo una nueva excepción como lanzable hace que el código que usase ese método podría ocasionar errores de compilación y que hace necesario el tratarla o declararla en la cadena de métodos hasta que sea tratada. Otro motivo es que a mayor nivel en la jerarquía de llamada en los métodos se necesitarán manejar una lista amplia de excepciones.

En el lado contrario las excepciones se consideran que son buenas porque conocer las condiciones de error o excepción que puede lanzar el método forma parte del contrato del método y es necesario para realizar un correcto manejo de errores. Las excepciones _checked_ pueden parecer un incordio pero son necesarias para hacer un correcto manejo de errores y evitar que el programa falle por no tratar las condiciones de error de las que advertirían. Por otro lado no deberían silenciarse con un bloque _catch_ vacío sin una buena razón. En las excepciones _checked_ el compilador es capaz de advertir si alguna excepción no ha sido capturada o lanzada.

Como regla general las excepciones _checked_ se usan cuando el programa es capaz de recuperarse del error y tratarlo adecuadamente, las _unchecked_ cuando se trata de un error de programación o no se puede hacer nada para recuperarse.

En el siguiente código se observa como capturar, lanzar y declarar excepciones en las firmas de los métodos en Java en una construcción _try catch finally_.

{{< code file="Bank.java" language="java" options="" >}}
{{< code file="Main.java" language="java" options="" >}}

Las excepciones _checked_ presentan los siguientes problemas:

* Son verbosas: requieren utilizar bloques de sentencias _try-catch_ y declarar en la firma del método la excepción en caso de que las lancen. Al mismo tiempo si hay niveles intermedios de métodos entre el método que la lanza y el que la trata la excepción debe ser declarada en los métodos intermedios.
* Promueven malas prácticas: si se intentan manejar en el lugar incorrecto, si se usan bloques de código _catch_ que no hacen nada que las ocultan, si se capturan excepciones generales como _Exception_ en vez de tipos específicos y si se transforman excepciones _Exception_ en _RuntimeException_.
* Incrementan el acoplamiento entre módulos: si se usa un método de una librería que añade una excepción, entonces todo el código que llame a ese método debe ser actualizado para manejar la excepción o relanzarla.
* Requieren transformar excepciones entre diferentes niveles de abstracción: por ejemplo la capa de acceso a base de datos puede requerir convertir uan excepción por un problema de red en una excepción de base de datos no disponible. Esto es tedioso pero necesario para evitar exponer detalles de implementación.
* No funciona bien con la herencia: si un método se sobreescribe no puede lanzar una excepción si el método sobreescrito no declara que lanza una excepción.
* No funcionan bien con las _lambdas_: en Java las _lambdas_ se implementan usando interfaces con un único método abstracto, ese método debe o declarar la posible excepción, en cuyo caso todo método que lo use debe manejarla, o el cuerpo de la _lambda_ debe capturar la excepción. Ambas opciones incrementan el código necesario para usar las _lambdas_ reduciendo uno de sus beneficios de ser más concisas.

### La solución a las excepciones _checked_ de otros lenguajes posteriores a Java

La solución por la que han optado otros lenguajes más modernos que Java como [C#][csharp] y [Kotlin][kotlin] es considerar a todas las excepciones como _unchecked_. Dado que en muchos casos el único tratamiento posible de una excepción posible es relanzarla, para manejar las excepciones en muchos casos es suficiente un manejador global de excepciones que las capture. Otro beneficio del manejador global de excepciones es que centraliza en un único punto el tratamiento de las excepciones.

Por el contrario dado que las excepciones _unchecked_ no necesitan declararse en los métodos ni el compilador produce ningún error en caso de que una excepción no se capture o lance al usar un método se desconoce que ese método puede lanzar una excepción. Para mitigar el problema de no conocer si un método lanzan una excepción en Java se pueden usar las anotaciones de Javadoc para declararlas al menos en la documentación. Dado que los IDEs muestran la documentación del Javadoc al usar un método no es necesario inspeccionar el cuerpo de un método para conocer si lanza una excepción.

En el aparatado referencia incluyo unos buenos enlaces que amplían y detallan más apropiadamente la controversia sobre las excepciones _checked_ y _unchecked_.

{{< reference >}}
* [Exceptions tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html)
* [Unchecked Exceptions — The Controversy](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)
* [Advantages of Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/advantages.html)
* [Why are Exceptions not Checked in .NET?](https://stackoverflow.com/questions/124143/why-are-exceptions-not-checked-in-net#126122)
* [The Trouble with Checked Exceptions](http://www.artima.com/intv/handcuffs.html)
* [What's the idea behind Kotlin removing checked exceptions?](https://stackoverflow.com/questions/58639126/whats-the-idea-behind-kotlin-removing-checked-exceptions)
{{< /reference >}}

{{% /post %}}
