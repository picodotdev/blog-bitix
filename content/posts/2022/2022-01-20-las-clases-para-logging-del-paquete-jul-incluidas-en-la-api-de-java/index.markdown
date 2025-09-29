---
pid: 618
type: "post"
title: "Las clases para logging del paquete JUL incluidas en la API de Java"
url: "/2022/01/las-clases-para-logging-del-paquete-jul-incluidas-en-la-api-de-java/"
date: 2022-01-20T22:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Entre las clases de la API de Java hay clases para la tarea de trazas o _logging_ disponibles desde la versión 1.4 del JDK que para muchos casos son suficientes. Una de las librerías más popular para _logging_ en Java como Log4j proporcionan funcionalidades más avanzadas que las del paquete _java.util.logging_ y la seguridad de que en caso de necesitar algo relativo a _logging_ será raro que Log4j no lo soporte de alguna forma. Sin embargo, las clases del paquete JUL de Java son suficiente y no es imprescindible una librería external ni incluir su dependencia en el proyecto."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las trazas o _logs_  en una aplicación forman parte de la parte del área de trazabilidad, observabilidad, monitorización e incluso en el área de seguridad, una de las [funcionalidades que necesitan las aplicaciones][blogbitix-516], más imprescindible aún en los microservicios. Las trazas son mensajes con información que emite la aplicación para su análisis, estas se pueden emitir a un destino que puede ser simplemente la salida estándar de la consola, un archivo y en los sistemas más avanzados un sistema ELK para [centralizar las trazas en un único sistema compuesto por múltiples aplicaciones][blogbitix-517]. Una buena práctica es emitir las trazas en la salida estándar de la aplicación y que sea un proceso externo el que determina como y donde se guardan las trazas de la aplicación,  lo que proporciona la flexibilidad de cambiar donde se guardan las trazas sin requerir cambios en la aplicación.

Las trazas permiten ver qué ha ocurrido hace tiempo y conocer qué ocurre en la aplicación en tiempo real. Las trazas incluyen las excepciones que se generan en la aplicación y errores, identificados los errores que se están produciendo las mismas trazas dan pistas de las condiciones en las que se producen, identificar el problema, reproducirlo y con todo ello hacer los cambios en el código necesarios para corregir los errores. Sin las trazas es mucho más difícil conocer qué errores se están produciendo en la aplicación y en qué condiciones se producen para reproducirlo. Hay que tener en cuenta que muchas veces la mayor parte del tiempo que se emplea para corregir un error es en identificar las causas y reproducirlo, la parte de corregirlo haciendo cambios en el código generalmente es una pequeña parte de todo el tiempo empleado. La facilidad y rapidez para corregir un error en gran medida depende de que la aplicación emita una colección de buenas trazas.

En Java la librería [Log4j][log4j] es la más utilizada para la funcionalidad de trazas, es una librería muy completa que tiene todas las funcionalidades relativas a trazas que requiera una aplicación en el momento actual o en el futuro. Otra librería muy popular en Java es [SLF4][slf4j] que permite independizar a la aplicación de la implementación de la traza pudiendo cambiar de una u otra según se desee.

Otra alternativa es usar las clases que se incluyen en él la propia API de Java para _logging_ en el paquete [java.util.logging](javadoc17:java.logging/java/util/logging/package-summary.html). No son tan avanzadas como las de Log4j pero son suficientes para muchos casos de uso. Dado a un [grave fallo de seguridad en Log4j][blogbitix-616] qué ha afectado a muchas empresas, y raíz de esta situación quizá alguna persona considere utilizar o revisar las clases de _logging_ de Java como alternativa a incluir una dependencia para algo que con lo que ofrece la API quizá sea suficiente para una aplicación.

{{< tableofcontents >}}

## Las clases de _logging_ de Java

Al ofrecer Java en su API clases para realizar tareas de _logging_ significa que cualquier aplicación las pueda utilizar sin ninguna dependencia adicional a partir de la versión 1.4 en la que fueron incluidas. A las clases y sistema de _logging_ de Java se le denomina JUL por las primeras letras de los paquetes en las que están, _java.util.logging_.

Las clases principales de JUL son equivalentes a las que se encuentran en otras librerías ya que en definitiva en cualquier librería representan los mismos conceptos del área de dominio de _logging_.

