---
pid: 204
type: "post"
title: "Script para instalar Arch Linux de forma fácil, rápido, desatendido, automatizado y personalizable"
url: "/2017/01/script-para-instalar-arch-linux-de-forma-facil-rapido-desatendido-automatizado-y-personalizable/"
aliases: ["/2017/01/script-para-instalar-arch-linux-facil-rapido-desatendido-automatizado-y-personalizable/", "/2017/01/script-de-instalacion-de-arch-linux-desatendido-automatizado-y-personalizable/"]
date: 2017-01-13T00:00:00+01:00
updated: 2020-06-20T20:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
imagePost: "logotype:archlinux.svg"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "Arch Linux es una de las distribuciones más personalizables de GNU/Linux pero cuya instalación requiere leer una buena cantidad de documentación, guías o manuales para saber que comandos ejecutar paso a paso en el _prompt_ del sistema en el que te deja se medio de instalación. Algunos usuarios eligen una distribución como Antergos, KaOS o Manjaro con un instalalador gráfico y guiado simplemente por el hecho de no enfrentarse a la instalación de Arch Linux desde la línea de comandos. Aún así, ejecutar un comando y esperar a que termine para introducir otro es lento y requiere de atención. "
---

{{% post %}}

{{< logotype image1="archlinux.svg" image2="linux.svg" >}}

Hace tiempo que instalar una distribución [GNU][gnu]/[Linux][linux] no tiene más dificultad que instalar un sistema operativo como [Windows][windows], que consiste en descargar la imagen ISO del CD o DVD, grabarla en un CD, DVD o memoria USB e iniciar el sistema con el medio. Las [distribuciones aconsejadas para usuarios que provienen de Windows o macOS][blogbitix-190] o no tiene muchos conocimientos informáticos poseen instaladores gráficos o basados en texto y guiados en varios pasos hasta completar la instalación en menos de media hora. Distribuciones como [Ubuntu][ubuntu], [elementary OS][elementary] o [Linux Mint][linuxmint] hacen que la instalación no requiera conocimientos y sea completada con éxito por cualquier usuario.

{{< image
    gallery="true"
    image1="image:debian-installer-text.png" optionsthumb1="300x200" title1="Instalador de Debian en modo texto"
    image2="image:debian-installer-graphics.png" optionsthumb2="300x200" title2="Instalador de Debian en modo gráfico"
    caption="Instalación guíada de Debian basada en texto y gráfica" >}}

Otras distribuciones basadas en principios diferentes y usuarios a los que está destinadas con otras necesidades o preferencias puede que requieran algo más de conocimientos a cambio de realizar configuraciones más avanzadas como el particionado del disco, cifrado del disco o software que se instala. Algunas distribuciones como [Arch Linux][archlinux] incluso no proporcionan ningún instalador.

> It is targeted at the proficient GNU/Linux user, or anyone with a do-it-yourself attitude who is
> willing to read the documentation, and solve their own problems.

{{< tableofcontents >}}

### La instalación del sistena con Arch Linux

