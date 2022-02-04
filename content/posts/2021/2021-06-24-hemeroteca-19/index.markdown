---
pid: 583
type: "post"
title: "Hemeroteca #19"
url: "/2021/06/hemeroteca-19/"
date: 2021-06-24T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:blogbitix.svg"
tags: ["blog", "planeta-codigo"]
series: ["hemeroteca"]
library: "true"
---

{{% post %}}

{{< logotype image1="blogbitix.svg" >}}

Ya son casi 600 artículos los que he escrito en el blog, con este número de artículos en alguna ocasión al escribir un nuevo artículo a veces tengo que mirar si ya he escrito sobre ello o algo muy similar para no escribir lo mismo con otras palabras. Estos últimos meses muchas semanas solo he escrito un artículo en vez de dos, un poco por no dedicar tanto tiempo al blog pero más por falta de ideas que no me requieran mucho tiempo de investigación, ideas sobre cosas que me gustaría aprender y compartir tengo pero algunas de ellas para poder escribir me requerirían primero una buena cantidad de tiempo de investigación. Y algunos de esos temas sobre los que me gustaría aprender más son cosas muy específicas ni demasiado populares con lo que para el objetivo de seguir haciendo que el blog tenga más visitas no son los más adecuados.

El trabajo de meses anteriores en los que escrito artículos con ese objetivo de hacer que el blog tenga más visitas se está notando ahora, este marzo ha sido el mejor mes en cuanto a visitas del blog hasta el momento, algunos artículos se han posicionado bien y son artículos sobre los que se hacen muchas búsquedas, ambas cosas combinadas hace que esos artículos contribuyan a aumentar el número de visitas. Haber estado publicando dos artículos durante buena parte del 2020 se ve recompensado ahora. Estos meses han sido el mejor registro de visitas y de ingresos en un mes de época no navideña, los meses de octubre, noviembre y diciembre. Los meses vacacionales siempre suelen bajar algo las visitas con lo que hasta septiembre la métrica de las visitas solo es comparable con los mismos meses de años anteriores.

Los ingresos por publicidad de AdSense se han recuperado también e incluso superado comparado con los primeros meses de pandemia llegando a casi un 1 € diario lo que al final de mes llega a algo más de 30 € de forma holgada. Comparado con los 3 primeros años de vida del blog que no llegaba a los 5 € al mes es un aumento significativo, aún así seguro que es una cifra baja comparada con otros sitios. En el caso de mi blog en gran medida los ingresos dependen de AdSense y esto depende del número de visitas.

Una cosas que me sorprende es que no son pocos los correos electrónicos que recibo con la intención de preguntar, querer insertar enlaces patrocinados en algún artículo o publicar un artículo ya redactado. Aunque no todos llegan a materializarse, también es otra forma de ingreso, aunque más esporádica e irregular que AdSense que continúa siendo la forma principal de ingresos. Sin embargo, alguno ya he rechazado también directamente, si el artículo patrocinado trata sobre apuestas o juegos azar son temas que tengo en mi lista de rechazados por el tema controvertido en sí y porque no están relacionados con los temas principales del blog. Aún así quizá en la siguiente solicitud de estos temas tantee a ver cuánto están dispuestos a ofrecer.

