---
pid: xxx
title: "Desempaquetado Intel NUC NUC8i5BEK (Bean Canyon), memoria RAM y SSD Samsung 970 EVO"
url: "/2018/11/desempaquetado-intel-nuc-nuc8i5bek-bean-canyon-memoria-ram-y-ssd-samsung-970-evo/"
date: 2018-11-13T20:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
image: ""
tags: ["hardware", "planeta-codigo"]
series: ["desempaquetado-tecnologia"]
summary: "Al fin tengo nuevo equipo que cumple con las necesidades que tengo. Principalmente que sea pequeño pero al mismo tiempo suficientemente potente para que me suponga un salto notable en rendimiento respecto al portátil que tenía. Después de decidirme entre portátil, ITX o NUC me decidí por unos de los nuevos Bean Canyon com procesador de 4 núcleos y 8 hilos posiblidad de SSD con NVMe y hasta un máximo de 32 GiB de RAM."
draft: true
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="intel.svg" title1="Intel" width1="200" image2="gskill.svg" title2="G.Skill" width2="200" image3="samsung.svg" title3="Samsung" width3="200" >}}

Llevaba unos cuantos meses buscando entre las muchas opciones que hay un nuevo ordenador personal, casi un año desde enero del 2018 hasta ahora noviembre. La espera en algunos momentos se me ha hecho larga ya que sobre Intel NUC por el que me he decidido como nuevo equipo ya había noticias de él en enero, sin embargo, hasta julio no los empezaba a distribuir y no ha sido hasta octubre y noviembre cuando ha empezado a estar disponible en las tiendas para comprar. Ha sido tanto tiempo por el conjunto de características que deseaba que limitaba en buena medida las opciones entre las que podía elegir, más cuando en las tiendas aún no estaba el equipo que quería. Por algunas cosas aún seguiría esperando pero ya me decidido a quedarme con el NUC ya que no quiero esperar más a tener mi propio equipo. Por obligación estoy usando un Apple MacBook Pro del 2015 y echo de menos mi distribución [GNU][gnu]/[Linux][linux] preferida que es [Arch Linux][archlinux].

Siempre que puedo cuando algún familiar necesita un ordenador lo que hago es darle el que tengo yo y yo comprar uno nuevo, de esta forma al ordenador que entrego le doy una segunda vida para la que es perfectamente útil, así ha sido con los tres ordenadores que he comprado hasta el momento desde el 2002. Mis usos son mucho más exigentes, principalmente los equipos se me han quedado pequeños por la cantidad de memoria RAM. El primer ordenador que compré en el 2004 fue un ordenador de escritorio en formato torre tradicional ATX con un AMD 1800+, 512 MiB de memoria, 60 GB de disco duro y tarjeta gráfica NVIDIA GeForce 440MX creo que con 32 MiB. Viendo que al finalizar su vida útil era algo complicado donar un ordenador ATX por tema de espacio el siguiente equipo que compré en el 2008 fue un portátil Dell 1530 XPS con un Intel Core 2 Duo 8100T, 4 GiB de memoria, 320 GB de disco duro y gráfica NVIDIA 8600GT con 512 MiB, al poco tiempo de donarlo se estropeó, se quedó completamente muerto sin llegar a hacer ningún atisbo de encenderse, desde entonces no quiero nada que venga de Dell incluidos monitores tampoco me convenció pasado el tiempo de deslumbramiento inicial de su posesión. El tercer equipo a finales del 2012 también fue un portátil con la misma intención de poder donarlo llegado el momento, un Sony VAIO de 14" de resolución 1600x900 en panel TN con un Intel Core i5 3210M, 8 GB de memoria con la que poder virtualizar más a gusto y un SSD Samsung 840 EVO de 250 GB que le puse a posteriori con el que el aumento de rendmiento en acceso a almacenamiento persistente fue una gran mejora respecto a los discos duros, mejor inversión que mejor procesador y más cantidad de memoria.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1=""
        image2="" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2=""
        image3="" command3="Fit" commandthumb3="Fit" options3="2560x1440" optionsthumb3="300x200" title3=""
        caption="" >}}
</div>
[Foto micro y memoria], Procesador Intel Core 2 Duo 8100T y 4 GiB de memoria DDR2 que aún conservo

