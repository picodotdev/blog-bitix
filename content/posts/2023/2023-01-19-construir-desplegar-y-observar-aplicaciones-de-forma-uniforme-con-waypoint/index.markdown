---
pid: 670
type: "post"
title: "Construir, desplegar y observar aplicaciones de forma uniforme con Waypoint"
url: "/2023/01/construir-desplegar-y-observar-aplicaciones-de-forma-uniforme-con-waypoint/"
date: 2023-01-19T20:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hashicorp-waypoint.svg"
tags: ["gnu-linux", "planeta-codigo", "software", "programacion"]
series: ["hashicorp"]
summary: "Cuando se tienen muchas aplicaciones si no se tienen unos procesos definidos seguramente cada una requerirá su propio proceso, herramientas de construcción y despliegue. Estas diferencias añaden complejidad y no es deseable para tener un flujo de trabajo ágil. La herramienta Waypoint de HashiCorp permite construir una aplicación independientemente del lenguaje en el que esté implementada, desplegar en diferentes proveedores de computación en la nube y observar las aplicaciones una vez desplegadas, todo con la misma herramienta y de forma uniforme que simplifica en gran medida el flujo de desarrollo."
---

{{% post %}}

{{< logotype image1="hashicorp-waypoint.svg" image2="hashicorp.svg" >}}

No son pocas los lenguajes de programación actuales y hay varias plataformas de computación en la nube. En una organización grande seguramente haya una diversidad de aplicaciones en cuanto a lenguaje de programación e incluso quizá utiliza varios proveedores de computación en la nube, cuanto menos es deseable tratar a todas las aplicaciones igual independientemente de su lenguaje para que el flujo sea uniforme para todas las aplicaciones ni es bueno acoplarse a un determinado proveedor de computación para tener la libertad y flexibilidad en un futuro de cambiar a otro si las circunstancias cambian.

Las aplicaciones para se ejecución en su ciclo de vida esencialmente necesitan al menos dos operaciones, una es su construcción a partir de código fuente y la segunda es su despliegue en el entorno de ejecución. Hay una tercera adicional que es la de publicación consistente en enviarle tráfico, el despliegue simplemente consiste en ejecutar en el entorno pero sin tráfico para la versión desplegada, esto es, separando el despliegue de la publicación.

[HashiCorp][hashicorp] ofrece varias herramientas que cada una cubre una necesidad de las aplicaciones, y de las que varias ya he escrito algunos artículos. Algunas de sus herramientas más conocidas son [Terraform][terraform] para tratar la infraestructura como código, [Consul][consul] para la conectividad entre servicios y [Vault][vault] centrada en la seguridad como servicio pero tiene otras muy interesantes como [Vagrant][vagrant] para la virtualización en local, [Packer][packer] para la construcción de imágenes de máquinas virtuales, [Boundary][boundary] también el apartado de seguridad o [Nomad][nomad] una alternativa similar a [Kubernetes][kubernetes] pero mucho más sencilla de ejecutar en local.

En el caso de la construcción y despliegue, la herramienta que proporciona HashiCorp es [Waypoint][waypoint].

{{< tableofcontents >}}

## Waypoint

Waypoint de HashiCorp es una herramienta que permite construir una aplicación y realizar el despliegue independientemente del lenguaje empleado por la aplicación y la infraestructura de ejecución que se utilice.

Con Waypoint es posible construir cualquier aplicación ya se trate de una aplicación programada en [Java][java], [Node][nodejs] o [Python][python] y desplegarla en cualquier proveedor en la nube soportado como [Amazon Web Services][amazon-web-services], [Google Cloud Platform][google-cloud] o [Microsoft Azure][microsoft-azure].

El utilizar la misma herramienta uniformiza el ciclo de vida de las aplicaciones y tratan a todas por igual simplificando el flujo de desarrollo, de forma similar que una propiedad de los contenedores es que permiten ejecutar procesos independientemente del lenguaje de programación en el que están programados, Waypoint uniformiza la construcción y despliegue.

