---
pid: 304
title: "7+ computadoras baratas del tamaño de una tarjeta de crédito basadas en GNU/Linux y ARM"
url: "/2018/03/7-plus-computadoras-baratas-del-tamano-de-una-tarjeta-de-credito-basadas-en-gnu-linux-y-arm/"
aliases: ["/2018/03/7-computadoras-baratas-del-tamano-de-una-tarjeta-de-credito-basadas-en-gnu-linux-y-arm/"]
date: 2018-03-10T10:00:00+01:00
date: 2018-03-14T20:21:00+01:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["gnu-linux", "hardware", "planeta-codigo"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="linux.svg" title1="Linux" width1="200" image2="gnu.svg" title2="GNU" width2="200" image3="arm.svg" title3="ARM" width3="350"  >}}

Una de las tendencias de la tecnología desde sus inicios es que en todo momento está en constante evolución y mejora. Cada vez es más potente, más pequeña y más barata. Hay multitud de ejemplos en cualquier dispositivo electrónico que se use para ver su evolución desde [discos duros de 5 megas que ocupaban un armario y pesaban varias decenas de kilos](https://en.wikipedia.org/wiki/History_of_IBM_magnetic_disk_drives#IBM's_first_HDD_versus_its_last_HDDs) a ahora las tarjetas microSD del tamaño de una uña con una capacidad de 256 GiB, de procesadores que en los años 70 funcionaban a una frecuencia de unos pocos megahercios a ahora varios gigahercios, de memorias RAM de unos pocos kilobytes a ahora varios gigabytes, de _chips_ con [unos miles de transistores](https://en.wikipedia.org/wiki/Transistor_count), ya no digamos válvulas de vacío, a varios miles de millones de transistores que emplean ahora las GPU de las tarjetas gráficas.

En poco más de dos décadas ha habido un salto de entre dos y tres órdenes de magnitud. Mi primer ordenador en 1996 fué un Intel Pentium a 120 Mhz con 8 MiB de memoria RAM que luego amplié a 32 MiB y un disco duro de 1 GiB que utilicé en mis años de universidad. En el 2002 cambié a un AMD 1800+ con 512 MiB, 60 GiB de disco duro y una gráfica Nvidia GeForce 2 MX creo que con 32 MiB de memoria gráfica con un monitor de 17" de tubo que pesaba más de 10 kilos seguro. En el 2008 dejé el ordenador de escritorio y pase a un portátil Dell, del que no me quiero acordar mucho por lo malo que resultó, con un Intel Core 2 Duo 8100 fabricado un una litografía de 45 nanómetros, 4 GiB de RAM, un disco duro de 320 GiB y una gráfica Nvidia 8600 con 256 MiB de memoria gráfica. Finalmente ya casi en el 2013 a mi siguiente equipo como portátil con un Intel i3-3210 de 2 núcleos y 4 hilos fabricado a 22 nanómetros, 8 GiB de memoria e inicialmente un disco duro de 500 GiB que posteriormente cambié por un SSD de 250 GiB. Y actualmente si cambiase de equipo optaría seguramente por un AMD basado en la arquitectura Zen que han aumentado de forma significativa los núcleos e hilos de los procesadores, iría a por 32 GiB para virtualizar a gusto o 64 GiB si fuese posible y la memoria DDR4 estuviese a precios más asequibles, un monitor 2K o 4K y un disco SSD basado en NVMe que aumenta más la tasa de transferencia incluso sobre la interfaz SATA. Eso en los computadores tradicionales pero ya algunos modelos de móviles de gama alta poseen más memoria que varios portátiles de la gama básica y en el tamaño de 5 pulgadas.

En otro artículo algo comentaba [Sobre el rápido avance de la tecnología][blogbitix-186] y básicamente sacaba dos conclusiones. Una no merece comprar tecnología hasta que realmente es necesario pero tampoco esperar indefinidamente a lo siguiente mejor y como segunda conclusión que no hace falta tener lo último para hacer lo mismo que ya es posible con lo anterior.

Una de las últimas adquisiciones que hice fué en 2012, uno de los primeros modelos de la popular computadora del tamaño de una tarjeta de crédito Raspberry Pi con un procesador ARM con el juego de instrucciones _armv6h_ y 256 MiB que he utilizado mayoritariamente para realizar descargas y compartir archivos _torrent_ o algunas pruebas de [utilización de sensores y dispositivos de entrada y salida con Java][blogbitix-212] o para [usar un certificador de Let's Encrypt en un servidor Nginx][blogbitix-252]. El siguiente uso que le daré es como [consola de juegos retro][blogbitix-301].

Ya había placas de tamaño reducido con un computador completo similares a la Raspberry Pi pero la Raspberry Pi ha sido la placa con más éxito y que sigue teniendo uno de los mejores soportes y comunidad. Su propósito original es el aprendizaje a nivel educativo sin embargo su precio reducido de unos 36€ por los que se puede [comprar ahora en Amazon](http://amzn.to/2EP9mHX) ha sido gran parte de su éxito junto con una labor de promoción muy bien realizada en internet que contribuyó a crear gran expectación superando con creces la demanda esperada por los propios creadores en los meses iniciales. Han sido elaborados nuevos modelos más capaces con 4 núcleos a más velocidad y basados en un procesador de arquitectura ARM Cortex-A53 de 64 bits, con WiFi y Bluetooth, pero con solo 1 GiB de memoria.

La Raspberry Pi es la más popular y con mejor soporte pero no es la mini placa más capaz ni incluso la más barata, debido a su éxito han surgido numerosas placas similares. La totalidad de estas placas usan alguna distribución de GNU/Linux y procesadores ARM ya sea [Debian][debian], [Ubuntu][ubuntu], [Android][android], [Arch Linux ARM][archlinuxarm], [Armbian][armbian] o algunas distribuciones especializadas como [Lakka][lakka] para consola de juegos retro y [LibreELEC][libreelec] o [Kodi][kodi] para centro multimedia de salón.

El uso que se les puede dar a estas computadores es muy diverso quizá no como para sustituir a un ordenador de escritorio o portátil basado en procesadores [Intel][intel] o [AMD][amd] pero si para otros propósitos como servidor de descargas, centro multimedia, servidor web, cluster de servidores con [Docker][docker], servicio en la nube propio con [OwnCloud][owncloud], servidor de código fuente con [GitLab][gitlab], servidor de integración continua con [Jenkins][jenkins], base de datos [PostgreSQL][postgresql] o los mencionados consola de juegos retro y centro multimedia entre cualesquiera otros usos que queramos mientras el software necesario esté disponible y la cantidad memoria de la computadora sea suficiente.

<div class="media" style="text-align: center;">
    <img src="assets/images/posts/2018/304/arm-powered.svg" width="300" alt="ARM Powered" title="ARM Powered">
</div>

### Raspberry Pi

Cuando apareció en el 2012 ya había placas similares pero su bajo precio y _marketing_ ha sido la que ha alcanzado la mayor popularidad sin ser la más potente. Gracias a su éxito con más de 14 millones de unidades vendidas es la mejor opción por su gran soporte de la comunidad aunque para algunos casos de uso su gigabyte de memoria en el modelo [Raspberry Pi 3](https://www.raspberrypi.org/magpi/raspberry-pi-3-specs-benchmarks/) puede quedarse escaso. Su _SoC_ BCM2837 de Broadcom no es el más capaz tanto en CPU (_quad-core_ ARM Cortex-A53 a 1.4 GHz) como en GPU (VideoCore IV) y con su gigabit ethernet compartida con los puertos USB y limitada lo que es un inconveniente importante en las transferencias de datos.

* CPU Broadcom BCM2837B0, 4 x Cortex-A53 (ARMv8) 64-bit SoC @ 1.4GHz
* GPU VideoCore IV
* Memoria 1GB LPDDR2 SDRAM
* WiFi 2.4GHz and 5GHz IEEE 802.11.b/g/n/ac wireless LAN, Bluetooth 4.2, BLE
* Red Gigabit Ethernet over USB 2.0 (maximum throughput 300 Mbps)
* Extended 40-pin GPIO header
* Full-size HDMI
* 4 USB 2.0 ports
* CSI camera port for connecting a Raspberry Pi camera
* DSI display port for connecting a Raspberry Pi touchscreen display
* 4-pole stereo output and composite video port
* Micro SD port for loading your operating system and storing data
* 5V/2.5A DC power input
* Power-over-Ethernet (PoE) support (requires separate PoE HAT)

* [Raspberry Pi](https://es.wikipedia.org/wiki/Raspberry_Pi)

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="raspberrypi3b.jpg" thumb1="raspberrypi3b-thumb.jpg" title1="Raspberry Pi 3"
        caption="Raspberry Pi 3" >}}
</div>

### Rock64

El segundo modelo que destacaré es este de [PINE64][pine64] porque posee versiones de 1, 2 y 4 GiB de memoria, gigabit ethernet y 1 puerto USB 3.0 a un precio similar al de una Raspberry Pi, lo único que le falta es WiFi para superar en todo a la RPi. También posee un módulo eMMC más rápido que las tarjetas microSD, SPI de 128 MiB para prescincir de tarjeta microSD en el arranque, salida de audio, otros 2 puertos USB 2.0, salida HDMI con soporte para 4K@60fps HDR10, botones dedicados de encendido y reinicio y los 40 pines para usar sensores. El _SoC_ es un [Rockchip RK3328](http://www.rock-chips.com/a/en/products/RK33_Series/2017/0118/829.html) _quad-core_ ARM Cortex-A53 y la GPU [ARM Mali-450MP2](https://developer.arm.com/products/graphics-and-multimedia/mali-gpus/mali-450-gpu) con capacidad para OpenGL ES 1.1 / 2.0.

* CPU Rockchip RK3328, 4 x Cortex-A53 (ARMv8) 64-bit SoC @ 1.4GHz
* GPU ARM Mali-450MP2
* Memoria 1 / 2 / 4 GiB 1600 MHz LPDDR3 SDRAM
* 128 Mb SPI Flash
* eMMC Module Socket, eMMC Jumper
* MicroSD Card Slot (Bottom)
* 3.5mm Barrel Power Jack (5V3A)
* 4K60P HDR Digital Output
* A/V Jack
* Red Gigabit Ethernet
* 2 x USB 2.0 Dedicated Host, 1 x USB 3.0 Dedicated Host
* Pi-P5+ Bus
* Pi-2 Bus
* Power, Reset and Recovery Buttons
* IR Receiver Port

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="rock64.jpg" thumb1="rock64-thumb.jpg" title1="Rock64"
        caption="Rock64" >}}
</div>

Para suplir la carencia de WiFi en su tienda hay disponible un accesorio conectable a un puerto USB, también tienen la posibilidad de comprar el alimentador de corriente, mando a distancia, caja acrílica y un _splitter_ con _Power Over Ethernet_ o _PoE_ que hace innecesario el alimentador de corriente si poseemos o adquirimos un _switch_ con esa función, tarjetas eMMC de 16, 32 y 64 GiB. Su precio de unos $45 para el modelo de 4 GiB.

<div class="media media-video" style="text-align: center;">
    <iframe width="640" height="360" src="https://www.youtube.com/embed/ZejkWra-Mfc" frameborder="0" allowfullscreen></iframe>
</div>

En el momento de escribir el artículo están preparando el lanzamiento de la placa Rock64Pro con un mejor _SoC_ [Rockchip RK3399](http://www.rock-chips.com/a/en/products/RK33_Series/2016/0419/758.html) _hexa-core_ con dos Cortex-A72 y 4 Cortex-A53 además de un puerto USB Type-C y mejor GPU con una [ARM Mali-T860MP4](https://developer.arm.com/products/graphics-and-multimedia/mali-gpus/mali-t860-and-mali-t880-gpus) _quad-core_ con soporte para OpenGL ES 1.1 / 2.0 / 3.0 and OpenCL 1.2.

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="rock64pro.jpg" thumb1="rock64pro-thumb.jpg" title1="Rock64Pro"
        caption="Rock64Pro" >}}
</div>

Aunque no es como los modelos anteriores de placas base este es destacable por ser un portátil basado en un procesador ARM a un precio de $90. Pantalla de 11" o 14" con una resolución de 1366x768, 2 GiB de memoria, WiFi, microSD, mini HDMI, 16GB eMMC, dos puertos USB 2.0, salida de audio, cámara web y batería de 10000mAH, teclado y _touchpad_.

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="pinebook-11-inch.jpg" thumb1="pinebook-11-inch-thumb.jpg" title1="PineBook (11 pulgadas)"
        caption="PineBook (11 pulgadas)" >}}
