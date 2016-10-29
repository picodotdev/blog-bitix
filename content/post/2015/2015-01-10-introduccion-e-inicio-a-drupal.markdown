---
pid: 60
title: "Introducción e inicio a Drupal"
url: "/2015/01/introduccion-e-inicio-a-drupal/"
date: 2015-01-10T11:08:12+01:00
updated: 2015-11-10T19:00:00+01:00
sharing: true
comments: true
tags: ["blog-stack", "gnu-linux", "planeta-codigo", "planeta-linux", "software", "software-libre"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="drupal.png" title="Drupal" >}}

Para los proyectos web cuyo punto central es el contenido disponemos de algunas opciones que prácticamente nos dan la solución hecha o facilitan enormemente la tarea como desarrolladores y posteriormente a los administradores que no necesitarán un gran conocimiento para administrar la web. Estas aplicaciones se les suele conocer como [Sistemas de gestión de contenidos][cms] (Content Management System, CMS) y como siempre disponemos de varias opciones, algunas similares.

No soy un experto en CMS ni he usado uno en un proyecto real pero trataré en este artículo hacer una introducción a [Drupal][drupal]. Hay otras opciones como [Wordpress][wordpress] o [Joomla][joomla] también desarrolladas en [PHP][php] y en el ámbito [Java][java] otras como [Alfresco][alfresco], [Nuxeo][nuxeo], [dotCMS][dotcms] y [Liferay][liferay] aunque estas en algunos puntos son diferentes ofreciendo gestión documental o [portales de internet][portal-internet]. Wordpress es más simple que Drupal pero si tenemos conocimientos de desarrollo Drupal posiblemente se adapte más a nuestras preferencias. Alfresco y Nuxeo son más bien gestores de contenido y de documentos pero no permiten gestionar como se visualiza el contenido en la web final, Liferay es algo más parecido a lo que ofrece Drupal pero tengo la sensación que es algo más compleja o no exactamente con las mismas opciones y está más destinado a la creación de portales web.

Drupal como decía está desarrollado sobre PHP pero solo necesitaremos conocer este lenguaje en el momento que queramos desarrollar y personalizarlo. Como usuarios para crear contenido y gestionar la disposición de los elementos en la página web no necesitamos conocer nada de PHP solo necesitaremos conocer como funciona la herramienta de administración que ofrece Drupal. El tipo de los proyectos para los que puede estar indicado Durpal puede ser aquellos en los que los usuarios quieran crear y publicar nuevo contenido y quieran ellos mismos modificar los elementos de las páginas sin necesidad de solicitar un desarrollo. Algunos casos de uso pueden ser instituciones de la administración pública, empresas, tiendas, comercios, ... aquellos que quieran tener una presencia en internet y puedan necesitar añadir, modificar o eliminar en algún momento el contenido de su web. La tarea de los desarrolladores consistirá en personalizar la instalación de Drupal a las necesidades del cliente y sus usuarios ya sea adaptando el aspecto de la web a crear, creando la taxonomía del contenido, definiendo los tipos de contenidos y que información contiene, usuarios y roles, ...

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/60/inicio.png" title="Inicio Drupal" data-gallery><img src="assets/images/custom/posts/60/inicio-thumb.png"></a>
	<a href="assets/images/custom/posts/60/inicio-administracion.png" title="Inicio Drupal (Administración)" data-gallery><img src="assets/images/custom/posts/60/inicio-administracion-thumb.png"></a>
</div>

