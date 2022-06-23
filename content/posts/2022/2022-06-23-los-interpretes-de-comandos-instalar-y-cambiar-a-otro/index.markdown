---
pid: 639
type: "post"
title: "Los intérpretes de comandos, instalar y cambiar a otro"
url: "/2022/06/los-interpretes-de-comandos-instalar-y-cambiar-a-otro/"
aliases: ["/2022/06/los-interpretes-de-comandos-instalar-y-cambiar-a-otro/"]
date: 2022-06-23T21:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:zsh.png"
tags: ["gnu-linux", "planeta-codigo"]
summary: "La terminal es una herramienta muy utilizada por usuarios avanzados aún habiendo sistemas operativos en los que prácticamente todo se puede realizar desde un programa con interfaz gráfica. La linea de comandos es menos amigable ya que no ofrece apenas ayuda que guíe al usuario pero cuando un usuario conoce como realizar una tarea desde la línea de comandos es mucho más rápido que usar un programa con interfaz gráfica además de otras ventajas. El intérprete de comandos es la pieza que proporciona un entorno dese el que se introducen los comandos y con el que interacciona el usuario desde la terminal con una interfaz basada en texto."
---

{{% post %}}

{{< logotype image1="linux.svg" image2="bash.svg" >}}

El sistema operativo es una pieza fundamental de software que hace usable el hardware y proporciona una abstracción y servicios más lógicos para los programas sin que estos se tengan que preocupar de detalles de bajo nivel relativos al hardware ni específicos de cada dispositivo hardware.

Una de las piezas fundamentales de un sistema operativo y la más conocida es el núcleo o _kernel_, dos núcleos con licencia de software libre son [Linux][linux] y [FreeBSD][freebsd] pero hay muchos otros menos conocidos como [Minix][minix], [Genode][gnome] o [Haiku][haiku] y distribuciones derivadas de estos. Sistemas operativos con núcleo privativos son el de [Windows][windows] de los sistemas de escritorio de [Microsoft][microsoft] y comerciales son el de [macOS][macos] de los ordenadores de [Apple][apple].

* [Descargar e instalar la distribución Ubuntu de GNU/Linux paso a paso desde cero][blogbitix-232]
* [Guía de instalación y uso básico de FreeBSD][blogbitix-570]
* [Licencias de software libre y diferencias con software privativo y de código abierto][blogbitix-552]

Sin embargo, el núcleo sólo es una parte de lo que se considera un sistema operativo ya que aunque tiene muchas funcionalidades un usuario no interacciona directamente con el sistema operativo sino que lo hace a través de una colección de herramientas y programas de línea de comandos, entre ellas se encuentra en un intérprete de comandos. Generalmente en las distribuciones Linux la colección de herramientas son las proporcionadas por [el proyecto GNU][gnu] que es un proyecto distinto al núcleo de Linux, y por eso muchas veces nos referimos a Linux por el término GNU/Linux cuando se desea dar crédito a ambos proyectos.

{{< tableofcontents >}}

### Qué hace un intérprete de comandos

Un intérprete de comandos es una interfaz basada en texto que permite introducir los comandos a ejecutar compuestos por el nombre del programa, opciones de invocación y sus argumentos. El intérprete de comandos también permite establecer variables de entorno para almacenar valores e incluirlos en el comando así como redirigir la salida a archivos y crear tuberías para redirigir la salida de un programa a la entrada de otros que permiten realizar una funcionalidad compleja a partir de comandos más simples.

Los sistemas operativos actuales tienen una interfaz gráfica pero todos proporcionan una terminal virtual en la que interactuar con el sistema operativo mediante comandos y texto, se ejecutan desde una terminal virtual, cada entorno de escritorio también proporciona su propia entre su colección de aplicaciones gráficas integradas. En [GNOME][gnome] es [Terminal][gnome-terminal] y [Console][gnome-console] y en [KDE][kde] es [Konsole][kde-konsole].

Aún con las interfaces gráficas las terminales y la línea de comandos siguen siendo muy utilizadas por su posibilidad de automatización y conociendo el comando es más sencillo que utilizar una herramienta gráfica. Además, para la administración de servidores siguen siendo la interfaz de administración principal incluso antes que las interfaces gráficas.

{{< image
    gallery="true"
    image1="image:gnome-terminal.png" optionsthumb1="650x450" title1="Emulador de terminal de GNOME Terminal"
    caption="Emulador de terminal de GNOME Terminal" >}}

### Funcionalidades del intérprete de comandos

Las funcionalidades de introducir comandos y ejecutarlos, variables de entorno, redirecciones y tuberías son funcionalidades básicas que los intérpretes de comandos actuales poseen. Algunas funcionalidades no están presentes en todos los intérprete de comandos y otras en función del inteŕprete funciona de forma ligeramente diferente o con algún añadido adicional.

Una funcionalidad que cambia en función del intérprete es qué ocurre cuando se pulsa la tecla tabulador. Al pulsar esta tecla los intérpretes suelen proporcionar autocompletado para el nombre del directorio o nombre de comando pero algunos son capaces de hacerlo incluso de forma recursiva en múltiples directorios o aplicando correcciones de fallos de tipografía aplicando la corrección cuando se introduce una letra mal.

