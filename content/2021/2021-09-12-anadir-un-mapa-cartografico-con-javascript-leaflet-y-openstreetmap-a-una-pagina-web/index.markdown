---
pid: 596
type: "post"
title: "Añadir un mapa cartográfico con JavaScript, Leaflet y OpenStreetMap a una página web"
url: "/2021/09/anadir-un-mapa-cartografico-con-javascript-leaflet-y-openstreetmap-a-una-pagina-web/"
date: 2021-09-12T00:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:leaflet-openstreetmaps.png"
imagePost: "image:leaflet-openstreetmaps.png"
tags: ["planeta-codigo", "web"]
summary: "Google Maps es un servicio de mapas cartográficos de Google que permite consultar mapas directamente desde la página de Google pero también insertarlo en páginas de terceros. En el caso de insertar mapas en una página de terceros como muchos de otros de sus servicios ofrece con una capa gratuita que cubre un pequeño número de peticiones. Aunque OpenStreetMap no ofrece el mismo nivel de detalle y calidad de la información que Google Maps permite su uso sin coste incluso con fines comerciales suficiente en muchos casos."
---

{{% post %}}

{{< logotype image1="html.svg" >}}

Un mapa cartográfico permite mostrar una ubicación real a escala en una vista aérea y con diferentes niveles de acercamiento, mostrando calles, zonas verdes, ciudades y comercios de hostelería, comerciales, farmacias, elementos de equipamiento y de otros tipos.

Para mostrar ubicaciones reales de un localización, calle o ciudad [Google][google] con su servicio de mapas de [Google Maps][google-maps] permite insertar sus mapas en una página web. Google Maps además permite ver a nivel de calle una ubicación.

Sin embargo, [Google Maps tiene límites de uso](https://cloud.google.com/maps-platform/pricing/) a partir de los cuales hay que pagar por usar el servicio y algunas funcionalidades solo están disponibles en el servicio de pago. Como alternativa están los mapas de [OpenStreetMap][openstreetmap] que tiene una licencia de acceso libre siempre que se otorgue reconocimiento.

{{< tableofcontents >}}

### Mapas con Leaflet e imágenes de OpenStreetMap

La librería [Leaflet][leafletjs] de JavaScript de código abierto permite insertar un _iframe_ en cualquier página web usando los mapas cartográficos de OpenStreetMap. Soporta marcadores, ventanas emergentes con información, capas vectoriales, navegadores de escritorio y móvil, navegación el teclado y eventos de JavaScript como las habituales interacciones para hacer _zoom_ con la rueda del ratón o arrastrar y soltar para desplazar el mapa entre otras funcionalidades.

Insertar el mapa requiere importar el JavaScript y el CSS de Leaflet en la página, crear una etiqueta HTML que actúe como contenedor del mapa con el tamaño deseado e iniciar el JavaScript de Leaflet para que inserte las imágenes del mapa hay que proporcionar las coordenadas de latitud y longitud. Leaflet ofrece la posibilidad de incluir marcadores, figura geométricas y otros tipos de información para enriquecer los mapas cartográficos.

* [Ejemplo de mapa con Leaflet y OpenStreetMap](code/leaflet-openstreetmap.html,target=_blank)

{{< image
    gallery="true"
    image1="image:leaflet-openstreetmap.jpg" optionsthumb1="650x450" title1="Mapas de OpenStreetMaps en vista callejero con Leaflet"
    caption="Mapas de OpenStreetMap en vista callejero con Leaflet" >}}

{{< code file="leaflet-openstreetmap.html" language="html" options="" >}}

### Mapas con Leaflet e imágenes de Google Maps

No he visto que OpenStreetMap ofrezca una vista aérea en modo satélite de las ubicaciones, sin embargo, cambiando el origen de las imágenes Leaflet es capaz de cargar las capas de otros servicios incluído el de Google. Este es el mismo mapa con la vista de callejero y satélite de la misma ubicación utilizando como fuente de las imágenes el servicio de Google.

* [Ejemplo de mapa con Leaflet y Google Maps _Streets_](code/leaflet-google-maps.html,target=_blank)
* [Ejemplo de mapa con Leaflet y Google Maps de _Streets_ y _Satellite_ (_Hybrid_)](code/leaflet-google-maps-hybrid.html,target=_blank)

{{< image
    gallery="true"
    image1="image:leaflet-google-maps.jpg" optionsthumb1="300x250" title1="Mapas de Google en vista callejero"
    image2="image:leaflet-google-maps-hybrid.jpg" optionsthumb2="300x250" title2="Mapas de Google en vista satélite"
    caption="Mapas de Google en vista callejero y satélite" >}}

{{< code file="leaflet-google-maps.html" language="html" options="" >}}
{{< code file="leaflet-google-maps-hybrid.html" language="html" options="" >}}

Las URLs de Google dependiendo de la capa de imágenes son las siguientes en las que varía los valores del parámetro _lyrs_.

* _Streets_
* _Hybrid_
* _Satellite_
* _Terrain_

{{< code file="google-maps-urls.js" language="javascript" options="" >}}

{{< reference >}}
* [Inicio rápido Leaflet](https://leafletjs.com/examples/quick-start/)
* [Documentación Leaflet](https://leafletjs.com/reference-1.7.1.html)
* [Leaflet Map API with Google Satellite Layer](https://stackoverflow.com/questions/9394190/leaflet-map-api-with-google-satellite-layer)
{{< /reference >}}


{{% /post %}}
