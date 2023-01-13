---
pid: 518
type: "post"
title: "Trazabilidad en servicios distribuidos con Sleuth y Zipkin"
url: "/2020/10/trazabilidad-en-servicios-distribuidos-con-sleuth-y-zipkin/"
date: 2020-10-02T17:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:zipkin.svg"
tags: ["java", "planeta-codigo"]
series: ["spring-cloud"]
summary: "En un sistema complejo como una arquitectura de microservicios medir los tiempos de respuesta de cada uno de ellos ayuda a identificar si alguno se está comportando de forma anómala. Sleuth permite asignar un identificador global que es compartido por todos los microservicios invocados en la misma transacción, permite exportar los tiempos de respuesta a Zipkin que ofrece un panel web en el que identificar que llamadas se han hecho entre microservicios y cuales han sido sus tiempos de respuesta."
---

{{% post %}}

{{< logotype image1="spring.svg" image2="zipkin.svg" >}}

El un sistema basado en microservicios unos servicios depende de otros y se comunican haciendo llamadas entre ellos. Las llamadas entre los servicios son un punto de fallo y problemas que conviene monitorizar para que el conjunto de la aplicación funcione correctamente. Con un número importante de servicios la monitorización y la trazabilidad es una de las [funcionalidades de las aplicaciones basadas en microservicios][blogbitix-516], muchas de estas funcionalidades son proporcionadas de forma específica por una herramienta.

En el caso de [trazabilidad en microservicios con Sleuth][blogbitix-396] proporciona un identificador global que permite correlacionar todas las trazas desencadenadas por una petición en los diferentes servicios, con el identificador global de una traza se puede obtener el resto de trazas del mismo servicio. Con el identificador global es posible acceder al registro de trazas y obtener todas las correlacionadas con el identificador global como sería el caso de [guardar las trazas en Elasticsearch, Logstash y Kibana][blogbitix-517]. Pero las trazas emitidas por los microservicios no ofrecen métricas de cuánto tiempo ha tardado cada uno de los microservicios en su ejecución en devolver la respuesta y sin nada adicional no permite correlacionar las trazas de un servicio con las trazas del servicio llamado.

Otro aspecto de las llamadas entre los microservicios es medir los tiempos de respuesta y latencia entre los servicios, si un servicio tiene un bajo rendimiento y tarda en responder es posible que produzca errores en los servicios que lo usan, provocando otros errores en el sistema. Para que el mal funcionamiento de un servicio provoque errores en otros los servicios han de estar preparados y admitir [tolerancia a fallos con la librería Resilience4j][blogbitix-425]. [Micrometer][micrometer], [Prometheus][prometheus] y [Grafana][grafana] son la forma de [obtener métricas y monitorizar una aplicación Java con Spring Boot][blogbitix-366] que incluye los tiempos de respuesta de los microservicios.

[Zipkin][zipkin] es una herramienta que recolecta las transacciones creadas por Sleuth en la ejecución de los microservicios e información de los tiempos de respuesta de las invocaciones que han intervenido en una transación. Ofrece las dos funcionalidades la recolección de datos y la obtención de los mismos. Tanto la recolección como el almacenamiento ofrecen diferentes herramientas para implementarlo, la recolección puede ser mediante peticiones HTTP, [RabbitMQ][rabbitmq] o [Kafka][apache-kafka] y el almacenamiento en memoria, [MySQL][mysql], [Cassandra][cassandra] o [Elasticsearch][elasticsearch].

En el ejemplo hay un microservicio que hace de servidor y otro de cliente que se comunican mediante peticiones HTTP con REST. Tanto el microservicio cliente como el microservicio servidor usan [Sleuth][spring-cloud-sleuth] que genera identificativos globales que permiten correlacionar todas las peticiones entre los diferentes servicios.

Sleuth proporcionar trazabilidad con identificativos globales que podrían ser recuperados a través del sistema de _logging_ centralizado como Elasticsearch, [Logstash][logstash] y [Kibana][Kibana]. Usando una librería de _logging_ como [Log4j][log4j] se puede [configurar Log4j para que emita en las trazas los identificadores globales][blogbitix-336].

### Ejemplo de microservicio con Spring Boot, Sleuth y Zipkin

En el ejemplo de trazabilidad de microservicios con Sleuth consistía en un servicio implementado con Spring Boot, también tiene un cliente que realiza de forma periódica peticiones al servicio. Ambos tienen Sleuth integrado y en las trazas de la consola en ambos aparece el identificador global _traceId_ de la traza. En este ejemplo se configura Sleuth para que envíe a Zipkin las transacciones de estos dos pequeños microservicios.

