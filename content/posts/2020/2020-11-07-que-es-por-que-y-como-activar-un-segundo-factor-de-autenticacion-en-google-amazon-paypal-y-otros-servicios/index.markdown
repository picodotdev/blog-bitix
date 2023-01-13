---
pid: 530
type: "post"
title: "Qué es, por qué y cómo activar un segundo factor de autenticación en Google, Amazon, PayPal y otros servicios"
url: "/2020/11/que-es-por-que-y-como-activar-un-segundo-factor-de-autenticacion-en-google-amazon-paypal-y-otros-servicios/"
date: 2020-11-07T23:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:paypal-2fa-2.webp"
tags: ["planeta-codigo", "seguridad"]
summary: "Las contraseñas son un mecanismo de seguridad empleado para autenticar a un usuario en un servicio al iniciar sesión. Pero las contraseñas son un mecanismo potencialmente débil, por ello varios de los servicios más importantes implementan mecanismos de seguridad adicionales para proteger en mayor medida las cuentas de los usuarios. El segundo factor de autenticación o 2FA es un mecanismo adicional, las contraseñas son algo que se conoce, el 2FA es algo que se tiene. Varios de los servicios más importantes como Google, Amazon y PayPal entre otros lo ofrecen y es recomendable activarlos en aquellos en los que lo hacen."
---

{{% post %}}

{{< logotype image1="keepassxc.svg" >}}

La autenticación es el mecanismo de seguridad que permite identificar a un usuario y es quien dice ser. Para comprobar que el usuario es quien dice ser se utiliza una contraseña que solo conoce el usuario. Pero las contraseñas como medida de seguridad no son perfectas, ya que algunos de los usuarios utilizan contraseñas débiles con pocos caracteres, con palabras de diccionario o conocidas y utilizan la misma contraseña en varias cuentas de modo que si un servicio sufre un problema de seguridad las cuentas de otros servicios con la misma contraseña quedan vulnerables.

Toda la seguridad de las contraseñas depende de que solo sea conocida por el propietario de la cuenta, las contraseñas son un factor de seguridad de algo que sólo el legítimo propietario conoce. Otro mecanismo de seguridad es basar la seguridad adicionalmente en algo que se tiene, esto es el segundo factor de autenticación.

{{< tableofcontents >}}

### Qué es un segundo factor de autenticación o 2FA

El segundo factor de autenticación o 2FA es un mecanismo de seguridad que se suele utilizar adicionalmente al mecanismo de seguridad de contraseña. Adicionalmente a la contraseña el usuario ha de proporcionar un código generado con el segundo factor de autenticación.

El generador de segundo factor de autenticación puede ser de diferentes formas, una de ellas es una aplicación para móvil y otra una aplicación de escritorio, también hay dispositivos hardware específicos de 2FA que se conectan por USB al ordenador. También se denomina doble factor de autenticación o autenticación de dos factores.

### Por qué activar un segundo factor de autenticación

Con el segundo factor de autenticación activado aunque la contraseña de un servicio se vea comprometida la cuenta seguirá protegida ya que adicionalmente a la contraseña se necesita el segundo factor de seguridad. Las contraseñas quedan expuestas si un servicio tiene una vulnerabilidad de seguridad o por alguno de los varios [tipos de ataque informático contra el usuario][blogbitix-605] que es aprovechada por delincuentes para obtener las contraseñas de seguridad de usuarios con el objetivo de poder acceder a sus cuentas. Estos fallos de seguridad son explotables a través de la red sin necesidad de acceso físico a los servidores del servicio.

El segundo factor de autenticación añade más seguridad ya que los delincuentes deben comprometer no solo el servicio sino adicionalmente el dispositivo generador de segundo factor de autenticación.

Activar el segundo factor de autenticación es recomendable porque añade una seguridad adicional mucho mayor que utilizando únicamente una contraseña. Los servicios como el correo electrónico de [Google][google], la plataforma de comercio electrónico [Amazon][amazon-affiliate], el sistema de pagos [PayPal][paypal] permite a los usuarios activar el 2FA. Es recomendable hacerlo cuando sea posible ya que servicios como estos se utilizan para tareas importantes como toda la comunicación e información del usuario, compras por internet donde se guardan tarjetas de crédito y números de cuenta bancaria.

### Cómo funciona un segundo factor de autenticación