</div>

### ASUS Tinker Board

La [ASUS Tinker Board](https://www.asus.com/uk/Single-Board-Computer/Tinker-Board/overview/) también está basada en un _SoC_ de Rockchip pero en este caso el modelo [RK3288](http://www.rock-chips.com/a/en/products/RK32_Series/2014/0504/484.html) que como CPU tiene el modelo más potente de 32 bits con una ARM Cortex-A17 _quad-core_ e incorporando una GPU Mali-T764 mejor que la Mali de la Rock64. Tiene 2 GiB de memoria e incorpora de serie WiFi N y Bluetooth 4.0.

Su precio es algo más elevado que la Rock64 y tiene menos memoria pero más que la Raspberry Pi, aún así incorpora de serie WiFi y Bluetooth por lo que no sería necesario comprar elementos adicionales.

* CPU Rockchip Quad-Core RK3288 processor
* Memoria 2GB Dual Channel DDR3
* GPU ARM Mali-T764
* Micro SD(TF) card slot
* Red Gigabit Ethernet
* WiFi 802.11 b/g/n, Bluetooth V4.0 + EDR
* Audio RTL ALC4040 CODEC
* 4 x USB 2.0
* GPIO 40-pin header, up to 28 x GPIO pins, up to 2 x SPI bus, up to 2 x I2C bus, up to 4 x UART, up to 2 x PWM, up to 1 x PCM/I2S, 2 x 5V power pins, 2 x 3.3V power pins, 8 x ground pins, 1 x PWM, 1 x S/PDIF, 1 x 15-pin MIPI DSI, 1 x 15-pin MIPI CSI

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="asus-tinker-board.jpg" thumb1="asus-tinker-board-thumb.jpg" title1="ASUS Tinker Board"
        caption="ASUS Tinker Board" >}}
