---
pid: 135
type: "post"
title: "Cambiar el formato de archivos de vídeo o películas en GNU/Linux"
url: "/2016/04/cambiar-el-formato-de-archivos-de-video-o-peliculas-en-gnu-linux/"
date: 2016-04-03T12:00:00+02:00
updated: 2021-03-21T00:00:00+01:00
index: true
rss: true
sharing: true
comments: true
language: "es"
imagePost: "logotype:ffmpeg.svg"
tags: ["gnu-linux", "software-libre"]
---

{{% post %}}

{{< logotype image1="ffmpeg.svg" title1="FFmpeg" width1="400" image2="linux.svg" >}}

En el artículo anterior comentaba [como cambiar el formato de archivos de música o audio][blogbitix-135], por ejemplo de _wav_ a _mp3_, de _wav_ a _ogg_ o de _mp3_ a _ogg de archivos individuales, de forma masiva los de un directorio o de forma recursiva de toda una biblioteca de música organizada en múltiples directorios.

Para hacer la conversión usaba la herramienta [FFmpeg][ffmpeg], esta misma herramienta sirve también para hacer conversiones en el formato de archivos de vídeo o películas. Las conversiones de los archivos de música nos puede interesar para reducir su tamaño, un _mp3_ ocupa mucho menos que un wav y un archivo _ogg_ es un formato libre de patentes no así el formato _mp3_. En el caso de los vídeos también nos puede interesar hacer una conversión, reducir su tamaño, extraer solo el audio, extraer solo el vídeo u obtener un trozo del vídeo entre varias cosas más que podemos realizar con FFmpeg.

{{< tableofcontents >}}

### Formatos de archivos de vídeo, contenedor y codificador de vídeo y audio

Formatos de vídeo hay bastantes, en el futuro surgirán más y por tanto los reproductores no son capaces de manejar correctamente todos o solo los más comunes, si el reproductor es algo antiguo seguramente los nuevos formatos no sea capaz de reproducirlos, el reproductor puede ser un aparato específico o una Smart TV que pasado un tiempo quedan en parte obsoletos si no pueden actualizarse para soportar nuevos y mejores formatos de vídeo o audio. Por otro lado la calidad de los vídeos varía según el tamaño pudiendo ser menor de lo considerado como alta definición, HD (720 líneas), Full HD (1080 líneas) o 4K (2160 líneas). Según sea la resolución de la televisión o pantalla donde vayamos a visualizar el vídeo no se va a ver mejor aunque el vídeo tenga más resolución que la pantalla por lo que cambiar la resolución del vídeo si es mayor que la resolución de la pantalla haremos que el vídeo ocupe mucho menos sin perder apenas o ninguna calidad de forma perceptible.

