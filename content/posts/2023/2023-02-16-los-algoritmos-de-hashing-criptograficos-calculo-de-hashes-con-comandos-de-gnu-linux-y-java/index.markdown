---
pid: 675
type: "post"
title: "Los algoritmos de hashing criptográficos, cálculo de hashes con comandos de GNU/Linux y Java"
url: "/2023/02/los-algoritmos-de-hashing-criptograficos-calculo-de-hashes-con-comandos-de-gnu-linux-y-java/"
date: 2023-02-16T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Los algoritmos de _hashing_ criptográficos son fundamentales en la firma digital y criptografía, pero también tienen su utilidad por sí mismos para la comprobación de la integridad. Se basan en un algoritmo y funciones matemáticas que transforman un conjunto de bytes en un número binario de longitud fija que constituye el _hash_ digital del contenido. Hay varios algoritmos de _hashing_ criptográficos y en GNU/Linux varios comandos que permiten calcular y comprobar el _hash_ de un archivo. En los lenguajes de programación como en el caso de Java se ofrecen clases y métodos para la generación y cálculo de _hashes_ en los algoritmos soportados."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

El cifrado o encriptación permite que solo el receptor del mensaje pueda leer los datos, la firma digital permite conocer que los datos han sido generados por quien los firma y al recibirlos no han sido modificados por una tercera persona. El cifrado y la firma digital utilizan claves para realizar el cifrado y para la firma digital.

El cifrado y la firma digital son operaciones muy importantes para garantizar la seguridad en multitud de ámbitos. Sin embargo, la utilización de claves añaden algo de complejidad que a veces no es necesaria, a veces utilizar simplemente un algoritmo de _hashing_ es suficiente y más sencillo ya que no requiere la utilización de claves.

* [Cifrar y descifrar datos usando algoritmos de clave simétrica con Java][blogbitix-677]

{{< tableofcontents >}}

## Los algoritmos de _hashing_ criptográficos

Los algoritmos de _hashing_ transforman un conjunto arbitrario de bytes a una cadena binaria de bytes de longitud fija, dependiendo del algoritmo la longitud fija resultante es diferente, algunos generan como resultado un número de 160 bits y los más seguros llegando a 512 bits.

Una aplicación práctica de los algoritmos de _hashing_ es para evitar guardar las contraseñas de los usuarios en la base de datos en texto plano en la que interviene un algoritmo de _hashing_, utilizando [la técnica conocida como _salted password hashing_][blogbitix-75].

Aunque las redes actuales ofrecen un gran ancho de banda y una fiabilidad notable siguen considerándose un medio poco fiable, a veces fallan y generan errores en la transmisión como pérdida de paquetes o alteración del contenido aún con las medida implementadas en el algoritmo de transmisión de paquetes TCP utilizado de forma mayoritaria en internet.

Al descargar un archivo grande es útil que el creador adjunte el _hash_ del contenido para comprobar su integridad una vez descargado. Comprobar la integridad proporciona dos garantías: que el contenido es el original y no ha sido modificado por un intermediario y que el archivo descargado no se ha corrompido en la descarga.

La seguridad de los algoritmos de cifrado se basan en que sea muy difícil encontrar la cadena original que produzca un determinado _hash_, que dada una cadena sea difícil encontrar otra cadena que produzca el mismo _hash_ y que sea difícil que dos cadenas distintas produzcan el mismo _hash_. Otra de sus propiedades es que aún con el cambio de un bit en el contenido _hasheado_ el _hash_ resultante es muy diferente. También por seguridad son algoritmos que distribuyen de forma uniforme los _hashes_ en todo el rango de posibles resultados.

Hay diferentes algoritmos de _hashing_ criptográfico:

* MD5: genera números de 160 bits de longitud. Ya se considera un algoritmo de _hashing_ poco seguro ya que con la capacidad de computación actual es posible encontrar un cadena de bytes que produzca el mismo _hash_.
* SHA-1: genera números de 160 bits de longitud. También se considera un algoritmo de _hashing_ vulnerable pudiendo encontrar colisiones, encontrar un contenido que genere un determinado _hash_.
* SHA-2: es la segunda versión de SHA utilizando algoritmos y funciones criptográficas más seguras. También se conoce como SHA-224, SHA-256, SHA-384 o SHA-512 donde el número indica el número de bits de _hash_ de resultado.
* SHA-3: es la última adición a los algoritmos SHA pero que es diferente de forma significativa en su estructuras internas a MD5, SHA-1 y SHA-2. También soporta diferentes longitudes de _hash_ denominándose SHA3-224, SHA3-256, SHA3-384 o SHA3-512.

## Comandos de GNU/Linux para el cálculo de _hashes_

El sistema operativo [GNU][gnu]/[Linux][linux] ofrece varios comandos, uno para cada algoritmo, para utilizar y calcular los _hashes_ de una cadena de texto o un archivo. Basta indicar el algoritmo y el contenido del que calcular su hash. En los siguientes comandos se calcula el _hash_ de la siguiente supuesta contraseña generada con [el gestor de contraseñas KeePassXC][blogbitix-196].

