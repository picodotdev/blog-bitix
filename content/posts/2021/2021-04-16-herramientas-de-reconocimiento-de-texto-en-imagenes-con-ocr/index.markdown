---
pid: 567
type: "post"
title: "Herramientas de reconocimiento de texto en imágenes con OCR"
url: "/2021/04/herramientas-de-reconocimiento-de-texto-en-imagenes-con-ocr/"
date: 2021-04-16T16:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:image-2.webp"
tags: ["java", "gnu-linux", "planeta-codigo"]
summary: "Un programa de reconocimiento de caracteres o OCR permite obtener el texto de una imagen o fotografía tomada con la cámara de un teléfono inteligente, captura de pantalla, _scanner_ u otro medio. Al programa OCR se le proporciona la imagen y este genera como resultado el texto reconocido, si la imagen es de buena calidad el reconocimiento de caracteres es bastante preciso. Hay programas OCR de línea de comandos, con interfaz gráfica, en línea sin necesidad de instalar software y también es posible realizar OCR desde en un programa con un lenguaje de programación como Java."
---

{{% post %}}

{{< logotype image1="linux.svg" image2="java.svg" >}}

El problema de reconocimiento de caracteres es una tarea simple para un humano pero no es una tarea sencilla para una computadora que no posee la inteligencia y experiencia de un humano sino simplemente la que los programadores le han dotado dentro de sus posibilidades. La ventaja de usar una computadora, al igual que en muchas otras tareas posibles con una computadora, es que con el software adecuado es capaz de hacer el reconocimiento de caracteres de manera mucho más rápida y bastante efectiva aunque requiera cierto trabajo de revisión y edición posterior del texto obtenido. La [conversión de texto a voz natural][blogbitix-555] es otra tarea simple para un humano pero difícil para una computadora si el resultado deseado ha de ser natural o difícilmente indistinguible de la entonación de un humana.

El texto de una imagen no es extraíble de forma directa ya que por el formato de las imágenes el texto es visiblemente únicamente por la disposición de los pixeles. Para extraer el texto es necesario aplicar un algoritmo que utilizando varias heurísticas es capaz de reconocer el texto de la imagen, el software de reconocimiento de caracteres se le denomina _Optical Character Recognition_ o OCR.

El reconocimiento de caracteres en una imagen es útil y necesario cuando el texto está insertado en una imagen o fotografía, por ejemplo cuando se toma una fotografía de un coche con su número de matrícula o cuando se toma una fotografía de una página de un libro y se quiere extraer el texto de la imagen, los teléfonos inteligentes con cámara son capaces de sustituir para usos básicos a dispositivos hardware como escáneres ópticos.

{{< tableofcontents >}}

## Programas de reconocimiento de caracteres con OCR

Para extraer el texto de una imagen es necesario un programa OCR, un programa OCR es un software especializado en reconocer patrones de caracteres en una imagen aún cuando los caracteres y texto de la imagen tiene cierta distorsión, baja calidad o defectos. Por supuesto, a mejor calidad de la imagen origen el programa OCR es capaz de ser más preciso en el reconocimiento del texto. Aunque los programas OCR no son siempre perfectos son bastante fiables con una imagen de buena calidad y son capaces de extraer el texto con un éxito cercano al cien por cien.

Los usos de OCR son múltiples, como digitalización de libros, páginas de un libro, periódico, documento, reconocimiento de matrículas de vehículos o direcciones postales entre otros muchos usos.

Hay programas OCR que se invocan desde la línea de comandos, programas con interfaz gráfica y herramientas en línea sin necesidad de programas adicionales.

### Reconocimiento de caracteres desde línea de comandos

La ventaja de una herramienta de línea de comandos es que permite automatizar la tarea de extracción de texto de un conjunto de imágenes, o también permite hacer la tarea más rápido sin necesidad de usar de forma interactiva con una aplicación de interfaz gráfica.

En el sistema operativo [GNU][gnu]/[Linux][linux] una herramienta de OCR es [tesseract][tesseract]. Basta con instalar el paquete de distribución para usarlo, en este caso también es posible instalar un diccionario con las palabras del idioma para un mejor reconocimiento del texto.

{{< code file="pacman-install.sh" language="bash" options="" >}}

Su uso desde la línea de comandos es el siguiente, se ha de proporcionar la imagen origen y el identificador del idioma en su código de tres letras. El comando no perite especificar un rectángulo de la imagen donde se desea reconocer el texto con lo que es necesario recorgar la imagen al área deseada con una herramienta de manipulación de imágenes como [GIMP][gimp]. El resultado se genera en un archivo de nombre _output.txt_.

{{< code file="tesseract.sh" language="bash" options="" >}}

Para el ejemplo he usado la siguiente imagen tomada con la cámara de fotos del móvil a partir de una página de un libro electrónico. El mismo caso es posible aplicarlo a la fotografía tomada de un periódico o de un documento puesto en una pared, lo importante es que que la imagen tenga la mejor calidad posible en cuanto a definición de caracteres, si es una fotografía que esté bien enfocada y sin brillos para que todo el texto sea reconocible. El reconocimiento del texto se realiza con efectividad mientras los caracteres tengan el formato tipográfico impreso en vez de escritura realizada a mano. Aún teniendo la imagen una leve rotación el texto se sigue reconociendo.

