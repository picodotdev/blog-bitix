---
pid: 579
type: "post"
title: "Implementación de los algoritmos de ordenación bubble sort, merge sort y quicksort en Java"
url: "/2021/06/implementacion-de-los-algoritmos-de-ordenacion-bubble-sort-merge-sort-y-quicksort-en-java/"
date: 2021-06-03T19:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Hay una buena cantidad de algoritmos de ordenación conocidos, entre los más conocidos está el _bubble sort_, el _merge sort_ y el _quicksort_. No es imprescindible conocerlos todos ni implementarlos ya que las librerías y clases del JDK ya los implementan. Sin embargo, son utilizados como ejemplo para implementar un algoritmo al empezar a programar en un lenguaje de programación."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Por norma general no es necesario implementar ningún algoritmo de ordenación, estos ya están implementados en las bibliotecas y en el caso de Java en las clases de su JDK. Lo único que es necesario implementar es una clase que implemente la interfaz [Comparable](javadoc11:java.base/java/lang/Comparable.html) o [Comparator](javadoc11:java.base/java/util/Comparator.html), esto es suficiente para [ordenar arrays y colecciones de objetos en Java][blogbitix-577].

Sin embargo, por temas didácticos los algoritmos de ordenación son utilizados como ejemplo para aprender a programar, no son complejos una vez entendido su funcionamiento.

Entre los algoritmos más conocidos están el de ordenación burbuja o _bubble sort_, _merge sort_  y _quicksort_. Cada uno tiene diferentes propiedades.

{{< tableofcontents >}}

### Propiedades de los algoritmos de ordenación

En función de las propiedades del algoritmo y el conjunto de datos a ordenar o su número un algoritmo es más adecuado que otro. Por ejemplo, el algoritmo _bubble sort_ no es el más rápido pero es estable y funciona por intercambio no requiriendo más memoria adicional que una variable temporal. Sin embargo, el algoritmo _bubble sort_ no es paralelizable y hay algoritmos más rápidos.

Teniendo en cuenta las propiedades de los algoritmos para colecciones pequeñas el algoritmo _bubble sort_ puede ser el más adecuado pero para colecciones de datos grandes los algoritmos _merge sort_ y _quicksort_ son más adecuados.

Dado que el algoritmo de ordenación más adecuado puede depender de variables como el número de datos a ordenar o el número de procesadores del sistema muy posiblemente la implementación de una función de utilidad de ordenación las tenga en cuenta para emplear un algoritmo u otro en vez de siempre el mismo.

Un algoritmo de ordenación se clasifica según las siguientes propiedades:

* Complejidad computacional: es la complejidad del algoritmo medida según el número de operaciones que necesita realizar, se utiliza la [notación Big O][wikipedia-big-o].
* Uso de memoria: es la cantidad de memoria que necesita el algoritmo para realizar la ordenación. Los algoritmos _in-place_ que realizan la ordenación en la misma colección solo necesitan una posición de memoria para realizar el intercambio.
* Recursividad: algunos algoritmos son recursivos o no recursivos, mientras otros una parte es recursiva y otra no.  Este último caso es el de _merge sort_ que es una parte recursiva y otra no.
* Estabilidad: los algoritmos de ordenación estables mantienen el orden en el que aparecen los elementos en la colección para aquellos que son considerados iguales.
* Método general: puede ser por inserción, intercambio, selección, fusión, ... Los algoritmos de intercambio incluyen _bubble sort_ y _quicksort_.
* Si el algoritmo es en serie o paralelo.
* Adaptabilidad: la ordenación de los elementos afecta al tiempo de ejecución, los algoritmos que tienen en cuenta esto son adaptativos.
* _Online_

### Algoritmo _bubble sort_

El [algoritmo de burbuja](https://en.wikipedia.org/wiki/Bubble_sort) o _bubble sort_ dada una colección de elementos compara los dos primeros elementos de la colección y los intercambia en función de su orden si es necesario.

A continuación realiza la comparación para el segundo y tercer elemento de la colección y los intercambia en función de su orden.

Este proceso se repite hasta llegar al último elemento de la colección, como resultado se tiene que en la última posición de la colección estará el elemento con mayor valor.

El proceso se repite de nuevo comenzando desde la primera posición  de la colección sin incluir la posición del elemento ya ordenado anteriormente, como resultado dará al siguiente elemento de mayor orden. Se repite esta ordenación tantas veces como elementos tenga la colección menos uno.

Esta es una interfaz que define un método para ordenar una colección, se proporciona la colección y una clase _Comparator_.

{{< code file="SortAlgorithm.java" language="java" options="" >}}

El programa Java crea una colección de 25 elementos con un valor aleatorio entre 0 y 100, y los ordena con cada uno de los algoritmos.

{{< code file="Main.java" language="java" options="" >}}

Esta es la implementación del algoritmos de burbuja.

{{< image
    gallery="false"
    image1="image:bubble-sort.gif" optionsthumb1="300x250" title1="Algoritmo bubble-sort"
    caption="Algoritmo bubble-sort" >}}

{{< code file="BubbleSort.java" language="java" options="" >}}

### Algoritmo de ordenación _merge sort_

El [algoritmo _merge sort_](https://en.wikipedia.org/wiki/Merge_sort) comienza con una fase de dividir listas, se divide la colección en dos partes del mismo número de elementos o una parte con elemento más que la otra si el número de elementos es impar. La división se aplica recursivamente hasta que las listas sean de un único elemento.

Una vez divididas las listas en elementos individuales comienza la fase de _merge_ donde los elementos se juntan tomando de cada lista el elemento que sea menor hasta que las listas ya no tengan más elementos.

La fase de _merge_ termina cuando se tenga una única lista con los elementos ordenados.

{{< image
    gallery="false"
    image1="image:merge-sort.gif" optionsthumb1="300x250" title1="Algoritmo merge-sort"
    caption="Algoritmo merge-sort" >}}

{{< code file="MergeSort.java" language="java" options="" >}}

### Algoritmo de ordenación _quicksort_

El [algoritmo _quicskort_](https://en.wikipedia.org/wiki/Quicksort)  selecciona un elemento como pivote de la colección. A continuación divide la colección en dos listas de elementos, los que tienen un valor inferior al valor de pivote y los que tiene un valor superior al valor de pivote.

A continuación se aplica la ordenación a cada una de las listas de forma recursiva, hasta que las listas no ordenadas tengan menos de dos elementos.

{{< image
    gallery="false"
    image1="image:quicksort.gif" optionsthumb1="300x250" title1="Algoritmo quicksort"
    caption="Algoritmo quicksort" >}}

{{< code file="QuickSort.java" language="java" options="" >}}
{{< code file="System.out" language="plaintext" options="" >}}

### Otros algoritmo de ordenación

Los anteriores no son los únicos algoritmos conocidos para realizar ordenación, el la página de la wikipedia sobre [algoritmos de ordenación](https://en.wikipedia.org/wiki/Sorting_algorithm) hay muchos otros con una tabla de información acerca de su complejidad en el mejor de los casos, promedio y en el peor de los casos, su consumo de memoria, si es estable y el método de ordenación empleado.

{{< image
    gallery="true"
    image1="image:comparison-sort-algorithms.png" optionsthumb1="650x450" title1="Comparación de algunos algoritmos de ordenación"
    caption="Comparación de algunos algoritmos de ordenación" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaSort" command="./gradlew run" %}}

{{% /post %}}
