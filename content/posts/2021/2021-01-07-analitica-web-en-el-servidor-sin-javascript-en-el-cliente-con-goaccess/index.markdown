---
pid: 544
type: "post"
title: "Analítica web en el servidor sin JavaScript en el cliente con GoAccess"
url: "/2021/01/analitica-web-en-el-servidor-sin-javascript-en-el-cliente-con-goaccess/"
date: 2021-01-07T10:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:goaccess-web-1.png"
tags: ["planeta-codigo", "web"]
summary: "Google Analytics es la herramienta más utilizada para implementar la funcionalidad de analítica en un sitio web. Es fácil de usar, de implementar, no requiere mantenimiento de infraestructura propia, es gratuita, ofrece numerosas funcionalidades que ayudan enormemente a obtener información del sitio web, de los usuarios, obtener informes. Sin embargo, Google Analytics es una tercera parte con la que se comparte la información del sitio web que supone una pérdida de privacidad y protección de datos del sitio web y requiere instalar un _script_ de JavaScript que ralentiza la carga de las páginas web. GoAccess ofrece analítica web en tiempo real que al contrario que Analytics obtiene los datos únicamente desde el lado del servidor basándose en los archivos del log del servidor web, es software libre y es utilizable sin necesidad de involucrar a terceras partes."
---

{{% post %}}

{{< logotype image1="goaccess.png" >}}

La analítica web es una funcionalidad imprescindible en la mayoría de sitios públicos accesibles desde internet web incluso en aquellos privados. La analítica web es una funcionalidad que proporciona valiosa información acerca del sitio web, desde número de visitas en determinados periodos de tiempo así como información de los usuarios y la interacción de estos con el sitio web.

La información proporcionada por la analítica web ayuda a la toma de decisiones basadas en datos observando el comportamiento de los usuarios en el sitio web con métricas que permiten conocer cuales son las páginas más visitas, en cuales permanecen más tiempo, que versión de navegador web usan, cuales son los países de los usuarios, cuál es el tráfico por hora del día, las fuentes del tráfico, sitios web de referencia, tráfico según días de la semana, ciertas fechas señaladas, periodos de vacaciones u cualesquiera otras formas de conocimiento interesantes para el negocio.

{{< tableofcontents >}}

### Google Analytics, la herramienta más utilizada para hace analítica web

La herramienta más utilizada para hacer analítica web es [Google Analytics][google-analytics], es gratuita, fácil de instalar en un sitio web y tiene gran cantidad de funcionalidades básicas y avanzadas que cubren las necesidades de cualesquiera usuarios. Almacena la información recogida a lo largo del tiempo que es consultable con posterioridad incluso pasados varios años de forma muy flexible con diferentes criterios de búsqueda y filtros, también ofrece varios paneles para observar el tráfico en tiempo real.

{{< image
    gallery="true"
    image1="image:google-analytics.png" optionsthumb1="300x200" title1="Goolge Analytics"
    caption="Goolge Analytics" >}}

Basta con tener una cuenta de Google para tener acceso a Google Analytics. Para integrarlo en un sitio web únicamente requiere insertar el _script_ de JavaScript proporcionado por Google Analytics al configurar el sitio web en todas las páginas del sitio web. Este _script_ se ejecuta en el navegador del usuario, recopila información y la envía a Google para su procesamiento y generación de la analítica web.

{{< code file="google-analytics-script.xhtml" language="html" options="" >}}

El principal inconveniente de Google Analytics es introducir una tercera parte en la relación entre el sitio web y el usuario, una tercera parte que recoge métricas del sitio web e información de los usuarios. Los sitios web han de tener en cuenta que han de cumplir con las regulaciones y leyes como la [GDPR][wikipedia-gdpr] en Europa en el tratamiento de la información de los usuarios para para proteger la privacidad de los usuarios que algunas empresas ponen en riesgo si no fuera por esas leyes que impiden o ponen límites a la recopilación de datos bajo penas de importantes multas, utilizar _cookies_ de seguimiento que permiten identificar a los usuarios de forma inequívoca o el tratamiento de información personal. Leyes que afectan también a la propia Google junto a otras empresas como Amazon, Facebook o Twitter de EEUU con unas regulaciones más permisivas, las leyes les afecta por ejemplo impidiendo transferir los datos de los usuarios europeos fuera de Europa.

