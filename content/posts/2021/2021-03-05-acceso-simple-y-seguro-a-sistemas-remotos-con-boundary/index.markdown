---
pid: 558
type: "post"
title: "Acceso simple y seguro a sistemas remotos con Boundary"
url: "/2021/03/acceso-simple-y-seguro-a-sistemas-remotos-con-boundary/"
date: 2021-03-05T15:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hashicorp-boundary.svg"
tags: ["gnu-linux", "planeta-codigo"]
series: ["hashicorp"]
summary: "Boundary es otra herramienta de HashiCorp dedicada a la seguridad. Vault está centrada en el almacenamiento de secretos, Boundary está centrada en otro aspecto de la seguridad que es el acceso a sistemas remotos. Una herramienta mejor adaptada y teniendo en cuenta las propiedades dinámicas de los sistemas actuales. Es una alternativa a los métodos de VPN, bastión y _firewall_ que se utilizan para permitir estos accesos remotos."
---

{{% post %}}

{{< logotype image1="hashicorp-boundary.svg" image2="hashicorp.svg" >}}

El acceso de forma remota a sistemas es necesario y útil más cuando las máquinas ya no son máquinas físicas sino que están en centros de datos en diferentes ubicaciones en el mundo e incluso son máquinas lógicas como las máquinas virtuales ejecutadas en la computación de la nube.

El acceso a las máquinas no solo se requiere para las aplicaciones de forma automatizada sino que en algunas situaciones se requiere acceso manual para realizar tareas administrativas o conexión con clientes específicos como escritorio remoto o clientes de bases de datos.

Permitir el acceso remoto es necesario, sin embargo, requiere seguridad de forma que únicamente los usuarios autorizados accedan únicamente a los sistemas autorizados.

{{< tableofcontents >}}

### Funcionamiento acceso remoto tradicional

La forma habitual de proporcionar acceso remoto a sistemas de una red privada es mediante una VPN o a través de un sistema intermediario denominado bastión. Estos conceptos proporcionan acceso remoto en ciertos aspectos de forma segura pero no están exentas de problemas e inconvenientes.

El primer problema es que una vez conectado a una VPN o un bastión se tiene acceso a cualquier ordenador de la red privada a la que uno se ha conectado. Un usuario que necesite acceso a una máquina potencialmente tiene conectividad con otros sistemas en esa red, lo que es riesgo de seguridad por permitir la posibilidad de accesos a sistemas no autorizados.

El segundo problema es que las infraestructuras actuales de computación en la nube no son estáticas sino que son dinámicas en las que los sistemas cambian de dirección IP o se crean y destruyen instancias.

Mediante un _firewall_ ubicado entre el bastión y el sistema de acceso remoto es posible limitar a que sistemas de la red es posible conectarse. Sin embargo, un _firewall_ constituye un elemento más a administrar que añade más complejidad en la infraestructura.

{{< image
    gallery="true"
    image1="image:boundary-workflow.png" optionsthumb1="650x450" title1="Boundary" >}}
{{< image
    gallery="true"
    image1="image:boundary-use-cases.png" optionsthumb1="605x450" title1="Casos de uso de Boundary"
    caption="Boundary" >}}

### Acceso remoto con Boundary

Para tratar de solventar los problemas de acceso remoto con VPN, bastión y _firewall_, simplificar la infraestructura y una administración de más alto nivel lógico de usuarios, permisos y sistemas HashiCorp ha publicado otra herramienta dedicada a la seguridad específica para el acceso remoto.

[Boundary][boundary] es una herramienta adaptada a los entornos modernos de computación en la nube y dinámicos agnóstica de la infraestructura y de las aplicaciones utilizadas por los clientes remotos. Su funcionamiento es similar al de un bastión con una diferencia importante de que Boundary es el que realiza la conexión a los equipos remotos y no la aplicación cliente.

Boundary gestiona el inventario de _hosts_, usuarios, grupos y roles y permisos con los que es capaz de permitir o denegar el acceso únicamente a los _hosts_ permitidos y denegar el acceso a los hosts para los que no se dispone acceso. Funciona en modo cliente y servidor, el servidor hace de bastión y es el que realmente se conecta al sistema remoto, en el modo cliente proporciona a la aplicación cliente conexión con el servidor, a diferencia de un sistema con un bastión o VPC la aplicación cliente no se conecta con el sistema remoto sino que únicamente se conecta al servidor de Boundary.

