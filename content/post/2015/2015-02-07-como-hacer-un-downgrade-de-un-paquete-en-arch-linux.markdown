---
pid: 66
title: "Cómo hacer un «downgrade» de un paquete en Arch Linux"
url: "/2015/02/como-hacer-un-downgrade-de-un-paquete-en-arch-linux/"
date: 2015-02-07T11:05:24+01:00
updated: 2015-04-10T20:00:00+02:00
rss: true
sharing: true
comments: true
tags: ["gnu-linux", "blog-stack", "planeta-linux"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="archlinux.png" title="Arch Linux" >}}

[Arch Linux][archlinux] es un distribución [_rolling release_][rollingrelease] en la que los paquetes son actualizados en cuanto sale una nueva versión. Esto tiene la ventaja de que siempre tendremos el sistema actualizado con las versiones más recientes de los programas, en los que se van añadiendo nuevas características y corrigiendo errores de seguridad y funcionales. Sin embargo, en algún momento nos puede ocurrir que una actualización de algún paquete nos introduzca algún error, en este caso podemos hacer una desactualización de paquete y volver a una versión estable.

No es habitual que esto ocurra pero puede darse el caso y antes de hacer una desactualización es recomendable buscar en los [foros de arch linux][archlinux-forums] una respuesta con una alternativa mejor. En todo este tiempo como usuario de Arch Linux, más de 5 años, los _downgrades_ que he tenido que hacer han sido dos, uno para el paquete de _subversion_ y el que voy a comentar. El último caso ha sido para el paquete de [_networkmanager_](https://www.archlinux.org/packages/extra/x86_64/networkmanager/) al actualizarse a la versión 1.0.0-1 desde la 0.9.10.0-4. El problema que me ocasionaba era varios intentos de conexión y desconexión tanto de la red cableada como de la wifi, después de varios intentos seguidos y menos de un minuto conseguía conectarse a la red correctamente pero era un poco molesto. En los foros de arch linux hay varios mensajes de otros usuarios con el mismo problema [I](https://bbs.archlinux.org/viewtopic.php?id=193275), [II](https://bbs.archlinux.org/viewtopic.php?id=192679) y [III](https://bbs.archlinux.org/viewtopic.php?id=192344), donde se comenta algunas alternativas, una de ellas la desactualización del paquete. He de decir que en otro equipo no he tenido ningún problema con la actualización.

El proceso de _downgrade_ no tiene mucha complicación, basta con lanzar el comando, la dificultad está en conocer si también hay que desactaulizar alguna dependencia (libnm-gtk y libnm-glib):

{{< gist picodotdev f6fd427da4ad64fb6a2b "pacman.sh" >}}

Para hacer una desactualización deberemos tener la versión del paquete a la que queramos hacer un _downgrade_, si no hemos hecho una limpieza de la caché de los paquetes no usados (con _pacman -Sc_) lo tendremos en en el directorio _/var/cache/pacman/pkg/_. Si hemos hecho la limpieza de la caché y no dispones del paquete hay que buscar en algún _mirror_ menos actualizado la versión del paquete desactualizado, también podemos usar [Arch Rollback Machine](https://wiki.archlinux.org/index.php/Arch_Rollback_Machine) (ARM) que va almacenando instantáneas de los repositorios durante un tiempo, usando los varios enlaces de ARM podemos encontrar la versión desactualizada del paquete que queramos como el [caso de networkmanager](http://seblu.net/a/arm/packages/n/networkmanager/). Para evitar tener que buscar y descargar manualmente los paquetes de ARM lo que hago es mantener hasta la siguiente actualización los paquetes instalados en la caché cuando ya he comprobado que después de una actualización todo funciona.

Si no queremos que un paquete sea actualizado en la siguiente actualización del sistema que hagamos deberemos añadirlo a la lista _IgnorePkg_ del archivo _/etc/pacman.conf_.

{{< gist picodotdev f6fd427da4ad64fb6a2b "pacman.conf" >}}

No es recomendable mantener muchos paquetes desactualizados y en la lista de _IgnorePkg_ ya que estos pueden tener dependencias sobre paquetes también desactualizados, solo debe ser empleado para casos puntuales.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Artículo en la Wiki sobre desactualización de paquetes](https://wiki.archlinux.org/index.php/Downgrading_Packages)
* [Arch Rollback Machine](https://wiki.archlinux.org/index.php/Arch_Rollback_Machine)
{{% /reference %}}

{{% /post %}}
