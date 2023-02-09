---
pid: 673
type: "post"
title: "Los códigos de barras, códigos QR y lectores hardware de códigos"
url: "/2023/02/los-codigos-de-barras-codigos-qr-y-lectores-hardware-de-codigos/"
date: 2023-02-09T18:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:qr-code.webp"
tags: ["hardware", "planeta-codigo", "software"]
summary: "Los códigos de barras y códigos QR tienen muchas aplicaciones y son numerosos ámbitos en los que se usan por las ventajas que proporcionan, al ser una imagen legible por las máquinas permiten automatizar de forma rápida y sencilla una tarea tan anodina y mundana como la introducción de los códigos en la computadora. Los lectores hardware de códigos de barras y códigos QR funcionan realmente como un teclado, no necesitan de ningún controlador ni programa y son compatibles con cualquier sistema operativo. Hay varios estándares de códigos de barras y códigos QR con su propia representación gráfica impresa o gráfica, en función del estándar codifica diferente información numérica, alfanumérica y limitada o sin limitación en la cantidad de información."
---

{{% post %}}

Los códigos de barras facilitan la identificación de productos con un lector, no es necesario teclear el código sino que hay dispositivos hardware que los reconocen, se representa con una imagen que es legible por una computadora con un hardware especializado.

Usar un lector hardware es mucho más sencillo y rápido, facilita mucho la labor y ahorra mucho tiempo en los ámbitos que se utilizan. Es habitual en puntos de venta como tiendas, supermercados y comercios, ámbitos como los de distribución de productos y logística, también es habitual para permitir el acceso a eventos con entradas en papel o digitales en el móvil.

Hay diferentes estándares y aplicaciones para generar códigos de barras, es posible también generarlos desde un lenguaje de programación como Java y lectores de hardware especializado y programas de software para leerlos.

* [Generar códigos de barras y códigos QR con Java][blogbitix-674]

Dado que los códigos de barras son leídos y procesados por una computadora proporciona varias ventajas como conocer qué productos se están vendiendo, cuál es el inventario de productos en tiempo real, con las tarjetas de cliente permite conocer qué productos consumen cada cliente y cuando, conocer que productos hay que reponer, además de un históricos de datos para extraer valiosa información que permita optimizar el negocio y por supuesto un rápido paso por caja y una simplificación del la tarea del trabajador en la caja.

{{< tableofcontents >}}

### Códigos de barras y códigos QR

El código se corresponde con el identificador de un producto, con el código se realiza la operación ya sea añadirlo a una lista de la compra en un supermercado o permitir el acceso a un evento deportivo. Hay varios estándares de códigos de barras y códigos QR pero en definitiva todos se representan con una imagen, normalmente son en blanco y negro pero también se admiten algunas combinaciones de colores sobre todo el los formatos matriciales o de dos dimensiones.

Algunos estándares se representan con una secuencia de barras normalmente en blanco y negro con el número de código al pié de del código de barras pos si hay que introducirlo manualmente por estar dañado el soporte en el que está el código o por si no se dispone de un lector hardware de código de barras, se ha estropeado o no funciona en el ordenador.

Dependiendo del estándar la información que codifica es un conjunto de números limitado, otros formatos permiten almacenar texto de longitud variable. Dependiendo del ámbito que se vayan a usar e información que tienen los códigos es más adecuado utilizar un estándar u otro.

### Los diferentes estándares

Hay diferentes tipos de códigos en cuanto a sus características y aspecto impreso. Un tipo de códigos son los lineales normalmente de una dimensión dispuesta en horizontal, pueden tener una altura variable y de esta forma tienen el aspecto de códigos de barras verticales formados por un conjunto de lineas en color negro y espacios en blanco de diferente anchura, en los productos de supermercado se usa alguna variación del estándar UPC o EAN. El otro tipo de códigos son los matriciales hay más diversidad en su aspecto visual por ofrecer más opciones incluso utilizando más colores que el blanco y negro, uno de los más conocidos de este tipo son los códigos QR formados por un rectángulo de cuadraditos negros y blancos.

Otra de sus variaciones está en si son continuos en el que un valor no puede leerse de forma individual dependiendo de la información del gráfico a continuación o discretos en los que cada carácter tiene su propia representación y se pueden leer de forma aislada de los siguientes.