Para entender los formatos de archivos de vídeo hay que distinguir tres conceptos que explican bastante bien en una [página de la wiki de Arch Linux relativa a la codificación de vídeo](https://wiki.archlinux.org/index.php/video_encoding): el contenedor, el codificador de vídeo y el codificador de audio. El contenedor determina la estructura en la que son almacenados los bits en el archivo de vídeo, mantiene la sincronicación entre el vídeo y el audio o permite que haya múltiples pistas de vídeo, audio en diferentes idiomas, subtítulos o un menú similar a los presentes en los DVD de los reproductores de salón. El codificador de vídeo aplica su algoritmo de compresión reduciendo considerablemente el tamaño de cada fotograma y el codificador de audio igualmente aplica un algoritmo de compresión que también reduce considerablemente el tamaño del audio. Las dimensiones del vídeo en ancho y alto en pixeles y la tasa de bits por segundo tanto del audio y vídeo determinan la calidad del vídeo, a más resolución y tasa de bits el vídeo tendrá más calidad y se verá mejor siempre que la pantalla donde se visualice lo permita, pero a mayor calidad mayor tamaño del archivo de vídeo.

Contenedores de vídeo son MKV (Matroska), AVI, MP4, WebM, WMV, o FLV siendo MKV, MP4 y AVI los más comunes en el momento de escribir este artículo.

Algunos de los codificadores (_encoders_) y decodificadores (_decoders_) más comunes son:

* Codecs de vídeo: h264, mpeg4, libxvid, vp8, vp9, ...
* Codecs de audio: mp3, ac3, libvorbis, ...

### Obtener codecs soportados por FFmpeg

Si antes de reproducirlos en el reproductor multimedia o Smart TV pruebas en vídeo convertido con el [reproductor multimedia VLC][vlc] has de tener en cuenta que VLC es capaz de reproducir casi todo lo que se le eche, por tanto que funcione con VLC no quiere decir que vaya a funcionar con el reproductor que usemos.

Con el siguiente comando obtenemos la lista de codificadores y decodificadores que es capaz de utilizar FFmpeg:

{{< code file="ffmpeg-codecs.sh" language="bash" options="" >}}

### Obtener información de un archivo de vídeo

Si no se indica la tasa de bits como indican en la [documentación de FFmpeg](https://ffmpeg.org/ffmpeg.html) el codificador seleccionará por defecto una tasa de bits muy baja resultando en una calidad muy pobre por lo que deberemos indicar una similar a la original. Para ver ver la calidad de un vídeo y del audio usamos el parámetro _-i_:

Por otro lado, los vídeos pueden tener múltiples pistas de audio y subtítulos en varios idiomas, para reducir el tamaño del archivo podemos eliminar las pistas de los idiomas que no queramos. Por ejemplo, el siguiente vídeo contiene tres pistas de subtítulos en los idiomas español, inglés y francés y cuatro pistas de audio en japonés, español, francés y español latinoamericano, el siguiente comando nos da información de las pistas o _streams_ en la parte final.

{{< code file="ffmpeg-info.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:ffmpeg-i.png" optionsthumb1="650x450" title1="Información de un vídeo" >}}

### Operaciones comunes con archivos de vídeo

#### Convertir un archivo de vídeo entre formatos

Partiendo de un vídeo en un contenedor MKV, vídeo codificado con H.264 y audio codificado con _mp3_ estos son los comandos que me han servidor para recodificarlo y reproducirlo correctamente en un reproductor multimedia [iomega ScreenPlay TV Link](https://www.google.es/search?q=omega+ScreenPlay+TV+Link&ie=utf-8&oe=utf-8&gws_rd=cr&ei=_Q4BV7WXBcnXU9nQtcgI) que tendrá ya más de 7 años. De MKV/H.264/MP3 a MPG/MPEG2/MP3, a MPG/MPEG1/MP3, a MP4/H.264/AC3 y en el caso de DivX AVI/XViD/MP el vídeo se reproducía con unos molestos frecuentes pequeños parones en la imagen.

Con el parámetro _-c:v_ indicamos el codificador de vídeo, con _-c:a_ el codificador de audio y con la extensión del archivo de salida el contenedor, los tres parámetros forman el formato del archivo de vídeo.

{{< code file="ffmpeg-convert.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:ffmpeg-output.png" optionsthumb1="650x450" title1="Salida conversión de MKV a MPG" >}}

#### Cambiar la resolución del vídeo

Con la información de la calidad el vídeo y audio original lo indicamos en la conversión con los parámetros _-b:v_ y _-b:a_ para el vídeo y audio de los como se ha usado en los ejemplos anteriores. Si queremos cambiar la resolución del vídeo lo indicamos con el parámetro _-vf_, reduciremos también la tasa de bits dedicados si el tamaño del vídeo de salida es menor.

{{< code file="ffmpeg-scale.sh" language="bash" options="" >}}

#### Extraer el audio de un vídeo

Con FFmpeg también nos es posible extraer el audio de un vídeo a un archivo de sonido _mp3_ o _ogg_ o quitar el audio del vídeo, para ello con el parámetro _-vn_ omitiremos el vídeo y con _-an_ omitiremos el audio.

{{< code file="ffmpeg-extract.sh" language="bash" options="" >}}

Para hacer la eliminación de las pistas usamos el siguiente comando donde los parámetros _-map_ son las pistas o _streams_ que queremos copiar y conservar del original en el nuevo archivo, en este caso solo la pista del vídeo y la pista de audio en español. Dependiendo del número de pistas para otros idiomas que tenga el archivo y de la duración del vídeo el ahorro de espacio que conseguiremos será más o menos notable. Haciendo un cálculo suponiendo algunas cifras habituales un audio a 320kb/s para una película de 120 minutos el audio ocupa alrededor de 281 MiB que ya es una cifra notable ((320 kilobits/segundo / 8 bits/byte) * 60 segundos/minuto * 120 minutos / 1024 kilobytes/megabyte = 281,25 megabytes) y a lo que hay que multiplicar cada pista de audio que eliminemos del original.

{{< code file="ffmpeg-map.sh" language="bash" options="" >}}

Si no tenemos instalado un reproductor de vídeo con el comando `ffplay` podemos reproducirlo, quizá en algún caso nos sirva aunque esté destinado principalmente para pruebas ya que no ofrece controles para pausar o detener la reproducción.

{{< code file="ffplay.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:ffplay.jpg" optionsthumb1="300x250" title1="FFplay" >}}

#### Extraer una imagen de un vídeo

Otra operación es extraer una imagen o fotograma de un archivo de vídeo indicando el tiempo específico del vídeo de la que se quiere la imagen. El parámetro _-ss_ indica el tiempo específico en segundos desde el inicio del fotograma y el parámetro _-frames:v_ indica que solo se desea un fotograma, el parámetro _-i_ especifica el vídeo de entrada y _image.jpg_ es el archivo de salida con el formato de imagen según la extensión indicada.

{{< code file="ffmpeg-image.sh" language="bash" options="" >}}

Esta operación de obtener un fotograma de una película o vídeo se puede combinar con [la herramienta j2pa para convertir una imagen a arte de caracteres ASCII][blogbitix-563].

### Realizar conversiones de forma masiva

Al igual que comentaba en el artículo de convertir archivos de audio todas estas operaciones son realizables de forma masiva en un directorio o recursiva en múltiples directorios con los siguientes comandos.

Otras opciones útiles disponibles en los comandos de FFmpeg son:

* _-t_: para limitar la operación hasta el tiempo en segundos indicado.
* _-ss_: para empezar desde desde un punto posterior relativo al inicio.
* _-sseof_: como _-ss_ pero desde el final.

{{< code file="ffmpeg-masive.sh" language="bash" options="" >}}

### Herramientas gráficas para convertir formatos y editar vídeos

Si preferimos realizar la conversión mediante una interfaz gráfica podemos se pueden utilizar varias herramientas, una de ellas es [HandBrake][handbrake] específica para realizar conversiones con multitud de opciones con la que es posible hacer las mismas operaciones que con FFmpeg e incluye preconfiguraciones para producir resultados compatibles y eficientes para dispositivos y páginas como YouTube o compartirlos en redes sociales. Tiene la opción de realizar encolar tareas de conversión a aplicar a varios archivos.

Otra herramienta de conversión pero también de edición son [Pitivi][pitivi] o [OpenShot][openshot], como herramientas de edición permiten crear vídeos con fragmentos de varios vídeos, cambiar o añadir el audio, definir la resolución y formato de salida.

#### Herramientas para archivos multimedia

Hay muchas otras operaciones y herramientas para archivos multimedia de vídeo o audio en formato de línea de comandos o con interfaz gráfica. En esta página [lista de aplicaciones multimedia](https://wiki.archlinux.org/index.php/List_of_applications/Multimedia) de la [wiki de Arch Linux][archlinux-wiki].

{{< reference >}}
* [Documentación de FFmpeg](https://trac.ffmpeg.org/wiki)
* [Video Encoding](https://wiki.archlinux.org/index.php/video_encoding)
* [Vídeos de ejemplo](http://www.sample-videos.com/)
{{< /reference >}}

{{% /post %}}