* [Logger](javadoc17:java.logging/java/util/logging/Logger.html): es la clase de entidad principal que usan las aplicaciones para emitir trazas. Se crea una instancia de _logger_ diferente por cada componente específico del sistema o aplicación.
* [LogRecord](javadoc17:java.logging/java/util/logging/LogRecord.html): es una clase que contiene los datos a ser pasados entre el _framework_ de logging y los _log handlers_.
* [Handler](javadoc17:java.logging/java/util/logging/Handler.html): envía los objetos _LogRecord_ a una variedad de destinos incluyendo memoria, flujos de salida, la consola, archivos y _sockets_. Existe una variedad de subclases de _Handler_ con una implementación  para cada destino. Adicionalmente los _Handlers_ pueden ser desarrollados por tercera partes e integrados sobre el núcleo de la plataforma.
* [Level](javadoc17:java.logging/java/util/logging/Level.html): define un conjunto estándar de niveles de _log_ que se usan para controlar la salida y gravedad del mensaje. Los programas pueden ser configurados para emitir trazas para algunos niveles mientras se ignoran las trazas de otros.
* [Filter](javadoc17:java.logging/java/util/logging/Filter.html): proporciona un control fino sobre lo que es emitido, más allá del control proporcionado por los niveles de _log_. La API de _logging_ soporta un mecanismo de propósito general que permite al código de la aplicación asociar filtros arbitrarios para controlar la salida de las trazas..
* [Formatter](javadoc17:java/util/logging/Formatter.html): proporciona soporte para especificar cómo son formateados los objetos _LogRecord_. El paquete de JUL incluye dos formateadores, SimpleFormatter y XMLFormatter, para formatear los registros de log en texto plano o XML respectivamente. Al igual que con los _Handlers_, terceras partes pueden desarrollar _Formatters_ adicionales.

## Motivos para usar JUL

Log4j es una de las mejores sino la mejor librería para trazas en Java, las ventajas son flexibilidad en caso de necesitar funcionalidades más en el futuro siendo raro que se necesite algo en materia de _logging_ que no soporte Log4j de alguna forma. Logj4 permite guardar archivos, rotar los archivos por fecha o volumen de datos guardando cierto número de copias de seguridad, información de contexto entre más funcionalidades avanzadas, cosas adicionales que no tiene JUL. JUL por el contrario es una solución que no requiere incluir una dependencia adicional el proyecto y una solución más sencilla que puede ser más que suficiente para una aplicación o un pequeño _script_.

Depende del contexto del proyecto en sí es más conveniente usar solo JUL u optar por Log4j, no hay una misma respuesta para todos proyectos y a veces no hay ningún motivo claro para decidirse entre una u otra, siendo ambas más que suficientes. En el entorno de [Google Cloud Functions][google-cloud-functions] por ejemplo solo entiende correctamente las trazas emitidas con JUL en el caso de una función con implementación Java. Es utilizar JUL directamente o utilizar Log4j sin que los niveles de las trazas se identifiquen bien en en los paneles de monitorización de Google Cloud Functions o en caso de que Log4j lo tuviese usar un adaptador para Log4j que redirija las trazas sobre JUL. Con la petición [LOG4J2-3282](https://issues.apache.org/jira/browse/LOG4J2-3282) en Log4j a partir de la versión 2.17.2 precisamente proporciona un adaptador que envía las trazas a la consola utilizando JUL.

Incluir una dependencia en una aplicación debe estar justificado, no conviene añadir dependencias que de las que solo se usan una sola clase. Incluir una dependencia añade tamaño a la aplicación teniendo en cuenta que no solo se incluye esa dependencia sino además las transitivas, incluir dependencias de forma indiscriminada en un proyecto hace que pueda ocurrir un conflicto entre las diferentes versiones de dos. También en el caso de que una dependencia está justificada conviene optar por una que tenga pocas dependencias de forma transitiva por los mismos motivos.

## Ejemplo usando las clases de Java para trazas

Este ejemplo muestra un uso básico de las clases de JUL que emite varias trazas usando diferente nivel para cada mensaje, en la salida de la consola se muestra cada uno de los mensajes con el formato por defecto que además del nivel y el mensaje incluye la fecha y hora como metadato adicional en la que ha ido emitido. Como suele ser una convención la instancia de _Logger_ se inicializa como una variable estática privada a partir del nombre de la clase que queda disponible para ser usada en cualquier parte del código de la clase. En este caso la instancia no está en una variable estática para el caso de indicar un argumento hacer la configuración previa.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="main-run-1.sh" language="bash" options="" >}}
{{< code file="Main-run-1.out" language="plain" options="" >}}

