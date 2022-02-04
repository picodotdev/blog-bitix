---
pid: 565
type: "post"
title: "Programas basados en consola con Java usando Lanterna"
url: "/2021/04/programas-basados-en-consola-con-java-usando-lanterna/"
date: 2021-04-04T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:lanterna.png"
tags: ["java", "planeta-codigo"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Todavía hoy en día la terminal no ha desaparecido, y no lo hará, aún habiendo pasado ya algunas décadas de la aparición de las interfaces gráficas. Algunas ventajas de la terminal es que conociendo los comandos es más fácil realizar una tarea que con un programa basado en una ventana con botones que hay que pulsar usando el ratón, además las tareas usando comandos se pueden automatizar con _scripts_ y combinar varios donde la entrada de uno sea la salida de otros dando lugar a funcionalidades mucho más complejas que las que realizan los comandos individualmente. También los comandos son más eficientes al no requerir tantos recursos del sistema. Por todo ello los comandos y programas basados en la consola o terminal no van a desaparecer.

En [GNU][gnu]/[Linux][linux] la terminal es una parte importante del sistema con la que es posible realizar muchas de las tareas, es más fácil y habitual para tareas repetitivas. [ncurses][ncurses] es una librería para desarrollar programas basados en texto pero simulando una interfaz gráfica. Se pueden crear ventanas, botones, mensajes de texto, paneles, gestores de disposición o _layouts_, etiquetas, cajas de texto, selectores, botones _check_ y _radio_, diálogos, etc...

En Java está la librería [Lanterna][lanterna] que proporciona similar funcionalidad que ncurses, y quizá más, sirve para el mismo propósito pero con una implementación completamente basada en Java y sin necesidad de recurrir a código nativo. En la [documentación de Lanterna](https://github.com/mabe02/lanterna/blob/master/docs/contents.md) hay varios ejemplos y guías de las [clases principales que forman la API](https://mabe02.github.io/lanterna/apidocs/3.1/).

Un ejemplo de uso posible para Lanterna es realizar un instalador basado en texto para el [_script_ de instalación de Arch Linux][blogbitix-204] que implementé y mantengo desde hace un tiempo. Con Lanterna es posible crear el instalador o configurador para la instalación desatendida y automatizada que junto con [jlink para crear un _runtime_ distribuible de Java][blogbitix-307] es posible ejecutar sin necesidad de instalar Java previamente en el sistema y utilizando la imagen que proporciona Arch Linux sin necesidad de modificarla.

El siguiente código muestra lo que podría ser la pantalla inicial de este instalador usando Lanterna.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="build.gradle" language="groovy" options="" >}}

{{< image
    gallery="true"
    image1="image:lanterna.png" optionsthumb1="650x450" title1="Hola Mundo con Lanterna"
    caption="Hola Mundo con Lanterna" >}}

{{% sourcecode git="blog-ejemplos/tree/master/Lanterna" command="./gradlew distZip && unzip -o -d app/build/distributions/ app/build/distributions/app.zip && ./app/build/distributions/app/bin/app" %}}

{{< reference >}}
* [Formatear con color sentencias SQL o código fuente en la terminal con Java y Jansi][blogbitix-359]
* [Convertir texto o imagen a arte de caracteres ASCII][blogbitix-563]
{{< /reference >}}

{{% /post %}}