No solo ha sido escribir nuevos artículos, también he realizado algunas pequeñas mejoras en el blog para mantenerlo actualizado. Para cargar las imágenes bajo demanda usaba la librería [lozad](https://apoorv.pro/lozad.js/), lo he cambiado para usar la forma estandarizada que ofrece HTML con el atributo _loading="lazy"_. He actualizado las librerías con las que está construido el blog, principalmente [Bootstrap][bootstrap] para los estilos y [jQuery][jquery] para cierta lógica pasando a las versiones 5 y 3.6. También he realizado mejoras en la accesibilidad, alguna corrección de errores de los que informa Google Console como _content layout shift_. He añadido una nota informativa a los artículos en aquellos que tienen enlaces de afiliación para informar del hecho.

Como ya comenté en hemerotecas anteriores tengo pendiente comprar un dominio propio y migrar a un _hosting_ distinto de [GitHub Pages][github-pages]. En caso de tener que migrar ya tengo más o menos analizado cual sería la opción, en este momento me decantaría por un servidor _cloud_ en [Linode][linode] con [Nginx][nginx] más [Cloudflare][cloudflare] como CDN. Esta infraestructura me permitiría aprender algunas cosas de administración de servidores, [Ansible][ansible], [Terraform][terraform] y [Lets Encrypt][letsencrypt], quizá algo más. Pero de momento no he llegado al límite de GitHub Pages ni he recibido ninguna notificación de que deba cambiar, aún no tengo ninguna necesidad de cambiar con lo que quizá siga en GitHub Pages hasta que me lo notifiquen.

También he seguido haciendo algunas mejoras en el [_script_ de instalación de Arch Linux][alis]. A petición de un usuario que quería soporte para el entorno de escritorio [Deepin][deepin] y como no es mucho esfuerzo añadir un nuevo escritorio lo he añadido. Ahora es posible configurar los módulos de _mkinitcipio_. Con la versión 6.0.0 del gestor de paquetes _pacman_ de [Arch Linux][archlinux] se le ha añadido el soporte para la descarga en paralelo de paquetes, _alis_ ya lo soporta. Añadido [paru](https://github.com/morganamilo/paru) como utilidad para los paquetes de AUR. También algunas corrección de errores y _typos_.

Esta es la lista de los artículos que he publicado durante los seis primeros meses del 2021.

Artículos sobre Java y programación.

* [Documentación para registrar las decisiones de arquitectura en software e infraestructura][blogbitix-547]
* [En Java, ¿los argumentos se pasan por valor o por referencia?][blogbitix-548]
* [Las metodologías OOCSS, BEM y SMACSS para organizar los estilos CSS][blogbitix-551]
* [Introducción a DDD y arquitectura hexagonal con un ejemplo de aplicación en Java][blogbitix-553]
* [Las anotaciones de Java y ejemplo de procesador de anotaciones en tiempo de compilación][blogbitix-556]
* [Novedades de Java 16][blogbitix-560]
* [Los conceptos de encapsulación, herencia, polimorfismo y composición de la programación orientada a objetos][blogbitix-564]
* [Programas basados en consola con Java usando Lanterna][blogbitix-565]
* [Las clases y librerías básicas de Java para bases de datos relacionales][blogbitix-566]
* [Crear de forma sencilla un cliente de un servicio REST o HTTP con Retrofit][blogbitix-569]
* [Qué son los datos estructurados y para que sirven con ejemplos][blogbitix-571]
* [Herramientas para mejorar una página web en SEO, conformidad estándares y rendimiento][blogbitix-573]
* [El patrón de diseño Factory, ventajas sobre new y diferencias con Builder][blogbitix-574]
* [Cómo ordenar arrays y colecciones de objetos en Java][blogbitix-577]
* [Implementación de los algoritmos de ordenación bubble sort, merge sort y quicksort en Java][blogbitix-579]
* [Los autómatas del juego de la vida de Conway y la hormiga Langton con su implementación en Java][blogbitix-582]

Artículos sobre juegos.

* [Análisis y guía del juego de rol For the King][blogbitix-543]
* [Estrategia de batalla básica en el juego World of Warships Legends][blogbitix-545]
* [Análisis de la serie de juegos de plataformas Trine][blogbitix-561]
* [Los mejores juegos gratuitos o free to play de PC, consolas y móvil][blogbitix-575]

Artículos sobre GNU/Linux o software libre.

* [Analítica web en el servidor sin JavaScript en el cliente con GoAccess][blogbitix-544]
* [Analítica web con Matomo como alternativa a Google Analytics][blogbitix-546]
* [Qué es la variable PATH del sistema y cómo cambiarla en GNU/Linux][blogbitix-549]
* [Licencias de software libre y diferencias con software privativo y de código abierto][blogbitix-552]
* [Guía de instalación de GNU/Linux para la Raspberry Pi][blogbitix-554]
* [Cifrado y descifrado como servicio con Vault][blogbitix-557]
* [Acceso simple y seguro a sistemas remotos con Boundary][blogbitix-558]
* [Repositorio de artefactos privado con Nexus][blogbitix-559]
* [Autenticación con segundo factor de autenticación en SSH][blogbitix-562]
* [Convertir texto o imagen a arte de caracteres ASCII][blogbitix-563]
* [Herramientas de reconocimiento de texto en imágenes con OCR][blogbitix-567]
* [Guía de instalación y uso básico de FreeBSD][blogbitix-570]
* [Herramientas de línea de comandos para monitorizar GNU/Linux][blogbitix-578]
* [Cómo crear y poner subtítulos a un vídeo][blogbitix-580]

Otros.

* [Después de dos años de comprar un Intel NUC, ¿cambiaría algo en el futuro?][blogbitix-550]
* [Herramientas para convertir texto a audio de voz natural][blogbitix-555]
* [Historias de un streamer, «Un nuevo reto»][blogbitix-568]
* [La tinta electrónica y los mejores lectores de libros electrónicos][blogbitix-572]
* [Conceptos teóricos generales de los procesadores de computadora][blogbitix-576]
* [Me cambio del ADSL a la fibra de Pepephone][blogbitix-581]

{{% /post %}}
