---
pid: 570
type: "post"
title: "Guía de instalación y uso básico de FreeBSD"
url: "/2021/04/guia-de-instalacion-y-uso-basico-de-freebsd/"
date: 2021-04-30T16:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:freebsd-text.webp"
imagePost: "logotype:freebsd.svg"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "Aún con su reducida cuota de uso las distribuciones GNU/Linux son conocidas por muchas personas como alternativa a los sistemas operativos comerciales Windows de Microsoft y macOS de Apple para propósitos de entorno de escritorio. Las distribuciones BSD también son conocidas, cualquier usuario de GNU/Linux con seguridad las conoce. FreeBSD es una abanderada de las BSD, aún así no son tantos los usuarios que deciden instalarla como su sistema operativo de uso cotidiano. Es fácil y rápido de instalar y a un usuario de GNU/Linux e incluso macOS el cambio al nuevo sistema resulta conocido ya que el entorno de escritorio y muchos comandos son exactamente los mismos."
---

{{% post %}}

{{< logotype image1="freebsd.svg" >}}

La cuota de uso entre los diferentes sistemas operativos para computadoras de escritorio está dominada con más de un 80% por el sistema operativo [Windows][windows] de [Microsoft][microsoft], un sistema operativo comercial con una licencia de uso privativo dirigido a ser fácil de usar incluso para usuarios sin ser expertos en informática. El éxito de Windows se debe a que es la opción preinstalada por la mayoría de ordenadores y portátiles a la venta, posee el paquete ofimático [Office][microsoft-office] también de Microsoft imprescindible en muchos puestos de trabajo, se puede usar sin licencia con algunas limitaciones o se puede [comprar una licencia de Windows 10 completamente válida][blogbitix-514] a un precio significativamente más reducido que el oficial.

El siguiente sistema operativo de escritorio más usado es [macOS][macos] de [Apple][apple], un sistema operativo comercial con licencia privativa exclusivo de las computadoras _Mac_. Los _Mac_ se consideran ordenadores que suelen incorporar las últimas tecnologías y cuidando más diseño que las que se encuentran en ordenadores con Windows, sin embargo, la exclusividad se ve reflejada en un significativo precio comparado con PC de características equivalentes. A pesar del precio son computadoras con cierta popularidad, deseadas y vendidas.

La tercera opción son alguna de [las distribuciones de GNU/Linux][blogbitix-190] siendo una de las más populares [Ubuntu][ubuntu]. Las distribuciones y sistema operativo GNU/Linux se caracterizan por tener una licencia de software libre basada en [la GPL][gnu-gpl] y ser generalmente gratuitas incluyendo la práctica totalidad de los programas. Las distribuciones GNU/Linux actualmente son tan fáciles de instalar y usar que los sistemas operativos Windows o _macOS_, incluyendo ser compatible con la mayoría del hardware. Sin embargo, dado que no hay tantas empresas que vendan y distribuyan equipos con algún GNU/Linux preinstalado no tiene en el escritorio una cuota tan alta como las anteriores, dos empresas que distribuyen GNU/Linux en sus equipos son [Slimbook][slimbook] y [Vant][vant]. En el área de los servidores y computación en la nube, por el contrario, GNU/Linux sí es la opción que domina el mercado, por su licencia, flexibilidad, fiabilidad y potencia.

La cuarta opción mucho menos conocida y usada son alguna de las distribuciones BSD. Las distribuciones BSD también tienen una licencia de software libre incluso más permisiva que la licencia GPL. La mayor permisividad de las licencias BSD la hace atractiva para algunas empresas, que al contrario de la licencia GPL con la licencia BSD no están obligadas a distribuir el código fuente de los programas lo que les permite proteger sus desarrollos de competidores. Es utilizada por empresas como [Netflix][netflix], [Sony][sony] en las consolas [PlayStation][playstation] e incluso macOS está basado en partes de BSD. Aunque las licencias BSD permiten usar el software sin obligar a colaborar en el desarrollo de su software se ven beneficiadas por colaboraciones puntuales dado que las empresas están interesadas en mejorar el software en el basan su negocio.

{{< tableofcontents >}}

## La distribución FreeBSD

