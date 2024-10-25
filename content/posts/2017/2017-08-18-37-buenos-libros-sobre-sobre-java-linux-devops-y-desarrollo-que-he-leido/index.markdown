---
pid: 254
type: "post"
title: "37 buenos libros sobre sobre Java, Linux, DevOps y desarrollo que he leído"
url: "/2017/08/37-buenos-libros-sobre-sobre-java-linux-devops-y-desarrollo-que-he-leido/"
date: 2017-08-18T23:00:00+02:00
updated: 2017-08-19T11:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:blog-bitix.svg"
tags: ["blog", "gnu-linux", "java", "planeta-codigo", "programacion"]
summary: "Con el buscador Google y usando las palabras clave adecuadas es raro no encontrar información sobre aquello que estemos buscando. Pero en internet la información suele estar muy dispersa y en cada sitio la información no es completa y con profundidad, internet esta muy bien como apoyo o para precisar una determinada cuestión. Sin embargo, los libros siguen siendo un buen material de aprendizaje, entre sus ventajas está que la información está mejor organizada con una estructura más didáctica y dependiendo del libro el tema tratado es explicado con profundidad. Desde el 2014 son unos cuantos libros más los que he leído."
---

{{% post %}}

En internet hay cantidad de información que nos permiten aprender sobre cualquier cosa a nuestro libre albedrío y que queramos teniendo el tiempo para dedicárselo ya sea en forma de escuetas presentaciones en [SpeakerDeck][speakerdeck] o [SlideShare][slideshare], páginas sobre temas de programación, artículos en bitácoras como esta que estás leyendo u otras, vídeos en [YouTube][youtube], convenciones presenciales o _hangouts_.

Algunas páginas dedicadas a la programación relativa a [Java][java] y [GNU][gnu]/[Linux][linux] a las que estoy suscrito son:

* [JAXenter](https://jaxenter.com/)
* [JavaWorld](https://www.javaworld.com/)
* [JavaMagazine](http://www.javamagazine.mozaicreader.com/)
* [VirtualJUG](https://virtualjug.com/)
* [BSD Magazine](https://bsdmag.org/)
* [LWN.net](https://lwn.net)
* [linux.com](https://www.linux.com/)

En la biblioteca digital [OpenLibra][openlibra] encontramos una gran cantidad de libros de diversos temas cuyas licencias permiten su libre acceso.

{{< image
    gallery="true"
    image1="image:openlibra.webp" optionsthumb1="300x200" title1="OpenLibra"
    caption="Web de OpenLibra" >}}

Podemos leer libros cuya información no está relacionada con un determinado herramienta técnica sino que su conocimiento es más generalista e independiente de la tecnología, en el artículo [8+ libros para mejorar como programadores][blogbitix-55] recogía varios de los más mencionados y recomendados por mucha gente todavía válidos a pesar del tiempo transcurrido desde que fueron escritos, son atemporales. En la época que estaba en la universidad hace unos 15-20 años para mi internet aún estaba llegando, lo más parecido que tenía era la biblioteca en la que pedía prestados libros pero lógicamente con mayor limitación de material disponible. Hoy en día los alumnos, de universidad o bachillerato y gente con interés que quiera formarse mediante internet tiene acceso a material del que aprender mucho mejor en cantidad, facilidad y seguramente en calidad. A la gente que ahora está en la universidad le recomendaría que no se limiten a aprender lo que les enseñan en clases magistrales sus profesores sino que tomen parte activa en su formación aprovechándose de los recursos que pueden encontrar en internet.

Estos son los que me ha parecido que están bastante bien, algunos he leído de forma completa otros solo ciertos capítulos, algunos después de leídos olvidados en parte por falta de uso y porque mi inglés no es muy bueno. Son libros relacionados con la programación, la web o el lenguaje Java en su mayoría. Hay algún libro más que he leído pero estos son los que quería destacar. Además de aprender leer esta buena cantidad de libros me da ideas para escribir algunos artículos, algunos de los cuales he incluído en este artículo como relacionados.

{{< tableofcontents >}}

## Libros sobre Java

### Java 9 Modularity

{{< amazon
    linkids="https://amzn.to/42nhyuQ"
    asins="1491954167"
    titles="" >}}

La modularidad no es la única característica que se incorporará en Java 9 hay muchas otras pero quizá la que más llama la atención en parte porque estaba prevista ya para versiones anteriores y finalmente se ha postergado desde la versión 7. En cualquier caso la modularidad mejora la encapsulación, seguridad, rendimiento y compatibilidad con versiones futuras aspecto de la compatibilidad hacia atrás que siempre se la ha dado y es destacado en la plataforma Java.

[Java 9 Modularity](https://amzn.to/42nhyuQ) explica los cambios que afectan principalmente a algún nuevo archivo de código fuente para definir de forma explícita las dependencias que posee un módulo y los paquetes que exporta que definen su interfaz pública. También explica los cambios que afectan a comandos básicos como _javac_ para compilar y _java_ para ejecutar una aplicación. Hasta que los módulos se empiecen a adoptar por las librerías, y será lento, se explican los mecanismos para usar en Java 9 con módulos librerías de código que no hayan sido adaptadas aún.

Java 9 será publicado en septiembre y ya tengo ideados unos cuantos artículos para publicar.

### Java Generics and Collections

{{< amazon
    linkids="https://amzn.to/4bmsmgG"
    asins="0596527756"
    titles="Java Generics and Collections" >}}

Los tipos genéricos se añadieron en Java 5, en el año 2004, que el copilador utiliza para validar la corrección del código evitando problemas que serían producidos en tiempo de ejecución. En la API de colecciones se usa de forma extensiva.

El concepto no es difícil pero dominar las declaraciones de los _generics_ no lo es, se puede complicar bastante. El libro [Java Generics and Collections](https://amzn.to/4bmsmgG) es antiguo pero no está anticuado y sigue tiendo plena validez hoy, explica porque los _generics_ fueron implementados en Java como fueron implementados, las limitaciones que causa y las ponderaciones que realizaron sus desarrolladores.

* [Tutorial sobre los tipos genéricos de Java][blogbitix-138]

### Cloud Native Java

{{< amazon
    linkids="https://amzn.to/4bkBJ0t"
    asins="B074R4B7LY"
    titles="Cloud Native Java" >}}

La flexibilidad que aporta la computación en la nube (o en los ordenadores de otros como dice la FSF) es en gran medida su éxito. Los programas que adopten esta nueva arquitectura obtienen varias ventajas como escalabilidad, tolerancia a fallos o administración de automatizada. Sin embargo, las aplicaciones tiene que diseñarse o adaptarse a este nuevo entorno.

Algunas librerías como [Spring Boot][spring-boot] proporciona a las aplicaciones Java facilidades para desarrollar aplicaciones que se consideran nativas para la computación en la nube. [Cloud Native Java](https://amzn.to/4bkBJ0t) hace un repaso a los factores a tener en cuenta en las aplicaciones.

### Java The Good Parts

{{< amazon
    linkids="https://amzn.to/3SJJqpR"
    asins="0596803737"
    titles="Java: The Good Parts" >}}

El lenguaje de programación Java es uno de los lenguajes más populares y que más desarrolladores posee desde ya muchos años. Su éxito se debe a varios principios que los arquitectos de la plataforma incorporaron desde sus inicios. Un lenguaje orientado a objetos, con excepciones para el tratamiento de errores, organización de clases en paquetes, recolector de basura que libera al programador de la tarea y evitar fugas de memoria o errores no controlados, la máquina virtual que posibilita escribir el código una vez y ejecutarlo en cualquier plataforma para la que haya una JVM, la gran documentación en _javadoc_ que sirve como consulta y como formato para documentar en el propio código fuente, las colecciones que proporcionan estructuras de datos básicas y útiles para cualquier programa, concurrencia con hilos y primitivas para la sincronización y con el paso del tiempo un ecosistema muy grande de librerías, herramientas y entornos integrados de desarrollo.

Todas estas características se comentan detalladamente en [Java The Good Parts](https://amzn.to/3SJJqpR).

### Building Maintainable Software

{{< amazon
    linkids="https://amzn.to/48Z3Kcu"
    asins="1491953527"
    titles="Building Maintainable Software: Ten Guidelines for Future-Proof Code" >}}

Hay conocimiento que es independiente del lenguaje que se utilice como tratar de escribir funciones o métodos con pocas líneas de código, mantener las interfaces pequeñas, separar en módulos, mantener el acoplamiento bajo y balanceados, que la base de código sea pequeña, automatizar las pruebas o escribir código limpio. Aunque [Building Maintainable Software](https://amzn.to/48Z3Kcu) es una edición para Java con los ejemplos en este lenguaje las motivaciones que describe de cada uno de los puntos anteriores es universal.

### The Well-Grounded Java Developer

{{< amazon
    linkids="https://amzn.to/3SI6zJ1"
    asins="1617290068"
    titles="The Well-Grounded Java Developer" >}}

[The Well-Grounded Java Developer](https://amzn.to/3SI6zJ1) diserta sobre varios conceptos a tener en cuenta para tener una base sólida sobre la plataforma Java. Nuevas características de Java 7, cosas vitales como inyección de dependencias, concurrencia, ficheros _class_ y _bytecode_ o lenguajes alternativos para la máquina virtual.

### Spring Boot in Action

{{< amazon
    linkids="https://amzn.to/42nuukc,https://amzn.to/3SKhOku"
    asins="1617292540,B01N750Z7H"
    titles="Spring Boot in Action,Mastering Spring 5.0" >}}

Spring Boot permite que una aplicación sea completamente autocontenida incluyendo un servidor web como [Tomcat][tomcat] entre otros disponibles. En vez de distribuir la aplicación en formato _war_ para ser desplegada en un contenedor de _servlets_ o aplicaciones la aplicación se distribuye en formato _jar_ y se inicia como una aplicación Java tradicional. Es la aplicación la que inicia el servidor web embebido y hace que proporcione su servicio. Esta es nueva tendencia que se sigue en las aplicaciones y en las aplicaciones basadas en la nube.

Spring Boot proporciona una integración con la parte del ecosistema de [Spring][spring] que requiera la aplicación. Para nuevos proyectos [Spring Boot in Action](https://amzn.to/42nuukc) es muy interesante.

* [Aplicación Java autocontenida con Spring Boot][blogbitix-103]
* [Información y métricas de la aplicación con Spring Boot Actuator][blogbitix-113]
* [Ejemplo de API REST en Java con JAX-RS y Spring Boot][blogbitix-178]

### Gradle in Action

{{< amazon
    linkids="https://amzn.to/3Ur9o2t"
    asins="1617291307"
    titles="Gradle in Action" >}}

[Gradle][gradle] es una herramienta de construcción de proyectos y gestión de dependencias que adopta las mejores características de [Ant][ant] y [Maven][maven] con pocas o ninguna de sus deficiencias. [Gradle in Action](https://amzn.to/3Ur9o2t) explica en profundidad esta herramienta de construcción para llegar a dominarla.

* [Herramienta de construcción Gradle][elblogdepicodev-98]
* [Usar Gradle mediante Gradle wrapper][elblogdepicodev-100]
* [Ejemplo de multiproyecto con Gradle][blogbitix-96]
* [Incluir información de la versión en el artefacto distribuible con Gradle][blogbitix-145]
* [Iniciar rápido un proyecto de Java con Gradle o de Spring con Spring Initializr][blogbitix-245]

### Java Testing with Spock

{{< amazon
    linkids="https://amzn.to/3UmsQgZ"
    asins="1617292532"
    titles="Java Testing with Spock" >}}

Las pruebas automatizadas son indispensables para escribir código con menos errores y con mayor calidad. En Java hay varias herramientas de _testing_ para hacer pruebas unitarias, de integración o funcionales. [Spock][spock] con el que se escriben especificaciones con un DSL y utilizando el lenguaje [Groovy][groovy] permite automatizar las indispensables pruebas de todo código. [Java Testing with Spock](https://amzn.to/3UmsQgZ) explica esta herramienta.

### Portlets in Action

{{< amazon
    linkids="https://amzn.to/3Ssdchu"
    asins="1935182544"
    titles="Portlets in Action" >}}

Los portales son utilizados por algunas organizaciones para gestionar el contenido que publican en su web. [Portlets in Action](https://amzn.to/3Ssdchu) explica como crear las unidades básicas de los portales en Java, los _portlets_.

* [Introducción a los portales y ejemplo de portlet con Liferay][blogbitix-243]
* [Portlets con el framework Apache Tapestry y Apache Pluto][blogbitix-244]

## Bases de datos relacionales y SQL

### SQL Antipatterns

{{< amazon
    linkids="https://amzn.to/3OsRad3"
    asins="1934356557"
    titles="SQL Antipatterns: Avoiding the Pitfalls of Database Programming" >}}

Aún con la popularización de las bases de datos NoSQL las bases de datos relacionales siguen siendo perfectamente válidas. El libro [SQL Antipatterns](https://amzn.to/3OsRad3) es una colección de consejos a tener en cuenta al diseñar y utilizar las bases de datos relacionales. Algunas formas de uso que generan problemas están desaconsejados y se consideran antipatrones.

### Beginning SQL Queries y Learning PostgreSQL

{{< amazon
    linkids="https://amzn.to/3OoWoa0,https://amzn.to/3Otdh36,https://amzn.to/49oqdQ5"
    asins="1484219546,1800567499,1783989181"
    titles="Beginning SQL Queries: From Novice to Professional,Mastering PostgreSQL 15: Advanced techniques to build and manage scalable reliable and fault-tolerant database applications,Learning Postgresql" >}}

El lenguaje declarativo SQL es muy potente y yo al menos desconocía o he usado poco muchas cosas que se han añadido al lenguaje en versiones más modernas y que algunas bases de datos como [PostgreSQL][postgresql] implementan. Algunas de estas cosas son las _windows functions_, tipos definidos por el usuario, _triggers_, _common table expression_ o CTE, consultas recursivas, búsqueda de texto más avanzado y eficiente que la expresión _like_, _lateral subqueries_, ...

Los libros [Beginning SQL Queries](https://amzn.to/3OoWoa0), [SQL Antipatterns](https://amzn.to/3OsRad3) y [Learning PostgreSQL](http://amzn.to/49oqdQ5) son una buena documentación sobre el lenguaje SQL que van más allá de lo enseñado en algunos cursos académicos.

### MongoDB in Action

{{< amazon
    linkids="https://amzn.to/3Snd5nq"
    asins="1617291609"
    titles="MongoDB in Action" >}}

La misión de las bases de datos es almacenar información para una posterior consulta o búsqueda. [MongoDB in Action](https://amzn.to/3Snd5nq) trata sobre la base de datos orientada a documentos [MongoDB][mongodb] catalogada como NoSQL.

* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-237]
* [Usar la base de datos NoSQL MongoDB con Java][blogbitix-239]

## Programación web

### JavaScript The Definitive Guide

{{< amazon
    linkids="https://amzn.to/480or6C"
    asins="1491952024"
    titles="JavaScript The Definitive Guide: Master the World's Most-Used Programming Language" >}}

[JavaScript The Definitive Guide](https://amzn.to/480or6C) explica este lenguaje de programación utilizado en los navegadores web. Denostado a veces por falta de conocimiento el libro explica muchas cosas interesantes del lenguaje e incorporadas la versión de ECMAScript 6. Es un libro que no requiere gran conocimiento previo y empieza desde un nivel básico.

* [Introducción al JavaScript de ECMAScript 6][blogbitix-198]

### The Book of CSS3

{{< amazon
    linkids="https://amzn.to/4bnZAMF"
    asins="1593275803"
    titles="The Book of CSS3: A Developer's Guide to the Future of Web Design" >}}

Al igual que JavaScript el denostado CSS también a veces lo es por el mismo motivo. [The Book of CSS3](https://amzn.to/4bnZAMF) explica las nuevas posibilidades para aplicar estilos a documentos HTML en los navegadores. _Media queries_, selectores, pseudo-clases y pseudo-elementos, _web fonts_, efectos y estilos de tipografías, disposiciones en múltiples columnas, imágenes de fondo, bordes y efectos de caja, color y opacidad, gradientes transformaciones 3D, animaciones y transiciones, _layouts_, modos _blend_, filtros y máscaras es el contenido del libro.

### CORS in Action

{{< amazon
    linkids="https://amzn.to/4bqukg3"
    asins="161729182X"
    titles="CORS in Action: Creating and Consuming Cross-Origin APIs" >}}

_Cross-Origin Resource Sharing_ permite a sitios distintos del origen de la página proporcionar su contenido. Por defecto, los navegadores solo permiten hacer peticiones al mismo sitio del origen de la página, con una combinación del navegador y cabeceras en las peticiones que proporcionan los sitios web permiten hacer llamadas entre diferentes sitios manteniendo la seguridad. El libro [CORS in Action](https://amzn.to/4bqukg3) trata en detalle este tema.

### Learning HTTP/2

{{< amazon
    linkids="https://amzn.to/3HPi4Ik"
    asins="1491962445"
    titles="Learning HTTP/2: A Practical Guide for Beginners" >}}

La siguiente versión del protocolo HTTP mejora varios aspectos del anterior. Es más eficiente, con menos latencia, con mejor rendimiento mas seguro al requerirse una conexión cifrada y haciendo innecesarios varias técnicas que se usaban anteriormente. [Learning HTTP/2](https://amzn.to/3HPi4Ik) es un buen material para conocer las diferencias y los problemas que presentaba HTTP/1.

* [Introducción al protocolo HTTP/2][blogbitix-127]

### OAuth 2 in Action y Mastering OAuth 2.0

{{< amazon
    linkids="https://amzn.to/3HPhZo0,https://amzn.to/42qGxgP"
    asins="161729327X,1784395404"
    titles="OAuth 2 in Action,Mastering Oauth 2.0" >}}

[OAuth][oauth] es un protocolo para proporcionar autenticación y autorización a un sitio o aplicación sin que el usuario le proporcione las credenciales. Una aplicación que requiera acceso aun tercero necesita obtener un _bearer token_ que mediante un proceso de autorización se puede obtener de cuatro formas diferentes dependiendo de la aplicación y de si el dispositivo autorizado es de confianza o no. [OAuth 2 in Action](https://amzn.to/3HPhZo0) y [Mastering OAuth 2.0](https://amzn.to/42qGxgP) es una buena introducción.

* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]
* [Integrar autenticación OAuth con Keycloak, Shiro, Apache Tapestry y Spring Boot][blogbitix-185]

## Servicios

### Learning RabbitMQ y Mastering RabbitMQ

{{< amazon
    linkids="https://amzn.to/3w2dzb0,https://amzn.to/3SJpQda"
    asins="1783984562,1783981520"
    titles="Learning RabbitMQ,Mastering RabbitMQ" >}}

Los libros [Learning RabbitMQ](https://amzn.to/3w2dzb0) y [Mastering RabbitMQ](https://amzn.to/3SJpQda) tratan sobre la herramienta [RabbitMQ][rabbitmq] para desarrollar aplicaciones que se comuniquen mediante mensajes. Los mensajes son una forma efectiva de desacoplar dos aplicaciones tanto temporalmente como en el lenguaje utilizado en cada una de ellas.

* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]

### Building Microservices

{{< amazon
    linkids="https://amzn.to/3HLtmh6"
    asins="1492034029"
    titles="Building Microservices: Designing Fine-Grained Systems" >}}

El ámbito _cloud_ y una nueva forma de arquitectura para las aplicaciones han originado los denominados microservicios. En vez de desarrollar una aplicación monolítica y grande se tiende hacia múltiples aplicaciones más pequeñas con un contexto específico que colaboran para proporcionar la misma funcionalidad. El libro [Building Microservices](https://amzn.to/3HLtmh6) trata las motivaciones, que valor aportan los microservicios así como sus principios y cultura.

## Libros sobre Linux y DevOps

### Git: Mastering Version Control

{{< amazon
    linkids="https://amzn.to/4bgZYg9"
    asins="B01MXIFY5E"
    titles="Git: Mastering Version Control" >}}

He leído varios libros sobre [Git][git], muchos empiezan explicando como es la estructura de información que guarda Git, no es necesario empezar por ahí. El libro [Git: Mastering Version Control](https://amzn.to/4bgZYg9) es mucho más pragmático y explica muy bien los casos de uso que uno requiere de este sistema de control de versiones. Con este libro finalmente he aprendido mejor como usar un _rebase_ interactivo para reescribir los _commits_ o hacer un _squash_, también como crear _alias_ y _hooks_, los diferentes modos de _reset_ además de como crear _commits_, hacer búsquedas en el repositorio y otras muchas cosas que no he usado aún.

### Mastering GitLab 12

{{< amazon
    linkids="https://amzn.to/49bIfFa"
    asins="1789531284"
    titles="Mastering GitLab 12" >}}

Como servidor de Git me gustan de [GitLab][gitlab] dos cosas: que tiene una versión para la comunidad usable por ejemplo en un servidor propio de una empresa u organización y que tiene varias herramientas integradas como son una wiki, un sistema de _tracking_ para las tareas de un proyecto junto con tableros y soporte para hacer integración y entrega continua sin necesidad de más herramientas lo que simplifica la infraestructura para el desarrollo en gran medida. También soporta otras características presentes en GitHub como _merge request_, revisiones entre pares o calidad de código entre otras muchas otras, la [lista completa de _features_](https://about.gitlab.com/features/) es grande. [Mastering GitLab 12](https://amzn.to/49bIfFa) es un libro introductorio para empezar a manejarlo.

### Docker in Action y The DevOps 2.1 Toolkit

{{< amazon
    linkids="https://amzn.to/489Zoy4,https://amzn.to/3w1RGIK"
    asins="1617294764,1542468914"
    titles="Docker in Action,The DevOps 2.1 Toolkit: Docker Swarm: building testing deploying and monitoring services inside Docker Swarm clusters" >}}

[Docker][docker] y los contenedores junto con la computación en la nube han cambiado la forma de distribuir y desplegar las aplicaciones en los servidores. Docker es una gran herramienta muy interesante de conocer. Son varias las herramientas del ecosistema, [Docker in Action](https://amzn.to/489Zoy4) explica _docker_ y [_docker compose_][docker-compose] y [The DevOps 2.1 Toolkit](https://amzn.to/3w1RGIK) explica [_docker swarm_][docker-swarm] para crear _clusters_ de servidores.

* [Serie de artículos sobre Docker][blogbitix-serie-docker]

### Ansible Up and Running y Implementing DevOps with Ansible 2

{{< amazon
    linkids="https://amzn.to/3UxAaWS,https://amzn.to/4bkS6dw"
    asins="1491979801,1787120538"
    titles="Ansible Up and Running,Implementing DevOps with Ansible 2" >}}

[Ansible][ansible] es una herramienta para automatizar tareas en una o múltiples máquinas locales o remotas. Otra de sus propiedades en la mayoría de los casos es que es idempotente, es decir, se pueden ejecutar las acciones múltiples veces aplicando los cambios pertinentes o no haciendo nada según el estado de la máquina y el estado deseado.

La herramienta es muy útil para tratar a los servidores siendo numerosos como ganado en vez de como mascotas y los libros [Ansible Up and Running](https://amzn.to/3UxAaWS) y [Implementing DevOps with Ansible 2](https://amzn.to/4bkS6dw) lo explican.

* [Introducción a Ansible][blogbitix-52]

### UNIX and Linux System Administration Handbook, Linux Administration A Beginner’s Guide y How Linux Works

{{< amazon
    linkids="https://amzn.to/42pnMu8,https://amzn.to/3Sr0AXP,https://amzn.to/3uhTdtQ"
    asins="0134277554,0071845364,1593275676"
    titles="UNIX and Linux System Administration Handbook,Linux Administration: A Beginner’s Guide,How Linux Works: What Every Superuser Should Know" >}}

[UNIX and Linux System Administration Handbook](https://amzn.to/42pnMu8) es un libro que presenta al usuario los sistemas GNU/Linux, [Linux Administration A Beginner’s Guide](https://amzn.to/3Sr0AXP) detalla las tareas administrativas para un servidor Linux y [How Linux Works](https://amzn.to/3uhTdtQ) es un libro que explica detalles más profundos de aspectos internos de Linux.

### Amazon Web Services in Action y Amazon Web Services For Dummies

{{< amazon
    linkids="https://amzn.to/49hNYJt"
    asins="1617295116"
    titles="Amazon Web Services in Action" >}}

Aún habiendo leído [Amazon Web Services in Action](https://amzn.to/49hNYJt) como no he tenido la oportunidad de practicar con la nube de Amazon casi todo de lo que leí lo tengo olvidado. [Amazon ofrece una capa gratuita](https://aws.amazon.com/es/free/) para probarlo.

## Publicaciones más breves

Esto ya no son libros sino documentos más pequeños sobre diferentes temas.

### Guide to the Java Ecosystem 2015

Aun es la [edición del 2015](https://dzone.com/guides/the-java-ecosystem-2015-edition) y no he visto que hayan publicado una más reciente. Explica las tendencias y el ecosistema de Java. Microservicios, _docker_, Java 8, programación _reactiva_, mejores prácticas, ...

### Java The Legend

¿A que es debido el éxito de Java? El libro [Java The Legend](http://www.oreilly.com/programming/free/java-the-legend.csp) expone varios motivos como importancia de la compatibilidad hacia atrás, fácil de aprender y de leer, un sistema de tipos simple o la máquina virtual de Java o JVM. Aunque también según el documento con algunos fracasos como los _Java Bean_ por promover la mutabilidad que es molesta para la concurrencia, la finalización de los objetos, sobre configuración de Java EE o la abstracción muy básica de hilos. Comenta el ecosistema alrededor de la plataforma Java con [Eclipse][eclipse], [Apache][apache] y [Spring][spring] junto con el futuro con Java 9, HTTP/2, JShell y quizá en un futuro el proyecto Panama para una nueva interfaz alternativa a JNI para código nativo y el proyecto Valhalla que trata sobre los tipos para usar cosas como definir colecciones genéricas con tipos primitivos.

### The daemon, the GNU and the penguin

{{< amazon
    linkids="https://amzn.to/3SlCMVn"
    asins="097903423X"
    titles="The Daemon the Gnu and the Penguin" >}}

[The daemon, the GNU and the penguin](https://amzn.to/3SlCMVn) es un documento que repasa el origen, inicios y evolución del software libre y de código abierto desde el año 1968 con referencias a Unix, BSD, Minix, Sun, GPL, Hurd, la web, Tanenbaum, Torvalds, Linux, ... Este documento y el siguiente son unos clásicos en internet.

### La catedral y el bazar

La catedral y el bazar hace referencia a dos modelos distintos de desarrollo uno centralizado y controlado por una organización y otro distribuido de más libre albedrío pero también se ha demostrado muy efectivo posibilitado por la democratización de internet. [La catedral y el bazar](http://catb.org/esr/writings/cathedral-bazaar/) está traducido a varios idiomas entre ellos el [español](http://biblioweb.sindominio.net/telematica/catedral.html).

Para acabar este artículo, viendo la cantidad de libros que he leído en unos 3 o 4 años con el tiempo que supone dedicado a adquirir estos conocimientos en gran parte en el tiempo libre y que en general empleamos los que trabajamos en el sector tecnológico hay que hacerse valer a la hora de recibir o postular a una oferta de trabajo y me refiero al ámbito económico que en muchas ofertas de [InfoJobs][infojobs] o [Tecnoempleo][tecnoempleo] no se corresponde con lo que supone los conocimientos que se solicitan.

{{% /post %}}
