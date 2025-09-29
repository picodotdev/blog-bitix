---
pid: 629
type: "post"
title: "Aplicación con interfaz gráfica para repositorios Git"
url: "/2022/04/aplicacion-con-interfaz-grafica-para-repositorios-git/"
date: 2022-04-19T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:gitg.svg"
tags: ["gnu-linux", "planeta-codigo", "programacion"]
summary: "Una herramienta con interfaz gráfica hace más fácil algunas tareas que usar la línea de comandos directamente. Este también es el caso al trabajar con repositorios de código fuente con la herramienta de control de versiones Git. Usar la linea de comandos tiene sus propias ventajas como permitir automatizar tareas con _scripts_ o realizar operaciones sin tener que usar la interfaz gráfica. La interfaz gráfica y la linea de comandos no son excluyentes, se pueden usar según convenga en cada ocasión. Al trabajar con múltiples repositorios de Git, para realizar operaciones comunes como _commits_, analizar el historial y ver las diferencias en un archivo entre dos versiones una herramienta gráfica también facilita la tarea."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

El sistema de control de versiones [Git][git] posee una línea de comandos con la que se realizan todas las acciones para clonar repositorios, para hacer commits, para ver diferencias, para explorar el historial, para crear ramas y cualquier otra acción. La línea de comandos es muy potente y además es [automatizable con un _script_][blogbitix-158] pero como cualquier otra línea de comandos no es intuitiva y difícil de aprender y en algún caso lenta al teclear un comando, sus opciones y argumentos.

Es aconsejable conocer los comandos básicos de línea de comandos pero en el uso diario o para algunas acciones las aplicaciones con interfaz gráfica son más rápidas, más fáciles de aprender y sin necesidad de recordar las opciones de cada comando. En [GNU][gnu]/[Linux][linux] y [GNOME][gnome] una aplicación cliente de Git es [gitg][gitg].

## La aplicación gitg

[Gitg] es una aplicación de escritorio con interfaz gráfica que permite visualizar un repositorio de Git, su historial y el contenido de los diferentes archivos en sus versiones. No tiene todas las opciones disponibles de la línea de comandos pero si muchos incluyendo los más comunes. Usar una herramienta con interfaz gráfica puede mejorar la productividad al trabajar con varios repositorios de control de versiones en Git y permite a los usuarios que estén empezando a usar Git una forma de usarlo más intuitiva.

Está adaptada a la guía de estilos de las aplicaciones de GNOME, es una aplicación de software libre, está disponible tanto para GNU/Linux, [macOS][macos] como [Windows][windows] y en GNU/Linux se puede instalar como paquete de la distribución o como [una aplicación en formato Flatpak][blogbitix-362] independiente de la distribución [disponible en Flathub con su paquete de gitg](https://flathub.org/apps/details/org.gnome.gitg).

{{< image
    gallery="false"
    image1="logotype:gitg.svg" optionsthumb1="200x150" title1="gitg" >}}

Las operaciones que permite son:

* Ver el historial.
* Ver el contenido de archivos.
* Gestionar el área de _staging_ para componer el _commit_.
* Añadir y clonar un repositorio.
* Actualizar la información del usuario.
* Cambiar varias opciones de preferencias.

En esta capturas se muestran los _commits_, historial y _merges_ en una linea de tiempo gráfica, además de poder ver las ramas locales, las ramas remotas, los orígenes de las ramas así como los archivos modificados en cada _commit_ y las diferencias y cambios realizados en cada archivo.

{{< image
    gallery="true"
    image1="image:gitg-1.webp" optionsthumb1="300x200" title1="Aplicación gitg"
    image2="image:gitg-2.webp" optionsthumb2="300x200" title2="Aplicación gitg" >}}
{{< image
    gallery="true"
    image1="image:gitg-3.webp" optionsthumb1="650x400" title1="Aplicación gitg"
    caption="Aplicación gitg" >}}

Otras aplicaciones cliente de Git que tiene características similares son [Github Desktop][github-desktop] y [SmartGit][smartgit] esta última tiene una licencia propietaria y no es gratuita, ambas tienen su versión de Flatpak en el repositorio de [Flathub][flathub].

{{% /post %}}
