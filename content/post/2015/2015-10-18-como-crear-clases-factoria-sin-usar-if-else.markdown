---
pid: 104
title: "Cómo crear clases factoría sin usar if-else"
url: "/2015/10/como-crear-clases-factoria-sin-usar-if-else/"
date: 2015-10-18T12:00:00+02:00
updated: 2015-10-19T19:00:00+02:00
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="java.png" title="Java" >}}

En el artículo [cómo crear clases factoría sin usar if-else](http://www.javacodegeeks.com/2014/10/factory-without-if-else.html) se comentan varias formas para conseguirlo. Las soluciones que se proponen son usar:

* [_Reflection_](http://docs.oracle.com/javase/8/docs/api/java/lang/reflect/package-summary.html): sin embargo esta solución obliga a que el cliente de la factoría conozca el nombre completo de la clase a crear incluyendo el paquete en el que está y hace que la factoría no abstraiga al cliente de conocerlo que puede ser uno de sus objetivos. Además el parámetro es un _String_ con lo que en los _refactors_ el compilador no nos ayudará, el compilador es una de [mis 10 razones para seguir usando Java][blogbitix-81].
* [_Map_](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html): en esta solución se asocia una clave (en String) con la clase que devuelve la factoría. Igualmente usar un _String_ como clave es algo a evitar ya que es propenso a errores al no tener ayuda del compilador en los valores de los Strings si estas cambian.
* [_Enum_](https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html): en esta solución para eliminar los _if-else_ se aprovecha de la propiedad del [polimorfismo](https://en.wikipedia.org/wiki/Polymorphism_%28computer_science%29) de los lenguajes orientados a objetos. El tipo _Enum_ usado tiene un método que es implementado en cada uno de los tipos concretos de enum, en función de que utilice se crea la instancia adecuada del objeto.

Hay que notar otra diferencia entre la soluciones con _if-else_, _Reflection_ y _Enum_ y por otra parte la solución con _Map_ y es que en el ejemplo las tres primeras crean una nueva instancia del validador cada vez que se llama a _newInstance_ mientras que en la solución Map solo devuelve la instancia puesta en el mapa.

La solución del _Enum_ el código resultante es bastante verboso y menos legible que las otras soluciones aún usando polimorfismo (en mi humilde opinión), por otra parte crear una nueva instancia de validador cada vez que se invoca a _newInstance_ en la factoría puede que no sea lo que queramos (o quizá sí). Por esto voy a plantear una nueva solución que es usando un _Enum_ como clave de un _Map_, que es una variante de la solución comentada en el artículo con _Map_. Es código de la nueva solución es el siguiente:

{{% gist id="3b4623579955cd66751d" file="ValidatorFactory.java" %}}

Si necesitásemos que la factoría devolviese una nueva instancia de _Validator_ en cada llamada a _newInstance_ (por ejemplo, porque no es _thread-safe_) podemos poner en el mapa una instancia de [Supplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html) haciendo uso de las funciones _lambda_ incluidas como una de las [nuevas características incorporadas en Java 8][blogbitix-17].

Cómo se comenta en los recomendables libros <a href="http://www.amazon.es/gp/product/0201485672/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=0201485672&linkCode=as2&tag=blobit-21">Refactoring: Improving the Design of Existing Code</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=0201485672" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;" /> y <a href="http://www.amazon.es/gp/product/0321356683/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=0321356683&linkCode=as2&tag=blobit-21">Effective Java</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=0321356683" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;" /> los _if-else_ o _switches_ son algo a evitar ya que se prefiere la pauta «abierto a extensión, cerrado a modificación», si necesitamos modificar código existente puede que introduzcamos algún error o se nos olvide modificar algún punto del código, por ello se prefiere el polimorfismo siendo la solución del _Enum_ la más cercana a este principio o alguna de las otras soluciones. En el libro <a href="http://www.amazon.es/gp/product/0321356683/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=0321356683&linkCode=as2&tag=blobit-21">Effective Java</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=0321356683" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;" /> además se comentan ideas interesantes y ventajas sobre por que usar factorías (en ciertas situaciones) en vez de la palabra clave reservada _new_. Sin embargo, hay que saber cuando aplicar cierto patrón ya que son soluciones en algún sentido mejor pero que añaden algo de complejidad, nos tocará en función de las modificaciones que preveamos que pueda tener el código, de las necesidades o experiencia saber cual aplicar.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Factory without if-else](http://www.javacodegeeks.com/2014/10/factory-without-if-else.html)
* [8+ libros para mejorar como programadores][blogbitix-55]
{{% /reference %}}

{{% /post %}}
