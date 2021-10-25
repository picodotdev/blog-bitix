---
pid: 594
type: "post"
title: "Los diagramas UML para documentar y una aplicación para crearlos"
url: "/2021/09/los-diagramas-uml-para-documentar-y-una-aplicacion-para-crearlos/"
date: 2021-09-02T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:gaphor.svg"
tags: ["planeta-codigo", "programacion"]
summary: "Todo el conocimiento está en el código fuente pero este no tiene una forma de fácil y rápida comprensión a alto nivel, para comprender un sistema es necesario una forma de documentación que muestre los detalles importantes de los que se compone el sistema. El lenguaje unificado de desarrollo o UML permite describir un sistema utilizando diferentes diagramas específicos para mostrar diferentes aspectos del sistema. Gaphor es una aplicación de software libre disponible para diferentes sistemas operativos que permite crear y exportar a imágenes los diferentes diagramas."
---

{{% post %}}

{{< logotype image1="gaphor.svg" >}}

El trabajo de desarrollo de software en mayor media es un trabajo colaborativo entre un grupo de personas, por ello la comunicación y transmisión de información es fundamental. Por otro lado, los sistemas informáticos de cierto tamaño son complejos de los cuales ninguna persona tiene el conocimiento completo y detallado de todas las partes que lo forman. Esto hace que la documentación y la transferencia de conocimiento sea importante en un grado similar al desarrollo de nuevas funcionalidades.

El problema de la documentación es que se quede obsoleta ni refleje el estado actual del sistema por los continuos cambios realizados en el código, para evitarlo la documentación ha de ser a alto nivel que describan los conceptos fundamentales del sistema que no serán propensos a cambiar. Otra forma de evitar esta obsolescencia en algunos casos es generar la documentación a partir de la información embebida en el código fuente como [la documentación Javadoc de las clases y API de Java][blogbitix-259] o [la documentación de una API REST con Swagger][blogbitix-584]. Sin embargo, no toda la documentación que aporta valor es posible generarla de forma automatizada.

El código fuente de una aplicación contiene el conocimiento más detallado y fiel de una aplicación , sin embargo, tratar de comprender un sistema complejo compuesto por unas cuantas decenas de miles de líneas de código aún usando un lenguaje de alto nivel ya sea Java , Python o C# es una tarea complicada o al menos que requiere mucho tiempo de investigación y esfuerzo de comprensión. Una breve descripción en prosa y un esquema del sistema permite obtener la misma información que analizando el código de forma mucho más rápida.

Si en una organización cuando una persona la abandona deja un vacío importante de conocimiento en las reglas de negocio implementadas o en la incorporación de una nueva persona a un equipo toma demasiado tiempo y dedicación adquirir el mismo conocimiento que el resto de miembros del equipo un problema de la organización quizá sea la falta de documentación. Con el paso del tiempo a medida que una aplicación es más compleja y dado que las personas en una organización se van con el conocimiento que posean y otras vienen pero necesitan adquirir conocimiento, la documentación es una forma de transferencia de conocimiento que está disponible de forma permanente y en forma de autoservicio.

Una buena documentación ha de se concisa y transmitir su información de forma rápida, una forma es utilizar diagramas que esquematizan el sujeto que se trata de describir complementados descripciones en texto. El lenguaje unificado de modelado o UML describe una notación estándar para los diagramas y varios tipos de diagramas.

{{< tableofcontents >}}

### El lenguaje unificado de modelado o UML

[El lenguaje unificado de desarrollo][wikipedia-uml] o UML estandariza las convenciones y elementos utilizados en los diagramas además de identificar y describir varios tipos de diagramas para describir un sistema desde varios puntos de vista. Cada uno de estos diagramas describen un sistema a alto nivel de forma esquematizada los conceptos fundamentales.

Los diagramas UML son de dos tipos estructurales que describen la parte estática del sistema y de comportamiento que describen la parte dinámica. 

Diagramas UML estructurales:

* [Diagrama de clases](https://es.wikipedia.org/wiki/Diagrama_de_clases): en un lenguaje de programación orientado a objetos muestra las clases e interfaces por las que está compuesto el sistema y sus relaciones de herencia, composición e implementación con diferentes tipos de flechas. También muestra los atributos y métodos que poseen las clases.
* [Diagrama de paquetes](https://es.wikipedia.org/wiki/Diagrama_de_paquetes): los paquetes son agrupaciones lógicas de varias clases, en este diagrama se muestran los paquetes fundamentales asi como su relación y dependencia con otros.
* [Diagrama de objetos](https://es.wikipedia.org/wiki/Diagrama_de_objetos): utilizan la misma notación que los diagramas de clases, se diferencian de los diagramas de clases en que muestran las instancias en un momento determinado del sistema.
* [Diagrama de componentes](https://es.wikipedia.org/wiki/Diagrama_de_componentes): muestran los componentes fundamentales en los que se divide un sistema complejo y sus relaciones.
* [Diagrama de estructura compuesta](https://es.wikipedia.org/wiki/Diagrama_de_estructura_compuesta): muestra la estructura interna de una clase.
* [Diagrama de despliegue](https://es.wikipedia.org/wiki/Diagrama_de_despliegue): muestra en que elementos de computación se despliega el software asi como sus relaciones. Son especialmente útiles en los sistemas distribuidos o basados en microservicios.
  
Diagramas UML de comportamiento:

* [Diagrama de flujo](https://es.wikipedia.org/wiki/Diagrama_de_flujo) o actividad: muestra la secuencia de acciones y decisiones implementadas en un algoritmo o proceso.
* [Diagrama de secuencia](https://es.wikipedia.org/wiki/Diagrama_de_secuencia): muestran las interacciones de los objetos entre si y el orden en el que se producen esas interacciones. El orden de las acciones se muestran de forma vertical y las interacciones como flechas.
* [Diagrama de caso de uso](https://es.wikipedia.org/wiki/Diagrama_de_casos_de_uso): proporciona una visión general de los actores involucrados en un sistema, las diferentes funciones que usa esos actores y cómo interactúan estas diferentes funciones. Proporciona un descripción global del sistema inicial.
* Diagrama de estado: algunos objetos o el sistema se comportan de forma diferente en función del estado, estos diagramas muestran los diferentes estados de los que se compone y cual es su comportamiento en función del estado y condiciones.
* [Diagrama de comunicación](https://es.wikipedia.org/wiki/Diagrama_de_comunicaci%C3%B3n)
* [Diagrama de interacción](https://es.wikipedia.org/wiki/Diagrama_global_de_interacciones): muestra un proceso al igual que un diagrama de actividad pero empleando una colección de diagramas de secuencia con su misma notación.
* [Diagrama de tiempos](https://es.wikipedia.org/wiki/Diagrama_de_tiempos)

{{< image
    gallery="true"
    image1="image:uml-diagrams.svg" optionsthumb1="650x450" title1="Esquema de diagramas UML" >}}
{{< image
    gallery="true"
    image1="image:uml-sequence-diagram.svg" optionsthumb1="300x250" title1="Diagramas UML"
    image2="image:uml-activity-diagram.svg" optionsthumb2="300x250" title2="Diagramas UML" >}}
{{< image
    gallery="true"
    image1="image:uml-use-case-diagram.svg" optionsthumb1="200x150" title1="Diagramas UML"
    image2="image:uml-class-diagram.svg" optionsthumb2="200x150" title2="Diagramas UML"
    image3="image:uml-components-diagram.png" optionsthumb3="200x150" title3="Diagramas UML"
    caption="Esquema y ejemplos de diagramas UML" >}}

### Aplicación para crear diagramas de UML

Para crear los diagramas de UML es necesario utilizar una aplicación, una orientada y especifica para crear diagramas UML es [Gaphor][gaphor]. Está disponible a través de [aplicación Flatpak](https://flathub.org/apps/details/org.gaphor.Gaphor) como [forma de empaquetar una aplicación para cualquier distribución GNU/Linux][blogbitix-362], para [Windows][windows] y [macOS][macos] además de ser una aplicación de software libre. Gaphor es una aplicación sencilla con un aspecto cuidado que forma parte del [círculo de aplicaciones de GNOME][gnome-apps] y sigue la guía de estilos para las aplicaciones de este escritorio.

{{< image
    gallery="true"
    image1="image:gaphor.png" optionsthumb1="650x450" title1=""
    caption="Aplicación Gaphor para crear diagramas UML" >}}

En el panel izquierdo se encuentran los elementos gráficos con los que crear los diferentes diagramas. Los elementos gráficos están categorizados para crear diagramas de clases, paquetes y componentes, también para diagramas de actividad, secuencia, estado y casos de uso, los otros diagramas se pueden crear con los elementos disponibles.

{{< image
    gallery="true"
    image1="image:gaphor-use-case-diagram.png" optionsthumb1="300x200" title1="Crear diagramas UML con Gaphor"
    image2="image:gaphor-class-diagram.png" optionsthumb2="300x200" title2="Crear diagramas UML con Gaphor"
    image3="image:gaphor-sequence-diagram.png" optionsthumb3="300x200" title3="Crear diagramas UML con Gaphor"
    caption="Crear diagramas UML con Gaphor" >}}

Gaphor permite guardar en un mismo archivo múltiples diagramas que describan los diferentes aspectos del sistema asi como exportar los diagramas a diferentes formatos de archivo como PDF y forma to de imagen como PNG y SVG. Otras de sus funciones son poder personalizar los estilos de los elementos con reglas de CSS. Finalmente, UML es simplemente una de los estándares para crear diagramas, Gaphor soporta otro tipos de estándares como el modelo C4, RAAML y SysML.

{{% /post %}}

