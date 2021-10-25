---
pid: 600
type: "post"
title: "Contenedores en macOS con Lima y minikube"
url: "/2021/09/contenedores-en-macos-con-lima-y-minikube/"
date: 2021-09-25T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:containerd.svg"
tags: ["gnu-linux", "planeta-codigo", "apple"]
series: ["docker"]
summary: "Para ejecutar contenedores en macOS hay dos opciones entre las posibles, una usar minikube que aunque su objetivo es ejecutar un _cluster_ de Kubernetes permite usarlo para ejecutar contenedores de Docker directamente, la segunda opción es lime que es una opción similar que usa containerd en vez de Docker. La empresa encargada del desarrollo de Docker ha anunciado que el producto Docker Desktop pasa a requerir una licencia de pago para organizaciones empresariales grandes. Docker Desktop es usado tanto en Windows como en macOS para ejecutar contenedores de Docker con la opción también de ejecutar un _cluster_ de Kubernetes, minikube y lima para macOS son una alternativa a Docker Desktop."
---

{{% post %}}

{{< logotype image1="minikube.svg" image2="containerd.svg" image3="docker.svg" >}}

[Docker][docker] es una tecnología para ejecutar procesos en contenedores nativa de [GNU][gnu]/[Linux][linux], los contenedores en Linux son simplemente un proceso más del sistema no utilizan virtualización con lo que requieren menos recursos, se inicia mucho más rápido que una máquina virtual y están aislados de otros procesos de sistema. Al usar tecnologías específicas de Linux no es posible ejecutarlo de forma nativa ni en [Windows][windows] ni en [macOS][macos], en estos para usar contenedores hay que recurrir a la virtualización. Aún siendo una tecnología de GNUL/Linux hay necesidad de ejecutarlo en otros sistemas operativos para obtener los mismos beneficios aún siendo con virtualización.

La empresa propietaria y encargada del desarrollo de Docker ha anunciado que [Docker Desktop][docker-desktop] usada para ejecutar Docker en Windows y macOS va a ser una opción de pago si se usa en un ámbito profesional en grandes organizaciones, para uso personal continúa siendo gratuito en su versión [Docker Personal][docker-personal].

