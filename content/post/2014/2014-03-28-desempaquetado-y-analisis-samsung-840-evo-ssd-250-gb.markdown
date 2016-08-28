---
pid: 18
title: "Desempaquetado y análisis Samsung SSD 840 EVO 250 GB"
url: "/2014/03/desempaquetado-y-analisis-samsung-840-evo-ssd-250-gb/"
date: 2014-03-28T18:22:41+01:00
updated: 2015-11-10T19:00:00+01:00
sharing: true
comments: true
tags: ["hardware", "planeta-linux", "planeta-arch-linux", "planeta-codigo"]
series: ["desempaquetado"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

En estos momentos sin lugar a dudas la actualización más provechosa para mejorar el rendimiento de un equipo es una unidad de estado sólido o SSD ([Solid State Drive](https://es.wikipedia.org/wiki/Unidad_de_estado_s%C3%B3lido)) una vez tengamos memoria RAM suficiente, unos 2 GiB suelen ser suficientes para tareas ofimáticas y acceder a internet y los equipos que se están vendiendo ahora ya suelen venir con 4 GiB e incluso 8 GiB. El tiempo de acceso a un disco duro mecánico es grande y la velocidad de transferencia es pequeña, en los momentos que se require un uso intensivo del disco se nota que son el cuello de botella del sistema. A veces una mejora en el software puede producir una notable aumento de rendimiento con el mismo hardware pero cuando ya no hay margen de mejora en el software con una mejora en el hardware podemos conseguir el aumento de rendimiento buscado, este es el caso de los SSD.

Así que después de estar leyendo prácticamente en todas las opiniones de los usuarios que han adquirido un SSD la apreciable mejora conseguida finalmente decidí comprar uno. El modelo que he elegido ha sido un <a href="http://www.amazon.es/gp/product/B00E391OX6/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00E391OX6&linkCode=as2&tag=blobit-21">Samsung 840 EVO SSD de 250 GB</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00E391OX6" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;">, que en el momento de adquirirlo es posiblemente una de las mejores opciones en cuanto calidad/precio, aunque posteriormente ha salido la evolución de este modelo el <a href="http://www.amazon.es/gp/product/B00P736UEU/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00P736UEU&linkCode=as2&tag=blobit-21">Samsung 850 EVO de 250 GB</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00P736UEU" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> con la novedad de que usa 3D NAND con la que consigue mayor densidad de almacenamiento que para los usuarios se traducirá en menor coste, otra buena opción era el <a href="http://www.amazon.es/gp/product/B00KFAGCWK/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00KFAGCWK&linkCode=as2&tag=blobit-21">Crucial MX100 256 GB</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00KFAGCWK" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> o el <a href="http://www.amazon.es/gp/product/B00IRRDHUI/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00IRRDHUI&linkCode=as2&tag=blobit-21">Crucial M550 256 GB</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00IRRDHUI" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;">. Los SSD aún siguen siendo un producto con un precio más alto que los discos mecánicos pero desde la aparición de los primeros modelos han bajado notablemente de precio y ya se están poniendo alrededor de los 110 € para los modelos de 250 GB y en 65 € para los modelos de 120 GB, unos precios que empiezan a ser muy accesibles y posiblemente sus ventas empiecen a dispararse y a ser incluidos de serie en los equipos nuevos. Hay modelos de más capacidad, de <a href="http://www.amazon.es/gp/product/B00P73B1E4/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00P73B1E4&linkCode=as2&tag=blobit-21">500 GB también en la serie Samsung 850 EVO</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00P73B1E4" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> y 750 GB pero los precios son algo abultados y posiblemente tampoco necesitemos tanto espacio.

En cualquier caso el SSD para mi tampoco ha sido una necesidad real sino más bien un capricho que me he permitido, si bien es cierto que el rendimiento del SSD hace palidecer al disco duro mecánico que tenía y los comentarios sobre los SSD están totalmente justificados también se puede vivir perfectamente con un disco duro mecánico si no nos importa esperar algo de tiempo. Con el disco duro mecánico el sistema me iniciaba en 1 minuto 30 segundos hasta llegar al inicio de sesión GDM y otros 30 segundos más hasta que el equipo me era usable en el escritorio. Con el SSD, y sin exagerar, el sistema se inicia en unos 10 segundos hasta llegar al inicio de sesión GDM y pocos segundos más hasta llegar al escritorio. En el inicio del sistema y en el arranque de alguna aplicación pesada que es donde más intensivamente se usa el disco es donde más notaremos el aumento de rendimiento, en mi caso que uso [Arch Linux](https://www.archlinux.org/) también lo noto en las actualización del sistema cuando instala los nuevos paquetes. Mi opinión es que el SSD es posiblemente la mejor actualización para prolongar algo más la vida de un equipo si la memoria RAM no es el problema pero tampoco es imprescindible a pesar de ser cierto el gran aumento de rendimiento.

El disco <a href="http://www.amazon.es/gp/product/B00E3W1726/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00E3W1726&linkCode=as2&tag=blobit-21">Samsung 840 EVO SSD de 250 GB</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00E3W1726" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> lo compré en <a target="_blank" href="http://www.amazon.es/?_encoding=UTF8&camp=3626&creative=24822&linkCode=ur2&tag=blobit-21">Amazon</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=ur2&o=30" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;">, en algunos productos como este suelen tener los mejores precios y una abundante cantidad de modelos donde elegir. En las ocasiones que he comprado en ellos el proceso de compra y el envío ha sido perfecto, en tres días laborables tenía los productos en la dirección de entrega y en todo momento he estado informado tanto del estado del pedido como del envío del producto. Amazon es un buen modelo a seguir en cuanto a comercio electrónico. Si bien es cierto que si hubiese tenido oportunidad y los precios hubiesen sido similares o un poco superiores lo hubiese preferido comprar en un tienda local, [IzarMicro](http://www.izarmicro.net/) me era una buena opción pero en el momento que hice el pedido el precio era algo superior.

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/18/samsung-840-evo-ssd-1.jpg" title="Samsung SSD 840 EVO 250 GB" data-gallery><img src="assets/images/custom/posts/18/samsung-840-evo-ssd-1-thumb.jpg"></a>
	<a href="assets/images/custom/posts/18/samsung-840-evo-ssd-2.jpg" title="Samsung SSD 840 EVO 250 GB" data-gallery><img src="assets/images/custom/posts/18/samsung-840-evo-ssd-2-thumb.jpg"></a>
	<a href="assets/images/custom/posts/18/samsung-840-evo-ssd-3.jpg" title="Samsung SSD 840 EVO 250 GB" data-gallery><img src="assets/images/custom/posts/18/samsung-840-evo-ssd-3-thumb.jpg"></a>
	<a href="assets/images/custom/posts/18/samsung-840-evo-ssd-4.jpg" title="Samsung SSD 840 EVO 250 GB" data-gallery><img src="assets/images/custom/posts/18/samsung-840-evo-ssd-4-thumb.jpg"></a>
</div>

Como se aprecia en las imágenes de desempaquetado el disco viene en una caja del tamaño de un CD conteniendo:

* El disco SSD
* Unas pegatinas
* Un CD con el manual y software.
* Unas guías de instalación
* La hoja de garantía

Las especificaciones técnicas de la serie de discos SSD de <a href="http://www.amazon.es/gp/product/B00E391OX6/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00E391OX6&linkCode=as2&tag=blobit-21">Samsung 840 EVO SSD</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00E391OX6" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> son:

* SATA 3 (6 Gbps) compatible con SATA 2 (3 Gbps)
* 7 mm de altura en 2.5"
* Lectura secuencial hasta 540 MB/s y 520 MB/s de escritura

El disco es muy ligero pesando solo unos pocos gramos, tiene un tamaño de 7 milímetros de altura y la dimensiones son de 2.5 pulgadas, el tamaño más ampliamente usando en los discos para los portátiles no muy viejos. El disco se nota que en su mayor parte está vacío, he visto en [alguna foto](http://www.xbitlabs.com/articles/storage/display/samsung-840-evo_3.html) que en realidad el disco solo ocupa una fracción del tamaño total de 2.5 pulgadas, por lo que los SSD todavía tienen margen para aumentar su capacidad, un punto donde aún les ganan los discos mecánicos. El ruido que hace es ninguno por lo que ya no oiremos "rascar" al disco cuando se escriba de forma intensiva en él. Aunque en la etiqueta se indica que el disco tiene una capacidad de 250 GB en realidad usables son unos 232 GiB una vez formateado.

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/18/samsung-840-evo-ssd-5.jpg" title="Samsung SSD 840 EVO 250 GB" data-gallery><img src="assets/images/custom/posts/18/samsung-840-evo-ssd-5-thumb.jpg"></a>
	<a href="assets/images/custom/posts/18/samsung-840-evo-ssd-6.jpg" title="Samsung SSD 840 EVO 250 GB" data-gallery><img src="assets/images/custom/posts/18/samsung-840-evo-ssd-6-thumb.jpg"></a>
</div>

Los SSD aún tienen margen de mejora, seguirán bajando de precio, aumentarán de capacidad y no tardarán mucho en masificarse para dejar de ser un producto a comprar para los que buscan una mejora de rendimiento. Como muestra no hace ni una semana de que lo compre y ya ha bajado de 125 € a 120 €. Un punto donde todavía puede haber dudas es cuanto a la durabilidad, si realizamos tareas que escriban muchos datos en el disco puede que prefiramos usarlo junto con un disco mecánico. En teoría los discos TLC como el <a href="http://www.amazon.es/gp/product/B00P736UEU/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00P736UEU&linkCode=as2&tag=blobit-21">Samsung 850 EVO de 250 GB</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00P736UEU" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> tiene una esperanza de vida de 1000 escrituras por celda, a unos 10 GiB de datos escritos por día la esperanza de vida estimada es de 11.7 años para el modelo de 120 GB y 23.4 para el modelo de 250 GB. Los 10 GiB de escritura por día me parece una estimación escasa para un día de trabajo, en mi trabajo suelo estar entre 20 GiB y 40 GiB de escritura, principalmente porque uso una base de datos MySql que por cada importación escribe al disco unos 14 GiB. También es cierto que los 1000 ciclos de escritura quizá sean algo bajos y las celdas aguanten algo más, probablemente y salvo que le demos mucho uso al disco antes de que falle cambiaremos de equipo.

Si alguien está pensando en comprar un disco SSD lo primero que deberá hacer es calcular el espacio mínimo que va a necesitar para saber que modelo comprar y el uso que le va a dar, una opción de 120 GB puede que sea más que suficiente, se recomienda que el espacio ocupado no sea superior al 75% de la capacidad. Si va usarse de forma intensiva en cuanto a escrituras o se necesita mucho espacio es recomendable seguir usando un disco mecánico. También se puede optar por un SSD para el sistema y un disco duro mecánico USB externo para el almacenamiento de datos, con una caja de disco duro USB externa podemos aprovechar el disco duro mecánico que sustituimos, una opción es <a href="http://www.amazon.es/gp/product/B005MWDD2I/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B005MWDD2I&linkCode=as2&tag=blobit-21">B-Move USB 2.0</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B005MWDD2I" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> que cumple perfectamente la misión además de ser de las cajas USB externas más baratas, las cajas USB 3.0 son algo más caras.

En siguientes artículos comentaré como monitorizar las escrituras a disco, obtener la cantidad de datos escritos y leídos, una guía de instalación de [Arch Linux](https://www.archlinux.org/) con notas específicas para SSD entre otras cosas y una serie de recomendaciones para proteger la esperanza de vida del SSD. En internet hay multitud de análisis sobre este SSD, dos de los que más me han gustado han sido [Testing the endurance of TLC NAND](http://www.anandtech.com/show/6459/samsung-ssd-840-testing-the-endurance-of-tlc-nand) y otro de [xbitlabs](http://www.xbitlabs.com/articles/storage/display/samsung-840-evo.html).

<div class="media-amazon" style="text-align: center;">
    <iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00P736UEU&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
    <iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00KFAGCWK&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00IRRDHUI&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Página de Samsung SSD 840 EVO](http://www.samsung.com/global/business/semiconductor/minisite/SSD/us/html/about/SSD840EVO.html)
* [Página de Samsung SSD 850 EVO](http://www.samsung.com/global/business/semiconductor/minisite/SSD/global/html/ssd850evo/overview.html)
* [Test de resistencia SSD 840 EVO](http://www.anandtech.com/show/6459/samsung-ssd-840-testing-the-endurance-of-tlc-nand)
* [Artículo sobre Samsung 840 EVO](http://www.xbitlabs.com/articles/storage/display/samsung-840-evo.html)
{{% /reference %}}

<div itemscope="" itemtype="http://data-vocabulary.org/Review">
    <span itemprop="itemreviewed">Samsung SSD 840 EVO 250 GB</span><br>
    Revisión por <span itemprop="reviewer">picodotdev</span>
    el <time datetime="2014-03-28" itemprop="dtreviewed">28 de marzo de 2014</time>.<br>
    Valor: <span itemprop="rating">5</span><br>
</div>

{{% /post %}}
