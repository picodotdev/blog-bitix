---
pid: 551
type: "post"
title: "Las metodologías OOCSS, BEM y SMACSS para organizar los estilos CSS"
url: "/2021/01/las-metodologias-oocss-bem-y-smacss-para-organizar-los-estilos-css/"
date: 2021-01-31T13:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:css.svg"
tags: ["planeta-codigo", "web"]
summary: "OOCSS, BEM y SMACSS son tres de las metodologías más conocidas para organizar el código CSS con la intención de que sea legible y mantenible. Cada una de estas metodologías define una serie de convenciones y reglas que han de seguir los estilos y propiedades CSS."
---

{{% post %}}

{{< logotype image1="css.svg" >}}

Las funciones de un preprocesador y algunas nativas como variables, funciones, _mixis_, operaciones matemáticas o anidaciones de estilos no son garantía para escribir código CSS bien organizado pero no son garantía para escribir código CSS con menos líneas de código, más mantenible y legible.

Por otro lado, en un proyecto en el que trabajan varias personas es recomendable seguir alguna metodología o convenciones como guía para editar los estilos y mantener la uniformidad del código. Para CSS hay varias metodologías entre las más utilizadas están OOCSS, BEM y SMACSS. Cada una de estas metodologías son independientes pero a veces se aplican varios de los principios y varias metodologías al mismo tiempo.

{{< tableofcontents >}}

### OOCSS

La metodología _Object-Oriented CSS_ o OOCSS se basa en los siguientes dos principios:

* Separar la estructura del diseño.
* Separar contenedor del contenido.

En CSS hay propiedades de estructura que son invisibles al usuario pero modifican el tamaño y posición de un elemento y otro grupo de propiedades que modifican el aspecto visual de un elemento. En esta metodología los nombres de los estilos no deben contener referencias a los nombres de las propiedades.

Algunas propiedades de estructura son:

* Height
* Width
* Margin
* Padding
* Overflow

Por otro lado, las propiedades de apariencia son propiedades que modifican el aspecto visual de los elementos:

* Color
* Font
* Shadow
* Gradient

#### Separar la estructura del diseño

Para aplicar la regla de separar la estructura del diseño en OOCSS se diferencian las propiedades que modifican la estructura de las propiedades que modifican el diseño, para ello se crean diferentes estilos sin mezclar propiedades de estos dos grupos. Por ejemplo, las propiedades _padding_ o _margin_ son propiedades que modifican la estructura o maquetación mientras que _color_ o _border_ son propiedades que modifican la visualización pero no la maquetación.

Estos son dos ejemplos de estilos que mezclan tanto propiedades de estructura como de diseño visual.

{{< code file="oocss-1.css" language="css" options="" >}}
{{< code file="oocss-1.xhtml" language="html" options="" >}}

En los estilos anteriores no se separa la estructura del diseño, las propiedades de estructura están repetidas en ambos estilos y las propiedades de diseño no son reutilizables en otros elementos. Aplicando la primera regla de OOCSS los estilos quedan de la siguiente forma.

{{< code file="oocss-2.css" language="css" options="" >}}
{{< code file="oocss-2.xhtml" language="html" options="" >}}

#### Separar contenedor del contenido

La segunda regla trata de evitar que los estilos dependen del contenedor en el que están dada la estructura de HTML ya que esto hace que el estilo aplicado al contenido no sea reutilizable.

Estos son dos ejemplos de estilos en los que el contenedor y contenido tienen una dependencia.

{{< code file="oocss-3.css" language="css" options="" >}}
{{< code file="oocss-3.xhtml" language="html" options="" >}}

Aplicando OOCSS para separar el contenedor del contenido los estilos se transforman en los siguientes.

{{< code file="oocss-4.css" language="css" options="" >}}
{{< code file="oocss-4.xhtml" language="html" options="" >}}

### BEM

La metodología _Block Element Modifier_ o BEM se basa en que las páginas web están compuestas de bloques como encabezado, pié de página o contenido, dentro de esos bloques están elementos como título, fecha o texto y los elementos tienen modificadores que varían su apariencia como estilo de fuente o color. La ventaja de BEM es que los estilos de un bloque están separados de los estilos de otros bloques de modo que cada uno de ellos se puede modificar de forma independiente sin afectar a otros.

La nomenclatura usa dos guiones bajos para separar el bloque de un elemento y dos guiones medios para separar el modificador. Se usan dos guiones para no crear ambigüedad en caso de que el elemento tenga un guión medio en el nombre. Un defecto de esta nomenclatura es que genera nombres largos para los nombres de los estilos.

Para nombrar las clases de los estilos se siguen unas convenciones:

{{< code file="bem-1.css" language="css" options="" >}}

En este ejemplo hay un bloque _header_, los elementos _title_, _date_ y _text_ y un modificador _secondary_ para el título.

{{< code file="bem-2.css" language="css" options="" >}}

### SMACSS

SMACSS son las siglas de _Scalable and Modular Architecture for CSS_ es otra metodología CSS que tiene como objetivo que el código CSS sea mejor, más fácil de leer y modular. En SMACSS se separan los siguientes elementos.

* _Base_: son los elementos base de una página web como _body_, _h1_ o _a_.
* _Layout_: se divide la página en varias secciones de estructura como _header_, _sidebar_, _content_ o _footer_. Forman la estructura de la página. Los estilos de _layout_ se preceden con _l-_ para distinguirlos de los de los módulos.
* _Modules_: son los elementos que forman la página.
* _State_: modifican el aspecto de los elementos en comportamientos en situaciones con interactividad como oculto, expandido, modificado o con errores. Los estilos se preceden con _is-_.
* _Theme_: modifican la apariencia de un elemento según el _layout_ y _módulo_.

Elementos _base_.

{{< code file="smacss-1.css" language="css" options="" >}}

Elementos de _layout_.

{{< code file="smacss-2.css" language="css" options="" >}}

Un módulo.

{{< code file="smacss-3.css" language="css" options="" >}}

Estilos de estado.

{{< code file="smacss-4.css" language="css" options="" >}}

Estilos de tema que modifican la apariencia.

{{< code file="smacss-5.css" language="css" options="" >}}

{{< reference >}}
* [Metodologías css: OOCSS, BEM y SMACSS](https://www.espai.es/blog/2016/07/metodologias-css-oocss-bem-smacss/)
* [OOCSS - The Future of Writing CSS](https://www.keycdn.com/blog/oocss)
* [Metodologías o Arquitecturas CSS (OOCSS, BEM, SMACSS, ITCSS, Atomic Design)](https://medium.com/williambastidasblog/metodolog%C3%ADas-o-arquitecturas-css-oocss-bem-smacss-itcss-atomic-design-a1a3cfbfa6c9)
* [Una introducción a la metodología BEM](https://webdesign.tutsplus.com/es/articles/an-introduction-to-the-bem-methodology--cms-19403)
* [Quick Tip: Fix Your Messy CSS With SMACSS](https://webdesign.tutsplus.com/articles/quick-tip-fix-your-messy-css-with-smacss--webdesign-18489)
* [Methods to Organize CSS](https://css-tricks.com/methods-organize-css/)
* [Exploring SMACSS: Scalable and Modular Architecture for CSS](https://www.toptal.com/css/smacss-scalable-modular-architecture-css)
{{< /reference >}}

{{% /post %}}
