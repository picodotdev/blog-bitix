---
pid: 549
type: "post"
title: "Qué es la variable PATH del sistema y cómo cambiarla en GNU/Linux"
url: "/2021/01/que-es-la-variable-path-del-sistema-y-como-cambiarla-en-gnu-linux/"
date: 2021-01-24T00:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:linux.svg"
tags: ["planeta-codigo", "gnu-linux"]
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

Al ejecutar un comando en la terminal el intérprete de comandos de [GNU][gnu]/[Linux][linux] lo busca en los directorios especificados en la variable de entorno del sistema _PATH_, esta variable de entorno contiene una lista de directorios separados por el caracter _:_ en la que se busca por orden. Al instalar un paquete seguramente el programa ejecutable se instale en _/usr/bin_, directorio que por defecto está incluido en la variable _PATH_.

En algún caso quizá nos interese instalar un programa ejecutable propio, un caso puede ser que la distribución usada no disponga del paquete del programa o esté en una versión antigua y se desee una más nueva. Como ejemplo usando [Arch Linux][archlinux] mi caso ha sido con las herramientas de [HashiCorp][hashicorp] como [Consul][consul], [Vault][vault] y [Nomad][nomad] para las que de algunas no está en los repositorios oficiales sino que está en los repositorios de los usuarios AUR.

Las herramientas que está desarrolladas con el lenguaje [Go][go] suelen un único binario que no tienen más dependencias. Basta con copiar ese binario al directorio _/usr/local/bin/_ que está destinado a que los usuarios instalen sus propios binarios sin entrar en conflicto con los que los paquetes instalan. El directorio _/usr/local/bin/_ es otro de los directorios incluídos en los directorios de búsqueda de la variable _PATH_.

Otra posibilidad es modificar la variable PATH del sistema y añadir el directorio que deseemos, por ejemplo, se puede crear el directorio _~/Software/bin_. El intérprete de comandos cuando el usuario introduce un comando lo busca en orden en los directorios especificados por la variable _PATH_, ya sea un binario o un _script_ ejecutable de un intérprete de comandos como [Bash][bash].

Este es parte del contenido de la variable _PATH_ del sistema en Arch Linux (los tres puntos es contenido que he omitido). Las distribuciones GNU/Linux suelen instalar los binarios de los programa ejecutables en los directorios _/usr/local/sbin_ con los binarios para el usuario _root_ y _/usr/bin_ es donde los paquetes ubican sus binarios y programas ejecutables. En el directorio _/usr/local/bin_ el usuario puede ubicar sus propios binarios sin que entren en conflicto con los instalados por los paquetes.

{{< code file="echo-path.sh" language="bash" options="" >}}
{{< code file="echo-path.out" language="plain" options="" >}}

Dado que el programa que manualmente se ubica en el directorio para binarios de los usuarios _/usr/local/bin_ o en un directorio propio no proviene de un paquete cuando se publique una nueva versión es el usuario el que ha de actualizar a la nueva versión descargando de nuevo el binario y copiarlo de nuevo al directorio.

La variable _PATH_ se configura en el _script_ de inicialización del intérprete de comandos, el de Bash es el archivo oculto _.bashrc_ ubicado en el directorio de inicio del usuario, en mi caso en _/home/picodotdev/.bashrc_. El archivo _.bashrc_ sirve también para [Personalizar el prompt del sistema del intérprete de comandos Bash][blogbitix-316] y el caso de utilizar el sistema de control de versiones [Git] para [Personalizar el prompt del intérprete Bash con información de estado del repositorio git][blogbitix-312].

{{% /post %}}
