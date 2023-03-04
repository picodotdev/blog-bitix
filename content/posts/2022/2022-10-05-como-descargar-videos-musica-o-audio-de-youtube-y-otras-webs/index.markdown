---
pid: 655
type: "post"
title: "Cómo descargar vídeos, música o audio de YouTube y otras webs"
url: "/2022/10/como-descargar-videos-musica-o-audio-de-youtube-y-otras-webs/"
date: 2022-10-05T21:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:youtube.svg"
tags: ["software", "planeta-codigo"]
summary: "En caso de buscar un vídeo o música es probable que esté subido en YouTube, en el caso de las canciones muchas incluyen el vídeo original que acompaña la canción y de artistas de varias décadas anteriores al inicio de YouTube. Además de contenido antiguo hay muchas personas dedicadas a crear contenido nuevo ya sea como _videoblogs_ o _podcasts_. Con todo el contenido disponible en YouTube descargar un vídeo o solo audio de un vídeo es algo deseado pero que no permite YouTube directamente pero hay aplicaciones que permiten descargar el contenido de YouTube."
---

{{% post %}}

{{< logotype image1="youtube.svg" >}}

El servicio de vídeo [YouTube][youtube] se ha convertido en la plataforma mayoritaria para compartir contenido en formato de vídeo. Prácticamente cualquier contenido de vídeo se puede encontrar en YouTube, incluso vídeos antiguos anteriores a la existencia de YouTube como una buena colección de música.

Algo que no facilita YouTube es descargar su contenido para consumirlo fuera de línea lo que impide a algunos usuarios consumir su contenido según sus necesidades en vez de las que solo permite YouTube.

Algunos usuarios desean guardar sus canciones favoritas y escucharlas fuera de línea como por ejemplo en un _smartphone_ sin necesidad de conexión a internet en cualquier lugar que se disponga del _smartphone_. También para evitar buena cantidad de publicidad que inserta YouTube que son los ingresos que percibe el servicio y los creadores de contenido pero que dada la cantidad de publicidad resulta molesta para los usuarios.

Hay algunas aplicaciones que permiten descargar los vídeos de páginas como YouTube, aplicaciones tanto con interfaz gráfica como de línea de comandos.

Una vez descargado el vídeo o el audio quizá dependiendo del dispositivo donde se pretenda reproducir quizá haya que cambiar el formato de del archivo o se desee extraer únicamente el audio.

* [Cambiar el formato de archivos de vídeo o películas en GNU/Linux][blogbitix-135]

{{< tableofcontents >}}

## Aplicaciones para descargar vídeos de YouTube

Una aplicación con interfaz gráfica en principio es muy intuitiva de usar y no requiere tanto conocimiento inicial para usarla, por el otro lado una aplicación con interfaz de línea de comandos aunque requiere conocer las opciones del comando para realizar la tarea deseada una vez conocido es más rápido conseguir lo que se desea que con una aplicación de interfaz gráfica, para conocer el comando exacto y las opciones se suelen proporcionar ejemplos de uso.

Los gestores de descargas son simplemente una de las categorías de programas que un usuario puede considerar de utilidad. Para cada tarea o necesidad de un usuario es casi seguro que exista un programa específico.

* [Listado de programas esenciales según categoría para GNU/Linux][blogbitix-469]
* [Download managers](https://wiki.archlinux.org/title/List_of_applications#Download_managers)

### Con interfaz gráfica

Un gestor de descargas, permite gestionar las descargas de cualquier fuente lo único necesario es el enlace del contenido a descargar. Algunos gestores de descargas entre las fuentes de descarga que permite está YouTube y es posible descargar un vídeo como únicamente el audio o la música.

Una aplicación específica para descargar vídeos y audio de YouTube eligiendo el formato deseado y en diferentes calidades que ofrece YouTube es [ClipGrab][clipgrab], un gestor de descargas que soporta descargar vídeos de YouTube o el audio de los vídeos es [JDownloader][jdownloader]. ClipGrab solo permite descargar de YouTube y JDownloader permite descargar vídeos de otras muchas páginas. Las dos aplicaciones tienen versiones para los sistemas operativos [Windows][windows], [macOS][macos] así como [GNU][gnu]/[Linux][linux].

{{< image
    gallery="true"
    image1="image:clipgrab.webp" optionsthumb1="300x250" title1="ClipGrab"
    image2="image:jDownloader.webp" optionsthumb2="300x250" title2="JDownloader"
    caption="ClipGrab y JDownloader" >}}

Este es un vídeo descargado de YouTube con un corto de animación de los varios que ha creado la fundación [Blender][blender] que desarrolla el programa de animación 3D y reproducido con aun aplicación reproductor instalado en la computadora.

{{< image
    gallery="true"
    image1="image:spring.webp" optionsthumb1="300x250" title1="Spring"
    caption="Spring" >}}

### De línea de comandos

La línea de comandos es más rápida conociendo el comando y hace fácil la descarga. La opción más conocida es [youtube-dl](https://github.com/ytdl-org/youtube-dl/) y un _fork_ de esta es [yt-dlp](https://github.com/yt-dlp/yt-dlp) que incorpora algunas características adicionales.

Dado que ambos programas están relacionados las opciones del comando son las mismas. Para descargar un vídeo y solo el audio se utiliza el siguiente comando.

{{< code file="youtube-dl.sh" language="bash" options="" >}}

El comando youtube-dl permite otras muchas opciones como la descarga de una imagen y otras opciones de selección para la calidad del vídeo tanto en resolución como en tamaño de archivo descargado así como variables para nombrar el archivo descargado, más opciones para casos de uso avanzados que las aplicaciones más sencillas pero más fáciles de usar con interfaz gráfica.

{{% /post %}}
