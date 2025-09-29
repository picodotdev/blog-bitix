---
pid: 612
type: "post"
title: "Buenas prácticas de programación sencillas en el código fuente"
url: "/2021/12/buenas-practicas-de-programacion-sencillas-en-el-codigo-fuente/"
date: 2021-12-09T23:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Al escribir código uno de las principales objetivos además de que funcione es tan importante que sea código legible ya que la mayor parte del tiempo en la programación generalmente no se emplea a escribir código nuevo sino a mantener código existente. Las prácticas de este artículo para escribir código más legible son sencillas de comprender y de aplicar en cualquier lenguaje de programación."
---

{{% post %}}

Tan importante que un programa funcione y esté libre de errores es que el código fuente del programa sea legible y fácil de entender. Esto permite que en caso de un error el análisis del código fuente sea más sencillo, incluso si es código escrito por uno mismo, cuando pasan varios meses e incluso años desde que se escribió el código uno ya no se acuerda de ninguno de sus detalles y hay que analizarlo como si lo hubiese escrito otra persona.

La legibilidad afecta a la facilidad de cambio del código fuente y a la facilidad de corrección de errores y tiempo que se tarda en resolverlos. En realidad la mayor parte del tiempo dedicado a la programación no es a la escritura de nuevas líneas de código fuente si no a la lectura de las existentes, por ello al escribir código este debería ser optimizado para la lectura para la mayoría de ámbitos y aplicaciones más que a la eficiencia. Que un programa tenga menos líneas de código fuente o un lenguaje necesite menos líneas para expresar lo mismo no implica que sea más legible.

A veces para escribir un buen código fuente y código legible no es necesario ni tiene que ver con utilizar patrones de diseño que en realidad añaden complejidad o complejas arquitecturas de software por capas o hexagonal, para escribir buen código fuente bastan unas pocas técnicas y prácticas  muy sencillas y fácilmente comprensibles. Además, estas prácticas son aplicables a cualquier lenguaje de programación, los patrones de diseño y las arquitecturas de software aunque útiles resuelven problemas de complejidad y extensibilidad que son problemas en primera instancia diferentes a la legibilidad del código fuente.

Estás prácticas son simplemente recomendaciones a seguir como normal general, no son reglas estrictas que si no se aplican en todos los casos y siempre significa que el código esté completamente mal. Todas estas prácticas son sencillas y aplicables a cualquier lenguaje de programación.

{{< tableofcontents >}}

## Un nivel de indentación por método

Que un método tenga varios niveles de indentación anidados normalmente significa que las varias anidaciones realizan una tarea cuyo objetivo hay que inferir analizando el código. Además, los varios niveles de anidación al leer el código exige recordar la tarea de cada uno de los bloques. Un simple bucle anidado con dos _for_ ya exige un esfuerzo significativo para analizar qué hace. Para facilitar la lectura y evitar los niveles de anidación se recomienda que cada método tenga como máximo un único nivel de anidación.

{{< code file="NestedLoop.java" language="java" options="" >}}

El _refactor_ a aplicar es crear tantos métodos como sea necesario para que cada uno solo tenga un nivel de indentación. Esto tiene dos ventajas, por un lado el número de líneas de cada método será más pequeño que el único método original y por tanto más fácil de comprender, y por otro lado al tener que asignar un nombre al método hacer que este describa qué función realiza el código.

{{< code file="UnnestedLoop.java" language="java" options="" >}}

## No usar la palabra clave del condicional _else_

Las sentencias condicionales son sentencias de control de flujo del programa que permiten ejecutar uno u otro bloque de código en función de una condición. Tener dos bloques de código que hacen cosas diferentes ofuscan cual es el camino que sigue el programa, cuando en el flujo del programa se añaden más combinaciones el número de caminos posible capaz de tomar el programa crece rápidamente, simplemente con dos sentencias _if_ anidadas el número de combinaciones son cuatro. Por otro lado, si el código de los bloques _if_ y _else_ son grandes impide visualizar ambos bloques al mismo tiempo lo que obliga a ejercitar la mente para recordar las líneas de código de cada uno en el análisis.

* [Evitar niveles de anidación de sentencias condicionales con «guard clauses»][blogbitix-267]

{{< code file="IfElse.java" language="java" options="" >}}

Algunos bloques _else_ de las sentencias condicionales _if_ son evitables usando cláusulas de guarda o _guard clauses_.

{{< code file="GuardClause.java" language="java" options="" >}}

## Encapsular los datos primitivos

En un lenguaje orientado a objetos todos los métodos han de estar encapsulados en una clase. Los métodos manipulan los datos de la instancia de la clase lo que proporciona la encapsulación y los beneficios de la orientación a objetos. Cuando no existe una clase en la que añadir un método surgen los métodos que se insertan en una clase de utilidades, estos métodos suelen definirse como estáticos y está junto a otros lo que ocasiona baja cohesión y una agrupación de métodos no relacionados.

Para evitar crear métodos de utilidad y proporcionar una clase en la que insertar los métodos que manipulan datos la opción es crear una clase. En vez de trabajar con tipos primitivos de datos como un _String_ o un _Long_ que no proporcionan información del dominio del que trata la aplicación la opción es crear una clase _Address_, _Telephone_, _Identifier_,  _Email_, ...

Estas clases de dominio proporcionan dos ventajas, una que es el lugar en el que insertar los métodos que manipulan las propiedades y proporcionan validación de tipos. En la clase _Email_ se insertan los métodos que manipulan direcciones y el compilador valida que un método recibe una dirección en vez de un _String_ que podría ser cualquier dato como un nombre.

{{< code file="EncapsulatedPrimitives.java" language="java" options="" >}}

## Encapsular las colecciones

