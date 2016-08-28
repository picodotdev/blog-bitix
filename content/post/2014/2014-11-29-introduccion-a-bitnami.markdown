---
pid: 54
title: "Introducción a Bitnami"
url: "/2014/11/introduccion-a-bitnami/"
date: 2014-11-29T10:45:45+01:00
updated: 2015-11-10T19:00:00+01:00
sharing: true
comments: true
tags: ["blog-stack", "hardware", "gnu-linux", "planeta-arch-linux", "planeta-codigo", "planeta-linux", "software", "software-libre"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="bitnami.png" title="Bitnami" >}}

Instalar algunas aplicaciones, herramientas o servicios no siempre es sencillo y rápido. En la mayoría de los casos los pasos están explicados suficientemente pero en la práctica nos pueden surgir dudas o encontrarnos con problemas que no están documentados que pueden hacer que la experiencia de instalación no sea agradable o necesitar dependencias que hay que instalar a la vez para obtener un funcionamiento correcto. Además, si no empleamos una máquina virtual necesitaremos instalarlo en nuestro propio equipo o servidor cuando quizá solo nos interesa evaluar el producto con lo que al hacer la desinstalación acabaremos con archivos temporales que quizá no se eliminen y permanezcan en nuestro equipo para siempre o hasta que nos demos cuenta.

[Bitnami][bitnami] es una amplia colección de servicios populares que nos permite instalarlos y disponer de ellos rápidamente completamente configurados sin necesidad de dedicar tiempo a conocer como instalarlos, además, si empleamos virtualización con [VirtualBox][virtualbox] evitamos que nuestro equipo quede completamente limpio una vez que quitemos el servicio. Esta colección de servicios podemos instalarla en nuestro propio equipo, en una máquina virtual con VirtualBox, [VMware][vmware] o en la nube por ejemplo de [Amazon EC2][amazon-ec2], [Azure][azure] o [Google Cloud][google-cloud] y proximamente usando [Docker][docker].