Entre las distribuciones BSD una popular es [FreeBSD][freebsd], que es posible utilizar con propósitos de computadora de escritorio, su uso también está destinado a software de servidor que es conocida por su pila TCP/IP de gran rendimiento para comunicación por red.

El mérito de las distribuciones BSD como FreeBSD es ciertamente notable, aunque no tienen la popularidad ni el apoyo por parte de las empresas ni disponer del mismo número de desarrolladores dedicado a su mejora de las distribuciones GNU/Linux consigue proporcionar un sistema operativo de gran calidad con funcionalidades equivalentes a las existentes GNU/Linux o incluso más innovadoras con un modelo de desarrollo diferente de GNU/Linux.

* [Las diferencias entre GNU/Linux, BSD y sus distribuciones][blogbitix-498]

Es perfectamente capaz de realizar las tareas comunes con el propósito de ordenador de escritorio. Posee programas de navegadores web, multimedia como vídeo y audio, ofimática, visor de documentos e imágenes y una colección formada por más de 36000 paquetes de software compatibles. Otras funciones de software que posee son [virtualización](https://docs.freebsd.org/en/books/handbook/virtualization), [Jails](https://docs.freebsd.org/en/books/handbook/jails) para crear procesos separados del resto del sistema que es la base de [los contenedores de Docker][blogbitix-49], [DTrace][dtrace] también desarrollada originalmente por [Sun Microsystems][sun-microsystems], [WINE][wine] para ejecutar programas de Windows y la posibilidad de agregar [compatibilidad binaria con Linux](https://docs.freebsd.org/en/books/handbook/linuxemu) lo que hace posible utilizar programas cuando no están disponibles de forma nativa.

La mayor dificultad de usar FreeBSD es en que sea compatible con todo el hardware de la computadora, si bien la compatibilidad con el hardware en GNU/Linux actualmente no es un problema en FreeBSD dado el mayor limitado de desarrolladores que tiene el interés de las compañía en ofrecer soporte para su hardware puede presentar algún problema de compatibilidad.

Las novedades de destacadas de FreeBSD 13 publicada en abril de 2021 es el soporte de la arquitectura de procesadores _arm64_ al mismo nivel que la arquitectura de procesadores _amd64_, actualización de paquetes de compiladores, eliminación de algunos programas obsoletos de GNU, soporte en el _kernel_ de TLS y otras mejoras de menor relevancia.

{{< image
    gallery="false"
    image1="logotype:freebsd.svg" optionsthumb1="200x150" title1="FreeBSD"
    image2="logotype:beastie.svg" optionsthumb2="200x150" title2="Beastie"
    caption="Logotipo y mascota de FreeBSD" >}}

### El sistema de archivos ZFS, ¿qué lo hace diferente?

Una de las opciones como sistema de archivos es la posibilidad de utilizar ZFS. La licencia BSD permite utilizar más fácilmente el avanzado sistema de archivos ZFS desarrollado originalmente por [la difunta Sun Microsystems][blogbitix-355].

Lo que hace diferente a ZFS es que combina el rol de gestor de volúmenes con el rol de sistema de archivos. El sistema de archivos ZFS es consciente de la estructura subyacente de discos. Al contrario que los sistemas de archivos tradicionales que solo pueden crearse en un único disco de almacenamiento, esto obliga a que si hay dos discos sea necesario crear dos sistemas de archivos diferentes.

La combinación de ZFS como gestor de volúmenes y sistema de archivos permite la creación de varios sistemas de archivos todos compartiendo un conjunto de almacenamiento disponible. Una de las mayores ventajas de ZFS de la disposición física de los discos es que los sistemas de archivos existentes pueden ser agrandados automáticamente al añadir discos adicionales al conjunto. Este nuevo espacio queda disponible para todos los sistemas de archivos. También tiene propiedades que pueden ser aplicadas a cada sistema de archivos en vez de crear un único sistema de archivos monolítico.

{{< image
    gallery="false"
    image1="logotype:openzfs.svg" optionsthumb1="200x150" title1="OpenZFS"
    caption="OpenZFS" >}}

## Guía de instalación

El primer paso de la instalación es descargar la última de las imágenes de medio de instalación disponibles, ya sea la versión de menor tamaño para realizar la instalación por red o la más completa sin necesidad de descargar nada adicionalmente desde la [página de descargas de FreeBSD](https://www.freebsd.org/where/). En caso de duda en la instalación tiene disponible un [manual completo de la instalación y uso](https://docs.freebsd.org/en/books/handbook/) o como documentación de referencia.

En este caso como primera toma de contacto con FreeBSD realizo la instalación en una máquina virtual con [VirtualBox][virtualbox] que está disponible también para Windows como macOS que permite probarla sin necesidad de eliminar el sistema existente en la computadora. En la definición de la máquina virtual se especifican las características de la máquina virtualizada desde el tamaño de memoria, almacenamiento de disco o tipo de BIOS, adicionalmente se proporciona la imagen del medio de instalación y se inserta en la unidad DVD virtual de la máquina.

El siguiente [error de VirtualBox impide iniciar FreeBSD en modo UEFI](https://www.virtualbox.org/ticket/19910), hasta que no sea resuelto requiere iniciar el sistema como BIOS, para la prueba el tipo de sistema BIOS o UEFI no tiene relevancia.

{{< image
    gallery="true"
    image1="image:virtualbox-freebsd.webp" optionsthumb1="300x200" title1="Configuración de máquina virtual con VirtualBox"
    caption="Configuración de máquina virtual con VirtualBox" >}}

La instalación de FreeBSD utiliza un asistente que guía y hace la instalación sencilla, rápida y fiable. Descargado el medio de instalación e iniciado el sistema desde él se presenta un menú de opciones que permiten iniciar la instalación. En los diferentes pasos del asistente se van introduciendo las preferencias de configuración desde la disposición del teclado, particionado del sistema de almacenamiento, copiado de archivos del sistema base, establecimiento de contraseña del usuario _root_, configuración de red, zona horaria y usuarios adicionales.

Inicio de la instalación.

{{< image
    gallery="true"
    image1="image:instalacion-freebsd-1.webp" optionsthumb1="200x150" title1="Inicio de la instalación de FreeBSD"
    image2="image:instalacion-freebsd-2.webp" optionsthumb2="200x150" title2="Inicio de la instalación de FreeBSD"
    image3="image:instalacion-freebsd-3.webp" optionsthumb3="200x150" title3="Inicio de la instalación de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:instalacion-freebsd-4.webp" optionsthumb1="200x150" title1="Inicio de la instalación de FreeBSD"
    image2="image:instalacion-freebsd-5.webp" optionsthumb2="200x150" title2="Inicio de la instalación de FreeBSD"
    image3="image:instalacion-freebsd-6.webp" optionsthumb3="200x150" title3="Inicio de la instalación de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:instalacion-freebsd-7.webp" optionsthumb1="200x150" title1="Inicio de la instalación de FreeBSD"
    image2="image:instalacion-freebsd-8.webp" optionsthumb2="200x150" title2="Inicio de la instalación de FreeBSD"
    image3="image:instalacion-freebsd-9.webp" optionsthumb3="200x150" title3="Inicio de la instalación de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:instalacion-freebsd-10.webp" optionsthumb1="200x150" title1="Inicio de la instalación de FreeBSD"
    image2="image:instalacion-freebsd-11.webp" optionsthumb2="200x150" title2="Inicio de la instalación de FreeBSD"
    image3="image:instalacion-freebsd-12.webp" optionsthumb3="200x150" title3="Inicio de la instalación de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:instalacion-freebsd-13.webp" optionsthumb1="200x150" title1="Inicio de la instalación de FreeBSD"
    caption="Inicio de la instalación de FreeBSD" >}}

El siguiente paso es automático, consiste en la copia de los archivos base del sistema.

{{< image
    gallery="true"
    image1="image:progreso-instalacion-freebsd-1.webp" optionsthumb1="200x150" title1="Copia de archivos base de FreeBSD"
    image2="image:progreso-instalacion-freebsd-2.webp" optionsthumb2="200x150" title2="Copia de archivos base de FreeBSD"
    image3="image:progreso-instalacion-freebsd-3.webp" optionsthumb3="200x150" title3="Copia de archivos base de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:progreso-instalacion-freebsd-4.webp" optionsthumb1="200x150" title1="Copia de archivos base de FreeBSD"
    image2="image:progreso-instalacion-freebsd-5.webp" optionsthumb2="200x150" title2="Copia de archivos base de FreeBSD"
    image3="image:progreso-instalacion-freebsd-6.webp" optionsthumb3="200x150" title3="Copia de archivos base de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:progreso-instalacion-freebsd-7.webp" optionsthumb1="200x150" title1="Copia de archivos base de FreeBSD"
    image2="image:progreso-instalacion-freebsd-8.webp" optionsthumb2="200x150" title2="Copia de archivos base de FreeBSD"
    caption="Copia de archivos base de FreeBSD" >}}

A continuación se sigue con la configuración básica del sistema para la conectividad de red, usuarios, fecha y servicios demonio.

{{< image
    gallery="true"
    image1="image:configuracion-freebsd-1.webp" optionsthumb1="200x150" title1="Configuración de FreeBSD"
    image2="image:configuracion-freebsd-2.webp" optionsthumb2="200x150" title2="Configuración de FreeBSD"
    image3="image:configuracion-freebsd-3.webp" optionsthumb3="200x150" title3="Configuración de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:configuracion-freebsd-4.webp" optionsthumb1="200x150" title1="Configuración de FreeBSD"
    image2="image:configuracion-freebsd-5.webp" optionsthumb2="200x150" title2="Configuración de FreeBSD"
    image3="image:configuracion-freebsd-6.webp" optionsthumb3="200x150" title3="Configuración de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:configuracion-freebsd-7.webp" optionsthumb1="200x150" title1="Configuración de FreeBSD"
    image2="image:configuracion-freebsd-8.webp" optionsthumb2="200x150" title2="Configuración de FreeBSD"
    image3="image:configuracion-freebsd-9.webp" optionsthumb3="200x150" title3="Configuración de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:configuracion-freebsd-10.webp" optionsthumb1="200x150" title1="Configuración de FreeBSD"
    image2="image:configuracion-freebsd-11.webp" optionsthumb2="200x150" title2="Configuración de FreeBSD"
    image3="image:configuracion-freebsd-12.webp" optionsthumb3="200x150" title3="Configuración de FreeBSD" >}}
{{< image
    gallery="true"
    image1="image:configuracion-freebsd-13.webp" optionsthumb1="200x150" title1="Configuración de FreeBSD"
    caption="Configuración de FreeBSD" >}}

Se ha de establecer la contraseña del usuario _root_ que otorga permisos de superusuario. También se da la oportunidad de crear los usuarios en el sistema.

{{< image
    gallery="true"
    image1="image:root-password-freebsd.webp" optionsthumb1="200x150" title1="Contraseña de superusuario en FreeBSD"
    image2="image:usuarios-freebsd-1.webp" optionsthumb2="200x150" title2="Creación de usuarios en FreeBSD"
    image3="image:usuarios-freebsd-2.webp" optionsthumb3="200x150" title3="Creación de usuarios en FreeBSD"
    caption="Contraseña de superusuario y creación de usuarios en FreeBSD" >}}

Las últimas pantalla indican la finalización de la instalación.

{{< image
    gallery="true"
    image1="image:finalizacion-instalacion-1.webp" optionsthumb1="200x150" title1="Finalización de la instalación de FreeBSD"
    image2="image:finalizacion-instalacion-2.webp" optionsthumb2="200x150" title2="Finalización de la instalación de FreeBSD"
    image3="image:finalizacion-instalacion-3.webp" optionsthumb3="200x150" title3="Finalización de la instalación de FreeBSD"
    caption="Finalización de la instalación de FreeBSD" >}}

### Primer inicio en FreeBSD

Completada la instalación al reiniciar el sistema se presenta la pantalla del cargador de arranque y posteriormente la de inicio de sesión en modo texto a partir de la cual es posible instalar programas adicionales y un entorno de escritorio con interfaz gráfica.

{{< image
    gallery="true"
    image1="image:cargador-arranque-freebsd.webp" optionsthumb1="200x150" title1="Inicio de sesión en modo texto de FreeBSD"
    image2="image:inicio-sesion-texto-freebsd-1.webp" optionsthumb2="200x150" title2="Inicio de sesión en modo texto de FreeBSD"
    image3="image:inicio-sesion-texto-freebsd-2.webp" optionsthumb3="200x150" title3="Inicio de sesión en modo texto de FreeBSD" >}}

### Instalación de entorno de escritorio

La instalación del entorno de escritorio requiere instalar el servidor gráfico y el entorno de escritorio deseado. Los entornos de escritorio disponibles son los mismos que están disponibles en GNU/Linux entre ellos [GNOME][gnome], [KDE][kde] y [XFCE][xfce] con lo que la experiencia de usuario es en gran medida la misma que en una distribución GNU/Linux, la mayor diferencia es que las versión del entorno de escritorio de FreeBSD disponible sea la versión anterior o tarde más tiempo en estar disponible desde su publicación.

A partir de la consola de inicio de sesión en modo texto hay que instalar los paquete del servidor gráfico [Xorg][xorg], el paquete del entorno de escritorio deseado y cambiar un archivo de configuración si se desea iniciar el entorno de escritorio al inicio del sistema.

{{< code file="pkg-gnome.sh" language="bash" options="" >}}

Para iniciar el gestor de sesión GNOME en el inicio del sistema hay que cambiar la configuración en los archivos _/etc/rc.conf_ y _~/.xinitrc_.

{{< code file="rc-gnome.conf" language="plain" options="" >}}
{{< code file="xinitrc.conf" language="plain" options="" >}}

Para establecer como idioma el español se requiere añadir la siguiente configuración en el archivo _/usr/local/etc/gdm/locale.conf_.

{{< code file="locale.conf" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:gnome-freebsd-1.webp" optionsthumb1="200x150" title1="GNOME en FreeBSD"
    image2="image:gnome-freebsd-2.webp" optionsthumb2="200x150" title2="GNOME en FreeBSD"
    image3="image:gnome-freebsd-3.webp" optionsthumb3="200x150" title3="GNOME en FreeBSD"
    caption="GNOME en FreeBSD" >}}

## Uso básico

FreeBSD proporciona una colección de programas básicos de línea de comandos incorporados en el sistema, al contrario que en GNU/Linux estos programas están desarrollados por los mismos desarrolladores del núcleo del sistema operativo y se actualizan como una unidad en cada versión en vez de individualmente.

Instalado el sistema dos tareas básicas son la instalación de programas adicionales y la actualización del sistema para obtener nuevas versiones de los programas, correcciones de errores y actualizaciones de seguridad.

{{< image
    gallery="true"
    image1="image:uname-freebsd.webp" optionsthumb1="300x200" title1="Comando uname en FreeBSD"
    image2="image:df-freebsd.webp" optionsthumb2="300x200" title2="Comando df en FreeBSD"
    caption="Comandos uname y df en FreeBSD" >}}

### Obtener permisos de superusuario _root_

Por seguridad algunas tareas administrativas y de edición de archivos de configuración requieren permisos de superusuario _root_. Esto es posible iniciando sesión en el sistema como el usuario _root_ o obteniendo los privilegios de superusuario con el programa _su_ o _sudo_.

Hay un único superusuario en el sistema cuyo nombre de usuario es _root_, este usuario tiene permisos para realizar cualquier acción en el sistema. La contraseña es necesaria para iniciar sesión, es la proporcionada en la instalación del sistema, y se solicita al obtener privilegios de superusuario en la línea de comandos con el comando _su_. El resto de usuarios distintos a _root_ son usuarios regulares, para que un usuario regular obtenga privilegios de supersusaurio se ha de utilizar el comando _su_ pertenecer al grupo _wheel_.

{{< code file="pw-add-user-to-wheel.sh" language="bash" options="" >}}
{{< code file="su.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:su.webp" optionsthumb1="300x200" title1="Obtener privilegios de superusuario"
    caption="Obtener privilegios de superusuario" >}}

### Instalación de paquetes

La finalidad de cualquier sistema operativo es permitir realizar tareas productivas en él a través de los programas. Para usar un programa es necesario instalarlo previamente en el sistema. 

FreeBSD diferencia los programas de dos formas en como están distribuidos, los paquetes con binarios precompilados para su fácil y rápida instalación y los _ports_ que requieren la compilación a partir del código fuente. En este sentido los paquetes de de FreeBSD son similares a los de los repositorios oficiales [Arch Linux][archlinux] y los _ports_ al [repositorio AUR][archlinux-aur] que requieren igualmente ser compilados a partir del código fuente.

La instalación de un paquete se realiza con el comando _pkg_ para buscar su nombre e instalarlo.

{{< code file="pkg-search.sh" language="bash" options="" >}}
{{< code file="pkg-install.sh" language="bash" options="" >}}

### Actualización del sistema

Una vez realizada la instalación en el futuro se publicarán nuevas versiones de los programas. Para mantener el sistema actualizado hay que ejecutar de forma periódica un comando de actualización.

* [Updating and Upgrading FreeBSD](https://docs.freebsd.org/en/books/handbook/cutting-edge/)

La actualización de parches de seguridad de FreeBSD se realiza con los siguientes dos comandos. El sistema base del sistema operativo de FreeBSD se actualiza independientemente de los programas instalados como paquetes.

{{< code file="freebsd-update.sh" language="bash" options="" >}}

El siguiente comando permite revertir la actualización en caso de algún error.

{{< code file="freebsd-update-rollback.sh" language="bash" options="" >}}

Para actualizar a versiones mayores se realiza con el siguiente comando en el que se indica la versión a la que actualizar.

{{< code file="freebsd-update-upgrade.sh" language="bash" options="" >}}

Los paquetes se actualizan independientemente del sistema base de FreeBSD con el siguiente comando.

{{< code file="pkg-upgrade.sh" language="bash" options="" >}}

En caso de una actualización a una versión mayor de FreeBSD se requiere una reinstalación de todos los paquetes y _ports_ con los siguientes comandos.

{{< code file="freebsd-pkg-upgrade.sh" language="bash" options="" >}}

## Obtener más ayuda

La cuota de uso de GNU/Linux no es muy grande en el escritorio pero tiene un grupo de usuarios muy activo que comparte gran cantidad de información, es difícil no encontrar una respuesta a una duda o problema y es fácil obtener respuesta a una pregunta.

El grupo de usuario de FreeBSD es más reducido y por tanto encontrar respuestas a algo concreto no muy común es más difícil, aún siendo un grupo de usuarios más reducido se caracteriza por recibir calurosamente a los nuevos usuarios de FreeBSD. Una buena forma de obtener respuestas y soporte son los foros de usuario y las listas de distribución. Aunque no hay tantos canales de YouTube y artículos en _blogs_ es posible encontrar información sobre FreeBSD a través de los buscadores.

* [Foros de FreeBSD](https://forums.freebsd.org/)
* [Listas de distribución](https://www.freebsd.org/community/mailinglists/)
* [BSD Now](https://www.bsdnow.tv/) un _podcast_ semanal sobre las distribuciones BSD

### Libros

También hay algunos libros dedicados en exclusiva a FreeBSD como [Absolute Freebsd: The Complete Guide To FreeBSD](https://amzn.to/3nLLi0h), [Design and Implementation of the FreeBSD Operating System](https://amzn.to/3nCspgc), [FreeBSD Device Drivers: A Guide for the Intrepid](https://amzn.to/3e3an3p), [FreeBSD Mastery: ZFS](https://amzn.to/3e4mU6t) y [FreeBSD Mastery: Advanced ZFS](https://amzn.to/2RaJ26j).

{{< amazon
    linkids="https://amzn.to/3SgFyeu,https://amzn.to/3SghSH5,https://amzn.to/3HAVhQH,https://amzn.to/3vYlLsU,https://amzn.to/3SkyzRC"
    asins="1593278926,0321968972,1593272049,1642350001,164235001X" 
    titles="Absolute FreeBSD,Design and Implementation of the FreeBSD Operating System,FreeBSD Device Drivers: A Guide for the Intrepid,FreeBSD Mastery: ZFS: 7,FreeBSD Mastery: Advanced ZFS: 9" >}}

Eso es todo lo básico para empezar a usar FreeBSD, si alguién lee este artículo y usa FreeBSD u otra distribución BSD como sistema principal o en el trabajo me encantaría conocer más detalles así que si quieres puedes dejar un comentario, también si a alguien que acceda al artículo y lo lea seguro que le resulta interesante como forma de animarse a probar este sistemas operativo de la familia UNIX.

{{% /post %}}