Waypoint en la [fase de construcción](https://developer.hashicorp.com/waypoint/docs/lifecycle/build) o _build_ en esencia lo que hace es construir una imagen de contenedor que luego es la que es desplegada. La construcción de la imagen puede ser con un habitual archivo Dockerfile de [Docker][docker] pero también a través de [Buildpacks][buildpacks]. Una vez construida la imagen es almacenada en un registro de imágenes. La fase de construcción suele estar automatizada por una herramienta de CI/CD como [Jenkins][jenkins], además se soportan otras como [GitHub Actions](https://developer.hashicorp.com/waypoint/docs/automating-execution/github-actions).

En la [fase de despliegue](https://developer.hashicorp.com/waypoint/docs/lifecycle/deploy) o _deploy_ la imagen es puesta en ejecución en la plataforma de despliegue seleccionada, se soportan varias incluyendo las más conocidas como Kubernetes, Nomad, AWS EC2, AWS ECS, AWS Lambdas, Google Cloud Run y Azure Containers Instances incluyendo Docker en local.

Si la plataforma de despliegue lo soporta la [fase de publicación](https://developer.hashicorp.com/waypoint/docs/lifecycle/release) o _release_ consiste en enviar tráfico a la aplicación.

* [Use Waypoint with Docker](https://developer.hashicorp.com/waypoint/tutorials/get-started-docker/get-started-docker)
* [Integrating Waypoint with GitHub Actions](https://developer.hashicorp.com/waypoint/docs/automating-execution/github-actions)

{{< youtube
    video="DsZVvv2dlUs" >}}

### Instalación de Waypoint

Al igual que muchas de las otras herramientas de HashiCorp que están implementadas con el lenguaje de programación [Go], Waypoint es un único binario con lo que basta descargar la última versión disponible y en el caso de [GNU][gnu]/[Linux][linux] copiarla al directorio _/usr/local/bin/_ donde los usuarios pueden instalar programas sin que entren en conflicto con los instalados por los paquetes de la distribución.

{{< code file="ls-bin.sh" language="bash" options="" >}}

### Inicio del servidor de Waypoint

Waypoint se ejecuta en modo servidor, en este ejemplo se inicia como un contenedor de Docker en local y ejecutar un comando para su inicialización.

{{< code file="waypoint-install.sh" language="bash" options="" >}}

Ofrece una interfaz gráfica en la que monitorizar trazas, builds, deployment y otros detalles. a la interfaz web se accede con el siguiente comando con la interfaz web accesible en la dirección _https://localhost:9702/_.

{{< code file="waypoint-ui-authenticate.sh" language="bash" options="" >}}

Una vez inicializado el servidor se puede iniciar sin necesidad de inicializarlo otra vez.

{{< code file="waypoint-server-run.sh" language="bash" options="" >}}

### Construcción de un proyecto con Waypoint utilizando Buildpacks

En un artículo anterior mostraba cómo construir una aplicación con el lenguaje Java de [Spring Boot][spring-boot] con [Gradle][gradle] y Buildpacks para generar la imagen del contenedor.

* [Construir la imagen del contenedor de la aplicación usando Buildpacks][blogbitix-669]

Para la construcción con Buildpacks utilizaba el siguiente comando que a través de argumentos configuraban los _buidpacks_ y construcción de la aplicación.

{{< code file="pack-build.sh" language="bash" options="" >}}

Utilizando el soporte de Buildpacks de Waypoint la definición de la construcción se especifican los mismos argumentos pero definidos en el archivo configuración de Waypoint. El comando _init_ inicializa el proyecto en Waypoint a partir del archivo de su definición.

{{< code file="waypoint-init.sh" language="bash" options="" >}}
{{< code file="waypoint.hcl" language="hcl" options="" >}}

En la documentación de Waypoint están especificados los argumentos que soporta cada uno de los _builders_.

* [pack (builder)](https://developer.hashicorp.com/waypoint/plugins/pack#pack-builder)
* [docker (builder)](https://developer.hashicorp.com/waypoint/plugins/docker#docker-builder)
* [docker (platform)](https://developer.hashicorp.com/waypoint/plugins/docker#docker-platform)

Para lanzar únicamente construcción se puede lanzar con el siguiente comando.

{{< code file="waypoint-build.sh" language="bash" options="" >}}
{{< code file="waypoint-build.out" language="plain" options="" >}}

En este ejemplo donde todo se muestra en local no hace falta especificar una _stanza_ _registry_ ya que Buildpacks de por sí ya añade al Docker local la imagen construida, haría falta en caso de querer enviar la imagen un un registro de imágenes y artefactos remoto.

La imagen construida no es más que una imagen de Docker normal que se lista con el comando de Docker.

{{< code file="docker-images.sh" language="bash" options="" >}}
{{< code file="docker-images.out" language="plain" options="" >}}

### Despliegue de la aplicación con Waypoint

En este ejemplo el despliegue consiste en ejecutar una instancia del contenedor con Docker en local, su _stanza_ es muy sencilla en la que solo se especifica el puerto en el que arrancar el el contenedor aunque también a través de diferentes propiedades se podrían especificar argumentos distintos.

El comando que realiza el _deploy_ es el siguiente. Y siguiendo la misma lógica hay un comando para el _release_ si fuera necesario.

{{< code file="waypoint-deploy-release.sh" language="bash" options="" >}}

Es posible hacer la construcción y despliegue con un único comando.

{{< code file="waypoint-up.sh" language="bash" options="" >}}

El servicio se inicia en la máquina y puede ser visto a través de la consola de Waypoint o con los comandos de Docker ya que en esencia no es más que una instancia de contenedor.

{{< code file="docker-ps.sh" language="bash" options="" >}}
{{< code file="docker-ps.out" language="plain" options="" >}}

La aplicación y su _endpoint_ _web_ responden al realizar una petición.

{{< code file="curl.sh" language="bash" options="" >}}
{{< code file="curl.out" language="plain" options="" >}}

### Interfaz web y de linea de comandos

La interfaz web de Waypoint accesible con un navegador ofrece información de los proyectos, _builds_, _deployments_ y _releases_ además de _logs_.

La linea de comandos también ofrece información acerca de la aplicación con los comandos de Waypoint sin utilizar la interfaz web. La línea de comandos soporta más operaciones como destruir los recursos de un proyecto o eliminar el proyecto completamente.

{{< code file="waypoint-deployment-list.sh" language="bash" options="" >}}
{{< code file="waypoint-deployment-list.out" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:waypoint.webp" optionsthumb1="650x450" title1="Interfaz web de Waypoint"
    caption="Interfaz web de Waypoint" >}}
{{< image
    gallery="true"
    image1="image:waypoint-builds.webp" optionsthumb1="200x150" title1="Builds"
    image2="image:waypoint-deployments.webp" optionsthumb2="200x150" title2="Deployments"
    image3="image:waypoint-releases.webp" optionsthumb3="200x150" title3="Relases"
    caption="Builds, deployments y releases de una aplicación en un proyecto" >}}

## Desarrollo en local

Para desarrollar en local todo este proceso de Buildpacks y Waypoint no es necesario, en el caso de la aplicación de ejemplo al estar basada en Spring Boot y utilizar Gradle es posible iniciarla con un comando directamente sin necesidad de construir una imagen de contenedor ni iniciar un contenedor en local.

{{< code file="gradlew-run.sh" language="bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/SpringInjectionPoint" command="./gradlew run" %}}

{{% /post %}}
