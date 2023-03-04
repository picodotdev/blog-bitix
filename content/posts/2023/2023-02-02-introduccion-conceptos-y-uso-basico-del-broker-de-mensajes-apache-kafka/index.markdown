---
pid: 672
type: "post"
title: "Introducción, conceptos y uso básico del broker de mensajes Apache Kafka"
url: "/2023/02/introduccion-conceptos-y-uso-basico-del-broker-de-mensajes-apache-kafka/"
date: 2023-02-02T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:apache-kafka.svg"
tags: ["java", "planeta-codigo", "programacion", "software"]
summary: "La comunicación mediante mensajes permite desacoplar las aplicaciones y procesar las peticiones de forma asíncrona. Apache Kafka es un _broker_ de mensajes muy popular por su escalabilidad, tolerancia a fallos y adaptabilidad para ser usado en diferentes casos de uso. Es una herramienta compleja dado el número de casos de uso que soporta y la necesidad de ser distribuida para dotarse de escalabilidad y tolerancia a fallos."
---

{{% post %}}

{{< logotype image1="apache-kafka.svg" >}}

Las aplicaciones tienen varias formas de comunicación, la más habitual es una comunicación petición y respuesta síncrona, otra opción es una comunicación mediante mensajes asíncrona. La comunicación mediante mensajes tiene varias ventajas al permitir que el productor del mensaje y consumidor no se conozcan lo que evita el acoplamiento entre servicios salvo por el esquema del mensaje que intercambian, la otra ventaja es que no es necesario que el productor y consumidor estén funcionando al mismo tiempo para comunicarse lo que mejora la fiabilidad y resiliencia de la aplicación.

Tecnologías de comunicación de mensajes hay varias en Java está JMS, dado que es una tecnología propia de Java limita las opciones del lenguaje en el que implementar los servicios quizá no es la más adecuada. Otra opción es [RabbitMQ][rabbitmq] que proporciona un broker de mensajes más agnóstico del lenguaje de programación que JMS.

* [Introducción y ejemplo sencillo de Java Message Service (JMS)][blogbitix-15]
* [Ejemplo de RabbitMQ con Java para enviar y recibir mensajes][blogbitix-210]

Siendo RabbitMQ una opción sencilla como cola de mensajes tiene unos límites en la escalabilidad y resiliencia. [Apache Kafka][apache-kafka] es otro broker de mensajes más compleja que RabbitMQ pero que soporta más casos de uso en cuanto a resiliencia y escalabilidad para dar soporte a un gran volumen de mensajes.

Como cualquier herramienta Apache Kafka solo es una herramienta y es muy posible que en el futuro surjan nuevas que mejoren en algún aspecto Apache Kafka, una de esas herramientas puede que sea [Memphis][memphis-dev] que se define a sí misma como tan simple como RabbitMQ y robusta como Apache Kafka.

En cualquier caso Apache Kafka es la opción dominante como _broker_ de mensajes por su gran adaptabilidad a diferentes casos de usos, surgida en el año 2010 y siendo en el 2016 en el que empezó a ganar popularidad.

{{< tableofcontents >}}

## Introducción a Apache Kafka

Apache Kafka es un _broker_ de mensajes altamente escalable al mismo tiempo que complejo. La complejidad en cierta medida es necesaria para ser resiliente y ofrecer tolerancia a fallos, la tolerancia a fallos y la escalabilidad se proporcionan creando un sistema distribuido que es el que añade complejidad a la herramienta como en la mayoría de herramientas distribuidas.

