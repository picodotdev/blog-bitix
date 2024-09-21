---
pid: 657
type: "post"
title: "Las funcionalidades de un service mesh en una arquitectura de microservicios"
url: "/2022/10/las-funcionalidades-de-un-service-mesh-en-una-arquitectura-de-microservicios/"
date: 2022-10-21T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hashicorp-consul.svg"
tags: ["programacion", "planeta-codigo"]
series: ["hashicorp"]
summary: "Los monolitos son la opción más sencilla y mejor en la mayoría de casos, los microservicios solo son recomendables cuando los problemas que resuelven compensan los problemas asociados que generan, entre ellos una mayor complejidad en la infraestructura y de comunicación entre servicios que utilizan un medio con fallos, la red de comunicaciones entre diferentes computadoras. Esta mayor complejidad, mayor número de servicios y mayor probabilidad de que alguna falle requiere utilizar herramientas para en el sistema tener seguridad, observabilidad, resiliencia y control de tráfico. Estas son funcionalidades que como una capa de red para los servicios proporciona un _service mesh_."
---

{{% post %}}

{{< logotype image1="hashicorp-consul.svg" image2="hashicorp.svg" >}}

Los sistemas basados en microservicios tienen sus ventajas como utilizar servicios independientes que pueden ser desarrollados y desplegados de forma independiente incluso por distintos equipos de desarrolladores al menos mientras solo se cambien sus detalles de implementación y mientras sus interfaces de comunicación no cambien. Otras ventajas son una mejor escalabilidad y mejor tolerancia a errores.

Un monolito es más que suficiente en muchos casos y los microservicios solo son necesarios en sistemas grandes y complejos formados por varios equipos de desarrollo, escalabilidad y tolerancia a fallos. Cuando tiene sentido utilizar un sistema basado en los microservicios se resuelve algunos problemas de los monolitos pero surgen otros propios de los microservicios tan o más complicados que los de los monolitos.

Uno de los mayores problemas de los microservicios es que el sistema es más complejo, formado por un conjunto mayor de piezas individuales que han de colaborar para proporcionar la funcionalidad global, han de tener en cuenta problemas que en las aplicaciones monolíticas no tienen, los microservicios son independientes pero se relacionan entre ellos a través de la red un medio de comunicación que es poco fiable comparado con la llamada a una función dentro de la misma computadora en un monolito.

Para tener bajo control las complicaciones de los microservicios son necesarias herramientas que hagan del sistema más fácil de mantener y desarrollar. Una de las herramientas específicamente desarrollada para los microservicios es [Consul][consul] de [HashiCorp][hashicorp] que proporciona varias funcionalidades como descubrimiento de servicios y un almacén compartido de información. Otra de las funcionalidades que implementa y provee Consul es el concepto de _service mesh_ que proporciona seguridad, observabilidad, resiliencia y control de tráfico.

{{< tableofcontents >}}

## Qué es un _service mesh_