La privacidad del los usuarios ya es un buen motivo para utilizar una alternativa a Google Analytics. Otro motivo es que el _script_ de Google Analytics se ejecuta en el navegador del usuario y afecta en una pequeña medida al rendimiento en la carga de la página web.

Otros sitios web privados de una intranet u aquellos que tienen interés en proteger la privacidad de sus usuarios prefieren utilizar otras herramientas para realizar analítica del sitio web que no involucren terceras partes. 

### GoAccess, una alternativa de analítica

[GoAccess][goaccess] es una pequeña utilidad que cubre una pequeña parte de la funcionalidad de analítica de un sitio web, en concreto ofrece la analítica en tiempo real de uno o múltiples sitios web, [Matomo][matomo] es una [alternativa equivalente a Google Analytics][blogbitix-546] que ofrece las funcionalidades de _tracking_ de eventos y _tag manager_. Sus características principales son que es una herramienta sencilla y pequeña, no requiere insertar ningún _script_ en las páginas del sitio web, no involucra terceras partes y es software libre.

Para realizar la analítica web no requiere insertar ningún _script_ de JavaScript en el cliente lo que mejora el tiempo de carga de las páginas. La analítica la hace completamente en el lado del servidor analizando los archivos de _log_ de acceso y errores del servidor web. Soporta los servidores web más utilizados como [Nginx][nginx] y [Apache HTTPD][apache-httpd].

GoAccess permite obtener información de analítica en tiempo real del número de usuarios, que navegadores utilizan, URLs solicitadas, distribución de las peticiones en el tiempo, navegadores y sistemas operativos de los usuarios, sitios y URLs de referencia, palabras clave de búsqueda, códigos de estado HTTP, geolocalización, ... Ofrece dos interfaces distintas, una basada en una interfaz de terminal y otra basada en una interfaz web.

### Analítica de sitio web con GoAccess en un servidor web Nginx 

En el ejemplo de uso de GoAccess para realizar analítica web de un servidor web y acceder a las dos interfaces que ofrece utilizo la herramienta [Docker][docker] que evita tener que instalar estas herramientas de forma directa en la computadora, hace fácil su instalación, prueba, uso y eliminación de la computadora local una vez evaluada la herramienta. En el ejemplo el servidor web que he utilizado ha sido Nginx para Apache HTTPD se sigue el mismo principio de proporcionar a GoAccess los archivos de _log_ de acceso al servidor web.

Para realizar el ejemplo son de utilidad los siguientes otros artículos en los que me he apoyado para crear este.

* [Introducción y características de Docker][blogbitix-49]
* [Cómo crear una imagen para Docker usando un Dockerfile][blogbitix-51]
* [Aplicaciones multicontenedor con Docker Compose][blogbitix-87]
* [Cómo crear un proxy inverso entre el servidor web Nginx y un servidor de aplicaciones Java][blogbitix-159]
* [Configurar un servidor web virtual en Nginx y Apache][blogbitix-507]
* [Obtener un nombre de dominio para una dirección IP privada][blogbitix-504]

