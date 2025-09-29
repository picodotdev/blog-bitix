---
pid: 679
type: "post"
title: "Cifrar y descifrar datos usando algoritmos de clave asimétrica con Java"
url: "/2023/03/cifrar-y-descifrar-datos-usando-algoritmos-de-clave-asimetrica-con-java/"
date: 2023-03-16T20:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "El lenguaje de programación Java ofrece clases e implementa varios algoritmos relacionados con la criptografía y seguridad. Con unas pocas líneas de código es posible listar los algoritmos soportados, generar claves, cifrar datos y descifrar datos. Soporta tanto criptografía de clave simétrica donde se usa la misma clave tanto para cifrar como para descifrar y como en este artículo se muestra criptografía asimétrica en la que se utiliza dos claves una la clave pública para cifrar datos y la clave privada para descifrar los datos."
---

{{% post %}}

{{< logotype image1="java.svg" >}}


Los algoritmos de clave simétrica que utilizan la misma clave para cifrar como para descifrar adolecen del problema conocido como [man-in-the-middle][wikipedia-man-in-the-middle-attack], el problema consiste en que no es posible compartir la clave privada entre el emisor y receptor del mensaje con garantía de que un intermediario no la haya alterado.

Aún con este problema los algoritmos de clave simétrica siguen siendo utilizados ya que tienen otras ventajas. Los algoritmos de clave asimétrica no tienen el problema de _man-in-the-middle_ aunque tienen otros defectos.

En el lenguaje de programación Java es posible generar un par de claves asimétricas, cifrar datos con la clave pública y descifrar los datos con la clave privada.

* [Cifrar y descifrar datos usando algoritmos de clave simétrica con Java][blogbitix-677]
* [Generar y convertir claves y certificados con OpenSSL][blogbitix-13]

{{< tableofcontents >}}

## Los algoritmos de clave asimétrica

Los algoritmos de clave asimétrica utilizan una clave para cifrar los datos y otra clave para descifrar los datos. Ambas claves están relacionadas matemáticamente de tal forma que los datos cifrados con una clave solo son descifrables por su clave asociada.

A una de las claves se le denomina clave pública porque esta puede ser compartida, es la que se utiliza para cifrar los datos que solo pueden ser descifrados por la clave privada. La clave privada se denomina así porque ha de mantenerse en secreto.

Varios algoritmos conocidos de clave asimétrica son los siguientes:

* [RSA](https://en.wikipedia.org/wiki/RSA_(cryptosystem))
* [DSA](https://en.wikipedia.org/wiki/Digital_Signature_Algorithm)
* [ECDSA](https://en.wikipedia.org/wiki/Elliptic_Curve_Digital_Signature_Algorithm)

{{< image
    gallery="true"
    image1="image:asymmetric-encryption-keys.svg" optionsthumb1="300x200" title1="Asymetric encryption keys"
    image2="image:public-key-encryption.svg" optionsthumb2="300x200" title2="Pulbic key encryption"
    caption="Asymmetric encryption keys and public key encryption" >}}

### Longitud de las claves

Los algoritmos de clave asimétrica necesitan claves más largas, de mayor número de bits, que las de los algoritmos de clave simétrica para ofrecer el mismo nivel de seguridad.

Algunos algoritmos de clave asimétrica permiten claves de 8192 bits de longitud aunque ya longitudes de 2048 bits se consideran seguros. Por el contrario los algoritmos de clave simétrica ofrecen con longitudes de 256 bits una seguridad equivalente.

## Utilizar algoritmos de clave asimétrica con Java

El lenguaje de programación Java soporta varios algoritmos de clave asimétrica, la generación de claves privada y pública, cifrado y descifrado de datos con las claves. Proporciona varias clases en el JDK disponibles para cualquier aplicación sin necesidad de librerías de terceros.

Para casos avanzados o tareas específicas en el ecosistema Java hay librerías en el ámbito de la seguridad y criptografía que se pueden añadir como una dependencia en un proyecto, una de ellas es [Bouncy Castle][bouncycastle].

Esta es la salida del programa en la que se muestra el texto de datos, el texto cifrado y el texto original descifrado y la clave privada y pública representada en formato PEM.

{{< code file="Main.out" language="plain" options="" >}}
{{< code file="build.gradle" language="groovy" options="" >}}

### Listar algoritmos de clave asimétrica

El siguiente código permite obtener que algoritmos soporta un determinado servicio de seguridad proporcionado por Java, en este caso el servicio para generar claves asimétricas. En el caso de Java 17 se soportan los algoritmos DSA y RSA.

{{< code file="Main-1.java" language="java" options="" >}}

### Generar un par de claves privada y pública

Antes de poder cifrar y descifrar datos con clave asimétrica hay que generar las claves privada y pública, son dos claves diferentes pero que están relacionadas matemáticamente y se generan al mismo tiempo.

Uno de los parámetros de configuración al generar las claves es la longitud de bits de las claves, a mayor longitud mayor seguridad con una mayor capacidad de cálculo requerido para el cifrado y descifrado. En el ejemplo se generan las claves con una longitud de 8192 bits que es un tamaño muy superior al necesario para la clave en los algoritmos de clave simétrica.

{{< code file="Main-2.java" language="java" options="" >}}

### Codificar la clave privada y pública en formato PEM

Las claves se suelen codificar en formato PEM y guardar en un archivo de texto. Java no ofrece una clase para realizar la codificación en un archivo PEM por lo que en este ejemplo se utilizan las clases _PemObject_ y _JcaPEMWriter_ de la librería Bouncy Castle.

{{< code file="Main-3.java" language="java" options="" >}}

### Cifrar datos con la clave pública

El proceso de cifrado de datos en un algoritmo de clave asimétrica se realiza con la clave pública. El proceso de cifrado con la clave pública es similar a como se hace con un algoritmo de clave simétrica.

{{< code file="Main-4.java" language="java" options="" >}}

### Descifrar datos con la clave privada

Los datos cifrados con la clave pública sólo es posible con la clave privada. El proceso de descifrado también es muy similar al descifrado con una clave simétrica.

{{< code file="Main-5.java" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaHashingEncrypt" command="./gradlew run" %}}

{{< reference >}}
* [RSA in Java](https://www.baeldung.com/java-rsa)
{{< /reference >}}

{{% /post %}}