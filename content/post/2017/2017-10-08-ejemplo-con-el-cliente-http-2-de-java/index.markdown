---
pid: 268
title: "Ejemplo con el cliente HTTP/2 de Java"
url: "/2017/10/ejemplo-con-el-cliente-http-2-de-java/"
date: 2017-10-08T12:00:00+02:00
updated: 2019-09-22T14:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Otra de las [nuevas funcionalidades incluidas en Java 9][blogbitix-264] aunque en modo incubación e incorporado en Java 11 de forma estable es el cliente con soporte para HTTP/2 para realizar peticiones a recursos usando este protocolo más eficiente y rápido. Al mismo tiempo se ha simplificado el código necesario para realizar una petición y obtener el resultado de una URL. También se ha añadido la funcionalidad de realizar peticiones asíncronas y creación de conexiones de _WebSockets_.

Las clases importantes de esta nueva API con [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html), [HttpRequest](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html) y [HttpResponse](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpResponse.html). Estas clases se encuentran en el módulo de incubación [jdk.incubator.httpclient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/package-summary.html), una vez que sea definitiva la API se renombrará el módulo.

El siguiente ejemplo realiza una petición a la página del buscador Google con unas cabeceras y obtiene el código de estado, las cabeceras devueltas y el cuerpo de la página de resultado. En la declaración del módulo para usar el cliente hay que indicar que tiene como requerimiento su módulo de _java.net.http_.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="module-info.java" language="java" options="" >}}

{{< figureproc
    image1="java-http2.png" thumb1="java-http2-thumb.png" options1="2560x1440" optionsthumb1="450x400" title1="Petición con el cliente de Java para HTTP/2"
    caption="Petición con el cliente de Java para HTTP/2" >}}

Otras fomas de manejadores del resultado de la petición son los siguientes:

* [BodyHandlers.ofString()](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpResponse.BodyHandlers.html#ofString()): almacena el resultado de la petición en un String
* [BodyHandlers.ofByteArray](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpResponse.BodyHandlers.html#ofByteArray()): almacena el resultado de la petición en un array de bytes como sería el caso de obtener una imagen o un archivo PDF
* [BodyHandlers.ofFile(Path)](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpResponse.BodyHandlers.html#ofFile(java.nio.file.Path)): almacena el resultado en un archivo del disco
* [BodyHandlers.discarding()](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpResponse.BodyHandlers.html#discarding()): descarta la respuesta y devuelve el valor indicado

Se puede definir la política de cómo procesar las redirecciones para seguirlas o no seguirlas.

* [HttpClient.Redirect.ALWAYS](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.Redirect.html#ALWAYS)
* [HttpClient.Redirect.NEVER](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.Redirect.html#NEVER)
* [HttpClient.Redirect.NORMAL](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.Redirect.html#NORMAL)

Con la clase [SSLContext](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/javax/net/ssl/SSLContext.html) es posible establecer autenticación para el cliente usando un certificado como muestro en el artículo [Autenticación mutua de cliente y servidor con certificados][blogbitix-241].

Para ejecutar el ejemplo usando [Gradle][gradle] y Java 11 hay que añadir un poco de configuración al _script_ de construcción que posiblemente en un futuro no será necesaria cuando se mejore el soporte.

{{< code file="build.gradle" language="Groovy" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/JavaHttp2" command="./gradlew run" >}}

{{% /post %}}
