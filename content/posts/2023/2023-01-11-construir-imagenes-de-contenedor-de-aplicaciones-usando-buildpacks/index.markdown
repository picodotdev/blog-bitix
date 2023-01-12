---
pid: 669
type: "post"
title: "Construir imágenes de contenedor de aplicaciones usando Buildpacks"
url: "/2023/01/construir-imagenes-de-contenedor-de-aplicaciones-usando-buildpacks/"
date: 2023-01-11T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:buildpacksio.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Los sistemas y servicios son significativamente complejos por sí mismos, además en sistemas que se componen de varios de ellos cada uno con sus diferencias añade más complejidad al sistema. Eliminar toda la complejidad posible y simplificar el sistema es algo deseable. La herramienta Buildpacks aplica a la construcción de las aplicaciones lo que los contenedores aplican en tiempo de ejecución de las mismas, uniformizando las aplicaciones independientemente del lenguaje y plataforma que usen."
---

{{% post %}}

{{< logotype image1="buildpacksio.svg" image2="paketo.svg" >}}

Los contenedores son una gran tecnología ya que hace muy simple ejecutar servicios independientemente de cómo están implementados y en qué lenguaje, uniformizan los procesos a ejecutar y tratarlos a todos de la misma forma y con la misma herramienta.

Al igual que en los barcos los contenedores facilitan transportar mercancías, en el ámbito de la tecnología los contenedores simplifican mucho la infraestructura en tiempo de ejecución permitiendo tratar los servicios de forma uniforme.

Los contendores se basan en una imagen y una herramienta que los ejecuta, una de las herramientas más conocidas para ejecutar contenedores es [Docker][docker] y el archivo con las instrucciones para construir las imágenes son los archivos _Dockerfile_.

* [Introducción y características de Docker][blogbitix-49]
* [Cómo crear una imagen para Docker usando un Dockerfile][blogbitix-51]

Es muy útil poder ejecutar procesos de forma uniforme independientemente del lenguaje y de forma aislada con todas las dependencias del entorno dentro de la imagen lo que permite ejecutar tantos servicios como se deseen sin que las dependencias de estos entre en conflicto.

La misma utilidad y propiedades de los contenedores en tiempo de ejecución es deseable en el momento de construcción de las imágenes de los servicios.

{{< tableofcontents >}}

### La herramienta Buildpacks

La herramienta [Buildpacks][buildpacks] analiza el código fuente y permite construir imágenes de contenedores compatible con OCI con la misma herramienta independientemente del lenguaje o plataforma que utilice y sin utilizar archivos Dockerfile. Además, permite reutilizar las instrucciones de construcción sin tener que implementarlas.

Los _buildpacks_ son los módulos que añaden el soporte e implementan las instrucciones para construir las imágenes. Hay un _buildpack_ específico según una necesidad de construcción, una aplicación puede necesitar varios _buildpacks_ al mismo tiempo.

Por ejemplo, una aplicación Java necesita una versión de la JVM para ejecutarse que es proporcionado por un _buildpack_, si la aplicación utiliza [Gradle][gradle] como herramienta de construcción hay un _nuildpack_ para construir aplicaciones Gradle. Y hay _buildpacks_ para Java, [Node][nodejs], [Python][python] y otros lenguajes.

