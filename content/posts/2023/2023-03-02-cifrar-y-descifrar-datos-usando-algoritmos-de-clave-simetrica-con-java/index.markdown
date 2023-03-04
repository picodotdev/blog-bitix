---
pid: 677
type: "post"
title: "Cifrar y descifrar datos usando algoritmos de clave simétrica con Java"
url: "/2023/03/cifrar-y-descifrar-datos-usando-algoritmos-de-clave-simetrica-con-java/"
date: 2023-03-02T21:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Algunos datos son sensibles y necesitan especial protección como los datos personales, bancarios o relacionados con la seguridad como contraseñas. Para minimizar los riesgos de seguridad en caso de un fallo se suele cifrar los datos al persistirlos en la base de datos de modo que en caso de la base de datos sea filtrada los datos sigan protegidos siempre y cuando la clave que permite descifrarlos no se ha filtrado también. Java ofrece clases en su JDK que implementan los principales algoritmos para cifrar y descifrar datos."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

El cifrado de datos consiste en hacer ilegibles los datos originales para cualquiera que no tenga la clave, el descifrado es el proceso contrario que transforma los datos cifrados en los datos originales usando la clave que permite descifrarlos.

Los algoritmos de cifrado se dividen en dos grupos, los algoritmos de clave simétrica en los que se emplea una única clave para hacer el cifrado como el descifrado y los algoritmos de clave asimétrica en los que una clave pública permite cifrar datos que solo la clave privada es capaz de descifrarlos.

El cifrado de datos es muy útil desde el punto de vista de la seguridad y para proteger información personal al guardarla en un sistema de almacenamiento ya sea el el sistema de archivos o en una base de datos. También es muy útil y usado para proteger las comunicaciones en internet, en la navegación web el cifrado se usa con el protocolo _https_.

De hecho en el protocolo _https_ se usan ambos tipos de algoritmos en un primer momento un algoritmo de clave asimétrica para compartir la clave secreta entre los sistemas que se desean comunicar, la clave secreata es la que se utiliza para el cifrado en los datos transmitidos. Se utiliza el algoritmo asimétrico para compartir la clave secreta de forma segura y posteriormente un algoritmo simétrico para el cifrado por ser más rápido.

Además de usar cifrado para algunas operaciones es igual de importante para la seguridad utilizar últimas versiones de las dependencias que se usen, analizar las dependencias en busca de fallos de seguridad descubiertos y en el caso de Java utilizar una versión del JDK con mantenimiento, ya que las últimas versiones suelen incluir correcciones de seguridad.

* [Analizar y detectar fallos de seguridad en las dependencias de Java][blogbitix-616]

El cifrado de datos es una parte esencial e importante en algunas aplicaciones y para algunos datos, realizarlo correctamente es igual de importante. Java implementa los algoritmos de cifrado utilizados actualmente y proporciona clases para utilizarlas desde una aplicación en este lenguaje. El cifrado y descifrado es una operación que hay que implementar correctamente con lo que no conviene tratar de implementar un algoritmo de cifrado u ofuscación, lo correcto es utilizar un algoritmo estándar aún considerado seguro.

El producto de software [Vault][vault] de [Hashicorp][hashicorp] ofrece como servicio las operaciones de cifrado y descifrado, en vez de que cada aplicación implementa el cifrado y descifrado las aplicaciones pueden delegar en Vault utilizando la API que ofrece como un servicio.

* [Administrar secretos y proteger datos sensibles con Vault][blogbitix-424]

{{< tableofcontents >}}

## Algoritmos de cifrado con clave simétrica

En los algoritmos simétricos se utiliza la misma clave tanto para cifrar como para descifrar los datos. Las claves de los algoritmos simétricos son más pequeñas, proporcionan mayor seguridad y se siguen utilizando ya que son más rápidos en el cifrado y descifrado. El inconveniente de estos algoritmos es que es necesario compartir la clave secreta de alguna forma para evitar el [ataque _man-in-the-middle_][wikipedia-man-in-the-middle-attack] para lo que se utiliza un algoritmo de clave asimétrica.

En general, un algoritmo con claves de mayor cantidad de bits para el tamaño de la clave proporciona una mayor seguridad. De esta forma AES con una clave de 256 bits es más seguro que AES con una clave de 128 bits.  Los algoritmos de clave simétrica cifran los datos en bloques, algunos algoritmos utilizan bloques de 64 bits y otros de 128 bits. Entre los algoritmos de cifrado simétricos están los siguientes algunos han sido reemplazados por AES.

### AES

[AES][wikipedia-aes] (_Advanced Encryption Standard_) es un algoritmo de cifrado de clave simétrica que utiliza bloques de 128 bits. AES es uno de los algoritmos de cifrado más seguros y eficientes disponibles. Es compatible con claves de 128, 192 y 256 bits.

