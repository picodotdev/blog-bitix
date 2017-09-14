---
pid: 259
title: "La herramienta de documentación Javadoc de Java"
url: "/2017/09/la-herramienta-de-documentacion-javadoc-de-java/"
date: 2017-09-11T13:00:00+02:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Programar y desarrollar requiere además de poseer diversos conocimientos disponer de una buena documentación de consulta y referencia. Una de las cosas buenas que me gustaron de Java cuando empecé a programar en este lenguaje, cuando aún estaba lejos de tener internet y aún me lo sigue pareciendo, fue su [documentación Javadoc][javadoc] de toda la API de clases incluidas en el JDK.

La documentación Javadoc es una colección de páginas HTML de todas las clases, métodos, parámetros y retornos junto con la información y especificaciones que quiera incluir el desarrollador de la API que en el caso de las clases de JDK incluye abundantes e interesantes detalles de implementación a tener en cuenta al usar las clases.

Se genera a partir del propio código fuente de las clases con los comentarios incluidos que siguen cierto formato precediendo la definición de las clases y métodos. Al estar código y documentación en el propio archivo de código fuente es más fácil mantener sincronizados el código y su documentación.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="259"
        image1="javadoc-jdk.png" thumb1="javadoc-jdk-thumb.png" title1="Documentación Javadoc del JDK"
        caption="Documentación Javadoc del JDK" >}}
</div>

La documentación en el código fuente se incluye en comentarios que preceden una clase o método, además, con anotaciones se pueden documentar los parámetros y el valor de retorno. Se pueden incluir etiquetas HTML junto con algunas de las anotaciones o _doclets_/_taglets_, algunas [anotaciones Javadoc incluidas en el JDK](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#javadoctags) son las siguientes pero también se pueden desarrollar _doclets_/_taglets_ propios.

* _@author_: indica el autor de la clase o método.
* _{@code}_: incluye en el comentario un trozo de código que se formatea de forma especial.
* _{@docRoot}_: incluye una ruta relativa al directorio raíz donde se genera la documentación.
* _@deprecated_: indica que un método ha quedado obsoleto, se desaconseja su uso y puede que en futuras versiones desaparezca.
* _@exception_: es sinónima de throws.
* _{@inheritDoc}_: hereda el comentario Javadoc de la clase o método superior en la jerarquía de clases.
* _{@link}_: incluye un enlace a otra sección de la documentación, método o clase.
* _{@linkplain}_: es idéntica a @link pero el enlace es un texto plano.
* _{@literal}_: muestra un texto sin interpretar el texto como HTML.
* _@param_: documenta un parámetro de un método.
* _@return_: documenta el valor de retorno de un método.
* _@see_: incluye un enlace con documentación adicional en la sección final de la documentación.
* _@serial_
* _@serialData_
* _@serialField_
* _@since_: indica a partir de que versión de la API fue incluida la clase o método.
* _@throws_: documenta una posible excepción que puede ser lanzada por el método.
* _{@value}_: muestra el valor de un campo estático.
* _@version_: para documentar la versión de cuando se hizo _checkout_ del sistema de control de versiones.

Un ejemplo usando estas anotaciones en una clase sería el siguiente.

{{< gist picodotdev 1a1ee2ee7dc1a33daa5b16e59070f908 "Main.java" >}}

Una vez documentado el código fuente hay que usar la [herramienta Javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html) para generar la documentación. Mediante la herramienta de construcción [Gradle][gradle] se hace con la tarea _javadoc_.

{{< gist picodotdev 1a1ee2ee7dc1a33daa5b16e59070f908 "gradle.sh" >}}
{{< gist picodotdev 1a1ee2ee7dc1a33daa5b16e59070f908 "build.gradle" >}}

La propia documentación de las clases del JDK está generada con la herramienta Javadoc. Este es el aspecto de la documentación de este ejemplo que tiene exactamente el mismo aspecto que la del JDK.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="259"
        image1="javadoc-1.png" thumb1="javadoc-1-thumb.png" title1="Documentación Javadoc"
        image2="javadoc-2.png" thumb2="javadoc-2-thumb.png" title2="Documentación Javadoc"
        caption="Documentación Javadoc del ejemplo" >}}
    {{< figure year="2017" pid="259"
        image1="javadoc-archivos.png" thumb1="javadoc-archivos-thumb.png" title1="Archivos de la documentación Javadoc"
        caption="Archivos de la documentación Javadoc" >}}
</div>

La documentación Javadoc al ser una colección de archivos HTML y demás recursos estáticos pueden copiarse a cualquier servidor web si es necesario que estén disponibles a través de internet y accesibles con cualquier navegador web.

{{% code git="blog-ejemplos/tree/master/Javadoc" command="./gradlew javadoc" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Javadoc 8](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)
* [Javadoc 7](https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html)
* [How to Write Doc Comments for the Javadoc Tool](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)
{{% /reference %}}

{{% /post %}}
