---
pid: 580
type: "post"
title: "Cómo crear y poner subtítulos a un vídeo"
url: "/2021/06/como-crear-y-poner-subtitulos-a-un-video/"
date: 2021-06-05T12:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:gnome-subtitles.webp"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "Los subtítulos permiten mostrar una transcripción en texto del audio y diálogos en vídeos, películas y series. Algunos reproductores de vídeo permiten seleccionar la pista de subtítulos embebidos en los propios vídeos o añadir nuevas pistas de subtítulos a los vídeos. Hay programas editores que permiten crear y modificar los archivos de subtítulos. También hay algunas páginas de las que descargar los archivos subtítulos."
---

{{% post %}}

Los formatos de vídeo como películas o vídeos de [YouTube][youtube] ofrecen la posibilidad de poner subtítulos con la transcripción en texto del audio y diálogos. YouTube es capaz de crear subtítulos de forma automática reconociendo las palabras habladas pero es bastante impreciso y en la transcripción contiene numerosos errores. Algunas películas contienen además del audio el texto con los subtítulos, algunos reproductores de vídeo permiten mostrar los subtítulos.

Los subtítulos son muy útiles en diferentes situaciones, para personas con incapacidades auditivas como una forma de accesibilidad, si el audio está en un idioma que no se domina tener los subtítulos en algún idioma entendido permite comprender los diálogos, para ver películas en la pista original de audio tal como se grabó con las voces originales de los actores y no las del doblaje a otros idiomas y también para aquellas personas que quieren aprender un idioma escuchando el audio y con los subtítulos.

Para mostrar subtítulos en un vídeo o película es necesario el archivo de subtítulos en el idioma que se quieran mostrar los subtítulos. Los archivos de subtítulos son archivos de texto que contienen el texto de los subtítulos e información de tiempos en los que se muestran y cuando se ocultan.

{{< tableofcontents >}}

## Cómo crear archivos de subtítulos para vídeos

Los archivos de subtítulos se crean con aplicaciones editores de subtítulos con una interfaz gráfica adaptada para facilitar la creación de subtítulos, además de soportar múltiples formatos de archivos de subtítulos. Estas aplicaciones muestran el vídeo con la capacidad de reproducirlo a diferentes velocidades, cada uno de los subtítulos y permiten especificar en que tiempo exacto se muestran y se ocultan.

Una aplicación editor de subtítulos en [GNU][gnu]/[Linux][linux] es [GNOME Subtitles][gnome-subtitles] otra es [Gaupol][gaupol], en [Windows][windows] dos de las aplicaciones de software libre disponibles son [Subtitle Edit][subtitle-edit] y [Subtitle Workshop][subtitle-workshop].

Estos editores muestran una previsualización del vídeo, un área para introducir el texto de los subtítulos y los tiempos en los que se muestran y ocultan. Además, tiene modos de traducción para crear subtítulos a partir de los archivos de subtítulos existentes de otros idiomas.

{{< image
    gallery="true"
    image1="image:gnome-subtitles.webp" optionsthumb1="650x450" title1="Editor de subtítulos de GNOME Subtitles"
    caption="Editor de subtítulos de GNOME Subtitles" >}}

Este es el contenido de un archivo de subtítulos en formato _srt_, para vídeos embebidos en una página web hay que usar el formato _vtt_ que es muy similar de modo que en este caso hay que convertir el _srt_ a _vtt_ si el programa editor de subtítulos no lo permite exportar directamente. También es posible editar el archivo de texto de subtítulos y transformar un _srt_ en un _vtt_ ya que son muy similares salvo una línea de cabecera.

{{< code file="darkest-dungeon-intro.srt" language="plain" options="" >}}
{{< code file="darkest-dungeon-intro.vtt" language="plain" options="" >}}

En este vídeo se pueden activar los subtítulos con la transcripción del audio, aunque en este caso el vídeo original ya incluye los subtítulos.

