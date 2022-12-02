---
pid: 663
type: "post"
title: "Base de datos de países, regiones, ciudades, códigos postales y otra información geográfica"
url: "/2022/12/base-de-datos-de-paises-regiones-ciudades-codigos-postales-y-otra-informacion-geografica/"
date: 2022-12-01T19:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:geonames.png"
imagePost: "image:geonames.png"
tags: ["programacion", "planeta-codigo"]
summary: "Entre las funcionalidades habituales en una aplicación está un formulario para introducir una dirección. El formulario de dirección consta de varios campos en el que suele ser recomendable realizar unas mínimas validaciones como que el código postal o ciudad sea correcto para evitar errores inadvertidos del usuario o intencionados con información incorrecta. La base de datos de GeoNames proporciona información geográfica muy útil y de forma gratuita que incluye países, ciudades, subdivisiones administrativas, códigos postales, nombres en diferentes idiomas y mucha otra información de todos los paises del mundo."
---

{{% post %}}

No todo son microservicios es más ni siquiera estos son la mejor opción en muchos casos, las cosas mundanas tienen también mucha importancia en la aplicación cómo ordenar alfabéticamente cadenas o introducir una dirección postal. Es habitual un formulario en el que haya que introducir direcciones lo que incluye códigos postales, ciudad, país y quizá adicionalmente un teléfono.

Lo que parece un simple formulario con varios campos de datos se complica cuando hay que validar los códigos postales, ciudad de un país o al incluir información de las diferentes subdivisiones administrativas. Más si en el formulario hay que tener en cuenta las diferentes variaciones según cada país, los códigos postales de España son muy diferentes de los de Reino Unido lo que significa que la expresión regular es muy diferente y según la normativa del país.

Estas tareas que parecen sencillas realmente tienen su dificultad, ordenar caracteres alfabéticamente se complica ya que hay que tener en cuenta las tildes o mayúsculas y minúsculas y validar direcciones para recibir datos correctos requiere validaciones y conocer cuales son los valores válidos.

* [La forma correcta de ordenar alfabéticamente cadenas en Java][blogbitix-276]
* [Ofrecer contenido personalizado al usuario basado en su ubicación][blogbitix-147]

El listado de países, unidades administrativas como comunidades autónomas, ciudades y códigos postales además del código del prefijo telefónico del país o zona horaria es habitual configurarlo como información más o menos estática en las aplicaciones ya sea en una base de datos o en archivos de configuración.

{{< tableofcontents >}}

### La base de datos de información geográfica de GeoNames

