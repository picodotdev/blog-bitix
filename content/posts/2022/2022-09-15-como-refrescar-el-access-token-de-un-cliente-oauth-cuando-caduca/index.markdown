---
pid: 652
type: "post"
title: "Cómo refrescar el access token de un cliente OAuth cuando caduca"
url: "/2022/09/como-refrescar-el-access-token-de-un-cliente-oauth-cuando-caduca/"
date: 2022-09-15T21:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:oauth.svg"
tags: ["java", "planeta-codigo"]
summary: "Dado que el protocolo HTTP es un protocolo sin estado los _access token_ se envían en cada petición que se hace al _endpoint_ de un recurso ofrecido por una aplicación mediante una interfaz REST. Para limitar el periodo de que un _access tokens_ sea utilizable en caso de ser filtrado estos se crean con un tiempo de vida corto, los clientes para seguir realizando peticiones han de solicitar al servidor de autorización un nuevo _access token_. La librería OkHttp permite realizar la renovación de forma transparente para el cliente y en el código donde se usa."
---

{{% post %}}

{{< logotype image1="oauth.svg" >}}

En [el protocolo OAuth][oauth] la autorización se concede o no en base a un _token_ que para el cliente es simplemente una cadena de letras sin ningún sentido.

* [Introducción al protocolo OAuth 2 para delegar la autorización][blogbitix-651]

Dado que los _access token_ tienen un tiempo de expiración relativamente corto el protocolo OAuth requiere que sean renovados cuando caducan para que los clientes sigan pudiendo realizar peticiones.

{{< tableofcontents >}}

### Los access token y refresh token de protocolo OAuth

El acceso a un servicio depende de tener un _token_ a través de alguna de las formas que proporciona el protocolo OAuth, la principal y recomendada el que denomina _authorization code grant_ que permite mantener privado tanto el secreto del cliente como el _access token_ del agente del usuario.

Dado que los _access token_ se envían en cada petición que realiza el cliente al servidor hay numerosas oportunidades que los _access tokens_ sean capturados, para minimizar el problema de seguridad se generan _access tokens_ con un tiempo de vida corto, de unos pocos minutos o unas pocas horas. Sin embargo, los clientes han de tener una forma de obtener un nuevo _access token_ cuando este expira para seguir haciendo invocaciones en el servidor, al mismo tiempo que se genera un _access token_ al cliente se le proporciona un _refresh token_ que le permite obtener un nuevo _access token_ cuando por ejemplo caduca.

Cuando un _access token_ ya no es válido el servidor de recurso devuelve un código de estado que el cliente puede capturar para saber si el _access token_ ha caducado y hay que obtener uno nuevo.

El obtener un _access token_ implica hacer una petición al servidor de autorización proporcionando entre otros datos el _refresh token_. Para que el cliente no sea consciente de la renovación del _access token_ y que estos pueden caducar en cualquier momento algunas librerías como [OkHttp][okhttp] permiten a los clientes implementar el obtener un nuevo _access token_ de forma transparente en las peticiones que se hagan.

### _Authenticator_ de OkHttp

La clase [Authenticator](https://square.github.io/okhttp/3.x/okhttp/okhttp3/Authenticator.html) permite realizar la autenticación antes de hacer la petición al servidor o cuando el servidor devuelva un determinado código de estado. Con una implementación de esta clase el código no necesita ser consciente de la autenticación que se necesita realizar en las peticiones al servidor.

Esta interfaz incluye un único método que hay que implementar, en el caso de que el método sea invocado y el código de respuesta de la petición haya sido un 401 y la petición incluya una cabecera de autorización quiere decir que las credenciales proporcionadas no son válidas en el caso de usar OAuth que el _access token_ proporcionado no es válido, un caso es que el _access token_ haya caducado, caso en el que hay que realizar la renovación del _access token_.

### Implementar un autenticator para OkHttp

* [Cliente de un servicio REST autenticado con OAuth en Java][blogbitix-183]
* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]

#### Cliente de servicio OAuth

Esta es la implementación de una interfaz _Autheticator_ que permite renovar el _access token_ cuando este caduca.

