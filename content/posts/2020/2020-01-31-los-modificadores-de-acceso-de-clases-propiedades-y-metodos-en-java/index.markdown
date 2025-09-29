---
pid: 458
type: "post"
title: "Los modificadores de acceso de clases, propiedades y métodos en Java"
url: "/2020/01/los-modificadores-de-acceso-de-clases-propiedades-y-metodos-en-java/"
date: 2020-01-31T17:00:00+01:00
updated: 2020-02-04T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:java-access-specifier.webp"
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Uno de los principios básicos de los lenguajes orientados a objetos es la encapsulación, mediante la cual se garantiza que los datos de una clase solo son modificados por las operaciones apropiadas implementadas en los métodos de sus clases para preservar su invariante, las reglas que define la clase y el estado consistente de su estado.

El acceso a las propiedades y métodos se determina mediante las palabras reservadas de los modificadores de acceso, en Java hay cuatro modificadores de acceso que definen ámbitos de visibilidad de más restrictivos a menos restrictivos:

* `private`: únicamente la clase puede acceder a la propiedad o método.
* `package private` (valor por defecto si no se indica ninguno): solo las clases en el mismo paquete pueden acceder a la propiedad o método.
* `protected`: las clases del mismo paquete y que heredan de la clase pueden acceder a la propiedad o método.
* `public`: la propiedad o método es accesible desde cualquier método de otra clase.

Las clases tienen uno de los modificadores de acceso `public` o `package private` con el mismo significado que en las propiedades y métodos, visibles desde cualquier otro paquete y solo visible desde su propio paquete.

Los modificadores de acceso son una palabra reservada del lenguaje y se colocan delante de la propiedad o método, el modificador de acceso `package private` no tiene palabra reservada se aplica en caso de que no se especifique un modificador de acceso explícitamente.

{{< code file="Foo.java" language="java" options="" >}}

Los ámbitos de visibilidad según el modificador de acceso y el origen de acceso a la propiedad o método son los siguientes.

<table class="table">
   <thead class="table-light">
       <th width="250px">Modificador de acceso</th>
       <th>Misma clase o anidada</th>
       <th>Clase en el mismo paquete</th>
       <th>Clase que hereda en otro paquete</th>
       <th>Clase que no hereda en otro paquete</th>
   </thead>
   <tbody>
       <tr>
           <td>private</td>
           <td>Sí</td>
           <td>No</td>
           <td>No</td>
           <td>No</td>
       </tr>
       <tr>
           <td>default (package private)</td>
           <td>Sí</td>
           <td>Sí</td>
           <td>No</td>
           <td>No</td>
       </tr>
       <tr>
           <td>protected</td>
           <td>Sí</td>
           <td>Sí</td>
           <td>Sí</td>
           <td>No</td>
       </tr>
       <tr>
           <td>public</td>
           <td>Sí</td>
           <td>Sí</td>
           <td>Sí</td>
           <td>Sí</td>
       </tr>
   </tbody>
</table>

En este gráfico hay representados paquetes que contienen clases, clases con rectángulos, las flechas indican herencia entre clases y las clases que están coloreadas indican que tienen visibilidad de la propiedad y método según el ámbito de acceso, la ubicación de la clase que accede y si hay una relación de herencia. En esencia es la misma información de la tabla pero representada de forma gráfica.

{{< image
    gallery="true"
    image1="image:java-access-specifier.webp" optionsthumb1="600x450" title1="Modificadores de acceso en Java"
    caption="Modificadores de acceso en Java" source="wikipedia.org" >}}

Una detalle a tener en cuenta es que los modificadores afectan a las clases, una propiedad privada de una clase es accesible para todas las instancias de esa clase. En concreto, este código es válido y el resultado es _two_, _bar1_ puede acceder a la propiedad _thing_ de la instancia _bar2_ aún siendo privada en la clase.

{{< code file="Bar.java" language="java" options="" >}}

No hay encapsulación entre las instancias de la misma clase, la encapsulación es para la clase accedida desde otras clases según los modificadores de acceso. Pero aunque una instancia tenga acceso y capacidad de modificar las propiedades de otra instancia se ha de seguir manteniendo la invariante, si una instancia modifica los datos de otra instancia ha de seguir manteniendo la invariante de la clase de esa otra instancia. Para mantener la invariante y la encapsulación generalmente se proporciona acceso a un método y no directamente a las propiedades.

## La modularidad a partir Java 9

Los ámbitos de visibilidad es un mecanismo bastante limitado ni es suficiente para proporcionar encapsulación. No hay ningún impedimento a que cualquiera pueda crear una clase en un paquete que contiene clases privadas de paquete o métodos _package private_ o heredar de esas clases y de esta manera tener acceso a clases, métodos y propiedades que el autor original no las diseñó para esos propósitos. Puede ser incluso un problema de seguridad.

[La modularidad añadida en Java 9][blogbitix-263] viene a complementar y dar una solución más completa a los ámbitos de visibilidad así como garantizar mejor la encapsulación tal y como el programador del paquete original ha diseñado.

{{< reference >}}
* [Java syntax](https://en.wikipedia.org/wiki/Java_syntax)
* [Class invariant](https://en.wikipedia.org/wiki/Class_invariant)
{{< /reference >}}

{{% /post %}}
