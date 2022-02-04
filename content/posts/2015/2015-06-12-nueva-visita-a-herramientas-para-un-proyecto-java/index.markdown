---
pid: 84
type: "post"
title: "Nueva visita a Herramientas para un proyecto Java"
url: "/2015/06/nueva-visita-a-herramientas-para-un-proyecto-java/"
date: 2015-06-12T20:00:00+02:00
updated: 2020-08-24T19:30:00+02:00
index: true
rss: true
sharing: true
comments: true
language: "es"
imagePost: "logotype:java.svg"
tags: ["java", "opinion", "planeta-codigo", "programacion"]
summary: "Las tecnologías para desarrollar una aplicación son un medio para resolver las necesidades del negocio o un cliente, no un fin. Pero esto no quiere decir que la elección sea trivial o poco importante, realizar las elecciones adecuadas según lo requisitos puede evitar complicaciones en un futuro. Para un proyecto basado en la plataforma Java esta es mi selección de herramientas de las que conozco."
---

{{% post %}}

{{< logotype image="java.svg" >}}

Hace 5 años escribí un [artículo comentando que tecnologías elegiría][elblogdepicodev-62] para un proyecto Java si tuviese posibilidad. Al ritmo que avanza la tecnología cuatro años es un tiempo bastante largo, también es un tiempo en el que he podido aprender y añadir a mi «caja de herramientas» nuevas opciones que después de evaluarlas me han gustado. En este artículo haré una nueva visita al artículo anterior y comentaré algunas nuevas herramientas.

### Lenguaje de programación

Como lenguaje de programación seguiría usando Java, en mi caso es el lenguaje que más conozco y sigue siendo una de las opciones más válidas. Pero con la salida de una nueva versión usaría Java 8. La [publicación de Java 8 incorporando varias novedades importantes][blogbitix-17] han mejorado varios aspectos de este lenguaje, por mencionar los más relevantes están la incorporación de _lambdas_ que proporciona una cierta forma de programación funcional, también destaca los _streams_ que permite describir los algoritmos que procesan datos de una forma más expresiva y legible además de aprovechar mejor los procesadores multinúcleo, métodos por defecto en interfaces que permite mantener compatibilidad hacia atrás (aunque esto interesa más a los desarrolladores que hacen APIs para que sean usadas por terceras partes), una nueva API para el manejo de fechas eliminando una de las críticas de versiones anteriores y algunas cosas más. El futuro con Java 9 es prometedor si presenta la interesante modularidad.

* [Novedades y nuevas características de Java 8][blogbitix-17]
* [Novedades y nuevas características de Java 11][blogbitix-350]
* [10 razones para seguir usando Java][blogbitix-81]
* [Introducción y nuevas características de Java EE 7][blogbitix-131]
* [Novedades de la plataforma Java][blogbitix-serie-java-platform]

### Persistencia en base de datos

Las librerías más populares de persistencia en Java son [Hibernate][hibernate] aunque para algunos casos usaría o como complemento [jOOQ][jooq], [Spring Data][spring-data] ofrece varias utilidades para facilitar crear las clases repositorio usando Hibernate.

jOOQ es una librería que no tienen tanta magia como Hibernate que en muchas ocasiones produce errores complicados de resolver y no tenemos tanto control de lo que hace, por otro lado jOOQ devuelve a la base de datos y al lenguaje SQL la relevancia que con Hibernate queda abstraída con el mapeado del modelo de datos a objetos y el lenguaje HQL. jOOQ permite construir las sentencias SQL de forma programática con lo que el compilador nos indicará errores de compilación y nos validará los tipos que usemos, ambas cosas son importantes cuando hacemos un _refactor_ en alguna parte de la aplicación. En el artículo [alternativa a Hibernate y ejemplo jOOQ][blogbitix-82] entro en más detalles.

* [Alternativa a Hibernate y ejemplo jOOQ][blogbitix-82]

Al persistir datos si es necesario realizar auditoría de datos dos posibilidades son [Envers][hibernate-envers] específica para Hibernate y [Javers][javers] usable con cualquier librería de persistencia. Permiten guarda los típicos campos fecha creación, usuario creación, fecha modificación y última modificación asi como mantener un histórico completo de todos los cambios realizados a una entidad.

* [Histórico de datos, auditoría y diferencias entre objetos con Javers en Java][blogbitix-191]

### Modificaciones de la base de datos

