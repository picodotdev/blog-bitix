---
pid: 506
type: "post"
title: "Comandos para crear una autoridad de certificación (CA) con OpenSSL"
url: "/2020/08/comandos-para-crear-una-autoridad-de-certificacion-ca-con-openssl/"
aliases: ["/2020/08/comandos-para-crear-certificados-como-una-autoridad-de-certificacion-con-openssl/"]
date: 2020-08-07T15:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:firefox-certificates.png"
tags: ["gnu-linux", "planeta-codigo"]
summary: "Los certificados autofirmados no son suficientes para un entorno de producción. En producción hay que usar certificados firmados por una entidad de confianza. AWS Certificate Manager ofrece la suya pero tiene un coste elevado y los certificados de otras entidades tampoco son baratos. Para certificados de uso interno en una organización que proporcionan comunicaciones seguras OpenSSL permite con una serie de comandos crear una autoridad de certificación o CA en la que los servidores y clientes internos confíen. Las funciones de la CA incluyen crear certificados a partir de las solicitudes de los certificados para los servidores y también la revocación y renovación de certificados."
---

{{% post %}}

{{< logotype image1="openssl.svg" >}}

Para [usar el protocolo seguro HTTPS en un servidor web][blogbitix-14] es necesario al menos [generar un certificado autofirmado][blogbitix-13] que incluya el dominio del sitio web. El certificado autofirmado proporciona comunicaciones seguras entre el servidor y cliente pero los clientes no lo consideran de confianza de modo que han de omitir la validación del certificado, un entorno de desarrollo o pruebas es suficiente pero en un escenario de producción para añadir más seguridad donde hay varios servidores que además requieren y validan también el certificado del cliente es necesario utilizar certificados generados por una entidad en la que se confíe, esta es la autoridad de certificación. La autoridad de certificación o CA es una entidad en la que se confía, si una CA ha firmado digitalmente un certificado esta asegura que el certificado pertenece a quien dice pertenecer. Las funciones de una CA son firmar los certificados que se le envían, de revocar los certificados cuando han sido comprometidos o de renovarlos cuando su validez expira.

[AWS][amazon-web-services] en su oferta de productos tiene [AWS Certificate Manager][amazon-certificate-manager] que hace las funciones de autoridad de certificados, delega la complejidad de hacer las funciones de autoridad de certificados pero su inconveniente es que tiene el significativo precio de $400 mensuales más un pequeño coste por certificado emitido. La herramienta [OpenSSL][openssl] permite hacer las funciones de una autoridad de certificación con un conjunto de comandos.

{{< tableofcontents >}}

### Crear la clave y certificado de la autoridad de certificación raíz

El certificado de una autoridad de certificación es un certificado autofirmado que se utilizar para comprobar que la firma de los certificados que emite la entidad es válida. Es necesario crear un par de claves asimétricas privada y pública y posteriormente generar su certificado.

{{< code file="ca-root-1.sh" language="bash" options="" >}}

El certificado de la CA raíz es simplemente un certificado autofirmado.

{{< code file="ca-root-2.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:archivos-ca.png" optionsthumb1="300x250" title1="Archivos y directorios de la autoridad de certificación con OpenSSL"
    caption="Archivos y directorios de la autoridad de certificación con OpenSSL" >}}

Al crear el certificado se utiliza un archivo de configuración donde se guardan las opciones por defecto de configuración que determinan varios aspectos de OpenSSL como directorios, políticas, fecha de validez de los certificados emitidos, campos de los certificados que emite o perfiles de firma entre algunos otros.

{{< code file="ca/openssl.conf" language="ini" options="" >}}

### Crear la clave y certificado de la autoridad de certificación intermedia

De la confianza en la CA raíz depende toda la seguridad de los certificados que emite, que su clave privada y pública o certificado sean comprometidos es posiblemente lo peor que le puede ocurrir a una CA. Para minimizar el riesgo se intenta hacer el menor uso posible de la clave privada y pública de la autoridad de certificación raíz, para esto se suele crear una autoridad de certificación intermedia que es la que realmente firma, revoca y renueva los certificados para el servidor, clientes o usuarios. Si la CA intermedia es comprometida aún siendo también un problema grave de seguridad al menos la CA raíz puede crear una nueva CA intermedia en la que se pueda confiar.

La CA intermedia tiene su propio par de claves asimétricas privada y pública, también tiene su certificado que en este caso está firmado por la CA raíz.

{{< code file="ca-intermediate-1.sh" language="bash" options="" >}}

La CA raíz firma únicamente el certificado de la CA intermedia. La CA intermedia generar la solicitud de firma de certificado, la CA raíz lo firma y se genera el certificado firmado.

{{< code file="ca-intermediate-2.sh" language="bash" options="" >}}

La CA recibe la solicitud de firma del certificado de la CA intermedia y la firma. La CA intermedia recibe las solicitudes de firma de certificados para los servidores, clientes y usuarios y genera los certificados firmados.

