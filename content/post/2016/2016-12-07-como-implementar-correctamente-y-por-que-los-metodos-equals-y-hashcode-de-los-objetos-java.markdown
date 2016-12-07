---
pid: 199
title: "Cómo implementar correctamente y por qué los métodos equals y hashCode de los objetos Java"
url: "/2016/12/como-implementar-correctamente-y-por-que-los-metodos-equals-y-hashcode-de-los-objetos-java/"
date: 2016-12-07T11:00:00+01:00
language: "es"
sharing: true
comments: true
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
summary: "Los métodos _equals_ y _hashCode_ son esenciales en las colecciones de objetos. Para su correcta implementación es necesario conocer unas cuantas propiedades que han de cumplir estos métodos. Pueden parecer sencillos pero no lo son tanto y una mala implementación posiblemente produzca algún tipo de error o comportamiento anómalo indeseado. En el siguiente artículo comento varias formas de implementarlos de forma sencilla y correcta."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.png" title1="Java" >}}

En Java los métodos [equals](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-) y [hashCode](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--) están definidos en la raíz de la jerarquía de clases, esto es en la clase [Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html), lo que significa que todas las instancias de objetos los poseen. Estos métodos son especialmente importantes ya que afectan al correcto funcionamiento de las colecciones como [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html), [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html), [Set](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html) y [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html), colecciones, listas, conjuntos y mapas que es difícil que cualquier programa no use alguna implementación de ellas.

El método _equals_ es usado en las colecciones de tipo _List_, _Set_, y también _Map_ para determinar si un objeto ya está incluida en la colección, el método _hashCode_ es usado en los _Map_ para encontrar el objeto asociado a la clave. Dado que las colecciones son ampliamente usadas en cualquier programa la correcta implementación implementación de los métodos _equals_ y _hashCode_ es fundamental ya que de lo contrario descubriremos errores poco agradables.

Una de las cosas que tenemos que tener cuenta es que siempre que sobreescribamos el método _equals_ también debemos sobreescribir el método _hashCode_. Según el [contrato definido en la clase Object](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html) deberemos saber que:

* Durante la ejecución del programa el método _hashCode_ debe retornar el mismo valor siempre que no se modifique la información usada en el método _equals_.
* Si dos objetos son iguales según sus métodos _equals_ entonces el valor devuelto por _hashCode_ en cada uno de los dos objetos debe devolver el mismo valor.
* Si dos objetos son distintos según sus métodos _equals_ el valor devuelto no ha de ser necesariamente distinto aunque se recomienda para mejorar el rendimiento de las colecciones _Map_.

### Cómo implementar el método _equals_

Según la [especificación del método equals](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-) definido en la clase _Object_ debe tener las siguientes propiedades:

* Es reflexiva: para cualquier referencia no nula de x, <code>x.equals(x)</code> debe retornar _true_.
* Es simétrica: para cualquier referencia no nula de x e y, <code>x.equals(y)</code> debe retornar _true_ si y solo si <code>y.equals(x)</code> retorna _true_.
* Es transitiva: para cualquier referencia no nula de x, y y z, si <code>x.equals(y)</code> debe retorna _true_ y <code>y.equals(z)</code> retorna _true_ entonces <code>x.equals(z)</code> debe retornar _true_.
* Es consistente: para cualquier referencia no nula de x e y, múltiples invocaciones de <code>x.equals(y)</code> consistentemente debe retornar _true_ o _false_, si no se ha modificado la información utilizada en la comparación.
* Para para cualquier referencia no nula de x, <code>x.equals(null)</code> debe retornar _false_.

La implementación del método _equals_ de la clase _Object_ usa la equivalencia más restrictiva posible, esto es, para cualquier referencia no nula de _x_ e _y_ este método retorna _true_ si y solo si son el mismo objeto (_x == y_ tienen la misma referencia).

Según estas reglas una implementación del método _equals_ tiene la siguiente forma:

{{< gist picodotdev 906be34ee509940d9025c689fa50bb20 "PhoneNumber-equals.java" >}}