[Los conceptos que usa Buildpacks](https://buildpacks.io/docs/concepts/) son:

* _Builder_: es la imagen del contenedor con la que se realiza la construcción, contiene todos los componentes necesarios junto con los _buildpacks_ para ejecutar la construcción.
* _Buildpack_: es el ejecutable que construye y ejecuta la aplicación. Al realizar la construcción contribuye los archivos necesarios a la imagen del contenedor resultante.
* _Lifecycle_: orquesta la construcción con los _buildpacks_ y ensambla los artefactos resultantes en la imagen del contenedor.
* _Stack_: se compone de dos imágenes de contenedor, la imagen _builder_ que proporciona la imagen para realizar la construcción y la imagen _run_ que proporciona la imagen para la ejecución.

Buildpacks es extensible y es posible crear y usar _builders_ y _buildpaks_ propios ajustados a las necesidades de la organización o aplicación si los existentes no son suficientes.

{{< image
    gallery="true"
    image1="image:buildpack-what.svg" optionsthumb1="300x200" title1="Buildpack"
    image2="image:buildpack-builder.svg" optionsthumb2="300x200" title2="Buildpack builder"
    caption="Buildpack" >}}

### El proyecto Paketo

[Paketo][paketo] es un proyecto de código abierto que proporciona numerosos _buildpacks_ para diferentes lenguajes de las aplicaciones más populares entre los que por supuesto están Java, Node, Python y Go entre otros.

En el [proyecto de GitHub de Paketo](https://github.com/paketo-buildpacks) están los diferentes repositorios de los _buildpacks_ junto con sus opciones de configuración y código fuente, además proporciona varios _builder_ basados en diferentes versiones de [Ubuntu][ubuntu].

* [Paketo Builders Reference](https://paketo.io/docs/reference/builders-reference/)

### Instalación de Buildpacks

Hay [varias formas de instalación de Buildpacks](https://buildpacks.io/docs/tools/pack/) según el sistema operativo que se use, junto a varias formas de instalación usando el gestor de paquetes nativo del sistema operativo o una instalación manual en el sistema.

Builpacks es una herramienta programada en el lenguaje Go que una de sus ventajas es que generan un único binario que incluye todas las dependencias con lo que su instalación manual es muy sencilla, basta con descargar y copiar un único archivo.

En [GNU][gnu]/[Linux][linux] basta descargar el paquete de distribución para este sistema operativo, descomprimirlo e instalar el binario en el _path_ del sistema de modo que al ejecutar el comando el intérprete del _shell_ lo encuentre. En GNU/Linux el directorio _/usr/local/bin_ permite añadir al sistema comandos adicionales sin que entren en conflicto con los que se instalan a través de paquetes de la distribución.

{{< code file="ls-bin.sh" language="bash" options="" >}}

### Construcción de la imagen del contenedor

Utilizando una aplicación escrita con Java con Gradle como herramienta de construcción y que usa [Spring Boot][spring-boot], el comando para construir la imagen OCI del contenedor es el siguiente.

{{< code file="pack-build.sh" language="bash" options="" >}}

Los _builpacks_ permiten ser configurados a través de las variables de entorno, en el comando es posible configurar el _buildpack_ de _adoptioum_ para que use la versión de Java que se desee o el tipo de máquina virtual, distribución JDK o JRE. En el archivo README.md de cada uno de los _buildpacks_ están documentados las variables de entorno con su descripción y en algunos casos sus valores.

* [paketo-buildpacks/adoptium README.md](https://github.com/paketo-buildpacks/adoptium)
* [paketo-buildpacks/gradle README.md](https://github.com/paketo-buildpacks/gradle)

El comando especifica los _buildpacks_ ya que el _builder_ utilizado no incluye ninguno. En el parámetro _path_ se indica la ruta al código fuente de la aplicación.

El _buildpack_ _paketo-buildpacks/procfile_ permite especificar diferentes comandos de ejecución en un archivo. El _entrypoint_ que añade el *builder* en la imagen permite especificar el proceso a iniciar en el comando de ejecución del contenedor.

### Ejecución del contenedor con Docker

Docker permite almacenar en local y ejecutar las imágenes construidas con Buildpacks como un contenedor normal. El comando para iniciar la aplicación es el siguiente que inicia el proceso por defecto de la imagen.

{{< code file="docker-run.sh" language="bash" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

Los _buildpacks_ añaden un _launcher_ que permite [varias formas de ejecutar el contenedor de la aplicación](https://buildpacks.io/docs/app-developer-guide/run-an-app/). También es posible especificar otro proceso de la imagen con la opción _--entrypoint_ y el nombre del proceso.

{{< code file="docker-run-entrypoint.sh" language="bash" options="" >}}

A veces interesa iniciar una _shell_ para inspeccionar el sistema de archivos del contenedor y ejecutar procesos manualmente. En este ejemplo se muestra la versión de Ubuntu que forma la imagen base del contenedor y se aprecia que está basada en la versión 22.04.

{{< code file="docker-run-entrypoint-bash.sh" language="bash" options="" >}}
{{< code file="issue.out" language="plain" options="" >}}

Finalmente, es posible inspeccionar la imagen del contenedor sin iniciarlo para obtener información acerca de cómo se ha construido.

{{< code file="pak-inspect-image.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringInjectionPoint" command="./pack-build.sh" %}}

{{% /post %}}
