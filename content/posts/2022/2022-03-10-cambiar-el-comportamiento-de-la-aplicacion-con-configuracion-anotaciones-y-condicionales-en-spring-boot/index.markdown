---
pid: 624
type: "post"
title: "Cambiar el comportamiento de la aplicación con configuración, anotaciones y condicionales en Spring Boot"
url: "/2022/03/cambiar-el-comportamiento-de-la-aplicacion-con-configuracion-anotaciones-y-condicionales-en-spring-boot/"
date: 2022-03-10T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:spring.svg"
tags: ["java", "planeta-codigo"]
summary: "Aparte de un sistema de configuración muy flexible para proporcionar configuración de diferentes fuentes, Spring permite cambiar el comportamiento de la aplicación en base a los valores resueltos de las propiedades de configuración. Mediante configuración y sin realizar cambios en el código fuente el contenedor de inversión de dependencias determina las instancias y sus dependencias que crea."
---

{{% post %}}

{{< logotype image1="java.svg" image2="spring.svg" >}}

Programar sobre interfaces permite crear varias implementaciones diferentes y cambiar de una a otra sin cambiar ninguna de las clases que usan la interfaz. Una implementación puede ser para persistir o recuperar datos de una base de datos relacional como [PostgreSQL][postgresql] o usando la misma interfaz guardar y recuperar los datos en una base de datos NoSQL como [MongoDB][mongodb], otro ejemplo es una interfaz de almacenamiento para guardar el contenido de un archivo en el sistema de archivos local o cambiando la implementación guardar los archivos en un sistema de almacenamiento en al nube.

Aunque los diferentes entornos de la aplicación en que se ejecuta son conocidos sigue siendo útil programar sobre interfaces, quizá en el entorno de producción se usa un almacenamiento en la nube pero en tiempo de desarrollo no es posible y es más fácil usar una implementación para guardar el contenido en el sistema de archivos local utilizando la misma interfaz que representa el sistema de almacenamiento. Con una interfaz la implementación queda encapsulada y cada sistema de almacenamiento tendrá la suya. También es útil para empezar a desarrollar con una implementación sencilla en memoria o en el sistema de archivos local y una vez en el despliegue en un entorno de pruebas o producción crear nuevas implementaciones de las interfaces adecuados para esos entornos.

Con varias implementaciones de las interfaces es necesario seleccionar de alguna forma que implementación en concreto a usar de entre las disponibles. En el entorno local se usará la implementación en memoria o el sistema de archivos local cuando en el entorno de producción se usará el almacenamiento en el sistema de almacenamiento de objetos del proveedor de la nube, en [Amazon es S3][amazon-s3] y en [Google Cloud es Storage][google-cloud-storage]. La selección de la implementación a usar es recomendable realizarla en base a configuración en tiempo de ejecución con la intención de evitar de que solo cambie la configuración y no el código de modo que el mismo artefacto ejecutable sea el mismo en todos los entornos.

El _framework_ [Spring][spring] proporciona un mecanismo muy flexible para proporcionar y sobrescribir la configuración de la aplicación con varias fuentes de propiedades y una prioridad entre ellas del que coger el valor en caso de que en alguna esté presente la propiedad, ya sea en un archivo dentro del artefacto ejecutable, en un archivo externo, mediante propiedades del sistema o variables de entorno entre otras formas.

* [Configuración de una aplicación con Spring Boot y configuración centralizada con Spring Cloud Config][blogbitix-613]

{{< tableofcontents >}}

### Anotaciones y condicionales para crear _beans_

Una de las funcionalidades proporcionadas por Spring es el contenedor de _beans_ que realiza la construcción de las instancias mediante la inyección de dependencias. Mediante la inyección de dependencias la aplicación ya no es responsable de realizar la creación de la instancias sino que esta tarea es delegada al contenedor de _beans_ que es capaz de elegir las implementaciones según diferentes condiciones y valores. A través del los perfiles de inicio de la aplicación, valores de configuración, de anotaciones y mediante un lenguaje de expresiones el contenedor de _beans_ crea las instancias de las implementaciones de las interfaces deseadas según la configuración proporcionada a la aplicación en tiempo de ejecución.

En Spring los _beans_ se definen con las siguientes anotaciones:

* [@Bean](spring-framework:org/springframework/context/annotation/Bean.html): se aplica sobre un método e indica que el método devuelve una instancia del tipo de retorno para que el contenedor la proporcione como dependencia a otra instancia que tenga como dependencia la del tipo devuelto.
* [@Component](spring-framework:org/springframework/stereotype/Component.html): permite definir una clase para que el contenedor cree una instancias de la clase y procese sus anotaciones cuando necesite proporcionar una instancia de la clase  como dependencia.
* [@Controller](spring-framework:org/springframework/stereotype/Controller.html), [@Service](spring-framework:org/springframework/stereotype/Service.html), [@Repository](spring-framework:org/springframework/stereotype/Repository.html): estas anotaciones son especializaciones más semánticas de la anotación _Component_. Tienen el mismo comportamiento que la anotación _Component_.

