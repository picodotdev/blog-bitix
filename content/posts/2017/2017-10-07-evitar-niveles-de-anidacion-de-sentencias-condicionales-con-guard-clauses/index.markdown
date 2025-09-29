---
pid: 267
type: "post"
title: "Evitar niveles de anidación de sentencias condicionales con guard clauses"
url: "/2017/10/evitar-niveles-de-anidacion-de-sentencias-condicionales-con-guard-clauses/"
date: 2017-10-07T12:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Al escribir el código de un método es habitual hacer algunas comprobaciones sobre los parámetros, por ejemplo, para comprobar que el parámetro tiene una referencia, si es nulo o cumple alguna condición, si es una lista que no esté vacía o para validar mediante alguna otra regla. Estas validaciones o aserciones se realizan al inicio del método con sentencias _if_ que añaden un nivel de tabulación al código si se anidan. Estos niveles de tabulación con sentencias _if_ anidadas hacen poco legible el código.

Este código que comprueba algunos parámetros no es tan sencillo de entender por los varios niveles de anidación.

{{< code file="NestedIf.java" language="java" options="" >}}

En vez de crear este nivel de anidación y crear varios flujos distintos por donde se bifurca el código, con aserciones, sentencias _if_ o _guard clauses_ el código queda más legible. Después de cada cláusula _if_ es seguro que la aserción que realiza se cumple.

{{< code file="GuardClauses.java" language="java" options="" >}}

También es una recomendación que los métodos solo tengan una sentencia _return_, de hecho en la enseñanza de programación se explica pero para estos casos de comprobaciones la ventaja de no tener varios _if_ anidados es justificable tener varios _return_, el código queda más legible.

Las _guard clauses_ son una de [algunas buenas prácticas sencillas a utilizar en el código fuente][blogbitix-612], otras son utilizan un único nivel de indentación, encapsular los datos primitivos en clases, un único punto por línea de código, evitar abreviaturas, mantener las clases pequeñas, evitar los métodos _getter_ y _setter_, revisar las dependencias de las clases y segregar los cambios en los commits.

{{< reference >}}
* [Replace Nested Conditional with Guard Clauses](https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html)
{{< /reference >}}

{{% /post %}}