[La definición de Consul de un _service mesh_](https://developer.hashicorp.com/consul/docs/concepts/service-mesh) es una capa de red dedicada que proporciona seguridad en la comunicación de servicio a servicio dentro y a través de la infraestructura, incluyendo el sistemas propios y entornos en la nube. Un _service mesh_ es utilizado habitualmente en arquitecturas basadas en microservicios pero son útiles en cualquier escenario en que hay una comunicación por red compleja.

Los microservicios se comunican entre ellos formado una red distribuida, un _service mesh_ es el nombre que se le da a este tipo de comunicación. En el aspecto de las herramientas de infraestructura para facilitar y dar solución a varios de los problemas de la comunicación por red un _service mesh_ trata todos estos problemas y aporta soluciones.

En gran medida la solución a los problemas de los microservicios en un _service mesh_ es implementada estableciendo un intermediario _proxy_ en la comunicación entre los servicios siendo en este intermediario denominado _sidecar proxy_ el que implementa la solución. Se denomina _sidecar proxy_ ya que al mismo tiempo que se despliega el servicio se despliega el intermediario adicionalmente para la comunicación entre los diferentes servicios que usa y los servicios en vez de comunicarse entre ellos directamente se comunican con el _sidecar proxy_.

El _service mesh_ a través de los _proxy sidecar_ permiten delegar en la infraestructura y no en las aplicaciones varios aspectos que son necesarios resolver en un sistema basado en microservicios y todo ello de forma transparente y sin tener que hacer modificaciones en los microservicios más allá de configuración de puertos.

### Seguridad

La computación en la nube de los proveedores de infraestructura como [Amazon AWS][amazon-aws], [Google Cloud][google-cloud] y [Microsoft Azure][microsoft-azure] no dejan de ser entornos compartidos de computación donde todos aun con las medidas de seguridad y lógicas utilizan los mismos medios de red y computación. Por eso se considera que la computación en la nube utiliza un medio no confiable en el que el descubrimiento de un fallo de seguridad tan importante como [el fallo de seguridad Meltdown y Spectre de los procesadores][blogbitix-293] aún con las medidas de mitigación implementadas es un grave problema.

Para proporcionar mayor seguridad en las comunicaciones de los microservicios en un medio no confiable Consul ofrece una forma de comunicación cifrada, con autenticación mutua entre ambos servicios y la gestión de la autorización en un plano lógico a nivel de servicio con el mecanismo de intenciones y no de más bajo nivel de direcciones IP, puertos y reglas de _firewall_.

La seguridad entre los servicios en Consul es implementada mediante el componente denominado _sidecar proxy_ que hace de intermediario o _proxy_ entre los dos servicios. Consul administra el _sidecar proxy_ y este proporciona las funcionalidades comentadas de cifrado, autenticación mutua y autorización. Toda comunicación se realiza de forma cifrada y con autenticación mutua ningún servicio ajeno al _service mesh_ puede comunicarse con sus servicios, para que un servicio externo al _service mesh_ pueda comunicarse con un servicio del _service mesh_ el tráfico entra por un _ingress gateway_ que es un servicio dedicado a esta función.

Una gran característica es que todas estas funcionalidades son proporcionadas sin que los servicios sean conscientes de que realmente la comunicación la están realizando a través de los _sidecar proxy_.

En [GNU][gnu]/[Linux][linux] la seguridad a nivel de red en la misma máquina en la comunicación entre el servicio y el _sidecar proxy_ se protege con _namespaces_ de red que proporciona Linux.

### Observabilidad

Dado el gran número de elementos de una arquitectura de microservicios es imprescindible tener visibilidad del estado y comportamiento de cada uno de ellos. Se necesitan métricas que permitan conocer el estado normal de los servicios y en caso de obtener unas métricas diferentes saber que está ocurriendo en el sistema en su conjunto y en cada pieza individual.

Varias medidas de observabilidad es posible implementarlas en los propios microservicios como parte de su implementación de código, sin embargo, añadir código a cada uno de los microservicios requiere hacerlo individualmente, los hace más complicados y dependientes del lenguaje de implementación para cada uno de ellos para una funcionalidad de infraestructura que no está relacionada con la lógica de negocio que proporcionan los microservicios.

Por ello en la medida de lo posible es mejor delegar la observabilidad a la infraestructura que además permite aplicar la observabilidad de forma homogénea y con las mismas herramientas para todos los microservicios. Algunas métricas de observabilidad son el número de peticiones que está recibiendo un servicio por unidad de tiempo, la tasa de errores o la latencia de red en diferente percentiles.

Dado que todo el tráfico entre los microservicios fluye a través de los _sidecar_ que hacen de _proxy_ Consul es capaz de proporcionar estas métricas de forma independiente a los microservicios. Consul exporta el formato de [Prometheus][prometheus] las métricas, proporciona una visualización básica de las mismas y con [Grafana][grafana] es posible obtener visualizaciones de los datos más detalladas y complejas.

Para obtener el flujo completo desde que entra en el _service mesh_ y fluye entre las diferentes llamadas entre los microservicios [Jaeger][jaeger] con [Zipkin][zipkin] o [OpenTelemetry][opentelemetry] permite obtener una foto de todos los pasos y saltos que ha tenido una petición entre los diferentes servicios.

### Resiliencia

Dado el número de servicios y el número de instancias de cada uno de ellos y la no completa fiabilidad de un medio de red que puede fallar es necesario que los microservicios sean resilientes ante la aparición de estos fallos.

La resiliencia se puede implementar de forma activa donde Consul comprueba que cada servicio mediante un _health check_ esté funcionando ya sea realizando una petición HTTP o TCP al servicio que si responde correctamente se considera que el servicio tiene un buen estado de funcionamiento.

Los _health check_ no son infalibles y los microservicios para aumentar su resiliencia pueden implementar otras medidas adicionales como reintentos o _retries_ realizando peticiones con la esperanza de que sean problemas temporales y en un siguiente intento funcione. Otra medida son los _timeout_ para poner límites a los tiempos de espera de modo que si un microservicio tarda en responder no ocasiones fallos en otros microservicios y el sistema caiga en cascada. Estas son medidas pasivas que implementan cada microservicio.

Consul implementa medidas de resiliencia tanto activas como pasivas, y en caso de que una instancia de un servicio falle redirigir el tráfico a otras instancias con buen estado de salud, este enrutamiento se realiza mediante el control de tráfico.

### Control de tráfico

Al ser los microservicios independientes estos se despliegan de forma independiente mientras los cambios sean solo de implementación. Al desplegar una nueva versión de un microservicio es muy útil tener la capacidad del enrutamiento de las peticiones entre los microservicios para usar diferentes estrategias de despliegue como _rollout_, _canary_, _blue/green_ y cambiar el enrutamiento.

Consul proporciona tres conceptos para controlar el enrutamiento entre los servicios los _resolvers_ que identifican las instancias de los servicios en grupos, _splitters_ que permite establecer la cantidad de tráfico que es enviada a los grupos de servicios y finalmente los _routers_ que permiten mediante reglas de la petición o de la instancia del servicio a que servicio se le envía el tráfico.

## Ejemplo práctico del _service mesh_ de Consul

En el libro Consul: Up & Running se explica más detalladamente los puntos anteriores junto con un ejemplo con las necesarias configuraciones para Consul de cada uno de ellos utilizando [Kubernetes][kubernetes] y máquinas virtuales.

En el libro se usa simplemente Consul, en este artículo muestro la misma configuración y práctica de los conceptos comentados pero usando [Nomad][nomad] el orquestador de procesos de HashiCorp equivalente a Kubernetes pero más simple de usar aunque no tan nombrado como Kuberntes. Lo ejecuto dentro de una máquina virtual de [VirtualBox][virtualbox] aprovisionada y creada con [Vagrant][vagrant] otro producto de HashiCorp.

El ejemplo consiste en dos procesos uno _frontend_ que muestra la información de una guía de aves y un servicio de _backend_ que devuelve los datos de las aves. Los procesos están empaquetados en sus imágenes de [Docker][docker]. Ambas aplicaciones se comunican mediante una API REST y permiten probar en la práctica todos los conceptos comentados.

### Creación de la máquina virtual

Docker es un software desarrollado para GNU/Linux que en otros sistemas no funciona de forma nativa y requiere adaptaciones y virtualización, para poder ejecutar el ejemplo en cualquier sistema operativo y no instalar nada en la máquina local más allá de VirtualBox y Vagrant se utiliza una máquina virtual que permiten crear estas dos herramientas de forma fácil.

El siguiente es el archivo de configuración para Vagrant de la máquina virtual que aprovisiona una distribución Ubuntu con las herramientas utilizadas en el ejemplo entre ellas Consul, Nomad, [Envoy][envoyproxy] y Docker. También se configura la cantidad de memoria asignada a la máquina virtual y la dirección IP que se le asigna.

Al iniciar la máquina virtual se aprovisiona, con Vagrant simplemente consiste en ejecutar el siguiente comando. Una vez iniciada se puede hacer SSH para iniciar una sesión de terminal dentro de la máquina virtual.

{{< code file="vagrant.sh" language="bash" options="" >}}
{{< code file="Vagrantfile" language="ruby" options="" >}}

{{< image
    gallery="true"
    image1="image:virtualbox.webp" optionsthumb1="650x450" title1="VirtualBox"
    caption="VirtualBox" >}}

### Inicio de Consul y Nomad y microservicios del ejemplo

Una de las ventajas de Consul y Nomad es que son muy simples de usar ya que sus programas son simplemente un único binario. Consul y Nomad se inician con los siguientes comandos en modo desarrollo con sus archivos de configuración.

{{< code file="consul.sh" language="bash" options="" >}}
{{< code file="nomad.sh" language="bash" options="" >}}
{{< code file="consul.d/consul.hcl" language="hcl" options="" >}}
{{< code file="nomad.d/nomad.hcl" language="hcl" options="" >}}

Consul requiere la definición del servicio _ingress gateway_ que permite que entre tráfico al _service mesh_, en esta configuración a través del puerto 8080, el tráfico entrante es redirigido al servicio _frontend_. Esta configuración se proporciona como un [Config Entry](https://developer.hashicorp.com/consul/docs/connect/config-entries) de Consul y se proporciona a Consul con un comando.

{{< code file="consul-ingress-gateway.sh" language="bash" options="" >}}
{{< code file="consul.d/config-entries/ingress-gateway.hcl" language="hcl" options="" >}}

La configuración de los _sidecar proxy_ que crear Consul en este ejemplo es necesario para exportar métricas de Prometheus y enviar la trazabilidad a Jaeger usando Zipkin.

{{< code file="consul.d/config-entries/proxy-defaults.hcl" language="hcl" options="" >}}

Tanto Consul como Nomad ofrecen una interfaz web para consultar el estado de los servicios y realizar tareas de forma más intuitiva que utilizando la línea de comandos. La interfaz de Consul se inicia en el puerto 8500 y la de Nomad en el puerto 4646 la dirección IP es la de la máquina virtual.

{{< image
    gallery="true"
    image1="image:consul.webp" optionsthumb1="300x200" title1="Consul"
    image2="image:nomad.webp" optionsthumb2="300x200" title2="Nomad"
    caption="Consul y Nomad" >}}

### Inicio de Prometheus y Jaeger

Prometheus es una base de datos temporal que permite guardar los datos de métricas y monitorización que generan las aplicaciones, los _sidecar proxy_ generan métricas que Prometheus recolecta y Consul permite mostrar algunos datos básicos de métricas como número de peticiones, tasa de fallos de las peticiones y percentiles de latencia.

Jaeger es una herramienta de trazabilidad que permite correlacionar todas las peticiones que se realizan entre los servicios, de esta forma es posible relacionar la petición del servicio _frontend_ con la petición del servicio _backend_ y mostrar ambas al mismo tiempo como si estuvieran relacionadas, esta relación se establece mediante un _traceId_ que identifica al conjunto de las peticiones individuales.

En el ejemplo Prometheus y Jaeger se inician como contenedores de Docker con los siguientes comandos. También se podrían haber iniciado como servicios de Nomad. La configuración de Prometheus es necesaria para recolectar las métricas de Consul.

{{< code file="prometheus.sh" language="bash" options="" >}}
{{< code file="prometheus.yml" language="yaml" options="" >}}
{{< code file="jaeger.sh" language="bash" options="" >}}

Jaeger ofrece una interfaz web en el puerto 16686 para visualizar la trazabilidad de una petición y buscar una traza en concreto a través de su _traceId_ y otros filtros.

{{< image
    gallery="true"
    image1="image:jaeger.webp" optionsthumb1="650x450" title1="Jaeger"
    caption="Jaeger" >}}

### Inicio de los servicios _frontend_ y _backend_

La aplicación de ejemplo que muestra información de aves está compuesta de dos microservicios el _frontend_ que muestra la información mediante una interfaz web y el servicio de _backend_ que proporciona la información al _frontend_.

En el libro Consul: Up & Running los servicios se inician utilizando únicamente Consul que es en lo que se centra el libro, pero en este ejemplo inicio estos servicios utilizando el orquestador Nomad, Nomad permite definir la misma configuración incluyendo la configuración para los *sidecar proxy* y se integra con Consul.

La definición del job de Nomad que engloba ambos servicios es la siguiente. Con esta definición declarativa Nomad se encarga de iniciarlos según la descripción indicada, del servicio de _backend_ se inician dos instancias una la de la versión _v1_ y otra de la _v2_.

{{< code file="nomad-birds.sh" language="bash" options="" >}}
{{< code file="birds.nomad" language="hcl" options="" >}}
{{< image
    gallery="true"
    image1="image:birds-v1-1.webp" optionsthumb1="650x450" title1="Frontend con datos de la versión v1 del backend"
    caption="Frontend con datos de la versión v1 del backend" >}}

### Demostraciones de las funcionalidades del _service mesh_

A la aplicación se accede a través del servicio de _ingress-gateway_ en el puerto 8080 de la máquina virtual. El servicio de _ingress-gateway_ simplemente redirige el tráfico a la aplicación de _frontend_. Esta es la topología de comunicación para el servicio de _frontend_ proporcionada por Consul, recibe peticiones del _ingress-gateway_ y a su vez realiza peticiones al servicio de _backend_.

{{< image
    gallery="true"
    image1="image:consul-topology-1.webp" optionsthumb1="650x450" title1="Toppología de un servicio y métricas en Consul"
    caption="Toppología de un servicio y métricas en Consul" >}}

Al arrancar Consul en modo desarrollo el comportamiento por defecto es permitir la comunicación entre los servicios, en una configuración en grado de producción por el contrario por defecto no se permite.

Creando una intención es posible denegar el tráfico entre el servicio de _frontend_ al de _backend_. Las intenciones sirven para permitir o denegar el tráfico en un plano de servicio y no de direcciones IP y cortafuegos lo que lo hace mucho más sencillo de administrar, además de que cualquier cambio es posible hacerlo desde la interfaz web y se aplica de forma instantánea.

El tráfico entre los servicios a través de los _sidecar proxy_ realizan la comunicación cifrada y utilizando autenticación mutua.

{{< image
    gallery="true"
    image1="image:consul-intentions.webp" optionsthumb1="650x450" title1="Intenciones en Consul"
    caption="Intenciones en Consul" >}}

Consul exporta métricas en formato Prometheus que el servidor de Prometheus recolecta de forma periódica, esta información puede ser visualizada con una herramienta como Grafana. También el propio Consul permite obtener una métricas básicas del servicio como el número de peticiones que se están realizando, tasa de errores y latencia agrupadas por percentiles. Esta información es muy valiosa ya que permite monitorizar el estado del servicio.

{{< image
    gallery="true"
    image1="image:consul-topology-2.webp" optionsthumb1="650x450" title1="Métricas de Prometheus en Consul"
    caption="Métricas de Prometheus en Consul" >}}

Consul y los _sidecar proxy_ de Envoy ofrecen soporte para trazabilidad con Zipkin junto con cierto soporte de los propios servicios la trazabilidad es enviada a un servidor como Jaeger que permite visualizar y relacionar las llamadas entre los diferentes servicios. Desde que entra por el _ingress-gateway_ hasta que llega al _backend_ pasando por el servicio de _frontend_ asi como el tiempo empleado en cada uno de ellos.

{{< image
    gallery="true"
    image1="image:jaeger.webp" optionsthumb1="650x450" title1="Trazabilidad en Jarger"
    caption="Trazabilidad en Jarger" >}}

Consul conoce y monitoriza el estado de todos los servicios, a los servicios que presenten un mal funcionamiento no se les envía tráfico para evitar errores y tener resiliencia aún con fallos parciales. Consul realiza peticiones de estado de salud de forma activa de forma periódica generalmente cada pocos segundos, para comprobar el estado de cada servicio. En la consola de administración muestra el estado de cada uno de ellos que también es una valiosa información de observabilidad.

{{< image
    gallery="true"
    image1="image:consul.webp" optionsthumb1="650x450" title1="Salud de los servicios en Consul"
    caption="Salud de los servicios en Consul" >}}

Como son los _sidecar proxy_ los que hacen las peticiones de red entre servicios estos tienen capacidad de realizar reintentos y _timeouts_. Con los reintentos se mitigan los errores temporales y con los _timeouts_ se evita que un servicio que esté tardando en responder afecte a otros servicios.

El control de tráfico es utilizado para diferentes funciones una de ellas para el despliegue de una nueva versión de forma controlada utilizando diferentes estrategias de despliegue. El servicio de _backend_ tiene dos versiones la _v1_ que da información de aves y la _v2_ que da información de aves pero solo de la familia de canarios.

Con el control de tráfico es posible distribuir el número de peticiones que va a cada servicio en función de un peso o porcentaje. En una repartición al 50 la mitad de las peticiones van a la _v1_ y la otra mitad a la _v2_, en el _frontend_ se muestra la información del ave y la versión del servicio que la ha proporcionado. Cada vez que se cambia un archivo de configuración y se quiere aplicar hay que escribir la configuración en Consul.

{{< code file="consul-traffic-control.sh" language="bash" options="" >}}
{{< code file="consul.d/config-entries/backend-service-resolver.hcl" language="hcl" options="" >}}
{{< code file="consul.d/config-entries/backend-service-router.hcl" language="hcl" options="" >}}
{{< code file="consul.d/config-entries/backend-service-splitter.hcl" language="hcl" options="" >}}

{{< image
    gallery="true"
    image1="image:birds-v1-2.webp" optionsthumb1="300x200" title1="Frontend con datos de la versión v1 del backend"
    image2="image:birds-v2.webp" optionsthumb2="300x200" title2="Frontend con datos de la versión v2 del backend"
    caption="Frontend con datos de la versión v1 y v2 del backend" >}}

## Libros sobre Consul

Este artículo es prácticamente un resumen del libro [Consul: Up & Running](https://amzn.to/3F0SmjY) que muestra y explica todo esto de forma más detallada y extensa además de mostrarlo usando Kubernetes. Está bien explicado y su ejemplo es muy fácil de seguir y probar además de muy didáctico para experimentar con los conceptos y funcionalidades comentadas de Consul. Otros libros sobre Consul y _service mesh_ son [Simplifying Service Management with Consul](https://amzn.to/3seULzU) y [Mastering Service Mesh](https://amzn.to/3DiIiS6).

{{< amazon
    linkids="https://amzn.to/3SAIGD8,https://amzn.to/3SFxSUE,https://amzn.to/499LH2U"
    asins="1098106148,1800202628,1789615798"
    titles="Consul: Up and Running,Simplifying Service Management with Consul,Mastering Service Mesh" >}}

{{% sourcecode git="blog-ejemplos/tree/master/ConsulServiceMesh" %}}

{{% /post %}}