---
pid: 552
type: "post"
title: "Licencias de software libre y diferencias con software privativo y de código abierto"
url: "/2021/02/licencias-de-software-libre-y-diferencias-con-software-privativo-y-de-codigo-abierto/"
date: 2021-02-05T16:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:gnu.svg"
tags: ["planeta-codigo", "software-libre"]
summary: "Hay varias categorías de licencias de software que los desarrolladores como autores del software le asignan al software que crean o en el caso de empresas le asignan para proteger su propiedad intelectual. En la década de los años 80 Richard Stallman lanzo el movimiento de software libre creando software equivalente al de UNIX utilizando una licencia de software libre con unos principios éticos. Otras categorías de licencias son las privativas y las de código abierto."
---

{{% post %}}

Todo software tiene una licencia. Una [licencia de software](https://es.wikipedia.org/wiki/Licencia_de_software) es el contrato formal entre el desarrollador del software, los intermediarios y el usuario del mismo, este contrato especifica los derechos que pertenecen al desarrollador y los derechos que otorga al usuario del software. La licencia de software es un documento con jerga legal que especifica los derechos y obligaciones de cada una de las partes.

Es el desarrollador o desarrolladores como autores de la obra intelectual de creación del software el que determina que licencia aplican a su obra. Incluso tienen la capacidad de en versiones posteriores del software cambiar la licencia en función de sus intereses.

Hay múltiples licencias de software que se catalogan principalmente en tres grupos: licencias de software privativo, licencias de software libre y licencias de código abierto.

{{< tableofcontents >}}

### Software privativo

Para entender las licencias de software libre o código abierto se puede empezar por entender que son las licencias de software privativo. Las licencias de software privativo se denominan así porque privan al usuario de ciertos derechos en el uso o propósito de uso, acceso al código fuente, modificación, distribución o distribución de modificaciones.

Este suele ser el tipo de licencias que emplean muchas empresas en su software comercial del cual obtienen sus ingresos a través de la venta de licencias de uso. Las limitaciones del software privativo permite a las empresas proteger sus programas de la competencia y les otorga exclusividad en la venta u oferta de servicios.

Normalmente estas licencias requieren el pago para el uso del software y realmente no se está comprando un producto sino simplemente se está adquiriendo una licencia de uso por un tiempo indefinido o limitado.

Dado que no se posee el código fuente, no se puede estudiar su funcionamiento. Muchos de los software privativos incluyen funciones malévolas que van contra los intereses de los usuarios, enviando datos acerca del uso del ordenador, la posibilidad de una puerta trasera que realice acciones  como desinstalar programas sin el permiso del usuario o enviar la ubicación del usuario.

No todos los programas de software privativo contienen funciones malévolas pero como el código es secreto no se puede saber qué programas las contienen. Al mismo tiempo los programadores cometen errores en el código pero como no se tiene el código fuente no es posible corregirlos.

Los software más populares con una licencia privativa son el sistema operativo [Windows][windows] de [Microsoft][microsoft], su paquete ofimático [Office][microsoft-office] y el sistema operativo [macOS][macos] de [Apple][apple].

{{< image
    gallery="false"
    image1="logotype:microsoft.svg" optionsthumb1="200x150" title1="Microsoft"
    image2="logotype:apple.svg" optionsthumb2="200x150" title2="Apple"
    caption="Microsoft y Apple" >}}

### Licencias de software libre

Las licencias de software libre se caracterizan por otorgar ciertos derechos a los usuarios. Algunos de estos son el acceso al código fuente, no imponer restricciones en el uso o propósito del código fuente, permitir realizar modificaciones a los programas y distribuir esas modificaciones.

Los programas de software libre también pueden requerir un pago a su distribuidor por su servicio. Pero dado que cualquier usuario al recibir el software recibe los derechos de acceso a código fuente, modificación y distribución esto permite distribuir el software de forma gratuita, por eso mucho del software libre es gratuito. Aunque el software libre normalmente es gratuito, el software libre no es sinónimo de gratuito y se permite el uso comercial.

* [¿Qué es el software libre?](https://www.gnu.org/philosophy/free-sw.es.html)
* [Por qué el «código abierto» pierde de vista lo esencial del software libre](https://www.gnu.org/philosophy/open-source-misses-the-point.es.html)

Nunca esta demás de ver al menos una vez en la vida o cada ciertos años una conferencia de [Richard Stallman][wikipedia-richard-stallman] hablando de la filosofía del software libre y leer el documento [Software libre para una sociedad libre](https://www.gnu.org/philosophy/fsfs/free_software.es.pdf).

Las siguientes no son los únicos tipos de licencias de software libre pero sí son las mas representativas y más utilizadas.

{{< youtube
    video="VFpIhA5Oufg" >}}

{{< youtube
    video="5t_EcPTEzh4" >}}

#### GPL, LGPL y AGPL

Las licencias de software libre de la fundación de software libre o FSF son las licencias más utilizadas para programas de software libre. La FSF considera que el software libre ha de otorgar las siguientes cuatro libertades al usuario para considerarse un software como software libre. Estas libertades tratan de garantizar los derechos del usuario y que el software conserve su condición de software libre.

* La libertad de ejecutar el programa como se desee, con cualquier propósito (libertad 0).
* La libertad de estudiar cómo funciona el programa, y cambiarlo para que haga lo que usted quiera (libertad 1). El acceso al código fuente es una condición necesaria para ello.
* La libertad de redistribuir copias para ayudar a otros (libertad 2).
* La libertad de distribuir copias de sus versiones modificadas a terceros (libertad 3). Esto le permite ofrecer a toda la comunidad la oportunidad de beneficiarse de las modificaciones. El acceso al código fuente es una condición necesaria para ello.

Las licencias GPL es una de las licencias más representativas de software libre. La licencia GPL en una licencia _copyleft_ que indica que si se incluye código bajo esta licencia en un programa más grande, el programa más grande también debe estar bajo esta misma licencia. La licencia LGPL es una variante de la GPL que elimina la restricción de que un software combinado con un software GPL haya de tener licencia GPL, esto es, permite combinar un software GPL con software no GPL en el caso de usar un software como una librería. La tercera licencia de la FSF es la AGPL para que el software usado en lado de servidor sea accesible considerando el uso en el lado del servidor como una forma de distribución del software. Requieren que al distribuir software modificado incluya una lista de cambios realizados.

Un peligro importante que la versión 3 de la GPL impide es la _tivoización_. La _tivoización_ significa que ciertos aparatos que contienen software cubierto por la GPL que en la práctica no se puede cambiar, porque el aparato se apaga si detecta software modificado. Los fabricantes de estos ordenadores sacan partido de la libertad que proporciona el software libre pero no permiten que el usuario haga lo mismo.

* [Licencia GPL](https://choosealicense.com/licenses/gpl-3.0/)
* [Licencia LGPG](https://choosealicense.com/licenses/lgpl-3.0/)
* [Liciencia AGPL](https://choosealicense.com/licenses/agpl-3.0/)
* [¿Por qué actualizar a la versión 3 de la GPL?](https://www.gnu.org/licenses/rms-why-gplv3.es.html)
* [Por qué en su próxima biblioteca no debería utilizar la Licencia Pública General Reducida de LGPL](https://www.gnu.org/licenses/why-not-lgpl.es.html)

El software más representativo con licencia de software libre es el núcleo Linux y muchos de los programas GNU de la fundación FSF que junto con Linux forman el sistema operativo [GNU][gnu]/[Linux][linux].

Hay muchas [distribuciones GNU/Linux entre las que elegir][blogbitix-190] según las preferencias o necesidades de cada usuario. [Ubuntu es fácil de instalar][blogbitix-232] en sencillos pasos y poco tiempo.

{{< image
    gallery="false"
    image1="logotype:gnu.svg" optionsthumb1="200x150" title1="GNU"
    image2="logotype:fsf.svg" optionsthumb2="200x150" title2="FSF"
    image3="logotype:linux.svg" optionsthumb3="200x150" title3="Linux"
    caption="GNU, FSF y Linux" >}}

#### BSD

Las licencias de BSD consideran que las 4 libertades de las licencias de software libre del proyecto GNU en realidad limitan las libertades del usuario, ya que la licencia GPL obliga a distribuir todo el código usado aunque otro tenga otra licencia de software libre con una licencia GPL de software libre.

El software de las licencias de BSD es interesante para algunas empresas ya que pueden utilizar el software y hacer modificaciones sin estar obligados a publicar su software con la misma licencia. Pueden utilizar código con licencia BSD, hacer modificaciones pero al distribuirlo no están obligados a publicar el código fuente.

Según las cláusulas que incluye la licencia las licencias BSD se clasifican en 4, 3 o 2 cláusulas.

* [Licencias BSD][bsd-licenses]
* [Las diferencias entre GNU/Linux, BSD y sus distribuciones][blogbitix-498]

#### MIT

Las licencias MIT son de las más permisivas, casi se consideran software de dominio público. Lo único que requieren es incluir la licencia MIT para indicar que el software incluye código con licencia MIT.

* [Licencia MIT][mit-license]

#### Apache License 2.0

La licencia Apache trata de preservar los derechos de autor, incluir la licencia en el software distribuido y una lista de los cambios realizados. En modificaciones extensivas del software original permite licenciar el software bajo otra licencia sin incluir el código fuente esas modificaciones.

* [Licencia Apache][apache-license]

#### Mozilla Public License

Esta licencia requiere que los archivos al ser distribuido conserven la misma licencia original pero puede ser usado junto con archivos con otra licencia al contrario que la licencia GPL que requiere que todo el código usado junto con código GPL sea licenciado como código GPL. También en caso de hacer modificaciones extensivas permite distribuirlas bajo diferentes términos y sin incluir el código fuente esas modificaciones

* [Licencia MPL][mpl-license]

#### Código de dominio público

Es un código que no está sujeto a derechos de autor que puede utilizarse sin restricciones.

#### Creative Commons

Las licencias de Creative Commons más que para el software se utilizan para cualquier creación artística digital, desde fotos, artículos en blogs, música, vídeos, ...

Hay varios tipos de licencias de _Creative Commons_ diferenciando entre permitir modificaciones a la obra original o permitiendo un uso comercial de la obra.

* [Licencia Creative Commons][creativecommons]

### Licencias de código abierto

Por normal general, el término código abierto y de software libre se utiliza indistintamente para denominar la misma categoría de software. Según [la definición de código abierto][opensource-definition] (o la [definición de código abierto en la wikipedia][wikipedia-opensource-definition]) se otorgan los mismos derechos derechos de acceso al código fuente, modificación y distribución que el software libre.

Las licencias de código abierto permiten el acceso al código fuente pero no todas se consideran licencias de software libre al no otorgar otros derechos que se requieren para considerar un software como software libre como el derecho a la uso o con cualquier propósito, modificación y distribución.

Dado el éxito del software libre como modelo de desarrollo de software algunas empresas cuyo software era privativo pueden decidir hacerlo de código abierto con la intención de suplir algunas carencias de software privativo pero sin perder ciertos derechos que son la fuente de sus ingresos como la venta de licencias. En este caso algunas de las licencias de código abierto son un intermedio entre las licencias privativas y las licencias de software libre.

> Las expresiones «software libre» y «código abierto» se refieren casi al mismo conjunto de programas. No obstante, dicen cosas muy diferentes acerca de dichos programas, basándose en valores diferentes. El movimiento del software libre defiende la libertad de los usuarios de ordenadores, en un movimiento en pro de la libertad y la justicia. Por contra, la idea del código abierto valora principalmente las ventajas prácticas y no defiende principios. Esta es la razón por la que estamos en desacuerdo con el movimiento del código abierto y no empleamos esa expresión.
>
> -  Richard Stallman

> El término «software de código abierto» es utilizado por alguna gente para indicar más o menos la misma categoría que software libre. No es exactamente el mismo tipo de software: ellos aceptan algunas licencias que nosotros consideramos demasiado restrictivas, y hay licencias de software libre que ellos no aceptan. Sin embargo, las diferencias en la extensión de la categoría son pequeñas: casi todo el software libre es de código abierto, y casi todo el código abierto es libre.
>
> -  Free Software Foundation

#### Microsoft Public License

La Microsoft Public License es una licencia de código abierto que permite la distribución del software bajo la misma licencia y la modificación para un uso un uso privado. Tiene restricciones en cuanto a las marcas registradas.

En caso de distribuir el software de forma compiladas o en forma de objeto binario no se exige proporcionar los derechos de acceso al código fuente del software compilado o en forma de objeto binario. En este caso esta licencia no otorga más derechos de los que se reciben pero si permite otorgar menos derechos al distribuir el software en el caso de distribuir software compilado o en forma de objeto binario.

* [Licencia pública de Microsoft][microsoft-ms-pl-license]

### Modelo de desarrollo de software bazar y catedral

El tipo de licencia no determina qué software es mejor o peor, si el privativo o de software libre, la diferencia entre las licencias está en sus características éticas y legales. Aunque el modelo de desarrollo con una licencia de código abierto a la larga suele tener un mejor desarrollo y éxito que el software privativo, más aún con un medio como internet que permite colaborar a cualesquiera personas en el mundo independiente donde estén ubicadas en el mundo.

* [La catedral y el bazar](https://es.wikipedia.org/wiki/La_catedral_y_el_bazar)
* [La catedral y el bazar. Modelos de desarrollo de softare](https://openlibra.com/es/book/download/la-catedral-y-el-bazar-version-espanola)
* [El caldero mágico](https://web.archive.org/web/20130122100227/http://gnuwin.epfl.ch/articles/es/magiccauldron/es-magic-cauldron/node1.html)
* [Colonizando la noosfera](https://biblioweb.sindominio.net/telematica/noosfera.html)

{{< reference >}}
* [Choose an open source license](https://choosealicense.com/)
* [Choose an open source license (licencias)](https://choosealicense.com/licenses/)
* [Choose an open source license (apéndice)](https://choosealicense.com/appendix/)
* [Open Source Licenses: A Comparison Of The Most Popular Types](https://www.kiuwan.com/blog/comparison-popular-open-source-licenses/)
* [Comparison of free and open-source software licenses](https://infogalactic.com/info/Comparison_of_free_and_open-source_software_licenses)
{{< /reference >}}

{{% /post %}}
