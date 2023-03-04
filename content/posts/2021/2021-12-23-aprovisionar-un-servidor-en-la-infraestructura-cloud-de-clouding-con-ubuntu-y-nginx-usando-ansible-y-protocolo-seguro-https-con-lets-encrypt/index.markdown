---
pid: 614
type: "post"
title: "Aprovisionar un servidor en la infraestructura cloud de Clouding con Ubuntu y Nginx usando Ansible y protocolo seguro HTTPS con Let's Encrypt"
url: "/2021/12/aprovisionar-un-servidor-en-la-infraestructura-cloud-de-clouding-con-ubuntu-y-nginx-usando-ansible-y-protocolo-seguro-https-con-lets-encrypt/"
date: 2021-12-23T09:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: true
imageHead: "image:header.webp"
imagePost: "logotype:clouding.svg"
tags: ["gnu-linux", "planeta-codigo", "patrocinado", "software-libre"]
summary: "La computación _cloud_ permite crear un servidor con unos pocos _clics_, en unos pocos minutos, con la posibilidad de ajustar a las necesidades su capacidad en unidades de cómputo, memoria y almacenamiento y facturado solo por los recursos consumidos por unidad de tiempo y con la flexibilidad de ampliar o reducir la capacidad del servidor _cloud_ en cualquier momento. Si con la computación _cloud_ ya es muy sencillo y rápido disponer de un servidor la mayor dificultad está en aprovisionarlo para que ofrezca el servicio que se desea, para facilitar el aprovisionamiento hay herramientas como Ansible diseñadas con este objetivo y tratar la infraestructura como código. En este artículo muestro como crear y aprovisionar un servidor _cloud_ en la infraestructura _cloud_ de Clouding para un servidor web con Ubuntu y Nginx aprovisionado con Ansible y configurado con el protocolo seguro HTTPS con certificados generados por Let's Encrypt."
---

{{% post %}}

{{< logotype image1="clouding.svg" >}}

Para ofrecer un servicio a través de internet es indispensable un servidor que por motivos de fiabilidad entre otros es recomendable contratar a un proveedor de infraestructura. La tendencia es usar servidores _cloud_ con las ventajas de solicitud de creación de los servidores mediante autoservicio con unos pocos clics, de forma rápida en unos pocos minutos y la posibilidad de ampliarlo, o reducirlo, en potencia cómputo, memoria del sistema o almacenamiento, además de una facturación por uso.

La parte de tener un servidor accesible en internet para proporcionar un servicio ya se consigue en unos pocos clics, no hace falta presupuestarlo, ni comprar hardware y pagar su precio de forma completa por adelantado, ni recibirlo y montarlo, ni alojarlo en infraestructura propia. La parte más difícil de un servidor es aprovisionarlo y configurarlo para el propósito que se desee y para facilitar esta tarea hay herramientas de software específicas.

{{< tableofcontents >}}

## Infraestructura _cloud_ de Clouding

Uno de los proveedores de infraestructura _cloud_ más reconocidos con sede en España es [Clouding][cloudingio], con un servicio de grado empresarial que ofrece un servicio de IaaS con oficinas y centro de datos en Barcelona. Como proveedor de computación _cloud_ ofrece las varias importantes ventajas de este modelo de alojar servicios entre los que están disponer de un servidor en unos pocos clics y en pocos minutos, gran flexibilidad en la selección para configuración del servidor en capacidad de cómputo, memoria y almacenamiento permitiendo ajustar el precio a las necesidad del servicio, flexibilidad para cambiar ya sea ampliar o reducir la configuración del servidor y facturación según uso por unidad de tiempo en horas.

Una de las ventajas determinante y diferenciadora de Clouding sobre otros proveedores es que al tener sede en España ofrece soporte de asistencia en caso de necesidad por personas en España. No es habitual requerir de esta asistencia pero en caso de necesitarla por el impacto que tiene si el servicio es de gran importancia para una empresa o persona si de este depende su facturación, clientes o sus proveedores. La asistencia es una característica determinante para la toma de una decisión al elegir un proveedor para en caso de necesidad restaurar el servicio.