¿Alguna vez has querido probar [Wordpress][wordpress], [Redmine][redmine], [Drupal][drupal], [MediaWiki][mediawiki], [phpBB][phpbb], [Liferay][liferay], [Jenkins][jenkins], [Plone][plone], [Alfresco][alfresco], ...? Estos son solo unos pocos ejemplos de [todos los servicios que nos ofrece Bitnami](https://bitnami.com/stacks) listos para que empecemos a usarlos en unos pocos minutos, hay muchos otros disponibles y en un futuro se agregarán más según las peticiones que realicen los usuarios de Bitnami. Algunas aplicaciones están compuestas de una pila completa de servicios como por ejemplo LAMP (Linux, Apache, MySQL, PHP) o LAPP (Linux, Apache, PostgreSQL, PHP) que incluyen una lista completa de dependencias que necesitan como FastCGI, OpenSSL, phpMyAdmin, ModSecurity, SQLite, Varnish, ImageMagick, xDebug, Xcache, OpenLDAP, ModSecurity, Memcache, OAuth, PEAR, PECL, APC, GD o cURL. Adicionalmente podemos usar una única pila como LAMP para instalar varios [módulos](https://bitnami.com/stack/lamp/modules) en vez de instalar cada aplicación individualmente (Drupal, MediaWiki, ...) de forma que todas las aplicación compartan la infraestructura de la misma pila.

En un futuro parece que Bitnami ofrecerá soporte para [Docker][docker] haciendo que no necesitemos ni siquiera un entorno de virtualización, ahorrando espacio en disco y ofreciendo el máximo rendimiento que ofrezca la máquina sin ningún tipo de penalización que impone virtualizar.

## ¿Cómo empezar a usar Bitnami?

A continuación explicaré la opción de usar Bitnami con VirtualBox, para el ejemplo usaré la aplicación de Redmine. En la [guía de inicio rápido de Bitnami](http://wiki.bitnami.com/Virtual_Appliances_Quick_Start_Guide) están eplicadas otras opciones y preguntas frecuentes, en este caso nos interesa la [sección para VirtualBox](http://wiki.bitnami.com/Virtual_Appliances_Quick_Start_Guide#Virtual_Box).

Teniendo instalado previamente VirtualBox, primeramente necesitaremos descargar la imagen del disco que contiene la aplicación. En el [apartado Virtual Machines](https://bitnami.com/stack/redmine/virtual-machine) pulsamos el botón «Download», aunque indique que es para VMware también podremos usarla en VirtualBox. A continuación crearemos una máquina virtual seleccionado como base Ubuntu 64 bits con al menos 512 MiB, como disco de arranque seleccionamos la opción de uno existente  y seleccionamos la imagen vmdk descargada de la aplicación una vez descomprimida. Habilitamos el adaptador de red de tipo adaptador puente y seleccionamos el dispositivo que estemos usando para conectarnos a internet de entre los que disponemos, WIFI o ethernet.

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/54/virtualbox-crear-imagen-bitnami-1.png" title="Crear imagen Bitnami" data-gallery><img src="assets/images/custom/posts/54/virtualbox-crear-imagen-bitnami-1-thumb.png"></a>
	<a href="assets/images/custom/posts/54/virtualbox-crear-imagen-bitnami-2.png" title="Crear imagen Bitnami (red)" data-gallery><img src="assets/images/custom/posts/54/virtualbox-crear-imagen-bitnami-2-thumb.png"></a>
</div>

Hechos estos pasos ya estamos en condiciones de arrancar la máquina virtual. Una vez que se inicie completamente veremos en la terminal que se nos solicita usuario y contraseña para iniciar sesión en el sistema, además, en este caso nos indica la dirección IP (en el caso de las capturas mostradas 192.168.0.11) que necesitaremos usar para acceder al servicio con el navegador. En el mismo [apartado de Virtual Machines de Bitnami](https://bitnami.com/stack/redmine/virtual-machine) para Redmine disponemos de los usuarios y contraseñas para acceder tanto a la máquina virtual desde la terminal (usuaro: bitnami, contraseña: bitnami) y para autenticarnos como usuarios accediendo a Redmine con el navegador (usuario: user, contraseña: bitnami).

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/54/virtualbox-terminal.png" title="Terminal de Bitnami" data-gallery><img src="assets/images/custom/posts/54/virtualbox-terminal-thumb.png"></a>
	<a href="assets/images/custom/posts/54/redmine-web.png" title="Aplicación de Bitnami (Redmine)" data-gallery><img src="assets/images/custom/posts/54/redmine-web-thumb.png"></a>
</div>

En definitiva, Bitnami nos puede ayudar enormemente si necesitamos usar cualquiera de los servicios que ofrece tanto para probarlos con una máquina virtual o para instalarlos en la nube de Amazon EC2, Azure o en Google Cloud. La nube nos permite librerarnos de adquirir hardware y posibilita escalarlo según nuestras necesidades siempre que estemos dispuestos a pagar su coste pero en algunos casos un Brix o NUC como por ejemplo <a href="http://www.amazon.es/gp/product/B00HYEU0C8/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=B00HYEU0C8&linkCode=as2&tag=blobit-21">Gigabyte GB-BXI5-4570R (Core i5, 16GiB)</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=B00HYEU0C8" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;"> que pueden incluir 16 GiB y algunos 32 GiB son mas que suficientes para una infraestructura interna de una empresa pequeña que incluya control de versiones, gestor de peticiones, integración continua, base de datos, servidor de aplicaciones, ... estos ordenadores pequeños en tamaño pero con una capacidad de proceso y memoria nada despreciable son una opción a considerar para algunas tareas y pueden actuar perfectamente como servidores en los que alojar algunos servicios para consumo interno.

<div class="media-amazon" style="text-align: center;">
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00HFCTV4W&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00HYEU0C8&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B00GHAKGDI&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Bitnami](https://bitnami.com)
* [Wikipedia Bitnami](https://en.wikipedia.org/wiki/Bitnami)
* [Bitnami, Una tienda de Aplicaciones para Servidores](http://www.jsitech.com/generales/bitnami-una-tienda-de-aplicaciones-para-servidores/)
* [Introducción a Docker][blogbitix-49]
* [Guía de inicio básico de Docker][blogbitix-50]
* [Cómo crear una imagen para Docker usando un Dockerfile][blogbitix-51]
* [Introducción a Ansible][blogbitix-52]
* [Integración entre Ansible y Docker][blogbitix-53]
* [Introducción al gestor de proyectos y tareas Redmine][blogbitix-57]
* [Instalar y usar plugin ágil en Redmine][blogbitix-59]
* [Introducción e inicio a Drupal][blogbitix-60]
{{% /reference %}}

{{% /post %}}
