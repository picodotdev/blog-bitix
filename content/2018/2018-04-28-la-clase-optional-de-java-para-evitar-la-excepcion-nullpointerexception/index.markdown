---
pid: 315
type: "post"
title: "La clase Optional de Java para evitar la excepción NullPointerException"
url: "/2018/04/la-clase-optional-de-java-para-evitar-la-excepcion-nullpointerexception/"
date: 2018-04-28T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Con la clase _Optional_ añadida en el JDK en la versión 8 del lenguaje Java se puede evitar una de las excepciones más comunes que se produce cuando se hace uso de una referencia nula a un objeto."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una de las excepciones que más se producen en un programa hecho con el lenguaje Java es la conocida [NullPointerException](javadoc10:java/lang/NullPointerException.html) que ocurre cuando se hace uso de una variable que referencia a un objeto pero que el contenido de la variable es _null_, sin valor o sin contener una referencia a un objeto de modo que la llamada al método no es posible. La excepción _NullPointerException_ extiende de [RuntimeException](javadoc10:java/lang/RuntimeException.html) por lo que es una _unchecked exception_ y por ello no es necesario capturarla o lanzarla, cuando se produce hay un error en el programa.

Entre las [novedades que Java 8 incluyó en el lenguaje y JDK][blogbitix-17] está la inclusión de la clase [Optional](javadoc10:java/util/Optional.html) con la que haciendo uso de ella se pueden evitar los _NullPointerException_. Un objeto de tipo _Optional_ contiene o no una referencia a otro tipo de objeto. Por ejemplo, una variable de tipo _Optional\<String\>_ contiene una referencia a un objeto _Optional_ que a su vez contiene o no una referencia a una cadena _String_. El uso de la variable _Optional_ no producirá un _NullPointerException_ y con sus métodos es posible saber si contiene o no una referencia al tipo usado en el genérico. Con el método [isPresent()](javadoc10:java/util/Optional.html#isPresent()) es posible saber si el _Optional_ contiene una referencia, con [orElse()](javadoc10:java/util/Optional.html#orElse(T)) y [orElseGet()](javadoc10:java/util/Optional.html#orElseGet(java.util.function.Supplier)) se obtiene la referencia que indiquemos en caso de que no tenga una referencia y con los métodos estáticos a modo de constructores [of()](javadoc10:java/util/Optional.html#of(T)) y [ofNullable()](javadoc10:java/util/Optional.html#ofNullable(T)) se obtiene respectivamente una instancia de _Optional_ con la referencia indicada que no puede ser nula o un _Optional_ que podría contener una referencia nula.

Usar un objeto _Optional_ advierte al programador de que la referencia que contiene puede ser nula y usada correctamente evita los _NullPointerException_ aunque usarla indiscriminadamente hace del código más incómodo de escribir y leer, en variables locales es prescindible su uso pero útil en algunos valores de retorno o parámetros de métodos.

{{< code file="jshell.sh" language="bash" options="" >}}

Hay algunos métodos más en la clase _Optional_ con algunas funcionalidades adicionales que hace uso de las nuevas capacidades funcionales del lenguaje como convertir un _Optional_ a un [Stream](javadoc10:java/util/stream/Stream.html).

{{< reference >}}
* [Las excepciones para gestionar errores en Java][blogbitix-270]
* [The $1 Solution to Avoid the Null Pointer Exception](https://dzone.com/articles/one-dollar-solution-to-avoid-null-pointer-exceptio)
{{< /reference >}}

{{% /post %}}