Otra variación en función del intérprete es el tipo de expansión comodín o _wildcard expansion_ que se soporta. Las expansiones comodín son una versión simplificada de las expresiones regulares que permiten encontrar múltiples coincidencias, estos es muy utilizado por ejemplo para seleccionar múltiples archivos o cierto tipo de archivos como todos los archivos de música o vídeo con _*.mp3_ o _*.mp4_ respectivamente, estas expresiones con un comodín del caracter asterisco seleccionan todos los archivos con cualquier nombre y de extensión _mp3_ o _mp4_.

Otra funcionalidad es la posibilidad de personalizar el aspecto del símbolo del sistema o _prompt_ para que muestre la información que se desee en texto y con los colores deseados. Los símbolos del sistema por defecto incluyen el directorio de trabajo actual, el nombre de usuario y el nombre del sistema en el que se está trabajando.

### Intérpretes de comandos

Dado que el intérprete de comandos es simplemente un programa más hay varios disponibles con ligeras variaciones sobre las funcionalidades básicas pero suficientes para que algunos desarrolladores según sus ideales hayan creado varios.

En las distribuciones GNU/Linux generalmente el intérprete de comandos preinstalado por defecto es [bash][bash] y otro popular es [zsh][zsh] que se ha convertido en el por defecto en el sistema operativo macOS en sustitución de bash. Otros intérprete de comandos menos conocidos son [fish][fishshell] y [dash][dash] pero no son los únicos.

Las funcionalidades principales de todo intérprete de comandos las soportan todos, casi la mayor diferencia entre ellos aparte de su popularidad es su nivel de personalización y en menor medida algunas mejoras y rendimiento pero que estas dos últimas no suelen ser determinantes para cambiar de uno a otro.

* [Command-line shell](https://wiki.archlinux.org/title/Command-line_shell)
* [Guía básica del intérprete de comandos Bash](https://picodotdev.github.io/blog-bitix/2016/07/guia-basica-del-interprete-de-comandos-bash/)
* [What are the practical differences between Bash and Zsh?](https://apple.stackexchange.com/questions/361870/what-are-the-practical-differences-between-bash-and-zsh)
* [Which terminal is better: Bash vs Zsh](https://hands-on.cloud/which-terminal-is-better-bash-vs-zsh/#h-features-comparison)
* [Bash Arc Linux Wiki](https://wiki.archlinux.org/title/Bash)
* [Zsh Arc Linux Wiki](https://wiki.archlinux.org/title/Zsh)
* [Bash Manual](https://www.gnu.org/software/bash/manual/bash.html)
* [Zsh Manual](https://zsh.sourceforge.io/Doc/Release/zsh_toc.html)

### Como cambiar el intérprete de comandos del usuario

Independientemente del intérprete de comandos que preinstale la distribución GNU/Linux este se puede cambiar instalando el paquete de otro intérprete y ejecutándolo como un comando más. También es posible cambiar el intérprete por defecto al abrir una terminal.

Este comando permiten instalar el intérprete de comandos zsh.

{{< code file="pacman-install-zsh.sh" language="bash" options="" >}}

Los siguientes permiten listar los intérpretes de comandos instalados en el sistema y cambiar el por defecto del usuario a otro.

{{< code file="chsh.sh" language="bash" options="" >}}

El aspecto por defecto de los _prompts_ de bash y zsh son los siguientes.

{{< image
    gallery="true"
    image1="image:bash.png" optionsthumb1="300x200" title1="Intérprete de comandos bash"
    image2="image:zsh.png" optionsthumb2="300x200" title2="Intérprete de comandos zsh"
    caption="Intérprete de comandos bash y zsh" >}}

### Configurar el _prompt_ del símbolo del sistema

Uno de los motivos de cambiar de un intérprete de comandos por otro es cambiar el aspecto del símbolo del sistema. bash con la variable de entorno _PS1_ permite personalizar en cierta medida _prompt_ y si se usa el sistema de control de versiones Git este ofrece una utilidad para que el símbolo del sistema indique el estado de cambios cuando el directorio de trabajo actual es un repositorio de Git. Sin embargo, estas personalizaciones son limitadas y específicas de cada _shell_.

En mi caso este es el aspecto que utilizo con bash, en el que he cambiado los colores y usado la utilidad para mostrar información del repositorio Git. La segunda image es la potente personalización e independiente del _shell_ ofrecida por [Starship][starship].

* [Personalizar el prompt del sistema del intérprete de comandos Bash][blogbitix-316]
* [Prompt de la terminal personalizado en carpetas de git con el intérprete Bash][blogbitix-312]
* _Personalizar el símbolo del sistema con Starship_

{{< image
    gallery="true"
    image1="image:bash-custom.png" optionsthumb1="300x200" title1="Terminal de bash personalizada"
    image2="image:starship.png" optionsthumb2="300x200" title2="Terminal personalizada con Starship"
    caption="Terminal de bash personalizada" >}}

{{% /post %}}