Por defecto Drupal ofrece dos tipos de contenido páginas y artículos que poseen un título y un cuerpo aunque podemos crear nuevos tipos de contenido con información adicional como podría ser eventos que recojan además una fecha y hora de inicio, fecha y hora de fin, lugar, .... Podemos crear usuarios y roles y asignarles permisos como poder crear contenidos, crear menús con enlaces a ciertos contenidos que queramos destacar. Podemos usar taxonomía para categorizar el contenido y que este esté organizado y sea fácilmente encontrado por los usuarios. Asociar tipos de contenido con ciertos tipo de taxonomía. Modificar los bloques como menús, formulario de inicio de sesión, ... y hacer que solo se vean en ciertas páginas, para algunos tipos de contenidos, ciertos roles o usuarios. Hacer vistas o listas de los contenidos. Modificar la disposición o layout de las páginas por ejemplo para que tenga dos, tres columnas, una cabecera y un pie, .... Finalmente también podemos modificar y personalizar el aspecto del sitio web con temas que podemos descargar o desarrollar nosotros mismos.

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/60/crear-pagina.png" title="Crear página" data-gallery><img src="assets/images/custom/posts/60/crear-pagina-thumb.png"></a>
	<a href="assets/images/custom/posts/60/crear-articulo.png" title="Crear artículo" data-gallery><img src="assets/images/custom/posts/60/crear-articulo-thumb.png"></a>
	<a href="assets/images/custom/posts/60/tipos-contenido.png" title="Tipos de contenido" data-gallery><img src="assets/images/custom/posts/60/tipos-contenido-thumb.png"></a>
	<a href="assets/images/custom/posts/60/taxonomia.png" title="Taxonomía" data-gallery><img src="assets/images/custom/posts/60/taxonomia-thumb.png"></a>
	<a href="assets/images/custom/posts/60/taxonomia-etiquetas.png" title="Etiquetas taxonomía" data-gallery><img src="assets/images/custom/posts/60/taxonomia-etiquetas-thumb.png"></a>
	<a href="assets/images/custom/posts/60/bloques.png" title="Bloques" data-gallery><img src="assets/images/custom/posts/60/bloques-thumb.png"></a>
	<a href="assets/images/custom/posts/60/personas.png" title="Personas" data-gallery><img src="assets/images/custom/posts/60/personas-thumb.png"></a>
	<a href="assets/images/custom/posts/60/personas-permisos.png" title="Permisos personas" data-gallery><img src="assets/images/custom/posts/60/personas-permisos-thumb.png"></a>
	<a href="assets/images/custom/posts/60/personas-roles.png" title="Roles personas" data-gallery><img src="assets/images/custom/posts/60/personas-roles-thumb.png"></a>
</div>

Drupal es ampliamente [extensible mediante módulos](https://www.drupal.org/project/project_module) permitiendo agregarle funcionalidades de diferentes categorías (contenido, administración, integración con terceros, comercio electrónico, ...) si las incorporadas por defecto no nos son suficientes. Algunos de  los módulos más empleados son [Panels](https://www.drupal.org/project/panels) que permite definir contenedores (barra izquierda, región contenido, barra derecha, pie, cabecera, ...) en una página y asignar el contenido que queremos que aparezca en cada uno de ellos, [Content Construction Kit (CCK)](https://www.drupal.org/project/cck), [Views](https://www.drupal.org/project/views) para definir vistas de una colección de elementos, [Pathauto](https://www.drupal.org/project/pathauto) para hacer las URLs amigables al usuario y buscadores mejorando el SEO del sitio, [Webform](https://www.drupal.org/project/webform) para crear formularios y recoger información y otros muchos más para infinidad de cosas. Sin embargo, conviene usar los más populares que están ampliamente probados y hayan demostrado su utilidad, los que tenga buen soporte y un mantenimiento activo, estas cosas las podemos seleccionar con el filtro en el formulario de búsqueda de módulos.

En definitiva Drupal es una herramienta de mucha utilidad para gestionar un sitio web. Si la parte importante del sitio web es el contenido Drupal puede adaptarse perfectamente a las necesidades y si el sitio web ha de ser administrado por usuarios sin conocimientos técnicos puede ser una solución que no requiera desarrollar una aplicación web a medida costando menos tiempo y menos esfuerzo. Para iniciarse en Drupal los libros <a href="http://www.amazon.es/gp/product/1430228598/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=1430228598&linkCode=as2&tag=blobit-21">Beginning Drupal 7</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=1430228598" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;">
 y <a href="http://www.amazon.es/gp/product/1430228385/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=1430228385&linkCode=as2&tag=blobit-21">Pro Drupal 7 Development</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=1430228385" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;">
 son un buen inicio, he leído el primero y están muy bien explicado partiendo desde cero conocimiento sobre Drupal o PHP, contiene suficiente detalle para conocer las diferentes posibilidades que ofrece esta plataforma de gestión de contenidos.

<div class="media-amazon" style="text-align: center;">
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=1430228598&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
	<iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=1430228385&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
</div>

Si queremos probarlo posiblemente la solución mas sencilla es usar la [imagen que ofrece Bitnami de Drupal](https://bitnami.com/stack/drupal) que contiene todos los requisitos de Drupal ya instalados (servidor web, base de datos, dependencias, ...), podemos descargarla y crear una máquina virtual con VirtualBox, en unos pocos minutos podemos empezar a probar Drupal. En el artículo de [introducción sobre Bitnami][blogbitix-54] explico como crear una máquina virtual e iniciar cualquiera de las [imágenes](https://bitnami.com/stacks) de las que ofrece [Bitnami][bitnami] con VirtualBox.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Introducción a Bitnami][blogbitix-54]
* [Introducción al gestor de proyectos y tareas Redmine][blogbitix-57]
* [Instalar y usar un plugin ágil en Redmine][blogbitix-59]
{{% /reference %}}

{{% /post %}}
