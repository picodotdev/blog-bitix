---
pid: 534
type: "post"
title: "5 formas de implementar el patrón Singleton en Java"
url: "/2020/11/5-formas-de-implementar-el-patron-singleton-en-java/"
date: 2020-11-21T23:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
series: ["java-patron-diseno"]
summary: "El patrón _Singleton_ es un patrón de diseño muy utilizado, este patrón garantiza que de una clase solo haya una única instancia. En Java hay varias formas de implementar el patrón, algunas son más sencillas otras no son _thread-safe_ o con evaluación _lazy_."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Los lenguajes orientados a objetos modelan los programas utilizando clases, estas clases contienen tanto los datos como las operaciones que tratan esos datos que solo pueden ser modificados a través de esas operaciones. Este principio de encapsulación tiene como beneficio garantizar que los datos no sean modificados por cualquier otro código distinto a las operaciones y que los datos no queden en un estado inconsistente.

En un lenguaje orientado a objetos de cada clase se crean tantas instancias u objetos como se necesiten en el programa. En algunos casos de una clase en concreto sólo se desea que haya una única instancia, normalmente hacer que de una clase en concreto haya una única instancia se implementa con el patrón _Singleton_.

En Java hay varias formas de implementar el patrón _Singleton_, aunque todas son posibles cada una tiene diferentes propiedades, algunas tiene más beneficios sin las debilidades de otras. En este artículo incluyo 5 formas de cómo implementar el patrón _Singleton_ con un ejemplo de código de cada una de ellas e indico cuales de ellas son las mejores opciones de entre estas.

{{< tableofcontents >}}

## Qué es el patrón Singleton

El [patrón Singleton](https://en.wikipedia.org/wiki/Singleton_pattern) es un patrón de diseño que restringe a una única instancia de objeto el número instancias de una clase que se pueden crear durante la ejecución del programa. Es útil en ciertas clases donde su única instancia es compartida en todo el código como si de una variable global se tratase. Si el método que permite obtener la referencia a la instancia es un método estático la instancia es como si fuese una variable global dado que es posible acceder a ella desde cualquier parte del programa siempre que lo permitan [los modificadores de acceso][blogbitix-458].

Los casos de uso de las clases _singleton_ son mantener un estado compartido, permitir inicialización bajo demanda o _lazy_, también es usado en otros patrones como el patrón _abstract factory_, _factory method_, [el patrón _builder_][blogbitix-99] o _facade_.

{{< image
    gallery="true"
    image1="image:singleton-pattern.webp" optionsthumb1="650x450" title1="Diagrama de clases del patrón de diseño Singleton"
    caption="Diagrama de clases del patrón de diseño Singleton" >}}

## Formas de implementar el patrón Singleton en Java

En Java hay varias formas de implementar el patrón _Singleton_ en una clase, el objetivo y resultado es el mismo pero la implementación hace que tengan algunas diferencias como que no sea _thread-safe_, no sea _lazy_, otras implementaciones igualmente sencillas son _thread-safe_ y _lazy_ al mismo tiempo.

Esta son las implementaciones más comunes, dependiendo de las necesidades de la aplicación todas son válidas simplemente la implementación con la clase _inner_ y _enum_ son las que proporcionan _thread-safe_ y evaluación _lazy_ que algunas de las otras no tienen.

### Forma tradicional

La forma tradicional de implementar el patrón _Singleton_ es utilizando una variable estática privada para guardar la referencia de la única instancia, hacer el constructor privado de modo que el resto de clases no tengan la posibilidad de crear más instancias y un método que crea la instancia si no ha sido creada con anterioridad con una sentencia condicional y devuelve la referencia si ya existe la instancia.

{{< code file="Singleton-1.java" language="java" options="" >}}

Esta implementación es muy utilizada, aunque es _lazy_ ya que la instancia no se crea hasta que se realiza la primera solicitud su inconveniente es que no es _thread-safe_ si varios hilos intentan obtener una instancia cuando aún no hay ninguna creada.

### Método sincronizado

Para implementar el patrón _Singleton_ con la propiedad de que sea _thread-safe_ en el caso anterior la forma más sencilla es hacer el método _synchronized_ de modo que Java garantiza que si varios hilos intentan obtener la referencia de la instancia cuando aún no está creada sólo uno de ellos la cree.

{{< code file="Singleton-2.java" language="java" options="" >}}

El inconveniente de esta implementación con _synchronized_ hace que el método para obtener la instancia sea más lento debido a la propia sincronización y a la contención que se produce si hay múltiples hilos en ejecución que hacen uso del método, si el rendimiento es una consideración importante hay otras formas de implementar el patrón _Singleton_ no mucho más difíciles que tienen mejor rendimiento.

### Variable estática final

La siguiente forma de implementar el patrón _Singleton_ es crear la instancia en la inicialización de la clase ya sea como una asignación en la variable o con el constructor estático.

{{< code file="Singleton-3a.java" language="java" options="" >}}
{{< code file="Singleton-3b.java" language="java" options="" >}}

Esta implementación no requiere utilizar una sentencia condicional _if_ para comprobar si la instancia ya ha sido creada y es _thread-safe_, sin embargo, no es _lazy_ ya que la instancia se crea cuando se inicializa la clase antes de que se haga el primer uso.

### Clase _inner_

Esta implementación es _thread-safe_ y tiene evaluación _lazy_ que algunas de las anteriores no tienen. Lo que hace es utilizar una clase anidada o _inner_ para mantener la referencia a la instancia de la clase que la contiene.

{{< code file="Singleton-4.java" language="java" options="" >}}

Este caso tampoco hace uso de la sentencia condicional _if_ y el propio lenguaje Java por la inicialización de las clases y propiedades _static_ garantiza que es _thread-safe_.

### Clases _enum_

Las clases _enum_ son por definición clases cuyos enumerados son _singleton_ y _thread-safe_. La particularidad de los _enum_ es que no se pueden crear nuevas instancias adicionales a las que se definan en el código en tiempo de compilación, el número de instancias es constante, por lo demás los _enum_ al igual que las clases pueden implementar interfaces, declarar métodos y constructores de uso en el propio _enum_.

{{< code file="Singleton-5.java" language="java" options="" >}}

## Conclusión

Si el Singleton debe extender una clase la mejor opción de crearlo es con la clase _inner_, la implementación con una clase _enum_ también es válida. Estas son las dos implementaciones aconsejadas para implementar el patrón _Singleton_. La forma tradicional sigue siendo muy utilizada con números ejemplos de código en artículos y es posible utilizarla, sin embargo, dadas sus desventajas es preferible utilizar alguna de las dos aconsejadas que son igual de sencillas de implementar.

{{< reference >}}
* [Five ways to implement Singleton pattern in Java](http://www.learn4master.com/algorithms/java-singleton-pattern)
* [Java Singletons Using Enum](https://dzone.com/articles/java-singletons-using-enum)
{{< /reference >}}

{{% /post %}}