Boundary es un único binario tanto cuando actúa en modo servidor como en modo cliente, dispone una interfaz de línea de comandos y una interfaz web. En las primeras versiones no están todas la [funcionalidades planificadas en el _roadmap_](https://www.boundaryproject.io/docs/roadmap), a medida que madure el servicio ganará en funcionalidades y utilidad. Integrando Boundary con [Consul][consul] tendrá acceso al inventario de servicios y _hosts_. Integrando Boundary con [Vault][vault] en el momento de acceso al sistema remoto Vault será capaz de crear credenciales de forma dinámica y efímeras.

En su modo servidor requiere de una base de datos [PostgreSQL][postgresql] que inicia como un contenedor de [Docker][docker]. La consola de administración se inicia en el puerto _9200_ y el usuario y contraseña se emite en la salida de la terminal al iniciar la herramienta.

{{< image
    gallery="true"
    image1="image:boundary-console-1.png" optionsthumb1="300x200" title1="Consola de administración de Boundary"
    image2="image:boundary-console-2.png" optionsthumb2="300x200" title2="Consola de administración de Boundary"
    caption="Consola de administración de Boundary" >}}

{{< youtube
    video="tUMe7EsXYBQ" >}}

#### Conceptos

Boundary tiene varios conceptos de dominio, por un lado está el catálogo o inventario de _hosts_, un conjunto de _hosts_ agrupados en un _host set_ y estos a su vez en un _host catalog_. Por otro lado, están los usuarios, grupo y roles. Finalmente los _targets_ son la abstracción del elemento al que se quiere realizar una conexión, el _target_ se traduce en un _host_. Finalmente, todos estos elementos se agrupan en ámbitos o _scopes_ y proyectos o _projects_.

En una organización con unas cuantas decenas de sistemas y usuarios, la gestión de este inventario se vuelve ciertamente complicado.

### Clientes soportados

Boundary incorpora varios clientes en su modo de funcionamiento como cliente. Estos son:

* _ssh_: por defecto es el cliente SSH local (_ssh_)
* _postgres_: por defecto el del cliente oficial de línea de comandos de Postgres (_psql_)
* _rdp_: por defecto es el cliente de Windows RDP (_mstsc_)
* _http_: por defecto es _curl_
* _kube_: por defecto es _kubectl_

Boundary soportar estos clientes de forma nativa sin embargo es agnóstico de la herramienta cliente, con la opción _exec_ es posible utilizar cualquier herramienta que se conecte con un sistema remoto.

* [Connect to Your First Target](https://www.boundaryproject.io/docs/getting-started/connect-to-target)

### Ejemplo de conexión SSH con Boundary

En el siguiente ejemplo muestro como conectarse a un _host_ a través de Boundary con SSH. En el mismo ejemplo muestro como usar Boundary con autenticación con claves SSH y como usar Boundary con Vault que generar un OTP para realizar la misma conexión sin usar claves SSH y únicamente con credenciales temporales. En este ejemplo el _host_ remoto es una [Raspberry Pi con sistema operativo Raspberry Pi OS instalado][blogbitix-554] y con el servicio de SSH iniciado.

En este ejemplo el servidor de Boundary y Vault están en la misma máquina. Los pasos del ejemplo para usar Boundary y SSH, los pasos que involucran ORP son opcionales si se usan claves SSH:

* Iniciar el servidor de Boundary.
* Configurar el servidor SSH para que valide los OTP conectándose a Vault.
* Iniciar Vault y permitir su acceso al _host_ remoto para validar los OTP.
* Realizar la conexión SSH con Boundary desde la máquina local a la máquina remota.

Iniciar el servidor Boundary es sencillo, basta [instalar el binario de Boundary en el PATH del sistema][blogbitix-549] e [instalar el gestor de contenedores Docker][blogbitix-50] previamente. Al iniciar el servidor de Boundary este descarga la imagen de Postgres e inicia su contenedor.

{{< code file="start-docker.sh" language="bash" options="" >}}
{{< code file="start-vault.sh" language="bash" options="" >}}
{{< code file="start-boudary.sh" language="bash" options="" >}}
{{< code file="docker-ps.sh" language="bash" options="" >}}

#### Conexión SSH a sistema remoto

Realizando la conexión directamente al _host_ remoto con SSH se realiza con el siguiente comando, esta es la forma tradicional de hacerlo usando claves SSH.

{{< code file="ssh.sh" language="bash" options="" >}}

Utilizando Boundary como intermediario estos son los comandos. Primero hay que realizar la autenticación en Boundary y luego usar Boundary para que establezca la conexión SSH con el _host_ destino, previamente hay es necesario haber configurado los recursos en Boundary.

{{< code file="boundary-authentication.sh" language="bash" options="" >}}
{{< code file="ssh-boundary-1.sh" language="bash" options="" >}}

Utilizar Boundary como intermediario requiere configurar la herramienta para que tenga un inventario de _host_ a los cuales es posible conectarse y usuarios con sus permisos. En el ejemplo utilizo una Raspberry Pi como _host_ al que realizar la conexión en la dirección IP _192.168.1.101_. Para utilizar la linea de comandos de Boundary hay que autenticarse y establecer la variable de entorno _BOUNDARY\_TOKEN_.

Las operaciones de estos comandos para crear los recursos están disponibles también para realizarlas desde su consola web de administración.

{{< image
    gallery="true"
    image1="image:boundary-host-catalog.png" optionsthumb1="300x200" title1="Crear recursos de Boundary"
    image2="image:boundary-targets.png" optionsthumb2="300x200" title2="Crear recursos de Boundary"
    caption="Crear recursos de Boundary" >}}

{{< code file="boundary-configuration.sh" language="bash" options="" >}}

Una de las funcionalidades que proporciona Boundary es trazabilidad en el _log_ se muestran los inicio de las conexiones y su finalización, permite ver las conexiones abiertas y forzar el cierre de una conexión.

{{< code file="boundary.log" language="plain" options="" >}}

{{< image
    gallery="true"
    image1="image:boundary-sessions.png" optionsthumb1="300x200" title1="Conexiones realizadas con Boundary"
    caption="Conexiones realizadas con Boundary" >}}

### Autenticación con SSH

La autenticaión de SSH usando Boudary no cambia, se solicita la contraseña del usuario con el que se realiza la conexión, la clave pública en caso de que el servidor se haya configurado con clave pública y privada o incluso un segundo factor de autenticación.

* [Autenticación mediante clave pública y privada con SSH][blogbitix-401]
* [Autenticación con segundo factor de autenticación en SSH][blogbitix-562]

#### Segundo factor de autenticación con Vault

Vault permite generar contraseñas de un solo uso y configurando SSH validar los OTP proporcionados en la conexión. La ventaja de usar OTP es que no es necesario distribuir las claves públicas en cada una de las máquinas en las que se quiera realizar la conexión SSH. Hay que cambiar los archivos de configuración _/etc/pam.d/sshd_, _/etc/ssh/sshd_config_, _/etc/vault-ssh-helper.d/config.hcl_ y reiniciar el servicio de SSH, estos cambios permiten al servidor SSH validar los _tokens_ proporcionados conectándose con Vault.

{{< code file="install-ssh-helper.sh" language="bash" options="" >}}
{{< code file="sshd.sh" language="bash" options="" >}}
{{< code file="sshd" language="plain" options="" >}}
{{< code file="sshd_config.sh" language="bash" options="" >}}
{{< code file="sshd_config" language="plain" options="" >}}

Para validar el OTP el servidor SSH a través del módulo de autenticación PAM le pide a Vault que lo valide, para ello necesita conectividad de red, si es necesario activando la regla del _firewall_ para permitir el tráfico de red para el puerto _8200_ que utiliza Vault.

{{< code file="vault-configuration-firewall.sh" language="bash" options="" >}}

También hay que configurar Vault con las políticas y permisos para permitir a un usuario autenticado generar los OTP de autenticación.

{{< code file="vault-configuration.sh" language="bash" options="" >}}

El comando para generar el OTP e iniciar la conexión es el siguiente. Este comando realiza dos acciones, generar el OTP e iniciar la conexión SSH, el OTP hay que copiarlo y pegarlo de forma manual en la solicitud de contraseña que corresponde con el OTP generado.

{{< code file="ssh-vault-otp-1.sh" language="bash" options="" >}}

Generar el OTP y realizar la conexión se puede realizar en dos pasos separados.

{{< code file="ssh-vault-otp-2.sh" language="bash" options="" >}}

Usando Vault la conexión se realiza entre la máquina local y la máquina destino, usando Boundary como intermediario el comando es el mismo que al usar una clave SSH pero al iniciar la conexión se solicita la contraseña OTP.

{{< code file="ssh-boundary-2.sh" language="bash" options="" >}}

{{< reference >}}
* [Boundary Quick Start Guide](https://learn.hashicorp.com/tutorials/boundary/getting-started-install?in=boundary/getting-started)
* [SSH Secrets Engine: One-Time SSH Password](https://learn.hashicorp.com/tutorials/vault/ssh-otp)
{{< /reference >}}

{{% /post %}}
