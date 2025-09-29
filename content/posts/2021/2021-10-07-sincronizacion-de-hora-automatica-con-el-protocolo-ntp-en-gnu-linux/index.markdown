---
pid: 602
type: "post"
title: "Sincronización de hora automática con el protocolo NTP en GNU/Linux"
url: "/2021/10/sincronizacion-de-hora-automatica-con-el-protocolo-ntp-en-gnu-linux/"
date: 2021-10-07T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:linux.svg"
tags: ["gnu-linux", "planeta-codigo"]
summary: "Muchos dispositivos electrónicos entre ellos las computadoras tienen un reloj interno que continúa funcionando aún estando apagados y desconectados de la red electrica gracias a una batería en forma de pila de botón. La fecha y la hora permiten conocer cuando se modifica un documento pero también otros procesos más importantes que también dependen del tiempo como el segundo factor de autenticación y los códigos temporales basados en el tiempo. Los relojes internos usados en las computadoras son bastante imprecisos y se adelantan o retrasan con respecto a la hora oficial, algunos dispositivos electrónicos ni siquiera tienen el hardware de un reloj interno. El protocolo _Network Time Protocol_ o NTP permite a las computadoras obtener la hora de un servidor que ofrezca este servicio de forma regular para evitar diferencias significativas, algunos de estos servidores están respaldados por una organización gubernamental que ofrecen la medida del tiempo oficial de un país y que poseen relojes atómicos mucho más precisos."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

Conocer la hora y fecha actual es importante también en las computadoras y programas para algo tan simple como conocer cuál fue la fecha de creación o modificación de un archivo o algo más importante como conocer la fecha de una compra o transacción bancaria. Las computadoras son capaces de mantener la hora y fecha gracias a una pequeña batería como fuente de energía como una pila de  botón habitualmente una CR2032 y un reloj que sigue funcionando cuando la computadora está apagada y desconectada de toda fuente de energía. La pila de botón es suficiente para mantener el reloj interno de la computadora y otros dispositivos electrónicos funcionando durante más de 10 años.

Otra funcionalidad importante que depende del tiempo es el segundo factor de autenticación o _2FA_. Para mayor seguridad es muy recomendable [configurar el segundo factor de autenticación en los principales servicios][blogbitix-530] como correo electrónico junto a otros servicios que tratan con información personal o datos bancarios. El segundo factor de autenticación o TOTP es un código adicional a la contraseña a introducir al autenticarse en un servicio. El TOTP normalmente está formado por unos seis dígitos que solo es válido durante un pequeño espacio de tiempo de unos 30 segundos, pasado este tiempo deja de ser válido y otro código pasa a ser válido. En caso de que el dispositivo que genera el código no posea el tiempo actual genera códigos inválidos.

Sin embargo, algunos dispositivos como la [Raspberry Pi][raspberrypi] no posee un reloj interno con lo que no son capaces de mantener la hora cuando están apagados. Por otro lado, los relojes de las computadoras no son totalmente precisos y durante periodos de tiempo prolongados cada uno mide el paso del tiempo de forma diferente, el tiempo proporcionado por cada uno varía en unos pocos milisegundos o segundos. Estas variaciones de tiempo aunque pequeñas son suficientes para no ser válidas en ciertos propósitos que necesitan marcas de tiempo totalmente precisas. Enviar un correo electrónico de respuesta con una fecha anterior al original es una paradoja temporal, posiblemente inocua en correo electrónico pero un problema en una transacción financiera. Los relojes más precisos son los atómicos que tardan en desfasarse un segundo al cabo de 52 millones de años. El motivo por el que se utilizan relojes de cuarzo en los ordenadores en vez de atómicos más precisos es que los primeros son más baratos, más pequeños y con menor consumo, los relojes de cuarzo ofrecen una precisión suficiente durante más o menos un pequeño periodo de tiempo.

