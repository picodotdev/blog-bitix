---
pid: 671
type: "post"
title: "Los juegos de Epic Games y GOG en GNU/Linux con el lanzador Heroic Games Laucher"
url: "/2023/01/los-juegos-de-epic-games-y-gog-en-gnu-linux-con-el-lanzador-heroic-games-laucher/"
aliases: ["/2023/01/los-juegos-de-epic-games-en-gnu-linux-con-el-lanzador-heroic-games-laucher/"]
date: 2023-01-26T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:heroic-games-launcher-1.webp"
imagePost: "image:heroic-games-launcher-1.webp"
tags: ["gnu-linux", "juegos", "planeta-codigo"]
series: ["juegos"]
summary: "Que aún GNU/Linux es un sistema minoritario y lo seguirá siendo es una realidad pero que en GNU/Linux no se puede jugar a los mismos juegos de Windows es ya un mito. El cliente de Steam para GNU/Linux da acceso al amplio catálogo de juegos de esta tienda donde la mayoría de juegos se puede jugar. En este artículo muestro como instalar y usar el cliente Heroic Games Launcher para las tiendas Epic Games y GOG que combinado con los juegos que regala semanalmente la Epic Games da acceso a un buen catálogo de juegos."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

El principal motivo por el que [Windows][windows] sigue siendo el sistema operativo más usado en el escritorio son en realidad dos: que Windows viene preinstalado en las computadoras que se venden en las grandes superficies y que a la mayoría de la gente el sistema operativo que usa le da igual mientras pueda realizar unas pocas tareas ofimáticas.

El siguiente motivo por el que algunos de los usuarios siguen usando Windows aún conociendo [GNU][gnu]/[Linux][linux] son los juegos. Dado el número de usuarios de Windows la mayoría de juegos se desarrollan para este sistema operativo.

Sin embargo, en la actualidad hay muchas opciones para poder jugar en GNU/Linux tan bien como en Windows. Quizá las excepciones sean los software que detectan trampas o _anti-cheat_ que pueden detectar GNU/Linux como algo extraño. Desde hace ya tiempo hay muchos juegos y opciones de juegos en GNU/Linux, y juegos triple AAA comerciales.

El cliente de [Steam][steam] es compatible con GNU/Linux y la empresa [Valve][valve] que lo desarrolla ha hecho grandes esfuerzos para mejorar la compatibilidad y ejecución de los juegos que han sido diseñados para Windows que puedan jugarse en GNU/Linux con similar rendimiento. La consola portátil [Steam Deck][steamdeck] de hecho usa un sistema operativo basado en GNU/Linux.

* [La aplicación cliente de Steam en GNU/Linux][blogbitix-431]

Por otro lado, los controladores gráficos de las tarjetas gráficas de [AMD][amd] e [Intel][intel] y las APIs gráficas como [OpenGL][opengl] y [Vulkan][vulkan] no están muy detrás de la API gráfica DirectX en Windows. En GNU/Linux los juegos diseñados para Windows que utilizan DirectX se traducen a las APIs gráficas OpenGL y Vulkan que soporta GNU/Linux con un rendimiento muy similar que el nativo en Windows.

Es posible jugar a juegos de Windows en GNU/Linux gracias a [Wine][wine] que traduce las APIs de los juegos de Windows a las APIs de GNI/Linux. Gracias a Wine pude jugar al [Diablo 3][blizzard-diablo3] de [Blizzard][blizzard].

* [Cómo usar Wine para jugar en GNU/Linux a juegos de Windows][blogbitix-364]

{{< tableofcontents >}}

## Juegos gratuitos de la Epic Games

Desde hace mucho tiempo la [Epic Games][epicgames] regala semanalmente al menos un juego de forma gratuita que se puede reclamar para incorporarlo a la biblioteca digital de esta tienda de juegos. Los juegos regalados son de diferentes géneros con lo que al cabo del tiempo reclamando aquellos en los que se está interesado se forma una colección de juegos interesante.

