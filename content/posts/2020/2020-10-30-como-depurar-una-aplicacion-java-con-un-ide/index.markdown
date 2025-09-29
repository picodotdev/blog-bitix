---
pid: 526
type: "post"
title: "Cómo depurar una aplicación Java con un IDE"
url: "/2020/10/como-depurar-una-aplicacion-java-con-un-ide/"
date: 2020-10-30T14:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:intellij-debug.webp"
tags: ["java", "planeta-codigo"]
summary: "La depuración de un programa o _debug_ permite ejecutar un programa de forma interactiva, paso a paso y examinar los valores de las variables. Esto proporciona valiosa información que permite comprobar el correcto funcionamiento de un programa o descubrir la causa de un error del que las trazas no proporcionan información suficiente. Los depuradores o _debuggers_ son las aplicaciones que ejecutan el programa en modo depuración, permiten establecer puntos de ruptura, continuar la ejecución paso a paso o hasta el siguiente punto de ruptura e inspeccionar los valores de las variables. Los entornos integrados de desarrollo o IDE de Java como IntelliJ IDEA y eclipse ofrecen soporte para hacer depuración."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Pocos programas están libres de errores, algunos errores se producen cuando se cumplen ciertas condiciones en los datos que maneja una aplicación. En ocasiones descubrir la causa de un error es suficiente si la aplicación emite trazas pero en los errores más complicados o con poca información se requiere depurar el programa.

* [La librería Log4j para emitir trazas en aplicaciones Java][blogbitix-334]
* [Centralizar y consultar las trazas de las aplicaciones con Elasticsearch, Logstash y Kibana][blogbitix-517]

La depuración se emplea en el momento de desarrollo o para reproducir y descubrir en el entorno de desarrollo local un error que se está produciendo en producción. Por la utilidad de la depuración todos los lenguajes ofrecen alguna posibilidad de hacer depuración. En Java la depuración se realiza configurando la máquina virtual con ciertos argumentos y el entorno integrado de desarrollo o IDE para conectarse a la máquina virtual.

{{< tableofcontents >}}

## Qué es la depuración de un programa

La depuración o _debug_ es una forma de ejecución de un programa con la que el programador puede observar de forma interactiva la ejecución línea a línea del código fuente con el objetivo de eliminar algún error. El depurador o _debugger_ es una aplicación que permite añadir puntos de ruptura o _breakpoints_ donde el depurador para la ejecución, inspeccionar los valores de las variables, continuar la ejecución hasta la siguiente línea o hasta el siguiente punto de ruptura, continuar la ejecución entrando en una función o sin entrar en la función.

## Configurar la máquina virtual Java para hacer _debug_

Para depurar un programa Java es necesario configurar la máquina virtual Java o JVM en modo _debug_. Esta configuración de la JVM se realiza añadiendo ciertos argumentos al comando que inicia la aplicación.

Un ejemplo de programa para practicar cómo depurar es el siguiente.

{{< code file="Main.java" language="java" options="" >}}

### Debug con el comando java

Si la aplicación se inicia con el comando [java][java-command] hay que añadir los siguientes argumentos para la máquina virtual. Estos argumentos indican a la máquina virtual que acepte conexiones de red para realizar depuración desde un depurador como un IDE de forma remota o desde la máquina local.

* _-agentlib:jdwp_: habilita el protocolo de depuración (JDWP) en la máquina virtual. Este es el argumento principal que habilita la depuración.
* _transport=dt_socket_: se usa una conexión de red para las conexiones de depuración.
* _server=y_: escucha conexiones de los depuradores. con valor _n_, el proceso intenta conectarse a un depurador en vez de esperar conexiones. El valor _n_ requiere argumentos adicionales.
* _suspend=n_: con el valor _n_ no se espera a una conexión del depurador en el inicio. La aplicación se inicia y ejecuta con normalidad hasta que un depurador se conecta. Con el valor _y_, la aplicación no empieza hasta que un depurador se conecta.
* _address=5005_: indica el puerto en el que la máquina virtual acepta conexiones.

{{< code file="java-debug.sh" language="bash" options="" >}}

### Debug con Gradle

En el momento de desarrollo si se emplea la herramienta de construcción [Gradle][gradle] también es posible arrancar la máquina virtual de la aplicación en modo depuración a la espera de la conexión del depurador. En el primer comando se inicia la depuración en el puerto _5005_ y con el segundo comando utilizando la variable de entorno _GRADLE\_OPTS_ es posible especificar los mismos parámetros de la máquina virtual que al ejecutar la aplicación Java directamente.

{{< code file="gradle-run-1.sh" language="bash" options="" >}}

La configuración del _debug_ también es posible especificarla en el archivo de construcción del proyecto.

{{< code file="build.gradle" language="groovy" options="" >}}
{{< code file="gradle-run-2.sh" language="bash" options="" >}}

## Depurar una aplicación Java con un IDE

