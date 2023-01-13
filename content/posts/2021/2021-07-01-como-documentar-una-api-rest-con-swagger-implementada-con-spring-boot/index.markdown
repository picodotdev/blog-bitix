---
pid: 584
type: "post"
title: "Cómo documentar una API REST con Swagger implementada con Spring Boot"
url: "/2021/07/como-documentar-una-api-rest-con-swagger-implementada-con-spring-boot/"
date: 2021-07-01T20:00:00+02:00
updated: 2021-07-12T22:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Una API REST no está obligada a publicar una definición de su API, sin embargo, para quien deba usar API es muy útil disponer de su documentación para usarla correctamente y descubrir de qué _endpoints_ se compone, métodos HTTP, cuales son sus parámetros, el esquema de los cuerpos de la petición y de los resultados, los tipos de los datos y sus formatos, los códigos de retorno devueltos, las cabeceras y su autenticación. OpenAPI permite definir la interfaz de una aplicación de forma agnóstica de la tecnología y lenguaje en el que se implementa, por otro lado Swagger a partir de esa definición permite generar una interfaz HTML con su documentación. La librería Springdoc junto con Spring Boot permite generar tanto la especificación de la API como la documentación simplemente añadiendo una dependencia y varias anotaciones en la implementación de la API."
---

{{% post %}}

{{< logotype image1="java.svg" image2="spring.svg" >}}

Disponer de documentación es esencial para el desarrollo, también es el caso de tener que usar una API REST donde es necesario conocer que _endpoints_ dispone la API, métodos HTTP, cuales son sus parámetros, el esquema de los cuerpos de la petición y de los resultados, los tipos de los datos y sus formatos, los códigos de retorno devueltos, las cabeceras y su autenticación.

[GraphQL][graphql] en sus especificaciones detallan además del protocolo define también una forma de exportar un esquema de la API y publicarlo junto con la misma que sirve como documentación. Una API REST que está basada más en convenciones y semántica del protocolo HTTP que en una especificación nada le obliga a proporcionar una especificación de la API. Aunque una API implemente HATEOAS e intente ser más autoexplicativa la documentación sigue siendo útil para explorar la API sin necesidad de realizar las peticiones.

* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]

No tener una especificación de la API es un inconveniente porque un cambio en la interfaz de la API puede provocar errores de compatibilidad, no tener su documentación para revisar la API dificulta su uso al implementar un cliente. No tener documentación es un inconveniente pero tener documentación no generada a partir del código fuente o de la especificación de la API también lo es porque la documentación corre el riesgo de no estar actualizada y sincronizada con la implementación en el código fuente. Además de quedar la documentación desactualizada respecto al código fuente requiere tiempo de mantenimiento que no se dedica a otras tareas.

Hay iniciativas y herramientas para suplir la carencia de las API REST de no obligar a proporcionar una especificación de la API REST y generar la documentación documentación a partir del código fuente. También es importante poder probar la API de forma sencilla, una de las formas más habituales de probar una API es que la documentación incluya el comando de la herramienta de línea de comandos _curl_ por su sencillez ni requerimientos adicionales que tener el comando instalado en sistema para ejecutarlo.

{{< tableofcontents >}}

### Documentación de un API con OpenAPI, Swagger y Springdoc

[OpenAPI][openapi] trata de proporcionar una especificación para definir esquemas de APIs agnósticas de la tecnología y la implementación de las APIs. Definida la interfaz de la API es posible crear un cliente o servidor que cumpla esa API. La definición de la API incluye sus _endpoints_, métodos HTTP, cuales son sus parámetros, el esquema de los cuerpos de la petición y de los resultados, los tipos de los datos y sus formatos, los códigos de retorno devueltos, las cabeceras y su autenticación.

Por otro lado las herramientas de [Swagger][swagger] permiten generar la documentación a partir de la especificación de la API y si se desea generar una implementación básica inicial de cliente y servidor para diferentes lenguajes de programación. La documentación de Swagger no solo incluye información sino que permite probar la API directamente desde la documentación u obtener el comando _curl_ a ejecutar desde la línea de comandos.

