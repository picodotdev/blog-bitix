---
pid: 564
type: "post"
title: "Los conceptos de encapsulación, herencia, polimorfismo y composición de la programación orientada a objetos"
url: "/2021/03/los-conceptos-de-encapsulacion-herencia-polimorfismo-y-composicion-de-la-programacion-orientada-a-objetos/"
date: 2021-03-31T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:polimorfism.webp"
tags: ["java", "programacion"]
summary: "La programación orientada a objetos es un paradigma adoptado por todos lenguajes modernos y publicados en las últimas décadas. La programación orientada a objetos proporciona una sintaxis en el lenguaje para definir abstracciones que hacen sencillo utilizar conceptos cuya implementación es compleja. La encapsulación, la abstracción, la herencia, el polimorfismo, la composición y la inyección de dependencias son conceptos fundamentales a conocer en la programación orientada a objetos."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Para el desarrollo de software a lo largo del tiempo se han definido varios paradigmas de programación implementados en los lenguajes de programación.

Los primeros lenguajes de programación utilizaban un paradigma imperativo con un conjunto de instrucciones ejecutadas de forma secuencial y organizado en funciones que manipulan datos. Posteriormente, surge el paradigma de programación orientado a objetos en el que el código se organiza en objetos que encapsulan los datos y las funciones que los manipulan. Otro paradigma es la programación funcional donde el código se organiza en funciones puras que dados unos datos de entrada genera un resultado y en vez de cambiar de estado a los datos existentes genera nuevos datos haciendo a los datos inmutables.

Muchos de los lenguajes de programación hacen uso u ofrecen formas de aplicar al mismo tiempo varios de estos paradigmas de programación. Por ejemplo, el lenguaje de programación C aún siendo un lenguaje de programación que hace uso del paradigma imperativo también es posible utilizar conceptos de la programación orientada a objetos o funcionales, aunque el lenguaje en sí no ofrezca abstracciones propias de orientación a objetos o funcionales. El lenguaje de programación Java es orientado a objetos aunque también en el código de los métodos utiliza programación imperativa y con las [novedades incluidas en Java 8][blogbitix-17] con las _lambdas_, _streams_, o [los records en Java 16][blogbitix-560] y el soporte en las estructuras de datos de las colecciones permite utilizar conceptos de la programación funcional.

En la programación orientada a objetos hay varios conceptos que definen este paradigma de programación, la encapsulación de datos, abstracciones de los modelos en los programas, los objetos, clases e instancias, herencia, polimorfismo y composición.

{{< tableofcontents >}}

## Conceptos de la programación orientada a objetos

Los lenguajes de programación orientados a objetos se diferencian de los imperativos en que el propio lenguaje incluye abstracciones y sintaxis específica para el soporte de la programación orientada a objetos.

El lenguaje de programación Java considerado como un lenguaje de programación a objetos incluye palabras reservadas para la definición de clases e interfaces e implementa los conceptos de herencia y polimorfismo.

### Encapsulación

La encapsulación no es un concepto propio de la programación orientada a objetos pero es fundamental, los objetos son la abstracción que proporciona la encapsulación.

La encapsulación consiste en hacer que los datos sean modificados únicamente por las funciones destinadas a tal efecto. La encapsulación permite que los datos conserven un estado válido y consistente, trata de evitar que cualquier código pueda modificar una estructura de datos con el consiguiente problema de generación de inconsistencias.

Se denomina encapsulación porque los datos y sus estructuras de datos no están accesibles de forma directa, sino que para acceder a los datos o manipularlos se ha de realizar a través de las funciones asociadas, los datos están encapsulados.

### Abstracción

La abstracción es el concepto por el que un modelo es creado con las propiedades relevantes a observar. Un programa trata únicamente con las propiedades de un objeto que al programa le interesa. Las clases son la abstracción de los conceptos que maneja la aplicación, pueden ser conceptos que existan en el mundo real pero simplificados al tener únicamente las propiedades relevantes para la aplicación. Las clases también pueden ser conceptos que no tengan una existencia física en el mundo real como una lista de elementos, una dirección IP o un archivo de ordenador.

Un avión es un objeto físico del mundo real con multitud de propiedades, desde su fabricante y modelo, color, tamaño, numero de asientos, ubicación, capacidad de carga, peso, año de diseño y fabricación, materiales de fabricación, altitud, posición GPS, dirección, velocidad y distancia máxima y muchas otras. De todas estas propiedades en una aplicación de gestión de embarque le interesará únicamente las propiedades de los asientos, quizá en otra aplicación para la programación de vuelo le interesa otras propiedades como altitud, posición GPS, dirección, velocidad aeropuerto origen y destino o distancia.

{{< image
    gallery="true"
    image1="image:abstraction.webp" optionsthumb1="650x450" title1="Abstracción en dos modelos diferentes de un objeto"
    caption="Abstracción en dos modelos diferentes de un objeto" source="matiasbeltramone.github.io" >}}