{{< image
    gallery="true"
    image1="image:image-1.webp" optionsthumb1="300x250" title1="Imagen original con texto"
    image2="image:image-2.webp" optionsthumb2="300x250" title2="Imagen rotada con texto horizontal"
    caption="Imagen original y rotada con texto horizontal" >}}
{{< image
    gallery="true"
    image1="image:image-3.webp" optionsthumb1="650x450" title1="Imagen recortada al área deseada"
    caption="Imagen recortada al área deseada" >}}

El texto es reconocido con bastante efectividad, el formato no es completamente fiel al original y algunos signos de puntuación pequeños no son reconocidos, sin embargo si la tarea es extrael el texto de un soporte la herramienta es bastante efectiva aunque requiera un revisión y edición posterior.

{{< code file="output.txt" language="plain" options="" >}}

### Reconocimiento de texto con un programa con interfaz gráfica

Para usos esporádicos, realizar de forma más intuitiva o con alguna funcionalidad adicional también hay disponibles programas con interfaz gráfica.

Una herramienta con interfaz gráfica en GNU/Linux es [gImageReader](https://github.com/manisandro/gImageReader). Es un programa que utiliza el reconocimiento de caracteres de _tesseract_ que como funcionalidad adicional permite seleccionar el área o áreas de la imagen de las que se desea reconocer el texto.

El reconocimiento de texto se realiza de forma rápida y se proporciona en forma de texto que se puede copiar y pegar en otro programa o documento. Es posible seleccionar las diferentes áreas de la imagen en las que realizar el reconocimiento de texto. También es posible realizar cierta edición en la imagen para rotarla y que las líneas de texto estén lo más horizontales posible, también es posible instalar un diccionario del idioma del texto para en las palabras en las que haya errores de reconocimiento utilizando un diccionario sean corregidos.

{{< image
    gallery="true"
    image1="image:gimagereader-1.webp" optionsthumb1="650x450" title1="Programa con interfaz gráfica para OCR en GNU/Linux" >}}
{{< image
    gallery="true"
    image1="image:gimagereader-2.webp" optionsthumb1="650x450" title1="Programa con interfaz gráfica para OCR en GNU/Linux"
    caption="Programa con interfaz gráfica para OCR en GNU/Linux" >}}

### Herramientas en línea de reconocimiento de texto sin instalar software

También hay disponibles herramientas en internet que proporcionan la funcionalidad de reconocimiento de texto proporcionando la imagen de la que se quiere reconocer el texto. Por privacidad y seguridad, la imagen proporcionada a estas herramientas en línea no ha de contener información personal o con información privada importante es más conveniente instalar la herramienta de línea de comandos o con interfaz gráfica anteriores.

Dos herramientas en línea de OCR que aceptan diferentes formatos de imagen son:

* [Free Online OCR ](https://www.newocr.com/)
* [Online OCR](https://onlineocr.org/).

## OCR con Java

Para automatizar el reconocimiento de caracteres en un programa propio hay una librería que permite invocar la funcionalidad de _tesseract_ desde un programa implementado con el lenguaje de programación Java.

La librería requiere instalar el paquete de _tesseract_ en el sistema o en [Windows][windows] extraer y cargar su librería _dll_ incluída en el propio archivo _jar_ de la librería. También para mayor efectividad requiere descargar los [modelos entrenados para mayor efectividad](https://github.com/tesseract-ocr/tessdata_best) o los [modelos para mayor rapided](https://github.com/tesseract-ocr/tessdata_fast) según el idioma para los caracteres del texto.

Por defecto, la librería procesa la imagen entera, sin embargo al igual que en la aplicación con interfaz gráfica es posible aplicar el reconocimiento de texto a un área especifica de la imagen usando un objeto [java.awt.Rectangle](javadoc11:java.desktop/java/awt/Rectangle.html) al invocar el método sobrecargado _doOCR_.

{{< code file="Main.java" language="java" options="" >}}

En el archivo de construcción del programa se ha de añadir la dependencia sobre la librería Java.

{{< code file="build.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaTesseract" command="./gradlew run" %}}

{{< reference >}}
* [How to Do OCR from the Linux Command Line Using Tesseract](https://www.howtogeek.com/682389/how-to-do-ocr-from-the-linux-command-line-using-tesseract/)
* [OCRFeeder](https://wiki.gnome.org/action/show/Apps/OCRFeeder?action=show&redirect=OCRFeeder)
* [Ocrad - The GNU OCR](https://www.gnu.org/software/ocrad/)
* [GOCR: open-source character recognition](https://www.archlinux.org/packages/community/x86_64/gocr/)
* [OCR in Java with Tess4J](https://www.javacodegeeks.com/2020/08/ocr-in-java-with-tess4j.html)
* [Optical Character Recognition with Tesseract](https://www.baeldung.com/java-ocr-tesseract)
{{< /reference >}}

{{% /post %}}