Usando la clase [EqualsBuilder](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/EqualsBuilder.html) de la librería [commons-lang](https://commons.apache.org/proper/commons-lang/) la implementación es aparentemente similar pero en el caso de necesitar hacer comparaciones con datos de tipo _float_, _double_, _arrays_ u objetos hace la implementación un poco más sencilla. En los _float_ y _double_ para hacer la comparación deberíamos usar los métodos [Float.compare](http://docs.oracle.com/javase/8/docs/api/java/lang/Float.html#compare-float-float-) y [Double.commpare](http://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#compare-double-double-) y en los objetos deberemos tener en cuenta si la referencia es posible que se a nula para evitar la excepción [NullPinterException](http://docs.oracle.com/javase/8/docs/api/java/lang/NullPointerException.html) cosas que la clase _EqualsBuilder_ ya tiene en cuenta.

{{< gist picodotdev 906be34ee509940d9025c689fa50bb20 "PhoneNumber-equals-commons-lang.java" >}}

### Como implementar el método _hasCode_

La implementación del [método hashCode](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--) se debe realizar según los siguientes pasos:

* Almacenar un valor constante distinto de 0 en una variable int, por ejemplo 17.
* Por cada campo usado en el método _equals_ se debe obtener un _hash code_ (int) realizando:
  * Si el campo es un _boolean_ se debe calcular <code>(f ? 1 : 0)</code>.
  * Si el campo es un _byte_, _char_, _short_ o _int_ se debe calcular <code>(int) f</code>.
  * Si el campo es un _long_ se debe calcular <code>(int) (f ^ (f >>> 32))</code>.
  * Si el campo es un _float_ se debe calcular <code>Float.floatToIntBits(f)</code>.
  * Si el campo es un _double_ se debe calcular <code>Double.doubleToLongBits(f)</code> y calcular el _hash_ del _long_ obtenido en el paso para los tipos _long_.
  * Si el campo es una referencia a un objeto y el método _equals_ de esta clase compara recursivamente invocando el método _equals_ del campo, invocar su método _hashCode_. si el valor de campo es nulo se debe retornar una constante que tradicionalmente es 0.
  * Si el campo es un _array_ se debe tratar individualmente cada elemento aplicando estas reglas a cada elemento. Si cada elemento del array es significativo se puede usar [Arrays.hashCode](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#hashCode-java.lang.Object:A-).
  * Combinar los _hash code_ obtenidos de la siguiente forma, <code>result = 31 * result + c</code>.

{{< gist picodotdev 906be34ee509940d9025c689fa50bb20 "PhoneNumber-hashcode.java" >}}

Implementar este método en cada clase de una aplicación es tedioso, repetitivo y propenso a errores, para hacer más sencilla su implementación existe el método [Objects.hash](https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html#hash-java.lang.Object...-) desde la versión 7 de Java. Si usamos una versión anterior a Java 7 disponemos de la clase [HashCodeBuilder](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html) en la librería _commons-lang_. La misma implementación anterior quedaría.

{{< gist picodotdev 906be34ee509940d9025c689fa50bb20 "PhoneNumber-hashcode-java.java" >}}

{{< gist picodotdev 906be34ee509940d9025c689fa50bb20 "PhoneNumber-hashcode-commons-lang.java" >}}

En el libro [Effective Java](http://amzn.to/2g9N1bi) se explican con un poco más detalle estas dos cosas y muchas otras otras sobre Java que son muy interesantes conocer, el libro es una buena y recomendada lectura para todo programador Java que está entre los [8+ libros para mejorar como programadores](https://picodotdev.github.io/blog-bitix/2014/12/8-plus-libros-para-mejorar-como-programadores/) que recomiendo.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=0321356683&linkId=9145a271e7886cea526d9fcbe9edb707&internal=1"></iframe>
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [HashCode and Equals method in Java object](http://www.javaworld.com/article/2074996/hashcode-and-equals-method-in-java-object---a-pragmatic-concept.html)
{{% /reference %}}

{{% /post %}}
