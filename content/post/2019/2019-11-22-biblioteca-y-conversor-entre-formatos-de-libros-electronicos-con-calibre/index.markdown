---
pid: 443
title: "Gestionar biblioteca y convertir entre formatos de libros electrónicos con Calibre"
aliases: ["/2019/11/biblioteca-y-conversor-entre-formatos-de-libros-electronicos-con-calibre/"]
url: "/2019/11/gestionar-biblioteca-y-convertir-entre-formatos-de-libros-electronicos-con-calibre/"
date: 2019-11-22T16:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["gnu-linux", "software"]
summary: "Dado que los libros electrónicos ocupan muy poco se puede llegar a tener una biblioteca grande de libros que necesitan de una herramienta para ser catalogados y para realizar conversión entre formatos si es necesario para uno de entre los que soporte el dispositivo de libros electrónicos. Calibre es una aplicación que ofrece estas dos principales funciones."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="" title1="" width1="200" image2="" title2="" width2="200" >}}

Hace ya unos años que tengo un lector de libros electrónicos y estoy muy contento con él, el popular [Amazon Kindle][amazon-kindle] aún echando de menos la luz integrada y el modelo _paperwhite_ que tiene la pantalla más blanca de los modelos más recientes que el mío. Una de las ventajas de los libros electrónicos es que una vez leídos no ocupan espacio físico con lo que no hay que tener varias baldas de libros ocupando espacio, al ser electrónicos no son más que un archivo de ordenador que ocupan muy poco no llegando muchos de ellos al megabyte de espacio. Teniendo una gran cantidad de libros según se obtienen o se empiezan a leer un libro se puede añadir a la biblioteca para tenerlo catalogado. Para gestionar la biblioteca de libros una de las mejores opciones sino la mejor es [Calibre][calibre] no solo por su completa funcionalidad sino porque es software libre.

Calibre permite organizar los libros por autor y muestra las portadas de los mismos, además permite convertir entre formatos de libros electrónicos. Dos de los formatos mayormente soportados por los lectores de libros electrónicos son el _EPUB_ y _MOBI_. Los Amazon Kindle soportan _MOBI_, _AZW_, _AZW3_ y muchas de otras marcas soportan _EPUB_, dependiendo del dispositivo en que vayan a ser leídos los libros y el formato en el que se tenga el libro puede ser necesaria una conversión.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="calibre.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Bibliteca y conversor de libros electrónicos Calibre"
        caption="Bibliteca y conversor de libros electrónicos Calibre" >}}
</div>

La conversión entre formatos no tarda muchos y en ningún momento me he encontrado que el resultado sea malo si el original está correctamente formateado. Como los libros electrónicos son archivos pequeños la conversión se realiza en unos pocos decenas de segundos. Además, Calibre permite realizar la conversión de forma masiva sobre varios libros guardando los libros en diferentes formatos en la biblioteca.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="calibre-convertir.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Convertir un libro electrónico a otro formato con Calibre"
        caption="Convertir un libro electrónico a otro formato" >}}
</div>

La biblioteca se guarda en el sistema de archivos del ordenador en una estructura de directorios organizados por autor, títulos del libro y en los formatos en los que se haya realizado la conversión.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="calibre-biblioteca-autores.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Organización por autores de los archivos en la biblioteca"
        image2="calibre-biblioteca-libros.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Organización por autor de los archivos en la biblioteca"
        caption="Organización de los archivos en la biblioteca" >}}
</div>

Quizá algunos de los libros no tenga los metadatos correctos como el título, nombre del autor o el número del libro si forma parte de una serie de libros relacionados, mediante otro diálogo se puede editar y catalogar correctamente el libro.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="calibre-metadatos.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Convertir un libro electrónico a otro formato con Calibre"
        caption="Metadatos de un libro electrónico" >}}
</div>

Calibre tiene más funciones pero la catalogar los libros en una biblioteca digital y la realizar la conversión entre formatos para diferentes dispositivos son las dos principales. Está [disponible para los principales sistemas operativos](https://calibre-ebook.com/download) como [GNU][gnu]/[Linux][linux], [Windows][windows] y [macOS][macos].

{{% /post %}}