Los dispositivos que no tienen un reloj interno mantiene la hora actual utilizando el protocolo [Network Time Protocol][wikipedia-ntp] o NTP, los dispositivos que tiene reloj interno pero se quieren mantener mayor precisión pueden utilizar este protocolo para mantener yna medida del tiempo más precisa. Hay servidores que ofrecen la hora a través del protocolo NTP tomando como fuente el tiempo proporcionado por un organismo gubernamental siendo la autoridad encargada de proporcionar esta medida considerada la oficial. En España es el [Real Instituto y Observatorio de la Armada](https://armada.defensa.gob.es) ubicado en Cádiz el que mantiene la hora oficial, este organismo posee un reloj atómico y ofrece el servicio de NTP, en su página web en la [Sección de Hora](https://armada.defensa.gob.es/ArmadaPortal/page/Portal/ArmadaEspannola/cienciaobservatorio/prefLang-es/06Hora--00Hora) proporciona más detalles.

Hace unos días intenté entrar en mi cuenta de PayPal en la que tengo configurada el segundo factor de autenticación, resulta que mi ordenador que no lo tenía configurado con NTP tenía una diferencia de tiempo de dos minutos con la real. Esta diferencia de tiempo era suficiente para que el código no fuera válido utilizando [el cliente KeePassXC para guardar las contraseñas][blogbitix-196] y que también utilizo para generar los códigos TOTP del segundo factor de autenticación. El resultado fué que la cuenta de PayPal se me bloqueó de forma temporal como medida de seguridad para evitar ataques de fuerza bruta después de varios intentos.

No tenía activado el servicio NTP proporcionado por el gestor de servicios _systemd_, lo activé de forma fácil en el panel de configuración de [GNOME][gnome].

{{< tableofcontents >}}

## Sincronización de hora en GNOME

Para activar el servicio NTP en GNOME basta con acceder al panel de configuración en la sección de _Fecha y hora_ y activar la opción _Fecha y hora_ automáticas. Esta opción hace lo mismo que desde la línea de comandos.

{{< image
    gallery="true"
    image1="image:gnome-fecha-hora.webp" optionsthumb1="300x250" title1="Configuración de fecha y hora en GNOME"
    caption="Configuración de fecha y hora en GNOME" >}}

## Sincronización de hora desde línea de comandos

El gestor de servicios _systemd_ proporciona un servicio para mantener sincronizada la fecha y hora con el protocolo NTP. Los siguientes comandos permiten conocer el estado del servicio y la información de la fecha y hora del sistema.

{{< code file="systemctl-timesyncd-status.sh" language="bash" options="" >}}
{{< code file="timedatectl.sh" language="bash" options="" >}}

El siguiente comando ofrece información más detallada, incluye el servidor NTP utilizado y el intervalo de tiempo entre sincronizaciones.

{{< code file="timesyncd-timesync-status.sh" language="bash" options="" >}}

Habilitar el servicio indica que el servicio se inicie como parte del inicio del sistema, si el servicio está parado se puede iniciar de forma explícita.

{{< code file="systemctl-timesyncd.sh" language="bash" options="" >}}

El servidor NTP utilizado para realizar la sincronización de la fecha y la hora usa la configuración personalizada del archivo _/etc/systemd/timesyncd.conf_ en el que se incluye el dominio del servicio. Sin haber realizado cambios en el archivo todos los valores de está configuración están comentados. Los valores por defecto de esta configuración se definen en tiempo de compilación, en el caso de los paquetes de [Arch Linux][archlinux] en el [archivo PKGBUILD de systemd](https://github.com/archlinux/svntogit-packages/blob/master/systemd/trunk/PKGBUILD).

En este archivo se pone el servidor NTP deseado si es otro distinto del por defecto como el proporcionado por el Real Instituto y Observatorio de la Armada, _hora.roa.es_ y _minuto.roa.es_, u otro de los proporcionados por la organización de voluntarios [NTP Pool Project][ntppool] que utilizan muchas distribuciones incluida Arch Linux y dispositivos en internet como _routers_.

{{< code file="timesyncd.conf" language="plain" options="" >}}

{{< reference >}}
* [Time Synchronization with NTP and systemd](https://feeding.cloud.geek.nz/posts/time-synchronization-with-ntp-and-systemd/)
* [El hombre que ajusta la hora en España](https://www.elmundo.es/ciencia/2015/06/30/559189c622601d6b4e8b4594.html)
* [Reloj atómico](https://es.wikipedia.org/wiki/Reloj_at%C3%B3mico)
* [Real-time clock](https://en.wikipedia.org/wiki/Real-time_clock)
{{< /reference >}}

{{% /post %}}
