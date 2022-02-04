---
pid: 571
type: "post"
title: "Qué son los datos estructurados y para que sirven con ejemplos"
url: "/2021/05/que-son-los-datos-estructurados-y-para-que-sirven-con-ejemplos/"
date: 2021-05-02T09:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:html.svg"
tags: ["web", "planeta-codigo", "programacion"]
summary: "Los datos estructurados son datos que describen los elementos relevantes que contiene la página, facilitan la tarea a los buscadores de analizar el contenido y extraer la información para su indexación y la aparición en las páginas de resultados. Incluir datos estructurados en cada página mejora el SEO y permite que en las páginas de resultados de los buscadores se muestre con un formato enriquecido que destaca sobre el resto de resultados."
---

{{% post %}}

{{< logotype image1="html.svg" >}}

Los buscadores rastrean la web en busca de páginas que una vez indexadas les permite mostrarlas en la página de resultados las más relevantes para las palabras clave introducidas al realizar la búsqueda.

Los [archivos _sitemap.xml_](https://developers.google.com/search/docs/advanced/sitemaps/overview?hl=es) permiten a los buscadores conocer todas páginas de las que se compone un sitio web. Los rastreadores los descargan cada cierto tiempo, de forma paulatina analizan los enlaces proporcionados en el _sitemap.xml_ y los indexa para mostrarlos como resultados si es un contenido relevante para las búsquedas de los usuarios.

Mostrar los enlaces más relevantes de una búsqueda no es una tarea sencilla, el algoritmo de indexación y del motor de búsqueda han de conocer qué es lo que trata de buscar el usuario y han de conocer cúal es el contenido de cada página. Conocer el contenido de una página no es sencillo ya que las páginas están en formato HTML que ofrece poca ayuda a los buscadores de la información que contienen.

{{< tableofcontents >}}

### Qué son los datos estructurados en una página web

Los buscadores hacen el mejor esfuerzo por entender el contenido de las páginas, los datos estructurados facilitan en gran medida el análisis del contenido. Los datos estructurados son un formato estandarizado que permite proporcionar de forma más precisa cuál es el contenido e información de una página. Para los buscadores obtener la información de la página es más sencillo y preciso obtener de los datos estructurados que analizando el texto del contenido HTML sin ninguna estructura.

Además, en las páginas de resultados los buscadores para las páginas que proporcionan datos estructurados son capaces de mostrar los resultados enriquecidos, por ejemplo, incluyendo una imagen, una descripción, una imagen o ciertos datos específicos del dato estructurado como ubicación o fecha.

Los datos estructurados son buenos para el SEO ya que los buscadores conocen mejor el contenido de la página. Por otro lado, los resultados enriquecidos destacan más sobre los que no son consiguiendo que los usuarios hagan más _clics_ en la página de resultados y atrayendo más visitantes a un sitio web.

El siguiente es un ejemplo de contenido que contiene datos como un título o fecha pero que no utiliza ningún formato de datos estructurados.

{{< code file="no-structured-data.html" language="html" options="" >}}

### Formatos de datos estructurados

Hay varios formatos de datos estructurados, el formato recomendado por Google es utilizar JSON-LD.

#### JSON-LD

[JSON for Linking Data](https://json-ld.org/) o JSON-LD es un formato de datos estructurados que se especifica en formato JSON y se incluye en la página normalmente en la cabecera separada del contenido, también se puede incluir en el cuero de la página. La diferencia de este formato es que el contenido no está mezclado con los datos estructurados.

{{< code file="json-ld.html" language="html" options="" >}}

#### Microdatos

El formato [HTML Microdata](https://www.w3.org/TR/microdata/) define nuevos atributos HTML para embeber datos simples legibles por computadoras en documentos HTML. Es similar a RDFa pero menos expresivo, simple de aprender y procesar  pero no ofrece el mismo nivel para la internacionalización, por ello en su lugar se suele recomendar usar RDFa o JSON-LD.

{{< code file="microdata.html" language="html" options="" >}}

#### RDFa

[RDFa](https://rdfa.info/) es una extensión de HTML que ayuda a marcar los datos de la página, embebiendo ciertos atributos en las etiquetas HTML.

{{< code file="rdfa.html" language="html" options="" >}}

### Tipos de datos estructurados

Hay múltiples tipos de datos estructurados según el contenido de la página. Por ejemplo, si el contenido de una página trata sobre un producto un dato es la imagen del producto, su nombre y descripción, su precio o su valoración. En el caso de un evento, por ejemplo un evento deportivo o concierto, sus datos específicos son la ubicación en la que tiene lugar, su hora de comienzo y final, además de también los participantes en el evento una su descripción.

Algunos datos son compartidos por múltiples tipos de datos estructurados, como un nombre o una descripción otros son específicos según el tipo de datos estructurados y contenido de la página. También algunos datos estructurados son comunes a todas las páginas como el autor del contenido o la organización que lo publica.

En la página [schema.org](https://schema.org/) está documentados los tipos de datos estructurados y que datos tiene cada uno. Algunas propiedades son obligatorias otras son opcionales.

Algunos tipos de datos estructurados son:

* Trabajos creativos: libro, película, música, receta, series de televisión, ...
* Objetos embebidos no de texto: objeto de audio, imagen, vídeo, ...
* Evento como un espectáculo deportivo o musical.
* Organización, como empresas.
* Persona
* Lugares, negocios locales, restaurantes, comercios, museos, ...
* Productos, ofertas, ...
* Análisis de productos
* Acciones

### Ejemplo de datos estructurados

Hay múltiples formas de incluir los datos estructurados en una página web. Una de ellas es embebiendo en el HTML los datos estructurados añadiendo ciertos atributos a etiquetas HTML.

Para separar el contenido HTML de los datos estructurados se puede utilizar _json-ld_, es la forma que recomienda Google para incluir datos estructurados en una página. Los datos estructurados _json-ld_ se suelen incluir en la cabecera de la página en formato JSON.

El el caso de mi blog en el que publico artículos el dato estructurado adecuado para cada publicación es [BlogPosting](https://schema.org/BlogPosting). Este tipo de dato estructurado tiene un título del artículo, una imagen que represente el contenido del artículo, una descripción, fecha de publicación y fecha de última actualización entre otros datos.

{{< code file="json-ld-blogbitix.html" language="html" options="" >}}

### Herramienta para validar los datos estructurados

Una vez incluidos los datos estructurados es recomendable validarlos, esto permite conocer con exactitud qué está extrayendo un buscador. Google ofrece una [herramienta de prueba de datos estructurados](https://search.google.com/structured-data/testing-tool/), basta con proporcionar la URL de la página y en el resultado se muestran los distintos tipos de datos estructurados que ha extraído de la página y sus propiedades. Para uno de los artículos del _blog_, [Analítica web con Matomo como alternativa a Google Analytics][blogbitix-546], la herramienta de análisis de Google muestra como resultado tipos de datos estructurados que reconoce que están incluídos en la cabecera de la página.

* [Herramienta de prueba de datos estructurados de una página de Blog Bitix](https://search.google.com/structured-data/testing-tool/u/0/#url=https%3A%2F%2Fpicodotdev.github.io%2Fblog-bitix%2F2021%2F01%2Fanalitica-web-con-matomo-como-alternativa-a-google-analytics%2F)

{{< reference >}}
* [Cómo funcionan los datos estructurados](https://developers.google.com/search/docs/guides/intro-structured-data)
{{< /reference >}}

{{% /post %}}
