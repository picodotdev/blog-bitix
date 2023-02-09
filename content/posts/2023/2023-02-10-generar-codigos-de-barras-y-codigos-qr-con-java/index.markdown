---
pid: 674
type: "post"
title: "Generar códigos de barras y códigos QR con Java"
url: "/2023/02/generar-codigos-de-barras-y-codigos-qr-con-java/"
date: 2023-02-09T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:qr-code.webp"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Los códigos de barras son una forma para que las computadoras reconozcan un código a partir de una etiqueta de un producto. Desde un lenguaje de programación como Java es posible generar códigos lineales o matriciales de diferentes estándares utilizando la librería que implemente el estándar."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Los códigos de barras y códigos QR son una imagen que codifica información y que es fácilmente legible por las computadoras con hardware dedicado. Son muy utilizados y su uso está muy extendido en diferentes ámbitos de la industria como en el sector de la distribución en supermercados, la logística, tiendas y comercios y en las entradas para el acceso a espectáculos digitales o impresas.

Cualquier producto de un supermercado está identificado por un código de barras que las cajeras al realizar el cobro simplemente tienen que pasar para que el lector lea el código, identifique el producto y añada su coste una el recibo de la compra. Todo automatizado por la computadora del cajero y registrando los productos de forma rápida para el cliente y también de forma sencilla por el empleado del supermercado.

Hay diferentes estándares de códigos de barras lineales como los de los productos de supermercado y otro tipo de códigos matriciales como los códigos QR con una imagen en dos dimensiones. En esencia codifican en una imagen el código compuesto de números o caracteres, con una cantidad limitada o de cualquier longitud según el estándar.

* [Los códigos de barras, códigos QR y lectores hardware de códigos][blogbitix-673]

Los lectores son un dispositivo hardware que permiten reconocer los códigos, se conectan al ordenador mediante cable USB o de forma inalámbrica y no necesitan de instalar controladores y son compatibles con cualquier sistema operativo ya sea [Windows][windows], [GNU][gnu]/[Linux][linux] o [macOS][macos]. Los lectores de códigos hardware son en esencia un teclado y el código que reconocen es como si se teclea manualmente con un teclado, teclear el código y son compatibles con cualquier programa.

{{< amazon
    linkids="48b106fcede17cd5010e264dde0c0821"
    asins="B07CBS52KJ" >}}

