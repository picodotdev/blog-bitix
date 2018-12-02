---
pid: 79
title: "Programación de juegos y 3D en Java con jMonkeyEngine"
url: "/2015/05/programacion-de-juegos-y-3d-en-java-con-jmonkeyengine/"
date: 2015-05-09T10:18:40+02:00
updated: 2015-11-10T19:00:00+01:00
rss: true
sharing: true
comments: true
tags: ["java", "programacion", "blog-stack", "planeta-codigo", "planeta-linux"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="jmonkeyengine.png" title1="jMonkeyEngine" image2="java.svg" title2="Java" width2="200" >}}

Erróneamente se sigue pensado que Java es un lenguaje lento en ejecución, en las primeras versiones era cierto pero hoy la realidad es que con las mejoras introducidas en cada versión de Java y la máquina virtual el rendimiento actual es comparable a C y C++. En la programación de juegos y 3D gran parte del proceso de representación gráfica se ha descargado de la CPU a las cada vez más potentes tarjetas gráficas, la potencia de estas GPU son las que determinan la capacidad de proceso gráfico y la calidad gráfica de los juegos.

Java no suele ser considerado como opción para programar videojuegos triple A pero ahí está [Minecraft](https://minecraft.net/) uno de los juegos más populares y un ejemplo de que un juego de buena calidad y rendimiento también se puede hacer en Java. Hay algunos otros ejemplos notables como de variados estilos _RPG_, _Puzzle_, _MOBA_, _Rogue_, _RTS_, _Card MMOG_, _FPS_, _Arcade_, _Platform_ ,...:

* [4089: Ghost Within](http://store.steampowered.com/app/329770/)
* [Rising World](https://www.rising-world.net/)
* [Seizon](https://play.google.com/store/apps/details?id=com.rampage.seizon)
* [Copod](http://herebeben.com/copod)
* [Drohtin – Tales of an Old Kingdom](http://drohtin.org/)
* [CHAOS: In the Darkness](http://4realms.net/News/)
* [Just Tactics](http://www.indiedb.com/games/just-tactics/)
* [Gentrieve 2](https://gentrieve.wordpress.com/)
* [Spermination](http://steamcommunity.com/sharedfiles/filedetails/?id=354610327)
* [Herodex](http://www.indiedb.com/games/herodex)
* [Gentrieve 2](https://gentrieve.wordpress.com/)
* [Maker’s Tale](http://www.indiedb.com/games/makers-tale/videos)
* [3089](http://store.steampowered.com/app/263360/)
* [Boardtastic](http://boardtastic.com/)
* [PirateHell](http://store.steampowered.com/app/321080)
* [Mythruna](http://mythruna.com/)
* [Hostile Sector](http://mindemia.com/hostilesector/)
* [3079](http://store.steampowered.com/app/259620/)
* [TYGRON](http://www.tygron.com/)
* [Fleshsnatcher](http://sourceforge.net/projects/fleshsnatcher/)
* [Nord](http://nordgame.com/)
* [Urban Galaxy](https://www.urbangalaxyonline.com/)
* [Grappling Hook](http://ghook.speedrungames.com/)
* [Mad Skills Motocross](http://www.madskillsmx.com/)
* [Betaville](http://betaville.net/)
* [Script Blocks](http://scriptblocks.com/)
* [Windup Baseball](http://jmonkeyengine.org/project/windup-baseball/)
* [Pets vs Monsters](https://www.petsvsmonsters.com/)
* [Bang! Howdy](http://www.banghowdy.com/)
* [Imperii](http://jmonkeyengine.org/project/imperii/)

Este es un vídeo del juego PirateHell que tiene una pinta muy buena:

<div class="media" style="text-align: center;">
	<iframe width="560" height="315" src="https://www.youtube.com/embed/ODjq7IUkwUg" frameborder="0" allowfullscreen></iframe>
</div>

Algunas capturas de imagen de estos juegos, en los enlaces anteriores se pueden encontrar vídeos de algunos de ellos.

<div class="media" style="text-align: center;">
	{{< figure year="2015" pid="79"
    	image1="4089.jpg" thumb1="4089-thumb.jpg" title1="4089: Ghost Within"
    	image2="rising-world.jpg" thumb2="rising-world-thumb.jpg" title2="Rising World" >}}
	{{< figure year="2015" pid="79"
    	image1="seizon.jpg" thumb1="seizon-thumb.jpg" title1="Seizon"
    	image2="copod.png" thumb2="copod-thumb.png" title2="Copod" >}}
	{{< figure year="2015" pid="79"
    	image1="drohtin-tales-of-old-kingdom.png" thumb1="drohtin-tales-of-old-kingdom-thumb.png" title1="Drohtin – Tales of an Old Kingdom"
    	image2="chaos_in-the-darkness.jpg" thumb2="chaos_in-the-darkness-thumb.jpg" title2="CHAOS: In the Darkness" >}}
	{{< figure year="2015" pid="79"
    	image1="just-tactics.jpg" thumb1="just-tactics-thumb.jpg" title1="Just Tactics"
    	image2="spermination.png" thumb2="spermination-thumb.png" title2="Spermination" >}}
	{{< figure year="2015" pid="79"
    	image1="makers-tale.jpg" thumb1="makers-tale-thumb.jpg" title1="Maker’s Tale"
    	image2="piratehell.jpg" thumb2="piratehell-thumb.jpg" title2="PirateHell" >}}
	{{< figure year="2015" pid="79"
    	image1="hostile-sector.jpg" thumb1="hostile-sector-thumb.jpg" title1="Hostile Sector"
    	image2="3079.png" thumb2="3079-thumb.png" title2="3079" >}}
	{{< figure year="2015" pid="79"
    	image1="urban-galaxy.jpg" thumb1="urban-galaxy-thumb.jpg" title1="Urban Galaxy"
    	image2="grapplinghook.jpg" thumb2="grapplinghook-thumb.jpg" title2="Grappling Hook" >}}
	{{< figure year="2015" pid="79"
    	image1="mad-skills-motocross.jpg" thumb1="mad-skills-motocross-thumb.jpg" title1="Mad Skills Motocross"
    	image2="pets-vs-monsters.jpg" thumb2="pets-vs-monsters-thumb.jpg" title2="Pets vs Monsters" >}}
	{{< figure year="2015" pid="79"
    	image1="bang-howdy.jpg" thumb1="bang-howdy-thumb.jpg" title1="Bang! Howdy"
    	image2="imperii.jpg" thumb2="imperii-thumb.jpg" title2="Imperii" >}}
</div>

Todos estos juegos están programados utilizando el lenguaje de programación Java y la librería [jMonkeyEngine][jmonkeyengine] que facilita las tareas de programación de videojuegos proporcionando programación gráfica en 3D usando [OpenGL][opengl], manejo de eventos de entrada como teclado o ratón, manejo de sonido, pantallas de menús o red. Usando jMonkeyEngine se pueden hacer cosas muy interesantes como se ve en los ejemplos. En el siguiente enlace se pueden encontrar el [código fuente de varios ejemplos](https://github.com/jMonkeyEngine/BookSamples/tree/master/src) que podemos probar.

A continuación mostraré el código y unas capturas de pantalla de algunas las posibilidades de jMonkeyEngine. Primeramente veamos como crear un ejemplo básico con un cubo aplicándole una textura con el que veremos como crear una escena. El segundo ejemplo aplica texturas de diferentes formas que varían la visualización de un elemento geométrico.

{{< gist picodotdev 79d85db5b54dc4f15d81 "HolaMundoJMonkeyEngine.java" >}}
{{< gist picodotdev 79d85db5b54dc4f15d81 "Materiales.java" >}}

Se pueden crear objetos con texturas transparentes, efectos de luz, _ray casting_, sistemas de partículas con las que simular fuego, chispas, polvo, establecer animaciones a objetos como cuando un personaje está descansando, terrenos, paisajes, aplicar efectos simulando la física del mundo real, sonido ambiental y posicional y más cosas. En las siguientes imágenes se pueden ver algunos ejemplos de las anteriores posibilidades (la tasa de _fps_ normal es de 60, al tomar las capturas baja).

<div class="media" style="text-align: center;">
	{{< figure year="2015" pid="79"
    	image1="cubo.png" thumb1="cubo-thumb.png" title1="¡Hola mundo!"
    	image2="cubos.png" thumb2="cubos-thumb.png" title2="Texturas y materiales" >}}
	{{< figure year="2015" pid="79"
    	image1="particulas-fuego.png" thumb1="particulas-fuego-thumb.png" title1="Sistemas de partículas, fuego"
    	image2="agua.png" thumb2="agua-thumb.png" title2="Agua" >}}
</div>

Un videojuego se compone de múltiples recursos como imágenes, modelos 3D, música, _sprites_, texturas, fuentes de texto, sonidos, iconos... en la página [Open Game Art](http://opengameart.org) podemos encontrar todo este tipo de material sin necesidad de tener que crearlo desde la nada.

jMonkeyEngine ofrece un entorno de desarrollo (IDE) basado [NetBeans][netbeans]. Descargando el paquete de jMonkeyEngine y copiando las librerías .jar los programas se puede ejecutar perfectamente independientemente del IDE y desarrollar con [eclipse][eclipse] y usar la [herramienta de construcción gradle][gradle].

Para instalar jMonkeyEngine debemos [descargar el SDK](http://jmonkeyengine.org/downloads/) adecuado para la plataforma que usemos ya sea Windows, Linux o Macintosh. En el caso de Linux es un archivo .sh que deberemos ejecutar (dando permisos de ejecución si es necesario), seguimos las instrucciones y seleccionamos el directorio de instalación del SDK. En _jmonkeyplatform/libs_ de la carpeta de instalación encontramos los archivos .jar que deberemos usar en el IDE o en los programas de los ejemplos.

El <a href="http://www.amazon.es/gp/product/1849516464/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=1849516464&linkCode=as2&tag=blobit-21">libro jMonkeyEngine 3.0 Beginners Guide</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=1849516464" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;">
 me ha resultado muy interesante como punto de introducción a la programación gráfica 3D con Java, pero también si realmente nos interesa la programación de videojuegos es muy recomendable leer el material ofrecido en el [Curso de Experto en Desarrollo de Videojuegos](http://www.cedv.es/), un libro de una extensión de más de 1100 páginas de muy buena calidad, en español y descargables gratuitamente. En la [_wiki_ de jMonkeyEngine](http://wiki.jmonkeyengine.org/doku.php) se pueden encontrar [numerosos tutoriales para principiantes](http://wiki.jmonkeyengine.org/doku.php/jme3#tutorials_for_beginners), también numerosos artículos de nivel más avanzado y el [javadoc de la API](http://javadoc.jmonkeyengine.org/).

<div class="media-amazon" style="text-align: center;">
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=1849516464&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
</div>

Otras librerías como [Slick2D][slick2d] permiten hacer videojuegos en 2D como serían los juegos de plataformas, más o menos lo que permite jMonkeyEngine en el 3D aplicado a 2D también usando como lenguaje Java. Sin duda los videojuegos han sido el motivo en parte de que muchos hoy seamos programadores e informáticos aunque en nuestro trabajo nos dediquemos a otro tipo de aplicaciones y entornos.

Que, ¿aún crees que en Java no se pueden hacer juegos que no tienen que envidiar a muchos otros?

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [jMonkeyEngine][jmonkeyengine]
* [Curso Experto Desarrollo Videojuegos](http://www.cedv.es./)
* <a href="http://www.amazon.es/gp/product/1849516464/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=1849516464&linkCode=as2&tag=blobit-21">Libro jMonkeyEngine 3.0 Beginners Guide</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=1849516464" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"><br>
* [Slick2D][slick2d]
* [libGDX](http://libgdx.badlogicgames.com/)
{{% /reference %}}

{{% /post %}}
