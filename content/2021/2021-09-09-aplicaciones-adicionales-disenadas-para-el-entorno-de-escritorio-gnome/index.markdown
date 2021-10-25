---
pid: 595
type: "post"
title: "Aplicaciones adicionales diseñadas para el entorno de escritorio GNOME"
url: "/2021/09/aplicaciones-adicionales-disenadas-para-el-entorno-de-escritorio-gnome/"
date: 2021-09-09T10:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:gnome-apps.png"
imagePost: "logotype:gnome.svg"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "Los entornos de escritorio proporcionan la interfaz gráfica de las computadoras, el aspecto de las ventanas, los estilos de los componentes junto con unas guías de interfaz de usuario que especifican como han de ser las aplicaciones en ese entorno de escritorio. El entorno de escritorio y las guías permiten que todas las aplicaciones tengan uniformidad visual y en su experiencia  que facilite al usuario el uso de las aplicaciones sin tener que aprender unas convenciones únicas para cada aplicación. La mayoría de entornos de escritorio integran unas pocas aplicaciones esenciales como explorador de archivos, visor de documentos e imágenes, editor de texto, terminal, reproductor de vídeo, navegador web y comunicación y gestor de software. El resto de aplicaciones necesarias son proporcionadas por desarrolladores ajenos al entorno de escritorio que no tienen por que seguir las mismas guías de estilos, sin embargo, algunas aplicaciones aún siendo de terceros si siguen las mismas guías de estilos del entorno de escritorio que proporcionan algunas funcionalidades adicionales a las de las aplicaciones esenciales."
---

{{% post %}}

{{< logotype image1="gnome.svg" >}}

Distribuciones de [GNU][gnu]/[Linux][linux] hay muchas que se diferencian principalmente en si están patrocinadas por una empresa como [Fedora][fedora] con [Red Hat][redhat], [Ubuntu][ubuntu] con [Canonical][canonical] o si están desarrolladas por una comunidad de usuarios como [Debian][debian] o [Arch Linux][archlinux], su modelo de publicación de versiones puede seguir un calendario como Ubuntu o estar en continua actualización como Arch Linux, otras diferencias son su popularidad medido por el número de usuarios que la utilizan o el entorno de escritorio o gestor de arranque instalado por defecto.

Sin embargo, en el uso habitual de cualquier distribución GNU/Linux las diferencias no son tantas, al final una distribución de GNU/Linux es simplemente una recopilación de programas y librerías de software libre. Los programas en cada una de las distribuciones son exactamente los mismos tanto de línea de comandos con los programas de GNU, el entorno de escritorio con sus aplicaciones integradas o las aplicaciones de terceros preinstaladas. Cada distribución suele tener su propia aplicación gestor de paquetes para instalar y desinstalar programas junto a sus dependencias de sus repositorios oficiales pero en esencia tanto el gestor de paquetes de Debian como de Arch Linux realizan la misma función incluso las distribuciones derivadas utilizan el mismo programa gestor de paquetes.

* [Elegir una distribución GNU/Linux según el usuario, uso o equipo][blogbitix-190]
* [Descargar e instalar la distribución Ubuntu de GNU/Linux paso a paso desde cero][blogbitix-232]

En realidad la distribución GNU/Linux que se utilice no es lo más relevante, lo importante son las aplicaciones usadas para realizan las tareas. Aplicaciones hay muchas con diferentes propósitos que se pueden clasificar en las siguientes categorías:

* Ofimática, documentos y escritorio (editor de documentos ofimáticos, editor de textos avanzado, libros electrónicos y cómics, capturador de pantalla, copias de seguridad).
* Internet y comunicaciones (navegador, correo electrónico, lector de _feeds_, descargar archivos torrent y descargas directas, mensajería instantánea, videoconferencia, nube privada).
* Fotos y gráficos (retoque fotográfico).
* Multimedia, vídeo y audio (reproductor de vídeo, audio, películas y música, editor de vídeo, conversor de vídeo y audio).
* Juegos.
* Programación y desarrollo (compiladores, entorno integrado de desarrollo, bases de datos y software de servidor, virtualización).
* Seguridad y privacidad.

