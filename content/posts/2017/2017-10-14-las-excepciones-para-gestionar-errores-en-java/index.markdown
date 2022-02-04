---
pid: 270
type: "post"
title: "Las excepciones para gestionar errores en Java"
url: "/2017/10/las-excepciones-para-gestionar-errores-en-java/"
aliases: ["/2017/10/las-excepciones-del-lenguaje-java/"]
date: 2017-10-15T11:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

En la ejecución de los programas se pueden producir numerosas condiciones de error, algunas condiciones son un dato con un formato inesperado como una letra cuando se espera un número o fallos en la entrada o salida al trabajar con archivos o comunicación por red si un archivo que se quiere utilizar en realidad no existe en el sistema de archivos o el servidor no está disponible en la comunicación por red, estos son solo unos pocos ejemplo de errores posibles en un programa. Pero hay muchas otras condiciones de error que se puedan producir para las que un programa ha de estar preparado.

En la documentación Javadoc de cada clase y utilizando la asistencia de código de los entornos integrados de desarrollo al usar un método es posible conocer que excepciones _checked_ lanza, el saber que excepciones lanza un método permite añadir el código necesario para tratar cada una de ellas de forma adecuada o lanzarlas para que el método invocante las trate.

### Qué son las excepciones de Java

Las excepciones son un mecanismo para capturar y generar errores en un programa en tiempo de ejecución. Son una de las [formas de gestionar errores][blogbitix-519] como alternativa al retorno de valores especiales, otra forma de gestionar errores alternativa a las excepciones son [gestionar errores con la clase Either][blogbitix-319] de [Vavr][vavr].

En Java las excepciones están incorporadas en el lenguaje desde Java desde la primera versión y que en posteriores se han mejorado. Las palabras reservadas del lenguaje para las excepciones son _try_, _catch_, _finally_ para la captura y _throw_ y _throws_ para lanzar excepciones en los métodos. Las excepciones son uno de [los tipos de sentencias y estructuras de control de flujo básicas de Java][blogbitix-494].

En Java las excepciones son objetos que extienden la clase [Throwable](javadoc11:java.base/lang/Throwable.html) de la que en el JDK es extendida por la clase [Error](javadoc11:java.base/lang/Error.html) y [Exception](javadoc11:java.base/lang/Exception.html) de la que de esta última a su vez hereda [RuntimeException](javadoc11:java.base/lang/RuntimeException.html). Entre las comprobaciones que realiza el compilador está que las excepciones _checked_ lanzadas por un método son capturadas por el código que lo llama. Solo los objetos que hereden de _Throwable_ pueden ser lanzados y capturados en los bloques _try-catch_.

### Tipos de excepciones, _checked_ y _unchecked_

Las excepciones que heredan de _Exception_ se denominan _checked exceptions_, han declararse en los métodos siendo de obligada captura o relanzado para su tratamiento en el método que invoca al que lanza la excepción. Estas son los tipos de excepciones que normalmente crea un programa para gestionar sus propias excepciones. Para crear una nueva excepción basta con crear una nueva clase que extienda de _Exception_, para lanzar una excepción hay que crear una instancia de la clase y lanzarla con la palabra reservada _throws_, en la firma del método ha de declararse que puede lanzar una excepción con la palabra reservada _throws_.

Las excepciones que heredan de _RuntimeException_ también se les conoce como _unchecked exception_, no necesitan declararse en los métodos para ser lanzadas, son empleadas para advertir de errores de programación como dividir por cero produciendo [ArithmeticException](javadoc11:java.base/java/lang/ArithmeticException.html), desreferenciar un puntero nulo produciendo [NullPointerException](javadoc11:java.base/java/lang/NullPointerException.html) o acceder a una posición inválida de un _array_ produciendo [ArrayIndexOutOfBoundsException](javadoc11:java.base/lang/ArrayIndexOutOfBoundsException.html).

Las excepciones que heredan de _Error_ son empleadas para casos en los que se han agotado recursos del sistema como la memoria o condiciones de error en el sistema que generalmente impiden el correcto funcionamiento del programa y de difícil tratamiento salvo terminar la ejecución.

### Las excepciones más comunes de Java

En la colección de clases de la API de Java se incluyen muchas excepciones, en la documentación Javadoc como en el resto de clases incluyen una descripción con la condición de error que indican. Algunas de las excepciones más comunes de Java son:

* [NullPointerException](javadoc11:java.base/java/lang/NullPointerException.html): se lanza cuando se intenta usar una referencia nula en el uso de un objeto. Es una de las excepciones más comunes y es debido a un error en la programación del programa.
* [ArrayIndexOutOfBounds](javadoc11:java.base/java/lang/ArrayIndexOutOfBoundsException.html): se lanza al acceder a una posición ilegal de un _array_ al ser el índice negativo, mayor o igual que el tamaño del _array_.
* [ClassCastException](javadoc11:java.base/java/lang/ClassCastException.html): se lanza cuando se hace una operación de _cast_ a un tipo de objeto de la que no es una instancia.
* [IllegalArgumentException](javadoc11:java.base/java/lang/IllegalArgumentException.html): se lanza para indicar que el valor de un argumento es inválido, se suele utilizar para comprobar una precondición al inicio del método.
* [IOException](javadoc11:java.base/java/io/IOException.html): indica algún tipo de error en una operación de entrada/salida. El error de entrada/salida es posible al trabajar con archivos o con operaciones de red.
* [FileNotFoundException](javadoc11:java.base/java/io/FileNotFoundException.html): es un tipo de error de entrada/salida que indica que al abrir el archivo este no existe en el sistema de archivos.
* [ClassNotFoundException](javadoc11:java.base/java/lang/ClassNotFoundException.html): se lanza cuando se intenta cargar una clase pero esta no existe usando _forName_, _findSystemClass_ o _loadClass_. Su causa suele ser porque una librería no se ha proporcionado en el _classpath_ al iniciar el programa o la aplicación requiere otra versión de alguna librería.
* [NoClassDefFoundError](javadoc11:java.base/java/lang/NoClassDefFoundError.html): es similar a _ClassNotFoundException_ pero se produce cuando la clase existe en tiempo de compilación pero no en tiempo de ejecución.
* [NoSuchMethodException](javadoc11:java.base/java/lang/NoSuchMethodException.html): se lanza cuando un método no se encuentra. Es posible cuando una nueva versión de una librería ha eliminado un método o ha cambiado su firma con un cambio no compatible con versiones anteriores.
* [SQLException](javadoc11:java.sql/java/sql/SQLException.html): se produce en el acceso a bases de datos relacionales, por ejemplo cuando la sentencia SQL no tiene una sintaxis correcta.
* [StackOverflowError](javadoc11:java.base/java/lang/StackOverflowError.html): se produce cuando se hacen demasiadas llamadas recursivas a un método.
* [InterruptedException](javadoc11:java.base/java/lang/InterruptedException.html): se produce cuando un _thread_ es interrumpido. Esto ocurre al trabajar en Java con hilos y operaciones de concurrencia.

### Ejemplo de código usando excepciones

Este sería un ejemplo de código que hace uso de una excepción propia para detectar una condición de error, una excepción es _checked_ y otra _unchecked_.

{{< code file="Account.java" language="java" options="" >}}
{{< code file="InvalidAmountException.java" language="java" options="" >}}
{{< code file="InvalidOperationException.java" language="java" options="" >}}
{{< code file="jshell.out" language="plain" options="" >}}

Aunque las excepciones son un buen mecanismo para el tratamiento de errores se les critica que rompen el flujo de ejecución de un programa y tienen un coste en rendimiento, aunque esta penalización de rendimiento en la mayoría de programas es irrelevante. Algunas recomendaciones que se hace para usar de forma efectiva las excepciones son:

* Las excepciones no deben reemplazar comprobaciones simples con sentencias _if_.
* No se deben microgestionar las excepciones. Los bloques _try-catch_ deben contener bloques de código de varias líneas de código en vez una única sentencia por cada bloque _try-catch_.
* Se debe hacer un buen uso de la jerarquía de excepciones y capturar la excepción que se vaya a tratar en el bloque _catch_. Una excepción se puede convertir en otra por ejemplo convertir un _NumberFormatException_ a _IOException_.
* No se deben capturar excepciones para no hacer nada con ellas, esto es no debe haber bloques _catch_ vacíos.
* En algunos casos es mejor lanzar una excepción que un valor _null_ que posiblemente produzca un _NullPointerException_ en otra parte distante del código de dónde se devolvió el valor _null_.
* Propagar excepciones no es un signo de poca sabiduría, puede haber motivos para ello sobre todo si no se le puede dar un tratamiento adecuado.

Cuando se produce una excepción la clase _Throwable_ posee métodos para emitir en la salida un informe de la pila de llamadas, también se puede personalizar la salida. Esta información es esencial y muy útil para conocer la causa de un error ya que indica entre otras cosas cada uno de los métodos, línea en el código fuente y clases donde se ha producido la excepción.

Hay una cierta [controversia sobre las excepciones _checked_ y _unchecked_][blogbitix-313] ya que mejoran el control de errores pero pueden generar otros problemas. Ambas opciones tienen sus lados positivos y otros negativos. En Java se optó por implementar el mecanismo de excepciones donde las _checked_ es obligatorio tratarlas o lanzarlas a un nivel superior de la pila de llamadas.

El siguiente [tutorial sobre el manejo de las excepciones en Java](http://tutorials.jenkov.com/java-exception-handling/exception-hierarchies.html) las comenta desde el punto de vista teórico de como se usan y mejores prácticas al usarlas.

{{% /post %}}
