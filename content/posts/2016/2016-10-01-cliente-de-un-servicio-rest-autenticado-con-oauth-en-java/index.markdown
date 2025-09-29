---
pid: 183
type: "post"
title: "Cliente de un servicio REST autenticado con OAuth en Java"
url: "/2016/10/cliente-de-un-servicio-rest-autenticado-con-oauth-en-java/"
aliases: ["/2016/10/cliente-de-un-servicio-rest-autenticado-con-oauth2-en-java/"]
date: 2016-10-01T11:00:00+02:00
updated: 2022-09-16T19:00:00+02:00
index: true
rss: true
sharing: true
comments: true
language: "es"
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Teniendo un servicio REST securizado con OAuth2 al invocarlo deberemos realizar el flujo necesario para obtener un _access token_ y posteriormente enviarlo al servicio REST como forma de autenticación y autorización. Usando un cliente programado en el lenguaje Java y usando la librería HttpClient podemos hacer las peticiones HTTP necesarias para la invocación del servicio."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Explicaba como llamar a un servicio REST autenticado con OAuth2 en el artículo [autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]. Para ello usaba la utilidad _curl_ para hacer las peticiones HTTP _get_ y _post_ necesarias tanto para obtener el _access token_ usando el flujo _client\_credentials_ como para una vez obtenido el _access token_ llamar al servicio REST. En una aplicación usaremos un lenguaje de programación para llamar al servicio.

En este ejemplo mostraré como llamarlo usando un cliente programado en lenguaje Java que hará las mismas peticiones _get_ y _post_ pero usando la librería [OkHttp][okhttp] en vez de _curl_.

Primero añadiremos como dependencia del proyecto la librería HttpComponents. Como en las diferentes llamadas el intercambio de datos se realiza mediante el [formato JSON][json] añadiremos otro par dependencias para procesar los datos en este formato, en este caso usando la API de [JSON-P][json-p] y una implementación.

{{< code file="build.gradle" language="groovy" options="" >}}

Este sencillo cliente realiza varias peticiones _get_ y _post_. Una para obtener la configuración de los _endpoints_, el que nos interesa es el de obtener un _access token_, otra petición para obtener el _access token_ y finalmente con el _access token_ invocar al servicio mediante otra petición.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="AccessTokenRepository.java" language="java" options="" >}}

Iniciado [Keycloak][keycloak] con [Docker][docker], configurado el _realm_ y creado un cliente junto con un rol e iniciado el servicio REST podemos ejecutar el cliente que invoque al servicio. El resultado de las trazas que obtendremos en la terminal será el siguiente.

{{< code file="System.out" language="plain" options="" >}}

En las trazas vemos el _endpoint_ para obtener _access token_, el _access token_ obtenido, _refresh token_ y tiempos de expiración de los mismos, finalmente los datos devueltos por el servicio. Como se observa los _access token_ para el cliente son una cadena opaca bastante larga de caracteres, está cifrada firmada digitalmente y contiene información como el rol y tiempos de expiración. Enviado el _access token_ al servicio REST [Spring Security][spring-security] vcalida la firma digital del _token_, descifrará la información, validará su tiempo de expiración y se comprobará si tiene el rol necesario para acceder al _endpoint_ del servicio REST u validaciones sobre otros _claims_. Notar que con la información incluida en el _token_ y el hecho de que está firmado digitalmente no es necesario que el servicio REST se comunique con el servidor de autorización de OAuth que ha emitido el _access token_ para hacer la validación.

Dos buenos libros sobre OAuth que he leído son los siguientes uno explica detalladamente el protocolo OAuth incluidas las varias formas de obtener un _token_ además del mostrado en este artículo usando las credenciales del cliente. El otro libro es sobre el proveedor de identidad [Keycloak] que implementa autenticación con [OpenID Connect][openid-connect].

{{< amazon
    linkids="https://amzn.to/42rQtXx,https://amzn.to/42rNmi5"
    asins="161729327X,1800562497"
    titles="OAuth 2 in Action,Keycloak Identity and Access Management for Modern Applications" >}}

{{< sourcecode git="blog-ejemplos/tree/master/SpringBootOauthClientServer" >}}

{{% /post %}}
