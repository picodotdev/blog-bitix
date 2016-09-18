---
pid: 179
title: "Monitorización con eventos de Google Analytics en una página web"
url: "/2016/09/monitorizacion-con-eventos-de-google-analytics-en-una-pagina-web/"
date: 2016-09-18T12:00:00+02:00
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "planeta-codigo", "programacion"]
summary: "Conociendo cómo usar los eventos de Analytics podemos conocer información interesante acerca de qué forma los usuarios interactúan con nuestra propia página web. Cualquier cosa que podamos realizar con JavaScript o ante cualquier evento que podamos reaccionar podemos medirla con Analytics."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="google-analytics.png" title1="Google Analytics" >}}

No se mucho de [Google Analytics][google-analytics] únicamente lo que he aprendido aplicándolo en mi propia bitácora. Cuando [cambié de herramienta para generar de forma estática la bitácora][blogbitix-0] pude personalizar en mucha mayor medida que lo que podía primeramente con [Blogger][blogger] y después tenía con [Octopress][octopress].

Google Analytics además de proporcionarnos diversa información interesante sobre las visitas que recibimos en una página web puede servirnos para analizar el comportamiento de los usuarios, como interaccionan con la página y qué acciones realizan. La forma con la que podemos agregar información propia de la aplicación o página web es mediante los eventos de Analytics. Después de [instalar el _script_ de Analytics](https://developers.google.com/analytics/devguides/collection/analyticsjs/) podemos enviar eventos simplemente con la siguiente linea de código JavaScript.

{{% gist id="c66532a6eaab846b579ff55b902e874f" file="ad-enter.js" %}}

La información del evento de compone de categoría (_category_), acción (_action_), etiqueta (_opt\_label_), valor  (_opt\_value_) y si no supone interacción (_opt\_noninteraction_). Deberemos proporcionar valores según queramos estructurar los eventos obligatoriamente para categoría y acción siendo el resto opcionales. En mi bitácora uso Analytics para además de visualizar la información que proporciona sobre visitas, ubicación de los usuarios, idioma, plataforma y navegador que usan, páginas más visitadas, origen de las visitas para lanzar unos cuantos eventos que me proporcionen información sobre las pulsaciones que se realizan en el menú de categorías y en los enlaces de redes sociales, en la lista de artículos recientes, artículos destacados y en el _widget_ de ShareThis y si se hace en el del inicio del artículo o en el del final. En la sección _Comportamiento > Eventos_ se agrega toda la información de los eventos, además podremos monitorizar en tiempo real los eventos que se estén produciendo en la sección _Tiempo real > Eventos_.

El parámetro _opt_noninteraction_ afecta al porcentaje de rebote, un evento interactivo se considerará como una acción que ha realizado el usuario después de visitar la página, si ese evento lo consideramos como un objetivo podemos hacer que esa interacción no se considere en el porcentaje de rebote. Por ejemplo, si el usuario permanece en la página 30 segundos podemos lanzar un evento que indique tal circunstancia con un _timeout_ y esa visita no se considere en el porcentaje de rebote.

Podemos medir cualquier cosa que deseemos incluso podemos conocer cuantos usuarios usan un bloqueador de anuncios como [AdBlock][adblockplus]. Este es el código JavaScript que uso para conocer cuantos usuarios lo tiene activado o no.

{{% gist id="c66532a6eaab846b579ff55b902e874f" file="ad-block.js" %}}

En la sección [Seguimiento de eventos](https://developers.google.com/analytics/devguides/collection/analyticsjs/events) de la documetación de Google Analytics y en el resto de secciónes hay más detalles sobre esta herramienta. Para cualquier cosa que podamos reaccionar mediente un evento con JavaScript podemos medirla con Analytics, sirviéndonos para extraer información valiosa sobre los usuarios que nos permitan medir, entender y luego mejorar la experiencia de usuario de nuestra propia página web.

Como para cualquier otra cosa en la que queramos profundizar una buena forma es con algunos libros específicos dedicados al tema. Los siguientes son dos libros de los [muchos libros sobre Google Analytics](http://amzn.to/2cH8SXR) disponibles en [Amazon][amazon].

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=0596158009&linkId=4babfb60d80236251c0ef398bc3ab757&internal=1"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=148420266X&linkId=c179f357fbd31e18d25e93fd13c9729e&internal=1"></iframe>
</div>

{{% /post %}}
