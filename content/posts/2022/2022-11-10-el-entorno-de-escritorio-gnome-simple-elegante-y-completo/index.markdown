---
pid: 660
type: "post"
title: "El entorno de escritorio GNOME, simple, elegante y completo"
url: "/2022/11/el-entorno-de-escritorio-gnome-simple-elegante-y-completo/"
date: 2022-11-10T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:gnome.webp"
imagePost: "image:gnome.webp"
tags: ["gnu-linux", "planeta-codigo"]
summary: "GNOME es uno de los entornos de escritorio con interfaz gráfica en GNU/Linux. Se caracteriza por ser simple en su guía de interfaz de usuario y ofreciendo simplemente las opciones de configuración que más utilizan todos los usuarios, dejando para la aplicación de retoques y las extensiones para añadir las funcionalidades que sólo algunos usuarios necesitan. Es simple pero completo incorporando un conjunto amplio de aplicaciones integradas desarrolladas junto al entorno de escritorio todas siguiendo la misma guía de estilos de usuario que permiten realizar las tareas básicas desde archivos hasta fotos, música, vídeos, visor de documentos e incluso juegos de pasatiempo entre muchas otras aplicaciones integradas."
---

{{% post %}}

{{< logotype image1="gnome.svg" >}}

Hace ya mucho tiempo cuando migré completamente de [Windows][windows] a [Arch Linux][archlinux] casi desde el principio empecé a usar [GNOME][gnome] en esa época en la versión 2.28 o 2.30. Después de varios años he seguido de [Arch Linux a Arch Linux][blogbitix-36] y sin cambiar ni de distribución ni de entorno de escritorio desde hace ya más de una década.

En los primeros momentos inicialmente al decidir qué entorno de escritorio usar empecé por KDE, ahora llamado Plasma, ya que era más parecido a Windows pero en poco tiempo opté por GNOME. Cuando finalmente di el salto a [GNU][gnu]/[Linux][linux] me resultó más atractivo GNOME por su simplicidad y ausencia de detalles innecesarios. Lo llevo usando desde GNOME 2.28 con el anterior concepto de dos barras y en los inicios de GNOME 3.0 a fecha de febrero 2011 en los que tan duramente fue criticado por cambiar varios conceptos fundamentales de las anteriores versiones, tanto que surgieron como una escisión [Mate][mate] y [Cinnamon][cinnamon].

Durante este tiempo he usado GNOME no solo a nivel personal sino en un entorno laboral y durante todo este tiempo cada seis meses he pasado por cada una de sus versiones, en cada versión las mejoras han sido notables hasta el estado actual que considero tiene poco que envidiar a cualquier otro entorno de escritorio si no es que no los supera, incluyendo los entornos de escritorios comerciales de Windows y [macOS][macos] aún teniendo a los mejores desarrolladores del mundo que el dinero puede pagar.

{{< image
    gallery="true"
    image1="image:gnome-1.x.webp" optionsthumb1="200x150" title1="Versiones antiguas de GNOME, 1.x"
    image2="image:gnome-2--6.webp" optionsthumb2="200x150" title2="Versiones antiguas de GNOME, 2.6"
    image3="image:gnome-2--30.webp" optionsthumb3="200x150" title3="Versiones antiguas de GNOME, 2.30" >}}
{{< image
    gallery="true"
    image1="image:gnome-3--0.webp" optionsthumb1="300x250" title1="Versiones antiguas de GNOME, 3.0"
    caption="Versiones antiguas de GNOME" >}}

No soy _distrohopper_ ni _desktophopper_, durante todo este tiempo Arch Linux y GNOME me han permitido realizar mis tareas de forma productiva y por tanto no he tenido necesidad de cambiar a otra distribución ni a otro entorno de escritorio. Si el modelo _rolling release_ de Arch Linux no fuese lo suficientemente estable para que continúe con mis tareas probablemente cambiaría a [la distribución Fedora Silverblue][blogbitix-483] y si GNOME no me permitiese realizar mis tareas de forma productiva seguro que evaluaría Plasma.

Según una frase que leí hace un tiempo con la que estoy de acuerdo venía a decir que la mayoría de análisis y _reviews_ de distribuciones de GNU/Linux son realmente poco más qie una análisis del entorno de escritorio y es que GNOME en la misma versión es el mismo en cualquier distribución salvo algunas mínimas personalizaciones de la distribución como en el caso de [Ubuntu][ubuntu].

