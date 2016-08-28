---
pid: 53
title: "Integración entre Ansible y Docker"
url: "/2014/11/integracion-entre-ansible-y-docker/"
date: 2014-11-22T10:28:52+01:00
updated: 2014-12-29T19:00:00+01:00
sharing: true
comments: true
tags: ["gnu-linux", "planeta-linux", "planeta-codigo", "planeta-arch-linux", "blog-stack"]
series: ["docker"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="ansible.png" title1="Ansible" image2="docker.png" title2="Docker" >}}

En la [introducción de Docker][blogbitix-49] y [inicio básico de docker][blogbitix-50] explicaba como usar Docker y en la [introducción a Ansible][blogbitix-52] explicaba las características de Ansible y como usarlo. Podemos usar [Docker][docker] y [Ansible][ansible] de forma separada pero también podemos combinarlas para beneficiarnos de las propiedades de cada una. En Ansible disponemos de dos módulos que nos permite manejar las imágenes y los contenedores de Docker, estos son [docker](http://docs.ansible.com/docker_module.html) y [docker_image](http://docs.ansible.com/docker_image_module.html).

Usando tareas de ansible podemos automatizar el arranque del servicio de docker, la construcción de las imágenes con los Dokerfile y el inicio o parada de los contenedores de docker. En el ejemplo se construye una imagen base en la que se basarán el resto de imágenes, se crea una imagen con mysql, otra de redis y finalmente la imagen apps donde se ejecutarán las aplicaciones que usarán los servicios de mysql y redis.

{{% gist id="835ff0192d48b613714a" file="install.yml" %}}

Una vez que disponemos de las imágenes de docker podemos arrancar contenedores con ellas automatizándolo con ansible. En este caso se inicia el servicio de docker, se inicializan los contenedores con los volúmenes donde se guardan los datos de forma persistente de mysql y redis, se inician los contenedores de mysql y redis usando los volúmenes anteriores y finalmente el contenedor de las aplicaciones. Al final, se muestra cierta información de las imágenes como sus direcciones IP.

{{% gist id="835ff0192d48b613714a" file="start.yml" %}}

Ansible [dispone muchos módulos](http://docs.ansible.com/list_of_all_modules.html) que nos permiten automatizar las tareas:

* [Módulos para manejo de entornos cloud](http://docs.ansible.com/list_of_cloud_modules.html) ([Amazon EC2][amazon-ec2], [Azure][azure], [Digital Ocean][digital-ocean], [OpenStack][openstack], [Rackspace][rackspace])
* [Módulos para ejecutar comandos](http://docs.ansible.com/list_of_commands_modules.html)
* [Módulos para administración de bases de datos](http://docs.ansible.com/list_of_database_modules.html)
* [Módulos para manejo de archivos](http://docs.ansible.com/modules_by_category.html)
* [Módulos para manejo de paquetes](http://docs.ansible.com/list_of_packaging_modules.html)
* [Módulos para manejo de sistemas de control de versiones](http://docs.ansible.com/list_of_source_control_modules.html)
* Y algunos más

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Introducción a Docker][blogbitix-49]
* [Guía de inicio básico de Docker][blogbitix-50]
* [Cómo crear una imagen para Docker usando un Dockerfile][blogbitix-51]
* [Introducción a Ansible][blogbitix-52]
* [Introducción a Bitnami][blogbitix-54]
* http://docs.ansible.com/docker_module.html<br>
* http://docs.ansible.com/docker_image_module.html
{{% /reference %}}

{{% /post %}}
