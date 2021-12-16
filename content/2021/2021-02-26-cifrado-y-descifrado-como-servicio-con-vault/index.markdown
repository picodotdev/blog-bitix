---
pid: 557
type: "post"
title: "Cifrado y descifrado como servicio con Vault"
url: "/2021/02/cifrado-y-descifrado-como-servicio-con-vault/"
date: 2021-02-26T16:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hashicorp-vault.svg"
tags: ["gnu-linux", "planeta-codigo"]
series: ["hashicorp"]
summary: "Implementar la seguridad en una aplicación no es sencillo, cuando un sistema se compone de múltiples aplicaciones los posibles fallos de seguridad se multiplican. Vault es una herramienta que permite centralizar y delegar varios aspectos de las aplicaciones relativos a la seguridad, uno de ellos es el cifrado y descifrado de los datos para su almacenamiento y recuperación de una base de datos. Entre sus funcionalidades Vault ofrece como servicio el cifrado y descifrado de datos."
---

{{% post %}}

{{< logotype image1="hashicorp-vault.svg" image2="hashicorp.svg" >}}

La seguridad en una aplicación involucra a los datos, tanto en la transmisión entre aplicaciones como en el almacenamiento a ambos se les conoce por la seguridad de los datos en tránsito o _transit_ y por la seguridad en persistencia o _rest_. La seguridad de los datos en tránsito se consigue utilizando protocolos de comunicación seguros como TLS y la seguridad de los datos en persistencia se consigue cifrando los datos ya sea a nivel de sistema de archivos, base de datos o datos específicos aplicando un algoritmo de cifrado.

No es fácil implementar la seguridad, por un lado hay que utilizar algoritmos de cifrado considerados seguros, en un sistema grande en el que hay múltiples aplicaciones que utilizan potencialmente diferentes lenguajes de programación y librerías han de tener soporte para esos algoritmos de cifrado. Por otro lado, al tener múltiples aplicaciones requiere que cada una de ellas mantenga seguras las claves privadas en las que se fundamenta la seguridad de cifrado y descifrado, con múltiples aplicaciones los posibles puntos vulnerables son varios.

La seguridad de los datos es muy importante, ciertos datos personales sensibles y que permiten identificar a personas están protegidos por leyes. El no cumplimiento de las leyes implica potencialmente a una empresa recibir importantes multas o descrédito que afecte a la viabilidad del negocio o suponga una reducción de facturación. Algunos datos candidatos a ser cifrados o transformados al guardarse en base de datos son, datos personales e identificativos como nombre y apellidos, DNI, dirección, tarjetas de crédito, bancarios u otros datos que estén regulados por las leyes de protección de datos.

Los datos protegidos incluso no es deseable que sean accesibles por cualesquiera trabajadores de la propia empresa, solo debería tener acceso a ellos aquellos trabajadores que los necesitan para desempeñar su trabajo y ofrecer el servicio que proporciona la empresa. Para el desarrollo de una aplicación los programadores necesitan una base de datos con el mismo esquema de la base de datos de producción y un conjunto de datos, una opción es [obtener una copia de la base de datos][blogbitix-481] de producción, sin embargo, obtener una copia de la base de datos de producción otorga acceso a los programadores acceso a los datos, al hacer la copia es posible aplicar un proceso que ofusque los datos sin embargo esto sigue sin solventar el problema de mantener seguros los datos en la propia base de datos de producción o sus réplicas. Si algunos datos se guardan cifrados aunque se tenga acceso a la base de datos los datos cifrados siguen protegidos.

