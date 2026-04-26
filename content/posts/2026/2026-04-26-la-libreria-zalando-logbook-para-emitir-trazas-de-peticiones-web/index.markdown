---
pid: 728
type: "post"
title: "La librería Zalando Logbook para emitir trazas de peticiones web"
url: "/2026/04/la-libreria-zalando-logbook-para-emitir-trazas-de-peticiones-web/"
date: 2026-04-26T11:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java"]
summary: "Las trazas en llamadas REST son fundamentales para la observabilidad en microservicios, ya que la red es un medio poco fiable con múltiples puntos de fallo. Sin un service mesh, cada servicio debe instrumentar sus propias llamadas HTTP, lo que con soluciones manuales resulta limitado y difícil de mantener. Zalando Logbook resuelve esto ofreciendo soporte para las librerías HTTP más populares de Java y Spring Boot, con funcionalidades avanzadas como correlación petición/respuesta, filtrado y ofuscación de datos sensibles. El artículo complementa el ejemplo con una configuración de Log4j2 que distingue entre entornos locales con salida en consola y entornos productivos con formato JSON estructurado para GCP."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las trazas o logs son uno de los métodos para tener observabilidad en un servicio. En los microservicios que usa REST que utilizan un método poco fiable como la red o aún no fallando es posible obtener una respuesta indicando algún tipo de error como validación de datos o error interno del servidor.

Añadir observabilidad en las llamadas REST es una forma de observar si los servicios están funcionando de la forma esperada o si hay errores que manifiestan algún tipo de comportamiento anómalo.

La librería Zalando Logbook permite emitir trazas en las llamadas recibidas o realizadas y las respuestas devueltas o que se obtienen. Tiene soporte para las librerías que permiten hacer peticiones http más populares y una buena cantidad de opciones de configuración para adaptarla a las necesidades del servicio.

{{< tableofcontents >}}

## Llamadas remotas

La red está compuesta por múltiples elementos (balanceadores, proxies, firewalls, DNS) cada uno de ellos un potencial punto de fallo. Pueden fallar de formas muy diversas: timeouts, pérdida de paquetes, reinicios abruptos o configuraciones incorrectas que interrumpen el servicio de forma temporal pero suficiente para causar errores en cascada.

En arquitecturas de microservicios, una solución es adoptar un _service mesh_ como [Istio][istio], [Linkerd][linkerd] o [Consul][consul], que traslada las responsabilidades de monitorización, trazabilidad y resiliencia a la infraestructura, evitando duplicar esa lógica en cada servicio. Sin embargo, cuando no se dispone de un service mesh, instrumentar los clientes HTTP con logs es la alternativa más directa para ganar visibilidad sobre esos puntos de fallo.

## La solución simple

La forma de añadir logs en las llamadas HTTP depende de la librería utilizada: HttpClient de Java, WebClient de Spring o [OkHttp][okhttp]. En cada caso, es necesario instrumentar el cliente implementando un interceptor o listener que emita los mensajes de log. Esta responsabilidad recae completamente en el desarrollador, lo que implica más código a mantener y un resultado con capacidades limitadas.

A continuación se muestra un ejemplo básico con OkHttp, válido para casos simples. Para necesidades más avanzadas, lo recomendable es utilizar una librería especializada como Zalando Logbook.

Ejemplo código java simple

## La librería Zalando Logbook

[Zalando Logbook][zalando-logbook] es una librería Java diseñada específicamente para registrar trazas de las peticiones y respuestas HTTP, tanto entrantes como salientes. Sus principales características son:

* Soporte multi-librería: compatible con OkHttp, HttpClient, WebClient, RestTemplate, Feign y otras.
* Correlación petición/respuesta: asocia cada petición con su respuesta correspondiente en los logs.
* Filtrado condicional: permite registrar solo las llamadas que cumplan determinados criterios.
* Ofuscación de datos sensibles: enmascara cabeceras o campos del cuerpo como _tokens_, contraseñas o datos personales.
* Formatos de salida configurables: JSON estructurado, texto plano u otros formatos personalizados.
* Integración nativa con Spring Boot: configuración mediante application.yml sin necesidad de código adicional.

Otra ventaja relevante es el desacoplamiento, si en algún momento se decide cambiar de librería HTTP o de formato de log, basta con ajustar la configuración de Logbook sin tocar la lógica de negocio o código de la aplicación.

{{< code file="CustomHttpLogFormatter.java" language="java" options="" >}}

Esta es la configuración necesaria para proporcionar el _interceptor_.

{{< code file="Beans.java" language="java" options="" >}}

Y el código donde se emite la traza.

{{< code file="Controller.java" language="java" options="" >}}

El resultado.

{{< code file="output.log" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/ZalandoLogbook" command="./gradlew run" %}}

{{% /post %}}