En el mismo sentido que encapsular datos primitivos en clases que representan conceptos del lenguaje de dominio, es añadir las colecciones en una clase sin ningún otro dato de instancia para insertar los métodos que manipulan la colección.

En este ejemplo teniendo una colección de direcciones y teniendo la necesidad de conocer cuál de ellas representa la dirección principal, en el caso de que la colección no tenga su propia clase el método para obtener la dirección principal si se ubica la clase _User_ el método queda enlazado con la clase _User_ cuando debería estar asociado a la colección de direcciones.

{{< code file="Collections.java" language="java" options="" >}}

La solución es crear una clase que represente la colección y entonces sí es posible crear el método asociado a la colección de direcciones.

{{< code file="FirstClassCollections.java" language="java" options="" >}}

## Un punto por línea de código

En los lenguajes de programación como Java el operador punto permite encapsular y acceder a miembros de una clase como propiedades y métodos. Cuando una misma línea de código utiliza varias veces el operador punto es posible que haya un problema de encapsulación de datos.

En vez de permitir que un tercero manipule un dato propio de la clase, para mantener la encapsulación la manipulación se ha de hacer a través de la clase. En este ejemplo la clase _User_ no encapsula correctamente las direcciones como se refleja en la clase controlador.

{{< code file="MultipleDot.java" language="java" options="" >}}

Esto se consigue evitando los métodos _getter_ y _setter_ proporcionando métodos más específicos para las operaciones.

{{< code file="OneDot.java" language="java" options="" >}}

## Evitar abreviaturas

Las abreviaturas permiten ahorrar teclear algunos caracteres cada vez que se utiliza la versión abreviada de la palabra. Sin embargo, las abreviaturas tienen el problema de que dificultan la legibilidad del código, y el código debería ser optimizado no para ser escrito sino para ser leído. Con los entornos integrados de desarrollo que proporcionan asistencia de código en la escritura en muchos casos escribir cuesta lo mismo que escribir la versión abreviada y no abreviada de una variable o método.

Las excepción a esta regla son aquellas abreviaturas que están ampliamente aceptadas como las variables _i_ y _j_ como los contadores en una iteración, _it_ par el dato de la _lambda_ o _min_ y _max_ para para indicar el máximo o mínimo.

## Mantener las clases pequeñas

Cuando una clase es muy grande es más difícil de entender y de mantener. Si una clase supera cierta cantidad de líneas de código es muy posible que pueda ser dividida en una o más clases más pequeñas o dividir un método de muchas líneas en varios más pequeños.

## Evitar los métodos _getter_ y _setter_

Los métodos _getter_ y _setter_ impiden mantener la encapsulación, las clases deben reflejar el dominio según _Domain Driven Design_. Estos métodos además impiden mantener la encapsulación de los datos y da lugar a clases anémicas que únicamente contiene propiedades y métodos _get_ y _set_.

En este ejemplo para incrementar el saldo de una cuenta con métodos _get_ y _set_ un código como el siguiente no tiene ninguna operación de dominio. La operación para incrementar el saldo no está encapsulada en la clase _Account_, en cualquier otra parte del código que necesite realizar la operación de incrementar el saldo hay que realizar la misma operación. Además, de no ser muy legible la operación de actualización del saldo de la cuenta en la clase controlador, se encadena varias operaciones con el operador punto.

{{< code file="GetterSetter.java" language="java" options="" >}}

Añadiendo un método en la clase _Account_ para incrementar el saldo el código es más legible. Además, el nuevo método _add_ sería el lugar adecuado para insertar una validación por ejemplo para requerir que al invocar la operación la cantidad sea un valor positivo.

{{< code file="DomainMethod.java" language="java" options="" >}}

## Revisar las dependencias de las clases

Si una clase tiene muchas dependencias es muy posible que se convierta en una clase compleja y que realice varias tareas no relacionadas con poca cohesión. Una clase no debería tener muchas dependencias, una forma sencilla y rápida es analizar los _imports_ de otras clases que utiliza una clase. Si tiene demasiados _imports_ igual hace demasiadas cosas o tiene dependencias con cosas que no debería, por ejemplo una clase de la capa de dominio no debe tener dependencias de infraestructura.

Hay herramientas que permiten revisar las dependencias de forma automatizada para precisamente comprobar que las clases de un paquete no utilicen las de otros paquetes no deseados. Una de ellas es [PMD][pmd] y otra [Checkstyle][checkstyle].

* [Análisis estático de código con PMD y un ejemplo][blogbitix-297]

## Segregar los cambios en los _commits_

Utilizar una herramienta de control de versiones permite conservar todo el historial de cambios del código fuente, por otro lado permite a varias personas colaborar y compartir los cambios unos con otros. Para hacer más efectivo el uso del historial o la revisión de código es aconsejable que los cambios de cada _commit_ tengan un único objetivo, es preferible crear varios _commits_ con cada acción de cambio que uno solo con todos los cambio con varias cosas mezcladas. Esta segregación hace posible eliminar los cambios no deseados de un _commit_ en concreto y hace más fácil revisar los cambios realizados.

Por ejemplo, al hacer cambios es conveniente separar los cambios que arreglan un error de los cambios de formateo de código. Separar los cambios en diferentes _commits_ requiere algo de planificación en los cambios que se desean hacer y dejar cambios para otro _commit_ si se descubren nuevos cambios. con la herramienta de control de versiones [Git][git] una opción es utilizar _git stash_, otra opción es simplemente anotar un cambio para realizarlo con posterioridad al actual.

{{< reference >}}
* [Object Calisthenics](http://williamdurand.fr/2013/06/03/object-calisthenics/)
{{< /reference >}}

{{% /post %}}
