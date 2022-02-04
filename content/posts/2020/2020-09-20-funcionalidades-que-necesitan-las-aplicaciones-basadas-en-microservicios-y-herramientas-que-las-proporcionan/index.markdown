---
pid: 516
type: "post"
title: "Funcionalidades que necesitan las aplicaciones basadas en microservicios y herramientas que las proporcionan"
url: "/2020/09/funcionalidades-que-necesitan-las-aplicaciones-basadas-en-microservicios-y-herramientas-que-las-proporcionan/"
date: 2020-09-20T10:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:cncf-landscape.png"
tags: ["planeta-codigo", "programacion"]
summary: "Los microservicios aún algunas ventajas necesitan una infraestructura mucho más compleja y necesitan de varias funcionalidades que en una aplicación monolítica no son necesarias. Para desarrollar y mantener con éxito una aplicación nativa para la nube basada en microservicios hay varias herramientas individuales específicas que resuelven cada una un problema específico de los microservicios. En este artículo se identifica cuales son las áreas funcionales que necesitan los microservicios, algunas herramientas hay para cada una de esas herramientas y algunos criterios para elegir una u otra herramienta."
---

{{% post %}}

En el caso de una aplicación compleja y grande llegará un punto en el que una aplicación monolítica presenta problemas como tamaño de la aplicación, un error afecta a toda la aplicación, es más difícil de escalar las partes que lo requieran o dificulta el trabajar a un número grande de desarrolladores sobre el mismo código. Ante estos problemas una posibilidad es dividir la aplicación monolítica en múltiples microservicios independientes y distribuidos, cada microservicio proporciona una funcionalidad y entre todos colaborar para proporcionar la funcionalidad completa. Los microservicios resuelven algunos problemas de las aplicaciones monolíticas pero presentan otros problemas.

Para cada una de las áreas funcionales que necesitan los microservicios hay múltiples herramientas que ayudan a resolver cada área funcional. Por ejemplo, en una aplicación basada en microservicios donde hay múltiples aplicaciones en varias instancias que varían en número y se comunica una parte importante para que los microservicios no sean un problema es la monitorización para permitir conocer el estado del sistema que permita identificar que está fallando rápidamente o incluso detectarlo antes de que ocurra. En una aplicación monolítica no son tan necesarias algunas de estas funcionalidades pero otras son igualmente necesarias o deseables como una base de datos, integración continua, entorno de ejecución también con contenedores, automatización y configuración, claves y secretos o monitorización, trazas y trazabilidad.

