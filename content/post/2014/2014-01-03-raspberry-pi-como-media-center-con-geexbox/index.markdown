 ---
pid: 5
title: "Raspberry Pi como Media Center con GeeXboX"
url: "/2014/01/raspberry-pi-como-media-center-con-geexbox/"
date: 2014-01-03T20:00:00+01:00
updated: 2016-09-23T19:00:00+01:00
rss: true
sharing: true
comments: true
tags: ["planeta-linux", "hardware", "software", "software-libre"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="geexbox.png" title1="GeeXboX" image2="raspberrypi.jpg" title2="Raspberry Pi" >}}

La Raspberry Pi es un computador del tamaño de una tarjeta de crédito que se puede usar para diferentes propósitos. Hasta hace no mucho a mi me servía para descargar películas mediante torrent, sin embargo, puede usarse perfectamente para otros propósitos muy interesantes como usarla como Media Center en una televisión que no es una Smart TV o como Media Center con más opciones.

Dada la popularidad que tiene la Raspberry Pi disponemos de varias distribuciones con soporte para usar la Raspberry Pi como centro multimedia de nuestro salón. Las principales son:

* [GeeXboX][geexbox]
* [OSMC][osmc]
* [OpenELEC][openelec]

Las tres opciones tienen la similitud que son muy fácilmente instalables (también para alguien sin muchos conocimientos técnicos) y que se basan en el programa [Kodi][kodi] para ofrecer la funcionalidad de Media Center. La mayor diferencia entre las tres opciones están en los dispositivos soportados, Raspberry Pi al ser muy popular es soportada perfectamente en las tres opciones, RaspMBC está destinada especificamente para la Pi y GeeXboX y OpenELEC soportan algunos otros dispositivos como sistemas i386, x64_86, Cubox 1 / 2 / Pro, Utilite, Cubiboard o incluso Apple TV. Otras diferencias es que RaspBMC está basado en la distribución [Debian][debian] mientras que OpenELEC y GeeXboX no se basan en ninguna y están desarrolladas específicamente para actuar como Media Center. GeeXboX tiene algunas características adicionales como un servidor HTTP, un servidor FTP, SSH, un cliente torrent con una interfaz accesible con el navegador, compatible con NFS, Samba, UPnP y DLNA.

