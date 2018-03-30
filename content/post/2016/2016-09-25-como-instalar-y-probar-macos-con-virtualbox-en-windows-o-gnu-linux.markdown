---
pid: 181
title: "Cómo instalar y probar macOS con VirtualBox en Windows o GNU/Linux"
url: "/2016/09/como-instalar-y-probar-macos-con-virtualbox-en-windows-o-gnu-linux/"
date: 2016-09-25T11:00:00+02:00
updated: 2016-09-25T23:00:00+02:00
rss: true
sharing: true
comments: true
language: "es"
tags: ["apple", "blog-stack", "gnu-linux", "planeta-codigo", "planeta-linux"]
summary: "¿Queres comprar un Mac, tienes dudas o deseas probar su sistema operativo antes? Aunque el rendimiento será menor que una máquina real y la experiencia de usuario será peor, usando un _hackintosh_ mediante VirtuaBox ya sea con Windows o GNU/Linux podremos probarlo en detalle y hacernos una idea de su funcionamiento."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="apple.png" title1="Apple" image2="macos.png" title2="macOS" >}}

Estaría leyendo alguna noticia, ahora no me acuerdo que me despertaría la curiosidad pero me pregunté si es posible usar de forma virtualizada [macOS][macos] con [VirtualBox][virtualbox] tanto en [Windows][windows] como en [GNU][gnu]/[Linux][linux]. Usar lo que se conoce como _hackintosh_, esto es _hackear_ el sistema operativo Macinstosh de los equipos de [Apple][apple] para que se ejecute en cualquier un dispositivo no soportado. En la página web [hackintosh](http://www.hackintosh.com/) puede encontrarse información diversa sobre el tema como instrucciones, vídeos, comunidades, ....

Es política de Apple que su sistema operativo solo pueda ser ejecutado en sus equipos que los vende de forma conjunta a un notable precio, no se si alguien usa macOS en un sistema no Mac con garantías de que en alguna actualización el sistema deje de funcionar teniendo en cuenta la política de Apple para con sus productos.

Los pasos para instalar macOS en una máquina virtual con VirtualBox son los siguientes, hay que descargar un [archivo con la imagen del disco duro de macOS](https://drive.google.com/drive/folders/0B1zbCptv-EMWc0ZfeUFLaldvUm8) y una vez creada la máquina virtual en VirtualBox ejecutar unos comandos para cambiar algunos parámetros del sistema de arranque EFI. Deberemos descomprimir la imagen del disco duro para obtener el archivo de extensión _vmdk_ y proceder a crear la máquina virtual. Elegimos crear una máquina virtual de tipo macOS, cambiaremos algunas opciones, asignando unos 2 o 3 GiB de memoria, seleccionaremos el disco duro con la imagen _vmdk_ descomprimida y cambiaremos la memoria asignada a la pantalla.

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="181"
        image1="configuracion-virtualbox-macos-vm.png" thumb1="configuracion-virtualbox-macos-vm-thumb.png" title1="Configuración máquina virtual macOS"
        image2="configuracion-virtualbox-macos-pantalla.png" thumb2="configuracion-virtualbox-macos-pantalla-thumb.png" title2="Configuración pantalla VirtualBox"
        caption="Configuración de la máquina virtual" >}}
</div>

Si usamos Windows deberemos ejecutar los siguientes comandos en modo administrador, sustituyendo _macOS Sierra 10.12_ por el nombre que le hayamos dado a la máquina virtual al crearla:

{{< gist picodotdev 588b2cd99be69d340c79cafb55f9b334 "windows.cmd" >}}

En el caso de Linux el archivo de comandos es similar e igualmente sustituiremos _macOS Sierra 10.12_ por el nombre que le hayamos dado a la máquina virtual al crearla:

{{< gist picodotdev 588b2cd99be69d340c79cafb55f9b334 "linux.sh" >}}

