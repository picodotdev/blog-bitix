---
pid: 627
type: "post"
title: "Por qué y ejemplo de cómo desarrollar un plugin de Gradle"
url: "/2022/04/por-que-y-ejemplo-de-como-desarrollar-un-plugin-de-gradle/"
date: 2022-04-07T19:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:gradle.svg"
tags: ["java", "planeta-codigo"]
summary: "Generalmente cuando se necesita una funcionalidad en Gradle esta suele estar proporcionada por los propios desarrolladores de Gradle, por los propios mantenedores de otras herramientas para integrarlas en Gradle o en último caso por alguien que antes ha tenido la misma necesidad ha publicado un _plugin_. Si aún así no hay un _plugin_ que ofrezca la funcionalidad que se desea, Gradle ofrece la posibilidad de que cualquiera desarrolle sus propios _plugins_ y los use para sus necesidades específicas o en caso de ser útil para otras personas compartirlo en un repositorio público como cualquier otro."
---

{{% post %}}

{{< logotype image1="gradle.svg" image2="java.svg" >}}

[Gradle][gradle] es una herramienta de construcción y gestión del ciclo de vida de un proyecto principalmente usando en proyectos Java. Realiza importantes tareas como la descarga y gestión de las dependencias y resolución de conflictos entre las diferentes versiones que necesite una aplicación, tareas importantes que ni [el sistema de módulos de Java][blogbitix-263] proporciona. Permite ejecutar de forma sencilla la aplicación o las pruebas unitarias de código entre otras muchas tareas que pueden ser automatizadas como pruebas estáticas de código y generación de artefactos además de ser suficientemente flexible para adaptarse a las necesidades que haya en un proyecto.

Por todas estas importantes tareas que realiza Gradle es una herramienta imprescindible en cualquier cualquier proyecto Java. Su alternativa más usada es [Maven][maven] que tiene un modelo declarativo y basado en XML con unos archivos más verbosos. Ambas herramientas se consideran ya maduras generalmente equivalentes para la mayoría de funcionalidades más comunes. Dado que los archivos de configuración de Gradle son menos verbosos, más legibles y fáciles de editar que el XML es la herramienta elegida como herramienta de construcción para muchos proyectos.

Cuando la funcionalidad ofrecida por Gradle no es suficiente, no existe un _plugin_ de un tercero o no está adaptado a las necesidades de un proyecto u organización al igual que otros han desarrollado sus propios _plugins_ cualquiera puede desarrolalr _plugins_ con la interfaz que ofrece Gradle para su desarrollo y extender su funcionalidad.

{{< tableofcontents >}}

## Por qué desarrollar un _plugin_ propio de Gradle

Es seguro que en una empresa a lo largo del tiempo esta desarrolle varios proyectos, siendo varios una cantidad de unas pocas decenas a cientos dependiendo del tamaño de la organización. Con múltiples proyectos por motivos de mantenimiento el copiar y pegar código entre proyectos no es una opción viable con lo que también seguro que surge la necesidad de reutilizar código propio de la organización entre los diferentes proyectos tal y como se reutiliza código de otras librerías como [Spring][spring], [Hibernate][hibernate] y otra multitud de librerías desarrolladas por terceros. La forma de reutilizar código en Java es a través de librerías que se publican en un repositorio de Maven igual que cualquier otra librería de terceros.

También es seguro que con múltiples proyectos surge la necesidad de reutilizar código entre múltiples proyectos incluso en la herramienta de construcción. La forma que ofrece Grade de reutilizar código es a través de _plugins_. En el caso de la herramienta de construcción un _plugin_ permite aplicar a todos los proyectos de forma sencilla, homogénea, mantenible y sin copiar y pegar elementos comunes considerados un estándar en la organización como comprobaciones estáticas de código con [PMD][pmd], [Checkstyle][checkstyle] y [SpotBugs][spotbugs] entre otras muchas cosas propias de una organización. Muchos terceros publican _plugins_ que cualquiera puede aplicar a un proyecto si este _plugin_ ofrece la funcionalidad que necesita y si no existe ninguno también es posible desarrollar uno propio ajustado a las necesidades propias.

