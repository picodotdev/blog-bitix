---
pid: 556
type: "post"
title: "Las anotaciones de Java y ejemplo de procesador de anotaciones en tiempo de compilación"
url: "/2021/02/las-anotaciones-de-java-y-ejemplo-de-procesador-de-anotaciones-en-tiempo-de-compilacion/"
date: 2021-02-19T17:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
summary: "Las anotaciones añadidas en Java 5 son muy utilizadas por múltiples librerías entre ellas Hibernate, Spring o Immutables. Desde Java 6 se ofrece una API para el procesamiento de las anotaciones en tiempo de compilación que permiten generar archivos de código fuente o emitir mensajes de error. Los procesadores de anotaciones son invocados por el compilador de Java permitiendo extender su comportamiento. En el artículo se muestra una implementación para generar clases que implementan el patrón _Builder_ y otro para realizar comprobaciones."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las anotaciones fueron añadidas en Java 5 como una forma de enriquecer con información el código. Tienen varios casos de uso, algunas están incorporadas en el propio JDK y las utiliza el compilador como [@Override](javadoc11:java.base/java/lang/Override.html), otras son meramente informativas como [@Deprecated](javadoc11:java.base/java/lang/Deprecated.html), también se utilizan en la [generación de documentación Javadoc con _taglets_][blogbitix-261], otras se procesan en tiempo de compilación para generar código o archivos dinámicamente al compilar, otras se procesan en tiempo de ejecución. Entre las [anotaciones predefinidas incorporadas en el JDK](https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html) hay algunas más.

En este artículo muestro cómo crear anotaciones para generar errores de compilación propios, como generar código dinámico en tiempo de compilación e integrarlo con un IDE como [IntelliJ][intellij] y con la herramienta de construcción [Gradle][gradle].

{{< tableofcontents >}}

## Qué es una anotación en Java

Las anotaciones es una metainformación que se añade en el código fuente. Por sí mismas no hacen nada, es al procesarlas cuando se añade su comportamiento, sirve desde para añadir documentación, realizar comprobaciones de compilación, generar código o programar funcionalidades transversales.

Si se crea directamente una instancia de una clase anotada sin procesar las anotaciones esta no incluye el comportamiento que las anotaciones tienen intención de añadir, es el creador de la instancia el que ha de encargarse de añadirles el comportamiento en el momento de instanciarlas, el procesado de las anotaciones se puede hacer en tiempo de compilación o en tiempo de ejecución.

Este es el código básico de una anotación y su uso en una clase, su definición se realiza con la palabra _@interface_, se indica a que elementos del código fuente se pueden aplicar y el nivel de retención.

{{< code file="Builder.java" language="java" options="" >}}
{{< code file="Value.java" language="java" options="" >}}
{{< code file="Foo.java" language="java" options="" >}}

Las anotaciones tienen una sintaxis especial y definen atributos para en el momento de utilización proporcionar valores. Además poseen un [nivel de retención](javadoc11:java.base/java/lang/annotation/RetentionPolicy.html) según el cual la anotación está disponible:

* _Runtime_: la información de las anotaciones quedan disponibles hasta en tiempo de ejecución y accesible mediante reflexión con los métodos de la clase [Class](javadoc11:java.base/java/lang/Class.html).
* _Class_: el compilador emite las anotaciones en tiempo de compilación en los archivos _class_ de _bytecode_ pero no están disponibles en tiempo de ejecución. Puede ser útil para herramientas que procesa los archivos de _bytecode_.
* _Source_: las anotaciones son procesadas y descartadas en tiempo de compilación.

Las anotaciones también definen en que elementos del código fuente se pueden indicar:

* _ElementType.ANNOTATION\_TYPE_ se puede aplicar en otra anotación.
* _ElementType.CONSTRUCTOR_ se puede aplicar en un constructor.
* _ElementType.FIELD_ se puede aplicar en una propiedad.
* _ElementType.LOCAL\_VARIABLE_ se puede aplicar en una variable local.
* _ElementType.METHOD_ se puede aplicar en un método.
* _ElementType.PACKAGE_ se puede aplicar en un paquete.
* _ElementType.PARAMETER_ se puede aplicar en un parámetro.
* _ElementType.TYPE_ se puede aplicar en un tipo.

No es habitual tener que crear un procesador de anotaciones, [Spring][spring] usa de forma extensiva las anotaciones procesándolas en tiempo de ejecución. Otra posibilidad es usar [AspectJ][aspectj] para procesarlas procesarlas después de la compilación a _bytecode_ o [ByteBuddy][bytebuddy] que permite procesarlas en tiempo de compilación o ejecución. Otras librerías que usan anotaciones son las librerías [Immutables][immutables], [Lombok][lombok] e [Hibernate][hibernate].