Al realizar la autenticación en un servicio para iniciar sesión se ha de introducir el identificativo del usuario y la contraseña, después con el segundo factor de autenticación activado adicionalmente se solicita un código adicional que el usuario ha de proporcionar. El código adicional es proporcionado por las aplicaciones generadores de 2FA.

Las aplicaciones generadoras de códigos 2FA se inicializan al activar el 2FA en la cuenta, en el caso de una aplicación móvil normalmente se hace escaneando un código QR con la cámara de fotos, para otros dispositivos se proporciona una secuencia de letras. El código QR o la secuencia de letras son la semilla con la que se generan los códigos.

Los códigos 2FA que se generan adicionalmente son temporales y solo son válidos durante un periodo corto de tiempo, a estos códigos 2FA se les denomina TOTP acrónimo de _time-based one-time password_ normalmente formados por 6 dígitos. De modo que aunque un código quedase comprometido sólo sería válido durante un periodo corto de tiempo de unos segundos, habitualmente de unos 30 segundos. Los generadores de códigos generan un nuevo código de forma periódica. Dado que la validez de los códigos generados dependen de la fecha y hora actual el dispositivo generador de códigos TOTP debe [mantener la fecha y hora sincronizada con el protocolo NTP][blogbitix-602]. 

En la autenticación en dos pasos las credenciales para autenticar un usuario están compuestas de dos factores, la contraseña y el código TOTP.

### Cómo activar un segundo factor de autenticación

El segundo factor de autenticación ha de activarse en cada servicio de forma individual. Al realizar la activación se proporciona el código QR a escanear por la aplicación móvil del generador de códigos con la cámara de fotos y alternativamente la secuencia de letras para otro tipo de aplicaciones o dispositivos que no tiene cámara de fotos.

El proceso de activar el 2FA es similar en todos los servicios, varía la interfaz que se utilizan pero básicamente todos proporcionan el código QR y la secuencia de letras. En este caso muestro como activar el segundo factor de autenticación en los servicios de Google, Amazon y PayPal, para otros servicios como [Twitter][twitter], [Facebook][facebook] u cualquier otro el proceso es similar.

#### Cómo activar 2FA en una cuenta de Google

Para activar el segundo factor de autenticación en una cuenta de Google hay que realizar la configuración desde _Gestionar tu cuenta de Google_ ubicado en el desplegable del menú del usuario, después acceder a las opciones de seguridad del menú _Protegemos tu cuenta_. En la _Revisión de seguridad_ el proceso de configuración de 2FA se inicia desde la opción _Verificación en dos pasos_, hay varios opciones de utilizar 2FA una de ellas es con una notificación de Google en el teléfono móvil, un SMS o un código de seguridad, la otra opción es utilizar una aplicación de autenticación o _authenticator_.

La autenticación con una aplicación de _authenticator_ se inicia desde el enlace _configuración de la verificación en dos pasos_ para configurar la aplicación _authenticator_. Es un proceso de varios pasos en el que se muestra el código QR a escánear por la aplicación _authenticator_ y el código formado por varios grupos de 4 letras equivalente, el código de letras solo se muestra con el enlace _¿No puedes escanearlo?_ debajo del código QR. Para asegurar que la aplicación _authenticator_ ha escaneado correctamente el código QR y genéra códigos de 6 dígitos se ha de verificar introduciendo su valor actual.

{{< image
    gallery="true"
    image1="image:google-2fa-1.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de Google"
    image2="image:google-2fa-2.webp" optionsthumb2="200x150" title2="Activación de 2FA en una cuenta de Google"
    image3="image:google-2fa-3.webp" optionsthumb3="200x150" title3="Activación de 2FA en una cuenta de Google" >}}
{{< image
    gallery="true"
    image1="image:google-2fa-4.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de Google"
    image2="image:google-2fa-5.webp" optionsthumb2="200x150" title2="Activación de 2FA en una cuenta de Google"
    image3="image:google-2fa-6.webp" optionsthumb3="200x150" title3="Activación de 2FA en una cuenta de Google" >}}
{{< image
    gallery="true"
    image1="image:google-2fa-7.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de Google"
    caption="Activación de 2FA en una cuenta de Google" >}}

Una vez configurado 2FA al iniciar sesión Google por defecto envía una notificación al teléfono móvil con la que solo es necesario realizar la confirmación del inicio de sesión desde el móvil, con la opción _Probar de otra manera_ permite usar otro método de autenticación 2FA entre ellos el de la aplicación _authenticator_.