Gradle ofrece un sistema muy flexible y extensible para desarrollar, publicar y reutilizar _plugins_. En su documentación hay varias páginas que explican cómo desarrollar un _plugin_, y otros muchos artículos incluido el presente.

* [Developing Custom Gradle Plugins](https://docs.gradle.org/current/userguide/custom_plugins.html)
* [Writing Custom Gradle Plugins](https://www.baeldung.com/gradle-create-plugin)
* [Developing a Custom Gradle Plugin](https://dzone.com/articles/gradle-plugin)

Por ejemplo, hace tiempo hice un ejemplo en el que generaba un archivo al hacer la construcción y generación del artefacto que incluía información del artefacto como la versión, fecha de construcción, _hash_ del _commit_ y número de _build_ de modo que en tiempo de ejecución el código tenga información para mostrar esa versión en las trazas y conocer la versión exacta del código desplegado en un entorno. Otro caso puede ser que se desee una funcionalidad similar al _plugin_ de [Maven Release](https://maven.apache.org/maven-release/maven-release-plugin/) que permite automatizar la generación de versiones de los proyectos para la que en Gradle la opción equivalente con [gradle-release](https://github.com/researchgate/gradle-release) su última versión es del 2017.

* [Incluir información de la versión en el artefacto distribuible con Gradle][blogbitix-145]

## Conceptos de Gradle

Gradle define varios conceptos propios para la configuración y ejecución. La configuración permite cambiar el comportamiento o adaptarlo a las necesidades propias. La configuración se definen en lo que Gradle denomina extensiones que son simplemente objetos de datos en los que Gradle permite al usuario introducir datos y que en el archivo de construcción se manifiestan como bloques de configuración con propiedades, las tareas al ejecutarse obtienen las extensiones y los datos configurados en ellas, en función de los datos las tareas varían su comportamiento.

Los _plugins_ a través de la API que ofrece Gradle pueden realizar cualquier acción que permita la API como por ejemplo aplicar nuevos _plugins_ al proyecto, definir objetos de extensiones, definir nuevas tareas o cambiar el comportamiento de tareas existentes. Con estas funcionalidades a disposición de cualquiera es posible crear un _plugin_ de Gradle en el que encapsular cualquier funcionalidad relativa al ciclo de vida del proyecto, aplicarla y utilizarla en cualesquiera proyectos.

Una vez creado el _plugin_ este se publica y comparte en un repositorio de Maven del que los proyectos que lo quieran usar simplemente han de incluir la URL del repositorio y la referencia al _plugin_.

## Ejemplo de _plugin_ para Gradle

Lo que hace este ejemplo es mediante código aplicar cambios a través de la clase _Project_ que representa al proyecto de Gradle. A través de esta clase se añaden nuevos _plugins_, se configuran las extensiones y las tareas para aplicar la funcionalidad deseada en el _plugin_.

El ejemplo añade los _plugins_ y configura sus extensiones para realizar comprobaciones estáticas de código a través de las herramientas PMD, Checkstyle, SpotBugs y sus _plugins_ para Gradle. Además, la funcionalidad del _plugin_ es generar un archivo con información de la versión del artefacto incluyendo su versión, fecha de construcción, _hash_ del _commit_ y número de _build_ para conocer esta información del artefacto.

Incluir información variable en el artefacto que es diferente en función de la fecha de ejecución hace que la generación no sea reproducible, esto es, no genere exactamente el mismo binario dado que su contenido es diferente con cada _build_ aunque el código no haya cambiado y solo lo haya hecho un archivo de configuración y únicamente ligeramente su contenido. Que la generación de los artefactos sean reproducibles tiene el beneficio de que el artefacto generado sea auditable por motivos de seguridad y es posible que esté libre de modificaciones diferentes del código empleado para la construcción.

### Crear un _plugin_ de Gradle

Hay varias formas de desarrollar un _plugin_ de Gradle, se puede desarrollar dentro de un proyecto existente o como un proyecto independiente, Gradle ofrece un comando para disponer de la estructura básica de un proyecto para el _plugin_ rápidamente y de forma sencilla al igual que se realiza para disponer de una aplicación normal. En el archivo de construcción del _plugin_ que genera Gradle incluye un _plugin_ propio de Gradle necesario para desarrollar _plugins_ de Gradle, _java-gradle-plugin_.

Los _plugins_ de Gradle se crean proporcionando una implementación de la interfaz [Plugin](https://docs.gradle.org/current/javadoc/org/gradle/api/Plugin.html), esta clase ofrece el método _apply_ que Gradle invoca para que el _plugin_ modifique el proyecto a través de la instancia de [Project](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html) que lo representa y se proporciona como argumento del método. La clase _Project_ ofrece métodos para añadir nuevos _plugins_ al proyecto, obtener los objetos que contienen los datos de las extensiones, permite añadir o modificar tareas existentes y crear dependencias entre las tareas. En el siguiente código se observan estas modificaciones en el proyecto en el _plugin_ del ejemplo.

Dado que el _plugin_ de ejemplo configura otros _plugins_ existentes necesita de las clases de los _plugins_ y de sus extensiones de modo que han de incluirse como una dependencia del _plugin_ desarrollado en el archivo de construcción del _plugin_. El _plugin_ también puede proporcionar sus propias clases de extensiones.

{{< code file="JavaGradlePlugin.java" language="java" options="" >}}
{{< code file="JavaGradleExtension.java" language="java" options="" >}}
{{< code file="Version.java" language="java" options="" >}}
{{< code file="build.gradle" language="groovy" options="" >}}

Los _plugins_ de Gradle se publican en un repositorio de Maven como cualquier librería o aplicación, Gradle compila el _plugin_ y lo publica. Para desarrollar es posible publicar el _plugin_ en el repositorio de Maven local.

{{< code file="gradlew-publishtomavenlocal.sh" language="bash" options="" >}}

También es posible realizar teses unitarios y funcionales sobre el _plugin_, al utilizar la tarea de creación del proyecto del _plugin_ Gradle crea unos ejemplos en los basarse para crear más.

### Aplicar el _plugin_ de Gradle a un proyecto

Una vez el _plugin_ está en el repositorio de Maven usar el _plugin_ y aplicarlo a un proyecto es exactamente igual que cualquier otro _plugin_ de Gradle, basta incluirlo en la sección _plugins_ con su identificador y su versión. Una vez aplicado el _plugin_ como este añade otros _plugins_ al ejecutar la tareas _tasks_ que muestra las tareas existentes en el proyecto se observa que aunque los _plugins_ de PMD, Checkstyle, SpotBugs han incluido sus tareas en el proyecto aunque no se hayan aplicado de forma explícita sino de forma transitiva a través del _plugin_ propio.

{{< code file="gradlew-tasks.sh" language="bash" options="" >}}
{{< code file="gradlew-tasks.out" language="plain" options="" >}}

Ejecutando la _build_ la tarea se genera el archivo como un recurso que se procesa al que se le realizan varias sustituciones con los valores que permite en tiempo de ejecución conocer información de la versión de la librería o aplicación, este archivo se incluye en el jar de la librería o aplicación y es posible acceder a él como un recurso más.

{{< code file="version.properties" language="plain" options="" >}}
{{< code file="App.java" language="java" options="" >}}
{{< code file="gradlew-run.sh" language="bash" options="" >}}
{{< code file="gradlew-run.out" language="plain" options="" >}}

Al ejecutar la tarea de _build_ dado que se han incluido los _plugins_ PMD, CheckStyle y Spotbugs sus tareas de validación se ejecutan y detectan los errores en los archivos del código fuente según las reglas de validación configuradas. En el código de ejemplo Checkstyle indica que hay el código no está formateado de forma apropiada siguiendo las convenciones establecidas para el proyecto.

{{< code file="gradlew-build.sh" language="bash" options="" >}}
{{< code file="gradlew-build.out" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/GradlePlugin" command="./gradlew build" %}}

{{% /post %}}
