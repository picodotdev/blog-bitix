---
pid: 538
type: "post"
title: "La herramienta Weblate para traducir una aplicación a múltiples idiomas"
url: "/2020/12/la-herramienta-weblate-para-traducir-una-aplicacion-a-multiples-idiomas/"
date: 2020-12-10T23:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:weblate.svg"
tags: ["planeta-codigo", "programacion", "software-libre"]
summary: "La internacionalización (i18n) y localización (i10n) en un proyecto de software es muy habitual y necesario cuando la aplicación ha de soportar múltiples lenguajes, países, regiones, culturas o en el mismo país diferentes idiomas oficiales según la región. La traducción necesita un flujo de trabajo coordinado entre los desarrolladores de la aplicación que realizan la internacionalización y los traductores que realizan la localización. Para facilitar el flujo de trabajo y funcionalidades útiles para los traductores hay herramientas específicas para la tarea, una herramienta para traducciones de software libre es Weblate."
---

{{% post %}}

{{< logotype image1="weblate.svg" >}}

En el desarrollo de un producto internacional destinado a los usuarios de múltiples lenguajes, países, regiones, culturas o en un mismo país en el que hay varias lenguas oficiales es un requerimiento ofrecer el producto o aplicación en el lenguaje preferido por los usuarios. Esto significa que la aplicación ha de soportar múltiples lenguajes y mostrar las cadenas de texto en la interfáz gráfica en el lenguaje preferido por el usuario.

Para que una aplicación soporte múltiples lenguajes ha de implementar internacionalización (i18n) y la localización (i10n), la internacionalización y localización son los procesos implementados en el código fuente de la aplicación que le habilita para mostrar las cadenas en un determinado lenguaje.  La localización consiste en traducir las cadenas de texto a cada uno de los lenguajes que soporte la aplicación.

La internacionalización y localización no es simplemente traducir una cadena a otro idioma, es un proceso con cierta complejidad en el que hay que tener en cuenta numerosos aspectos:

* Formas plurales. El español tiene dos formas plurales, uno y más de uno, pero otros lenguajes tienen más formas plurales algunos hasta seis formas plurales y otros lenguajes solo una forma plural.
* El formato de los horarios.
* El formato de las fechas. En español las fechas se escriben siguiendo el patrón día/mes/año pero en Estados Unidos se escribe mes/día/año.
* Monedas internacionales. Según el país, región o cantidad monetaria el formato y símbolo que representa la moneda es distinto.
* Sistema de pesos y medidas (pulgadas/centímetros, libras/gramos, etc.).
* Códigos de caracteres (cirílico, kanji, árabe, ...).
* Formato de números. El formato de escribir los números varía para los separadores decimales, separadores de miles, etc.
* Las imágenes no es un formato de texto pero también es un recurso susceptible de ser localizado.
* Según el alfabeto y caracteres las reglas para [ordenar alfabéticamente correctamente cadenas de texto][blogbitix-276].
* Algunos valores ha de insertarse en las cadenas como fechas, números u palabras.

En los puntos anteriores también hay que tener en cuenta que las traducciones y localización han de realizarlas múltiples personas o traductores, quizá tantos traductores como idiomas haya de soportar la aplicación que entiendan tanto el idioma original en el que se entregan las cadenas para traducir como el idioma, región y cultura a la que hay que localizar.

Para coordinar y facilitar el proceso de localización de una aplicación a los múltiples idiomas se suele utilizar una herramienta específica para esta tarea de traducción y localización. Los recursos localizados son utilizados en la aplicación para [localizar y formatear cadenas, cadenas con múltiples formas plurales, fechas, números e importes en un idioma][blogbitix-539].

{{< tableofcontents >}}

### Cómo funciona el proceso de internacionalización (i18n)

El proceso de internacionalización realiza cambios en el código fuente de la aplicación para que la aplicación pueda ser localizada a múltiples lenguajes, países, regiones o culturas. Se ha de tener en cuenta los números aspectos de la internacionalización y localización de la lista anterior como formas plurales, formato de fechas, formatos monetarios, formato de números, etc.

El proceso de internacionalización genera como resultado un archivo fuente de cadenas originales que es proporcionado a la herramienta de traducción para su localización por los traductores. A lo largo del tiempo habrá cadenas que se modifiquen y requieran una nueva traducción, nuevas cadenas que se añadan y cadenas que se eliminen. Las cadenas originales suelen estar en inglés por ser el lenguaje compartido en los lenguajes de programación y el más común entendido por los posibles traductores.

### Cómo funciona el proceso de localización (i10n)

