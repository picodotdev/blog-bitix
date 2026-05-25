---
pid: 730
type: "post"
title: "Introducción y uso de las especificaciones de CloudEvents y AsyncAPI"
url: "/2026/05/introduccion-y-uso-de-las-especificaciones-de-cloudevents-y-asyncapi/"
date: 2026-05-18T20:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["programacion", "planeta-codigo"]
summary: "En los sistemas distribuidos, la integración entre servicios no se limita a las APIs REST. La comunicación asíncrona mediante mensajes es igualmente habitual, y también requiere un contrato claro entre las partes. Para cubrir esta necesidad existen dos especificaciones complementarias. Una CloudEvents, que estandariza el formato del sobre de cualquier menssaje o event, y otra AsyncAPI, que describe la API completa de un servicio orientado a mensajes, de forma análoga a lo que hace OpenAPI para REST. En este artículo exploramos ambas especificaciones y las ponemos en práctica con una aplicación Spring Boot que produce y consume eventos Kafka encapsulados como CloudEvents, acompañada de su documento AsyncAPI correspondiente."
---

{{% post %}}

{{< logotype image1="asyncapi.svg" image2="cloudevents.svg" >}}

En una arquitectura de microservicios o sistemas distribuidos es necesario conocer la API de esos servicios para poder integrar otros servicios con ellos. En los servicios que exponene una API con REST la definición de las APIs se puede especificar con [OpenAPI][openapi].

* [Generar clientes REST con su interfaz OpenAPI][blogbitix-715]

Pero es habitual como otra forma de integrar servicios hacerlo mediante mensajes de forma asíncrona. Aunque la comunicación sea asíncrona y desacoplada también existe un contrato o interfaz entre los servicios, este es el formato del mensaje y los datos que incluye.

Para la definición de las APIs de un servicio que produce o consume mensajes están [CloudEvents][cloudevents] y [AsyncAPI][asyncapi]. Ambas especificaciones son complementarias pero independientes.  AsyncAPI describe la API (canales, operaciones, esquemas) mientras CloudEvents define el sobre del mensaje que viaja por esos canales.

{{< tableofcontents >}}

## CloudEvents

CloudEvents simplemente estandaríza el formato del mensaje y define algunos campos a incluir como el payload de datos del mensaje y el tipo del formato de ese payload.

CloudEvents es una especificación para el evento en sí mismo, un formato de sobre estándar bajo la CNCF. Define un conjunto de atributos obligatorios y opcionales (_id_, _source_, _specversion_, _type_, más opcionales como _subject_, _time_, _datacontenttype_, etc.) que cualquier evento debería incluir, independientemente de si viaja por Kafka, HTTP, AMQP, NATS o Pub/Sub. El payload en sí sigue siendo específico del servicio, CloudEvents simplemente estandariza el envoltorio de metadatos y cómo se serializa sobre cada protocolo (modo binario vs. modo estructurado).

Esto permite tener un formato de mensajes  común entre varios servicios, equipos, sistemas de mensajería o integraciones con terceras partes.

### Modos de transporte

CloudEvents soporta diferentes protocolos de transporte y dos modos de funcionamiento:

* Modo estructurado: El evento completo, metadatos y _payload_, viaja en un único bloque serializado, normalmente JSON. En Kafka eso significa que todo va en el *value* del mensaje.
* Modo binario: Los atributos de CloudEvents se mapean a los *headers* del protocolo de transporte, y el *value* del mensaje contiene únicamente el payload de negocio.

Un ejemplo en modo estructurado.

{{< code file="cloudevents-1.json" language="json" options="" >}}

Un ejemplo en modo binario. Estas son las cabeceras en el mensaje de Kafka.

{{< code file="cloudevents-2-headers.json" language="plain" options="" >}}

El _payload_ de Kafka.

{{< code file="cloudevents-2-body.json" language="json" options="" >}}

### Qué modo usar y por qué

El modo binario es el recomendado en la mayoría de casos productivos por varias razones prácticas:

