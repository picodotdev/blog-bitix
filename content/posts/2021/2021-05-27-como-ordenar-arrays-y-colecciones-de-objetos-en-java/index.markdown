---
pid: 577
type: "post"
title: "Cómo ordenar arrays y colecciones de objetos en Java"
url: "/2021/05/como-ordenar-arrays-y-colecciones-de-objetos-en-java/"
date: 2021-05-27T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Al implementar un algoritmo es común querer iterar los elementos de una colección en un orden según un criterio, por ejemplo, si se trata de números de menor a mayor, si se trata de fechas de menor a mayor y si se trata de personas por orden alfabético del nombre, de menor a mayor edad o de menor a mayor antigüedad en la empresa, también es posible la necesidad de iterar en orden inverso. El JDK de Java proporciona interfaces para implementar la ordenación de objetos y que ya implementan algunos de los algoritmos de ordenación conocidos."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Entre las clases proporcionadas en el JDK de Java se proporciona un amplio [conjunto de clases dedicadas a colecciones][blogbitix-386] que son el fundamento de muchos algoritmos de programación y programas. Las clases de colecciones sirven para almacenar referencias a objetos, algunas colecciones no tiene un orden definido como [Set](javadoc11:java.base/java/util/Set.html) y [Map](javadoc11:java.base/java/util/Map.html) y otras definen un orden en la iteración de la colección como [List](javadoc11:java.base/java/util/List.html) pero no un orden entre los elementos, otras colecciones como [TreeSet](javadoc11:java.base/java/util/TreeSet.html) y [TreeMap](java.base/java/util/TreeMap.html) guardan los elementos ordenados según un criterio manteniéndose ordenada incluyendo al realizar inserciones y eliminaciones de elementos.

Hay varios [algoritmos de ordenación](https://es.wikipedia.org/wiki/Algoritmo_de_ordenamiento) conocidos como la [ordenación por burbuja o _bubble sort_, por inserción, _merge sort_ o _quicksort_][blogbitix-579] cada uno con diferentes propiedades de complejidad o consumo de memoria. Normalmente no es necesario implementar estos algoritmos, sino que ya están implementados en las bibliotecas y en el caso de Java en las clases del JDK.

El usar colecciones ordenadas por un orden es una funcionalidad común al implementar programas lo único que es necesario es utilizar la colección adecuada y únicamente crear una clase que implemente la interfaz [Comparator](javadoc11:java.base/java/util/Comparator.html) que determina el orden entre dos elementos, aplicando la comparación a los elementos de la colección con el algoritmo de ordenación ser ordena la colección.

{{< image
    gallery="false"
    image1="image:bubble-sort.gif" optionsthumb1="300x200" title1="Algoritmo de ordenación bubble-sort"
    image2="image:merge-sort.gif" optionsthumb2="300x200" title2="Algoritmo de ordenación merge-sort"
    caption="Algoritmo de ordenación bubble-sort y merge-sort" >}}

{{< tableofcontents >}}

## La interfaz _Comparator_

La interfaz _Comprator_ es una interfaz funcional, por tener un único método a implementar, que recibe dos argumentos y devuelve un entero. Los argumentos son los dos objetos a comparar y el resultado indica cual es el orden de los elementos entre sí.

Si el resultado es un _-1_ se indica que el argumento _a_ tiene un orden menor que _b_, si devuelve un _0_ el orden de los elementos es el mismo y si devuelve un _1_ el argumento _a_ tiene 
un orden superior a _b_.

Estas son implementaciones de _Comparator_ utilizando referencias de métodos.

{{< code file="Comparators.java" language="java" options="" >}}

Para ordenar cadenas alfabéticamente también es necesario crear un comparador, sin embargo, la ordenación de cadenas alfabéticamente no es tan simple como utilizar el método [comprateTo](javadoc11:java.base/java/lang/String.html#compareTo(java.lang.String)) de la clase [String](java.base/java/lang/String.html). Para [ordenar cadenas alfabéticamente en Java][blogbitix-276] hay de tener en cuenta letras con tildes, mayúsculas y minúsculas que varían según el idioma de las palabras, el método comprteTo que podría usarse para crear un _Comprator_ no es válido y puede producir resultados inesperados ya que el _String.compareTo_ ordena según el código de los caracteres sin tener en cuenta tildes ni mayúsculas ni minúsculas.

Esta es la implementación de un _Comparator_ que ordena cadenas en orden ascendente de forma alfabética utilizando la clase [Collator](avadoc11:java.base/java/text/Collator.html).

{{< code file="NameComparator.java" language="java" options="" >}}

Con la clase _Comparator_ es posible ordenar cualquier clase, en este ejemplo de clase _Person_ se ordenan los objetos según su edad, fecha de contratación y nombre. Como en este caso es posible tener varias implementaciones de _Comprator_ para una misma clase para ordenar los objetos por diferentes criterios.

## La interfaz _Comparable_

Otra interfaz relacionada con la ordenación es la interfaz [Comparable](javadoc11:java.base/java/lang/Comparable.html), es una interfaz que pueden implementar los objetos, la ordenación que se establece en la ordenación se le denomina el orden natural.

A diferencia de la clase _Comparator_ de la que es posible crear varias implementaciones, las clases sólo pueden implementar una vez la interfaz _Comparable_.

{{< code file="Person-comparable.java" language="java" options="" >}}

## Cómo ordenar los elementos un _array_

La clase [Arrays](javadoc11:java.base/java/util/Arrays.html) contiene varios métodos de utilidad entre ellos varios dedicados a la ordenación de los elementos de un _array_ tanto para elementos primitivos como para objetos. Hay métodos que utilizan el la ordenación natural de la interfaz _Comparable_ y hay métodos en los que es posible indicar la clase _Comparator_ con el orden deseado entre los elementos.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="Array-sort.java" language="java" options="" >}}
{{< code file="System.out-array" language="java" options="" >}}

## Cómo ordenar los elementos de una colección

Las clase [Collections](javadoc11:java.base/java/util/Collections.html) es el equivalente de la clase _Arrays_ para las colecciones, también tiene métodos de utilidad en este caso para las colecciones. Tiene un método [sort](javadoc11:java.base/java/util/Collections.html#sort(java.util.List)) para ordenar los elementos de una lista según el orden natural y para ordenar los elementos de la lista según el criterio de un _Comparator_.

A tener en cuenta que tanto los métodos _sort_ de _Arrays_ como de _Collections_ no devuelven una nueva instancia de _array_ o colección ordenada sino que modifican la instancia de _array_ o colección que se proporciona para ordenar.

{{< code file="List-sort.java" language="java" options="" >}}
{{< code file="Person-sort.java" language="java" options="" >}}
{{< code file="System.out-collection" language="java" options="" >}}

## Invertir el orden

La interfaz _Comprable_ establece un orden ya sea ascendente o descendente según el criterio que implementa, si en un caso se desea el orden inverso del comprador la propia interfaz _Comparator_ permite obtener un _Comparator_ con el orden inverso al de la instancia. También es posible obtener un comprador que ordene las referencias nulas al principio si es que hay alguna en la colección.

{{< code file="Sort-reverse.java" language="java" options="" >}}
{{< code file="System.out-reverse" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaSort" command="./gradlew run" %}}

{{% /post %}}
