---
pid: 507
type: "post"
title: "Configurar un servidor web virtual en Nginx y Apache"
url: "/2020/08/configurar-un-servidor-web-virtual-en-nginx-y-apache/"
date: 2020-08-09T14:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:nginx-virtual-server.webp"
tags: ["planeta-codigo", "web"]
---

{{% post %}}

{{< logotype image1="nginx.svg" image2="apache.svg" >}}

La función básica de un servidor web es devolver al navegador los recursos estáticos de un sitio web como son las páginas HTML, hojas de estilo CSS, imágenes o archivos de JavaScript. Pero también pueden hacer otras funciones muy útiles como son la de [_proxy_ entre en navegador y la aplicación de _backend_][blogbitix-159] que genera el contenido dinámico, terminación de protocolo de seguridad con la que el navegador se comunica con el servidor web usando el protocolo seguro HTTPS pero el servidor web se comunica con la aplicación _backend_ usando el protocolo HTTP, [añadir autenticación básica a ciertas rutas][blogbitix-505] o utilizar [el protocolo HTTP/2][blogbitix-129].

Los servidores web no requieren una capacidad de cómputo elevada, para aprovechar al máximo la capacidad e una máquina y para no requerir un servidor web por cada sitio web se utilizan los servidores web virtuales. Un servidor web virtual es un servidor web que sirve el contenido de un sitio web normalmente asociado a un nombre de dominio aunque también es posible estar asociado a una dirección IP. Por ejemplo, empleando servidores web virtuales la misma instancia de servidor web puede servir el contenido de varios sitios web diferentes como _site1.127.0.0.1.xip.io_, _site2.127.0.0.1.xip.io_ y  _site3.127.0.0.1.xip.io_. El servidor web obtiene el nombre del dominio por el que es accedido y determina el servidor web al que redirigir la solicitud.

En cada servidor web virtual se puede configurar HTTPS y HTTP/2 para lo que es necesario generar un certificado autofirmado con el nombre del dominio.

* [Generar y convertir claves y certificados con OpenSSL][blogbitix-13]
* [Configurar SSL/TLS en un servidor Tomcat, JBoss, WildFly, Lighttpd, Nginx o Apache][blogbitix-14]
* [Configurar HTTP/2 en Nginx, Apache HTTPD, WildFly o Jetty][blogbitix-129]

En el ejemplo se usa un contenedor de [Docker][docker] de modo que para probar los ejemplos no sea necesario instalar Nginx ni Apache.

* [Introducción y características de Docker][blogbitix-49]
* [Cómo instalar y guía de inicio básica de Docker][blogbitix-50]

{{< tableofcontents >}}

### Configurar un servidor web virtual en Nginx

En el servidor web [Nginx][nginx] añadir un servidor web virtual consiste en añadir la configuración del sitio web en el directorio _/etc/nginx/conf.d_. La directiva relevante es _server\_name_ que asocia el servidor web virtual con el nombre del dominio. En base al nombre del dominio por el que se accede al servidor web se utiliza su configuración.

{{< code file="nginx-www.127.0.0.1.xip.io.conf" language="nginx" options="" >}}
{{< code file="docker-nginx.sh" language="bash" options="" >}}

En el ejemplo se accede al sitio web por su nombre de dominio _www.127.0.0.1.xip.io_, usando [el servidor de nombres xip.io][blogbitix-504] que permite asociar un nombre de dominio a una dirección IP privada en este caso la dirección _127.0.0.1_ que identifica a la propia máquina anfitrión.

Algunas distribuciones permiten añadir la configuración de los sitios web en los directorios _/etc/nginx/sites-available_, _/etc/nginx/sites-enabled_, _/etc/apache2/sites-available_, _/etc/apache2/sites-enabled_ en el caso de las imágenes de Docker tanto para Nginx como para Apache estos directorios no están configurados por defecto.

En el ejemplo se usa la misma raíz de documentos que el sitio web para _localhost_, si se añaden archivos de contenido en una raíz de documentos propia para el servidor web virtual el contenido devuelto cambia o si cambia la raíz de documentos al directorio _/usr/share/nginx_ que Nginx no tiene permitido el acceso se observa que cuando se accede al servidor por el nombre del dominio _localhost_ se muestra la página de inicio y cuando se accede por el nombre del dominio _www.127.0.0.1.xip.io_ se devuelve un error _HTTP 403 Forbidden_ por falta de permisos.

{{< image
    gallery="true"
    image1="image:nginx-virtual-server.webp" optionsthumb1="300x200" title1="Servidor web virtual en Nginx"
    caption="Servidor web virtual en Nginx" >}}

### Configurar un servidor web virtual en Apache HTTPD

En el servidor web [Apache HTTPD][apache-httpd] un servidor web virtual se configura con sus propias directivas que igualmente indican el nombre del dominio asociado al servidor web y la ruta raíz de los recursos estáticos. Dependiendo del nombre del dominio por el que se accede al servidor web, se utiliza su configuración.

{{< code file="httpd-vhosts.conf" language="plain" options="" >}}
{{< code file="docker-apache.sh" language="bash" options="" >}}

Usando Apache también se accede al sitio web por su nombre de dominio _www.127.0.0.1.xip.io_.

{{< image
    gallery="true"
    image1="image:apache-virtual-server.webp" optionsthumb1="300x200" title1="Servidor web virtual en Apache"
    caption="Servidor web virtual en Apache" >}}

{{% sourcecode git="blog-ejemplos/tree/master/WebVirtualServer" command="./docker-nginx.sh, ./docker-apache.sh" %}}

{{< reference >}}
* [How to Install Apache Server and Set Up Virtual Hosts on Ubuntu 20.04](https://linuxhint.com/install_apache_server_setup_virtual_hosts_ubuntu/)
{{< /reference >}}

{{% /post %}}