El depurador permite añadir puntos de ruptura en cualquiera de las líneas del código fuente del programa para que la ejecución se detenga cuando se llegue a ese punto. Una vez detenida la ejecución el depurador permite examinar los valores de las variables disponibles en el ámbito de detención de la ejecución.

Para continuar la ejecución si se ha detenido en una línea de código con una llamada a una función el depurador ofrece la posibilidad de continuar entrando al código de esa función o saltar a la siguiente línea de código, también se ofrece la posibilidad de continuar la ejecución hasta la siguiente punto de ruptura. Los puntos de ruptura se pueden habilitar o deshabilitar, no siendo necesario eliminarlos para que no tengan efecto.

### Cómo depurar código Java con IntelliJ IDEA

Arrancada la máquina virtual en modo depuración hay que configurar [IntelliJ][intellij] para que se conecte a la máquina virtual ya sea con una configuración de inicio de la aplicación o uniendo el IDE a un proceso local de la máquina virtual en modo depuración desde el menú _Run > Attach to Process_.

{{< image
    gallery="true"
    image2="image:intellij-debug-configurations.webp" optionsthumb2="300x200" title2="Configuración de depuración remota"
    caption="Configuración de depuración remota" >}}

Añadir un punto de ruptura se hace desde el espacio vertical del editor del código fuente al inicio de cada línea, de forma alternativa utilizando la combinación de teclas <kbd>Ctrl+F8</kbd> añade un punto de ruptura en la línea de edición actual. Los puntos de ruptura pueden ser:

* De línea: suspende la ejecución al llegar a esa línea de código.
* De método: suspende la ejecución al entrar o salir del método.
* De variable: suspende la ejecución al leer o escribir en la variable.
* De excepción: suspende la ejecución cuando una excepción es lanzada en este caso no depende de una referencia de código concreta.

La combinación de teclas para continuar la ejecución cuando se ha suspendido por llegar a un punto de ruptura son:

* Sin entrar en la función o _step over_: <kbd>F8</kbd>.
* Entrar a la función o _step into_: <kbd>F7</kbd>.
* Salir de la función o _step out_: <kbd>Shift+F8</kbd>.
* Hasta llegar a la posición del cursor: <kbd>Alt+F9</kbd>.

El programa se detiene en el punto de ruptura, los valores de las variables en el contexto donde se ha detenido la ejecución se pueden inspeccionar, en este caso el valor del _array_ con los argumentos del programa.

{{< image
    gallery="true"
    image1="image:intellij-debug.webp" optionsthumb1="300x200" title1="Depuración de un programa en IntelliJ IDEA"
    caption="Depuración de un programa en IntelliJ IDEA" >}}

### Cómo depurar código Java con eclipse

El entorno de desarrollo integrado [eclipse][eclipse] también ofrece un depurador para aplicaciones Java. Permite iniciar una aplicación en modo depuración, conectarse a una máquina virtual externa configurada con soporte para depuración, establecer puntos de ruptura con la combinación de teclas <kbd>Ctrl+Shift+B</kbd>, continuar la ejecución cuando se ha suspendido e inspeccionar los valores de las variables.

{{< image
    gallery="true"
    image1="image:eclipse-debug-configurations.webp" optionsthumb1="300x200" title1="Configuración de depuración remota"
    caption="Configuración de depuración remota" >}}

La combinación de teclas para continuar la ejecución cuando se ha suspendido por llegar a un punto de ruptura son:

* Sin entrar en la función o _step over_: <kbd>F6</kbd>.
* Entrar a la función o _step into_: <kbd>F5</kbd>.
* Salir de la función o _step out_: <kbd>F7</kbd>.
* Hasta llegar a la posición del cursor: <kbd>Ctrl+R</kbd>.

Los puntos de ruptura se crean con la tecla Ctrl+Shift+B o con el botón derecho desde el espacio vertical del editor del código fuente al inicio de cada línea.

{{< image
    gallery="true"
    image1="image:eclipse-debug.webp" optionsthumb1="300x200" title1="Depuración de un programa en eclipse"
    caption="Depuración de un programa en eclipse" >}}

{{< reference >}}
* [The Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html)
* [IntelliJ IDEA Debug code](https://www.jetbrains.com/help/idea/debugging-code.html)
* [IntelliJ IDEA Attach to process](https://www.jetbrains.com/help/idea/attaching-to-local-process.html)
* [IntelliJ IDEA Run/debug configurations](https://www.jetbrains.com/help/idea/run-debug-configuration.html)
* [IntelliJ IDEA Step through the program](https://www.jetbrains.com/help/idea/stepping-through-the-program.html#smart-step-into)
* [Debugging the Eclipse IDE for Java Developers](https://www.eclipse.org/community/eclipse_newsletter/2017/june/article1.php)
{{< /reference >}}

{{< sourcecode git="blog-ejemplos/tree/master/HolaMundoJava8" command="./gradlew run" >}}

{{% /post %}}
