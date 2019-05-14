---
pid: 214
title: "Ejemplo encender y apagar diodo LED con la Raspberry Pi en Java"
url: "/2017/03/ejemplo-encender-y-apagar-diodo-led-con-la-raspberry-pi-en-java/"
aliases: ["/2017/03/ejemplo-de-apagado-y-encendido-de-diodo-led-con-la-raspberry-pi-en-java/"]
date: 2017-03-11T10:00:00+01:00
updated: 2017-03-12T00:15:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "gnu-linux", "java", "planeta-codigo", "planeta-linux", "programacion"]
series: ["electronica"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="raspberrypi.svg" title1="Raspberry Pi" width1="200" image2="java.svg" title2="Java" width2="200" >}}

El primer ejemplo que haré de un programa Java que usa los _pines_ GPIO para realizar algo con el [kit de inicialización a la electrónica con la Raspberry Pi][blogbitix-212] consiste en un pequeño programa Java que hace parpadear un diodo LED. La librería [Diozero][diozero] ofrece a los programas Java el acceso a los _pines_ GPIO de la diferentes versiones de la Raspberry Pi desde la 1 (rev 1 y rev 2) pasando por los modelos B+, 2 y 3. Otra librería que se puede usar con el lenguaje de programación Java es [Pi4J][pi4j] aunque personalmente Diozero me ha gustado más por ser de más alto nivel.

Algunas de las características que ofrece la librería Diozero son:

* Soporta dispositivos GPIO / I2C / SPI (LEDs, botones, sensores, motores, pantallas, etc).
* Soporta todos los modelos de las placas Raspberry Pi, Odroid C2, BeagleBone Black, C.H.I.P y Asus Tinker.
* Usa caracterśiticas de Java como [gestión automática de recursos](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html), [expresiones lambda](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) y [referencias a métodos](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html) que simplifican el desarrollo y mejoran la legibilidad.
* [Lista de deipositivos](http://rtd.diozero.com/en/latest/#devices).
* Soporta varios proveedores para el acceso al los dispositivos con [diferentes grados de rendimiento](http://rtd.diozero.com/en/latest/#performance).

Una de las primeras cosas a conocer es como se numeran los _pines_ en la Raspberry Pi ya que hay varias nomenclaturas (_header_, wiringPi y Broadcom) y que nomenclatura utiliza la librería Diozero. También deberemos tener en cuenta el modelo de la Raspberry Pi que poseamos ya que según el modelo hay pequeñas diferencias en algunos _pines_. Además si usamos una placa de extensión para pruebas sin sodadura como la [wiringPi][wiringpi] deberemos identificarlos por su nombre. Yo que poseo una de las primeras Raspberry Pi (la 1, rev1) el correspondiente su [esquema de _pines_ Raspberry Pi 1 (rev. 1)](https://www.raspberrypi.org/documentation/usage/gpio/) es el del enlace. En ese esquema se define que el _pin_ número 12 según el conteo del _header_ corresponde a GPIO 18 según la nomenclatura Broadcom y la librería Diozero y al GPIO 1 en la librería Pi4J y en la placa de extensión wiringPi.

<div class="media" style="text-align: center;">
    {{< figure
        image1="raspberrypi1b.jpg" thumb1="raspberrypi1b-thumb.jpg" title1="Raspberry Pi 1 B"
        image2="raspberrypi3b.jpg" thumb2="raspberrypi3b-thumb.jpg" title2="Raspberry Pi 3 B"
        caption="Placas modelos Raspberry Pi 1 B y 3 B" >}}
    {{< figure
        image1="breadboard-cableado.jpg" thumb1="breadboard-cableado-thumb.jpg" title1="Breadboard y placa extesión GPIO wiringPi de 26 pines"
        image2="gpio-extension-40-pines-board.jpg" thumb2="gpio-extension-40-pines-board-thumb.jpg" title2="Placa extesión GPIO de 40 pines"
        caption="Breadboard y placa extesión GPIO wiringPi de 26 _pines_ y 40 pines" >}}
    {{< figure
        image1="esquema-cableado.png" thumb1="esquema-cableado-thumb.png" title1="Esquema del cableado"
        caption="Esquema del cableado" >}}
</div>

Como uso la placa de extensión wiringPi para conectar los _pines_ de la Raspberry Pi a otra placa para hacer pruebas sin soldadura también conocidas como _breadboard_  resultará que en el programa Java al usar Diozero uso la nomenclatura Broadcom para identificar los _pines_ pero al conectar los cables en la placa de pruebas uso la nomenclatura de wiringPi.

Para el ejemplo utilizaré la placa de extensión sin soldadura, una resistencia de 200 ohmios (dadas sus bandas de colores rojo, negro, marrón y dorado) y un diodo LED además de un par de cables macho-macho para realizar las conexiones electrónicas entre el GPIO 18 (según la nomenclatura de la librería Diozero y Broadcom, 12 según la nomenclatura del _header_ y 1 según la de wiringPi) y la resistencia además de entre el diodo y la línea de tierra. Los diodos LED poseen una orientación y hay que conectar la resistencia con el polo positivo del diodo LED, el polo positivo del diodo LED identifica porque es la patita larga y el negativo con tierra es la patita corta. Si realizamos la conexión al revés solo pasará que el diodo no se enciende pero no lo estropeará, la resistencia si es necesaria para no hacer que pase por el diodo una intensidad que lo estropee como se explica en
[¿Qué resistencia ooner a un LED?](http://www.educachip.com/resistencia-led/).

<div class="media" style="text-align: center;">
    {{< figure
        image1="diodo-resistencia.jpg" thumb1="diodo-resistencia-thumb.jpg" title1="Diodo blanco y resistencia de 200 ohmios"
        caption="Diodo blanco y resistencia de 200 ohmios" >}}
</div>

Las resistencias poseen cuatro bandas de colores que indican el valor en ohmios de esa resistencia, la tabla de colores es el siguiente:

<div class="media" style="text-align: center;">
    {{< figure
        image1="codigo-colores-resistencias.jpg" thumb1="codigo-colores-resistencias-thumb.jpg" title1="Código de colores de las resistencias"
        caption="Código de colores de las resistencias" >}}
</div>

El programa Java para hacer parpadear el diodo LED con la librería Diozero con el proveedor [pigpio](http://abyz.co.uk/rpi/pigpio/). El ejemplo consiste en activar y apagar el _pin_ sucesivamente en un bucle y usar el método [Thread.sleep](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#sleep-long-) para que pase unos segundos entre uno y otro y nos de tiempo a ver el encendido y apagado. El nada complejo programa Java para controlar el diodo y un vídeo de su funcionamiento están a continuación.

{{< code file="PinBlink.java" language="Java" options="" >}}
{{< code file="executeSSH.sh" language="Bash" options="" >}}
{{< code file="executeGradle.sh" language="Bash" options="" >}}

<div class="media media-video" style="text-align: center;">
  <iframe width="640" height="360" src="https://www.youtube.com/embed/NX5tBxWuzFA" frameborder="0" allowfullscreen></iframe>
</div>

En un artículo anterior comento [como disponer de un entorno para desarrollar, desplegar las librerías _jar_ en la Raspberry Pi][blogbitix-213] y como ejecutar los ejemplos desde la línea de comandos usando una combinación de herramientas de [SSH][ssh], [rsync][rsync] y [Ansible][ansible].

{{< sourcecode git="blog-ejemplos/tree/master/JavaRaspberryPi" command="./gradlew executePinBlink" >}}

{{% /post %}}
