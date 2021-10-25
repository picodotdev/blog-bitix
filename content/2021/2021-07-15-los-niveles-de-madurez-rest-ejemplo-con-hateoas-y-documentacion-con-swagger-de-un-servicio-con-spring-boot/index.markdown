---
pid: 587
type: "post"
title: "Los niveles de madurez REST, ejemplo con HATEOAS y documentación con Swagger de un servicio con Spring Boot"
url: "/2021/07/los-niveles-de-madurez-rest-ejemplo-con-hateoas-y-documentacion-con-swagger-de-un-servicio-con-spring-boot/"
date: 2021-07-15T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Los niveles de madurez de una API implementada con las convenciones REST trata de aplicar los conceptos y semántica de la web y el protocolo HTTP a un servicio web. Muchas APIs que dicen ser REST no cumplen con todos los niveles de madurez para ser considerada RESTful que incluyen HATEOAS para crear enlaces entre los recursos y HAL para codificar los datos. Muchas se quedan en el nivel 2 al hacer uso únicamente de recursos y verbos, llegar a cumplir el nivel 3 para incluir controles _hypermedia_ tiene algunas ventajas adicionales. Spring Boot proporciona soporte para crear una API que soporte el nivel de madurez 3 de REST y Springdoc permite generar la documentación de la API con Swagger."
---

{{% post %}}

{{< logotype image1="java.svg" image2="spring.svg" >}}
 
Utilizar REST para implementar un servicio es muy común por ser fácil de construir y consumir, se ha convertido en un estándar para los servicios web. REST proporciona acciones para las operaciones, cacheo, redirecciones y delegación, seguridad tanto para cifrado como para autenticación, compatibilidad hacia atrás y evolución de las APIs, escalabilidad y servicios sin estado. A pesar de su amplio uso en realidad no define ninguna especificación, es simplemente una aproximación, estilo y restricciones para construir servicios escalables basados en la web.

Cómo alternativa a los servicios REST están gRPC y GraphQL que también son capaces de utilizar como medio de transporte el protocolo HTTP pero se basan en aproximaciones diferentes.

* [Qué es GraphQL y ejemplo para una interfaz de un servicio con Spring Boot y Java][blogbitix-275]
* [Introducción a gRPC y ejemplo con Java][blogbitix-512]

Utilizar el protocolo HTTP no es suficiente para que en servicio se considere que implementa REST de forma completa, al implementar un servicio basado en la semántica del protocolo HTTP y la web hay varios niveles de madurez. [Spring Boot][spring-boot] ofrece soporte para implementar servicios web que cumplan con todos los niveles de madurez de REST y [Springdoc][springdoc] crear la documentación a partir de las anotaciones de [Swagger][swagger].

{{< tableofcontents >}}

### Los niveles de madurez REST

REST se basa en los mismos estándares que se utilizan para las páginas web, estos son el protocolo HTTP y los hiperenlaces que construyen la web. El protocolo HTTP tiene una semántica para cada una de sus operaciones que incluyen las diferentes operaciones básicas de CRUD (crear, leer, actualizar y eliminar), códigos de estado para el resultado de la operación y direcciones de los recursos. Las páginas web devuelven HTML, los servicios REST como formato de datos suelen emplear JSON. Los servicios REST son la aplicación de los mismos conceptos de la web a integración de servicios para computadoras, en vez de a humanos o navegadores web.

Los niveles de madurez de REST son la aplicación de la semántica del protocolo HTTP y la web a los servicios web. Cada uno de estos niveles incluye una aplicación del protocolo HTTP y la web que el servicio REST debe seguir.

Muchos servicios que se denominan REST no cumplen con todos los niveles de madurez de REST, no es suficiente utilizar HTTP como transporte, utilizar URLs bonitas para los recursos y usar verbos HTTP. No son pocos los servicios que se denominan como REST pero que no implementan todos los niveles de madurez.

#### Nivel 0, transporte HTTP

En este nivel simplemente se usa HTTP como medio de transporte para hacer llamadas remotas sin usar la semántica de la web. Cada petición tiene su propia dirección de _endpoint_, estas URLs puede que sigan algunas convenciones como utilizar guiones medios para mejorar legibilidad de las URLs, preferiblemente letras en minúsculas y sin extensiones en las URLs, un _endpoint_ puede devolver los datos en el formato solicitado según la cabecera _Accept_ de modo que la extensión es redundante o no es necesaria.

En este nivel de madurez las URLs suelen incluir verbos que es una mala práctica, como en los siguientes ejemplos.

{{< code file="rest-0.txt" language="plaintext" options="" >}}