Algunos juegos de los gratuitos son minoritarios o que ya pasados más de dos años se consideran antiguos no tanto porque tengan mucho tiempo sino porque en ese periodo hasta hoy han sido publicados muchos otros.

Aún así entre todos los que regala de vez en cuando hay algunos juegos que son triple AAA que son de lo mejor, por mencionar algunos de la biblioteca digital de la Epic que tengo están _Control_, _A plague Tale: Innocence_, _Death Stranding_, _Frost Punk_, _For the King_, la trilogía de _Tomb Raider_, _XCOM 2_ y muchos otros muy reconocidos.

* [El juego XCOM 2 y guía de estrategia][blogbitix-459]
* [Análisis y guía del juego de estrategia Frostpunk][blogbitix-482]
* [Análisis y guía del juego de rol For the King][blogbitix-543]

Para jugar en GNU/Linux a los juegos de la Epic Games se necesita una forma de instalarlos y lanzarlos.

## Lanzador de juegos Heroic Games Launcher

Steam tiene un cliente para la instalación y ejecución de los juegos desarrollado específicamente para GNU/Linux usando Wine y Proton, la versión de Wine desarrollada por Steam. La Epic Games no tiene un cliente oficial ni se preocupa por GNU/Linux con lo que hay que recurrir a lanzadores desarrollados por personas de la comunidad de GNU/Linux.

El cliente o lanzador permite mostrar el catálogo de juegos que se posee de la tienda, comprados o los regalados conseguidos gratuitamente. También permiten descargar e instalar los juegos fácilmente y finalmente ejecutarlos para empezar a jugar.

El lanzador [Heroic Games Launcher][heroicgameslauncher] es un cliente compatible con GNU/Linux y las tiendas de la Epic Games y [GOG][gog]. El cliente tiene una versión de aplicación en formato [Flatpak][flatpak] con lo que su instalación es muy sencilla y compatible con cualquier distribución de GNU/Linux con un simple clic en la tienda de Software de GNOME.

