---
pid: 648
type: "post"
title: "Prueba de velocidad de unidad de almacenamiento en GNU/Linux"
url: "/2022/08/prueba-de-velocidad-de-unidad-de-almacenamiento-en-gnu-linux/"
date: 2022-08-18T18:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:uhs-sdxc.webp"
tags: ["gnu-linux", "hardware"]
summary: "En un dispositivo de almacenamiento hay que tener en cuenta el tipo de la interfaz del dispositivo que determina la tasa de transferencia máxima teórica, el tipo de almacenamiento que determina en qué rangos de tasa de transferencia ofrecen la mayoría de dispositivos, el dispositivo en concreto que dependiendo del fabricante y calidad determina en qué parte del rango está la tasa de transferencia el modelo usado, la capacidad de almacenamiento y finalmente el precio que está en función de todo lo anterior."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

Dependiendo del tipo de interfaz y el tipo de almacenamiento de un dispositivo la tasa de transferencia de un dispositivo varía. Una buena tasa de transferencia permite un óptimo rendimiento y finalizar las tareas en menos tiempo principalmente al copiar archivos de una unidad a otra lo que incluye [hacer copias de seguridad][blogbitix-144] para tener una segunda copia de los datos y sincronizada de forma regular para no perder ningún dato en caso de fallo.

Más que el procesador las dos mejoras que se le pueden realizar a algunos equipos para mejorar el rendimiento y prolongar su uso es la ampliación de la memoria RAM y cambiar el tipo de almacenamiento. Si es el caso de pasar de un disco duro a un SSD la diferencia será más que significativa y casi parecerá un equipo nuevo siempre y cuando la cantidad de memoria RAM sea suficiente para la tarea.

A fecha del 2022 tener un SSD y 8 GiB de memoria RAM es lo menos que se le puede exigir a un equipo para usarlo en unas condiciones suficientes que de un rendimiento aceptable para tareas de navegar por internet y ofimática.

Las tasas de transferencia anunciadas por los fabricantes en algunos casos distan significativamente de las reales al hacer uso de ellas con lo que es recomendable si es posible un análisis del rendimiento de la unidad sobre todo para las memorias USB y tarjetas micro SD.

{{< tableofcontents >}}

## Tipos de interfaces

A lo largo de los años los ordenadores han ido adoptando varios tipos de interfaces como estándar que todos los fabricantes de equipos incorporan en sus productos. Cada nueva interfaz ha ofrecido un ancho de banda mayor y mayores tasas de transferencia que la interfaz anterior reemplazada. Dentro de los tipos de interfaces se puede diferenciar entre los empleados para el almacenamiento interno donde se instala el sistema operativo y las interfaces empleadas para conectividad de dispositivos externos extraíbles como memorias USB.

Para el almacenamiento interno se suele utilizar la interfaz SATA que está siendo reemplazada por la interfaz NVMe. Para el almacenamiento externo y dispositivos extraíbles que se pueden conectar y desconectar con el equipo encendido se utiliza la interfaz USB. Cada interfaz también ha evolucionado en diferentes versiones así la última versión de SATA es la 3 que duplicó la tasa de transferencia de la anterior y USB tiene varias versiones como 2.0, 3.0, 3.1/3.2, 4.0 e igualmente para NVMe que tiene 3.0, 4.0 y más adelante 5.0.

Cada interfaz ofrece un ancho de banda máximo teórico, los dispositivos que son capaces de saturar la interfaz llegan casi al máximo teórico aunque normalmente suelen quedarse cerca y algo por debajo.

Las tasas de transferencia máximas teóricas para cada una de las interfaces son las siguientes. NVMe que es la más reciente y sustituye a SATA ofrece una tasa de transferencia varias veces mayor que la última versión de SATA. dado que el acceso al almacenamiento una de los motivos en la rapidez de un ordenador el paso de SATA a NVMe supone una gran mejora cuando se usa de forma intensa, cada nueva versión de NVMe está duplicando la tasa de transferencia de la versión anterior. En las diferentes versiones de USB las tasas de transferencia también aumentan en varias veces la ofrecida por la versión anterior además de tener otras ventajas como soportar una mayor capacidad de alimentación a los dispositivos conectados.

