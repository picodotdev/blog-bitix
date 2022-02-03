---
pid: 620
type: "post"
title: "Validar las propiedades de configuración de Spring Boot al iniciar la aplicación"
url: "/2022/02/validar-las-propiedades-de-configuracion-de-spring-boot-al-iniciar-la-aplicacion/"
date: 2022-02-03T18:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:spring.svg"
tags: ["java", "planeta-codigo"]
summary: "Spring permite realizar validaciones sobre las propiedades de configuración antes de iniciar la aplicación, esto permite evitar errores en tiempo de ejecución más difíciles de depurar ya que la excepción que se origine quizá sea difícil de asociar a que el valor una variable de configuración es incorrecta, por ejemplo, que una variable de configuración no tiene valor y si fuese una contraseña de base de datos originar un fallo de conexión a la base de datos o que un servicio REST devolviese un error 403 por no proporcionar la credencial. Con las mismas anotaciones de validación sobre las propiedades de configuración del paquete _javax.validation_ que se utilizan para validar _beans_ de Java se pueden utilizar para validar los valores de configuración de Spring."
---

{{% post %}}

{{< logotype image1="java.svg" image2="spring.svg" >}}

Al recibir datos los programas tienen varias opciones. Una es ser estricto, validar los datos y en caso de que no sean válidos emitir un mensaje y devolver un error impidiendo continuar. Otra opción es ser flexible para validar los datos y en caso de que sean incorrectos tomar valores por defecto para al menos realizar la tarea aunque sea en un modo degradado. La opción final es no validar los datos y dar por supuesto que son correctos.

No validar los datos tiene varios problemas que se pueden manifestar en la aplicación de diferentes formas, fallando en el procesado de los datos quizá habiendo realizado parte de la tarea, ocasionando algún fallo de seguridad o generando resultados incorrectos o inconsistencias en otros datos. Cualquiera de estas formas de errores son manifestaciones que pueden tener como causa unos datos no válidos de entrada, errores cuya causa raíz pueden no ser fácilmente identificable al intentar depurar el programa y que suelen requerir muchas horas de investigación. Por ello validar los datos es una buena práctica al crear aplicaciones, sobre todo validar datos que provienen de fuentes no confiables como información introducida por el usuario o programas externos.

La configuración de una aplicación es otra forma de datos que un programa utiliza, en este caso para variar su comportamiento en tiempo de ejecución sin necesidad de recompilar la aplicación o generar un nuevo artefacto binario para su ejecución. Sobre las variables de configuración también es posible realizar validaciones y en caso de que las variables de configuración sean incorrectas fallar de forma temprana en vez de fallar más tarde cuando se realiza una acción.

{{< tableofcontents >}}

### Cómo validar propiedades de configuración

La configuración de una aplicación son simplemente variables a la que se les da un nombre y a través del cual se recupera un valor. El programa no tiene hardcodeado en el código el valor e incluyéndose en un archivo de configuración permite cambiarlo sin cambiar el código.

Por ejemplo, si un programa necesita conectarse a una base de datos seguramente necesite unas credenciales compuestas por el _host_ del servidor de base de datos, un usuario y una contraseña de conexión. Estos datos de configuración para el programa son requeridos ya que quizá sin poder conectarse a la base de datos va a fallar. Otros datos quizá deban ser numéricos, no vacíos, deban tener un valor mínimo o máximo o deban cumplir una expresión regular.

El _framework_ Spring proporciona varias funcionalidades útiles para las aplicaciones relacionadas con la configuración desde [un mecanismo muy flexible de proporcionar los valores de las propiedades de diferentes fuentes hasta un servidor de configuración centralizada][blogbitix-613]. Otra de sus funcionalidades es precisamente validar las propiedades de configuración a través de anotaciones.

Aunque no sean validaciones complejas son útiles ya que normalmente las variables las crean los desarrolladores pero al desplegar la aplicación o tener que cambiar algún valor puede que lo realicen personas con el rol de sistemas que quizá no conozcan tan en detalle las variables de configuración existentes y sus reglas de validación. Al añadir las validaciones si alguna falla se obtiene un mensaje de error bastante descriptivo de cual es el problema de configuración.

### Ejemplo de código

Una forma de recuperar los valores de configuración en una aplicación de Spring es trasladando los valores de los archivos de configuración a clases Java que contengan sus valores y sean fácilmente usables desde el lenguaje Java. Es en estas clases Java donde se colocan las anotaciones  de validación.

Las anotaciones que se pueden utilizar son [las anotaciones del paquete javax.validation](https://javaee.github.io/javaee-spec/javadocs/javax/validation/constraints/package-summary.html), realmente son las mismas anotaciones para realizar validaciones sobre cualquier otro tipo de objeto Java, en este caso simplemente son clases con propiedades de configuración.

Este es un ejemplo de archivo de configuración de una aplicación en la que hay una serie de propiedades. Dependiendo del entorno y de los valores proporcionados en tiempo de ejecución a través de este archivo de configuración, propiedades del sistema o variables de entorno las propiedades tendrán unos valores u otros y algunos valores como las contraseñas por motivos de seguridad es preferible no incluirlos en los archivos de configuración del código fuente para no compartirlas en el sistema de control de versiones.

{{< code file="application-2.yaml" language="yaml" options="" >}}

Dado que algunas propiedades es necesario proporcionarle en tiempo de ejecución la aplicación como medida de precaución valida que los valores de las propiedades son válidas. Esta es una clase que tiene el objetivo de cargar y proporcionar acceso a los valores de configuración.

{{< code file="AppConfiguration.java" language="java" options="" >}}

La clase al estar anotada con la anotación [@Configuration](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html) de Spring la identifica como una clase de configuración y con el atributo _prefix_ Spring carga los valores de las variables de configuración cuyos nombres coinciden con los nombres de las propiedades. La clase incluye también la anotación [@Validated](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/annotation/Validated.html) que indica que debe ser validada con las anotaciones de validación que incluya. Finalmente, las propiedades que necesitan validaciones se anotan con la anotación correspondiente para hacer la propiedad requerida u otro tipo de validación sobre su valor.

Realizando validaciones sobre los valores de las propiedades de configuración si la aplicación se inicia y una o varias propiedades no cumplen una validación, la aplicación en vez de continuar finaliza inmediatamente con un mensaje de error que indica que una propiedad no cumple las validaciones.

{{< code file="application-1.yaml" language="yaml" options="" >}}
{{< code file="gradlew-run-1.sh" language="bash" options="" >}}
{{< code file="System.out-1" language="plain" options="" >}}

Cambiando el comando de inicio para proporcionar el valor requerido y que las otras propiedades sean válidas la aplicación se inicia correctamente.

{{< code file="application-2.yaml" language="yaml" options="" >}}
{{< code file="gradlew-run-2.sh" language="bash" options="" >}}
{{< code file="System.out-2" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringBootConfigValidated" command="./gradlew run" %}}

{{% /post %}}