* [Descargar e instalar la distribución Ubuntu de GNU/Linux paso a paso desde cero][blogbitix-232]

Más que la distribución las tareas que se hacen con la computadora al final son lo importante, la distribución o entorno de escritorio solo son un medio (dramatización) salvo que seas un _youtuber_ que su análisis son un fin y un día dice que cambia a una distribución o entorno de escritorio haciendo su revisión y opinión jurando amor eterno para poco más que a la semana siguiente decir que cambia a otra distribución y entorno de escritorio.

{{< tableofcontents >}}

## El entorno de escritorio GNOME

Un entorno de escritorio proporciona un modelo de interacción gráfica con el sistema, una guía de estilos y un conjunto de aplicaciones sencillas que cubren las necesidades básicas de los usuarios sin tener que instalar aplicaciones de terceros.

En los entornos de escritorio gráficos tanto de Windows, macOS y GNU/Linux actuales estos son la parte con la que más interacciona el usuario, dos distribuciones distintas con el mismo entorno de escritorio no hay tantas diferencias en el uso diario.

Al contrario de Windows y macOS que solo ofrecen un único entorno de escritorio, que salvo algunas personalizaciones y configuraciones son el mismo para todos los usuarios en su forma de trabajar y aplicaciones integradas. En GNU/Linux se tienen más opciones de elección según las preferencias del usuario.

GNOME solo es uno de los entornos de escritorio más populares de GNU/Linux, se caracteriza por ser simple y eliminar detalles aún suponiendo tener menos opciones de personalización. Al mismo nivel está el entorno de escritorio [Plasma][kde-plasma] con un modelo de experiencia de usuario más parecido al entorno de escritorio Windows y por esto suele ser la opción que aquellos usuarios que migran de Windows a GNU/Linux evalúan como primera opción para que la transición sea más suave. Finalmente, [XFCE][xfce] es un entorno de escritorio más ligero que se caracteriza por consumir menos recursos y más adecuado para equipos con algunos años. Hay muchos más entornos de escritorio en GNU/Linux más minoritarios que estos tres.

{{< image
    gallery="true"
    image1="image:gnome.webp" optionsthumb1="650x450" title1="Entorno de escritorio GNOME 43"
    caption="Entorno de escritorio GNOME 43" >}}

### Introducción

GNOME es uno de los entornos de escritorio más populares en GNU/Linux junto a Plasma. GNOME se caracteriza por su simplicidad y elegancia, al mismo tiempo que ser un entorno de escritorio completo que cubre las necesidades esenciales que ha de integrar todo entorno de escritorio sin necesidad de aplicaciones adicionales proporcionadas por terceros y todas manteniendo una consistencia de diseño. Esta simplicidad a veces adolece de opciones de personalización y configuración, aunque las opciones de configuración más usadas suelen estar o se van incorporando con el tiempo.

Hay una nueva versión de GNOME cada seis meses que incorpora cambios y mejoras significativas. Durante estos seis meses se van publicando actualizaciones menores que corrigen errores e incorporan pequeñas mejoras.

En el modelo de desarrollo de GNOME este está en constante actualización y cambio. Dado que es muy completo tiene numerosos componentes y aplicaciones, esto puede resultar que en las [guía de estilos para la interfaz de las aplicaciones de GNOME][gnome-hig] algunas aplicaciones tardan algunas versiones en actualizarse que resulta en algunas pequeñas inconsistencias en las aplicaciones. Esto no es algo que ocurra solo en GNOME y un sistema operativo de pago como Windows también sigue incorporando el obsoleto panel de control de versiones antediluvianas.

### Funcionalidades generales

Independientemente de las aplicaciones integradas y de terceros el entorno de escritorio define un marco de trabajo para el usuario. La forma de trabajar y de interaccionar con el entorno de escritorio es diferente en Windows, macOS, GNOME o Plasma aunque todos comparten ciertas convenciones como que las aplicaciones se representan de forma gráfica en lo que en el contexto de los escritorios se denominan ventanas.

Los entornos de escritorio modernos se aprovechan de las capacidades de las GPU para ofrecer mejor rendimiento y animaciones fluidas que más allá de los efectos gráficos permiten transiciones suaves y progresivas para evitar cambios bruscos que desorientan al usuario. Esto hace que el escritorio se note más agradable al trabajar con él. Atrás han quedado los llamativos efectos sorprendentes de [Compiz][wikipedia-compiz] y [Beryl][wikipedia-beryl] con su cubo, ventanas gelatinosas o ardiendo para en los entornos de escritorio actuales únicamente usar los mínimos efectos que realmente mejoran la experiencia del usuario.

