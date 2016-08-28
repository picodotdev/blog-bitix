---
pid: 3
title: "Firmar digitalmente documentos con el DNIe y Sinadura"
url: "/2013/12/firmar-digitalmente-documentos-con-el-dnie-y-sinadura/"
date: 2013-12-21T11:34:55+01:00
updated: 2015-05-28T20:00:00+02:00
sharing: true
comments: true
tags: ["planeta-linux", "planeta-arch-linux", "planeta-codigo", "software-libre", "hardware", "software"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="dnie.jpg" title1="DNIe" image2="sinadura.png" title2="Sinadura" >}}

Ya he comentado [como instalar un lector de tarjetas inteligentes en linux para usar el DNI electrónico][elblogdepicodev-183] y que una vez instalado podemos darle algún uso útil como por ejemplo [autenticarnos con en DNI][blogbitix-1] en las páginas que lo soporten como medio de autenticación y el navegador Firefox. También he comentado [como usar el DNI con el cliente de correo Evolution para enviar correos firmados digitalmente][blogbitix-2].

En esta entrada voy a comentar como firmar digitalmente documentos en cualquier formato electrónico con la aplicación de software libre [Sinadura](http://www.sinadura.net/es/). La firma que hagamos con el DNIe sobre estos documentos puede tener la misma validez legal que la firma manuscrita.

Primeramente deberemos descargar la última versión de [Sinadura](http://www.sinadura.net/es/) según la arquitectura de nuestro procesador, de 32 bits o de 64 bits y la plataforma linux o windows. El archivo descargado es un instalador que deberemos ejecutarlo, como es un programa Java deberemos tener instalado previamente un entorno de ejecución de Java como el [OpenJDK](http://openjdk.java.net/). Lo ejecutamos con:

{{% gist id="8067882" file="instalar-sunadura.sh" %}}

<div class="media" style="text-align: center;">
    <a href="assets/images/custom/posts/3/instalador-sinadura.png" title="Instalador Sinadura" data-gallery><img src="assets/images/custom/posts/3/instalador-sinadura-thumb.png" alt="Instalador Sinadura" title="Instalador Sinadura"></a>
</div>

Una vez instalado sinadura y también teniendo instalado el lector de tarjetas inteligentes para el DNI y funcionando deberemos conectarlo al ordenador e introducir el DNI. Iniciaremos sinadura con que se encuentra dentro del directorio de instalación de Sinadura:

{{% gist id="8067882" file="iniciar-sinadura.sh" %}}

Inicialmente veremos una pantalla como la siguiente con una lista de archivos vacíos y una serie de botones para realizar varias acciones.

<div class="media" style="text-align: center;">
    <a href="assets/images/custom/posts/3/sinadura.png" title="Sinadura" data-gallery><img src="assets/images/custom/posts/3/sinadura-thumb.png" alt="Sinadura" title="Sinadura"></a>
</div>

Con Sinadura y el DNI podemos firmar digitalmente cualquier tipo de archivo pero entre los archivos de tipo pdf y el resto hay diferencias. En el caso de los pdf la firma se incrusta en el propio documento y en resto se crea un nuevo archivo con la firma y el documento original, el archivo nuevo creado tiene la extensión .sar. Para firmar un documento deberemos añadirlo a la vista con el botón «Añadir documento» y posteriormente firmarlo con el botón «Firmar». Para realizar la firma se nos pedirá que introduzcamos el PIN secreto que nos proporcionaron al emitirnos el DNI en la oficina de expedición.

<div class="media" style="text-align: center;">
    <a href="assets/images/custom/posts/3/pin.png" title="Preferencias de Sinadura" data-gallery><img src="assets/images/custom/posts/3/pin-thumb.png" alt="Administrador de dispositivos (1)" title="Preferencias de Sinadura"></a>
</div>

En el caso de los archivos pdf Sinadura por defecto incrusta además de la firma una marca de agua en la primera página aunque se pueden cambiar algunas preferencias en «Archivo> Preferencias»:

<div class="media" style="text-align: center;">
    <a href="assets/images/custom/posts/3/preferencias-sinadura.png" title="Preferencias de Sinadura" data-gallery><img src="assets/images/custom/posts/3/preferencias-sinadura-thumb.png" alt="Preferencias de Sinadura" title="Preferencias de Sinadura"></a>
</div>

Los archivos .sar creados para archivos que no son pdf tienen el siguiente contenido, además del archivo original una serie de archivos .xml que contendrán la firma (son archivos zip con extensión .sar):

<div class="media" style="text-align: center;">
    <a href="assets/images/custom/posts/3/sinadura-documento-firmado.png" title="Documento firmado con Sinadura" data-gallery><img src="assets/images/custom/posts/3/sinadura-documento-firmado-thumb.png" alt="Documento firmado con Sinadura" title="Documento firmado con Sinadura"></a>
</div>

Una vez firmados los archivos podemos enviarlos por correo electrónico y la persona que los reciba podrá validarlos también con Sinadura. Para ello se debe añadir el archivo y pulsar el botón «Validar».

Si queremos firmar muchos archivos a la vez usar la interfaz gráfica nos va a resultar lento y un trabajo repetitivo. En caso de querer hacer firmas masivas de archivos es mejor usar la linea de comandos o un proceso sh que nos haga la tarea. Sinadura también proporciona una utilidad basada en la linea de comandos que debemos usar de la siguiente forma:

{{% gist id="8067882" file="sinadura-console-pdf.sh" %}}
{{% gist id="8067882" file="sinadura-console-xades.sh" %}}

Con el siguiente archivo de preferencias:

{{% gist id="8067882" file="preferences-console.properties" %}}

La linea de comandos producirá el mismo resultado que el uso con la interfaz gráfica. Realmente los ejemplos de firma por consola no me han funcionado, en ambos casos me da una excepción NullPointerException que no es muy aclarativa de lo que está sucediendo, probablemente sea que falta algo en el archivo de preferencias.

En definitiva, la firma digital de documentos es otro uso que le podemos dar al DNI electrónico y con Sinadura realizarlo es bastante sencillo.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Crear una par de claves GPG][elblogdepicodev-181]
* [Como firmar correos electrónicos con GPG][elblogdepicodev-182]
* [Instalar un lector de tarjetas inteligentes y usar el DNIe en Arch Linux ][elblogdepicodev-183]
* [Usar el DNI electrónico con Firefox en Linux][blogbitix-1]
* [Enviar correos electrónicos firmados con el DNIe y Evolution][blogbitix-2]
{{% /reference %}}

{{% /post %}}
