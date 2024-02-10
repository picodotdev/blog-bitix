---
pid: 695
type: "post"
title: "Obsidian, una herramienta para almacenar conocimiento"
url: "/2024/02/obsidian-una-herramienta-para-almacenar-conocimiento/"
date: 2024-02-10T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "logotype:obsidian.svg"
imagePost: "logotype:obsidian.svg"
tags: ["gnu-linux", "planeta-codigo"]
summary: "Hace ya unos meses desde que estoy usando Obsidian, pasado este tiempo considero que es un salto a los anteriores editores y aplicaciones de notas que he estado usando. A nivel personal como diario y otros asuntos ofrece varias funcionalidades que facilitan el gestionar el conocimiento y que no he visto en los anteriores editores que he usado. Es más que un editor de texto es una herramienta para almacenar conocimiento."
---

{{% post %}}

{{< logotype image1="obsidian.svg" >}}

Las aplicaciones son las que proporcionan las funcionalidades que como usuarios de escritorio necesitamos para facilitar nuestro trabajo y necesidades. Hay herramientas para multitud de propósitos, las que proporciona el sistema operativo o entorno de escritorio.

* [El entorno de escritorio GNOME, simple, elegante y completo][blogbitix-660]

Nuestras necesidades y conocimientos cambian y por ello dejamos de usar algunas aplicaciones o cambiamos a otras aplicaciones equivalentes con alguna característica que permite o simplifica nuestra necesidad, a veces que una aplicación tenga una característica que deseamos es el motivo por el cambiar de una aplicación a otra, a estas características se les conoce como _killer features_.

En la categoría de aplicaciones para tomar notas hay una buena cantidad de ellas, desde editores de texto simples, editores avanzados para desarrolladores, paquetes ofimáticos para documentos y herramientas para gestionar conocimiento como [Obsidian][obsidian] que es de la que hablaré en este artículo.

{{< tableofcontents >}}

## Mis anteriores alternativas

A lo largo de muchos años he pasado por muchas aplicaciones he ido cambiado de unas a otras según mis necesidades y el ir descubriendolas. Si originalmente guardaba todas mis contraseñas en un simple archivo de texto, una mala recomendación por no estar cifrado, ahora utilizo [KeePassXC][keepassxc] para guardar cifradas las contraseñas, utilizar contraseñas fuertes y únicas para cada servicio.

* [Guardar contraseñas de forma segura con KeePassXC][blogbitix-196]

En los editores de texto también he pasado por varios desde el editor de texto del sistema operativo o entorno de escritorio a [Google Docs][google-docs], [Evernote][evernote] y [Notion][notion] por sus características de guardar los archivos en la nube y poder compartirlos entre varios ordenadores.

Para los artículos del blog utilizo varias de estas, dejé de usar Evernote y cambié a Notion y sigo usando Google Docs para realizar las comprobaciones léxicas y sintácticas. Pero para gestionar conocimiento he empezado a usar Obsidian.

## Uso y características

Obsidian ofrece recopilar y gestionar cualquier tipo de conocimiento de cualquier tema en el que estemos interesados desde un diario, recetas de cocina, tareas que nos planifiquemos, viajes, conocimiento técnico. Es posible utilizar Obsidian para volcar en la aplicación mucha parte de la información que acumulamos en nuestro cerebro.

Como diario requiere dedicarle un tiempo a la semana pudiendo recoger qué cosas destacadas han pasado en el día, pensamientos y emociones, pasado un tiempo releerlo y retocar algún aspecto o añadir algún detalle más a una determinada nota, y de recordar detalles que tal vez ya no teníamos tan presentes.

Obsidian no es simplemente un editor de texto, es una aplicación para organizar el conocimiento con características específicas para facilitar la tarea. Entre las características que ofrece están las siguientes:

* Carpetas: replica la estructura jerárquica en el sistema de archivos. El inconveniente de las carpetas es que las notas se relacionan entre ellas y las carpetas son una estructura que no permite crear esas relaciones. Para solventar las limitaciones de las carpetas están las etiquetas y los enlaces entre notas o _backlinks_ que para mi son las verdaderas _killer features_ de Obsidian.
* Etiquetas: es posible convertir cualquier palabra o conjunto de palabras en una etiqueta, la potencia de las etiquetas es que estas se pueden incluir en cualesquiera notas y encontrar rápidamente todas las ocurrencias de esa etiqueta en todas las notas. Otra de sus potentes características es que las etiquetas se pueden anidar en una estructura jerárquica.
* Enlaces entre notas o _backlinks_: los _backlinks_ son otra forma de relacionar las notas entre ellas son enlaces que permiten formar una red entre las notas y navegar por el conocimiento.
* Marcadores: los marcadores permiten añadir un recordatorio a una nota o a una sección de la nota como otra forma de organización y para volver sobre esas notas con posterioridad y rapidez.
* Archivos adjuntos: es posible guardar documentos en otros formatos como imágenes y añadir enlaces en las notas.

Para las notas de texto utiliza un formato similar a [Markdown][markdown]. Todas estas características son proporcionadas por la aplicación de Obsidian de escritorio, que tiene una estética muy simple y fácil de acceder a las carpetas, etiquetas, enlaces entre notas o _backlinks_ y los marcadores a través de las opciones de menú y paneles situados en los laterales del editor.

Hay muchos complementos que añade funcionalidades adicionales. Obsidian tiene una versión gratuita y la opción de pago para sincronizar las bóvedas de conocimiento entre diferentes equipos.

{{< image
    gallery="true"
    image1="image:obsidian-1.webp" optionsthumb1="650x450" title1="Obsidian" >}}
{{< image
    gallery="true"
    image1="image:obsidian-2.webp" optionsthumb1="200x150" title1="Obsidian"
    image2="image:obsidian-3.webp" optionsthumb2="200x150" title2="Obsidian"
    image3="image:obsidian-4.webp" optionsthumb3="200x150" title3="Obsidian"
    caption="Obsidian" >}}

### Instalación e inicio de Obsidian

La instalación de obsidian es muy sencilla en los sistemas [GNU][gnu]/[Linux][linux] con [Flatpak][flatpak] es independiente de la distribución, también está el paquete de la distribución y otros formatos como [AppImage][appimage] o Snap. En [macOS][macos] y [Windows][windows] es posible descargar el binario de instalación propios de estos sistemas operativos que se obtienen desde su [página de descargas](https://obsidian.md/download).

{{< youtube
    video="mqpXEjRhZz0" >}}

{{% /post %}}