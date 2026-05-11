---
pid: 729
type: "post"
title: "Procesos orquestados fiables y observables en servicios distribuidos con Temporal"
url: "/2026/05/procesos-orquestados-fiables-y-observables-en-servicios-distribuidos-con-temporal/"
date: 2026-05-05T20:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "logotype:temporal.svg"
tags: ["java", "programacion"]
summary: "Temporal es una herramienta open source que permite modelar procesos de larga duración directamente en código, con garantías de fiabilidad y resiliencia ante fallos. En este artículo explico sus conceptos fundamentales, workflows, actividades y señales, y los pongo en práctica implementando en Java un proceso de compra de entradas con múltiples microservicios implicados."
---

{{% post %}}

{{< logotype image1="temporal.svg" >}}

Desde hace tiempo tenía pendiente investigar y entender Cadence, tras un tiempo leyendo su documentación no tenía claro cómo funcionaba, qué funcionalidad cubría y como funcionaba. Sin embargo, al preguntarle a Claude me indicó, como ocurre ocurre en muchos casos, los desarrolladores originales han creado un _fork_, en el caso de Cadence el _fork_ es Temporal.

Tras unos días y siguiendo la buena documentación ofrecida por Temporal tengo un ejemplo con el que entender, practicar y usar un pequeño proceso. Con la documentación y el ejemplo pensar en posibles casos de uso al dar a solución a necesidades.

En la web dan algunos ejemplos de casos de uso de Temporal y en su página de inicio una descripción introductoria de la herramienta.

{{< tableofcontents >}}

## Procesos con Temporal

De forma muy resumida [Temporal][temporal] es una herramienta que permite modelar procesos que pueden durar minutos, días, meses o años e integrar interacciones humanas. Además, la implementación de los procesos se realiza con garantías de fiabilidad y resiliencia al considerar que cualquier paso del proceso puede fallar e implementando políticas de reintentos.

Temporal tiene otras características diferenciadoras. Las herramientas tradicionales para modelar procesos se basan en la definición de un proceso en una notación textual como xml y su entorno de ejecución es el servidor de procesos. En Temporal por el contrario el proceso se define en código en alguno de los diferentes lenguajes de programación soportados con mayores capacidades que un archivo descriptor.

