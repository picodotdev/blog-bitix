---
pid: 640
type: "post"
title: "Personalizar el símbolo del sistema de la línea de comandos con Starship"
url: "/2022/06/personalizar-el-simbolo-del-sistema-de-la-linea-de-comandos-con-starship/"
date: 2022-06-26T04:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:starship-1.png"
tags: ["gnu-linux", "planeta-codigo"]
summary: "El símbolo del sistema o _prompt_ en la línea de comandos de la terminal precede a la introducción del comando a ejecutar. Los intérpretes de comandos o _shells_ ofrecen un símbolo del sistema por defecto que muestran cierta información y con el formato que sus desarrolladores han elegido. Además de que cada intérprete de comandos tenga uno propio estos son bastante limitados en cuanto a personalización además de utilizar una configuración poco intuitiva. Starship es un personalizador del símbolo del sistema independiente del intérprete de comandos, que ofrece una gran cantidad de módulos para personalizar el símbolo del sistema según el directorio de trabajo actual y cuya configuración es muy intuitiva y está bien documentada."
---

{{% post %}}

{{< logotype image1="linux.svg" title1="bash.svg" >}}

Para los usuarios avanzados la línea de comandos sigue siendo la forma más rápida de hacer ciertas tareas y a veces es la única forma de hacerlo ya que no se dispone de la aplicación equivalente. La línea de comandos no solo no ha desaparecido con las interfaces gráficas sino que se sigue usando mucho. Con algunas aplicaciones gráficas la línea de comandos no es necesaria y hay muchas aplicaciones gráficas para multitud de necesidades pero para algunas cosas especializadas no las hay y la mejor opción es un programa de línea de comandos.

Dada la utilidad de la línea de comandos esta se usa y se seguirá usando. En la línea de comandos un elemento destacado es el símbolo del sistema o _prompt_ que muestra una información básica como el nombre de usuario, directorio y cierta información de estado. Hay varias utilidades para personalizar el símbolo del sistema según las preferencias del usuario.

{{< tableofcontents >}}

### Formas de personalizar el símbolo del sistema de la línea de comandos

La información y formato del símbolo del sistema por defecto depende del intérprete de comandos usado, [bash][bash] tiene un formato diferente de [zsh][zsh]. Sin utilidades adicionales tanto bash como zsh permiten personalizar el símbolo del sistema cambiando la información que muestra, el formato y los colores.

En bash el cambiar el formato del símbolo del sistema consiste en cambiar el valor de la variable de entorno _PS1_ en el archivo _~/.bashrc_ del usuario. El formato es un tanto críptico tanto para símbolos que permiten incluir los datos como el formato para los colores. Leyendo el contenido de una variable _PS1_ no es fácil saber como es el aspecto final del símbolo del sistema. zsh también se puede personalizar y dispone de herramientas específicas para la personalización como [Oh my zsh][ohmyz].

Por otro lado, [Git][git] por ejemplo ofrece una utilidad con la que personalizar el símbolo del sistema para mostrar cierta información según información del sistema, el directorio actual de trabajo contiene un repositorio Git u otro tipo de contenido.

* [Personalizar el prompt del sistema del intérprete de comandos Bash][blogbitix-316]
* [Prompt de la terminal personalizado en carpetas de git con el intérprete Bash][blogbitix-312]
* [Los intérpretes de comandos, instalar y cambiar a otro][blogbitix-639]
* [Guía básica del intérprete de comandos Bash][blogbitix-158]

Aún así las personalizaciones que ofrecen tanto bash como zsh son propias de cada uno de esos intérpretes, diferentes entre sí y limitadas en cuanto a elementos de personalización. En la siguiente configuración el _prompt_ está personalizado con la variable _PS1_ modificando los colores para el nombre del usuario y _host_ además del _git prompt_.

{{< code file=".bashrc-1" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:bash-prompt-1.png" optionsthumb1="650x450" title1="Símbolo del sistema de bash"
    caption="Símbolo del sistema de bash" >}}

### El configurador del símbolo del sistema Starship

[Starship][starship] es una utilidad para configurar el símbolo del sistema fácil de instalar, sencillo de configurar, muchas opciones y elementos de configuración, moderno, tiene numerosos módulos que soportan múltiples tecnologías de las cuales mostrar información y compatible tanto con bash como zsh entre muchos otros intérpretes de comandos.

Está escrito en el lenguaje de programación [Rust][rust] que se caracteriza por ofrecer un alto rendimiento y ser eficiente, aunque la diferencia para el usuario no va a ser apreciable para el usuario.

