---
pid: 349
title: "Recargar la configuración sin reiniciar una aplicación Spring Boot con Spring Cloud Config"
url: "/2018/09/recargar-la-configuracion-sin-reiniciar-una-aplicacion-spring-boot-con-spring-cloud-config/"
date: 2018-09-23T00:15:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
series: ["spring-cloud"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="spring.svg" title1="Spring" width1="200" image2="java.svg" title2="Java" width2="200" >}}

No es raro la necesidad de querer cambiar algunos valores de la configuración de una aplicación sin ningún cambio adicional en el código. Normalmente la configuración se externaliza en un archivo de texto en un determinado formato como _properties_ o _yaml_ que se lee al iniciarse la aplicación pero que al querer hacer cambios y hacerlos efectivos requiere reiniciar la aplicación.

Para evitar la caída de servicio en un reinicio de aplicación requiere tener varias instancias de la aplicación, ir sacando del balanceador las instancias para que no le soliciten nuevas peticiones, reiniciarlas y añadirlas de nuevo al balanceador si se hace blalanceo de carga en el servidor o reiniciar las aplicaciones progresivamente si se hace en el cliente. Y esto con todas las instancias del servicio. [Spring Cloud Config][spring-cloud-config] y [Spring Boot][spring-boot] entre sus funcionalidades de configuración posee una que consiste en recargar la configuración o ciertas partes de la misma. Para ello [Spring Boot Actuator][spring-boot-actuator] ofrece un _endpoint_ con el que disparar la recarga.

En el siguiente ejemplo de microservicio que posee una clase de configuración con algunas propiedades. El valor de estas propiedades se utilizan para el resultado de una acción en un _endpoint_ del servicio.


{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "DefaultConfiguration.java" >}}
{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "DefaultController.java" >}}
{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "service.yml" >}}

Iniciada la aplicación que requiere iniciar previamente el servicio de registro y descubrimiento y el servidor de configuración, la aplicación al iniciarse obtiene su configuración del servidor de configuración. Si se cambia la configuración de la variable _config.key_ la aplicación no obtendrá el valor actualizado hasta que se invoque el _endpoint_ _http\://localhost:8080/actuator/refresh_. Para que Spring Boot recargue la configuración es necesario anotar con _@RefreshScope_ la clase de configuración. Invocado el _endpoint_ de recarga de configuración la aplicación toma de nuevo los nuevos valores del servicio de configuración.

{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "gradle-run-1.sh" >}}
{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "curl-1.sh" >}}

En una aplicación orientada microservicios es muy posible que haya múltiples instancias del mismo servicio y para recargar la configuración de cada uno de ellos hay que hacerlo de forma individual con su _endpoint_ de recarga de configuración. Dado el número de microservicios y su ubicación distribuida hacerlo de forma individual es un inconveniente.

Para resolver este inconveniente integrando [Spring Cloud Bus][spring-cloud-bus] en las aplicaciones es posible recargar la configuración de todos los microservicios haciendo una única llamada al _endpoint_ _http\://localhost:8090/monitor_ indicando el servicio a actualizar su configuración lo que es indenpendiente del número de instancias y de su ubicación. Integrar Spring Clud Bus requiere disponer de una instancia de mensajes como [RabbitMQ][rabbitmq] e incluir como dependencia tanto en el servidor de configuración como en el servicio la dependencia _spring-cloud-starter-bus-amqp_. Para esta comunicación de mensajes Spring Cloud Config crea en RabbitMQ una cola de mensajes que empieza por _springCloudBus_. 

{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "configserver.gradle" >}}
{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "service.gradle" >}}

Los pasos para probar estas funcionalidades con Spring Cloud Bus en una o varias varias instancias son iniciar una instancia o más del servidor de registro y descubrimiento, iniciar una o más instancias del servidor de configuración, iniciar una o varias instancias del servicio todas las instancias en un puerto y terminal diferente, invocar el servicio cuyo valor de respuesta depende de una propiedad de configuración, modificar el valor de la propiedad de configuración, recargar la configuración e invocar de nuevo el servicio para comprobar que el nuevo valor se ha hecho efectivo.

{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "gradle-run-2.sh" >}}
{{< gist picodotdev c8304298f6f6a0aa64b69b25ad2f886e "curl-2.sh" >}}

{{% code git="blog-ejemplos/tree/master/SpringCloud" command="./gradle-run-1.sh && ./curl-1.sh" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Spring Cloud Bus - Quick Start](http://cloud.spring.io/spring-cloud-static/spring-cloud-bus/2.0.0.RELEASE/single/spring-cloud-bus.html#_quick_start)
* [Spring CloudBus - Push Notifications and Spring Cloud Bus](http://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.0.1.RELEASE/single/spring-cloud-config.html#_push_notifications_and_spring_cloud_bus)
* [Spring Boot - Configuration Classes](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-configuration-classes.html)
{{% /reference %}}

{{% /post %}}