</div>

### Cubox

Aunque los modelos de [SolidRun][solid-run] son significativamente más caros poseen buenas especificaciones como el modelo [CuBox i4x4](https://www.solid-run.com/product/cubox-i4x4/) con un procesador _quad core_ aunque en si versión ARMv7 de 32 bits pero con 4 GiB de memoria, gigabit ethernet, WiFi y blutooth incluido en un formato de cubo de 2 pugadas. Como decía a un precio mas elevado, $170.

El modelo mas reciente [Cubox Pulse](https://www.solid-run.com/nxp-family/cubox-pulse/) incorpora un procesador [NXP i.MX8M](https://www.nxp.com/products/processors-and-microcontrollers/applications-processors/i.mx-applications-processors/i.mx-8-processors:IMX8-SERIES) ya a 64 bits pero con el precio igualmente abultado respecto a otras opciones de 170€.

* CPU i.MX8M Dual/Quad core ARM Cortex A53 up to 1.5Ghz (with ARM M4 GPP)
* Memoria up to 4GB LPDDR4
* MicroSD
* eMMC
* 2 x USB 3.0, 1 x HDMI 2.0, 1 x RJ45
* Power on button
* Indication LEDs
* RTC
* IR receiver
* PoE sink support

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="cubox.jpg" thumb1="cubox-thumb.jpg" title1="Cubox"
        image2="cubox-pulse.jpg" thumb2="cubox-pulse-thumb.jpg" title2="Cubox Pulse"
        caption="Cubox y Cubox Pulse" >}}