### Objeto, clase e instancia

Los objetos, clases e instancias son conceptos característicos de la programación a objetos. Son la denominación que le dan los lenguajes de programación orientada a objetos para la encapsulación y las abstracciones.

Los objetos y clases encapsulan los datos y definen la colección de funciones que los manipulan. Las clases son la definición de los objetos en tiempo de compilación y las instancias son la creación en tiempo de ejecución de una clase, en tiempo de ejecución un programa puede crear tantas instancias de una clase como desee, al crear la instancia se reserva la memoria para el conjunto de datos de la clase.

A las funciones de las clases en el lenguaje de dominio de la orientación a objetos se les denomina métodos y a los datos propiedades. Esta es la definición de una clase en Java con varias propiedades y varios métodos que manipulan esas propiedades, si otra clase quiere manipular los datos lo ha de hacer a través de los métodos de la clase tal como permitan [los modificadores de acceso de los miembros de la clase][blogbitix-458].

{{< code file="Car-class.java" language="java" options="" >}}

En Java la creación de una instancia de una clase se realiza con la palabra reservada _new_ y utilizando un método especializado únicamente en construir instancias de la clase, un método constructor. 

{{< code file="Car-new.java" language="java" options="" >}}

La invocación de _new_ y un constructor devuelve una referencia a la instancia del objeto a través de la cual se realizan la invocación de sus métodos o también denominado el paso de mensajes.

El formato de la invocación de un método en una instancia de un objeto es el siguiente. La referencia del objeto se separa con un punto del método a invocar, entre los paréntesis se proporciona una lista de argumentos separados por comas. Los argumentos pueden ser referencias a otras instancias de objetos o en Java datos primitivos numéricos.

{{< code file="Car-method.java" language="java" options="" >}}

### Herencia e interfaces

Otro de los conceptos propios de la programación orientada a objetos es la herencia. Al implementar una clase y para reutilizar el código una clase puede extender de otra, heredando el comportamiento de la clase extendida. La relación de herencia entre las clases es una relación de «es-un».

En Java la herencia se realiza con la palabra reservada _extends_. 

{{< code file="Car-inheritance.java" language="java" options="" >}}

Como un coche es un vehículo la referencia de coche se puede asignar a la referencia de vehículo, por el contrario no todos los vehículos son coches y por tanto una referencia de vehículo no se puede asignar directamente a una referencia de coche.

Si se está seguro de que una referencia de vehículo es una referencia de coche es posible hacer la asignación haciendo un _cast_ de la referencia de forma explícita para que el compilador no genere un error de compilación. Para comprobar si una referencia es de una determinada clase se utiliza el operador _instanceof_. Si se realiza un _cast_ y en tiempo de ejecución no se puede realizar porque la referencia no sea asignable a la clase que se hace _cast_ se produce una excepción del tipo [ClassCastException](javadoc11:java.base/java/lang/ClassCastException.html).

{{< code file="Car-cast.java" language="java" options="" >}}

En Java solo se permite heredar de una única clase, no hay herencia múltiple. Sin embargo, si se permite implementar múltiples interfaces. En Java una interfaz es una colección de métodos que una clase que la implementa debe proporciona una implementación de los métodos de la interfaz.

{{< code file="Car-interface.java" language="java" options="" >}}

En Java las clases abstractas no pueden instanciarse pero si ser extendidas por otras clases que no sean abstractas. Una clase al implementar una interfaz ha de proporcionar una implementación para todos los métodos de la interfaz, en caso de no implementar algún método la clase ha de declararse como abstracta con la palabra reservado _abstract_.

{{< image
    gallery="true"
    image1="image:classes-interfaces.webp" optionsthumb1="650x450" title1="Clases, herencia e interfaces"
    caption="Clases, herencia e interfaces" source="matiasbeltramone.github.io" >}}

Desde la versión de Java 8 las interfaces con los métodos _default_ pueden proporcionar implementaciones para métodos.

{{< code file="Math-interface-default-method.java" language="java" options="" >}}

## Ejemplos de clases, herencia e interfaces

{{< code file="Animal-classes.java" language="java" options="" >}}
{{< code file="Shapes-classes.java" language="java" options="" >}}

### Polimorfismo

El polimorfismo es una propiedad por la cual el método invocado varía en función de la clase de la instancia de un objeto. El polimorfismo es una característica única en la programación orientada a objetos, mientras que la encapsulación y herencia es posible conseguirla en lenguajes no orientados a objetos de una manera razonablemente segura el polimorfismo al usar punteros a funciones es propensa a errores. Los los lenguajes orientados lo que proporcionan es un uso sencillo y seguro del polimorfismo ocultando los detalles internos de su implementación de sus punteros a funciones.

{{< image
    gallery="true"
    image1="image:polimorfism.webp" optionsthumb1="650x450" title1="Polimorfismo"
    caption="Polimorfismo" source="matiasbeltramone.github.io" >}}

