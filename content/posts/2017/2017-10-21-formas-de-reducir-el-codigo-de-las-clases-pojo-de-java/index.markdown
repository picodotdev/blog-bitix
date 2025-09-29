---
pid: 272
type: "post"
title: "Formas de reducir el código de las clases POJO de Java"
url: "/2017/10/formas-de-reducir-el-codigo-de-las-clases-pojo-de-java/"
aliases: ["/2017/10/implementar-funcionalidades-comunes-en-java-con-la-libreria-lombok/"]
date: 2017-10-21T11:00:00+02:00
updated: 2020-02-15T20:45:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "La clases POJO o _beans_ de Java son clases que en su mayor parte únicamente tiene la definición de sus propiedades. En Java al ser un lenguaje que promueve el ser explícito requiere definir mucho código que aporta poco valor. Cuando el número de estas clases simples es grande el código aún siendo simple se convierte en un problema de mantenimiento. Las librerías Immutables, Lombok y los _records_ de Java incorporados en la versión 14 permite reducir en gran medida esa cantidad de código, mantener la consistencia y adicionalmente proporcionar clases que implementan el patrón _Builder_."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Java es un lenguaje verboso además requerir realizar las definiciones de forma explícita y con poco azúcar sintáctico (o como diría algún otro, poco veneno para ratas), esto hace que el número de líneas de código necesarias sea mayor que en otros lenguajes. Que Java sea verboso, explícito y con poco azúcar sintáctico no es necesariamente un inconveniente ya que la mayor parte del tiempo los programadores la dedicamos a leer código ya escrito, sin embargo, a mayor número de líneas mayor tiempo se requiere en mantenerlas.

Un ejemplo claro está en las clases POJO que sieguen las convenciones de los _beans_ con múltiples propiedades donde por cada propiedad es necesario definir un método _get_ y un método _set_ además [implementar de forma correcta los métodos _equals_ y _hashCode_][blogbitix-199], el método _toString_, utilizar el [patrón builder][blogbitix-99], múltiples constructores con combinaciones de propiedades o comprobaciones para valores no nulos en parámetros.

Aunque los entornos integrados de desarrollo como [IntelliJ][intellij] o [eclipse][eclipse] permiten generar de forma rápida los métodos _getter_, _setter_, _equals_ y _hashCode_ cuando son numerosas las clases a generar de esta forma el tiempo necesario para crearlas y mantenerlo es elevado. Todos estos métodos no son complicados pero dado su número también se convierte en un problema de mantenimiento y legibilidad, además de que mayor dificultad para mantener la consistencia en todas las clases.

Y aunque los IDE tiene la capacidad e generar algunos métodos no tienen la funcionalidad de generar las clases del patrón _Builder_. Implementar un patrón _Builder_ puede complicarse con el número de funcionalidades que se le incorporen.

Para facilitar el mantenimiento y reducir numerosas líneas de código de _boilerplate_ que requiere Java para estas clases, que aún simples sean numerosas y no tengan muchos métodos propios, hay dos librerías utilizables que se encargan mediante anotaciones de generarlas y construir las clases _Builder_ asociadas.

## Librerías para reducir el código de las clase POJO de Java

Es habitual que en una aplicación se necesiten clases como simplemente estructuras de datos y objetos para transferir datos o en el caso de [implementar DDD y algunos patrones tácticos][blogbitix-553] como los _value objects_. Clases que que tienen unas pocas propiedades y son inmutables.

Dos librerías que permiten simplificar estas clases son [Immutables][immutables] y [Lombok][lombok]. Ambas se basan en el uso de anotaciones, aunque la implementación en cada una de ellas es diferente, la implementación de Immutables tiene algunas ventajas.

Las anotaciones de estas librerías permiten escribir menos código repetitivo en _beans_ o objetos de transferencia de datos o DTO pero conviene conocer lo que hacen esas anotaciones. Una de las características por las que algunas personas usan otros lenguajes más recientes como [Groovy][groovy] es que en estos requieren menos líneas código para hacer lo mismo que en Java como el caso de los métodos _get_ y _set_ de las propiedades que en Groovy se proporcionan de forma implícita y en Java de forma explícita, con Immutables o Lombok en Java estos métodos también se pueden proporcionar con anotaciones sin tener que codificarlos.