Una fuente que permite obtener esta información geográfica es la página de [GeoNames][geonames] que tiene una [licencia de Creative Commons][creativecommons]. La base de datos de información está compuesta por varios archivos CSV muchos separados por tabuladores con las varias columnas de información. En el [archivo README](https://download.geonames.org/export/dump/readme.txt) hay una lista de los archivos de los que consta la base de datos.

{{< code file="archives.txt" language="plain" options="" >}}
{{< code file="table-geoname.txt" language="plain" options="" >}}
{{< code file="table-alternate-names.txt" language="plain" options="" >}}

#### countryInfo.txt

Este archivo contiene la lista de países. Entre su información está el código del país, las expresiones regulares de los códigos postales, el prefijo del teléfono, código de la divisa, lenguaje entre otra información como población, superficie y la capital además de los países vecinos.

{{< code file="country-es.txt" language="plain" options="" >}}

#### allCountries.zip y XX.zip

El archivo _allCountries.zip_ contiene las ciudades de todos los países (todos los archivos _XX.zip_) y en los archivos _XX.zip_ están las ciudades de un país en concreto, _ES.zip_ para las ciudades de España. Contienen el código ciudad, latitud y longitud, zona horaria y los códigos de las unidades administrativas junto con su población, elevación e identificador _geonameid_

{{< code file="es-bilbao.txt" language="plain" options="" >}}

Hay varios archivos de ciudades filtrados según si superan una cantidad de población, _cities500.zip_, _cities1000.zip_, _cities5000.zip_, _cities15000.zip_. Las columnas son las mismas que las del archivo anterior donde están agrupadas por país pero en este están agrupadas por población.

{{< code file="cities15000-bilbao.txt" language="plain" options="" >}}

#### alternateNamesV2.zip

Contiene diversa información más extendida de cada uno de los _geonames_, incluyendo su nombre en diferentes idiomas, el conjunto de códigos postales asociados, identificador del aeropuerto y enlaces a la wikipedia.

El archivo _alternateNamesV2.txt_ es tan grande que ocupa varios cientos de megabytes descomprimido de modo que casi no se puede abrir con un editor y hay que recurrir al comando _grep_ para buscar dado el _geonameid_.

El _geonameid_ de la ciudad de Bilbao es _3128026_ y buscando en el archivo se encuentran estas referencias que incluyen los diferentes nombres en diferentes lenguajes, códigos postales, _iata_ y _uncl_ para la denominación del aeropuerto además de otra información como enlaces a la wikipedia. En casi de en un mismo lenguaje haber varios nombres el 1 indica cual es la denominación preferida.

{{< code file="grep-alternate-names-bilbao.sh" language="bash" options="" >}}
{{< code file="grep-alternate-names-bilbao.out" language="plain" options="" >}}

#### admin1CodesASCII.txt

Contiene las unidades administrativas del primer nivel, en el caso de España son las comunidades autónomas. El archivo incluye el _geonameid_ asociado a la comunidad autónoma.

{{< code file="admin1-es.txt" language="plain" options="" >}}

#### admin2Codes.txt

Contiene las unidades administrativas del segundo nivel, en el caso de España son las provincias. El archivo incluye el _geonameid_ asociado a la provincia.

{{< code file="admin2-es.txt" language="plain" options="" >}}

#### featureCodes.txt, timeZones.txt y hierarchy.zip

Los _geonames_ pueden tener un metadato asociado con varios identificadores que en la documentación se denominan _features_, hay varias categorías de identificadores y varios identificadores dentro de cada categoría. Por ejemplo, la feature _P_ se utiliza para ciudades y villas y _PPLA1_, _PPLA2_, _PPLA3_, _PPLA4_, _PPLA5_ indica el nivel de unidad administrativa. Las _features_ permiten conocer a qué representa el _geoname_ por ejemplo si es un aeropuerto o una universidad entre muchos otros elementos.

El archivo _hierarchy.zip_ permite reconstruir la relación jerárquica de los diferentes _geonames_

#### shapes_all_low.zip

Contiene el polígono del _geoname_ que permite una representación gráfica de su superficie, por ejemplo del país.

{{< code file="shape-es.txt" language="plain" options="" >}}

#### Información de la base de datos

Cada uno de los elementos geográficos tiene asociado un identificador de _geonameid_, con este identificador es posible obtener más información en el archivo _alternateNamesV2.zip_ y relacionar la información de unos archivos con la información de otros archivos. La información que incluyen están en columnas separadas por tabuladores, las columnas son las indicadas en estructura de la tabla _geoname_ y _alternate names_.

Los archivos contienen mucha información, posiblemente en una aplicación solo interese parte de toda esa información y se puede ignorar el resto. Por ejemplo, en la información de los países se incluye su extensión de superficie, población, continente moneda, lenguaje o la capital del país que para validar direcciones no es necesaria pero la moneda o lenguajes es útil para mostrar precios o textos según el idioma del país.

Hay otra información muy útil como los nombres de las ciudades en diferentes idiomas e incluso las abreviaturas de los aeropuertos. Por supuesto están las expresiones regulares de los códigos postales o los códigos de prefijo de los teléfonos, los propios del país conoceremos pero la de muchos otros países seguro que no.

La expresión regular de España es esta.

{{< code file="regexp-es.txt" language="plain" options="" >}}

La de Reino Unido es esta otra.

{{< code file="regexp-uk.txt" language="plain" options="" >}}

### Cómo acceder a la información

Los archivos son muy grandes, seguramente no sea necesaria toda la información y no está en el formato adecuado para un rápido acceso al dato que se necesita en una aplicación, con lo que hay que procesarlos para adaptarlos. Los archivos son archivos de texto con los datos de las columnas separados por tabuladores, una librería para procesar los archivos con el lenguaje Java es [OpenCSV][opencsv].

* [Ejemplo sencillo de cómo leer datos de un archivo Excel o CSV en Java][blogbitix-357]

Es necesario procesar los archivos de alguna forma para extraer y transformar la información de los archivos de GeoNames a un formato adecuado para la aplicación posiblemente con un _script_ en el lenguaje [Groovy][groovy], Java, [Kotlin][kotlin] u otro lenguaje. La información geográfica quizá no cambie muy habitualmente pero seguramente hay que procesar los archivos cada cierto tiempo o si por alguna circunstancia se produce un cambio significativo que requiera una actualización.

* [Java para tareas de scripting con JBang y Gradle][blogbitix-108]

Una vez disponible la información en la aplicación conviene crear una serie de clases que hagan de fachada en el acceso a la información de tal forma que el resto del código no se acople a la implementación interna de los datos que puede cambiar en el futuro. Hay varias formas de implementación, una en que las clases de información geográfica serían un módulo en la aplicación y propietaria de los datos geográficos. Otra opción es que las tablas estén en una base de datos y sea compartida por varias aplicaciones, en este caso las tablas o vistas se convierten en una interfaz para las aplicaciones.

{{% /post %}}