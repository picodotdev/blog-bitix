---
pid: 559
type: "post"
title: "Repositorio de artefactos privado con Nexus"
url: "/2021/03/repositorio-de-artefactos-privado-con-nexus/"
date: 2021-03-12T18:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:nexus.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Los repositorios de software almacenan los artefactos, son utilizados por las herramientas de construcción que los descargan y almacenan de forma local para posteriores usos las dependencias que los proyectos declaran en el archivo de construcción. Los desarrolladores de los artefactos publican en los repositorios de software las nuevas versiones. Nexus es un repositorio de software con soporte para repositorios de software de varios tipos, Maven para Java, imágenes de Docker, paquetes npm para JavaScript además de artefactos para los lenguajes Python y Go."
---

{{% post %}}

{{< logotype image1="nexus.svg" image2="java.svg" image3="docker.svg" >}}

Los proyectos de software son complejos, las librerías permiten crear módulos de las aplicaciones y reutilizar código ya implementado sin tener que implementar la misma funcionalidad en cada proyecto. Todos los lenguajes de una forma u otra permiten reutilizar librerías. En Java las librerías son archivos _jar_, en Docker imágenes de contenedores y en JavaScript paquetes _npm_.

Para reutilizar las librerías los proyectos declaran las librerías de las que dependen, la herramienta de construcción se encargan de descargarlas y almacenarlas en un repositorio local para evitar una nueva descargacon su transferencia de red en usos posteriores. En Java las herramientas de construcción [Gradle][gradle] y [Maven][maven] automatizan la descarga y enlazado de las dependencias declaradas con el código del proyecto. Para JavaScript una herramienta de dependencias es [npm][npm].

Las librerías requieren de la funcionalidad de un repositorio de librerías o artefactos que permite descargarlas cuando un proyecto las declare como dependencias. En Java el repositorio más popular es [Maven Central][maven-central], las imágenes para iniciar un contenedor de Docker también tienen su propio repositorio de imágenes de Docker con [Docker Hub][docker-hub] o el repositorio de librerías JavaScript que utiliza  npm.

Los repositorios anteriores son públicos donde los desarrolladores suben sus artefactos cada vez que publican una nueva versión de sus librerías o _frameworks_. Una organización en sus proyectos utiliza numerosas dependencias de los repositorios oficiales de cada lenguaje o herramienta pero también con seguridad desarrollará sus propias librerías que necesita compartir en diferentes proyectos de forma interna sin compartirlos en los repositorios públicos.

{{< tableofcontents >}}

### Repositorio de artefactos con Nexus

[Nexus] es un software que ofrece la funcionalidad de repositorio de artefactos. Permite a una organización compartir de forma privada los artefactos entre los diferentes proyectos. Soporta diferentes tipos de repositorios según el tipo de artefactos, librerías _jar_ para Java, paquetes npm para JavaScript, imágenes de contenedores para Docker, paquetes de [Python][python] y [Go][go].

Nexus OSS es la versión de código abierto que permite su uso sin coste, la versión [Nexus Pro][nexus-pro] tiene características empresariales como autenticación SSO.