XBMC permite reproducir música, vídeo, películas, ofrece soporte para subtítulos, series, fotos incluso ver y grabar en directo la televisión con [MythTV][mythtv] además puede ser controlado conectándonos vía HTTP o [con nuestro teléfono inteligente]( http://www.geexbox.org/geexbox-daily-usage-iphone-and-android-remote-control/) ya sea [Android][android] o iPhone aunque la Raspberry Pi al no disponer de WiFi de por sí deberemos tenerla conectada a la red de nuestra casa con un cable ethernet.

En esta entrada explicaré como instalar GeeXboX en la Raspberry Pi y de esta manera dotar a nuestra televisión de características que quizá no tenga. El proceso no es nada complicado y en muy poco tiempo podremos empezar a usar la Raspberry Pi como centro multimedia si nuestra televisión dispone de una entrada para cable HDMI. El material que necesitaremos para esta guía es:

* Una Rasbpberry Pi y su adaptador de energía.
* Una tarjeta SD de unos 8 GiB o más.
* Un cable HDMI (por el se transmitirá el sonido y la imagen).
* Una televisión con entrada HDMI.

Lo primero que haremos es [descargar la última versión de GeeXboX](http://www.geexbox.org/download/) para la Raspberry Pi y el [script para nuestro dispositivo](http://www.geexbox.org/geexbox-for-embedded-devices-creating-a-bootable-sd-card/) que hará todo el proceso de instalación, en este caso para la Pi. Antes de grabar la imagen en la tarjeta SD deberemos conocer el nombre del dispositivo de la tarjeta SD, para ello ejecutamos los comandos lsblk y blkid en modo superusuario antes y después de introducir la tarjeta SD. Anotamos el nombre y lo usamos para ejecutar el siguiente comando sustituyendo el dispositivo anotado por el de este comando si es distinto que copiará la imagen del sistema a la tarjeta SD.

{{< code file="instalacion-geexbox.sh" language="Bash" options="" >}}

Este comando tardará un poco de tiempo dada la lentitud de las tarjetas SD. Una vez termine quizá debamos expandir la partición del sistema para aprovechar todo el espacio de la tarjeta SD. Para expandir la partición del sistema podemos usar [GParted](http://gparted.org/). Y esto es lo mínimo imprescindible para instalar GeeXboX en una tarjeta SD para la Pi. GeeXboX tiene el siguiente aspecto.

<div class="media" style="text-align: center;">
	{{< figure year="2014" pid="5"
    	image1="geexbox-inicio.jpg" thumb1="geexbox-inicio-thumb.jpg"
    	image2="geexbox-ajustes.jpg" thumb2="geexbox-ajustes-thumb.jpg" >}}
	{{< figure year="2014" pid="5"
    	image1="geexbox-ajustes-video.jpg" thumb1="geexbox-ajustes-video-thumb.jpg"
    	image2="geexbox-apariencia-ajustes.jpg" thumb2="geexbox-apariencia-ajustes-thumb.jpg" >}}
	{{< figure year="2014" pid="5"
    	image1="geexbox-servicios.jpg" thumb1="geexbox-servicios-thumb.jpg"
    	image2="geexbox-reproduccion.jpg" thumb2="geexbox-reproduccion-thumb.jpg" >}}
</div>

Como apunte a tener en cuenta es que para reproducir películas en formato imagen de DVD (iso) *intuyo* que se necesita adquirir la licencia MPEG-2 en la [tienda de la propia página de Raspberry Pi](http://www.raspberrypi.com/license-keys/). Para otros formatos como DivX (avi) o Matroska (mkv) no tendremos ningún problema en reproducir las películas y van totalmente fluídas. Otras opciones son bastante más potentes pero Pi ya tiene capacidad suficiente para reproducir películas a 1080p.

A pesar de lo interesante de la Raspberry Pi esta tiene algunas incomodidades. Una incomodidad es que la tarjeta SD es muy lenta comparada con un disco duro conectado por el puerto USB. La tasa de transferrencia de la tarjeta SD es suficiente para reproducir películas sin ningún problema pero al navegar entre los menús de  XBMC notaremos ciertos atascos mientas se accede a la tarjeta SD. Una tarjeta SD de 32 GB tiene espacio suficiente para almacenar varias películas y los archivos de GeeXboX.

Usando la [Raspberry Pi con un disco duro externo o memoria USB][blogbitix-6] hará que GeeXboX se inicie mucho más rápido, evitaremos los atascos en la navegación de los menús y el copiar las películas al disco USB nos llevará menos tiempo que copiarlas a la tarjeta SD. Pero tampoco es una solución exenta de sus pequeños inconvenientes ya que los puertos USB de la Raspberry Pi son incapaces de proporcionar suficiente energía a un disco duro externo de 2.5" para hacerlo funcionar por lo que necesitaremos un concentrador de puertos USB alimentado, son unos cuantos cables más que necesitaremos si pretendemos tener las películas almacenadas de esta forma. Si queremos usar la Raspberry Pi usando un disco duro externo USB podemos seguir las instrucciones de otra entrada que escribiré sobre como Usar la Raspberry Pi con un disco duro externo USB.

Otro inconveniente de la Raspberry Pi es que no dispone de WiFi (si la tercera versión de esta placa) y algunas funcionalidades de GeeXboX se proporcionan cuando la Pi está conectada a internet con lo que perderemos esas funcionalidades o deberemos tener la suerte de tener el router cerca para conectar el cable ethernet, también útil si pretendemos controlar XMBC con nuestro teléfono en vez de con un ratón. Aunque esas opciones proporcionadas al estar conectados a internet tampoco son indispensables.

Los elementos básicos para usar este miniordenador Pi son la [placa de la Raspberry Pi](https://amzn.to/2cN0d6L), una [cargador de 3A](https://amzn.to/2dfFJT7) junto con una [tarjeta SD](https://amzn.to/2cN0SFi).

<div class="media-amazon" style="text-align: center;">
		<iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01CD5VC92&linkId=fecbf2f5ac6495bca6b3e686bc0fa2e0&internal=1"></iframe>
		<iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01566WOAG&linkId=1a779c49b1d7df6206e1c1428af645e7&internal=1"></iframe>
		<iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B00J2BU7WO&linkId=f3093eebc185e207e98f6b5c53cd2c3a&internal=1"></iframe>
</div>

¡A disfrutar!

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Guía instalación Raspberry Pi con Arch Linux][elblogdepicodev-108]
* [Iniciar la Raspberry Pi desde un disco o memoria USB][blogbitix-6]
{{% /reference %}}

{{% /post %}}