#### Nivel 1, recursos

Los recursos son una parte fundamental del protocolo HTTP, cada recurso tiene su propia dirección web, _endpoint_ o URL. Normalmente en una aplicación los modelos corresponden con su propio recurso junto a su propio  _endpoint_ o URL.

En este nivel se aplican varias convenciones como las URLs no incluyen una _/_ al final de la dirección, una _/_ representa una relación jerárquica entre diferentes recursos, es posible usar singular o plural para los nombres según se prefiera pero de forma consistente.

Los _endpoints_ en este nivel de madurez son de la siguiente forma.

{{< code file="rest-1.txt" language="plaintext" options="" >}}

#### Nivel 2, verbos

Las operaciones que se realizan sobre los recursos son las operaciones de creación, obtención, actualización y eliminación o CRUD. Usando los diferentes verbos del protocolo HTTP es posible asignar a cada uno de ellos las diferentes operaciones básicas de manipulación de datos.

Si se quiere obtener un elemento concreto de un recurso se realiza una petición al recurso con el verbo _GET_ indicando el identificativo del modelo, si se quieren obtener todos los elementos del recurso se realiza una petición con el verbo _GET_ sin especificar ningún identificativo, si se quiere crear un nuevo elemento en el recurso se utilizar el verbo _POST_, si se quiere modificar el verbo _PUT_ y si se quiere eliminar el verbo _DELETE_.

* _POST_: verbo utiliza para realizar operaciones de creación sobre un recurso.
* _GET_: verbo utiliza para obtener un elemento de la colección o varios elementos de la colección.
* _PUT_: verbo utilizado para realizar operaciones de modificación.
* _DELETE_: verbo utilizado para realizar operaciones de eliminación.

Las cabeceras que son parte del protocolo HTTP son metadatos utilizados con diferentes propósitos como indicar en qué formato se quieren los datos en la respuesta o añadir seguridad.

Los parámetros de las URLs son otra parte del protocolo HTTP que permiten proporcionar argumentos y datos en la propia URL después del símbolo _?_ en vez de como datos en el cuerpo de la petición. Los parámetros de las consultas son utilizados con diferentes propósitos como especificar los criterios de una búsqueda o propiedades de los datos que se desean como paginación, filtrado u ordenación.

Otra parte del protocolo HTTP con los códigos de estado, los códigos de estado HTTP son un número que indica el resultado de la operación. Estos son varios de los códigos de estado más comunes:

* 200: la operación se ha procesado correctamente.
* 201, _CREATED_: un nuevo recurso ha sido creado.
* 204, _NO CONTENT_: el recurso ha sido eliminado, no se devuelven datos en el cuerpo de la respuesta.
* 304, _NOT MODIFIED_: los datos retornados no han cambiado y provienen de una caché.
* 400, _BAD REQUEST_: la respuesta es inválida y no puede ser procesada. La descripción del mensaje de error puede ser devuelta en lo datos retornados.
* 401, _UNAUTHORIZED_: acceder o manipular el recurso requiere autenticación.
* 403, _FORBIDDEN_: el servidor entiende la petición pero las credenciales proporcionadas no permiten el acceso.
* 404, _NOT FOUND_: el recurso de la URL no existe.
* 500, _INTERNAL SERVER ERROR_: se ha producido un error interno al procesar la petición por un fallo de programación. En la respuesta no se siempre se devuelve una descripción del error, sin embargo en las trazas del servidor debería haber información detallada del error.

Tanto para enviar datos como obtener datos el formato utilizado es JSON por ser un formato de texto plano y manipulable desde JavaScript en un navegador web.

Aunque hasta este nivel puede ser suficiente para implementar un servicio y proporcionar la funcionalidad, no es suficiente para considerarlo RESTful, es necesario el siguiente nivel de madurez con los controles _hypermedia_.

#### Nivel 3, controles _hypermedia_

Este nivel se divide en dos aspectos, negociación de contenido y descubrimiento de enlaces del recurso. Este es el nivel al que muchas implementaciones de servicios REST no implementan por mayor sencillez aún sin las ventajas que proporcionan los controles _hypermedia_ o por los problemas de los controles _hypermedia_ que si son ignorados ni utilizados no proporcionan ninguna de sus ventajas.

La negociación del contenido permite al cliente especificar el formato de los datos en los que quiere el resultado. Se solicita con la cabecera _Accept_ en la petición. Por ejemplo, un cliente del servicio REST que desee los datos en formato JSON debe proporcionar una cabecera _Accept: application/json_ y si los desea en formato XML una cabecera _Accept: application/xml_. En caso de enviar datos en el cuerpo de la petición el formato de los datos proporcionados se especifica con la cabecera _Content-Type_. En caso de que el servicio no soporte el tipo de datos proporcionado o no sea capaz de proporcionar en el formato solicitado devuelve el código de estado 415 que indica formato de tipo de datos no soportado.

