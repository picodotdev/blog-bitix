---
pid: 668
type: "post"
title: "Obtener valores de configuración antes iniciar una aplicación con Spring Boot"
url: "/2023/01/obtener-valores-de-configuracion-antes-iniciar-una-aplicacion-con-spring-boot/"
date: 2023-01-06T01:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:spring.svg"
tags: ["java", "planeta-codigo"]
summary: "El mecanismo de configuración de Spring Boot es muy flexible y por otro lado Spring Boot proporciona varias formas de extensión. En el proceso de inicialización de la aplicación Spring Boot permite recibir eventos y realizar las acciones que se deseen. Uno de estos eventos es _ApplicationEnvironmentPreparedEvent_ antes de inicializar el contexto y crear los _beans_ del contenedor de dependencias, cyando se lanza este evento es posible acceder a las propiedades de configuración."
---

{{% post %}}

{{< logotype image1="spring.svg" >}}

Los archivos de configuración de las aplicaciones permiten cambiar su comportamiento sin tener que modificar el código fuente ni tener que generar un artefacto de despliegue. Con diferentes archivos de configuración la aplicación es la misma pero su comportamiento diferente por ejemplo según el entorno en el que se despliegue, en la propia máquina de desarrollo, producción u otro entorno.

Los archivos de configuración son una colección de propiedades y valores. El formato del archivo de configuración también variable puede ser un archivo _properties_ u otros formatos como XML, un formato más legible como YAML u otro tipo de formato. En los archivos de configuración se guardan las variables y los valores y las aplicaciones cuando necesitan el valor de una propiedad lo solicitan a través del nombre de la variable.

[Spring Boot][spring-boot] proporciona un soporte para archivos de configuración muy flexible que permite obtener los valores de las propiedades de diferentes formas y con un orden de preferencia que incluye archivos de configuración externos y variables de entorno. Uno de los formatos que soporta es YAML que es legible aún con su defecto de ser propenso a errores debido a que su sintaxis se basa en una tabulación correcta.

Cuando la aplicación de Spring Boot se inicia se cargan las propiedades de configuración y posteriormente se crean los servicios del contenedor de dependencias con los valores de las propiedades de configuración, pero en necesidades muy concretas es necesario acceder al valor de las propiedades de configuración antes incluso de que la aplicación de Spring Boot empiece a crear los _beans_.

{{< tableofcontents >}}

### Obtener valores de configuración antes de la inicialización del contexto de Spring Boot

En caso de necesitar obtener valores de configuración Spring Boot proporciona la interfaz [ApplicationListener](spring-framework:org/springframework/context/ApplicationListener.html) para recibir el evento [ApplicationEnvironmentPreparedEvent](spring-boot:org/springframework/boot/context/event/ApplicationEnvironmentPreparedEvent.html) que se invoca antes de iniciar la aplicación de Spring Boot. Se recibe una referencia del evento a través de la cual es posible acceder a los valores de configuración.

{{< code file="DefaultApplicationListener.java" language="java" options="" >}}
{{< code file="application.yml" language="yaml" options="" >}}

Al iniciar la aplicación antes incluso de que aparezca el mensaje del _banner_ de Spring Boot sale la traza con el valor de la propiedad de configuración.

{{< code file="System.out" language="plain" options="" >}}

En la [documentación sobre eventos y listeners](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.spring-application.application-events-and-listeners) de Spring Boot están todos los eventos a los que una aplicación puede suscribirse junto con la descripción de cuando se lanzan. En la misma documentación se especifica que como alguno de los eventos se lanzan incluso antes de iniciarse el contexto de Spring Boot los _listeners_ no pueden definirse como _beans_ del contenedor de dependencias.

Para registrar los listeners hay que usar el método [SpringApplication.addListeners](spring-boot:org/springframework/boot/SpringApplication.html#addListeners(org.springframework.context.ApplicationListener...)) al crear y arrancar la aplicación de Spring Boot.

{{< code file="Main.java" language="java" options="" >}}

{{% /post %}}