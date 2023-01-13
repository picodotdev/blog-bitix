---
pid: 532
type: "post"
title: "Crear un archivo Zip con Java, comprimir y descomprimir datos"
url: "/2020/11/crear-un-archivo-zip-con-java-comprimir-y-descomprimir-datos/"
date: 2020-11-15T00:30:00+01:00
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

Desde las primeras versiones de Java en el JDK se incluyen clases en el paquete _java.util.zip_ que heredan de las del paquete _java.io_ del sistema de entrada y salida con las que crear archivos comprimidos en formato Zip y comprimir datos en este formato. Comprimir los archivos que permite reducir sensiblemente el tamaño de algunos tipos de archivos como los basados en texto o ciertos archivos que de por si no están comprimidos. En otros tipos de archivo que en su formato ya están comprimidos como las imágenes JPEG o WebP la reducción de tamaño no es tan grande. Al contrario que los los algoritmos de compresión para imágenes JPEG y WebP que son com pérdida de datos, los algoritmos de compresión de archivos son sin pérdida, esto quiere decir que los datos obtenidos al comprimirlos y posteriormente descomprimirlos son exactamente los mismos que los originales.

Los archivos de texto o binarios no comprimidos comprimirlos supone un considerable reducción respecto del tamaño del archivo original no comprimido, un archivo XML del 20 MiB se puede quedar en 1.5 MiB, es decir, una tasa de compresión de 13 o lo que es lo mismo el fichero comprimido ocupa 13 veces menos.

Aparte del ahorro de espacio, crear un archivo Zip que agrupe varios es útil en ciertos casos. Las aplicaciones web como respuesta solo pueden devolver un archivo o documento por petición, un documento HTML, un recurso o un archivo. Para devolver varios archivos a la vez como una exportación de varios documentos dada la limitación del protocolo HTTP de petición y respuesta no es posible. Para dar solución a esta limitación normalmente se crea un archivo Zip que contenga los varios a devolver. [Configurar los servidores web Nginx y Apache para comprimir los recursos][blogbitix-155] minimiza los datos transferidos desde el servidor al cliente.

{{< tableofcontents >}}

### Clases de Java para comprimir y descomprimir

Las clases del paquete [java.util.zip](javadoc11:java.base/java/util/zip/package-summary.html) permiten comprimir y descomprimir datos utilizando diferentes algoritmos. Se proporcionan soporte para el formato [archivo Zip][wikipedia-zip], [GZIP][wikipedia-gzip], el algoritmo de compresión [DEFLATE][wikipedia-deflate] usando la librería [ZLIB][wikipedia-zlib] y otras clases para la corrección de errores o _checksums_.

Para la creación de archivos Zip las clases básicas son [ZipEntry](javadoc11:java.base/java/util/zip/ZipEntry.html) que representa un flujo de datos comprimido o archivo en un archivo Zip, [ZipFile](javadoc11:java.base/java/util/zip/ZipFile.html) representa un archivo Zip, [ZipInputStream](javadoc11:java.base/java/util/zip/ZipInputStream.html) que permite descomprimir los flujos de datos al leerlos y [ZipOutputStream](javadoc11:java.base/java/util/zip/ZipOutputStream.html) que permite comprimir flujos de datos.

### Cómo crear un archivo Zip con Java

El siguiente código permite comprime varios archivos en diferentes formatos en un único archivo Zip. Este caso es el de una aplicación Java de línea de comandos, el código para una aplicación web consistiría en crear el archivo Zip y devolver como resultado el _stream_ de _ZipOutputStream_ de datos en el [OutputStream](javadoc11:java.base/java/io/OutputStream.html) empleado por la aplicación web para devolver el resultado al cliente. 

Los archivos de extensión _jar_ utilizados en Java para crear librerías y _war_ para empaquetar aplicaciones web no son más que archivos Zip con estas extensiones.

{{< code file="Main-compress.java" language="java" options="" >}}
{{< code file="System.out-compress" language="plain" options="" >}}

### Cómo descomprimir un archivo Zip con Java

El proceso contrario a la compresión es la descompresión, la descompresión permite recuperar los datos originales. Para esto se utiliza la clase _ZipFile_ en el caso de leer de un archivo, la clase _ZipInputStream_ también permite leer el Zip creado con _ZipOutputStream_.

{{< code file="Main-decompress.java" language="java" options="" >}}
{{< code file="System.out-decompress" language="plain" options="" >}}

### Listar el contenido de un archivo Zip con Java

En Java el contenido de un _ZipFile_ se representa con la clase _ZipEntry_, el siguiente código lista el contenido de un archivo Zip.

{{< code file="Main-list.java" language="java" options="" >}}
{{< code file="System.out-list" language="plain" options="" >}}

Usando la aplicación integrada en el entorno de escritorio o sistema operativo para visualizar el contenido del archivo Zip se observa que contiene comprimidos el conjunto de archivos añadidos en él.

{{< image
    gallery="true"
    image1="image:archivo-zip.webp" optionsthumb1="300x200" title1="Contenido de archivo Zip"
    caption="Contenido de archivo Zip" >}}

O listar el contenido de un archivo Zip desde la linea de comandos.

{{< code file="unzip.sh" language="bash" options="" >}}

### Otros formatos de archivos comprimidos

Los algoritmos ofrecidos en la API de Java no son los únicos, hay otros con diferentes características. Zip es un formato de archivos comprimidos popular disponible en los sistemas operativos mayoritarios como [Windows][windows], [GNU][gnu]/[Linux][linux] y [macOS][macOS] ofrecen soporte para comprimir y descomprimir archivos con este formato. Otro algoritmos distintos al Zip son mejores en varios aspectos, ofreciendo ratios de compresión mayores o con velocidades de compresión y descompresión mayores.

Algunos algoritmos de compresión alternativos son [bzip2][wikipedia-bzip2], [lzma][wikipedia-lzma], [lzo][wikipedia-lzo] o [zstd][wikipedia-zstd] para los que Java no proporciona clases en el JDK y en algunos de ellos no hay ninguna librería que permite trabajar en Java con estos formatos de archivo. La única alternativa es [ejecutar un proceso del sistema que invoque el comando del sistema][blogbitix-132] para realizar la compresión, la descompresión o listar el contenido del archivo.

{{% sourcecode git="blog-ejemplos/tree/master/JavaZip" command="./gradlew run" %}}

{{< reference >}}
* [The Canterbury Corpus](https://corpus.canterbury.ac.nz/descriptions/)
* [Compression Comparison Benchmarks: zstd vs brotli vs pigz vs bzip2 vs xz](https://community.centminmod.com/threads/round-3-compression-comparison-benchmarks-zstd-vs-brotli-vs-pigz-vs-bzip2-vs-xz-etc.17259/)
{{< /reference >}}

{{% /post %}}
