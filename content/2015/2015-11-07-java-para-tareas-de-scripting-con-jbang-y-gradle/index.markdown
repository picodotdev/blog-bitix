---
pid: 108
type: "post"
title: "Java para tareas de scripting con JBang y Gradle"
url: "/2015/11/java-para-tareas-de-scripting-con-jbang-y-gradle/"
aliases: ["/2015/11/java-para-tareas-de-scripting/"]
date: 2015-11-07T15:00:00+01:00
updated: 2021-09-20T20:00:00+02:00
index: true
rss: true
sharing: true
comments: true
language: "es"
imagePost: "logotype:jbang.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "Para programar pequeños _scripts_ normalmente se suele emplear el intérprete de comandos en GNU/Linux bash o si es algo complejo un lenguaje interpretado como Python, Ruby o Groovy. Pero no pienses que Java no puede ser empleado para tareas de _scripting_, en este artículo muestro que problemas presentan los lenguajes interpretados o dinámicos, que ventajas tiene usar Java y finalmente como usarlo con la misma sencillez que un lenguaje interpretado para el nicho funcional de los _scripts_."
---

{{% post %}}

{{< logotype image="jbang.svg" image2="java.svg" >}}

Java puede emplearse para cualquier propósito desde aplicaciones web en la parte servidora, aplicaciones de escritorio como escribí en dos [artículos introductorios sobre JavaFX][blogbitix-100], juegos con alta calidad gráfica incluso para dispositivos de capacidades más reducidas como <abbr title="Internet of Things">IoT</abbr> o embebidos y verdaderamente limitados. Para cualquier plataforma en la que haya disponible una <abbr title="Java Virtual Machine">JVM</abbr> se pueden ejecutar aplicaciones programadas con el lenguaje Java. Sin embargo, hay multitud de otros lenguajes aunque solo una docena con un porcentaje de uso significativo que tratan de ocupar nichos de funcionalidades específicas. Unos pueden ser el análisis de datos como con el [lenguaje R][r] y otro la programación de pequeñas tareas, los _scripts_ o lenguajes de _scripting_.

Para los _scripts_ normalmente se han utilizado intérpretes como [Bash][bash] por su disponibilidad en cualquier sistema GNU/Linux o si se necesita un lenguaje más avanzado [Python][python], [Ruby][ruby] o [Groovy][groovy]. Cualquiera de estas opciones son empleadas para realizar tareas de _scripting_ que involucran desde generación de informes o archivos, envío de correos electrónicos hasta actualización de una base de datos relacional o nosql, cualquier cosa que se desee automatizar. Al no necesitar compilarse ni generar un artefacto extraño como los archivos _.class_ o _.jar_ de Java basta con escribir el código fuente del _script_ con cualquier editor de texto y ejecutarlo con el intérprete correspondiente directamente desde el código fuente sin una compilación previa.