### GDM

Las computadoras pueden ser usadas por diferentes usuarios de modo que los escritorios primero han de autenticar al usuario que va a usarlo generalmente con un usuario y contraseña. Una vez el sistema autentica al usuario con la pantalla de inicio de sesión el sistema muestra los archivos del usuario y sus configuraciones personalizadas del sistema. La pantalla de inicio de sesión de GNOME es implementada por GDM.

{{< image
    gallery="true"
    image1="image:gnome-gdm-1.webp" optionsthumb1="300x200" title1="Inicio de sesión en GNOME con GDM"
    image2="image:gnome-gdm-2.webp" optionsthumb2="300x200" title2="Inicio de sesión en GNOME con GDM"
    caption="Inicio de sesión en GNOME con GDM" >}}

### Escritorio

Al iniciar sesión en GNOME el escritorio se muestra en la vista de actividades mostrando una miniatura del escritorio y las aplicaciones ancladas en la barra de acceso rápido para iniciarlas, también se permite teclear para iniciar una búsqueda de otras aplicaciones o documentos.

El escritorio de GNOME se compone de una barra superior, en la parte izquierda el acceso a la vista de actividades, en la parte central la fecha y hora que da acceso al área de notificaciones y calendario y en la parte derecha la bandeja del sistema que da acceso a las funciones de configuración y apagar el sistema.

{{< image
    gallery="true"
    image1="image:gnome-escritorio-1.webp" optionsthumb1="300x200" title1="Escritorio"
    image2="image:gnome-escritorio-2.webp" optionsthumb2="300x200" title2="Escritorio" >}}
{{< image
    gallery="true"
    image1="image:gnome-escritorio-3.webp" optionsthumb1="300x200" title1="Escritorio"
    image2="image:gnome-escritorio-4.webp" optionsthumb2="300x200" title2="Escritorio"
    caption="Escritorio" >}}

### Escritorios virtuales

El escritorio GNOME ofrece no solo un escritorio sino varios virtuales que permite organizar las diferentes ventanas de las aplicaciones a conveniencia del usuario, por ejemplo para tener una organización que permite realizar más fácilmente el trabajo.

Es posible cambiar de un escritorio a otro a través de la pantalla de actividades o con el atajo de teclado _Comando + Alt + flecha derecha_ o _izquierda_. El número de escritorios es configurable para que tenga un número fijo o sea dinámico con tan solo los que se están usando junto a la posibilidad de crear uno adicional.

{{< image
    gallery="true"
    image1="image:gnome-escritorios-virtuales-1.webp" optionsthumb1="300x200" title1="Escritorios virtuales"
    caption="Escritorios virtuales" >}}

### Pantalla de bloqueo

Cuando no se esté usando el equipo GNOME permite bloquear la sesión temporalmente de modo que otro usuario tenga acceso a él. Para volver al abrir la sesión hay que volver a autenticarse pero todas las aplicaciones y estado del trabajo seguirá en el mismo punto donde se dejó.

La pantalla de bloqueo permite mostrar ciertas notificaciones de las aplicaciones sin tener que continuar la sesión.

{{< image
    gallery="true"
    image1="image:gnome-pantalla-bloqueo-1.webp" optionsthumb1="300x200" title1="Pantalla de bloqueo"
    image2="image:gnome-pantalla-bloqueo-2.webp" optionsthumb2="300x200" title2="Pantalla de bloqueo"
    caption="Pantalla de bloqueo" >}}

### Notificaciones y calendario

En la parte central de la barra superior la fecha y hora da acceso al área de notificaciones, calendario, relojes y meteorología. Las notificaciones de las aplicaciones ofrecen información al usuario de cierto evento como que ha llegado un mensaje de correo electrónico, también permiten realizar algunas acciones sin tener que usar la aplicación como pausar la música de un reproductor de audio.

El calendario se integra con otros servicios como Google para mostrar los eventos que se agreguen en [Google Calendar][google-calendar]. La aplicación de relojes permite configurar múltiples relojes en zonas horarias y conocer cuál es la hora local en esa zona horaria que es muy útil si se trabaja para entornos internacionales.

Finalmente, el área de notificaciones muestra la meteorología prevista para los siguientes días de la zona configurada, útil para conocer el tiempo previsto como sol o lluvia.

