---
pid: 662
type: "post"
title: "Obtener el ancho y alto, escalar y convertir a otro formato imágenes con Java"
url: "/2022/11/obtener-el-ancho-y-alto-escalar-y-convertir-a-otro-formato-imagenes-con-java/"
date: 2022-11-25T20:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Las aplicaciones que tratan con imágenes es posible que necesiten conocer algunos datos de la imagen como anchura y altura, realizar algunas operaciones de manipulación básicas como escalado y conversión entre formatos. El propio JDK de Java ofrece algunas clases y soporta varios de los formatos más comunes de imagen. Para usos más avanzados hay que recurrir a librerías y si no fueran suficientes a comandos del sistema más avanzados."
draft: true
---

{{% post %}}

{{< logotype image1="java.svg" >}}

El lenguaje de programación Java es un lenguaje de propósito general con el que es posible realizar cualquier tarea. Sin embargo, Java como lenguaje o plataforma aparte de [Android][android] el mayor uso que se le da es para aplicaciones y servicios en el lado del servidor.

Una de las necesidades que pueden surgir es escalar una imagen o cambiarle de formato, aunque Java solo ofrece un soporte básico para el tratamiento de imágenes, hay varias librerías pero pocas bien mantenidas y con buen soporte, si ni una librería ofrece la funcionalidad deseada en el caso más extremo se puede invocar a un proceso del sistema que ofrezca la funcionalidad com [ImageMagick][imagemagick] o [FFmpeg][ffmpeg].

La conversión y cambio de formato es un proceso costoso en CPU y memoria ya que requieren cargar la imagen en memoria más si las imágenes a tratar son muy grandes, de varias decenas de megabytes.

El propio JDK ofrece en su API soporte para los formatos de imágenes más comunes y algunas manipulaciones de imagen, hay varias librerías Java para la manipulación de imágenes y finalmente están los comandos del sistema.

* [Cómo crear miniaturas o vistas previas de imágenes con ImageMagick][blogbitix-161]
* [Comando para convertir imágenes JPEG y PNG a WebP][blogbitix-447]

{{< tableofcontents >}}

### Formatos de imágenes

Hay varios formatos de imágenes que se pueden categorizar en dos grandes grupos: imágenes sin pérdida o con pérdida. Los formatos sin pérdida son más fieles a la imagen original pero con mayor tamaño que la equivalente en un formato con pérdida de calidad, en las imágenes con pérdida de calidad los algoritmos tratan de que visualmente la pérdida sea lo menos apreciable posible con un tamaño de imagen significativamente menor.

El formato png es un formato sin pérdida que ofrece buenos resultados para capturas de pantalla del ordenador. Para fotos el formato jpg ofrece un archivo de tamaño significativamente más reducido con una calidad bastante fiel al original.

Más recientemente han surgido nuevos formatos de imagen que utilizan otros algoritmos para la reducción de tamaño heic y heif utilizan el algoritmo con coste de licencia de x265 y el formato sin coste de licencia webp que ofrece similar o mejor resultado que el x265 que además soporta imágenes sin pérdida.

### Procesar imágenes con Java

En el propio JDK hay varias clases para la utilización de imágenes en el paquete [java.awt](javadoc17:java.desktop/java/awt/package-summary.html).

#### Formatos de imagen soportados

Los formatos de imagen soportados se pueden obtener mediante código y son extensibles añadiendo librerías al _classpath_, es posible que un formato se soporte en modo lectura pero no en escritura.

{{< code file="Main-formats.java" language="java" options="" >}}

En esta lista de formatos aparece webp como formato soportados en la lectura porque en el ejemplo de código está incluida la librería de TwelveMonkeys que añade el soporte para la lectura de webp.

{{< code file="Main-formats.out" language="plain" options="" >}}

#### Lectura y escritura de una imagen

La clase que representa una imagen en Java es [BufferedImage](havadoc17:java.desktop/java/awt/image/BufferedImage.html) que se obtiene al leer el archivo del sistema de archivos o el [InputStream](javadoc17:java.base/java/io/InputStream.html), para la lectura y la escritura está la clase [ImageIO](javadoc17:java.desktop/javax/imageio/ImageIO.html).

{{< code file="Main-read-write.java" language="java" options="" >}}

La imagen en formato jpg tiene un tamaño de 192 KB y en formato webp tiene un tamaño de 80 KB, menos de la mitad en webp con una calidad similar.

{{< image
    gallery="true"
    image1="image:gnome.jpg" optionsthumb1="300x200" title1="Imagen en formato jpg"
    image2="image:gnome.webp" optionsthumb2="300x200" title2="Imagen en formato webp"
    caption="Imagenes en formatos jpg y webp" >}}

#### Obtener el ancho y alto de una imagen

