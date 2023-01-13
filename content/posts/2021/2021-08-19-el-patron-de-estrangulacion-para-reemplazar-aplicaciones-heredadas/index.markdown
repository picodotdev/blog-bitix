---
pid: 592
type: "post"
title: "El patrón de estrangulación para reemplazar aplicaciones heredadas"
url: "/2021/08/el-patron-de-estrangulacion-para-reemplazar-aplicaciones-heredadas/"
date: 2021-08-19T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:the-strangler-pattern-1.webp"
tags: ["planeta-codigo", "programacion"]
summary: "Reescribir partiendo desde cero una aplicación grande que tiene sus defectos pero que ha sido desarrollada durante mucho tiempo y su funcionalidad más importante opera mayormente bien no es una estrategia que esté exenta de riesgo ni garantiza que el el nuevo sistema tenga defectos parecidos, más graves o incluso la reescritura fracase. Si la reescritura tiene riesgos pero existe la necesidad de sustituir la aplicación heredada para solventar algunos de sus problemas es necesaria otra estrategia diferente a partir de cero. Una estrategia que se utiliza en estos casos es aplicar el patrón de estrangulación que reemplaza el sistema antiguo de forma incremental cada una de sus piezas."
---

{{% post %}}

Algunas aplicaciones tienen un tiempo de vida de uso muy prolongado que ofrecen una funcionalidad esencial en una empresa u organización, muchas son mantenidas durante todo este tiempo de vida añadiendo funcionalidades, modificando las existentes, corrigiendo errores, fallos de seguridad o modestas modernizaciones. Otras aplicaciones finalmente dejan de mantenerse completamente salvo errores graves. Pasado un lustro o una década con una cantidad importante de líneas de código implementadas por un equipo grande de personas probablemente las aplicaciones pueden considerarse heredadas presentando problemas en su base de código o tecnologías ya consideradas obsoletas.

Quizá llegue un momento que surge el interés de sustituir la aplicación, una opción es reescribir completamente la aplicación heredada con sus propios inconvenientes y riesgos otra opción es reemplazarla de forma incremental, en este último caso se suele utilizar el patrón de estrangulación. Los proyectos que se empiezan desde un inicio son los menos, lo habitual es tener que mantener sistemas existentes y cuando es necesario migrarlos a nuevos.

{{< tableofcontents >}}

### El problema de las aplicaciones heredadas

Algunos de los motivos para desear sustituir una aplicación son los siguientes.

* Código heredado complicado.
* Reglas de negocio en varias ubicaciones del código.
* Diseño monolítico no adecuado para las necesidades actuales de rendimiento, fiabilidad u organización de equipos de desarrollo.
* Dificultad para hacer pruebas.
* Acumulación de deuda técnica.
* Proceso de despliegue complicado con varios pasos manuales.

Cuando los problemas son demasiado grandes las consecuencias son que al hacer un cambio en el código existente no haya confianza de que vaya a funcionar correctamente o requiera demasiado tiempo incrementando los tiempos desde que se acepta un cambio hasta que este es aplicado en producción. Más tiempo entre despliegues agrava los problemas, el resultado es que se crea una espiral de decadencia que realimenta si no se toman medidas para evitarlo.

### Reescribir o reemplazar

Entre las dos opciones al sustituir una aplicación se puede optar por reescribir la aplicación completamente desde cero o sustituir sus funcionalidades de forma incremental. La reescritura completa de una aplicación grande no está exenta de problemas y riesgos. Probablemente no sea posible dejar de dar servicio a la aplicación existente corrigiendo errores o modificaciones mientras la nueva aplicación se está desarrollando. Sustituir un sistema de un tiempo de vida de más de un lustro de vida en poco tiempo no es fácil conseguirlo en unos pocos meses ya que posiblemente tenga una base de código grande con lógica de negocio complicada embebida, la realidad es que pasado unos pocos meses después de empezada la tarea el nuevo sistema únicamente tiene un porcentaje bajo de todas las funcionalidades de la antigua, también se acumule la deuda técnica con la presión de realizar una entrega y la nueva aplicación contenga varios de los mismos problemas que la aplicación que pretende sustituir.

