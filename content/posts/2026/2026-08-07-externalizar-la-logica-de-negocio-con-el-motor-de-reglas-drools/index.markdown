---
pid: 735
type: "post"
title: "Externalizar la lógica de negocio con el motor de reglas Drools"
url: "/2026/08/externalizar-la-logica-de-negocio-con-el-motor-de-reglas-drools/"
date: 2026-08-08T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["java", "programacion", "planeta-codigo"]
summary: "Toda plataforma de cierta complejidad requiere gestionar reglas de negocio, lógica que cambia con frecuencia o que conviene desacoplar del código. Este artículo analiza en qué casos resulta conveniente externalizar dicha lógica mediante un motor de reglas y en cuáles no, centrándose en Drools, sus distintas formas de definición de reglas (DRL, DMN, tablas de decisión). El ejemplo de código práctico muestra una integración entre Drools con Spring Boot exponiendo una regla de negocio como un endpoint REST que otros servicios pueden consumir."
---

{{% post %}}

{{< logotype image1="java.svg" image2="drools.svg" >}}

Son muchas las necesidades de una plataforma para dar servicio a sus usuarios. Almacenamiento de datos, autenticación, catálogo, inventario, contenido, compras, envíos, mensajes, pagos, antifraude, indexación, _feature flags_, analítica de datos. Y sus correspondientes servicios que dan solución a cada una de esas necesidades para gestionar toda esa complejidad.

* [Cómo desacoplar despliegue y release con feature flags y tests A/B usando Flagsmith][blogbitix-734]

Una de esas necesidades son los procesos y en el tema de este artículo las reglas de negocio.

{{< tableofcontents >}}

## Reglas de negocio

Las reglas de negocio recopilan el conocimiento de los expertos de negocio y automatizan la toma de decisiones, las reglas de negocio definen cómo se comporta el sistema.

La particularidad de las reglas de negocio es que cambian en base a requerimientos de negocio con mucha frecuencia o por requerimientos legales, siendo necesario actualizarlas con cierta prontitud y de forma simple.

Las reglas de negocio se pueden codificar en código sin embargo el código es más difícil de cambiar y complejo cuando las reglas son complejas. Además, los motores que ejecutan reglas de negocio ofrecen características adicionales en la inferencia que un lenguaje de programación no tiene.

Por ello, y debido a que estas reglas idealmente son cambiables por personas de negocio, las reglas de negocio se suelen externalizar del código. Las personas de negocio mantienen las reglas y el sistema simplemente las evalúa.

