---
pid: 647
type: "post"
title: "Ejemplo de patrón Builder para las clases Record de Java"
url: "/2022/08/ejemplo-de-patron-builder-para-las-clases-record-de-java/"
date: 2022-08-12T18:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Java se ha caracterizado por ser un lenguaje verboso y necesitar declarar todo de forma explícita, esto tiene la ventaja de que el código es muy explícito sin apenas convenciones que es necesario conocer para saber cómo se comporta el código. Por el contrario esta verbosidad requiere declarar gran cantidad de código que se hace repetitivo en muchas clases. Los _Records_ de Java 16 permite declarar clases de datos en muy pocas líneas de código, esto lo consiguen introduciendo algunas convenciones que son de uso común en el lenguaje. Un aspecto que no resuelven los _Records_ es el crear clases Builder que son una necesidad asociada para esas clases de datos."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Java desde la versión 8 ha incorporado muchas novedades y con el ciclo de desarrollo de una nueva versión cada seis meses las novedades han sido numerosas y cada poco tiempo. Una de las novedades disponibles en la versión de Java 16 ha sido una especialización de clase, las clases _Record_.

* [Novedades de Java 16][blogbitix-560]

Aún con todas las novedades que se han incorporando sigue habiendo algunas necesidades que al no estar cubiertas por el propio lenguaje o una clase del JDK, una de ellas relacionada con las clases _Record_ es utilizar [el patrón de diseño Builder][blogbitix-99].

{{< tableofcontents >}}

## Las clases _Record_ de Java

Las clases _Record_ incorporadas en Java son muy útiles ya que simplifican enormemente el código de esas clases que prácticamente son contenedores de datos. Las clases _Record_ evitan tener que declarar explícitamente los métodos _getter_  para cada una de las propiedades así como [los métodos _hashCode_ y _equals_, métodos que hay implementar correctamente][blogbitix-199], también evitan declarar el método _toString_ muy útiles para el correcto funcionamiento cuando las clases se añaden en colecciones.

{{< code file="records-2.java" language="java" options="" >}}

La definición de una clase _Record_ lo único que requieren prácticamente es declarar las propiedades y sus tipos, con esta definición la clase implícitamente implementa los métodos _getter_, _hashCode_, _equals_ y _toString_, esta clase _Record_ es equivalente a la anterior pero en muchas menos líneas de código.

{{< code file="records-1.java" language="java" options="" >}}

## Alternativas a _Records_ en versiones anteriores de Java

Usar una de las últimas versiones de Java no es posible en el caso de una aplicación con mucho código existente que usa una versión anterior, a veces no tanto por pasar una nueva versión en el código ya que Java se caracteriza por mantener la compatibilidad hacia atrás sino por alguna dependencia antigua que no es compatible con una versión de Java más reciente o por el entorno de ejecución de la aplicación que ejecutarla sobre una versión más moderna de Java requiere actualizar versiones de librerías que requieren cambios en el código.

Dos librerías como alternativa a los _Records_ utilizables en versiones anteriores de Java 16 son [Immutables][immutables] y [Lombok][lombok]. Ambas proporcionan soporte para evitar mucho del código de las clases Java que se suelen utilizar como contenedores de datos, además proporcionan soporte para crear _Builders_ a partir de ellas.

Aunque Immutables y Lombok son librerías que proporcionan una funcionalidad similar su implementación entre ellas es muy diferente, la implementación de Immutables se realiza generando código con un procesador de anotaciones y Lombok manipula el _bytecode_ de las clases. La aproximación de Immutables es más limpia y potencialmente menos problemática que Lombok pero Lombok es una librería muy popular y sigue utilizándose.

* [Formas de reducir el código de las clases POJO de Java][blogbitix-272]

## Librería para utilizar el patrón Builder sobre clases _Record_

No habiendo ninguna restricción para utilizar una versión de Java a partir de la 16 no es necesaria una alternativa a los _Records_ ni requiere dependencias adicionales. Aunque los _Records_ dan solución a una necesidad de crear clases de datos que suelen tener todas las aplicaciones no cubren todas las necesidades como es el caso de crear instancias con una clase _Builder_.

Hay una librería [RecordBuilder](https://github.com/Randgalt/record-builder) para disponer de la funcionalidad del patrón _Builder_ para las clases _Record_. Con leer [el archivo README.md del repositorio Git](https://github.com/Randgalt/record-builder/blob/master/README.md) es más que suficiente para conocerlo todo sobre como usarlo.

## Ejemplo de patrón _Builder_ con clase _Record_

El uso de la librería _RecordBuilder_ para generar clases que implementan el patrón _Builder_ es muy sencillo una vez están definidas las clases _Record_, basta con anotarlas con la anotación _RecordBuilder_.

{{< code file="Book.java" language="java" options="" >}}
{{< code file="Main.java" language="java" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

Como ocurre con cualquier otra librería hay que incluirla como dependencia del proyecto en este caso con la herramienta de construcción [Gradle][gradle].

{{< code file="build.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaRecordBuilder" command="./gradlew run" %}}

{{% /post %}}