{{< code file="ca-intermediate-3.sh" language="bash" options="" >}}

Una vez generado el certificado de la CA intermedia se puede inspeccionar el certificado y validar que la firma sea correcta utilizando el certificado de la CA raíz.

{{< code file="ca-intermediate-4.sh" language="bash" options="" >}}

Para validar los certificados emitidos por la CA intermedia se genera una cadena de certificados que incluye los certificados de la CA raíz y la CA intermedia, la cadena de certificados es simplemente la unión del contenido de ambos certificados.

{{< code file="ca-intermediate-5.sh" language="bash" options="" >}}

La CA intermedia también utiliza su propio archivo de configuración de OpenSSL. En él se incluye la opción _copy_extensions_ que permite copiar algunos atributos de la solicitud al certificado que genera, esto sirve para incluir en el certificado los múltiples _Subject Alternative Name_ o SAN enviados en la solicitud. Si se permite emitir certificados con el mismo _Subject_ es necesario utilizar la opción de configuración _unique_subject = no_.

{{< code file="intermediate/openssl.conf" language="ini" options="" >}}

### Crear y firmar el certificado del servidor, clientes o usuarios

Para cada servidor, cliente de servidor o usuario hay que crear nuevamente su clave privada y pública, crear la solicitud de firma o _Certificate Sign Request_ (CSR) que se envía a la autoridad de certificación intermedia para que lo firme y esta devuelva un certificado firmado. En el caso de un certificado para un servidor se incluye en el campo _Common Name_ o _CN_ el nombre del dominio del servidor, un certificado multidominio contiene varios nombres de dominio para los que es válido en el campo _Subject Alternative Names_ o _SAN_, los certificados también se pueden asociar a una dirección IP concreta pero lo habitual es utilizar nombres de dominio.

Para crear el certificado firmado por la CA intermedia nuevamente el primer paso es generar la clave privada y pública y la solicitud de firma de certificado.

{{< code file="server-certs-1.sh" language="bash" options="" >}}

Una vez la CA intermedia firma la solicitud y genera el certificado se puede validar que la firma sea correcta.

{{< code file="server-certs-2.sh" language="bash" options="" >}}

Los clientes de un servidor como un navegador web cuando establecen la conexión segura comprueban que el nombre del dominio al que se ha realizado la solicitud esté contenido en los nombres de dominio y direcciones IP del certificado devuelto por el servidor y que esté firmado por una CA en la que se confía.

Para incluir en un certificados múltiples SAN ya sean dominios y direcciones IP es necesario indicarlo en el archivo de configuración al crear la solicitud en la sección _alt_names_ y habilitar la extensión _req_ext_.

{{< code file="server/openssl-consul-server.conf" language="ini" options="" >}}

Inspeccionando la información del documento se observa que en la sección _X509v3 Subject Alternative Name_ están incluidos los SAN adicionales.

{{< code file="server-certs-3.sh" language="bash" options="" >}}

### Añadir el certificado de la CA en el navegador Firefox

Los navegadores incluyen los certificados de algunas autoridades de certificados en las que se confía. En el caso de crear una CA propia como lo mostrado en este artículo y un certificado para un servidor firmado por esta autoridad de certificados el navegador mostrará una advertencia indicando que el certificado presentado no es de confianza antes de permitir entrar al sitio web, con este mensaje el usuario es consciente de que el certificado del servidor no es de confianza y si el usuario lo desea se permite el acceso al sitio web. Aún así el navegador muestra una advertencia en el icono de seguridad del sitio web de que hay un problema con el certificado.

{{< image
    gallery="true"
    image1="image:firefox-security-warning.png" optionsthumb1="300x250" title1="Advertencia con certificado no de confianza en Firefox"
    image2="image:firefox-certificate-warning.png" optionsthumb2="300x250" title2="Advertencia con certificado no de confianza en Firefox"
    caption="Advertencia con certificado no de confianza en Firefox" >}}

Para eliminar el mensaje de advertencia al acceder al sitio web y la advertencia del icono de seguridad del sitio web hay que instalar en el navegador el certificado de la CA raíz o intermedia. En el navegador web [Firefox][Firefox] se importa un nuevo certificado de una CA en la que se confía desde la opción _Preferencias > Privacidad y seguridad > Ver certificados > Autoridades > Importar_ en el navegador [Chrome][google-chrome] desde _Configuración > Privacidad y seguridad > Gestionar certificados_. Por defecto los navegadores web ya incorporan los certificados de varias CA importantes de internet.

{{< image
    gallery="true"
    image1="image:firefox-certificates.png" optionsthumb1="300x250" title1="Certificados de autoridades de certificación de confianza en Firefox"
    caption="Certificados de autoridades de certificación de confianza en Firefox" >}}

Una vez se importa el certificado de la CA el navegador muestra el icono de seguridad del sitio web con el icono sin advertencias indicando que no hay ningún problema de seguridad en la conexión con el sitio web, tampoco se muestra la página inicial de advertencia.