Una herramienta para definir reglas de negocio de código abierto es Drools. Otras soluciones son [Easy Rules](https://github.com/j-easy/easy-rules) pero que está en modo mantenimiento aunque no tiene las mismas características de Drools en cuanto a inferencia.

### ¿Cuándo usar un motor de reglas y cuándo no?

Una pregunta es cuándo compensa añadir la complejidad de un motor de reglas al sistema. La respuesta es cuando las reglas son complejas y la inferencia de los motores de reglas son una funcionalidad deseada o cuando se desea desacoplar el ciclo de vida del código del ciclo de vida de las reglas.

Los cambios en el negocio o los requerimientos legales requieren que el comportamiento del sistema cambie, quizá con cierta frecuencia y urgencia.

Con un motor de reglas el código del sistema es el mismo y el cambio de comportamiento se delega en las reglas, más fáciles de cambiar que el código equivalente que las implemente. Si es posible implementar las reglas con simples _ifs_ en código y no cambian con frecuencia la complejidad de un motor de reglas no compensa.

## Procesos de negocio

Relacionado con las reglas de negocio están los procesos de negocio, los procesos son también una de las necesidades habituales de un sistema de cierta complejidad. Hay herramientas para modelar procesos y que estos puedan ser cambiados por personas de negocio.

Sin embargo, modelar estos procesos es complejo aún ofreciendo herramientas gráficas que aparentemente lo facilitan y no llegan a la capacidad de un lenguaje de programación. Por otro lado, la realidad es que estos procesos no suelen ser cambiados por personas de negocio.

Para definir procesos están las herramientas BPMN, aunque para procesos han surgido herramientas de nueva generación como [Temporal][temporal].

* [Procesos orquestados fiables y observables en servicios distribuidos con Temporal][blogbitix-729]

## Drools

[Drools][drools] es una herramienta dentro del ecosistema de KIE en el que hay otros proyectos como Kogito para la automatización de negocio para construir sistemas inteligentes, OptaPlanner un solucionador de restricciones y jBPM para procesos de negocio.

Los motores de reglas de negocio ofrecen un sistema para la inferencia, son un sistema avanzado que se basa en los hechos o datos de entrada junto con las reglas. Las reglas definen cuando los hechos cumplen las condiciones y las acciones que se realizan.

Los motores de reglas en esencia son _ifs_ pero que usan algoritmos como RETE para evaluar los hechos contra las reglas de forma eficiente y efectiva, en el caso de Drools se usa el [algoritmo Phreak](https://docs.drools.org/latest/drools-docs/drools/rule-engine/index.html#phreak-algorithm-con_rule-engine).

{{< image
    gallery="true"
    image1="image:drools.webp" optionsthumb1="650x450" title1="Componentes básicos de Drools"
    caption="Componentes básicos de Drools" >}}

Kogito es una evolución _cloud native_ para reglas de negocio y procesos que los expone mediante una interfaz REST.

### El lenguaje de las reglas

Las reglas de Drools se definen con un lenguaje especializado en archivos de texto. Los archivos de las reglas tienen varias secciones en las que destacan las secciones de *when* que definen cuando se aplica la regla a los hechos en la memoria de trabajo y la sección *then* que define que acciones se aplican.

La sección _when_ tiene reglas de lógica booleana y filtrado. La sección _then_ puede establecer valores, insertar, actualizar o eliminar hechos de la memoria de trabajo.

Una sección _query_ permite consultar hechos de la memoria de trabajo.

* [Plugins de Drools](https://marketplace.visualstudio.com/items?itemName=jim-moody.drools) para Visual Studio Code
* [Plugin de Kogito](https://marketplace.visualstudio.com/items?itemName=kie-group.vscode-extension-kogito-bundle) para Visual Studio Code

{{< code file="loan-application-age-limit.drl" language="plain" options="" >}}

### Decision Model and Notation

Otra forma de crear reglas son mediante archivos DMN, un estándar de Object Management Group (OMG) para describir y modelar decisiones operacionales. Estos se crean de forma gráfica con un editor especializado que las guarda las reglas en un archivo XML.

{{< image
    gallery="true"
    image1="image:loan-application-age-limit-1.webp" optionsthumb1="200x150" title1="Decision Model and Notation"
    image2="image:loan-application-age-limit-2.webp" optionsthumb1="200x150" title1="Decision Model and Notation"
    image3="image:loan-application-age-limit-3.webp" optionsthumb1="200x150" title1="Decision Model and Notation"
    caption="Decision Model and Notation" >}}
{{< image
    gallery="true"
    image1="image:loan-application-age-limit-4.webp" optionsthumb1="200x150" title1="Decision Model and Notation"
    image2="image:loan-application-age-limit-5.webp" optionsthumb1="200x150" title1="Decision Model and Notation"
    caption="Decision Model and Notation" >}}

### Tablas de decisión en hojas de cálculo

La tercera forma posible de definir reglas es mediante una hoja de cálculo. Una hoja de cálculo tiene la particularidad de que es una herramienta a la que están habituadas las personas de negocio, además de ver en una tabla los diferentes valores de las reglas y una fácil edición. Lo que pueden ser varios archivos de reglas es posible implementarlo en una hoja de cálculo con cierta estructura.

* [Tablas de decisión con hojas de cálculo](https://docs.drools.org/latest/drools-docs/drools/language-reference/index.html#decision-tables-con_decision-tables)

### Ejemplo de Drools con Spring Boot

El siguiente es un ejemplo de Drools con [Spring Boot][spring-boot] que implementa un endpoint REST para invocar la regla. El motor de reglas está embebido en la propia aplicación, actualizar las reglas requeriría actualizar la aplicación.

Exponer las reglas como un endpoint REST permite consumirlas desde otros servicios de la plataforma.

Los modelos de datos.

{{< code file="Applicant.java" language="java" options="" >}}
{{< code file="LoanApplication.java" language="java" options="" >}}

Los modelos de las peticiones y respuestas.

{{< code file="LoanRequest.java" language="java" options="" >}}
{{< code file="LoanResponse.java" language="java" options="" >}}

Beans del motor de reglas.

{{< code file="Beans.java" language="java" options="" >}}

Ejemplo de invocación de reglas a través de un _endpoint_ REST.

{{< code file="LoanController.java" language="java" options="" >}}

{{< code file="curl.sh" language="bash" options="" >}}
{{< code file="responses.txt" language="plain" options="" >}}

En [apache/incubator-kie-examples](https://github.com/apache/incubator-kie-examples) hay una colección muy completa de ejemplos reglas y Decision Model and Notation que examinar.

{{% sourcecode git="blog-ejemplos/tree/master/DroolsRules" command="./gradlew run" %}}

{{% /post %}}