Como las notificaciones pueden interrumpir la concentración y el trabajo está la opción de activar no molestar que silencia las notificaciones emergentes pero que se siguen mostrando cuando se abre el área de notificaciones.

{{< image
    gallery="true"
    image1="image:gnome-notificaciones.webp" optionsthumb1="300x200" title1="Notificaciones y calendario"
    caption="Notificaciones y calendario" >}}

### Bandeja del sistema y configuración

En la parte derecha de la barra superior está la bandeja del sistema que muestra varios iconos con información de estado como el volumen de sonido, el tipo de conexión de red junto a otros iconos que las aplicaciones pueden agregar como un poder cambiar de disposición de teclado rápidamente. Como está captura de imagen está tomada de una máquina virtual ha varias opcione que no se muestra como opción de conexión WIFI y Bluetooth.

{{< image
    gallery="true"
    image1="image:gnome-bandeja-del-sistema-1.webp" optionsthumb1="300x200" title1="Bandeja del sistema"
    caption="Bandeja del sistema" >}}

La misma bandeja del sistema da acceso rápido a cambiar ciertas opciones habituales como cambiar cambiar el volumen de sonido, la conexión cableada o WIFI, activar Bluetooth, el modo de rendimiento, activar la luz nocturna y el modo oscuro de las ventanas para evitar la luz blanca de la pantalla que molesten o dañen los ojos.

También está la opción de tomar una captura de la pantalla completa, de una ventana, un recuadro de selección ajustable o capturar un vídeo todo mostrando u ocultando el puntero del ratón. Con la tecla _Imprimir pantalla_ se obtiene acceso a la herramienta de captura de pantalla, con la tecla _Shift + Imprimir_ pantalla se toma una captura completa de pantalla y con la tecla _Alt + Imprimir_ pantalla la ventana que tiene el foco.

{{< image
    gallery="true"
    image1="image:gnome-captura-de-pantalla-1.webp" optionsthumb1="200x150" title1="Captura de pantalla"
    image2="image:gnome-captura-de-pantalla-2.webp" optionsthumb2="200x150" title2="Captura de pantalla"
    image3="image:gnome-captura-de-pantalla-3.webp" optionsthumb3="200x150" title3="Captura de pantalla"
    caption="Captura de pantalla" >}}

El conjunto completo de opciones de configuración están en la aplicación Configuración, con todas las opciones que se muestran en la siguiente galería de imágenes, desde la conexiones, apariencia, sonido teclado, pantalla e impresoras, región e idioma para los formatos de fecha, moneda y temperatura, accesibilidad, información del sistema entre otras.

{{< image
    gallery="true"
    image1="image:gnome-configuracion-1.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-2.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-3.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-4.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-5.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-6.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-7.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-8.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-9.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-10.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-11.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-12.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-13.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-14.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-15.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-16.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-17.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-18.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-19.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-20.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-21.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-22.webp" optionsthumb1="200x150" title1="Configuración"
    image2="image:gnome-configuracion-23.webp" optionsthumb2="200x150" title2="Configuración"
    image3="image:gnome-configuracion-24.webp" optionsthumb3="200x150" title3="Configuración" >}}
{{< image
    gallery="true"
    image1="image:gnome-configuracion-25.webp" optionsthumb1="200x150" title1="Configuración"
    caption="Configuración" >}}

### Fondos de pantalla

Una de las opciones de configuración más utilizadas es cambiar la imagen del fondo del escritorio. GNOME proporciona varias dinámicas que cambian según la hora y según se utilice el modo claro o el modo oscuro. El usuario también puede añadir sus propias imágenes si lo desea.

{{< image
    gallery="true"
    image1="image:gnome-fondo-de-pantalla-1.webp" optionsthumb1="200x150" title1="Fondo de pantalla"
    image2="image:gnome-fondo-de-pantalla-2.webp" optionsthumb2="200x150" title2="Fondo de pantalla"
    image3="image:gnome-fondo-de-pantalla-3.webp" optionsthumb3="200x150" title3="Fondo de pantalla" >}}
{{< image
    gallery="true"
    image1="image:gnome-fondo-de-pantalla-4.webp" optionsthumb1="200x150" title1="Fondo de pantalla"
    image2="image:gnome-fondo-de-pantalla-5.webp" optionsthumb2="200x150" title2="Fondo de pantalla"
    image3="image:gnome-fondo-de-pantalla-6.webp" optionsthumb3="200x150" title3="Fondo de pantalla" >}}
{{< image
    gallery="true"
    image1="image:gnome-fondo-de-pantalla-7.webp" optionsthumb1="200x150" title1="Fondo de pantalla"
    image2="image:gnome-fondo-de-pantalla-8.webp" optionsthumb2="200x150" title2="Fondo de pantalla"
    image3="image:gnome-fondo-de-pantalla-9.webp" optionsthumb3="200x150" title3="Fondo de pantalla"
    caption="Fondo de pantalla" >}}