Starship permite aplicar misma configuración y tener el mismo símbolo del sistema independientemente del intérprete de comandos que es útil por ejemplo si se usa equipos diferentes, uno con [GNU][gnu]/[Linux][linux] en las que generalmente utilizan por defecto el intérprete de comandos bash y otro con [macOS][macos] en el que el intérprete de comandos por defecto es zsh.

{{< image
    gallery="false"
    image1="image:starship.png" optionsthumb1="300x200" title1="Starship" >}}

En [Arch Linux][archlinux] se instala con el comando del gestor de paquetes.

{{< code file="pacman-install-starship.sh" language="bash" options="" >}}

### Configuración de Starship

Además de ofrecer un alto grado de personalización la configuración de Starship es muy sencilla con la ayuda de su [documentación para cada módulo de configuración](https://starship.rs/config/). Dado que tiene una buena cantidad de opciones lo más rápido es partir de una configuración ya creada y adaptarla ligeramente a las necesidades o preferencias personales.

En la sección de [Presets](https://starship.rs/presets/) hay varios ejemplos de configuración. Para el caso de este ejemplo parto de la configuración [Pastel Powerline](https://starship.rs/presets/pastel-powerline.html). La configuración de Starship consiste en un archivo de texto en formato _toml_, cada uno de los ejemplos de preconfiguraciones se puede descargar su archivo.

Editar el archivo es bastante intuitivo tanto por el nombre de las variables como por su contenido, consiste en un conjunto de variables cuyos valores modifican un aspecto de la línea de comandos. La configuración consiste en cambiar los valores de las variables cuya descripción está documentada en la sección de configuración. El archivo de configuración es un archivo _dotfile_ en la carpeta del directorio del usuario _~/.config/starship.toml_.

{{< code file=".bashrc-2" language="bash" options="" >}}

Este es un archivo de configuración de ejemplo en el que algunos símbolos no se muestran correctamente por faltar la fuente que los incluye. La variable _format_ determina el formato e información del símbolo del sistema.

{{< code file="starship.toml" language="toml" options="" >}}

Utilizando colores el símbolo del sistema casi tiene un aspecto gráfico y al estar cada información separada por diferentes colores hace que sea fácilmente identificable.

{{< image
    gallery="true"
    image1="image:starship-1.png" optionsthumb1="650x450" title1="Símbolo del sistema personalizado con Starship" >}}

Las siguientes capturas muestran información en el símbolo del sistema de una carpeta que es un repositorio de Git que incluye información del estado del espacio de trabajo y en otra además en que la carpeta es un proyecto en el lenguaje Java.

{{< image
    gallery="true"
    image1="image:starship-2.png" optionsthumb1="300x200" title1="Símbolo del sistema personalizado con Starship"
    image2="image:starship-3.png" optionsthumb2="300x200" title2="Símbolo del sistema personalizado con Starship"
    caption="Símbolo del sistema personalizado con Starship" >}}

La variable principal _format_ incluye el contenido y estilos del símbolo del sistema, por ejemplo el nombre del usuario y el directorio actual, hasta aquí es lo mismo que ofrecen bash y zsh por defecto, pero además permite mostrar información de estado según el contenido de del directorio de trabajo actual como Git, múltiples lenguajes de programación y tecnologías.

Si el directorio de trabajo actual es un repositorio de Git se muestra la rama y el estado de cambios, si es una carpeta de un proyecto muestra de que tipo de proyecto es por ejemplo un proyecto de Java y la versión actual de Java soportando muchos otros tipos de proyectos y tecnologías, también puede mostrar información de [Docker][docker].

Otra información que puede mostrar es la hora actual que permite registrar a qué hora se ha ejecutado un determinado comando en caso de revisar la sesión de línea de comandos.

Una vez configurado Starship con [Chezmoi][chezmoi] es posible guardar este _dotfile_ en un repositorio de Git en caso de querer aplicarlo en una nueva instalación o en otro equipo y mantener sincronizado el contenido entre varios equipos.

* [Qué son los archivos dotfiles y cómo gestionarlos con chezmoi][blogbitix-621]

{{< youtube
    video="SKtauGe0d3I" >}}

{{< reference >}}
* [https://kruschecompany.com/zsh-vs-bash-unix-shell/](https://kruschecompany.com/zsh-vs-bash-unix-shell/)
* [https://linuxhint.com/differences_between_bash_zsh/](https://linuxhint.com/differences_between_bash_zsh/)
* [https://www.youtube.com/watch?v=SKtauGe0d3I](https://www.youtube.com/watch?v=SKtauGe0d3I)
{{< /reference >}}

{{% /post %}}
