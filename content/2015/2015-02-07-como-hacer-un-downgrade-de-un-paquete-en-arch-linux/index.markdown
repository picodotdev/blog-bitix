---
pid: 66
type: "post"
title: "Cómo hacer un «downgrade» de un paquete en Arch Linux"
url: "/2015/02/como-hacer-un-downgrade-de-un-paquete-en-arch-linux/"
date: 2015-02-07T11:05:24+01:00
updated: 2020-10-27T21:00:00+01:00
index: true
rss: true
sharing: true
comments: true
imagePost: "logotype:archlinux.svg"
tags: ["gnu-linux"]
---

{{% post %}}

{{< logotype image1="archlinux.svg" >}}

[Arch Linux][archlinux] es un distribución [_rolling release_][rollingrelease] en la que los paquetes son actualizados constantemente en cuanto sale una nueva versión. Esto tiene la ventaja de que siempre se tiene el sistema actualizado con las versiones más recientes de los programas, en los que se van añadiendo nuevas características y corrigiendo errores de seguridad y funcionales. Sin embargo, en algún momento puede ocurrir que una actualización de algún paquete nos introduzca algún error, en este caso podemos hacer una desactualización o _downgrade_ de paquete y volver a la última versión estable.

No es habitual que haya errores pero puede darse el caso, antes de hacer una desactualización es recomendable buscar en los [foros de arch linux][archlinux-forums] una respuesta con una alternativa mejor. En todo este tiempo como usuario de Arch Linux, más de 5 años, los _downgrades_ que he tenido que hacer han sido dos, uno para el paquete de _subversion_ y el que voy a comentar.

El último caso ha sido para el paquete de [_networkmanager_](https://www.archlinux.org/packages/extra/x86_64/networkmanager/) al actualizarse a la versión _1.0.0-1_ desde la _0.9.10.0-4_. El problema que me ocasionaba era varios intentos de conexión y desconexión tanto de la red cableada como de la wifi, después de varios intentos seguidos y menos de un minuto conseguía conectarse a la red correctamente pero era un poco molesto. En los foros de Arch Linux hay varios mensajes de otros usuarios con el mismo problema [I](https://bbs.archlinux.org/viewtopic.php?id=193275), [II](https://bbs.archlinux.org/viewtopic.php?id=192679) y [III](https://bbs.archlinux.org/viewtopic.php?id=192344), donde se comenta algunas alternativas, una de ellas la desactualización del paquete.

En la página [desactualizar paquetes](https://wiki.archlinux.org/index.php/Downgrading_packages) de la wiki de Arch Linux hay información más detallada de la desactualización de un paquete.

### Cómo hacer _downgrade_ de un paquete

El proceso de desactualización o _downgrade_ no tiene complicación, basta con lanzar un comando, se desactualiza el paquete y sus dependencias con la limitación de que en el caso de conflictos con otros paquetes también hay que desactualizar esos otros paquetes.

{{< code file="pacman-downgrade.sh" language="bash" options="" >}}

Arch Linux guarda en una caché del sistema de archivos local todos los paquetes con sus versiones que descarga, la caché se encuentra por defecto en el directorio _/var/cache/pacman/pkg/_.

### Cómo buscar un paquete de una versión anterior

Para hacer una desactualización hay que tener la versión del paquete a la que hacer _downgrade_, si no se ha hecho una limpieza de la caché de los paquetes viejos no usados con el comando _pacman -Sc_ están en el directorio de caché de paquetes. Si se ha hecho la limpieza de la caché y no se dispone del paquete hay que buscar en alguna réplica menos actualizada la versión del paquete desactualizado.

Otra posibilidad es usar [Arch Rollback Machine](https://wiki.archlinux.org/index.php/Arch_Rollback_Machine) que va almacenando instantáneas de las últimas versiones de los paquetes, de los repositorios organizados por fecha y de las imágenes iso del medio de instalación. Usando estas instantáneas de paquetes se puede encontrar una versión desactualizada de cualquier paquete individual. En los casos más extremos Arch Rollback Machine ofrece la posibilidad de desactualizar un equipo completamente a una fecha concreta, aunque no es recomendable.

Indicando en el comando la dirección completa del paquete este es descargado y desactualizado.

{{< code file="pacman-arm.sh" language="bash" options="" >}}

Cómo práctica recomendable es mantener hasta la siguiente actualización los paquetes en la caché, una vez actualizado el sistema y comprobado que todo sigue funcionando correctamente. Antes de una actualización se puede eliminar de la cache los paquetes viejos no usados, se actualiza el sistema, con lo que se tiene en la cache los paquetes nuevos y viejos, se comprueba que el sistema funciona correctamente y si hay que hacer un _downgrade_ de alguno se dispone de él en la cache local.

La secuencia de comandos en una actualización que uso es la siguiente.

{{< code file="pacman-update.sh" language="bash" options="" >}}

### Cómo evitar que un paquete se actualice

Para evitar que un paquete sea actualizado en la siguiente actualización del sistema hay añadirlo a la lista de paquetes ignorados _IgnorePkg_ del archivo _/etc/pacman.conf_.

{{< code file="pacman.conf" language="plaintext" options="" >}}

No es recomendable mantener muchos paquetes desactualizados y en la lista de _IgnorePkg_ ya que estos pueden tener dependencias sobre paquetes también desactualizados, solo debe ser empleado para casos puntuales.

{{% /post %}}
