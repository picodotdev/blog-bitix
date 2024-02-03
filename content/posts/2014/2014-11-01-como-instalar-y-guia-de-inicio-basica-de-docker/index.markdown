---
pid: 50
type: "post"
title: "Cómo instalar y guía de inicio básica de Docker"
url: "/2014/11/como-instalar-y-guia-de-inicio-basica-de-docker/"
aliases: ["/2014/11/inicio-basico-de-docker/"]
date: 2014-11-01T09:47:51+01:00
updated: 2015-05-26T20:00:00+01:00
index: true
rss: true
sharing: true
comments: true
imagePost: "logotype:docker.svg"
tags: ["gnu-linux", "planeta-codigo"]
series: ["docker"]
summary: "La tecnología de contenedores nos ofrece múltiples ventajas para nuestras aplicaciones. Docker es una de las más nombradas y que está creciendo muy rápidamente. Es muy sencillo empezarla a usar como mostraré en unos pocos comandos a continuación."
---

{{% post %}}

{{< logotype image1="docker.svg" >}}

En el [artículo anterior introductorio sobre Docker][blogbitix-49] comentaba cuales son sus principales características, que diferencias tiene con la virtualización y algunos casos y motivos por los que nos puede interesar usarlo. El objetivo de este artículo será a modo de guía rápida como empezar a usar [Docker][docker]. Comentaré cuales son los comandos básicos para manejar Docker, las imágenes y los contenedores.

Antes de empezar con la guía propiamente hay que tener en cuenta que otra de las características de Docker es que únicamente se inicia un solo proceso, cuando este finaliza finaliza la instancia del contenedor. Aunque en principio un contenedor solo tiene un proceso, podemos usar como proceso inicial uno que arranque otros, la [imagen pushion](https://registry.hub.docker.com/u/phusion/baseimage/) tiene un sistema de inicio adaptado al funcionamiento de Docker (no siendo así upstart de las imágenes de ubuntu o systemd usado por otras distribuciones) con el que podemos iniciar otros procesos, en cualquier caso es recomendable que un contenedor sino tiene un solo proceso tenga unos pocos procesos relacionados con el servicio que proporciona la imagen, la recomendación es no proporcionar una imagen con un montón de servicios/procesos sino varias pequeñas cuyos servicios colaboren. Una imagen de un contenedor es básicamente el sistema de archivos que usará el proceso que iniciamos en el contenedor, necesitamos obtener o construir una imagen para iniciar los contenedores y trabajar con Docker.

Primeramente deberemos instalar el paquete de Docker, en el caso de [Arch Linux][archlinux] con el siguiente comando:

{{< code file="docker-1.sh" language="bash" options="" >}}

Para poder usar Docker sin emplear el comando _sudo_ hay que añadir nuestro usuario al grupo _docker_:

{{< code file="docker-2.sh" language="bash" options="" >}}

Una vez instalado el paquete y añadido nuestro usuario al grupo _docker_ podemos iniciar el servicio de Docker, en Arch Linux con systemd:

{{< code file="docker-3.sh" language="bash" options="" >}}

Con el comando _docker images_, _docker ps_, _docker ps -a_ podemos respectivamente ver las imágenes de Docker disponibles en nuestro sistema, las instancias de los contenedores iniciadas y las instancias de los contenedores incluyendo las no iniciadas.

{{< code file="docker-4.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:docker-images.webp" optionsthumb1="300x200" title1="docker-images" >}}

Con los comandos _docker rm [contenedor]_ y _docker rmi [imagen]_ podemos eliminar las instancias de contenedores y las imágenes que no estén siendo usadas por ningún contenedor, si están siendo usadas deberemos eliminar primero el contenedor.

Una vez conocemos los comandos básicos para gestionar las imágenes y contenedores aún nos quedan conocer los comandos para obtener las imágenes y como iniciarlas. No hace falta que las imágenes las construyamos nosotros desde cero sino que podemos utilizar las que otras personas han construido. Docker dispone de un [registro o repositorio de imágenes](https://registry.hub.docker.com/) en el que la comunidad (otras personas) publica las que ellos han construido, en este registro podemos encontrar imágenes para cualquier servicio de los más utilizados ya sea [MySQL][mysql], [Nginx][nginx], [Redis][redis], [WordPress][wordpress], [PostgreSQL][postgresql], [Ubuntu][ubuntu], [CentOS][centos], ... Este repositorio de imágenes hace que podamos disponer de estos servicios muy fácilmente, sin embargo, hay que tener en cuenta que algunas imágenes no son construidas por alguien "oficial" de ese servicio, cualquier persona puede [construir una imagen de un servicio][blogbitix-51] y hacerlo disponible en el repositorio esto hace que algunas imágenes puedan no ser de la máxima calidad o que estén apropiadamente mantenidas. Por ello, es recomendable utilizar las imágenes más descargadas y usadas, a pesar de todo con Docker si una imagen no nos gusta podemos basarnos en ella para construir una que se adapte totalmente a nuestras necesidades y con la que nos sintamos cómodos.

Para obtener una imagen usamos el comando _docker pull [imagen]_ indicando el usuario que creó la imagen, el nombre de la imagen y el tag. En el siguiente enlace en la pestaña tags podemos ver las [imágenes para Docker de Debian y sus tag](https://registry.hub.docker.com/_/debian/):

{{< code file="docker-5.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:docker-pull.webp" optionsthumb1="300x200" title1="docker-pull" >}}

Una vez descargada la imagen podemos iniciar un contenedor de la imagen con:

{{< code file="docker-6.sh" language="bash" options="" >}}

En la terminal que se inicia podemos usar cualquier comando que usaríamos en un sistema [debian][debian] como apt-get.

Este es el momento para explicar otra de las peculiaridades de los contenedores de Docker y es que estos no conservan el estado de una ejecución a otra y cada vez que ejecutemos el comando se creará una nueva instancia del contenedor. Para conseguir la persistencia al crear las imágenes se pueden establecer puntos de montaje, esta persistencia está externalizada en otros contenedores con el solo propósito de servir como volúmenes de datos, también se puede montar un directorio del sistema anfitrión.

Si usamos el comando «uname -a» puede verse que aunque el contenedor usa el sistema de archivos de una distribución debian el kernel empleado es el del sistema anfitrión, esto es así porque los contenedores Docker en esencia no son más que procesos dentro del sistema.

{{< image
    gallery="true"
    image1="image:docker-run.webp" optionsthumb1="300x200" title1="docker-run" >}}

Continuando esta serie de artículos sobre Docker explicaré como construir una imagen mediante los Dockerfiles con la que podamos usar una base de datos mysql y explicaré en más detalle como podemos conseguir la persistencia que necesitaremos para almacenar los datos de la base de datos usando otro contenedor con este propósito.

{{< amazon
    linkids="https://amzn.to/3unwPz9,https://amzn.to/3Oud8MX"
    asins="1492036730,1633430235"
    titles="Docker: Up & Running: Shipping Reliable Containers in Production,Docker in Action" >}}

{{< reference >}}
* [Introducción a Ansible][blogbitix-52]
* [Integración entre Ansible y Docker][blogbitix-53]
* [Introducción a Bitnami][blogbitix-54]
* [Docker Hub Registry](https://registry.hub.docker.com/)
{{< /reference >}}

{{% /post %}}
