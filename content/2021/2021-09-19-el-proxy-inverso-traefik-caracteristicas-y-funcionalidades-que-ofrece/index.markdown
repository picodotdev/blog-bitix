---
pid: 598
type: "post"
title: "El proxy inverso Traefik, características y funcionalidades que ofrece"
url: "/2021/09/el-proxy-inverso-traefik-caracteristicas-y-funcionalidades-que-ofrece/"
date: 2021-09-19T13:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:traefik-architecture.png"
imagePost: "image:traefik-architecture.png"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "Un _proxy_ inverso oculta la complejidad de los servicios para los que hace de intermediario. Al situarse como intermediario el _proxy_ inverso es capaz de proporcionar funcionalidades adicionales como balanceo de carga, limitar el número de peticiones por unidad de tiempo, duplicar peticiones o realizar la autenticación entre otras funciones. Traefik es un _proxy_ inverso diseñado para los entornos _cloud_ dinámicos soportando autoconfigurarción a partir de varios proveedores de registro y descubrimiento de servicios como Consul o Docker, también se integra con herramientas para la observabilidad como Prometheus para métricas y Zipkin y Elastic para trazabilidad y registro de trazas."
---

{{% post %}}

{{< logotype image1="traefik.svg" >}}

La tendencia de desarrollo es utilizar microservicios, contenedores y computación en la nube. Una característica de este tipo de aplicaciones es que se componen de múltiples elementos muy dinámicos que se crean, eliminan o cambia el número de instancias. La configuración de las herramientas nativas para la nube ha de ser dinámica en vez de estática.

Una herramienta específica para hacer de _proxy_ inverso adaptada a la nube es Traefik.

{{< tableofcontents >}}

### El _proxy_ inverso Traefik

[Traefik][traefik] es un _proxy_ inverso y balanceador de carga adaptado a la computación en la nube mediante microservicios. Traefik se integra con los principales componentes de infraestructura configurándose a sí mismo automáticamente y de forma dinámica. Traefik es simple de operar pero capaz de manejar sistemas complejos y grandes en entornos diversos y diferentes capas de la pila de red como HTTP, TCP o UDP. Proporciona funcionalidades de intermediario que aumenta sus capacidades para realizar balanceo de carga o servir como _gateway_ API.

Como _proxy_ inverso intercepta las peticiones entrantes y las redirige a los servicios adecuados. A diferencia de _proxys_ inversos configurados de forma estática, Traefik usa descubrimiento de servicios para configurarse a sí mismo según los servicios en ejecución. Puede hacer de _proxy_ en diferentes capas de la pila de red proporcionando funcionalidades como balanceo de carga, limitación de peticiones, _circuit breaker_, duplicación o _mirroring_, autenticación y más. También soporta terminación de SSL y puede usar un proveedor ACME como [Let’s Encrypt][letsencrypt] para la generación automática de certificados.

{{< image
    gallery="true"
    image1="image:traefik.png" optionsthumb1="300x250" title1="El proxy inverso Traefik"
    image2="image:traefik-architecture.png" optionsthumb2="300x250" title2="Arquitectura del proxy inverso Traefik"
    caption="El proxy inverso Traefik y arquitectura" >}}

El ecosistema de Traefik permite integrarse con otras herramientas destacadas soportando de forma nativa observabilidad con trazabilidad distribuida y con varios proveedores de métricas.

* Proveedores y orquestadores: [Consul][consul], [Docker][docker], [etcd][etcd], [Kubernetes][kubernetes], [Marathon][marathon], [Rancher][rancher], [Redis][redis].
* Trazabilidad: [Jaeger][jaeger], [Open Tracing][opentracing], [Zipkin][zipkin], [Instana][instana].
* Métricas: [Datadog][datadog], [Prometheus][prometheus], [InfluxData][influxdata].

