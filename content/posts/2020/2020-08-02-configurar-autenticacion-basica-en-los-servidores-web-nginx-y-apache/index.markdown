---
pid: 505
type: "post"
title: "Configurar autenticación básica en los servidores web Nginx y Apache"
url: "/2020/08/configurar-autenticacion-basica-en-los-servidores-web-nginx-y-apache/"
date: 2020-08-02T10:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:nginx-401.webp"
tags: ["planeta-codigo", "web"]
---

{{% post %}}

{{< logotype image1="nginx.svg" image2="apache.svg" >}}

La autenticación básica o _basic auth_ es un mecanismo de autenticación sencillo que permite proteger los recursos solicitados de un sitio o aplicación web. Es fácil de configurar en el servidor web y está implementado en los propios navegadores, otra ventaja es que se puede añadir a un sitio o aplicación web sin necesidad de realizar modificaciones en su código.

La autenticación básica por seguridad requiere [utilizar en el servidor web el protocolo seguro HTTPS][blogbitix-14] y [obtener un certificado para el nombre del dominio del servidor web][blogbitix-13] ya que el navegador cuando envía al servidor el usuario y contraseña no los protege de forma especial y utiliza el mecanismo de comunicación de la conexión, es usual también [configurar un servidor web virtual][blogbitix-507] para aplicar esta configuración únicamente al sitio web deseado. Con el protocolo HTTPS el usuario y contraseña se transmite cifrada por la propia conexión segura.

Cuando un servidor para el acceso a un recurso requiere autenticación básica el navegador muestra una ventana emergente en la que se solicita un usuario y contraseña.

{{< image
    gallery="true"
    image1="image:nginx-401.webp" optionsthumb1="300x200" title1="Solicitud de credenciales por el navegador con autenticación básica"
    caption="Solicitud de credenciales por el navegador con autenticación básica" >}}

{{< tableofcontents >}}

## Configurar autenticación básica en el servidor web Nginx

La autenticación básica en [Nginx][nginx] se activan añadiendo dos directivas, _auth_basic_ y _auth_basic_user_file_, en el archivo de configuración del sitio web y recurso a proteger. En este caso con la raíz del sitio web _/_ cualquier ruta está protegida con autenticación básica.

{{< code file="nginx-default.conf" language="plain" options="" >}}

Utilizando [Docker][docker] se puede crear un contenedor y probar la configuración. Al acceder a la dirección _https:\/\/localhost_ el navegador mostrará el diálogo que solicita el usuario y contraseña. 

{{< code file="docker-nginx.sh" language="bash" options="" >}}

Si los datos proporcionados no son correctos se devuelve el código de estado _401_ que indica que el acceso no se ha autorizado y se requiere autorización. Si las credenciales son válidas se muestra el recurso solicitado.

{{< image
    gallery="true"
    image1="image:nginx-401.webp" optionsthumb1="300x200" title1="Solicitud de credenciales"
    image2="image:nginx-web.webp" optionsthumb2="300x200" title2="Acceso al recurso solicitado"
    caption="Solicitud de credenciales y acceso al recurso solicitado" >}}

## Configurar autenticación básica en el servidor web Apache

El comando de Docker para Apache es similar e incluye el archivo de configuración del servidor virtual con el recurso protegido, la clave privada y el certificado que proporcionan cifrado en las comunicaciones con el protocolo HTTPS.

{{< code file="docker-apache.sh" language="bash" options="" >}}

En el caso del servidor web Apache las directivas necesarias a añadir en la configuración para activar la autenticación básica son: _AuthType_, _AuthName_, _AuthUserFile_ y _Require_.

{{< code file="httpd-vhosts.conf" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:apache-401.webp" optionsthumb1="300x200" title1="Solicitud de credenciales"
    image2="image:apache-web.webp" optionsthumb2="300x200" title2="Acceso al recurso solicitado"
    caption="Solicitud de credeciales y acceso al recurso solicitado" >}}

## Cómo crear los archivos de credenciales _htpasswd_

La autenticación se realiza solicitando el navegador a petición del servidor un usuario y contraseña. En este caso el navegador presenta un diálogo al usuario que una vez introducidos realiza la petición de nuevo con las credenciales proporcionadas. El servidor para un recurso que requiere autenticación comprueba los datos de autenticación enviados y los valida con una pequeña base de datos en un archivo _htpasswd_ que el administrador del servidor ha creado previamente con las credenciales de todos los usuarios.

