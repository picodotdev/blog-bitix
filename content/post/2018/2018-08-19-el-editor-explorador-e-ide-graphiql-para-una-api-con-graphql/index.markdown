---
pid: 340
title: "El editor, explorador e IDE GraphiQL para una API con GraphQL"
url: "/2018/08/el-editor-explorador-e-ide-graphiql-para-una-api-con-graphql/"
date: 2018-08-19T11:00:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
series: ["graphql"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="graphql.svg" title1="GraphQL" width1="200" >}}

Hace ya un tiempo escribí una [serie de artículos sobre GraphQL][blogbitix-serie-graphql] para desarrollar una API en una aplicación como alternativa a desarrollarla basada en REST. En esa serie de artículos explicaba varios conceptos básicos e introductorios pero suficientes para desarrollar una API completamente funcional con [GraphQL][graphql] usando el lenguaje de programación Java. Sin embargo, sobre algunos otros conceptos y posibilidades de GraphQL no había escrito.

Uno de esas posibilidades de GraphQL es la herramienta [GraphiQL][graphiql] que es un editor interactivo para construir consultas y explorar la API. Una de sus mayores ventajas es que ofrece asistencia contextual y proporciona mensajes de error en caso de que la sintaxis de la consulta sea errónea. En los artículos que escribí con anterioridad los ejemplos los hice usando el comando _curl_. Ahora que he visto y aprendido algunas pocas cosas más de GraphQL en este artículo explico como usar la herramienta GraphiQL como alternativa a _curl_.

GraphiQL en casi un IDE para crear consultas de GraphQL, está basado en JavaScript, se ejecutan en el navegador y para su funcionamiento solo hay que proporcionarle el _endpoint_ de la API a probar.

En la página de este tutorial se explica cual es el [código HTML necesario para el editor GraphiQL](https://www.howtographql.com/graphql-java/2-queries/). Básicamente es un HTML que hay colocar en el caso de una aplicación Java en el directorio web de la aplicación, utilizando la página por defecto _index.html_ se carga al acceder a la dirección _http\://localhost:8080/_.

En el código del archivo _index.html_ del ejemplo he tenido que cambiar estas dos cosas, una de ellas es utilizar un CDN para los recursos de GraphiQL y la otra en la función _graphQLFetcher_ es que en este ejemplo el _endpoint_ es _/library_ en vez de _/graphql_.

{{< code file="index-1.html" language="HTML" options="" >}}
{{< code file="index-2.html" language="HTML" options="" >}}

Varias de las mismas consultas que hacía con _curl_ en otros artículos es posible lanzarlas con GraphiQL. Es posible formatear correctamente una consulta con el botón _prettify_. Con en el enlace de documentación se puede explorar los tipos definidos en la API. Con funciones similares a un IDE ofrece errores de sintaxis, asistencia contextual e historial de consultas, en definitiva es una gran mejora sobre _curl_ y su incorporación a la aplicación es muy sencilla.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="graphiql-1.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Consulta"
        image2="graphiql-2.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Asistencia contextual"
        image3="graphiql-3.png" command3="Fit" commandthumb3="Fit" options3="2560x1440" optionsthumb3="300x200" title3="Explorador del esquema"
        caption="El editor GraphiQL para una API con GraphQL" >}}
</div>

{{< sourcecode git="blog-ejemplos/tree/master/GraphQL" command="./gradlew run" note="Requiere Java 9+ o Docker" >}}

{{% /post %}}
