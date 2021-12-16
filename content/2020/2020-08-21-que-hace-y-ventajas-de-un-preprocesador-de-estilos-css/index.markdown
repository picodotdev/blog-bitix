---
pid: 509
type: "post"
title: "Qué hace y ventajas de un preprocesador de estilos CSS"
url: "/2020/08/que-hace-y-ventajas-de-un-preprocesador-de-estilos-css/"
date: 2020-08-21T15:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:css.svg"
tags: ["planeta-codigo", "web"]
---

{{% post %}}

{{< logotype image1="html.svg" image2="css.svg" >}}

Las páginas web están formadas por varios elementos separados, por un lado está el código HTML que forma el contenido de la página con las referencias a elementos externos que use como hojas de estilo CSS, imágenes o archivos JavaScript. Las imágenes son recursos gráficos soportando todos los navegadores los formatos _png_, _jpg_ para fotos e imágenes vectoriales como _svg_. Los archivos JavaScript permite incorporar a la página código ejecutable con el propósito de añadir ciertos comportamientos mediante programación. Finalmente, están las hojas de estilo CSS que proporcionan los estilos y cambian el aspecto visual y maquetación de los elementos, el contenido HTML puede ser el mismo pero con una hoja de estilos diferente la visualización muy diferente.

Las hojas de estilos cambian propiedades de visualización de diferentes elementos como tipo de letra, tamaño de texto, formato, color, alineación, bordes, etc... La etiqueta _h1_ representa el título principal de la página, con el siguiente código se establecen varias propiedades a cada una de las etiquetas.

Siendo las páginas web cada vez más complejas, en gran medida las hojas de estilo también se convierten en más complejas con la aparición de los dispositivos móviles un poco más dado que hay que adaptar la página al tamaño de cada uno de los dispositivos. Por esto se suelen usar _toolkits_ o librerías como [Bootstrap][bootstrap] con unos estilos elegantes listos para usar sin tener que crearlos desde cero en cada proyecto,  proporciona diferentes componentes como _layouts_, botones, formularios, modales, _popovers_, barras de progreso, desplegables y algunos más. Sin embargo, aún con Bootstrap suelen ser necesarios escribir algunos estilos adicionales propios de la página.

### El preprocesador de estilos CSS

Para facilitar la escritura de hojas de estilos complejas con menor código y más legibles han aparecido preprocesadores de hojas de estilos o _css preprocessors_ con funcionalidades adicionales y una nueva forma de escribir hojas de estilo menos tediosa que el CSS.

Un preprocesador de CSS es un programa que lee un archivo de código fuente en el formato pseudo-código CSS que espera el preprocesador y genera un archivo hoja de estilos en formato CSS que los navegadores entienden, es un compilador que transforma el lenguaje de un archivo a otro en este caso de un código que suele ser similar a CSS a CSS estándar. Uno de los preprocesadores más populares por su simplicidad pero con muchas funciones útiles es [less][less], otro es [Sass][sass].

Las ventajas de un preprocesador CSS es que se pueden usar variables que pueden ser utilizadas por ejemplo para aplicar el mismo color a varios elementos sin tener que repetir el color RGB en cada uno de los elementos lo que facilita el mantenimiento de las hojas de estilo. Anidar estilos relativos a un elemento y selectores, realizar operaciones, utilizar funciones, _namespaces_ para agrupar contextos de estilos y _mixins_ para estilos aplicables a varios elementos algunas cosas más pero estas ya mejoran y simplifican significativamente el trabajo con hojas de estilo.

Los preprocesadores CSS facilitan la escritura y mantenimiento de las hojas de estilos con funcionalidades que CSS no tiene pero esto no hace del CSS resultante generado mejor que si estuviese escrito directamente sin utilizar un preprocesador, por ejemplo no conviene crear muchos niveles de anidación ya que el CSS generado será más grande y más costoso de aplicar al navegador. Para desarrollar estilos aplicables a elementos HTML que sean reutilizables y más fácilmente mantenibles hay que emplear alguna de las metodologías más aceptadas que proponen buenas prácticas para la escritura de CSS, algunas de estas metodologías son OOCSS, BEM y SMACSS.

Este es un ejemplo de código fuente en formato del preprocesador Less que muestra varias de las funcionalidades que aporta sobre CSS estándar.

{{< code file="example.less" language="plain" options="" >}}

### Cómo transformar el pseudo-codigo CSS a CSS con un preprocesador

El archivo CSS en formato _less_ hay que compilarlo para producir el archivo CSS que los navegadores entienden. La herramienta para compilar el archivo de estilos en formato _less_ es _lessc_. Se puede usar directamente en un _script_ Bash o con una herramienta de construcción como [npm][npm] o a través de [webpack][webpack].

{{< code file="npm-install.sh" language="bash" options="" >}}
{{< code file="package.json" language="json" options="" >}}
{{< code file="npm-less-main.sh" language="bash" options="" >}}

La hoja de estilos en formato _less_ que genera el código CSS anterior el siguiente. Se observa que los estilos aplicables a _la etiqueta _article_ quedan agrupados en el contexto de _article_ de modo que son fácilmente identificables todos los estilos de cada etiqueta, además algunos colores utilizan variables cuyos valores no hace falta repetir en cada uso junto con el uso de funciones como _lighten_ y _darken_ para generar variaciones del color base indicado.

{{< code file="main.less" language="plain" options="" >}}

Y su resultado en formato CSS.

{{< code file="main.css" language="css" options="" >}}

{{% /post %}}