Una vez obtenida una instancia de BufferedImage esta clase ofrece métodos para conocer el ancho y alto de la imagen y a partir de estos la proporción o _aspect ratio_ entre ambos valores.

{{< code file="Main-width-height.java" language="java" options="" >}}
{{< code file="Main-width-height.out" language="plain" options="" >}}

#### Escalar una imagen

Escalar una imagen consiste en cambiar de tamaño a la imagen, normalmente el escalado se realiza conservando la proporción de anchura y altura para no distorsionar la imagen original.

El escalado suele realizarse para reducir la imagen con lo que se perderá información o píxeles, dependiendo del algoritmo de escalado aplicado la calidad y tamaño de la imagen resultante es diferente. En un escalado que implica reducir el tamaño los bytes de la imagen escalada serán menores.

{{< code file="Main-scale-java.java" language="java" options="" >}}

Dependiendo de las opciones empleadas en el escalado la calidad y tamaño resultante es diferente y hay que conocerlas. Utilizando una librería permite realizar la operación de escalado con mejor calidad.

{{< image
    gallery="true"
    image1="image:gnome-scaled-java.jpg" optionsthumb1="650x450" title1="Imagen escalada con Java"
    caption="Imagen escalada con Java" >}}

#### Cambiar el formato de una imagen

Cambiar de formato a una imagen es otra operación común, por ejemplo convertir al formato webp que ofrece una calidad mejor en el mismo tamaño o un significativo reducción de tamaño que puede llegar al más del 50% con una calidad similar.

Una vez cargada la imagen en un BufferedImage es posible escribir la imagen a un archivo en cualquier otro formato de escritura soportado.

{{< code file="Main-convert-java.java" language="java" options="" >}}

#### Librerías de terceros

Hay varias librerías que añaden o facilitan algunas funcionalidades sobre la API de Java. [Thuilmator](https://github.com/coobird/thumbnailator) permite realizar escalados de imágenes de una forma más sencilla que la API de Java.

{{< code file="Main-scale-thuilmator.java" language="java" options="" >}}

Otra librería es [TwelveMonkeys](https://github.com/haraldk/TwelveMonkeys) que añade soporte para algunos formatos de imagen como webp.

{{< code file="Main-scale-twelvemonkeys.java" language="java" options="" >}}

El tamaño de la imagen escalada por el código Java es de 28 KB, el tamaño de la imagen de Thuilmator es de 38 KB y el tamaño de TwelveMonkeys es de 26 KB con algunas pequeñas diferencias apreciables en el resultado de cada una de ellas.

{{< image
    gallery="true"
    image1="image:gnome-scaled-thumbnailator.jpg" optionsthumb1="200x150" title1="Imagen escalada con Thumbnailator"
    image2="image:gnome-scaled-twelvemonkeys.jpg" optionsthumb2="200x150" title2="Imagen escalada con Twelvemonkeys"
    image3="image:gnome-scaled-imagemagick.jpg" optionsthumb3="200x150" title3="Imagen escalada con ImageMagick y Java"
    caption="Imagen escaladas con diferentes librerías (Thumbnailator, Twelvemonkeys y ImageMagick)" >}}

#### Usando ImageMagick

Las funcionalidades de Java y de las librerías de Java para casos avanzados no son suficientes. El software de [ImageMagick][imagemagick] ofrece a través de un comando muchas más opciones para la manipulación de imágenes. El inconveniente de ImageMagick es que para ser usado desde Java requiere instalar el comando en el sistema. El uso de ImageMagick desde Java es como la invocación de cualquier otro proceso del sistema, es posible pasar la imagen a manipular por la entrada estándar del sistema y obtener la imagen resultado por la salida estándar del sistema del proceso.

* [Cómo ejecutar un proceso del sistema con Java][blogbitix-132]

Si no hay ninguna librería Java que proporcione soporte para la escritura a formato webp el siguiente código invoca el comando _convert_ de ImageMagick para realizar la conversión. El tamaño de la imagen en formato webp es de 80 KB que son muchos menos que en cualquiera de los formatos jpg generados con Java de los casos anteriores.

{{< code file="Main-scale-imagemagick.java" language="java" options="" >}}
{{< code file="Main-convert-imagemagick.java" language="java" options="" >}}

{{< image
    gallery="true"
    image1="image:gnome-convert-imagemagick.webp" optionsthumb1="300x200" title1="Imagen convertida a webp con ImageMagick y Java"
    caption="Imagen convertida a webp con ImageMagick y Java" >}}

{{% sourcecode git="blog-ejemplos/tree/master/" command="./gradlew run" %}}

{{< reference >}}
* [How Can I Resize an Image Using Java?](https://www.baeldung.com/java-resize-image)
{{< /reference >}}

{{% /post %}}