---
pid: 438
title: "Cobertura de código y mutation testing en las pruebas con JaCoCo y PIT en Java"
url: "/2019/10/cobertura-de-codigo-y-mutation-testing-en-las-pruebas-con-jacoco-y-pit-en-java/"
date: 2019-10-25T16:30:00+02:00
date: 2019-10-25T18:15:00+02:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
tags: ["java", "planeta-codigo", "programacion"]
summary: "En el caso extremo una cobertura de código del cien por cien pero que no tenga ningún _assert_ pasa los teses pero que en realidad no comprueba nada así que por si sola no es garantía de tener teses efectivos. _Mutation testing_ da una medida adicional a la cobertura de los teses más completa y efectiva que simplemente la cobertura de código ejecutado por los teses unitarios."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="java.svg" title1="Java" width1="200" >}}

Una medida que se suele emplear para medir la calidad o efectividad de los teses unitarios es su cobertura de código que consiste en la cantidad de código ejercitado del total por las pruebas unitarias con los casos de prueba y _fixtures_ empleados. Sin embargo, la cobertura de código no es una medida fiable para conocer si los casos de prueba empleados son precisos y completos. La cobertura de código puede seguir siendo del cien por cien si se sustituye un un mayor que por un mayor que e igual o faltan casos de prueba que ejerciten los límites de las condiciones, los teses seguirán siendo correctos.

Para complementar la cobertura de código y obtener una medida de la precisión y completitud de los teses se emplea _mutation testing_. Esta forma de pruebas analiza el código, realiza operaciones de mutación en el código y posteriormente ejecuta las pruebas contra el código mutado y genera un resultado en el que indica cuáles de las mutaciones realizadas ha sobrevivido pasando todos los teses o cuantas mutaciones han muerto porque los teses han fallado, si alguna mutación sobrevive los teses no son precisos y completos al cien por cien.

[PIT][pit] es una librería que permite realizar _mutation testing_ en Java. Las [operaciones de mutación](https://pitest.org/quickstart/mutators/) que realiza en el código pueden ser en los condicionales, incrementos, invertir negativos, matemáticas, negar condicionales, cambiar los valores de retorno o eliminar llamadas a métodos sin retorno. Un ejemplo de mutaciones son realizar mutaciones en los límites de comparaciones, cambiando un _<_ por un _<=_ y comprobar si con operador mutado la mutación sobrevive pasando todos los teses.

<table class="table">
   <thead class="thead-light">
       <tr class="thead-light">
           <th>Original</td>
           <th>Mutación</td>
       </tr>
   </thead>
   <tbody>
       <tr>
           <td><</td>
           <td><=</td>
       </tr>
       <tr>
           <td><=</td>
           <td><</td>
       </tr>
       <tr>
           <td>></td>
           <td>>=</td>
       </tr>
       <tr>
           <td>>=</td>
           <td>></td>
       </tr>
  </tbody>
</table>

En siguiente ejemplo, la clase _TicketPriceCalculator_ calcula el precio de los billetes de un grupo de viajeros. La lógica del calculador de precios determina el precio en función de la edad de los pasajeros y de si cumplen la condición de familia se les aplica un descuento.

{{< code file="Passenger.java" language="Java" options="" >}}
{{< code file="TicketPriceCalculator.java" language="Java" options="" >}}

La siguiente batería de teses proporciona una cobertura de teses del cien por cien tanto para la cobertura del código como para las mutaciones como se muestran en los informes de [JaCoCo][jacoco] para la cobertura de código y de PIT para la cobertura de mutaciones, depués de haber realizado cambios tanto en el código como en los teses para obtener estos resultados.

{{< code file="TicketPriceCalculatorTest.java" language="Java" options="" >}}

El caso de prueba _calculatePriceForOneAdult_ utilizando un valor de edad de _20_ no es preciso ya que el límite de la edad de una persona adulta es a partir de 18 y no de 20, si PIT realiza una operación de mutación cambiando los límite de la condición de _if (passenger.getAge() > ADULT\_AGE)_ a _if (passenger.getAge() >= ADULT\_AGE)_, la mutación sobrevive. Para que esta mutación no sobreviva el valor del caso de prueba que se debe utilizar es el valor del límite a partir del cual una persona se considera adulta, _18_ o la constante _ADULT\_AGE_.

El caso de prueba _calculatePriceForFamily_ prueba que una familia esté formada por 2 adultos y 2 menores, PIT realiza las mutaciones para considerar una famila en el caso de ser de 3 adultos o 3 menores, la prueba de _calculatePriceForFamily_ mata estas mutaciones haciendo que los teses sean precisos y completos. La cobertura de teses de mutación llega al cien por cien. En el informe de PIT se observa una descripción y número de mutaciones que ha realizado entre ellas ivisiones en vez de multiplicaciones, substracciones en vez de sumas, reemplazo de valores de retorno o cambios y negaciones en condicionales.

<div class="media" style="text-align: center;">
    {{< figureproc
        image1="jacoco-report.png" command1="Fit" commandthumb1="Fit" options1="2560x1440" optionsthumb1="300x200" title1="Informe de cobertura de pruebas con JaCoCo"
        image2="pit-report-1.png" command2="Fit" commandthumb2="Fit" options2="2560x1440" optionsthumb2="300x200" title2="Informe de cobertura y mutation testing de PIT"
        image3="pit-report-2.png" command3="Fit" commandthumb3="Fit" options3="2560x1440" optionsthumb3="300x200" title3="Informe de cobertura y mutation testing de PIT"
        caption="Informe de cobertura de pruebas con JaCoCo e informe de mutation testing de PIT" >}}
</div>

Para generar los informes de cobertura de código y de mutación en Java y usando [Gradle][gradle] como herramienta de construcción las herramientas JaCoCo y PIT proporcionan un complemento o _plugin_ que hay que añadir al archivo de construcción, además de proporcionar algunas opciones de configuración en la sección _pitest_, entre estas propiedades está _mutators_ en la que se puede inidicar los _mutators_ que PIT emplea para lanzar los teses con mutaciones. Los informes se generan en el directorio _build/reports/_.

{{< code file="build.gradle" language="Groovy" options="" >}}
{{< code file="gradlew-run.sh" language="Bash" options="" >}}

{{% sourcecode git="blog-ejemplos/tree/master/JavaMutationTesting" command="./gradlew test jacocoTestReport pitest" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Mutation testing – PIT nuestro gran amigo](http://qajungle.com/mutation-testing-pit-nuestro-gran-amigo/)
* [Pitest - mutation testing in Java](https://beyondscheme.com/2016/pitest-with-java)
* [Mutation Testing with PITest](https://www.baeldung.com/java-mutation-testing-with-pitest)
* [Mutation Testing con PIT](https://www.adictosaltrabajo.com/2015/11/10/mutation-testing-con-pit/)
{{% /reference %}}

{{% /post %}}
