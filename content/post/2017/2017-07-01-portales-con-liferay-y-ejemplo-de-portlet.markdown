---
pid: 243
title: "Portales con Liferay y ejemplo de portlet"
url: "/2017/07/portales-con-liferay-y-ejemplo-de-portlet/"
date: 2017-07-01T12:00:00+02:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
summary: "Muchas organizaciones usan portales para mantener su presencia en internet. Los portales son herramientas muy versátiles que incluyen la gestión de contenidos y flujo de trabajo para publicarlo, foros, blog, ... Liferay es uno de los más conocidos que usa la plataforma Java. La unidad básica funcional de un portal es un _portlet_ que en ciertos aspectos son similares en otros diferentes a lo que son los servlets en las aplicaciones web Java."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="liferay.png" title1="Liferay" width1="400" image2="java.svg" title2="Java" width2="200" >}}

Los portales son una especialización de un sitio web que presenta información diversa de una forma integrada y uniforme. Suelen aplicarse cuando una entidad tiene necesidades de presentar información según el usuarios autenticado, su rol, los usuarios necesitan colaborar o se necesita integrar información de múltiples fuentes. Son usados por entidades públicas como gobiernos, ayuntamientos y también por corporaciones de tamaño mediano y grande.

Algunos de sus casos de uso son:

* [Plataforma web](https://web.liferay.com/es/products/what-is-a-portal/web-platform)
* [Sistema de gestión de contenidos](https://web.liferay.com/es/products/what-is-a-portal/enterprise-cms)
* [Plataforma de integración](https://web.liferay.com/es/products/what-is-a-portal/integration-platform)
* [Plataforma de colaboración](https://web.liferay.com/es/products/what-is-a-portal/collaboration-platform)
* [Plataforma de aplicaciones sociales](https://web.liferay.com/es/products/what-is-a-portal/social-apps-platform)

Uno de los servidores de portales más destacados es [Liferay][liferay]. Liferay implementa la especificación de los [_portlets_](https://es.wikipedia.org/wiki/Portlet) de Java que son la pieza básica funcional de un portal.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="243"
        image1="inicio.png" thumb1="inicio-thumb.png" title1="Página inicial de Liferay" >}}
</div>

Liferay es el contenedor de _portlets_ y proporciona un entorno de ejecución similar a lo que los contenedores de servlet proporcionan para los servlets. Las similitudes y diferencias entre un servlet y un _portlet_ son las siguientes:

* Los _portlets_ son gestionados por un contenedor.
* Su ciclo de vida está gestionado por el contenedor.
* Generan contenido dinámico.
* Interactúan con el cliente mediante peticiones y respuestas.

Y se diferencia en que:

* Los _portlets_ generan únicamente un fragmento de la página web.
* No están asociados directamente a una URL.
* No pueden generar contenido arbitrario, si se solicita text/html los _portlets_ deben generar text/html.

El contenedor de _portlets_ proporciona funcionalidades como:

* Almacenamiento persistente para las preferencias.
* Procesamiento de solicitudes.
* Modos de los _portlets_.
* Estado de la ventana o fragmento.
* Información de usuario,

[Liferay incluye más de 60 _portlets_](https://web.liferay.com/es/community/wiki/-/wiki/Main/Liferay+Portlets) listos para usar que cumplen las funciones de <abbr title="Content Management System">CMS</abbr>, foros, blogs, agregador de blogs, wiki, calendario, encuestas, anuncios, herramientas sociales, de comercio electrónico, integración de contenido de sistemas externos, geolocalización, tiempo, administración, gestión de flujo de trabajo y [otros muchos más ofrecidos en el marketplace](https://web.liferay.com/marketplace).

Desde la [página de descargas](https://www.liferay.com/es/downloads) se puede obtener la edición para la comunidad de Liferay además de otros productos eligiendo la versión deseada y en la [red para desarrolladores](https://dev.liferay.com/es/home) obtener documentación y material de referencia. Una vez descargado el archivo de la distribución de Liferay y descomprimido se inicia con el comando ubicado en _tomcat-8.0.32/bin/startup.sh_. En el archivo _tomcat-8.0.32logs/catalina.out_ se emiten las trazas y mensajes del servidor. Iniciado Liferay se presenta una página de configuración, se han de aceptar los términos y condiciones e iniciar sesión con el usuario creado en la primera página de configuración.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="243"
        image1="configuracion.png" thumb1="configuracion-thumb.png" title1="Configuración básica de Liferay" >}}
</div>

Para añadir un _portlet_ propio a Liferay hay que acceder al _Panel de control > Aplicaciones > Gestor de aplicaciones_ y pulsar la opción cargar ubicada en la parte superior derecha de la página. En la salida del servidor aparecerán varias trazas relativas al despliegue del _portlet_.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="243"
        image1="gestor-de-aplicaciones.png" thumb1="gestor-de-aplicaciones-thumb.png" title1="Gestor de aplicaciones"
        image2="instalar-aplicacion.png" thumb2="instalar-aplicacion-thumb.png" title2="Instalar aplicación" >}}
</div>

Los _portlets_ se distribuyen por lo general como archivos de aplicaciones web _.war_ con varios descriptores adicionales con información que usa Liferay para el despliegue del _portlet_. En el siguiente ejemplo comentaré cómo crear un _portlet_ Hola Mundo sin ayuda de ningún framework como [Spring][spring] o [Apache Tapestry][tapestry] aunque Liferay proporciona ayuda y documentación para desarrollarlos con [Liferay MVC Portlet](https://dev.liferay.com/es/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet) o [Spring MVC](https://dev.liferay.com/es/develop/tutorials/-/knowledge_base/7-0/spring-mvc). El archivo principal es _portlet.xml_  donde se describen los _portlets_ de la aplicación indicando por ejemplo el nombre del _portlet_, la clase que lo implementa o los modos que soporta, otros archivos descriptores son _web.xml_, _liferay-portlet.xml_ y _liferay-display.xml_ con unas propiedades exclusivas de Liferay indicando el icono y la categoría en la que ubicar el _portlet_ en la paleta de _portlets_.

{{< gist picodotdev 0549b3b333adf38fc3be5a2ba6cfee45 "portlet.xml" >}}
{{< gist picodotdev 0549b3b333adf38fc3be5a2ba6cfee45 "web.xml" >}}
{{< gist picodotdev 0549b3b333adf38fc3be5a2ba6cfee45 "liferay-portlet.xml" >}}
{{< gist picodotdev 0549b3b333adf38fc3be5a2ba6cfee45 "liferay-display.xml" >}}

Un _portlet_ es una clase Java que extiende de [GenericPortlet](https://docs.liferay.com/portlet-api/2.0/javadocs/javax/portlet/GenericPortlet.html). En el caso del ejemplo es muy sencillo ya que solo emite un mensaje usando una preferencia de configuración que Liferay se encarga de persistir, tiene un modo de edición y procesa una acción para cambiar el valor de una preferencia que se utiliza al emitir el mensaje.

{{< gist picodotdev 0549b3b333adf38fc3be5a2ba6cfee45 "HolaMundoPortlet.java" >}}

Usando como herramienta de construcción del proyecto [Gradle][gradle] el archivo _.war_ a desplegar el Liferay se genera con la tarea _build_ en el directorio _build/libs/HolaMundoPortlet-0.1.war_. Esta archivo hay que desplegarlo en Liferay para posteriormente incluirlo en alguna página, se visualice el contenido que genera y se pueda interactuar con él.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="243"
        image1="anadir-portlet.png" thumb1="anadir-portlet-thumb.png" title1="Añadir portlet"
        image2="inicio-hola-mundo-portlet.png" thumb2="inicio-hola-mundo-portlet-thumb.png" title2="Portlet HolaMundo" >}}
    {{< figure year="2017" pid="243"
        image1="preferencias.png" thumb1="preferencias-thumb.png" title1="Preferencias del portlet"
        image2="preferencias-usuario.png" thumb2="preferencias-usuario-thumb.png" title2="Portlet HolaMundo con preferencias" >}}
</div>

Aunque el libro [Liferay in Action](http://amzn.to/2sc1tWN) no está actualizado a la última versión de Liferay sirve para conocer los conceptos básicos de su funcionamiento, explica la teoría e incluye ejemplos de código de como crear un _portlet_.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=193518282X&linkId=a4fdbe9a2a0c68685cfc8119f253125b"></iframe>
</div>

{{% code git="blog-ejemplos/tree/master/HolaMundoPortlet" command="./gradlew build" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Web portal](https://en.wikipedia.org/wiki/Web_portal)
* [Portlet](https://es.wikipedia.org/wiki/Portlet)
* [¿Qué es un Portal?](https://web.liferay.com/es/products/what-is-a-portal/web-platform)
* [Portlet Specification 3.0](https://jcp.org/aboutJava/communityprocess/edr/jsr362/index2.html)
{{% /reference %}}

{{% /post %}}
