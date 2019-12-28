---
pid: 431
title: "La aplicación cliente de Steam en GNU/Linux"
url: "/2019/09/la-aplicacion-cliente-de-steam-en-gnu-linux/"
aliases: ["/2019/09/como-instalar-el-cliente-de-steam-en-gnu-linux/"]
date: 2019-09-06T18:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["gnu-linux", "planeta-codigo"]
series: ["juegos"]
summary: "Durante unos años he estado sin jugar a juegos porque al haberme pasado a GNU/Linux creía que no se podía al menos a los juegos comerciales actuales, en GNU/Linux hay algunos juegos que funcionan perfectamente como juegos retro con RetroArch, SuperTux, OpenTTD o Battle for Wesnoth en entre algunos de diversos géneros. Pero la realidad es que con Wine y el cliente de Steam son dos posibilidades para jugar a algunos perfectamente y tener un catálogo que no llega a la altura de Windows pero bastante amplio."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="steam.svg" title1="Steam" width1="200" image2="linux.svg" title2="Linux" width2="200" >}}

La plataforma de juegos [Steam][steam] es una de las más populares. Con su cliente de juegos es posible comprar, descargar e instalar juegos para tanto para Windows, macOS y también para [GNU][gnu]/[Linux][linux]. Steam y [Valve][valve] han posibilitado que se puedan jugar a varios juegos triple A en GNU/Linux, Valve se está esforzando para que muchos juegos sean jugables en la plataforma del pingüino.

Steam ofrece un cliente nativo para GNU/Linux que en Arch Linux se ofrece en los repositorios oficiales. Requiere además habilitar el repositorio _multilib_ para paquetes de 32 bits e instalar el controlador gráfico de la tarjeta gráfica de 32 bits.

{{< code file="pacman-steam.sh" language="bash" options="" >}}

Para habilitar el repositorio _multilib_ en Arch Linux hay que editar el archivo de configuración _/etc/pacman.conf_ y descomentar varias líneas quitando el caracter _#_ que las precede.

{{< code file="pacman.conf" language="bash" options="" >}}

En el caso de un gráfica Intel hay que instalar el paquete lib32-mesa.

{{< code file="pacman-lib32-mesa.sh" language="bash" options="" >}}

Hecho esto se puede iniciar el cliente, en el menú de aplicaciones del sistema aparecen su acceso directo de aplicación, se necesita una cuenta por que lo que hay que registrarse antes desde su plataforma web. Los juegos de la biblioteca aparecen en el cliente para instalar. En este caso tengo el juego [Company of Heroes 2](https://store.steampowered.com/app/231430/Company_of_Heroes_2/).

<div class="media">
    {{< figureproc
        image1="cliente-steam.png" options1="2560x1440" optionsthumb1="300x200" title1="Cliente de Steam en GNU/Linux"
        image2="cliente-steam-juegos.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Cliente de Steam en GNU/Linux"
        caption="Cliente de Steam en GNU/Linux" >}}
</div>

Instalado el juego se puede iniciar desde el cliente de Steam. Aún con una gráfica integrada Intel de un [Intel NUC8i5BEK (Bean Canyon)][blogbitix-363] se pueden jugar a muchos juegos bajando algo los detalles y resolución.

<div class="media">
    {{< figureproc
        image1="company-of-heroes-2-1.jpg" options1="2560x1440" optionsthumb1="300x200" title1="Company of Heroes 2"
        image2="company-of-heroes-2-2.jpg" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Cliente de Steam en GNU/Linux"
        caption="Company of Heroes 2" >}}
</div>

Los juegos ofrecen diferente nivel de soporte para GNU/Linux, para [Windows][windows], [macOS][macOS] y Steam+Linux, los que deben funcionar sin problema son los de la última categoría. Los de Windows pueden funcionar a través de una capa de emulación desarrollada por Steam, para activarla hay que ir a _Steam > Parámetros > Steam Play_ y activar las opciones _Enable Steam Play for Supported title_ y _Activar Steam para todos los demás títulos_. Sin embargo, no hay garantía que los juegos con solo soporte de Windows funcionen correctamente, he probado con [World of Warships](https://store.steampowered.com/app/552990/World_of_Warships/) y no se iniciaba correctamente mostrando únicamente una pequeña ventana negra.

<div class="media">
    {{< figureproc
        image1="steam-play-settings.png" options1="2560x1440" optionsthumb1="300x200" title1="Steam Play Settings"
        caption="Steam Play Settings" >}}
</div>

De vez en cuando Steam ofrece juegos gratuitos que se pueden añadir libremente a la biblioteca y en ocasiones ofrece grandes descuentos. También a través de su propio cliente es posible [jugar a los juegos de Blizzard como Diablo 3][blogbitix-364], aunque en este caso es necesario utilizar [Wine][wine].

{{% /post %}}
