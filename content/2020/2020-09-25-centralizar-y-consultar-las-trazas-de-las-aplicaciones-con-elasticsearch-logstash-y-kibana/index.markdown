---
pid: 517
type: "post"
title: "Centralizar y consultar las trazas de las aplicaciones con Elasticsearch, Logstash y Kibana"
url: "/2020/09/centralizar-y-consultar-las-trazas-de-las-aplicaciones-con-elasticsearch-logstash-y-kibana/"
date: 2020-09-25T16:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:elk-beats-platform.png"
tags: ["planeta-codigo", "programacion"]
summary: "Las aplicaciones monolíticas solo generan un archivo de trazas, es fácil de monitorizar, basta con conectarse por SSH a la máquina de su entorno de ejecución y utilizar los comandos _grep_ o _tail_ o descargarlo para examinarlo con otra herramienta de forma local. Pero aún siendo una aplicación monolítica es raro que una organización tenga solo una aplicación sino varias diferentes y las aplicaciones complejas se dividen en varias aún siendo parte de la misma aplicación. Esto hace que haya múltiples aplicaciones en cuyo caso acceder por SSH a una máquina diferente en cada caso no es cómodo. En el caso de múltiples aplicaciones o aplicaciones basadas en microservicios se opta por centralizar las trazas provenientes de múltiples fuentes en una única herramienta, una de las herramientas es la combinación de Elasticsearch, Logstash y Kibana que forma la pila ELK."
---

{{% post %}}

{{< logotype image1="elastic-stack.svg" >}}

Las trazas de una aplicación permiten conocer en tiempo real el estado de la aplicación, las trazas contienen cualquier información que haya considerado el desarrollador de interés para la monitorización cuando el código se ejecuta, la información pueden ser los valores de ciertos datos, cualquier mensaje que indique por qué líneas de código se están ejecutando en el caso de sentencias condicionales, de bucle o que varían el flujo del programa, si es una traza de error, de información, de advertencia o su marca de tiempo.

Las trazas se suelen almacenar en un registro como un archivo que además permita consultarlas en un futuro en caso necesario. A veces un problema se descubre pasadas varias horas o días, en este caso acceder al registro de trazas permite obtener información para averiguar que ha sucedido y si es el caso descubrir la causa del error en el código fuente.

En una aplicación monolíticas guardar las trazas en un archivo puede ser suficiente, en las aplicaciones distribuidas y compuestas por varios microservicios cada uno emitiendo su propio registro de trazas se hace necesario recopilarlos y centralizarlos en una herramienta para su almacenamiento y consulta sencilla.

La monitorización y las trazas son dos de las [necesidades de funcionalidad de las aplicaciones basadas en microservicios][blogbitix-516] aunque es también aplicable a las aplicaciones monolíticas. El complemento a las trazas son las [métricas y monitorización con Prometheus y Grafana][blogbitix-366].

Para cubrir la necesidad de monitorización y trazas de las aplicaciones hay múltiples herramientas, entre las que tienen una licencia de software libre o de código abierto están la combinación de [Elasticsearch][elasticsearch], [Logstash][logstash] y [Kibana][kibana] también conocidas por las siglas _ELK_.

### ElasticSearch, Logstash, Filebeat y Kibana

ElasticSearch proporciona la indexación y el almacenamiento, Logstash que permite la recolección, tratamiento y envío a Elasticsearch y Kibana permite su consulta y visualización con una interfaz web.

Cada aplicación que emite trazas necesita tener un recolector de trazas asociado que permite enviarlas a Elasticsearch para su indexación y almacenamiento. Logstash es una herramienta pesada como para incluirla en cada máquina donde se instancian los servicios, por ello la propia empresa Elasticsearch ha desarrollado herramientas más ligeras, [Beats][elastic-beats], compuestas de varias herramientas como por ejemplo para archivos de log, métricas, de red o de actividad entre algunos otros.

En vez de usar Logstash se puede usar la herramienta más ligera [Filebeat][filebeat] para indexar la salida estándar de las aplicaciones directamente en Elasticsearch o para enviar a una instancia de Logstash que realice el procesado y el indexado en Elasticsearch.

{{< image
    gallery="true"
    image1="image:elk-beats-platform.png" optionsthumb1="300x200" title1="Arquitectura de la plataforma ELK"
    caption="Arquitectura de la plataforma ELK" >}}

ELK también cubre algunas funcionalidades de monitorización y visualización de datos de métricas, que se solapa en algunos aspectos con la funcionalidad proporcionada por [Prometheus][prometheus] y [Grafana][grafana].

ELK solo son las herramientas que se encargan de almacenar y permitir el acceso a las trazas, son las aplicaciones las que se encargan de emitirlas con el formato, información y nivel de detalle que desean. En Java una de las librerías que se suele emplear de soporte para emitir trazas es [Log4j 2][log4j].

