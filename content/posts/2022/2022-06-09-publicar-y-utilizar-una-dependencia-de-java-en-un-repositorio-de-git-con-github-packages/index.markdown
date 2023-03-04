---
pid: 637
type: "post"
title: "Publicar y utilizar una dependencia de Java en un repositorio de Git con GitHub Packages"
url: "/2022/06/publicar-y-utilizar-una-dependencia-de-java-en-un-repositorio-de-git-con-github-packages/"
date: 2022-06-09T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:github-packages-artifact.webp"
tags: ["java", "planeta-codigo"]
summary: "El software ofrecido como servicio tiene la ventaja principal de que delega en el proveedor del servicio su administración. El delegar la administración del servicio está motivada por tener ciertas garantías de que el software tiene mayor disponibilidad y fiabilidad. A cambio de esa administración el proveedor del software como servicio establece un precio por su SaaS que llega a compensar tener que administrar el servicio uno mismo que también tiene unos costes en servidor, personal, fiabilidad y también tiempo de dedicación. Una de las funcionalidades que requiere una infraestructura en una organización para desarrollar sus aplicaciones es un repositorio de artefactos o paquetes, GitHub Packages el servicio de repositorio de artefactos compatibles con los artefactos producidos por los principales lenguajes de programación para compartir bibliotecas de código entre ellos Java y JavaScript y tecnologías de contenedores como imágenes Docker."
---

{{% post %}}

{{< logotype image1="github.svg" image2="java.svg" >}}

La forma de compartir y reutilizar código en Java es a través de librerías java que se incluyen como dependencias en los proyectos que las quieran usar. Las dependencias pueden tener a su vez dependencias que han de incluirse de forma transitiva, ni Java ni el JDK ofrece soporte para gestionar las dependencias en un proyecto más allá de [los módulos del incorporados en Java 9][blogbitix-263] pero que no cubren la resolución de dependencias ni el versionado.

Para gestionar las dependencias en un proyecto Java se ha de utilizar [la herramienta de construcción como Gradle][blogbitix-245] o [Maven][maven]. Estas herramientas se encargan de descargar las versiones correctas y las dependencias transitivas declaradas en el proyecto y sus dependencias. Las dependencias se guardan en repositorios de Maven que además de los propios archivos Java de las librerías almacenan metadatos en los que se incluyen qué dependencias tiene cada una de esas librerías.

Los repositorios de dependencias se almacenan en un servidor que se encarga de permitir acceder y agregar nuevos artefactos. [El software de servidor Nexus como repositorio de artefactos][blogbitix-559]  tiene una versión _open source_ que se puede descargar e instalar para ofrecer el servicio.

Aunque Nexus permite disponer de un software de servidor para guardar artefactos de un repositorio Maven es un servidor que tiene los inconvenientes de un servidor administrado por uno mismo. Administrar el servidor que no debería fallar pero ocasionalmente requiere dedicación para aplicar actualizaciones de seguridad, actualizaciones a nuevas versiones o cuando deja de funcionar. Un servidor administrado por uno mismo requiere dedicarle tiempo del que a veces no se dispone. Para no tener que dedicar tiempo a mantener un servidor la opción de los software como servicio o los servicios gestionados son interesantes. Los software como servicio suelen tener un coste pero puede compensar teniendo en cuenta el coste de dedicarle a administrar uno mismo el servicio, el coste de la infraestructura en la que alojarlo, además se tiene más garantías de que el software administrado tenga mayor disponibilidad y fiabilidad.

Una alternativa a Nexus es el servicio [GitHub Packages][github-packages] que permite almacenar artefactos para distintos repositorios entre ellos artefactos de Maven, imágenes de [Docker][docker] y paquetes de [npm][npm].

{{< tableofcontents >}}

## El servicio de Github Packages

GitHub Packages es un servicio de GitHub que permite almacenar artefactos de distintos tipos y paquetes de las principales plataformas de programación como librerías _jar_ de Java, paquetes npm de JavaScript e imágenes de contenedores de Docker. Un repositorio de GitHub Packages se almacena en un repositorio de git de GitHub.

Los artefactos de Java se pueden publicar como en cualquier otro repositorio de Maven teniendo las credenciales de autenticación y los repositorios se pueden utilizar para descargar de ellos las dependencias al incluirlas en el archivo de construcción del proyecto.