{{< image
    gallery="true"
    image1="image:google-2fa-8.webp" optionsthumb1="200x150" title1="Inicio de sesión en una cuenta de Google con 2FA"
    image2="image:google-2fa-9.webp" optionsthumb2="200x150" title2="Inicio de sesión en una cuenta de Google con 2FA"
    image3="image:google-2fa-10.webp" optionsthumb3="200x150" title3="Inicio de sesión en una cuenta de Google con 2FA" >}}
{{< image
    gallery="true"
    image1="image:google-2fa-11.webp" optionsthumb1="200x150" title1="Inicio de sesión en una cuenta de Google con 2FA"
    caption="Inicio de sesión en una cuenta de Google con 2FA" >}}

#### Cómo activar 2FA en una cuenta de Amazon

En el caso de Amazon la configuración del segundo factor de autenticación se realiza desde _Mi cuenta > Inicio de sesión y seguridad > Configuración de la verificación en dos pasos (2SV)_. Hay que registra un autenticador de verificación de dos pasos, al seleccionar utilizar una _app_ de verificación se muestra el cogido QR y en código de letras en el enlace _¿No puedes escanear el código de barras?_. Escáneado el código QR el y el código de letras se verifica la aplicación _autenticator_ está configurada correctamente y genera códigos TOTP correctos.

{{< image
    gallery="true"
    image1="image:amazon-2fa-1.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de Amazon"
    image2="image:amazon-2fa-2.webp" optionsthumb2="200x150" title2="Activación de 2FA en una cuenta de Amazon"
    image3="image:amazon-2fa-3.webp" optionsthumb3="200x150" title3="Activación de 2FA en una cuenta de Amazon" >}}
{{< image
    gallery="true"
    image1="image:amazon-2fa-4.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de Amazon"
    image2="image:amazon-2fa-5.webp" optionsthumb2="200x150" title2="Activación de 2FA en una cuenta de Amazon"
    image3="image:amazon-2fa-6.webp" optionsthumb3="200x150" title3="Activación de 2FA en una cuenta de Amazon" >}}
{{< image
    gallery="true"
    image1="image:amazon-2fa-7.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de Amazon"
    caption="Activación de 2FA en una cuenta de Amazon" >}}

Al iniciar sesión en la cuenta de Amazon se solicita el correo electrónico y la contraseña, con 2FA activado adicionalmente se solicita el cógido TOTP, marcando la opción _No vuelvas a pedir ningún código en este navegador_ el código TOTP solo se solicita una vez por dispositivo.

{{< image
    gallery="true"
    image1="image:amazon-2fa-8.webp" optionsthumb1="200x150" title1="Inicio de sesión en una cuenta de Amazon"
    image2="image:amazon-2fa-9.webp" optionsthumb2="200x150" title2="Inicio de sesión en una cuenta de Amazon"
    caption="Inicio de sesión en una cuenta de Amazon" >}}

#### Cómo activar 2FA en una cuenta de PayPal

En PayPal la configuración de segundo factor de autenticación se realiza desde el _Centro de seguridad_ con la opción _Verificación en dos pasos_ para configurarlo. En la configuración se muestra el código QR y el código de letras introducir en la aplicación _authenticator_. Como en los otros casos para verificar que la aplicación _authenticator_ se ha configurado correctamente para generar los código TOTP se solicita validar uno.

{{< image
    gallery="true"
    image1="image:paypal-2fa-1.webp" optionsthumb1="200x150" title1="Activación de 2FA en una cuenta de PayPal"
    image2="image:paypal-2fa-2.webp" optionsthumb2="200x150" title2="Activación de 2FA en una cuenta de PayPal"
    image3="image:paypal-2fa-3.webp" optionsthumb3="200x150" title3="Activación de 2FA en una cuenta de PayPal"
    caption="Activación de 2FA en una cuenta de PayPal" >}}

Al iniciar sesión en la cuenta de PayPal se solicita también el correo electrónico y la contraseña, con 2FA activado adicionalmente se solicita el código TOTP, marcando la opción _Confiar en este dispositivo_ el código TOTP solo se solicita una vez por dispositivo.