### Modo claro y modo oscuro

Uno de las funciones que han ido adoptando todos los escritorios incluyendo GNOME es la funcionalidad de modo claro y oscuro para las ventanas. El modo oscuro utiliza tonos oscuros para evitar la luz blanca que moleste a los ojos. El cambio de un modo a otro es posible realizarlo sin tener que reiniciar la sesión con una animación de transición entre ambos tonos. Esto se aplica tanto a los bordes de las ventanas y objetos gráficos de las mismas en aquellas aplicaciones que soportan los modos claros y oscuro.

{{< image
    gallery="true"
    image1="image:gnome-modo-caro-oscuro-1.webp" optionsthumb1="300x200" title1="Modo claro"
    image2="image:gnome-modo-caro-oscuro-2.webp" optionsthumb2="300x200" title2="Modo oscuro"
    caption="Modo claro y oscuro" >}}

### Esquina activa

El acceso a la pantalla de actividades también es posible mediante la esquina activa de modo que sin hacer clic en la opción _Actividades_ en la barra superior a la izquierda tan solo moviendo el ratón a la esquina superior izquierda. Como esta acción a veces es posible activarla sin querer es posible desactivarla con la opción de configuración.

### Pantalla dividida

Los monitores actuales que llegan a ser de 27" y 32" algunos en formatos panorámicos con resolución 2K, 4K nativos y resoluciones híbridas en los panorámicos. En los monitores con gran cantidad de espacio horizontal una funcionalidad muy útil es colocar dos ventanas lado a lazo una ocupando el lado izquierdo y otra el lado derecho de la pantalla pudiendo cambiar entre una y otra el foco fácilmente sin que ninguna parte de ambas quede oculta por otras ventanas.

{{< image
    gallery="true"
    image1="image:gnome-patalla-dividida-1.webp" optionsthumb1="300x200" title1="Pantalla dividida"
    caption="Pantalla dividida" >}}

### Siempre encima

Cada vez que una ventana toma la foto se eleva sobre el resto de las otras ventanas para mostrar todo su contenido. Cuando otra ventana toma el foco esta se eleva y la que lo tenía desciende quedando oculta parte de su contenido si ambas coinciden en parte de su ubicación.

GNOME tiene una funcionalidad de siempre encima, con la funcionalidad activada la ventana aunque pierda el foco se mantiene por encima de cualquier otra. Esto permite trabajar con una aplicación mientras otra más pequeña que no tiene el foco se mantiene por encima pudiendo cambiar de una a otra fácilmente al igual que con la pantalla dividida.

{{< image
    gallery="true"
    image1="image:gnome-siempre-encima-1.webp" optionsthumb1="300x200" title1="Siempre encima"
    image2="image:gnome-siempre-encima-2.webp" optionsthumb2="300x200" title2="Siempre encima"
    caption="Siempre encima" >}}

### Renombrar múltiples archivos

El nombre del archivo es el elemento que los identifica, no puede haber dos archivos con el mismo nombre, teniendo en cuenta que GNU/Linux es sensible a mayúsculas y minúsculas y Windows es insensible a mayúsculas y minúsculas, en GNU/Linux _archivo.txt_ y _Archivo.txt_ son dos nombres de archivo diferentes y válidos cuando en Windows producen conflicto en el nombre del archivo.

La tecla _F2_ permite renombrar el nombre del archivo, también con el clic derecho en el archivo ofrece un menú contextual con la opción de _Renombrar_. A veces interesa renombrar múltiples archivos a la vez poniendo a todos los archivos el mismo nombre junto a un sufijo adicional con un número incremental para diferenciarlos. Habiendo seleccionado múltiples archivos con la misma tecla _F2_ abre un diálogo con varias opciones para hacer el renombrado de los archivos.

* [Renombrar múltiples archivos en GNOME][blogbitix-480]

