---
pid: 621
type: "post"
title: "Qué son y cómo gestionar archivos dotfiles con chezmoi"
url: "/2022/02/que-son-y-como-gestionar-archivos-dotfiles-con-chezmoi/"
date: 2022-02-17T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:linux.svg"
tags: ["gnu-linux", "planeta-codigo"]
summary: "Los archivos _dotfiles_ son utilizados para guardar información de configuración de las aplicaciones que se almacenan en el directorio del usuario. Se denominan _dotfiles_ porque son archivos que empiezan por un punto o están en una carpeta que empieza por un punto, además en GNU/Linux los archivos que empiezan por un punto son archivos ocultos que por defecto los exploradores de archivos no muestran. Para no perder la configuración de estos archivos adaptada por el usuario a sus preferencias al migrar a otro sistema o compartir la configuración entre varios sistemas hay varios programas con la funcionalidad específica de gestionar los archivos _dotfiles_."
---

{{% post %}}

{{< logotype image1="linux.svg">}}

A lo largo de los meses y años de uso de un equipo los usuarios vamos configurando el sistema con las aplicaciones necesarias y configurando esas aplicaciones a las preferencias de cada usuario. El problema es que cuando hay que mudarse a otro sistema porque se cambia a un equipo más nuevo y potente o porque deja de funcionar por un motivo de software o por una mala actualización que requiere reinstalar el sistema. Para estos casos y como medida de seguridad es recomendable [hacer copias de seguridad de los datos][blogbitix-144] con lo que migrarlos a otro equipo es menos complicada. Sin embargo, hacer copias de las configuraciones de los programas no es tán habitual con lo que si no se hace al instalar un nuevo sistema requiere configurarlo tal como se tenía anteriormente lo que requiere una buena cantidad de tiempo que hay que volver a dedicar en una nueva instalación.

En [GNU][gnu]/[Linux][linux] buena parte de los archivos de configuración de los programas se guardan en los archivos _dotfiles_.

{{< tableofcontents >}}

### Qué son los archivos _dotfiles_

Los archivos _dotfiles_ son archivos dedicados a guardar la configuración de los programas. Se guardan en la carpeta personal del usuario (_/home/${usuario}_) y se denominan _dotfiles_ porque su nombre empieza por un punto, en GNU/Linux los archivos que empiezan por punto son especiales ya que son archivos que los exploradores de archivos ocultan por defecto.

