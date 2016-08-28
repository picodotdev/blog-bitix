---
pid: 6
title: "Iniciar la Raspberry Pi desde un disco o memoria USB"
url: "/2014/01/iniciar-la-raspberry-pi-desde-un-disco-o-memoria-usb/"
date: 2014-01-10T12:41:33+01:00
updated: 2015-05-25T23:00:00+02:00
sharing: true
comments: true
tags: ["planeta-linux", "planeta-arch-linux", "hardware", "software", "software-libre"]
summary: "La Raspberry Pi utiliza para arrancar una tarjeta SD en todo caso, sin embargo, dada la lentitud de lectura y escritura de esta es recomendable instalar el sistema en una memoria USB o disco duro externo. No es muy complicado y el aumento de rendimiento es notable."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="raspberrypi.jpg" title="Raspberry Pi" >}}

Por defecto la Raspberry Pi se instala en una tarjeta SD y se inicia desde ella, sin embargo, la tarjeta SD es muy lenta tanto en la lectura como en la escritura de datos y cuando tiene que hacerlo de forma intensiva el sistema queda sin responder y aparentemente atascado. Para aumentar muy notablemente el rendimiento de la Raspberry Pi podemos instalar la partición del sistema en una memoria USB o disco duro externo.

En la guía de [Instalación de la Raspberry Pi](http://elblogdepicodev.blogspot.com.es/2012/06/guia-instalacion-raspberry-pi-con-arch.html) expliqué como instalar la distribución [Arch Linux ARM](http://archlinuxarm.org/) en una tarjeta SD, esa guía será el punto de partida para esta entrada. La Raspberry Pi inicia el sistema desde la tarjeta SD por lo que aunque instalemos el sistema en un dispositivo USB seguiremos necesitando la tarjeta SD para que contenga al menos la partición de arranque (boot).

Teniendo instalado en la tarjeta SD tanto la partición de arranque como la partición del sistema donde está realmente la distribución que hayamos instalado necesitaremos mover la partición del sistema a una dispositivo USB. Para ello podemos utilizar el comando dd, deberemos conocer el dispositivo asignado a la tarjeta SD cuando la introduzcamos en el ordenador, para ello podemos usar el comando lsblk:

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/6/lsbk.png" title="Dispositivos del sistema" data-gallery><img src="assets/images/custom/posts/6/lsbk-thumb.png"></a>
</div>

{{% gist id="8350659" file="leer-particion.sh" %}}

El comando dd creará un archivo en nuestro ordenador con la imagen de la partición del sistema de la Raspberry Pi. A continuación deberemos escribir esa imagen de la partición en el dispositivo USB también usando el comando dd. Hay que tener en cuenta que al escribir la imagen perderemos todos los datos que tengamos en el dispositivo destino. En el ejemplo, el dispositivo /dev/sdb1 se corresponde con la partición del dispositivo USB en el que copiaremos la partición del sistema de la Raspberry Pi.

{{% gist id="8350659" file="escribir-particion.sh" %}}

Una vez escrita la imagen muy probablemente deberemos redimensionar la partición para aprovechar todo el espacio de almacenamiento del dispositivo. Con [GParted](http://gparted.org/) podemos hacerlo de forma muy sencilla. Seleccionamos /dev/sdb1, desmontamos la partición y redimensionamos el espacio para la partición, finalmente pulsamos en la opción «Editar> Aplicar operaciones».

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/6/gparted.png" title="Redimiensionado de patición con GParted" data-gallery><img src="assets/images/custom/posts/6/gparted-thumb.png"></a>
</div>

El paso final que deberemos hacer es cambiar en la partición de arranque que sigue estando en la tarjeta SD un archivo para indicar que la partición del sistema ahora está en un dispositivo USB. Probablemente la partición del sistema que deberemos indicar sea /dev/sda1, con este valor modificamos el archivo cmdline.txt y lo asignamos al parámetro root, deberemos tener algo como como lo siguiente:

{{% gist id="8350659" file="cmdline.txt" %}}

A partir de este punto con la tarjeta SD insertada en la Raspberry Pi y el dispositivo USB conectado a uno de los puertos se iniciará la Pi desde la partición del sistema del dispositivo USB en vez de la tarjeta SD. Enseguida se nota un aumento de rendimiento considerable en el tiempo de inicio y las perdidas de respuesta porque se está leyendo o escribiendo serán mucho menores o nulos con la mayor tasa de transferencia de los dispositivos USB.

Aunque la tarjeta SD es suficiente para usarla como [Media Center en la Raspberry Pi][blogbitix-5] al navegar por los menús notaremos que se atasca y el tiempo de arranque es notable. Con la partición del sistema instalada en una memoria o disco duro externo siguiendo el método descrito en esta entrada notaremos un menor tiempo de arranque de [GeeXboX](http://www.geexbox.org/) y evitaremos los atascos en los menus de [XBMC](http://xbmc.org/).

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Guía instalación Raspberry Pi con Arch Linux ARM (Parte I, instalación base)](http://elblogdepicodev.blogspot.com.es/2012/06/guia-instalacion-raspberry-pi-con-arch.html)
* [Guía instalación Raspberry Pi con Arch Linux ARM (Parte II, programas) ](http://elblogdepicodev.blogspot.com.es/2012/06/guia-instalacion-raspberry-pi-con-arch_22.html)
* [Raspberry Pi como Media Center con GeeXboX][blogbitix-5]
* [RaspBerry en USB](http://www.diverteka.com/?p=580)
* [HOWTO: Move the filesystem to a USB stick](http://www.raspberrypi.org/phpBB3/viewtopic.php?f=29&t=44177)
* [Using usb hard drive as default boot and storage?](http://www.raspberrypi.org/phpBB3/viewtopic.php?f=91&t=9117)
{{% /reference %}}

{{% /post %}}
