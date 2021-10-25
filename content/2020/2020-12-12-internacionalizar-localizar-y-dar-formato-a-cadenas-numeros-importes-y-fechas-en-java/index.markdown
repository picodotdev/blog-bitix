---
pid: 539
type: "post"
title: "Internacionalizar, localizar y dar formato a cadenas, números, importes y fechas en Java"
url: "/2020/12/internacionalizar-localizar-y-dar-formato-a-cadenas-numeros-importes-y-fechas-en-java/"
date: 2020-12-12T16:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "La internacionalización y localización son funcionalidades habitualmente necesarias de implementar en una aplicación. La localización ha de tener en cuenta los diferentes literales para cada idioma y no _hardcodearlos_ en el código fuente de la aplicación, las diferentes formas plurales de los lenguajes, el formato de números e importes, fechas e incluso utilizar la forma correcta para ordenar cadenas alfabéticamente."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Muchas aplicaciones necesitan soportar múltiples idiomas, es imprescindible en aquellas multinacionales dirigidas a usuarios con diferentes idiomas, país, cultura o región. Aunque la aplicación no sea multinacional también se necesita si en el mismo país hay múltiples idiomas oficiales en diferentes regiones como en España que el idioma oficial es el español pero comparte oficialidad con Euskera en el País Vasco, con el catalán en Catalunya y el gallego en Galicia. Y aunque no sea imprescindible soportar múltiples idiomas es aconsejable usar las facilidades del lenguaje, librerías o _framework_ para no _hardcodear_ en el código cadenas de literales, no cuesta mucho hacerlo si es desde un principio pero que añadirlo con posterioridad significa revisar el código fuente completo de la aplicación.

Añadir la funcionalidad de internacionalización (i18n) a una aplicación requiere de código específico, la internacionalización genera uno o varios archivos de recursos que posteriormente con el proceso de localización (l10n) genera archivos de recursos adicionales para los diferentes idiomas, países, culturas y regiones. Por otro lado, la funcionalidad de localización está relacionada con la funcionalidad de dar formato a cadenas, números, importes y fechas.

Para generar los archivos localizados se suele utilizar una [herramienta especializada en realizar traducciones como Weblate][blogbitix-358] de software libre que permite coordinar el trabajo de internacionalización de los desarrolladores proporcionando el archivo de recurso de la aplicación con las cadenas originales a localizar y con el trabajo de localización de los traductores que proporcionan los recursos localizados.

{{< tableofcontents >}}

### Archivos de cadenas y recursos localizados con Java

El lenguaje Java ofrece soporte para la internacionalización y localización con varias clases incluidas en el JDK y la API. La clase [Locale](javadoc11:java.base/java/util/Locale.html) identifica un idioma, país, región y cultura. La clase [ResourceBundle](javadoc11:java.base/java/util/ResourceBundle.html) ofrece acceso a los archivos de recursos y _properties_ adecuado según el _Locale_ para el que se quieran obtener los recursos.

Los archivos de recursos de Java en formato _properties_ son archivos de texto con una cadena por línea, cada cadena está identificada con una clave. La clave de la cadena es la misma en todos los archivos localizados de la cadena, varía el literal asociado de la cadena. Es recomendable que los archivos de recursos utilicen el formato de caracteres UTF-8 par soportar los caracteres de todos los alfabetos como cirílicos, kanji y árabes.

{{< code file="resource.properties" language="plaintext" options="" >}}
{{< code file="resource_en.properties" language="plaintext" options="" >}}
{{< code file="resource_es.properties" language="plaintext" options="" >}}

### La clase Locale y Locales soportados por Java

El JDK de Java proporciona soporte para múltiples _locales_ con los idiomas mayoritarios en el mundo. La lista completa de locales para los que Java incluye soporte en el JDK se obtiene con la clase _Locale_. En caso de que el _Locale_ no tenga una variable con el _locale_ estática se ofrece un constructor para indicar el código del lenguaje, el país y su variante.

{{< code file="Main-locales-1.java" language="java" options="" >}}
{{< code file="System-locales-1.out" language="plaintext" options="" >}}

La clase _Locale_ está formada por el identificador de dos letras del idioma y opcionalmente adicionalmente por el identificador del país y opcionalmente adicionalmente un código de variante. Estos tres identificadores separados por un guión forman el identificador del _Locale_. En los archivos de recursos los guiones se ponen con barras bajas.

{{< code file="Main-locales-2.txt" language="plaintext" options="" >}}

### Localización de cadenas

La clase _ResourceBundle_ busca y devuelve el literal adecuando según la clave que lo identifica y el _Locale_ deseado.

{{< code file="Main-literals-1.java" language="java" options="" >}}
{{< code file="System-literals-1.out" language="plaintext" options="" >}}

#### Cadenas con argumentos

Algunas cadenas tienen datos o _placeholders_ que son insertados en tiempo de ejecución como un número, fecha, importe u otra pequeña cadena como un nombre, para insertar estos datos se utiliza la clase [MessageFomat](javadoc11:java.base/java/text/MessageFormat.html) que sustituye los argumentos o _placeholders_ por los valores proporcionados.

Los argumentos o _placeholders_ están numerados entre llaves.