Sus [casos de uso](https://docs.temporal.io/evaluate/use-cases-design-patterns) son los siguientes:

* Máquinas de estados complejas: Modelar entidades de negocio con ciclos de vida largos y múltiples transiciones de estado, como un pedido que pasa por creado, reservado, pagado, enviado y entregado a lo largo de días.
* Procesos de larga duración con esperas humanas: Flujos de aprobación, onboarding de usuarios o procesos de KYC donde el workflow queda en espera de una acción humana durante horas, días, semanas, meses o años y luego continúa.
* Sagas y transacciones distribuidas: Implementar el patrón saga para gestionar la consistencia eventual entre microservicios con compensaciones automáticas si algún paso falla, evitando inconsistencias de datos.
* Tareas programadas y recurrentes: Con la funcionalidad de schedule, sustituir cronjobs frágiles por procesos planificados con reintentos, historial y observabilidad.

Casos de no uso son los siguientes:

* Procesos simples y de corta duración: si el proceso se resuelve en una sola llamada HTTP o una transacción de base de datos, Temporal añade una complejidad innecesaria. Para esos casos un simple servicio REST o una cola de mensajes como Kafka es suficiente.
* Alta frecuencia y baja latencia: Temporal no está diseñado para procesos que requieren respuesta en milisegundos o millones de ejecuciones por segundo, como procesamiento de eventos en tiempo real o sistemas de trading de alta frecuencia.
* Equipos pequeños sin necesidad de resiliencia distribuida: Si la aplicación es un monolito o un sistema sencillo, introducir Temporal implica operar un servidor adicional con su base de datos, lo que puede no justificarse.
* Lógica puramente reactiva a eventos: Si el sistema ya está bien modelado con event sourcing y CQRS y los procesos son stateless, herramientas como Kafka Streams o Apache Flink encajan mejor de forma natural.
* Procesos con requisitos de latencia estrictos en cada actividad: Temporal introduce overhead en la coordinación entre el servidor y los workers, por lo que si cada actividad del proceso tiene un SLA de milisegundos, ese overhead puede ser un problema.

## Conceptos

Temporal define los siguientes conceptos.

* Workflows que representan los procesos y se implementan en código de un lenguaje de programación. En Java los workflows tienen la definición de una interfaz con un método que inicia el proceso, métodos para recibir mensajes que permiten consultar el estado del proceso y cambiar el estado. El proceso en su implementación invoca los pasos del proceso o actividades.
* Las actividades son los pasos del proceso, se ejecutan como consecuencia del progreso del proceso, cambio estado o de los mensajes que recibe el proceso.
* Las señales son la forma de comunicarse con el proceso, obtener información, cambiar el flujo de ejecución de forma síncrona y asíncrona.
* Finalmente los workers, son las unidades de cómputo que ejecutan el proceso y las actividades.

Temporal es un binario que hace de servidor coordinando la ejecución de los procesos, gestionando los cambios de estado y almacenando el estado del proceso. Registra el inicio de una nueva instancia de un proceso, los datos de entrada y salida de cada actividad junto a otros metadatos como cuanto tiempo tarda cada paso.

En modo desarrollo el estado de los procesos se almacena en memoria. En un entorno de producción el estado se aconseja guardar en una base de datos como PostgreSQL.

Esta supervisión de los procesos permite a Temporal dar visibilidad de los procesos que es una característica que modelan los procesos con coreografía está distribuída entre los diferentes procesos.

## Funcionalidades

Temporal soporta diferentes funcionalidades, en la documentación se detallan entre las que están:

* Schedule: permite planificar procesos cada cierto tiempo.
* Composability: es posible crear e invocar subprocesos.
* Encryption: permite cifrar los datos del proceso, algunos de los datos pueden ser sensibles.
* Observability: se recogen métricas a raíz del estado del proceso.
* Testing: es posible crear diferentes tipos de teses unitarios, de integración y end-to-end.

## Definición de un proceso

Supongamos que tenemos un proceso de compra de entradas. El sistema ha sido desarrollado utilizando domain driven design y microservicios separando las funciones del sistema en diferentes subdominios y se compone del flujo hasta que el order queda en estado confirmada.


{{< image
    gallery="true"
    image1="image:diagrama-de-secuencia.webp" optionsthumb1="650x450" title1="Diagrama de secuencia"
    caption="Diagrama de secuencia" >}}

Tras el estado de confirmación o cancelación de la orden comenzaría un siguiente proceso, subprocesos asociados o faltaría definir más pasos en el proceso hasta que el producto o servicio por el que se ha pagado ha sido entregado al cliente. Para el ejemplo es suficiente hasta confirmación.

Implementar este proceso en un sistema distribuido de microservicios en el que hay múltiples puntos de fallo basta que alguna de las actividades temporalmente no funcione para generar inconsistencias de datos aún con reintentos en los procesos de esta coreografía de microservicios.

Que pasa si, ¿se reservan los tickets, falla la autorización y no se pueden liberar los tickets temporalmente? o ¿si se crea el order pero no se puede notificar la decisión al sistema de fraude si finalmente se acepta la transacción?. Además al estar el proceso distribuido en diferentes servicios es difícil tener visibilidad con información completa del estado del proceso y pasos por los que ha pasado.

Generalmente un sistema con coreografía funciona pero en ciertos errores el proceso deja un estado inconsistente las transacciones, investigar y resolver las inconsistencias de datos, si la resolución es manual es una actividad que consume gran cantidad de tiempo.

Temporal proporciona soluciones a estos problemas a un proceso vital en cualquier dominio como son las transacciones como este en el proceso de compra.

## Ejemplo con Temporal

Esta es la definición del proceso en el lenguaje de programación Java. El proceso es simplemente una interfaz con anotaciones.

### Workflow

La definición del _workflow_.  

{{< code file="PlaceOrderWorkflow.java" language="java" options="" >}}

Esta es la implementación del proceso. Contiene las diferentes actividades del proceso que invocan diferentes sistemas. No contiene más unas pocas sentencias de código haciendo uso de las actividades del proceso. Como si fuera una secuencia de intrucciones u orquestado en un patrón saga.

{{< code file="PlaceOrderWorkflow.java" language="java" options="" >}}

### Activities

La definición en interfaces.

{{< code file="FraudActivities.java" language="java" options="" >}}
{{< code file="InventoryActivities.java" language="java" options="" >}}
{{< code file="OrderActivities.java" language="java" options="" >}}
{{< code file="PaymentActivities.java" language="java" options="" >}}

La implementación. Para el caso del ejemplo las actividades no invocan sistemas externos, simplemente emiten un mensaje. Los procesos han de ser reproducibles y reconstruibles, por tanto algunas funcionalidades han de usar las facilidades de Temporal, especialmente en el manejo de tiempo y datos aleatorios.

{{< code file="DefaultFraudActivities.java" language="java" options="" >}}
{{< code file="DefaultInventoryActivities.java" language="java" options="" >}}
{{< code file="DefaultOrderActivities.java" language="java" options="" >}}
{{< code file="DefaultPaymentActivities.java" language="java" options="" >}}

### Worker

El worker registra que _workflows_ y las _activities_ va a ejecutar.

{{< code file="PlaceOrderWorkflowWorker.java" language="java" options="" >}}

### Programa

Luego arranca una instancia el _worker_ y el _workflow_,

{{< code file="Main.java" language="java" options="" >}}
{{< code file="Workflow.java" language="java" options="" >}}

## Servicio de Temporal

El servicio de Temporal se inicia con el siguiente comando en modo desarrollo.

{{< code file="temporal.sh" language="bash" options="" >}}

Esta es la salida en la consola de la ejecución del _workflow_.

{{< code file="System.out" language="plain" options="" >}}

La línea de comandos de Temporal permite iniciar _workflows_, actividades y enviar mensajes además de consultar el estado de los _workflows_. Temporal tiene una interfaz gráfica que permite visualizar y ver el estado de la ejecución de cada proceso, con los datos de entrada y salida de las actividades así como los tiempos de inicio y fin. Esta observabilidad es una característica que no tiene un sistema distribuido que emplea coreografía cuando hay que examinar en que estado está el proceso por alguna inconsistencia de datos. 

{{< image
    gallery="true"
    image1="image:temporal-1.webp" optionsthumb1="300x200" title1="Worflow en Temporal"
    image2="image:temporal-2.webp" optionsthumb2="300x200" title2="Worflow en Temporal"
    caption="Worflow en Temporal" >}}

Finalmente, es posible construir teses unitarios para el proceso.

## Videos de introducción

{{< youtube
    video="zLjhNrOKphE" >}}

{{< youtube
    video="2HjnQlnA5eY" >}}

{{< youtube
    video="EwweiH2rd7M" >}}

{{< youtube
    video="zLjhNrOKphE" >}}

{{< youtube
    video="rtWrzjnKlSQ" >}}

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoTemporal" command="./gradlew run" %}}

{{% /post %}}
