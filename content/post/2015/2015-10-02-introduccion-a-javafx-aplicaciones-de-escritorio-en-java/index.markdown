---
pid: 100
title: "Introducción a JavaFX, aplicaciones de escritorio en Java"
url: "/2015/10/introduccion-a-javafx-aplicaciones-de-escritorio-en-java/"
date: 2015-10-02T19:00:00+02:00
updated: 2015-11-10T19:00:00+01:00
rss: true
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
summary: "Java tiene una fuerte presencia en el ámbito de desarrollo para aplicaciones web, más recientemente ha sido empleado por la plataforma Android. En la misma medida que han crecido las aplicaciones web y móviles nativas las aplicaciones de escritorio han perdido presencia, sin embargo, para ciertos casos son la opción adecuada. Con la aparición de JavaFX aportando varias mejoras sobre Swing o AWT en la plataforma Java se pueden desarrollar aplicaciones de escritorio multiplataforma «ricas». Podemos empezar por el ejemplo Hola Mundo de JavaFX de este artículo."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="java.svg" title="Java" width="200" >}}

El lenguaje de programación Java es ampliamente usado en el ámbito empresarial destacando la programación de aplicaciones web. Las aplicaciones web se han extendido tanto por su fácil despliegue a los clientes (un navegador basta), un desarrollo no excesivamente complicado, fácil actualización (solo hace falta actualizar la aplicación en el servidor) y su seguridad tanto para el cliente (al ser el navegador el marco que limita las acciones de la aplicación) como para el servidor (al proteger los accesos a la base de datos por ejemplo). Sin embargo, las aplicaciones web también tienen algunas limitaciones a pesar de que mejoran con cada nueva versión de <abbr title="HyperText Markup Language">HTML</abbr>, <abbr title="Cascading Style Sheets">CSS</abbr>, JavaScript así como de los propios navegadores a medida que evolucionan incorporando nuevas APIs como el manejo de vídeo, audio e incluso aceleración 3D. Las aplicaciones web cada vez son más «ricas» pero aún no han llegado a las capacidades de las aplicaciones de escritorio tradicionales y en algún caso puede ser la solución elegida. En Java primeramente teníamos [<abbr title="Abstract Window Toolkit">AWT</abbr>][awt], luego [Swing][swing] y ahora [JavaFX][javafx] con mejoras notables. Las aplicaciones web también tiene el problema de que si el servidor no está disponible o la conexión de red se pierde salvo que la aplicación esté preparada (como Google Docs) para ello será imposible usarla.

Algunas de las características notables de JavaFX son:

* _Java APIs_: las APIs están escritas en código nativo Java compatibles con otros lenguajes soportados por la máquina virtual.
* _FXML and Scene Builder_: <abbr title="FX Markup Language">FXML</abbr> es un lenguaje de marcado que describe las interfaces de usuario. Se pueden escribir directamente o usar la herramienta [JavaFX Scene Builder](https://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html) para crearlos con una interfaz gráfica.
* _WebView_: permite embeber páginas HTML en las aplicaciones JavaFX. Ofrece soporte para JavaScript.
* _Built-in UI controls and CSS_: proporciona cantidad de controles para construir aplicaciones completas. El estilo de los controles puede ser modificado con CSS.
* _Canvas API_: para dibujar directamente en la pantalla.
* _Multitouch Support_: soporte para gestos táctiles múltiples en función de las posibilidades de la plataforma subyacente.
* _Hardware-accelerated graphics pipeline_: haciendo uso de la GPU se consiguen animaciones gráficas fluidas en las tarjetas gráficas soportadas, si la gráfica no está soportada de hace uso de la pila de software Java2D.
* High-performance media engine: soporta la reproducción de contenido multimedia con baja latencia basándose en [GStreamer][gstreamer].
* _Self-contained application deployment model_: las aplicaciones contenidas tiene todos los recursos y una copia privada de los entornos de ejecución de Java y JavaFX. Son distribuidos como paquetes instalables y proporcionan la misma experiencia de instalación e inicio que las aplicaciones nativas del sistema operativo.

A continuación mostraré los requerimientos y entorno que deberemos tener para realizar el ejemplo _Hola Mundo_ que consistirá en una aplicación con una ventana que mostrará un botón. Si usamos el JDK 8 de Oracle, JavaFX ya está incorporporado y podremos usarlo sin más requerimientos además de las [otras muchas novedades incorporadas en Java 8][blogbitix-17]. En el caso de [Linux][linux] y más concretamente en [Arch Linux][archlinux], aunque será similar otros sistemas operativos, usando el paquete _openjdk_ deberemos instalar además el paquete _java-openjfx_. En Arch Linux si no tenemos instalado el paquete _java-openjfx_ obtendremos en la terminal un mensaje no muy descriptivo similar a:

{{< code file="no-java-openjfx.txt" language="plaintext" options="" >}}

En el [repositorio de GitHub del ejemplo de JavaFX](https://github.com/picodotdev/blog-ejemplos/tree/master/HolaMundoJavaFX) está el código fuente. Usando [Gradle][gradle] como herramienta automatizada de construcción y descargado el código podemos lanzar la aplicación de ejemplo con el comando de _run.sh_.

{{< code file="build.gradle" language="Groovy" options="" >}}
{{< code file="run.sh" language="bash" options="" >}}

La clase [HelloWorld](https://github.com/picodotdev/blog-ejemplos/blob/master/HolaMundoJavaFX/src/main/java/io/github/picodotdev/javafx/HelloWorld.java) extiende de [Application](https://docs.oracle.com/javafx/2/api/javafx/application/Application.html) y en el método _start_ definimos los controles de lo que será la escena (la ventana), principalmente un botón con un manejador de evento a ejecutar al ser pulsado.

{{< code file="HelloWorld.java" language="java" options="" >}}

Esta es la captura de pantalla de la aplicación y el mensaje emitido en la consola cuando se hace clic en el botón.

<div class="media" style="text-align: center;">
    {{< figure
        image1="helloworld.png" thumb1="helloworld-thumb.png" title1="Aplicación ¡Hola Mundo!"
        caption="Aplicación ¡Hola Mundo! con JavaFX" >}}
</div>

{{< code file="System.out" language="plaintext" options="" >}}

En las [demostraciones y ejemplos de Java 8](https://www.oracle.com/technetwork/java/javase/downloads/index.html) hay unos cuantos referentes a JavaFX bastante completos. Iniciando la aplicación contenida en _Ensemble.jar_ podemos ver numerosas funcionalidades (animación, efectos visuales, gráficas, controles, gráficos 2D, gráficos 3D, lenguaje, disposición, multimedia, ...), también con la aplicación _Modena.jar_ podemos ver como son visualmente por defecto los diferentes elementos gráficos de JavaFX.

{{< code file="examples.sh" language="bash" options="" >}}

<div class="media" style="text-align: center;">
    {{< figure
        image1="ensemble.png" thumb1="ensemble-thumb.png" title1="Aplicación Ensemble"
        caption="Aplicación Ensemble" >}}
    {{< figure
        image1="modena.png" thumb1="modena-thumb.png" title1="Aplicación Modena"
        caption="Aplicación Modena" >}}
</div>

El ejemplo de este artículo es muy sencillo y solo sirve para disponer de un entorno de desarrollo. Aunque no lo leído en el libro <a href="https://www.amazon.es/gp/product/1118385349/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=1118385349&linkCode=as2&tag=blobit-21">JavaFX For Dummies</a><img src="https://ir-es.amazon-adsystem.com/e/ir?t=blobit-21&l=as2&o=30&a=1118385349" width="1" height="1" border="0" alt="" style="border:none !important; margin:0px !important;" /> explican bastante detalladamente y de forma didáctica muchas posibilidades de JavaFX y como en el resto de libros de la serie _for dummies_ paso a paso y de forma simple sin suponer mucho conocimiento del lector.

<div class="media-amazon" style="text-align: center;">
    <iframe src="https://rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=1118385349&internal=1" style="width:120px;height:240px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
</div>

Hace ya mucho tiempo cuando programaba alguna aplicación de escritorio con Swing una de las cosas más complicadas era construir la interfaz usando código, el código resultante era extenso, poco legible y bastante complicado por tener que construir cada elemento gráfico, posicionarlo y establecerle sus estilos. En JavaFX y al igual que en las plataformas de [Microsoft][microsoft] y [GNOME][gnome] los elementos gráficos de una ventana se puede definir en un documento y este documento se puede generar usando una aplicación con una interfaz gráfica arrastrando y soltando componentes, estableciendo valores de propiedades. En el siguiente artículo comentaré [cómo crear un documento FXML con la aplicación JavaFX Scene Builder y como usarlo en una aplicación][blogbitix-102].

GNOME proporciona una librería de _bindings_ para el lenguaje Java sin embargo teniendo JavaFX me parece mejor esta última ya que la aplicación será más portable además de estar mejor soportada, ya con los propios ejemplos de GNOME con Java he obtenido errores por lo que parecían incompatibilidades entre versiones.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [JavaFX Get Started Tutorial](https://docs.oracle.com/javase/8/javafx/get-started-tutorial/hello_world.htm)
* [Novedades y nuevas características de Java 8][blogbitix-17]
{{% /reference %}}

{{% /post %}}