Como alternativa a la reescritura está el reemplazo de forma incremental de partes del sistema mientras continúa ofreciendo su servicio. La razón más importante de optar por el reemplazo es reducir el riesgo de la reescritura completa. Realizar el reemplazo de forma incremental permite realizar entregas más frecuentemente y monitorizar el progreso con más cuidado. A veces esta opción no es tomada en cuenta ya que se considera que costosa más, sin embargo, el poder realizar ciclos de entrega más cortos permite aplicar el conocimiento adquirido en cada ciclo a los siguientes y obtener valor de forma más temprana que con la reescritura completa. Para el reemplazo de forma incremental se utiliza el patrón de estrangulación.

Teniendo en cuenta que una aplicación pasado un tiempo se considerará heredada y obsoleta, las aplicaciones deberían diseñarse y estructurarse de tal forma que faciliten su sustitución en un futuro.

### El patrón de estrangulación

El patrón de estrangulación permite sustituir una aplicación heredada reemplazando de forma gradual y con menos riesgos piezas específicas de funcionalidad por nuevas aplicaciones y servicios. A medida que las funcionalidades de la aplicación heredada son sustituidas, el nuevo sistema eventualmente reemplaza todas las funcionalidades del sistema heredado que es estrangulado hasta que nada de él queda ya por sustituir, lo que queda si no es necesario es posible eliminarlo. La aplicación heredada se mantiene en funcionamiento en todo momento mientras el nuevo sistema funciona de la forma esperada.

El objetivo es reducir lentamente la dependencia del sistema heredado, el reemplazo llevará bastante tiempo pero planeado correctamente el patrón de estrangulación minimiza el riesgo y tiene menos impacto que una reescritura completa.

El patrón no es la única estrategia que ayuda a reemplazar un sistema existente otras técnicas que ayudan son [utilizar un servidor _mock_ para imitar las peticiones y respuestas][blogbitix-589] mientras se desarrolla o en las pruebas unitarias y realizar [pruebas de contrato para validar de forma unitaria el cliente y el servidor][blogbitix-591] de un servicio. El intermediario para aplicar patrón de estrangulación puede ser simplemente [un servidor  web que haga de _proxy_ como Nginx o Apache][blogbitix-159] o mejor aún [un servidor _proxy_ adaptado a la computación nativa en la nube como Traefik][blogbitix-598].

{{< image
    gallery="true"
    image1="image:the-strangler-pattern-1.webp" optionsthumb1="650x450" title1="El patrón estrangulación" >}}
{{< image
    gallery="true"
    image1="image:the-strangler-pattern-2.webp" optionsthumb1="650x450" title1="El patrón estrangulación"
    caption="El patrón estrangulación" >}}

#### Funcionamiento

El funcionamiento del patrón de estrangulación es bastante simple. El primer paso consiste en establecer un intermediario entre el código o aplicación heredado a sustituir y el cliente o usuario de este código. En el inicio de proceso de estrangulación, el intermediario simplemente delega cada petición que recibe en el código o aplicación heredado. Una vez que el intermediario está ubicado puede redirigir las peticiones a otra aplicación o componente una vez completado su desarrollo.

Por ejemplo, suponiendo que se desea reemplazar la funcionalidad de autenticación y autorización, se desarrollan los nuevos servicios de autenticación y autorización de forma completamente separada de la aplicación heredada. Para empezar usar el nuevo código, el intermediario intercepta todos los intentos de inicio de sesión y en vez de delegarlos en la aplicación heredada los delega en el nuevo servicio. Este proceso se repite para cada nueva funcionalidad, en cada reemplazo se redirigen más peticiones fuera de la aplicación heredada.

Los usuarios del servicio no deberían notar los cambios, al menos no ninguno que no sea una mejora en el servicio o que el reemplazo proporcione una nueva funcionalidad a medida que su código aumenta. Algunos servicios reemplazados dependen de nuevos repositorios de datos, es posible que haya la necesidad de coordinar ciertos datos entre los repositorios de datos de lo que queda de la aplicación heredada y el repositorio de datos de un nuevo servicio. Esta coordinación de datos es un aspecto a tener en cuenta para tratar de minimizar la redundancia de datos que facilite la transición.