* [Flatpak, distribución e instalación de programas de escritorio en las distribuciones GNU/Linux][blogbitix-362]
* [Flatpak Heroic Games Launcher](https://flathub.org/apps/details/com.heroicgameslauncher.hgl)

{{< image
    gallery="true"
    image1="image:gnome-software.webp" optionsthumb1="300x200" title1="Software de GNOME"
    caption="Software de GNOME" >}}

Una vez instalado el cliente es necesario descargar e instalar una versión de Wine o Proton que también es muy fácil de hacer con un simple clic.

{{< image
    gallery="true"
    image1="image:heroic-games-launcher-1.webp" optionsthumb1="300x200" title1="Heroic Games Launcher"
    image2="image:heroic-games-launcher-2.webp" optionsthumb2="300x200" title2="Heroic Games Launcher"
    caption="Heroic Games Launcher" >}}

Hay que iniciar sesión con las credenciales de la Epic Games, GOG o ambas tras lo cual aparecerán los juegos de la biblioteca. Instalar un juego de los que se poseen nuevamente simplemente requiere un clic teniendo en cuenta que se tenga suficiente espacio disponible de almacenamiento y una tarjeta gráfica que permita jugar al juego de forma fluida.

{{< image
    gallery="true"
    image1="image:heroic-games-launcher-3.webp" optionsthumb1="300x200" title1="Juego de Epic Games con Heroic Games Launcher"
    image2="image:heroic-games-launcher-4.webp" optionsthumb2="300x200" title2="Juego de Epic Games con Heroic Games Launcher"
    image3="image:heroic-games-launcher-5.webp" optionsthumb3="300x200" title3="Juego de Epic Games con Heroic Games Launcher"
    caption="Juego de Epic Games con Heroic Games Launcher" >}}

## Equipo para jugar en GNU/Linux

Muchos de los juegos requieren un equipo potente siendo el componente principal la tarjeta gráfica. Hasta hace poco había menos opciones si se quería jugar en GNU/Linux, por un lado los controladores de [NVIDIA][nvidia] son privativos y su uso en GNU/Linux requiere su instalación lo puede dar lugar a algunos problemas. Intel siempre ha dado buen soporte a GNU/Linux en sus controladores gráficos pero hasta la comercialización de las gráficas [Intel Arc][intel-arc] dedicadas a finales del 2022 solo tenía gráficas integradas de bajo rendimiento para los juegos. Por la parte de AMD que si tenía ya gráficas dedicadas [Radeon][amd-radeon] ha empezado a desarrollar controladores gráficos de código abierto con lo que su uso es sencillo y en general sin ningún problema ni con un _kernel_ reciente de Linux. Para Intel y AMD no hace falta instalar los controladores al contrario de los de NVIDIA.

Con la oferta de estas tres empresas, Intel, AMD y NVIDIA la recomendación que hago es descartar a NVIDIA y optar alguna de la gráficas dedicadas de Intel o AMD por su controladores de código abierto que están mejor soportados en GNU/Linux que los de NVIDIA para evitar problemas.

Si la intención es jugar a juegos de forma asidua es necesario un equipo y gráfica dedicada, la gente de [Slimbook][slimbook] proporciona equipos compatibles con GNU/Linux con gráficas dedicadas de Intel y AMD potentes. El optar por una gráfica dedicada suele ser para un equipo grande de torre por necesitar espacio para la refrigeración.

Si se desea un equipo compacto pero con la posibilidad de juegos están los Intel NUC como _Serpent Canyon_ que a pesar de su pequeño tamaño llevan una Intel Arc A770. También hay equipos con procesador AMD y gráfica integrada Radeon más capaces que las integradas de Intel con un formato similar a los Intel NUC.

Hace ya unos años compré un Intel NUC _Bean Canyon_ y para jugar una [PlayStation 4][playstation-4] teniendo el lanzador de Heroic y Steam posiblemente en un futuro quizá mire un equipo como los anteriores que siguiendo siendo compactos tenga una gŕafica más potente que la integrada de Intel como el _Serpent Canyon_ o el parecido que haya en el futuro.

* [Desempaquetado Intel NUC8i5BEK (Bean Canyon), HyperX Impact (RAM) y Samsung 970 EVO NVMe (SSD)][blogbitix-363]
* [Desempaquetado de PlayStation 4 Slim de 1 TB][blogbitix-432]

## Otras opciones de juegos en GNU/Linux

Más allá de los juegos triple AAA hay muchos usuarios que siguen disfrutando de juegos retro con [Mame][mame] o lanzadores de juegos de arcade y máquinas recreativas como [RetroArch][retroarch]. O los míticos juegos de _point and clic_ como _Monkey Island_, _Maniac Mansion_ o _Day of the Tentacle_.

* [Consola de juegos retro con Lakka y una Raspberry Pi][blogbitix-301]
* [Jugar a videojuegos clásicos y míticos de arcade con Mame en GNU/Linux][blogbitix-170]
* [Varios juegos de culto con ScummVM en GNU/Linux][blogbitix-173]

El entorno de escritorio GNOME también proporciona unos pocos juegos en el apartado de pasatiempo solitarios para lo que no es necesario un equipo potente y que con una gráfica integrada funcionan perfectamente.

* [El entorno de escritorio GNOME, simple, elegante y completo][blogbitix-660]
* [Juegos incluidos en el entorno de escritorio GNOME][blogbitix-167]

También hay algunos juegos gratuitos como el _Transport Tycoon_ al que hace ya muchos años dedique una enorme cantidad de tiempo.

* [22+ buenos juegos en GNU/Linux][blogbitix-172]

{{< reference >}}
* [Juegos de Epic Games en Linux | Heroic games launcher](https://www.youtube.com/watch?v=lgAGPhHYVNM)
* [Juegos de Windows en Linux | Steam Play](https://www.youtube.com/watch?v=2ROg_aTWGV8)
* [Juegos de bajos recursos y open source](https://www.youtube.com/watch?v=erphUB2Mf4Q)
{{< /reference >}}

{{% /post %}}