En un proyecto de larga duración y en el que se realicen mejoras probablemente necesitaremos modificar el modelo de la base de datos. Empleando la herramienta [Liquibase][liquibase] podremos [automatizar la actualización del esquema de la base de datos][elblogdepicodev-155] ya necesitemos añadir campos, cambiarlos de nombre, eliminarlos, crear o eliminar tablas, insertar, eliminar o actualizar datos. Estas actualizaciones de la base de datos se indican en un archivo que podemos guardar en nuestro repositorio de control de versiones de forma que podamos ver y reproducir los cambios que se han hecho a la base de datos a lo largo del tiempo.

* [Modificar la base de datos con Liquibase][elblogdepicodev-155]

### Pruebas

Para pruebas unitarias dos buenas posibilidades son [JUnit][junit], [Spock][spock] y [Geb][geb]. Spock permite realizar los teses unitarias con un DSL bastante descriptivo que facilita la lectura posteriormente de la prueba, sin embargo, JUnit ofrece las mismas ventajas para los teses que el para el código de la aplicación al usar Java. Por otro lado Geb permite automatizar las pruebas de las aplicaciones web proporcionando facilidades, se puede integrar con Spock.

Otra herramienta muy útil es [Testcontainers][Testcontainers] que permite hacer pruebas de integración usando el mismo software de producción como puede ser una base de datos [MySQL][mysql] o [PostgreSQL][postgresql] en vez de una base de datos en memoria.

* [Pruebas unitarias con Spock y Mockito][elblogdepicodev-114]
* [Pruebas funcionales con Geb en una aplicación web Java][blogbitix-332]
* [Cobertura de código y mutation testing en pruebas unitarias con JaCoCo y PIT en Java][blogbitix-438]
* [Pruebas de carga y rendimiento de un servicio web con Apache Bench][blogbitix-411]
* [Teses unitarios parametrizados con JUnit][blogbitix-410]
* [Pruebas de integración con Testcontainers, ejemplo de JPA con la base de datos PostgreSQL][blogbitix-490]

### Cliente

En el lado cliente de una aplicación web usaría la combinación de varias tecnologías, [jQuery][jquery] para acceder y manipular el DOM de la página web, [RequireJS][requirejs] para cargar los archivos necesarios en la página y evitar la polución del ámbito global JavaScript.

Si la aplicación tiene una carga importante en el lado cliente evaluaría usar [TypeScript][typescript] como lenguaje por ser un compilado con sus ventajas en bases de código grandes, [Webpack][webpack] para gestionar los recursos, empaquetarlos y realizar transformaciones como alternativa a RequireJS y [React][react] para crear componentes en el lado cliente. [Jest][jestjs] o [Jasmine][jasmine] para hacer pruebas unitarias en JavaScript.

* [Ejemplo lista de tareas con Backbone y React][blogbitix-20]
* [Internacionalización (i18n) en JavaScript][blogbitix-63]
* [Componentes en el cliente con Web Components usando JavaScript, HTML y CSS][blogbitix-388]
* [Usar un paquete npm con Webpack creado con Storybook, React y TypeScript][blogbitix-472]

### _Framework_ web

Para el desarrollo de una página o aplicación web seguiría usando [Apache Tapestry][tapestry] por la productividad y alta reutilización que se puede conseguir, también por la flexibilidad, extensibilidad y adaptabilidad del _framework_ si necesita recursos de cliente añadiendo dependencias con [Webjars][webjars]. Descargando el [libro PlugIn Tapesty][blogbitix-12] puedes conocer muchos más detalles.

Si se tratase de una aplicación REST evaluaría [Spring Boot][springboot] o [GraphQL][graphql] para proporcionar la interfaz exterior de los microservicios y quizá evaluaría [Apache Thrift][apache-thrift] o [gRPC][grpc] para consumirlos internamente, Thrift permite acceder a la API de una forma programática más sencillamente que consumir una interfaz REST o GraphQL cruda.

La tendencia actual es desarrollar microservicios y esto en las aplicaciones web supone que sean capaces de ofrecer su servicio por si mismas no usando un servidor de aplicaciones que hay que instalar previamente simplificando el despliegue a los administradores de sistemas, esto también se puede conseguir en parte usando [Docker][docker]. En vez de usar un [Tomcat][tomcat] tradicional podemos usar la versión embebible o Spring Boot.