{{< image
    gallery="true"
    image1="image:firefox-certificate-valid.png" optionsthumb1="300x250" title1="Certificado de confianza y conexión segura en Firefox"
    caption="Certificado de confianza y conexión segura en Firefox" >}}

### Revocar y renovar un certificado

Además de firmar y generar de certificados las funciones de una CA es revocar un certificado cuando se ha comprometido y ya no es de confianza por un problema de seguridad y renovar de los certificados cuando se fecha de validez expira. 

Una vez se revoca un certificado hay dos formas en la que la CA permite conocer si un certificado ha sido revocado, con _Certificate Revocation List_ (CRL) o _Online_Certificate_Status_Protocol_ (OCSP) aunque CRL ha quedado en desuso y se prefiere OCSP. El problema del método CRL es que hay una latencia entre el momento en que se realiza la revocación esta ventana de tiempo no es deseable para evitar problemas de seguridad con la mayor inmediatez, la revocación se publica en el registro de CRL, su segundo problema es que en una CA grande con un número grande de certificados revocados el registro de CRL será también grande. OCSP también permite conocer si un certificado ha sido revocado, evita la latencia de CRL ya que no depende de un registro y requiere menos tráfico para realizar la comprobación ya que no requiere la descargar del registro CRL, pero introduce otros problemas como que debe soportar una número enorme de peticiones de comprobación de certificado ya que se hace una petición por cada visita a un sitio web, en el caso de una CA importante que tiene un número de certificados emitidos que usan sitios con mucho tráfico de internet es un problema. El segundo problema es una pérdida de privacidad para los usuarios ya que la CA al recibir una solicitud de comprobación de certificado por cada visita podría rastrear a los usuarios. Para evitar o al menos mitigar en gran medida los problemas de OCSP se ha desarrollado [OCSP Stapling](https://en.wikipedia.org/wiki/OCSP_stapling).

La CA guarda los certificados que emite, con OpenSSL en la carpeta _newcerts_ con un número de serie, en el archivo a modo base de datos _index.txt_ almacena entre otras cosas el número de serie y el _Subject_ del certificado emitido. El comando para revocar un certificado incluye como argumento el certificado a revocar y opcionalmente el motivo de la revocación. El archivo _index.txt_ se actualiza y en la entrada asociada al certificado se indica una _R_ para indicar que ha sido revocado.

Si la revocación del certificado se realiza por un problema de seguridad en el que la clave privada y pública asociada al certificado han sido comprometidas es necesario revocar el certificado comprometido, generar un nuevo par de claves para evitar suplantaciones de identidad y emitir un nuevo certificado generado a partir de las nuevas claves privada y pública.

{{< code file="ca-intermediate-revoke.sh" language="bash" options="" >}}
{{< code file="index.txt" language="plain" options="" >}}

Al revocar un certificado la CA regenera el CRL.

{{< code file="ca-intermediate-crl.sh" language="bash" options="" >}}

Los certificados se emiten con una de caducidad, llegado en el tiempo a la fecha de caducidad el certificado ya no se considera válido. En este caso hay que renovarlo. El servidor, cliente o usuario genera una nueva solicitud de certificado a partir de las mismas u otras nuevas claves privada y pública, la autoridad intermedia la recibe y realiza dos acciones: revoca el certificado anterior y genera un nuevo certificado a partir de la nueva solicitud de certificado. El comando de OpenSSL de la autoridad de certificados no permite que haya dos certificados con el mismo _Subject_ válidos de modo que primero hay que revocar el certificado anterior. Los comandos son los mismos que los mostrados anteriormente para cada una de las acciones.

{{% sourcecode git="blog-ejemplos/tree/master/CertificateAuthority" command="./ca.sh && ./server-certs.sh" %}}

{{< reference >}}
* [OpenSSL Certificate Authority](https://jamielinux.com/docs/openssl-certificate-authority/create-the-root-pair.html)
* [How do you sign a Certificate Signing Request with your Certification Authority?](https://stackoverflow.com/questions/21297139/how-do-you-sign-a-certificate-signing-request-with-your-certification-authority)
* [Know about SAN Certificate and How to Create With OpenSSL](https://geekflare.com/san-ssl-certificate/)
* [Subject Alternative Name not present in certificate](https://stackoverflow.com/a/30977497)
* [Get your certificate chain right](https://medium.com/@superseb/get-your-certificate-chain-right-4b117a9c0fce)
* [Consul Common Error Messages](https://www.consul.io/docs/common-errors.html)
* [Certificate management](https://tldp.org/HOWTO/SSL-Certificates-HOWTO/x195.html)
* [Revoke Certificates](https://roll.urown.net/ca/ca_revoke.html)
* [How do I issue multiple certificates for the same Common Name?](https://serverfault.com/questions/810557/how-do-i-issue-multiple-certificates-for-the-same-common-name/810608)
{{< /reference >}}

{{% /post %}}