- Rendimiento: los consumidores que solo necesitan enrutar o filtrar mensajes pueden leer los _headers_ sin deserializar el _payload_ completo. En un sistema con alto volumen de mensajes esto tiene impacto real.
- Compatibilidad: el _payload_ de negocio queda aislado en el value sin que CloudEvents lo contamine, lo que facilita la convivencia con consumidores que usan y no usan CloudEvents.
- Procesamiento en infraestructura: herramientas intermedias como Kafka Streams, conectores de Kafka Connect o proxies HTTP pueden inspeccionar y enrutar por los metadatos sin tocar el _payload_.

El modo estructurado es más sencillo de implementar y depurar porque todo está en un sitio, pero acopla el formato CloudEvents al consumidor. Cualquier lector del mensaje necesita entender el sobre para extraer el _payload_.

La forma de usar un modo u otro es la siguiente. El serializador se encarga de mover los atributos a _headers_ o al JSON según el modo, de forma transparente para el resto del código.

{{< code file="cloudevents-3.java" language="java" options="" >}}

## AsyncAPI

AsyncAPI es una especificación para describir una API orientada a eventos, como OpenAPI, pero para sistemas asíncronos. La especificación es un documento _yaml_ que describe los canales de tu servicio (_topics_ o colas), las operaciones que publica/suscribe, los esquemas de los mensajes, los _bindings_ del servidor (clúster de Kafka, configuración del broker) y la seguridad.

A partir de ese documento al igual que con OpenAPI se puede generar documentación, _stubs_ de código y teses de contrato. Los conceptos clave de AsyncAPI, siguiendo la estructura del documento de especificación:

* Info y servers: _info_ contiene los metadatos de la API. Título, versión, descripción y licencia, igual que en OpenAPI. _servers_ define los brokers con los que trabaja la aplicación: la URL de conexión, el protocolo (_kafka_, _amqp_, _mqtt_, _ws_...) y la configuración de seguridad.
* Channels: Un _channel_ es el canal de comunicación, el _topic_ de Kafka, la cola de RabbitMQ o el _subject_ de NATS. Es el concepto central de AsyncAPI, define dónde viajan los mensajes. Cada _channel_ tiene un nombre y puede tener parámetros dinámicos en el _path_, igual que las rutas REST.
* Operations: Una opración define qué hace tu aplicación con un _channel_, _send_ (produce mensajes) o _receive_ (los consume). A diferencia de OpenAPI donde un _endpoint_ suele hacer una sola cosa, un mismo _channel_ puede tener operaciones de envío y recepción en servicios distintos.
* Messages: Un message define la estructura del mensaje que viaja por el channel: sus headers, el _content type_ y el _payload_. En el _payload_ es donde encaja CloudEvents: los atributos _ce_type_, _ce_source_, etc. se pueden modelar como headers del mensaje en AsyncAPI.
* Components: El bloque _components_ es el almacén de elementos reutilizables, exactamente igual que en OpenAPI. _schemas_ para los modelos de datos, _messages_ para los mensajes, _securitySchemes_ para los mecanismos de autenticación y _serverVariables_ para parametrizar los servers. Todo se referencia con _$ref_.
* Bindings: Los _bindings_ son el concepto más específico de AsyncAPI y uno de los más potentes. Permiten añadir configuración propia del protocolo en cualquier nivel, _server_, _channel_, _operation_ o _message_. En Kafka por ejemplo puedes definir el número de particiones, el factor de replicación o el _groupId_ del consumidor, cosas que no tienen equivalente en un protocolo genérico.

En el ejemplo uso la siguiente definción de la API con AsyncAPI del productor y consumidor de la aplicación.

{{< code file="orders.yaml" language="yaml" options="" >}}

{{< image
    gallery="true"
    image1="image:flowchart.webp" optionsthumb1="650x450" title1="Diagrama de flujo"
    caption="Diagrama de flujo" >}}

### Línea de comandos

AsyncAPI ofrece una herramienta de linea de comandos con la que validar la especificación y generar artfactos al igual que se puede hacer con el documento de especificación OpenAPI. Por ejemplo, generar los clienteso documentación de la API.

