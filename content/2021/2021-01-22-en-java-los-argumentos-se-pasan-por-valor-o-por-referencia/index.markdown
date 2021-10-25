---
pid: 548
type: "post"
title: "En Java, ¿los argumentos se pasan por valor o por referencia?"
url: "/2021/01/en-java-los-argumentos-se-pasan-por-valor-o-por-referencia/"
date: 2021-01-22T16:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

En el lenguaje de programación Java los punteros no existen de la misma forma que en otros lenguajes como C o C++. En Java existen los punteros o referencias pero no son libres de apuntar a cualquier dirección de memoria del programa, en C y C++ lenguajes más destinados a la programación de sistemas y cercanos al lenguaje de la máquina ofrecen manipulación de punteros pudiendo obtener un puntero con la dirección de memoria de una variable.

En el lenguaje Java los punteros realmente son vistos simplemente como variables, no es posible obtener un puntero a la dirección de memoria de una variable, únicamente permite copiar o duplicar el valor de una variable en otra variable. Otra diferencia está en que en Java los argumentos de los métodos siempre se pasan por valor, en C y C++ los argumentos se pueden pasar por valor o por referencia.

Estas diferencias de Java con los lenguajes C y C++, hacen de Java un lenguaje más sencillo en la sintaxis, seguro y menos propenso a errores, por otro lado, la manipulación de punteros en C y C++ es útil en la programación de sistemas por ser un modelo similar al empleado por la CPU en su funcionamiento.

### El paso por valor de los argumentos en Java

En Java todos los argumentos se pasan por valor. El paso por valor de los argumentos de un método en Java tiene varias consecuencias. El paso por valor significa que al método en la variable del argumento le llega una copia del valor en el caso de un tipo primitivo de datos o una copia del puntero a la dirección de memoria del objeto. En el paso por referencia el argumento contiene un puntero con la dirección de memoria de la variable. En el paso por valor al asignar un valor a la variable del argumento no modifica el valor de la variable usada para invocar al método, esto ocurre tanto para argumentos de tipo primitivo y para objetos.

{{< image
    gallery="true"
    image1="image:java-reference-value.gif" optionsthumb1="450x300" title1="Paso de argumentos por referencia y paso por valor"
    caption="Paso de argumentos por referencia y paso por valor" >}}

[La palabra reservada _final_ en los argumentos][blogbitix-540] sirve para impedir asignar un nuevo valor a una variable, una variable _final_ es una variable cuyo valor es una constante ya que en caso de intentar asignar a la variable un nuevo valor el compilador produce un error de compilación. La variable no pude cambiar de valor, sin embargo, si la variable es una referencia a un objeto el objeto si puede cambiar de estado, para que un objeto no pueda cambiar ha de ser inmutable.

Algunas clases como la clase [String](javadoc11:java.base/java/lang/String.html) en Java son inmutables, esto significa que al manipular el objeto se devuelve una nueva instancia de la clase en vez de modificar la original. En Java para manipular un _String_ y obtener la misma referencia en vez de una nueva instancia hay que utilizar la clase [StringBufffer](javadoc11:java/lang/StringBuffer.html) o [StringBuilder](javadoc11:java/lang/StringBuilder.html). Algunas instancias de listas obtenidas con la API de colecciones son inmutables como [List.of](javadoc11:java.base/java/util/List.html), y sus métodos [add](javadoc11:java.base/java/util/List.html#add(E)) y [remove](javadoc11:java.base/java/util/List.html#remove(java.lang.Object)) lanzan la excepción en caso de ser invocados.

### Ejemplo práctico del paso por valor

En este programa aunque a las variables de los argumentos _x_, _y_ y _w_ se les asigne un nuevo valor las variables _a_, _b_ y _c_ usadas en la invocación del método continúan teniendo el mismo valor. Las variables de los argumentos se pasan por valor, sin embargo, los objetos a los que apuntan esas variables si son mutables y son modificados en el método los cambios son visibles en el ámbito de invocación con la variable utilizada como argumento.

La lista de la variable _d_ es modificada por el método, se corresponde con la variable _z_, y esos cambios son visibles en el ámbito de _d_, al contrario de las otras variables que aunque se les asigna un nuevo valor en el método fuera de él conservan su valor original aún después de la invocación del método. Esto ocurre porque en el caso de _d_ se ha modificado el objeto mutable al cual tanto _d_ y _z_ apuntan.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="System.out" language="plaintext" options="" >}}

{{% sourcecode command="java Main.java" %}}

{{< reference >}}
* [Does Java pass by reference or pass by value?](https://www.infoworld.com/article/3512039/does-java-pass-by-reference-or-pass-by-value.html)
{{< /reference >}}

{{% /post %}}
