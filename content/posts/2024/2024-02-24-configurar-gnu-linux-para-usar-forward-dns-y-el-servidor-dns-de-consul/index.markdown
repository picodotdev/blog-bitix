---
pid: 696
type: "post"
title: "Configurar GNU/Linux para usar forward DNS y el servidor DNS de Consul"
url: "/2024/02/configurar-gnu-linux-para-usar-forward-dns-y-el-servidor-dns-de-consul/"
date: 2024-02-24T18:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hashicorp-consul.svg"
tags: ["planeta-codigo", "gnu-linux"]
series: ["hashicorp"]
summary: "Una de las cuestiones que tenía pendiente de mirar sobre Consul es como hacer que un nodo con GNU/Linux pueda acceder al catálogo de servicios mediante la interfaz DNS que ofrece Consul. Para esto es necesario configurar el forward DNS."
---

{{% post %}}

{{< logotype image1="hashicorp-consul.svg" >}}

Quizá las aplicaciones no se pueden modificar para integrarse directamente con [Consul][consul] por su antigüedad o no hay tiempo ni personas para hacerle esos grandes cambios, una forma sencilla que no requiere grandes cambios en las aplicaciones y que les da acceso a algunas de las funcionalidades que proporciona Consul es utilizar su servicio DNS.

Configurar que el nodo donde se ejecuta un servicio pueda acceder al catálogo de servicios de Consul mediante la interfaz DNS requiere configurar el _forward_ DNS en la configuración de red.

{{< tableofcontents >}}

## El servidor Consul

Consul es una herramienta desarrollada por [Hashicorp][hashicorp] centrada en la conectividad entre los servicios. Ofrece varias funcionalidades específicas para las aplicaciones compuestas de múltiples servicios. Consul proporciona una catálogo de servicios actualizado y con información del estado de los servicios, conectividad entre los servicios con el _service mesh_ y almacenamiento clave-valor para la configuración de aplicaciones.

Algo ya he comentado en varios artículos sobre Consul y otros productos de Hashicorp como [Vault][vault] y [Nomad][nomad].

* [Serie de artículos sobre Hashicorp][blogbitix-serie-hashicorp]

Consul además a través de su API el catálogo de los servicios lo ofrece mediante un servidor DNS propio. Los servicios del catálogo se añaden a su servidor DNS con el sufijo _consul_, los servicios registrados en Consul tienen un nombre de dominio con la siguiente estructura. Consul se encarga de que en una consulta solo se devuelvan los servicios que han respondido correctamente al _healthcheck_ y devolver todas las instancias de un mismo servicio si hay varias en varios nodos.

* _web.service.consul_ para los servicios.
* _web.query.consul_ para las consultas.

Con las consultas o _queries_ es posible crear filtros específicos por varios criterios y obtener los servicios que coincidan con la consulta, pero las consultas dan para otro artículo. En este artículo con el servidor DNS que ofrece Consul la cuestión es cómo hacer que en GNU/Linux uno de esos dominios sea resuelto para lo que hay que configurar el _forward_ DNS.

## Configurar en GNU/Linux el _forward_ DNS al servicio DNS de Consul

El número puerto estándar del servicio DNS es el 53, en GNU/Linux los servicios en el número de puerto menor de 1024 requiere permisos de _root_ que por seguridad es algo que no se desea ejecutar Consul.

Consul ofrece el servicio de DNS en el puerto 8600 con lo que para ejecutar consul sin permisos de _root_ es necesario redirigir las consultas de DNS al puerto 8600. Esta redirección en GNU/Linux se puede hacer de dos formas con _systemd-resolver_ o con _dnsmasq_. A continuación muestro la configuración para la opción con _systemd-resolver_ y _NetworkManager_.

## Redirigir peticiones DNS de los dominios a Consul

En Arch Linux el gestor de red que utilizo es _NetworkManager_, el primer paso para la configuración es hacer que _NetworkManager_ también utilice _systemd-resolved_ para la resolución de las consultas DNS. Hay que crear el siguiente archivo de configuración _/etc/NetworkManager/conf.d/dns.conf_.

{{< code file="dns.conf" language="plain" options="" >}}

El segundo paso es configurar _systemd-resolved_ para que redirigir las consultas de DNS de los dominios con el sufijo _consul_ al servidor DNS de Consul, en esta configuración se especifica la dirección IP del servidor de Consul que será la local y el puerto. Hay que editar el archivo _/etc/systemd/resolved.conf_.

{{< code file="resolved.conf" language="plain" options="" >}}

Con la configuración realizada hay que iniciar y habilitar el servicio _systemd-resolved_ y recargar la configuración de _NetworkManager_.

{{< code file="systemctl.sh" language="bash" options="" >}}

Ahora es posible acceder a la interfaz de la consola web de Consul con el dominio en vez de con la dirección IP.

{{< image
    gallery="true"
    image1="image:consul.webp" optionsthumb1="650x450" title1="Consola web de Consul con dominio del servicio"
    caption="Consola web de Consul con dominio del servicio" >}}

También es posible hacer un _ping_ y el dominio se resolverá a la dirección IP. Y obtener información del dominio con los comandos _dig_ y _nslookup_.

{{< code file="dig.sh" language="bash" options="" >}}
{{< code file="dig.out" language="plain" options="" >}}

{{< code file="nslookup.sh" language="bash" options="" >}}
{{< code file="nslookup.out" language="plain" options="" >}}

{{< reference >}}
* [systemd-resolved](https://wiki.archlinux.org/title/Systemd-resolved)
* [NetworkManager#Configuration](https://wiki.archlinux.org/title/NetworkManager#Configuration)
* [dnsmasq](https://wiki.archlinux.org/title/Dnsmasq)
* [resolved.conf.5](https://man.archlinux.org/man/resolved.conf.5)
* [Configure systemd-resolved to use a specific DNS nameserver for a given domain](https://gist.github.com/brasey/fa2277a6d7242cdf4e4b7c720d42b567)
{{< /reference >}}

{{% /post %}}