{{< code file="asyncapi.sh" language="bash" options="" >}}

## Cuando tiene sentido adoptar CloudEvents y AsyncAPI

Uno de los escenarios donde más se nota la ausencia de AsyncAPI es cuando un evento evoluciona (campos nuevos, obsolescencia de campos, _breaking changes_). Tener el contrato escrito permite obtener las diferencias entre versiones y comunicar cambios.

Tiene sentido adoptarlas juntas cuando se dan una o varias de estas situaciones.

### Múltiples equipos o servicios consumiendo los mismos eventos

Cuando un evento lo consumen tres servicios distintos mantenidos por equipos diferentes, CloudEvents garantiza que todos hablan el mismo idioma en cuanto al sobre del mensaje, y AsyncAPI documenta el contrato de forma que cualquier equipo puede integrarse sin preguntar. Sin estas especificaciones el contrato existe igualmente, pero está en la cabeza de alguien o en un Confluence desactualizado.

### Heterogeneidad de sistemas de mensajería

Si hoy usas Kafka pero mañana puede aparecer RabbitMQ, un bus de eventos cloud como EventBridge o una integración con un tercero vía HTTP, CloudEvents te da portabilidad del formato del evento independientemente del transporte. AsyncAPI describe los _bindings_ específicos de cada protocolo sin cambiar el resto del documento.

### Integración con terceros o sistemas externos

Es el caso donde más valor aportan. Cuando expones eventos a un partner externo o consumes eventos de una plataforma SaaS, tener un documento AsyncAPI es el equivalente a publicar una API REST con OpenAPI. El otro equipo puede leerlo, validarlo y generar código sin necesidad de reuniones. CloudEvents añade la garantía de que el formato del mensaje es estándar y reconocible.

### Equipos que ya usan OpenAPI para sus APIs REST

Si el equipo ya tiene cultura de API-first con OpenAPI, AsyncAPI es la extensión natural para los servicios asíncronos. El salto conceptual es pequeño y las herramientas son similares. CloudEvents complementa esto estandarizando lo que OpenAPI no cubre, el formato del mensaje en tránsito.

### Cuándo no tiene tanto sentido

Si tienes un sistema pequeño con un único productor y un único consumidor en el mismo equipo y repositorio, el _overhead_ de mantener un documento AsyncAPI y ajustarse a CloudEvents puede no compensar. El contrato en ese caso es el código compartido, un _record_ de Kotlin o un DTO de Java con Jackson. Adoptar estas especificaciones tiene sentido cuando el coste de la descoordinación entre partes empieza a ser real, no como práctica preventiva en proyectos pequeños.

La regla práctica es que si el evento cruza una frontera de equipo, de sistema o de organización, las dos especificaciones juntas valen la inversión. Si el evento es interno a un servicio o a un equipo muy pequeño, probablemente es sobreingenieria.

## Ejemplo usando CloudEvents y AsyncAPI

En este ejemplo de aplicación Java hay un servicio REST que recibe una petición de y genera un mensaje que es enviado a una cola de [Kafka][spring-kafka], el mensaje es encapsulado en un mensaje de CloudEvents usando el modo recomendado binario.

{{< code file="EventsController.java" language="java" options="" >}}
{{< code file="EventsProducer.java" language="java" options="" >}}
{{< code file="EventPayload.java" language="java" options="" >}}

La configuración de Kafka para la aplicación de Spring Boot es la siguiente, junto con el archivo de construcción con las dependencias.

{{< code file="application.yml" language="yaml" options="" >}}
{{< code file="build.gradle.kts" language="kotlin" options="" >}}

El archivo de Docker Compose para iniciar el contenedor de Kafka.

{{< code file="docker-compose.yml" language="yaml" options="" >}}

El consumidor del mensaje está en la misma aplicación que simplemente emite un mensaje en la salida del sistema.

{{< code file="EventsConsumer.java" language="java" options="" >}}
{{< code file="curl.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/AsyncApiCloudEvents" command="./gradlew run" %}}

{{% /post %}}

