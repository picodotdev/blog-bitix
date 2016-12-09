---
pid: 200
title: "Transpiling de ECMAScript 6 a 5, Uglify y ESLint con Gulp"
url: "/2016/12/transpiling-de-ecmascript-6-a-5-uglify-y-eslint-con-gulp/"
date: 2016-12-09T12:00:00+01:00
updated: 2016-12-09T19:30:00+01:00
language: "es"
sharing: true
comments: true
tags: ["blog-stack", "javascript", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="javascript.png" title1="JavaScript" >}}

Este artículo es una combinación de varias cosas que forman el actual estado del arte en JavaScript. [Gulp][gulpjs] como ejecutor de tareas, traducción o _transpiling_ de ECMAScript 6 a ECMAcript 5 con [Babel][babeljs], [Uglify][uglifyjs] como minificador y ofuscador de código y [ESLint][eslint] como verificador del código.

Dos de los navegadores web más importantes como [Chrome][google-chrome] y [Firefox][firefox] ya soportan gran parte de la especificación de ECMAScript 6 que añade nuevas funcionalidades al lenguaje JavaScript que comento en [Introducción al JavaScript de ECMAScript 6][blogbitix-198]. Sin embargo, hasta que prácticamente toda la totalidad de dispositivos soporten ECMAScript 6, formada por la diversidad actual de dispositivos en las que se incluyen los dispositivos móviles es necesario traducir el código JavaScript a la especificación ECMAScript 5. Haciendo esta traducción podemos usar ES6 y al mismo tiempo soportar todos los dispositivos. Hacer _transpiling_ es necesario si el código será accedido desde internet de forma pública para cualquiera y queremos usar las nuevas características de ECMAScript 6. Si se tratase de una aplicación en un entorno controlado de uso interno en el que se usasen los navegadores que soporten ES6 el _transpiling_ no sería imprescindible.

El tamaño de una página afecta al tiempo de carga de la misma, dada las velocidades de incluso los dispositivos móviles la velocidad ya no es tan importante como cuando el ancho de banda era significativamente menor y ahora importa más la latencia que [el protocolo HTTP/2][blogbitix-127] trata de mejorar. En cualquier caso tanto comprimir con gzip el contenido devuelto por el servidor como minificar se consigue la mayor reducción en el tamaño de una página.

Minificar el código y ofuscarlo hace que depurar sea más complicado al no tener el código fuente original. Para que los depuradores tengan la información de los nombres originales al minificar se pueden generar archivos _.map_ que los contengan y que los depuradores obtienen y usan cuando es necesario.

ESLint es un verificador estático de código que nos informará de aquellas reglas que se no se respetan según las normas de estilos que definamos. En ESLint hay cantidad de [reglas](https://eslint.org/docs/rules/) que podemos aplicar al código y personalizar según nuestras preferencias, por ejemplo, requerir que las cadenas de texto se usen mediante comillas simples o que las llaves de bloques de código estén en la misma linea que la expresión _if_, _else_, _while_, etc.

Gulp define las tareas de un proyecto en un archivo de nombre _gulpfile.js_ que usando _gulp-balbel_, _eslint_, _gulp-uglify_ y _gulp-sourcemaps_ obtenemos todas las funcionalidades anteriores. Con Gulp los archivos son transformados en varios pasos y finalmente enviados a un directorio destino del sistema de archivos.

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "gulpfile.js" >}}

Para usar Gulp deberemos tener instalado [npm][npm] que en Arch Linux sería instalar su paquete:

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "npm.sh" >}}

Instalar Gulp a nivel global en el sistema:

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "gulp.sh" >}}

Y finalmente instalar en el proyecto las dependencias definidas en el archivo _package.json_:

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "npm-install.sh" >}}

El archivo JavaScript original con ECMAScript 6 es el siguiente.

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "main.js" >}}

Transformado a ECMAScript 5 queda lo siguiente.

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "main-babel.js" >}}

Estos son los archivos de configuración para ESLint donde indicaremos las reglas que queremos aplicar al código JavaScript y la configuración para Babel donde indicamos a que versión de JavaScript haremos la transformación.

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 ".eslintrc.js" >}}
{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 ".babelrc" >}}

Transformado con Uglify queda algo ilegible pero con el archivo _.map_ podremos depurar en el navegador mostrándose los nombres de las variables originales.

{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "main-uglify.js" >}}
{{< gist picodotdev 0cf6306cf3a7770953946e94618578b8 "main.js.map" >}}

{{% code git="blog-ejemplos/tree/master/ECMAScript6" command="npm install && gulp" %}}

{{% /post %}}