En estos artículos he escrito sobre las aplicaciones más conocidas para cada una de las categorías anteriores:

* [Listado de programas esenciales según categoría para GNU/Linux][blogbitix-469]
* [Consola de juegos retro con Lakka y una Raspberry Pi][blogbitix-301]
* [Jugar a videojuegos clásicos y míticos de arcade con Mame en GNU/Linux][blogbitix-170]
* [La aplicación cliente de Steam en GNU/Linux][blogbitix-431]

Estas son las aplicaciones de línea de comandos de GNU/Linux de los cuales son instalados en la mayoría de distribuciones como parte del sistema base.

* [50+ comandos básicos y útiles de GNU/Linux][blogbitix-477]

El propio entorno de escritorio que proporciona la interfaz gráfica también incluye una serie de aplicaciones esenciales básicas que hacen el sistema gráfico usable e incluso sin necesitar aplicaciones de terceros adicionales para los mismos propósitos, sólo son necesarias aplicaciones adicionales de terceros en el caso de requerir funcionalidades más avanzadas o necesidades no cubiertas.

* [Las aplicaciones integradas del entorno de escritorio GNOME][blogbitix-464]
* [Juegos incluidos en el entorno de escritorio GNOME][blogbitix-167]

### Aplicaciones adicionales del círculo de GNOME

Las aplicaciones del entorno de escritorio de GNOME son aplicaciones sencillas que cubren las necesidades más básicas, para propósitos adicionales específicos es necesario instalar aplicaciones desarrolladas por terceros fuera del entorno de escritorio.

El problema de las aplicaciones de terceros, si es que es un problema, es que no tienen por qué seguir las directrices de diseño de la interfaz del usuario de ningún entorno de escritorio. Esta libertad que tienen las aplicaciones de terceros hacen que las aplicaciones no estén integradas con el escritorio al emplear un diseño totalmente diferente. La [guía de estilos de las aplicaciones de GNOME][gnome-hig] contiene recomendaciones y normas que las aplicaciones deben seguir para este entorno de escritorio, estas guías permiten uniformidad en las aplicaciones y contienen una colección de buenas prácticas en el diseño de aplicaciones para hacerlas más fáciles de usar e intuitivas para los usuarios.

{{< image
    gallery="true"
    image1="image:gnome-circle-apps.png" optionsthumb1="200x150" title1="Aplicaciones del círculo de GNOME" >}}
{{< image
    gallery="true"
    image1="image:gnome-circle-apps-1.png" optionsthumb1="200x150" title1="Aplicaciones del círculo de GNOME"
    image2="image:gnome-circle-apps-2.png" optionsthumb2="200x150" title2="Aplicaciones del círculo de GNOME"
    image3="image:gnome-circle-apps-3.png" optionsthumb3="200x150" title3="Aplicaciones del círculo de GNOME"
    caption="Aplicaciones del círculo de GNOME" >}}

Algunas aplicaciones aunque desarrolladas por terceros siguen las guías de estilos de diseño de interfaces del escritorio de GNOME dando la sensación que están perfectamente integradas con el entorno de escritorio. Muchas son aplicaciones distribuidas como paquetes en formato [Flatpak][flatpak] que las hace fácil de instalar en cualquier distribución GNU/Linux sin depender de los paquetes oficiales de la distribución.

* [Flatpak, distribución e instalación de programas de escritorio en las distribuciones GNU/Linux][blogbitix-362]

Las siguientes son aplicaciones sencillas desarrolladas por terceros que siguen las guías de estilos de diseño de interfaces del escritorio de GNOME y que están recomendadas por GNOME, cada una cubre una necesidad específica que si es necesaria permite hacerlo mediante una aplicación con interfaz gráfica en vez de tener que recurrir a otras aplicaciones o la línea de comandos, en conjunto cubren funcionalidades muy diversas, desde un editor de texto en formato _markdown_ hasta un lector de la [Wikipedia][wikipedia].

