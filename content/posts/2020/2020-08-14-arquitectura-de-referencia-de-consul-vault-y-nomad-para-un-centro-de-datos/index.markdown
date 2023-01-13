---
pid: 508
type: "post"
title: "Arquitectura de referencia de Consul, Vault y Nomad para un centro de datos"
url: "/2020/08/arquitectura-de-referencia-de-consul-vault-y-nomad-para-un-centro-de-datos/"
date: 2020-08-14T16:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "logotype:hashicorp-text.svg"
imagePost: "image:consul-reference-architecture.webp"
tags: ["gnu-linux", "planeta-codigo"]
series: ["hashicorp"]
summary: "HashiCorp proporciona una buena documentación de sus productos con tutoriales, documentación de las herramientas y vídeos de formación en su canal de Youtube. Aunque en la documentación está todo explicado para poner en práctica una arquitectura para un caso más real y con cierto grado de producción requiere leer múltiples artículos para aplicar y aglutinar todo lo descrito. En este artículo muestro una arquitectura de referencia de varios productos de HashiCorp como Consul, Vault y Nomad que forman un centro de datos o entorno de ejecución para servicios en un ejemplo aprovisionado con Vagrant en máquinas virtuales de VirtualBox."
---

{{% post %}}

{{< logotype image1="hashicorp-consul.svg" image2="hashicorp-vault.svg" image3="hashicorp-nomad.svg" >}}

La empresa [HashiCorp][hashicorp] cataloga las necesidades de las aplicaciones basadas en la computación en la nube u orientadas a microservicios en cuatro aspectos que necesitan: aprovisionamiento, registro y conexión, seguridad y ejecución. HashiCorp en base a estos cuatro aspectos de las aplicaciones ha desarrollado para cada una de ellas una aplicación específica integrable con las otras pero también con la posibilidad de usar cada una de forma individual e independiente que permiten disponer de un centro de datos y cubre varias de las necesidades computacionales de una empresa en entornos modernos basados en nube que son dinámicos y requieren elasticidad.

En muchas organizaciones los desarrolladores solo se encargan del desarrollo de la aplicación o servicio, del despliegue y administración se encargan los administradores de sistemas. Los centros de datos se operan en base a peticiones entre los desarrolladores y los administradores de sistemas, este modelo organización es lento y propenso a ineficiencias, para evitarlo hay que tratar que los desarrolladores sean lo más autosuficientes posible para operar el entorno de ejecución de sus servicios haciendo que estos no solo desarrollen sus aplicaciones sino que también tengan capacidad de desplegarlas y monitorizarlas.

En este artículo muestro un ejemplo usando tres de estos mismos productos que se usarían en un entorno de producción [Consul][consul] para el registro de servicios y conexión de forma segura, [Vault][vault] para seguridad y servicios de cifrado y [Nomad][nomad] para la ejecución de servicios con contenedores [Docker][docker]. Para el aprovisionamiento y ejecutar el ejemplo en la propia máquina el ejemplo usa [Vagrant][vagrant] que permite crear y aprovisionar máquinas virtuales sobre [VirtualBox][virtualbox], en un entorno basado en la nube se usaría [Terraform][terraform]. Consul, Vault y Nomad forman lo que sería un centro de datos de un entorno de ejecución para aplicaciones y servicios.