En el caso de una aplicación monolítica es posible optar por una opción también monolítica pero más modular que resuelva algunos de los problemas en el código de la aplicación heredada o ya que se realiza la tarea del reemplazo aprovechar para utilizar una arquitectura de microservicios si estos se adaptan a las necesidades de la aplicación y de desarrollo en diferentes equipos independientes para que puedan trabajar de forma simultánea e independiente.

El intermediario en una aplicación monolítica es una clase fachada que delega en el código heredado, en el nuevo módulo o en un microservicio que extrae de la aplicación heredada la funcionalidad a estragular. En el caso de una aplicación heredada que funciona mediante llamadas de red como HTTP el intermediario es un _proxy_ inverso que redirige las peticiones a la aplicación heredada o al nuevo servicio sin que los clientes sean conscientes del cambio. Adicionalmente si la aplicación heredada utiliza colas de mensajes una posible opción es hacer cambiar la aplicación heredada para que lea de otras colas nuevas y el intermediario lea de las colas de mensajes originales, de esto modo el intermediario puede decidir si enviar los mensajes a las colas de la aplicación heredada o a su reemplazo.

#### Ventajas e inconvenientes

Las ventajas del patrón de estrangulación son:

* Proporciona una forma de transformar un sistema con menos riesgo que una reescritura desde cero.
* Los nuevos servicios se desarrollan de forma incremental.
* Se mantienen los servicios antiguos mientras se desarrollan los nuevos.

Los inconvenientes son:

* Requiere cuidado en el enrutado de peticiones hacia los diferentes sistemas. Cada nuevo servicio requiere adaptar la lógica del intermediario para redirigir las peticiones de la funcionalidad del viejo servicio al nuevo, cuando una aplicación está compuesta de gran cantidad de servicios es un trabajo significativo.
* Requiere un plan de vuelta hacia para cada funcionalidad reemplazada en caso de que las casas vayan mal. Aún así esto es más sencillo y está más acotado que en el caso de la reescritura.

#### Que seleccionar primero para estrangular

Otra cuestión que funcionalidades de la aplicación heredada reemplazar primero. Es posible utilizar varios criterios pudiendo ser su facilidad de reemplazo, importancia, tamaño o dependencias que tenga.

{{< reference >}}
* [StranglerFigApplication](https://martinfowler.com/bliki/StranglerFigApplication.html)
* [The Strangler pattern in practice](https://www.michielrook.nl/2016/11/strangler-pattern-practice/)
* [Use the Strangler Pattern to Refactor Legacy Apps](https://www.kiuwan.com/strangle-pattern-legacy-apps/)
* [Strangler Fig pattern](https://docs.microsoft.com/en-us/azure/architecture/patterns/strangler-fig)
* [Microservices Design Patterns Part 3 : Patterns(Proxy, Aggregator)](https://www.linkedin.com/pulse/microservices-design-patterns-part-3-patternsproxy-satish-sharma)
* [Monolith to Microservices Using the Strangler Pattern](https://dzone.com/articles/monolith-to-microservices-using-the-strangler-patt)
* [The Strangler Pattern: Microservices Design Patterns In Practice Made Easy with AWS Serverless](https://medium.com/nerd-for-tech/the-strangler-pattern-microservices-design-patterns-in-practice-made-easy-with-aws-serverless-97437a82b052)
* [The pros and cons of the Strangler architecture pattern](https://www.redhat.com/architect/pros-and-cons-strangler-architecture-pattern)
* [Break the monolith: Chunking strategy and the Strangler pattern](https://www.ibm.com/garage/method/practices/code/chunking-strategy-strangler-pattern/)
* [PART 3: Choosing the Right Strategy to Migrate Your Monolithic Application to a Microservices-Based Architecture](https://capgemini-engineering.com/us/en/insight/part-3-choosing-the-right-strategy-to-migrate-your-monolithic-application-to-a-microservices-based-architecture/)
* [Strangler Pattern: How to Deal With Legacy Code During the Container Revolution](https://www.overops.com/blog/strangler-pattern-how-to-keep-sane-with-legacy-monolith-applications/)
* [Strangler Pattern](https://mechanicalrock.github.io/2020/05/04/strangler-pattern.html)
* [The Strangler Pattern and Legacy Migration Strategies](https://www.atelier-solutions.com/the-strangler-pattern-and-legacy-migration-strategies/)
{{< /reference >}}

{{% /post %}}
