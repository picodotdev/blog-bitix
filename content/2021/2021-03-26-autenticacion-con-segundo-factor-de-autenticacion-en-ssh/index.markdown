---
pid: 562
type: "post"
title: "Autenticación con segundo factor de autenticación en SSH"
url: "/2021/03/autenticacion-con-segundo-factor-de-autenticacion-en-ssh/"
date: 2021-03-26T17:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:linux.svg"
tags: ["gnu-linux", "seguridad", "software-libre"]
summary: "El servicio SSH permite el acceso a equipos remotos a un usuario desde cualquier ubicación en la que se encuentre el usuario siempre que tenga conectividad desde el punto de acceso a internet y el equipo remoto. La autenticación se realiza mediante un usuario y contraseña o mediante clave privada y pública, adicionalmente SSH también se puede configurar para requerir un segundo factor de autenticación o 2FA que consituye una segunda clave."
---

{{% post %}}

{{< logotype image1="gnu.svg" image2="linux.svg" >}}

Una forma de acceso a sistemas remotos para realizar tareas de administración es mediante SSH. La red permite acceder a sistemas remotos con SSH siempre que haya conectividad entre el la red del usuario y la red del servidor, con el auge de los servidores ofrecidos por los proveedores de hospedaje o los servicios de computación en la nube en la que lo servidores ya no son físicos sino lógicos SSH es la forma de acceder a los sistemas desde cualquier lugar.

SSH es un protocolo seguro y cifrado que utiliza como método de autenticación un usuario y contraseña o para mayor seguridad una clave SSH, también se puede configurar para añadir adicionalmente un segundo factor de autenticación o 2FA para mayor seguridad.

En el artículo específico comento como [configurar SSH con autenticación mediante clave pública y privada][blogbitix-401], en otro artículo comento como tener [acceso simple y seguro a sistemas remotos con Boundary][blogbitix-558].

Es recomendable [usar segundo factor de autenticación en servicios como Google, Amazon y Paypal][blogbitix-530] y otros relevantes que lo ofrezcan. También es recomendable utilizar un [gestor de contraseñas como KeePassXC][blogbitix-196] para guardar las contraseñas únicas y como aplicación para generar los _tokens_ de 2FA.

{{< tableofcontents >}}

### Instalar el módulo SSH de Google 2FA

Usar 2FA con SSH requiere instalar un módulo de autenticación que valide el proceso de autenticación. El módulo permite usar los _tokens_ de un solo uso en el proceso de autenticación adicionalmente a la contraseña o clave SSH.

En los sistemas basados en [Debian][debian] como [Ubuntu][ubuntu] hay que instalar el siguiente paquete que añade un nuevo módulo de PAM para 2FA.

{{< code file="apt-install-libpam-google-authenticator.sh" language="plain" options="" >}}

Posteriormente hay que configurar SSH para que haga uso del módulo PAM de Google Authenticator añadiendo la siguiente línea en el archivo _/etc/pam.d/sshd_.

{{< code file="sshd.sh" language="bash" options="" >}}
{{< code file="sshd-1" language="plain" options="" >}}

También hay que modificar el archivo de configuración de SSH _/etc/ssh/sshd\_config_.

{{< code file="sshd_config.sh" language="bash" options="" >}}
{{< code file="sshd_config-1" language="plain" options="" >}}

### Configurar el módulo SSH de Google 2FA

La autenticación con 2FA es una segunda contraseña o código a introducir al iniciar sesión en el sistema remoto. Para utilizar 2FA hay que configurar previamente una aplicación que proporcione los códigos temporales.

El siguiente comando ejecutado con el usuario permite generar un código QR y la información para generar los _tokens_ o configurar una aplicación como [KeePassXC][keepassxc] para generarlos. El comando realiza varias preguntas a las que se puede contestar con los siguientes valores.

* _Make tokens "time-base"_: yes
* _Update the .google_authenticator file_: yes
* _Disallow multiple uses_: yes
* _Increase the original generation time limit_: no
* _Enable rate-limiting_: yes

{{< code file="google-authenticator.sh" language="plain" options="" >}}

El resultado del comando es un código QR que se puede escanear con la aplicación de Google Authenticator para teléfono inteligente o con la clave secreta utilizar KeePassXC para generar los _tokens_.

{{< image
    gallery="true"
    image1="image:google-authenticator.png" optionsthumb1="300x200" title1="Configuración de Google Authenticator"
    image2="image:keepassxc-1.png" optionsthumb2="300x200" title2="Configuración OTP en KeePassXC"
    image3="image:keepassxc-2.png" optionsthumb3="300x200" title3="Obtención de OTP en KeePassXC"
    caption="Configuracón para Google Authenticator, KeePassXC y obtención OTP desde KeePassXC" >}}

Una vez modificada la configuración hay que reiniciar el servicio de SSH.

{{< code file="sshd-restart.sh" language="plain" options="" >}}

### Usar autenticación 2FA con claves SSH

Al utilizar la autenticación mediante claves públicas y privadas con la configuración por defecto es suficiente para otorgar acceso al sistema remoto, con lo que no se solicitará el segundo factor de autenticación. 
{{< code file="ssh-copy-id.sh" language="bash" options="" >}}

Para que se pida el segundo factor de autenticación aún utilizando la autenticación mediante clave pública y privada hay que modificar la configuración de SSH de nuevo con los siguientes cambios de adicionales.

La siguiente línea del archivo _/etc/pam.d/sshd_ ha de estar comentada de esta forma y añadir al final de archivo los métodos de autenticación.

{{< code file="sshd-2" language="plain" options="" >}}

Nuevamente hay que modificar el archivo _/etc/ssh/sshd\_config_ y añadir esta línea al final.

{{< code file="sshd_config-2" language="plain" options="" >}}

Y después reiniciar el servicio de SSH.

{{< code file="sshd-restart.sh" language="plain" options="" >}}

### Probar la autenticación SSH

Una vez realizada la configuración es recomendable probar que la autenticación se realiza de forma correcta tanto con usuario y contraseña como en el caso de claves. Una vez proporcionado el usuario y la contraseña se solicita el _token_ del segundo factor de autenticación que es generado por la aplicación Google Authenticator, KeePassXC u otra aplicación configurada para generarlos.

En este caso realizando la autenticación con usuario y contraseña se solicita el _token_ 2FA.

{{< code file="ssh-pi-password.sh" language="bash" options="" >}}

En este otro caso con clave privada y pública también se solicita el _token_ 2FA.

{{< code file="ssh-pi-pubkey.sh" language="bash" options="" >}}

{{< reference >}}
* [Configure SSH to use two-factor authentication](https://ubuntu.com/tutorials/configure-ssh-2fa#1-overview)
* [Using Two-Factor Authentication for SSH](https://pimylifeup.com/setup-2fa-ssh/)
* [Set Up Multi-Factor Authentication for SSH on Ubuntu 20.04](https://www.rosehosting.com/blog/how-to-set-up-multi-factor-authentication-for-ssh-on-ubuntu-20-04/)
{{< /reference >}}

{{% /post %}}
