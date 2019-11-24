---
pid: 163
title: "Jugar al clásico de estrategia de tablero Go en GNU/Linux"
url: "/2016/07/jugar-al-clasico-de-estrategia-de-tablero-go-en-gnu-linux/"
date: 2016-07-23T13:00:00+02:00
rss: true
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "gnu-linux", "planeta-linux", "software-libre"]
series: ["juegos"]
summary: "En esta serie de artículos sobre juegos vamos a ver que en GNU/Linux también hay juegos a los que podemos jugar en nuestra plataforma preferida. Otro de ellos es el clásico de estrategia Go."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="linux.svg" title1="Linux" width1="200" image2="gnu.svg" title2="GNU" width2="200" >}}

Otro de los juegos clásicos junto con el ajedrez también para dos personas que requiere pensar y evaluar gran cantidad de posibles movimientos es el juego de origen chino Go. En [GNU][gnu]/[Linux][linux] e instalado el paquete [gnugo](https://www.archlinux.org/packages/extra/x86_64/gnugo/) podremos jugar a este antiguo juego de estrategia tan o más complicado que el propio ajedrez por las diferentes estrategias que se pueden desarrollar.

{{< code file="pacman.sh" language="bash" options="" >}}

Dado que en esta versión para computadora el juego Go se basa en modo texto podremos jugarlo en cualquier sistema GNU/Linux dados su bajos requerimientos que son despreciables para cualquier computadora actual, incluso para una [Raspberry Pi][raspberrypi] u otros sistemas como la familia [BSD][bsd] e incluso [Minix][minix].

El tablero clásico se compone de una matriz de 19 por 19 en el que en las intersecciones se irán colocando las fichas de forma alternativa entre cada jugador tratando de capturar las fichas del oponente rodeándolas con las que nosotros pongamos o de dominar la mayor cantidad del tablero. Cuando se da el juego por terminado se realiza el cálculo del marcador según el territorio dominado por las fichas y las piezas capturadas del oponente en el transcurso del mismo.

En el juego de computadora el siguiente movimiento se hace introduciendo la coordenada del tablero donde queremos colocar la siguiente piedra que se compone de una letra para la coordenada horizontal y un número para la coordenada vertical.

<div class="media" style="text-align: center;">
    {{< figure
        image1="tablero-go.jpg" thumb1="tablero-go-thumb.jpg" title1="Tablero tradicional de Go"
        image2="tablero-gnugo.png" thumb2="tablero-gnugo-thumb.png" title2="Tablero de Gnugo"
        caption="Tablero tradicional de juego y tablero de Gnugo" >}}
</div>

En la wikipedia está documentado de forma bastante extensa las [reglas del juego Go](https://es.wikipedia.org/wiki/Go#Reglas_del_go), las diferentes formas de [calcular el marcador al final del juego](https://es.wikipedia.org/wiki/Go#Puntuaci.C3.B3n) y las [tácticas](https://es.wikipedia.org/wiki/Go#T.C3.A1ctica) y [estrategias](https://es.wikipedia.org/wiki/Go#Estrategia_b.C3.A1sica) para jugar de la forma más efectiva que podamos.

Como una partida puede durar una buena cantidad de tiempo podemos salvar una partida a medias y cargarla de nuevo con los siguientes comandos:

{{< code file="save-commands.txt" language="plaintext" options="" >}}

Con el comando <code>help</code> obtendremos una lista completa de los comandos a nuestra disposición como volver un movimiento hacia atrás si nos hemos equivocado al introducir el siguiente movimiento.

{{< code file="help.out" language="plaintext" options="" >}}

En Amazon hay algunos libros como [El Go, un juego oriental milenario](https://amzn.to/2anENOU) y [GO para principiantes](https://amzn.to/29ULTWQ), en la librería libre también hay algunos como [Manual del Juego del Go](https://openlibra.com/es/book/manual-del-juego-del-go) y [GO: Utilizando las 36 estrategias chinas](https://openlibra.com/es/book/go-utilizando-las-36-estrategias-chinas).

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B015ENSU18&linkId=210d272021d815869d2c44e1503b904e&internal=1"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=8415211945&linkId=f7342910d70bf91cda078a1ce3abbbc0&internal=1"></iframe>
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Juego Go](https://en.wikipedia.org/wiki/Go_(game))
* [Rules of go](https://en.wikipedia.org/wiki/Rules_of_go)
* [GNU Go](https://www.gnu.org/software/gnugo/)
{{% /reference %}}

{{% /post %}}
