---
pid: 731
type: "post"
title: "Análisis estático de código Java con Spotless y Error Prone"
url: "/2026/05/analisis-estatico-de-codigo-java-con-spotless-y-error-prone/"
date: 2026-05-27T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["java"]
summary: "Mantener la consistencia en el código de un equipo no debería depender de la disciplina individual de cada desarrollador. El análisis estático de código automatiza esta tarea, desde verificar que se siguen las convenciones de formateo hasta detectar patrones que pueden convertirse en errores en producción. En este artículo repaso las herramientas disponibles para Java, comparo las opciones más habituales, Checkstyle, PMD y Spotbugs con alternativas como Spotless y Error Prone, y explico cómo integrarlas en el flujo de trabajo con Gradle."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Cada lenguaje de programación define unas convenciones para formatear el código, reglas de espaciado, orden de elementos, longitud de línea, entre otras. Seguirlas garantiza que cualquier desarrollador familiarizado con el lenguaje pueda leer el código sin fricción. A partir de esas convenciones estándar, los equipos pueden acordar ajustes propios, como aumentar la longitud máxima de línea si trabajan con monitores de alta resolución o adoptar alguna convención adicional que mejore la legibilidad en su contexto.

Aunque el formateo pueda parecer un detalle menor, no lo es. Todo editor moderno lo aplica de forma automática con muy poco esfuerzo, por lo que no tenerlo en orden es una señal de que el código no se está manteniendo con el cuidado necesario. Su importancia radica en la legibilidad, dado que los desarrolladores pasamos la mayor parte del tiempo leyendo código, ya sea propio o ajeno, un código bien formateado reduce la carga cognitiva y facilita la comprensión.

Más allá del formateo, existe otro tipo de herramientas orientadas a detectar errores simples pero frecuentes que pueden manifestarse en tiempo de ejecución o que introducen ruido innecesario en el código, _imports_ no utilizados, variables declaradas pero nunca referenciadas, bloques _catch_ vacíos que silencian excepciones, entre otros.

El análisis estático de código consiste en examinar el código fuente tal y como está escrito, sin necesidad de ejecutarlo. Dependiendo de la herramienta, este análisis puede cubrir distintos objetivos, verificar que se siguen las convenciones de estilo, detectar patrones de código propensos a errores, o identificar el uso de librerías con vulnerabilidades de seguridad conocidas. Para Java existen varias opciones que cubren uno o varios de estos ámbitos, y que se pueden combinar para obtener una cobertura más completa.

{{< tableofcontents >}}

## Formateo de código

Las convenciones de formateo más utilizadas en Java son las propias del lenguaje, definidas originalmente por Sun Microsystems, y las de Google, que introduce algunos ajustes sobre las anteriores. Como desarrolladores podemos usarlas como base para crear nuestra propia guía de estilo. Las dos primeras establecen una longitud máxima de línea de entre 80 y 100 caracteres, un límite que tiene su origen en las pantallas de terminal de los años 80 y que hoy resulta restrictivo.

Con la verbosidad característica de Java, nombres de clases largos, genéricas, llamadas encadenadas, y los monitores actuales de alta resolución, una línea de 120 caracteres se queda corta con frecuencia. Por preferencia personal trabajo con 180 caracteres, que permite escribir código más expresivo sin necesidad de romper artificialmente las líneas.