{{< image
    gallery="true"
    image1="image:gnome-renombrar-1.webp" optionsthumb1="300x200" title1="Renombrar archivo"
    image2="image:gnome-renombrar-2.webp" optionsthumb2="300x200" title2="Renombrar archivos"
    caption="Renombrar archivos" >}}

### Comprimir archivos

La compresión de archivos permite reducir el tamaño de los mismos, los archivos de texto suelen ser altamente comprimibles otros archivos que ya están comprimidos como música, fotos y vídeo la reducción de tamaño suele ser menor.

Otra utilidad de crear un archivo comprimido es que permite agrupar múltiples archivos en uno solo para hacerlos más fácilmente copiables a una memoria USB o subirlo a un servicio de internet.

El compresor de archivos GNOME permite crear archivos zip y archivos zip protegidos con una contraseña además de otros formatos de compresión.

{{< image
    gallery="true"
    image1="image:gnome-comprimir-1.webp" optionsthumb1="300x200" title1="Comprimir archivo"
    image2="image:gnome-comprimir-2.webp" optionsthumb2="300x200" title2="Comprimir archivo"
    caption="Comprimir archivo" >}}

### Atajos de teclado

Los atajos de teclado son una forma de realizar una acción más fácil y rápida con una combinación de teclas en vez de usar el ratón y varios clic utilizando la interfaz gráfica.

GNOME soporta múltiples atajos de teclado para diversas funciones como cambiar de escritorio virtual y capturar la pantalla, también permite definir atajos de teclado personalizados para las funcionalidades que se deseen.

Por defecto GNOME no ofrece una barra de tareas como Windows y Plasma y tampoco un _dock_ como macOS de modo que para cambiar de ventana hay que utilizar _Alt + tabulador_ si no se quiere hacer el cambio de ventana pasando por la vista de actividades que requiere más pasos.

{{< image
    gallery="true"
    image1="image:gnome-atajos-de-teclado-1.webp" optionsthumb1="300x200" title1="Atajos de teclado"
    caption="Atajos de teclado" >}}

### Configuración de teclado

Lo habitual es utilizar el teclado con la disposición de caracteres del lenguaje propio y según la serigrafía del teclado. Algunos usuarios prefieren utilizar un teclado con disposición en inglés aún siendo el lenguaje del usuario el español, para tareas de programación la disposición en inglés si uno se acostumbra la disposición de caracteres requiere menos combinaciones de teclas.

También es posible usar varios teclados con diferentes disposición y se necesita cambiar de disposición en el sistema operativo según el teclado. GNOME soporta cualquier disposición de teclado y permite cambiar de una a otra fácilmente con un icono en la bandeja del sistema.

Los español parlantes lo habitual es utilizar la disposición en español o inglés internacional con teclas muertas por AltGr si se quiere utilizar la disposición en inglés pero pudiendo utilizar ciertos caracteres españoles con una combinación de teclas.

{{< image
    gallery="true"
    image1="image:gnome-configuracion-de-teclado-1.webp" optionsthumb1="300x200" title1="Configuración de teclado"
    image2="image:gnome-configuracion-de-teclado-2.webp" optionsthumb2="300x200" title2="Configuración de teclado"
    caption="Configuración de teclado" >}}

### Configuración de monitor

No son pocos los usuarios que tiene un monitor de alta resolución 4K o 2K o usuarios que utilizan dos pantallas externas o una pantalla externa y la del portátil. GNOME ofrece varias opciones de configuración para los monitores pudiendo elegir la resolución del monitor y la disposición de los monitores para que corresponda con la disposición física y el paso del puntero del ratón de un monitor a otro sea algo natural.

Los monitores 4K ofrecen gran resolución pero dependiendo de su tamaño de pantalla en su resolución nativa los elementos gráficos pueden verse muy pequeños. Para usar un monitor 4K en su resolución nativa casi preferible un monitor de 32". Los entornos de escritorio ofrecen un factor de escalado para usar la resolución nativa del monitor pero representando los gráficos como si fuesen de un tamaño inferior, la ventaja de usar un monitor 4K con factor de escalado es que la definición gráfica es mayor por tener el monitor más píxeles.

{{< image
    gallery="true"
    image1="image:gnome-configuracion-de-pantalla-1.webp" optionsthumb1="300x200" title1="Configuración de pantalla"
    image2="image:gnome-configuracion-de-pantalla-2.webp" optionsthumb2="300x200" title2="Configuración de pantalla"
    caption="Configuración de pantalla" >}}