En una jerarquía de clases que representen diferentes figuras geométricas dos operaciones son el cálculo del área y de la longitud del perímetro. La fórmula matemática depende de la clase de figura. En el caso de un cuadrado y de un círculo las fórmulas para el cálculo del área y perímetro son distintas.

{{< code file="Shape-polymorfism-classes.java" language="java" options="" >}}

La potencia del polimorfismo es que teniendo una referencia de _Shape_ al invocar al método de la operación el método que se ejecuta es el propio de la clase de la instancia en tiempo de ejecución, si _shape_ es un _square_ se llama al método _calculatePerimeter_ o _calculateArea_ de _square_ y si _shape_ es un _circle_ a sus respectivos métodos.

{{< code file="Shape-polymorfism-example.java" language="java" options="" >}}

## Los problemas de la herencia

El principal problema de la herencia es que en ocasiones no es el mecanismo adecuado para reutilizar el comportamiento, ocasionado que al intentar usar herencia provoque un problema exponencial del número de clases posibles.

En un ejemplo de pizzas que tienen ingredientes una posible jerarquía de clases es la siguiente.

{{< code file="Pizza-classes-1.java" language="java" options="" >}}

El problema surge cuando a las pizzas se les añade otro vector adicional de diseño, además de los ingredientes. Si a las pizzas se les añade el tipo de cocina y una clase concreta con las diferentes combinaciones el número de clases crece de forma exponencial con el nuevo vector de diseño, se produce una proliferación de clases que se convierte en un problema de mantenimiento.

{{< code file="Pizza-classes-2.java" language="java" options="" >}}

Con el tipo de masa podría haber sido otro vector de diseño pero en este caso se ha implementado con una relación «tiene-un» en vez de «es-un», la solución para el tipo de cocina es aplicar una relación «tiene-un», esto es, en vez de usar herencia la solución es usar composición.

## Composición

Si la herencia es una relación «es-un» entre dos clases, la composición es una relación «tiene-un» entre dos clases. La composición se produce cuando una clase contiene referencias a instancias de otras clases en sus propiedades. La clase coche contiene una marca, modelo, motor, ruedas, aceite, color, velocidad y velocidad máxima.

{{< code file="Car-composition.java" language="java" options="" >}}

En el problema de las pizzas utilizando herencia la solución es usar composición para la forma de cocinar de las pizzas en vez de herencia.

{{< code file="Pizza-composition.java" language="java" options="" >}}

### Beneficios de la composición

Aunque la herencia es útil y adecuado en algunos casos por regla general se recomienda usar composición sobre herencia por los siguientes beneficios:

* Flexibilidad: con la herencia la reutilización de código está fijado por la herencia. Es posible la circunstancia en que la clase hija solo necesite una parte de los métodos de la clase padre pero con el mecanismo de la herencia la clase hija los hereda todos o que solo una parte de las clases hijas necesitan los métodos adicionales. La herencia es habitualmente no suficientemente flexible.
* Herencia múltiple: la herencia en Java solo permite extender de una única clase padre. Las interfaces y los métodos por defecto suplen en cierta medida la herencia múltiple pero también tienen limitaciones como no poder tener propiedades.
* Evitar duplicidad: aún usando interfaces si no es a través de los métodos por defecto obliga a duplicar código en diferentes clases. La duplicidad de código por norma general es algo a evitar.

## Inyección de dependencias

Los lenguajes de programación ofrecen un mecanismo para construir instancias, en Java es a través de la palabra reservada _new_ y los constructores, las relaciones con otras instancias se establecen pasando sus referencias como argumentos del constructor o con los métodos de la instancia.

[El recolector de basura de Java][blogbitix-463] es el mecanismo de la máquina virtual de Java de liberar la memoria de las instancias cuando ya no hay más referencias accesibles por otras instancias, la recolección de basura se realiza de forma automática liberando al programador de hacer esta tarea de forma explícita lo que hace los programas más sencillos, con menos errores y ofreciendo un rendimiento similar y suficiente para la mayoría de los programas.

Una herramienta muy utilizada son los contenedores, uno de ellos en Java conocidos [Spring][spring], estos permiten delegar en ellos la construcción de las instancias junto con las relaciones con otras instancias a través de la inyección de dependencias.

El contenedor de objetos para la creación de las instancias y el recolector de basura para la liberación de la memoria permiten liberar al programador de realizar estas tareas de forma explícita.

{{< reference >}}
* [Clean Architecture — OOP](https://medium.com/@stoltmanjan/clean-architecture-oop-4c122e6a91e7)
* [Composition over Inheritance (what it is, why use it)](http://marcotroisi.com/composition-over-inheritance/)
* [POO Lección 1: ¿Qué rayos es un paradigma orientado a objetos?](https://matiasbeltramone.github.io/oop-lesson-1/)
{{< /reference >}}

{{% /post %}}