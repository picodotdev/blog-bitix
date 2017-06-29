---
pid: 61
title: "Cómo hacer un substring de una cadena HTML"
url: "/2015/01/como-hacer-un-substring-de-una-cadena-html/"
date: 2015-01-16T13:03:52+01:00
sharing: true
comments: true
tags: ["blog-stack", "java", "planeta-codigo", "planeta-linux", "programacion", "software"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="java.png" title="Java" >}}

Una de las cosas que he tenido que resolver en [Blog Stack][blogstack] es como conseguir un extracto del contenido completo de una cadena que es HTML. Esto lo he usado en los diferentes feeds de Blog Stack donde solo se muestra el extracto de cada artículo y no el texto completo. A primera vista puede parecer fácil pero no lo es tanto, cortar una cadena que es html haciendo un substring puede ocasionar problemas si se hace en un mal punto de la cadena y luego esa cadena cortada se inserta como contenido de nuevo el una página web. Lo más probable que ocurra es una desmaquetación de la página o que los estilos aparezcan mal pero puede dejar estropeada la página completa.

Por ejemplo, si tenemos la siguiente cadena y la cortamos en un mal punto y luego insertamos en una página html ese contenido lo que ocurrirá es que todo el texto a continuación de él aparecerá en negrita.

{{< gist picodotdev 22c4114197036349dce9 "text-1.html" >}}
{{< gist picodotdev 22c4114197036349dce9 "text-2.html" >}}

Usar una expresión regular tampoco es solución, con alguna puede parecer que en algún caso funciona pero posiblemente para cada expresión regular podamos encontrar un html para el que no sirva. La expresión regular puede ser complicada.

Para dar solución a este problema podemos emplear jsoup. [Jsoup][jsoup] es una librería Java que nos permite manipular el html, desde extraer en forma de texto plano el contenido hasta modificar el html empleando una API. Empleando esta librería podemos ir extrayendo el contenido para contar cuantos caracteres de texto contiene el html y a la vez crear un extracto del documento html.

{{< gist picodotdev 22c4114197036349dce9 "AppPostRecord.java" >}}

Empleando esta librería la cadena del ejemplo anterior podría quedar:

{{< gist picodotdev 22c4114197036349dce9 "text-3.html" >}}

El resultado aplicando está solución puede verse en el [feed de la portada de Blog Stack][blogbitix-feed] donde se muestra una entradilla o extracto del inicio del contenido de cada uno de los artículos.

<div class="media" style="text-align: center;">
	{{< figure year="2015" pid="61"  
    	image1="blogstack.png" thumb1="blogstack-thumb.png" >}}
</div>

El código fuente de Blog Stack está disponible en un [repositorio de GitHub](https://github.com/picodotdev/blog-stack/tree/master) donde puede verse el código completo.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Qué es y cómo hacer «web scraping» en Java][blogbitix-47]
* [Cómo filtrar contenido HTML de forma segura][blogbitix-48]
{{% /reference %}}

{{% /post %}}
