---
pid: 398
title: "Introducción a Nomad para gestionar aplicaciones y microservicios"
url: "/2019/04/introduccion-a-nomad-para-gestionar-aplicaciones-y-microservicios/"
date: 2019-04-14T13:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["planeta-codigo", "programacion", "software"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="nomad.svg" title1="Nomad" width1="300" image2="consul.svg" title2="Consul" width2="300" image3="hashicorp.svg" title3="HashiCorp" width3="200" >}}

En las arquitecturas de aplicaciones basadas en microservicios cada microservicio o simplemente servicio es una aplicación distinta e independiente, son varias aplicaciones que hay que gestionar y desplegar de forma individual o de forma coordinada.

Dado su número hay que automatizar todas las tareas para tratar de conseguir la menor intervención manual, ningún proceso manual si es posible. Han de tratarse como ganado de forma masiva en vez de como animales de compañía que requieran atención individual para continuar generando valor en un proyecto con el tiempo disponible en vez de dedicarlo a tareas que no lo aportan.

Dado que cada microservicio puede emplear una tecnología diferente es necesario algo que permita tratarlos a todos por igual, esta tecnología son los contenedores que hacen un papel similar al que hacen en el transporte de mercancías en barcos.

Hay varias tecnologías para gestionar los microservicios y crear _clusters_ de máquinas en las que desplegarlos, una de ellas es [Docker Swarm][docker-swarm] sencilla e integrada con [Docker][docker] pero no con tantas funcionalidades como otra de las populares que es [Kubernetes][kubernetes] y para usarlo en una máquina local [minikube][minikube], ofrece mas funcionalidad pero añade una complejidad significativa que para algunos casos de uso no compensa además requiere mas tiempo para dominarla. Una solución intermedia conservando la sencillez pero con mas funcionalidad es [Nomad] de [HashiCorp][hashicorp]. Otra de sus características destacadas es que el _cluster_ de _Nomad_ puede estar formado en diferentes centros de datos y proveedores de la nube al mismo tiempo, por ejemplo en [AWS][amazon-web-services] y [GCP][google-cloud] entre otros. En la sección [Nomad vs. Other Software](https://www.nomadproject.io/intro/vs/index.html) de su documentación se compara con otras opciones.

_Nomad_ a diferencia _Docker_ no solo puede gestionar contenedores _docker_ sino también tareas del sistema y otras como maquinas virtuales [qemu][qemu] o contenedores con [rkt], a diferencia de _Kubernetes_ es mucho mas sencilla pero conservando funcionalidad suficiente para muchos casos de uso. _Nomad_ requiere de otra de las herramientas de HashiCorp que es [Consul][consul] para el registro y descubrimiento y para la configuración del _cluster_, también se integra con otras de sus herramientas como [Vault][vault] para guardar cifrados datos sensibles como contraseñas y certificados. Con [Connect](https://www.consul.io/docs/connect/platform/nomad.html) es capaz de proporcionar conexión TLS con autenticación mutua de forma transparente para los servicios.

Los _jobs_ son la unidad de trabajo que contienen la definición de los servicios, se definen en un [archivo de configuración](https://www.nomadproject.io/docs/job-specification/index.html) donde los elementos son el nombre, los grupos de tareas, las tareas y en cada tarea el _driver_ que usa dependiendo del cual se proporciona la configuración apropiada. Se pueden configurar variables de entorno, memoria asignada a cada tarea, propiedades de red y CPU.

_Nomad_ y _Consul_ cada uno son un binario ejecutable sin ninguna otra dependencia. Basta con descargarlos e incluirlos en el _path_ del sistema. En este ejemplo hay definido un _job_ compuesto por una tarea de un contenedor _docker_ de [nginx][nginx] configurado en un puerto aleatorio y con 1 GB de memoria para cada una de las dos instancias del servicio. Dado que lo usa es necesario [instalar Docker][blogbitix-50].

{{< code file="nginx.nomad" language="Plaintext" options="" >}}

_Consul_ y _Nomad_ se ejecutan con los siguientes comandos en modo desarrollo.

{{< code file="nomad-consul.sh" language="Bash" options="" >}}

Iniciados se pueden enviar _jobs_ y ver su estado, dirección y puerto asignado así como los _logs_ generados.

{{< code file="nomad.sh" language="Bash" options="" >}}

También poseen una interfaz web integrada en la que consultar la misma información, la de _Consul_ está en el puerto _8500_ y la de _Nomad_ en el _4646_ donde ver el estado de los _jobs_ y el progreso de los despliegues.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="consul.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="200x150" title1="Consul"
        image2="nomad-1.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="200x150" title2="Nomad"
        image3="nomad-2.png" command3="Fit" commandthumb3="Fit" options3="2560x1440" optionsthumb3="200x150" title3="Nomad"
        caption="Interfaces web de Consul y Nomad" >}}
</div>

_Nomad_ permite varias estrategias para actualizar los _jobs_ a una nueva versión de un servicio, basta modificar la configuración del _job_, volverlo a enviar a _Nomad_ y este se encarga de actualizar las instancias siguiendo la estrategia _rolling_, _blue/green_ o _canary_ definida en el _job_, pero eso lo muestro mas detalladamente en otro artículo.

La [documentación de _Nomad_](https://www.nomadproject.io/docs/index.html), _Consul_ y otros productos de HashiCorp dedicados a la infraestructura en la nube esta muy bien explicada y detallada, este artículo solo es un resumen de las partes básicas para conocer como empezar a usarlo. En el siguiente vídeo se hace una pequeña explicación y demostración.

<div class="media media-video" style="text-align: center;">
	<iframe width="640" height="360" src="https://www.youtube.com/embed/A6CuZUoINX0?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

{{% /post %}}