### Immutables

Immutables es una librería que permite generar clases y eliminar código repetitivo. Funciona a través de la definición de una interfaz de la clase o una clase abstracta, el uso de anotaciones y un procesador de anotaciones. Al realizar la compilación el procesador de anotaciones genera el código fuente de una clase que implementa la interfaz o clase abstracta y la clase _Builder_.

Las clases generadas por defecto son inmutables, _type-safe_, _null-safe_ y _thread-safe_ y se genera una _Builder_ asociada. Permite la personalización de ciertos estilos al generar las clases, soportan serialización con [Jackson][jackson] y [Gson][gson].

La anotación principal es _@Value.Immutable_ que permite definir una clase que implemente la interfaz o clase abstracta indicada junto con su clase _Builder_. Con la anotación _@Value.Style_ se personalizan los valores por defecto de la generación según las convenciones deseadas.

{{< code file="Car-immutables.java" language="java" options="" >}}

### Lombok

Lombok es una librería popular para el propósito de eliminar todo ese código repetitivo necesario en cada _bean_ o clase Java utilizando varias anotaciones. Usarla no requiere ninguna complicación basta añadirla a la lista de dependencias de compilación y las anotaciones serán procesadas.

La implementación de Lombok se realiza modificando la clase anotada para añadirle las implementaciones de los métodos, modifica el archivo _.class_ que se genera al compilar. Por esta forma de instrumentalizar las clases, los IDEs para ser conscientes de los cambios requieren instalar un complemento que añada el soporte para Lombok.

Lombok proporciona un conjunto de anotaciones, en la [documentación](https://projectlombok.org/features/all) se explica detalladamente cuales son y  que hace cada una de ellas y un ejemplo de código bastante ilustrativo comparando el código usando las anotaciones y el código Java equivalente.

Además de por modificar el archivo _class_ generado por el compilador y necesitar un complemento en el IDE, hay [algunos motivos por los que no usar Lombok](https://medium.com/@vgonzalo/dont-use-lombok-672418daa819). La anotación _@NonNull_ simplemente ahorra el uso de [Objects.nonNull()](javadoc11:java.base/java/util/Objects.html#nonNull(java.lang.Object)), el uso de la anotación simplemente varia la forma de hacer la comprobación con el efecto dañino de no hacer explícto el código. La anotación _@Cleanup_ no es necesario con la existencia de la sentencia _try-with-resources_ a partir de Java 7. Hacer uso _@SneakyThrows_ no es una buena práctica. _@Synchronized_ es equivalente a usar la palabra reservada _syncrhonized_ en el método. _@Log_ reemplaza una linea de código por otra. _@val_ _@var_, en Java 10 se implementa de forma nativa con la palabra _var_. _@Getter_, _@Setter_, _@ToString_, _@EqualsAndHashCode_, _@NoArgsConstructor_, _@RequiredArgsConstructor_, _@AllArgsConstructor_ hay mejores alternativas para implementar algunas clases del modelo de dominio con la aproximación de interfaces o clases abstractas que proporciona Immutable. Por la implementación de Lombok tiene o ha tenido problemas de compatibilidad con Java 9.

{{< code file="Car-lombok.java" language="java" options="" >}}

### _Records_ de Java 14

Los _records_ de Java incorporados desde la versión 14 de Java permiten definir clases inmutables y con un código mínimo para estas clases que básicamente se componen de un conjunto de propiedades. Los _recrods_ son la implementación nativa proporcionada por Java para estas clases, sin embargo, no se proporcionan las clases _Builder_ y estos hay que seguir implementándolos.

{{< code file="Car-record.java" language="java" options="" >}}

## Ejemplo de código

Un programa de ejemplo que hace uso de estas clases es el siguiente.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="System.out" language="java" options="" >}}

Usando [Gradle][gradle] hay que añadir en el archivo de construcción del proyecto _build.gradle_ las dependencias y los procesadores de anotaciones.

{{< code file="build.gradle" language="groovy" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/LombokImmutablesRecord" command="./gradlew run" >}}

{{% /post %}}