Traefik posee dos versiones una _open source_ gratuita con varias de las funcionalidades básicas, para los casos de uso empresariales proporciona funcionalidades adicionales como autenticación [OAuth2][oauth] o integración con [Vault][vault], en la [tabla que compara ambas versiones](https://traefik.io/pricing/) se especifica cuales están incluídas en cada una.

Al iniciar Traefik se integra un panel de información o _dashboard_ en el que observar las rutas, servicios y _middelwares_ configurados, dado el entorno dinámico de la computación en la nube y microservicios permite ver el estado del sistema.

{{< image
    gallery="true"
    image1="image:traefik-dashboard.png" optionsthumb1="650x450" title1="El panel de información o dashboard integrado de Traefik" >}}
{{< image
    gallery="true"
    image1="image:traefik-dashboard-routes.png" optionsthumb1="200x150" title1="El panel de información o dashboard integrado de Traefik"
    image2="image:traefik-dashboard-services.png" optionsthumb2="200x150" title2="El panel de información o dashboard integrado de Traefik"
    image3="image:traefik-dashboard-middlewares.png" optionsthumb3="200x150" title3="El panel de información o dashboard integrado de Traefik"
    caption="El panel de información o dashboard integrado de Traefik" >}}

### Casos de uso y características

Los principales casos de uso deTraefik son:

* Balanceador de carga: permite distribuir la carga de peticiones con un enrutamiento flexible en la capa 4 (TCP y UDP) y 7 (HTTP, aplicación) junto con un conjunto amplio de _midleware_ que permite escalado dinámico, despliegues _blue-green_ y _canary_ sin caídas, duplicación de peticiones y más.
* API _gateway_: usar Traefik como _proxy_ inverso delante de los servicios permite delegar a Traefik aspectos funcionales transversales incluyendo autenticación, limitación de peticiones y terminación de SSL. Estas capacidades son expandibles mediante complementos o _plugins_.
* _Kubernetes Ingress_: Traefik se puede usar como controlador _Kubernetes Ingress_ para añadir la flexibilidad y facilidad de uso a los despliegues de Kubernetes asi como al resto de su infraestructura de red.
* Gestión de certificados: Traefik soporta de forma nativa gestión de certificados de proveedores ACME como Let’s Encrypt así como certificados actualizables de forma dinámica definidos por el usuario.

Sus características son:

* [Enrutado y balanceo de carga](https://docs.traefik.io/routing/overview/): capa de enrutado flexible en la capa 4 y 7, soporta los protocolos HTTP, HTTP/2, TCP, UDP, Websockets, gRPC, despliegues _blue-green_ y _canary_, fijación de sesión o _session stickness_ y comprobaciones de salud.
* [Seguridad](https://docs.traefik.io/https/overview/): automatización de HTTP, soporte para Let’s Encrypt, certificados personalizados y autenticación.
* [Configuración dinámica](https://docs.traefik.io/providers/overview/): a través de descubrimiento de servicios (Kubernetes, Docker Swarm, Red Hat OpenShift, Rancher, Amazon ECS, _key-value stores_) y funcionales de intermediario o _middelware_ (_circuit breakers_, reintentos, _buffering_, _compresión de la respuesta_, cabeceras o limitación de peticiones).
* [Observabilidad](https://docs.traefik.io/observability/metrics/overview/): posee un panel informativo de forma nativa, trazabilidad distribuida (Jaeger, Open Tracing, Zipkin) y métricas en tiempo real (Datadog, Grafana, InfluxDB, Prometheus, StatsD).

Estas [funcionalidades son extensibles mediante _plugins_](https://traefik.io/blog/using-private-plugins-in-traefik-proxy-2-5/).

### Conceptos

Como _proxy_ inverso la funcionalidad de Traefik es redirigir las peticiones entrantes a los servicios de _backend_ para los que hace de _proxy_. Traefik emplea los siguientes conceptos fundamentales para su configuración:

* [Proveedores](https://doc.traefik.io/traefik/providers/overview/): permiten descubrir los servicios disponibles proporcionando su ubicación y salud y al mismo tiempo realizar la configuración de Traefik de forma dinámica.
* [_Entrypoints_](https://doc.traefik.io/traefik/routing/entrypoints/): son los puertos por los que entra el tráfico de red  y el protocolo usado HTTP, TCP o UDP.
* [Enrutadores](https://doc.traefik.io/traefik/routing/routers/): analizan las peticiones entrantes en base a unas reglas que utilizan el nombre de dominio, ruta o cabecera, definen que las funcionalidades de _middelware_ que se aplican y a que servicios se redirigen.
* [Servicios](https://doc.traefik.io/traefik/routing/services/): son los destinos de las peticiones entrantes pudiendo aplicar funcionalidad de balanceo de carga entre las diferentes instancias de los servicios de _backend_ para los que se hace de _proxy_.
* [_Middlewares_](https://doc.traefik.io/traefik/middlewares/overview/): pueden actualizar la petición como añadir cabeceras HTTP, actualizar la ruta o realizar acciones de intermediario como autenticación o limitación de peticiones.

### Ejemplos de funcionalidades

En este ejemplo se utiliza Traefik como _proxy_ inverso para dos instancias de servidor web [Nginx][nginx]. La configuración de los _entry points_ de Traefik es estática que en el ejemplo se define un un archivo de configuración estático.

La configuración de los enrutadores, servicios y _middlewares_ es dinámica pudiendo actualizarse sin necesidad de reiniciar Traefik. La configuración dinámica la obtiene de los proveedores como Consul o Docker donde los servicios al iniciarse incluyen metainformación que Traefik obtiene se autoconfigura como se muestra en el artículo [Microservicios con Spring Cloud, Consul, Nomad y Traefik][blogbitix-436]. En el caso de este ejemplo por sencillez la configuración dinámica se proporciona a Traefik en un archivo de texto, aún siendo manual escribir esta configuración Traefik la puede recargar y aplicar sin necesidad de reiniciar el servidor.

La configuración de Traefik del ejemplo se divide en tres secciones que son: enrutadores, _middlewares_ y servicios. En la configuración se definen varios enrutadores que aplican reglas en función del _host_ de la petición, los enrutadores definen que _middlewares_ se aplican y a que servicios se redirigen las peticiones coincidentes.

Esta es la configuración estática de Traefik.

{{< code file="traefik.yml" language="yaml" options="" >}}

El archivo de [Docker Compose][docker-compose] que inicia Traefik y los servidores de Nginx de _backend_.

{{< code file="docker-compose.yml" language="yaml" options="" >}}

Y los archivos HTML de los servidores web.

{{< code file="index-1.html" language="html" options="" >}}
{{< code file="index-2.html" language="html" options="" >}}

#### Balanceador de carga

Un balanceador de carga permite distribuir la carga entre las diferentes instancias del servicio de _backend_. Por defecto, se aplican una estrategia _round-robin_ para distribuir la carga de forma equitativa entre las diferentes instancias.

Con esta configuración al realizar peticiones se distribuyen entre la instancia _nginx-1_ y _nginx-2_ de forma alternativa. La primera petición a la instancia _nginx-1_, la segunda a la instancia _nginx-2_ y la tercera de nuevo a la instancia _nginx-1_.

{{< code file="traefik-dynamic-load-balancer.yml" language="yaml" options="" >}}
{{< code file="curl-load-balancer.sh" language="bash" options="" >}}

#### Balanceador de carga con peso

A veces interesa no distribuir la carga de forma uniforme entre las diferentes instancias de los servicios de _backend_, sino realizar un balanceo de carga con peso.

En este caso el balanceo de carga se configura para que la instancia de _nginx-1_ tenga un peso de 3 y la instancia de _nginx-2_ tenga un peso de 1, según estos pesos la instancia _nginx-1_ de cada 4 peticiones recibe 3 mientras que la instancia _nginx-2_ de cada 4 peticiones recibe 1.

{{< code file="traefik-dynamic-load-balancer-weighted.yml" language="yaml" options="" >}}
{{< code file="curl-load-balancer-weighted.sh" language="bash" options="" >}}

#### Limitar número de peticiones

Para evitar que un servicio reciba más peticiones de las que es capaz de procesar según su nivel de servicio, con el objetivo de evitar que se sature y falle o  evitar denegación de servicio por una carga excesiva es posible establecer un límite en el número de peticiones que se envían a los servicios de _backend_. El límite se puede establecer de forma global para todas las peticiones o agruparse según la dirección IP origen o el valor de una cabecera.

En esta configuración se establece que el servicio de _backend_ no reciba más de una petición por segundo, en caso de que se realicen más peticiones por unidad de tiempo que este límite Traefik devuelve un error [429 Too Many Requests](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/429).

{{< code file="traefik-dynamic-rate-limit.yml" language="yaml" options="" >}}
{{< code file="curl-rate-limit.sh" language="bash" options="" >}}

#### Duplicar peticiones o _mirroring_

Este _middleware_ permite duplicar la petición que se envía a un servicio a otros servicios descartando el resultado devuelto por esos otros servicios.

Esto puede ser útil en el caso de aplicar el patrón de estrangulación ya que permite probar las peticiones sin riesgos. El servicio heredado continúa recibiendo y procesando las peticiones a la vez que el nuevo servicio recibe las mismas peticiones, una vez que el nuevo servicio se comporta de la forma esperada las peticiones del servicio heredado se redirigen al nuevo servicio completando la estrangulación.

{{< code file="traefik-dynamic-mirroring.yml" language="yaml" options="" >}}

Al hacer la petición el resultado que se devuelve es el _nginx-1_.

{{< code file="curl-mirroring.sh" language="bash" options="" >}}

Traefik al mismo tiempo que realiza la petición a _nginx-1_ se realiza a _nginx-2_ como se observa en las trazas de los servidores Nginx.

{{< code file="curl-mirroring-nginx.out" language="plaintext" options="" >}}

### Otras funcionalidades

Hay otros _middlewares_ que se pueden aplicar como el de _retry_ para reintentar las peticiones en caso de fallo, aplicar el patrón _circuit breaker_ para aplicar resiliencia evitando enviar peticiones a un servicio que está fallando, autenticación, reescribir el _path_ de las peticiones o quitar un prefijo del _path_ y añadir o eliminar cabeceras.

Al aplicar [el patrón de estrangulación para reemplazar aplicaciones heredadas][blogbitix-592] la solución es añadir un intermediario como Traefik que permita redirigir las peticiones a la aplicación heredada o a su reemplazo cuando esté esté implementado. Este intermediario permite ir reemplazando de forma incremental la aplicación heredada.

Traefik también permite delegar la autenticación en [Keycloak][keycloak] u otros servicios con los _middlewares_ [Forward Auth](https://doc.traefik.io/traefik/middlewares/forwardauth/), [Digest Auth](https://doc.traefik.io/traefik/middlewares/digestauth/) y [Basic Auth](https://doc.traefik.io/traefik/middlewares/basicauth/).

{{% sourcecode git="blog-ejemplos/tree/master/Traefik" command="docker-compose up" %}}

{{% /post %}}