* [La librería Log4j para emitir trazas en aplicaciones Java][blogbitix-334]

Por otro lado, al centralizar las trazas en una herramienta hace que todas estén mezcladas de modo que encontrar las correlacionadas que se ha emitido por una aplicación en una petición o todas las trazas que ha desencadenado en diferentes servicios es necesario utilizar identificadores globales y [Sleuth][spring-cloud-sleuth]. Teniendo el identificador global único basta con hacer una búsqueda para encontrar todas las relacionadas que permiten analizar en detalle el comportamiento de la aplicación para una petición.

* [Identificar todas las trazas de una petición en una aplicación web Java con Log4j][blogbitix-336]
* [Trazabilidad en microservicios con Spring Cloud Sleuth][blogbitix-396]

Otra parte importante en las trazas es no incluir u ofuscar los datos sensibles como datos personales o contraseñas.

* [Ofuscar datos sensibles en las trazas con Log4j][blogbitix-383]

### Ejemplo monitorización de trazas con ELK y Docker

ELK son en realidad tres herramientas individuales diferentes que en conjunto proporcionan la funcionalidad que permite monitorizar las trazas de los servicios de forma centralizada y almacenarlas para su consulta en un futuro. Pueden ser cuatro herramientas si se usa Logstash como intermediario entre Filebeat y Elasticsearch.

Utilizando [Docker][docker] y [Docker Compose][docker-compose] en un archivo se puede definir la colección de contenedores para arrancarlos como una unidad. En este caso se inicia un contenedor para Elasticsearch, para Kibana y otro para Filebeats que monitoriza el servicio de Docker con todos los contenedores que se inicien.

{{< code file="docker-compose-elk.yml" language="yaml" options="" >}}
{{< code file="docker-compose-elk.sh" language="bash" options="" >}}

Filebeat permite indexar las trazas de un archivo de _log_ que genera la aplicación o en el caso de un contenedor de Docker las que emite en la salida estándar o en la salida de error. Filebeat monitoriza los contenedores de Docker y según las etiquetas que se le asignan al contenedor personaliza la indexación de las trazas. Filebeat ofrece módulos para indexar las trazas de algunas herramientas como el servidor web Nginx o Apache, entiende su formato, procesa la traza emitida, la enriquece con sus propios datos y las envía a Elascticsearch o si fuese el caso a Logstash.

Para configurar Elascticsearch y Filebeat es necesario realizar una configuración con un contenedor y comando de Filebeat que crea el índice en Elasticsearch y configura algunos paneles de visualización.

{{< code file="filebeat-setup.sh" language="bash" options="" >}}

El siguiente comando arranca un contenedor con una instancia de [Nginx][nginx]. El archivo de Docker Compose para iniciar el contenedor de Nginx incluye varias etiquetas para activar la monitorización de trazas de Filebeat. Filebeat monitoriza los contenedores que se inician y las trazas que emiten los contenedores que tiene asociadas las etiquetas que activan Filebeat. 

{{< code file="docker-compose-nginx.yml" language="yaml" options="" >}}
{{< code file="docker-compose-nginx.sh" language="bash" options="" >}}

Al acceder a alguna página del servidor web emite una traza como salida. En este caso se solicita la página por defecto y el navegador intenta encontrar el _favicon_ de la web, la página se devuelve con un código de estado 200 y el _favicon_ como no está configurado se devuelve un código de estado 404 de recurso no encontrado.

{{< code file="nginx.out" language="plain" options="" >}}

Kibana es la herramienta como cliente web que permite consultar las trazas almacenadas en Elasticsearch, permite crear filtros para precisar las trazas a buscar y limitar las consultas a ciertos periodos de tiempo, muestra las ocurrencias encontradas y el número de ellas en un cierto periodo de tiempo. Filebeat además de las trazas que generan los contenedores indexa algunas propiedades adicionales como el nombre del contenedor, la imagen del contenedor, datos del agente que realiza la solicitud, marca de tiempo de generación junto a más datos que permite filtrar para encontrar las trazas deseadas entre todas las indexadas de todos los contenedores. También permite seleccionar las trazas en un rango de tiempo y los campos de datos a mostrar como resultado. Kibana tiene su propia sintaxis para realizar las consultas y permite guardarlas para futuros usos y compartirlas, además de poder construir avanzados paneles de información.

En el ejemplo Kibana se accede a través de la dirección _http:\/\/localhost:5601_ con un navegador web.

{{< image
    gallery="true"
    image1="image:kibana.png" optionsthumb1="300x200" title1="Búsqueda en Kibana de trazas del contenedor Docker de Nginx"
    image2="image:nginx.png" optionsthumb2="300x200" title2="Página de inicio por defecto de Nginx"
    caption="Búsqueda en Kibana de trazas del contenedor Docker de Nginx al solicitar la página por defecto" >}}

{{% sourcecode git="blog-ejemplos/tree/master/ELK" %}}

{{% /post %}}