El proceso de localización consisten en traducir el archivo fuente de cadenas originales generado en el proceso de internacionalización a un lenguaje, país, región o cultura. El traductor es la persona que tiene conocimiento para realizar correctamente la localización, la herramienta de traducción y localización es la que facilita la traducción.

La herramienta de traducción y localización ofrece al traductor varias funcionalidades que facilitan su tarea.

### Herramientas de traducción y localización

Para facilitar la tarea de los traductores con diferentes funcionalidades y gestionar el ciclo de vida de las cadenas se utiliza una herramienta u aplicación especializada en la tarea. El archivo fuente de cadenas originales generadas en el proceso de internacionalización se importan en la herramienta de traducción par que los traductores realicen la traducción, una vez los traductores completan la traducción la aplicación genera como resultado un archivo localizado que se importa en la aplicación para su uso en el lenguaje, país, región o cultura adecuado.

Las funcionalidades que ofrecen las herramientas de traducciones facilitan la tarea a los traductores y permiten gestionar las cadenas cuando se modifica alguna existente, se añaden nuevas o se eliminan. Algunas funcionalidades que ofrecen son:

* Glosario: un glosario de términos para emplear siempre el mismo término en todas las cadenas en el que algunas palabras hay varias posibilidades de traducción y que la localización sea consiste en toda la localización.
* Estado de la traducción: una de las principales funcionalidades es permitir encontrar fácilmente las cadenas que faltan por traducir y obtener métricas y estadísticas de las cadenas totales, cuantas están traducidas en cada lenguaje, cuantas faltan por traducir o cuantas están traducidas pero marcadas a revisar. Las estadísticas permiten conocer el estado de la localización en cada uno de los idiomas a localizar. En los flujos de trabajo más complejos se soporta un proceso de revisión y aprobación de traducciones.
* Historial: con un historial el traductor tiene cuales son las traducciones que han sido empleadas para una cadena para volver a una anterior o conocer cual era la traducción en una determinada fecha.
* Comentarios: los comentarios permiten añadir notas explicativas.
* Cadenas iguales y similares: en las cadenas originales la cadena puede estar repetida lo que evita repetir la misma traducción o proporciona parte y aumenta la consistencia.
* Búsquedas con filtros: otra funcionalidad es poder buscar cadenas por el texto en la cadena original, en la cadena traducida, por su estado o por filtros de fecha en la que se ha realizado la traducción o última modificación. Otra funcionalidad es buscar y reemplazar cuando se cambia un término o para hacer una corrección de forma masiva.
* Formatos de archivos de cadenas originales: según el lenguaje de programación o tecnología se utilizan diferentes formatos de archivo para los recursos que contienen las cadenas originales como los recursos localizados. Las aplicaciones han de soportar múltiples formatos de archivos de recursos, en Java por ejemplo se suelen utilizar los archivos _properties_, las aplicaciones de Android  e iOS tienen su propio formato.
* Imagen de contexto: para que el traductor comprenda el contexto en el que se muestra la cadena se puede adjuntar una imagen.
* Notificaciones: enviar notificaciones a los traductores cuando se añadan nuevas cadenas o se modifiquen de forma que sean traducidas de manera más rápida y automatizando la comunicación, o cuando se completa una traducción.
* Integraciones: las herramientas ofrecen una API para integrarlas en los procesos de desarrollo y automatizar las tareas de importación de nuevas cadenas o de exportación de las cadenas e incluso con los repositorios de código fuente de [Git][git] como [GitHub][github] o [GitLab][gitlab].
* Autenticación: la herramienta será utilizada tanto por desarrolladores como por múltiples traductores, esto hace necesario que posea un sistema de autenticación y permisos.

#### Alternativas de herramientas

Dos de las herramientas más conocidas con [Transifex][transifex] y [Crowdin][crowdin], son herramientas comerciales ofrecidas como SaaS aunque para proyectos de software libre permiten su uso sin ningún coste. Otra alternativa a estas de software libre es [Weblate][weblate] que además de ofrecerse como SaaS con un coste por el servicio permite autoalojarlo.

{{< image
    gallery="true"
    image1="image:transifex.png" optionsthumb1="300x200" title1="Web de Transifex"
    image2="image:crowdin.png" optionsthumb2="300x200" title2="Web de Crowdin"
    image3="image:weblate.png" optionsthumb3="300x200" title3="Web de Weblate"
    caption="Webs de Transifex, Crowdin y Weblate" >}}

#### Costes

Los costes de las herramientas suelen varia en función del número de cadenas o palabras originales a traducir, algunas funcionalidades solo está disponibles en función del plan contratado y algunos tienen limitaciones en el número de proyectos. No tiene un coste insignificante que pueden partir de unos 100 € al mes pero llegar a 600 € o 1000 € en proyectos de tamaño medio y grande.

