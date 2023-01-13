---
pid: 661
type: "post"
title: "Programas para crear máquinas virtuales y virtualizar sistemas"
url: "/2022/11/programas-para-crear-maquinas-virtuales-y-virtualizar-sistemas/"
date: 2022-11-17T20:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:cajas-1.webp"
tags: ["gnu-linux", "planeta-codigo"]
summary: "La virtualización es la herramienta con la que probar un sistema operativo sin tener que utilizar una máquina física. Con la cantidad de núcleos de los sistemas actuales y la cantidad de memoria RAM que tienen, la virtualización es una opción disponible para los usuarios. Una alternativa a la virtualización son los contenedores pero estos son más para servicios sin interfaz gráfica. Hay aplicaciones que permiten tener una máquina virtual de Windows en un sistema operativo GNU/Linux o probar una distribución de GNU/Linux en un sistema Windows antes de migrar definitivamente a GNU/Linux."
---

{{% post %}}

{{< logotype image1="linux.svg" >}}

La virtualización permite crear un sistema que está instalado como software dentro de otro sistema físico. La virtualización es muy útil para probar sistemas operativos sin necesidad de instalarlo en la máquina física ni riesgo de hacer pruebas que rompan el sistema instalado, por ejemplo para probar la distribución [Ubuntu][ubuntu] que es una de las recomendadas como primera distribución para los nuevos usuarios de [GNU][gnu]/[Linux][linux].

La virtualización permite sistemas aislados sin necesitar una máquina física, es posible virtualizar un sistema [Windows][windows] en GNU/Linux, virtualizar un sistema GNU/Linux en un sistema Windows o otros sistema GNU/Linux en un sistema GNU/Linux. Dadas las grandes cantidades de RAM que tienen las computadoras actuales incluso las de los usuarios es posible instalar un sistema virtual dentro de un sistema físico.

* [Elegir una distribución GNU/Linux según el usuario, uso o equipo][blogbitix-190]
* [Descargar e instalar la distribución Ubuntu de GNU/Linux paso a paso desde cero][blogbitix-232]

La virtualización requiere de un sistema anfitrión con suficiente potencia de cómputo y memoria RAM, pero incluso los procesadores de consumo que actualmente ya tienen más de cuatro núcleos y muchos más de 16 GB de memoria RAM son suficientemente capaces de virtualizar un sistema, con más núcleos y memoria RAM los sistemas virtualizados pueden ser más numerosos.

Como el sistema virtualizado es algo lógico en el momento de su creación es posible definir sus características como núcleos de CPU que tiene, cantidad de memoria RAM y cantidad de almacenamiento. Es posible crear tantas máquinas como se deseen y tener tantas arrancadas al mismo tiempo como el sistema anfitrión tenga capacidad de ejecutar con buen rendimiento.

Un sistema virtualizado tiene una pequeña pérdida de rendimiento respecto a un sistema físico, los procesadores actuales incluyen soporte e instrucciones para que la pérdida del rendimiento sea mínima. Algunas características en la virtualización como la aceleración gráfica tiene mayores penalizaciones de rendimiento o directamente no es posible. Por ejemplo, jugar a juegos en un sistema virtualizado no ofrece buen rendimiento más teniendo en cuenta que los juegos son de los programas más exigentes en capacidad de cómputo de tarjeta gráfica y de procesador.

Para virtualizar un sistemas es necesario un programa, [VirtualBox][virtualbox] está disponible para Windows, [macOS][macos] y GNU/Linux y en GNU/Linux el sistema de virtualización nativo es KVM, el sistema de virtualización nativo es de Windows es Hyper-V.

La virtualización requiere virtualizar un sistema operativo completo que impone una pequeña penalización de rendimiento y unos tiempos de arranque elevados. Para solventar estos problemas los contenedores y una herramienta como [Docker][docker] permite ofrecer servicios con menor penalización de rendimiento y tiempos de arranque mínimos, los contenedores no virtualizan un sistema operativo completo pero son procesos aislado del resto del sistema.

* [Introducción y características de Docker][blogbitix-49]
* [Cómo instalar y guía de inicio básica de Docker][blogbitix-50]

{{< tableofcontents >}}

### VirtualBox

Dado que VirtualBox está disponible para los tres sistemas operativos que usan la mayoría de usuarios y es gratuito es una opción muy utilizada como programa para virtualizar. Tiene una interfaz gráfica de usuario con la que configurar muchas de las opciones de los sistemas operativos como cantidad de RAM y capacidad de almacenamiento.

{{< image
    gallery="true"
    image1="image:virtualbox-1.webp" optionsthumb1="200x150" title1="VirtualBox"
    image2="image:virtualbox-2.webp" optionsthumb2="200x150" title2="VirtualBox"
    image3="image:virtualbox-3.webp" optionsthumb3="200x150" title3="VirtualBox"
    caption="VirtualBox" >}}

La interfaz gráfica permite crear máquinas virtuales manualmente. Un programa [Vagrant][vagrant] permite automatizar la creación y aprovisionamiento de las máquinas virtuales, en base a una descripción de la máquina a crear con un archivo de configuración.

* [Crear de forma sencilla y rápida máquinas virtuales de VirtualBox con Vagrant][blogbitix-403]
* [Cómo instalar y probar macOS con VirtualBox en Windows o GNU/Linux][blogbitix-181]

{{< code file="Vagrantfile" language="ruby" options="" >}}

### Cajas de GNOME

La aplicación integrada de Cajas de [el entorno de escritorio GNOME][blogbitix-660] permite crear máquinas virtuales usando la virtualización nativa de GNU/Linux. Ofrece una interfaz gráfica no tan completa en opciones como VirtualBox pero si las opciones mínimas como memoria RAM, CPU, EFI, almacenamiento y aceleración gráfica. En caso de no usar GNOME o se desean más opciones de configuración [virt-manager][virt-manager] ofrece una mucha más completa interfaz y opciones de configuración para KVM.

{{< image
    gallery="true"
    image1="image:cajas-1.webp" optionsthumb1="200x150" title1="Cajas de GNOME"
    image2="image:cajas-2.webp" optionsthumb2="200x150" title2="Cajas de GNOME"
    image3="image:cajas-3.webp" optionsthumb3="200x150" title3="Cajas de GNOME"
    caption="Cajas de GNOME" >}}

Con el comando _virt-install_ es posible también automatizar la creación de máquinas virtuales. El siguiente comando permite configurar la ubicación del sistema de almacenamiento de la máquina virtual además de la RAM y definir el sistema como un sistema con arranque EFI.

{{< code file="virt-install-bridge.sh" language="bash" options="" >}}
{{< code file="virt-install.sh" language="bash" options="" >}}

### Otras opciones

Las anteriores son opciones de software libre sin coste de licencia, [VMware Workstation Player](https://www.vmware.com/products/workstation-player.html) y [Parallels](https://www.parallels.com/es/) son opciones comerciales para Windows y macOS.

{{% /post %}}