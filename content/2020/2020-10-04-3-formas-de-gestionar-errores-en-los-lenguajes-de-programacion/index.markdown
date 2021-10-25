---
pid: 519
type: "post"
title: "3 formas de gestionar errores en los lenguajes de programación"
url: "/2020/10/3-formas-de-gestionar-errores-en-los-lenguajes-de-programacion/"
date: 2020-10-04T02:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:java.svg"
tags: ["java", "planeta-codigo", "programacion"]
summary: "El lenguaje de programación C utiliza códigos de retorno como forma de gestionar errores, Java con excepciones y Go y Rust de forma similar a C códigos de retorno pero con la posibilidad de devolver varios valores, uno para el valor en caso correcto y un valor en caso de error. La gestión de errores es parte esencial de los programas para que funcionen correctamente estando preparados en los casos error posibles."
---

{{% post %}}

{{< logotype image1="java.svg" >}}

Una parte importante de los programas está dedicada a la gestión de errores y a tratar los posibles errores. Los posibles errores son desde una entrada de datos con un formato inesperado, datos no válidos, si el programa se comunica por red que la comunicación falle, que ocurra que un archivo no existe y no se pueda abrir o ya exista y no se pueda crear.

Dado que las condiciones de error son numerosas es importante al usar un código ya sea función o método que condiciones de error se pueden producir al ejecutarlo. Algunos lenguajes catalogan los errores como recuperables como sería que un archivo ya existe pudiendo informar al usuario o irrecuperables como falta de memoria en el sistema, un fallo del hardware o por un fallo de programación con el acceso a una posición fuera de rango en un _array_.

A lo largo de la historia los lenguajes han implementado varias formas de gestionar los errores.

{{< tableofcontents >}}

### Códigos de retorno

En el lenguaje C la gestión de errores se hace con códigos de retorno. Al llamar a una función esta devuelve un retorno y según sea su valor se indica que la función se ha ejecutado correctamente o por el contrario se ha producido algún error que ha de ser tratado.

Los códigos de retorno tienen dos problemas. El primer problema es que comprobar el código de retorno puede ser ignorado haciendo que el programa no trate adecuadamente las condiciones de error y fallar o terminar de forma abrupta cuando alguna función no se ejecuta correctamente. El segundo problema es que el código del flujo en el que todo funciona correctamente está mezclado con el código de gestión de errores lo que hace a los programas algo menos legibles.

{{< code file="error-handling.c" language="c" options="" >}}

### Excepciones

En los lenguajes posteriores a C algunos optan por proporcionar un mecanismo de gestión de errores con sintaxis específica, en Java con las excepciones y el bloque _try-catch_. Una ventaja de las excepciones sobre los códigos de retorno es que las excepciones se han de capturar o lanzar de forma obligatoria, sino el compilador emite un error. Otra ventaja es que los métodos declaran cuales son las posibles excepciones que lanzan de modo que al usar esos métodos se conoce cuales hay que tratar, los entornos integrados de desarrollo suelen ofrecer asistencia del código para tratar las excepciones.

Cuando un método detecta una condición de error que el usuario del método no espera se lanza una excepción para que sea tratada. En Java las excepciones son objetos que heredan de la clase [Exception](javadoc11:java.base/java/lang/Exception.html) o [RuntimeException](javadoc11:java.base/java/lang/RuntimeException.html).

{{< code file="error-handling.java" language="java" options="" >}}

Al igual que los códigos de retorno el problema de las excepciones es que también pueden ser ignoradas, aunque se ha de hacer de forma explícita, ya sea incluyendo en la firma del método la clausula _throws_ que delega el tratamiento en el método superior en la pila de invocaciones o con bloques de código _catch_ vacíos. Otro aspecto a tener en cuenta es que de entre todas las sentencias del bloque _try_ es ambiguo que método ha provocado la excepción.

### Retorno de valor y error

Los lenguajes [Go][go] y [Rust][rust] usan una aproximación diferente, no usa excepciones. Usan códigos de retorno como C pero retornando múltiples valores, un valor es para el caso de que en la función se haya ejecutado sin error y otro valor para el caso de error.

Si se intenta acceder al valor del caso correcto en el caso de que se haya producido un error en la función se produce un _panic error_ que aborta la ejecución sin delegar en el invocador el problema con la clausula _throw_ cuando el invocador tampoco va a ser capaz de tratar el error, en este caso el programa no continúa si se ignora el error. Si una función retorna un valor y un error no se puede asumir nada hasta que no se inspeccione el error de modo que los errores hay que tratarlos para que la ejecución del programa continúe, no se pueden ignorar. Sin embargo, al igual que en C y a diferencia del bloque _try-catch_ de Java el código que maneja los errores está mezclado con el código del camino sin errores.

{{< code file="error-handling.go" language="go" options="" >}}

En Rust la gestión de errores se hace de la siguiente forma de forma similar a Go.

{{< code file="error-handling.rust" language="rust" options="" >}}

Una forma similar de gestión de errores en Java es usando una clase como [Either](https://www.javadoc.io/doc/io.vavr/vavr/latest/io/vavr/control/Either.html) de Varv que permite a un método devolver múltiples valores de retorno, aunque en Java no creo que sea la recomendada teniendo excepciones.

{{< reference >}}
* [Error handling and Go](https://blog.golang.org/error-handling-and-go)
* [Rust Error Handling](https://doc.rust-lang.org/book/ch09-00-error-handling.html)
* [Why Go gets exceptions right](https://dave.cheney.net/2012/01/18/why-go-gets-exceptions-right)
{{< /reference >}}

{{% /post %}}
