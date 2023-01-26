---
pid: 431
type: "post"
title: "La aplicación cliente de Steam en GNU/Linux"
url: "/2019/09/la-aplicacion-cliente-de-steam-en-gnu-linux/"
aliases: ["/2019/09/como-instalar-el-cliente-de-steam-en-gnu-linux/"]
date: 2019-09-06T18:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:cliente-steam.webp"
imagePost: "image:cliente-steam.webp"
tags: ["gnu-linux", "planeta-codigo"]
series: ["juegos"]
summary: "Durante unos años he estado sin jugar a juegos porque al haberme pasado a GNU/Linux creía que no se podía al menos a los juegos comerciales actuales, en GNU/Linux hay algunos juegos que funcionan perfectamente como juegos retro con RetroArch, SuperTux, OpenTTD o Battle for Wesnoth en entre algunos de diversos géneros. Pero la realidad es que con Wine y el cliente de Steam son dos posibilidades para jugar a algunos perfectamente y tener un catálogo que no llega a la altura de Windows pero bastante amplio."
---

{{% post %}}

{{< logotype image1="steam.svg" >}}

La plataforma de juegos [Steam][steam] es una de las más populares. Con su cliente de juegos es posible comprar, descargar e instalar juegos para tanto para [Windows][windows], [macOS][macos] y también para [GNU][gnu]/[Linux][linux]. Steam y [Valve][valve] han posibilitado que se puedan jugar a varios juegos triple A en GNU/Linux, Valve se está esforzando para que muchos juegos sean jugables en la plataforma del pingüino.

### Instalar el cliente de Steam en GNU/Linux

Aunque las distribuciones como Arch Linux ofrecen paquetes del cliente de Steam rquieren activar el repositorio _multilib_ para paquetes de 32 bits. Otra forma más sencilla de instalar el cliente de Steam e independiente de la distribución es utilizar la versión en formato [Flatpak de Steam](https://flathub.org/apps/details/com.valvesoftware.Steam). Usando el cliente de software de [GNOME][gnome] se hace de forma gráfica simplemente haciendo una búsqueda y un clic en el botón de instalar.

* [Flatpak, distribución e instalación de programas de escritorio en las distribuciones GNU/Linux][blogbitix-362]
* [El entorno de escritorio GNOME, simple, elegante y completo][blogbitix-660]

{{< image
    gallery="true"
    image1="image:gnome-software-steam.webp" optionsthumb1="300x200" title1="Aplicación de software de GNOME"
    caption="Aplicación de software de GNOME" >}}

### El cliente de Steam

Instalado el cliente se puede iniciar, en el menú de aplicaciones del sistema aparecen su acceso directo de aplicación, se necesita una cuenta por que lo que hay que registrarse antes desde su plataforma web. Los juegos de la biblioteca aparecen en el cliente para instalar. En este caso tengo el juego [Company of Heroes 2](https://store.steampowered.com/app/231430/Company_of_Heroes_2/).

{{< image
    gallery="true"
    image1="image:cliente-steam.webp" optionsthumb1="300x200" title1="Cliente de Steam en GNU/Linux"
    image2="image:cliente-steam-juegos.webp" optionsthumb2="300x200" title2="Cliente de Steam en GNU/Linux"
    caption="Cliente de Steam en GNU/Linux" >}}

Instalado el juego se puede iniciar desde el cliente de Steam. Aún con una gráfica integrada Intel de un [Intel NUC8i5BEK (Bean Canyon)][blogbitix-363] se pueden jugar a muchos juegos bajando algo los detalles y resolución.

{{< image
    gallery="true"
    image1="image:company-of-heroes-2-1.webp" optionsthumb1="300x200" title1="Company of Heroes 2"
    image2="image:company-of-heroes-2-2.webp" optionsthumb2="300x200" title2="Cliente de Steam en GNU/Linux"
    caption="Company of Heroes 2" >}}

Los juegos ofrecen diferente nivel de soporte para GNU/Linux, para Windows, macOS y Steam+Linux, los que deben funcionar sin problema son los de la última categoría. Los de Windows pueden funcionar a través de una capa de emulación desarrollada por Steam, para activarla hay que ir a _Steam > Parámetros > Steam Play_ y activar las opciones _Enable Steam Play for Supported title_ y _Activar Steam para todos los demás títulos_. Sin embargo, no hay garantía que los juegos con solo soporte de Windows funcionen correctamente, he probado con [World of Warships](https://store.steampowered.com/app/552990/World_of_Warships/) y no se iniciaba correctamente mostrando únicamente una pequeña ventana negra.

{{< image
    gallery="true"
    image1="image:steam-play-settings.webp" optionsthumb1="300x200" title1="Steam Play Settings"
    caption="Steam Play Settings" >}}

De vez en cuando Steam ofrece juegos gratuitos que se pueden añadir libremente a la biblioteca y en ocasiones ofrece grandes descuentos. También a través de su propio cliente es posible jugar a los juegos de [Blizzard][blizzard] como [Diablo 3][blizzard-diablo3], aunque en este caso es necesario utilizar [Wine][wine].

* [Cómo usar Wine para jugar en GNU/Linux a juegos de Windows][blogbitix-364]

### Un cliente para los juegos de Epic Games

La tienda alternativa a Steam es [Epic Games][epicgames] que aunque no tiene un cliente y lanzador de juegos oficial está [Heroic Games Launcher][heroicgameslauncher] también muy fácil de instalar como aplicación Flatpak.

* [Los juegos de Epic Games en GNU/Linux con el lanzador Heroic Games Laucher][blogbitix-671]

{{% /post %}}