El resultado de los algoritmos de _hashing_ es un número de un determinado número de bits, los _hashes_ se representan como un número en base 16, lo que para un número de 512 bits da una cadena de 128 números hexadecimales.

{{< code file="command-hash.sh" language="java" options="" >}}
{{< code file="command-hash.out" language="plain" options="" >}}

El paquete _sha3sum_ hay que instalarlo manualmente si no está instalado, los otros comandos generalmente están instalados por ser dependencia de otro.

## Usar algoritmos de _hashing_ con Java

Desde un lenguaje de programación de alto nivel como Java también puede ser útil utilizar algoritmos de _hashing_. Java incorpora una API e implementa de forma eficiente los estándares de _hashing_ criptográficos para ser utilizados de forma fácil por los programas.

### Listar algoritmos de _hashing_ soportados por el JDK

Los algoritmos de _hashing_ soportados dependen de la versión del JDK, en el JDK 9 se añadió el soporte para SHA-3. Es posible listar qué algoritmos de _hashing_ soporta el JDK con el que se ejecuta un programa con el siguiente código a través de la clase [Security](javadoc17:java.base/java/security/Security.html).

{{< code file="Main-1.java" language="java" options="" >}}
{{< code file="Main-1.out" language="plain" options="" >}}

Estos son los algoritmos de _hashing_ soportados por el JDK 17.

{{< code file="java-17-hash-algorithms.out" language="plain" options="" >}}

Por el JDK 11.

{{< code file="java-11-hash-algorithms.out" language="plain" options="" >}}

Y por el JDK 8.

{{< code file="java-8-hash-algorithms.out" language="plain" options="" >}}

### Calcular _hashes_ con las clases del JDK de Java

La clase principal para calcular _hashes_ criptográficos en Java es [MessageDigest](javadoc17:java.base/java/security/MessageDigest.html), se obtiene una instancia de la clase a partir del algoritmo que se desea utilizar para calcular el _hash_. La clase _MessageDigest_ ofrece los métodos [update](javadoc17:java.base/java/security/MessageDigest.html#update(byte)) para proporcionar los bytes del contenido sobre él que calcular el _hash_ y para terminar el cálculo se utiliza el método [digest](javadoc17:java.base/java/security/MessageDigest.html#digest()).

{{< code file="Main-2.java" language="java" options="" >}}
{{< code file="Main-2.out" language="plain" options="" >}}

El resultado es un array de bytes que hay que codificar en hexadecimal. En el JDK 17 se ha añadido la clase [HexFormat](javadoc17:java.base/java/util/HexFormat.html) que permite hacer la conversión fácilmente sin dependencias adicionales.

Si se desea calcular el _hash_ de un flujo de [InputStream](javadoc17:java.base/java/io/InputStream.html) u [OutputStream](javadoc17:java.base/java/io/OutputStream.html) la API de Java ofrece las clases [DigestInputStream](javadoc17:java.base/java/security/DigestInputStream.html) y [DigestOuputStream](javadoc17:java.base/java/security/DigestOutputStream.html). En este ejemplo se calcula el SHA de la imagen del medio de instalación de la distribución [Arch Linux][archlinux] del que su _hash_ se publica en la misma ubicación que la imagen ISO y en la [página de descargas de Arch Linux][archlinux-download].

{{< code file="Main-3.java" language="java" options="" >}}

Este es el _hash_ calculado por con Java.

{{< code file="Main-3.out" language="java" options="" >}}

### Calcular _hashes_ con una librería de terceros

En caso de estar obligado a utilizar un JDK antiguo que no soporte alguno de los algoritmos de _hashing_ hay que recurrir a una librería de terceros. Hay varias, entre ellas [Bouncy Castle][bouncycastle], [Guava][guava] y [Apache Commons][apache-commons].

{{% sourcecode git="blog-ejemplos/tree/master/JavaHashingEncrypt" command="./gradlew run" %}}

{{< reference >}}
* [Hash function](https://en.wikipedia.org/wiki/Hash_function)
* [Cryptographic hash function](https://en.wikipedia.org/wiki/Cryptographic_hash_function)
* [Secure Hash Algorithms](https://en.wikipedia.org/wiki/Secure_Hash_Algorithms)
* [SHA-256 and SHA3-256 Hashing in Java](https://www.baeldung.com/sha-256-hashing-java)
* [List of available MessageDigest Algorithms](https://mkyong.com/java/java-list-of-available-messagedigest-algorithms/)
* [Calculating SHA 3 Hash in Java](https://stackoverflow.com/questions/30109958/calculating-sha-3-hash-in-java)
* [md5sum.1.en](https://man.archlinux.org/man/md5sum.1.en)
* [sha1sum.1.en](https://man.archlinux.org/man/sha1sum.1.en)
* [sha512sum.1.en](https://man.archlinux.org/man/sha512sum.1.en)
* [sha3sum.1.en](https://man.archlinux.org/man/sha3sum.1.en)
{{< /reference >}}

{{% /post %}}