En Arch Linux con su [forma de hacer las cosas](https://wiki.archlinux.org/index.php/Arch_Linux) deja al usuario el poder de personalizar el sistema completamente a sus necesidades y preferencias únicamente limitado por su determinación de conseguirlo. Solo proporciona [el medio con el que iniciar la instalación][archlinux-download] que comienza con una terminal y un _prompt_ del sistema, una [guía de instalación][archlinux-install-guide] junto con las [recomendaciones generales][archlinux-general-recomendations] y [una de las mejores fuentes de información en GNU/Linux][archlinux-wiki] además de completa de cada aspecto que necesitemos en la instalación. Después de haber leído las páginas relevantes de la wiki de Arch Linux, seguramente más de una y dos veces, hasta comprenderla en su mayor parte se empieza a realizar la receta que contenga los comandos necesarios hasta completar la instalación.

En multitud de blogs y vídeos de YouTube hay guías que contienen los comandos y las explicaciones necesarias. Cualquier usuario de Arch Linux con un blog seguramente ha publicado un artículo con su guía de instalación (sí, [yo también publiqué la mía][blogbitix-22]). Un usuario que quiera instalar Arch Linux debe leer varios de esos artículos además de la guía oficial de instalación. Según la filosofía de Arch Linux este proceso de aprendizaje se considera necesario y permite una mayor comprensión del sistema.

Pero saber los comandos que hay que introducir para realizar la instalación de Arch Linux no evita tener que teclearlos uno de tras de otro y esperar a que termine el anterior para introducir el siguiente. Tampoco todas las personas tienen el tiempo para realizarlo. Varias [distribuciones derivadas de Arch Linux](https://wiki.archlinux.org/index.php/Arch-based_distributions) como [Antergos][antergos], [KaOS][kaos] o [Manjaro][manjaro] ofrecen los instaladores gráficos y guiados más amigables que atraen a algunos usuarios buscando usar Arch Linux pero no pasar por su poco amigable proceso de instalación. Por fortuna al ser una distribución _rolling release_ (en constante actualización) solo hay que hacer una única instalación por equipo en principio durante toda su vida de uso. Pero incluso para los usuarios de Arch Linux realizar una segunda instalación en un nuevo equipo es cansina.

Hace unos meses conocí [arch-anywhere][arch-anywhere] que básicamente es un _script_ de bash, con un instalador guiado y basado en texto que hace menos laboriosa la instalación de Arch Linux de forma similar a las existentes en otras distribuciones más amigables. Sin embargo, hay un dos cosas que no me convencen de _arch-anywhere_, una es que no es desatendido requiriendo contestar a varias preguntas de forma interactiva, esperar a que termine ejecute algún comando según la contestación anterior y contestar a la siguiente pregunta. Otra cosa que no me convence es que usa una imagen ISO propia y no la original de Arch Linux. Por lo demás, ofrece un buen nivel de personalización cubriendo las necesidades más comunes de los usuarios como personalizar el particionado, elegir el sistema de archivos, si se quiere LVM, cifrado, el entorno de escritorio (GNOME, KDE, XFCE, ...), kernel, cargador de arranque (GRUB) y programas a instalar.

### El instalador alis y características soportadas

Basándome en la idea de _arch-anywhere_ y dedicando un poco de tiempo he creado un _script_ en [bash][bash] para instalar Arch Linux de forma automatizada, desatendida y personalizable hasta cierto punto aunque siendo útil para los casos de configuraciones más comunes, sin ser imprescindible leer unas decenas de páginas de la wiki de Arch Linux ni seguir ninguna guía o tutorial paso a paso. Algunas de las funcionalidades que soporta son:

* **Sistema**: UEFI, BIOS
* **Almacenamiento**: SATA, NVMe i MMC
* **Cifrado**: partición root cifrada y no cifrada
* **Particionamiento**: no LVM, LVM, LVM on LUKS, GPT on UEFI, MBR on BIOS
* **Sistema de archivos**: ext4, btrfs (with subvols), xfs, f2fs, reiserfs
* **Kernels**: linux, linux-lts, linux-hardened, linux-zen
* **Entorno de escritorio**: GNOME, KDE, XFCE, Mate, Cinnamon, LXDE, i3-wm, i3-gaps
* **Gestor de inicio de sesión**: GDM, SDDM, Lightdm, lxdm
* **Controlador gráfico**: intel, nvidia y amd con opcionalmente inicio KMS. Con intel opcionalmente fastboot, aceleración de vídeo por hardware y compresión de framebuffer.
* **Cargador de arranque**: GRUB, rEFInd, systemd-boot
* **Shell personalizado**: bash, zsh, dash, fish
* **Red inalámbrica WIFI** en la instalación
* **TRIM periódico** para almacenamiento SSD
* **Microcódigo de procesador** para Intel y AMD
* **Archivo swap** opcional
* **VirtualBox guest additions**
* **Conpresión del Kernel compression** y **parámetros personalizados**
* **Creación de usuarios** y **adición a grupo sudoers**
* **Habilitar y deshabilitar units de systemd**
* Soporte **Multilib**
* **Instalación de paquetes**
* Instalación de utilidad Flatpak e **instalación de paquetes Flatpak**
* Instalación de utilidad SDKMAN e **installation de paquetes SDKMAN**
* Instalación de utilidad AUR (paru, yay, aurman) e **installation de paquetes AUR**
* **Instalación de paquetes después de la inatalación del sistema base** (forma preferida para la instalación de paquetes)
* Script para la descarga, **scripts de recuperación** y archivos de configuración
* **Reintento de descarga de paquetes** al haber errores de conexión on espejo
* **Soporte Packer** para pruebas en VirtualBox
* **Tradas de instalación** con todos los comandos ejecutados y su salida en un archivo o **vídeo de asciinema**
* Espera después de la instalación para un **reinicio abortable**
* Posibilidad de crear un _fork_ y **usar configuración propia**

La forma aconsejada de instalar Arch Linux es aprendiendo cuales son los comandos a ejecutar y que hace cada uno de ellos, primero lee la guía oficial de instalación de Arch Linux y comprende que hacen los comandos del _script_. Este _script_ no es oficial y por ello en el [foro de Arch Linux en español][archlinux-forums-es] o [en inglés][archlinux-forums] no podrán darte soporte aunque yo en los comentarios de este artículo te ayudaré si preguntas. Ten en cuenta que este _script_ elimina toda la información del dispositivo de instalación. Si lo pruebas deja un comentario al final de artículo.

Iniciado el sistema con la imagen ISO de Arch Linux de instalación, hay que descargar el _script_, editar algunas variables de entorno para la configuración de alis e iniciar el proceso de instalación. Dependiendo del entorno de escritorio preferido, si se desea LVM, el tipo del sistema de archivos, si se desea cifrar el sistema de archivos y paquetes a instalar los valores de las variables variarán según las preferencias que inicialmente tienes unos valores comunes. Dos variables que hay que modificar al menos son _USER\_NAME_ y _USER\_PASSWORD_ con el nombre del usuario y su contraseña que vaya a utilizar el sistema.

El tiempo requerido de instalación varía según el ancho de banda de la conexión a internet y del tipo de almacenamiento, en mi caso con ADSL con una descarga de 1.2 MiB/s y SSD la instalación del sistema base sin entorno de escritorio me tarda aproximandamente 10 minutos. Con una conexión de fibra tardará sensiblemente menos tiempo. El _script_ de instalación contiene con los mismos comandos que serían necesarios para realizar la instalación manualmente de forma interactiva.

Aunque no se utilice alis, el código bash del _script_ puede verse como documentación ejecutable o como referencia si se opta por hacer la instalación manualmente. Contiene los mismos comandos que un usuario ejecutaría manualmente uno detrás de otro en una instalación interactiva. [alis][alis] es un _script_ que permite hacer la instalación de Arch Linux de forma desatendida, automatizada y personalizable de Arch Linux.

Estos son los comandos de uso de alis y su archivo de configuración con el que personalizar la instalación.

{{< code file="alis-install.sh" language="bash" options="" >}}
{{< code file="alis.conf" language="bash" options="" >}}

### Capturas de diferentes entornos de escritorio soportados

Estas son algunas capturas de pantalla con diferentes entornos de escritorio que he probado con VirtualBox.

{{< image
    gallery="true"
    image1="image:archlinux-gnome.jpg" optionsthumb1="300x200" title1="GNOME"
    image2="image:archlinux-kde.jpg" optionsthumb2="300x200" title2="KDE"
    caption="Entornos de escritorio GNOME y KDE" >}}
{{< image
    gallery="true"
    image1="image:archlinux-xfce.jpg" optionsthumb1="300x200" title1="XFCE"
    image2="image:archlinux-cinnamon.jpg" optionsthumb2="300x200" title2="Cinnamon"
    caption="Entornos de escritorio Xfce y Cinnamon" >}}
{{< image
    gallery="true"
    image1="image:archlinux-lxde.jpg" optionsthumb1="300x200" title1="LXDE"
    image2="image:archlinux-mate.jpg" optionsthumb2="300x200" title2="Mate"
    caption="Entornos de escritorio LXDE y Mate" >}}
{{< image
    gallery="true"
    image1="image:archlinux-root-password.png" optionsthumb1="300x200" title1="Solicitud de contraseña para descifrar partición root"
    caption="Solicitud de contraseña para descifrar partición root" >}}

### Vídeo de instalación del sistema base con alis

El siguiente vídeo capturado con [asciinema][asciinema] muestra como utilizando alis se instala Arch Linux en una computadora con el proceso completo de la instalación base del sistema.

{{< asciinema id="418524" caption="Instalación de sistema base de Arch Linux con alis" >}}

### Código fuente de alis

El código del instalador no es muy complicado y mucho más sencillo que otros _scripts_ interactivos similares, al no mezclar los comandos de la instalación con los mensajes interactivos que hacen preguntas y esperan respuestas, además después de configurar las variables e iniciado el proceso de instalación la misma se realiza de forma desatendida hasta completarse sin requerir que el usuario introduzca más datos de forma interactiva.

Los comandos que he recopilado de la receta son los que ejecutaríamos uno detrás de otro partiendo el medio de instalación original de Arch Linux. La función _main_ contiene los pasos en los que consiste la instalación, desde el particionado y cifrado del disco hasta el reinicio una vez completada la instalación, el resto de las funciones los comandos de ese paso de la instalación.

{{< code file="alis.sh" language="bash" options="" >}}

{{< sourcecode git="alis/tree/master/" >}}

{{% /post %}}
