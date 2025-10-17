---
pid: 533
type: "post"
title: "Autenticación con OpenID/OAuth en cualquier web con Nginx y de forma nativa con Spring Boot"
url: "/2020/11/autenticacion-con-openid-oauth-en-cualquier-web-con-nginx-y-de-forma-nativa-con-spring-boot/"
date: 2020-11-20T16:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:autenticacion-google-1.webp"
tags: ["java", "planeta-codigo", "programacion", "software-libre"]
summary: "La autenticación permite identificar a los usuarios en una aplicación, en muchas es una necesidad para no permitir accesos no autorizados a la información que proporcionan o realizar las acciones que ofrecen. Las aplicaciones heredadas o _legacy_ en ocasiones no es posible modificarlas y cuando una organización tiene varias aplicaciones gestionar los usuarios en cada una de ellas de forma individual se convierte en un problema. OpenID Connect proporciona la autenticación en el protocolo OAuth 2, con este protocolo las aplicaciones pueden delegar la autenticación a un proveedor de autenticación y ser este la que identifique a los usuarios y los gestione de forma forma centralizada además de proporcionar un inicio de sesión único o _single-sing-on_ a varias aplicaciones. El servidor web Nginx tiene _proxys_ que permiten añadir autenticación OAuth 2 a cualquier servicio web y las aplicaciones de Spring Boot pueden implementarlo de forma nativa."
---

{{% post %}}

{{< logotype image1="oauth.svg" image2="java.svg" >}}

La funcionalidad de autenticación y autorización es básica en la mayoría de aplicaciones web y servicios REST. La forma de implementar la autenticación y autorización es posible de varias formas. Sin embargo, con los métodos anteriores las aplicaciones gestionan sus propios usuarios y cada aplicación ha de implementar la autenticación y autorización. Otras formas comunes de autenticación son emplear [autenticación básica en el servidor web][blogbitix-505], [autenticación mutua entre el servidor y el cliente][blogbitix-241] o con una implementación específica en la que una parte importante es [guardar las contraseña de forma segura con salted-password-hashing][blogbitix-75].

Gestionar los usuarios de en cada una aplicación se convierte en un problema cuando el número de aplicaciones y servicios son varios, ya que a lo largo del tiempo hay que dar de alta a los usuarios nuevos y de baja a los usuarios que ya no deben acceder a la aplicación por ejemplo porque ya no forman parte de una organización. Por otro, lado hay aplicaciones heredadas o _legacy_ que no se pueden modificar para añadirles la autenticación que necesitan.

Para administrar de forma centralizada los usuarios o credenciales de las aplicaciones y que el sistema de autenticación y autorización sea uno compartido para cualesquiera usuarios y aplicaciones una forma de implementarlo es usando un proveedor que implemente el estándar [OpenID Connect][openid-connect]. Para las aplicaciones heredadas la opción es añadir un _proxy_ que proteja la aplicación con autenticación y en el caso de una aplicación Java con [Spring Boot][spring-boot] el proyecto de [Spring Security][spring-security] permite añadir autenticación fácilmente para cualquier proveedor de OpenID.

{{< tableofcontents >}}

## Qué es OpenID Connect

OpenID Connect es una capa de identidad que funciona sobre el [protocolo OAuth][oauth] 2.0. Permite a los clientes verificar la identidad del usuario basándose en la autenticación realizada por el servidor de autorización, así como obtener información básica del perfil del usuario. El protocolo funciona con principios similares a REST lo que lo hace interoperable con cualquier sistema.

Son varios los proveedores que ofrecen autenticación con sus cuentas, algunos de ellos son [Google][google], [GitHub][github], [Azure Active Directory][microsoft-azure-active-directory], [AWS Cognito][amazon-cognito] u [Okta][okta]. Para implementar el servicio de autenticación y autorización OAuth gestionando sin depender de esas otras organizaciones está [Keycloak][keycloak].

## Autenticación OpenID/OAuth con Nginx

Para añadir autenticación OpenId Connect en una aplicación web se suele configurar con el servidor web actuando de _proxy_ y un _proxy_ de OAuth. La función del servidor web y el _proxy_ es requerir que el usuario esté autenticado en el proveedor de autenticación. De este modo entre el usuario y la página web están el servidor web, el intermediario de OAuth y el proveedor de autenticación, el esquema es el siguiente.

{{< image
    gallery="true"
    image1="image:oauth2-proxy.webp" optionsthumb1="300x200" title1="Modos de funcionamiento de oauth2-proxy (con y sin Nginx)"
    caption="Modos de funcionamiento de oauth2-proxy (con y sin Nginx)" >}}