Lo primero es crear una imagen de Docker con GoAccess, la imagen oficial de Docker para GoAccess es [allinurl/goaccess](https://hub.docker.com/r/allinurl/goaccess), sin embargo esta no me ha funcionado y he tenido que crear una propia basada en [Ubuntu][ubuntu]. El archivo _Dockerfile_ para crear la imagen de Docker con GoAccess y el comando para construirla son los siguientes.

{{< code file="Dockerfile" language="dockerfile" options="" >}}
{{< code file="docker-build.sh" language="bash" options="" >}}

Una vez construida la imagen de GoAccess se almacena en el repositorio local de imágenes.

{{< code file="docker-images.sh" language="bash" options="" >}}
{{< code file="docker-images.out" language="bash" options="" >}}

Nginx también tiene su propia imagen de Docker, en este caso utilizo la imagen oficial del servidor web. Se requiere iniciar dos contenedores de Docker, uno para el servidor web Nginx con varios volúmenes de datos configurados enlazados con los directorios de la máquina anfitrión para proporcionarle los archivos de configuración para los servidores web virtuales de un sitio web a analizar, el servidor virtual de la interfaz web de GoAccess y para compartir con GoAccess los archivos de _log_ dal servidor web. El segundo contenedor es el de GoAccess utilizando la imagen propia también con varios volúmenes de datos configurados, entre ellos el del directorio con los archivos de _log_ de acceso del servidor web.

La configuración de Nginx incluye servidores web virtuales y configuración de _proxy_ con _WebSockets_ para ofrecer las métricas en tiempo real desde la interfaz web.

{{< code file="www.127.0.0.1.xip.io.conf" language="nginx" options="" >}}
{{< code file="goaccess.127.0.0.1.xip.io.conf" language="nginx" options="" >}}

En el [archivo de configuración de GoAccess](https://github.com/allinurl/goaccess/blob/master/config/goaccess.conf) o en la [configuración desde la linea de comandos](https://goaccess.io/man#configuration) se especifican varias propiedades de configuración.

{{< code file="goaccess.conf" language="plain" options="" >}}

Ambos contenedores están definidos en un archivo de [Docker Compose][docker-compose].

{{< code file="docker-compose.yml" language="yaml" options="" >}}
{{< code file="docker-compose-up.sh" language="bash" options="" >}}

Una vez los contenedores están en ejecución es posible acceder a las interfaces que ofrece GoAccess y observar las métricas que ofrece del acceso a los sitios web.

{{< code file="docker-ps.sh" language="bash" options="" >}}

#### Interfaz de terminal

La interfaz de terminal o consola no requiere herramientas adicionales, desde la propia terminal es posible acceder a la información de analítica. Hay que ejecutar el comando de GoAccess, al usar Docker desde dentro de su contenedor. GoAccess se puede iniciar individualmente para acceder a su interfaz basada en la terminal.

{{< code file="goaccess-run.sh" language="bash" options="" >}}

Es posible navegar entre los diferentes paneles de analítica pulsando varias teclas, la tecla de navegación están documentadas en una ventana de ayuda a la que se accede con la tecla _?_, en la ayuda están indicadas el reto de teclas de navegación en la interfaz.

{{< image
    gallery="true"
    image1="image:goaccess-terminal-1.png" optionsthumb1="300x200" title1="Interfaz consola de analítica GoAccess"
    caption="Interfaz consola de analítica GoAccess" >}}

#### Interfaz de navegador web

La interfaz de terminal no es la más cómoda de usar ni más accesible de forma remota, por ello GoAccess también proporciona una interfaz web accesible con un navegador web.

Para ello genera un archivo html de informe con los datos que se ha de servir desde un servidor web y ofrece un servidor con _WebSockets_ para las métricas en tiempo real. En el ejemplo su dirección de acceso con el navegador _http:\/\/goaccess.127.0.0.1.xip.io/report.html_, la interfaz se actualiza con analítica de acceso del servidor web Nginx del sitio web _http:\/\/www.127.0.0.1.xip.io/_.

{{< image
    gallery="true"
    image1="image:nginx.png" optionsthumb1="200x150" title1="Sitio web con Nginx"
    image2="image:goaccess-web-1.png" optionsthumb2="200x150" title2="Interfaz web de analítica GoAccess"
    image3="image:goaccess-web-2.png" optionsthumb3="200x150" title3="Interfaz web de analítica GoAccess"
    caption="Sitio web e interfaz web de analítica GoAccess" >}}

{{% sourcecode git="blog-ejemplos/tree/master/GoAccess" command="./docker-compose up" %}}

{{< reference >}}
* [Add analytics.js to Your Site](https://developers.google.com/analytics/devguides/collection/analyticsjs/)
{{< /reference >}}

{{% /post %}}
