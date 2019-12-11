---
pid: 448
title: "Buscar en el historial y otras coincidencias desde la barra de direcciones inteligente de Firefox"
url: "/2019/12/buscar-en-el-historial-y-otras-coincidencias-desde-la-barra-de-direcciones-inteligente-de-firefox/"
date: 2019-12-11T17:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["planeta-codigo", "software"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="firefox.svg" title1="Firefox" width1="200" >}}

Los navegadores web guardan un historial de todas las páginas a las que se acceden, además de los marcadores que los usuarios creen. Este historial en Firefox se guarda en orden cronológico pudiendo consultar todas las páginas accedidas en el día actual, el de ayer y anteriores fechas. También es posible realizar búsquedas para encontrar una cierta página. En Firefox al historial se accede desde en menú con la opción _Historial > Mostrar todo el historial_. El historial se muestra en una ventana.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="historial-firefox.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="650x450" title1="Historial de páginas en Firefox"
        caption="Historial de páginas en Firefox" >}}
</div>


Acceder al historial requiere abrir una ventana y luego hacer una búsqueda pero sin necesidad de abrir la ventana del historial desde la barra de direcciones inteligente también pueden hacerse búsquedas, no solo para las páginas del historial sino también entre los marcadores, pestañas abiertas, por títulos de página o por coincidencias en la dirección web. Con ciertos caracteres especiales se determina el comportamiento de la búsqueda.

* <code>^</code>: para buscar coincidencias en el historial de búsqueda.
* <code>*</code>: para buscar coincidencias en los marcadores.
* <code>+</code>: para buscar coincidencias en las etiquetas de las páginas.
* <code>%</code>: para buscar entre las pestañas abiertas actualmente.
* <code>#</code>: Para buscar coincidencias en usando los títulos de página.
+ <code>$</code>: para buscar coincidencias en la dirección web.

En esta captura de hace una búsqueda por el título de las páginas en el historial.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="barra-de-busqueda-firefox.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="650x450" title1="Barra de búsqueda inteligente de Firefox"
        caption="Barra de búsqueda inteligente de Firefox" >}}
</div>

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Address bar autocomplete in Firefox - Search your bookmarks, history and tabs](https://support.mozilla.org/en-US/kb/address-bar-autocomplete-firefox?redirectlocale=en-US&redirectslug=awesome-bar-search-firefox-bookmarks-history-tabs)
{{% /reference %}}

{{% /post %}}
