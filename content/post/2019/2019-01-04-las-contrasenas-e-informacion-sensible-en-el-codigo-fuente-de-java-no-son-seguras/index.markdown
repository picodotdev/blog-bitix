---
pid: 371
title: "Las contraseñas e información sensible en el código fuente de Java no son seguras"
url: "/2019/01/las-contrasenas-e-informacion-sensible-en-el-codigo-fuente-de-java-no-son-seguras/"
date: 2019-01-04T19:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

En Java el código fuente se compila a una representación en _bytecode_ independiente de la arquitectura del procesador y sistema operativo donde posteriormente se ejecuta. Este _bytecode_ es un formato binario pero que puede ser decompilado fácilmente con la herramienta _javap_ incluida en el propio JDK o examinado su contenido simplemente con un editor de texto hexadecimal. Con estas herramientas es fácil ver las instrucciones del programa para la máquina virtual y los caracteres de las cadenas que fueron incluidas en el código fuente.

Lógicamente, de este modo _hardcodear_ una contraseña en el código fuente hace que el código fuente sea inseguro pero es que incluso distribuir el binario compilado no es seguro ya que cualquier usuario que tenga acceso al binario de la aplicación es potencialmente capaz de recuperar la contraseña. Quien dice contraseña dice igualmente una clave privada de cifrado simétrico usada para cifrar o descifrar datos o un _bearer token_ de OAuth. En definitiva es un problema de seguridad.

Compilado el programa y utilizando la herramienta _javap_ se puede obtener el valor de la contraseña. ¿Adivinas cual es la contraseña en este archivo binario de _bytecode_ examinado el contenido?

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="class-hex.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Contenido hexadecimal de un archivo binario de bytecode Java"
        caption="Contenido hexadecimal de un archivo binario de bytecode Java" >}}
</div>

El siguiente ejemplo sencillo de un programa Java incluye una cadena con una supuesta contraseña. Se observa que en el archivo visualizado en formato hexadecimal o decompilado los caracteres de la cadena son fácilmente reconocibles.

{{< gist picodotdev f273b2f5bd210887b89a2eeb5dc97236 "Main.java" >}}

Para compilar este pequeño programa se utiliza el comando _javac_ que genera el archivo de _bytecode_ _Main.class_.

{{< gist picodotdev f273b2f5bd210887b89a2eeb5dc97236 "javac.sh" >}}

Para decompilar este pequeño programa se utiliza el comando _javap_, con él se ven las instrucciones interpretadas por la máquina virtual de Java y la cadena con la contraseña.

{{< gist picodotdev f273b2f5bd210887b89a2eeb5dc97236 "javap.sh" >}}

Una solución para evitar este problema de seguridad es [ubicar la contraseña a un archivo de configuración incluso con los valores sensibles cifrados][blogbitix-351] y que sean descifrados únicamente por la aplicación en el momento de iniciarse. En el caso de ubicar este archivo de configuración en un servidor se puede proteger mediante permisos para que solo los administradores o algunos desarrolladores tenga acceso a él y no cualquier usuario que consiga acceso al sistema.

{{% /post %}}
