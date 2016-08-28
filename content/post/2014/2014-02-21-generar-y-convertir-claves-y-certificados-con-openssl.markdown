---
pid: 13
title: "Generar y convertir claves y certificados con OpenSSL"
url: "/2014/02/generar-y-convertir-claves-y-certificados-con-openssl/"
date: 2014-02-21T17:36:21+01:00
updated: 2015-05-25T21:00:00+02:00
sharing: true
comments: true
tags: ["programacion", "seguridad", "software", "software-libre", "planeta-linux", "planeta-arch-linux"]
series: ["web"]
summary: "Usando los comandos expuestos en este artículo y con OpenSSL podemos crear una clave pública y privada para usarlo con ssh o para cifrar y descifrar mensajes, un certificado autofirmado que podremos usar en un servidor de aplicaciones para usar un protocolo seguro y también convertir las claves y certificados a uno de los formatos aceptados por la aplicación que usemos."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="openssl.png" title="OpenSSL" >}}

Para un uso personal como enviar correos o archivos cifrados o firmados digitalmente usar [GnuPG](http://elblogdepicodev.blogspot.com.es/2013/11/introduccion-la-criptografia-e-inicio-con-gpg.html) es una buena opción. En Internet los servidores también se aprovechan del uso de criptografía para realizar comunicaciones seguras entre el usuario y el servidor.

Para hacer uso en un servidor de una comunicación https donde los datos viajan cifrados y sin que otras partes salvo el usuario y el servidor puedan acceder a los datos necesitamos un certificado digital. Un certificado es un archivo que contiene la clave pública sirviéndonos para verificar su autenticidad. Un certificado autofirmado es un certificado firmado con la misma clave privada asociada a la clave pública que contiene el certificado. Un certificado autofirmado es suficiente para un entorno de pruebas pero en un servidor para proporcionar confianza a los usuarios deberemos solicitar que una autoridad de certificados que nos firme con su clave nuestro certificado, si el usuario confía en esa autoridad de certificado puede de esta manera confiar en nuestro certificado y clave pública. Varias entidades de registro de dominios o halojamiento web ofrecen la compra de certificados SSL, en el artículo [Certificado SSL, de empresa, «wildcard» y de validación extendida][blogbitix-77] comento con un poco más detalle los varios tipos de certificados y algunas opciones donde obtenerlos o comprarlos.

Dependiendo del tipo de certificado que solicitemos y nos entregue la autoridad de certificado el usuario podrá ver que está simplemente accediendo a un servidor con conexión segura, ver los detalles de nuestro certificado y en algunos casos el usuario podrá ver en la barra de direcciones en verde el nombre de la entidad, que puede darle al usuario más confianza y ver que realmente está accediendo al servidor correcto y no a uno que esté intentando suplantar una identidad. En este último caso la barra de direcciones no tendría en verde el nombre de la entidad, esto es algo que como usuarios debemos comprobar al acceder a determinados sitios de forma segura.

Con la herramienta [OpenSSL][openssl] y los siguientes comandos podemos generar claves y certificados y realizar las conversiones entre formatos que necesitemos.

### Crear claves y certificados

#### Crear una clave privada y pública

Podemos generar un par de claves RSA que nos permitan tanto cifrar como realizar firmas, con OpenSSL usando el siguiente comando:

{{% gist id="9138093" file="script-1.sh" %}}

#### Exportar la clave pública

El archivo generado al crear el par de claves contiene tanto la clave pública como la privada. La privada no se debe distribuir y se debe mantener protegida de forma que solo la conozca su propietario. La clave pública es la que se distribuye a otras personas o entidades. Para extraer la clave pública del archivo generado anterior por OpenSSL usamos el siguiente comando:

{{% gist id="9138093" file="script-9.sh" %}}

#### Crear un certificado

Un certificado contiene la firma de una tercera parte que valida nuestra clave pública como auténtica. Para que esa tercera parte pueda firmar nuestra clave deberemos generar una petición de firma de certificado y enviársela a la autoridad de certificado que nos lo devolverá firmado. La petición firma de certificado se crea con el siguiente comando:

{{% gist id="9138093" file="script-2.sh" %}}

Si no queremos tratar con una autoridad de certificado, ya que cobran por la firma, podemos crear un certificado autofirmado que puede ser suficiente para un entorno de pruebas. El comando para generar el certificado autofirmado es:

{{% gist id="9138093" file="script-3.sh" %}}

### Convertir un certificado a otros formatos

Dependiendo de la autoridad de certificado el certificado puede estar en diferentes formatos, dependiendo del servidor donde tengamos idea de usarlo podemos necesitar convertirlo a otro formato. También podemos usar OpenSSL para hacer las conversiones.

#### Convertir un certificado en formato DER (.crt .cer .der) a PEM

{{% gist id="9138093" file="script-4.sh" %}}

#### Convertir un certificado en formato PEM a DER

{{% gist id="9138093" file="script-5.sh" %}}

#### Convertir un certificado en formato PEM y una clave privada a PKCS#12 (.pfx .p12)

{{% gist id="9138093" file="script-6.sh" %}}

#### Convertir un archivo en formato PKCS#12 (.pfx .p12) que contiene una clave privada y certificado a PEM

{{% gist id="9138093" file="script-7.sh" %}}

#### Convertir PKCS#12 a keystore JKS

{{% gist id="9138093" file="script-8.sh" %}}

Una vez que disponemos de un certificado y del formato en el que necesitemos podemos hacer uso de él, por ejemplo, en un servidor de páginas web o aplicaciones para proporcionar acceso mediante el protocolo HTTPS y proporcionar seguridad SSL. Pero eso será tema para la entrada [Configurar SSL en un servidor Tomcat, JBoss, WildFly, Lighttpd, Nginx o Apache][blogbitix-14].

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [OpenSSL][openssl]
* [The Most Common OpenSSL Commands](http://www.sslshopper.com/article-most-common-openssl-commands.html)
{{% /reference %}}

{{% /post %}}
