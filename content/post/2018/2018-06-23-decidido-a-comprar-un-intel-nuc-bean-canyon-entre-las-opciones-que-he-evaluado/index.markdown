---
pid: 329
title: "Decidido a comprar un Intel NUC Bean Canyon entre las opciones que he evaluado"
url: "/2018/06/decidido-a-comprar-un-intel-nuc-bean-canyon-entre-las-opciones-que-he-evaluado/"
date: 2018-06-23T22:30:00+02:00
updated: 2018-06-24T21:30:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["hardware", "planeta-codigo", "opinion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="intel.svg" title1="Intel" width1="300" >}}

El ordenador portátil que tenía sigue siendo perfectamente útil, sin embargo, en algunos casos concretos de mi uso la cantidad de memoria que tenía se me estaba quedando corta y cuando me pasa esto suelo aprovechar si alguien de mi entorno necesita ordenador traspasarlo y yo comprarme otro nuevo.

El ordenador que tenía era un [Sony VAIO de finales del 2012][elblogdepicodev-134] con un procesador [Intel i3 3210M](https://ark.intel.com/es-es/products/65700/Intel-Core-i3-3110M-Processor-3M-Cache-2_40-GHz) con gráfica integrada [HD 4000](https://www.notebookcheck.org/Intel-HD-Graphics-4000.81383.0.html), 8 GiB de memoria, un disco duro de estado sólido Samsung 840 EVO de 256 GiB con interfaz SATA y una pantalla de resolución 1600x900. Durante todo este tiempo me ha funcionado a la perfección, incluso con su gráfica me fue suficiente para un entorno de escritorio [GNOME][gnome] con [GNU][gnu]/[Linux][linux] y he acabado muy contento con la marca [Sony][sony] al contrario de mi anterior portátil [Dell] XPS 1530 de la que no pienso comprar nunca nada de esta marca, quizá tuve mala suerte pero el portátil Dell no fue barato, no acabé contento con la calidad del producto pasado el encantamiento inicial y al final acabó totalmente muerto sin ni siquiera encenderse.

Con lo que desde hace unos meses he empezado a barajar [opciones para comprar equipo][blogbitix-309] con los requisitos que tengo de ser lo más pequeño posible, silencioso, con 32 GiB de memoria, procesador con mínimo 4 cores y 8 hilos, no necesito tarjeta gráfica dedicada ya que no jugaré a juegos o una integrada con bajar calidad de detalles me servirá a modo ocasional. También quería una pantalla externa de 25" o 27" con resolución de 2K para mayor comodidad y evitar problemas por estar con la cabeza medio agachada durante bastante tiempo. Habiendo pasado 5 años desde mi anterior portátil Sony quiero que el ordenador sea un salto significativo en lo que tenía por eso el requerimiento de los 4 cores, los 32 GiB de memoria y la pantalla 2K. La memoria a pesar del caro precio de la DDR4 en el momento de este artículo. El SSD es la mejor inversión para mejorar el rendimiento que uno puede hacer, más que el procesador. El SSD probablemente elija uno en formato M.2 con interfaz NVMe que tienen una tasa de transferencia 3 veces superior a los SSD con interfaz SATA que ya son rápidos, los NVMe son algo más caros pero no mucho más.

He barajado varias opciones principalmente ordenadores en factor de forma pequeños como los [Intel NUC][intel-nuc], [Gigabyte Brix](http://es.gigabyte.com/products/select/desktop_platform/Mini-PcBarebone), [Zotac](https://www.zotac.com/es/product/mini_pcs/overview), [MSI Cubi](https://www.msi.com/Desktops/#?tag=Pro-Series), [Slimbook ONE][slimbook-one], [Slimbook PRO2][slimbook-pro2] o [AMD Ryzen][amd-ryzen] montando un [mini-ITX](https://es.wikipedia.org/wiki/Mini-ITX). Ya estoy casi decidido a lo que compraré y no quiero esperar mucho más a tener equipo propio de cuando se comercialicen y aunque estoy aguantando con un Mac del trabajo comentando [Tu con tu macOS yo con mi GNU/Linux][blogbitix-305]. Será con mucha probabilidad un Intel NUC Bean Canyon que se lanzarán en la segunda mitad del 2018.

A pesar de saber que todos los procesadores Intel de la última década está afectados por los [importantes problemas de seguridad Meltdown y Spectre][blogbitix-293] que se están mitigando por software, de momento no se ha descubierto una forma de aprovecharlos pero quizá en un futuro se pueda y afecte de forma importante al rendimiento para corregirlos si es que se puede. Pero es que el resto de opciones he ido descartando por otros motivos.

<div class="media" style="text-align: center;">
    <figure>
        <img src="assets/images/posts/2018/329/meltdown.svg" alt="Meltdown" title="Meltdown" width="200" height="200"/>
        <img src="assets/images/posts/2018/329/spectre.svg" alt="Spectre" title="Spectre" width="200" height="200"/>
        <figcaption>Logotipos de Meltdown y Spectre</figcaption>
    </figure>
</div>

El precio que calculo tendrá el Intel NUC Bean Canyon con procesador [Intel i5 8250U](https://ark.intel.com/es-es/products/124967/Intel-Core-i5-8250U-Processor-6M-Cache-up-to-3_40-GHz) (4 núcleos, 8 hilos, 6 MiB cache) estará en el momento de su salida entre los 350 y 450€ sin incluir la memoria y el SSD, respecto al modelo con el [Intel i3 7100U](https://ark.intel.com/es-es/products/95442/Intel-Core-i3-7100U-Processor-3M-Cache-2_40-GHz) (2 núcleos, 4 hilos, 3 MiB cache) que cuesta unos 250€ se puede dudar si esos dos núcleos adicionales y 3 MiB de caché más en el procesador merecen la pena la diferencia de precio. En mi caso estoy estoy dispuesto a pagarlo queriendo tener algo significativamente con mejor rendimiento teórico que lo que tenía.

He revisado algunos análisis de los NUC y me han gustado, en este por ejemplo se explica cómo instalar la memoria, el SSD, como es la BIOS/UEFI y sus opciones de configuración como por ejemplo el _ring_ que puede mostrar la actividad del SSD y se pueden personalizar los colores, también muestra el rendimiento en algunos juegos. Y en otro la gráfica HD 620 es suficiente para jugar a Diablo 3 a 30 fotogramas por segundo en un resolución de 1080p bajando detalles, esta gráfica integrada equivale a una dedicada NVIDIA 920m o GeForce 730 que no está del todo mal para un jugador ocasional si no importa jugar bajando algunos detalles de calidad. En GNU/Linux es posible jugar a juegos destinados a Windows con [PlayOnLinux](https://www.playonlinux.com/en/).

<div class="media media-video" style="text-align: center;">
	<iframe width="640" height="360" src="https://www.youtube.com/embed/GBCI3Xg3b9g" frameborder="0" allowfullscreen></iframe>
</div>
<div class="media media-video" style="text-align: center;">
	<iframe width="640" height="360" src="https://www.youtube.com/embed/su06vU8X1V8" frameborder="0" allowfullscreen></iframe>
</div>

El uso que le daré es el de programación, virtualización, contenedores [Docker][docker], navegación, ofimática, leer PDF, tal vez algún juego ocasional, quizá Diablo 3 si pudiese, y usando GNU/Linux con la distribución [Arch Linux][archlinux]. En un futuro lo quiero como equipo servidor dedicado personal con por ejemplo [Nextcloud][nextcloud].

Los Gigabyte Brix ya hay algunos modelos con los Intel i5 8250U a un precio de unos 440€, sin embargo, no me gusta que no tienen luz indicador de actividad de SSD y creo que su BIOS/UEFI es en modo texto. La luz de actividad de SSD me parece importante para saber si en algún momento el ordenador se queda bloqueado o por algún tipo de problema lee, o peor escribe, constantemente al disco duro cuando no debería. Los Zotac están dotados de una buena cantidad de puertos USB, incluso algunos tiene dos conexiones de red pero son algo caros y la mayoría de sus modelos están orientados a _gamers_, no son fáciles de encontrar ni siquiera en [Amazon][amazon] ni en [PC Componentes][pccomponentes] que es donde compraré finalmente el equipo. De MSI Cubi no hay muchos modelos y aún no he visto ninguno anunciado con un Intel de 4 núcleos. Los Slimbook ONE me gustaban pero los modelos con los Intel de 4 núcleos solo tienen una ranura de memoria, se les pueden instalar 32 GiB... cuando haya módulos de memoria de 32 GiB, ahora me tendría que conformar con 16 GiB.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B07D8ZK6QB&linkId=84f61ad5974ea5bb1b1c0d644818349a"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01N6SG8DP&linkId=d2208cc6633acfc885c4f2f7ef94c7a0"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B07CZ6GMRB&linkId=6c7ae73f4dc54a1da316d1b5292ca18d"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B019FRBHZ0&linkId=6353b999569a8b0acfdc24bb1a9e4532"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B07CGGNX7S&linkId=f2af5cb1f93ced34794cb7a11675b810"></iframe>
</div>

También he seguido los NUC con un Intel Core i3 7100U sin embargo tiene solo 2 cores y 4 hilos con 3 MiB de memoria será algo mejor que el Intel i3 3210M que tenía pero no me parece una mejora significativa habiendo pasado 5 años y a pesar de que el 7100U está fabricado a 14 nanómetros y el 3210M a 22 nanómetros, los NUC con [Intel i7 8650U](https://ark.intel.com/es/products/124968/Intel-Core-i7-8650U-Processor-8M-Cache-up-to-4_20-GHz) son más caros, sobre los 580€ y teniendo en cuenta que la memoria y SSD hay que comprarlos aparte. Los NUC más económicos sobre los 175€ con [Intel Pentium J5005](https://ark.intel.com/es/products/128984/Intel-Pentium-Silver-Processor-J5005-4M-Cache-up-to-2_80-GHz) solo admiten 8 gigas como máximo y me parece que están limitados artificialmente a ese tamaño máximo de memoria. En [Aliexpress][aliexpress] hay productos similares a los NUC con procesador i5 8250U pero a ver quien se la juega a importar desde China por aduanas y con la incertidumbre de lo que llega y que tal funciona, también he estado mirando placas [ARM][arm] aunque no con el objetivo que sea mi equipo principal, [7+ computadoras baratas del tamaño de una tarjeta de crédito basadas en GNU/Linux y ARM][blogbitix-304], como la [Rock64][rock64] que tiene 4 gigas pero siguiendo su desarrollo y foros no son pocos los problemas que la gente crea en su repositorio de [Github](https://github.com/ayufan-rock64/linux-build) con lo que me da que pensar que la experiencia podría ser algo frustrante, la arquitectura x86/x64 es mucho más compatible, en ARM cada placa es un mundo aparte de que su rendimiento es mucho más limitado.

Preferiría un microprocesador [AMD][amd] ya que solo están afectados por algunas variantes de _Spectre_, pero AMD no tiene tanta diversidad de productos como tiene Intel, no tienen nada parecido a los Intel NUC y en portátiles prácticamente todos los modelos equipan un Intel, quizá cambia ahora algo con los Ryzen. Por rendimiento y precio posiblemente elegiría un [AMD Ryzen 2400G](https://www.amd.com/es/products/apu/amd-ryzen-5-2400g) pero de momento las placas con el _chipset_ B450 para sacarle el máximo rendimiento aún no están disponibles, en la generación 1xxx de los Ryzen no hay modelos con gráfica integrada, tendría que ser un mini-ITX y el equipo me lo tendría que montar yo, no es difícil pero si tuviese algún imprevisto o incompatibilidad sería un problema por tener que devolver piezas y buscar otras, además no estoy seguro del ruido que haría, lo quiero muy silencioso al menos en tareas ofimáticas, navegación por internet y cosas simples, no estoy seguro de que lo sea con el ventilador de la CPU y fuente de alimentación. Ocupa más que un NUC para mi importante si en un futuro le quiero dar uso como ordenador secundario, servidor personal o quisiera traspasarlo. Pero por 400€ se tiene un ordenador muy decente en plataforma AMD Ryzen a falta de memoria y SSD.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B077BZRZVL&linkId=a905704fcfef9ed2713a34d3ddb555fa"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B079D8FD28&linkId=143ee2d24bf0649b2d0affddf13f24cb"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B073R8DB3G&linkId=5f0cd76fff30430ed354ec67845802e8"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B015UDUAKG&linkId=08b14b0134d00cc43b35b907b2815c0a"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B019NWTCEA&linkId=21cd29a0ba90015364a242ae10b47284"></iframe>
</div>

La pantalla que estoy siguiendo es una 2K de [BenQ PD2700Q](https://amzn.to/2ltsRhf) en tamaño de 27”, 4K me parece excesiva resolución para ese tamaño de pantalla, a su máxima resolución en una 27" 4K las cosas ven muy pequeñas y tendría que escalarla a 2K, busco que sea IPS ya que los colores en este tipo de paneles se ven significativamente mejor que en un panel TN, uno al lado del otro la diferencia es clara. Apreciaré una mejora notable en la calidad de la pantalla de la que tenía en el portátil Sony. No es barata pero es algo que no necesitan renovarse tan a menudo como el ordenador seguramente pueda tener un uso durante una década si no presenta ningún problema de funcionamiento. Otros aspectos interesantes son ser regulable en altura y horizontalmente, colocable en posición vertical y puertos USB, HDMI y DisplayPort por otro lado una información en pantalla o OSD aceptable y de fácil configuración.

Entre otras cosas están el teclado y ratón que me conformo con un modelo básico y barato como el [Logitech MK120](https://amzn.to/2yCcE2T) y una alfombrilla que cubra la mesa.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01K2210GS&linkId=1f32d07d228fd9c0eaaafcd1146b25d8"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B00564GWEI&linkId=e51cd8d969848df3bb23c36529afdc8e"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01HNYV65S&linkId=76bdcf7d3ff1a90eff397bc913a911d7"></iframe>
</div>

Cuando realmente compre el equipo publicaré varios artículos con el _unboxing_, instalación, análisis, uso y opinión personal de varios de ellos.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Intel NUC Roadmap 2018 – 2019](https://www.cnx-software.com/2017/09/20/intel-nuc-roadmap-2018-2019-gemini-lake-coffee-lake-and-kaby-lake-h/)
* [Intel HD Graphics - Nvidia and AMD Equivalents](https://www.pvladov.com/2015/07/intel-hd-graphics-nvidia-and-amd-equivalents.html)
* [Games Playable on Intel HD Graphics](https://www.pvladov.com/2014/10/games-playable-on-intel-hd-graphics.html)
* [How to Install Diablo 3 on Linux](https://news.softpedia.com/news/How-to-Install-Diablo-3-on-Linux-273950.shtml)
* [Intel NUC NUC7i7DNKE Intel Core i7-8650U](https://www.pccomponentes.com/barebone-intel-nuc-nuc7i7dnke-intel-core-i7-8650u)
* [Intel NUC NUC7PJYH2 Intel Pentium Silver J5005](https://www.pccomponentes.com/barebone-intel-nuc-nuc7pjyh2-intel-pentium-silver-j5005)
{{% /reference %}}

{{% /post %}}
