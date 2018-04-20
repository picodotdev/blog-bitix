---
pid: 313
title: "La controversia sobre las excepciones checked y uncheked"
url: "/2018/04/la-controversia-sobre-las-excepciones-checked-y-uncheked/"
date: 2018-04-20T17:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Las excepciones son una forma de gestionar las condiciones de error que se dan en los programas. En el lenguaje C se utiliza el valor de retorno de la función para determinar la condición de error que se ha producido, el problema es que comprobar el valor de retorno puede ignorarse y la gestión de errores está mezclada con la tarea del programa.

El lenguaje Java utiliza un mecanismo de excepciones, las excepciones son objetos que se lanzan cuando se produce una condición de error. Todas las excepciones en Java heredan de [Throwable](https://docs.oracle.com/javase/10/docs/api/java/lang/Throwable.html) subdividiéndose en [Error](https://docs.oracle.com/javase/10/docs/api/java/lang/Error.html) y [Exception](https://docs.oracle.com/javase/10/docs/api/java/lang/Exception.html), las primeras son condiciones de error del sistema y las segundas condiciones de error del programa. A su vez las _Exception_ pueden ser _checked_ si heredan de esta y son aquellas que el compilador fuerza a que sean capturadas no pudiendo ignorarse, han de capturarse en una construcción _try catch_ o declarar que el método puede lanzar la excepción no capturada. Las excepciones _uncheked_ heredan de [RuntimeException](https://docs.oracle.com/javase/10/docs/api/java/lang/RuntimeException.html) que heredan a su vez de _Exception_ pero tienen la particularidad de que no es necesario capturarlas ni declararlas como que se pueden lanzar debido a que se consideran condiciones de error en la programación como un acceso a un _array_ fuera de rango que produce un [ArrayIndexOutOfBounds](https://docs.oracle.com/javase/10/docs/api/java/lang/ArrayIndexOutOfBoundsException.html), el conocido [NullPointerException](https://docs.oracle.com/javase/10/docs/api/java/lang/NullPointerException.html) cuando se utiliza una referencia nula, otro es [ArithmeticException](https://docs.oracle.com/javase/10/docs/api/java/lang/ArithmeticException.html) que se produce al dividir por 0.

Hay una cierta polémica sobre si las excepciones _checked_ son una buena idea. Entre los motivos que se alegan en contra de su uso están que cambiar la firma de un método añadiendo una nueva excepción como lanzable hace que el código que usase ese método podría ocasionar errores de compilación y que hace necesario el tratarla o declararla en la cadena de métodos hasta que sea tratada. Otro motivo es que a mayor nivel en la jerarquía de llamada en los métodos se necesitarán manejar una lista amplia de excepciones.

En el lado contrario las excepciones se consideran que son buenas porque conocer las condiciones de error o excepción que puede lanzar el método forma parte de la firma del método y es necesario para realizar un correcto manejo de errores. Las excepciones que heredan de _RuntimeException_ no se obliga a controlarlas ya que son errores producidos por un error en la programación.

Las excepciones _checked_ pueden parecer un incordio pero son necesarias para hacer un correcto manejo de errores y evitar que el programa falle por no capturarlas. Por otro lado no deberían silenciarse con un bloque _catch_ vacío sin una buena razón.

En el siguiente código se observa como capturar, lanzar y declarar excepciones en las firmas de los métodos en Java en una construcción _try catch finally_.

{{< gist picodotdev c0fc9936712783e76b22088e5b8f2b2e "Bank.java" >}}
{{< gist picodotdev c0fc9936712783e76b22088e5b8f2b2e "Main.java" >}}

En el aparatado referncia incluyo unos buenos enlaces que amplian y detallan más apropiadamente la controversia sobre las excepciones _checked_ y _uncheked_.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Unchecked Exceptions — The Controversy](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)
* [Why are Exceptions not Checked in .NET?](http://stackoverflow.com/questions/124143/why-are-exceptions-not-checked-in-net#126122)
* [The Trouble with Checked Exceptions](http://www.artima.com/intv/handcuffs.html)
{{% /reference %}}

{{% /post %}}
