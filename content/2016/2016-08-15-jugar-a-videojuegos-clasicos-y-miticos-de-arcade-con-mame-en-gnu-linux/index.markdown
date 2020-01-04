---
pid: 170
title: "Jugar a videojuegos clásicos y míticos de arcade con Mame en GNU/Linux"
url: "/2016/08/jugar-a-videojuegos-clasicos-y-miticos-de-arcade-con-mame-en-gnu-linux/"
date: 2016-08-15T12:00:00+02:00
updated: 2016-08-15T23:00:00+02:00
rss: true
sharing: true
comments: true
language: "es"
tags: ["gnu-linux", "software-libre"]
series: ["juegos"]
---

{{% post %}}

{{< logotype image1="mame.jpg" title1="MAME" width1="200" image2="linux.svg" title2="Linux" width2="200" >}}

[GNU][gnu]/[Linux][linux] no es la plataforma preferida por las empresas de desarrollo de videojuegos para lanzar sus juegos triple A. Esto hace que los juegos más destacados no esté disponibles en GNU/Linux o sea con un tiempo de retraso notable de meses y años. Pero esto no quiere decir que en la plataforma del pingüino no haya juegos algunos que ya he comentado como el [ajedrez][blogbitix-160], [go][blogbitix-163], [juegos ofrecidos en el entorno del escritorio GNOME][blogbitix-167] y otros de diferentes géneros como estrategia, _arcade_ o simulación.

