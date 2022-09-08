---
pid: 651
type: "post"
title: "Introducción al protocolo OAuth 2 para delegar la autorización"
url: "/2022/09/introduccion-al-protocolo-oauth-2-para-delegar-la-autorizacion/"
aliases: ["/2022/09/introduccion-al-protocolo-oauth-2-0-para-delegar-la-autorizacion/"]
date: 2022-09-08T11:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "logotype:oauth.svg"
imagePost: "logotype:oauth.svg"
tags: ["planeta-codigo", "programacion"]
summary: "El protocolo OAuth es un protocolo en el ámbito de la seguridad que permite que el dueño de los recursos conceda permisos a un cliente sin necesidad de compartir las credenciales, el servidor de recursos unicamente necesita un _token_ que certifique el cliente tiene permisos para acceder a los recursos, el _token_ es emitido por el servidor de autorización en el que el usuario se autentica y en el que servidor de recursos confía en los _tokens_ de autorización que emite. Gracias a la flexibilidad del protocolo OAuth y estar basado en la ubicuidad del protocolo HTTP este es el mecanismo de autorización adoptado predominantemente en la web por los diferentes servicios que ofrecen sus APIs mediante REST sin limitarse a este tipo de APIs. OAuth se compone de varias especificaciones que permiten delegar la autorización, OpenID Connect construido sobre OAuth proporciona otro aspecto de la seguridad, la autenticación."
---

{{% post %}}

{{< logotype image1="oauth.svg" >}}

La web se compone de una multitud de páginas desarrolladas y bajo el control de múltiples organizaciones no relacionadas. Los usuarios almacenan sus datos y recursos en un servicio, en una web interconectada compuesta de múltiples servicios surge la necesidad de que un servicio pueda acceder de forma segura a los recursos de un usuario en otro servicio.

Este es el caso de una aplicación de un tercero que por ejemplo desea acceder a los documentos,  fotos o datos de un usuario alojada en otro servicio pero sin tener que compartir las credenciales del servicio donde están alojadas las fotos.

{{< tableofcontents >}}

### El protocolo OAuth

El protocolo OAuth permite al usuario delegar la autorización a un cliente para el acceso a sus recursos proporcionado por un servidor de recursos, permite delegar la autorización de forma segura y sin que el usuario comparta sus credenciales de acceso en el servicio donde están los recursos al mismo tiempo que mantiene la posibilidad de revocar el acceso u otorgarlo de forma temporal.

Esta definición se compone de cuatro elementos:

* El usuario: que es propietario de los recursos almacenados en el servidor de recursos.
* El servidor de recursos: que contiene los recursos del usuario.
* El cliente: la aplicación a la que el usuario quiere otorgar acceso a los recursos del servidor.
* El servidor de autorización: es el que proporciona el _token_ (entre otras cosas) a modo de credencial al cliente y que el servidor de recurso utiliza para verificar el acceso al recurso o no. El servidor de recurso confía en el servidor de autorización.

{{< image
    gallery="true"
    image1="image:oauth-components.png" optionsthumb1="650x450" title1="Componentes de OAuth 2.0"
    caption="Componentes de OAuth 2.0" source="Libro OAuth 2 in Action">}}

El protocolo OAuth define varios flujos de autorización que un cliente puede emplear para obtener un _token_ pero deja otros aspectos sin definir como cuál es el formato o contenido del _token_, tampoco define como el servidor de autorización debe realizar la autenticación del usuario. Esta no definición de algunos aspectos del protocolo le permite ser flexible y adaptarse a diferentes contextos.

El protocolo OAuth en esencia consiste básicamente en una forma que emplea el cliente para obtener un _token_ y que este envía en sus peticiones para el acceso al recurso en el servidor de recursos.

Es el mecanismo de autorización más empleado para proteger servicios que utilizan una API con interfaz REST. En las peticiones REST que el cliente realiza al servidor de recurso envía el _access token_ en una cabecera que el servidor de recurso válida ya sea por sí mismo o validando el _access token_ haciendo una petición al servidor de autorización.

El servidor de autorización junto al _access token_ emite el _refresh token_, los _tokens_ tienen un tiempo de expiración relativamente corto u que por motivos de seguridad en caso de que sean filtrados han de ser renovados para reducir el tiempo en el que un atacante pueda hacer uso de ellos. Cuando to _access token_ expira el cliente puede obtener un nuevo _access token_ solicitándoselo al servidor de autorización y haciendo uso del _refresh token_. Dado que los _refresh token_ solo se usan en las peticiones para obtener un nuevo _access token_ hay menos posibilidades de que sean filtrados al contrario que los _access token_ que son enviados en cada petición.