El formato del mensaje se puede cambiar estableciendo una propiedad de sistema en el comando de ejecución que inicia la máquina virtual. En el javadoc de la clase _SimpleFormatter_ está detallada la explicación del [formato y símbolos de la expresión para dar formato al mensaje](javadoc17:java.logging/java/util/logging/SimpleFormatter.html#format(java.util.logging.LogRecord)). Cambiando el formato el resultado en la salida del programa cambia.

{{< code file="main-run-2.sh" language="bash" options="" >}}
{{< code file="Main-run-2.out" language="plain" options="" >}}

### Configuración de JUL

Una forma de configurar JUL es a través de un archivo de propiedades, utilizar un archivo de configuración en vez código tiene la ventaja que no es necesario cambiar código en la aplicación para cambiar el comportamiento de JUL y si el archivo de configuración es obtenido de forma externa a la aplicación no es necesario compilar ni generar un nuevo artefacto ejecutable para cambiar la configuración.

El archivo de configuración permite cambiar los _handlers_ a los que se envía las trazas, especificar el nivel de _log_ mínimo aceptado por cada _logger_ y finalmente configurar un _Formatter_ para cambiar el formato por defecto de los mensajes a uno personalizado.

En este ejemplo de archivo de configuración se cambia el nivel de trazas de los _logger_ que están en la jerarquía de nombres _io.github.picodotdev.blogbitix.javalogging_ en base al nombre del paquete haciendo que solo acepten las trazas de nivel _fine_ o superior, por defecto en el solo se aceptan las de nivel _information_. En el archivo de configuración es también posible indicar el formato de los mensajes. Con la configuración personalizada la salida del mismo programa es diferente.

{{< code file="jul.properties" language="plain" options="" >}}
{{< code file="main-run-3.sh" language="bash" options="" >}}
{{< code file="Main-run-3.out" language="plain" options="" >}}

### Soportar JUL y Log4j como implementación

A veces por flexibilidad interesa poder cambiar de implementación de _framework_ de _logging_, añadir esta flexibilidad implica añadir un intermediario entre la aplicación y la implementación, en Java la librería SLF4J es precisamente este intermediario y permite cambiar de implementación a través de configuración sin necesidad de más cambios en el código de la aplicación cuya dependencia directa está en el intermediario y no en la implementación.

Sin embargo, por el mismo motivo de evitar incluir dependencias en un proyecto para casos sencillos es posible desarrollar un intermediario sencillo que haga la función de SLF4J. Esta implementación sencilla incluye algunos conceptos comunes de _logging_ como la entidad _Logger_ y _Level_. La clase _Logger_ en este caso además para emitir las trazas se usa como factoría para obtener las instancias de _Logger_, para poder cambiar de implementación hace uso de un _LogSupplier_ que simplemente es una función que recibe como parámetro una clase y devuelve una instancia de _Logger_. Configurando un _LogSupplier_ u otro se cambia de implementación de _framework_ de _logging_.

Estas son las funciones _lambdas_ que implementan la interfaz _LogSuplier_, una implementación sirve para obtener instancias de _Logger_ de JUL y otra implementación obtiene instancias de _Logger_ de Log4j.

{{< code file="Logger.java" language="java" options="" >}}
{{< code file="LogManager.java" language="java" options="" >}}
{{< code file="JulLogger.java" language="java" options="" >}}
{{< code file="Log4j2Logger.java" language="java" options="" >}}

Al iniciar la aplicación según una variable de entorno, propiedad del sistema o argumento del programa se configura el _LogSupplier_ para usar la implementación deseada.

{{< code file="LogSupplier.java" language="java" options="" >}}

Con la petición de LOG4J2-3282 deja de ser necesario usar JUL directamente y se puede utilizar a través de Log4j pero este es un ejemplo de una implementación sencilla en vez de tener que recurrir al SLF4J para la tarea de tener la flexibilidad de cambiar de implementación entre JUL o Log4j.

{{% sourcecode git="blog-ejemplos/tree/master/" command="" %}}

{{% /post %}}
