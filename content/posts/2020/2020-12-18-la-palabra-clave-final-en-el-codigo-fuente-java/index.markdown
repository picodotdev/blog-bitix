---
pid: 540
type: "post"
title: "Para qué sirve la palabra clave final en el código fuente Java"
url: "/2020/12/para-que-sirve-la-palabra-clave-final-en-el-codigo-fuente-java/"
date: 2020-12-18T18:00:00+01:00
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

Algunas personas y en algunos proyectos Java añaden en la declaración de cada parámetro de un método o variable local la palabra reservada _final_.

La palabra _final_ tiene su utilidad pero hacerlo de forma indiscriminada y en la totalidad de cada parámetro y variable local de cada método hace más verboso el código sin proporcionar una utilidad significativa porque Java ya en ciertas circunstancias considera a muchas variables efectivamente como _final_, si al escribir el código se sigue la recomendación de no hacer nunca asignaciones sobre variables que son parámetros y se prefiere crear una nueva variable local en vez de modificar el valor de una existente.

## ¿Qué hace la palabra clave _final_?

La palabra clave reservada _final_ hace que el compilador genere un error de compilación cuando a una variable _final_ se le intenta asignar un nuevo valor. El error de compilación quizá en algún caso evita un _bug_ ya que no debería haber intención de asignar un nuevo valor a una variable _final_.

La palabra clave reservada _final_ se usa en diferentes contextos:

* En la declaración de variables: permite declarar constantes, no se puede asignar un nuevo valor a la variable una vez inicializada.
* En la declaración de un método: en este contexto una clase que herede un método _final_ no puede redefinirlo en la clase hija, no se puede hacer un _override_.
* En la declaración de una clase: impide extender de la clase.

Las tres formas de inicializar una variable declarada como _final_ son:

* En su declaración.
* En el constructor.
* En el contexto estático si la variable es estática.

Los constructores no pueden declararse como _final_ ya que no se heredan.

{{< code file="Bike.java" language="java" options="" >}}
{{< code file="Honda.java" language="java" options="" >}}

## ¿Debería añadirse la palabra clave _final_ a cada uno de los argumentos de un método y variables locales?

En teoría, sí. En la práctica, no. Sólo es útil en métodos cuyo código es largo y complicado, y en ese caso el método debería simplificarse, o cuando el argumento tiene la posibilidad de confundirse con una variable local o miembro de la clase con la posibilidad de reasignación.

Aunque un argumento de un método se declare como _final_ e impide asignarle un valor no se impide modificar su estado si esa variable es una referencia a un objeto como es modificar los elementos de una colección. Para evitar modificaciones en colecciones hay que hacerlas inmutables.

La palabra _final_ en un argumento sirve para capturar un error en el que el nombre de la variable del argumento se llama igual que una propiedad de la clase en un constructor o un _setter_, pero usarlo de forma indiscriminada para todos los argumentos puede hacer el código aún más verboso sin proporcionar nada significativo, en los constructores y métodos _setter_  puede ser útil como en el siguiente caso pero en el resto de métodos no es significativamente útil con el coste de legibilidad del código que genera.

{{< code file="Bike-setter.java" language="java" options="" >}}

Aunque una variable no se indique como _final_ Java la considera efectivamente como _final_ si se dan las siguientes circunstancias en el caso de que tenga inicializador:

* No está declarada como _final_.
* No aparece en la parte izquierda de una expresión de asignación (la asignación en la declaración no se considera una expresión de asignación).
* No aparece nunca como un operando de un operador _pre_ o _post_ incremento o decremento.

Una variable que no tenga inicializador se considera efectivamente como _final_ si:

* No está declarada como _final_.
* Cuando aparece como una expresión de asignación, está sin asignar.
* No aparece nunca como un operando de un operador _pre_ o _post_ incremento o decremento.

Si una variable es efectivamente _final_ añadir el modificador _final_ no genera ningún error en tiempo de compilación. Una variable o parámetro que es declarado como _final_ en un programa válido se convierte en efectivamente _final_ si se elimina el modificador _final_.

También suele comentarse que una variable _final_ ofrece mejor rendimiento, la diferencia de rendimiento no es significativa y si el rendimiento del programa depende de optimizarlo con palabras clave _final_ seguramente haya muchas otras cosas a mejorar primero que el uso de palabras reservadas _final_. Otra justificación para el uso indiscriminado de la palabra _final_ es en programas concurrentes con varios hilos con la intención de que los hilos no vean objetos parcialmente construidos, casos avanzados en los que quizá las primitivas de sincronización sea más adecuado.

* [The Java final Keyword – Impact on Performance](https://www.baeldung.com/java-final-performance)

{{< reference >}}
* [Final Keyword In Java](https://www.javatpoint.com/final-keyword)
* [final keyword in java](https://www.geeksforgeeks.org/final-keyword-java/)
* [Does use of final keyword in Java improve the performance?](https://stackoverflow.com/questions/4279420/does-use-of-final-keyword-in-java-improve-the-performance)
* [Excessive use “final” keyword in Java](https://softwareengineering.stackexchange.com/questions/98691/excessive-use-final-keyword-in-java)
* [Final Keyword and JVM Memory Impact](https://dzone.com/articles/final-keyword-and-jvm-memory-impact)
* [Thread-safety with the Java final keyword](https://www.javamex.com/tutorials/synchronization_final.shtml)
{{< /reference >}}

{{% /post %}}