Para que Sleuth envíe al servidor las métricas de las llamadas entre los microservicios basta con añadir al proyecto del cliente y microservicio la dependencia _spring-cloud-starter-zipkin_ y configurar el medio de transporte que se utiliza para enviar las métricas al servidor, en este caso mediante peticiones HTTP y la dirección de Zipkin. Esto se configura con las propiedades de Spring Boot _spring.zipkin.enabled_, _spring.zipkin.baseUrl_ y _spring.zipkin.sender.type_.

{{< code file="build.gradle" language="groovy" options="" >}}
{{< code file="client.yml" language="yaml" options="" >}}

Los siguientes comandos inician el registro y descubrimiento de servicios, el orquestador de contenedores, el servidor de configuración, el servicio, un _proxy_ del servicio que balancea la carga si hubiera diferentes instancias del servicio y admite tolerancia a fallos si alguna de las instancias falla y finalmente el cliente del servicio. En el ejemplo se utiliza [Nomad][nomad] y [Docker][docker] junto a [Consul][consul] y [Traefik][traefik]. El ejemplo requiere iniciar Consul y Nomad y tener instalado, configurado y en ejecución Docker.

* [Microservicios con Spring Cloud, Consul, Nomad y Traefik][blogbitix-436]
* [Registro y descubrimiento de servicios con Spring Cloud y Consul][blogbitix-206]
* [Guía sobre Docker][blogbitix-serie-docker]

{{< code file="run-1.sh" language="bash" options="" >}}

Nomad se encarga de orquestar los contenedores de Docker para crear las instancias de los microservicios. Las definiciones de los microservicios se envian a Nomad para que inicie las instancias.

{{< code file="run-2.sh" language="bash" options="" >}}

Sleuth exporta los datos de las transacciones a Zipkin donde se observa los tiempos de respuesta de los microservicios para una petición determinada dado su identificador global. Pero también permite observar que microservicios han sido llamados lo que ayuda a conocer cual ha sido el comportamiento del sistema, dependiendo de la lógica de negocio que implementen no tienen por que ser siempre los mismos.

Zipkin ofrece una aplicación web con la que consultar las llamadas desencadenadas por una petición y los tiempos de respuesta de los servicios involucrados, la aplicación web está en la dirección _http://localhost:9411/zipkin/_ o a través de Traefik con _http://localhost:8093/zipkin/_. Utilizando uno de los identificadores globales de petición e introduciéndolo en el cuadro de búsqueda de Zipkin se observan los tiempos de respuesta de cada uno de los servicios.

{{< code file="client.out" language="plain" options="" >}}

Tanto el microservicio cliente como el microservicio servidor tienen Sleuth integrado y envían de forma asíncrona cuando terminan las peticiones HTTP la información de trazabilidad a Zipkin. Con el _traceId_ de una transacción se observa en Zipkin la cadena de llamadas entre los microservicios y sus tiempos de respuesta. En el ejemplo solo hay dos pero podrían en un caso real quizá sean tres, cuatro o más ya sea porque cada servicio utiliza otro o porque un mismo servicio utiliza varios, ver esta información de forma gráfica es mucho más fácil de analizar que solo con las trazas correlacionadas en ELK.

{{< image
    gallery="true"
    image1="image:nomad.webp" optionsthumb1="300x200" title1="Interfaz web de Nomad"
    image2="image:zipkin.webp" optionsthumb2="300x200" title2="Interfaz web de Zipkin"
    caption="Interfaz web de Nomad y Zipkin" >}}

Este es el código del cliente que hace la petición al servidor y el código del servidor. Spring ya proporciona integración con Sleuth en sus utilidades como _RestTemplate_. Si se utiliza el cliente HTTP de Java añadido junto con otras [novedades de Java 11][blogbitix-350] hay que añadir el soporte para que en la petición se añadan las cabeceras cuando se haga la petición. La forma que tiene Sleuth de compartir los identificativos de las transacciones entre el cliente y el servidor es a través de las cabeceras en las peticiones HTTP.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="ProxyService.java" language="java" options="" >}}
{{< code file="DefaultController.java" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringCloudConsulNomadTraefik" command="./run.sh" %}}

{{< reference >}}
* [Trazabilidad Distribuida con Spring Cloud: Sleuth y Zipkin](https://www.paradigmadigital.com/dev/trazabilidad-distribuida-spring-cloud-sleuth-zipkin/)
* [Zipkin Dependencies](https://github.com/openzipkin/zipkin-dependencies)
* [Zipkin Examples](https://github.com/openzipkin?utf8=%E2%9C%93&q=example)
* [Sleuth Common application properties](https://docs.spring.io/spring-cloud-sleuth/docs/2.2.5.RELEASE/reference/html/appendix.html)
{{< /reference >}}

{{% /post %}}