* [Libro sobre desarrollo de aplicaciones con Apache Tapestry][blogbitix-12]
* [Introducción y ejemplo de API RPC con Apache Thrift][blogbitix-72]
* [Introducción a gRPC y ejemplo con Java][blogbitix-512]
* [Aplicación web Java autocontenida con Tomcat Embedded][blogbitix-71]
* [Ejemplo sencillo de servicio web con RESTEasy][elblogdepicodev-142]
* [Aplicación Java autocontenida con Spring Boot][blogbitix-103]
* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]
* [Dependencias sobre librerías de lado de cliente con Webjars en una aplicación web Java][blogbitix-325]

### Base de datos

Para una base de datos relacional en vez de [MySQL][mysql] usaría [PostgreSQL][postgresql]. PostgreSQL posee numerosas opciones avanzadas y es una de las bases de datos libres más reconocidas. El futuro de MySQL con Oracle y su escisión en MariaDB es más incierto además de no poseer algunas características que PostgreSQL sí.

Dependiendo del causística de la aplicación otras opciones complementarias son [Redis][redis] y [MongoDB][mongodb], una base de datos clave-valor, una base de datos de documentos y en caso de tener que hacer búsquedas de texto completo con [Elasticsearch][elasticsearch].

* [Introducción a la base de datos relacional PostgreSQL][blogbitix-236]
* [Introducción a la base de datos NoSQL MongoDB][blogbitix-237]
* [Introducción a Elasticsearch][blogbitix-21]

### Entorno de desarrollo

Para el entorno de desarrollo o _devbox_ usaría [Docker][docker] y [Compose][docker-compose] que permiten disponer de un entorno más parecido al entorno de producción y en menos tiempo que instalando todo lo necesario en la máquina física. También podemos usar Docker para el entorno de producción. Para automatizar la tareas de configuración o despliegue usaría [Ansible][ansible] ya que al contrario de otras opciones no requiere instalar un agente en las máquinas a administrar, con un acceso SSH es suficiente.

* [Introducción y características de Docker][blogbitix-49]
* [Inicio básico de Docker][blogbitix-50]
* [Cómo crear una imagen para Docker usando un Dockerfile][blogbitix-51]
* [Introducción a Ansible][blogbitix-52]

### Hospedaje

En cuanto al alojamiento para la aplicación la nube es otra tendencia por su flexibilidad. La [nube de Amazon][amazon-ec2] proporciona muchos servicios que pueden sernos útiles sin embargo si nuestra aplicación no los necesita y no es demasiado complicada podemos optar por otras opciones más baratas. Dos opciones más baratas son [Linode][linode] o [Digital Ocean][digital-ocean] con las que por unos 5 o 10 € al mes podemos disponer de una máquina con 1 GIB de RAM, unos 30 GiB de discos SSD y una amplia transferencia de datos entrantes y salientes.

* [Nueva visita a 5+ opciones de «hosting» para aplicaciones][blogbitix-76]

Aún usando un servicio en la nube para no encadenarse a uno determinado y disponer un centro de datos están [Consul] como sistema de registro, descubrimiento, gestión de configuración y conexión entre servicios, [Vault][vault] para usar seguridad como servicio, [Nomad][nomad] como orquestador de las aplicaciones con posibilidad de hacer despliegues _blue/greeen_ o _canary_ asi com volver a una versión anterior fácilmente y [Terraform][terraform] para aprovisionar el entorno en la nube independiente del proveedor y haciendo que la infraestructura este bajo el control de versiones al usar infraestructura como código.

* [Microservicios con Spring Cloud, Consul, Nomad y Traefik][blogbitix-436]
* [Administrar secretos y proteger datos sensibles con Vault][blogbitix-424]
* [Estrategias de despliegue para microservicios con Nomad][blogbitix-399]
* [Revertir un servicio a una versión anterior con Nomad][blogbitix-503]
* [Arquitectura de referencia de Consul, Vault y Nomad para un centro de datos][blogbitix-508]

### Otras

Otras funcionalidades que necesita algunas aplicaciones son:

* Trazabilidad con [Sleuth][spring-cloud-sleuth].
* Tolerancia a fallos con [Resilience4j][resilience4j].
* Búsquedas a texto completo con [Elasticsearch][elasticsearch].
* Métricas y monitorización con [Micrometer][micrometer], [Prometheus][prometheus] y [Grafana][grafana].
* Autenticación y autorización con [Keycloak][keycloak], [OAuth][oauth], [JWT][jwt] y [pac4j][pac4j].
* Bus de eventos con Spring Events o [Guava][guava].
* Comunicación desacoplada entre aplicaciones basado en mensajes con [RabbitMQ][rabbitmq].
* [Quartz][quartz] para la programación de tareas de forma periódica.
* [JasperReports][jasperreports] y [PDFBox][apache-pdfbox] para informes complejos o documentos CSV, Excel o PDF sencillos.
* [Log4j 2][log4j] para emitir trazas.
* Preprocesador CSS con [Less][less] para facilitar la escritura de hojas de estilo CSS y [Bootstrap][bootstrap] como estilos por defecto para una aplicación.
* [PMD][pmd] y [Checkstyle][checkstyle] para analizar el código fuente.
* Repositorio de artefactos privado con [Nexus][nexus].

