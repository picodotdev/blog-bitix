---
pid: 616
type: "post"
title: "Analizar y detectar fallos de seguridad en las dependencias de Java"
url: "/2022/01/analizar-y-detectar-fallos-de-seguridad-en-las-dependencias-de-java/"
aliases: ["/2021/01/analizar-y-detectar-fallos-de-seguridad-en-las-dependencias-de-java/"]
date: 2022-01-06T11:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:log4shell.png"
tags: ["java", "planeta-codigo", "seguridad"]
summary: "Dada la complejidad de muchas aplicaciones de software hace que estas usen gran cantidad de dependencias, muchas de proyectos de software libre o código abierto. Es muy probable que con el paso del tiempo en alguna de las dependencias de las aplicaciones se descubra un error de seguridad importante y requiera una actualización lo más pronto posible. No es posible estar completamente a salvo de estar afectado por un fallo de seguridad en el software por mucho que se intente, por este motivo la mejor estrategia es detectar proactivamente y temprana los errores de seguridad y actualizar las dependencias a nuevas versiones con el fallo corregido. Varias organizaciones identifican, definen, describen y catalogan los fallos de seguridad de forma pública en una base de datos y hay herramientas automatizadas que con las bases de datos de fallos de seguridad permiten analizar las dependencias de un proyecto. Un ejemplo ha sido el caso de la librería Log4j 2 que por su gravedad y y popularidad muchas organizaciones han estado afectadas."

---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una vez desarrollada una aplicación esta entra en un modo de mantenimiento en el que se añaden nuevas funcionalidades a las existentes y se corrigen errores. La mayor parte de la vida de una aplicación es empleada en su mantenimiento con generalmente pequeños cambios incrementales. Algunas aplicaciones son empleadas durante periodos de tiempo muy largos, de lustros o décadas, que quizá ya se consideren como heredadas y en las que ya únicamente se hacen cambios en caso de errores graves.

Aunque en una aplicación heredada ya no se hagan mejoras, ni se actualicen versiones mayores de librerías otro de los motivos por los que una aplicación requiere mantenimiento es por fallos de seguridad. Con el paso del tiempo es muy posible que en una aplicación que tenga dependencias de versiones antiguas de librerías se descubran fallos de seguridad. Si es posible y dependiendo de la gravedad del fallo de seguridad descubierto y la forma de explotarlo es conveniente actualizar a la última versión de la librería o al menos a la última versión compatible con el fallo de seguridad corregido. En una aplicación heredada quizá no sea posible actualizar a la última versión ya que posiblemente por un lado requiere cambios importantes en el código y tiempo para hacerlos y por otro lado se trate de evitar hacer cambios para no introducir errores en el código que está funcionando.

Una de las formas de analizar el código fuente de una aplicación es analizar sus dependencias para conocer si en alguna de ellas se descubre alguna vulnerabilidad. Hay herramientas automatizadas que realizan las dependencias y generan un informe con las vulnerabilidades que tienen. En este caso el análisis estático de código se hace sobre las dependencias sobre el código fuente al igual que las comprobaciones que también se pueden hacer sobre el código para comprobar que cumple las convenciones, algunas restricciones y algunos fallos detectables sobre el código fuente que se pueden hacer con [PMD][pmd].

* [Análisis estático de código con PMD y un ejemplo][blogbitix-297]

{{< tableofcontents >}}

### Base de datos de fallos de seguridad

Los fallos de seguridad descubiertos se identifican, definen y catalogan con un nombre y se añaden a una [base de datos pública de vulnerabilidades de seguridad][cve]. Al definir los fallos de seguridad se les asigna un nivel orientativo de gravedad, dos propiedades importantes que sirven para asignar la gravedad son como es la forma de explotar el fallo de seguridad, si requiere acceso físico al sistema o es posible explotarlo de forma remota, y que permite el fallo de seguridad, como ejecución de código remoto o obtención de información confidencial.

