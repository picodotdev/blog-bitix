---
pid: 268
title: "Ejemplo con el cliente HTTP/2 de Java"
url: "/2017/10/ejemplo-con-el-cliente-http-2-de-java/"
date: 2017-10-08T12:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Otra de las [nuevas funcionalidades incluidas en Java 9][blogbitix-264] aunque en modo incubación es el cliente con soporte para HTTP/2 para realizar peticiones a recursos usando este protocolo más eficiente y rápido. Al mismo tiempo se ha simplificado el código necesario para realizar una petición y obtener el resultado de una URL. También se ha añadido la funcionalidad de realizar peticiones asíncronas y creación de conexiones de _WebSockets_.

Las clases importantes de esta nueva API con [HttpClient](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpClient.html), [HttpRequest](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpRequest.html) y [HttpResponse](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpResponse.html). Estas clases se encuentran en el módulo de incubación [jdk.incubator.httpclient](http://docs.oracle.com/javase/9/docs/api/jdk.incubator.httpclient-summary.html), una vez que sea definitiva la API se renombrará el módulo.

El siguiente ejemplo realiza una petición a la página del buscador Google con unas cabeceras y obtiene el código de estado, las cabeceras devueltas y el cuerpo de la página de resultado. En la declaración del módulo para usar el cliente hay que indicar que tiene como requerimiento su módulo de _jdk.incubator.httpclient_.

{{< gist picodotdev c3f0bea0d89da4ec3a81235e479d069b "Main.java" >}}
{{< gist picodotdev c3f0bea0d89da4ec3a81235e479d069b "module-info.java" >}}

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="268"
        image1="java-http2.png" thumb1="java-http2-thumb.png" title1="Petición con el cliente de Java para HTTP/2"
        caption="Petición con el cliente de Java para HTTP/2" >}}
</div>

Otras fomas de manejadores del resultado de la petición son los siguientes:

* [BodyHandler.asString()](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpResponse.BodyHandler.html#asString--): almacena el resultado de la petición en un String
* [BodyHandler.asByteArray()](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpResponse.BodyHandler.html#asByteArray--): almacena el resultado de la petición en un array de bytes como sería el caso de obtener una imagen o un archivo PDF
* [BodyHandler.asFile(Path)](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpResponse.BodyHandler.html#asFile-java.nio.file.Path-): almacena el resultado en un archivo del disco
* [BodyHandler.discard()](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpResponse.BodyHandler.html#discard-U-): descarta la respuesta y devuelve el valor indicado

Se puede definir la política de cómo procesar las redirecciones para seguirlas, no seguirlas o solo si son seguras o utilizan el mismo protocolo.

* [HttpClient.Redirect.ALWAYS](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpClient.Redirect.html#ALWAYS)
* [HttpClient.Redirect.NEVER](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpClient.Redirect.html#NEVER)
* [HttpClient.Redirect.SAME_PROTOCOL](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpClient.Redirect.html#SAME_PROTOCOL)
* [HttpClient.Redirect.SECURE](http://docs.oracle.com/javase/9/docs/api/jdk/incubator/http/HttpClient.Redirect.html#SECURE)

Con la clase [SSLContext](http://docs.oracle.com/javase/9/docs/api/javax/net/ssl/SSLContext.html) es posible establecer autenticación para el cliente usando un certificado como muestro en el artículo [Autenticación mutua de cliente y servidor con certificados][blogbitix-241].

Para ejecutar el ejemplo usando [Gradle][gradle] y Java 9 hay que añadir un poco de configuración al _script_ de construcción que posiblemente en un futuro no será necesaria cuando se mejore el soporte.

{{< gist picodotdev c3f0bea0d89da4ec3a81235e479d069b "build.gradle" >}}

{{< sourcecode git="blog-ejemplos/tree/master/JavaHttp2" command="./gradlew run" >}}

{{% /post %}}