### Extensiones

Las extensiones de GNOME son una forma de permitir a los desarrolladores implementar ciertas funcionalidades que GNOME no implementa y ofrece por defecto a todos los usuarios. Instalando una extensión permite añadir y personalizar el escritorio a las preferencias y necesidades del usuario.

Instalar una extensión se puede realizar instalando el paquete _gnome-shell-extensiones_, el [Flatpak de Extensions](https://flathub.org/apps/details/org.gnome.Extensions) o manualmente descargando la extensión y descomprimiendola en el directorio _~/.local/share/gnome-shell/extensions_.

Una funcionalidad demandada es tener una barra de tareas o _dock_ que permite cambiar de ventana sin tener que acceder a la vista de actividades o utilizar la combinación de teclas _Alt + tabulador_. [Dash to Dock][dash-to-dock] es una extensión para GNOME que crea un _dock_ al estilo de macOS, tiene varias opciones de configuración como posicionarlo en la parte inferior de la pantalla o en un lateral.

{{< image
    gallery="true"
    image1="image:gnome-extensiones-1.webp" optionsthumb1="200x150" title1="Extensiones"
    image2="image:gnome-extensiones-2.webp" optionsthumb2="200x150" title2="Extensiones"
    image3="image:gnome-extensiones-3.webp" optionsthumb3="200x150" title3="Extensiones" >}}
{{< image
    gallery="true"
    image1="image:gnome-extensiones-dash-to-dock-1.webp" optionsthumb1="300x200" title1="Extensiones"
    image2="image:gnome-extensiones-dash-to-dock-2.webp" optionsthumb2="300x200" title2="Extensiones" >}}
{{< image
    gallery="true"
    image1="image:gnome-extensiones-dash-to-dock-3.webp" optionsthumb1="300x200" title1="Extensiones"
    image2="image:gnome-extensiones-dash-to-dock-4.webp" optionsthumb2="300x200" title2="Extensiones"
    caption="Extensiones" >}}

### Aplicación retoques

La simplicidad de GNOME hace que solo ofrezca las opciones de personalización más utilizadas por la mayoría de usuarios. Esto tiene la ventaja de que la interfaz gráfica no esté llena de opciones de personalización que apenas se usan, lo que hace la interfaz de GNOME más simple y menos recargada.

Sin embargo, algunos usuarios desean algunas opciones más de personalización, para estos usuarios está la aplicación específica _Retoques_ que permite modificar varios aspectos de GNOME sin tener que agregar esas opciones a la sección de configuración de GNOME.

{{< image
    gallery="true"
    image1="image:gnome-retoques-1.webp" optionsthumb1="200x150" title1="Retoques"
    image2="image:gnome-retoques-2.webp" optionsthumb2="200x150" title2="Retoques"
    image3="image:gnome-retoques-3.webp" optionsthumb3="200x150" title3="Retoques" >}}
{{< image
    gallery="true"
    image1="image:gnome-retoques-4.webp" optionsthumb1="200x150" title1="Retoques"
    image2="image:gnome-retoques-5.webp" optionsthumb2="200x150" title2="Retoques"
    image3="image:gnome-retoques-6.webp" optionsthumb3="200x150" title3="Retoques" >}}
{{< image
    gallery="true"
    image1="image:gnome-retoques-7.webp" optionsthumb1="300x200" title1="Retoques"
    image2="image:gnome-retoques-8.webp" optionsthumb2="300x200" title2="Retoques"
    caption="Retoques" >}}

### Papelera de reciclaje

Eliminar un archivo es una operación destructiva y si se realiza por error la recuperación del archivo y su contenido no es posible sin herramientas especializadas que no son de uso habitual. Para evitar errores y poder recuperar los archivos si más tarde se cambia de opinión los entornos de escritorio ofrecen el concepto de papelera de reciclaje.

Los archivos antes de ser eliminados de forma definitiva se envían a la papelera de reciclaje dando una segunda oportunidad de restaurarlos. La eliminación definitiva la puede realizar el usuario al vaciar el contenido de la papelera de reciclaje, en GNOME también es posible programar la eliminación al cabo de ciertos días configurable en la sección _Privacidad > Histórico de archivos_.

{{< image
    gallery="true"
    image1="image:gnome-papelera-de-reciclaje-1.webp" optionsthumb1="300x200" title1="Papelera de reciclaje"
    image2="image:gnome-papelera-de-reciclaje-2.webp" optionsthumb2="300x200" title2="Papelera de reciclaje"
    caption="Papelera de reciclaje" >}}

## Aplicaciones integradas de GNOME

Junto al sistema de ventanas y escritorio se proporcionan una amplia colección de aplicaciones que cubren las necesidades de cualquier usuario al menos en necesidades básicas con la que habitualmente no es necesario recurrir a instalar aplicaciones adicionales de terceros.

Una de las propiedades de estas aplicaciones desarrolladas junto al entorno de escritorio es que todas siguen la misma guía de estilos de interacción, de modo que todas tienen el mismo aspecto y formas de interacción. Esto hace del escritorio una experiencia consistente y uniforme independientemente de cualquier aplicación que se use. El usuario no tiene que aprender una forma distinta de funcionamiento en cada aplicación.

[Las aplicaciones integradas del entorno de escritorio GNOME][blogbitix-464] son las siguientes:

* Archivos
* Navegador web
* Calculadora
* Fotos
* Visor de documentos
* Música
* Video
* Editor de texto
* Captura de pantalla
* Renombrar archivos
* Tipografías
* Calendario
* Configuración
* Software
* Terminal (y Console)
* Juegos
* Aplicación webcam
* Monitor del sistema
* Discos
* Gestor de correo electrónico
* Cajas (virtualización)
* Meteorología
* Mapas
* Grabadora de sonidos
* Documentos
* Libros
* Contactos
* Relojes
* Retoques

La siguiente galería de imágenes muestra varias de las aplicaciones en su aspecto visual y parte de las funcionalidades que ofrecen.

{{< image
    gallery="true"
    image1="image:gnome-aplicaciones-1.webp" optionsthumb1="300x200" title1="Aplicaciones"
    image2="image:gnome-aplicaciones-2.webp" optionsthumb2="300x200" title2="Aplicaciones"
    image3="image:gnome-aplicaciones-3.webp" optionsthumb3="300x200" title3="Aplicaciones"
    caption="Aplicaciones" >}}

## Otras aplicaciones para GNOME y aplicaciones de terceros

Si la colección de aplicaciones integradas no es suficiente y se requieren funcionalidades más avanzadas hay desarrolladores que ofrecen sus aplicaciones y funcionan en el entorno de escritorio GNOME.

En las aplicaciones de terceros hay que diferenciar dos grupos, las que siguen las normas de estilos de GNOME, que forman [el círculo de aplicaciones de GNOME][gnome-circle], y otras que siguen sus propios estilos.

* [Guardar contraseñas de forma segura con KeePassXC][blogbitix-196]
* [Gestionar biblioteca y convertir entre formatos de libros electrónicos con Calibre][blogbitix-443]
* [Flatpak, distribución e instalación de programas de escritorio en las distribuciones GNU/Linux][blogbitix-362]

Para cada una de ellas suele haber varias opciones entre las que elegir que pueden ser instaladas mediante el gestor de paquetes de la distribución o mediante el sistema de distribución de software independiente de la distribución Flatpak.

* Colección ofimática ([LibreOffice][libreoffice], [OnlyOffice][onlyoffice])
* Editor de retoque fotográfico ([GIMP][gimp])
* Gestor de contraseñas ([KeePassXC][keepassxc])
* Editor de texto ([Visual Studio Code][microsoft-visual-studio-code])
* Gestor de libros digitales ([Calibre][calibre])
* Navegador web ([Firefox][firefox])
* Descargas torrent ([Fragments][fragments], [Transmission][transmissionbt])
* Reproductor de música ([Amberol][amberol])
* Entorno integrado de desarrollo ([IntelliJ][intellij])

Para cada necesidad distinta es muy probable que exista no solo una sino varias aplicaciones específicas, en GNU/Linux además es posible que exista una opción sin costes de licencia y sea fácilmente instalable en formato Flatpak usando la aplicación _Software_.

## Análisis de GNOME en vídeo

Las imágenes estáticas de este artículo no muestran el escritorio de GNOME en funcionamiento, las animaciones su fluidez. En varios videos de [YouTube][youtube] hay guías en vídeo del entorno de escritorio asi como las novedades y cambios de la última versión.

{{< youtube
    video="n89mfZ3-Xbk" >}}
{{< youtube
    video="GtvbT_p0HMk" >}}
{{< youtube
    video="xauLrdxo-Qk" >}}

{{% /post %}}