[HashiQube](https://github.com/servian/hashiqube/) proporciona un entorno para hacer pruebas de desarrollo con todas las herramientas de HashiCorp, pero no es utilizable en un entorno de producción. Para construir un entorno con grado de producción hay que leer mucha de la buena documentación que ofrece HashiCorp de sus productos. Los productos de HashiCorp tienen una buena documentación en formato guía y tutoriales divididos en secciones individuales sobre un tema en concreto.

* [Tutorial](https://learn.hashicorp.com/consul) y [documentación](https://www.consul.io/docs/index.html) de Consul
* [Tutorial](https://learn.hashicorp.com/vault) y [documentación](https://www.vaultproject.io/docs)  de Vault
* [Tutorial](https://learn.hashicorp.com/nomad) y [documentación](https://www.nomadproject.io/docs/index.html) de Nomad
* [Totorial](https://learn.hashicorp.com/terraform) y [documentación](https://www.terraform.io/docs/index.html) de Terraform
* [Documentación de Vagrant](https://www.vagrantup.com/docs) y [documentación de Packer](https://www.packer.io/docs)
* [Canal de YouTube de HashiCorp](https://www.youtube.com/c/HashiCorp/)

{{< image
    gallery="true"
    image1="image:hashicorp-static-dynamic.webp" optionsthumb1="650x450" title1="Diferencias entre entornos estáticos anteriores y dinámicos nuevos"
    caption="Diferencias entre entornos estáticos anteriores y dinámicos nuevos" >}}

Este artículo hace uso, está relacionado y se complementa con otros artículos que muestran varias de las funcionalidades individuales utilizadas en el ejemplo.

* [Crear de forma sencilla y rápida máquinas virtuales de VirtualBox con Vagrant][blogbitix-403]
* [Configurar SSL/TLS en un servidor Tomcat, JBoss, WildFly, Lighttpd, Nginx o Apache][blogbitix-14]
* [Configurar HTTP/2 en Nginx, Apache HTTPD, WildFly o Jetty][blogbitix-129]
* [Configurar autenticación básica en los servidores web Nginx y Apache][blogbitix-505]
* [Cómo crear un proxy inverso entre el servidor web Nginx y un servidor de aplicaciones Java][blogbitix-159]
* [Comandos para crear una autoridad de certificación (CA) con OpenSSL][blogbitix-506]
* [Comunicaciones seguras, autenticación mutua y autorizaciones con intenciones entre servicios usando Consul Connect y Nomad][blogbitix-502]
* [Microservicios con Spring Cloud, Consul, Nomad y Traefik][blogbitix-436]
* [Obtener un nombre de dominio para una dirección IP privada][blogbitix-504]
* [Introducción a Nomad para gestionar aplicaciones y microservicios][blogbitix-398]
* [Estrategias de despliegue para microservicios con Nomad][blogbitix-399]
* [Administrar secretos y proteger datos sensibles con Vault][blogbitix-424]
* [Generar credenciales de conexión a base de datos bajo demanda con Vault][blogbitix-428]
* [Utilizar credenciales de conexión a la base de datos generadas por Vault en una aplicación de Spring][blogbitix-429]

{{< tableofcontents >}}

### Servicios de HashiCorp

{{< image
    gallery="true"
    image1="image:hashicorp-products-1.webp" optionsthumb1="200x150" title1="Productos de HashiCorp"
    image2="image:hashicorp-products-2.webp" optionsthumb2="200x150" title2="Productos de HashiCorp"
    image3="image:hashicorp-products-2.webp" optionsthumb3="200x150" title3="Productos de HashiCorp"
    caption="Productos de HashiCorp" >}}

{{< image
    gallery="true"
    image1="image:hashicorp-consul-before-after.webp" optionsthumb1="200x150" title1="Antes y después con Consul"
    image2="image:hashicorp-vault-before-after.webp" optionsthumb2="200x150" title2="Antes y después con Vault"
    image3="image:hashicorp-nomad-before-after.webp" optionsthumb3="200x150" title3="Antes y después con Nomad" >}}
{{< image
    gallery="true"
    image1="image:hashicorp-terraform-before-after.webp" optionsthumb1="200x150" title1="Antes y después con Terraform"
    image2="image:hashicorp-terraform-before-after.webp" optionsthumb2="200x150" title2="Ecosistema con Nomad"
    caption="Ecosistema con Nomad" >}}

#### Consul

En una aplicación basada en servicios a lo largo del tiempo estos se añaden y se eliminan, varía el número de instancias, cambian de ubicación, pueden dejar de estar accesibles de forma inesperada y los servicios necesitan comunicarse de forma segura.

La herramienta Consul proporciona proporcionan funcionalidades para todas estas necesidades con un registro de registro y descubrimiento que mantiene el catálogo de servicios e instancias con su ubicación, proporciona comprobaciones de estado o _health checks_ para conocer en todo momento que las instancias están funcionado correctamente, proporciona comunicaciones seguras entre los servicios con [Consul Connect][consul-connect] y permite o deniega la comunicación con intenciones y basándose en la identidad de los servicios.

El catálogo de servicios es accesible mediante una API REST, también ofrece su catálogo de servicios a través de una interfaz DNS ya que incorpora la funcionalidad de servidor DNS. Consul además proporciona un almacén de clave/valor que los servicios pueden utilizar como valores de configuración que cambian de forma dinámica sin necesidad de reiniciar las instancias del servicio para que los nuevos valores surtan efecto.

#### Vault

Vault proporciona servicios de seguridad. En vez de implementar las necesidades de seguridad en cada aplicación con el lenguaje de programación en el que esté desarrollado Vault permite delegar y centralizar en él los requerimientos y políticas de seguridad.

Proporciona funcionalidades de cifrado y descifrado, cifrado y descifrado conservando el formato y enmascaramiento basado en políticas. También permite generar certificados con un tiempo de expiración pequeño y generación de credenciales dinámicas, esto es, en vez de generar unas credenciales de conexión para cada aplicación a una base de datos la aplicación puede solicitar a Vault unas credenciales de conexión para la base de datos que únicamente son válidas mientras la aplicación necesita la conexión. Las credenciales dinámicas permiten aumentar la seguridad.

#### Nomad

Los servicios necesitan ejecutarse y crear instancias para proporcionar sus funcionalidades, Nomad hace las funciones de orquestador de servicios. Se encarga de planificar el nodo de entre los disponibles en los que se ejecutara cada instancia del servicio, de mantener el número de instancias indicadas en la descripción de cada servicio y de aplicar la estrategia de despliegue según la política definida para cada servicio, como _blue/green_ o _canary_.

Nomad se integra con Consul y permite que al iniciar la instancia de un servicio se registre en Consul y configurar un _health check_ para que Consul monitorice el estado de la instancia del servicio.

#### Vagrant y Terraform

Para automatizar la creación de los entornos, crear los entornos como código y que estos queden descritos en archivos se utilizan herramientas de aprovisionamiento. HashiCorp proporciona dos, una es Vagrant para crear máquinas virtuales en local con VirtualBox para un entorno de desarrollo. Para un entorno de producción se usará un proveedor de computación en la nube como [Amazon Web Services][amazon-web-services], [Google Cloud Platform][google-cloud], [Microsoft Azure][microsoft-azure] u otros como [Digital Ocean][digital-ocean] o [Linode][linode].

Cada uno de estos entornos de computación tiene sus peculiaridades, Terraform permite describir las necesidades de sistemas en cuanto a infraestructura del entorno de ejecución de forma independiente a cada uno de estos proveedores, automatizar el aprovisionamiento y definir la infraestructura como código lo que permite guardar en un repositorio de control de versiones los cambios que se realizan a la infraestructura.

#### Packer

La herramienta Packer permite construir las imágenes base para los sistemas en la nube y de los contenedores. Es agnóstica de los diferentes sistemas en la nube permitiendo construir imágenes para cada una de ellas, esto permite no estar encadenado a un proveedor determinado.

### Comparación con Kubernetes

[Kubernetes][kubernetes] es otro sistema con la funcionalidad de orquestador de contenedores desarrollado originalmente por Google y al que ahora contribuyen numerosas empresas como  [Red Hat][redhat]. Proporciona todas las características que necesitan las aplicaciones basadas en contenedores incluyendo gestión del clúster, planificación, descubrimiento de servicios, monitorización, gestión de secretos y otros más.

HashiCorp proporciona la mayoría de las funcionalidades de Kubernetes en cada una de las diferentes herramientas que tiene siguiendo la filosofía Unix de herramientas con un ámbito reducido y agregables, por ejemplo, Consul proporciona descubrimiento de servicios, Vault seguridad y Nomad está diseñado específicamente para proporcionar gestión del cluster y planificación.

Kubernetes se centra en Docker mientras que Nomad soporta aplicaciones de propósito general como pueden ser comandos del sistema, aplicaciones Java, Docker o [Podman][podman]. Kubernetes está diseñado como una colección de más de media docena de servicios que interoperan para proporcionar la funcionalidad completa. Soporta alta disponibilidad pero operacionalmente es complejo de configurar.

La arquitectura de las herramientas de HashiCorp  es más simple, cada  una de las herramientas es un único binario que sirve tanto para actuar como servidor como cliente y que se pueden utilizar sin requerir al resto, tanto es así que Consul se puede utilizar y algunos lo utilizan junto a Kubernetes. Los servidores son distribuidos, con alta disponibilidad y operacionalmente más fáciles. Los servidores soportan configuraciones multicentro de datos y multiregión llegando a tamaños que superan los 10K nodos en entornos de producción.

* [Nomad vs Kubernetes](https://www.nomadproject.io/intro/vs/kubernetes.html)
* [Consul vs. Other Software](https://www.consul.io/intro/vs)

### Arquitectura de referencia

En una arquitectura de referencia para proporcionar alta disponibilidad y tolerancia a fallos es necesario crear varias instancias de cada servicio. Las diferentes instancias de Consul, Vault y Nomad forman un clúster algunas funcionando en modo servidor y otras funcionando en modo cliente. Para los servidores de Consul, Vault y Nomad se recomiendan al menos 3 instancias de cada uno de ellos, en total 9 instancias. En cada instancia de Vault y Nomad se crea un agente de Consul que actúa en modo cliente que atiende a las peticiones de forma local y realiza las comunicaciones con los servidores de Consul. De Nomad habrá varias instancias adicionales más actuando en modo cliente que serán donde se ejecuten las instancias de los servicios de aplicación como un un contenedor Docker con Nginx, una base de datos [PostgresQL][postgresql] o una aplicación Java con [Spring Boot][spring-boot].

En total un centro de datos para un entorno puede estar compuesto como mínimo entre 9, 15 o 21 para el clúster con 3, 5, 7 servidores de Consul, Vault y Nomad según las necesidades de computación del centro de datos. De Nomad en modo cliente donde se ejecutan los servicios el número de instancias variará según las necesidades de computación y número de servicios que necesitan las aplicaciones, también puede ser un número significativo de entre 10 a 100 o más instancias. El número de máquinas e instancias es elevado por eso hay que tratarlas como ganado y no como animales de compañía que requieran administrarlas de forma individual.

* [Consul Reference Architecture](https://learn.hashicorp.com/tutorials/consul/reference-architecture)
* [Vault Reference Architecture](https://learn.hashicorp.com/tutorials/vault/reference-architecture)
* [Nomad Reference Architecture](https://learn.hashicorp.com/tutorials/nomad/production-reference-architecture-vm-with-consul)

Estos son los esquemas de las arquitecturas de referencia de Consul, Vault y Nomad para configuraciones de producción formado por varios servidores, clientes y regiones o centros de datos.

{{< image
    gallery="true"
    image1="image:consul-reference-architecture.webp" optionsthumb1="200x150" title1="Arquitectura de referencia de Consul"
    image2="image:vault-reference-architecture.webp" optionsthumb2="200x150" title2="Arquitectura de referencia de Vault"
    image3="image:nomad-reference-architecture.webp" optionsthumb3="200x150" title3="Arquitectura de referencia de Nomad"
    caption="Arquitecturas de referencia de Consul, Vault y Nomad" >}}

#### Precio

Dependiendo del tamaño de las máquinas de cómputo elegidas para cada instancia el coste de la infraestructura varia. En AWS una instancia _t3a.medium_, _t3a.large_ y _t3a.xlarge_ con 4, 8 y 16 GiB tienen un coste aproximado de $125, $250 y $400 al año si se paga por adelantado una reserva de 3 años. Multiplicado esto por el número de instancias el coste no es despreciable pero asequible para una empresa que llega a necesitar 100 instancias para sus servicios. La ventaja de la computación en la nube y estas herramientas de HashiCorp es que permiten adaptar el entorno del centro de datos a las necesidades suficientes para cada momento, si se necesitan más máquinas e instancias se añaden si dejan de necesitar se eliminan, esta la elasticidad ofrecida por la computación en la nube que los costes sean únicamente los necesarios.

Es difícil calcular el coste exacto de un centro de datos ya que tendrá una mezcla de diferentes tipos de instancias, instancias reservadas y bajo demanda aprovechando la elasticidad de la computación en la nube, también hay que tener en cuanta que una organización necesitará entornos de pruebas aún siendo de menor tamaño y número que el de producción. El mayor coste lo determina el número de nodos de Nomad, para 50 nodos estarán sobre los $20K y para 1000 los $400K anuales teniendo en cuenta que una empresa que necesita esas cantidades de nodos ya son muy grandes seguramente sean del tamaño multinacionales con facturaciones de varios cientos o miles de millones.

<table class="table">
   <thead class="table-light">
     <tr>
       <th colspan="4">Coste anual por instancia reservada durante 3 años (aproximado)</th>
     </tr>
     <tr>
       <th width="100px">Tipo instancia</th>
       <th width="100px">Cores</th>
       <th width="100px">Memoria (GiB)</th>
       <th width="200px">Precio/año (reserva 3 años)</th>
     </tr>
   </thead>
   <tbody>
       <tr>
           <td>t3a.medium</td>
           <td>2</td>
           <td>4</td>
           <td>$125</td>
       </tr>
       <tr>
           <td>t3a.large</td>
           <td>2</td>
           <td>8</td>
           <td>$250</td>
       </tr>
       <tr>
           <td>t3a.xlarge</td>
           <td>4</td>
           <td>16</td>
           <td>$400</td>
       </tr>
   </tbody>
</table>

<table class="table">
   <thead class="table-light">
     <tr>
       <th colspan="4">Coste anual del clúster de servidores Consul, Nomad y Vault con diferente número de instancias (aproximado)</th>
     </tr>
     <tr>
       <th width="100px">Tipo</th>
       <th width="100px">Número de instancias</th>
       <th width="200px">Precio/año (reserva 3 años)</th>
     </tr>
   </thead>
   <tbody>
       <tr>
           <td>t3a.xlarge</td>
           <td>3 (1x3)</td>
           <td>$1200</td>
       </tr>
       <tr>
           <td>t3a.xlarge</td>
           <td>9 (3x3)</td>
           <td>$3600</td>
       </tr>
       <tr>
           <td>t3a.xlarge</td>
           <td>15 (5x3)</td>
           <td>$6000</td>
       </tr>
   </tbody>
</table>

<table class="table">
   <thead class="table-light">
     <tr>
       <th colspan="7">Coste anual nodos Nomad con diferente número de instancias (aproximado)</th>
     </tr>
     <tr>
       <th width="100px">Tipo</th>
       <th width="100px">10</th>
       <th width="100px">50</th>
       <th width="100px">100</th>
       <th width="100px">300</th>
       <th width="100px">1000</th>
     </tr>
   </thead>
       <tr>
           <td>t3a.medium</td>
           <td>$1.25K</td>
           <td>$6.25K</td>
           <td>$12.5K</td>
           <td>$37.5K</td>
           <td>$125K</td>
       </tr>
       <tr>
           <td>t3a.large</td>
           <td>$2.5K</td>
           <td>$12.5K</td>
           <td>$25K</td>
           <td>$75K</td>
           <td>$250K</td>
       </tr>
       <tr>
           <td>t3a.xlarge</td>
           <td>$4K</td>
           <td>$20K</td>
           <td>$40K</td>
           <td>$120K</td>
           <td>$400K</td>
       </tr>
   </tbody>
</table>

### Ejemplo de la arquitectura de referencia con Vagrant

Para ejecutar la arquitectura de referencia en vez de crear 3 o 5 instancias de Consul, Vault y Nomad en el siguiente ejemplo se utiliza solo 1 instancia de cada uno de ellos. Para ejecutarlos en local se usa Vagrant para crear y aprovisionar las máquinas virtuales de VirtualBox.

Vault se comunica con Consul para utilizar el almacén de clave-valor o KV en el que Vault almacena los datos cifrados, Nomad se comunica con Vault para proporcionar a las aplicaciones algunos requerimientos de seguridad y con Consul para obtener datos de su KV y proporcionarlos a las instancias de los servicios, también para registrar en Consul las instancias de los servicios y si lo utilizan proporcionales sus necesidades de comunicación con _Consul Connect_.

{{< image
    gallery="true"
    image1="image:virtualbox.webp" optionsthumb1="300x250" title1="Máquinas virtuales en VirtuaBox aprovisionadas con Vagrant"
    caption="Máquinas virtuales en VirtuaBox aprovisionadas con Vagrant" >}}

La comunicación entre Consul, Vault y Nomad se realiza utilizando diferentes protocolos y puertos de red, el entorno de computación como el que proporcionan los proveedores de computación en la nube no es confiable por ser compartido aún con las medidas de aislamiento de red que implementan. Para mayor seguridad Consul, Vault y Nomad permite utilizar comunicaciones cifradas para todo el tráfico de red entrante y saliente para lo que es necesario generar certificados que permitan cifrar las comunicaciones y permitan identificar tanto al servidor como al cliente con autenticación mutua basada en certificados.

Los certificados se generan con [OpenSSL][openssl] con una pequeña autoridad de certificación (CA) propia.

{{< code file="generate-certificates.sh" language="bash" options="" >}}

Vault requiere una inicialización para generar un _token_ raíz y hacer el _unseal_. El _unseal_ es un proceso que permite desbloquear el almacén cifraddo de Vault en el que interviene una clave dividida en varias partes de las cuales un número igual o menor al total son necesarias para desbloquear Vault cuando se inicia el clúster de servidores Vault.

Las máquinas virtuales utilizan la distribución [Ubuntu][ubuntu] de [GNU][gnu]/[Linux][linux] en las que se instala las herramientas de HashiCorp para los servidores y adicionalmente Docker en las instancia cliente de Nomad. En la instancia que da acceso a las consolas web de administración se instala el servidor web Nginx que hace de _proxy_ con el servidor de Consul, Vault y Nomad. El aprovisionamiento de las maquinas se realiza con varios _scripts_ de _bash_ que contienen los comandos necesarios para instalar los paquetes, realizar la misma configuración que se realizaría de forma manual desde una terminal y copiar los archivos de configuración y certificados desde la máquina anfitrión a las máquinas virtuales.

Dado que el ejemplo aún con el mínimo número de máquinas virtuales necesarias requiere varias es necesario una máquina _host_ tenga entre 8 y 16 GiB de memoria RAM como mínimo, se puede ajustar la cantidad de memoria que usa cada máquina virtual en el archivo de configuración de Vagrant.

El ejemplo consta de las siguientes máquinas virtuales.

* 1 máquina virtual servidor Consul, _nomad-server-1_.
* 1 máquina virtual servidor Vault, _vault-server-1_.
* 1 máquina virtual servidor Nomad, _nomad-server-1_.
* 1 máquina virtual cliente Nomad, _nomad-agent-1_.
* 1 máquina virtual con Nginx como _proxy_ a consolas de administración web Consul, Vault, Nomad, _ui-server_.

Estos son los _scripts_ de aprovisionamiento con sus funciones:

* _hashicorp\_role\_script_: instala los binarios de Consul, Vault, Nomad, Envoy para Consul Connect y los _cni-plugins_.
* _docker\_role\_script_: instala Docker.
* _nginx\_role\_script_: instala Nginx.
* _consul\_dns\_server\_role\_script_: redirige las consultas DNS del puerto 53 al puerto 8600 e instala _iptables-persistent_. Esto permite ejecutar Consul sin requerir permisos de administrador requeridos para abrir un puerto privilegiado como el 53.
* _create\_provision\_directories\_script_: inicializa los directorios de aprovisionamiento en la máquina virtual.
* _consul\_provision\_script_, _vault\_provision\_script_, _nomad\_provision\_script_, _ui\_server\_provision\_script_: aprovisionan los archivos de configuración de la máquina anfitrión a las máquinas virtuales en los directorios apropiados.
* _vault\_configure\_script_, _nomad\_configure\_script_: realizan la configuración de los servidores Vault y Nomad.
* _consul\_services\_script_, _vault\_services\_script_, _nomad\_services\_script_, _ui\_server\_services\_script_, _docker\_services\_script_: inician los servicios de [systemd][systemd].

Consul tiene la función de servidor DNS con el catálogo de servicios registrados. Para dar acceso a las máquinas virtuales al servidor de nombres DNS de Consul es necesario redirigir el tráfico del puerto 53 al puerto 8600 y modificar el archivo _resolv.conf_. Esto requiere un poco de configuración en Ubuntu modificando _iptables_ y utilizar _iptables-persistent_ para que los cambios sean permanentes entre reinicios.

{{< image
    gallery="true"
    image1="image:consul-dns.webp" optionsthumb1="300x250" title1="Servicio DNS de Consul accedido desde el servidor de Nomad"
    caption="Servicio DNS de Consul que expone el catálogo de servicios accedido desde el servidor de Nomad" >}}

Este es el archivo de aprovisionamiento de Vagrant para crear las máquinas virtuales en VirtualBox. En todos los servidores no son necesarias todas las herramienta pero por simplicidad en el ejemplo se instalan, por ejemplo, en el servidor de Nomad no es necesario instalar el binario de Vault.

{{< code file="Vagrantfile" language="ruby" options="" >}}

El comando que permite crear y aprovisionar las máquinas es el siguiente.

{{< code file="vagrant-up.sh" language="bash" options="" >}}

En el primer inicio Vault requiere generar el _token root_ que tiene los permisos de superusuario, también se divide una clave en varias partes de las cuales un número mínimo son necesarias para realizar la operación de _unseal_, las partes de la clave son distribuidas entre varias personas y son necesarias varias para realizar la operación _unseal_, para que la persona que genera las partes de las claves no conozca todas las partes de la clave y para transmitirlas de forma segura las partes de la clave se cifran con PGP. Para la creación del _token root_ y las claves en la creación de más máquinas en vez de usar _vagrant-up.sh_ hay que aprovisionar las máquinas en varios pasos.

Se inicializa la máquina virtual del servidor Consul, luego del servidor Vault y se genera el _token root_, se inicia sesión con el _token root_ y se realiza la operación _unseal_ con el número mínimo de claves necesarias para desbloquear los datos cifrados del servidor.

{{< code file="vagrant-up-1.sh" language="bash" options="" >}}

El _token_ raíz devuelto en la inicialización de Vault es necesario ponerlo en la variable _VAULT\_ROOT\_TOKEN_ del archivo _Vagranfile_ junto con 2 de las 5 claves para desellar Vault en las variables _VAULT\_UNSEAL\_KEY\_1_ y _VAULT\_UNSEAL\_KEY\_2_. Luegos se inician y aprovisionan el resto de máquinas virtuales.

{{< code file="vagrant-up-2.sh" language="bash" options="" >}}

Tanto Consul, Vault como Nomad necesitan un archivo de configuración, en él se describe las direcciones IP de los servidores en las máquinas virtuales, se usan las propiedades de configuración necesarias para que la comunicaciones estén cifradas y diferentes propiedades adicionales para configurar cada servicio servidor.

* El servidor Consul utiliza la dirección IP _192.168.33.10_
* El servidor Vault utiliza la dirección IP _192.168.33.20_
* El servidor Nomad utiliza la dirección IP _192.168.33.30_
* El cliente de Nomad utiliza la dirección IP _192.168.33.40_
* El servidor Nginx con las consolas de administración web utiliza la dirección IP _192.168.33.50_

{{< code file="consul-server/consul.hcl" language="hcl" options="" >}}
{{< code file="vault-server/vault.hcl" language="hcl" options="" >}}
{{< code file="nomad-server/nomad.hcl" language="hcl" options="" >}}
{{< code file="nomad-agent/nomad.hcl" language="hcl" options="" >}}

Como se muestra en los esquemas de de arquitectura de referencia Vault y Nomad no se comunican directamente con los servidores Consul sino que se comunica con un cliente de Consul en la propia máquina y es el cliente de Consul el que se comunica con los servidores de Consul. Consul se instala con la función de cliente en las máquinas servidor Vault, servidor Nomad y cliente de Nomad, el archivo de configuración de Consul en modo cliente son similares a este variando la dirección IP de la interfaz de red a la que se asocian.

{{< code file="vault-server/consul-agent.hcl" language="hcl" options="" >}}

#### Interfaz web para Consul, Vault y Nomad

Las consolas de administración web de Consul, Vault y Nomad están accesibles en estas direcciones URL que están protegidas por una autenticación básica, dado que el certificado de la CA no es de confianza para el navegador se muestra previamente un mensaje de advertencia. En la consola web de Consul se observan los servicios y nodos registrados registrados y su estado de salud.

* Dirección, usuario y contraseña de consola web Consul: _https:\/\/consul.192.168.33.50.xip.io_, _consul_ y _consul_.
* Dirección, usuario y contraseña de consola web Vault: _https:\/\/vault.192.168.33.50.xip.io_, _vault_ y _vault_.
* Dirección, usuario y contraseña de consola web Nomad: _https:\/\/nomad.192.168.33.50.xip.io_, _nomad_ y _nomad_.

Esta es la definición del servidor web virtual de la consola de Consul, los servidores web virtuales de Vault y Nomad son similares.

{{< code file="ui-server/consul.conf" language="nginx" options="" >}}

Las consolas de administración muestran el estado de centro de datos y con todos los servicios está disponibles.

{{< image
    gallery="true"
    image1="image:consul-console.webp" optionsthumb1="300x250" title1="Consola web de Consul con los servicios registrados y su estado de salud"
    image2="image:consul-console-nodes.webp" optionsthumb2="300x250" title2="Consola web de Consul con los nodos registrados y su estado de salud" >}}
{{< image
    gallery="true"
    image1="image:vault-console.webp" optionsthumb1="300x250" title1="Consola web de Vault"
    image2="image:nomad-console.webp" optionsthumb2="300x250" title2="Consola web de Nomad"
    caption="Consolas web de Consul, Vault y Nomad y estados de salud de los servicios y nodos" >}}

#### Interfaz de línea de comandos para Consul, Vault y Nomad

Los mismos binarios de Consul, Vault y Nomad que tienen las funciones de servidor y cliente permiten utilizar su línea de comandos para realizar y automatizar con _scripts_ las mismas acciones que se pueden hacer desde las consolas web pero la línea de comandos.

Dado que en el ejemplo los servidores requieren identificar al cliente es necesario configurar variables de entorno que indiquen la dirección IP del servidor y los certificados para el cifrado y autenticación. Una vez configuradas las variables de entorno se pueden lanzar comandos que permiten realizar tareas de administración para obtener información y para enviar _jobs_ a Nomad.

{{< code file="environment.sh" language="bash" options="" >}}
{{< code file="environment-status.sh" language="bash" options="" >}}

#### Ejecución de un servicio

El siguiente es la definición de un servicio o _job_ para Nomad que define un servidor Nginx ejecutado en un contenedor de Docker. Una vez Nomad lo pone en ejecución se registra en Consul de forma automática de forma que pudiera ser encontrado por otros servicios que consulten el catálogo de servicios de Consul.

{{< code file="nomad-agent/nginx.nomad" language="hcl" options="" >}}
{{< code file="nomad-job.sh" language="hcl" options="" >}}

{{< image
    gallery="true"
    image1="image:nomad-job-status.webp" optionsthumb1="300x250" title1="Estado de un job en la consola web de Nomad"
    image2="image:nginx.webp" optionsthumb2="300x250" title2="Servicio de Nginx ejecutado en Nomad como un contenedor de Docker"
    caption="Ejecución, estado de un job con un servicio de Nginx ejecutando en un contenedor de Docker en la consola web de Nomad" >}}

{{< image
    gallery="true"
    image1="image:consul-services-web.webp" optionsthumb1="300x250" title1="Servicios en consul con el servicio de Nginx registrado por Nomad"
    image2="image:datacenter-commandline-information.webp" optionsthumb2="300x250" title2="Información de estado del centro de datos obtenido desde la línea de comandos"
    caption="Servicios en consul con el servicio de Nginx registrado por Nomad e información de estado del centro de datos obtenido desde la línea de comandos" >}}

### Requerimientos de operaciones

Este artículo solo tiene un ejemplo básico pero que sirve como base para crear un entorno de producción real basado en las herramientas de HashiCorp.

Hay otras funcionalidades que un entorno real de producción requeriría, como telemetría y monitorización, cual sería el proceso para actualizar el centro de datos a nuevas versiones, aprovisionamiento con Terraform para un centro de computación proporcionado por un proveedor en la nube, utilizar un sistema de autenticación como OAuth en vez autenticación básica en la consolas de administración, quizá [consul-template][consul-template] en algunos servicios para actualizar de forma dinámica configuraciones que tomen datos de Vault o Consul o utilizar [Traefik][traefik] para que haga de _proxy_ de los servicios que utilicen el protocolo HTTP.

{{% sourcecode git="blog-ejemplos/tree/master/ConsulVaultNomadDatacenter" command="./vagrant-up.sh" %}}

{{< reference >}}
* [How to make iptables rules persistent after reboot on Linux](https://linuxconfig.org/how-to-make-iptables-rules-persistent-after-reboot-on-linux)
* [Securing Consul Web UI](https://github.com/hashicorp/consul/issues/7150)
* [Consul Web UI: Need to make it password enabled](https://github.com/hashicorp/consul/issues/1720)
* [Vagrant box startup timeout due to no serial port](https://bugs.launchpad.net/cloud-images/+bug/1829625)
{{< /reference >}}

{{% /post %}}