Hecho estos dos pasos podemos proceder a iniciar la máquina virtual, veremos que empiezan a salir unos cuantos mensajes hasta que se inicia el sistema gráfico con la instalación y su asistente que constará de varios pasos.

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="181"
        image1="1-instalacion-inicio.png" thumb1="1-instalacion-inicio-thumb.png" title1="Inicio instalación macOS"
        image2="2-instalacion-welcome.png" thumb2="2-instalacion-welcome-thumb.png" title2="Asistente instalación macOS" >}}
    {{< figure year="2016" pid="181"
        image1="3-instalacion-keyboard.png" thumb1="3-instalacion-keyboard-thumb.png" title1="Teclado"
        image2="4-instalacion-location.png" thumb2="4-instalacion-location-thumb.png" title2="Localización" >}}
    {{< figure year="2016" pid="181"
        image1="5-instalacion-transfer.png" thumb1="5-instalacion-transfer-thumb.png" title1="Transferencia"
        image2="6-instalacion-apple-id.png" thumb2="6-instalacion-apple-id-thumb.png" title2="Apple ID" >}}
    {{< figure year="2016" pid="181"
        image1="7-instalacion-terms-conditions.png" thumb1="7-instalacion-terms-conditions-thumb.png" title1="Términos y condiciones"
        image2="8-instalacion-account.png" thumb2="8-instalacion-account-thumb.png" title2="Cuenta" >}}
    {{< figure year="2016" pid="181"
        image1="9-instacion-timezone.png" thumb1="9-instacion-timezone-thumb.png" title1="Zona horaria"
        image2="10-instalacion-diagnostics.png" thumb2="10-instalacion-diagnostics-thumb.png" title2="Diagnósticos" >}}
    {{< figure year="2016" pid="181"
        image1="11-instalacion-siri.png" thumb1="11-instalacion-siri-thumb.png" title1="Siri"
        image2="12-instalacion-setting-up.png" thumb2="12-instalacion-setting-up-thumb.png" title2="Configurando" >}}
</div>

Terminada la instalación entramos en el escritorio con el aspecto clásico y fondo de pantalla de macOS y las aplicaciones incorporadas en el propio sistema por defecto.

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="181"
        image1="13-macos-start.png" thumb1="13-macos-start-thumb.png" title1="Inicio"
        image2="14-macos-help.png" thumb2="14-macos-help-thumb.png" title2="Ayuda" >}}
    {{< figure year="2016" pid="181"
        image1="15-macos-overview.png" thumb1="15-macos-overview-thumb.png" title1="Resumen"
        image2="16-macos-finder.png" thumb2="16-macos-finder-thumb.png" title2="Finder" >}}
    {{< figure year="2016" pid="181"
        image1="17-macos-photos.png" thumb1="17-macos-photos-thumb.png" title1="Fotos"
        image2="18-macos-preferences.png" thumb2="18-macos-preferences-thumb.png" title2="Preferencias" >}}
</div>

Como partidario del software libre y la privacidad además de [los ínfimos impuestos que paga Apple][blogbitix-175] usando reprobable ingeniería fiscal y su software privativo no recomiendo usar un Mac pero si alguien quiere probar este sistema operativo antes de decidirse a hacer una compra usarlo de forma virtualizado es una buena ayuda para tomar una decisión y ver si nos gusta su sistema operativo aparte del propio equipo junto con su precio que podemos ver en las tiendas. Si no nos convence ni su software ni su abultado precio podemos optar por un portátil de [Slimbook](https://slimbook.es/) mucho más económico y con características notables con los que no tendremos que pagar tampoco el [impuesto Windows][elblogdepicodev-57] ya que podemos elegir entre varias la distribución GNU/Linux preinstalada que deseamos.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B00OQ2I4GK&linkId=cb28136e0fe9b53cc2f70659a332a802&internal=1"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01ET2K6SU&linkId=0e5bd11129416c356bc34a6aefbcfff7&internal=1"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01EWWHFOU&linkId=a06a014a2d694cd3a57c629904fbba42&internal=1"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B00ULPMPF2&linkId=90349519a4fe5c650bd8ca6d64f05302&internal=1"></iframe>
</div>

Deberemos tener en cuenta al probar macOS con VirtualBox que el rendimiento de la máquina virtual no será exactamente el mismo que en un equipo real por la sobrecarga impuesta por la virtualización realizada. La experiencia de usuario puede cambiar significativamente junto con el hecho de que los portátiles Mac tiene una pantalla bastante mejor y con más resolución que la mayoría de portátiles diseñados para Windows o GNU/Linux y un SSD de gran rendimiento además de la falta de su _touchpad_, todo esto puede marcar alguna diferencia.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [How to Install macOS Sierra 10.12 on VirtualBox?](http://www.wikigain.com/install-macos-sierra-10-12-virtualbox/)
{{% /reference %}}

{{% /post %}}
