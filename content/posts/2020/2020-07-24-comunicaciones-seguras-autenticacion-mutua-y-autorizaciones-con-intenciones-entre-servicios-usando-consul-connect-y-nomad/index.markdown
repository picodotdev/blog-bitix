---
pid: 502
type: "post"
title: "Comunicaciones seguras, autenticación mutua y autorizaciones con intenciones entre servicios usando Consul Connect y Nomad"
url: "/2020/07/comunicaciones-seguras-autenticacion-mutua-y-autorizaciones-con-intenciones-entre-servicios-usando-consul-connect-y-nomad/"
date: 2020-07-24T15:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:consul-connect-communications.webp"
tags: ["gnu-linux", "planeta-codigo"]
series: ["hashicorp"]
summary: "La configuración de seguridad estática y basada en direcciones IP no es adecuada en un entorno en el que los recursos de computación está compartidos y no son confiables, ni para aplicaciones basadas en microservicios cuyo número de servicios e instancias cambia a lo largo del tiempo. Consul y Consul Connect ofrecen un mecanismo de comunicación segura adaptados a la computación en la nube y adecuado para aplicaciones basadas en microservicios."
---

{{% post %}}

{{< logotype image1="hashicorp-consul.svg" image2="hashicorp-nomad.svg" image3="hashicorp.svg" >}}

El método habitual para proporcionar seguridad a varios sistemas que se comunican por red ha sido utilizar protocolos de comunicaciones cifrados, poner cortafuegos e impedir una comunicación directa con algunos sistemas como las base de datos en ocasiones en base a direcciones IP. La configuración estática cuando hay que adaptarla por añadir nuevos sistemas es difícil de cambiarla.

En un entorno en la nube, aún con sus propias medidas de seguridad y aislamiento que implementan, la infraestructura no es confiable por ser compartida con otras organizaciones como demuestran los [fallos de seguridad de meltdown y spectre de los procesadores][blogbitix-293] por su ejecución especulativa. En una arquitectura dinámica de múltiples microservicios e instancias una configuración estática no es práctica para un sistema que por naturaleza es altamente dinámico y en un medio de computación compartido no confiable hay que adoptar medidas adicionales.

La propuesta que ofrecen algunas herramientas nativas para la computación en la nube es proporcionar seguridad realizando cada comunicación servicio a servicio de forma cifrada y transparente para las aplicaciones incluso si estas no implementan comunicación cifrada de forma nativa, con autenticación mutua entre los dos servicios utilizando su identidad en vez de direcciones IP y con autorizaciones basadas en intenciones fáciles de administrar y cambiar.

Una de estas herramientas es [Consul][Consul] que proporciona la funcionalidad de registro y descubrimiento de servicios y conectividad entre esos servicios. Consul Connect proporciona comunicaciones seguras de forma transparente para las aplicaciones.

{{< image
    gallery="true"
    image1="image:infrastructure-static.webp" optionsthumb1="300x200" title1="Infraestructura estática"
    image2="image:infrastructure-static-approach.webp" optionsthumb2="300x200" title2="Infraestructura estática"
    caption="Infraestructura estática" >}}
{{< image
    gallery="true"
    image1="image:infrastructure-dynamic.webp" optionsthumb1="300x200" title1="Infraestructura dinámica"
    image2="image:infrastructure-dynamic-approach.webp" optionsthumb2="300x200" title2="Infraestructura dinámica"
    caption="Infraestructura dinámica" >}}

El esquema de funcionamiento de [Consul Connect][consul-connect] es hacer que los servicios no se comuniquen directamente entre ellos sino que utilizan _proxys_, la creación del enlace de comunicación se delega en Consul que crea los _proxys_ y con ellos le es posible proporcionar comunicaciones seguras, con autenticación mutua y posibilitando autorización con intenciones. Las intenciones son los permisos que establece el operador de Consul para establecer que dos servicios tiene permitido la comunicación entre ellos, cualquier intento de comunicación si no está aprobado de forma explícita por su intención no se permite.

{{< image
    gallery="true"
    image1="image:consul-connect-communications.webp" optionsthumb1="300x200" title1="Comunicación servicio a servicio con Consul Connect"
    caption="Comunicación servicio a servicio con Consul Connect" >}}

Otra de las herramientas de [HashiCorp][hashicorp] es [Nomad][nomad] que proporciona la funcionalidad de orquestador de servicios, creando las instancias de los servicios necesarias en los diferentes nodos de computación. Nomad se integra con Consul y Consul Connect haciendo más sencillo utilizar todas estas tecnologías.