Al crear una cuenta en Clouding ofrecen un cupón de 5 € para probar su servicio gratis, este saldo permite crear una instancia de servidor _cloud_ con un tiempo de funcionamiento ininterrumpido de unos 45 días en la configuración más básica.

### Selección de capacidad de los servidores

Clouding permite seleccionar los recursos de cómputo para los servidores según las necesidades partiendo de la opción más básica de 1/2 unidades de cómputo, 2 GiB por core y 5 GiB de almacenamiento con la opción de elegir Linux o Windows como sistema operativo y dentro de Linux con varias distribuciones y versiones como [Ubuntu][ubuntu], [Centos][centos] o [Debian][debian]. Esta opción más básica que ya es apta para algunos servicios parte de los 3 € al mes, la opción más capaz llega a las 48 unidades de cómputo o _cores_, 192 GiB de memoria y casi 2 TiB de almacenamiento suficiente incluso para las necesidades empresariales más exigentes con un precio de 550 € al mes. Y entre la opción más básica y la más capaz la posibilidad de elegir individualmente cada uno de los tres parámetros principales de configuración como unidades de cómputo, memoria y almacenamiento gracias a la computación _cloud_ y una ventaja de los servidores virtuales privados de la generación anterior que no tenían una configuración tan flexible.

{{< image
    gallery="true"
    image1="image:clouding-seleccion-recursos.webp" optionsthumb1="650x450" title1="Selección de los recursos de cómputo y coste"
    caption="Selección de los recursos de cómputo y coste" >}}

### Infraestructura

Clouding ofrece una infraestructura de alta disponibilidad, con servicios de copias de seguridad para preservar datos y restauración, toma de instantáneas como medida de seguridad y recuperación, archivado de servidores para ahorrar costes, redimensionado de servidores gracias a la computación _cloud_ y configuración de red privada para mayor seguridad. En el panel de administración de los servidores también se ofrecen detalles para la monitorización y observabilidad con los que comprobar el buen estado de funcionamiento del servidor.

{{< image
    gallery="true"
    image1="image:clouding-caracteristicas-1.webp" optionsthumb1="200x150" title1="Características de Clouding"
    image2="image:clouding-caracteristicas-2.webp" optionsthumb2="200x150" title2="Características de Clouding"
    image3="image:clouding-caracteristicas-3.webp" optionsthumb3="200x150" title3="Características de Clouding" >}}
{{< image
    gallery="true"
    image1="image:clouding-caracteristicas-4.webp" optionsthumb1="200x150" title1="Características de Clouding"
    image2="image:clouding-caracteristicas-5.webp" optionsthumb2="200x150" title2="Características de Clouding"
    image3="image:clouding-caracteristicas-6.webp" optionsthumb3="200x150" title3="Características de Clouding"
    caption="Características de Clouding" >}}

Para garantizar un buen servicio utilizan una infraestructura moderna y de alto rendimiento, redundante y tolerante a fallos compuesta por hardware, software, red, imágenes y centros de datos. Almacenamiento con discos SSD NVMe de alta velocidad, RAM con corrección de errores ECC, consola de emergencia y monitorización, red de alto rendimiento baja velocidad con protecciones frente ataques DDOS y con dirección IP pública, imágenes para servidores basados en Linux o Windows, paneles de control y aplicaciones preinstaladas, finalmente sus centros de datos ubicados en España son redundantes y con energia 100% renovable.

{{< image
    gallery="true"
    image1="image:clouding-infraestructura-1.webp" optionsthumb1="200x150" title1="Infraestructura de Clouding"
    image2="image:clouding-infraestructura-2.webp" optionsthumb2="200x150" title2="Infraestructura de Clouding"
    image3="image:clouding-infraestructura-3.webp" optionsthumb3="200x150" title3="Infraestructura de Clouding" >}}
{{< image
    gallery="true"
    image1="image:clouding-infraestructura-4.webp" optionsthumb1="200x150" title1="Infraestructura de Clouding"
    image2="image:clouding-infraestructura-5.webp" optionsthumb2="200x150" title2="Infraestructura de Clouding"
    caption="Infraestructura de Clouding" >}}