Con el servidor web [Nginx][nginx] dos intermediarios o _proxys_ que proporcionan autenticación OpenID Connect son [oauth2-proxy][oauth2-proxy] y [vouch-proxy][vouch-proxy]. Tanto [oauth2-proxy][oauth2-proxy] y [vouch-proxy][vouch-proxy] son dos servicios que le indican a Nginx si el usuario está autenticado usando la directiva _auth_request_ de Nginx. Estos _proxys_ simplemente devuelven como respuesta un código de estado _202 Accepted_ o _401 Unauthorized_ para indicarle a Nginx si hay que realizar la autenticación, las otras directivas de configuración son para establecer cabeceras con las que es posible entre otras cosas indicarle a la aplicación web final cual es el usuario autenticado. En caso de que haya que autenticar al usuario el _proxy_ de OAuth redirige al proveedor de autenticación.

En el caso de [oauth2-proxy][oauth2-proxy] la configuración consiste en hacer de _proxy_ para la aplicación web en la ubicación _/_ y requerir autenticación con el _endpoint_ _/oauth2/_ y _/oauth2/auth_ que hace de _proxy_ para oauth2-proxy. Buena parte de esa [configuración de _proxy_](https://oauth2-proxy.github.io/oauth2-proxy/docs/configuration/overview#configuring-for-use-with-the-nginx-auth_request-directive) son el tratamiento de las cabeceras con las que se obtiene el nombre de usuario autenticado y correo electrónico.

Este es la configuración para Nginx.

{{< code file="nginx.conf" language="nginx" options="" >}}
{{< code file="nginx.127.0.0.1.xip.io.conf" language="nginx" options="" >}}

El servicio de _proxy_ OAuth debe ubicarse en un subdominio de la página a autenticar ya que para esto se utilizan _cookies_.

{{< code file="oauth2.nginx.127.0.0.1.xip.io.conf" language="nginx" options="" >}}

Tanto _oauth2-proxy_ como _vouch-proxy_ tienen imágenes de [Docker][docker] para su fácil uso sin necesidad de instalar nada en la máquina local salvo el propio Docker ([imagen docker oauth2-proxy](https://hub.docker.com/r/bitnami/oauth2-proxy), [imagen docker vouch-proxy](https://hub.docker.com/r/voucher/vouch-proxy)). Ambos _proxys_ requieren cierta configuración indicada en la [documentación del archivo de configuración](https://oauth2-proxy.github.io/oauth2-proxy/docs/configuration/overview), las propiedades son desde el puerto en el que escucha el servicio del _proxy_ las peticiones desde Nginx, configuración de TLS, proveedor de autenticación, dirección de redirección después de la autenticación, configuración de _logging_, por supuesto el _client-id_ y el _client-secret_ obtenidos del proveedor de autenticación, los correos electrónicos considerados como válidos y configuración de la _cookie_ que mantiene la autenticación. También permite guardar la información de la sesión en [Redis][redis] en vez de en una _cookie_.

La configuración del _oauth-proxy_.

{{< code file="oauth2-proxy.cfg" language="plain" options="" >}}

Y en este caso las direcciones de correos electrónicas o usuarios permitidos en la aplicación.

{{< code file="oauth2-proxy-authenticated-emails.cfg" language="plain" options="" >}}

Al acceder a la página en el dominio _nginx.127.0.0.1.xip.io_ se solicita el inicio de sesión o selección de cuenta.

{{< image
    gallery="true"
    image1="image:autenticacion-google-1.webp" optionsthumb1="300x200" title1="Autenticación con cuenta de Google"
    image2="image:autenticacion-google-2.webp" optionsthumb2="300x200" title2="Autenticación con cuenta de Google"
    caption="Autenticación con cuenta de Google" >}}

Una vez autenticado el usuario se permite el acceso a la página web, en este caso la página por defecto de Nginx, también se observa la creación de la _cookie_ que mantiene la sesión.

{{< image
    gallery="true"
    image1="image:nginx-1.webp" optionsthumb1="300x200" title1="Página web y cookie de sesión"
    image2="image:nginx-2.webp" optionsthumb2="300x200" title2="Página web y cookie de sesión"
    caption="Página web y cookie de sesión" >}}

Al implementar el ejemplo me he encontrado con dos mensajes de error, _OAuth2: unable to obtain CSRF cookie_ y _http: named cookie not present_. Para resolver el primero es necesario indicar el parámetro de configuración _cookie-domain_ que en el momento de realizar el ejemplo solo me ha sido posible indicándolo a través de la línea de comandos no en el archivo de configuración y para resolver el segundo es necesario que el _host_ del servicio _proxy_ OAuth esté en un subdominio del dominio de la página web.

{{< code file="docker-compose.yml" language="yaml" options="" >}}

## Autenticación OpenID/OAuth con Apache

En el caso del servidor web [Apache HTTPD][apache-httpd] la solución que he encontrado es usar el módulo [mod_auth_openidc](https://www.mod-auth-openidc.org/).

## Autenticación OpenID en una aplicación Spring Boot

Las aplicaciones de Java que usan [Spring Boot][spring-boot] a través de la dependencia de [Spring Security][spring-security] que soporta OpenID Connect con el que añadir soporte a la aplicación fácilmente con un proveedor de autenticación. En este ejemplo se usa Google como proveedor de autenticación , [Keycloak][keycloak] es otro [proveedor de autenticación OAuth para autenticar un servicio REST][blogbitix-180].

El siguiente ejemplo es un servicio de Spring Boot accedido desde el navegador, en caso de que el cliente fuese otra aplicación o servicio hay que obtener un _token_ con el que poder invocar este servicio.

* [Cliente de un servicio REST autenticado con OAuth en Java][blogbitix-183]
* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot](https://picodotdev.github.io/blog-bitix/2016/09/autenticacion-con-oauth-y-keycloak-en-un-servicio-rest-con-jax-rs-y-spring-boot/)

Primero hay que incluir en el proyecto la dependencia del cliente OAuth2.

{{< code file="build.gradle" language="groovy" options="" >}}

Añadir la configuración en la aplicación para incluir el identificativo del cliente y el secreto del servicio de autenticación que permite validar la autenticación.

{{< code file="application.yml" language="yaml" options="" >}}

La información del usuario autenticado se puede obtener con la anotación [@AuthenticationPrincipal](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/bind/annotation/AuthenticationPrincipal.html) o mediante la clase [SecurityContextHolder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/context/SecurityContextHolder.html).

{{< code file="MainController.java" language="java" options="" >}}

Lo último necesario es configurar qué rutas necesitan autenticación con OpenID Connect, en caso de que el usuario no esté autenticado se realiza la redirección al proveedor de autenticación. Con la configuración indicada se requiere autenticación para acceder a todas las rutas.

{{< code file="OAuth2LoginSecurityConfig.java" language="java" options="" >}}

Al acceder a una de las URLs del cliente se solicita la autenticación con una cuenta de Google, una vez autenticado se accede a la página que en este caso obtiene la información del usuario como el nombre y correo electrónico.

{{< image
    gallery="true"
    image1="image:spring-boot-google.webp" optionsthumb1="300x200" title1="Aplicación de Spring autenticada con una cuenta de Google"
    caption="Aplicación de Spring autenticada con una cuenta de Google" >}}

## Configuración de autenticación con cuentas de Google

En el ejemplo de este artículo muestro la autenticación con Google como proveedor de autenticación Oauth 2 pero perfectamente podría ser otro como Keycloak, en todos básicamente se trata de obtener las credenciales _client-id_ y _client-secret_ que permiten validar la autenticación tanto en estos casos Nginx como la aplicación de Spring Boot. Con una cuenta de Google y desde la [consola para desarrolladores](https://console.developers.google.com/) en el apartado credenciales es posible generar las credenciales para la autenticación de usuarios en una aplicación. Estas credenciales son los mencionados _client-id_ y _client-secret_.

Los pasos para obtener las credenciales para una aplicación con autenticación OAuth con cuentas de Google son:

* Crear un proyecto
* Configurar la pantalla de consentimiento. En este paso se pide configurar la pantalla de consentimiento, la pantalla de consentimiento se muestra al usuario cuando realicen la autenticación entre la información está que aplicación solicita el consentimiento, opcionalmente es posible añadir un logotipo o enlaces de términos de uso.
* Crear las credenciales del cliente. Aquí se configura las URL de retorno válidas cuando el usuario se haya autenticado. Si no se ha configurado la pantalla de consentimiento se solicita como paso previo a este.

Las credenciales tienen unos valores similares a, estas se indican tanto en la configuración de _oauth-proxy_ como de la aplicación de Spring Boot:

* _client-id_: _949347437228-m2c85v7bkmo1qb90vso702j27j4tccvr.apps.googleusercontent.com_
* _client-secret_: _szgiplYPZR-pGgHP9MiJ-6-q_

Al crear las credenciales para el cliente se indican las URL de retorno permitidas a las que se retorna al _proxy_ o a la aplicación después del inicio de sesión.

{{< image
    gallery="true"
    image1="image:google-client.webp" optionsthumb1="300x200" title1="Pasos para la creación de credenciales en Google para la autenticación OAuth"
    caption="Pasos para la creación de credenciales en Google para la autenticación OAuth" >}}

{{% sourcecode git="blog-ejemplos/tree/master/oauth2-proxy" command="docker-compose up" %}}
{{% sourcecode git="blog-ejemplos/tree/master/SpringOAuthClient" command="./gradlew run" %}}

{{< reference >}}
* [Spring Security and OpenID Connect](https://www.baeldung.com/spring-security-openid-connect)
* [Google Setting up OAuth 2.0](https://developers.google.com/identity/protocols/oauth2/openid-connect#appsetup)
{{< /reference >}}

{{% /post %}}