La web es una colección de páginas web relacionadas a través de enlaces. HATEOAS es el principio que aplica enlaces en los datos de las entidades que permite navegar entre ellas y descubrir las acciones disponibles, un cliente de un servicio REST que implemente HATEOAS no necesita conocer las URLs para interaccionar con las diferentes acciones, estas son devueltas en los datos de la respuesta como metadatos.

Para obtener los enlaces que ofrece el recurso es necesario hacer una petición y obtener datos, esto es un problema ya que si el cliente ha de conocer de antemano los enlaces o hacer una petición para obtenerlos se anulan parte de las ventajas de HATEOAS, el cliente ha de _harcodearlos_ en su código. Esta acción _index_ permite obtener todos los enlaces que se ofrece en el recurso que el cliente puede utilizar.

{{< code file="curl-get-index.sh" language="bash" options="" >}}
{{< code file="curl-get-index.json" language="json" options="" >}}

Al realizar la siguiente llamada al servicio del ejemplo cuando se devuelve una entidad _Message_ el JSON de sus datos incluye una propiedad _\_links_ con los enlaces de sus acciones, en este caso realizar la operación de eliminar. La propiedad _links_ es un array de enlaces que tienen la URL y un nombre o identificativo asociado.

{{< code file="curl-get.sh" language="bash" options="" >}}
{{< code file="curl-get.json" language="json" options="" >}}

Con HATEOAS en vez de que los clientes construyen las URLs de los recursos para hacer peticiones las obtienen de los datos de la respuesta, al mismo tiempo en la respuesta se especifica las acciones posibles de modo que el cliente no necesita implementar lógica para determinar si una operación es posible. La aplicación tampoco necesita construir URLs con interpolación de cadenas para incluir el identificativo de una entidad, el enlace completo es devuelto en los datos. Esto permite a los clientes no tener que implementarlo reduciendo el riesgo de que la lógica de operaciones posibles del servidor y el cliente quede desincronizadas.

HAL es un formato de tipos de datos que permite codificar no sólo datos sino también controles _hypermedia_, indicando a los consumidores otras partes de la API a las que llamar. El enlace _self_ indica al propio recurso, el enlace _root_ indica el recurso de la colección, los enlaces _add_ y _delete_ indican dos operaciones posibles.

### Ventajas y problemas de HATEOAS

Al cambiar la estructura de las URLs se rompe la compatibilidad de la API con versiones anteriores, uno de los beneficios de HATEOAS es que si la estructura de la URL de la API puede cambiar sin afectar a los clientes al describir estos las URLs de forma dinámica.

Los enlaces devueltos proporcionan al cliente la lista de operaciones que es posible llamar según el estado de la aplicación o la entidad. Esto es útil para los desarrolladores de los clientes dado que no han de duplicar lógica de cuando es posible realizar una operación. En los casos de varias operaciones encadenadas realizadas en varios pasos con HATEOAS la API guía a los clientes hacia el siguiente paso en el flujo proporcionando únicamente los enlaces que son relevantes según el estado de la aplicación.

La documentación de la API sigue siendo requerida para describir la semántica de cada enlace junto con información como la estructura de los tipos y tipo de contenido. 

En la parte negativa está que HATEOAS añade complejidad a la API, que afecta tanto al desarrollador de la API como al consumidor de la misma. Hay que realizar un trabajo adicional para añadir los enlaces apropiados en cada respuesta según el estado de la entidad. Esto provoca que la API sea más compleja de construir que una API que no implementa HATEOAS.

Los clientes de la API también tienen complejidad añadida para entender la semántica de cada enlace además de tener y procesar cada respuesta para obtener los enlaces. Los beneficios pueden compensar esta complejidad añadida pero hay que tenerla en cuenta.

Si la API es pública seguramente algún cliente la use de forma que la usa incorrectamente sin usar el _hypermedia_, haciendo a HATEOAS inútil.

### Ejemplo de recurso REST con HATEOAS y ejemplo de código

En el artículo [Cómo documentar una API REST con Swagger implementada con Spring Boot][blogbitix-584] incluía como ejemplo un servicio REST que únicamente implementa hasta el nivel de madurez 2 de REST, esta es la revisión del servicio para implementar hasta el nivel 3 incluyendo _hypermedia_ con HATEOAS y HAL.