Son muchas las empresas que confían en Clouding como proveedor de servicios para sus necesidades tecnológicas. Dado el tamaño de estas empresas es garantía de que el servicio de Clouding está a la altura para tenerlos como clientes.

{{< image
    gallery="true"
    image1="image:clouding-clientes.webp" optionsthumb1="650x450" title1="Clientes de Clouding"
    caption="Clientes de Clouding" >}}

## Crear un servidor _cloud_ en Clouding

Empezar a usar Clouding es sencillo y rápido, necesitando únicamente crear una cuenta en el servicio  y añadir algo de saldo a través de las formas de pago que se ofrecen como pago con tarjeta, domiciliación bancaria o cuenta de PayPal. Dispone de notificaciones de saldo bajo y autorecarga para evitar supervisar el saldo y que un servicio no deje de funcionar por motivos de facturación. Informes con el detalle del coste usado en un periodo de tiempo y en el apartado _Cuenta_ la posibilidad de configurar la muy útil y recomendable medida de seguridad del segundo factor de autenticación.

El primer paso para crear un servidor es crear una llave SSH con la que posteriormente acceder al servidor por línea de comandos, la llave SSH se puede descargar para configurarla en el cliente SSH local. Una vez creada ya es posible crear el servidor donde entre otras se seleccionan las características del mismo como sistema operativo y versión, cantidad de unidades de cómputo, cantidad de memoria y cantidad de almacenamiento, teniendo en cuenta que los tres últimos se pueden ampliar o reducir con posterioridad.

{{< image
    gallery="true"
    image1="image:clouding-virtual-machine-create-1.webp" optionsthumb1="200x150" title1="Selección de características para crear instancia de servidor cloud"
    image2="image:clouding-virtual-machine-create-2.webp" optionsthumb2="200x150" title2="Selección de características para crear instancia de servidor cloud"
    image3="image:clouding-virtual-machine-create-3.webp" optionsthumb3="200x150" title3="Selección de características para crear instancia de servidor cloud"
    caption="" >}}
{{< image
    gallery="true"
    image1="image:clouding-virtual-machine-create-4.webp" optionsthumb1="200x150" title1="Selección de características para crear instancia de servidor cloud"
    caption="Selección de características para crear instancia de servidor cloud" >}}

Después de confirmar las características e iniciar la creación del servidor se inicializa y está disponible al cabo de unos pocos segundos o pocos minutos proporcionado entre sus detalles la dirección IP pública que le ha sido asignada y necesaria para la conexión y la configuración DNS del nombre de dominio en el proveedor externo de registro de dominio o en el panel de DNS de Clouding si se configura como servidor administrativo autorizado para el dominio.

{{< image
    gallery="true"
    image1="image:clouding-virtual-machine-create-progress.webp" optionsthumb1="650x450" title1="Progreso de creación de servidor cloud"
    caption="Progreso de creación de la servidor cloud, estadísticas y claves SSH" >}}
{{< image
    gallery="true"
    image1="image:clouding-virtual-machine-statistics.webp" optionsthumb1="200x150" title1="Estadisticas de estado para monitorización y observabilidad"
    image2="image:clouding-virtual-machine-ssh.webp" optionsthumb2="200x150" title2="Claves SSH para los servidores"
    caption="Progreso de creación de la servidor cloud, estadísticas y claves SSH" >}}

## Conexión desde línea de comandos con SSH

Para la conexión al servidor por línea de comandos se utiliza el protocolo seguro SSH, para ello en GNU/Linux como cliente se usa [OpenSSH][openssh] y en [Windows][windows] una posibilidad es [PuTTY][putty]. La clave privada es una clave privada RSA en formato _pem_ que para usar con OpenSSH si se desea usar una propia primero hay que convertirla a formato _ssh-rsa_ con el siguiente comando y aprovisionar la clave pública en forma _ssh-rsa_ en el servidor. Para usar la llave hay que configurar el archivo _.ssh/config_ con la dirección IP pública del servidor. Como Clouding ya se encarga de tanto generar la llave privada como de aprovisionarla en el servidor la conversión y aprovisionamiento no es imprescindible.

