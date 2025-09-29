---
pid: 643
type: "post"
title: "Interfaz web para JMX con Hawtio"
url: "/2022/07/interfaz-web-para-jmx-con-hawtio/"
date: 2022-07-14T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:hawtio-console.webp"
tags: ["java", "planeta-codigo"]
summary: "Una cosa es la funcionalidad que proporciona una aplicación y otra las tareas de administración y mantenimiento relacionas con la aplicación. Estas tareas de administración y mantenimiento no son tareas destinadas a los usuarios sino destinadas a los administradores de la aplicación. Estas tareas pueden ser manuales y ejecutadas a conveniencia siendo muy útil poder ejecutarlas sin necesidad de realizar cambios en el código ni un despliegue de la aplicación. La tecnología JMX de Java define una arquitectura para administrar y monitorizar aplicaciones que se puede utilizar para estas tareas administrativas, Hawtio es una interfaz que permite el acceso y ejecución a JMX mediante un navegador y proporciona una librería para integrase con Spring Boot."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las computadoras y las aplicaciones realizan sus funciones de forma rápida y determinista ofreciendo su servicio a sus usuarios. El software generalmente se diseña para el caso en el que todo funcione correctamente pero también ha de estar preparado para los casos en los que se produzca un error ya sea simplemente emitiendo una traza o autocorrigiendo el problema con un reintento.

Especialmente con el advenimiento de los microservicios que se basan en la comunicación por red las aplicaciones han de estar preparadas para los errores. Si un proceso en una computadora es muy fiable salvo por fallo del hardware las redes son mucho menos fiables más cuando hay comunicación entre varios servicios dado que la conectividad de red se puede perder o un servicio puede fallar repentinamente por un fallo de software o por un fallo de hardware.

Para reparar los efectos de estos errores o simplemente para ejecutar de forma explícita ciertos procesos administrativos independientemente de si es a raíz de un error o no. Las aplicaciones puden incorporar estas adicionalmente estas funcionalidades administrativas y proporcionar una forma de invocarlas, de forma sencilla, rápida y evitando en la medida posible errores manuales.

{{< tableofcontents >}}

## La funcionalidad principal y las tareas administrativas

Seguramente la funcionalidad principal que proporciona una aplicación no requiera ninguna acción, mantenimiento ni acciones manuales. La aplicación mientras esté en funcionamiento proporcionará su servicio ya sea una aplicación web que sirva contenido para un navegador web o una API basada en REST que proporcione datos y reciba peticiones de otras aplicaciones.

Otras funcionalidades relacionadas con la aplicación se desean ejecutar por ejemplo para corregir algún error puntual en la aplicación, para obtener algún dato o simplemente para realizar una acción que se desea iniciar de forma manual. Las tareas administrativas pueden ser parte una parte de la funcionalidad que es necesario ejecutar a conveniencia en momentos determinados.

Hay que poder acceder a ellas de alguna forma, idealmente mejor sin desplegar una nueva versión por los riesgos de un despliegue sobre todo si los procesos y aseguramiento de calidad no permiten hacer despliegues con confianza o los despliegues requieren demasiado tiempo o suponen una interrupción del servicio. Que esté la funcionalidad disponible e invocarla en el momento que se necesite hace más sencilla la necesidad.

## La tecnología JMX de Java

La tecnología [JMX][java-jmx] es un estándar de Java que define una arquitectura para administrar y monitorizar aplicaciones y servicios Java. Desde el punto de vista del desarrollador JMX requiere programar unas clases de Java siguiendo las convenciones del estándar denominadas MBean. Las instancias de estas clases son registradas y administradas por el contenedor de JMX donde quedan disponibles para uso.

En el artículo [Interfaz de monitorización e instrumentalización con JMX en aplicaciones Java][blogbitix-441] comentaba cómo crear una clase MBean con unos métodos para realizar acciones y otro método para devolver datos. El ejemplo incluía la forma de usar JMX en una aplicación Java y en una aplicación de [Spring Boot][spring-boot] con el soporte que ofrece el _framework_ para la utilización de [JMX en Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.jmx).

{{< code file="HelloMBean.java" language="java" options="" >}}
{{< code file="Hello.java" language="java" options="" >}}
{{< code file="Main.java" language="java" options="" >}}
{{< code file="application.yml" language="java" options="" >}}
{{< code file="build.gradle" language="groovy" options="" >}}

## La interfaz Hawtio

La tecnología de JMX es una buena opción como punto de entrada en la implementación de esas tareas administrativas. Sin embargo, es necesario una forma de poder invocarlas.

El proyecto [Hawtio][hawtio] proporciona una interfaz web en la que poder ver, obtener datos e invocar las operaciones ofrecidas por los MBeans registrados en el contenedor de JMX, los propios de la aplicación y muchos otros que proporciona Java y en el caso de utilizarlo Spring Boot.