* [OAuth 2.0](https://oauth.net/2/)
* [Especificaciones de OAuth 2.0](https://oauth.net/specs/)

### Flujos de autorización

El protocolo OAuth define varios flujos o _grant types_ que un cliente puede seguir para la obtención del _access token_ que le permite acceder los recursos. Para el servidor de recursos la forma que el cliente emplee para obtener el _token_ y para el cliente el contenido o formato del _token_ si tiene alguno no tiene relevancia.

El flujo por defecto, más completo, seguro y recomendado es el flujo de _authorization code_. Los otros flujos son variaciones adaptadas a algunas limitaciones de otros contextos o casos de uso.

* [OAuth Grant Types](https://oauth.net/2/grant-types/)

#### _Authorization code grant_

El flujo de _authorization code_ es el más completo y por ello más recomendado de usar siempre que se pueda. De forma simplificada en cuanto a la información que se utiliza en las peticiones los pasos de este flujo son los siguientes:

1. El cliente solicita al usuario autorización para el acceso a un recurso, para ello construye una URL entre cuya información está el identificativo del cliente que redirige al usuario al servidor de autorización para la obtención de un _token_.
2. El servidor de autorización autentica al usuario si es necesario, informa y permite al usuario de cambiar los permisos que desea otorgar al cliente. Una vez el usuario otorga el acceso el servidor genera un _authorization code_ que el cliente utiliza para intercambiar por el _token_. El servidor de autorización envía al agente del usuario una redirección a un _endpoint_ del cliente con el _authorization code_.
3. El cliente recibe el _callback_ del servidor de autorización con el _authorization code_ que junto con las credenciales del cliente utiliza para intercambiarlos por el _token_ con una nueva petición al servidor de autorización.
4. El servidor de autorización recibe la petición del cliente con sus credenciales y el _authorization code_, si son correctos el servidor de autorización genera un _token_ o _access token_ que el cliente utiliza para obtener acceso al recurso del usuario.
5. Una vez el cliente tiene el _token_ o _access token_ realiza una petición al servidor de recurso para obtener acceso al recurso.
6. El servidor de recurso valida el _access token_ y en función de la autorización otorgada en el _token_ concede o no acceso al recurso.

En estos pasos desde el 1 al 4 hay una comunicación a través del agente o navegador del usuario entre el cliente y el servidor de autorización, en el contexto de OAuth a este canal de comunicación se le denomina _front channel_ y como es a través del navegador del usuario es público. En estos pasos el _authorization code_ se envía a través del agente del usuario.

Los pasos 4 y 5 componen lo que en el contexto de OAuth se les denomina _back channel_ y se realiza de forma privada y directa entre el cliente y el servidor de autorización. En estos pasos las credenciales del cliente se mantienen privadas.

Otro aspecto importante es que el usuario en caso de tener que autenticarse únicamente proporciona las credenciales al servidor de autenticación, el cliente no tiene acceso a las credenciales del usuario que únicamente recibe un _authorization code_ y _access token_, el agente del usuario no tiene acceso al _access token_.

* [The OAuth 2.0 Authorization Framework, Authorization Code Grant](https://www.rfc-editor.org/rfc/rfc6749#section-4.1)

{{< code file="authorization-code-grant.txt" language="plaintext" options="" >}}

#### _Client credentials grant_

El flujo _client credentials_ se usa cuando el cliente accede a los recursos en su propia representación sin la intervención de un usuario.

Para obtener el _token_ el cliente proporciona sus credenciales al servidor de autorización que obtiene como respuesta directamente el _access token_.

* [The OAuth 2.0 Authorization Framework, Client Credentials Grant](https://www.rfc-editor.org/rfc/rfc6749#section-4.4)

{{< code file="client-credentials-grant.txt" language="plaintext" options="" >}}

#### _Device grant_

Este flujo se utiliza en caso de que el dispositivo no tenga posibilidad de utilizar un agente de usuario. En vez de ello el dispositivo simplemente presenta un código que el usuario utilizando otro dispositivo se autentica con sus credenciales y junto al código realiza la autorización. El dispositivo comprueba periódicamente si el usuario ha realizado la autorización al servidor de autorización que en caso de ser correcta le devuelve el _access token_.

* [OAuth 2.0 Device Authorization Grant](https://www.rfc-editor.org/rfc/rfc8628)

{{< code file="device-grant.txt" language="plaintext" options="" >}}

#### _Implicit grant_

El uso del flujo _implicit_ está desaconsejado y se recomienda utilizar el flujo _authorization code_ en caso de ser posible. En este flujo se utiliza cuando el cliente se ubica en el propio agente del usuario. En la redirección del _front channel_ el servidor responde con una redirección que incluyen el fragmento de la URL el _access token_.

Dado que el agente del usuario tiene acceso al _access token_ se considera menos seguro.

* [The OAuth 2.0 Authorization Framework, Implicit Grant](https://www.rfc-editor.org/rfc/rfc6749#section-4.2)

{{< code file="implicit-grant.txt" language="plaintext" options="" >}}

#### _Resource owner password credentials grant_

Antes de OAuth para que un servicio pudiera acceder a los recursos de otro servicio una posibilidad era que el usuario compartiera las credenciales del servicio del recurso con el cliente. Compartir las credenciales del usuario entre servicios es una de las cosas que trata de evitar OAuth ya que cualquier cliente inseguro compromete las credenciales del usuario.

En el flujo _resource owner password_ el cliente solicita las credenciales al usuario, una vez las obtiene las utiliza para obtener un _access token_ en el servidor de autorización. Para su funcionamiento el cliente tiene acceso a las credenciales del usuario por eso también está desaconsejado su uso pero al menos una vez el cliente obtiene el _access token_ no tiene que enviar las credenciales del usuario en cada petición al servidor de recursos lo que reduce las posibilidades de que las credenciales sean filtradas.

* [The OAuth 2.0 Authorization Framework, Resource Owner Password Credentials Grant](https://www.rfc-editor.org/rfc/rfc6749#section-4.3)

{{< code file="resource-owner-password-credentials-grant.txt" language="plaintext" options="" >}}

### Casos de uso

De forma preferente los clientes han de utilizar el flujo _authorization code_ por ser el más seguro de los flujos que define OAuth 2. La flexibilidad de OAuth le permite ser usado en múltiples dispositivos y protocolos.

#### Web

En los servicios y aplicaciones web las arquitecturas de las aplicaciones se componen de una parte cliente que se ejecuta en el navegador del usuario y una parte servidor. En esta arquitectura la parte que hace de servidor para el cliente del navegador web a su vez hace de cliente para el servidor del recurso.

En este caso dado el flujo de autorización OAuth que se utiliza es el de _authorization code_.

#### Servicio _backend_

Algunos clientes no dependen de ningún usuario, esto es el caso de servicios que únicamente tienen una parte de _backend_ En este caso dado el flujo de autorización OAuth que se utiliza es el de _client credentials_.

#### Aplicación nativa

Los clientes actuales incluyen dispositivos con capacidades de cómputo como los teléfonos inteligentes y tabletas que se ejecutan como aplicaciones nativas de estos dispositivos sin utilizar un navegador. 

Este tipo de dispositivos pueden utilizar también el flujo de autenticación _authorization code_, sin embargo, como se ejecutan en un dispositivo del usuario no pueden mantener la confidencialidad de las credenciales de modo que han de utilizar mecanismos adicionales para obtener unas credenciales únicas del cliente.

El protocolo OAuth en algunas especificaciones relacionadas del protocolo permite el registro de forma dinámica de los clientes que en esencia proporciona unas credenciales únicas para cada instalación de la aplicación de forma que si las credenciales de un cliente son filtradas el problema no afecte al resto de clientes, solo exclusivamente a las credenciales del cliente afectado que puedan ser revocadas sin afectar al resto de clientes.

#### Dispositivo

Los televisores inteligentes también tienen capacidades para acceder a servicios de terceros pero son unos dispositivos especiales por su limitación de métodos de entrada y de entorno de ejecución que limitan el poder utilizar el flujo de _authorization code_. Para cubrir las necesidades de estos dispositivos está el flujo _device_.

### Especificaciones

El protocolo OAuth está definido en una colección de especificaciones que son bastante cortas, componen la teoría del protocolo y bastante clarificadoras en varios puntos.

Algunas de las especificaciones definen el protocolo y otras elementos relacionados con OAuth como el registro de clientes de forma dinámica que se utiliza para el caso concreto o las especificaciones que permiten implementar un protocolo de autenticación sobre el protocolo de autorización OAuth como lo es [OpenID Connect][openid-connect], OpenID Connect a su vez define otro conjunto de especificaciones.

#### OpenID Connect

La autorización permite aceptar o rechazar una petición en base a algunos criterios de la petición, también en función del usuario o cliente que realiza la acción o los permisos concedidos.

La autenticación es utilizada para identificar que un usuario es quien dice ser, para identificar a un usuario normalmente se utiliza algo que conoce como una contraseña y más reciente con algo que posee como un segundo factor de autenticación que proporciona un código o un dispositivo hardware físico que identifica al usuario.

OAuth es un protocolo para delegar la autorización pero normalmente se utiliza en combinación con un protocolo para realizar la autenticación, en el ámbito de OAuth el protocolo estándar para la autenticación es OpenID Connect.

OpenID Connect define más claramente algunos puntos que OAuth no entra en su definición. Un punto que define es el formato, contenido y qué información contienen los _tokens_, para los clientes el _token_ sigue siguiendo un valor opaco pero con esta definición los servidores de recursos pueden utilizar el contenido del _token_ para tomar algunas decisiones de autorización sin necesidad de realizar una petición al proveedor de identidad.

Otro aspecto que modifica OpenID Connect es la introducción de algunos elementos para adecuarlos al ámbito de la autenticación, el cliente se denomina _reliying party_ o RP, el servidor de autorización hacer también las funciones de servidor de recursos y se denomina _identity provider_ o IdP, OpenID connect además emite al mismo tiempo que el _access token_ un nuevo _oken_que identifica al usuario, el _identity token_ y la sesión de este.

* [OpenID Connect][openid-connect]
* [OpenID Connect Specifications](https://openid.net/developers/specs/)

#### _Discovery_

Hay múltiples proveedores que proporcionan identidad, el estándar de OpenID Connect define un archivo que los proveedores hacen accesible en una URL y que los clientes pueden utilizar para utilizarlo independientemente de del proveedor mientras cumpla con el estándar, este archivo forma parte.

Muchos de los grandes actores de internet como [Google][google], [Microsoft][microsoft], [Apple][apple], [Facebook][facebook] o [GitHub][github] entre muchos otros permiten a los usuarios que tienen una cuenta en esos servicios iniciar sesión en otros servicios y páginas web sin tener que crear una cuenta nueva en el servicio que se quiere iniciar sesión, esto para los usuarios tiene la ventaja de no necesitar recordar o generar múltiples contraseñas ni crear una cuenta en cada servicio.

En el caso de Google por ejemplo hace público su definición de OpenID Connect en la siguiente ubicación y forma parte del descubrimiento de OpenID Connect.

* [Gogle OpenID Connect Configuration](https://accounts.google.com/.well-known/openid-configuration)

#### JOSE y JWT

En OpenID Connect tanto el _identity token_ como el _access token_ son en realidad un JSON codificado en base64, el cliente no necesita conocer ni analizar su contenido pero el servidor de recursos puede analizarlo para tomar alguna decisión de autorización. El servidor de recurso tiene dos posibilidades, analizar el contenido de _token_ lo que requiere conocer qué formato contiene o hacer una petición al _identity provider_ para que este le devuelva su contenido, ambas tienen alguna ventaja pero si es suficiente para evitar una llamada adicional y latencia en las llamadas se evita hacer peticiones al _identity provider_.

El formato de los _tokens_ según lo define OpenID Connect normalmente es un texto en formato JSON codificado en base64 que aparentemente parece algo aleatorio. Se compone de tres partes separadas por un punto, una cabecera en la que se especifica el algoritmo de firma empleado y formato, un cuerpo que contiene varios campos o _claims_ definidos en el estándar y una firma digital que el servidor de recursos utiliza para validar que ha sido emitido por el servidor de autorización y garantizar que su contenido no ha sido modificado.

El formato de los _tokens_ se engloban en una serie de colección de siglas relacionadas para cada una de las partes los definen:

* JSON Object Signing and Encryption (JOSE)
* JSON Web Token (JWT, RFC 7519): son dos documentos JSON en base64 separados por un punto.
* JSON Web Signature (JWS, RFC 7515): añade una forma digital a los _token_, siendo el tercer documento de un _token_.
* JSON Web Encryption (JWE, RFC 7516): permite el cifrado del cuerpo de los tokens para que su contenido aunque firmado no sea analizable sin la correspondiente clave de descifrado.
* JSON Web Algorithms (JWA, RFC 7518): define los algoritmos de cifrado que deben usar JWS y JWE.
* JSON Web Key (JWK, RFC 7517): define el formato para representar una clave criptográfica en formato JSON.

Otra término mencionado es _proof key for code exchange_ o PKCE que evita algunos problemas de seguridad al enviar servidor de autorización un dato que solo conoce el cliente de modo que solo el cliente legítimo pueda intercambiar el _autorization code_ por el _access token_, útil principalmente para las aplicaciones nativas que no pueden garantizar el secreto de sus credenciales.

### Implementar OAuth y OpenID Connect

Dado que los protocolos OAuth y OpenID Connect se basan en el protocolo HTTP cualquier herramienta que utilice este protocolo es capaz de utilizar OAuth incluso de forma transparente sin que el servicio protegido sea consciente de ello con un _proxy_ y un servidor en el que delegar la autenticación y autorización.

Tanto el servidor web [Apache HTTPD][apache-httpd] como [Nginx][nginx] ofrecen _plugins_ para el uso de OAuth y OpenID Connect, cualquier lenguaje de programación capaz de realizar peticiones HTTP y trabajar con JSON como son la mayoría de lenguajes de propósito general poseen librerías de modo que su uso sea fácil empezar a usarlos simplemente incluyendo una dependencia.

* [Autenticación con OpenID/OAuth en cualquier web con Nginx y de forma nativa con Spring Boot][blogbitix-533]

### Servidor OpenID Connect

[Keycloak][keycloak] es un servidor implementado en el lenguaje Java desarrollado por [RedHat][redhat] y publicado con una licencia de código abierto que proporciona la funcionalidad de servidor de autorización y OpenID Connect para cualquier aplicación incluso aquellas no implementadas en Java. Keycloak se caracteriza por implementar las especificaciones con los diferentes flujos de autorización del protocolo OAuth y OpenID Connect.

* [Integrar autenticación OAuth con Keycloak, Shiro, Apache Tapestry y Spring Boot][blogbitix-185]

### Cliente de OAuth con Spring

En Java el framework [Spring][spring] que proporciona dependencias para prácticamente cualquier funcionalidad común que un desarrollador necesite también proporciona soporte para OAuth y OpenID Connect añadiendo unas pocas líneas de configuración e integrándose con el framework de seguridad [Spring Security][spring-security].

Dado que los _access tokens_ tienen un tiempo de expiración relativamente corto estos han de ser renovados utilizando el _refresh token_, la librería [OkHttp][okhttp] proporciona soporte para realizar la renovación de forma transparente a través de la clase [Authenticator](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-authenticator/) explicada en [su Javadoc](https://square.github.io/okhttp/3.x/okhttp/okhttp3/Authenticator.html).

* [Cliente de un servicio REST autenticado con OAuth en Java][blogbitix-183]

### Servidor de recurso

Al igual que Spring proporciona soporte para la utilización de OpenID Connect el framework de seguridad Spring Security ofrece soporte para la integración de autorización con OAuth de modo que un _endpoint_ de una API REST o funcionalidad de un servicio pueda implementar la lógica para permitir o denegar una petición.

* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]

## Libros sobre OAuth

Las especificaciones de OAuth y OpenID Connect son cortas y explican el protocolo en detalle con descripciones fáciles de entender, incluso a veces es mejor leer las especificaciones directamente que cualquier otro material.

Aún así, los libros explican de otra forma más guiada y paso a paso el funcionamiento del protocolo, dos libros que he leído y me han parecido una buena introducción al protoclo ha sido por un lado [OAuth 2 in Action](https://amzn.to/3D444JO) que explica la teoría de OAuth con varios ejemplos de código y aplicaciones con el que probar el protocolo, por otro lado el libro [Keycloak - Identity and Access Management for Modern Applications](https://amzn.to/3RGHriy) que explica el uso y repasa la teoría de OAuth de la implementación del protocolo OpenID Connect y OAuth en el servidor Keycloak.

* [Servidor OAuth, gateway y servicio REST utilizando tokens JWT con Spring][blogbitix-382]

{{< amazon
    tags=""
    linkids="38b33d0ec3ca9855c0a981d1e2154b63,eb189e8a81b21d362b31f7b08f4b9f4e"
    asins="161729327X,1800562497" >}}

{{% /post %}}