<table class="table">
    <thead class="table-light">
        <th width="300px">Tipo de interfaz</th>
        <th width="300px" colspan="2">Tasa de transferencia</th>
        <th>Año</th>
    </thead>
    <tbody>
        <tr>
            <td>USB 1.0</td>
            <td>12 Mbit/s</td>
            <td>1.5 MB/s</td>
            <td>1996</td>
        </tr>
        <tr>
            <td>USB 2.0</td>
            <td>480 Mbit/s</td>
            <td>60 MB/s</td>
            <td>2000</td>
        </tr>
        <tr>
            <td>USB 3.0</td>
            <td>5 Gbit/s</td>
            <td>500 MB/s</td>
            <td>2010</td>
        </tr>
        <tr>
            <td>USB 3.1</td>
            <td>10 Gbit/s</td>
            <td>1.212 GB/s</td>
            <td>2013</td>
        </tr>
        <tr>
            <td>USB 3.2</td>
            <td>20 Gbit/s</td>
            <td>2.424 GB/s</td>
            <td>2017</td>
        </tr>
        <tr>
            <td>USB 4.0</td>
            <td>40 Gbit/s</td>
            <td>5 GB/s</td>
            <td>2019</td>
        </tr>
        <tr>
            <td>UHS-I <small>(SDHC, SDXC, SDUC)</small></td>
            <td>400 Mbit/s</td>
            <td colspan="2">50 MB/s</td>
        </tr>
        <tr>
            <td>SATA 1</td>
            <td>1.500 Gbit/s</td>
            <td>150 MB/s</td>
            <td>2003</td>
        </tr>
        <tr>
            <td>SATA 2</td>
            <td>3 Gbit/s</td>
            <td>300 MB/s</td>
            <td>2004</td>
        </tr>
        <tr>
            <td>SATA 3</td>
            <td>6 Gbit/s</td>
            <td>600 MB/s</td>
            <td>2008</td>
        </tr>
        <tr>
            <td>NVMe 3.0</td>
            <td>32 Gbit/s</td>
            <td>3.938 GB/s</td>
            <td>2013</td>
        </tr>
        <tr>
            <td>NVMe 4.0</td>
            <td>64 Gbit/s</td>
            <td>7.876 GB/s</td>
            <td>2017</td>
        </tr>
        <tr>
            <td>NVMe 5.0</td>
            <td>128 Gbit/s</td>
            <td>15.754 GB/s</td>
            <td>2019</td>
        </tr>
    </tbody>
</table>

