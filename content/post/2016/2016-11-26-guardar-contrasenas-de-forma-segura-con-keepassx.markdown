---
pid: 196
title: "Guardar contraseñas de forma segura con KeePassX"
url: "/2016/11/guardar-contrasenas-de-forma-segura-con-keepassx/"
date: 2016-11-26T11:00:00+01:00
updated: 2016-11-26T22:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
tags: ["blog-stack", "gnu-linux", "planeta-codigo", "planeta-linux"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="keepassx.png" title1="KeePassX" >}}

La mayor parte de servicios usan aún como forma de autenticación el método de usuario y contraseña. Si la contraseña proporcionada conocida solo por su usuario es correcta el sistema confía en que el usuario es quien dice ser. Las contraseñas debería ser difíciles de averiguar, se recomienda que tenga una longitud mínima de 8 caracteres y que contenga letras en minúscula y mayúscula, números y símbolos, sin embargo, cuanto más fuerte y difícil sea de averiguar más difícil será recordarla. Además, idealmente se debe utilizar una contraseña distinta en cada servicio para evitar que descubierta la contraseña por un fallo de seguridad en cualquiera de en los que se use pueda accederse a todos los otros servicios que use ese usuario utilice, por lo que el número de contraseñas a recordar pueden ser numerosas de modo que muchos usuarios terminan usando la misma contraseña en varios servicios con el riesgo que conlleva. Dado que recordar una contraseña fuerte para cada servicio es difícil y mantenerlas guardadas en un archivo de texto o en un papel no es lo más recomendable podemos usar un programa que las almacene.

Uno de estos programas es [KeePassX][keepassx], básicamente es una base de datos de contraseñas que se almacenan de forma segura y cifrada y protegidas por una contraseña maestra. La información básica que podemos guardar es el nombre del servicio y su URL, el usuario que usamos en ese servicio y la contraseña. Con KeePassX podemos crear grupos de contraseñas que nos permitan buscarlas de forma más sencilla.

Instalado el [paquete de KeePassX](https://www.archlinux.org/packages/community/x86_64/keepassx2/) de nuestra distribución [GNU][gnu]/[Linux][linux] o el binario para nuestro sistema operativo lo primero que deberemos hacer es crear una base de datos que protegeremos con una contraseña maestra, podemos guardar la base de datos en un archivo en la ubicación que deseemos.

{{< gist picodotdev 1e0bc79d0f7260e6243c744207cb19ff "pacman.sh" >}}

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="196"
        image1="keepassx-1.png" thumb1="keepassx-1-thumb.png" title1="KeePassX"
        image2="keepassx-4.png" thumb2="keepassx-4-thumb.png" title2="KeePassX" >}}
</div>

Lo siguiente será crear una entrada con el usuario y contraseña de un servicio.

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="196"
        image1="keepassx-2.png" thumb1="keepassx-2-thumb.png" title1="KeePassX" >}}
</div>

Una vez almacenada la credencial podemos copiar al portapapeles el usuario y contraseña de esa credencial para usarla en el servicio. También podremos ver la contraseña no protegida con asteriscos sino los caracteres originales.

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="196"
        image1="keepassx-2.png" thumb1="keepassx-2-thumb.png" title1="Nueva entrada"
        image1="keepassx-3.png" thumb1="keepassx-3-thumb.png" title1="Contraseñas guardadas" >}}
</div>

KeePassX es una herramienta que no tiene más complejidad, podemos asociar alguna información más a la credencial con atributos adicionales y archivos adjuntos pero no es imprescindible.

Si somos nosotros los que implementamos un servicio con el método de autenticación de usuario y contraseña deberíamos [almacenar las contraseñas de forma segura usando «password salted hasing»][blogbitix-75].

Otras formas de guardar las contraseñas es en un [archivo cifrado con GPG][elblogdepicodev-181] que desciframos cuando queramos usar alguna contraseña o en un [sistema de archivos cifrado como con EncFS][blogbitix-126] que lo montamos para acceder a archivo de texto con las contraseñas.

KeePassX posee una utilidad para generar contraseñas únicas para cada servicio con cierta complejidad, podemos variar la longitud, si incluye símbolos, y mayúsculas y minúsculas. [StrongPassowrdGenerator][strongpasswordgenerator] en una página web con la que podemos generar contraseñas usando estas mismas preferencias desde el navegador.

{{% /post %}}