[Spring HATEOAS][spring-hateoas] proporciona métodos y clases para incluir los enlaces de _hypermedia_ de las entidades que se devuelven como resultado en el servicio. La clase [RepresentationModel](https://docs.spring.io/spring-hateoas/docs/current/api/org/springframework/hateoas/RepresentationModel.html) es una clase base que incluye métodos para añadir los controles _hpermedia_, la clase [EntityModel](https://docs.spring.io/spring-hateoas/docs/current/api/org/springframework/hateoas/EntityModel.html) es utilizada cuando el resultado es para una única entidad, [CollectionModel](https://docs.spring.io/spring-hateoas/docs/current/api/org/springframework/hateoas/CollectionModel.html) cuando el resultado es una colección de entidades y [PagedModel](https://docs.spring.io/spring-hateoas/docs/current/api/org/springframework/hateoas/PagedModel.html) cuando el resultado es paginado.

Este es la implementación de servicio REST de ejemplo que trata mensajes, permite obtener una lista de mensajes, crear nuevos y eliminar además de una acción para descubrir todos los enlaces del recurso. Para crear los enlaces de _hypermedia_ de HAL que se devuelven en el JSON como respuesta del servicio se delegan en una clase [RepresentationModelAssembler](https://docs.spring.io/spring-hateoas/docs/current/api/org/springframework/hateoas/server/RepresentationModelAssembler.html).

{{< code file="MessageController.java" language="java" options="" >}}

Estos son dos comandos de _curl_ para realizar una petición y obtener datos de una colección de entidades.

{{< code file="curl-get-all.sh" language="bash" options="" >}}
{{< code file="curl-get-all.json" language="json" options="" >}}

Los enlaces de _hypermedia_ siguiendo la especificación HAL incluidos en el JSON es posible incluirlos directamente con la clase _EntityModel_, sin embargo, si la misma entidad es devuelta por varios _endpoints_ para no duplicar código es posible delegar la creación de la representación del modelo en una clase dedicada a esta tarea.

{{< code file="MessageModelAssembler.java" language="java" options="" >}}

En caso de que la API esté detrás de un _proxy_ los enlaces devueltos por las entidades han de ser adaptados, Spring proporciona un filtro que aplicado a la aplicación permite especificar con cabeceras los datos de las URLs.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="curl-get-index-proxy.sh" language="bash" options="" >}}
{{< code file="curl-get-index-proxy.json" language="json" options="" >}}

Para usar las clases que ofrecen el soporte para HATEOAS es necesario incluir la dependencia de Spring Boot en el archivo de construcción de [Gradle][gradle].

{{< code file="build.gradle" language="groovy" options="" >}}

### Documentación con Swagger

Swagger permite documentar un servicio REST, también incluye soporte para documentar un servicio que cumpla con el principio de _hypermedia_ HATEOAS. Swagger proporciona varias anotaciones que se incluyen en la interfaz del servicio, al procesarlas genera un esquema de la interfaz del servicio con [OpenAPI][openapi] a partir del cual genera la documentación que incluye los _endpoints_ y argumentos, verbos, códigos de respuesta y datos de los modelos. Swagger también permite hacer llamadas a los servicios y obtener el comando _curl_ para hacer la petición desde la línea de comandos.

La definición de la interfaz del servicio además de las anotaciones de Spring para el servicio REST incluye las anotaciones de Swagger para generar el esquema del servicio en _http:\/\/localhost:8080/v3/api-docs_ y generar la documentación en formato HTML accesible en la dirección _http:\/\/localhost:8080/swagger-ui.html_.

{{< code file="MessageApi.java" language="java" options="" >}}

Esta es la documentación de Swagger.

{{< image
    gallery="true"
    image1="image:swagger-ui-1.png" optionsthumb1="650x450" title1="Documentación de servicio REST con Swagger UI" >}}
{{< image
    gallery="true"
    image1="image:swagger-ui-2.png" optionsthumb1="300x200" title1="Documentación de servicio REST con Swagger UI"
    caption="Documentación de servicio REST con Swagger UI" >}}

{{< reference >}}
* [4 Maturity Levels of REST API Design](https://blog.restcase.com/4-maturity-levels-of-rest-api-design/)
* [Richardson Maturity Model](https://www.martinfowler.com/articles/richardsonMaturityModel.html)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [HATEOAS REST Services With Spring](https://dzone.com/articles/hypermedia-driven-rest-services-with-spring-hateoa)
* [Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)
* [HAL - Hypertext Application Language](https://stateless.group/hal_specification.html)
{{< /reference >}}

{{% /post %}}