Empezaba por decidirme que formato de ordenador quería con [Intentando elegir portátil, NUC o mini ITX para comprar nuevo equipo][blogbitix-309] y pasado un tiempo ya casi optado por la opción que quería, [Decidido a comprar un Intel NUC entre las opciones que he evaluado][blogbitix-329]. Para el nuevo ordenador pesonal en cualquier opción que eligiese quería monitor externo, al final elegí un [Benq PD2700Q con resolución QHD (2560x1440) e IPS](https://amzn.to/2OFdFi9). Al final descarté un ATX por tamaño e ITX aún siendo un formato más pequeño seguía siendo grande para mi, a pesar de que con esta opción podría elegir un AMD Ryzen con mayor cantidad de núcleos. Portátil seguía siendo una opción válida para poder donarlo en un futuro pero ocupa cierto espacio en la mesa y no tengo intención de moverlo por lo que la función que le da nombre no me es necesaria, si eligiese uno sería un [Slimbook Pro2](https://slimbook.es/en/pro-ultrabook-13-aluminium) seguramente. Como opción me quede con algún ordenador en formato [Intel NUC][intel-nuc], en un futuro cuando ya se me quede no válido para mis usos principales lo utilizaré como ordenador servidor de archivos, de descargas u otros usos que pueda darle, como ocupa poco el espacio no será un problema.

### Intel NUC NUC8i5BEK (Bean Canyon)

Es una pena que AMD no ofrezca equipos en formato de Intel NUC, muy posiblemente lo hubiese elegido principalmente por [los graves fallos de seguridad Meltdown y Spectre] que se hicieron públicos a inicios del 2018 conocidos que afectan más a Intel y que por ser un fallo del hardware solo se pueden no arreglar simplemente mitigar haciendo que sea más difícil explotarlos. Para que en el cambio del equipo fuese una mejora significativa respecto al anterior portátil Sony que tenía quería que tuviese algún núcleo más en esta guerra entre Intel y AMD por ver quien ofrece más núcleos que parece por ahora va ganando AMD con su nueva arquitectura Zen y los problemas que está teniendo Intel para bajar de los 14nm en litografía.

Además, tengo intención de intentar jugar algún juego como Diablo 3 en GNU/Linux instalando la aplicación [Battle.net de Blizzard] ejecutándola con [Wine][wine] y [PlayOnLinux][playonlinux], al menos en una máquina virtual con [VirtualBox][virtualbox] lo conseguí a pesar de que no llegé a jugar pero si a instalar el juego. También intentaré instalr algún juego de [Steam][stema] y de [GOG][gog] sobre los que si lo consigo ublicaré sus respectivos artículos para explicarlo. Las gráficas integradas de Intel son muy básicas y no está destinadas principalmente a juegos, al menos no triple AAA nuevos, pero son suficientes para un jugador ocasional como yo que tampoco le importa bajar la resolución y detalles gráficos para tener unos FPS razonables para jugar o se conforma con juegos con unos años.

Intel ha lanzado al mercado unos NUC con gráfica AMD Vega con los que si es posible a jugar bien a juegos, los NUC conocidos como [Hades Canyon]. Sin embargo, estos son más caros y tienen una fuente de alimentación tan grande como el propio NUC con la que era un poco reticente. Al mismo tiempo había leído noticias de los [Bean Canyon] que cumplían los dos requermientos que tenía, al menos 4 núcleos y 8 hilos y una gráfica un poco mejor, de la gama Iris que tienen el doble de potencia de lo que ofrece Intel en algunos de sus procesadores. Sin embargo, esto me implicaba esperar ya que estos equipos aún no estaban en el mercado, había NUCs con 4 núcleos pero con gráfica UHD 630 (GT2) no Iris Graphics 655 y que eran más caros que el precio al que finalmente han salido los Bean Canyon que llevan también 4 nucleos y la citada Iris Graphics 655 (GT3). Las [gráficas Intel][intel-graphics] no son muy potentes comparadas con las dedicadas NVIDIA y AMD pero tienen muy buen soporte de controladors en GNU/Linux.

Hay varios modelos de Bean Canyon variando el procesador que incorporan, ligeranmente en tamaño si ofrecen bahía para disco de 2.5" y en el precio. El más básico [NUC8i3BEK](https://www.intel.com/content/www/us/en/products/boards-kits/nuc/kits/nuc8i3bek.html) lleva un procesador [i3-8109U](https://www.intel.com/content/www/us/en/products/processors/core/i3-processors/i3-8109u.html) que tiene 2 núcleos y 4 hilos con 4 MiB de cache pero la misma gráfica Iris que los modelos mayores. El [NUC8i5BEK](https://www.intel.com/content/www/us/en/products/boards-kits/nuc/kits/nuc8i5bek.html) lleva un procesador [i5-8259U](https://ark.intel.com/products/135935/Intel-Core-i5-8259U-Processor-6M-Cache-up-to-3_80-GHz) con 4 núcleos y 8 hilos con 6 MiB de cache, la misma [Iris Graphics 655] de todos estos modelos. El modelo [NUC8i7BEK](https://www.intel.com/content/www/us/en/products/boards-kits/nuc/kits/nuc8i7beh.html) lleva un [i7-8559U](https://ark.intel.com/products/137979/Intel-Core-i7-8559U-Processor-8M-Cache-up-to-4-50-GHz-) con 8 MiB de cache. Todos los modelos tienen un TPD de 28W que es algo mayor que los 15W de la generación anterior pero para que no soponga un problema el calor generado en los Bean Canyon Intel ha incorporado un ventilador de un tamaño más grande para que aún así el rudio que hace al funcionar sea menor, además ha modificado las rejillas de ventilación para permitir mayor flujo de aire. Todos poseen un puerto Ethernet Gigabit y WiFI ac, una salida de vídeo HDMI 2.0a con soporte hasta 4K, salida Dislpay Port 1.2 integrada en el contector USB Type-C, cuatro puertos USB dos en la parte frontal (uno con soporte carga) y dos en la parte trasera, además del conector para la fuente de alimentación que tiene un tamaño pequeño comparado con la de los Hades Canyon, en la parte frontal está el botón de encendido y un LED indicador de actividad del SSD junto con la salida de audio en formato jack 3.5mm. Los modelos acabados en _K_ son más pequeños en tamaño vertical ya que no ofrecen bahía 2.5 pulgadas para un segundo disco duro con interfaz SATA III que si ofrecen los acabados en _H_. En un lateral se encuentra la ranura para [tarjetas SDXC][wikipedia-securedigital] en formato micro. En el interior están los dos _slots_ para la memoria pudiendo instalar hasta un total de 32 GiB DDR4 a 2400Mhz, también está el conector M.2 para un SSD con interfaz NVMe o SATA III de longitud 2280 o 2242. Ocupan muy poco, tiene un tamaño de ¿¿¿15,5 x 12,7 x 10,2 cm??? (ancho, largo, alto en centímetros) y la fuente de alimentación XXXxXXX con una longitud de cable de XXX metros.

* [Next Unit of Computing (NUC)](https://en.wikipedia.org/wiki/Next_Unit_of_Computing)
* [Intel Graphics Technology](https://en.wikipedia.org/wiki/Intel_Graphics_Technology)

[Especificaciones NUC de cada modelo]
> https://www.tomshardware.com/news/intel-bean-canyon-pricing-specs,37626.html

En el momento de comercialización el modelo NUC8i3BEK tiene un precio sobre los 300€, el NUC8i5BEK sobre los 400€ y el NUC8i7BEK sobre los 500€. Los Hades Canyon llegan a los 800€ y 1000€ pero con mejor gráfica y mayor número de puertos de conexión. Con la aparición de los Bean Canyon los [Baby Canyon] se han convertido en una opción desaconsejada por la reducida diferencia de precio, el [NUC7i5BNK](https://www.intel.com/content/www/us/en/products/boards-kits/nuc/kits/nuc7i5bnk.html) (i5-7260U) cuesta unos 360€ y tiene 2 núcleos menos que el NUC8i5BEK.

[Modelos NUC en Amazon]

Al final opté por el modelo NUC8i5BEK, por sus cuatro núcleos. El precio en el momento de salida es de unos 400€ no es muy superior respecto a los que costaría un equipo ITX tinedo en cuenta que en estos hay que comprar placa base, fuente de alimentación y caja con la diferencia de que ocupa sensiblemente más. Un [AMD Ryzen 2400G](https://www.pccomponentes.com/amd-ryzen-r5-2400g-36ghz), [placa base MSI B450I](https://www.pccomponentes.com/msi-b450i-gaming-plus-ac), [fuente de 450W](https://www.pccomponentes.com/corsair-cx450-450w-80-plus-bronze) y [caja ITX] el conjunto se va a aproximadamente a los mismos 400€, el Ryzen tiene mejor gráfica integrada que la Intel pero el cojunto ocupa sensiblemente muhco más espacio.

He esperado todos estos meses hasta ahora que han aparecido a la venta en [Amazon][amazon]. Varios meses antes se han publicado varios artículos analizando en buen detalle estos modelos de NUC asi como antes la [nota de prensa con la presentación oficial de Intel](https://newsroom.intel.com/news/intel-introduces-new-nuc-kits-nuc-mini-pcs-intel-nuc-family/) en el momento de su inicio de comercialización. En mi experiencia con este caso desde que aparecen las primeras noticias en los medios hasta que se empieza a comercializar y más tarde hasta que aparece en las tiendas y llega a tiendas como Amazon y PC Componentes, puede pasar perfectamente más de medio año o un año.

* [Coffee Lake i3 NUC Review (NUC8i3BEH)](https://nucblog.net/2018/10/coffee-lake-nuc8i3beh-review/)
* [Coffee Lake i5 NUC Review (NUC8i5BEK / NUC8i5BEH)](https://nucblog.net/2018/11/coffee-lake-i5-nuc-review-nuc8i5bek-nuc8i5beh/)
* [Coffee Lake i7 NUC Review (NUC8i7BEH)](https://nucblog.net/2018/11/coffee-lake-i7-nuc-review-nuc8i7beh/)
* [Gaming on Bean Canyon NUCs – Comparison](https://nucblog.net/2018/11/gaming-on-bean-canyon-nucs/)
* [Intel NUC (NUC8i7BEH) Mini-PC Kit First Impressions](https://www.thurrott.com/hardware/190478/intel-nuc-nuc8i7beh-mini-pc-kit-first-impressions)
* [Intel NUC Kit NUC7PJYH Review](https://www.cnx-software.com/2018/05/16/intel-nuc-kit-nuc7pjyh-review/)
* [Nuevos NUC Intel Bean Canyon: ahora con procesadores Coffee Lake-U](https://hardzone.es/2018/07/25/nuc-intel-bean-canyon-coffee-lake-u/)

<div class="media media-video" style="text-align: center;">
	<iframe width="640" height="360" src="https://www.youtube.com/embed/MMuJlVW7mQE?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

También se puede ver una comparativa del rendimiento entre los modelos de procesador de los Bean Canyon y la generación anterior.

* https://browser.geekbench.com/v4/cpu/9361455

### Memoria

Como desarrollador suelo virtualizar o iniciar contenedores de [Docker][docker] que demandan en buena medida cantidad de memoria. Todos los equipos principalmente se me quedan pequeños por la cantidad de memoria much más incluso que por el procesador o por la velocidad o tamaño del almacenamiento ya habiendo pasado a los SSD, incluso los 8 GiB del portátil Sony se me quedaron pequeños. La memoria DDR4 está muy cara, más incluso que en el momento de su salida lo que es una anomalía en la tecnología que siempre baja de precio por la presión de los avances, los fabricantes deben estar teniendo unos márgenes de beneficios brutales con la memoria DDR4. Han preferido producir memoria NAND y RAM para teléfonos móviles que memoria RAM para ordenadores. En el 2019 está previsto que baje de precio entre un 10% y 20% por menor demanda.

Probablemente 16 GiB me sería suficientes pero no me importa ir a por los 32 GiB aún con el consiguiente aumento de precio. Del modelo de la memoria no tengo muchos requermientos simplemente una que fuese de 2400Mhz y estuviese entre las [memorias validada por Intel](http://compatibleproducts.intel.com/ProductDetails/ExportPeripheralInfo?moduleName=Intel%C2%AE%20NUC&productType=Kits&productName=Intel%C2%AE%20NUC%20Kit%20NUC8i5BEK) para estos NUC. Hay módulos de 16 GB que se venden sueltos o _kits_ de dos pares de módulos para hacerlos funcionar en _dual channel_. Dependiendo del momento puede salir más económico comprar los dos módulos por separado o comprar un _kit_. La ventaja del _kit_ es que están validados para hacerlos funcionar en _dual channel_ y es que los módulos separados pueden tener alguna diferencia según el momento en que fuero fabricados ya que podrían haberse producido con componentes de diferentes proveedores pudiendo hacer que alguna diferencia provocase algún error. Comprando los dos módulos en el mismo momento es raro que tenga alguna diferencia pero por asegurar se puede optar por el _kit_.

* [Kits de RAM vs módulos individuales, ¿existe realmente alguna diferencia?](https://foro.noticias3d.com/vbulletin/showthread.php?t=433238)
* [Difference between memory kits and single memory modules](http://www.tomshardware.co.uk/forum/270105-30-difference-memory-kits-single-memory-modules)
* [HyperX Impact Memory Module Specifications](https://www.kingston.com/datasheets/HX424S14IBK2_32.pdf)

Dos modelos validados por Intel compatibles con esto NUC son [HyperX Impact](https://amzn.to/2K7lnfs) de Kingston y los [G.Skill Ripjaws](https://amzn.to/2PuxIjO) más o menos en el mismo precio, los HyperX tienen algo mejor latencia aunque será poco apreciable en el uso del ordenador. Los 32 GiB en el momento en que los he comprado están entre 280 y 320€. Al final opté por la memoria HyperX Impact.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&language=es_ES&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01BNJL8I4&linkId=ef22daf2b91899709c8686b7db8a49c7"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&language=es_ES&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B017UC3UPW&linkId=06d1aea567128e234b58d2e800ef099c"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&language=es_ES&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B017UC3UFC&linkId=d0f4f32393a8a66aed6fadf13b996f4f"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&language=es_ES&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01BNJL8K2&linkId=6533af03761af37a69a759fade2c9a66"></iframe>
</div>

### Samsung 970 EVO M.2 NVMe

El almacenamiento en formato SSD está bajando notablemente de precio a cada mes o par de meses que pasa. En tamaño de 250GB ya tienen un precio muy asequible e incluso en 500GB y 1TB no son prohibitivos. Cambiar el disco duro por un SSD es la mejor inversión a realizar en un ordenador si la cantidad de memoria es suficiente, cualquier procesador cumple para usos ofimáticos. Se puede optar por un SSD con interfaz SATA III con una velocidad de lectura y escritura de 550/500 MB/s que ya es bastante rápido para muchos usuarios o en formato M.2 NVMe que ofrece sensiblemente mayores tasas de transferencia de hasta 3500/2500 MB/s.

Por la cantidad de datos que tengo el tamaño que necesito está entre 250 y 500 teniendo que una gran parte de los datos los tengo en dos discos duros USB externos por duplicado y el equipo solo contendría los más importantes. Un M.2 con interfaz SATA III de 500 Gb como el [Crucial MX500](https://amzn.to/2DGhIFg) y un [Samsung 970 EVO](https://amzn.to/2OITkDX) también M.2 de 500 GB pero NVMe. Por coparar como han evolucionado el SSD que compré en el 2014 para el Sony Vaio era un Samsung 840 EVO de 250GB me costó 125€ y ahora un NVMe ofrece el doble de capacidad y a una velocidad sensiblemente superior y en foramto SATA III doble de capacidad a un precio sensiblemente inferior.

Al final he optado por el Samsung 970 EVO, con el Crucial con interfaz SATA III tendría creo que suficiente pero no estoy ajustando mucho el precio, al igual que en la memoria. 

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&language=es_ES&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B077SQ8J1V&linkId=048e7dde98c59aff3bdf3eda82b0057a"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&language=es_ES&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B07CGGP7SV&linkId=a3638f97cf6e347781428650f83a3a9a"></iframe>
</div>

Es muy posible que compre adicionalmente una [tarjeta micro SDXC de 128 GB] como una forma de ampliar la cantidad de almacenamiento que tengo disponible y como caperta de descargas y archivos temporales. Las SDXC admiten hasta un almacenamiento de 2 TiB que en un futuro se harán más asequibles en precio.

[Enlaces amazon tarjeta micro SDXC]

### Desempaquetado

Con estos componentes he realizado el pedido en Amazon ya que es la tienda que suelo utilizar para las compras en internet que hago, por comodidad, ahorrar tiempo, poder informarme lo mejor que puedo y por el amplio catálogo de productos que tiene. En total el conjunto de todos estos componentes me ha salido por unos 830€, algo más barato que el portátil Sony si tengo en cuenta el SSD que le compre luego pero sensiblemente mejor con el lustro que ha pasado entre uno y otro.

#### NUC

La caja del NUC es muy pequeña en la que se inlyue el NUC y la fuente de alimentación.

[Fotos NUC]
> Caja, exterior, interior

#### Memoria

El _kit_ de la memoria no tiene nada especial. Cada módulo está empquetado en un _blister_ de plástico.

[Fotos memoria]

#### SSD

El _stick_ del SSD también es muy pequeño.

[Fotos SSD]

### Montaje de memoria y SSD

Montar la memoria y el SSD en el NUC es sencillo. Para acceder al interior del NUC hay que quitar los cuatro tornillos de la tapa inferior que da acceso a los _slots_ de memoria y el conector M.2. La memoria se coloca en el _slot_ con cierta inclinación y posterior mente se empuja hacia abajo, no es necesario hacer mucha fuerza ni hace falta forzarlo, como los _slots_ están uno encima del otro primero se coloca el módulo que queda abajo.

[Imágenes desatornillado de la tapa]
[Imagenes colocación memoria]

El SSD se coloca de forma similar, con cierta inclinación al prsentar el SSD en el contecto, empujando sin hacer mucha fuerza hacia abajo y con un tornillo que queda al final del _stick_ queda colocado en su posición final para que no se mueva.

[Imágenes colocación SSD]

Una vez colocados ambos elementos se vuelve a colocar la tapa inferior que hace de base en el NUC.

### Apple Mac mini

Un equipo equivalente a los NUC son los [Apple Mac mini](https://www.apple.com/es/mac-mini/). Despues de unos años Apple los ha renovado como unos procesadores más recientes, más cantidad de memoria y SSD instalable. Pero a unos precios muy exagerados, tanto que el Mac mini con una configuración similar a la de este artículo del NUC saldría por 2100€, bastante más del doble. Salvo que uno quiera macOS sí o sí y uno esté muy ocecado en querer Apple no es una opción en mi opinión acertada. Paro bueno esto pasa con cualquier producto de Apple no solo es específico de Mac mini aunque como se aprecia es un ejemplo claro y sangrante al compararlo. Por si fuera poco el procesador no soporta HyperThreading o SMT con lo que son 4 núcleos y 4 hilos y la gráfica es una UHD 630 la mitad de potente que la Iris Graphics 655.

En cualquier caso no entraba dentro de mis planes un Mac mini ni aunque tuviese el mismo precio del NUC ya que prefiero GNU/Linux como detallo en [Tú con tu Mac, yo con mi GNU/Linux][blogbitix-305].

<table>
    <tr>
        <th>Componente</td>
        <th>Apple Mac mini</td>
        <th>Intel NUC Bean Canyon</td>
    </tr>
    <tr>
        <td>Ordenador</td>
        <td>900€</td>
        <td>300€ (i3), 400€ (i5), 500€ (i7)</td>
    </tr>
    <tr>
        <td>Memoria 32 GiB</td>
        <td>+720€</td>
        <td>+300€</td>
    </tr>
    <tr>
        <td>SSD 500GB</td>
        <td>+480€</td>
        <td>+140€ (Samsung 970 EVO, NVMe)</td>
    </tr>
    <tr>
        <td>Total</td>
        <td>2100€</td>
        <td>840€ (i5)</td>
    </tr>
</table>

### Slimbook One

También hubiese podido optar por un [Slimbook One](https://slimbook.es/en/power-minipc-one) per no me convención porque solo tienen un _slot_ de memoria, admite hasta 32GB pero aún no hay módulos de esa cantidad si no es en _kit_, lo que en la práctica limita su memoria a 16GB. La tarjeta gráfica que tienen es una UHD 630 como comentgaba la mitad de potente en teoría que la Iris Graphics 655.

### Nuevo escritorio

Este es la dispoción que tenía antes con el Sony VAIO y la que tengo ahora con el NUC, la pantalla externa, teclado y ratón inalámbrico y alfombrilla para ellos junto con un concentrador USB. He ganado mucho espacio en la mesa, la pantalla la tengo ahora al final de la mesa y el NUC me ocupa menos espacio que el portátil además de que puedo colocarlo en cualquier lugar de la mesa.

[Imágenes antes y después, desde arriba, desde el mismo plano lateral]

He tenido que esperar muchos meses a tener esta configuración, entre elegir que quería y aguantar a que lo que quería saliese al mercado, no se si la siguiente vez esperaré tanto ya que en algunos momento se me ha hecho un tanto difícil sobre todo los fines de semana y algunos días de vacaciones. Pero bueno ya lo tengo y ahora solo me queda sacarle provecho, no tener GNU/Linux durante un tiempo ha hecho que no haya escrito artículos sobre él, en cuanto pruebe el [_script_ para instalar Arch Linux de forma automatizada, desatendida y personalizable][blogbitix-204] muy posiblemente escriba algunos artículos, empezando por esos que comento de como poder jugar a juegos con Wine y PlayOnLinux además de Steam y GOG en algún momento más tarde.

[Foto GNOME]

> Hardware (Inte NUC, [Benq PD2700Q], [V7 keyboard/mouse]). Software (GNU/Linux, GNOME). Complete.

{{% /post %}}
