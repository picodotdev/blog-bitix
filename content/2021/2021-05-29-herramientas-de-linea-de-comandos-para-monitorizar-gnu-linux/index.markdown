---
pid: 578
type: "post"
title: "Herramientas de línea de comandos para monitorizar GNU/Linux"
url: "/2021/05/herramientas-de-linea-de-comandos-para-monitorizar-gnu-linux/"
date: 2021-05-29T18:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:bpytop.png"
imagePost: "image:bpytop.png"
tags: ["gnu-linux", "planeta-codigo"]
summary: "A veces un proceso del sistema consume gran cantidad de procesador, memoria o realiza muchas operaciones de lectura o escritura en el almacenamiento, simplemente se desea obtener o monitorizar cierta información del sistema. Hay varias herramientas en GNU/Linux para monitorizar los procesos del sistema, en almacenamiento, la red y temperatura de componentes, otros comandos permiten obtener información de los principales componentes hardware y software de la computadora."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

En GNU/Linux hay una colección de herramientas de línea de comandos para obtener información y monitorizar el estado del sistema. Estas herramientas permiten dar respuesta a las preguntas ¿qué porcentaje del procesador están usando los procesos?, ¿cuánta memoria están usando los procesos y cuanta queda libre?, ¿cual es el espacio de almacenamiento usado y cuanto espacio de almacenamiento queda libre?, ¿cual es la tasa de transferencia para el disco y red? o ¿que temperatura tiene el procesador?.

Otra información que es posible conocer es cuanta memoria tiene el sistema, cuánto almacenamiento tiene el sistema en total, cual es el modelo de procesador, cuál es el modelo de tarjeta gráfica o cual es el entorno de escritorio.

Las siguientes herramientas permiten monitorizar en tiempo real el estado del sistema y obtener información del mismo, tanto para los procesos, almacenamiento y red.

{{< tableofcontents >}}

### Monitorizar procesos del sistema

Estas herramientas permiten monitorizar los procesos del sistema, principalmente en consumo de procesador y memoria.

#### top

Esta herramienta permite monitorizar los procesos del sistema en tiempo real, ver cuánto porcentaje de procesador se está usando, cuanta memoria se está usando y qué procesos lo están haciendo además de conocer qué cantidad de memoria total tiene el sistema.

Es una herramienta que está entre la colección de herramientas instaladas por defecto en la mayoría de sistema GNU/Linux.

Com la tecla _h_ se accede al panel de configuración en el que es posible personalizar los colores, ver las teclas de acceso para mostrar información y otras opciones de configuración que se mantienen entre diferentes ejecuciones del programa.

{{< code file="top.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:top.png" optionsthumb1="300x200" title1="Comando top" >}}
{{< image
    gallery="true"
    image1="image:top-help.png" optionsthumb1="300x200" title1="Comando top"
    image2="image:top-colors.png" optionsthumb2="300x200" title2="Comando top"
    caption="Comando top" >}}

#### htop

_htop_ es una herramienta con el mismo propósito que _top_ pero un poco más avanzada mostrando algo más de información, permite ver los procesos del sistema, consumo de procesador y memoria que están usando. Permite ver el uso del procesador por núcleo de CPU.

Al igual que _top_ permite ordenar la lista de procesos por uso de procesador, uso de memoria, o tipos de CPU consumidos en orden descendente o en orden ascendente. Ofrece integración con el comando _lsof_.

Con la tecla _F1_ es posible ver las opciones de configuración de la utilidad.
 
{{< code file="htop.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:htop.png" optionsthumb1="300x200" title1="Comando htop"
    image2="image:htop-help.png" optionsthumb2="300x200" title2="Comando htop"
    caption="Comando htop" >}}

#### ps

El comando _ps_ permite obtener información del estado de los procesos del sistema en el instante que se ejecuta. Posee varias opciones para filtrar los procesos que devuelve y su información de estado.

{{< code file="ps.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:ps.png" optionsthumb1="300x200" title1="Comando ps"
    caption="Comando ps" >}}

#### bpytop

[bpytop][bpytop] es una herramienta similar a _top_ y _htop_ que muestra información del sistema en tiempo real pero incluyendo también tráfico de red y almacenamiento además del procesador, memoria, procesos. Algunas estadísticas las muestra en formato gráfica utilizando texto para ver más rápidamente el porcentaje de utilización del recurso.

Con la tecla _M_ se accede al menu del programa donde configurar los diferentes paneles de información que muestra el programa. Además de ofrecer información de varios aspectos esenciales del sistema otra de sus grandes ventajas es que las teclas de acceso rápido para cambiar las opciones están visibles  en la interfaz general con lo que no hace falta recordar las teclas para cada opción.

{{< code file="bpytop.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:bpytop.png" optionsthumb1="300x200" title1="Comando bpytop" >}}
{{< image
    gallery="true"
    image1="image:bpytop-menu.png" optionsthumb1="300x200" title1="Comando bpytop"
    image2="image:bpytop-options.png" optionsthumb2="300x200" title2="Comando bpytop"
    caption="Comando bpytop" >}}

#### free

El comando _free_ permite ver la memoria física del sistema y la cantidad de memoria virtual o _swap_.

{{< code file="free.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:free.png" optionsthumb1="300x200" title1="Comando free"
    caption="Comando free" >}}

### Monitorizar almacenamiento

Las siguientes herramientas permiten monitorizar el almacenamiento del sistema. Permiten ver cuál es la capacidad de todas las unidades conectadas, cuanto espacio de almacenamiento queda libre en cada una de ellas y cual es la tasa de transferencia de almacenamiento que está utilizando el sistema tanto en lectura como en escritura.

#### df