### 3DES

3DES (_Triple Data Encryption Standard_) es un algoritmo de cifrado de clave simétrica que utiliza bloques de 64 bits y una clave de 168 bits.

### Blowfish

Blowfish es un algoritmo de cifrado de clave simétrica que utiliza bloques de 64 bits y una clave de entre 32 y 448 bits. Fue desarrollado en 1993 y se utiliza en algunas aplicaciones.

### RC5

RC5 es un algoritmo de cifrado de clave simétrica que utiliza bloques de 64 bits y una clave de entre 0 y 2040 bits. Fue desarrollado en 1994 y se utiliza en algunas aplicaciones.

### IDEA

IDEA (_International Data Encryption Algorithm_) es un algoritmo de cifrado de clave simétrica que utiliza bloques de 64 bits y una clave de 128 bits. IDEA fue desarrollado en 1991 y es utilizado en algunas aplicaciones.

## Ejemplos de código de cifrado y descifrado con Java

### Listar algoritmos de cifrado soportados con Java

Java soporta varios algoritmos de cifrado simétrico dependiendo de la versión de Java que se pueden listar utilizando la API que ofrece el JDK.

{{< code file="Main-1.java" language="java" options="" >}}
{{< code file="Main-1.out" language="plain" options="" >}}

El siguiente código muestra el resultado de cifrar un texto asi como descifrar para obtener el valor original, también muestra el resultado de la operación de _hasing_ HMAC.

{{< code file="Main-2.java" language="java" options="" >}}
{{< code file="Main-2.out" language="plain" options="" >}}

### Generar una clave simétrica

En los algoritmos de clave simétrica es necesario generar la clave para realizar el cifrado y descifrado de datos. La clave es simplemente un número binario de cierta longitud habiendo dos forma de generarlo, una generando el número de forma aleatoria pero segura al que en la jerga de criptografía se le denomina material.  La clave en Java se representa por la clase [SecretKey](javadoc17:java.base/javax/crypto/SecretKey.html)

{{< code file="Main-3.java" language="java" options="" >}}

La segunda forma es generar la clave como una derivada de una contraseña. Un número de 256 bits aleatorio aunque se represente en hexadecimal es complicado de recordar para un humano, derivar la clave a partir de una contraseña es más fácil de recordar.

{{< code file="Main-4.java" language="java" options="" >}}

### Cifrar datos

Una vez generada la contraseña se cifran los datos, los datos cifrados sólo pueden ser devueltos a su estado original aplicando la operación inversa del algoritmo con la misma clave simétrica.

{{< code file="Main-5.java" language="java" options="" >}}

### Descifrar datos

{{< code file="Main-6.java" language="java" options="" >}}

### Cifrar flujos de datos y archivos

A veces se desea cifrar un flujo de datos de tamaño no conocido de antemano, las clases [CipherIntputStream](javadoc17:java.base/javax/crypto/CipherInputStream.html) e [CipherOutputStream](javadoc17:java.base/javax/crypto/CipherOutputStream.html) permite realizar el cifrado a un flujo de datos.

{{< code file="Main-7.java" language="java" options="" >}}

### Calcular el _hash_ HMAC

Los algoritmos criptográficos de _hashing_ proporcionan una huella digital de los datos que tienen ciertas propiedades de seguridad. Estos utilizan como entrada los datos y un algoritmo de _hashing_.

Otra utilidad utilizada en el cifrado es un algoritmo HMAC que calcula un _hash_ también a partir de los datos y el algoritmo de _hashing_ y adicionalmente una clave.

En un algoritmo de _hashing_ criptográfico el _hash_ variará únicamente a partir de los datos de entrada. En un algoritmo de HMAC el _hash_ varía en función de los datos y la clave secreta. Utilizando los mismos datos pero diferente clave el _hash_ obtenido es diferente.

{{< code file="Main-8.java" language="java" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaHashingEncrypt" command="./gradlew run" %}}

{{< reference >}}
* [Criptografía simétrica](https://es.wikipedia.org/wiki/Criptograf%C3%ADa_sim%C3%A9trica)
* [PBKDF2](https://en.wikipedia.org/wiki/PBKDF2)
* [Java AES Encryption and Decryption](https://www.baeldung.com/java-aes-encryption-decryption)
* [3DES in Java](https://www.baeldung.com/java-3des)
* [Encrypting and Decrypting Files in Java](https://www.baeldung.com/java-cipher-input-output-stream)
* [HMAC in Java](https://www.baeldung.com/java-hmac)
{{< /reference >}}

{{% /post %}}