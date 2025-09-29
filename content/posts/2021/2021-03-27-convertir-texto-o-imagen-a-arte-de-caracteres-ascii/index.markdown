---
pid: 563
type: "post"
title: "Convertir texto o imagen a arte de caracteres ASCII"
url: "/2021/03/convertir-texto-o-imagen-a-arte-de-caracteres-ascii/"
date: 2021-03-27T00:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:linux.svg"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "El arte ASCII es utilizar caracteres para obtener una composición que simula una imagen o dibujo. Es posible convertir texto a arte de caracteres ASCII o una imagen en formato _svg_, _jpeg_ o _png_ a texto. En GNU/Linux hay herramientas de línea de comandos que permiten crear arte ASCII tanto para texto como para imágenes. En internet también hay disponibles páginas que ofrecen el servicio de generar arte ASCII sin necesidad de instalar ningún software en la propia computadora."
---

{{% post %}}

{{< logotype image1="gnu.svg" image2="linux.svg" >}}

Con la combinación adecuada de caracteres y varias líneas de texto es posible crear composiciones artísticas de texto, formando un arte ASCII basado en múltiples caracteres. El arte ASCII permite crear dibujos o convertir una frase en un texto en forma de dibujo.

El arte ASCII es adecuado para ser mostrado en interfaces basadas en texto como la terminal del sistema operativo. En [GNU][gnu]/[Linux][linux] hay utilidades de línea de comandos que permiten crear arte ASCII, también hay páginas web que proporcionan la funcionalidad sin necesidad de instalar ningún software en el sistema.

{{< tableofcontents >}}

## Texto a arte de caracteres ASCII

La herramienta [figlet][figlet] de linea de comandos permite convertir una frase a arte ASCII, es posible configurarla con diferentes estilos de fuente que producen resultados diferentes en el arte ASCII. Utilizando caracteres de escape de la terminal es posible añadir colores, [Jansi es una librería Java que permite añadir colores a texto emitido en la terminal][blogbitix-359].

Además de instalar el paquete de _figlet_ en la distribución de GNU/Linux hay que descargar los [archivos de las fuentes](http://www.figlet.org/fontdb.cgi) para generar el arte ASCII con el estilo deseado. La fuente se indica en el comando con el parámetro _-f_.

{{< code file="figlet.sh" language="bash" options="" >}}
{{< code file="figlet.txt" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:figlet.webp" optionsthumb1="650x450" title1=""
    caption="Texto convertido a arte de caracteres ASCII" >}}

## Imagen a arte de caracteres ASCII

También es posible convertir una imagen a arte ASCII, en el caso de las imágenes el resultado ha de tener un número adecuado de líneas y columnas para producir resultados con la suficiente definición para obtener resultados fieles a la imagen original.

La herramienta [jp2a][jp2a] permite convertir una imagen a arte ASCII. Basta con indicar la imagen fuente y personalizar las opciones del resultado como la anchura de caracteres con la opción _--width_. Utilizando las tuberías del intérprete de comandos es posible combinar el resultado de un comando que procese o transforme la imagen y se la proporcione como entrada a _jp2a_, en este caso para utilizar formatos no soportado por _jp2a_ convirtiéndolo previamente a uno soportado permite obtener el arte ASCII.

La herramienta _jp2a_ combinada con el [extraer un fotograma de una película][blogbitix-135] permite generar el arte ASCII de un momento de una película.

{{< code file="jp2a.sh" language="bash" options="" >}}
{{< code file="jp2a.txt" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:jp2a-archlinux.webp" optionsthumb1="200x150" title1="Logotipo de Arch Linux convertido a arte de caracteres ASCII con jp2a"
    image2="image:jp2a-linux.webp" optionsthumb2="200x150" title2="Logotipo de Linux convertido a arte de caracteres ASCII con jp2a"
    image3="image:jp2a-gnome.webp" optionsthumb3="200x150" title3="Logotipo de GNOME convertido a arte de caracteres ASCII con jp2a" >}}
{{< image
    gallery="true"
    image1="image:jp2a-gnu.webp" optionsthumb1="200x150" title1="Logotipo de GNU convertido a arte de caracteres ASCII con jp2a"
    caption="Varios logotipos convertidos a arte de caracteres ASCII con jp2a" >}}

## Páginas web de conversión a arte de caracteres ASCII

No es necesario instalar aplicaciones para generar tanto un texto en arte ASCII como convertir una imagen a arte ASCII, hay algunas páginas que ofrecen estas funcionalidades simplemente introduciendo el texto a convertir a arte ASCII como proporcionando la imagen a convertir a arte ASCII.

El resultado es un conjunto de caracteres que forman el arte ASCII. El propio [buscador DuckDuckGo][blogbitix-286] es capaz de generar el arte ASCII de _figlet_ con la fuente _standard_, la página [Online Ascii Tools](https://onlineasciitools.com/convert-text-to-ascii-art) permite convertir un texto a arte ASCII seleccionando la fuente o incluyendo [una colección de arte ASCII](https://textfancy.com/ascii-art/) y la página [ASCII Generator](https://ascii-generator.site/) permite convertir una imagen a arte ASCII.

{{< reference >}}
* [TextKool](https://textkool.com/en/ascii-art-generator?hl=default&vl=default&font=Red%20Phoenix&text=Your%20text%20here%20)
{{< /reference >}}

{{% /post %}}
