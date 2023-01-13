---
pid: 574
title: "El patrón de diseño Factory, ventajas sobre new y diferencias con Builder"
type: "post"
url: "/2021/05/el-patron-de-diseno-factory-ventajas-sobre-new-y-diferencias-con-builder/"
date: 2021-05-14T16:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
series: ["java-patron-diseno"]
summary: "El patrón de diseño _Factory_ es uno de los patrones dedicados a la creación de instancias. El patrón _Factory_ proporciona varias ventajas sobre la palabra reservada _new_ que proporcionan los lenguajes de programación orientada a objetos para la creación de instancias. Es muy utilizado en muchas librerías, en ocasiones también es necesario implementar una clase que implemente este patrón por lo que es muy útil conocer y usar este patrón en las ocasiones que sea adecuado."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

El patrón de diseño factoría o _Factory_ es uno de los más empleados en multitud de ocasiones en el código propio y aunque no se implemente al ser usado en multitud de librerías también en muchos casos conviene conocer sus principios y ventajas frente a otros métodos.

El patrón de diseño _Factory_ es uno de patrones ya identificados como útiles en los que su aplicación es adecuada. Los patrones de diseño se clasifican en las áreas funcionales de creacionales dedicados a la creación de objetos, de comportamiento centrados en la comunicación entre objetos, estructurales para mantener de forma sencilla relaciones entre entidades y finalmente los patrones de concurrencia empleados en la aplicación concurrente con múltiples hilos de ejecución.

{{< tableofcontents >}}

### Las limitaciones de instanciar objetos con la palabra reservada _new_

Los lenguajes de programación orientada a objetos modelan los conceptos que trata una aplicación mediante clases que define las propiedades del objeto y los métodos que permiten el acceso o modifican el estado del objeto, esto proporciona encapsulación y es uno de los [conceptos fundamentales de la programación orientada a objetos][blogbitix-564] entre los que también se encuentran el polimorfismo, herencia y composición.

{{< code file="Shape.java" language="java" options="" >}}
{{< code file="Circle.java" language="java" options="" >}}
{{< code file="Square.java" language="java" options="" >}}

Las clases son una definición de las entidades de una aplicación en tiempo de compilación, en tiempo de ejecución un programa crea instancias individuales de las clases. En el lenguaje de programación Java para la creación de instancias de objetos se usa la palabra reservada _new_. Al emplear esta palabra reservada el lenguaje en tiempo de ejecución devuelve una referencia a la instancia creada, habitualmente se asigna la referencia a una variable.

{{< code file="Main-new.java" language="java" options="" >}}

La palabra reservada _new_ o una equivalente es el mecanismo que emplean muchos lenguajes de programación orientados a objetos como Java y C#. Al ser un mecanismo proporcionado por el lenguaje es sencillo de utilizar en la mayoría de ocasiones.

Aunque la palabra reservada _new_ es una opción válida para crear instancias tiene algunas limitaciones que en algunas ocasiones requieren una alternativa.

Una de sus limitaciones es que la palabra reservada _new_ siempre devuelve una instancia del tipo concreto que explícitamente se crea, a veces el tipo concreto de la instancia no se conoce en tiempo de compilación, por ejemplo en tiempo de compilación se sabe que se necesita un _Shape_ pero solo hasta en tiempo de ejecución no se sabe si la instancia a construir es un _Square_ o un _Circle_.

Otra limitación es que el código que usa _new_ tiene la responsabilidad de crear las instancias, a veces interesa delegar esta responsabilidad en otra clase para no repetirla múltiples veces.

Para evitar las las limitaciones de la palabra _new_ se suele emplear alguno de los patrones creacionales, como el patrón de diseño _Factory_.

### El patrón de diseño Factory

El patrón _Factory_ es un patrón de diseño creacional que tiene como función crear instancias de objetos sin las limitaciones de la palabra reservada _new_. El patrón _Factory_ solventa las dos limitaciones comentadas de _new_, pudiendo crear diferentes tipos de instancias que implementen una interfaz o hereden de una clase común, al mismo tiempo el código de creación de las instancias queda encapsulado en la clase que implementa el patrón _Factory_ abstrayendo al código que lo usa de la responsabilidad de creación de instancias.