* [Programación orientada a aspectos con AspectJ, Spring AOP y la clase Proxy][blogbitix-461]
* [Generación de código en tiempo de ejecución con Byte Buddy][blogbitix-184]
* [Formas de reducir el código de las clases POJO de Java][blogbitix-272]

## Procesador de anotaciones

El JDK ofrece una API para el desarrollo de procesadores de anotaciones. Un procesador de anotaciones es una clase que implementa la interfaz [Processor](javadoc11:java.compiler/javax/annotation/processing/Processor.html), normalmente al crear un procesador de anotaciones se extiende de la clase [AbstractProcessor](javadoc11:java.compiler/javax/annotation/processing/AbstractProcessor.html).

Al definir el procesador de anotaciones se indica que anotaciones soporta el procesador y que nivel de código fuente soporta. El compilador de Java al realizar el proceso de compilación invoca a los procesadores de anotaciones proporcionando los elementos de código fuente que los contienen.

El método principal a implementar es el método [process](javadoc11:java.compiler/javax/annotation/processing/Processor.html#process(java.util.Set,javax.annotation.processing.RoundEnvironment)), el procesador ha de recopilar la información que necesite a través de los objetos proporcionados en el método y hacer uso de los servicios proporcionados en la clase [ProcessingEnvironment](javadoc11:java.compiler/javax/annotation/processing/ProcessingEnvironment.html). Para generar archivos de código fuente se utiliza el servicio [Filer](javadoc11:java.compiler/javax/annotation/processing/Filer.html) y para emitir mensajes de error el servicio [Messager](javadoc11:java.compiler/javax/annotation/processing/Messager.html).

Con la infraestructura de servicios de Java se define el procesador de anotaciones creando un archivo de texto en la ubicación _META-INF.services/javax.annotation.processing.Processor_. El archivo contiene una línea por cada procesador de anotaciones de la librería. Los procesadores de anotaciones también se puede especificar de forma explícita con la opción _-processor_ de _javac_.

{{< code file="javax.annotation.processing.Processor" language="plain" options="" >}}

### Generar código fuente

Utilizando el servicio _Filer_ el procesador de anotaciones es capaz de generar nuevos archivos de código fuente. En este ejemplo se muestra como generar una clase que implementa el patrón _Builder_ para la clase _Foo_ anotada con la anotación _@Builder_. El procesador de anotaciones explora los elementos de la clase y con las propiedades que descubre genera el código fuente de la clase y los métodos adecuados de la clase _Builder_. El procesador de anotaciones en este caso emite el resultado mediante un [PrintStream](javadoc11:java.base/java/io/PrintStream.html).

{{< code file="BuilderProcessor.java" language="java" options="" >}}

El resultado del procesador de anotaciones usando Gradle es un archivo de código fuente ubicado en _build/generated/sources/annotationProcessor/java/main_ con el siguiente contenido. Las clases del proyecto pueden hacer referencia a esta clase generada como si existiese en el momento de compilación, los IDE también la detectan como cualquier otra clase.

{{< code file="FooBuilder.java" language="java" options="" >}}

El uso de la clase _builder_ es igual que cualquier otra clase del proyecto.

{{< code file="Main.java" language="java" options="" >}}

### Realizar comprobaciones de compilación

La anotación _@Value_ es una anotación mediante la cual en tiempo de compilación se comprueba que una clase tiene redefinidos en este caso los métodos _equals()_, _hashCode()_ y _toString()_. Es importante [implementar correctamente los métodos _equals()_, _hashCode()_][blogbitix-199] porque son usados por las colecciones, una implementación de estos que no cumple con los contratos de los métodos da lugar a potenciales errores y comportamientos anómalos. En caso de que la clase anotada no tenga redefinidos estos métodos se emite una advertencia de compilación.

Este procesador de anotaciones hace uso del servicio _Messenger_ que posee métodos para emitir mensajes de error, de advertencia o de información. El procesador busca que métodos tiene la clase anotada y si no cumple la validación emite un error.

{{< code file="ValueProcessor.java" language="java" options="" >}}

La clase _Foo_ al estar anotada con la anotación _Foo_ pero no redefinir los métodos _equals_, _hashCode_ y _toString_ heredados de _Object_ el compilador y el procesador de la anotación genera un mensaje de advertencia en la compilación.

{{< code file="System.out" language="plain" options="" >}}

## Procesador de anotaciones en Gradle

Para que Gradle utilice los procesadores de anotaciones definidos en una librería hay que declararlo en la sección de dependencias mediante _annotationProcessor_.

{{< code file="build-annotationprocessor.gradle" language="groovy" options="" >}}

Esta dependencia se instala en el repositorio de [Maven][maven] local haciendo uso del _plugin_ _maven-publish_.

{{< code file="build-javaannotations.gradle" language="groovy" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaAnnotationProcessor" command="./gradlew annotationprocessor:publishToMavenLocal && ./gradlew main:run" %}}

{{% /post %}}
