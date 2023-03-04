---
pid: 554
type: "post"
title: "Guía de instalación de GNU/Linux para la Raspberry Pi"
url: "/2021/02/guia-de-instalacion-de-gnu-linux-para-la-raspberry-pi/"
date: 2021-02-12T16:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:raspberrypi.svg"
tags: ["gnu-linux", "planeta-codigo"]
summary: "El propósito original del computador de pequeño tamaño Raspberry Pi es el educativo y aprendizaje de progamación e introducción a la  electrónica, sin embargo, debido a su bajo coste y ser una computador de propósito general es utilizado con otros múltiples propósitos. El primer paso para empezar a usar una Raspberry Pi es instalarle un sistema operativo, dos opciones son el ofrecido por la fundación de la Raspberry Pi o la versión ofrecida por Ubuntu."
---

{{% post %}}

{{< logotype image1="raspberrypi.svg" image2="ubuntu.svg" >}}

La evolución tecnológica permite desarrollar componentes electrónicos cada vez más pequeños, más baratos y más capaces que los de la generación anterior. En ejemplo de esto es la [Raspberry Pi][raspberrypi], una computadora del tamaño de una tarjeta de crédito pero con las características de una computadora de gama baja, aún así suficiente para múltiples propósitos.

Y es que aunque la Raspberry Pi aún no tiene la suficiente potencia como para reemplazar una computadora de escritorio tiene una potencia significativa comparable en procesador y cantidad de memoria a las computadoras de hace una década o lustro. Es seguro que en futuras versiones de la Raspberry Pi será aún más potente y entonces sí quizá para algunos usuarios pase a ser una opción viable para convertirse en el ordenador de escritorio de uso habitual.

