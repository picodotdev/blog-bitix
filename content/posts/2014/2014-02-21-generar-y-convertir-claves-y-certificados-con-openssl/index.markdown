---
pid: 13
type: "post"
title: "Generar y convertir claves y certificados con OpenSSL"
url: "/2014/02/generar-y-convertir-claves-y-certificados-con-openssl/"
date: 2014-02-21T17:36:21+01:00
updated: 2020-07-27T12:00:00+02:00
index: true
rss: true
sharing: true
comments: true
imagePost: "logotype:openssl.svg"
tags: ["programacion", "seguridad", "software", "software-libre", "web"]
summary: "Usando los comandos expuestos en este artículo y con OpenSSL podemos crear una clave pública y privada para usarlo con ssh o para cifrar y descifrar mensajes, un certificado autofirmado que podremos usar en un servidor de aplicaciones para usar un protocolo seguro y también convertir las claves y certificados a uno de los formatos aceptados por la aplicación que usemos."
---

{{% post %}}

{{< logotype image1="openssl.svg" >}}

Para un uso personal como enviar correos o archivos cifrados o firmados digitalmente usar [GnuPG](https://elblogdepicodev.blogspot.com.es/2013/11/introduccion-la-criptografia-e-inicio-con-gpg.html) es una buena opción. En Internet los servidores también se aprovechan del uso de criptografía para realizar comunicaciones seguras entre el usuario y el servidor.

Para hacer uso en un servidor de una comunicación https donde los datos viajan cifrados y sin que otras partes salvo el usuario y el servidor puedan acceder a los datos necesitamos un certificado digital. Un certificado es un archivo que contiene la clave pública sirviéndonos para verificar su autenticidad. Un certificado autofirmado es un certificado firmado con la misma clave privada asociada a la clave pública que contiene el certificado. Un certificado autofirmado es suficiente para un entorno de pruebas pero en un servidor para proporcionar confianza a los usuarios deberemos solicitar que una autoridad de certificados que nos firme con su clave nuestro certificado, si el usuario confía en esa autoridad de certificado puede de esta manera confiar en nuestro certificado y clave pública. Varias entidades de registro de dominios o alojamiento web ofrecen la compra de certificados SSL, en el artículo [Certificado SSL, de empresa, «wildcard» y de validación extendida][blogbitix-77] comento con un poco más detalle los varios tipos de certificados y algunas opciones donde obtenerlos o comprarlos.

Dependiendo del tipo de certificado que solicitemos y nos entregue la autoridad de certificado el usuario podrá ver que está simplemente accediendo a un servidor con conexión segura, ver los detalles de nuestro certificado y en algunos casos el usuario podrá ver en la barra de direcciones en verde el nombre de la entidad, que puede darle al usuario más confianza y ver que realmente está accediendo al servidor correcto y no a uno que esté intentando suplantar una identidad. En este último caso la barra de direcciones no tendría en verde el nombre de la entidad, esto es algo que como usuarios debemos comprobar al acceder a determinados sitios de forma segura.

Con la herramienta [OpenSSL][openssl] y los siguientes comandos podemos generar claves y certificados y realizar las conversiones entre formatos que necesitemos. Una vez que disponemos de un certificado y del formato en el que necesitemos podemos hacer uso de él, por ejemplo, en un servidor de páginas web o aplicaciones para proporcionar acceso mediante el protocolo HTTPS y proporcionar seguridad SSL. En otros artículos muestro [cómo configurar SSL/TLS en un servidor Tomcat, JBoss, WildFly, Lighttpd, Nginx o Apache][blogbitix-14], por otro lado usar HTTPS es un requisito para [utilizar el protocolo HTTP/2 en un servidor web][blogbitix-129].

{{< tableofcontents >}}

## Crear claves y certificados

### Crear una clave privada y pública

Para generar un par de claves RSA pública y privada que nos permitan tanto cifrar datos como realizar firmas se emplea el siguiente comando:

{{< code file="script-1.sh" language="bash" options="" >}}

Para cifrar la clave generada con el algoritmo _aes256_ y protegerla por una contraseña se puede emplear el siguiente comando, en realidad al generar la clave indicando la misma opción _-aes256_ en el comando anterior la clave se generará cifrada y protegida por una contraseña. Para cambiar la contraseña es el mismo comando y el segundo comando elimina la contraseña y la descifra:

{{< code file="script-12.sh" language="bash" options="" >}}

El contenido de un archivo de clave privada sin cifrar tiene el siguiente aspecto (los tres puntos son líneas de contenido omitidas).

{{< code file="localhost.key" language="plain" options="" >}}

### Exportar la clave pública

El archivo generado al crear el par de claves contiene tanto la clave pública como la privada. La privada no se debe distribuir y se debe mantener protegida de forma que solo la conozca su propietario por ejemplo guardándola en una base de datos de KeePassXC como un archivo adjunto de [la aplicación KeePassXC][blogbitix-196]. La clave pública es la que se distribuye a otras personas o entidades. Para extraer la clave pública del archivo generado anterior por OpenSSL usamos el siguiente comando:

{{< code file="script-9.sh" language="bash" options="" >}}

También se puede obtener la clave pública en formato [OpenSSH][openssh] y una representación gráfica de la huella digital.

{{< code file="script-10.sh" language="bash" options="" >}}

{{< code file="script-15.sh" language="bash" options="" >}}

### Obtener la huella digital de la clave pública

La huella digital de una clave pública sirve para comprobar que la clave es la esperada. Son una cadena de números y letras pudiendo estar cada pareja de caracteres separados por _:_.

{{< code file="script-11.sh" language="bash" options="" >}}

### Crear un certificado

Un certificado permite utilizar el protocolo seguro HTTPS en un servidor web y contiene la firma de una tercera parte que valida nuestra clave pública como auténtica. Para que esa tercera parte pueda firmar nuestra clave deberemos generar una petición de firma de certificado y enviársela a la autoridad de certificado que nos lo devolverá firmado. La petición firma de certificado se crea con el siguiente comando:

{{< code file="script-2.sh" language="bash" options="" >}}

Si no queremos tratar con una autoridad de certificado, ya que cobran por la firma, podemos crear un certificado autofirmado que puede ser suficiente para un entorno de pruebas de un servidor web. El comando para generar el certificado autofirmado es, la opción _-subj_ indica la información del sujeto a autenticar:

{{< code file="script-3.sh" language="bash" options="" >}}

## Convertir un certificado a otros formatos

Dependiendo de la autoridad de certificado el certificado puede estar en diferentes formatos, también dependiendo del servidor donde se quiera usar es necesario convertirlo a al formato adecuado. OpenSSL permite para hacer las conversiones entre formatos DER, PEM y PKCS#12.

### Convertir un certificado en formato DER (.crt .cer .der) a PEM

{{< code file="script-4.sh" language="bash" options="" >}}

### Convertir un certificado en formato PEM a DER

{{< code file="script-5.sh" language="bash" options="" >}}

### Convertir un certificado en formato PEM y una clave privada a PKCS#12 (.pfx .p12)

{{< code file="script-6.sh" language="bash" options="" >}}

### Convertir un archivo en formato PKCS#12 (.pfx .p12) que contiene una clave privada y certificado a PEM

{{< code file="script-7.sh" language="bash" options="" >}}

### Convertir PKCS#12 a keystore JKS

{{< code file="script-8.sh" language="bash" options="" >}}

## Examinar certificados

### Examinar un certificado

{{< code file="script-13.sh" language="bash" options="" >}}

### Examinar el certificado de un servidor web

{{< code file="script-14.sh" language="bash" options="" >}}

## Autoridad de certificación

Los comandos anteriores permiten generar un certificado autofirmado válido para proporcionar una conexión cifrada entre un servidor y un cliente como es el caso de un servidor web y un navegador web. Pero los certificados autofirmados no permiten todas las validaciones de seguridad, el cliente no puede confiar en que realmente se está conectando al nombre del dominio del servidor que el certificado incluye y por ello en el cliente hay que eliminar la validación de comprobación del certificado. Para mayor seguridad y en un entorno de producción se ha de utilizar una autoridad de certificación, es posible [crear una autoridad de certificación propia con comandos de OpenSSL][blogbitix-506].

{{< reference >}}
* [OpenSSL][openssl]
* [The Most Common OpenSSL Commands](http://www.sslshopper.com/article-most-common-openssl-commands.html)
{{< /reference >}}

{{% /post %}}