La creación de instancias es una de las tareas más comunes que realiza un programa, de modo que habitualmente es necesario implementar una clase factoría o en caso de usar una librería utilizar una factoría implementada por una clase de la librería. Un contenedor de IoC y su inyección de dependencias que también tienen como misión delegar en ellos la creación de instancias requieren implementar una factoría propia que el contenedor invoca.

El patrón _Factory_ es tan simple como una clase con uno o varios métodos que devuelven la instancia que la factoría crea, el método de la factoría puede recibir parámetros. El método factoría puede ser estático si la creación de las instancias depende de únicamente los parámetros que recibe el método o el método puede ser de instancia y estar basado además de los parámetros que recibe en propiedades de la instancia de la factoría.

El patrón _Factory_ hay dos categorías: _Factory Method Pattern_ que se basan en un único método y _Abstract Factory Pattern_ que son una indirección más pudiendo sustituir una implementación de factoría por otra haciendo posible devolver cada una diferentes instancias.

Como muchos patrones de diseño añade cierta complejidad en el diseño de las clases del programa con lo que su uso debe estar justificado con el objetivo de simplificar el código o la necesidad de evitar las limitaciones de la palabra _new_.

{{< image
    gallery="true"
    image1="image:factory-pattern.webp" optionsthumb1="650x450" title1="Diagrama de clases del patrón de diseño Factory"
    caption="Diagrama de clases del patrón de diseño Factory" >}}

#### Diferencias con el patrón de diseño _Builder_

El patrón de diseño _Builder_ es otro patrón creacional dedicado a la creación de instancias, aunque comparte objetivos las implementación es diferente del patrón _Factory_.

Una diferencia entre el patrón _Factory_ y el patrón _Builder_ es que el patrón _Factory_ crea la instancia en un único paso con la invocación de un método de la factoría que lo devuelve inmediatamente, el patrón _Builder_ suele requerir la invocación de varios métodos y un método final _build_ que realiza la creación de la instancia con una API fluída.

Los _Builder_ son objetos con estado y requieren crear una instancia de _Builder_, el patrón _Factory_ no requiere crear una instancia y se puede compartir entre varios objetos que la necesitan.

Por el contrario el patrón _Builder_ proporciona más control sobre los pasos de la creación de la instancia y proporciona más flexibilidad para variar la representación interna de la instancia creada. Otra diferencia es que el _Builder_ crea instancias con diferente composición de objetos.

### Ejemplo de patrón de diseño _Factory_

En el siguiente ejemplo de implementación en Java de patrón _Factory Method_  se observa que el método factoría en función del parámetro se devuelve una instancia u otra empleando una sentencia _if-else_. Esta factoría además contiene otros dos métodos de factoría específicos para tipos concretos de _Shape_.

{{< code file="ShapeFactory.java" language="java" options="" >}}

La sentencia _if-else_ hace que no se cumpla el principio _O_ (_Open-closed_) de SOLID, si se añade un nuevo tipo requiere modificar el código del método en vez de proporcionar una extensión. Para [evitar la sentencia _if-else_ en una factoría][blogbitix-104] hay varias opciones.

Con la incorporación de los _default methods_ en Java el método factoría es posible implementarlo en una interfaz de _Shape_ no requiriendo una clase _ShapeFactory_ dedicada que lo contenga.

El siguiente programa crea una figura según el parámetro indicado que solo se conoce en tiempo de ejecución según el argumento proporcionado al lanzar el programa y una instancia de cada tipo de figura, se observa que la clase _Main_ no utiliza directamente los constructores de ninguna figura.

{{< code file="Main-factory.java" language="java" options="" >}}

{{< reference >}}
* [Patrones de diseño creacionales](https://en.wikipedia.org/wiki/Software_design_pattern#Creational_patterns)
* [Patrón de diseño Factoría](http://www.oodesign.com/factory-pattern.html)
* [Software Design Patterns: Factory and Builder in a Nutshell](https://medium.com/javarevisited/design-patterns-101-factory-vs-builder-vs-fluent-builder-da2babf42113)
* [Everything You Need to Know About Factory and Builder Design Patterns](https://dzone.com/articles/everything-you-need-to-know-about-factory-and-buil)
* [What is the difference between Builder Design pattern and Factory Design pattern?](http://stackoverflow.com/questions/757743/what-is-the-difference-between-builder-design-pattern-and-factory-design-pattern)
{{< /reference >}}

{{% /post %}}
