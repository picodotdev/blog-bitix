---
pid: 642
type: "post"
title: "Generar releases de un proyecto Java con Gradle"
url: "/2022/07/generar-releases-de-un-proyecto-java-con-gradle/"
date: 2022-07-08T16:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Utilizar las etiquetas de las herramientas de control de versiones permite conservar ciertos hitos o _commits_ importantes para consultarlos en el futuro. Generar una versión de un proyecto suele implicar generar una etiqueta en la herramienta de control de versiones. Dado que la generación de nuevas versiones es parte del ciclo de vida de un proyecto hay _plugins_ tanto para Maven como para Gradle que ofrecen como funcionalidad automatizar esta proceso."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

La generación de _releases_ es una tarea habitual en un proyecto, la _release_ es una forma de identificar de forma exacta la versión que se utiliza para desplegar en un entorno o en producción o es la versión que utilizan otros proyectos para utilizarla como una dependencia.

Hay varias nomenclaturas para denominar a las versiones. En Java hay varios _plugins_ para las herramientas de construcción [Maven][maven] o [Gradle][gradle] para los proyectos Java que permite generar _releases_.

{{< tableofcontents >}}

### Las _releases_ de un proyecto o artefacto

En el ciclo de vida de un proyecto o repositorio hay ciertos hitos importantes como cómo son los momentos en los que se desea generar una nueva versión a partir de un _commit_ ya sea para ser desplegada en un servidor o ser usada por otros proyectos como una dependencia.

Las _releases_ son los _commits_ a los que se les asigna una versión y a partir de los cuales se generan los artefactos del repositorio o proyecto. Una forma de recordar esos hitos en el repositorio de control de versiones como [Git][git] es creando un _tag_. Un _tag_ es simplemente asignar una etiqueta con un nombre más semántico y fácilmente identificable que el _hash_ del _commit_.

Una forma habitual para los nombres de las versiones o _releases_ es utilizar una nomenclatura compuesta de tres números separados por un punto, por ejemplo 1.2.6, un número mayor en esta versiones implica una versión más reciente y seguramente con más funcionalidades y mejorada. Cada uno de estos números se denominan _major.minor.patch_ donde por norma general el _major_ se incrementa en cada versión destacada o incompatible respecto a las versiones anteriores, _minor_ se incrementa cuando hay cambios importantes con alguna funcionalidad destacada añadida pero que siguen siendo compatibles con versiones anteriores y *patch* se incrementa cuando los cambios de la nueva versión son menores como corrección de errores o fallos de seguridad pero sin nuevas funcionalidades.

Dado que la construcción y ciclo de vida del proyecto se gestiona generalmente con una herramienta de construcción la generación de _releases_ se automatiza con Maven o Gradle en el caso de proyectos Java.

### _Plugin_ de _release_ para Gradle

Gradle no dispone de un _plugin_ oficial para generar _releases_ de un proyectos y hay que recurrir a _plugins_ desarrollados por un tercero. En Gradle hay dos _plugins_ destacables que permiten automatizar la generación de _releases_ en un proyecto. El segundo no ha generado una nueva versión desde hace cinco años con lo que parece más recomendable el primero por estar mejor mantenido actualmente

* [axion-release-plugin](https://axion-release-plugin.readthedocs.io/en/latest/)
* [gradle-release](https://github.com/researchgate/gradle-release)

El [_plugin_ de Axion](https://axion-release-plugin.readthedocs.io/en/latest/) para generar _releases_ la forma que utiliza para generar una _release_ es creando un _tag_ en el repositorio de código fuente siguiendo la nomenclatura _major.minor.patch_ según las etiquetas ya creadas e incrementando el número de uno de estos tres elementos.

Que la release consista simplemente en crear una etiqueta tiene la ventaja de que no es necesario crear _commits_ adicionales en el repositorio de código fuente lo que potencialmente invalida la versión probada en un proceso de aseguramiento de calidad.

### _Plugin_ de _release_ para Maven

El [_plugin_ de _release_ de Maven](https://github.com/researchgate/gradle-release) es ofrecido de forma oficial por los desarrolladores de esta herramienta de construcción.

Su forma de funcionamiento también incrementa las versiones siguiendo la nomenclatura _major.minor.patch_ pero dado que en Maven la versión del proyecto se especifica en los archivos descriptores de construcción _pom.xml_ implica que el proceso de _release_ crea _commits_ en el repositorio de código fuente.

### Ejemplo usando el _plugin_ de _release_ para Gradle

Usar el _plugin_ de Axion en Gradle requiere añadir el _plugin_ en la lista de _plugins_ del proyecto. El _plugin_ ofrece una sección en la que personalizar algunas opciones de configuración según las preferencias que se deseen en la sección _scmVersion_. Para diferenciar las etiquetas de _releases_ del resto de etiquetas se puede establecer en prefijo, en el ejemplo con la letra _v_.

{{< code file="build.gradle" language="groovy" options="" >}}

El comando principal para generar una nueva versión es tan simple como utilizar una tarea ofrecida por el _plugin_.

{{< code file="gradlew-release.sh" language="bash" options="" >}}

En la versión actual del proyecto se obtiene con otra tarea añadida por el proyecto. En esencia busca entre los _tags_ en el repositorio y según el _commit_ actual devuelve la versión actual.

{{< code file="gradlew-currentversion.sh" language="bash" options="" >}}

Dado que las releases son simplemente etiquetas en el repositorio de código fuente estas pueden verse con el comando de Git para listar las etiquetas.

{{< code file="git-tag.sh" language="bash" options="" >}}

El comportamiento por defecto de la tarea _release_ es incrementar el componente menos significativo es incrementado que estando la versión compuesta de tres elementos significa que se incrementa el número de _patch_, el _plugin_ ofrece también la posibilidad de especificar el número de versión que se desea o incrementar otro elemento.

{{< code file="gradlew-marknextversion.sh" language="bash" options="" >}}

En caso de utilizar GitHub al generar la versión y etiqueta y subirla al repositorio lo añade en la sección de _releases_ en la página de GitHub del proyecto.

{{% /post %}}