{{< video
    video="darkest-dungeon.mp4"
    poster="darkest-dungeon.webp"
    subtitles="videos/darkest-dungeon-intro-es.vtt"
    subtitlesLabel="Español" subtitlesLang="es"
    caption="Vídeo subtitulado" >}}

## Cómo descargar pistas de subtítulos

Algunas páginas permiten descargar archivos de subtítulos de películas y series en diferentes idiomas ya creados por otras personas, dos de ellas son [Audio-Track][audio-track] y [Subscene][subscene]. Tiene un buscador para buscar por el título de la película o serie.

Un formato de archivo de subtítulos es _srt_, estos archivos hay que cargarlos en el programa reproductor usado para visualizar la película.

## Cómo poner archivos de subtítulos en el reproductor VLC

El [reproductor VLC][vlc] permite cargar archivos de subtítulos específicos de diferentes formatos, aunque algunos vídeos incluyen embebidos varias pistas de subtítulos en diferentes idiomas. En el menú _Subtítulos > Pista de subtítulos_ se muestran las pistas disponibles en el vídeo.

Si el vídeo no tiene pistas de subtítulos se pueden cargar archivos de subtítulos también desde el menú _Subtítulos > Añadir archivo de subtítulos..._. Una vez cargado el archivo de subtítulos y seleccionada la pista de subtítulos al reproducir el vídeo se irán mostrando los subtítulos en la parte inferior del vídeo con la transcripción.

{{< image
    gallery="true"
    image1="image:vlc-subtitles.webp" optionsthumb1="650x450" title1="Subtítulos en el reproductor de vídeo VLC"
    caption="Subtítulos en el reproductor de vídeo VLC" >}}

El reproductor VLC también permite modificar la apariencia de los subtítulos como su posición, fuente de texto, el tamaño de la fuente y el color de la fuente, su opacidad para que los subtítulos no oculten completamente la imagen sobre la que se muestran, el grosor del borde y su color, este borde permite que los bordes del texto no se confundan con la imagen si hay alguna escena en que tengan colores similares.

{{< image
    gallery="true"
    image1="image:vlc-subtitles-preferences.webp" optionsthumb1="650x450" title1="Preferencias de subtítulos de VLC"
    caption="Preferencias de subtítulos de VLC" >}}

## Crear y poner subtítulos con el editor de YouTube

Los vídeos de YouTube también se pueden subtitular con unos propios mejores que las transcripciones que hace el YouTube convirtiendo el audio del vídeo en texto. Se pueden crear desde [YouTube][youtube-studio] o subir un archivo de subtítulos creados con alguna de las aplicaciones anteriores.

En la [sección de ayuda](https://support.google.com/youtube/) hay información de cómo añadir subtítulos en los vídeos de YouTube y los [tipos de archivos soportados](https://support.google.com/youtube/answer/2734698). Hay algunos [servicios que permiten obtener los subtítulos](https://support.google.com/youtube/answer/9861599?hl=en&ref_topic=7296214) de vídeos sin subtítulos aunque no son gratuitos.

Las ventajas de añadir subtítulos en vídeos de YouTube son una mayor audiencia, mejor retención de visualización, mejor accesibilidad, mejor SEO y mejor entendimiento al prescindir de las pronunciaciones.

{{< image
    gallery="true"
    image1="image:youtube-subtitles.webp" optionsthumb1="650x450" title1="Editor de subtítulos de YouTube"
    caption="Editor de subtítulos de YouTube" >}}

{{< reference >}}
* [5 Free Subtitle Editor Software for Windows 10](https://www.ilovefreesoftware.com/21/windows-10/free-subtitle-editor-windows-10.html)
* [5 Great Subtitle Editors in Linux Systems in 2021](https://www.debugpoint.com/2015/08/3-great-subtitle-editors-in-linux-systems/)
{{< /reference >}}

{{% /post %}}