{{< code file="client/DefaultAuthenticator.java" language="java" options="" >}}

Esta otra clase de utilidad se encarga de almacenar el _access token_ y _refresh token_ y de realizar las peticiones para obtener el _access token_ tanto la primera vez como cuando se solicita utilizando el _refresh token_. Utiliza el método _client credentials_ para obtener un _access token_ al mismo tiempo junto a un _refresh token_ a partir de las credenciales del cliente y para hacer la renovación del _access token_ se utiliza un _refresh_token_ además de las credenciales del cliente.

{{< code file="client/AccessTokenRepository.java" language="java" options="" >}}

En el ejemplo el siguiente cliente que utiliza el método _client credentials_ para obtener un _access token_ hace peticiones cada unos pocos segundos al servidor del recurso. La renovación de _access token_ se realiza de forma transparente y aunque el _access token_ haya caducado y una petición falle con un código de estado 401 se invoca la renovación de _access token_ y la petición con el _access token_ renovado se reintenta. La interfaz _Authenticator_ se usa al construir la instancia de OkHttp que utiliza el cliente del recurso.

{{< code file="client/Main.java" language="java" options="" >}}

El programa del cliente OAuth realiza peticiones al servidor del recurso utilizando el _access token_ cuando este caduca la clase _Authenticator_ es invocada y solicita al _AccessTokenRepository_ un nuevo _access token_ para ello utiliza el _refresh token_. Para el código que realiza la petición la caducidad del _access token_ y la renovación ocurre sin su conocimiento.

{{< code file="client/System.out" language="plain" options="" >}}

#### Servidor de recurso

El servidor de recursos implementado como una aplicación del [Spring Boot][spring-boot] comprueba el _access token_ como parte de su autorización, en caso de que el _access token_ sea inválido porque haya caducado o sea incorrecto se devuelve un código de estado 401. [Spring Security][spring-security] proporciona las utilidades para validar el _access token_ enviado en cada petición y configura Spring para todas las peticiones al servidor requieran un _access token_ simplemente añadiendo la anotación _@EnableWebSecurity_ y un poco de configuración en la aplicación.

{{< code file="server/MessageResource.java" language="java" options="" >}}

Definiendo el _bean_ del tipo JwtDecoder peremite personalizar las validaciones que se realizan sobre el _access token_ por defecto Spring solo valida el _issuer_ y en este ejemplo se muestra como validar otro campo o _claim_ del documento JSON del _token_ en formato JWT en este caso el de audiencia, si el _claim_ _iss_ indica quien ha emitido el _aceess token_  el _claim_ _aud_ indica quien es el destinatario del _access token_.

{{< code file="server/Main.java" language="java" options="" >}}
{{< code file="server/application.yml" language="yaml" options="" >}}

#### Servidor de autorización

Como servidor de autorización se utiliza [Keycloak] creando un _realm_ y un cliente que hay que crear en el _realm_ que al hacerlo se obtienen sus credenciales, en este caso el servidor de Keycloak se inicia como [un contenedor de Docker][blogbitix-50].

{{< code file="docker/docker-compose.yml" language="yaml" options="" >}}

{{< image
    gallery="true"
    image1="image:keycloak-clients.png" optionsthumb1="650x450" title1="Clientes en Keycloak"
    caption="Clientes en Keycloak" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringBootOauthClientServer" command="./gradlew client:run" %}}

{{< reference >}}
* [OkHttp: How to Refresh Access Token Efficiently](https://medium.com/@sumon.v0.0/okhttp-how-to-refresh-access-token-efficiently-6dece4d271c0)
* [Refreshing OAuth token using Retrofit without modifying all calls](https://stackoverflow.com/questions/22450036/refreshing-oauth-token-using-retrofit-without-modifying-all-calls)
* [OkHttp Authenticator](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-authenticator/)
* [Problem Solved 2: Access Token refresh with Okhttp Authenticator](https://stengale.medium.com/problem-solved-2-access-token-refresh-with-okhttp-authenticator-5ccb798ede70)
{{< /reference >}}

{{% /post %}}