</div>

### ODROID

Otro distribuidor importante es [Hardkernel][hardkernel] con sus modelos ODROID de pequeñas computadoras basadas en ARM con varios modelos entre ellos el [ODROID C2](http://www.hardkernel.com/main/products/prdt_info.php?g_code=G145457216438) que posee un procesador ARM Cortex-A53 _quad-core_, 2 GiB de memoria, gigabit ethernet, soporta HDMI 2.0 4K@60fps, eMMC, 4 puertos USB 2.0 pero sin WiFi ni bluetooth. A un precio de $46.

Están preparando el modelo [ODROID N1](https://forum.armbian.com/topic/6496-odroid-n1-not-a-review-yet/) tambien basada en el _SoC_ Rockchip RK3399.

<div class="media" style="text-align: center;">
    {{< figure year="2018" pid="304"
        image1="odroid-c2.jpg" thumb1="odroid-c2-thumb.jpg" title1="ODROID C2"
        caption="ODROID C2" >}}
</div>

### NanoPi, OrangePi y BananaPi

De [NanoPi][nanopi] voy a destacar el modelo [NanoPi K2](http://nanopi.org/NanoPi-K2_Feature.html) con 2 GiB de memoria, procesador quad core, con WiFi y bluetooth y gigabit ethernet y soporte de 4K@60fps y H.265 10bit, H.264 a un precio de $50.

Parece que con la costumbre de denominar a estas placas bases el nombre de frutos están [OrangePi Plus 2](http://www.orangepi.org/orangepiplus2/) y [BananaPi M3](http://www.banana-pi.org/m3.html), el primero a un precio de 45€ con 2 GiB de memoria y soporte para HDMI 4K además de incluir gigabit ethernet y WiFi. El modelo de Banana Pi se diferencia en tener un procesador _octa-core_, un puerto SATA y un precio superior de 85€.

### Conclusión

Estos no son los únicos modelos que existen pero son algunos de los más destacables. Si queremos 4 GiB de memoria la opción preferente es la Rock64 con la posibilidad de añadirle en un puerto USB la conectividad WiFi. Si con 2 GiB es suficiente está la la ODROID C2, si se desea que tenga WiFI sin adaptadores adicionales la opción preferente es la NanoPi K2 o la ASUS Tinker Board.

A pesar de que las alternativas de placas bases sean más capaces que la Raspberry Pi esta les gana en mejor soporte del software como distribuciones GNU/Linux y programas como Kodi que también es algo a tener muy en cuenta y este posiblemente es el motivo de que aún siendo menos capaz tiene más éxito e igualmente una muy buena opción. Quizá con algunas de estas placas alternativas hay que leer la documentación que tengan, algún foro y probar más cosas de las que requeriría una Raspberry Pi.

Hasta el 2019 no se lanzará un nuevo modelo de Rasbperry Pi lo que sería la versión 4 y, sin embargo, cuando salga no creo que sea mucho más potente ni tenga más memoria que la que ya hoy tiene la Rock64.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Procesadores ARM Cortex-A](https://www.arm.com/products/processors/cortex-a)
* [Arquitectura ARM](https://es.wikipedia.org/wiki/Arquitectura_ARM)
* [GPUs ARM Mali](https://www.arm.com/products/graphics-and-multimedia/mali-gpu)
* [GPUs ARM Mali (wkipedia)](https://www.arm.com/products/graphics-and-multimedia/mali-gpu)
* [VideoCore](https://en.wikipedia.org/wiki/VideoCore)
* [OpenGL ES][opengles]
* [OpenCL][opencl]
* [OpenVG][openvg]
{{% /reference %}}

{{% /post %}}