Aunque algunos fallos de seguridad permiten la ejecución remota de código no se consideran tan importantes si requieren acceso físico al sistema. Los más graves son aquellos que concurren ambas circunstancias, permiten explotar los fallos de seguridad de forma remota y permite realizar acciones graves como ejecución de código remoto, escalar privilegios u obtener información confidencial.

Por otro lado, la organización [OWASP][owasp] tiene documentados fallos de seguridad comunes en las aplicaciones y que conviene evitar, por ejemplo, el error de _sql injection_ o _cross site scripting_ que no por ser ya muy conocidos y no complicados de evitar dejan de ser graves si la aplicación no se implementa adecuadamente.

### El problema de seguridad de Log4j 2

Un caso de error grave de seguridad denominado identificado con el nivel máximo en la escala de gravedad es el de la librería [Log4j 2][log4j] en las versiones menores a 2.3.2 (para Java 6), 2.12.4 (para Java 7) y 2.17.1 (para Java 8 y posteriores) que es posible explotarlo de forma remota y permite ejecución remota de código denominado _Log4Shell_. Log4j 2 es una librería de Java muy utilizada en los proyectos por ser una funcionalidad fundamental para cualquier aplicación que sirve para emitir trazas o _logging_.

* [Log4Shell: RCE 0-day exploit found in log4j 2, a popular Java logging package](https://www.lunasec.io/docs/blog/log4j-zero-day/)

Dada la gravedad del error descubierto y el amplio uso de la librería en los proyectos Java muchas organizaciones se han visto afectadas por el error de seguridad. La corrección del error requiere actualizar la versión de la dependencia de Log4j a una que no sea vulnerable al error. El problema es que muchas aplicaciones heredadas actualizar a la última versión no es posible e incluso actualizar a una versión compatible no vulnerable supone gran esfuerzo que requiere actualizar la dependencia en el código fuente, generar el nuevo artefacto, validarlo y hacer su despliegue en el entorno de producción.

Mientras se realiza la corrección conviene observar los registros de trazas, el uso de la CPU, red, memoria, almacenamiento y registros de _log_ ante cualquier comportamiento anómalo para ver si la aplicación está siendo objeto de ataque.

Aún siendo Log4j una librería mantenida por tres personas de forma voluntaria su licencia de código abierto y alta calidad que muchas veces es mayor incluso que las opciones equivalentes comerciales es utilizada por muchas empresas incluso con facturaciones mil millonarias debido a que no necesitan pagar licencias de software para usarla. Sin embargo, no todas las empresas mil millonarias que usan un software que es vital para su negocio apoyan económicamente a esos proyectos de software que usan. Aún así, esos tres voluntarios pocas horas después de hacerse público el error con la ayuda de los interesados han publicado varias versiones de la librería con el fallo original y posteriores descubiertos corregidos.

Esta misma historia ya se repitió en el 2014 con [OpenSSL][openssl] con el denominado [Heartbleed](https://en.wikipedia.org/wiki/Heartbleed) y se volverá a repetir con otro ejemplo en el futuro. Proyectos en los que no solo se fundamenta ya una empresa sino en los que se fundamenta internet cuyos desarrolladores trabajan de forma voluntaria sin apoyo económico.

* [Tech giants, chastened by Heartbleed, finally agree to fund OpenSSL](https://arstechnica.com/information-technology/2014/04/tech-giants-chastened-by-heartbleed-finally-agree-to-fund-openssl/)
* [Of Money, Responsibility, and Pride](https://veridicalsystems.com/blog/of-money-responsibility-and-pride/)

{{< image
    gallery="false"
    image1="image:log4shell.png" optionsthumb1="650x450" title1="Logotipo de Log4Shell"
    caption="Logotipo de Log4Shell" source="https://www.lunasec.io/" >}}

### Analizar y detectar fallos de seguridad en las dependencias de Java con Gradle y Maven

Dado que se volverá a repetir un fallo de seguridad como Log4 2 o _Heartbleed_ y dado que es imposible estar seguro de que una dependencia no se vea afectada en algún momento por un fallo grave de seguridad conviene estar suscrito a los boletines de seguridad y analizar las dependencias, automatizar el análisis de las dependencias es la mejor opción para que la mayor parte del trabajo lo hagan las computadoras en vez de personas y detectar los fallos de seguridad en cuanto sean publicados.

La misma organización OWASP proporciona una herramienta automatizada para comprobar la seguridad de las dependencias de un proyecto. La herramienta se usa como un complemento en las herramientas de construcción [Gradle][gradle] o [Maven][maven] y al ejecutar las tareas que añaden analizan las dependencias y versiones del proyecto y las compara con las bases de datos de errores conocidos. El resultado es un informe con una lista de las vulnerabilidades de cada librería del proyecto si es que tienen alguna. El _plugin_ de OWASP también detecta los fallos de seguridad en las dependencias de forma transitiva a las que se declaren en el archivo de construcción de forma explícita.

En el siguiente ejemplo de proyecto con Gradle se incluye como dependencia una versión de Log4j 2 vulnerable, con el _plugin_ de OWASP para detectar vulnerabilidades y la tarea _dependencyCheckAnalyze_ se identifican los CVE a los que es vulnerable cada una de las dependencias en este caso la de Log4j 2. En cada uno de los CVE y en las referencias asociadas se detalla el fallo de seguridad.

Estos son los CVE que detecta para la versión 2.14.1.

* [CVE-2021-44228](https://www.cve.org/CVERecord?id=CVE-2021-44228)
* [CVE-2021-44832](https://www.cve.org/CVERecord?id=CVE-2021-44832)
* [CVE-2021-45046](https://www.cve.org/CVERecord?id=CVE-2021-45046)
* [CVE-2021-45105](https://www.cve.org/CVERecord?id=CVE-2021-45105)

{{< code file="build-1.gradle" language="groovy" options="" >}}
{{< code file="gradle-dependencycheck.sh" language="bash" options="" >}}
{{< code file="gradle-dependencycheck-1.out" language="plain" options="" >}}

Detectado el fallo de seguridad basta con cambiar la versión de Log4j 2 a la última no vulnerable y el error desaparece del informe.

{{< code file="build-2.gradle" language="groovy" options="" >}}
{{< code file="gradle-dependencycheck-2.out" language="plain" options="" >}}

En un proyecto con Maven el análisis se realiza con el siguiente comando:

{{< code file="mvn.sh" language="java" options="" >}}

#### Analizar repositorios de Git

En una organización con gran cantidad de repositorios de Git un error como este supone analizar cada uno de los proyectos, para automatizar la tarea el siguiente _script_ clona los repositorios a analizar, detecta si es un repositorio Gradle o Maven y ejecuta la tarea de análisis de las dependencias.

{{< code file="init.gradle" language="groovy" options="" >}}
{{< code file="settings.xml" language="xml" options="" >}}
{{< code file="owasp-vulnerabilities-check.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/DependencyCheck" command="./gradlew dependencyCheckAnalyze" %}}

{{< reference >}}
* [Dependency Check Gradle Usage](https://jeremylong.github.io/DependencyCheck/dependency-check-gradle/index.html)
* [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)
* [How to identify vulnerable dependencies in a Maven project](https://nullbeans.com/how-to-identify-vulnerable-dependencies-in-a-maven-project/)
* [How to detect the Log4j vulnerability in your applications](https://www.infoworld.com/article/3644492/how-to-detect-the-log4j-vulnerability-in-your-applications.html)
* [Enabling Dependabot version updates](https://docs.github.com/en/code-security/supply-chain-security/keeping-your-dependencies-updated-automatically/enabling-and-disabling-dependabot-version-updates#enabling-dependabot-version-updates)
* [Jenkins Plugin OWASP Dependency-Check](https://plugins.jenkins.io/dependency-check-jenkins-plugin/)
* [Anchore](https://anchore.com/opensource/)
* [Syft](https://github.com/anchore/syft)
* [Grype](https://github.com/anchore/grype)
{{< /reference >}}

{{% /post %}}