* [Convenciones de Java](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf)
* [Convenciones de Google](https://google.github.io/styleguide/javaguide.html)
* [Convenciones de Palantir](https://github.com/palantir/gradle-baseline/tree/develop/docs)
* [Sun Microsystems cae, la adquiere Oracle][blogbitix-355]

Los formateadores automáticos hacen un buen trabajo en la mayoría de situaciones, pero no siempre producen el resultado más legible cuando el código es complejo o tiene estructuras poco habituales. En esos casos es razonable aplicar un criterio manual. El valor real de estas herramientas no está en la perfección del resultado sino en la consistencia, cuando el equipo comparte las mismas reglas, los comentarios en una _code review_ dejan de centrarse en el formateo y pueden dedicarse íntegramente a la lógica del programa.

## Herramientas de análisis estático de código

Esta es la combinación de herramientas de análisis estático de código que he estado usando hasta ahora para mantener la consistencia en el formateo del código y detectar errores básicos en el código. Estas herramientas se solapan un poco entre ellas, PMD al igual que Checkstyle hace algunas comprobaciones de formateo de código.

* [PMD][pmd]: análisis estático de código con más de 400 reglas.
* [Checkstyle][checkstyle]: comprobación de reglas de formateo.
* [Spotbugs][spotbugs]: para detectar errores de programación que pueden generar errores en tiempo de ejecución.
* [IntelliJ][intellij]: para aplicar las reglas de formateo.

No son las únicas opciones disponibles. Preguntando a Claude otras opciones son Spotless y Error Prone en sustitución o combinación de las anteriores que son las que destacaré en este artículo.

Esta es una tabla comparativa de las herramientas.

| Herramienta | Categoría  | Formatea | Detecta bugs | Plugin Gradle  |
|-------------|------------|----------|--------------|----------------|
| Checkstyle  | Estilo     | No       | No           | Sí             |
| Spotless    | Estilo     | Sí       | No           | Sí             |
| PMD         | Análisis   | No       | Parcial      | Sí             |
| Spotbugs    | Bugs       | No       | Sí           | Sí             |
| Error Prone | Compilador | No       | Sí           | Sí             |

## Spotless

Checkstyle solo detecta errores de formateo pero no formatea el código, no es un gran problema ya que IntelliJ es capaz de formatear todo el código con muy poco esfuerzo, [Spotless][spotless] realiza ambas funciones, detección de errores y formateo de código. El formateo usando la linea de comandos sin necesidad de utilizar el editor.

Un problema de usar Checkstyle es que la configuración de reglas de formateo están duplicadas, hay que tener unas reglas para IntelliJ y otras reglas equivalentes para Checkstyle de modo que aplicando las reglas desde IntelliJ pasen las validaciones de Checkstyle. Spotless tiene la ventaja de poder usar las mismas reglas y formateador para ambos.

* [Exportar desde IntelliJ en formato eclipse](https://www.jetbrains.com/help/idea/configuring-code-style.html#export-code-style)
* [Usar Spotless con la configuración en foramto de eclipse](https://github.com/diffplug/spotless/tree/main/plugin-gradle#intellij-idea)

En caso de que Spotless no formatee algún bloque de código como deseamos es posible desactivar el formateador en ciertas regiones, basta con encapsular el bloque de código que no se desea formatear ente dos comentarios _spotless:off_ y _spotless:on_.

{{< code file="Main-spotless.java" language="java" options="" >}}

## Error Prone

[Error Prone][error-prone] es una analizador estático del código para prevenir errores en tiempo de ejecución. Equivalente a PMD y Spotbugs. Error Prone se define a sí mismo de la siguiente manera.

* Es común que incluso los mejores programadores cometan errores simples. Y a veces, una refactorización que parece segura puede dejar código que nunca hará lo que se espera.
* Estamos acostumbrados a recibir ayuda del compilador, pero este no hace mucho más allá de la comprobación estática de tipos. Al usar Error Prone para complementar el análisis de tipos del compilador, puedes detectar más errores antes de que te hagan perder tiempo o se conviertan en errores en producción.

En la siguiente página están listados los errores que detecta Error Prone.

* [Bug Patterns](https://errorprone.info/bugpatterns)

Error Prone es ejecutado en la compilación del código fuente, los errores detectados se reportan como _error_ o _warning_ como si fuera un mensaje de compilador. La anotación _@SuppressWarnings_ permite suprimir un mensaje error que tenga el código, en este caso el de _SelfAssignment_.

Por ejemplo, hacer una asignación a la misma variable no tiene efecto con lo que posiblemente sea un descuido del programador, Error Prone lo indica como un error a no ser que se suprima la comprobación.

{{< code file="Main.java" language="java" options="" >}}

## Editorconfig

Finalmente, [Editorconfig][editorconfig] permite definir las reglas de formateo de forma independiente del editor como longitud de líneas, usar espacios o tabuladores y tamaño de la indentación para cada tipo de archivo. IntelliJ tiene la opción de exportar las reglas en este formato aunque algunas opciones no sean interpretables por otros editores.

{{< code file=".editorconfig" language="plain" options="" >}}

## Integración en el flujo de trabajo

Spotless y Error Prone tienen un plugin para Gradle lo que permite ejecutar sus reglas al desarrollar y en el entorno de integración continua.

{{< code file="build.gradle.kts" language="kotlin" options="" >}}

Los comandos para ejecutar Spotless y Error Prone son los siguientes.

{{< code file="gradlew-spotlessCheck.sh" language="bash" options="" >}}
{{< code file="gradlew-compileJava.sh" language="bash" options="" >}}

## Recomendación final

Mi recomendación es usar al menos una para analizar el estilo del código y mantener la consistencia y otra herramienta para detectar código que puede producir errores en  tiempo de ejecución. Con esta recomendación es posible usar las siguientes combinaciones:

* PMD, Checkstyle y Spotbugs
* Spotless y Error Prone

Tras mucho tiempo usando la primera opción estoy evaluando la segunda con dos herramientas en vez de tres por el motivo de que cada una se centra en un área específica. Mi preferencia es no incluir las reglas de formateo de código como un _hook_ al realizar un _commit_ ya que añade tiempo para realizar el _commit_ y realiza cambios sin supervisión, aplicar el formateo es simple y rápido desde el editor de código como IntelliJ.

{{% sourcecode git="blog-ejemplos/tree/master/HolaMundoSpotless" command="./gradlew run" %}}

{{% /post %}}
