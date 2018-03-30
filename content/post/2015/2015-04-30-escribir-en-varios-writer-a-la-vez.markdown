---
pid: 78
title: "Escribir en varios «Writer» a la vez"
url: "/2015/04/escribir-en-varios-writer-a-la-vez/"
date: 2015-04-30T20:00:47+02:00
rss: true
sharing: true
comments: true
tags: ["java", "programacion", "blog-stack", "planeta-codigo"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="java.png" title="Java" >}}

Hace un tiempo tuve necesidad de generar cierto contenido sobre varios writers, la necesidad en concreto era generar un archivo xml en disco y al mismo tiempo el mismo contenido para un correo electrónico. Para no escribir lo mismo en dos [Writer](https://docs.oracle.com/javase/8/docs/api/java/io/Writer.html) diferentes la solución fue crear un writer y este fuese el que escribiese el contenido que se le enviaba sobre varios writers. En la API de Java no hay una clase específica que haga esto pero es muy sencillo hacer una implementación que lo haga, esto va a ser lo que explicaré en el siguiente artículo.

Para hacer que el contenido de un writer se escriba a varios deberemos extender la clase Writer de esta manera su uso será como la de cualquier otro Writer. Lo especial de la implementación del writer es que su misión será realizar la misma operación que se haga sobre él sobre los writers que en este caso se pasan como parámetros en el constructor en forma de varargs.

{{< gist picodotdev e17bd9a8c38d32d70c85 "MultipleWriter-1.java" >}}

El bucle _for_ sobre cada uno de los _Writer_ está encapsulado en el método _doWriters_, el objeto _Command_ es que realmente hace la escritura en el writer usando el método _write_ que se llamó sobre la clase _MultipleWriter_. A falta de las [funciones lambda hasta Java 8][blogbitix-17] se usa el objeto _Command_ y el método doWriters_, por contra se crea por cada método _writer_ invocado se crea una instancia de la clase Command.

Con [closures y las novedades de Java 8 en la API][blogbitix-17] no sería necesario que usaramos una clase Command, el código es más sencillo, breve y más legible.

{{< gist picodotdev e17bd9a8c38d32d70c85 "MultipleWriter-2.java" >}}

Independiente de la implementación con Java 7 o con a Java 8 el uso sería el siguiente:

{{< gist picodotdev e17bd9a8c38d32d70c85 "Main.java" >}}

<div class="media" style="text-align: center;">
    {{< figure year="2015" pid="78"
        image1="multiplewriter-consola.png" thumb1="multiplewriter-consola.png" title1="Salida en la consola" >}}
</div>

Con [Groovy][groovy] además de las _closures_ no será necesario que declararemos de forma explícita el lanzamiento de las excepciones sin embargo al usarlo perderíamos la ayuda que ofrece el compilador.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Otros artículos sobre Java][blogbitix-tag-java]
* [Novedades y nuevas características de Java 8][blogbitix-17]
{{% /reference %}}

{{% /post %}}
