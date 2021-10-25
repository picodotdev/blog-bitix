---
pid: 279
type: "post"
title: "Devolver mensajes de error descriptivos en GraphQL"
url: "/2017/11/devolver-mensajes-de-error-descriptivos-en-graphql/"
date: 2017-11-12T11:30:00+01:00
updated: 2020-08-22T21:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:graphql.svg"
tags: ["java", "planeta-codigo", "programacion"]
series: ["graphql"]
---

{{% post %}}

{{< logotype image1="graphql.svg" >}}

Por defecto [GraphQL][graphql] devuelve errores con mensajes descriptivos para los errores del cliente como son los errores de sintaxis en la sentencia de consulta o mutación, en el caso de que el campo solicitado no exista o no se ha indicado ninguno. En el caso de Java si se lanza una excepción en la clase repositorio que guarda los datos o en la lógica de negocio y no se captura GraphQL indicará que se ha producido un error interno en el servidor. Esto no es muy descriptivo y es mejor indicar errores más útiles para el usuario de la API como podría ser que no se tienen permisos para realizar la modificación o el error que se ha producido al validar los datos y por los que la operación no se ha completado.

En esta consulta de mutación que añade un nuevo libreo a la librería se puede producir dos excepciones, uno en el caso de que el usuario que lanza la consulta no tenga permisos y otro en el caso de que el autor no exista.

{{< code file="Library.java" language="java" options="" >}}
{{< code file="Mutation.java" language="java" options="" >}}

En el caso de no personalizar los mensajes de error se devuelve un error genérico de error interno del servidor nada descriptivo para el usuario de que cual es el motivo del error devuelvo como respuesta, la respuesta debería indicar el autor no existe y el usuario no tiene permisos.

{{< code file="curl-generic-errors.sh" language="bash" options="" >}}

Para algunos tipos de error como una consulta cuya sintaxis no es correcta o se hace referencia a campos que no existen se devuelven errores más descriptivos.

{{< code file="curl-default-errors.sh" language="bash" options="" >}}

Los errores en GraphQL usando el lenguaje Java se gestionan haciendo uso de la clase la interfaz [GraphQLError](https://github.com/graphql-java/graphql-java/blob/master/src/main/java/graphql/GraphQLError.java), que contiene los datos que se devuelven como respuesta como el mensaje de error, el tipo de error, la ubicación en el código fuente donde se ha producido además de otros datos personalizados adicionales que se quieran incluir.

Para adaptar las clases excepción que se lanzan desde el servicio de persistencia a las clases GraphQLError que utiliza GraphQL hay que utilizar métodos con la anotación @ExceptionHandler que básicamente transforman una [RuntimeException](javadoc11:java.base/java/lang/RuntimeException.html) a un [GraphQLError](https://javadoc.io/doc/com.graphql-java/graphql-java/latest/graphql/GraphQLError.html).

{{< code file="ExceptionHandlers.java" language="java" options="" >}}

En el caso de este ejemplo solo un usuario de nombre _admin_ tiene permitido hacer modificaciones en la colección de libros guardados en la clase repositorio _LibraryRepository_. Por otro lado, cuando se añade un libro se hace una validación de los datos comprobando que el autor del libro a añadir exista en la librería. Estas son las peticiones válidas.

{{< code file="curl.sh" language="bash" options="" >}}

Y estas las inválidas que devuelven los mensajes propios más descriptivos de los errores o validaciones realizadas en el servidor de más utilidad para un usuario del servicio.

{{< code file="curl-custom-errors.sh" language="bash" options="" >}}

La interfaz _GraphQLError_ posee el método _getMessage()_ para devolver la descripción del mensaje pero con el método _getExtensions()_ es posible incluir cualquier dato en forma de clave-valor que deseemos como un código de error o cualquier otra información deseada. El caso de la excepción _PermissionException_ devuelve dos datos adicionales _foo_ y _fizz_, en un caso real se implementaría una lógica más útil para devolver estos datos adicionales posiblemente proporcionándolos en el constructor u obteniéndolos con la referencia a algún objeto, podría ser incluso el _stacktrace_ completo de la excepción.

{{< code file="PermissionException.java" language="java" options="" >}}
{{< code file="ValidationException.java" language="java" options="" >}}

{{< sourcecode git="blog-ejemplos/tree/master/GraphQL" command="./gradlew run" >}}

{{< reference >}}
* [Execution](https://graphql-java.readthedocs.io/en/v5/execution.html)
{{< /reference >}}

{{% /post %}}