* [List of interface bit rates](https://en.wikipedia.org/wiki/List_of_interface_bit_rates)

## Tipos de almacenamiento

El tipo de almacenamiento es la tecnología que se emplea en los dispositivos de almacenamiento, los discos duros con platos circulares en cuya superficie magnética se guarda la información, han sido ya reemplazados por las unidades SSD sin partes móviles que ofrece un tiempo de acceso mucho menor y una tasa de transferencia mucho mayor. Si hay que actualizar un equipo que tiene un disco duro sustituir este componente por un SSD seguramente suponga una gran mejora en el rendimiento de equipo más que cualquier otra actuación salvo quizá la cantidad de memoria.

Un disco duro que normalmente utiliza la interfaz SATA queda muy lejos de ofrecer una tasa de transferencia cercana a la que es capaz de soportar la interfaz SATA. Un dispositivo de almacenamiento SSD con interfaz SATA si se queda cerca del máximo teórico de la interfaz.

La tasa de transferencia máxima teórica de la interfaz es una cosa pero la real que observa el usuario al copiar archivos puede ser muy distinta en la que influye el tipo de almacenamiento y la calidad del dispositivo. Las tasas de transferencia que se observa al copiar archivos suelen ser algo inferiores a la máximas teóricas.

## Precios

Los precios varían según los diferentes tipos de almacenamiento, el tipo de interfaz y la cantidad de almacenamiento. En el caso del almacenamiento y dado el avance de la tecnología es cuestión de tiempo de que o bien los precios se reduzcan paulatinamente o por el contrario se ofrezca el mayor cantidad de almacenamiento por el mismo precio.

Por este avance de la tecnología es una buena idea comprar un producto que ofrezca una cantidad de espacio suficiente para el uso actual y de unos pocos años posteriores pero no es recomendable comprar una cantidad desorbitada de almacenamiento si no se le va a dar uso, por el paso del tiempo su coste se reducirá o bien surgirá una nueva interfaz de almacenamiento que deje obsoleta a una unidad actual.

En los siguientes enlaces hay una muestra de los precios actuales de varios tipos de almacenamiento e interfaces desde una tarjeta microSD hasta un SSD con interfaz NVMe que ofrece las mayores tasas de transferencia pero a un precio más caro por GB de almacenamiento.

{{< amazon
    tags="storage-microsd" >}}
{{< amazon
    tags="storage-usb-external" >}}
{{< amazon
    tags="storage-usb-memory,storage-ssd,storage-nvme" >}}

## Pruebas de velocidad

La transferencia de una unidad depende de la combinación de la interfaz y tipo de almacenamiento. El máximo teórico de la interfaz de conexión y dependiendo del tipo de almacenamiento el dispositivo está limitado por la interfaz, cercano a la interfaz o por debajo de la interfaz.

Un dispositivo SSD conectado mediante la interfaz SATA 3 llega a los límites teóricos de la interfaz cercano a los 600 MB/s. Un disco duro conectado en una interfaz SATA se queda en apenas 80 MB/s muy por debajo del máximo teórico de la interfaz, un disco duro no es capaz de saturar la interfaz SATA.

No dispongo de todos los tipos de almacenamiento, ni de todas las interfaces. Para conocer una medida aproximada de las tasas de transferencia de una marca o tipo de almacenamiento es recomendable consultar un vídeo para conocer si la tasa de transferencia real y si cumple con el mínimo exigido según la tarea a la que se vaya a dedicar el dispositivo de almacenamiento antes de comprarlo.

Estas son las tasas de transferencia que he obtenido para un disco duro externo con interfaz USB 2.0 y conectado a un concentrador USB 2.0, también de una memoria USB 2.0 y de una tarjeta microSD de tipo SDXC conectado con la interfaz UHS-I al ordenador.


{{< image
    gallery="true"
    image1="image:opciones-prueba-de-rendimiento.webp" optionsthumb1="300x200" title1="Opciones de la pruebas de rendimiento" >}}
{{< image
    gallery="true"
    image1="image:usb-20-hdd.webp" optionsthumb1="300x200" title1="Prueba de velocidad de HDD"
    image2="image:usb-20-memory.webp" optionsthumb2="300x200" title2="Prueba de velocidad de memoria USB" >}}
{{< image
    gallery="true"
    image1="image:uhs-sd.webp" optionsthumb1="300x200" title1="Prueba de velocidad de tarjeta micro SD"
    image2="image:uhs-sdxc.webp" optionsthumb2="300x200" title2="Prueba de velocidad de tarjeta SDXC"
    caption="Pruebas de velocidad de HDD, memoria USB, tarjeta SD y SDXC" >}}

El uso que le iba a dar a una tarjeta micro SD de tipo SDXC era como almacenamiento para un _smartphone_ pero como finalmente [compré un Samsung Galaxy S21 FE][blogbitix-633] que no dispone memoria ampliable con tarjeta micro SD he tenido que reutilizar la tarjeta para otro propósito.

La tarjeta micro SD Samsung de 512 GB ofrece una transferencia de 85 MB/s en lectura y 55 MB/s en escritura que ha sido suficiente para realizar virtualización y usar la micro SD como unidad de almacenamiento externo en el que hacer copias de seguridad. Para virtualizar es más apropiado utilizar la unidad interna NVMe o al menos un SSD con interfaz SATA interno o USB 3.0 pero aunque tenía dudas si iba a ser suficiente la micro SD permite usarla como unidad de almacenamiento al virtualizar con un rendimiento aceptable.

Las diferencias entre dos dispositivos con la misma interfaz y tipo de almacenamiento también varía y es recomendable consultar algún vídeo donde ver el rendimiento real que observa el usuario y no el anunciado por el fabricante para comprar el mejor producto. Un programa que permite analizar el rendimiento de un dispositivo es [CrystalMark][crystalmark] para [Windows][windows] y la aplicación _Discos_ con su opción _Probar rendimiento de la partición_ para [GNU][gnu]/[Linux][linux] usando el entorno de escritorio [GNOME][gnome].

En los siguientes vídeos se muestra la tasa de transferencia de varios de los diferentes tipos de memoria e interfaces.

{{< youtube
    video="kJ5qljYa4aM" >}}
{{< youtube
    video="aazprXilyxY" >}}
{{< youtube
    video="7VuxTnKEKic" >}}
{{< youtube
    video="mVLlPIyvxkY" >}}

{{< reference >}}
* [How to Measure Disk Performance using Fio in Linux](https://linoxide.com/measure-disk-performance-fio/)
* [Linux Storage Benchmarking With FIO](https://www.youtube.com/watch?v=TASYaWTkg0U)
{{< /reference >}}

{{% /post %}}
