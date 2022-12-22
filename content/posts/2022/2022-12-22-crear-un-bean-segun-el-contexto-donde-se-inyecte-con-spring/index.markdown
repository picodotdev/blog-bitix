---
pid: 666
type: "post"
title: "Crear un bean según el contexto donde se inyecte con Spring"
url: "/2022/12/crear-un-bean-segun-el-contexto-donde-se-inyecte-con-spring/"
date: 2022-12-22T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:spring.svg"
tags: ["java", "planeta-codigo"]
summary: "Spring permite inyectar un _bean_ construido utilizando información del contexto o clase donde se inyecte. Una caso de uso para esta funcionalidad es eliminar la habitual forma estática de inyectar las referencias a un Logger de una librería de _logging_, la inyección según el contexto permite proporciona el colaborador del Logger no como una variable estática sino en el constructor igual que otros colaboradores. Los beneficios son dos, se elimina una referencia estática que facilita los teses unitarios y se elimina un poco de código repetido en cada clase donde se use un Logger."
---

{{% post %}}

{{< logotype image1="spring.svg" >}}

La opción habitual para inyectar una instancia de Logger con la que emitir trazas es hacerlo mediante un método estático y guardar la referencia en una propiedad estática. Esta es incluso la forma recomendada en el ejemplo de varias de las librerías de Logging. Cada clase donde se quiera añadir Logging requiere añadir el mismo código. Las propiedades estáticas son algo a evitar para evitar algunos problemas como la mayor dificultad de realizar pruebas unitarias.

En el artículo [Opciones de arquitectura para emitir trazas en una aplicación Java][blogbitix-645] mostraba varias opciones de como inyectar el Logger en la clase que quiera emitir trazas. Una la habitual de forma estática mostrada a continuación que es la que se suele mostrar como ejemplo de código en las propias librerías de Logging.

{{< code file="Service-1.java" language="java" options="" >}}

Para evitar una referencia estática del Logger en la clase que dificulta las pruebas unitarias la opción es inyectar el Logger como un colaborador en el constructor.

{{< code file="Service-2.java" language="java" options="" >}}

Una de las propiedades de la inversión de control es que no sean las clases las que creen sus dependencias sino que sea el contenedor el que las inyecte según las propiedades que necesita su constructor. [Spring][spring] y [Spring Boot][spring-boot] proporcionan un contenedor de inversión de control con el que evitar la obtención de la referencia al Logger de forma estática y transformarlo a una inyección en el constructor siempre y cuando la clase en la que inyectar el _Loggger_ esté gestionada por el contenedor de dependencias de Spring, de lo contrario hay que seguir recurriendo a la variable estática o gestionar manualmente la inyección de la dependencia al crear las instancias.

{{< tableofcontents >}}

### Crear un _bean_ según el contexto donde se inyecte con Spring

Las instancias de las dependencias en Spring se crean en los métodos anotados con la anotación [Bean](spring-framework:org/springframework/context/annotation/Bean.html) pero en el caso del Logger el construir la instancia depende del contexto, la clase, donde se inyecte ya que para obtener la referencia del Logger depende de la clase.

El método de creación del _bean_ puede recibir un [InjectionPoint](spring-framework:org/springframework/beans/factory/InjectionPoint.html) a partir del cual conocer cuál es la clase en la que inyecta el Logger y obtener una referencia a este según su clase que lo va a contener.

Como hay múltiples instancias de _loggers_ según se inyecten hay que anotarlo también con la anotación [@Scope("prototype")](spring-framework:org/springframework/context/annotation/Scope.html) para que Spring crea una instancia cada vez que se solicita.

{{< code file="Beans.java" language="java" options="" >}}

Con esta funcionalidad de Spring es posible evitar los Logger estáticos e inyectarlos como un colaborador en el constructor. La funcionalidad de Logging funciona exactamente igual.

{{< code file="Main.java" language="java" options="" >}}
{{< code file="System.out" language="plain" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringInjectionPoint" command="./gradlew run" %}}

{{< reference >}}
* [Logger Injection With Spring’s InjectionPoint](https://dzone.com/articles/logger-injection-with-springs-injectionpoint)
* [Inject loggers in Spring | Java or Kotlin](https://medium.com/simars/inject-loggers-in-spring-java-or-kotlin-87162d02d068)
{{< /reference >}}

{{% /post %}}