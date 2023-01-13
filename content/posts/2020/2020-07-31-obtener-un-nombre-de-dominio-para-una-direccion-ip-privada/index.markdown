---
pid: 504
type: "post"
title: "Obtener un nombre de dominio para una dirección IP privada"
url: "/2020/07/obtener-un-nombre-de-dominio-para-una-direccion-ip-privada/"
date: 2020-07-31T11:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:dig.webp"
tags: ["gnu-linux", "planeta-codigo"]
---

{{% post %}}

Las direcciones IP en la versión 4 del protocolo son un número de 32 bits, se representan en cuatro grupos de números decimales que toman el valor entre 0 y 255 separados por un punto, por ejemplo para la dirección que representa la máquina local _127.0.0.1_. En [la versión 6 del protocolo IP][blogbitix-361] las direcciones IP son un número de 128 bits. Dado que los números de la direcciones IP son difíciles de recordar para los humanos se usan alias o sinónimos denominados nombres de dominio, por ejemplo, _picodotdev.github.io_ o _duckduckgo.com_. El software que permite la conversión de nombre de dominio a dirección IP son los servidores _DNS_.

Algunos rangos de las direcciones IP están reservados para crear redes privadas no accesibles desde internet estos son de _10.0.0.0_ a _10.255.255.255_, de _172.16.0.0_ a _172.31.255.255_ y de _192.168.0.0_ a _192.168.255.255_. Para estas direcciones IP privadas en algunos casos también es necesario asociarlas con nombre de dominio, los servidores web virtuales que permiten alojar en el mismo servidor varios sitios web dependen del nombre del dominio a través del cual se realiza la petición, [usar el protocolo seguro HTTPS][blogbitix-151] y [usar el protocolo HTTP/2][blogbitix-129] también depende de los nombres de dominio.

Para resolver nombres de dominio a direcciones IP hay dos posibilidades, la primera es utilizar el archivo _/etc/hosts_ creando una línea con el nombre del dominio y su dirección IP, la segunda forma es crear y administrar un servidor DNS. Hay algunos servicios que ofrecen la funcionalidad de resolver dominios a direcciones IP sin necesidad de utilizar el archivo _/etc_hosts_ ni crear un servidor DNS.

Los servidores DNS de [xip.io](https://www.xip.io/) resuelven dominios a direcciones privadas IPv4 y [sslip.io](https://sslip.io/) resuelve dominios a direcciones privadas IPv4 e IPv6. La utilidad de los nombres de dominio es hacer las direcciones IP más significativas incluyendo una cadena que las describa y permitir utilizar servidores virtuales en los servidores web sin utilizar el archivo _etc/hosts_ ni un servidor DNS propio.

Estos servicios DNS permiten embeber en el nombre de dominio la dirección IP a la que resuelven cuando se les hace la consulta. Por ejemplo, para el nombre de dominio _consul.192.168.33.10.xip.io_ resuelven a la dirección IP _192.168.33.10_. El servicio sslip permite utilizar guiones en vez de puntos para separar los elementos de la dirección IP, por ejemplo _consul.192-168-33-10.sslip.io_.

Utilizando el comando _dig_ se obtienen los registros DNS del nombre de dominio indicado incluído el registro _A_ para la dirección IP.

{{< code file="dig.sh" language="bash" options="" >}}

{{% /post %}}