La Raspberry Pi 4 es una computadora con las siguientes [especificaciones en su cuarta versión](https://www.raspberrypi.org/products/raspberry-pi-4-model-b/specifications/). Además, hay diferentes modelos como la Raspberry Pi 400 que está integrada con un teclado.

El propósito primario de la Raspberry Pi no es proporcionar a los usuarios avanzados computadoras de bajo coste, es facilitar la educación en computadoras eliminando la barrera del precio. La primera barrera era el precio, es difícil sino imposible encontrar una computadora con todas las características, de propósito general por menos de lo que cuesta comprar una RPi.

La Raspberry Pi no es barata, hay que tener en cuenta que además hay que comprar el dispositivo de almacenamiento y cargador de energía aparte cuando menos si no hace falta además una carcasa. El modelo con 8 GiB tiene un precio de 85 €. Un NUC de última generación puede equipar mínimo 8 GiB de memoria en los modelos más básicos y hasta 64 GiB en los más capaces siendo sus procesadores significativamente más potentes. Los NUC parten de los 120 € a los que hay que incorporarles la memoria y almacenamiento.

El propósito original de la Raspberry Pi es el educativo y la electrónica, en cualquier caso es capaz de utilizarse para multitud de propósitos como servidor NAS, descargas torrent, centro multimedia, servidor [NextCloud][nextcloud] como alternativa a las herramientas de [Google Docs][google-docs] o [Photos][google-photos] o evitar anuncios con AdGuard, consola de juegos retro con [RetroPie][retropie] o [Lakka][lakka].

Desde su aparición en el año 2010 han surgido [numerosas alternativas de la Raspberry Pi][blogbitix-304] en formato y características similares. Algunas incluso más potentes, sin embargo, dada la popularidad de la RPi es la que mejor soporte de software ofrece y de la que hay más información en caso de buscarla.

La [revista MagPi][magpi] en PDF se puede descargar de forma gratuita y contiene numerosos artículos sobre como realizar proyectos de programación y electrónica con esta computadora. También puede adquirirse en formato impreso, se solicita pagar un pequeño importe  para mantener la publicación así que si la sueles obtener todos los números considera hacer un pago de vez en cuando.

{{< tableofcontents >}}

## Cómo instalar GNU/Linux en la Raspberry Pi

Una vez en posesión de una Raspberry Pi y elementos imprescindibles, cargador de alimentación y tarjeta SD o microSD y opcionalmente teclado y monitor, el primer paso es realizar la instalación del sistema operativo. El sistema operativo oficial es [Raspberry Pi OS][raspberrypi-software].

La instalación se hace en una tarjeta de memoria microSD o una memoria USB, para la instalación se requiere de otra computadora en la que utilizar la aplicación _Raspberry Pi Image_ que descarga la imagen del sistema operativo y formatea la unidad de almacenamiento. Dispone de una versión para los sistemas operativos [Windows][windows], [macOS][macos] y como aplicación [Flatpak][flatpak] para cualquier distribución [GNU][gnu]/[Linux][linux].

El grabar la imagen en la tarjeta de almacenamiento conlleva la pérdida de los datos que tuviese con lo es necesario previamente haber hecho una copia de seguridad de los datos que contenga.

### Raspberry Pi OS

El sistema operativo Raspberry Pi OS está basado en la distribución [Debian][debian], dispone de varias versiones una con entorno gráfico de escritorio y la versión _Lite_ sin entorno de escritorio.

Una de las ventajas de Raspberry Pi OS es que la misma imagen de la distribución sirve par cualesquiera versiones de la Raspberry Pi independientemente de las diferentes versiones que se han lanzado, incluyendo las primeras 1, 2, 3 y 4.

La aplicación Raspberry Pi Image es muy sencilla, basta seleccionar la versión de la Raspberry Pi OS, insertar la tarjeta SD, seleccionar la unidad de la tarjeta SD y proceder a la instalación que se completa en unos pocos minutos. En la tarjeta SD se crean dos particiones, la de arranque o _boot_ y la raíz o _root_.

{{< image
    gallery="true"
    image1="image:raspberrypi-imager-1.webp" optionsthumb1="300x200" title1="Raspberry Pi Imager"
    image2="image:raspberrypi-imager-2.webp" optionsthumb2="300x200" title2="Raspberry Pi Imager"
    image3="image:raspberrypi-imager-3.webp" optionsthumb3="300x200" title3="Raspberry Pi Imager"
    caption="Raspberry Pi Imager" >}}

Una vez instalado Raspberry Pi OS basta con insertar la tarjeta SD y conectar el adaptador de corriente. Para comenzar a trabajar con ella antes del inicio se puede conectar a un monitor y un teclado. También es posible acceder a la terminal de la Raspberry Pi desde otro ordenador en la misma red con SSH sin necesidad de conectar a la RPi un teclado y monitor directamente. Para ello es necesario conocer qué dirección IP le ha asignado el _router_ local mediante DHCP.

{{< image
    gallery="true"
    image1="image:raspberrypi-os-boot.webp" optionsthumb1="300x200" title1="Raspberry Pi OS root"
    image2="image:raspberrypi-os-root.webp" optionsthumb2="300x200" title2="Raspberry Pi OS boot"
    caption="Archivos de Raspberry Pi OS" >}}

Para activar SSH en la Raspberry Pi hay que crean un archivo de nombre ssh en la partición _boot_. Cuando se inicia la Raspberry Pi lo detecta, activa SSH y lo elimina. El usuario y contraseña por defecto son _pi_ y _raspberry_ respectivamente, es aconsejable eliminar este usuario o cambiar de contraseña.

{{< code file="ssh-raspberrypio-os.sh" language="bash" options="" >}}

{{< code file="create-user.sh" language="bash" options="" >}}
{{< code file="change-user-password.sh" language="bash" options="" >}}

Una vez con acceso a la Raspberry Pi se pueden instalar los paquetes y programas de software con el gestor de paquetes de las distribuciones basadas en Debian.

{{< code file="apt-update-upgrade.sh" language="bash" options="" >}}
{{< code file="apt-install-nginx.sh" language="bash" options="" >}}

### Ubuntu Server

[Ubuntu][ubuntu] también ofrece una versión de su distribución GNU/Linux para la Raspberry Pi para las versiones 2+, no soportando los primeros modelos originales de la Raspberry Pi. La versión de escritorio de Ubuntu para la Raspberry Pi requiere al menos 4 GiB y la versión RPi 4, Ubuntu Server y Ubuntu Core no incluye interfaz gráfica.

El proceso de instalación de Ubuntu tanto para la versión de escritorio como la para las versiones _Server_ y _Core_, es posible realizarlas mediante Raspberry Pi Image. Raspberry Pi Image permite seleccionar la versión de Ubuntu y esta se encarga de descargarla e instalarla en la tarjeta microSD seleccionada.

{{< image
    gallery="true"
    image1="image:raspberrypi-imager-other.webp" optionsthumb1="300x200" title1="Ubuntu para la Raspberry Pi"
    caption="Ubuntu para la Raspberry Pi" >}}

El usuario y contraseña por defecto son _ubuntu_ y _ubuntu_ respectivamente, es aconsejable eliminar este usuario o cambiar de contraseña. Como Ubuntu también es una distribución derivada de Debian el gestor de paquetes y los comandos para instalarlos son los mismos.

{{< code file="apt-update-upgrade.sh" language="bash" options="" >}}
{{< code file="apt-install-nginx.sh" language="bash" options="" >}}

### Otras distribuciones y documentación

La misma Raspberry Pi Image permite instalar otras distribuciones de uso específico o paquetes como consola de juegos retro, servidor multimedia, servidor NAS, _proxy_ de navegación para evitar publicidad y ser rastreado, nube de documentos personal, ... Otras distribuciones también ofrecen una versión de su sistema operativo para la Raspberry como [Debian RPi](https://wiki.debian.org/RaspberryPi) y [Arch Linux ARM][archlinuxarm]. Como el sistema operativo se almacena en una tarjeta de memoria o USB es fácil cambiar de una distribución a otra.

{{< image
    gallery="true"
    image1="image:raspberrypi-imager-games.webp" optionsthumb1="300x200" title1="Juegos retro para la Raspberry Pi"
    image2="image:raspberrypi-imager-media-player.webp" optionsthumb2="300x200" title2="Reproductor multimedia para la Raspberry Pi"
    caption="Otros sistemas operativos para la Raspberry Pi" >}}

Documentación y ayuda:

* [Sistemas operativos para la Raspberry Pi](https://www.raspberrypi.org/software/operating-systems/)
* [Documentación de la Raspberry Pi](https://www.raspberrypi.org/documentation/)
* [Ayuda para la Raspberry Pi](https://www.raspberrypi.org/help/)
* [Configuración de la Raspberry Pi](https://projects.raspberrypi.org/en/projects/raspberry-pi-setting-up)
* [Proyectos de ejemplo para la Raspberry Pi](https://projects.raspberrypi.org/en/projects)

En otros artículos he comentado como realizar algunos proyectos de electrónica con la Raspberry Pi:

* [Artículos sobre la Raspberry Pi y ejemplos con electrónica][blogbitix-serie-electronica]
* [Consola de juegos retro con Lakka y una Raspberry Pi][blogbitix-301]
* [Nube privada para documentos personales con Nextcloud y OnlyOffice][blogbitix-446]

{{< reference >}}
* [How to Boot Raspberry Pi 4 From a USB SSD or Flash Drive](https://www.tomshardware.com/how-to/boot-raspberry-pi-4-usb)
* [How to install Ubuntu Desktop on Raspberry Pi 4](https://ubuntu.com/tutorials/how-to-install-ubuntu-desktop-on-raspberry-pi-4#1-overview)
* [How to install Ubuntu Server on your Raspberry Pi](https://ubuntu.com/tutorials/how-to-install-ubuntu-on-your-raspberry-pi#1-overview)
{{< /reference >}}

{{% /post %}}