* [Comparación de caracterśiticas entre Nexus Repository OSS y Pro](https://www.sonatype.com/nexus/repository-oss-vs-pro-features)

[Github Packages][github-packages] permite hacer las funcionalidades de repositorio de artefactos pero no soporta todos los tipos de artefactos de todos los lenguajes. [Google Artifact Registry][google-artifact-registry] también ofrece un servicio para almacenar los artefactos resultado de compilación, [Amazon][amazon-affiliate] con [AWS CodeArtifact][amazon-codeartifact] también tiene su servicio administrado de artefactos.

{{< image
    gallery="true"
    image1="image:nexus.jpg" optionsthumb1="650x450" title1="Nexus"
    caption="Arquitectura de un repositorio de artefactos con Nexus" >}}

### Ejemplo de repositorio con Nexus

Nexus ofrece una imagen de Docker que permite iniciar el servidor de Nexus de forma fácil como un contenedor. El siguiente _script_ lo inicia en el puerto 8081 y en el puerto adicional 8082 para el repositorio de Docker.

{{< code file="docker-run.sh" language="bash" options="" >}}

El usuario administrador es _admin_ y la contraseña aleatoria generada al inicio del contenedor se almacena en el archivo _/nexus-data/admin.password_ del sistema de archivos del contenedor.

{{< code file="docker-exec.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:nexus-1.png" optionsthumb1="300x250" title1="Nexus"
    image2="image:nexus-2.png" optionsthumb2="300x250" title2="Nexus"
    caption="Nexus" >}}

#### Publicar un artefacto a un repositorio Maven local

Usando Gradle o Maven las dependencias de un proyecto se cachean en un directorio local del directorio de inicio del usuario, _~/.gradle_ o _~/.m2_. Al desarrollar es posible publicar una versión de una librería en estos repositorios de caché. Para publicar un artefacto en un repositorio de Maven con Gradle se utiliza el _plugin_ [Maven Publish](https://docs.gradle.org/current/userguide/publishing_maven.html).

{{< code file="gradlew-publishToMavenLocal.sh" language="bash" options="" >}}

#### Publicar un artefacto a un repositorio Maven en Nexus con Gradle

Gradle ofrece el _plugin_ _Maven Publish_ para publicar artefactos en repositorios de Maven. Hay que configurar y definir los artefactos y los repositorios en los que se puede publicar. El _plugin_ añade varias tareas de Gradle con las que elegir de forma específica que artefacto publicar, en que repositorio, el grupo del artefacto, su nombre y versión.

Este es un archivo de construcción de Gradle con un repositorio Maven de Nexus en el que publicar los artefactos de la librería.

{{< code file="build-library.gradle" language="groovy" options="" >}}
{{< code file="gradlew-publishToNexusRepository.sh" language="java" options="" >}}

{{< image
    gallery="true"
    image1="image:nexus-maven.png" optionsthumb1="300x250" title1="Librería de Java publicada en repositorio Nexus"
    caption="Librería de Java publicada en repositorio Nexus" >}}

Una vez publicado el artefacto otro proyecto puede declararlo como una dependencia en la sección _dependencies_ y añadir el repositorio de Maven donde buscar dependencias en la sección _repositories_. Al ejecutar el programa Gradle descarga la dependencia y la añade al proyecto como cualquier dependencia del repositorio de _mavenCentral_.

{{< code file="build-application.gradle" language="groovy" options="" >}}

El programa trata las clases de la librería del repositorio de Nexus como cualquier otra clase de una librería de Maven Central.

{{< code file="Library.java" language="java" options="" >}}
{{< code file="Main.java" language="java" options="" >}}
{{< code file="gradlew-run.sh" language="bash" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

#### Publicar una imagen Docker a un repositorio repositorio en Nexus

Para usar el repositorio de imágenes de Docker en Nexus es necesario crear un repositorio y configurar Nexus para que se ubique en un puerto diferente del del puerto estándar. En este caso el contenedor de Docker de Nexus además del puerto 8081 se inicia en el puerto 8082.

{{< image
    gallery="true"
    image1="image:nexus-repository-1.png" optionsthumb1="200x150" title1="Repositorios de Nexus"
    image2="image:nexus-repository-2.png" optionsthumb2="200x150" title2="Repositorios de Nexus"
    image3="image:nexus-repository-3.png" optionsthumb3="200x150" title3="Repositorios de Nexus"
    caption="Repositorios de Nexus" >}}

Para subir una imagen de un contenedor al repositorio hay que crear su _tag_ y realizar la subida de la imagen. [Crear una imagen de Docker con un Dockerfile][blogbitix-51] genera una imagen de un contenedor en el repositorio local, una vez se ha generado se le añade la etiqueta y se sube al repositorio.

{{< code file="docker-push.sh" language="bash" options="" >}}
{{< image
    gallery="true"
    image1="image:nexus-docker.png" optionsthumb1="300x250" title1="Imagen de Docker publicada en repositorio Nexus"
    caption="Imagen de Docker publicada en repositorio Nexus" >}}

Una vez la imagen del contenedor se ha subido se puede utilizar especificando la URL del repositorio de imágenes. Para comprobar que la imagen se descarga del repositorio de Nexus primero se elimina de la caché local.

{{< code file="docker-rmi.sh" language="bash" options="" >}}
{{< code file="docker-pull.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/Nexus" %}}

{{< reference >}}
* [Docker Authentication](https://help.sonatype.com/repomanager3/formats/docker-registry/docker-authentication)
* [Pushing Images](https://help.sonatype.com/repomanager3/formats/docker-registry/pushing-images)
* [GitHub Packages](https://docs.github.com/en/packages/guides)
{{< /reference >}}

{{% /post %}}
