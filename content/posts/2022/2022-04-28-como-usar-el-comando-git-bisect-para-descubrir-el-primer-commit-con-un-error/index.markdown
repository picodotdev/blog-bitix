---
pid: 630
type: "post"
title: "Cómo usar el comando git bisect para descubrir el primer commit con un error"
url: "/2022/04/como-usar-el-comando-git-bisect-para-descubrir-el-primer-commit-con-un-error/"
date: 2022-04-28T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:git.svg"
tags: ["gnu-linux", "planeta-codigo", "programacion"]
summary: "El comando _git bisect_ es muy útil cuando se desea encontrar en que _commit_ se ha introducido un error en un rango de _commits_ ya que permite automatizar la búsqueda sin tener que hacerlo manualmente el desarrollador que simplemente se limita a indicar a Git si el _commit_ a analizar en cada prueba es correcto o incorrecto. Una vez descubierto en que _commit_ se ha introducido el error es cuestión de revisar los cambios de ese _commit_, analizar sus cambios e identificadas las líneas de código erróneas aplicar la solución."
---

{{% post %}}

{{< logotype image1="git.svg" >}}

Una herramienta de control de versiones para el código fuente de las aplicaciones es esencial, para guardar todos los cambios realizados en el código y permitir compartir código entre los desarrolladores aparte de otras funcionalidades proporcionadas. Una de las herramientas de control de versiones más populares es [Git][git], desarrollado por [Linus Torvalds][linus-torvalds] también autor original de núcleo [Linux][linux] entre otros colaboradores.

Una situación habitual al realizar cambios en el código fuente de un programa es pasado un tiempo descubrir un error y no conocer en que cambio lo ha producido y en que _commit_. Si no se tiene una pista de cual es el cambio que ha introducido error una solución es probar cada _commit_ y para ver si esa versión de la aplicación tiene el error o no. Una vez probados varios _commit_ al final se descubre que _commit_ es el que ha introducido el error. Conociendo el _commit_ que introduce el error hay que revisar los cambios de ese _commit_ para conocer y cambiar las líneas de código erróneas.

## El comando _git bisect_

Una de las funcionalidades que proporciona la herramienta de control de versiones Git es el subcomando _bisect_. [El comando _git bisect_](https://git-scm.com/docs/git-bisect) automatiza encontrar que _commit_ ha introducido un error dado un rango de _commits_ en el que se sospecha está el _commit_ con el error.

El comando _git bisect_ aplica un algoritmo de búsqueda al rango de _commits_, en función de la indicación que se le proporcione de si el commit tiene o no el error al final del número de comprobaciones que sean necesarias proporciona el primer _commit_ con el error en el rango analizado.

El comando _git bisect_ no se utiliza tan habitualmente como _git stash_ o _git commit_ pero cuando cuando es necesario es muy útil conocer como se usa y usarlo.

El primer paso del comando _git bisect_ es iniciar la bisección y proporcionar el rango de _commits_ a analizar. Una vez proporcionado el rango de _commits_ _git_ cambia el _commit_ del espacio de trabajo según su algoritmo de búsqueda. El siguiente paso suele consistir en arrancar la aplicación y comprobar si el _commit_ tiene o no el error. Una vez conocido si el _commit_ es correcto o tiene el error se le indica a Git con el comando _git bisect good_ o _git bisect bad_ respectivamente. Git a continuación selecciona otro _commit_ teniendo que comprobar de nuevo si el nuevo _commit_ seleccionado es correcto o incorrecto. Después de varias repeticiones de estos pasos se descubre el _commit_ en el que se introdujo el error.

Una vez terminada la bisección o en cualquier momento se puede dar por terminada con el comando _git bisect reset_ y volver a un espacio de trabajo fuera del _bisect_. El comando _git bisect view_ permite ver el estado de la bisección y los _commits_ analizados.

Estos con los comandos básicos y un ejemplo de uso.

{{< code file="git-bisect-1.sh" language="bash" options="" >}}
{{< code file="git-log.sh" language="bash" options="" >}}
{{< code file="git-bisect-example.sh" language="bash" options="" >}}

{{% /post %}}