Hawtio utiliza el proyecto [Jolokia][jolokia] que expone mediante una interfaz REST los MBeans del contenedor JMX, Hawtio utiliza la interfaz REST de Jolokia para invocar las operaciones. Spring Boot proporciona soporte para la implementación de JMX y Hawtio proporciona una dependencia para su integración con Spring Boot que añade en la interfaz de administración un _endpoint_ para acceder a la consola de Hawtio.

Las operaciones administrativas seguramente sean funcionalidades que realicen tareas que no deban ser accesibles para los usuarios y han de estar protegidos. Hawtio permite la integración con Keycloak para la autenticación y permite solicitar credenciales para el acceso a la consola web.

{{< image
    gallery="true"
    image1="image:hawtio-console.webp" optionsthumb1="650x450" title1="Consola de Hawtio como un actuator en aplicación de Spring Boot"
    caption="Consola de Hawtio como un actuator en aplicación de Spring Boot" >}}

## Añadir seguridad a la interfaz de Hawtio con HashiCorp Boundary

Por motivos de seguridad es deseable que la consola web de Hawtio no esté accesible mediante una conexión directa. [Bondary][boundary] es uno de los productos de [HashiCorp][hashicorp] que proporciona la funcionalidad de un bastión y un _proxy_ de conexión a servicios internos, es más seguro que una VPN ya que a diferencia de una VPN únicamente otorga acceso a los sistemas necesarios y no a toda una red interna completa.

* [Acceso simple y seguro a sistemas remotos con Boundary][blogbitix-558]

El uso de Boundary requiere una conexión a una base de datos, en el modo de desarrollo inicia una instancia de [PostgreSQL][postgresql] mediante [Docker][docker]. Una vez iniciado hay que autenticarse para obtener un _token_ que otorga las credenciales de autorización de conexión a los diferentes sistemas o _targets_ como los denomina Boundary.

Por defecto Boundary en el modo desarrollo crea una definición del _host_ local y un _target_ al puerto _22_ de la máquina local. Dado que Hawtio seguramente esté en otros puertos, en el ejemplo en el puerto 8081 es necesario crear un _target_ y añadir el _host_ para la máquina local que permita la conexión a ese puerto.

Con Boundary iniciado y el _target_ definido hay que establecer el túnel con la conexión a la máquina destino, esto crea un túnel abriendo un puerto en la máquina local que tiene como destino el _target_ que se ha utilizado para la conexión, en este ejemplo a la propia máquina local al puerto _8081_.

{{< image
    gallery="true"
    image1="image:boundary-console.webp" optionsthumb1="200x150" title1="Consola de Boundary"
    caption="Consola de Boundary" >}}
{{< image
    gallery="true"
    image1="image:boundary-target-1.webp" optionsthumb1="200x150" title1="Creación de un target en Boundary"
    image2="image:boundary-target-2.webp" optionsthumb2="200x150" title2="Creación de un target en Boundary"
    image3="image:boundary-target-3.webp" optionsthumb3="200x150" title3="Creación de un target en Boundary"
    caption="Creación de un target en Boundary" >}}

Antes de establecer el túnel de la conexión hay que autenticarse en Boudary lo que otorga un _token_ para establecer la conexión, en el siguiente comando el _token_ se guarda en una variable de entorno que comando de Buildary utiliza para la autorización al solicitar la conexión, en la conexión se indica el _target_ y Boundary proporciona el puerto del túnel y el identificativo de la sesión.

{{< code file="boundary-connect.sh" language="bash" options="" >}}

Esto proporciona un puerto en la máquina local abierto contra Boundary y este contra la máquina y el puerto destino. En este ejemplo está todo en la máquina local pero a efectos prácticos ahora es posible utilizar el puerto proporcionado por Boundary para hacer la conexión a la consola de Hawtio, en vez del puerto _8081_ la conexión a través de Boundary se realiza mediante el puerto _43923_.

{{< image
    gallery="true"
    image1="image:boundary-hawtio.webp" optionsthumb1="650x450" title1="Conexión a Hawtio a través de Boundary"
    caption="Conexión a Hawtio a través de Boundary" >}}

Además, con Boundary es posible monitorizar las conexiones y sesiones establecidas lo que proporciona medidas adicionales de seguridad y auditoría.

{{< image
    gallery="true"
    image1="image:boundary-sessions.webp" optionsthumb1="650x450" title1="Sesiones establecidas y activas establecidas a través de Boudary"
    caption="Sesiones establecidas y activas establecidas a través de Boudary" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringBootHawtio" command="./gradlew run" %}}

{{% /post %}}