En la [página de la wikipedia de Barcodes](https://en.wikipedia.org/wiki/Barcode) hay una información detallada de los estándares que existen y en algunos casos como se representan gráficamente. Algunos ejemplos de la representación impresa o gráfica de códigos de barras y códigos QR son los siguientes, en la wikipedia hay más ejemplos.

Algunos de las familias formatos más populares lineales son los siguiente, habiendo dentro de cada familia algunas variaciones:

* [Code 128](https://en.wikipedia.org/wiki/Code_128)
* [EAN](https://en.wikipedia.org/wiki/International_Article_Number)
* [UPC](https://en.wikipedia.org/wiki/Universal_Product_Code)

En los formatos matriciales o de dos dimensiones están:

* [Códigos QR](https://en.wikipedia.org/wiki/QR_code)
* [Data Matrix](https://en.wikipedia.org/wiki/Data_Matrix)

{{< image
    gallery="true"
    image1="image:ean.webp" optionsthumb1="300x250" title1="Código de barras EAN"
    image2="image:upc.webp" optionsthumb2="300x250" title2="Código de barras UPC"
    caption="Código de barras lineales EAN y UPC" >}}
{{< image
    gallery="true"
    image1="image:qr-code.webp" optionsthumb1="300x250" title1="Código QR"
    image2="image:data-matrix.webp" optionsthumb2="300x250" title2="Código Maxi Code"
    caption="Código matriciales QR Code y Maxi Code" >}}

### Aplicaciones para generar códigos de barras y códigos

La aplicación [Zint Barcode Studio](https://flathub.org/apps/details/uk.org.zint.zint-qt) permite generar códigos de barras en una gran cantidad de estándares, se puede instalar en [GNU][gnu]/[Linux][linux] fácilmente al estar en formato [Flatpak][flatpak] con la aplicación Software de [GNOME][gnome]. Se selecciona el estándar para el código de barras, algunas propiedades de la imagen e introducir el código a codificar en la imagen, finalmente la imagen se puede exportar a diferentes formatos de imagen.

* [Flatpak, distribución e instalación de programas de escritorio en las distribuciones GNU/Linux][blogbitix-362]

{{< image
    gallery="true"
    image1="image:zint-gnome-software.webp" optionsthumb1="300x200" title1="Zint Barcode Studio en Software de GNOME"
    image2="image:zint-barcode-studio.webp" optionsthumb2="300x200" title2="Zint Barcode Studio"
    caption="Zint Barcode Studio" >}}

Otra aplicación sencilla para códigos QR es [Decodificador](https://flathub.org/apps/details/com.belmoussaoui.Decoder), también disponible en GNU/Linux en formato Flatpak muy fácil de instalar. Zink también permite generar códigos QR pero no leerlos, Decodificador permite generar sólo códigos QR pero también leerlos proporcionada la imagen con una captura de imagen desde el ordenador o desde un archivo, sin necesidad de utilizar un móvil.

{{< image
    gallery="true"
    image1="image:decoder-gnome-software.webp" optionsthumb1="300x200" title1="Decoder en Software de GNOME"
    image2="image:decoder.webp" optionsthumb2="300x200" title2="Decoder"
    caption="Decoder" >}}

### Lectores de códigos de barras y códigos QR

Hay dispositivos hardware especializados para leer códigos, algunos soportan varios estándares. Utilizan un láser que escanea la imagen e identifica el código. Muchos no necesitan de ningún software especializado ni controlador para el sistema operativo ya que el dispositivo hardware es como un teclado y al escanear el código es como si se escribiera manualmente en el teclado.

Funcionan con conexión por cable USB y algunos son inalámbricos por Bluetooth, son compatibles con los sistemas operativos [Windows][windows], GNU/Linux y [macOS][macos]. Suelen soportar un modo en el que hay que apretar un gatillo para hacer el reconocimiento o en modo continuo de funcionamiento en el que basta pasar el código por delante sin necesidad de pulsar el gatillo lo que permite liberar las manos para coger los productos, esto es un modo eficiente de lectura de códigos para volúmenes de códigos grandes.

No son muy caros y hay [multitud de modelos de códigos de barras en Amazon](https://amzn.to/3YCd1S3), hay que fijarse en los estándares de códigos que soporta y si funciona mediante cable o también es inalámbrico y si es por Bluetooth o radiofrecuencia con un adaptador, algunos solo soportan códigos lineales, otros más capaces soportan ambos lineales y matriciales.

{{< amazon
    linkids="48b106fcede17cd5010e264dde0c0821"
    asins="B07CBS52KJ" >}}

Hay aplicaciones para _smartphones_ que combinado con la cámara y reconocimiento de imagen permite actuar como un lector de códigos. En la tienda de aplicaciones [Google Play][google-play-apps] para [Android][android] hay varias, algunas gratuitas simplemente hay que elegir una de confianza por los comentarios de los usuarios.

{{< reference >}}
* [Código de barras](https://es.wikipedia.org/wiki/C%C3%B3digo_de_barras)
{{< /reference >}}

{{% /post %}}