---
pid: 737
type: "post"
title: "Lectura de un documento JSON conociendo y sin conocer que propiedades contiene"
url: "/2026/10/lectura-de-un-documento-json-conociendo-y-sin-conocer-que-propiedades-contiene/"
date: 2026-10-02T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Procesar un JSON cuya estructura no se conoce de antemano es un problema habitual cuando parte de sus propiedades son dinámicos y no se pueden mapear a un objeto en tiempo de compilación. En este artículo veremos cómo descubrir y recorrer esos atributos desconocidos con Jackson, utilizando JsonNode para inspeccionar el tipo y el valor de cada campo. Además, comprobaremos cómo lograr el mismo resultado con la API estándar de Java, para que puedas elegir la opción que mejor encaje en tu proyecto."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Habitualmente al procesar datos en formato JSON se conoce la estructura de datos origen y es posible mapear esos datos a un objeto. En ocasiones el JSON puede tener una parte dinámica como una colleción de atrubitos que se conoce que existirá pero no se conoce que nombres tienen los atributos que se incluyen. Al tener una parte diámica no es posible mapaear completamente los datos a un objeto ya que se desconocen las propiedades y los tipos de esas propiedades.

Tanto la librería Jackson como la API proporcionada por Java ofrecen la posiblidad de descubrir cuales son las propiedades del JSON. 

* [Validar documentos JSON con JSON Schema][blogbitix-610]
* [Convertir un JSON a objetos y objetos a JSON con JSON-B, Gson y Jackson en Java][blogbitix-378]
* [Usar expresiones JSONPath para extraer datos de un JSON en Java][blogbitix-376]
* [Generar, procesar y modificar documentos JSON con JSON-P en Java][blogbitix-374]

{{< tableofcontents >}}

## Leer un JSON de forma dinámica con Jackson

Jackson ofrecen un una clase que representa el nodo raiz de on JSON, con una instancia de este objeto es posible conocer el tipo de nodo, de que propiedades se compone el JSON y los tipos de datos de esas propiedades.

El siguiente ejemplo imprime en la consola algunas de las propiedades en el objeto raiz. Estas propiedades y valores son desonoccidos en tiempo de compilación. En el ejemplo se muestara también el uso de JSON-P para alguno de las propiedades que sean conocidos en tiempo de compilación. 

El archivo de datos y el programa de ejemplo.

{{< code file="data.json" language="json" options="" >}}
{{< code file="Main-1.java" language="java" options="" >}}

La salida en la consola del programa es el siguiente.

{{< code file="System-1.out" language="plain" options="" >}}

### Leer unn JSON a un Map

Otra forma de leer el JSON es mapeandolo a un objeto _Map_.

{{< code file="Main-2.java" language="java" options="" >}}

La salida en la consola del programa es el siguiente.

{{< code file="System-2.out" language="plain" options="" >}}

## Leer un JSON de forma dinámica con la API de Java

El siguiente ejemplo es el equivalente que on Jackson pero utilizando APIs ofrecidas por Java.

{{< code file="Main-3.java" language="java" options="" >}}

La salida en la consola del programa es el siguiente.

{{< code file="System-3.out" language="plain" options="" >}}

Las librerías dependencias del ejemplo.

{{< code file="libs.versions.toml" language="toml" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaJsonRead" command="./gradlew run" %}}

{{% /post %}}