{{< code file="ssh-key-conversion.sh" language="bash" options="" >}}
{{< code file="ssh-config" language="plain" options="" >}}
{{< code file="ssh.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:ssh-login.webp" optionsthumb1="650x450" title1="Conexión SSH a instancia de servidor cloud"
    caption="Conexión SSH a instancia de servidor cloud" >}}

## Cómo aprovisionar un servidor _cloud_

Una vez la conexión al servidor por línea de comandos funciona ya es posible configurar el servidor, no se diferencia en ningún aspecto a configurar un servidor por linea de comandos según el sistema operativo elegido. El aprovisionamiento del servidor y configuración consiste básicamente en la instalación de paquetes, configuración de servicios editando archivos de configuración y reinicio de servicios para que la configuración modificada tome efecto. Los paquetes a instalar dependen del propósito o propósitos del servicio para el servidor puede ser un servidor web con [Nginx][nginx] o [Apache HTTPD][apache-httpd], un servidor de base de datos con [PostgreSQL][postgresql] o [MySQL][mysql], un servidor de documentos personales  con [Nextcloud][nextcloud] o servidor de archivos entre otras muchas otras funcionalidades.

Aunque es posible configurar un servidor introduciendo los comandos uno a uno es tedioso además de una tarea repetitiva en caso de tener que aplicarlos en varios servidores, no queda automatizado ni los comandos están bajo un sistema de control de versiones en el que queden descritos los comandos de configuración. Hay herramientas de software con el objetivo de aprovisionar servidores que permiten realizar el aprovisionamiento de un servidor de forma automatizada con la ventaja de ser más rápido, reproducible y la posibilidad de utilizar un sistema de control de versiones para los _scripts_ de aprovisionamiento con sus propias ventajas como historial de cambios y colaboración entre personas en su edición. Una herramienta de aprovisionamiento muy conocida es [Ansible][ansible], en esencia esta herramienta ejecuta los comandos en el servidor, permite hacerlo de forma remota con únicamente una conexión SSH sin necesidad de instalar software de servidor y la posibilidad de aplicar los cambios a un grupo completo de servidores. La herramienta de aprovisionamiento y configuración Ansible entre dentro del grupo de herramientas para tratar a la [infraestructura como código][wikipedia-iac].

En un primer momento para desarrollar los _scripts_ de aprovisionamiento es posible crear una máquina virtual en local y tratarla como si de un servidor se tratase. [Vagrant][vagrant] permite crear máquinas virtuales de forma automatizada con una de sus posibilidades crear máquinas virtuales en [VirtualBox][virtualbox]. Una vez que el script de aprovisionamiento funciona ya es posible lanzarlo contra el servidor _cloud_.

## Ejemplo de aprovisionamiento de un servidor _cloud_ de Clouding con Ansible

