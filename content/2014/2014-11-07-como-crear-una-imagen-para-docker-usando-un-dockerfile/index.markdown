---
pid: 51
type: "post"
title: "Cómo crear una imagen para Docker usando un Dockerfile"
url: "/2014/11/como-crear-una-imagen-para-docker-usando-un-dockerfile/"
date: 2014-11-07T19:50:17+01:00
updated: 2020-12-28T17:30:00+01:00
index: true
rss: true
sharing: true
comments: true
imagePost: "logotype:docker.svg"
tags: ["gnu-linux", "planeta-codigo"]
series: ["docker"]
summary: "Podemos usar los contenedores disponibles en Docker Hub, donde están disponibles las aplicaciones de bases de datos, servidores de aplicaciones de múltiples lenguajes, servidores web más populares y entre otras muchas herramientas. Pero también podemos definir nuestras propias imágenes personalizadas con las necesidades que tengamos. Lo que necesitamos es escribir un archivo que contenga la receta para construir la imagen del contenedor, este archivo es el Dockerfile."
---

{{% post %}}

{{< logotype image="docker.svg" title="Docker" width="200" >}}

Las imágenes de [Docker][docker] son el sistema de archivos que usa el proceso o procesos que se arrancan en los contenedores. Si nos convencen las [características de Docker][blogbitix-49] y estamos decididos a usarlo y ya sabemos [como administrar de forma básica los contenedores][blogbitix-50] si queremos disponer de una imagen adaptada a los servicios que necesitamos para iniciar contenedores tendremos que crearla, en este artículo explicaré cómo crear una imagen para docker personalizada.

Antes de crear una imagen para docker podemos buscar en el [registro de imágenes de Docker](https://registry.hub.docker.com/) que han creado otros usuarios y los han compartido por si hay alguna que ya se adapte a nuestras necesidades, si nos sirve alguna y es algo popular nos evitaremos tener que modificarla nosotros mismos según salgan nuevas versiones de los servicios que use. El registro de imágenes de docker es un servicio en el que las organizaciones publican versiones oficiales junto a otros usuarios que comparten y colaboran en la creación de las imágenes. Para los servicios más conocidos dispondremos ya de las imágenes como podrían ser: [mysql](https://registry.hub.docker.com/_/mysql/), [redis](https://registry.hub.docker.com/_/redis/), [postgresql](https://registry.hub.docker.com/_/postgres/), [ubuntu](https://registry.hub.docker.com/_/ubuntu/), [wordpress](https://registry.hub.docker.com/_/wordpress/), [nginx](https://registry.hub.docker.com/_/nginx/), [mongodb](https://registry.hub.docker.com/_/mongo/), ...

Si no hay ninguna que se adapte totalmente a nuestras necesidades, no nos gusta como están construidas las existes o no confiamos en el mantenimiento que puedan tener esas imágenes podemos crear las nuestras propias. Para crear una imagen de docker se necesita una receta en forma de [archivo Dockerfile](http://docs.docker.com/reference/builder/) que contiene la descripción e instrucciones para construir la imagen. Para crear un dockerfile podemos basarnos en los de las imágenes del registro de docker.

{{< tableofcontents >}}

### Archivo Dockerfile

Este podría ser el contenido y la receta de un dockerfile si quisiésemos crear una imagen de docker con el servidor web nginx como servicio basada en la distribución [Ubuntu][ubuntu].

{{< code file="Dockerfile" language="dockerfile" options="" >}}

Los archivos Dockerfile son archivos de texto con una secuencia de directivas:

* _FROM_: indica la imagen base a partir de la cual crearemos la imagen que construirá el Dockerfile.
* _MAINTAINER_: documenta el creador de la imagen.
* _ENV_: establece el valor de una variable de entorno para los siguientes comandos _RUN_.
* _RUN_: permite ejecutar una instrucción en el contenedor, por ejemplo, para instalar algún paquete mediante el gestor de paquetes (apt-get, yum, rpm, ...).
* _ADD_: permite añadir un archivo al contenedor, en muchas ocasiones se utiliza para proporcionar la configuración de los servicios (ssh, mysql, ...).
* _VOLUME_: establece puntos de montaje que al usar el contenedor se pueden proporcionar, los volúmenes son al forma de externalizar un determinado directorio y proporcionar persistencia (las imágenes de docker son de solo lectura y no almacenan datos entre diferentes ejecuciones).
* _EXPOSE_: indica los puertos TCP/IP por los que se pueden acceder a los servicios del contenedor, los típicos son 22 (SSH), 80 (HTTP) u 443 (HTTP).
* _CMD_: establece el comando del proceso de inicio que se usará si no se indica uno al iniciar un contenedor con la imagen.

### Construir una imagen de Docker

Las instrucciones RUN y ADD permiten aprovisionar la imagen a partir de la imagen base, ejecutando comandos para instalar paquetes como Nginx y añadir archivos de configuración si los hubiera copiados de la máquina anfitrión. El archivo _Dockerfile_ es la receta con la que se construye una imagen de Docker.

{{< code file="docker-build.sh" language="bash" options="" >}}
{{< code file="docker-build.out" language="plain" options="" >}}

La imagen construida queda almacenada en el registro local de imágenes.

{{< code file="docker-images.sh" language="bash" options="" >}}
{{< code file="docker-images.out" language="plain" options="" >}}

### Iniciar un contenedor con la imagen

El siguiente comando inicia una instancia del contenedor con un servidor web virtual para el dominio _www.127.0.0.1.xip.io_.

{{< code file="docker-run.sh" language="bash" options="" >}}
{{< code file="www.127.0.0.1.xip.io.conf" language="nginx" options="" >}}

Otra forma de iniciar el contenedor es con un archivo de [Docker Compose][docker-compose] que básicamente contiene los mismos parámetros del comando _docker run_.

{{< code file="docker-compose.yml" language="yaml" options="" >}}
{{< code file="docker-compose-up.sh" language="bash" options="" >}}

Accediendo con el navegador al servidor nginx del contenedor se devuelve la página por defecto.

{{< image
    gallery="true"
    image1="image:nginx.png" optionsthumb1="300x200" title1="Nginx" >}}

Para aprender más sobre Docker es buena idea seguir un manual de referencia como los libros [Docker: Up & Running](https://amzn.to/3DgQj7G) y [Docker in Action](https://amzn.to/3pH6uEr).

{{< amazon
    linkids="666d0aff3a2629cd87c205370bc5ae87,9d344246cd59cd65a952305379c2556a"
    asins="1492036730,1633430235" >}}

{{% sourcecode git="blog-ejemplos/tree/master/DockerNginx" command="./docker-build.sh" %}}

{{< reference >}}
* [Lista de enlaces sobre Docker](http://www.nkode.io/2014/08/24/valuable-docker-links.html)
* [Introducción a Ansible][blogbitix-52]
* [Integración entre Ansible y Docker][blogbitix-53]
* [Introducción a Bitnami][blogbitix-54]
{{< /reference >}}

{{% /post %}}
