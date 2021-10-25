---
pid: 547
type: "post"
title: "Documentación para registrar las decisiones de arquitectura en software e infraestructura"
url: "/2021/01/documentacion-para-registrar-las-decisiones-de-arquitectura-en-software-e-infraestructura/"
date: 2021-01-16T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:adr.png"
tags: ["planeta-codigo", "programacion"]
summary: "Las tareas de un programador no consisten únicamente en escribir líneas de código o de un arquitecto decidir que usar o no usar y como. Una tarea importante de un programador o un arquitecto debe ser también escribir o mantener documentación actualizada. La arquitectura empleada en una aplicación permite conocer cuáles son sus componentes y piezas específicas de las que se compone sin tener que analizar el código fuente. La arquitectura de una aplicación puede ser de muchas formas, las decisiones de arquitectura también son un aspecto susceptible de ser documentadas, que permitan conocer cuáles fueron las motivaciones para elegir entre unas opciones y otras y que cambios de arquitectura ha sufrido una aplicación y por que motivos."
---

{{% post %}}

Al desarrollar una aplicación desde cero se han de tomar numerosa decisiones relativas a la arquitectura de la aplicación. Desde que lenguaje de programación a usar, que tecnología cómo que base de datos, si va a utilizar mensajes, el conjunto de librerías, si va a estar implementada con DDD o arquitectura hexagonal, que funcionalidades se implementan y como, como se va a hospedar y con que infraestructura entre muchas otras decisiones. La documentación es importante para conservar y transmitir el conocimiento sin tener que analizar el código fuente de la aplicación, ya sea de los componentes de arquitectura y cómo interactúan entre ellos o cuales son los requerimientos de un determinado proyecto para iniciarlo.

La arquitectura de una aplicación y los componentes de los que está formada una aplicación suelen documentarse con un esquema gráfico. Pero el esquema gráfico muestra únicamente la situación actual de la arquitectura y no es suficiente para conocer las motivaciones y requerimientos que han intervenido para optar por esa arquitectura y no otra, o los cambios que se han realizado en la arquitectura con el paso del tiempo.

Siempre se quiere tener documentación actualizada con la información que se necesita, a nadie le apetece extraer información a partir de código fuente ya que esto requiere mucho tiempo e impreciso. Querer documentación no es lo mismo que querer escribirla, pero normalmente es de gran ayuda para otros o para uno mismo en el futuro. Y no solo es escribirla sino también mantenerla actualizada.

### Documentación _Architecture Decision Records_

La documentación de decisiones de arquitectura o _Architecture Decision Records_ (ADR) es una colección de documentos que recogen individualmente cada una de las decisiones de arquitectura tomadas. Los ADR pueden ser simplemente un documento en Google Docs, una Wiki o una colección de documentos de texto en formato _markdown_. Como toda documentación esta requiere mantenimiento para que esté completa y no quede desfasada con el paso del tiempo, de modo que ha de sar fácil de mantener, accesible para su edición y tener visibilidad en la organización para encontrarla fácilmente.

Estas son siglas relacionadas con la documentación ADR:

* AD: _architecture decision_, es una decisión de arquitectura tomada
* ADR: _architecture decisión record_, es el registro con información relacionada de una AD.
* ASR: _architecturally-significant requirement_, es un requerimiento destacado que influye en un AD.
* ADL: _architecture decision log_, son el conjunto de los ADR.
* AKM: _architecture knowledge management_

En los documentos ADR tienen las siguientes propiedades:

* Fecha: cuando el AD ha sido tomada.
* Razones: explicar las razones para tomar una determinada AD incluyendo los ASR.
* Específico: cada ADR debe tratar un AD específico.
* Inmutabilidad: los AD deben ser inmutables, no se ven afectados por decisiones futuras.

Cada ADR además puede recoger información de contexto, ya sea por la situación de una organización, prioridades de negocio o características o habilidades de los equipos. También pueden recoger otras informaciones como consecuencias de una AD, ya que las decisiones de arquitectura suele tomarse antes de implementarlas que riesgos tiene y que asunciones se están haciendo, ante esos riesgos que mitigaciones son posibles en caso de tener que optar por un cambio, que otras decisiones de arquitectura candidatas se han evaluado y por que se han descartado, o un análisis de arquitecturas por las que se han optado en otras organizaciones ante problemas similares.

Estos son algunos apartados de un ADR:

* Fecha
* Contexto
* Decisión
* Consecuencias
* Riesgos
* Mitigaciones
* Candidatos evaluados
* Asunciones
* Restricciones
* Argumentos

Un buen ejemplo de _Architecture Decision Records_ son los documentos de [especificaciones JEP](https://openjdk.java.net/jeps/0) de Java que incluyen un resumen, objetivos, motivaciones y una descripción detallada de la proposición.

### Decisiones de arquitectura tomadas en mi blog

Utilizando como ejemplo mi blog a lo largo de los años he tenido que tomar varias decisiones que podrían ser de arquitectura. Entre Blogger, el generador estático de sitios web con Hugo y quizá en un futuro con AWS las cosas han cambiado mucho en mi blog, sin embargo, no he recogido en ningún documento específico cuáles son las decisiones en cada uno de esos cambios y los motivos.

El siguiente no sigue ninguna plantilla en concreto en los enlaces de referencia se incluyen algunas de ejemplo. Cualquier cosa relevante en la arquitectura es candidato a ser incluido en el ADR.

{{< code file="adr.xmarkdown" language="markdown" options="" >}}

{{< reference >}}
* [Architecture decision record (ADR)](https://github.com/joelparkerhenderson/architecture_decision_record#sources)
* [Implementing a workflow for your Architecture Decisions Records](https://asiermarques.medium.com/implementing-a-workflow-for-your-architecture-decisions-records-ab5b55ee2a9d)
* [ADR Tools](https://github.com/npryce/adr-tools)
* [Markdown Architectural Decision ](https://adr.github.io/madr/)
{{< /reference >}}

{{% /post %}}
