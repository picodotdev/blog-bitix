---
pid: 119
type: "post"
title: "Windows 10 y Office 2016 tan fáciles de usar sin licencia como siempre"
url: "/2016/01/windows-10-y-office-2016-tan-faciles-de-usar-sin-licencia-como-siempre/"
date: 2016-01-08T17:00:00+01:00
updated: 2020-09-12T01:00:00+02:00
index: true
rss: true
sharing: true
comments: true
language: "es"
imageHead: "image:windows-10-wallpaper.jpg"
imagePost: "logotype:microsoft.svg"
tags: ["microsoft", "opinion", "planeta-codigo", "windows"]
summary: "Comentaba en un artículo que es sorprendente que Microsoft con la capacidad que tiene y estando los dispositivos conectados a internet mayoritariamente no sea capaz de evitar usar su software sin licencia. Estoy a punto de ver si con Windows 10 y Office 2016 sigue siendo tan fácil como siempre. También tendré una muestra de la injusta fama de Windows pero que permite Microsoft por el _bloatware_ que preinstalan los fabricantes de dispositivos con su sistema operativo."
---

{{% post %}}

{{< logotype image1="microsoft.svg" image2="windows-10.svg" image3="microsoft-office.svg" >}}

Este 2016 lo he empezando nada más y nada menos que instalando el sistema operativo y programas de un portátil. Uno de esos favores que habitualmente hacemos los informáticos de los que yo normalmente hago de buen grado, en esta ocasión recibí otro a cambio de otra índole así que perfecto. La necesidad de la reinstalación venía de que el portátil era notablemente lento a pesar de tener un disco duro mecánico y tampoco era porque el equipo fuese antiguo o tuviese poca memoria, el procesador era un AMD Athlon x2 QL-65 con 4 GiB de memoria y 320 GB de disco duro, algo que debería ser más que suficiente para su propósito de navegar por internet, ver películas, escuchar música y tareas ofimáticas. Era lento al iniciarse e al iniciar la ejecución programas además de algún mensaje del antivirus quejándose de que su periodo de prueba había expirado que aparecía al entrar al escritorio. Eso no era todo algún virus había cifrado los archivos personales, fotos y documentos, pidiendo a cambio de recuperarlos algo que ni siquiera me molesté ver en detalle, seguro que nada bueno.

El equipo tenía varios programas de _bloatware_ típicos de los portátiles con [Windows][windows] proporcionados por los fabricantes, un antivirus que podría encajar con la definición de aquello de lo que intenta proteger que caduca al cabo de un tiempo y lo deja desprotegido, un reproductor que se inicia con el escritorio y se esconde en la parte superior de la pantalla por si atípicamente lo necesitas. Los fabricantes son los que preinstalan estos programas indeseables y serán los principales responsables pero si [Microsoft][microsoft] conociendo el mal nombre que le dan a sus sistemas hasta el momento no lo ha impedido algo de culpa tendrá, si su sistema operativo es una imposición en prácticamente la totalidad de portátiles que se venden en las tiendas, ¿conocéis el [impuesto Windows][elblogdepicodev-57]?, podría imponer alguna condición para permitir preinstalar Windows y hacer más felices a sus usuarios.

Por otra parte desde la época de Windows Vista el equipo se puede reinstalar y dejar como de fábrica incluído todo el _bloatware_ anteriormente citado pero para ello se reservan unos 10 GiB del disco duro espacio que no es usable por el usuario. Con discos duros de capacidades de 320 / 500 GB que queden reservados 10 GiB no es mayor problema pero más de uno ha preguntado porque su espacio disponible no se corresponde con las especificaciones del equipo que compró.

De modo que para corregir los problemas hice una reinstalación limpia.

### Inicio la instalación