* [Docker Updating Product Subscription](https://www.docker.com/blog/updating-product-subscriptions/)

> Docker Desktop is free to use, as part of the Docker Personal subscription, for individuals, non-commercial open source developers, students and educators, and small businesses of less than 250 employees AND less than $10 million in revenue. Commercial use of Docker Desktop at a company of more than 250 employees OR more than $10 million in annual revenue requires a paid subscription (Pro, Team, or Business) to use Docker Desktop.

Mucho del software de código abierto y libre es gratuito, sin embargo, es mantenido en algunos casos por personas en su propio tiempo personal con poco soporte económico. En el caso de Docker Desktop la empresa propietaria seguramente ha tomado esta medida como una vía para monetizar y financiar el desarrollo de Docker. Dada la popularidad que Docker ha adquirido en los últimos años muchas empresas basan de forma importante sus entornos de producción o desarrollo en Docker.

Docker Desktop permite ejecutar contenedores de Docker en sistemas distintos a GNU/Linux como macOS y Windows, incluye otras herramientas como [Docker Compose][docker-compose] y se integra con [Docker Hub][docker-hub] para almacenar imágenes, también integra una versión de [Kuberntes][kubernetes] para orquestar contenedores.

El cambio en el acuerdo de uso de Docker Desktop obliga a los usuarios de Apple macOS que no cumplan los criterios para usar la suscripción _Personal_ han de pagar por su uso o buscar una alternativa. Para uso personal uso la distribución [Arch Linux][archlinux] pero por política de empresa en el trabajo estoy obligado a usar macOS con lo que he tenido que buscar una alternativa para seguir usando Docker. En el caso de macOS dos de las opciones como alternativa a Docker Desktop son [minikube][minikube] y [Lima][lima].

{{< tableofcontents >}}

### Contenedores de Docker y Kuberntes con Minikube en macOS

minikube tiene el objetivo de crear un _cluster_ de Kubernetes rápidamente en GNU/Linux, Windows y macOS, pero dado que usa Docker el comando _docker_ puede interaccionar con la instancia de minikube. Docker es una herramienta nativa de GNU/Linux en el caso de macOS y Windows usa virtualización, en estos dos sistemas operativos Docker se ejecuta en una máquina virtual con GNU/Linux y se proporcionan una serie de integraciones para el reenvío de puertos entre la máquina anfitrión y la máquina virtual con los contenedores.

* [Documentación de minikube](https://minikube.sigs.k8s.io/docs/)

[El gestor de paquetes homebrew para macOS][blogbitix-195] permite instalar fácilmente minikube simplemente con un comando. minikube y Kubernetes utilizan Docker para la ejecución de contenedores, aunque el propósito de minikube es ejecutar aplicaciones en contenedores permite el uso de las herramientas de línea de comandos _docker_ y _docker-compose_.

{{< code file="minikube-install.sh" language="bash" options="" >}}

Una vez instalado minikube hay que iniciar la máquina virtual con el siguiente comando.

{{< code file="minikube-start.sh" language="bash" options="" >}}

Para que la herramienta de línea de comandos interaccione con el servidor de Docker en la máquina virtual de minikube el siguiente comando permite establecer varias variables de entorno.

{{< code file="minikube-docker-env.sh" language="bash" options="" >}}

Hecha la configuración previa iniciar un contenedor de Docker es exactamente igual que en GNU/Linux de forma nativa, en realidad el comando docker interacciona con el servidor de Docker en la máquina virtual. Por otro lado dado que los contenedores de Docker se ejecutan dentro de la máquina virtual y los puertos se exponen en ella es necesario acceder a la máquina virtual es con su dirección IP, dirección IP proporcionada por el siguiente comando y si se desea añadirla al archivo _/etc/hosts_ con un nombre de dominio.

{{< code file="minikube-docker-run.sh" language="bash" options="" >}}
{{< code file="minikube-ip.sh" language="bash" options="" >}}

Para destruir la máquina virtual que usa minikube hay otro comando.

{{< code file="minikube-delete.sh" language="bash" options="" >}}

### Contenedores de Docker con Lima en macOS

Aunque minikube permite ejecutar contenedores de Docker directamente su propósito es ejecutar recursos de Kubernetes como _pods_ en vez de directamente contenedores. Para el caso de simplemente contenedores de Docker minikube no ofrece reenvío de puertos y compartición de archivos entre la máquina virtual y local que requiere hacer el paso adicional del comando _minikube-ip.sh_.

Otra opción en macOS alternativa a Docker en macOS es Lima, denominada a sí misma como _macOS subsystem for Linux_ parafraseando el _Windows subsystem for Linux_ o WSL de Windows. El funcionamiento de Lima es también a través de una máquina virtual en la que se ejecutan los contenedores, la ventaja de Lima es que ofrece compartición de archivos entre la máquina local y la virtual y reenvío de puertos de forma automática de modo que la experiencia de uso es similar a Docker en GNU/Linux.

Lima como entorno de ejecución de contenedores utiliza [containerd][containerd], pero dado que las imágenes de los contenedores de Docker de DockerHub siguen el estándar OCI son compatibles con _containerd_ lo que permite usar exactamente las mismas imágenes.

Lima se instala también con un comando usando homebrew.

{{< code file="lima-install.sh" language="bash" options="" >}}

La máquina virtual con GNU/Linux y docker en la que se ejecutan los contenedores con el siguiente comando.

{{< code file="lima-start.sh" language="bash" options="" >}}

Para ejecutar un contenedor el comando a usar es _lima nerdctl_, el resto de opciones y parámetros son los mismos a los habituales a los del comando _docker_ están en la [documentación de comandos de nerdctl](https://github.com/containerd/nerdctl#command-reference). En este caso se inicia un contenedor del servidor web [Nginx][nginx] que se se accede con la dirección _http:\/\/localhost:8080_.

{{< code file="lima-run.sh" language="bash" options="" >}}

Para destruir la máquina virtual que usa lima hay otro comando.

{{< code file="lima-delete.sh" language="bash" options="" >}}

_nerdctl_ soporta muchos de los subcomandos de _docker_ tanto para la ejecución de contenedores, gestión, construcción, gestión de imágenes, gestión de volúmenes y de Docker Compose.

{{< image
    gallery="true"
    image1="image:lima-nerd-images.png" optionsthumb1="300x200" title1="Imágenes de contenedores con lima en macOS"
    image2="image:lima-nerd-run.png" optionsthumb2="300x200" title2="Ejecución de un contenedor con lima en macOS"
    caption="Ejecución de un contenedor con lima en macOS" >}}

{{< reference >}}
* [Docker Developer Tools](https://www.docker.com/products/developer-tools)
* [containerd & Lima: Open source alternative to Docker for Mac](https://medium.com/nttlabs/containerd-and-lima-39e0b64d2a59)
* [Run Docker without Docker Desktop on macOS](https://dhwaneetbhatt.com/blog/run-docker-without-docker-desktop-on-macos)
* [Docker Kubernetes](https://www.docker.com/products/kubernetes)
* [Free Docker Desktop Alternative For Mac And Windows](https://devopstales.github.io/home/docker-desktop-alternatives/)
* [Replacing Docker Desktop with hyperkit + minikube](https://www.javacodegeeks.com/2021/09/replacing-docker-desktop-with-hyperkit-minikube.html)
{{< /reference >}}

{{% /post %}}