Las aplicaciones nativas para la nube necesitan varias herramientas, en la página [CNCF Cloud Native Interactive Landscape](https://landscape.cncf.io/) se agrupan las herramientas disponibles por categoría. Para cada categoría hay más de diez herramientas disponibles entre las que elegir para cubrir la necesidad. La colección de herramientas además de estar categorizadas por funcionalidad es posible filtrar aquellas que tienen una licencia de software libre o código abierto.

Para tomar una decisión al elegir una herramienta u otra hay varios criterios:

* Documentación: una buena documentación permite aprender a como utilizarla y sacarle el máximo provecho directamente desde la fuente original.
* Popularidad, madurez, comunidad: utilizar una herramienta popular permite que si hay algún problema posiblemente ya le haya ocurrido a otra persona antes de modo que es muy posible encontrar una respuesta rápidamente y correcta. Una herramienta madura tendrá mejor documentación y es más fácil encontrar más información de ella.
* Herramienta específica o generalista: dado que las necesidades de los microservicios son varias es necesario utilizar múltiples herramientas para resolver esa necesidad. Cada una de estas herramientas añade un poco más de complejidad a la infraestructura que hay que mantener para que los microservicios funcionen o para poder entregar funcionalidad desde el entorno de desarrollo al entorno de producción. Algunas herramientas realizan varias funcionalidades, si una única herramienta es adecuada para varias necesidades permite reducir la complejidad de la infraestructura que si fuesen varias. Las herramientas específicas proporcionan funcionalidades más avanzadas pero las generalistas aún quizá no teniendo esas funciones avanzadas las que tienen son suficientes.
* Dependencias o posibilidad de cambiar: cada herramienta que se utiliza añade una dependencia al sistema, es algo a tener en cuenta ya que para alguna de entre todas las que se elijan será sustituida en el futuro por una nueva alternativa mejor. Tener la posibilidad de cambiar a una herramienta en el futuro es algo deseable sin que suponga un gran impacto en el sistema.
* Licencia y coste: las herramientas tienen un coste, los productos con licencia de software libre o código abierto no incurren en costes pero tienen un coste de mantenimiento tanto económico como en tiempo que ha de hacer uno mismo, por el contrario los servicios gestionados basados en la nube no tienen coste de infraestructura ni de mantenimiento del que se encarga el propio servicio pero tienen un coste mensual.

En el caso de optar por administrar y mantener uno mismo el servicio otro punto importante a considerar son las actualizaciones a nuevas versiones. Conviene considerar cual es el procedimiento para actualizar a una nueva versión y tener un plan para realizarlas de forma periódica, no solo para tener acceso a las nuevas características que ofrezcan sino también para evitar quedar expuesto a un problema de seguridad de una versión antigua y ya no mantenida.

{{< image
    gallery="true"
    image1="image:cncf-landscape.png" optionsthumb1="650x450" title1="Colección de herramientas nativas para la nube"
    caption="Colección de herramientas nativas para la nube" >}}

{{< tableofcontents >}}

### Base de datos

Los datos son una de las partes más importantes para el correcto funcionamiento de un negocio. Una parte importante de las aplicaciones consiste tanto en persistir datos como recuperarlos cuando se necesitan. Una de las ventajas de los microservicios es que cada uno de ellos puede utilizar el tipo de base de datos que más adecuado considere para almacenar los datos. Puede ser una base de datos relacional como [PostgreSQL][postgresql], clave-valor como [Redis][redis], de documentos con [MongoDB][mongodb] o incluso una combinación de varios.

* [Introducción a la base de datos relacional PostgreSQL](https://picodotdev.github.io/blog-bitix/2017/05/introduccion-a-la-base-de-datos-relacional-postgresql/)
* [Introducción a la base de datos NoSQL MongoDB][blogbitix-237]
* [Introducción a la base de datos NoSQL Redis][blogbitix-240]

### Mensajería

Una forma de comunicar los microservicios sin crear acoplamiento entre los servicios o de forma asíncrona es utilizar una comunicación basada en mensajes. Un sistema de mensajería es [RabbitMQ][rabbitmq], otra popular es [Kafka][apache-kafka] con algunas diferencias.

* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]

### Construcción de imágenes

La forma nativa de ejecutar las aplicaciones basadas en microservicios es utilizando contenedores. Los contenedores son una forma de independizarse de las dependencias de ejecución que necesita cada microservicio ya sea por el lenguaje de programación que utilizan o librerías que necesitan, los contenedores permite ejecutarlos y tratarlos de la misma forma. [Docker][docker] permite ejecutar aplicaciones en contenedores así como construir imágenes de contenedores, [Packer][packer] es otra herramienta que permite construir diferentes artefactos ya sea para una máquina virtual [VirtualBox][virtualbox] o la computación en la nube de [Amazon Web Services][amazon-web-services], [Google Cloud Computing][google-cloud] o [Microsoft Azure][microsoft-azure].

* [Introducción y características de Docker][blogbitix-49]
* [Cómo crear una imagen para Docker usando un Dockerfile][blogbitix-51]

### Integración continua y despliegue continuo

Desarrollando pruebas automatizadas al mismo tiempo que desarrollar la funcionalidad de una aplicación permite conocer que el software desarrollado con los casos creados funciona como se espera. Más tarde al introducir más cambios permiten conocer si un cambio afecta de forma inesperada a alguna funcionalidad cubierta por las pruebas automatizadas. Utilizando integración continua las pruebas automatizadas proporcionan el estado por cada cambio realizado en el repositorio de código fuente. Automatizar el despliegue de los cambios es la entrega continua y permite reducir el tiempo entre que una funcionalidad se ha terminado de desarrollar y el tiempo en que está disponible en el entorno de producción. [Gitlab][gitlab] es una herramienta que cubre varias necesidades, entre ellas la de repositorio de código fuente con [Git][git] y también las funcionalidades de integración y entrega continua. [Jenkins][jenkins] es una herramienta más específica y cubre las funcionalidades de integración y entrega continua.

* [GitLab, la completa herramienta integrada para desarrollo de software][blogbitix-290]
* [Integración y entrega continua con GitLab sobre Docker][blogbitix-294]

### Planificación y orquestación

Con un número importante de microservicios, incluso múltiples instancias de cada uno de ellos que varían en número a lo largo del tiempo para soportar tolerancia a fallos y alta disponibilidad se hace imprescindible tratar a las instancias como ganado y una herramienta que se encargue de su administración, tanto para planificar en que nodos se crean las instancias según sus restricciones como de aumentar o reducir su número, realizar despliegues utilizando estrategias _blue/green_ o _canary_ y revertir a versiones anteriores. [Kubernetes][kubernetes] es una popular específica para contenedores Docker, [Nomad][nomad] es una herramienta más sencilla e independiente pero integrable con otras herramientas de [HashiCorp][hashicorp] que en conjunto proporcionan similares funcionalidades que Kubernetes.

* [Introducción a Nomad para gestionar aplicaciones y microservicios][blogbitix-398]
* [Arquitectura de referencia de Consul, Vault y Nomad para un centro de datos][blogbitix-508]

### Descubrimiento de servicios y conexión

Si los microservicios son numerosos, se crean múltiples instancias de cada uno de ellos y son efímeros pudiendo desaparecer es necesario un servicio que guarde un registro de cual es su ubicación que permita conocer al resto de servicios donde se encuentran para comunicarse con ellos. [Consul][consul] es un servicio que proporciona la funcionalidad de de registro y descubrimiento además de otras funcionalidades como conectividad segura entre microservicios basado en intenciones en vez de direcciones IP, puertos y reglas de _firewall_.

* [Registro y descubrimiento de servicios con Spring Cloud y Consul][blogbitix-206]
* [Comunicaciones seguras, autenticación mutua y autorizaciones con intenciones entre servicios usando Consul Connect y Nomad][blogbitix-502]

### Llamada a procedimientos remotos y API

Unos microservicios tienen como dependencia y ofrecen su funcionalidad a otros a través de una interfaz de programación o API. La interfaz puede ser REST, [GraphQL][graphql] o si es un servicio interno con llamada a procedimiento remoto con [gRPC][grpc]. La comunicación entre servicios mediante API y mensajes no es incompatible, es más, es común que un microservicio utilice ambas formas para comunicarse o otros microservicios.

* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]
* [Introducción a gRPC y ejemplo con Java][blogbitix-512]
* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]