{{< code file="Main-literals-2.java" language="java" options="" >}}
{{< code file="System-literals-2.out" language="plaintext" options="" >}}

#### Herencia de cadenas

La clase _ResourceBundle_ implementa un mecanismo de herencia por el que si no existe el archivo o clave de la cadena de la clave solicitada en el archivo de recursos del _locale_ indicado se obtiene la cadena del siguiente _locale_ más general. Por ejemplo, si se desea obtener una cadena para el _Locale_ _es\_ES_ (español de España) o _es\_MX_ (español de México) para el que no existe su propio archivo de recursos o la clave de la cadena entonces se obtiene la cadena del archivo de recurso español (_es_), si incluso no existiese en el _locale_ _es_ se obtendría la cadena del recurso base _resource.properties_.

{{< code file="Main-literals-3.java" language="java" options="" >}}
{{< code file="System-literals-3.out" language="plaintext" options="" >}}

#### Localización de cadenas con múltiples formas plurales

La localización de las cadenas también ha de tener en cuenta que los lenguajes usan varias formas plurales, cada lenguaje tiene su propio número de formas plurales. El español tiene dos formas plurales, la de un elemento y más de un elemento. Otros lenguajes tiene tres o más formas plurales según las propias reglas del lenguaje y número de elementos a los que se refiera la expresión, por ejemplo el ruso tiene tres formas plurales y el chino una sola forma plural.

En español dependiendo del número de elementos a los que nos referimos se utiliza el singular o el plural.

{{< code file="Main-pluralforms-1.txt" language="plaintext" options="" >}}

Las aplicaciones que no soportan diferentes normas plurales en las cadenas suelen utilizar expresiones como las siguientes, la cadena es la misma tanto para el singular como para el plural.

{{< code file="Main-pluralforms-2.txt" language="plaintext" options="" >}}

En Java la clase que permite utilizar diferentes formas plurales es [ChoiceFormat](javadoc11:java.base/java/text/ChoiceFormat.html). Utiliza un formato especial para que cadena elegir según la forma plural, está formada por una colección de número de forma plural y la cadena a utilizar. Para elegir la cadena adecuada según la forma plural se ha de proporcionar el número de elementos a los que se refiere la expresión, este número selecciona la cadena de la forma plural.

{{< code file="Main-pluralforms-3.java" language="java" options="" >}}
{{< code file="System-pluralforms-3.out" language="plaintext" options="" >}}

### Localización de fechas

Las fechas también son textos que varían según el _Locale_ ya sea según el formato que se utilicen para convertir las fechas a texto como por incluir nombres de semana, nombres de los meses e intercalar algunas preposiciones. También según el país varía el formato, en Estados Unidos el mes se indica antes que el día, con el formato _MM/dd/yyyy_ cuando en España y muchos otros países se utiliza el formato _dd/MM/yyyy_ con el día primero.

Al usar fechas también suele ser necesario [convertir fechas y husos horarios][blogbitix-64] y es recomendable [guardar las fechas en formato UTC en la base de datos][blogbitix-168] porque este huso horario no varía con el horario de verano e invierno.

{{< code file="Main-dates-1.java" language="java" options="" >}}
{{< code file="System-dates-1.out" language="plaintext" options="" >}}

### Localización de números e importes

Al igual que con las fechas hay que tener algunas [consideraciones al trabajar con importes][blogbitix-389] como utilizar la clase [BigDecimal](javadoc11:java.base/java/math/BigDecimal.html) en vez de un _float_ o _double_ y en ocasiones es necesario realizar [conversiones de importes entre diferentes divisas con ratios de conversión][blogbitix-90]. Java tiene una especificación que define una API para tareas monetarias, la [librería Moneta][java-money] es la implementación de referencia.

Los números e importes también varían en formato según el _Locale_. En los números en algunos países se utiliza el punto para separar los millares y la coma para los decimales y en otros es al revés se utiliza la coma para separar los millares y el punto para los decimales.

{{< code file="Main-numbers-1.java" language="java" options="" >}}
{{< code file="System-numbers-1.out" language="plaintext" options="" >}}

Los importes suelen incluir el símbolo de la moneda y dependiendo del _Locale_ el símbolo puede estar antes de la cifra, después de la cifra, con el código de la moneda o el símbolo de la moneda.

{{< code file="Main-money-1.java" language="java" options="" >}}
{{< code file="System-money-1.out" language="plaintext" options="" >}}

### Ordenar cadenas correctamente de forma alfabética

Incluso ordenar cadenas alfabéticamente correctamente no es una tarea tan simple como parece a primera vista. Para ordenar correctamente cadenas hay que tener en cuenta o considerar como iguales ciertos caracteres cómo la letra _a_ o la letra _á_ con tilde. Si la ordenación se hace únicamente por el código numérico del caracter dependiendo de la lista de cadenas la lista supuestamente ordenada quizá no sea la que debería.

Al ordenar cadenas también es posible diferenciar las letras en mayúsculas o considerarlas como iguales al ordenarlas. En el artículo [la forma correcta de ordenar alfabéticamente cadenas en Java][blogbitix-276] explico esta funcionalidad y que soporte ofrece Java.

{{% sourcecode git="blog-ejemplos/tree/master/JavaInternacionalizationLocalization" command="./gradlew run" %}}

{{% /post %}}