{{< image
    gallery="true"
    image1="image:apostrophe.png" optionsthumb1="200x150" title1="Aplicaciones del círculo de GNOME"
    image2="image:curtail.png" optionsthumb2="200x150" title2="Aplicaciones del círculo de GNOME"
    image3="image:kooha.png" optionsthumb3="200x150" title3="Aplicaciones del círculo de GNOME" >}}
{{< image
    gallery="true"
    image1="image:pika-backup.png" optionsthumb1="200x150" title1="Aplicaciones del círculo de GNOME"
    image2="image:shortwave.png" optionsthumb2="200x150" title2="Aplicaciones del círculo de GNOME"
    caption="Algunas aplicaciones del círculo de GNOME" >}}

En la descripción de cada aplicación y en su página del repositorio en [Flathub][flathub] están los detalles para instalar la aplicación con Flatpak. Se puede instalar con la aplicación software de GNOME o desde la línea de comandos. Una vez instalada la aplicación Flatpak la mantiene actualizada automáticamente en cada nueva publicación de versión.

{{< image
    gallery="true"
    image1="image:gnome-software.png" optionsthumb1="650x450" title1="Aplicación gestión de software de GNOME"
    caption="Aplicación para instalar y desinstalar software de GNOME" >}}

Comandos para instalar y ejecutar la aplicación con Flatpak, el identificativo de cada aplicación se muestra en la [página de descripción de Apostrophe en Flathub](https://flathub.org/apps/details/org.gnome.gitlab.somas.Apostrophe).

{{< code file="flatpak-install.sh" language="bash" options="" >}}
{{< code file="flatpak-run.sh" language="bash" options="" >}}

* **Apostrophe**: Un editor Markdown elegante y libre de distracciones
* **Authenticador**: Generar códigos de autenticación de doble factor
* **Blanket**: Escuche diferentes sonidos
* **Copia de respaldo Pika**: Respaldos sencillos basados en borg
* **Copias de seguridad Déjà Dup**: Mantenga sus documentos importantes a salvo de cualquier peligro
* **Cozy**: Un moderno reproductor de audiolibros
* **Curtail**: Comprima sus imágenes
* **Decodificador**: Escanear y generar códigos QR
* **Depósito seguro de contraseñas**: Gestionar sus contraseñas
* **Descargador de tipografías**: Instalar tipografías de fuentes en línea
* **Dialect**: Traduce entre idiomas
* **Dibujo**: Una aplicación de dibujo para el escritorio GNOME
* **Fragmentos**: Un cliente de BitTorrent
* **Gaphor**: Herramienta de modelado UML y SysML
* **Hashbrown**: Comrprueba las huellas digitales de sus archivos
* **Health**: Una aplicación para la monitorización de la salud para el escritorio GNOME
* **Identity**: Comparar imágenes y vídeos
* **Khronos**: Medir el tiempo de cada tarea de una forma simple sin obstrucciones
* **Kooha**: Graba tu pantalla elegantemente
* **Limpiador de metadatos**: Ver y limpiar los metadatos en los archivos
* **Mercados**: Un rastreador de acciones, divisas y criptodivisas
* **NewsFlash**: Siga sus blogs y sitios de noticias favoritos.
* **Ofuscador**: Censor de información privada
* **Plots**: Dibujado de gráficas simple
* **Podcasts**: Aplicación de _podcast_ para GNOME
* **Recortador de vídeo**: Recortar vídeos rápidamente
* **Shortwave**: Escuchar la radio por internet
* **Solanum**: Equilibrio entre tiempo de trabajo y de descanso
* **Tangram**: Ejecutar aplicaciones web en su escritorio
* **Tootle**: Cliente rápido para Mastodon
* **Webfont Kit Generator**: Cree fácilmente los _kits_ _@font*face_
* **Wike**: Lector de Wikipedia

{{% /post %}}
