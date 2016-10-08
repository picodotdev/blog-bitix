---
pid: 176
title: "Localización de un comando y paquete propietario de un archivo en GNU/Linux"
url: "/2016/09/localizacion-de-un-comando-y-paquete-propietario-de-un-archivo-en-gnu-linux/"
date: 2016-09-03T13:00:00+02:00
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "gnu-linux", "planeta-linux"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="archlinux.png" title1="Arch Linux" >}}

Usando alguna distribución [GNU][gnu]/[Linux][linux] en algún momento nos interesará saber a qué paquete de los que tenemos instalados pertenece un determinado comando o a qué paquete pertenece un determinado archivo del sistema.

Para saber en qué paquete está un determinado archivo primero deberemos conocer la ruta absoluta del archivo en cuestión. Si se trata de un comando con el comando <code>which</code> conoceremos la ubicación absoluta. Por ejemplo, para conocer la ubicación de el comando <code>java</code> usamos:

{{< gist picodotdev 928f2b813d905f89de91b3515ba47dc1 "which.sh" >}}

Conociendo la ruta absoluta del archivo y con el gestor de paquetes de la distribución podremos conocer en qué paquete está contenido y por qué paquete ha sido instalado o es su propietario. En [Arch Linux][archlinux] se averigua con el siguiente comando:

{{< gist picodotdev 928f2b813d905f89de91b3515ba47dc1 "pacman-java.sh" >}}

En Arch Linux _java-runtime-common_ es un paquete que nos permite cambiar la versión de la máquina virtual a usar en el sistema pudiendo intercambiar entre Java 7 y Java 8, esto se realiza con algunos enlaces simbólicos.

{{< gist picodotdev 928f2b813d905f89de91b3515ba47dc1 "ls.sh" >}}

Si queremos conocer el paquete del binario <code>java</code> usaremos la ruta absoluta en el comando para averiguarlo con el gestor de paquetes ya sea con <code>pacman</code> o <code>yaourt</code>:

{{< gist picodotdev 928f2b813d905f89de91b3515ba47dc1 "pacman-java8.sh" >}}

En las distribuciones basadas en paquetes _.deb_ como [Debian][debian] o [Ubuntu][ubuntu] el comando es el siguiente:

{{< gist picodotdev 928f2b813d905f89de91b3515ba47dc1 "dpkg.sh" >}}

Y en las distribuciones basadas en _.rpm_ como como [RedHat][rhel] y [Fedora][fedora]:

{{< gist picodotdev 928f2b813d905f89de91b3515ba47dc1 "rpm.sh" >}}

{{% /post %}}