Los archivos _htpasswd_ que guardan las credenciales se crean con la utilidad de línea de comandos _htpasswd_. Tanto para añadir nuevos usuarios como para modificarlos y eliminarlos. Es posible guardar las contraseñas con diferentes algoritmos _hash_ que transforman la contraseña en texto plano en un resultado equivalente con el cual no es posible conocer la contraseña original pero si validar que un dato proporcionado genera el mismo resultado con una función de una sola dirección. El algoritmo _hash_ más seguro soportado es _bcrypt_ que en el comando _htpasswd_ se usa con la opción _-B_, los algoritmos _MD5_ y _SHA_ con las capacidades de computación actuales se consideran inseguros.

{{< code file="htpasswd.sh" language="bash" options="" >}}

El resultado del archivo es una línea por credencial creada con el nombre del usuario y el _hash_ de la contraseña.

{{< code file=".localhost.htpasswd" language="plain" options="" >}}

## Funcionamiento y cabecera de la autenticación básica

El formulario de autenticación solo se presenta una vez una vez introducidas unas credenciales correctas, dado que el protocolo HTTP es un protocolo sin estado el navegador en cada solicitud de cada página y recurso envía la cabecera _Authorization_ que contiene el usuario y contraseña codificadas en base64. Esta cabecera y su valor aunque codificadas no tienen un mecanismo adicional de seguridad por eso es necesario utilizar el protocolo seguro HTTPS cuando se utiliza autenticación básica de modo que los datos incluidos en las cabeceras se transmitan cifrados.

{{< image
    gallery="true"
    image1="image:nginx-header.webp" optionsthumb1="300x200" title1="Cabecera enviada en la petición con las credenciales de acceso al recurso"
    caption="Cabecera enviada en la petición con las credenciales de acceso al recurso" >}}

La codificación en base64 no añade seguridad, el valor de la cabecera con las credenciales decodificadas contiene el usuario y la contraseña separados por dos puntos.

{{< code file="base64.sh" language="bash" options="" >}}

Con la herramienta de linea de comandos _curl_ que permite realizar peticiones HTTP también hay que enviar la cabecera de autenticación para obtener el recurso.

{{< code file="curl.sh" language="bash" options="" >}}

El servidor web indica al navegador que el recurso solicitado requiere autenticación básica devolviendo en la respuesta el [código de estado 401 Unauthorized](https://developer.mozilla.org/es/docs/Web/HTTP/Status/401) y la cabecera _www-authenticate_.

{{< image
    gallery="true"
    image1="image:http-auth.webp" optionsthumb1="650x450" title1="Esquema de peticiones, cabeceras y respuestas del protocolo de autenticación básica"
    caption="Esquema de peticiones, cabeceras y respuestas del protocolo de autenticación básica" >}}

Cuando no se envían credenciales o se envían unas incorrectas el navegador devuelve un código de estado 401.

{{< code file="curl-authorization.sh" language="bash" options="" >}}

## Alternativas a la autenticación básica

La ventaja de la autenticación básica es que es sencilla de implementar pero con algunos inconvenientes a tener en cuenta que son motivo de buscar alternativa más avanzadas aunque también mas complejas. Una desventaja es que los usuarios y contraseñas de las base de datos _htpasswd_ son fijas, por seguridad las contraseñas se deberían rotar cada cierto tiempo para que usuarios que ya no deberían tener acceso tengan conocimiento de la contraseña válida actual, en una empresa es el caso de personas que ya no son empleados pero que si no se rotan las contraseñas seguirían teniendo acceso si no se implementan mecanismos de seguridad adicionales.

Otra desventaja de la autenticación básica es que los archivos _htpasswd_ los crea el administrador de sistemas de modo que este tiene conocimiento de todas las contraseñas. Por esto la autenticación básica no es adecuada para realizar la autenticación en aplicaciones en las que los usuarios crean sus cuentas, las eliminan, tienen ellos mismos la posibilidad de cambian sus contraseñas y con la posibilidad de cerrar la sesión.

Estas desventajas hace de la autenticación básica no adecuada para algunos casos, dependiendo del caso las alternativas son implementar un formulario de autenticación, [implementar OAuth en una aplicación web][blogbitix-185] o [utilizar autenticación mutua con certificados][blogbitix-241].

{{% sourcecode git="blog-ejemplos/tree/master/WebBasicAuth" command="./docker-nginx.sh o ./docker-apache.sh" %}}

{{< reference >}}
* [Autenticación HTTP](https://developer.mozilla.org/es/docs/Web/HTTP/Authentication)
* [Restricting Access with HTTP Basic Authentication (nginx)](https://docs.nginx.com/nginx/admin-guide/security-controls/configuring-http-basic-authentication/)
* [Autenticación y Autorización (apache)](https://httpd.apache.org/docs/2.4/howto/auth.html)
* [htpasswd - Manage user files for basic authentication](https://httpd.apache.org/docs/2.4/en/programs/htpasswd.html)
{{< /reference >}}

{{% /post %}}