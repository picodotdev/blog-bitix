---
pid: 727
type: "post"
title: "Parchear una clase de una librería Java para solucionar un error"
url: "/2026/04/parchear-una-clase-de-una-libreria-java-para-solucionar-un-error/"
date: 2026-04-12T14:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Hay un chiste en el que alguien le pregunta al técnico, «¿500 € solo por girar un tornillo?», y el técnico responde, «No, por girar el tornillo ha sido 1 €, por saber hacia que lado girarlo 499». Pues el siguiente artículo es lo equivalente que me ha pasado con un error en una aplicación de Java."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

No creo que los creadores del lenguaje y ecosistema Java lo diseñaran pensando en que los desarrolladores pudiéramos parchear una clase de una librería redefiniendo y colocándola en el _classpath_ con mayor prioridad de carga.

Pero esto que es posible es precisamente lo que he tenido que hacer recientemente para solucionar un problema en una aplicación _legacy_ al migrar versiones antiguas de librerías.

{{< tableofcontents >}}

## El error

El error tenía el siguiente _stacktrace_ motivado por una actualización mayor de Java y el _driver_ de Oracle _ojdbc_ desde Java 7 y _ojdbc_ 5 a Java 8 y _ojdbc_ 8, de una aplicación que usa [Spring][spring] e [Hibernate][hibernate] 3 en una versión antiguas.

El error indica que alguna comprobación que realiza Hibernate empieza a fallar con la actualización de librerías. Con este _stacktrace_ toca investigar si alguien con menos suerte ha sido el primero que se ha encontrado con esta misma excepción y la ha compartido en [stackoverflow][stackoverflow] o en un post de algún blog.

{{< code file="stacktrace.txt" language="plain" options="" >}}

## La sugerencia de Claude Code

Otra vía de investigación con esto de la IA ha sido usar [Claude Code][claude-code], esta sugería cambiar la propiedad de configuración de Hibernate _batch\_size_ al valor 0 en vez de 100.

Claude Code lo comentaba con bastante seguridad, sin embargo, al cambiarla se seguía produciendo el mismo _stacktrace_.

## El parche

Dado que ni una búsqueda en internet ni Claude Code daban una solución que funcionase no me ha quedado otro remedio que buscar alguna alternativa por mucho _workaround_ o ñapa que fuera capaz de entrar en las primeras posiciones del salón de la fama.

En primer lugar inspecciono ese _stacktrace_ donde se está dando la excepción, me fijo en la clases _BatchingBatcher_ y _Expectations_ que por el nombre de sus métodos están haciendo alguna comprobación confirmado por el mensaje de la excepción.

Con [IntelliJ][intellij] y su decompilador de archivos _class_ con el _bytecode_ de Java obtener el pseudo código fuente de esas clases que quizá no conserva los mismos números de línea o nombres de variables pero que sirve para obtener un aproximación del código fuente suficiente para inspeccionarlo y entender que está haciendo.

{{< code file="BatchingBatcher-1.java" language="java" options="" >}}
{{< code file="Expectations.java" language="java" options="" >}}

El cambio consiste en modificar esa llamada a _verifyOutcome_ con el _rowCount_ que debe obtener del _driver_ y pasarle un el número mágico de _-2_ o _-3_ que la clase de _Expectations_ acepta como si al ejecutar la sentencia SQL ha sido exitoso pero no se ha podido obtener el número de filas afectadas o ha habido algún error en el _batch_.

{{< code file="BatchingBatcher-2.java" language="java" options="" >}}

Con este cambio de una sola línea ya solo queda colocar esta nueva redefinición de la clase en un orden de prioridad que el de la librería. Para lo cual es necesario conocer que en las aplicaciones Java las clases en el _classpath_ de la aplicación tiene más prioridad que el de las clases Java de las librerías.

Sí, puede que el parche sea feo pero es que la mejor solución sería reescribir esa aplicación usando las versiones tanto de Java como del conjunto de librerías que usa esa aplicación, pero eso cuesta algunos meses de trabajo y el parche solo unas horas o días. Así que determinar cual es la mejor solución depende por que criterio se mida.

Un código del que alguien dejó escrito esto en el _README_ de un repositorio con código _legacy_ parecido.

{{< code file="README.txt" language="plain" options="" >}}

## La solución

La solución implica analizar el _stacktrace_, decompilar y conocer cómo inspeccionar el código fuente de las clases de ese _stacktrace_ y donde colocar esa nueva clase para que sea efectiva.

El cambio es sencillo, modificar solo una línea de código fuente, para lo demás uno puede que necesite 25 años de experiencia trabajando con Java para idear esta solución.

{{% /post %}}


