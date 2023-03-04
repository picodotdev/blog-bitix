---
pid: 525
type: "post"
title: "Entorno de desarrollo Java para editar, compilar y ejecutar programas"
url: "/2020/10/entorno-de-desarrollo-java-para-editar-compilar-y-ejecutar-programas/"
date: 2020-10-25T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "El entorno de desarrollo son la colección de herramientas necesarias para editar código fuente, compilarlo y ejecutar sus programas. El primer paso para aprender a programar en Java es disponer de un entorno de desarrollo con las herramientas mínimas necesarias para practicar y aplicar los conocimientos aprendidos de un curso de formación o utilizando algún libro sobre el lenguaje Java. El entorno mínimo necesario consta del JDK que incluye el compilador Java, otras herramientas que facilitan la programación son un editor avanzado o IDE con asistencia de código y una herramienta de construcción para automatizar tareas."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Java es un lenguaje ya con 25 años de vida, desarrollado originalmente por [Sun Microsystems][sun-microsystems] y adquirida por [Oracle][oracle] con toda su propiedad intelectual incluyendo la plataforma y lenguaje Java. Es uno de los lenguajes de programación que ha mantenido durante muchos años la primera posición como el lenguaje más utilizado. Algunas de sus características que han contribuido a su éxito son independencia del entorno de ejecución y _bytecode_, recolección de basura, clases de la API y documentación Javadoc, legibilidad del código y compatibilidad hacia atrás además de ser un lenguaje fuertemente tipado y orientado a objetos.

El lenguaje de programación Java es un lenguaje compilado, eso tiene sus ventajas y algunos inconvenientes, la mayoría son ventajas. Uno de sus inconvenientes es que ejecutar un programa Java requiere el paso de compilación, a diferencia de los lenguajes dinámicos e interpretados que pueden ejecutarse desde el código fuente pero a riesgo de que en tiempo de ejecución se produzcan errores que en los lenguajes compilados se detectan en tiempo de compilación.

Durante todos estos años el lenguaje ha incorporado muchas novedades al lenguaje original desde las colecciones genéricas hasta más recientemente _lambdas_ y modularidad entre otras muchas novedades más, en cada nueva vesión se siguen añadiendo nuevas características.

* [10 razones para seguir usando Java][blogbitix-81]
* [Novedades y nuevas características del lenguaje Java en cada nueva versión][blogbitix-serie-java-platform]

Este artículo contiene las herramientas básicas para disponer del entorno mínimo para editar, compilar y ejecutar programas escritos con el lenguaje de programación Java.

{{< tableofcontents >}}

## Libros sobre Java para aprender

Una buena forma de aprender a programar en un lenguaje es utilizando un libro específico sobre el lenguaje, un libro que comience si es necesario por los fundamentos del lenguaje considerando que el lector no tiene conocimientos previos en el lenguaje ni en la programación. Los libros tienen un contenido bien estructurado y son más didácticos que artículos individuales.