En los sitios en los que se pueden aplicar las anotaciones anteriores es posible aplicar las siguientes anotaciones para que las instancias de esos _beans_ se añadan al contenedor de forma condicional. Además de las anotaciones condicionales más importantes indicadas en esta lista en el paquete [org.springframework.boot.autoconfigure.condition](spring-framework:org/springframework/boot/autoconfigure/condition/package-summary.html) hay unas cuantas más.

* [@Profile](spring-framework:org/springframework/context/annotation/Profile.html): en caso de que el perfil indicado coincida con alguno de los perfiles de la aplicación el _bean_ se añade al contenedor.
* [@ConditionalOnProperty](spring-boot:org/springframework/boot/autoconfigure/condition/ConditionalOnProperty.html): crea la instancia de forma condicional en base a una propiedad de configuración.
* [@ConditionalOnBean](spring-boot:org/springframework/boot/autoconfigure/condition/ConditionalOnBean.html): crea la instancia de forma condicional en base a la existencia de una instancia de una interfaz.
* [@ConditionalOnClass](spring-boot:org/springframework/boot/autoconfigure/condition/ConditionalOnClass.html): crea la instancia de forma condicional en base a la existencia de una clase.
* [@ConditionalOnMissingBean](spring-boot:org/springframework/boot/autoconfigure/condition/ConditionalOnMissingBean.html): crea la instancia de forma condicional cuando no existe de una instancia de una interfaz.
* [@ConditionalOnExpression](spring-boot:org/springframework/boot/autoconfigure/condition/ConditionalOnExpression.html): mediante un [lenguaje de expresiones](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions) es posible crear condiciones complejas con las que decidir si una instancia de un _bean_ se crea o no.
* [@ConditionalOnResource](spring-boot:org/springframework/boot/autoconfigure/condition/ConditionalOnResource.html): crea una instancia de forma condicional cuando existe un recurso en el _classpath_.
* [@Primary](spring-framework:org/springframework/context/annotation/Primary.html): en caso de que a través de varias anotaciones sea posible construir una instancia con la misma interfaz este método permite eliminar la ambigüedad seleccionado la instancia donde se aplica esta anotación. Solo es posible aplicar la anotación _Primary_ sobre la misma interfaz.

Además de las anteriores anotaciones proporcionadas por Spring es posible crear anotaciones condicionales propias implementando una clase de la interfaz [Condition](spring-framework:org/springframework/context/annotation/Condition.html) y usando la anotación [Conditional](spring-framework:org/springframework/context/annotation/Conditional.html).

{{< code file="Beans.java" language="java" options="" >}}
{{< code file="OnLinuxCondition.java" language="java" options="" >}}
{{< code file="OperatingSystem.java" language="java" options="" >}}

### Ejemplo de _beans_ condicionales de con Spring Boot

En este pequeño ejemplo de aplicación se definen dos implementaciones de una interfaz. En base al valor de una propiedad de configuración proporcionada por configuración y con la posibilidad de cambiar su valor en tiempo de ejecución con una variable de entorno se selecciona la implementación deseada utilizando las anotaciones condicionales.

La interfaz del ejemplo simplemente tiene un método que devuelve un mensaje según el idioma que le corresponde.

{{< code file="Message.java" language="java" options="" >}}

Creando varias implementaciones de la interfaz de ejemplo cada implementación devuelve el mensaje en un idioma diferente.

{{< code file="SpanishMessage.java" language="java" options="" >}}
{{< code file="EnglishMessage.java" language="java" options="" >}}

Cambiando el valor de la propiedad Spring decide que implementación usar e inyectar en las dependencias. De esta forma el comportamiento de la aplicación cambia no cambiando el código sino cambiando la configuración de la aplicación y sin tener que implementar la lógica condicional mediante código propio sino usando las facilidades proporcionadas por Spring.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="application.yml" language="yaml" options="" >}}

En función del valor de la propiedad _app.message.implementation_ proporcionado en la configuración y los argumentos del programa la implementación seleccionado por spring cambia y a su vez el comportamiento del programar sin necesidad de cambiar código.

{{< code file="gradlew-run.sh" language="bash" options="" >}}
{{< code file="gradlew-run.out" language="plain" options="" >}}
{{< code file="gradlew-run-spanish.sh" language="bash" options="" >}}
{{< code file="gradlew-run-spanish.out" language="plain" options="" >}}
{{< code file="gradlew-run-english.sh" language="bash" options="" >}}
{{< code file="gradlew-run-english.out" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringBootConfigConditional" command="./gradlew run" %}}

{{< reference >}}
* [Conditional Beans with Spring Boot](https://reflectoring.io/spring-boot-conditionals/)
{{< /reference >}}

{{% /post %}}
