---
pid: 368
title: "Exponer las métricas de Hystrix en Grafana con Prometheus de una aplicación Spring Boot"
url: "/2018/12/exponer-las-metricas-de-hystrix-en-grafana-con-prometheus-de-una-aplicación-spring-boot/"
date: 2018-12-21T11:30:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion", "software"]
series: ["spring-cloud"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" image2="spring.svg" title2="Spring" width2="200" >}}

[Hystrix][netflix-hystrix] es una implementación del patrón _circuit breaker_ para hacer que un servicio sea tolerante fallos cuando aquellos que utiliza fallan. Es conveniente tener una herramienta de monitorización para conocer el estado del sistema y actuar pronto o conocer si el comportamiento del sistema es diferente al hacer algún cambio. Hystrix proporciona varios datos como el número de peticiones realizadas, cuantas han fallado o cual es el estado del patrón _circuit breaker_. [Prometheus][prometheus] es una herramienta de monitorización que recoge las métricas de los servicios de forma periódica y las almacena para una consulta posterior, [Grafana][grafana] es otra herramienta de monitorización que permite visualizar en gráficas las métricas almacenadas en Prometheus y observar los valores a lo largo del tiempo.

En el artículo [Tolerancia a fallos en un cliente de microservicio con Spring Cloud Netflix y Hystrix][blogbitix-352] explicaba como crear un servicio de [Spring][spring] que implementa el patrón _circuit breaker_ con Hystrix y en el artículo [Monitorizar una aplicación Java con Spring Boot, Micrometer, Prometheus y Grafana][blogbitix-366] explicaba como exportar las métricas de [Spring Boot Actuator][spring-boot-actuator] a Prometheus y como crear gráficas en Grafana.

Hystrix ofrece un _dashboard_ algo espartano con los datos de Hystrix de la propia aplicación. Los datos de las métricas de Hystrix por defecto no se exponen en Spring Boot Actuator pero se pueden añadir creando un _bean_ _HystrixMetricsBinder_ en la configuración de Spring.

{{< code file="Main.java" language="Java" options="" >}}
{{< code file="build.gradle" language="Groovy" options="" >}}

Una vez hecho esto Spring en el _endpoint_ _/actuator/metrics_ se exponen las métricas de Hystrix, si además se configura Spring añadiendo la dependencia _io.micrometer:micrometer-registry-prometheus_ para exponer las métricas en el formato para que Prometheus las recolecta también se añaden en el _endpoint_ _/actuator/prometheus_.

{{< code file="metrics-endpoints.sh" language="Bash" options="" >}}
{{< code file="actuator-metrics.json" language="JSON" options="" >}}
{{< code file="actuator-prometheus.prometheus" language="Plaintext" options="" >}}

Con estas métricas recolectadas por Prometheus se pueden visualizar en gráficas por Grafana. Hay algunos [paneles de Grafana para Hystrix](https://grafana.com/dashboards?search=hystrix) como el [7145](https://grafana.com/dashboards/7145) pero que necesitan ser adaptados según la nomenclatura de las propiedades expuestas por Spring Boot. En este caso se monitoriza el número de peticiones realizadas, el tiempo de latencia, si los circuitos están abiertos, los fallos, éxitos y _tiemouts_ así como el estado de los _thread pools_ que utiliza Hystrix para realizar las peticiones de un cliente a un servicio.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="grafana-hystrix-dashboard.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="600x450" title1="Panel de Grafana pàra métricas de Hystrix"
        caption="Panel de Grafana para métricas de Hystrix" >}}
</div>

Exponer las métricas en una aplicación de Spring Boot para Prometheus es muy sencillo y con Grafana se puede observar el estado del sistema de forma tan detallada como lo sean las métricas expuestas por la aplicación. Por defecto Spring Boot ya expone una buena cantidad de métricas del estado del servicio como uso de CPU, memoria, hilos o recolector de basura.

{{< code file="gradle-run.sh" language="Bash" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/SpringCloud" command="./gradle-run.sh" >}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Expose Hystrix metrics to Actuator /metrics (and /prometheus)](https://github.com/spring-cloud/spring-cloud-netflix/issues/3004)
{{% /reference %}}

{{% /post %}}
