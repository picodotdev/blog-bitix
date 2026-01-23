---
pid: 722
type: "post"
title: "Crear diagramas gráficos con el formato de texto Mermaid"
url: "/2026/01/crear-diagramas-graficos-con-el-formato-de-texto-mermaid/"
date: 2026-01-17T00:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:diagrama-de-estado.webp"
imagePost: "image:diagrama-de-estado.webp"
tags: ["planeta-codigo", "programacion"]
summary: "La documentación es imprescindible en la programación o al menos de enorme ayuda que facilita comprender el elemento documentado. Sin embargo, ela documentación es uno de los aspecto que no siempre se le da la relevancia que se le da a otros aspectos. En la documentación ténica se incluyen diagramas que son una representación gŕafica del objeto documentado. Hay diagramas de diferentes tipos que representa un aspecto del objeto documentado como un diagramas de secuencia, de estado, de clases, entidad releación o de arquitectura. Habitualmente estos diagramas se crean con una herramienta gráfica y se exportan como una imagen, la dificultad está en la actualización de esa imagen que es mucho más compleja. Mermeaid es un formato de texto que permite crear diagramas con un formato de texto."
---

{{% post %}}

La documentación al igual que los teses unitarios o formatear bien el código siguiendo las convenciones definidas para mi es parte intrínseca de una tarea, no hace falta indicarlo explícitamente de una tarea.

Al empezar en un área desconocida habitualmente lo primero que se pide a los demás casi como si fuera un derecho, también habitualmente ya hacerla para los demás ya tal.

Hay varias formas de documentación, comentarios de código, archivo _README_ de un repositorio, una página de gestión de conocimiento colaborativo como una wiki. Varias son en formato de texto y prosa, otras una representación gráfica de los conceptos.

Al explicar conceptos complejos un gráfico permite transmitirlos de forma más fácil en la que queden representados los conceptos relevantes.

Habitualmente se ha utilizado una imagen generada por alguna aplicación que permite componer el esquema con rectángulos, etiquetas y flechas. Una imagen es más difícil de actualizar en un futuro, cuanto más difícil y tiempo lleve actualizar la documentación más fácilmente es que no se actualice por pereza.

Es por esta facilidad de actualización edición en la que los formatos de texto como Markdown para documentos se han expandido, tiene unas reglas simples para dar el formato, son simples de editar y no se necesita ninguna aplicación específica solo se necesita un editor de texto.

## El Markdown para gráficos orientados a programación

¿No sería genial que por sus ventajas hubiera un formato de texto para representar gráficos orientados al área de tecnología y programación? Pues estamos de suerte porque si lo hay, y varios, dos de ellos son [PlantUML][plantuml] y [Mermaid][mermaid], cualquiera de ellos puede considerarse el Markdown para gráficos como diagramas de secuencia, diagramas de estado, diagramas de clases, diagramas entidad relación, diagramas de arquitectura, ... entre muchos otros. Mermaid posee un [editor gráfico online](https://mermaid.live/edit) que va visualizando el resultado a medida que se edita el texto del diagrama. 

Habitualmente creando un gráfico empleamos más tiempo alineando los elementos, manipulando los elementos del gráfico y exportando el contenido a una imagen que creando el contenido del mismo. En las herramientas como Mermaid es al reves el tiempo que se emplea es únicamente para crear el contenido del gráfico, el tiempo nenecesario para conocer la sintaxis que es bastante simple y hay ejemplo de cada una de las opciones. Además algunas herramientas gráficas no son especificas para la creación de diagramas orientados a la programación.

Mermaid se encarga de alinear y establecer la disposición de los elementos automáticamente para que se visualicen correctamente. Al crear digramas con un formato de texto obtenemos el mismo beneficio que el código fuente al incorporarlo a un sistema de control de versiones como [Git][git]. Ver claramente las diferencias entre las diferentes versiones y menos espacio ocupado en el repositorio. 

Una herramienta gráfica como alternativa es [Gaphor][gaphor].

* [Los diagramas UML para documentar y una aplicación para crearlos][blogbitix-594]

En la documentación de Mermaid hay ejemplos del formato de texto a seguir para cada uno de los diagramas y de las diferentes opciones que permite. Además tiene un editor que a medida que se escribe lo representa de forma gráfica.

El Markdown de GitHub soporta Mermaid que a partir del texto lo representa de forma gráfica como en los archivos _README_.

### Ejemplos

Estos son un diagrama de estado, un diagrama de clases, un diagrama de secuencia, un diagrama entidad relación y un diagrama de arquitectura.

#### Diagrama de flujo

{{< code file="diagrama-de-flujo.mermaid" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:diagrama-de-flujo.webp" optionsthumb1="650x450" title1="Diagrama de flujo"
    caption="Diagrama de flujo" >}}

#### Diagrama de secuencia

{{< code file="diagrama-de-secuencia.mermaid" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:diagrama-de-secuencia.webp" optionsthumb1="650x450" title1="Diagrama de secuencia"
    caption="Diagrama de secuencia" >}}

#### Diagrama de estado

{{< code file="diagrama-de-estado.mermaid" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:diagrama-de-estado.webp" optionsthumb1="650x450" title1="Diagrama de estado"
    caption="Diagrama de estado" >}}

#### Diagrama entidad relación

{{< code file="diagrama-entidad-relacion.mermaid" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:diagrama-entidad-relacion.webp" optionsthumb1="650x450" title1="Diagrama entidad relación"
    caption="Diagrama entidad relación" >}}

{{% /post %}}