El comando _df_ permite ver información del almacenamiento de las unidades conectadas al sistema en el momento de ejecutar el comando. Permite ver en cada una de ellas su capacidad total y espacio libre restante que le queda, dispositivo hardware, sus particiones y puntos de montaje. Con la opción _-h_ muestra los datos en unidades más comprensibles como KiB, MiB y GiB en vez de en _bytes_.

{{< code file="df.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:df.png" optionsthumb1="300x200" title1="Comando df"
    caption="Comando df" >}}

#### iotop

El comando _iotop_ permite ver en tiempo real la tasa de transferencia de lectura y escritura que están empleando los procesos del sistema. Se puede ordenar los procesos por cantidad de lectura o cantidad de escritura.

Esta herramienta requiere permisos de superusuario en su ejecución.

* [Monitorizar procesos que realizan lectura o escritura al almacenamiento en GNU/Linux][blogbitix-367]

{{< code file="iotop.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:iotop.png" optionsthumb1="300x200" title1="Comando iotop"
    caption="Comando iotop" >}}

#### lsof

El comando _lsof_ permite conocer cuales son los archivos abiertos por los procesos del sistema en el momento de ejecutar el comando.

{{< code file="lsof.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:lsof.png" optionsthumb1="300x200" title1="Comando lsof"
    caption="Comando lsof" >}}

### Monitorizar red

Las siguientes herramientas permite monitorizar la entrada y salida del trafico red.

#### netstat

El comando _netstat_ permite ver cuales son las conexiones de red establecidas por los procesos del sistema y su estado. Con la opción _-c_ monitoriza el tráfico de red en tiempo real o de forma continua.

{{< code file="netstat.sh" language="bash" options="" >}}

#### tcpdump

El comando _tcpdump_ permite capturar el tráfico de red de una interfaz de red, para un puerto de red o para un nombre de _host_ como origen o destino específico. Con la opción _-c_ se limita la captura a un número de paquetes determinado. También es posible guardar la captura a un archivo para analizarlo una vez terminada la captura con la opción _-w_.

{{< code file="tcpdump.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:tcpdump.png" optionsthumb1="300x200" title1="Comando tcpdump"
    caption="Comando tcpdump" >}}

### Otras herramientas de monitorización

#### uptime

El comando _uptime_ permite ver cuánto tiempo lleva en funcionamiento el sistema desde su último apagado o reinicio.

{{< code file="uptime.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:uptime.png" optionsthumb1="300x200" title1="Comando uptime"
    caption="Comando uptime" >}}

#### iostat

El comando _iostat_ muestra información de entrada y salida del sistema agrupando la información por dispositivo de almacenamiento. A diferencia de _iotop_ no muestra cual es el proceso que está realizando la operación de entrada y salida.

{{< code file="iostat.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:iostat.png" optionsthumb1="300x200" title1="Comando iostat"
    caption="Comando iostat" >}}

#### sensors

El comando _sensors_ permite obtener información de la temperatura que incorporan varios de los componente de las computadoras. Este comando muestra la temperatura del procesador para cada uno de sus núcleos, también del dispositivo de almacenamiento NVMe.

{{< code file="sensors.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:sensors.png" optionsthumb1="300x200" title1="Comando sensors"
    caption="Comando sensors" >}}

#### watch

El comando _sensors_ no muestra la temperatura en tiempo real, únicamente en el momento de su ejecución. El comando _watch_ permite ejecutar un comando según el intervalo de tiempo deseado, utilizado con el comando _sensors_ permite dotarle a este de monitorización casi en tiempo real configurando el intervalo de ejecución cada un segundo.

{{< code file="watch.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:watch.png" optionsthumb1="300x200" title1="Comando watch"
    caption="Comando watch" >}}

#### neofetch

El comando _neofetch_ muestra la información básica del sistema en el que se ejecuta. Aunque la información no es muy detallada contiene la descripción del los componentes de hardware y software principales del sistema como procesador, memoria, tarjeta gráfica, _kernel_, distribución de GNU/Linux, entorno de escritorio, número de paquetes instalados y algunas informaciones adicionales menos relevantes.

* [Información básica del sistema y entorno de escritorio desde la terminal de GNU/Linux][blogbitix-439]

{{< code file="neofetch.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:neofetch.png" optionsthumb1="300x200" title1="Comando neofetch"
    caption="Comando neofetch" >}}

#### hwinfo

Aunque el comando _hwinfo_ no es un comando de monitorización permite conocer diversa y detallada información del hardware del sistema. La información incluye datos sobre el modelo de procesador, modelo de placa base, unidad de almacenamiento y tarjeta gráfica. Esta información es útil en caso de necesitar algún paquete ya que algunos son específicos según el hardware del sistema por ejemplo los controladores de la tarjeta gráfica.

{{< code file="hwinfo.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:hwinfo.png" optionsthumb1="300x200" title1="Comando hwinfo"
    image2="image:hwinfo-help.png" optionsthumb2="300x200" title2="Comando hwinfo"
    caption="Comando hwinfo" >}}

{{< reference >}}
* [6 Common Linux Commands for System Monitoring](https://www.terminalbytes.com/common-linux-commands-system-monitoring/)
* [20 Command Line Tools to Monitor Linux Performance](https://www.tecmint.com/command-line-tools-to-monitor-linux-performance/)
* [An introduction to using tcpdump at the Linux command line](https://opensource.com/article/18/10/introduction-tcpdump)
* [How to use the Linux iostat command to check on your storage subsystem](https://www.techrepublic.com/article/how-to-use-the-linux-iostat-command-to-check-on-your-storage-subsystem/)
{{< /reference >}}

{{% /post %}}