El despliegue en un entorno de pruebas o producción de un _script_ es sencillo, basta con copiar el código fuente a la máquina correspondiente donde se vaya a ejecutar y ejecutarlos, únicamente es necesario instalar la versión del intérprete adecuado y las dependencias adicionales del _script_ si necesita alguna como en Python posiblemente en un [virtualenv](https://virtualenv.readthedocs.io/en/latest/). En entornos más avanzados es posible [realizar la planificación de las tareas de _scripting_ con Nomad y Docker][blogbitix-599].

{{< tableofcontents >}}

### Java para tareas de _scripting_

Pero al contrario de lo que piensa mucha gente Java puede ser usado perfectamente como lenguaje de _scripting_ con igual simpleza o más que Python, Ruby o Groovy. Java es más verboso sí pero en mi opinión estas son [10 razones para seguir usando Java][blogbitix-81] entre ellas el compilador, en el artículo [Java for everything](https://www.teamten.com/lawrence/writings/java-for-everything.html) dan una buena extensa descripción de porque usar Java también para los _scripts_ y donde se expone una forma de usarlo, en este artículo mostraré otras dos creo que mejores.

El compilador de Java validará al menos que el código no tienen errores léxicos o de sintaxis que en un lenguaje interpretado son fáciles de introducir cuando hace meses que no modificas el _script_ olvidando gran parte del código, cuando no has escrito tú el _script_, cuando hay prisa y presión para hacer las modificaciones por un error en producción apremiante, cuando el tamaño del código empieza a ser considerable, es modificado por varias personas o cuando no se domina el lenguaje profundamente. ¿Qué prefieres, algo más verbosidad (ahora menos con varias de las [novedades introducidas en Java 8][blogbitix-17]) o evitar que un _script_ importante en producción se quede a medio ejecutar por un error de compilación al interpretarlo y provoque alguna catástrofe? Yo lo tengo claro, que el compilador me salve el culo.

En cuanto al número de líneas necesarias para hacer un _script_ en Java puede que se necesiten alguna más por su menor azúcar sintáctico o la falta de algún método de utilidad no disponible en la propia <abbr title="Application Programming Interface">API</abbr> del <abbr title="Java Development Kit">JDK</abbr> ajustado a la necesidad pero si el _script_ es pequeño ¿realmente importan unas pocas lineas más? y si el _script_ es grande el compilador y el <abbr title="Integrated Development Environment">IDE</abbr> serán de gran ayuda.

Entrando a discutir el apartado de la simplicidad de ejecución de un lenguaje interpretado, en Java se puede conseguir lo mismo con [JBang][jbang] y algo similar con [Gradle][gradle] suficiente para muchos casos. Estas herramientas encargan de descargar la dependencias que necesite la aplicación y junto con un _wrapper_ no es necesario tener nada más instalado que la máquina virtual de Java. Si el _script_ tiene algún error de compilación el compilador lo indicará y no se ejecutará. [La herramienta SDKMAN permite instalar software de la plataforma Java][blogbitix-489] entre este software está tanto el JDK de Java como JBang y Gradle incluso varias versiones diferentes al mismo tiempo.

Los ejemplos en cada caso consisten en tres pequeños _scripts_ programados en Java con una dependencia de una librería. Son lo más sencillos posible emitiendo en la consola simplemente un mensaje pero podrían hacer cualquier cosa desde [realizar peticiones a una API REST con Retrofit][blogbitix-569] a [generar archivos Excel o CSV con Apache POI y OpenCSV][blogbitix-146] por mencionar dos cosas habituales que pueden realizan los _scripts_.

#### _Scripting_ en Java con JBang

JBang es una utilidad que proporciona una experiencia de uso igual que un _script_ de Python o Groovy. Un _script_ de Java con JBang no requiere ninguna estructura de directorios y es posible ejecutarlo desde el código fuente. SDKMAN permite instalar JBang de forma sencilla con el siguiente comando.

{{< code file="sdkman-install-jbang.sh" language="bash" options="" >}}

Si el _script_ tiene dependencias sobre librerías hay que añadir un comentario que empiece por _//DEPS_ seguido por el grupo, artefacto y versión de la dependencia, al ejecutar el _script_ JBang la descarga y añade al _classpath_.

En [GNU][gnu]/[Linux][linux] los intérpretes de comandos como [Bash][bash] permiten ejecutar todo archivo que tiene el permiso de ejecución establecido, en el caso de archivos de texto el programa encargado de interpretar el código fuente se especificar en la secuencia de caracteres [_shebang_][wikipedia-shebang]. El _shebang_ habitualmente se especifica de la siguiente forma _#!/usr/bin/env groovy_, para hacer compatible secuencia de caracteres del _shebang_ con el código fuente Java hay que usar la forma que se muestra en el código fuente del _script_ de ejemplo. Esta directivas especificadas como comentarios en el código fuente de Java son interpretadas por JBang antes de ejecutar el _script_.

{{< code file="Script1-jbang.java" language="java" options="" >}}
{{< code file="Script1-run.sh" language="bash" options="" >}}

El resultado del _script_ es simplemente una cadena en la salida estándar de la terminal.

{{< code file="Script1.out" language="plaintext" options="" >}}

En Java la tarea de edición de archivos de código fuente se realiza con un entorno integrado de desarrollo o IDE ya sea [IntelliJ IDEA][intellij], [eclipse][eclipse] o [NetBeans][netbeans]. Un IDE proporciona asistencia de código y muestra errores de compilación según se escribe, esto permite escribir código Java de una forma bastante rápida aún con la verbosidad de Java.

Los _scripts_ con JBang no tienen una estructura de proyecto que los IDEs entiendan, sin embargo JBang posee un comando para generar a partir del _script_ un proyecto temporal para editar el código fuente desde un IDE. Este es el comando para editar el _script_ desde IntelliJ IDEA instalado como una aplicación de [Flatpak][flatpak] o con el editor [Visual Studio Code][microsoft-visual-studio-code].

{{< code file="Script1-intellij.sh" language="bash" options="" >}}

Otras opciones que permite JBang es especificar en el código fuente la versión de Java necesaria para ejecutar el _script_, también permite crear un lanzador _wrapper_ y usar JBang sin que este esté instalado previamente, el _wrapper_ lo descarga e instala si no lo está incluso descarga e instala un JDK si no está instalado previamente.

Para proporciona la misma experiencia de usuario que un _script_ interpretado desde el código fuente aparte de descargar las dependencias indicadas se encarga de compilar y crear un archivo _jar_ que se pueda ejecutar con el JDK. Para no repetir el proceso de creación del _jar_ en cada ejecución lo guarda en una caché ubicada en el directorio _~/.jbang/cache_. Al hacer modificaciones y ejecutar un _script_ en la caché se un _jar_ por cada versión, un comando permite purgar los archivos de esta caché.

En caso necesario también permite exportar el _script_ a un archivo _jar_ o instalar el _script_ como un comando del sistema.

{{< code file="jbang-1.sh" language="bash" options="" >}}

#### _Scripting_ en Java con Gradle

Gradle requiere un archivo _build.gradle_ y cierta estructura de directorios pero no es algo suficiente complejo como para descartar Java como lenguaje para este propósito más teniendo en cuenta sus ventajas. Gradle es una solución algo más complicada que JBang pero a cambio no tiene la limitación de dependencias entre clases de diferente paquetes y adicionalmente ofrece poder tener teses unitarios ejecutables de forma sencilla.

Al igual que JBang a parte de la JVM el proyecto de _scripts_ será autocontenido incluso para las dependencias con lo que su despliegue en un entorno de producción será muy sencillo basta con copiar archivos (<abbr title="File Transfer Protocol">FTP</abbr>, _wget_, ...) o hacer `git clone` y `git pull` directamente del repositorio de código fuente para actualizarlo, si se usan los _scripts_ lanzadores después de actualizar el código fuente del proyecto será necesario reconstruirlo con `./gradlew build`.

El _plugin_ _application_ de Gradle solo permite definir un único programa con su _main_ pero creando tareas de tipo [JavaExec](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) es posible tener cuantos diferentes _scripts_ se deseen.

Para los 3 pequeños _scripts_ de ejemplo el archivo _build.gradle_ necesario es el siguiente definiendo de forma dinámica una tarea _runScript_ que permite lanzar el _script_ con el lanzador _wrapper_ de Gradle y usar Gradle sin que este esté instalado previamente descargándolo e instalándolo si no lo está previamente. Si no se desea usar el _wrapper_ de Gradle como forma de ejecutar los _scripts_ las tareas _createStartScripts_ crean varios archivos de Bash que lanzan los programas de Java estableciendo de forma adecuada los argumentos de Java para añadir al _classpath_ las dependencias.

{{< code file="build.gradle" language="groovy" options="" >}}
{{< code file="Script1-gradle.java" language="java" options="" >}}

La tarea de Gradle _createStartScripts_ genera todos los lanzadores de Bash para los _scripts_.

{{< code file="gradle-build.sh" language="bash" options="" >}}

La ejecución de cada uno de los _scripts_ con _gradlew_ y usando los _scripts_ lanzadores es la siguiente:

{{< code file="gradle-run.sh" language="bash" options="" >}}

{{< asciinema id="29509" caption="Ejecución de <i>scripts</i> en Java" >}}

{{< sourcecode git="blog-ejemplos/tree/master/JavaScripts" command="./Script1.java, ./gradlew runScript1" >}}

{{< reference >}}
* [Java for everything](http://www.teamten.com/lawrence/writings/java-for-everything.html)
* [Herramienta de construccion Gradle][elblogdepicodev-98]
* [Usar Gradle mediante Gradle Wrapper][elblogdepicodev-100]
* [Aplicacion Java autocontenida con Spring Boot][blogbitix-103]
{{< /reference >}}

{{% /post %}}