### _Proxy_ de servicios

Un _proxy_ permite hacer de intermediario entre el origen servicio origen y el servicio destino para ofrecer una visión más sencilla de los microservicios al consumidor o hacer algunas operaciones en las comunicaciones. [Traefik][traefik] es una _proxy_ adaptado a los microservicios, es integrable con Consul y con Docker.

* [Microservicios con Spring Cloud, Consul, Nomad y Traefik][blogbitix-436]

### Automatización y configuración

Para hacer manejable la infraestructura y configuración de la misma es necesario automatizarla tratando a la infraestructura como código. Esto permite evitar cambios manuales, mantener un registro de los cambios y replicar la misma infraestructura rápidamente en caso de ser necesario. La infraestructura puede ser mutable o inmutable, tener una infraestructura inmutable es más deseable ya que en todo momento se tiene conocimiento del estado de la infraestructura evitando cambios temporales aplicados que no estén bajo control e impidan replicar la infraestructura. [Ansible][ansible] permite automatizar ciertas tareas para una infraestructura mutable en algunos casos es herramienta conveniente, [Terraform][terraform] es una herramienta declarativa con la que se especifica la infraestructura deseada y aplica los cambios necesarios para tenerla, sigue la filosofía inmutable con la que si una instancia de una máquina cambia en vez de cambiar la instancia crea una nueva y elimina la antigua.