De Java hay mucha documetación y libros con los que aprender a programar, desde básicos hasta más avanzados o temas específicos como la concurrencia. Tres de los mejores libros sobre Java son [Thinking in Java](https://amzn.to/2sNCizu), [Effective Java](https://amzn.to/31GZwFF) y [Java 8 in Action](https://amzn.to/2MXMTi3) sin olvidar otros [buenos libros para mejorar como programadores][blogbitix-55].

{{< amazon
    linkids="2a437ba4126a093af22af88c893ac407,3a13c135d5cd40ca21d3f1dd853ab2a3,2256857b147eea05bc79aef83d2548fe"
    asins="0131872486,0134685997,1617291994" >}}

## El programa Hola Mundo en Java

El primer programa que se suele escribir al empezar a aprender un lenguaje de programación, es el programa _Hola Mundo_ que emite en la consola simplemente un mensaje. En Java es el siguiente.

{{< code file="Main.java" language="java" options="" >}}

Todo programa contiene un punto de entrada desde la que se inicia la ejecución, en Java es un método estático de nombre _main_ que tiene como argumento un _array_ de _Strings_ con los argumentos con los que se ha invocado la ejecución. En Java el método _main_ se declara dentro de una clase dado que Java es un lenguaje orientado a objetos.

El programa _Hola Mundo_ aún con su sencillez permite escribir el mínimo programa en un lenguaje, ejecutarlo, ver su resultado pero sobre todo disponer de todas las herramientas en el entorno de desarrollo local para posteriormente escribir programas más complejos.

Las sentencias de control son los elementos básicos con los que se construyen los programas.

* [Las sentencias de control de flujo en Java (if, switch, for, while, do-while, try-catch, break, continue e invocación)][blogbitix-494]
* [4 formas de hacer un bucle for en Java][blogbitix-247]

## El JDK y la máquina virtual de Java

En Java la herramienta básica que se necesita para compilar programas y ejecutarlos es un JDK que incluye el compilador que transforma el código fuente en _bytecode_ independiente de la arquitectura del procesador de la máquina ya sea x86, Arm, PowerPC o RISC-V y sistema operativo [Windows][windows], [GNU][gnu]/[Linux][linux], [macOS][macos] o [FreeBSD][freebsd]. 

El programa compilador de Java es [javac][javac-command] que informa de errores de compilación como referencias a variables, métodos o clases que no existen o de uso o asignación de tipos incorrectos entre otros y el intérprete del _bytecode_ Java que transforma en tiempo de ejecución el _bytecode_ en código máquina para su ejecución e incluye [el recolector de basura][blogbitix-463] que exime al programador de liberar explícitamente la memoria reservada.

El comando para compilar el programa _Hola Mundo_ es el siguiente. En sus parámetros se indica el directorio donde se encuentra el código fuente y el directorio de salida para los archivos compilados a _bytecode_.

{{< code file="javac.sh" language="bash" options="" >}}

El compilador genera un archivo de extensión _class_ por cada clase del programa como se muestra en el siguiente listado de archivos del directorio de salida del compilador.

{{< code file="tree.sh" language="bash" options="" >}}

Para ejecutar un programa Java se indica la ubicación de los archivos de _bytecode_, las librerías _jar_ de clases compiladas adicionales si se utilizase alguna y la clase que contiene el método principal del programa, en Java el método estático _main_.

{{< code file="java.sh" language="bash" options="" >}}

El resultado de la ejecución del programa es un mensaje en la terminal.

{{< code file="System.out" language="bash" options="" >}}

## La herramienta SDKMAN

A lo largo del tiempo Java ha publicado varias versiones del lenguaje y del JDK. Con el calendario de publicaciones aplicado desde la versión 9 se publica una nueva versión de Java cada seis meses y una versión de soporte a largo plazo cada tres años siendo la 11 la primera LTS en el 2018/09 y la 17 en el 2021/09.

Con este prolífico calendario de publicación de nuevas versiones es necesario una herramienta con la que administrar los JDK, tanto para instalar, actualizar versiones menores con parches de seguridad, desinstalar JDKs e incluso tener instaladas varias versiones del JDK al mismo tiempo.

La herramienta [SDKMAN][sdkman] sirve para administrar los JDK, además de diferentes versiones hay diferentes distribuciones del JDK, todas parten del código fuente del proyecto OpenJDK y diferentes organizaciones proporcionan su distribución compilada y usable del JDK  sin cambios o con algunos cambios adicionales. También sirve para instalar otras herramientas como una herramienta de construcción de proyectos como [Gradle][gradle] o los lenguajes [Kotlin][kotlin] y [Groovy][groovy].

{{< image
    gallery="false"
    image1="logotype:sdkman.svg" optionsthumb1="100x100" title1="SDKMAN" >}}

La herramienta SDKMAN se instala y usa con los siguientes comandos.

{{< code file="sdk-install.sh" language="bash" options="" >}}
{{< code file="sdk-usage.sh" language="bash" options="" >}}
{{< code file="sdk-commands.sh" language="bash" options="" >}}

## La herramienta de construcción Gradle

En programas y proyectos grandes con muchas clases no se usa el compilador del JDK directamente y su comando para compilar las clases sino que se suele utilizar una herramienta de construcción, una de ellas es [Maven][maven] que con un archivo de descripción del proyecto en formato XML y siguiendo varias convenciones compila el programa, otra herramienta es Gradle que a diferencia de Maven su archivo descriptor de proyecto es con el lenguaje Groovy o Kotlin menos verboso y propenso a errores que el XML.

{{< image
    gallery="false"
    image1="logotype:gradle.svg" optionsthumb1="100x100" title1="Gradle" >}}

Además de para compilar un proyecto, una herramienta de construcción proporciona otras funcionalidades como ejecutar las pruebas unitarias o de integración, [generar la documentación de las clases][blogbitix-260] de la documentación incluida en el código fuente con [la herramienta Javadoc][blogbitix-259], descargar las dependencias de librerías definidas por el proyecto, empaquetar las clases del proyecto en una librería _jar_ y distribuir ese artefacto en los repositorios de librerías para otros proyectos.

SDKMAN también sirve para instalar una herramienta de construcción, Gradle se instala con el siguiente comando.

{{< code file="sdk-install-gradle.sh" language="bash" options="" >}}

El siguiente archivo de configuración de proyecto, con la estructura de directorios según las convenciones de Gradle permite compilar el código Java y ejecutar el programa a partir de la clase que contiene el método _main_.

{{< code file="build.gradle" language="bash" options="" >}}

Estos son los comandos para construir el proyecto y ejecutar el método _main_ del programa con Gradle.

{{< code file="gradle-build.sh" language="bash" options="" >}}
{{< code file="gradle-run.sh" language="bash" options="" >}}

Además, Gradle permite crear la estructura básica de directorios y archivos necesarios de forma automatizada con un comando para iniciar un proyecto de forma rápida y sin esfuerzo.

* [Iniciar rápido un proyecto de Java con Gradle o de Spring con Spring Initializr][blogbitix-245]

## Ejecutar un programa Java desde el código fuente

Para simplificar el primer acercamiento al lenguaje Java desde la versión 11 de Java se ofrece la posibilidad de ejecutar un archivo de código fuente Java sin necesidad de compilar el código previamente de forma explícita. El propio comando [java][java-command] ofrece esta posibilidad. La limitación está en que el programa Java ha de estar contenido en un único archivo de código fuente aunque con la posiblidad de contener múltiples clases en el mismo archivo y no puede tener dependencias de librerías de terceros.

{{< code file="java-run.sh" language="bash" options="" >}}

Otra de las facilidades proporcionada por Java es una consola interactiva REPL (_read, eval, print, loop_) similar a las existentes en lenguajes dinámicos e interpretados. La consola REPL consiste en un bucle para leer una entrada, evaluar, imprimir en la salida y repetir los pasos.

La consola REPL de Java se inicia con el comando _jshell_ y sirve para hacer pequeñas pruebas de código e incluso probar el programa _Hola Mundo_ sin necesidad de realizar los pasos de compilación y ejecución desde el código fuente ni crear un proyecto o archivos de código fuente.

{{< code file="jshell.sh" language="bash" options="" >}}

## Entorno integrado de desarrollo

Escribir código el código fuente de un programa es posible con cualquier editor de texto incluído el básico que incluyen los sistemas operativos de escritorio como Bloc de notas en Windows o Gedit del entorno de escritorio [GNOME][gnome] y GNU/Linux, también con editores basados en consola como Nano o Vim.

Sin embargo, en la plataforma Java la mayor parte de los programadores utilizamos un editor especializado para editar código fuente con un entorno integrado de desarrollo o IDE. En la plataforma Java los IDE más populares son [IntelliJ IDEA][intellij] que tiene una licencia comercial pero una versión para la comunidad gratuita muy completa, [eclipse][eclipse] y [NetBeans][netbeans] tienen una licencia de software libre y son gratuitos aún así por sus características y buen funcionamiento IntelliJ se ha convertido en una de los preferidos de los programadores Java.

{{< image
    gallery="true"
    image1="image:intellij-holamundo.webp" optionsthumb1="300x200" title1="IntelliJ IDEA"
    caption="IntelliJ IDEA" >}}

Son muchos los beneficios de usar un IDE con un lenguaje compilado y fuertemente tipado como Java. Los IDE aprovechan estas características para proporcionar errores precisos y descriptivos de compilación según se escriben las líneas de código, asistencia de código en métodos disponibles de una clase que aún siendo Java un lenguaje verboso permite escribir código pulsando pocas teclas, _refactors_ de código que permiten cambiar el código existente de forma automatizada evitando mucho trabajo manual que aumenta dramáticamente la productividad, integran una terminal para ejecutar comandos sin necesidad de salir del IDE e integración con las herramientas de construcción como Gradle y de pruebas unitarias automatizadas entre otras muchas funcionalidades muy útiles.

## Distribuciones GNU/Linux

Uno de los beneficios del software libre y del código abierto es que normalmente es gratuito, no tienen costosas licencias comerciales para usar el software lo que permite usarlo sin grandes barreras ya sea con fines educativos o comerciales.

GNU/Linux siempre ha sido un sistema operativo con un excelente soporte y herramientas destinadas a los desarrolladores con cantidad de lenguajes de programación disponibles, compiladores, editores y una excelente línea de comandos con multitud de utilidades para automatizar tareas repetitivas. A día de hoy hay muchas distribuciones de GNU/Linux que son tan fáciles de instalar y usar como Windows o macOS ni tienen nada que envidiar a estos sistemas operativos comerciales con licencias privativas. En los últimos años cualquier hardware conocido funciona bien sin necesidad de instalar controladores adicionales y el soporte para juegos y controladores gráficos poseen un rendimiento similar a Windows.

Dado que muchos de los servidores funcionan con GNU/Linux su conocimiento es muy útil en la vida laboral y demandado por las empresas. Usarlo es la mejor forma de aprender y adquirir conocimientos.

El primer paso para usar GNU/Linux es [elegir una distribución GNU/Linux][blogbitix-190] según nuestras preferencias, una de las más populares y recomendadas para usuarios que dan el salto desde Windows es Ubuntu. El siguiente paso es [descargar e instalar Ubuntu paso a paso desde cero][blogbitix-232] con un instalador guiado con varios pasos en los que hay que hacer poco más que seleccionar el idioma, la disposición del teclado y nombre de usuario, en menos de una hora se instala [Ubuntu][ubuntu]. Uno de los motivos por el que muchos usuarios siguen usando Windows son los juegos, con [Wine para ejecutar programas de Windows en GNU/Linux][blogbitix-364] y [la plataforma de juegos Steam][blogbitix-431] la mayoría de los juegos desarrollados para Windows funcionan en GNU/Linux.

{{< image
    gallery="false"
    image1="logotype:ubuntu.svg" optionsthumb1="100x100" title1="Ubuntu"
    image2="logotype:fedora.svg" optionsthumb2="100x100" title2="Fedora"
    image3="logotype:archlinux.svg" optionsthumb3="100x100" title3="Arch Linux" >}}
{{< image
    gallery="false"
    image1="logotype:gnu.svg" optionsthumb1="100x100" title1="GNU"
    image2="logotype:linux.svg" optionsthumb2="100x100" title2="Linux" >}}

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoJava8" %}}

{{% /post %}}