Tiene unos límites de uso bastante reducidos en sus [planes de precios de GitHub Packages](https://github.com/features/packages#pricing) que están divididos en espacio de almacenamiento usado y datos transferidos, sin embargo no dependen del número de usuarios.

[Github Packages][github-packages] permite hacer las funcionalidades de repositorio de artefactos pero no soporta todos los tipos de artefactos de todos los lenguajes. [Google Artifact Registry][google-artifact-registry] también ofrece un servicio para almacenar los artefactos resultado de compilación y [Amazon][amazon] con [AWS CodeArtifact][amazon-codeartifact] también tiene su servicio administrado de artefactos.

* [Working with the Apache Maven registry](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)
* [About scopes and permissions for package registries](https://docs.github.com/en/packages/learn-github-packages/about-permissions-for-github-packages#about-scopes-and-permissions-for-package-registries)
* [Creating a personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

## Crear y publicar una librería de Maven en GitHub Packages

### Crear la librería

El siguiente proyecto es un proyecto de [Gradle][gradle] que contiene una clase de utilidad que se desea distribuir como una librería para compartirla y que se pueda utilizar en cualesquiera otros proyectos de Gradle o Maven que la necesiten. El código Java de la clase no tiene nada especial y en este caso simplemente contiene una utilidad para ofuscar una cadena de texto.

{{< code file="utils/Utils.java" language="java" options="" >}}
{{< code file="utils/build.gradle" language="groovy" options="" >}}

### Crear el repositorio de Maven en GitHub

Cada repositorio de git permite generar artefactos o paquetes para que puedan ser descargados. Para agrupar todos los artefactos se puede crear un repositorio específico de git y publicar en él todos los artefactos de modo que en los proyecto únicamente sea necesario incluir la configuración de un único repositorio.

### Publicar la librería en el repositorio

Para publicar artefactos en el repositorio de Maven se requieren unas credenciales para limitar publicar únicamente artefactos a aquellas personas o procesos que tengan permisos. Una de las formas de publicar paquetes es utilizar un _Personal Access Token_ o PAT al que se asocian unos permisos y se generan desde la sección _Settings_ de la cuenta dentro de [Developer settings](https://github.com/settings/apps) y _Personal access tokens_.

{{< image
    gallery="true"
    image1="image:github-pat.webp" optionsthumb1="650x450" title1="GitHub Personal Access Token"
    caption="GitHub Personal Access Token" >}}

Con el PAT y cambiando la configuración de la definición del proyecto de la herramienta de construcción el artefacto de cada uno de los módulos se publica en el repositorio de Git con el comando _publish_ proporcionado por el plugin [maven-publish](https://docs.gradle.org/current/userguide/publishing_maven.html).

{{< code file="gradlew-publish.sh" language="bash" options="" >}}

Para no guardar el PAT en el repositorio de código fuente en el archivo de construcción Gradle permite externalizar las propiedades en el archivo de configuración de Gradle en un archivo _dotfile_ ubicado en _~/.gradle/gradle.properties_.

{{< code file=".gradle/gradle.properties" language="plain" options="" >}}

Una vez publicado el artefacto desde la administración del repositorio es posible descargar cada uno de los elementos del artefacto incluso eliminarlos asi como ver las diferentes versiones publicadas del mismo artefacto. En la siguiente imagen se muestra el artefacto anterior publicado en el repositorio con GitHub Packages.

{{< image
    gallery="true"
    image1="image:github-packages-artifact.webp" optionsthumb1="650x450" title1="Artefacto de Maven publicado un repositorio con GitHub Packages"
    caption="Artefacto de Maven publicado un repositorio con GitHub Packages" >}}

### Usar la librería del repositorio de Maven

Una vez publicado el artefacto en el repositorio de Maven para usarlo hay que declarar de forma explícita la ubicación del repositorio de Maven de GitHub Packages y las credenciales para acceder al repositorio.

Con esta definición Gradle se encarga de descargar la dependencia y hacer que esté disponible en tiempo de compilación y ejecución como cualquier otra dependencia descargada del repositorio por defecto [Maven Central][maven-central] donde generalmente se publican las dependencias.

{{< code file="app/Main.java" language="java" options="" >}}
{{< code file="app/build.gradle" language="groovy" options="" >}}
{{< code file="gradlew-app-run.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaGithubPackages" command="./gradlew app:run" %}}

{{% /post %}}