Weblate permite autoalojarlo sin coste por el servicio salvo por el coste que tenga gestionar el servicio uno mismo. Las herramientas de traducciones son usadas de forma intensiva en el proceso inicial de traducción, una vez las traducciones están completadas la tarea de traducción es de mantenimiento con unas pocas cadenas a traducir o modificar cada cierto tiempo de forma esporádica, estár afrontando sus costes mensuales para esos momentos quizá no compensa o cuando se pretenden ahorrar costes o ser más eficiente en los mismos.

Dependiendo del caso Weblate es una opción más económica que Transifex y Crowdin.

* [Precios Transifex](https://www.transifex.com/pricing/)
* [Precios Crowdin](https://crowdin.com/pricing#annual)
* [Precios Weblate](https://weblate.org/es/hosting/)

### La herramienta para traducciones Weblate

Weblate es una de las herramientas disponibles para realizar traducciones de aplicaciones de software. Su característica diferenciadora con respecto a Transifex y Crowdin es que es software libre, esto permite hospedarlo uno mismo y un coste más económico por la funcionalidad.

Posee las múltiples funcionalidades que tienen estas herramientas de utilidad para los traductores y para gestionar las traducciones en la aplicación. Posee una API para integrar Weblate y automatizar los procesos de desarrollo y autenticación con cuentas OpenID/OAuth con los que gestionar de forma centraliza las múltiples cuentas y accesos de los usuarios que usen la herramienta.

En la extensa y detallada [documentación de Weblate](https://docs.weblate.org/en/latest/) se ofrece un manual de usuario y administración de la herramienta tanto para instalarla con [Docker][docker], configurarla como para realizar copias de seguridad y actualizaciones a nuevas versiones.

### Formatos de archivos de traducciones

Weblate soporta numerosos [formatos de archivos](https://docs.weblate.org/en/latest/formats.html). Cada formato de archivo soporta diferentes características, el formato de archivo a utilizar dependen del lenguaje de programación utilizado en la aplicación, librerías o _framework_ de desarrollo. Algunos formatos de archivo soportan múltiples idiomas en el mismo archivo de recurso, múltiples formas normales, comentarios, contexto, ubicación dela cadena en el código fuente o indicadores para las cadenas como no traducida, traducida o por revisar. 

El formato de archivo para los recursos de traducciones en Java son los archivos _properties_. Los archivos _properties_ empleados en Java para los recursos de traducciones son un archivo de texto con una linea por cadena compuesta por la clave o identificador de la cadena y el texto de la cadena. Los archivos _properties_ no soportan múltiples formas plurales. Para implementar múltiples formas plurales se ha de utilizar la clase [ChoiceFormat](javadoc11:java.base/java/text/ChoiceFormat.html), las cadenas con múltiples formas plurales necesitan un formato de cadena especial. Por otro lado las cadenas pueden tener ciertos datos o _placeholders_ que son proporcionadas por la aplicación en el momento de ejecución al construir la cadena definitiva que ve el usuario, los datos son un nombre, número o un importe, la clase [MessageFormat](javadoc11:java.base/java/text/MessageFormat.html) permite realizar la inserción de estas palabras en los _placeholders_ de las cadenas.

Otros formatos de archivos de recursos son para las aplicaciones Android, iOS, PHP, i18next para JavaScript entre otros muchos.

#### Inicio usando Docker

Weblate proporciona un archivo de [Docker Compose][docker-compose] con el que iniciar la herramienta fácilmente. Weblate utiliza una base de datos [PostgreSQL][postgresql] y [Redis][redis] que en el archivo de Docker Compose se inician al mismo tiempo que el contenedor de Weblate. En el archivo de Docker Compose se indica en nombre de usuario y contraseña entre otras muchas [variables de entorno de configuración](https://docs.weblate.org/en/latest/admin/install/docker.html) con el que realizar la autenticación en la página _http\://localhost_ de inicio de sesión.

{{< code file="weblate-docker.sh" language="bash" options="" >}}

Es necesario crear un archivo _docker-compose.override.yml_, dos de ellas se utilizan para el inicio de sesión con el nombre de usuario administrador y su contraseña.

{{< code file="docker-compose.override.yml" language="yaml" options="" >}}

{{< image
    gallery="true"
    image1="image:weblate-authentication.png" optionsthumb1="300x200" title1="Autenticación en Weblate"
    image2="image:weblate-start.png" optionsthumb2="300x200" title2="Página de inicio de Weblate"
    caption="Autenticación en Weblate" >}}

Los diferentes recursos de una aplicación se agrupan en proyectos y componentes. El primer paso es crear un proyecto y los componentes como sean necesarios para realizar la traducción de cada uno de los recursos a traducir. Los componentes son los recursos a traducir, es posible obtener el archivo de traducciones desde el repositorio de código fuente o crearlo desde un archivo. Al crear el componente desde un archivo hay que especificar los parámetros _Plantilla para traducciones nuevas_, _Máscara de archivos_, _Formato de archivo_ y _Archivo de base monolingüe_.

{{< image
    gallery="true"
    image1="image:weblate-create-project.png" optionsthumb1="300x200" title1="Crear un proyecto en Weblate"
    image2="image:weblate-components.png" optionsthumb2="300x200" title2="Componentes en Weblate"
    caption="Creación de un proyecto en Weblate" >}}

{{< image
    gallery="true"
    image1="image:weblate-create-component-01.png" optionsthumb1="300x200" title1="Crear un componente en Weblate"
    image2="image:weblate-create-component-02.png" optionsthumb2="300x200" title2="Crear un componente en Weblate"
    caption="Creación de un componente en Weblate" >}}

Este es un ejemplo de archivo original _properties_ de Java con varias cadenas a traducir.

{{< code file="web.properties" language="plaintext" options="" >}}

El siguiente paso es definir los idiomas deseados en los que realizar la traducción del componente con el botón _Comenzar una traducción nueva_.

{{< image
    gallery="true"
    image1="image:weblate-component-01.png" optionsthumb1="300x200" title1="Componente en Weblate"
    image2="image:weblate-component-02.png" optionsthumb2="300x200" title2="Componente en Weblate"
    image3="image:weblate-start-translation.png" optionsthumb3="300x200" title3="Iniciar una traducción en Weblate"
    caption="Componente e iniciar una traducción en Weblate" >}}

{{< image
    gallery="true"
    image1="image:weblate-component-03.png" optionsthumb1="300x200" title1="Componente en Weblate"
    caption="Componente en Weblate" >}}

La herramienta de traducción muestra la cadena a traducir del archivo fuente de traducciones, lac clave de la cadena y múltiples herramientas para asistir en la traducción. Algunas de estas herramientas de asistencia en la traducción son un glosario de términos para que la traducción sea consistente, sugerencias a partir de otras cadenas iguales o similares ya traducidas, comentarios, un historial o como se ha traducido en otros idiomas además de permitir adjuntar una captura de pantalla con el contexto en el que se muestra la cadena.

Se ofrecen también un filtro para buscar cadenas por su estado o por coincidencias en la traducción de la cadena origen o en las cadenas traducidas de los diferentes idiomas o según la fecha de traducción.

{{< image
    gallery="true"
    image1="image:weblate-translation.png" optionsthumb1="300x200" title1="Traducción en Weblate"
    image2="image:weblate-search.png" optionsthumb2="300x200" title2="Búsqueda en Weblate"
    caption="Traducción en Weblate" >}}

Una vez las cadenas se han traducido y localizado es posible descargar los archivos de recursos localizados a importar en el código fuente de la aplicación, también es posible hacerlo de forma manual o de forma automatizada. Los archivos _properties_ siguen una convención en los nombres de archivos de recursos compuesta por el nombre del recurso, idioma, opcionalmente país y variante separados por una barra baja con el siguiente formato _resource_es_ES.properties_. La descarga manual se realiza desde _Archivos > Descargar archivos de traducción en archivador ZIP_.

{{< code file="web_es.properties" language="plaintext" options="" >}}
{{< code file="web_es-ES.properties" language="plaintext" options="" >}}
{{< code file="web_es-MX.properties" language="plaintext" options="" >}}

La operación final para completar el flujo de trabajo de traducción es actualizar las cadenas a traducir cuando por modificaciones en la aplicación haya nuevas cadenas por traducir, se hayan modificado existentes o se haya eliminado alguna. La carga del nuevo archivo fuente de traducciones se realiza desde _Archivos > Cargar traducción_ en el idioma base de la traducción, para el caso de que el archivo fuente tenga nuevas cadenas a traducir hay que seleccionar la opción _Reemplazar traducción existente_.

{{< image
    gallery="true"
    image1="image:weblate-update-translations.png" optionsthumb1="300x200" title1="Actualizar traducciones en Weblate"
    caption="Actualizar traducciones en Weblate" >}}

{{< reference >}}
* [Localization and Plurals](https://developer.mozilla.org/en-US/docs/Mozilla/Localization/Localization_and_Plurals)
* [Language Plural Rules](https://unicode-org.github.io/cldr-staging/charts/37/supplemental/language_plural_rules.html)
{{< /reference >}}

{{% /post %}}
