---
pid: 625
type: "post"
title: "Detectar errores y comprobar la sintaxis de los scripts de Bash"
url: "/2022/03/detectar-errores-y-comprobar-la-sintaxis-de-los-scripts-de-bash/"
date: 2022-03-24T22:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:linux.svg"
tags: ["gnu-linux", "planeta-codigo"]
summary: "El lenguaje de _scripting_ Bash permite crear sencillos y no tan sencillos _scripts_ para automatizar tareas del sistema o funciones. Dado que un _script_ de Bash se interpreta no se compila un error no se detecta hasta llegar a la línea con el error que puede ser en la mitad del _script_ dejando la tarea sin finalizar correctamente. Para evitar errores antes de la ejecución del _script_ Bash ofrece varias opciones."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

A pesar de los lenguajes interpretados como [Python][python] o [Groovy][groovy], los _scripts_ de [Bash][bash] siguen siendo muy útiles e incluso más adecuados como herramienta en algunas tareas. Python y Groovy son lenguajes de programación de propósito general capaces de realizar tareas muy complejas al mismo tiempo que su código sigue siendo mantenible y legible. Ambos lenguajes son interpretados por lo que no requieren realizar un paso previo de compilación que hacen fácil tener un _script_ sencillo en poco tiempo pero que suponen un problema al realizar modificaciones por introducir errores de compilación que no se manifiestan hasta llegar a la línea de código errónea.

Bash es un intérprete de comandos que permite ejecutar _scripts_, Bash está orientado al propósito de ejecutar comandos del sistema, obtener sus códigos de retorno, la salida de los comandos, y permitir ciertas expresiones que aunque no llegan a la capacidad de los lenguajes de propósito general permite realizar bucles, condicionales, crear funciones con argumentos y tener variables.

Un _script_ de Bash grande es un problema ya que al ser interpretado también ocurre que hasta no se ejecuta la línea del _script_ se manifieste el error. Si la tarea se queda a medias y no termina correctamente sobretodo cuando hay acciones que realizan modificaciones dejan el sistema en un estado inconsistente que no permiten reejecutar el _script_ una vez corregido su error.

Bash ofrece varias opciones para capturar algunos de los errores en la ejecución de los _scripts_. Algunas de estas opciones las he utilizado en el [_script_ de instalación de Arch Linux][alis] que está basado en Bash. El _script_ tiene un tamaño de unas 3K+ líneas de código es fácil introducir un error, ya que no tengo tiempo para probar y menos justo el caso en el que se produce el error por la cantidad de opciones posibles que permite de configuración.

{{< tableofcontents >}}

## Opciones para detectar errores

Bash permite establecer varias opciones de ejecución. La opción _-e_ permite detener el _script_ en cuanto un comando se ejecuta y devuelve un código de error distinto de cero que como convención indica que el comando se ejecutó con algún error, un valor distinto de cero significa un código de error o código de estado que tiene un significado que indica el motivo. Otra opción, con _-u_ permite detener el _script_ cuando una variable de entorno intenta evaluar su valor y la variable aún no está definida, continuar sin el valor adecuado supone probablemente que el _script_ realiza acciones incorrectas.

Por defecto al ejecutar un _script_ este solo genera como resultado la salida de los comandos que ejecuta, para depurar los _scripts_ o ver que comandó están ejecutando la opción _-x trace_ emite el comando ejecutado que permite ver el valor de los argumentos del comando y ver qué valores han tomado los argumentos de las variables de ese comando.

## Opciones de Bash para comprobar un _script_

Otra forma de probar un _script_ es ejecutar las líneas del _script_ pero sin ejecutar los comandos de esas líneas. La opción _-n_ permite ejecutar un _script_ sin ejecutar los comandos. Esto tiene la parte buena de que los comandos no se ejecutan con las posibles acciones de modificación. Para algunos _scripts_ esto es una forma de probarlos con ciertas garantías de que funciona, para los complejos no es suficiente.

Dado que la opción _-n_ no ejecuta los comandos tampoco se obtienen los valores correctos de modo que la línea de ejecución seguramente no sea la misma en el caso de haber sentencias condicionales, bucles o llamadas a funciones. En realidad, lo ideal para probar un _script_ es establecer la opción _-n_ solo para aquellos comandos que realizan acciones de modificación, los comandos que realizan acciones para obtener datos al no realizar modificaciones y ser inocuos se pueden ejecutar sin nin problema. De este modo, desactivando la ejecución únicamente de los comandos de modificación es posible probar una mayor parte del _script_.

La opción se puede activar en la ejecución del _script_ dentro del _script_ cambiando el valor de la opción para desactivar la ejecución de los comandos con _set -n_ o volver a activar la ejecución de los comandos _set +n_. En este ejemplo el comando que emite el se ejecuta y su salida en la consola pero el comando _ls_ no se ejecuta, ni siquiera sale en las trazas que genera _-x xtrace_.

{{< code file="test.sh" language="bash" options="" >}}
{{< code file="test.out" language="plain" options="" >}}

## Programa para validar la sintaxis de un _script_ de Bash

Las opciones anteriores permiten validar los _scripts_ de Bash en buena parte pero tienen limitaciones y dependiendo del código del _script_ quizá no generen ningún error un _script_ que en realidad tiene errores. Para una validación más completa la utilidad [shellcheck][shellcheck] realiza una validación que es capaz de detectar una mayor cantidad de errores, de forma más precisa y más sencilla. _shellcheck_ realiza comprobaciones adicionales que se basan en entender la sintaxis de Bash y comprobar que el _script_ tiene una sintaxis válida acorde a la definición del lenguaje.

Incluso en la web de _shellcheck_ es posible realizar la validación sin necesidad de instalar el comando en el sistema local. Aún con el mejor esfuerzo que hace _shellcheck_ por detectar errores algunos errores se deben a argumentos, opciones de comandos que son erróneas o argumentos con valores inválidos, _shellcheck_ comprueba que la sintaxis del _script_ sea válido pero sigue habiendo casos que en tiempo de ejecución se manifiestan como errores que en tiempo de validación no es posible detectar.

Para usarlo como comando en el sistema local se usa el siguiente comando para lo que es necesario instalar el correspondiente paquete de la distribución usada, en [Arch Linux][archlinux] el paquete es [shellcheck](https://archlinux.org/packages/community/x86_64/shellcheck/).

{{< code file="shellcheck.sh" language="bash" options="" >}}

{{< reference >}}
* [Bash The Set Builtin](https://www.gnu.org/software/bash/manual/html_node/The-Set-Builtin.html)
* [Check the Syntax of a Bash](https://www.baeldung.com/linux/validate-bash-script)
{{< /reference >}}

{{% /post %}}