Una de las funcionalidades que ofrece [Vault][vault] de [HashiCorp][hashicorp] es ofrecer el cifrado y descifrado como servicio. Este artículo está basado en la [guía de Vault sobre la funcionalidad de cifrado y descifrado](https://learn.hashicorp.com/tutorials/vault/eaas-spring-demo?in=vault/encryption-as-a-service).

{{< tableofcontents >}}

### Vault como servicio de cifrado y descifrado

Vault es una herramienta dedicada a la seguridad de la empresa HashiCorp. Tiene diferentes funcionalidades como servir de almacenamiento de secretos en su base de datos de claves y valores, [generar credenciales de acceso bajo demanda a recursos como bases de datos][blogbitix-428] entre otras como cifrado y descifrado como servicio. En todas estás funcionalidades diversos aspectos de la seguridad se centralizan en un único componente del sistema.

Las claves de cifrado únicamente se almacenan en Vault, por otro lado las aplicaciones no han de mantener credenciales para el acceso a una base de datos sino que es Vault el que crea las credenciales válidas únicamente por un periodo de tiempo corto con posibilidad de renovación. Esto aumenta la seguridad ya que una aplicación no ha de mantener unas credenciales para la base de datos válidas por un tiempo indefinido, al mismo tiempo las claves de cifrado están centralizadas en vez estar incluidas en cada aplicación. En caso necesario Vault es capaz de revocar las credenciales de cualquier aplicación.

Vault ofrece dos servicios para proteger los datos, el servicio de cifrado y descifrado está disponible en Vault y el de transformación requiere la versión _Enterprise_.

{{< youtube
    video="HVK-a8SKqvc" >}}
{{< youtube
    video="DOw0Y6ig1i4" >}}

#### Servicio de cifrado y descifrado

El servicio de cifrado y descifrado de Vault consisten simplemente en aplicar un algoritmo de cifrado a un dato en texto plano y devolverlo cifrado y realizar la operación contraria aplicar el algoritmo de descifrado a un dato cifrado y devolverlo en texto plano. Además de mantener las claves de cifrado con la posibilidad rotarlas, es decir, crear nuevas claves.

El proceso de cifrado de Vault transforma el dato original en un valor que no tiene ningún sentido sin aplicar el proceso de descifrado. El formato del dato original se pierde, esto es, si el dato original es un número de teléfono con el formato _(+34) 666554433_ el dato cifrado es una secuencia de caracteres de cierta longitud con otro formato. Esta pérdida de formato es un inconveniente al guardar el dato en la base de datos para solventarlo Vault ofrece el servicio de transformación.

{{< image
    gallery="true"
    image1="image:vault-encryption.png" optionsthumb1="650x450" title1="Uso del servicio de cifrado y descifrado de Vault"
    caption="Uso del servicio de cifrado y descifrado de Vault" >}}

#### Servicio de transformación

En vez de cifrado Vault también ofrece un servicio de transformación que permite obtener un dato ofuscado pero que conserva el mismo formato y longitud que el original. Que el dato tenga el formato original es importante en una base de datos relacional ya que la longitud y formato de la columna para guardarlo será el mismo que el original, en el caso de un dato cifrado el campo se ha ade adaptar al resultado cifrado lo que no es deseable.

{{< code file="vault-transform.sh" language="plain" options="" >}}

En este ejemplo se codifica y descodifica un número de tarjeta de crédito conservando el formato.

{{< code file="vault-transform-encode.sh" language="plain" options="" >}}
{{< code file="vault-transform-decode.sh" language="plain" options="" >}}

#### Proveedor de claves

En un caso de uso en el que es necesario cifrar volúmenes de datos grandes, como _blobs_ de 1 GB, requiere codificar en _base64_ y enviar a Vault por red y obtener la respuesta de tal volumen de datos, esto no es deseable para obtener el mejor rendimiento. En vez de enviar los datos se pueden cifrar los datos localmente con la clave obtenida de Vault. La idea es permitir a la aplicación cifrar y descifrar los datos sin necesidad de llamadas y retornos a Vault con grandes volúmenes de datos.

La respuesta para obtener la clave de cifrado contiene la clave de datos tanto en texto plano como en forma cifrada. Con la clave de datos en texto plano se pueden cifrar los datos y almacenar la clave de datos cifrada junto a los datos. Al necesitar descifrar los datos se solicita a Vault descifrar la clave de datos cifrada para obtener la clave de datos en texto plano permitiendo de esta forma descifrar los datos localmente. Esto es, una vez que el _blob_ está cifrado no es necesario almacenar la clave de datos, solo se necesita almacenar la versión cifrada de la misma.

Esta idea permite cifrar y descifrar grandes volúmenes de datos a la aplicación sin realizar comunicaciones de red costosas con Vault. En este caso Vault no proporciona el servicio de cifrado y descifrado sino que lo hace la aplicación, sin embargo, Vault administra la gestión de las claves usadas por la aplicación que no ha de mantener ninguna clave privada.

### Ejemplo de cifrado y descifrado de datos

Vault dispone tres métodos de acceso a sus funcionalidades entre ellas el servicio de cifrado y descifrado. Los tres métodos son mediante línea de comandos, mediante API REST o mediante la consola web de administración. En este ejemplo solo se muestra la versión de línea de comandos, la opción mediante API REST es posible probarla mediante una herramienta de linea de comandos como _curl_.

El primer paso es iniciar Vault, en este caso por simplicidad en modo desarrollo y habilitar el _transit engine_ que proporciona el servicio de cifrado y descifrado.

{{< code file="vault-start.sh" language="bash" options="" >}}
{{< code file="vault-enable-transit.sh" language="bash" options="" >}}

El siguiente paso es obtener una clave, Vault la devuelve en texto plano o _plaintext_ y cifrada o _ciphertext_.

{{< code file="vault-create-key.sh" language="bash" options="" >}}
{{< code file="vault-key.sh" language="bash" options="" >}}

Una vez creada la clave, se solicita a Vault cifrar y descifrar datos. Los datos han de proporcionase en codificados en _base64_.

{{< code file="vault-encrypt-decrypt.sh" language="bash" options="" >}}

Algunas aplicaciones para aumentar la seguridad y evitar usar una única clave que en el tiempo quede comprometida Vault proporciona la opción de generar una nueva versión de la misma, la clave antigua sigue siendo válida pero los datos serán cifrados con la última versión. Una vez todos los datos hayan sido cifrados con una versión más reciente las versiones antiguas se pueden deshabilitar.

{{< code file="vault-rotate-key.sh" language="bash" options="" >}}

### Ejemplo aplicación con Spring

[Spring][spring] proporciona clases de soporte para el acceso al servicio de Vault. Tanto para la configuración de acceso a Vault como para usar sus servicios mediante una API de clases Java sin tener que recurrir a la API REST de Vault directamente. La clase [VaultOperations](https://docs.spring.io/spring-vault/docs/current/api/org/springframework/vault/core/VaultOperations.html) contiene las referencias de clases para el acceso a las API de Vault, para el caso de el servicio de cifrado y descifrado con la clase [VaultTransitOperations](https://docs.spring.io/spring-vault/docs/current/api/org/springframework/vault/core/VaultTransitOperations.html).

{{< image
    gallery="true"
    image1="image:vault-encryption-java.png" optionsthumb1="650x450" title1="Uso del servicio de cifrado y descifrado de Vault en una aplicación Java"
    caption="Uso del servicio de cifrado y descifrado de Vault en una aplicación Java" >}}

Para usar el servicio de cifrado y descifrado en una aplicación de Spring, Vault permite la autenticación mediante el mecanismo _AppRole_. _AppRole_ es un método de autenticación destinadas a las aplicaciones, básicamente proporciona unas credenciales como un usuario y contraseña. El _policy_ se asocia con las credenciales de la aplicación para permitirle el acceso a la clave de cifrado y descifrado _app_.

{{< code file="app-vault-approle.sh" language="bash" options="" >}}
{{< code file="app-vault-role.sh" language="bash" options="" >}}

Credo el rol _app_ para la aplicación las credenciales formadas por el _role\_id_ y _secret\_id_ ponen en el archivo de configuración de la aplicación de Spring.

{{< code file="application.yml" language="yaml" options="" >}}

El cifrado y descifrado en la aplicación consiste simplemente en hacer uso de la API que proporciona Spring para el acceso a Vault, esta API hace transparente las llamadas REST subyacentes que se hacen a Vault.

{{< code file="Main.java" language="java" options="" >}}

El resultado de cifrar y descifrar el dato se muestra como salida en la consola.

{{< code file="System.out" language="plain" options="" >}}

En el archivo de dependencias de la aplicación se ha de incluir la que proporciona Spring para añadir el soporte a Vault.

{{< code file="build.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringCloudVaultEncryption" command="./gradlew run" %}}

{{< reference >}}
* [Encrypt Application Data in Low Trust Networks](https://www.vaultproject.io/use-cases/data-encryption)
* [Encryption as a Service: Transit Secrets Engine](https://learn.hashicorp.com/tutorials/vault/eaas-transit?in=vault/encryption-as-a-service)
* [Transit Secrets Engine](https://www.vaultproject.io/docs/secrets/transit)
* [Transform Secrets Engine](https://www.vaultproject.io/docs/secrets/transform)
{{< /reference >}}

{{% /post %}}