Hay algunas aplicaciones para generar códigos de barras y códigos QR, dos aplicaciones disponibles en GNU/Linux en formato [Flatpak][flatpak] son [Zint Barcode Studio](https://flathub.org/apps/details/uk.org.zint.zint-qt) que soporta multitud de estándares y [Decoder](https://flathub.org/apps/details/com.belmoussaoui.Decoder) solo sirve para códigos QR pero que permite el reconocimiento dada una imagen o una captura de pantalla.

Los códigos de barras y códigos QR se pueden generar desde un programa e incluir la imagen del código.

{{< tableofcontents >}}

### Librerías para generar códigos de barras y códigos QR en Java

En el caso del lenguaje de programación Java hay varias librerías que permiten generar códigos de barras y códigos QR. Cada librería implementa un subconjunto de todos los estándares de códigos lineales o matriciales y ofrecen una API para el lenguaje de programación.

Como hay varias opciones hay que determinar por algún criterio cuál de ellas utilizar, teniendo en cuenta que varias de estas librerías son de código abierto hechos como un proyecto de una persona. Algunos criterios para elegir una son por supuesto los estándares que soporta este simple hecho ya puede descartar alguna opción si no soporta el estándar que se necesita o hay alguna librería que soporta más estándares o mejor, otro criterio es su popularidad, si tiene actualizaciones recientes de corrección de errores o nuevas características y su antigüedad.

En Java algunas de las librerías de código abierto son las siguientes pero que por su estado de mantenimiento es mejor otra opción:

* [barcode-java](https://github.com/barnhill/barcode-java): soporta varios estándares de códigos lineales.
* [Barbecue](https://barbecue.sourceforge.net/): una librería cuya última actualización es del 2007.
* [Barcode4J](http://barcode4j.sourceforge.net/:) una librería cuya última actualización es del 2012.
* [ZXing](https://github.com/zxing/zxing): el autor de esta librería ha dejado de desarrollar y solo incorpora los cambios que se le envíen.

También hay algunas librerías comerciales con un coste por su uso bastante significativo pero que tienen algunas características que no están en las de código abierto como el reconocimiento del código dada una imagen además de seguramente tener mejor mantenimiento. Dos librerías comerciales para generar códigos son:

* [Barcode for Java](https://www.barcodelib.com/java_barcode/main.html)
* [Aspose Java Barcode Generation & Recognition API](https://products.aspose.com/barcode/java/)
* [Free Spire.Barcode for Java](https://www.e-iceblue.com/Introduce/free-barcode-for-java.html)

Buscando alguna opción de código abierto pero mejor mantenida que los casos anteriores está la siguiente que soporta una buena cantidad de estándares entre los que están los más utilizados:

* [OkapiBarcode](https://github.com/woo-j/OkapiBarcode)

### Ejemplos de código

Los siguiente ejemplos de código son utilizando la librería OkapiBarcode que en esencia generan una imagen utilizando las clases que incluye el JDK de Java, esta imagen se puede exportar en diferentes formatos como _png_ y _jpg_.

#### Generar un código de barras con Java

La librería ofrece una clase con el mismo nombre por cada estándar que implementa. En los códigos lineales se ofrece la posibilidad de mostrar el código del texto que codifica la imagen, lo que es útil en caso de que el lector hardware no funcione y haya que introducir manualmente el código en el ordenador. Se puede especificar el tamaño vertical de las barras, la fuente y posición del texto y es recomendable utilizar el _RenderingHints.VALUE\_TEXT\_ANTIALIAS\_ON_ para que el texto tenga mejor aspecto aplicando _antialias_ a la fuente al generar la imagen.

* [Obtener el ancho y alto, escalar y convertir a otro formato imágenes con Java][blogbitix-662]

El estándar que se suele utilizar en los productos del supermercado es el EAN o UPN que es el que se muestra en la siguiente imagen, estos son varios estándares de códigos lineales.

{{< image
    gallery="true"
    image1="image:code-128.webp" optionsthumb1="200x150" title1="Code 128"
    image2="image:ean-13.webp" optionsthumb2="200x150" title2="EAN 13"
    image3="image:upc.webp" optionsthumb3="200x150" title3="UPC"
    caption="Code 128, EAN 13 y UPC" >}}

{{< code file="Main-1.java" language="java" options="" >}}
{{< code file="build.gradle" language="groovy" options="" >}}

#### Generar un código QR con Java

Otro formato de códigos son los que tienen dos dimensiones entre los que está el popular código QR que muchos _smartphones_ reconocen con la cámara.

En este caso basta instancia la clase que representa el código QR y proporcionarle el contenido que se desea codificar, el proceso de generación de la imagen es igual que en el caso de los códigos de barras.

{{< image
    gallery="true"
    image1="image:qr-code.webp" optionsthumb1="300x200" title1="Código QR"
    caption="Código QR" >}}

Como el programa Decoder soporta decodificación dada una imagen permite comprobar si el código QR contiene el valor codificado al generarla.

{{< image
    gallery="true"
    image1="image:decoder.webp" optionsthumb1="300x200" title1="Código QR"
    caption="Código QR" >}}

Normalmente los códigos suelen generarse en los colores blanco y negro pero también es posible cambiar el color primario y de fondo si se desean otros colores teniendo en cuenta que han de ser colores que el lector pueda reconocer al leerlo. Hay que cambiar el formato del píxel de la imagen con _BufferedImage.TYPE\_INT\_ARGB_ y especificar otros colores en la clase _Java2DRenderer_.

{{< image
    gallery="true"
    image1="image:qr-code-blogbitix.webp" optionsthumb1="300x200" title1="Código QR en color"
    caption="Código QR en color" >}}

{{< code file="Main-2.java" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaBarcode" command="./gradlew run" %}}

{{< reference >}}
* [Generating Barcodes and QR Codes in Java](https://www.baeldung.com/java-generating-barcodes-qr-codes)
{{< /reference >}}

{{% /post %}}