Los juegos actuales de las consolas y PC poseen una calidad gráfica muy notable y han evolucionado al juego en red con otros jugadores como <abbr title="Massively Multiplayer Online">MMO</abbr> siendo más sociales. Hace una décadas las máquinas de _arcade_ y las consolas más modestas como la [NES de Nintendo](https://es.wikipedia.org/wiki/Nintendo_Entertainment_System) o los juegos [Neo Geo](https://es.wikipedia.org/wiki/Neo-Geo) eran la norma y a pesar de que no se pueden comparar con el realismo de las tres dimensiones proporcionados por las potentes tarjetas gráficas en los juegos actuales su aspecto [pixel-art](https://es.wikipedia.org/wiki/Pixel_art) es muy notable pero sobre todo la diversión que proporcionan no tiene que envidiar a cualquier juego actual.

Juegos míticos como 1942, 1943, 1944, Ghouls & Ghosts, Double Dragon, Super Mario 3, Golden Axe, Pengo, Amidar, Donkey Kong, Metal Slug, Rainbow Islands, Track & Field, The Legend of Zelda, ... y me dejo muchísimos The NewZealand Story, Super Glob, Q*bert, Jump Bug, Bagman, ... que en los que andamos entre los 35 y 50 forman parte de nuestro recuerdos de infancia, solo hay que buscar en la [base de datos de juegos MAME](http://www.mamedb.com). Quizá sea por estos recuerdos que la comunidad de juegos retro sigue vigente hoy en día y que organiza eventos de encuentro para fans de las consolas viejas que no obsoletas e informática antigua. Quizá dentro de unas décadas los que hoy está en su infancia con la [Raspberry Pi][raspberrypi] dentro de unas décadas recuerden este pequeño ordenador de 40€ como hoy algunos consideran a la [MSX](https://es.wikipedia.org/wiki/MSX).

Voy a explicar como instalar [Mame][mame] para emular esos sistemas antiguos y con las ROMs de los juegos jugar a ellos en un sistema GNU/Linux. Si nos hacemos con un controlador de consola con la típica cruceta y cuatro o  más botones la experiencia será mejor que con el teclado y más real a la original de la máquina de _arcade_ o consola. El número de  [sistemas soportados por Mame](http://www.progettoemma.net/mess/sysset.php) llega a más de 2000 entre ellos [Amiga](https://en.wikipedia.org/wiki/Amiga), [Amstrad](https://en.wikipedia.org/wiki/Amstrad), [Atari](https://en.wikipedia.org/wiki/Atari), [Commodore](https://en.wikipedia.org/wiki/Commodore_International), [Famicon (NES)](https://es.wikipedia.org/wiki/Nintendo_Entertainment_System), [Master System](https://es.wikipedia.org/wiki/Master_System), [Neo Geo](https://es.wikipedia.org/wiki/Neo-Geo) o [ZX Spectrum](https://es.wikipedia.org/wiki/Sinclair_ZX_Spectrum).

Deberemos instalar el paquete del [emulador Mame](https://www.archlinux.org/packages/community/x86_64/mame/) y un lanzador que en este caso usaré [QMC2](https://www.archlinux.org/packages/community/x86_64/qmc2/) aunque como alternativa también está [RetroArch](https://wiki.archlinux.org/index.php/RetroArch) o [Lakka para una Raspberry Pi][blogbitix-301]. Posteriormente deberemos obtener las ROMs de los juegos de algunas de las muchas páginas que existen en internet. En [Arch Linux][archlinux] los paquetes del emulador Mame y el lanzador QMC2 los instalamos con el siguiente comando:

{{< code file="pacman.sh" language="bash" options="" >}}

Instalados estos paquetes y descargada una ROM de ejemplo como la de [Ghouls & Gosht](https://www.emuparadise.me/M.A.M.E._-_Multiple_Arcade_Machine_Emulator_ROMs\/Ghouls'n_Ghosts_(World)/13191) de [EMUPARADISE](http://www.emuparadise.me) y colocada en un directorio (no hace falta descomprimir el archivo de la ROM) iniciamos el lanzador QMC2. QMC2 inicialmente nos pedirá donde se encuentra el binario del emulador y el directorio donde están las ROMs. Configurado QMC2 buscaremos el juego por su nombre del que hayamos descargado la ROM.

{{< figureproc
    image1="qmc2.png" thumb1="qmc2-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Lanzador QMC2"
    image2="qmc2-config.png" thumb2="qmc2-config-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Configuración de QMC2"
    caption="Lanzador QMC2 de juegos MAME y su configuración" >}}

Si hay varios resultados para el mismo nombre de juego iremos probando a iniciarlos uno a uno hasta que se abra una ventana emergente con el juego. Las teclas por defecto del primer jugador son las flechas del teclado, la tecla <kbd>Alt-izquierda</kbd> para el botón 1, <kbd>Ctrl-izquierdo</kbd> para el botón 2 y la <kbd>barra espaciadora</kbd> para el botón 3. Iniciado sdlmame en la sección de _Input > Player 1_ podremos configurar las teclas. Las teclas <kbd>1</kbd> y <kbd>2</kbd> nos permitirá seleccionar entre uno y dos jugadores y la tecla <kbd>5</kbd> introducir monedas o créditos.

Esta es una pequeña lista no completa de los juegos míticos que recuerdo de mi época.

{{< figureproc
    image1="amidar.png" thumb1="amidar-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Amidar"
    image2="dark-seal.png" thumb2="dark-seal-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Dark Seal"
    caption="Amidar y Dark Seal" >}}
{{< figureproc
    image1="donkey-kong.png" thumb1="donkey-kong-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Donkey Kong"
    image2="donkey-kong-jr.png" thumb2="donkey-kong-jr-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Donkey Kong Jr."
    caption="Donkey Kong y Donkey Kong Jr." >}}
{{< figureproc
    image1="ghosts-and-goblins.png" thumb1="ghosts-and-goblins-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Ghosts and Goblins"
    image2="ghouls-and-ghosts.png" thumb2="ghouls-and-ghosts-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Ghouls and Ghosts"
    caption="Ghosts and Goblins y Ghouls and Ghosts" >}}
{{< figureproc
    image1="hyper-sports.png" thumb1="hyper-sports-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Hyper Sports"
    image2="metal-slug.png" thumb2="metal-slug-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Metal Slug"
    caption="Hyper Sports y Metal Slug" >}}
{{< figureproc
    image1="meikyu-jima.png" thumb1="meikyu-jima-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Meikyu Jima"
    image2="new-rally-x.png" thumb2="new-rally-x-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="New Rally X"
    caption="Meikyu Jima y New Rally X" >}}
{{< figureproc
    image1="pang.png" thumb1="pang-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Pang"
    image2="pengo.png" thumb2="pengo-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Pengo"
    caption="Pang y Pengo" >}}
{{< figureproc
    image1="puck-man.png" thumb1="puck-man-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Puck Man"
    image2="rainbow-islands.png" thumb2="rainbow-islands-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Rainbow Islands"
    caption="Puck Man y Rainbow Islands" >}}
{{< figureproc
    image1="rygar.png" thumb1="rygar-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Rygar"
    image2="squash.png" thumb2="squash-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Squash"
    caption="Rygar y Squash" >}}
{{< figureproc
    image1="super-mario-bros.png" thumb1="super-mario-bros-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Super Mario Bros"
    image2="super-mario-bros-3.png" thumb2="super-mario-bros-3-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Super Mario Bros 3"
    caption="Super Mario Bros y Super Mario Bros 3" >}}
{{< figureproc
    image1="super-volleyball.png" thumb1="super-volleyball-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Super Volleyball"
    image2="tetris.png" thumb2="tetris-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Tetris"
    caption="Super Volleyball y Tetris" >}}
{{< figureproc
    image1="toki.png" thumb1="toki-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Toki"
    image2="track-and-field.png" thumb2="track-and-field-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Track and Field"
    caption="Toki y Track and Field" >}}
{{< figureproc
    image1="traverse.png" thumb1="traverse-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Traverse"
    image2="willow.png" thumb2="willow-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Willow"
    caption="Traverse y Willow" >}}
{{< figureproc
    image1="wonder-boy.png" thumb1="wonder-boy-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Wonder Boy"
    image2="world-rally.png" thumb2="world-rally-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="World Rally"
    caption="Wonder Boy y World Rally" >}}

Ya solo nos queda buscar con tu buscador preferido algún artículo que recopile las mejores ROMs o aquellas que recuerdes y descargarlas en el directorio de las ROMS. Algunos emuladores deberemos previamente [descargar su ROM de emulador como en el caso de Neo-Geo](https://www.emuparadise.me/M.A.M.E._-_Multiple_Arcade_Machine_Emulator_ROMs/Neo-Geo/15030) para esta plataforma y colocarla también en el directorio de las ROMs.

Para jugar a los juegos de la NES de Nintendo con QMC2 no he conseguido ejecutarlos, he tenido que introducir el comando <code>sdlmame nes</code> en la terminal buscar la ROM navegando por los directorios, seleccionarla y usar la opción de _reset_.

{{< figureproc
    image1="sdlmame-nes.png" thumb1="sdlmame-nes-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="sdlmame NES"
    image2="sdlmame-nes-supermariobros3.png" thumb2="sdlmame-nes-supermariobros3-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="sdlmame Super Mario Bros 3"
    caption="Ejecución de un juego NES con sdlmame" >}}

Si no usamos Linux tanto Mame como QMC2 están disponibles tanto para [Windows][windows] como para [Mac OS X][macos] en sus secciones de descarga:

* https://www.mame.net/
* http://qmc2.batcom-it.net/

Incluso con una [Raspberry Pi][amazon-raspberrypi] podremos jugar usando como pantalla una televisión. Con un [mando inalámbrico de arcade](https://amzn.to/2bjiYN8) (no he probado si funcionará) tendremos una experiencia muy parecida a una consola. Instalando en la RPi una distribución como [Retropie](https://retropie.org.uk/) o [Libretro](https://www.libretro.com/) emularemos los juegos en este pequeño computador multipropósito.

{{< amazon
    link1="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01CD5VC92&linkId=a79bf1bb391f6591c039b7696a22e9ef&internal=1"
    link2="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B013B61SCS&linkId=61b4a105bc7d42ff174de961aaab0e67&internal=1" >}}

Ya solo nos queda disfrutar y pasar un buen rato con nuestros juegos preferidos, nada que envidiar en cuanto a diversión a una PlayStation 4 ;), ah y no ocupan 20 GiB sino menos de 1 MiB.

{{< reference >}}
* [Emuparadise](http://www.emuparadise.me)
* [MAME Database](http://www.mamedb.com)
* [Emuladores, ROMs y el debate entre la nostalgia, el amor a lo retro y la ilegalidad](https://www.xataka.com/videojuegos/emuladores-roms-y-el-debate-entre-la-nostalgia-el-amor-a-lo-retro-y-la-ilegalidad)
{{< /reference >}}

{{% /post %}}
