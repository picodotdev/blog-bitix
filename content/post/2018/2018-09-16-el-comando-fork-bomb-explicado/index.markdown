---
pid: 347
title: "El comando fork bomb explicado"
url: "/2018/09/el-comando-fork-bomb-explicado/"
date: 2018-09-16T11:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["gnu-linux", "planeta-codigo"]
draft: true
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="gnu.svg" title1="GNU" width1="200" image2="linux.svg" title2="Linux" width2="200" >}}

El siguiente aparente, gracioso e inocente conjunto de caracteres que parecen una colección de varios extraños _emojis_ es un comando que si se ejecuta en una terminal de GNU/Linux crea gran cantidad de procesos de forma continua hasta que los recursos del sistema se agotan causando que sea inusable haya que reiniciarlo generando los problemas que de ello se deriven si el reinicio es forzado, este comando es el comando [fork bomb](https://en.wikipedia.org/wiki/Fork_bomb).

<pre>:(){ :|: & };:</pre>
{{< gist picodotdev id "file" >}}

Analizando detalladamente este conjunto de caracteres es un comando _bash_ que se compone de la definición de una función de nombre _:_, los paréntesis _( )_ definen la función, que en su cuerpo entre las llaves _{ }_ recursivamente se llama a si misma dos veces con un _pipeline |_ y una llamada con _&_ que no espera a que termine y lo pone en segundo plano llamando de nuevo a la función y repitiendo el proceso.

A continuación de la función se encuentra el comando _:_ que realiza la primera llamada a la función que desencadena el _fork bomb_. Esta llamada inicial está separada por el caracter _;_ que sirve para escribir varios comandos uno a continuación de otro en una misma línea. El primer comando es la definición de la función y el segundo hace la llamada a la función e inicia el _fork bomb_.

Este _fork bomb_ crea procesos en el sistema de forma exponencial (1, 2, 4, 8, 16, 32, ..., 2^n) hasta que el sistema agota los recursos de procesador o memoria en un periodo de tiempo muy corto, menos de unos pocos segundos desde su inicio.

Dado el problema que causa este aparente comando u otro similar una buena recomendación a seguir es no ejecutar ningún comando obtenido de internet que no sepamos lo que hace y de igual modo ningún programa o _script_ de _shell_ no obtenido de una fuente de confianza. En las distribuciones GNU/Linux la fuentes confiables son los repositorios oficiales.



{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Fork Bomb](https://en.wikipedia.org/wiki/Fork_bomb)
{{% /reference %}}

{{% /post %}}