### Registro de contenedores y artefactos

A partir del código fuente se construyen los artefactos que posteriormente se incluyen en contenedores que son desplegados. Un repositorio de artefactos permite almacenar los binarios y paquetes de cada versión a partir del código fuente, esto permite volver a una versión anterior y guardar un historial de los mismos para el futuro. [Docker Registry][docker-registry] es un registro para imágenes Docker, [Artifactory][artifactory] o [Nexus][sonatype-nexus] son registros más generalistas que permite guardar artefactos de JavaScript, Python o Java entre otros, [Archiva][apache-archiva] es específico de artefactos para Java.

### Autenticación, claves y secretos

Para autenticar y autorizar las llamadas a los servicios a través de de la API que ofrecen se suele utilizar [OAuth][oauth], como proveedor de autenticación OAuth una opción es [Keycloak][keycloak].

Por otro lado, cierta información que tratan las aplicaciones es de carácter sensible e importante para la seguridad, estas son las claves y contraseñas que permiten conectarse a las bases de datos o autenticarse en otros microservicios, datos personales o bancarios. [Vault][vault] permite generar credenciales bajo demanda con un tiempo de expiración pequeño y se ofrece como un servicio centralizado para cifrar y descifrar datos, esto aumenta la seguridad, reduce las posibilidades de ataque y en caso de éxito mitiga la información comprometida si la más sensible está cifrada.

* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]
* [Administrar secretos y proteger datos sensibles con Vault][blogbitix-424]
* [Generar credenciales de conexión a base de datos bajo demanda con Vault][blogbitix-428]
* [Utilizar credenciales de conexión a la base de datos generadas por Vault en una aplicación de Spring][blogbitix-429]

### Monitorización, trazas y trazabilidad

Un sistema complejo como son los microservicios es indispensable que esté monitorizado para dar visibilidad a su estado y observar su funcionamiento en tiempo real. Las métricas son obtener las trazas que emiten cada uno de los microservicios, monitorizar los tiempos de respuesta de cada microservicio, consumo de memoria, CPU o almacenamiento.

[Prometheus][prometheus] permite recoger las métricas de funcionamiento de cada uno de los microservicios y almacenar los datos. [Grafana][grafana] permite acceder a los datos recogidos por Prometheus y crear diferentes tipos de gráficas que permitan visualizar gran cantidad de información con una fácil compresión.

[Fluentd][fluentd] y [Logstash][logstash] permiten recopilar los registro de trazas que emiten las aplicaciones y enviarlos a un consumidor para que sean agregados e indexados en [Elasticsearch][elasticsearch], [Kibana][kibana] es una servicio que permite buscar y obtener los mensajes de trazas deseados.

* [Centralizar y consultar las trazas de las aplicaciones con Elasticsearch, Logstash y Kibana][blogbitix-517]

Una solicitud genera varias llamadas entre servicios en cadena, para tener una visión global de cada petición y cuales son las llamadas que ha desencadenado entre los diferentes servicios que se usan como dependencias están [Sleuth][spring-cloud-sleuth] y [Zipkin][zipkin]. Un identificador único global permite obtener toda la cadena de llamadas realizada con los tiempos empleados en cada microservicio para atender la petición lo que permite identificar problemas de rendimiento.

* [Monitorizar una aplicación Java de Spring Boot con Micrometer, Prometheus y Grafana][blogbitix-366]
* [Exponer las métricas de Hystrix en Grafana con Prometheus de una aplicación Spring Boot][blogbitix-368]
* [Trazabilidad en servicios distribuidos con Sleuth y Zipkin][blogbitix-518]

### Repositorio de artefactos

Los repositorio sde artefactos permiten compartir artefactos generados en la compilación de los proyectos. Los artefactos en Java son librerías que otros proyectos utilizan como dependencias, en Docker son las imágenes que utilizan los contenedores generados con un archivo Dockerfile en Java Script paquetes npm y otros lenguajes como Python, Go o C# los suyos. [Nexus][nexus] permite crear un repositorio de artefactos privado.

* [Repositorio de artefactos privado con Nexus][blogbitix-559]

{{% /post %}}
