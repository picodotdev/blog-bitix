---
pid: 351
title: "Almacenar cifrados los valores de configuración sensibles en Spring Cloud Config"
url: "/2018/09/almacenar-cifrados-los-valores-de-configuracion-sensibles-en-spring-cloud-config/"
date: 2018-09-30T01:30:00+02:00
updated: 2018-09-30T01:40:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
series: ["spring-cloud"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="spring.svg" title1="Spring" width1="200" image2="java.svg" title2="Java" width2="200" >}}

Para no tener que hacer cambios en el código que implica recompilar la aplicación y generar de nuevos los artefactos al cambiar algunos valores de la aplicación se utilizan los archivos de configuración. Los archivos de configuración son archivos de texto plano que pueden seguir algún formato como _properties_, _xml_ o _yaml_, externos a la aplicación pero que lee sus propiedades al iniciarse. Algunas propiedades de configuración de ejemplo pueden ser la cadena de conexión a una base de datos, el usuario y contraseña.

Dado que algunos valores de configuración son sensibles como en el ejemplo anterior la cadena de conexión, usuario y contraseña es recomendable por mayor seguridad almacenar estos valores cifrados y que la aplicación los descifre con la clave correspondiente al necesitar su valor original.

[Spring Cloud Config][spring-cloud-config] permite guardar los archivos de configuración con algunos valores cifrados. Hay varias posibilidades de configuración para guardar los datos cifrados: mediante clave simétrica, clave privada-pública, guardarlos en el servicio externo [Vault][vault] de [Hasicorp][hasicorp], mantenerlos cifrados solo en el almacenamiento persistente o transmitirlos cifrados y que sea el cliente el que los descifre.

Utilizando la forma más simple para mantener los datos cifrados con una clave simétrica en el servicio de configuración hay que mantener en una propiedad de configuración la clave simétrica para cifrar y descifrar los datos, _encrypt.key_. En este ejemplo la clave simétrica y las propiedades cifradas están en archivos de configuración diferentes pero incluidos en el mismo servidor de configuración. Esto no parece que aporte mucha seguridad ya que si se tiene acceso al archivo de configuración de un servicio con una propiedad cifrada probablemente se tenga acceso al archivo con la clave cifrada y la medida de seguridad no es útil. Sin embargo, esto permite al estar separados los archivos de configuración añadir el archivo con la propiedad cifrada a un repositorio público sin peligro siempre y cuando la clave de cifrado se mantenga en secreto. Los archivos de configuración de los servicios en el servidor de configuración se podrían añadir a un repositorio de Git.

{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "bootstrap.yml" >}}

Definida la clave simétrica e iniciado el servidor de configuración este ofrece dos _endpoints_ para cifrar y descifrar datos. Utilizando el de cifrado se obtiene el valor cifrado del dato sensible que se quiere proteger. Con el _endpoint_ de descifrado se puede descifrar. Se observa que utilizando varias veces el _endpoint_ de cifrado se devuelve en cada una un valor distinto, sin embargo, descifrando cada uno de estos valores con el _endpoint_ de descifrado siempre se obtiene el valor original. Esto es debido seguramente a que en la operación de cifrado se utiliza la técnica del _salt_ para que a los valores cifrados se les pueda aplicar un ataque de diccionario, el _salt_ es incluido en el valor devuelto para que la operación de descifrado devuelva el valor original.

{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "curl-1.sh" >}}

El valor cifrado obtenido por este _endpoint_ se puede guardar en los archivos de configuración entrecomillándolo y precediéndolo con la cadena _{cipher}_.

{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "client.yml" >}}

En este caso el servicio al iniciarse obtiene su configuración del servicio de configuración, los datos se transmiten en forma plana sin cifrar y el cifrado utilizando en el servidor de configuración es transparente para el cliente. Accediendo al _endpoint_ del servidor de configuración que devuelve la configuración de un servicio con una propiedad cifrada se observa que al obtener el valor se devuelve en texto plano al cliente, esta petición es la misma que hace el servicio para obtener su configuración, de modo que aunque la información está cifrada en el servidor de configuración se transmite al servicio sin cifrar en texto plano. En este ejemplo se utiliza el protocolo inseguro HTTP, lo recomendable es utilizar el protocolo HTTPS para cifrar el tráfico entre el servidor de configuración y el cliente de modo que los valores sensibles queden protegidos también en la transmisión. 

{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "curl-2.sh" >}}

Iniciado el servicio de descubrimiento, el de configuración y un servicio que tiene un dato cifrado de configuración el valor que obtiene está ya descifrado. En este caso el servicio _client_ obtiene el valor de la propiedad _config.password_ descifrado con el valor _secret_.

{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "gradlew-run.sh" >}}
{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "System.out" >}}

{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "Main.java" >}}
{{< gist picodotdev caccab2d4558ec75babab6ef71808f37 "DefaultConfiguration.java" >}}

{{% code git="blog-ejemplos/tree/master/SpringCloud" command="./gradle-run.sh, ./curl-1.sh" %}}

{{% /post %}}