En una aplicación que implementa una API REST con [Spring Boot][spring-boot] la librería [Springdoc][springdoc] permite generar de forma automática la especificación de la API que implementa el código publicándose en un _endpoint_, esta librería también genera la documentación de Swagger de la API en otro _endpoint_.

Otra forma de obtener la especificación de la API es mediante el [_plugin_ para Gradle de springdoc](https://github.com/springdoc/springdoc-openapi-gradle-plugin) o utilizar [imagen de Docker de Swagger UI](https://hub.docker.com/r/swaggerapi/swagger-ui) para crear un servidor que aloje la documentación. También es posible [descargar la última versión de Swagger UI](https://github.com/swagger-api/swagger-ui/releases/latest) en el [directorio dist](https://github.com/swagger-api/swagger-ui/tree/master/dist), cambiar el archivo _index.html_ y reemplazar la URL _https:\/\/petstore.swagger.io/v2/swagger.json_ por la especificación de OpenAPI deseada.

{{< code file="gradlew-generateOpenApiDocs.sh" language="bash" options="" >}}

El documento en formato JSON incluye de la definición de la API, es un documento con el fin de ser utilizado por alguna herramienta como Swagger UI que en su caso genera la documentación en formato HTML.

{{< code file="api-docs.json" language="json" options="" >}}

La documentación en formato HTML de Swagger tiene el siguiente aspecto con la que además de obtener información sobre la API es posible ejecutar sus operaciones y obtener el comando _curl_ para ejecutarlo desde la linea de comandos.

{{< image
    gallery="true"
    image1="image:swagger-ui-1.webp" optionsthumb1="650x450" title1="Documentación de Swagger UI de una API REST" >}}
{{< image
    gallery="true"
    image1="image:swagger-ui-2.webp" optionsthumb1="300x250" title1="Documentación de Swagger UI de una API REST"
    image2="image:swagger-ui-3.webp" optionsthumb2="300x250" title2="Documentación de Swagger UI de una API REST"
    caption="Documentación de Swagger UI de una API REST" >}}

### Ejemplo de documentación REST con Spring Boot y Swagger

El siguiente ejemplo de Spring Boot implementa una pequeña API REST con un _endpoint_ y varios métodos HTTP, uno para obtener un mensaje, otro para añadir un mensaje y otro para eliminar un mensaje. La API se define en un interfaz con las anotaciones tanto de Spring para REST como las anotaciones de Swagger para la definición de la API y documentación que al iniciar la aplicación permite generar la definición en formato OpenAPI.

{{< code file="MessageApi.java" language="java" options="" >}}

La implementación de la API simplemente guarda en un mapa los mensajes, en caso de que detecte una condición de error lanza una excepción con el código de estado definido en la API para la condición, en caso de que la operación sea correcta se ejecuta su funcionalidad y se devuelve el código de estado 200 y los datos solicitados en su caso.

{{< code file="MessageController.java" language="java" options="" >}}

Con los siguientes comandos de _curl_ es posible probar los diferentes métodos de la API.

{{< code file="curl-get-all.sh" language="bash" options="" >}}
{{< code file="curl-get.sh" language="bash" options="" >}}
{{< code file="curl-put.sh" language="bash" options="" >}}
{{< code file="curl-put-conflict.sh" language="bash" options="" >}}
{{< code file="curl-delete.sh" language="bash" options="" >}}

Este ejemplo es suficiente, pero no cumple con todos [los niveles de madurez de REST][blogbitix-587], el ejemplo de este otro artículo se puede comparar con el de este para ver las diferencias y conocer las ventajas e inconvenientes de implementar HATEOAS y HAL en una API REST.

Con la aplicación iniciada en en la URL _http:\/\/localhost:8080/v3/api-docs_ por defecto se exporta especificación de la API en formato OpenAPI, en la URL _http:\/\/localhost:8080/swagger-ui.html_ por defecto está la documentación de la API de Swagger generada por Springdoc. Con solo añadir las dependencias de Springdoc a la herramienta de construcción, en este caso [Gradle][gradle], Spring Boot hace disponibles ambos _endpoints_.

{{< code file="build.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringRestSwagger" command="./gradlew run" %}}

{{% /post %}}
