---
pid: 190
title: "Elegir una distribución GNU/Linux según el usuario, uso o equipo"
url: "/2016/10/elegir-una-distribucion-gnu-linux-segun-el-usuario-uso-o-equipo/"
date: 2016-10-29T12:00:00+02:00
updated: 2017-05-13T11:30:00+02:00
rss: true
sharing: true
comments: true
language: "es"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "En GNU/Linux hay multitud de distribuciones, no existe la mejor distribución sino aquella que mejor se adapta según el usuario o sus preferencias. En muchas distribuciones hay posibilidad de elegir diferentes _sabores_ según el entorno de escritorio. Para usuarios nuevos, intermedios, avanzados, para servidor, que requieren pocos recursos o con menor cantidad de software no libre."
---

{{% post %}}

{{< logotype image1="linux.svg" title1="Linux" width1="200" image2="gnu.svg" title2="GNU" width2="200" >}}

Linux es el núcleo del sistema operativo de las distribuciones [GNU][gnu]/[Linux][linux]. GNU es el conjunto de herramientas de usuario que permiten interactuar mediante programas específicos según la tarea con el núcleo Linux. Tanto GNU como Linux son software libre y su licencia otorga al usuario [las 4 libertades que debe poseer todo software libre](https://www.gnu.org/philosophy/free-sw.es.html) para ser calificado como tal. Como las herramientas GNU, Linux y otros proyectos de software libre como los entornos de escritorio [GNOME][gnome], [KDE][kde], [XFCE][xfce], [LXDE][lxde] y programas como [VLC][vlc], [LibreOffice][libreoffice], [Firefox][firefox] entre otros están disponibles en la mayoría de los casos de forma gratuita, grupos de usuarios y organizaciones forman distribuciones que contienen las herramientas GNU, el núcleo Linux, y un gestor de paquetes de los programas para instalarlos de forma sencilla junto con sus dependencias.

En otros sistemas operativos solo hay una versión del sistema operativo o varias pero muy parecidas entre ellas, es el caso de [Windows][windows] o [macOS][macos]. Esto hace que en algunas circunstancias no se adapten bien a las necesidades o preferencias de los usuarios, por ejemplo, equipos antiguos se quedan sin soporte por no poder actualizar a la versión más reciente haciendo que incluso se queden sin actualizaciones de seguridad aún siendo perfectamente útiles. El software libre permite ajustarse mejor a las necesidades del usuario habiendo por ejemplo distribuciones para equipos antiguos que requieren menos recursos. Hace algunos lustros instalar una distribución GNU/Linux no era sencillo para un usuario nuevo o con pocos conocimientos de informática, afortunadamente hoy instalar un sistema GNU/Linux es tan sencillo como instalar Windows o macOS gracias a los instaladores gráficos, sencillos y guiados paso a paso y prácticamente todo el hardware común está soportado y funciona correctamente.

Dado que en GNU/Linux hay muchas distribuciones un nuevo usuario puede sentirse tentado de buscar la «mejor distribución», sin embargo, no existe la «mejor distribución» sino aquella que se mejor se adapta a las preferencias o necesidades de ese usuario. Y también, y no menos importante, una vez elegida una no tiene que ser para siempre, si pasado un tiempo hay otra distribución que pasa a adaptarse mejor a las necesidades o preferencias del usuario puede cambiarse de distribución. Aún así hay algunas distribuciones GNU/Linux que son más usadas que otras por su mayor tiempo de vida o fama y hay distribuciones que son derivadas de otras más grandes.

<div class="media">
  <figure>
    <img src="assets/images/logotypes/ubuntu.svg" alt="Ubuntu" title="Ubuntu" width="100">
    <img src="assets/images/logotypes/opensuse.svg" alt="openSUSE" title="openSUSE" width="100">
    <img src="assets/images/logotypes/debian.svg" alt="Debian" title="Debian" width="100">
    <img src="assets/images/logotypes/archlinux.svg" alt="Arch Linux" title="Arch Linux" width="100">
    <img src="assets/images/logotypes/fedora.svg" alt="Fedora" title="Fedora" width="100">
    <img src="assets/images/logotypes/elementary.svg" alt="Elementary OS" title="Elementary OS" width="100">
    <img src="assets/images/logotypes/trisquel.svg" alt="Trisquel" title="Trisquel" width="100">
    <figcaption>Logotipos de varias de las distribuciones GNU/Linux más importantes y usadas</figcaption>
  </figure>
</div>

Esta categorización que he hecho es general y muchos usuarios avanzados usan distribuciones que en esta categorización incluyo como para usuarios nuevos. Algunas distribuciones publican nuevas versiones cada seis meses o versiones más estables cada dos años con soporte durante mayor periodo de tiempo como Ubuntu y sus <abbr title="Long Term Support">LTS</abbr>. Algunas otras distribuciones se califican como en continua actualización o _rolling release_ que no siguen un calendario de publicaciones en las que en cada actualización completa del sistema se tiene la última versión disponible de cada paquete y programas. Algunas categorías y distribuciones son las siguientes:

* Para nuevo usuario de GNU/Linux:
  * [Ubuntu][ubuntu]: una de las distribuciones más usadas y que más han contribuido a popularizar GNU/Linux. Posee varios sabores según el entorno de escritorio usado GNOME, KDE, XFCE, LXDE.
  * [elementary OS][elementary]: es una distribución con una estética muy cuidada con ciertas similitudes a macOS. Está basada en Ubuntu, tiene buenas opiniones y es una de las mejores candidatas para un usuario recién llegado a GNU/Linux o que quiera un sistema para trabajar sin complicaciones.
  * [Linux Mint][linuxmint]: basada en Ubuntu ha ganado popularidad en la medida que los usuarios de Ubuntu están descontentos con su interfaz de escritorio Unity.
  * [openSUSE][opensuse]: la distribución comunitaria de SUSE. Hace no mucho ha cambiado su política de versionado ofreciendo una más estable basada en la versión empresarial SUSE llamada [openSUSE Leap](https://en.opensuse.org/Portal:Leap) y otra con un modelo _rolling release_ y con las últimas versiones del software llamda [openSUSE Tumbleweed](https://en.opensuse.org/Portal:Tumbleweed).
  * [Debian][debian]: una de las distribuciones más antiguas y con mayor número de derivadas, una de ellas Ubuntu. Posee tres ramas según la confianza de cada una del software que contiene. Para servidores la recomendación es usar la rama estable y para un usuario la rama _testing_ que contiene software más reciente.

{{< figureproc
    image1="ubuntu-installer.png" thumb1="ubuntu-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Ubuntu"
    image2="ubuntu.png" thumb2="ubuntu-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Ubuntu"
    caption="Ubuntu con Unity" >}}
{{< figureproc
    image1="elementary-os-installer.png" thumb1="elementary-os-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de elementary OS"
    image2="elementary-os.png" thumb2="elementary-os-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Elementary OS"
    caption="Elementary OS" >}}
{{< figureproc
    image1="linux-mint-installer.png" thumb1="linux-mint-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Linux Mint"
    image2="linux-mint.png" thumb2="linux-mint-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Linux Mint con Cinnamon"
    caption="Linux Mint con Cinnamon" >}}
{{< figureproc
    image1="opensuse-installer.png" thumb1="opensuse-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de openSUSE"
    image2="opensuse.png" thumb2="opensuse-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="openSUSE con KDE"
    caption="openSUSE con KDE" >}}
{{< figureproc
    image1="debian-installer-1.png" thumb1="debian-installer-1-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Debian (1)"
    image2="debian-installer-2.png" thumb2="debian-installer-2-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Instalador de Debian (2)"
    caption="Instalador de Debian" >}}

* Para usuario intermedio:
  * [Arch Linux][archlinux]: de las distribuciones _rolling release_ es una de las más populares, no posee instalador gráfico y su instalación puede intimidar a un usuario nuevo de GNU/Linux que se hace leyendo la [guía de instalación](https://wiki.archlinux.org/index.php/Installation_guide) y sus referencias, adaptándola cada uno a sus preferencias y equipo y ejecutando comandos en la terminal paso a paso desde la selección desde la disposición del teclado, pasando por el particionado del disco hasta la instalación del cargador de arranque. [arch-anywhere][arch-anywhere] ofrece un instalador que hace más fácil y rápida la instalación de una distribución Arch Linux.
  * [Fedora][fedora]: es la distribución comunitario que ofrece [RedHat][redhat] a los usuarios en la que desarrollan los cambios que luego se incorporan a la distribución empresarial RHEL.

{{< figureproc
    image1="archlinux-installer-1.png" thumb1="archlinux-installer-1-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Arch Linux (1)"
    image2="archlinux-installer-2.png" thumb2="archlinux-installer-2-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Instalador de Arch Linux (2)"
    caption="Instalador de Arch Linux" >}}
{{< figureproc
    image1="archlinux.jpg" thumb1="archlinux-thumb.jpg" options1="2560x1440" optionsthumb1="450x400" title1="Arch Linux con GNOME"
    caption="Arch Linux con GNOME" >}}
{{< figureproc
    image1="arch-anywhere-installer-1.png" thumb1="arch-anywhere-installer-1-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de arch-anywhere (1)"
    image2="arch-anywhere-installer-2.png" thumb2="arch-anywhere-installer-2-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Instalador de arch-anywhere (2)"
    caption="Instalador de arch-anywhere" >}}
{{< figureproc
    image1="fedora-installer.png" thumb1="fedora-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Fedora"
    image2="fedora.png" thumb2="fedora-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Fedora con GNOME"
    caption="Fedora con GNOME" >}}

* Para usuario avanzado:
  * [Gentoo][gentoo]: la instalación de los programas se hace compilando el código fuente. Permite personalizar y obtener el máximo rendimiento del equipo en que sea instalado pero el tiempo de compilación puede ser notable.
* Para servidor:
  * [Ubuntu][ubuntu]: la versión para servidores de Ubuntu, con características empresariales desarrollada por [Canonical][canonical].
  * [RHEL][rhel]: distribución con soporte empresarial y orientada a la empresa creada por RedHat.
  * [CentOS][centos]: distribución basada en RHEL sin el coste del soporte empresarial.
  * [SUSE][suse]: distribución con soporte empresarial y orientada a las empresas.
* Para equipos antiguos: aún con el gran avance que se produce en la tecnología cada pocos meses equipos con algún lustro siguen siendo útiles para muchos usuarios. Con una distribución que use pocos recursos el equipo seguirá siendo válido.
  * [Puppy Linux][puppylinux]
  * [Lubuntu][lubuntu]
  * [Xubuntu][xubuntu]

{{< figureproc
    image1="xubuntu-installer.png" thumb1="xubuntu-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Xubuntu"
    image2="xubuntu.png" thumb2="xubuntu-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Xubuntu con XFCE"
    caption="Xubuntu con XFCE" >}}

* Para usuarios con preferencias de software libre: aunque las distribuciones GNU/Linux son en su mayoría software libre hay algunas partes que no lo son como controladores privativos de la tarjeta gráfica o diversos firmwares del núcleo o controladores. Estas distribuciones tratan de eliminar la mayor parte posible de ese software que no es libre.
  * [gNewSense][gnewsense]
  * [Trisquel][trisquel]

{{< figureproc
    image1="triquel-installer.png" thumb1="triquel-installer-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Instalador de Trisquel"
    image2="trisquel.png" thumb2="trisquel-thumb.png" options2="2560x1440" optionsthumb2="450x400" title2="Trisquel"
    caption="Trisquel" >}}

Estas son solo algunas de las distribuciones más populares y usadas de GNU/Linux y las que recomiendo pero en [DistroWatch][distrowatch] hay un listado más completo. En el libro [Introduction Linux Distros](https://amzn.to/2fol03B) encontraremos una introducción más detallada a GNU/Linux y sus distribuciones.

{{< amazon
    link1="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=1484213939&linkId=f78ab092f9be61313e9c686e15cc3441&internal=1" >}}

[Windows es fácil usarlo sin licencia][blogbitix-119] y es muy común con el consentimiento de la compañía estadounidense, [a Microsoft no le importa que Windows sea usado sin licencia][blogbitix-92], pero como muestro en este artículo hay opciones que en muchos casos no tienen nada que envidiar a los sistemas operativos privativos y en otros aspectos son mucho mejores ya que son software libre generalmente gratuito, no incluyen _bloatware_, software espía y con un soporte durante mayor tiempo que el que ofrecen [Microsoft][microsoft] o [Apple][apple] en sus equipos.

Descarga una distribución GNU/Linux que consideres adecuada para ti o según tus preferencias de escritorio gráfico y pruébalas sin compromiso en una máquina virtual con [VirtualBox][virtualbox] aunque ten en cuenta que la experiencia de uso no será la misma que la instalación sobre el hardware te servirá para comprobar si te gusta. En GNU/Linux encontrarás software, numerosa documentación en la web, gente dispuesta a ayudarte ante cualquier problema o duda que tengas como yo (así que puedes preguntarme :) y varias opciones para cualquier tarea que realices como edición gráfica o de vídeo, programación, música, fotos, compartición P2P, correo electrónico, navegadores web, reproductores de vídeos e incluso numerosos juegos con los que pasar ratos como los [juegos casuales proporcionados por GNOME][blogbitix-167], una [colección de 22 buenos juegos de diferentes géneros][blogbitix-172], [juegos clásicos y míticos de arcade con Mame][blogbitix-170] o [juegos de culto con ScummVM][blogbitix-173].

Lee el artículo [Descargar e instalar la distribución Ubuntu de GNU/Linux paso a paso desde cero][blogbitix-232] para conocer cuales son los pasos para instalar Ubuntu, en sus derivadas es similar.

{{% /post %}}
