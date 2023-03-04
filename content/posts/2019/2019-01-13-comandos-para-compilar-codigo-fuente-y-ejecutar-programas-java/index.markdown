---
pid: 373
type: "post"
title: "Comandos para compilar código fuente y ejecutar programas Java"
url: "/2019/01/comandos-para-compilar-codigo-fuente-y-ejecutar-programas-java/"
aliases: ["/2019/01/compilar-el-codigo-fuente-y-ejecutar-con-los-comandos-javac-java-y-jar-en-java-8-o-anteriores/"]
date: 2019-01-13T14:00:00+01:00
updated: 2020-10-24T22:30:00+01:00
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

Aún recuerdo cuando empecé a programar con el lenguaje Java sobre el año 1997 que la compilación y ejecución del código la hacía manualmente con los comandos [javac][javac-command] [java][java-command] y [jar][jar-command] en un máquina Intel Pentium a 120 Mhz con tan solo 8 MiB, más tarde 32 MiB, con Windows 95 y Java 1.2, momento en el que ni siquiera había un IDE ni las herramientas de construcción modernas como [Maven][maven] y [Gradle][gradle], había que descargar manualmente las librerías de dependencias en forma de archivos _jar_ que se requiriesen. Luego con JBuilder como IDE este se encargaba de realizar la compilación y ejecución y no hacía falta utilizar estos comandos directamente.

Ahora con herramientas como Gradle además de compilar y ejecutar el programa incluso las dependencias son descargadas de forma automática de repositorios donde se ubican versionadas incluso de forma transitiva, descargando las dependencias de las dependencias.

Usar estos dos comandos directamente ya no es necesario pero como curiosidad comentaré como es su uso ya que siguen formando parte del [entorno de desarrollo mínimo de Java para editar, compilar y ejecutar programas][blogbitix-525]. El comando _javac_ sirve para compilar los archivos de código fuente, dado que los paquetes del código fuente de Java se corresponden con directorios en el sistema de archivos el código fuente se ha de ubicar de forma consistente entre la estructura de directorio y el código fuente. Suponiendo que que hay las siguientes clases que hacen uso de la librería [log4j2][log4j] y están ubicadas en el directorio _src/main/java_ con la misma convención que utiliza Gradle el comando para realizar la compilación y copiar los recursos es el siguiente.

{{< tableofcontents >}}

## Estructura de directorios de un proyecto

Las herramientas de construcción Maven y Gradle siguen una convención en la estructura de directorios y archivos para los proyectos Java. Esta convención proporciona que la estructura de directorios sea la misma en todos los proyectos Java y hace innecesaria una configuración específica para cada proyecto lo que hace a los proyectos más sencillos y fáciles de empezar. El código fuente se ubica en el directorio _src/main/java_ y las clases compiladas se generan en el directorio _target_ o _build_.

{{< code file="tree.sh" language="bash" options="" >}}

## El programa Hola Mundo en Java

El siguiente es el programa mínimo de Java similar al _Hola Mundo_ por el que se suele empezar a programar en cualquier lenguaje de programación, al ejecutarse simplemente emite un mensaje en la consola. El método _main_ es un método estático, con un ámbito de visibilidad público, está dentro de una clase, recibe un _array_ de _strings_ con los argumentos con los que ha invocado su ejecución y retorna _void_. El método _System.out.println()_ recibe un _string_ y lo emite en la salida estándar del programa.

{{< code file="Main.java" language="java" options="" >}}

## Comando para compilar código fuente Java

Para compilar el código fuente Java se usa el compilador de Java, el compilador de Java es un programa que a partir del código fuente genera archivos de _bytecode_, el programa y comando del compilador de Java es _javac_.

Con el parámetro _-classpath_ se indica la ubicación de las librerías o dependencias que requiriere el código fuente, con el parámetro _-sourcepath_ el directorio raíz de los archivos de código fuente, el parámetro _-source_ indica la versión del lenguaje del código fuente, _-target_ la versión de la máquina virtual del _bytecode_ que generará el compilador y con el parámetro _-d_ el directorio donde generan los archivos _class_ con el _bytecode_.

{{< code file="javac.sh" language="bash" options="" >}}

## Comando para ejecutar un programa Java

Una vez generados los archivos de _bytecode_ a partir de la compilación del código fuente su ejecución se realiza con el comando _java_ donde hay que indicar las ubicaciones del los archivos _class_ y las librerías _jar_ necesarias que necesiten, la clase principal con el punto de entrada del programa que contenga un método _public static void main(String[] args)_ y los parámetros del programa que se reciben en el parámetro _args_ del método _main_. En la ejecución del programa la máquina virtual de Java interpreta el _bytecode_ que consiste en traducir a código máquina ejecutable por el procesador del sistema anfitrión.

{{< code file="java.sh" language="bash" options="" >}}
{{< code file="java-run.sh" language="bash" options="" >}}

## Comando para crear una librería Java ejecutable

La distribución de los archivos _class_ se suele realizar usando librerías _jar_ y estas se construyen usando el comando _jar_. El [archivo de manifiesto](https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html) es un descriptor en el que se puede indicar la clase de entrada sin tener que especificarla en el comando _java_ haciendo los archivo _jar_ similar a un ejecutable.

{{< code file="jar.sh" language="bash" options="" >}}
{{< code file="MANIFEST.MF" language="plain" options="" >}}

Y la ejecución de del programa contenido en el archivo _jar_.

{{< code file="java-jar.sh" language="bash" options="" >}}
{{< code file="java-jar-run.sh" language="bash" options="" >}}

Así es la compilación y ejecución de código Java en Java 8 y anteriores, con la [introducción de la modularidad a partir de Java 9][blogbitix-263] esto cambia ya que el _classpath_ queda obsoleto y es reemplazado por el equivalente con módulos _module-path_.

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoJava8" %}}

{{% /post %}}
