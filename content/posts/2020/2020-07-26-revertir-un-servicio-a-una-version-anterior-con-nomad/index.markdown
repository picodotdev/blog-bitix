---
pid: 503
type: "post"
title: "Revertir un servicio a una versión anterior con Nomad"
url: "/2020/07/revertir-un-servicio-a-una-version-anterior-con-nomad/"
aliases: ["/2020/07/revertir-a-una-version-anterior-de-un-servicio-con-nomad/"]
date: 2020-07-26T09:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:nomad-versions.png"
tags: ["gnu-linux", "planeta-codigo"]
series: ["hashicorp"]
summary: "Los errores no se planifican, se producen de forma inesperada. Además, un error en un entorno de producción es normalmente urgente e importante lo que obliga a cambiar las prioridades del equipo dejando lo que están haciendo y ocuparse de resolver el problema. En ocasiones no será posible resolver el problema y la única solución es revertir la versión de la aplicación a la anterior. Dependiendo de la automatización de los procesos incluso el volver a la versión anterior quizá sea complicado. Los errores no se planifican pero si se puede planificar estar preparado para algunos errores, una forma de estar preparado ante errores es tener un proceso y herramientas para volver a la versión anterior rápido y fácilmente."
---

{{% post %}}

{{< logotype image1="hashicorp-nomad.svg" image2="hashicorp.svg" >}}

En el desarrollo de una aplicación se dedica un considerable esfuerzo a tener la mayor seguridad que los cambios no introducen errores, se realizan numerosas pruebas unitarias, pruebas de integración, si es una aplicación web pruebas funcionales simulando la interacción de un usuario. Todas estas pruebas se ejecutan además del equipo de cada desarrollador en un entorno de integración continua o CI en cada _commit_ al repositorio de control de versiones del código fuente, el equipo de desarrollo recibe una notificación con inmediatez en caso de fallar alguna prueba. Aún se hacen mas pruebas algunas manuales en un entorno de _staging_ que tiene una configuración similar al de producción. Finalmente, se despliega la versión validada en producción todavía haciendo más comprobaciones con estrategias de despliegue _blue/green_ y _canary_, si no se ha descubierto ningún error se promociona la versión en todas las instancias.

Y aún con todo estos procesos de pruebas en ocasiones se descubren errores en producción. Si la causa del error se descubre rápido y es fácil de solucionar se opta por corregirlo, crear una nueva versión y desplegarla. Si el error es leve y no afecta a una funcionalidad importante se puede esperar a la siguiente versión para corregirlo. Pero algunos errores impactan en una funcionalidad vital para el negocio, tanto que obligan al equipo a dejar las tareas planificadas que están haciendo aún siendo también importantes para descubrir la causa del error y corregirlo, a veces la causa y la corrección se realiza rápido. En otras ocasiones la causa es difícil de determinar o la corrección es compleja, en estas ocasiones la opción adecuada es volver a la versión anterior buena conocida haciendo un _revert_ o _rollback_. Se pierden las otras nuevas funcionalidades incorporadas en la misma versión pero se evita hacer cambios que introducen deuda técnica por la presión de dar solución rápido al error y se evita al equipo trabajar en un desagradable bajo presión.

En la operación de sistemas lo ideal es que todas las tareas estén automatizadas para evitar errores al realizar operaciones manuales y para evitar complejos procesos que impidan a cualquier miembro de equipo realizar la tarea por falta de conocimiento, complejidad o requerir formación. Dos de estas tareas a automatizar son el despliegue de una aplicación con una nueva versión y también el revertir a una versión anterior en caso de detectar que la nueva versión tiene algún tipo de error.

[Nomad es un orquestador de servicios][blogbitix-398] que en gran medida automatiza el despliegue de de las aplicaciones, su actualización a nuevas versiones y revertir a versiones anteriores.

Se inicia con el siguiente comando.

{{< code file="nomad-start.sh" language="bash" options="" >}}

Para ejecutar un servicio basta con enviar su definición en su archivo de configuración con un comando.

{{< code file="nomad-run-1.sh" language="bash" options="" >}}

La definición del servicio en [Nomad][nomad] incluye la versión del artecfacto a desplegar. En este caso el servicio es simplemente un comando que emite una traza en la consola, a efectos didácticos no es relevante ya que sería lo mismo si fuera el comando de inicio de la aplicación Java que incluya la versión del _jar_ o si el contenedor Docker estuviese versionado.

{{< code file="service-1.nomad" language="bash" options="" >}}

El servicio emite en la salida el mensaje.

{{< code file="service-1-logs.sh" language="bash" options="" >}}

Al hacer cambios en el código de un servicio con nuevas características, correcciones de errores o mejoras de las funcionalidades existentes hay que generar una nueva versión del artefacto a desplegar, en el caso de una aplicación Java con [Spring Boot][spring-boot] puede ser un nuevo archivo _jar_ con un nuevo nombre que incluye su versión, el artefacto de despligue es un contenedor Docker este también estará versionado. En la definición del servicio para Nomad hay que actualizar la referencia del artefacto a la nueva versión. El comando para realizar el despliegue es el mismo, basta con enviar la nueva definición del servicio a Nomad.

{{< code file="service-2.nomad" language="bash" options="" >}}

Como una nueva versión implica cambios en el código o la configuración del servicio es posible introducir nuevos errores que aún con todos los procesos de pruebas en diferentes entornos comentados sean descubiertos solo en el entorno de producción como es el típico [NullPointerException](javadoc11:java.base/java/lang/NullPointerException.html) o en este caso un mensaje de traza erróneo.

{{< code file="service-2-logs.sh" language="bash" options="" >}}

Para la tarea de volver a una versión anterior el proceso con Nomad es igualmente sencillo, Nomad recuerda las definiciones anteriores enviadas con lo que solo se necesita utilizar [el comando para restaurar la definición del servicio](https://www.nomadproject.io/docs/commands/job/revert.html). Con los artefactos de despliegue versionados ya sea con contenedores Docker o con versiones en archivos _jar_ las definiciones de los servicios contienen la referencia de los artefactos que utilizan.

{{< code file="nomad-revert.sh" language="bash" options="" >}}

El volver a la versión anterior puede hacerse desde la consola gráfica de administración de Nomad o desde la interfaz de línea de comandos introduciendo la definición anterior del servicio. Nomad se encarga de actualizar a la versión anterior de todas las instancias de forma progresiva utilizando [estrategias de despliegue _blue/green_ y _canary_ con Nomad][blogbitix-399] que haya del servicio de forma automatizada y el servicio vuelve a la normalidad.

{{< code file="service-3-logs.sh" language="bash" options="" >}}

Desde la consola web de administración también se ven las definiciones de los jobs y sus versiones con sus diferencias.

{{< image
    gallery="true"
    image1="image:nomad-service-1.png" optionsthumb1="200x150" title1="Versión 1 del servicio"
    image2="image:nomad-service-2.png" optionsthumb2="200x150" title2="Versión 2 del servicio"
    image3="image:nomad-versions.png" optionsthumb3="200x150" title3="Versiones del servicio"
    caption="Versiones del servicio" >}}

{{% /post %}}