La [documentación de Apache Kafka](https://kafka.apache.org/documentation/) para conocer y entender los conceptos básicos es un buen sitio por donde empezar a aprender. Si se quiere algo más guiado y estructurado en el libro [Apache Kafka: The Definitive Guide](https://amzn.to/3HQfnay) se explica de forma detallada todos los principales conceptos de forma detallada incluyendo los parámetros más importantes de configuración, un libro que me ha parecido muy detallado y recomiendo.

{{< amazon
    linkids="e9a76136c36140e0db4a6d1ecc6e180e"
    asins="1492043087" >}}

### Por qué Apache Kafka

Kafka no es el único sistema de mensajería, las características que hacen de Kafka una buena opción son las siguientes:

* Múltiples productores: varios productores pueden escribir en el mismo _topic_.
* Múltiples consumidores: varios consumidores pueden leer del mismo _topic_ y los mismos mensajes sin que estos se interfieran.
* Durables y retención en disco: los mensajes son persistidos en disco para que ante un error no se pierdan y poder continuar su proceso una vez el error se resuelva. Los _topics_ pueden definir una política de retención en base al tamaño en bytes o de tiempo, superado el límite definido los mensajes se descartan.
* Escalable: la facilidad de particionar los _topics_ y configurar consumidores permite procesar el volumen de mensajes que se tenga aunque sea muy grande, basta con añadir más _brokers_, particiones y consumidores.
* Alto rendimiento: dada su escalabilidad el número de mensajes que puede entregar también es grande, y manteniendo latencias bajas en la entrega de mensajes.
* Características de la plataforma: proporciona librerías y una API para extender el comportamiento de Kafka, integraciones para conectarlo a otros sistemas con [Kafka Connect](https://kafka.apache.org/documentation/#connect) por ejemplo para recibir como mensajes los cambios en una base de datos y [Kafka Streams](https://kafka.apache.org/documentation/streams/) permite procesar flujos constantes de datos.

### Conceptos esenciales

Para usar Kafka de forma correcta conviene entender varios de los conceptos que maneja este _broker_ de mensajes.

Para proporcionar escalabilidad y tolerancia a fallos es necesario utilizar varias instancias de servidor o _brokers_ de Kafka que forman un _cluster_. En caso de que un _broker_ falle por cualquier motivo el resto de _brokers_ asumen su trabajo. Uno de los _brokers_ es designado por el propio _cluster_ como el _controller_ y encargado de ciertas tareas administrativas del _cluster_.

Las aplicaciones tienen un rol de productor de mensajes, consumidor de mensajes o incluso ambos si lee y escribe al mismo tiempo. Las aplicaciones leen y escriben mensajes de Kafka a través de los _topics_, pudiendo haber múltiples productores y consumidores de un mismo _topic_ al mismo tiempo.

Los _producers_ envían los mensajes a los _topics_ pero por escalabilidad y tolerancia a fallos los _topics_ se dividen en particiones o _partitions_. Las particiones son consumidas por un único _consumer_ pero un _consumer_ puede tener asignados varias _partitions_ de las que leer. Al crear el _topic_ se especifica el número de peticiones que se desean para él y se distribuyen entre los diferentes _brokers_. Los mensajes son entregados a las particiones en base a una clave del mensaje en caso de que la tengan, esto permite entregar y agrupar ciertos mensajes en una misma partición para procesarlos en el orden que se producen por ejemplo todos los mensajes relacionados con un usuario.

Los mensajes son obtenidos por los consumidores de las particiones, cada partición es asignada a un _consumer_, solo un consumer lee de una partición y los mensajes de la partición se leen en el mismo orden en que fueron escritos. Por este motivo no tiene sentido tener más consumidores que particiones, y si se necesita escalar el consumo de mensajes con más consumidores es necesario aumentar el número de particiones. En caso de añadir nuevos consumidores se produce un rebalanceo en la asignación de las particiones distribuyendo la carga entre los diferentes _consumers_. Los _consumers_ están agrupados en grupos de consumidores o _consumers groups_, dos _consumer groups_ diferentes reciben la misma colección de mensajes que se envíen al _topic_. A cada mensaje se le asigna un número incremental u _offset_, la diferencia entre el _offset_ producido en un partition y el _offset_ consumido se denomina _lag_, estando cercano a cero significa que el consumidor está procesando los mensajes sin retardo y un _lag_ con valores altos y que se incrementan significa que el consumidor no está siendo capaz de procesar el volumen de mensajes producidos.

Al crear el _topic_ se define el número de particiones que se desea y se especifica el número de réplicas de cada partición. El número de particiones se determina en función del volumen de tráfico previsto del _topic_. Las particiones y las réplicas se distribuyen entre los diferentes _brokers_, para cada una de las particiones una es elegida como la partición _leader_ y en la que los _producer_ envían los mensajes. Las réplicas se mantienen sincronizadas con la partición _leader_, pudiendo definirse también el mínimo número de réplicas que han de estar sincronizadas. A las réplicas que están sincronizadas se les denomina ISR o _in sync replica_.

Los mensajes enviados a Kafka se escriben en disco y tienen la durabilidad definida según el periodo de retención definido para el _topic_. Los mensajes de las particiones escritas en el sistema de archivos se guardan en segmentos. Realmente la durabilidad aplica no a los mensajes individuales sino a los segmentos de las particiones.

En un sistema distribuido que se comunica por red hay fallos y que pueden ocasionar cosas inesperadas. Kafka proporciona la garantía que los mensajes se entrega en el mismo orden que se reciben, que los mensajes considerados _commited_, aquellos que se escriben en todas la réplicas sincronizadas, no se pierden y que los consumidores solo leen mensajes _commited_. Otras garantías que Kafka implementa son medidas de garantía en la entrega de mensajes o _delivery garantees_ para que las aplicaciones se abstraigan de los posibles errores. Las garantías en la entrega de mensajes son como mucho uno o _at most one_ con la que el mensaje solo se entrega como mucho una vez pudiendo perderse mensajes, con al menos uno o _at least one_ se garantiza que se entrega una vez pero pudiendo recibir el mismo mensaje varias veces y finalmente exactamente uno o _exactly once_ con la que el mensaje se entrega exactamente una vez. Sin estas garantías, puede haber casos en los que los mensajes no se entregan o se entregan varias veces.

Los mensajes tienen una clave, en algunos casos de uso solo interesa mantener el último mensaje de cada clave en un _topic_, a estos _topics_ que solo mantienen la última versión de un mensaje según su clave se les denomina _compacted topics_.

Kafka no impone ninguna restricción en el contenido de los mensajes, los trata simplemente como una cantidad de bytes ya sean de texto, comprimidos o cifrados. Como formato se puede utilizar uno basado en texto como JSON o uno binario más eficiente como Avro y con un esquema.

Para algunas de tareas Kafka utiliza [Zookeeper][apache-zookeeper] para la coordinación, consenso y almacenamiento de configuración. Es otro _cluster_ de servidores que hay que mantener y añade complejidad. Los desarrolladores están trabajando para que Zookeeper no sea necesario y simplificar el uso de Kafka.

### Propiedades de configuración

Kafka soporta numerosas [propiedades de configuración](https://kafka.apache.org/documentation/#configuration) entre las que están el número de particiones de los _topics_, la retención de los segmentos de los mensajes en base a tiempo o número de bytes, el número de réplicas a mantener de cada partición y otros parámetros de tiempos al procesar los mensajes y comunicación entre brokers.

Estas propiedades se configuran en función del volumen de tráfico generado por los productores, el volumen que es capaz de procesar cada consumidor y el volumen de tráfico o almacenamiento que es capaz de soportar un _broker_. El conjunto de los consumidores han de tener una capacidad de procesamiento similar a la de los productores, si los consumidores no fueran capaces de soportar el volumen de los productores y la situación se mantuviese de forma prolongada pasado un tiempo se acumularían demasiados mensajes sin procesar, el _lag_ aumentaría, situación que podría generar malos comportamientos en las aplicaciones como pérdida de mensajes.

Un solo _broker_ soporta un volumen grande de tráfico, pero en los casos de un uso intenso es necesario mayor número de instancias.

### Descarga e inicio con contenedores Docker

Una opción sencilla para usar Apache Kafka es usándolo a través de un contenedor de [Docker][docker], basta instalar Docker y descargar el contenedor de Kafka.

* [Cómo instalar y guía de inicio básica de Docker][blogbitix-50]
* [Aplicaciones multicontenedor con Docker Compose][blogbitix-87]

Como imagen de contenedor de Kafka están las proporcionadas por la empresa Confluent que participa en el desarrollo de Kafka y proporciona varias herramientas complementarias de Kafka.

* [Imagen de Docker de cp-kafka](https://hub.docker.com/r/confluentinc/cp-kafka)
* [Imagen de Docker de cp-kafka cp-zookeeper](https://hub.docker.com/r/confluentinc/cp-zookeeper)
* [Guida de inicio rápido de Kafka con Docker](https://developer.confluent.io/quickstart/kafka-docker/)
* [Propiedades de configuración de apache Kafka](https://docs.confluent.io/platform/current/installation/configuration/broker-configs.html)
* [Propieades de configuración de Zookeeper](https://docs.confluent.io/platform/current/installation/docker/config-reference.html#required-zk-settings)

Este es un archivo de [Docker Compose][docker-compose] que permite arrancar varios contenedores a la vez, en el se muestra un _cluster_ de tres instancias de Kafka y una única instancia de Zookeeper.

{{< code file="docker-compose-up.sh" language="bash" options="" >}}
{{< code file="docker-compose.yml" language="yaml" options="" >}}

### Uso

Los _producers_ y _consumers_ se pueden implementar en diferentes lenguajes entre los que está Java. Kafka proporciona una API para la integración con Kafka y el proyecto [Spring][spring] una integración para las aplicaciones que utilizan este _framework_ para mayor facilidad de uso en el proyecto [Spring for Apache Kafka][spring-kafka].

No es necesario crear un cliente de Kafka con un lenguaje de programación, Kafka proporciona varias utilidades administrativas que permiten consultar información de configuración y enviar y recibir mensajes desde la línea de comandos a un determinado _topic_.

Con esta utilidades permite probar Kafka por ejemplo para realizar el balanceo de consumidores cuando se agregan y se quitan, probar el periodo de retención de los mensajes o probar que ocurre cuando un _broker_ se cae o detiene.

Listar que _topics_ hay en un _cluster_ y crear un _topic_ con las propiedades de número de particiones, réplicas y retención se realiza con las utilidades de línea de comandos para interactuar con el _cluster_.

{{< code file="kafka-topics-create.sh" language="bash" options="" >}}
{{< code file="kafka-topics-delete.sh" language="bash" options="" >}}
{{< code file="kafka-topics-list.sh" language="bash" options="" >}}
{{< code file="kafka-topics-list.out" language="plain" options="" >}}

Como se ha indicado de forma explícita al crear el _topic_ y por los valores indicados en las variables de entorno del archivo _docker-compose.yml_ que indican la configuración por defecto si no se hubiese indicado de forma explícita el _topic_ tiene dos particiones y cada partición dos réplicas. al describir el _topic_ se indican que _broker_ es el líder de la partición, en que _brokers_ están las réplicas de cada patición y que _brokers_ tienen su réplica sincronizada.

{{< code file="kafka-topics-describe.sh" language="bash" options="" >}}
{{< code file="kafka-topics-describe-1.out" language="plain" options="" >}}

Las operaciones básicas son que el productor envíe mensajes y los consumidores los reciban. Las utilidades de línea de comandos permiten crear consumidores y grupos de consumidores.

{{< code file="kafka-console-producer.sh" language="bash" options="" >}}
{{< code file="kafka-console-producer.out" language="plain" options="" >}}

Diferentes formas de arrancar un consumidor, leyendo el primer mensaje del _topic_, leyendo solo los nuevos mensajes y dentro de un _consumer group_.

{{< code file="kafka-console-consumer.sh" language="bash" options="" >}}
{{< code file="kafka-console-consumer.out" language="plain" options="" >}}

Listar los _consumers_ permite ver cuántos hay, que _offset_ están consumiendo y que _lag_ tienen. En esta salida el primer _consumer-group_ hay un único _consumer_ que está consumiendo de ambas particiones del _topic_, en el segundo _consumer-group_ hay dos _consumers_ diferentes cada uno consumiendo de una partición.

{{< code file="kafka-consumer-groups.sh" language="bash" options="" >}}
{{< code file="kafka-consumer-groups.out" language="plain" options="" >}}

Las réplicas de las particiones son distribuidas entre los _brokers_, si un _broker_ que es _leader_ de una partición deja de funcionar otro _broker_ con una réplica sincronizada o _isr_ se convertirá en el nuevo _leader_, este mecanismo es el que utiliza Kafka para tener la propiedad de tolerancia a fallos.

Aunque Kafka tiene tolerancia a fallos no se auto repara a si mismo en todas las situaciones. Las réplicas que tuviese un _broker_ que ha fallado no se distribuyen en otros _brokers_ para que el número de réplicas definido se mantenga constante. Kafka espera que si se cae un _broker_ esté al cabo de un tiempo se recupere y mientras tanto el resto de _brokers_ siguen procesando los mensajes. Las réplicas no se mueven de _broker_ debido a que podría ser demasiado costoso ya que pueden alcanzar tamaños suficientemente grandes para que el tráfico de red fuese muy elevado y costoso en tiempo.

Al forzar la parada de un _broker_ simulando un fallo sus réplicas se dejan de considerar sincronizadas.

{{< code file="docker-stop-broker2.sh" language="bash" options="" >}}
{{< code file="kafka-topics-describe-2.out" language="plain" options="" >}}

Una vez el _broker_ se vuelve a levantar quizá por una acción manual del administrador de sistemas o automáticamente por del orquestador de contenedores las réplicas que tenía asignadas el _broker_ 2 se vuelven marcar como sincronizadas en la columna _isr_.

{{< code file="docker-start-broker2.sh" language="bash" options="" >}}
{{< code file="kafka-topics-describe-3.out" language="plain" options="" >}}

### Tutorial

En [YouTube][youtube] hay varios tutoriales con varios capítulos acerca de Kafka con más información y mostrándolo en vídeo, basta con hacer un búsqueda.

* [Spring Boot + Apache Kafka Tutorial - #1 - Apache Kafka Overview](https://www.javaguides.net/2022/12/spring-boot-apache-kafka-tutorial-1.html)

{{< youtube
    video="j4bqyAMMb7o" >}}

{{< reference >}}
* [Kafka - Broker failures, replicas was not recreated](https://stackoverflow.com/questions/64534639/kafka-broker-failures-replicas-was-not-recreated)
{{< /reference >}}

{{% /post %}}