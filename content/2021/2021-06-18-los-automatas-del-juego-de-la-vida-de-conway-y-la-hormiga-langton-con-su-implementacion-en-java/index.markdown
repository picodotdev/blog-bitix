---
pid: 582
type: "post"
title: "Los autómatas del juego de la vida de Conway y la hormiga Langton con su implementación en Java"
url: "/2021/06/los-automatas-del-juego-de-la-vida-de-conway-y-la-hormiga-langton-con-su-implementacion-en-java/"
date: 2021-06-18T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:game-of-life-glider.png"
imagePost: "image:game-of-life-glider.png"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Algunos procesos que aparentemente son complejos siguen reglas muy simples, y aún siguiendo reglas muy simples dan lugar a muchos posibles comportamientos diferentes. Los sistemas que implementan y aplican estas reglas simples se les conoce como autómatas. Los autómatas no poseen inteligencia artificial, no aprenden ni toman decisiones en base a anteriores resultados, los autómatas simplemente siguen sus reglas de comportamiento en el estado del sistema. Dos autómatas conocidos son el juego de la vida de [John Horton Conway](https://es.wikipedia.org/wiki/John_Horton_Conway) publicado en 1970 y la hormiga de Langton de [Chris Langton](https://es.wikipedia.org/wiki/Christopher_Langton) publicado en 1986."
---

{{% post %}}

{{< tableofcontents >}}

### El juego de la vida de Conway

El juego de la vida de Conway es un autómata con unas reglas muy simples que da lugar a múltiples y variados comportamientos. Es un autómata de cero jugadores que se desarrolla por sí mismo en base al estado inicial y las reglas del juego.

El juego es un tablero bidimensional formado por cuadrados o células que poseen dos estados, células muertas o encendidas o células vivas o apagadas. Cada célula está rodeada por 8 células vecinas. El estado de las células evoluciona a lo largo de unidades de tiempo discretas, el estado de todas las células se tiene en cuenta para calcular el estado en el turno siguiente aplicando las reglas del juego. Todas las células se actualizan simultáneamente en cada turno.

* [Juego de la vida](https://es.wikipedia.org/wiki/Juego_de_la_vida)

#### Reglas

Las reglas del juego de la vida definido por Conway son las siguientes:

* Una célula muerta con exactamente 3 células vecinas vivas nace, en el siguiente turno estará viva o encendida.
* Una célula viva con 2 o 3 células vecinas vivas sigue viva.
* En cualquier otro caso la célula muere o se apaga, por tener menos o más células adyacentes vivas de las reglas anteriores. Una célula viva muere por soledad o por superpoblación.

#### Variaciones, con otras reglas

El juego de la vida definido por Conway se representa con la siguiente nomenclatura 23/3, los primeros dos números indican las células necesarias para que una célula siga viva y el tercer número indica las células adyacentes necesarias para que una célula nazca.

Con otro número de células requeridas es posible crear variaciones del juego de la vida. Por ejemplo, 16/6 en el que una célula nace si tiene 6 vivas adyacentes y sigue viva si tiene un número igual a 1 o 6. Otra variación es 23/36 en el que una célula nace si el número de adyacentes vivas es 3 o 6 y sigue viva si el número de células vivas es 2 o 3, este caso es similar al juego de la vida de Conway variando que una célula nace si tiene 6 adyacentes vivas.

#### Patrones

Dado el estado inicial y las reglas de juego de la vida se observan varios patrones de comportamiento.

* Osciladores: estos patrones siguen una serie de pasos hasta que un número determinado llega al estado inicial, el patrón se repite de forma cíclica.
* Vidas estáticas: estas no cambian de estado con el paso del tiempo se mantienen en el mismo estado con el paso del tiempo.
* Naves espaciales: estos patrones evolucionan dando la sensación de que se trasladan por el tablero de juego de la vida.
* Matusalenes: son patrones que evolucionan o desaparecen después de un gran número grande de turnos.
* Cañones: son patrones que generan planeadores o naves espaciales.
* Locomotoras: son patrones que se desplazan por el tablero dejando un rastro de basura de osciladores, vidas estáticas, planeadores o naves espaciales.
* Sintetizadores: son patrones que dispuestos de elementos más básicos como _gliders_ producen otro tipos de patrones.

Uno de los patrones destacados del juego de la vida es una nave espacial conocido como _glider_, este es un patrón importante ya que son fáciles de producir que se pueden hacer colisionar con otros objetos de este modo ser usados para transmitir información. Ocho _gliders_ pueden ser posicionados para que colisionen formando un cañón _gosper glider_. Otros patrones como bloques, _beehives_, _blinkers_, _traffic lights_ son sintetizables con únicamente dos _gliders_.

Los _gliders_ también son capaces de colisionar para producir otros comportamientos, si dos _gliders_ son lanzados contra un bloque de la forma adecuada el bloque se mueve hacia a la fuente de los _gliders_. Si tres _glider_ son disparados en la forma correcta el bloque se mueve más aún. Esta memoria de desplazamiento puede ser usada para simular un contador modificable lanzándole _gliders_. También es posible construir puertas lógicas como _AND_, _OR_ o _NOT_ usando _gliders_. Esto es la misma capacidad de computación que una máquina universal de Turing, de modo que usando _gliders_ el juego de la vida es de forma teórica tan capaz como cualquier computadora con memoria ilimitada y sin restricciones de tiempo. Por estas propiedades del _glider_ se ha adoptado como un icono de la cultura _hacker_.

* [Juego de la vida](https://es.wikipedia.org/wiki/Juego_de_la_vida)

En esta [librería de patrones del juego de la vida de Conway](https://conwaylife.appspot.com/library) hay una colección de patrones en la que además es posible visualizar su comportamiento.

{{< image
    gallery="true"
    image1="image:game-of-life-glider.png" optionsthumb1="200x150" title1="Glider"
    image2="image:game-of-life-glider-gun.png" optionsthumb2="200x150" title2="Glider gun"
    image3="image:game-of-life-diehard.png" optionsthumb3="200x150" title3="Diehard"
    caption="Patrones glider, glider gun y diehard" >}}

* [Glider](https://conwaylife.appspot.com/pattern/glider) (nave espacial)
* [Gosper Glider Gun](https://conwaylife.appspot.com/pattern/gosperglidergun) (cañón)
* [Diehard](https://conwaylife.appspot.com/pattern/diehard) (matusalén)

{{< image
    gallery="true"
    image1="image:game-of-life-1.png" optionsthumb1="200x150" title1="Patrones que crecen indefinidamente"
    image2="image:game-of-life-2.png" optionsthumb2="200x150" title2="Patrones que crecen indefinidamente"
    image3="image:game-of-life-3.png" optionsthumb3="200x150" title3="Patrones que crecen indefinidamente"
    caption="Patrones que crecen indefinidamente" >}}

* [10 cell infinite growth](https://conwaylife.appspot.com/pattern/10cellinfinitegrowth)
* [5x5 infinite growth](https://conwaylife.appspot.com/pattern/5x5infinitegrowth)
* [Unidimensional infinite growth](https://conwaylife.appspot.com/pattern/unidimensionalinfinitegrowth)

Otros patrones:

* [Acorn](https://conwaylife.appspot.com/pattern/acorn) (matusalén)
* [Beehive](https://conwaylife.appspot.com/pattern/beehive) (vida estática)
* [Beehive Synth](https://conwaylife.appspot.com/pattern/beehive_synth) (sintetizador)
* [Gosper Glider Gun Synth](https://conwaylife.appspot.com/pattern/gosperglidergun_synth) (sintetizador)
* [Blinker Synth](https://conwaylife.appspot.com/pattern/blinker_synth) (sintetizador)
* [Traffic Light](https://conwaylife.appspot.com/pattern/trafficlight) (oscilador)
* [Traffic Light Synth](https://conwaylife.appspot.com/pattern/trafficlight_synth) (sintetizador)
* [Prepulsarshuttle26](https://conwaylife.appspot.com/pattern/prepulsarshuttle26) (oscilador)
* [88p28](https://conwaylife.appspot.com/pattern/88p28) (oscilador)
* [Line puffer](https://conwaylife.appspot.com/pattern/linepuffer) (locomotora)

#### Cómo probar el juego de la vida

En algunas páginas es posible probar el juego de la vida para experimentar con su comportamiento.

* [Visualizador del juego de la vida I](https://conwaylife.appspot.com/new)
* [Visualizador del juego de la vida II](https://playgameoflife.com/)

### La hormiga de Langton

La hormiga de Langton es otro tipo de autómata con unas reglas muy simples pero que da lugar a un comportamiento complejo. Al igual que el juego de la vida de Conway se desarrolla en un tablero de dos dimensiones en el que cada celda del tablero está encendida o apagada.

En está página de la wikipedia se puede probar la hormiga de Langton.

* [Hormiga de Langton](https://es.wikipedia.org/wiki/Hormiga_de_Langton)

{{< image
    gallery="true"
    image1="image:hormiga-de-langton.png" optionsthumb1="300x200" title1="Hormiga de Langton"
    caption="Patrón generado por la hormiga de Langton después de 10K pasos" >}}

#### Reglas

La hormiga de Langton se basa en las siguientes reglas:

* Si está sobre un cuadrado encendido, cambia el color del cuadrado, gira noventa grados a la izquierda y avanza un cuadrado.
* Si está sobre un cuadrado apagado, cambia el color del cuadrado, gira noventa grados a la derecha y avanza un cuadrado.

En el caso de la hormiga de Langton al cabo de unos 10000 turnos crea un patrón que sigue de forma indefinida.

#### Variaciones, con otras reglas

A la hormiga de Langton también es posible aplicarle otra reglas, por ejemplo añadiendo más estados a las celdas con colores o incluyendo varias hormigas en el tablero.

### Implementación del juego de la vida en Java

Esta es la implementación en código con lenguaje Java del juego de la vida de Conway y sus reglas. Estas son las clases principales que implementan el juego, la clase _Cell_ representa una célula con su estado y la clase _Board_ en el método _step_ produce el siguiente estado con las reglas del juego implementadas en los métodos _survives_, _borns_ y _countAliveNeighbours_.

{{< code file="conway/Cell.java" language="java" options="" >}}
{{< code file="conway/Board.java" language="java" options="" >}}

### Implementación de la hormiga de Hormiga de Langton en Java

Esta es la implementación en lenguaje Java de la hormiga de Langton y las reglas propias del juego. La clase _Turmite_ representa la hormiga, el método _step_ aplica la lógica del autómata de la hormiga en función del estádo de la celda en la que está. Los metodos _turnLeft_, _turnRight_ y _forward_ cambian el estado de la hormiga haciéndola cambiar de dirección y avanzando a otra celda.

{{< code file="langton/Turmite.java" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaConwayLangton" %}}

{{< reference >}}
* [El juego de la vida por Manuel R. D.](https://www.it.uc3m.es/jvillena/irc/practicas/09-10/04mem.pdf)
* [El Juego de la Vida de Conway](https://www.adictosaltrabajo.com/2020/04/30/el-juego-de-la-vida-de-conway/)
* [El mítico juego de la vida del matemático John Conway sigue siendo uno de los problemas clásicos de programación 50 años después](https://www.xataka.com/historia-tecnologica/mitico-juego-vida-matematico-john-conway-sigue-siendo-uno-problemas-clasicos-programacion-50-anos-despues)
* [Implementación en Java del juego de la vida](http://www.bitstorm.org/gameoflife/code/)
{{< /reference >}}

{{% /post %}}
