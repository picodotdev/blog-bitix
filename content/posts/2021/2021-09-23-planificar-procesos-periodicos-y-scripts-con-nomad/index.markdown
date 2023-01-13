---
pid: 599
type: "post"
title: "Planificar procesos periódicos y scripts con Nomad"
url: "/2021/09/planificar-procesos-periodicos-y-scripts-con-nomad/"
date: 2021-09-23T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hashicorp-nomad.svg"
tags: ["gnu-linux", "planeta-codigo"]
series: ["hashicorp"]
summary: "Los pequeños _scripts_ aún con su pequeña función específica, sus pocas líneas de código y breves instantes de ejecución limitados a segundos, minutos u pocas horas al día o a la semana realizan tareas importantes dentro de todas las tares de las que se compone un sistema computacional completo. Al igual que cualquier otro proceso requieren de un entorno de ejecución pero dada su naturaleza breve hace que dedicar una máquina reservada que estará infrautilizada en exclusiva para ellos sea ineficiente además de un coste en la infraestructura. En vez de dedicar una máquina en exclusiva para uno o unos pocos _scripts_ el orquestador de procesos Nomad permite planificarlos en alguna de las instancias de computación existentes del _cluster_ ya sea como procesos del sistema o dentro de contenedores."
---

{{% post %}}

{{< logotype image1="hashicorp-nomad.svg" >}}

Con mucha seguridad surge la necesidad de ejecutar de forma periódica un pequeño proceso o _script_ completamente autónomo con el que no interacciona ningún usuario o administrador pero realiza una función esencial para el funcionamiento de una aplicación u organización. Las funciones o tareas de los _scripts_ son muy diversas desde [generar informes en formato CSV][blogbitix-146] o [generador documentos PDF][blogbitix-430] a enviar correos electrónicos, para ello quizá se conecten a bases de datos relacionales o NoSQL o [realizar llamadas usando REST a APIs de aplicaciones][blogbitix-178] propias o de otras organizaciones, incluso un _script_ con la complejidad suficiente varias cosas de todo esto.

Los lenguajes habitualmente usados para realizar tareas de _scripting_ son dinámicos e interpretados por no requerir ser compilados y ejecutables desde el código fuente. En la plataforma Java una opción como lenguaje de _scripting_ es [Groovy][groovy] y en otros lenguajes son propio intérprete de comandos  [Bash][bash] o [Python][python], aunque [el lenguaje Java con JBang o Gradle es una opción para realizar tareas de _scripting_][blogbitix-108] con las ventajas de ser un lenguaje compilado con la asistencia de código de los entornos integrados de desarrollo o IDE.

Una vez creado un _script_ este ha de ser ejecutado en un sistema, dependiendo de las dependencias como fuentes de datos que tenga el _script_ la ejecución se puede realizar de forma manual en la propia computadora de un usuario o desarrollador. Por el contrario, como cualquier otra aplicación requiere su propio entorno de ejecución en producción, la forma más simple con una expresión _cron_ como un proceso del sistema habiendo aprovisionado previamente el entorno y las dependencias necesarias del mismo. Otra propiedad de muchos _scripts_ es que su tiempo de ejecución no es continuo durando unos pocos minutos u alguna hora realizan su atarea y terminan hasta la siguiente ejecución, al contrario que las aplicaciones con funciones de servidor que están continuamente funcionado por si algún cliente se conecta. En función de las necesidades del _script_ su ejecución se planifica con [una expresión cron cada minuto, hora, día o una vez a la semana][blogbitix-499].

### Ejemplo de planificar procesos o _scripts_ de forma periódica con Nomad

Una de las ventajas de la computación en la nube es poder crear máquinas virtuales cuando se necesitan y destruirlas cuando ya no son necesarias, de forma rápida y bajo demanda. Los contenedores permiten ejecutar procesos en cualquier máquina que soporte para la ejecución de contenedores, esto permite que un proceso no dependa de una máquina física o virtual en concreto.

