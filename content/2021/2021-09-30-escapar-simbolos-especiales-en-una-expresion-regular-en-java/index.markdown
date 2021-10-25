---
pid: 601
type: "post"
title: "Escapar símbolos especiales en una expresión regular en Java"
url: "/2021/09/escapar-simbolos-especiales-en-una-expresion-regular-en-java/"
date: 2021-09-30T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo"]
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Las expresiones regulares son cadenas formados por una serie de caracteres y combinación de ellos que se interpretan de forma especial. Las expresiones regulares permiten expresar mediante un patrón ocurrencias en una cadena, permiten encontrar coincidencias y validar que una cadena cumple el patrón de la expresión regular. El patrón de las expresiones regulares .

Así el caracter _^_ en una expresión regular indica el inicio de la cadena, _$_ el final, el _._ cualquier caracter, un grupo seguido _+_ una o más ocurrencias y un grupo seguido de _*_ cero o más ocurrencias. Hay muchos otros caracteres especiales que puede contener una expresión regular.

En el caso de incluir alguno de los caracteres especiales en una expresión regular estos son interpretados de forma especial, para que un caracter especial o grupo de caracteres no sea interpretado sino tratado como un literal este debe ser escapado. Para escapar una palabra en una expresión regular en Java basta con utilizar el método [Pattern.quote()](javadoc17:java.base/java/util/regex/Pattern.html#quote(java.lang.String)).

La expresión regular _(^1+$)_ cumple con todas las cadenas que estén formadas con al menos un caracter del número _1_. En caso de querer buscar las cadenas que contengan el grupo de caracteres _(^1+$)_ que empiecen por _a_ y acaben por _b_ el código sería el siguiente.

{{< code file="Main-1.java" language="java" options="" >}}

En la cadena de la expresión regular se observa que el método _quote_ ha insertado varios caracteres antes y después de la cadena escapada _(1+)_.

{{< code file="System.out-1" language="plaintext" options="" >}}

En caso de no escapar el grupo de caracteres _(^1+$)_ no se encontraría la coincidencia porque los caracteres _(_ _)_ especiales de grupo y el _+_ son interpretados por la expresión regular.

{{< code file="Main-2.java" language="java" options="" >}}
{{< code file="System.out-2" language="plaintext" options="" >}}

Escapar una palabra es importante si con ella se construye una expresión regular y esta proviene de un campo de entrada del usuario o de otro sistema. No hacerlo posibilita una forma de inyectar en la expresión regular un patrón que sea interpretado y podría ser un problema de seguridad.

El ejemplo se puede probar con una de las [novedades introducidas en Java 11][blogbitix-350] para ejecutar un programa desde el código fuente sin requerir compilación.

{{< reference >}}
* [How to escape text for regular expression in Java](https://stackoverflow.com/questions/60160/how-to-escape-text-for-regular-expression-in-java)
{{< /reference >}}

{{% /post %}}
