---
pid: 383
title: "Ofuscar datos sensibles en las trazas con Log4j"
url: "/2019/02/ofuscar-datos-sensibles-en-las-trazas-con-log4j/"
date: 2019-02-10T10:30:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Los archivos de trazas o _logs_ contienen información de lo que ha realizado la aplicación. Estos registros de información contienen los datos que el desarrollador considera de utilidad en caso de necesitar su consulta. Algunos datos son especialmente sensibles ya que su obtención permiten acceder a cuentas de usuario, obtener datos como tarjetas de crédito o cuentas bancarias, contraseñas o _bearer tokens_ de peticiones HTTP que autorizan el acceso. [Proteger las contraseñas _hasheandolas_ aún con _salt_][blogbitix-75] y cifrar información por motivos seguridad y privacidad es inútil si luego esta información está presente en los archivos de _log_ en texto plano.

[Log4j][log4j] es una de las librerías más utilizadas para añadir la funcionalidad de las trazas en una aplicación Java. Proteger algunos datos sensibles se puede hacer de varias formas. Una de ellas es hacer que sea la aplicación la que se encargue de no emitir estos datos en las trazas u ofuscarla enmascarándola al toda o parte. Para este caso se pueden [utilizar objetos Message][blogbitix-338] que adaptan los objetos de la aplicación a los datos a emitir en las trazas pero requiere modificar en todos los puntos de la aplicación.

En el siguiente ejemplo se hace uso de _lookahead_ como se detalla en la clase [Pattern](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html) de Java para añadir la funcionalidad de que los últimos caracteres queden visibles y la clase _SecuredMessage_ aplica expresiones regulares al mensaje, en caso de encontrar una coincidencia realiza la ofuscación.

{{< code file="Main.java" language="Java" options="" >}}
{{< code file="SecuredMessage.java" language="Java" options="" >}}

Utilizar una clase que implemente la interfaz [Message](https://logging.apache.org/log4j/2.x/log4j-api/apidocs/org/apache/logging/log4j/message/Message.html) para realizar el reemplazo requiere modificar todos los puntos de la aplicación que emitan información sensible, para evitar posibles omisiones este aspecto de la aplicación se puede delegar en Log4j y ser aplicado de forma global.

Con los parámetros de configuración _replace_, _regex_ y _replacement_ el reemplazo los hace la clase _PatterLayout_ utilizando una expresión similar regular que en el caso de _SecuredMessage_.

{{< code file="log4j2.yaml" language="Yaml" options="" >}}

En la salida del ejemplo la primera traza corresponde al uso de la clase _SecurdMessage_ y la segunda al _PatternLayout_.

{{< code file="System.out" language="Plaintext" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/Log4j" command="./gradlew run" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Lookahead en expresiones regulares con Java][blogbitix-385]
{{% /reference %}}

{{% /post %}}