Llegado al punto de hacer la reinstalación no tuve mayor problema, el equipo tenía Windows 7 originalmente, se había actualizado a Windows 10 y activado correctamente en el periodo de actualización gratuita que ofrece Microsoft. Como he hecho multitud de veces en años y con versiones anteriores empiezo por [descargar la imagen del medio de instalación de Windows 10](https://www.microsoft.com/es-es/software-download/windows10) y el [medio de instalación de Microsoft Office, Hogar y Estudiantes](http://officecdn.microsoft.com/pr/492350f6-3a01-4f97-b9c0-c7c6ddf67d60/media/es-es/HomeStudent2019Retail.img). A continuación [iniciar la instalación de Windows 10 paso a paso desde cero][blogbitix-231]. Las imágenes del medio de instalación de Windows 10 la ofrece amablemente Microsoft en su propia página web por lo que no me fué necesario buscarla como antiguamente en las redes de compartición de archivos, punto para ellos.

En uno de los pasos de la instalación borré todas las particiones recuperando el espacio reservado para la partición de recuperación. Unos cuantos minutos de espera, un par de reinicios y varias opciones de privacidad desactivadas que podrían dar a Windows 10 el calificativo de software espía, supuestamente por el bien de usuario, y después de crear la cuenta del usuario el sistema se inicia con todos los dispositivos funcionando correctamente incluidos la tarjeta gráfica, la red y la WiFi. Windows se muestra como activado.

Toca instalar los programas adicionales básicos con la intención de que sean de software libre o respetando las licencias [Firefox][firefox] junto con [AdBlockPlus][adblockplus], [VLC][vlc], [PeaZip][peazip], [ImgBurn][imgburn], [Acrobat Reader][adobe-acrobat-reader], [FreeFileSync][freefilesync], [uTorrent][utorrent] a los que añado [Java][java]. Finalmente me piden que instale la _suite_ ofimática [Microsoft Office][microsoft-office] ya que va a ser utilizada, mi intención al traste, me planteo recomendar alguna opción alternativa como [Google Docs][google-docs] o [LibreOffice][libreoffice]. Sin embargo, Office es lo que usan ¿piden a los alumnos que adquieran una licencia que aún para estudiantes cuesta unos 125 €? ¿250 € para otros mortales?, es inicio del 2016, es fin de semana y no quiero darle muchas vueltas al tema, descargo la última versión de Office y la instalo.

### Trato de activar Windows y Office

Inicio el Word y me muestra el mensaje que tras unos 30 días se desactivarán muchas de sus funciones. Llega el momento de probar empíricamente una vez más aquello que dije en [A Microsoft no le importa que uses Windows u Office sin licencia][blogbitix-92], busco algún activador en Google, hay cientos de resultados, descargo uno del enlace que me genera algo de esperanza de no tener virus, inmediatamente descargado el archivo ejecutable Windows Defender avisa de que tiene virus, lo ejecuto a pesar de todo y hago clic en el botón para activar el Office, sale un mensaje indicando que no se ha realizado la activación correctamente, pruebo varias opciones sin efecto aparente ¿a ver si no tengo razón y Microsoft ha hecho lo suyo para evitar la piratería?. Rebusco entre los enlaces y me descargo otro, aviso de virus, todo normal, lo ejecuto, empiezan a aparecer iconos en el escritorio de programas raros que se están instalando y al Firefox se le ha cambiado su página de inicio, ¡vaya! este supuesto activador si que era un virus, Defender tenía razón, toca reinstalar de nuevo todo.

### Buscando un activador sin virus

Saco las armas gordas, comienzo una nueva reinstalación de Windows 10 y Office en una máquina virtual con [VirtualBox][virtualbox] que me permitirá hacer las pruebas más fácilmente usando las instantáneas del sistema, si algo va mal podré volver a un punto anterior sin tener que reinstalar todo de nuevo. Objetivo encontrar algún activador que funcione, hace tiempo cuando usaba Windows en el primer o segundo quizá el tercero enlace no más de Google estaba el activador que funcionaba, vuelvo a pensar que igual Microsoft ha protegido mejor su software. Me informo un poco más en algunas de los cientos de páginas, encuentro que tengo que desactivar Windows Defender, en la explicación de esas páginas activar Windows y Office (cualquiera de sus versiones) es tan fácil como recordaba, usar un activador y darle a un botón ¿simplemente no he encontrado el correcto?. Pongo un poco más de interés y busco la página «oficial» de un activador, parece que he encontrado una con buenas sensaciones. Desactivado Windows Defender ejecuto el activador, se abre la ventana y selecciono activar Windows y Office, en unos segundos acaba e informa de que la activación ha sido correcta. Compruebo que Windows está activado y Office también.

### A la tercera instalación va la vencida

Inicio la tercera instalación de nuevo en el portátil y realizo la activación de la misma forma que en la máquina virtual, salvo que solo de Office ya que el portátil ya tiene licencia de Windows. Office informa de que está activado. Me gustaría no haberlo podido conseguir.

Es inicio del 2016 y no he hecho una sino tres instalaciones de Windows y Office, más que días del año. Y sigo teniendo razón, usarlos sin licencia sigue siendo tan fácil como siempre una vez encontrados los enlaces adecuados en Google, ¿un martes de parches se instalará alguna actualización de Windows que descubra el _crack_ aplicado al Office? Me enteraré.

{{< image
    gallery="true"
    image1="image:windows-10.png" optionsthumb1="300x200" title1="Windows 10"
    image2="image:microsoft-word-2016.png" optionsthumb2="300x200" title2="Microsoft Word 2016"
    caption="Windows 10 y Microsoft Word 2016" >}}

### Qué alternativa hay a usar un activador

Al final recurrí a un activador pero son inseguros, es mejor comprar una licencia a alguno de los vendedores alternativos a Microsoft que ofrecen códigos de licencia totalmente válidas y legales a unos precios mucho más baratos que los de Microsoft. Microsoft vende las licencias de Windows a partir de 145 € para Windows 10 y de Microsoft Office a partir de 149 €, otros vendedores que se pueden encontrar en [Amazon][amazon-affiliate] y [eBay][ebay] ofrecen licencias totalmente legales y mucho más baratas, de entre 10 € y 15 €. Para que no se deshabiliten algunas opciones de personalización en Windows, se deshabilite la edición de documentos en Microsoft Office y no aparezcan más mensajes solicitando la activación hay que [comprar una licencia y activar Windows 10 y Office 2019][blogbitix-514].

* [Licencia de Windows 10](https://www.microsoft.com/es-es/store/b/windows) en Microsoft.
* [Licencia de Microsoft Office 2019](https://www.microsoft.com/es-es/microsoft-365/buy/compare-all-microsoft-365-products) en Microsoft.
* [Licencia de Windows 10 Home](https://amzn.to/333Df5X) en Amazon.
* [Licencia de Windows 10 Pro](https://amzn.to/3iZ4dBk) en Amazon.
* [Licencia de Microsoft Office 2019](https://amzn.to/369oJfc) en Amazon.
* [Licencia de Windows 10 Home](https://www.ebay.es/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=windows+10+home+key+64+bits&_sacat=0&LH_TitleDesc=0&_sop=2&_osacat=0&_odkw=windows+10+home+key+64) en eBay.
* [Licencia de Windows 10 Pro](https://www.ebay.es/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=windows+10+professional+digital+key+64+bits&_sacat=0&LH_TitleDesc=0&_sop=2&_osacat=0&_odkw=windows+10+pro+digital+key+64+bits) en eBay.
* [Licencia de Microsoft Office 2019](https://www.ebay.es/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=microsoft+office+2019++key&_sacat=0&LH_TitleDesc=0&_sop=2&_osacat=0&_odkw=microsoft+office+2019+pro+key) en eBay.

Si puedes usa software con su licencia, sino usa alguna de las opciones alternativas disponibles tanto para Windows [eligiendo una distribución GNU/Linux][blogbitix-190] como para [Office y alguna de sus 4 alternativas][blogbitix-143] que se adapte tus necesidades. E incluso si se quiere [comprar un ordenador nuevo sin Windows][blogbitix-419] tanto portátil o de escritorio de buena calidad las marcas [Slimbook][slimbook] y [VANT][vant] ofrecen varios modelos en cada categoría, también están [los pequeños Intel NUC][blogbitix-363].

{{% /post %}}
