---
pid: 2
title: "Enviar correos electrónicos firmados con el DNIe y Evolution"
url: "/2013/12/enviar-correos-electronicos-firmados-con-el-dnie-y-evolution/"
date: 2013-12-13T16:39:07+01:00
updated: 2015-05-28T20:00:00+02:00
rss: true
sharing: true
comments: true
tags: ["planeta-linux", "planeta-codigo", "software-libre", "hardware", "software"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="archlinux.svg" title1="Arch Linux" width1="250" image2="dnie.jpg" title2="DNIe" image3="evolution.png" title3="Evolution" >}}

Otra forma de la que podemos sacar partido al DNI electrónico además de para [identificarnos en sitios web][blogbitix-1] que lo soporten como medio de autenticación es usarlo para firmar digitalmente los correos electrónicos que enviamos. Ya he comentado [como firmar correos electrónicos con GPG][elblogdepicodev-182] pero hacerlo con el DNI electrónico tiene la ventaja de que no necesitaremos [crear una par de claves GPG][elblogdepicodev-181] sino que con tener el DNI e [instalar un lector de tarjetas inteligentes][elblogdepicodev-183] será suficiente.

En esta entrada explicaré como usar el DNI electrónico para firmar digitalmente correos usando el cliente de correo [Evolution](https://projects.gnome.org/evolution/). Primeramente necesitaremos hacer una configuración adicional a la que hemos necesitado para poder usar el lector de tarjetas inteligentes y el DNIe en Linux. Y es usar el siguiente comando tal y como está comentado en [esta página](https://forja.cenatic.es/plugins/mediawiki/wiki/opendnie/index.php/Documentacion_Aplicaciones_Evolution#Configuraci.C3.B3n_del_DNIe_en_el_cliente_de_correo_Evolution) cambiando el directorio home del usuario por el que corresponda:

{{< code file="comando-opensc-dnie.sh" language="Bash" options="" >}}

A continuación, el proceso es similar a como se configura la firma con claves GPG, con el lector de tarjetas inteligentes conectado al equipo y el DNI electrónico introducido accedemos a las propiedades de la cuenta, a la sección seguridad y en el apartado MIME seguro (S/MIME) seleccionamos nuestro certificado. Si queremos que nuestros mensajes se firmen automaticamente debemos marcar la opción «Firmar siempre los mensajes salientes cuando se use esta cuenta». Habiendo ejecutado el comando modutil cuando pulsemos el botón seleccionar en el apartado S/MIME se nos solicitará el PIN secreto de nuestro DNIe.

<div class="media" style="text-align: center;">
    {{< figure year="2013" pid="2"
        image1="pin.png" thumb1="pin-thumb.png" title1="Diálogo de solicitud de PIN con el DNIe"
        image2="seleccion-certificado.png" thumb2="seleccion-certificado-thumb.png" title2="Selección de certificado digital con el DNIe" >}}
    {{< figure year="2013" pid="2"
        image1="seguridad-evolution.png" thumb1="seguridad-evolution-thumb.png" title1="Diálogo de preferencias de seguridad de Evolution para el DNIe" >}}
</div>

Una vez con las propiedades de la cuenta configurada para que Evolution use nuestro DNI electrónico, si no hemos marcado la opción «Firmar siempre los mensajes...» podemos realizarlo al escribir el propio correo marcando la opción «Opciones> Firmar con S/MIME». Al enviar un correo electrónico firmado se añadirá automáticamente un adjunto con la firma de nombre «smime.sp7». Al contrario que GPG la firma está en un formato binario y es dificilmente legible con un lector de texto plano.

<div class="media" style="text-align: center;">
    {{< figure year="2013" pid="2"
        image1="confimacion-firma.png" thumb1="confimacion-firma-thumb.png" title1="Confirmación de firma digital con el DNIe"
        image2="mensaje-firmado-dnie.png" thumb2="mensaje-firmado-dnie-thumb.png" title2="Mensaje firmado con el DNIe" >}}
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Crear una par de claves GPG][elblogdepicodev-181]
* [Como firmar correos electrónicos con GPG][elblogdepicodev-182]
* [Instalar un lector de tarjetas inteligentes y usar el DNIe en Arch Linux ][elblogdepicodev-183]
* [Usar el DNI electrónico con Firefox en Linux][blogbitix-1]
* [Firmar digitalmente documentos con el DNIe y Sinadura][blogbitix-3]
{{% /reference %}}

{{% /post %}}