{{< image
    gallery="true"
    image1="image:paypal-2fa-4.webp" optionsthumb1="200x150" title1="Inicio de sesión en una cuenta de PayPal"
    image2="image:paypal-2fa-5.webp" optionsthumb2="200x150" title2="Inicio de sesión en una cuenta de PayPal"
    caption="Inicio de sesión en una cuenta de PayPal" >}}

### Aplicación para _smartphone_ generador de TOTP

Dos aplicaciones gratuitas para teléfono inteligente o _smartphone_ son [Google Authenticator][google-authenticator] y [Microsoft Authenticator][microsoft-authenticator] disponibles tanto para los que utilizan [Android][android] como [iOS][apple-ios] como sistema operativo en sus respectivas tiendas de aplicaciones.

Instalada la aplicación en el _smartphone_ el primer paso es iniciar el proceso de activación de 2FA en un servicio, este servicio proporciona el código QR que escaneándolo con la aplicación permite generar generar los códigos temporales 2FA. Una vez escaneado el código QR se muestra como información el servicio y cuenta, el código temporal válido y un indicador de tiempo que una vez expirado se genera un nuevo código.

Las aplicaciones móviles ofrecen comodidad pero ¿qué ocurre si el móvil se pierde, nos lo roban o se estropea? No disponer del dispositivo generador de 2FA es un problema ya que sin él no se impide el inicio de sesión ya que en algún momento se solicitará el código 2FA.

{{< image
    gallery="true"
    image1="image:google-authenticator-1.webp" optionsthumb1="200x150" title1="Google Authenticator"
    image2="image:google-authenticator-2.webp" optionsthumb2="200x150" title2="Google Authenticator"
    image3="image:microsoft-authenticator-1.webp" optionsthumb3="200x150" title3="Microsoft Authenticator"
    caption="Aplicaciones Google Authenticator y Microsoft Authenticator" >}}

### Aplicación de escritorio generador de TOTP

[KeePassXC][keepassxc] es una aplicación de escritorio que sirve como base de datos para [guardar contraseñas de forma segura y generar contraseñas únicas seguras][blogbitix-196] para los servicios. Está disponible para los sistemas operativos [Windows][windows], [GNU][gnu]/[Linux][linux] y [macOS][macos].

{{< image
    gallery="true"
    image1="image:keepass-1.webp" optionsthumb1="300x200" title1="Aplicación gestor de contraseñas KeePassXC"
    caption="Aplicación gestor de contraseñas KeePassXC" >}}

KeePassXC también sirve para guardar el 2FA siendo capaz de generar los mismo códigos TOTP de las aplicaciones de _smartphone_. Para guardar la semilla con la que se generan los TOTP hay que ir a la opción del menú _Apuntes > TOTP > Configurar TOTP_ o con el menú contextual del botón derecho sobre el apunte, esta opción muestra una ventana en la que introducir el código de letras alternativo al código QR al activar el 2FA del servicio. También es capaz de mostrar el código QR original, lo que permite migrar el 2FA a otro móvil.

{{< image
    gallery="true"
    image1="image:keepass-2.webp" optionsthumb1="300x200" title1="Código TOTP generador por KeePassXC"
    caption="Configuración TOTP en KeePassXC" >}}

Si se configura como generador de códigos un _smartphone_ y KeePassXC es posible comprobar que generan los mismos TOTP y están bien configurados, con la opción _Apuntes > TOTP > Mostrar TOTP_ se muestra un diálogo con el código TOTP válido.

{{< image
    gallery="true"
    image1="image:keepass-3.webp" optionsthumb1="300x200" title1="Código TOTP generador por KeePassXC"
    caption="Código TOTP generador por KeePassXC" >}}

#### Por qué utilizar KeePassXC adicionalmente a una aplicación del móvil

No conviene guardar los 2FA únicamente en el _smartphone_, perder el dispositivo generador de códigos 2FA impide iniciar sesión en los servicios cuando se solicite el TOTP. Algunos servicios permiten recuperar la cuenta utilizando otras formas de autenticación pero algunos otros servicios perder el 2FA quizá suponga la pérdida del acceso a la cuenta.

Utilizar KeePassXC permite tener un segundo dispositivo con el que generar los TOTP, además es fácil [hacer una copia de seguridad][blogbitix-144] del archivo de la base de datos de las contraseñas de KeePassXC ya que es simplemente un archivo cifrado y permite configurar otros dispositivos como generadores de TOTP a través del código QR original.

{{% /post %}}