De algunas de estas herramientas he escrito de forma individualizada.

* [Trazabilidad en microservicios con Spring Cloud Sleuth][blogbitix-396]
* [Implementar tolerancia a fallos con Resilience4j][blogbitix-425]
* [Introducción a Elasticsearch][blogbitix-21]
* [Monitorizar una aplicación Java de Spring Boot con Micrometer, Prometheus y Grafana][blogbitix-366]
* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]
* [Servidor OAuth, gateway y servicio REST utilizando tokens JWT con Spring][blogbitix-382]
* [Publicación y suscripción de eventos con Guava EventBus en una aplicación Java][blogbitix-422]
* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]
* [Tareas programadas de forma periódica con Quartz y Spring en Java][blogbitix-497]
* [Ejemplo sencillo de como crear un Excel o CSV en Java con Apache POI y OpenCSV][blogbitix-146]
* [Ejemplo sencillo de como crear un documento PDF con Java y PDFBox][blogbitix-430]
* [Generar documentos, informes y facturas en formato PDF con JasperReports y Java][blogbitix-449]
* [La librería Log4j para emitir trazas en aplicaciones Java][blogbitix-334]
* [Qué hace y ventajas de un preprocesador de estilos CSS][blogbitix-509]
* [Análisis estático de código con PMD y un ejemplo][blogbitix-297]
* [Repositorio de artefactos privado con Nexus][blogbitix-559]

Hay otras herramientas aún pasado este tiempo seguiría usando como [Git][git] para el control de versiones, [GitLab][gitlab] como plataforma para desarrollo que incluye repositorio de git, gestión de peticiones e integración continua además de otras funcionalidades en una única herramienta. [SDKMAN][sdkman] para gestionar las versiones de librerías Java en el entorno de desarrollo, [Gradle][gradle] como herramienta de construcción, [GNU/Linux][linux] tanto para desarrollar como para el servidor en el que desplegar la aplicación, [IntelliJ][intellij] como IDE para editar código Java o [Visual Studio Code][microsoft-visual-studio-code] para editar archivos no Java.

* [GitLab, la completa herramienta integrada para desarrollo de software][blogbitix-290]
* [Herramienta de construcción Gradle][elblogdepicodev-98]
* [La herramienta SDKMAN para instalar varias versiones del JDK y software de la plataforma Java][blogbitix-489]

Usar herramientas adecuadas para un proyecto es importante aunque independientemente de las herramientas también es importante la metodología, [Domain Divern Design][wikipedia-domain-driven-design] o DDD propone numerosas pautas para organizar y modelar aplicaciones complejas con reglas de negocio de mofo que las diferentes partes estén desacopladas y el mantenimiento sea más sencillo. Los patrones de diseño aplicados de forma adecuada simplifican enormemente el código.

* [Generar en el dominio los identificativos de las entidades aplicando DDD antes de persistirlas en la base de datos][blogbitix-493]
* [El patrón de diseño Specification, ejemplo de implementación y uso en JPA con Spring Data][blogbitix-491]
* [Ejemplo del patrón de diseño Builder][blogbitix-99]
* [Ejemplo del patrón de diseño No Operation][blogbitix-8]
* [Ejemplo de máquina de estados con Spring Statemachine][blogbitix-394]

Para cada un de estas herramientas en muchos casos tendremos varias alternativas similares para elegir, a veces elegir una u otra es algo subjetivo. Estas herramientas en muchos casos son de lo mejor que hay disponible pero perfectamente se pueden usar alternativas similares.

¿Cuales serán las herramientas que formarán el «estado del arte» dentro de otros cuatro años? ¿Cuales sobrevivirán y cuales perecerán en el camino? ¡Apasionante!

{{< reference >}}
* [Elegir herramientas para un proyecto][elblogdepicodev-62]
* [Build Your Own Technology Radar](http://nealford.com/memeagora/2013/05/28/build_your_own_technology_radar.html)
* [Technology Radar](http://www.thoughtworks.com/radar)
{{< /reference >}}

{{% /post %}}