* [Arch Linux Wiki Dotfiles](https://wiki.archlinux.org/title/Dotfiles)

En GNU/Linux uno de los principales archivos _dotfiles_ es el archivo de configuración de inicio del intérprete de línea de comandos, que en el caso de [Bash][bash] su archivo _dotfile_ es _~/.bashrc_ y en el caso de [zsh][zsh] es _~/.zshrc_. Al igual que estos dos archivos _dotfiles_ en la carpeta personal del usuario se guardan multitud de otros como la configuración para realizar conexiones SSH cuyos _dotfiles_ de configuración del usuario se guardan en el directorio _~/.ssh_. Los propios entornos de escritorio guardan otra buena cantidad de archivos _dotfiles_ con las configuraciones integradas en el entorno de escritorio.

Este es el contenido de mi archivo _~/.bashrc_ en el que he configurado los colores de los elementos del símbolo del sistema en la línea de comandos, cierta configuración para que el símbolo del sistema cambien cuando el directorio de trabajo es una carpeta de código fuente del sistema de control de versiones de [Git][git].

{{< code file=".bashrc" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:bash-prompt.png" optionsthumb1="650x450" title1="Terminal personalizada"
    caption="Terminal con los colores del símbolo del sistema personalizados e información del repositorio de Git" >}}

### Cómo gestionar los archivos _dotfiles_

Una forma de migrar los archivos _dotfiles_ de un sistema a otro es haciendo una copia de estos y restaurándolos en el otro sistema. Sin embargo, los problemas son que las copias de seguridad no permiten mantener un registro de los cambios que se realizan a cada uno de estos archivos, no es tan fácil mantener sincronizadas todas las copias de los archivos entre varios equipos que pueden ser de diferentes sistemas operativos como GNU/Linux, [macOS][macos] o [Windows][windows] y algunos archivos de configuración tienen requerimientos de seguridad que hay que mantener depositados en un almacén seguro como son las claves privadas SSH de conexión o algunas credenciales. Además de programas como [el programa KeePassXC para guadar contraseñas y secretos de forma segura][blogbitix-196] también soportan funcionalidades de cifrado y descifrado para algunos datos.

Para dar solución a los problemas de la gestión de los archivos _dotfiles_ hay varios programas específicos para la tarea, en GNU/Linux es [chezmoi][chezmoi] que también está disponible para otros sistemas operativos como macOS e incluso Windows. También es posible utilizarlo en máquinas virtuales.

### El programa chezmoi y guía de uso para gestionar archivos _dotfiles_

chezmoi es uno de los programas dedicados a la tarea de gestionar los archivos de configuración _dotfiles_, hay otros varios pero chezmoi en su [lista de características que soporta chezmoi comparándolo con las alternativas](https://www.chezmoi.io/comparison-table/) lo hacen más potente, además al estar basado en Go generalmente los programas en este lenguaje se componen de un único binario hace que sean muy fácilmente de instalar y fiable al carecer de dependencias externas.

Muchas de estas herramientas para gestionar archivos _dotfiles_ se basan en un repositorio de Git en el que mantienen el control de versiones de los archivos y de cada uno de los cambios que se realizan en ellos. Entre las funcionalidades que ofrecen además de mantener el control de versiones están facilitar las tareas de uso básico como subir cambios al repositorio de Git para aplicar a otros sistemas o a un nuevo sistema y aplicar cambios al sistema local provenientes de otros sistemas, soportan variables y plantillas con las que generar el contenido de los archivos de forma dinámica y obtener datos de programas de base de datos que guardan secretos como KeePassXC entre muchos otros gestores de contraseñas.

#### Inicialización del repositorio

En la distribución [Arch Linux][archlinux] el programa chezmoi se instala instalando su paquete con el gestor de paquetes. En otras distribuciones basadas en [Debian][debian] o [Fedora][fedora] basta con buscar el nombre del paquete equivalente en esas distribuciones e instalarlo con su gestor de paquetes.

{{< code file="pacman-install-chezmoi.sh" language="bash" options="" >}}

El siguiente paso es crear el repositorio de Git en local con los siguientes comandos,  añadir el origen del repositorio remoto que hay que crear previamente en este caso usando un repositorio de [GitHub][github] y subir el contenido inicial.

{{< code file="chezmoi-create-repository.sh" language="bash" options="" >}}

#### Compartir archivos _dotfiles_ entre máquinas

Para inicializar el repositorio de Git de los archivos _dotfiles_ a partir del contenido de uno existente se utiliza este comando. Aunque los archivos se puede compartir entre máquinas incluso de diferentes sistemas operativos la intención es que todos esos archivos de _dotfiles_ de un usuario compartan la mayor similitud posible, aunque a veces por las diferencias de las máquinas no es completamente posible. Para ayudar a compartir lo más posible están las variables y plantillas, en caso de necesidad es posible administrar un _dotfile_ completamente diferente según el sistema operativo añadiendo condiciones en las plantillas.

{{< code file="chezmoi-init.sh" language="bash" options="" >}}

#### Ver cambios sin aplicarlos

El siguiente comando aplica los cambios a los archivos _dotfiles_ locales a partir del contenido del repositorio de archivos _dotfiles_ de chezmoi. Con las opciones _-v_ se muestra información más detallada de los cambios que se aplican, y con _-n_ permite ver que cambios se aplicarían en caso de realizar la acción sin la opción, sus opciones en formato largo son _-verbose_ y _--dry-run_.

{{< code file="chezmoi-update.sh" language="bash" options="" >}}

El comando _update_ actualiza el repositorio Git local con los cambios del repositorio origen y aplica los cambios a los archivos _dotfiles_. Otros comandos útiles son _diff_ para ver las diferencias entre el repositorio de Git y los archivos locales, el comando _apply_ aplica los cambios sobre los archivos _dotfiles_. Es importante tener en cuenta que los cambios en los _dotfiles_ locales se sobrescriben y se pierden en caso de tener diferencias con su versión en el repositorio de Git de chezmoi.

{{< code file="chezmoi-diff-apply.sh" language="bash" options="" >}}

#### Realizar cambios en los archivos _dotfiles_

Al empezar a usar chezmoi y a medida que se desean guardar las configuraciones de los archivos _dotfiles_ hay que añadir los archivos y sus cambios al repositorio de Git de los archivos _dotfiles_. El comando que permite añadir un archivo al repositorio es _add_.

{{< code file="chezmoi-add.sh" language="bash" options="" >}}

chezmoi permite editar el contenido de un archivo y añadirlo al repositorio automáticamente con el comando _edit_. Si un archivo _dotfile_ se modifica con un programa externo hay que utilizar el comando _add_ para añadirlo al repositorio después de que la aplicación haya hecho el cambio.

{{< code file="chezmoi-edit.sh" language="bash" options="" >}}

Editar un archivo con un programa externo

{{< code file="chezmoi-edit-external.sh" language="bash" options="" >}}

* [Daily operations](https://www.chezmoi.io/user-guide/daily-operations/)
* [How do I edit my dotfiles with chezmoi?](https://www.chezmoi.io/user-guide/frequently-asked-questions/usage/#how-do-i-edit-my-dotfiles-with-chezmoi)

El editar un _dotfile_ se puede configurar para que chezmoi lo añada al repositorio y lo suba al origen. El propio archivo de configuración de chezmoi ubicado en _~/.config/chezmoi/chezmoi.yaml_ es un _dotfile_ que se puede añadir al repositorio de _dotfiles_. El archivo de configuración soporta varios formatos entre ellos _toml_ y _yaml_.

{{< code file="chezmoi.yaml" language="yaml" options="" >}}

#### Subir al repositorio cambios en los archivos _dotfiles_

Después de realizar cambios en los archivos _dotfiles_ estos quedan en el repositorio de Git local en el directorio de trabajo, para subir los archivos al repositorio de Git origen es necesario hacer el _commit_ y el _push_ en el repositorio. chezmoi proporciona varios comandos de utilidad para trabajar con el repositorio de Git en el que se guardan los _dotfiles_.

Con el comando _cd_ se entra en un modo de edición del repositorio de Git, usándolo el directorio actual de trabajo se cambia a la carpeta del repositorio de Git. Para salir de este modo hay que usar el comando _exit_.

{{< code file="chezmoi-cd.sh" language="bash" options="" >}}

Con la carpeta de trabajo del símbolo del sistema en el repositorio de Git es posible utilizar los comandos como en cualquier otro repositorio de Git.

{{< code file="git-commit.sh" language="bash" options="" >}}

Si no se quiere entrar en el modo de edición del repositorio de Git se ofrece un comando para actuar contra el repositorio de Git de los _dotfiles_ aunque el directorio de trabajo actual sea otro, anteponiendo chezmoi a lo que es el comando de git que se aplica sobre el repositorio de Git de los _dotfiles_.

{{< code file="chezmoi-git.sh" language="bash" options="" >}}

#### Variables y plantillas

Algunos datos de los archivos _dotfiles_ para compartirlos entre máquinas o para externalizarlos de los archivos permite guardarlos de forma externa. chezmoi soporta plantillas, una plantilla se procesa junto varias las variables y genera el contenido del archivo final del _dotfile_. Las plantillas no solo soportan variables sino también expresiones con condiciones.

* [More complicated logic](https://www.chezmoi.io/user-guide/templating/#more-complicated-logic)

En el caso de querer considerar a un archivo como una plantilla hay que usar el siguiente comando al añadir lo para que sea administrado.

{{< code file="chezmoi-add-template.sh" language="bash" options="" >}}

Las variables se definen en el archivo de configuración de chezmoi.

{{< code file="chezmoi-2.yaml" language="yaml" options="" >}}

Además de variables propias en el archivo de configuración, chezmoi añade las suyas entre las que se pueden utilizar.

{{< code file="chezmoi-data.sh" language="bash" options="" >}}
{{< code file="chezmoi-data.json" language="java" options="" >}}

En los archivos de plantilla las variables se referencian con la siguiente expresión entre llaves.

{{< code file="chezmoi-template.file" language="plain" options="" >}}

El comando _execute-template_ permite evaluar una plantilla con el contenido de las variables, esto permite comprobar el funcionamiento de las plantillas y comprobar que generan el contenido deseado al evaluarse, a partir de una plantilla proporcionada como argumento del programa o desde el contenido de un archivo de plantilla.

{{< code file="chezmoi-execute-template.sh" language="bash" options="" >}}

#### Gestionar secretos

Algunas variables por su contenido sensible por motivos de seguridad hay que protegerlos y no deben ser compartidos, esto es importante tenerlo en cuenta ya que el contenido que se usa al repositorio de los _dotfiles_ cualquier usuario que tenga acceso a él puede ver su contenido, esto incluye credenciales e incluso claves SSH. Estos secretos se han de almacenar de forma diferente y no el repositorio de Git de los _dotfiles_ sobre todo si el repositorio de Git es público como puede ser el caso de si se utiliza un hospedaje como GitHub.

La forma que utiliza chezmoi para guardar estos secretos es utilizar un programa de base de datos cifrada para guardar credenciales como los gestores de contraseñas. Uno de ellos es KeePassXC pero chezmoi soporta muchos entre los que están los más populares. chezmoi es capaz de recuperar usuarios, contraseñas e incluso valores de atributos del apunte guardado en la base de datos cifrada.

* [Password manager integration](https://www.chezmoi.io/user-guide/password-managers/)

La siguiente variable de configuración indica donde se guarda la base de datos de secretos en este caso de KeePassXC.

{{< code file="chezmoi-3.yaml" language="yaml" options="" >}}

Y en las plantillas se utiliza la siguiente expresión para recuperar credenciales del apunte indicado y valores de atributos que puede ser cualquier contenido como una clave SSH privada.

{{< code file="chezmoi-template-2.file" language="plain" options="" >}}

#### Otras características

Los _dotfiles_ de algunos programas son más complejos de los de este ejemplo y hay varios programas que los utilizan, ¿no sería maravilloso poder utilizar los archivos de otros usuarios que comparten en sus repositorios de _dotfiles_? lo es y es posible utilizar estos archivos _dotfiles_ de otros usuarios, no solo para utilizarlos sino como ejemplo de cómo se configura alguna propiedad del _dotfile_ sobre todo si la documentación oficial del _dotfile_ no es amplia o no es lo suficientemente clara. En la wiki de Arch Linux hay una [colección de repositorios de usuarios con ejemplos de _dotfiles_](https://wiki.archlinux.org/title/Dotfiles#User_repositories) con ejemplos para intérpretes de línea de comandos, gestores de ventanas y entornos de escritorio, editores, terminal, multiplexores de terminal entre varios otros programas.

* [Include dotfiles from elsewhere](https://www.chezmoi.io/user-guide/include-dotfiles-from-elsewhere/)

chezmoi también soporta cifrado con la utilidad de cifrado [gpg][gnupg] y [age][age], si se quiere guardar algún _dotfile_ sensible en el repositorio de Git sin exponer su contenido pero sin utilizar un gestor de contraseñas.

* [Encryption](https://www.chezmoi.io/user-guide/encryption/)

{{< reference >}}
* [I like chezmoi. How do I say thanks?](https://www.chezmoi.io/user-guide/frequently-asked-questions/general/#i-like-chezmoi-how-do-i-say-thanks)
{{< /reference >}}

{{% /post %}}
