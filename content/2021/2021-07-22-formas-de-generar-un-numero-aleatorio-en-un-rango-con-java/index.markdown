---
pid: 588
type: "post"
title: "Formas de generar un número aleatorio en un rango con Java"
url: "/2021/07/formas-de-generar-un-numero-aleatorio-en-un-rango-con-java/"
date: 2021-07-22T19:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "En Java hay varias formas de generar números aleatorios, la clase _Random_ permite generar números aleatorios individuales y desde Java 8 con la adición de _streams_ permite obtener una secuencia de números aleatorios de tamaño determinado o indefinido. La clase _Math_ también permite generar números aleatorios aunque es más recomendable usar la clase _Random_. Finalmente, en caso de querer un identificativo único universal está la clase UUID que genera números aleatorios de 128 bits que se representan mediante caracteres alfanuméricos."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las computadoras hacen el mejor esfuerzo para ser capaces de generar números aleatorios, para ello hacen uso de la entropía de que disponen para obtener aleatoriedad como datos de entrada que recibe por dispositivos de teclado, ratón o red. Generar números aleatorios es útil en ciertas funcionalidades de programación como la criptografía pero también útil en tareas más sencillas como seleccionar un elemento de un array de forma aleatoria u obtener un número aleatorio entre dos cifras.

Todos los lenguajes de programación ofrecen funciones de soporte para generar números aleatorios, el lenguaje Java también puede hacerse de varias formas.

{{< tableofcontents >}}

### Generar números aleatorios en un rango

Java ofrece varias clases y formas para generar números aleatorios, dependiendo de cada una la forma de generar un número aleatorio u obtener un número aleatorio en un rango varía ligeramente.

#### Con la clase Random

La clase [Random](javadoc11:java.base/java/util/Random.html) permite generar números aleatorios con varios métodos según el tipo de datos deseado, en el caso de querer números enteros del tipo _int_ con el método [nextInt](javadoc11:java.base/java/util/Random.html#nextInt(int)) que devuelve números enteros uniformemente distribuidos entre 0 de forma inclusiva y el límite superior indicado de forma exclusiva.

Dada la especificación del método _nextInt_ si se desea un número aleatorio entre un rango distinto que no empiece en el 0 hay que realizar una pequeña operación matemática.

{{< code file="RandomUtil-random.java" language="java" options="" >}}
{{< code file="Main-random.java" language="java" options="" >}}
{{< code file="RandomUtil-random.out" language="plain" options="" >}}

#### Usando un _stream_

En el caso de desear una secuencia de números aleatorios la clase _Random_ ofrece soporte para obtener [un _stream_ en Java 8][blogbitix-17] de enteros que son números aleatorios.

{{< code file="RandomUtil-stream.java" language="java" options="" >}}
{{< code file="Main-stream.java" language="java" options="" >}}
{{< code file="RandomUtil-stream.out" language="plain" options="" >}}

#### Con la clase Math

Es más eficiente usar la clase _Random_ pero otra forma posible de generar números aleatorios es con la clase [Math](javadoc11:java.base/java/lang/Math.html). El método [random](javdoc11:java.base/java/lang/Math.html#random()) de _Math_ devuelve números aleatorios del tipo _double_ entre 0 de forma inclusiva y 1 de forma exclusiva. Para obtener el número aleatorio hay que hacer una multiplicación y conversión a entero.

{{< code file="RandomUtil-math.java" language="java" options="" >}}
{{< code file="Main-math.java" language="java" options="" >}}
{{< code file="RandomUtil-math.out" language="plain" options="" >}}

#### Generar un identificativo único universal

Si se desea generar un identificador único universal para una entidad en vez de un número aleatorio en un rango que tiene posibilidades de repetirse está la clase [UUID](javaoc11:java.base/java/util/UUID.html) que genera número únicos de 128 bits que se presentan con caracteres alfanuméricos.

{{< code file="RandomUtil-uuid.java" language="java" options="" >}}
{{< code file="Main-uuid.java" language="java" options="" >}}
{{< code file="RandomUtil-uuid.out" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaRandom" command="./gradlew run" %}}

{{% /post %}}