[Nomad][nomad] de [HashiCorp][hashicorp] es un orquestador de procesos y contenedores, en lo relativo al tema de este artículo permite planificar no solo tareas para mantenerse en ejecución de forma constante e indefinida sino también [planificar una tarea de forma periódica y temporal](https://www.nomadproject.io/docs/job-specification/periodic). Una ventaja de Nomad sobre una opción alternativa como [Kuberntes][kubernetes] es que permite planificar no solo [contenedores de Docker][blogbitix-49] sino también contenedores [Podman][podman] o procesos del sistema a partir de comandos existentes en el mismo. Otra ventaja de Nomad es que es simplemente un orquestador de procesos de fácil instalación y uso al constar de únicamente un binario, se integra con otras herramientas como [Consul][consul] para el registro y descubrimiento y conectividad y [Vault][vault] para seguridad.

* [Arquitectura de referencia de Consul, Vault y Nomad para un centro de datos][blogbitix-508]

Desde luego crear una infraestructura basada en un _cluster_ de servidores Nomad para planificar un _script_ es un esfuerzo innecesario teniendo en cuenta que la opción de una máquina con un _cron_ es más sencillo. Sin embargo, a poco complejo que sea un entorno para prestar un servicio que conste de más de una decena de instancias computacionales, la opción del _cluster_ de Nomad permite utilizar la misma infraestructura para cualquier tipo de carga de trabajo, además siendo una infraestructura con la opción de proporcionar autoservicio permite conseguir algunos de los ideales propuestos de la cultura _DevOps_ y detallados en [The three ways y The five ideals de los libros The DevOps Handbook, The Phoenix Project y The Unicorn Project][blogbitix-593].

En este ejemplo se muestran la definición de dos tareas o _jobs_ para Nomad, una como definida como un proceso del sistema a partir de los comandos disponibles en la máquina en la que se ejecuta y otra tarea como un contenedor de Docker. Ambas tareas son lo más simple posible emitiendo únicamente en la salida estándar un mensaje, en el caso del proceso del sistema con el comando _echo_ y en el caso del contenedor con el mismo comando de una imagen de contenedor con [BusyBox][busybox]. En la definición de las tareas de Nomad se planifica su ejecución con una expresión _cron_ cada un minuto.

{{< code file="echo-busybox.nomad" language="hcl" options="" >}}
{{< code file="echo-bash.nomad" language="hcl" options="" >}}

Nomad y Docker se inician con los siguientes comandos.

{{< code file="nomad.sh" language="bash" options="" >}}
{{< code file="docker.sh" language="bash" options="" >}}

Antes de enviar a Nomad las tareas el panel de administración integrado o _dashboard_ permite ver el estado, está disponible en la dirección _http:\/\/127.0.0.1:4646/ui/_, inicialmente sin ninguna tarea planificada, una vez se envían las tareas aparecen en el panel.

{{< image
    gallery="true"
    image1="image:nomad-dashboard.webp" optionsthumb1="650x450" title1="Dashboard de Nomad"
    caption="Dashboard de Nomad" >}}
{{< image
    gallery="true"
    image1="image:nomad-dashboard-echo-busybox.webp" optionsthumb1="300x250" title1="Dashboard de Nomad"
    image2="image:nomad-dashboard-echo-bash.webp" optionsthumb2="300x250" title2="Dashboard de Nomad"
    caption="Dashboard de Nomad" >}}

Las tareas se envían a Nomad para su planificación con el siguiente comando, otros comandos permiten ver el estado de las tareas y las trazas que ha generado. Nomad planifica una ejecución de cada tarea según la expresión _cron_ indicada en la definición de los _jobs_.

{{< code file="nomad-job-run-echo-busybox.sh" language="bash" options="" >}}
{{< code file="nomad-job-run-echo-bash.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/NomadCron" %}}

{{% /post %}}