En el siguiente ejemplo sigo la [guía de ejemplo para probar Consul Connect](https://www.nomadproject.io/docs/integrations/consul-connect) con Nomad utilizando [Envoy][envoyproxy] para crear los _proxys_. Para ejecutar el ejemplo es necesario obtener los binarios de Consul, Nomad y Envoy además de las utilidades de comunicaciones de _cni-plugins_ y [Docker][docker].

Hay que instalar los paquetes de Consul, Nomad, Envoy y _cni-plugins_ de la distribución [GNU][gnu]/[Linux][linux] o la descarga directa de sus binarios accesibles en la variable _PATH_ del sistema utilizada por los intérpretes de comandos para buscar comandos. Para obtener Envoy hay que ejecutar el siguiente comando que instala el binario de Envoy en la carpeta _/usr/local/bin_ que incluida en la variable _PATH_.

{{< code file="getenvoy.sh" language="bash" options="" >}}

Nomad requiere que los _cni-plugins_ esté ubicados en la carpeta _/opt/cni/bin_, en Arch Linux se instalan en la carpeta _/usr/lib/cni/_ por lo que hay que crear un enlace simbólico para que sean encontrados por Nomad. En Ubuntu la opción es realizar la instalación descargando el paquete manualmente.

{{< code file="install-cni-plugins.sh" language="bash" options="" >}}
{{< code file="install-cni-plugins-archlinux.sh" language="bash" options="" >}}

Con los binarios necesarios instalados hay que iniciar Consul y Nomad con los siguientes comandos que los arrancan en su configuración de desarrollo y con el soporte para Consul Connect en Nomad.

{{< code file="start-consul-nomad.sh" language="bash" options="" >}}

El siguiente paso es enviar el Nomad la definición de los servicios para que planifique su ejecución, en este caso utilizando contenedores de Docker que ha de estar instalado previamente y con su servicio del sistema iniciado.

* [Introducción y características de Docker][blogbitix-49]

La definición incluye dos servicios uno que proporciona un contador y otro que obtiene el valor de ese contador y lo muestra en una página web. En la definición del servicio _count-api_ se define que va a ofrecer su comunicación por red en el puerto 9001 asociado a la interfaz de red _localhost_ de modo que este puerto solo pueda ser accedido por Consul a través del _proxy_ que crea, la definición del servicio _count-dashboard_  ofrece su servicio en el puerto 9002 y que tiene tiene como dependencia el servicio _count-api_ para el que Consul crea un nuevo _proxy_, el servicio _count-dashboard_ se conecta al puerto _8080_ para comunicarse con el  servicio _count-api_ mediante los _proxys_ de Consul Connect. Los servicios solo se comunican con los _proxys_ y son los _proxys_ los que se comunican entre ellos.

{{< code file="nomad-run.sh" language="bash" options="" >}}

En la definición de _job_ las partes relativas a Consul Connect están en los bloques o _stanzas_ _connect_, en el caso de _count-api_ simplemente para indicar que desea un _proxy_ y en el caso de _count-dashboard_ para indicar que desea un _proxy_ para conectarse al servicio _count-api_, la variable de entorno _COUNTING\_SERVICE\_URL_ contiene la dirección del _proxy_ que permite la conexión con _count-api_.

{{< code file="connect.nomad" language="hcl" options="" >}}

El resultado es esta aplicación sencilla de ejemplo que muestra un contador en una página web con el tiempo se va incrementando. En las consolas de administración de Consul se observan los servicios registrados y en Nomad la ejecución de los mismos.

{{< image
    gallery="true"
    image1="image:consul-console.webp" optionsthumb1="300x200" title1="Consola de administración de Consul"
    image2="image:nomad-console.webp" optionsthumb2="300x200" title2="Consola de administración de Nomad"
    image3="image:dashboard.webp" optionsthumb3="300x200" title3="Servicio count-dashboard"
    caption="Consola de administración de Consul y Nomad y servicio count-dashboard" >}}

Dado que el servicio _count-dashboard_ se conecta con el servicio _count-api_ a través de los _proxys_ que crea Consul, Consul es capaz de permitir o denegar la comunicación con las intenciones. Las intenciones son las autorizaciones concedidas a cada servicio origen y servicio destino de comunicación. La ventaja de las intenciones es que son agnósticas de la red como redes físicas, en la nube, basadas en software o cualesquiera otras ya que están basadas en la identidades de los servicios.

En el modo desarrollo de Consul aún sin una intención la comunicación se autorizan las comunicaciones pero si se crea una intención que deniegue la comunicación desde el servicio _count-dashboard_ a _count-api_ el servicio _count-dashboard_ no es capaz de recuperar el contador y se muestra desconectado. Cambiando la intención para que autorice el tráfico la comunicación se restaura y el servicio _count-dashboard_ vuelve a mostrar el contador.

{{< image
    gallery="true"
    image1="image:consul-intentions-deny.webp" optionsthumb1="300x200" title1="Intención que deniega la comunicación"
    image2="image:dashboard-deny.webp" optionsthumb2="300x200" title2="Intención que deniega la comunicación"
    caption="Intención que deniega la comunicación" >}}

{{< image
    gallery="true"
    image1="image:consul-intentions-allow.webp" optionsthumb1="300x200" title1="Intención que permite la comunicación"
    image2="image:dashboard-allow.webp" optionsthumb2="300x200" title2="Intención que permite la comunicación"
    caption="Intención que permite la comunicación" >}}

{{% /post %}}