En este ejemplo muestro como aprovisionar un servidor _cloud_ de Clouding de forma automatizada con la herramienta Ansible. El servidor _cloud_ creado en el paso anterior tiene el sistema operativo Ubuntu en la versión LTS 20.04, la funcionalidad del servidor es la de un servidor web con Nginx instalado como paquete de software de Ubuntu por más sencillez para el ejemplo que instalarlo con [Docker][docker], para que el servidor utilice el protocolo HTTPS es necesario un certificado que con el servicio de [Let's Encrypt][letsencrypt] permite obtenerlo de forma rápida y automatizada.

Let's Encrypt ofrece la herramienta [certbot][certbot] y siguiendo sus [instrucciones para Ubuntu](https://certbot.eff.org/instructions?ws=nginx&os=ubuntufocal) permite obtener el certificado que posteriormente hay que usar en la configuración de Nginx al configurar el protocolo HTTPS y para que los navegadores clientes validen correctamente el dominio del servidor. Por motivos de seguridad usar el protocolo HTTPS es un requerimiento indispensable sin embargo es necesario un certificado que identifique el dominio usado en el servidor que en algunos proveedores tiene un coste significativo de unos 100 € o más y se tarda unos días en obtenerlo. Let's Encrypt permite obtener un certificado para el dominio en unos pocos segundos de forma automatizada y completamente gratuito ni ningún coste.

Además, de la configuración básica mostrada en este artículo es posible configurar otras opciones en Nginx para variar su comportamiento, de las que he escrito en [otros artículos con la etiqueta web][blogbitix-tag-web].

### Organización de roles en Ansible

Ansible define unas convenciones para los nombres de archivos  necesarios, estructura de directorios y formato para los archivos. El archivo _hosts_ define el inventario de máquinas a las que Ansible puede conectarse y las credenciales de conexión además de poder definir variables asociadas a las máquinas y definir grupos de máquinas según un rol como servidor web o servidor de base de datos si hay varias instancias con ese rol, tiene un formato INI.

Otra de las convenciones de Ansible y una forma de organizar las tareas de configuración son lo que se denomina como roles que contienen además de las tareas a aplicar a las máquinas que utilicen ese rol de Ansible, los archivos de configuración, plantillas y disparadores o _handlers_ a ejecutar como reacción a la ejecución de alguna tarea. Finalmente, están los _playbooks_ que son las recetas a ejecutar que indican que roles se aplican a una instancia o grupo de instancias del inventario. Dentro de un rol los archivos se organizan en varias carpetas.

* _tasks_: contiene la definición de las tareas que se realizan al aplicar el rol. Permiten asociales etiquetas para filtrar su ejecución, añadirles condiciones de ejecución realizar la acción sobre una lista de elementos. Ansible posee una amplia colección de tareas para cualquier acción desde ejecución de comandos como instalación y actualización de paquetes hasta funcionalidades para trabajar con Docker. Las tareas se definen en archivos en formato YAML con la ventaja a diferencia de _scripts_ de Bash ser independientes de la distribución.
* _handlers_: contiene la definición de tareas que se ejecutan como consecuencia de la ejecución de otras tareas en puntos concretos de las tareas normales del rol.
* _files_: son archivos estáticos que permiten aprovisionarlos en las instancias de las máquinas, pueden ser cualquier tipo de archivo como archivos de configuración de un servicio o recursos estáticos para el caso de un servidor web.
* _templates_: son archivos que al procesarse junto variables en tiempo de ejecución producen como resultado un archivo estático. Aunque el resultado final es un archivo estático este se genera dinámicamente.

Para empezar a aprender está la documentación oficial con [la guía de usuario de Ansible](https://docs.ansible.com/ansible/latest/user_guide/index.html) junto con los conceptos de [introducción a los _playbooks_](https://docs.ansible.com/ansible/latest/user_guide/playbooks_intro.html), [el inventario](https://docs.ansible.com/ansible/latest/user_guide/intro_inventory.html#intro-inventory), [tareas](https://docs.ansible.com/ansible/latest/user_guide/basic_concepts.html#tasks), [roles](https://docs.ansible.com/ansible/latest/user_guide/playbooks_reuse_roles.html) y [los módulos](https://docs.ansible.com/ansible/2.7/modules/list_of_all_modules.html) además de otros capítulos de su amplia [tabla de contenidos](https://docs.ansible.com/ansible/latest/user_guide/index.html#traditional-table-of-contents).

Tengo que decir que no soy un experto en Ansible y es muy posible que parte de la configuración de Ansible mostrada en este artículo sea posible mejorarla para hacerla más reutilizable los roles y sus tareas o estructurarlos algo mejor. Esta es la estructura completa de archivos y directorios del ejemplo.

{{< code file="ansible-role-structure.txt" language="plain" options="" >}}

El contenido del archivo _host_ para el servidor _cloud_ de Clouding es el siguiente, que incluye la dirección IP pública proporcionada al crearlo y visible en los detalles del servidor y la llave privada que Ansible utiliza en su conexión por SSH al servidor y a través de las cuales ejecuta los comandos de forma remota. En el ejemplo se incluyen los datos para la conexión para pruebas en un entorno con [Vagrant][vagrant] en una máquina virtual local y una [Raspberry Pi][raspberrypi], además de la instancia en la infraestructura de Clouding.

{{< code file="hosts" language="plain" options="" >}}

Los roles de Ansible permiten agrupar las tareas por funcionalidad a aplicar a un servidor, unos roles quizá sean comunes para cualquier servidor como como el rol de _picodotdev.system_ que realiza unas configuraciones básica en el sistema como instalar paquetes comunes, crear usuarios y configuración SSH y actualizan los paquetes, Otros roles son los siguientes.

* _picodotdev.system_: configuración común del servidor independiente de su servicio.
* _picodotdev.ufw_: tareas para configurar el cortafuegos UFW para limitar puertos abiertos.
* _picodotdev.nginx_: tareas para configurar una máquina con la función de servidor web Nginx.
* _picodotdev.site_: tareas para configurar un sitio web en el servidor de Nginx.
* _picodotdev.certbot_: tareas para utilizar certbot y generar los certificados de Let's Encrypt para configurar en Nginx.
* _picodotdev.goaccess_: [GoAccess][goaccess] es una herramienta para analizar los logs de Nginx.

El primer paso es comprobar que Ansible puede establecer correctamente la conexión con el servidor para ello hay un módulo que hace la función. Si el comando funciona Ansible es capaz de lanzar el resto de tareas administrativas definidas en los roles a esa instancia del inventario.

{{< code file="ansible/ansible-clouding-ping.sh" language="bash" options="" >}}
{{< code file="ansible-ping.out" language="plain" options="" >}}

Estos son los archivos del rol _picodotdev.system_ y _picodotdev.ufw_ de Ansible.

{{< code file="ansible/picodotdev.system/tasks/main.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.system/tasks/install.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.system/tasks/ssh.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.system/tasks/update.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.system/tasks/users.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.ufw/tasks/main.yml" language="yaml" options="" >}}

### Comandos de ejecución

Para configurar el servidor en el ejemplo la aplicación de los roles están divididas en cuatro _playbooks_ con sus cuatro archivos bash para su ejecución.

* _system-init_: aplica las tareas del rol _picodotdev.system_.
* _system-install_: aplica las tareas del rol _picodotdev.site_.
* _system-update_: aplica las tareas del rol _picodotdev.site_ etiquetadas con _system-update_ que permite filtrar las tareas del rol a ejecutar omitiendo el resto que no tienen esa etiqueta.
* _content-update_: aplica las tareas del rol _picodotdev.site_ etiquetadas con _content-update_.

Estos son la definición de los _playbooks_ y los comandos de ejecución en los que se especifican las etiquetas de las tareas a ejecutar del _playbook_ y roles.

{{< code file="ansible/ansible-clouding-system-init.sh" language="bash" options="" >}}
{{< code file="ansible/ansible-clouding-system-install.sh" language="bash" options="" >}}
{{< code file="ansible/ansible-clouding-system-update.sh" language="bash" options="" >}}
{{< code file="ansible/ansible-clouding-content-update.sh" language="bash" options="" >}}
{{< code file="ansible/site-system-init.yml" language="yaml" options="" >}}
{{< code file="ansible/site-system-install.yml" language="yaml" options="" >}}
{{< code file="ansible/site-system-update.yml" language="yaml" options="" >}}
{{< code file="ansible/site-content-update.yml" language="yaml" options="" >}}

Además en el archivo _ansible-env.conf_ se definen algunos datos como variables de entorno que probablemente podrían definirse también como alternativa en el archivo _hosts_ como variables del inventario.

{{< code file="ansible/ansible-env.conf" language="bash" options="" >}}

### Certificado de seguridad con Let's Encrypt en servidor web Nginx

Para generar el certificado para el servidor web con Let's Encrypt está la herramienta _certbot_ disponible para Nginx y usando Ubuntu a través del paquete en formato de aplicación [snap][snapcraft]. El rol _picodotdev.certbot_ contiene las tareas para realizar la configuración de Nginx una vez este está al menos configurado e iniciado para funcionar con el protocolo HTTP.

Para validar un dominio y comprobar que el propietario del sitio web es el dueño del dominio Let's Encrypt con la ayuda de certbot genera unos archivos en la raíz de documentos del sitio en la ubicación _/.well-known/acme-challenge/_, el proceso de validación de Let's Encrypt accede al sitio web usando el dominio con el protocolo HTTP y si encuentra los archivos que espera valida el dominio y genera los certificados que certbot instala en el directorio _/etc/letsencrypt/live/{{ domain }}/fullchain.pem_.

{{< code file="ansible/picodotdev.certbot/tasks/main.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.certbot/tasks/configure.yml" language="yaml" options="" >}}

Estos son unos ejemplos de certificados generados por Let's Encrypt y certbot. Los certificados tienen un tiempo de validez de unos pocos meses con lo que cada cierto tiempo hay que renovarlos de lo que se encarga certbot de forma automática.

{{< code file="certbot-certificates.sh" language="bash" options="" >}}

### Configuración del servidor web Nginx

Antes de generar los certificados de Let's Encrypt es necesario configurar el servidor web Nginx e iniciar el servicio, la configuración incluye modificar los archivos de configuración de Nginx para crear en este caso un servidor web virtual y su contenido. Un servidor web virtual permite devolver un contenido u otro en función del nombre del dominio por el que se acceda al servidor. Las tareas también crean varios directorios y archivos como por ejemplo de directorio raíz que el servidor web utiliza para obtener los recursos estáticos que devuelve.

Configurado Nginx con el protocolo HTTP y el servidor web virtual ya es posible iniciar la validación y generación de certificados de Let's Encrypt y certbot. Las tareas de Ansible del rol _picodotdev.nginx_ usan las tareas del rol _picodotdev.certbot_.

Entre los archivos del rol de Nginx está un archivo que es la plantilla de configuración del servidor para Nginx que procesándolo junto ciertas variables permite generar el contenido final, el contenido generado se guarda en el directorio _/etc/nginx/sites-available/{{ item }}_ y posteriormente se crea un enlace simbólico a este archivo en el directorio _/etc/nginx/sites-enabled/{{ item }}_ del que Nginx lee la configuración de los servidores web virtuales.

{{< code file="ansible/picodotdev.nginx/tasks/main.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.nginx/tasks/restart.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.nginx/handlers/main.yml" language="yaml" options="" >}}
{{< code file="ansible/picodotdev.nginx/templates/nginx-virtual-server.conf" language="plain" options="" >}}

En este ejemplo el contenido del servidor web es simplemente un archivo _html_ con un mensaje de bienvenida inicial que permite comprobar que el servidor funciona correctamente. Este se copia al servidor con Ansible, otra forma de aprovisionamiento del contenido del servidor web virtual podría haber sido utilizar la herramienta _rsync_ para sincronizar una carpeta remota en la raíz de documentos del servidor web y otra opción podría ser hacer un clonado de un repositorio de Git que es por ejemple como funciona [GitHub Pages][github-pages] que es simplemente un rama en un repositorio donde se añade el contenido del sitio web.

{{< code file="ansible/picodotdev.site/files/index.html" language="html" options="" >}}

### Servidor web público

Para configurar el servidor he utilizado como nombre de dominio uno proporcionado por [sslip][sslip] de forma que sea posible configurar el servidor web virtual en Nginx. Con el servidor configurado con los pasos anteriores el y accediendo con el navegador web a la dirección del servidor se obtiene la página con el mensaje de bienvenida, utiliza el protocolo seguro HTTPS con el certificado de Let's Encrypt y que el navegador [Firefox][firefox] reconocer como autoridad de certificación válida y sin mostrar ningún error en la barra de direcciones.

{{< image
    gallery="true"
    image1="image:site.webp" optionsthumb1="650x450" title1="Sitio web"
    caption="Sitio web" >}}

{{% sourcecode git="blog-ejemplos/tree/master/CloudingSiteInfrastructure" command="" %}}

{{% /post %}}
