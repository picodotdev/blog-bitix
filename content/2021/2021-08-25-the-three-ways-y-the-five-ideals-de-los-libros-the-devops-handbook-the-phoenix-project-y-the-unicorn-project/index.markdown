---
pid: 593
type: "post"
title: "The three ways y The five ideals de los libros The DevOps Handbook, The Phoenix Project y The Unicorn Project"
url: "/2021/08/the-three-ways-y-the-five-ideals-de-los-libros-the-devops-handbook-the-phoenix-project-y-the-unicorn-project/"
date: 2021-08-25T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:the-devops-handbook.jpg"
imagePost: "image:the-devops-handbook.jpg"
tags: ["planeta-codigo", "programacion"]
summary: "En la trilogía de los libros _The DevOps Handbook_, _The Phoenix Project_ y _The Unicorn Project_ se explican más detalladamente varios principios para conseguir que la cadena de valor en un producto de software funcione de forma correcta y evitar en gran medida muchos problemas comunes, son los principios denominados como _The three ways_ y _The five ideals_."
---

{{% post %}}

Una fábrica que manufactura objetos físicos en una cadena de montaje en varios pasos, pueden ser coches o mesas de escritorio, puede parecer que no tiene mucho que ver con el desarrollo de software ya que en este último no se fabrican objetos físicos sino software que es algo intangible que no tiene una representación física.

Sin embargo, en realidad una cadena de montaje de objetos físicos tiene muchos puntos en común al desarrollo de software, en ambos casos se siguen una secuencia de pasos o cadena de valor desde que llega una orden hasta que ésta es entregada al cliente. En el caso de una fábrica de coches se producen coches y en un desarrollo de software se producen funcionalidades en una aplicación.

Los mismos principios que se aplican en una cadena de valor en una cadena de montaje de una fábrica también se pueden aplicar al desarrollo de software para producir software, esos principios y metodologías permiten fabricar de forma eficiente y evitar problemas en la cadena de valor. Estos principios y metodologías identificados en varias organizaciones que tienen un gran rendimiento en su cadena de valor se resumen en _The three ways_ y _The five ideals_.

{{< tableofcontents >}}

### _The three ways_

_The three ways_ son tres principios que se centran en el aspecto productivo, estos principios son explicados en el libro [The DevOps Handbook](https://amzn.to/2UFmLzB) de una forma teórica, detallada y extensa y en el libro [The Phoenix Project](https://amzn.to/3zcYDDC) que son el argumento subyacente de una historia de ficción sobre una empresa con problemas en sus proyectos de software, algunos son reconocibles a los que uno pueda tener en su organización.

 El libro empieza con los personajes de una empresa con multitud de problemas en el desarrollo que hacen peligrar la viabilidad de la empresa en una época en la que la tecnología está transformando su negocio, a lo largo del libro y con la guía de un cierto personaje misterioso van aplicando varias mejoras de forma paulatina que les permite recuperar el control.

Parte de la base de _The three ways_ se fundamenta o es similar los principios definidos en el libro [Lean Enterprise](https://amzn.to/3B73QgE), el movimiento ágil y el movimiento de entrega continua.

En mi experiencia profesional de típica consultoría informática no hice prácticamente ninguna de las prácticas de los libros ni siquiera lo más básico como pruebas pruebas unitarias. Sin embargo, en otra experiencia profesional de una empresa de producto quizá no todos pero muchas de las prácticas de estos principios si que los veo identificados.

Son libros muy recomendables de leer en los que se mencionan muchos otros bastante populares como [Release it!](https://amzn.to/2WhI4bx), [The Mythical Man Month](https://amzn.to/3Dh6IJI), [Continuous Delivery](https://amzn.to/2XLNtHZ), [The Pragmatic Programmer](https://amzn.to/38c7mtE), [Extreme Programming Explained](https://amzn.to/3sKn4WB).

{{< amazon
    linkids="9bde9b606b1e5d657881bee89a764d08,12422320bbd79294d7fd0b73ab29b714,2e0e926c66741529fa1963ab16f581d9"
    asins="1942788002,1942788290,1492091774" >}}

#### _The first way: The technical practices of flow_

El primer principio trata sobre la efectividad del flujo de trabajo, para medirlo toma como métrica principal el tiempo entre que entra una petición y esta es entregada que se denomina como el _lead time_. En el caso de desarrollo de software desde se crea una petición hasta que ésta es desplegada en producción, su finalización engloba el despliegue no solo su desarrollo. Las prácticas de este principio tratan de mejorar y evitar problemas en esta métrica.

Hay que tener en cuenta que dos partes de la cadena de valor tiene objetivos diferentes, el área de desarrollo requiere de realizar despliegues de forma continua que potencialmente introducen errores, por otro lado el área de operaciones requiere que los entornos sean estables y que los servicios no tengan errores por un cambio ni caídas de servicio. Reducir la frecuencia de los despliegues para no introducir errores no resuelve los errores sino que  lo agrava al ser despliegues más grandes. La opción es automatizar en la mayor medida posible las pruebas y el procesos de los despliegues, de modo que el conjunto de cambios esté probado por las pruebas y sea más pequeño para fácil identificar la causa de un error más fácilmente y no requiera intervenciones manuales que evite esperas.

El software al ser algo intangible es más difícil de conocer su estado e identificar dónde hay algún problema en la cadena de valor. Para visualizar el estado del trabajo se emplean los paneles kanban que agrupan en columnas según el estado las peticiones, de esta forma es posible visualizar rápidamente qué peticiones han sido desplegadas, que peticiones están el la cola de despliegue, que peticiones están siendo probadas, que peticiones están en desarrollo y cuales están a la espera de ser desarrolladas. Este panel permite ver si en una determinada columna se están acumulando muchas peticiones. El panel kanban permite ver el trabajo en progreso o _work in progress_  en la columna de peticiones en desarrollo o ver el tamaño del lote o _batch size_ en la columna de peticiones a la espera de ser desplegadas.

Otra forma de visibilizar el trabajo es hacer reuniones diarias que sirven para compartir información en la que cada miembro del equipo comenta en que estuvo trabajando el día anterior, en que trabajará durante el día y si existe algún bloqueo. El resto de los miembros del equipo quizá puedan ayudar a eliminar ese bloqueo o proporcionar alguna ayuda.

Conociendo el dato del trabajo en progreso y del tamaño del lote permite limitarlos, ya que si son grandes es posible que causen problemas en la cadena de valor. Por ejemplo, un trabajo en progreso muy grande ocasiona que se esté haciendo mucho trabajo pero que poco esté siendo terminado o generando problemas de cambio de contexto entre diferentes peticiones, un tamaño de lote grande es un problema ya que si hay algún problema en un despliegue será más difícil identificar cuál de las peticiones es la causante.

Para evitar entregar defectos en el siguiente paso de la cadena de valor una práctica es implementar teses unitarios del código. Otro de los problemas en la cadena de valor es el cambio de manos del trabajo o _handoffs_ entre diferentes personas, por ejemplo de una persona de desarrollo a una de operaciones ya que se requiere coordinación y comunicación. Para evitar los problemas de los _handoffs_ conviene automatizar en la medida de lo posible cosas como la creación de entornos de prueba o el despliegue de forma que las personas sean autosuficientes.

Para evitar riesgos en la publicación de funcionalidades éstas se pueden desplegar de forma progresiva obteniendo información de forma más temprana que en el caso de publicarla en un momento determinado de forma completa. Para ello se utilizan los _feature flags_ que permiten activar una funcionalidad a voluntad. Publicar la funcionalidad de forma progresiva y activarla con un _feature flag_ permite reducir el tamaño del lote y obtener _feedback_ de forma más temprana.

También conviene eliminar trabajo desperdiciado como un exceso de peticiones realizadas pero no desplegadas, realizar peticiones que no aportan valor, realizar más de lo necesario, evitar _handoffs_, evitar esperas y defectos.

{{< image
    gallery="true"
    image1="image:the-first-way.jpg" optionsthumb1="650x450" title1="The first way"
    caption="The first way" >}}

#### _The second way: The technical practices of feedback_

Una vez el flujo es correcto para mejorar la cadena de valor es necesario obtener información lo antes posible. Para ello se utilizan prácticas como integración continua en la que cada _commit_ al repositorio de código fuente ejecuta los teses unitarios y de integración con el objetivo de comprobar que los cambios no introducen errores en las partes probadas del código ni errores de regresión.

Otras forma de _feedback_ es obtener información de los sistemas mediante telemetría para comprobar el estado de los sistemas, conocer su estado normal e identificar algún tipo de problema cuando algo se está comportando de forma anómala respecto a su estado normal. También es posible utilizar pruebas A/B para comprobar si realmente una funcionalidad aporta valor o cual de varias posibilidades es la que más aporta. Por ejemplo es posible realizar una prueba A/B que permita conocer cuál de dos diseños de una página de compra genera más compras.

Otras prácticas de _feedback_ son realizar programación en parejas o _pair programming_ que permite evaluar otro punto de vista en el mismo momento de estar programando, revisiones de código o _peer review_ para obtener una segunda opinión antes de aplicar los cambios en la rama principal de desarrollo o desplegar en producción. Herramientas como [GitHub][github] o [GitLab][gitlab] facilitan estos _peer review_ a través de los _pull request_.

{{< image
    gallery="true"
    image1="image:the-second-way.jpg" optionsthumb1="650x450" title1="The second way"
    caption="The second way" >}}

#### _The third way: The technical practices of continual learning_

El tercer principio propone prácticas que permitan mejorar de forma continua y adquirir nuevo conocimiento. Unas de estas prácticas son crear _post mortems_ cuando ocurra un error con un impacto en el servicio. Los _post mortem_ permiten averiguar la causa que generó el problema y establecer acciones para evitar que ocurra en el futuro, aplicando las acciones  de los _post mortem_ según sucedan los problemas a lo largo del tiempo deberían evitarse que sucedan los mismo problemas de forma recurrente. Los _post mortem_ no deben ser utilizados para castigar o identificar personas culpables sino para evitar que los mismo problemas sucedan en el futuro, deben ser _blameless_. Y en sistemas complejos como son muchos productos de software con muchas dependencias en los que una única persona no posee el conocimiento completo problemas se producirán sin ninguna duda.

Otra opción de aprendizaje es introducir problemas de forma intencionada para comprobar se un sistema continúa funcionando aún con los errores y para aprender cómo se comporta el sistema ante esos errores, estas técnicas se denominan _chaos monkey_.

Para compartir información en vez de crear documentos que quedarán desactualizados es preferible crear documentación ejecutable como pruebas unitarias con ejemplo del uso de la API. En vez de utilizar correos electrónicos como forma de comunicación privadas utilizar herramientas más instantáneas y públicas como [Slack][slack] o [Teams][microsoft-teams] e incluso utilizar _bots_ desde estas herramientas que lancen acciones como despliegues. Al quedar los registros de las comunicaciones públicas es fácil compartir la información.

{{< image
    gallery="true"
    image1="image:the-third-way.jpg" optionsthumb1="650x450" title1="The third way"
    caption="The third way" >}}

### _The five ideals_

De los mismos autores que los libros anteriores es el libro [The Unicorn Project](https://amzn.to/384Yt5l), es una novela de una historia ficticia simultánea a la historia del de libro _The Phoenix Project_ ambientada en la misma organización, la historia es similar con un comienzo en el que hay varios problemas, en este caso un grupo de personas forma «la rebelión aplicando también mejoras de forma paulatina que les permite retomar el control, el mismo personaje misterioso aparece en este libro.

El libro _The Unicorn Project_ explica _The five ideals_ con varios principios que en global _The three ways_ añadiendo el objetivo del éxito desde el punto vista de negocio y de la organización.

{{< amazon
    linkids="81db509fbd9848a1d9c5e7e0c9454703"
    asins="1942788762" >}}

#### _The first ideal: locality and simplicity_

Uno de los problemas que afecta al _lead time_ es que en una determinada tarea sea necesario que intervengan varias personas y roles diferentes, sobre todo cuando cada una tiene sus propias tareas y prioridades. Estos cambios de manos generan tiempos de espera por cambios de contexto, comunicación y coordinación que retrasan la entrega.

Este ideal propone que la persona encargada de hacer los cambios de una tarea tenga la mayor capacidad posible para completarla con la menor ayuda de otros equipos. Hay que evitar procesos y burocracia innecesaria.

Una medida para implementar este ideal es las personas tenga a su disposición servicios en forma de autoservicio, por ejemplo si un desarrollador necesita un nuevo entorno de pruebas o desplegar en producción sea este el que pueda crearlo con alguna herramienta de forma automatizada o tenber la capacidad de hacer despliegues. A evitar sería el requerir de una persona con el rol de operaciones de forma manual cree el entorno o haga el despliegue. En este caso el rol de operaciones es proporcionar a desarrollo las herramientas necesarias para realizar las tareas de forma automatizada.

#### _The second ideal: focus, flow, and joy_

El segundo ideal indica que el trabajo no debería ser desagradable. Para que no lo sea hay que evitar esperas a que otras personas hagan cosas, estar trabajando en pequeñas cosas sin conocer su objetivo, evitar estar constantemente apagando fuegos, evitar identificar responsables cuando algo no funciona y evitar el agotamiento.

En vez de eso hay que tener visibilidad de los resultados del trabajo y tener una visión global de su impacto que sirvan de motivación, trabajar en pequeños lotes de trabajo que fluyan rápido en la cadena de valor y obtener realimentación del trabajo. Estas condiciones permiten un desempeño correcto, ser un reto, aprender y crecer como profesionales, dominar el dominio de negocio e incluso disfrutar de ello.

#### _The third ideal: Improvement of daily work_

El tercer ideal indica que mejorar el trabajo diario es al menos tan importante como el propio trabajo. Cuando se crea deuda técnica el eliminarla es tratada como una prioridad, la arquitectura es mejorada y modernizada de forma constante de modo que siga siendo posible entregar trabajo con fluidez y más valor antes, de forma segura sin miedos a hacer cambios por riesgos de generar errores y de forma agradable.

#### _The fourth ideal: psychological safety_

En el cuarto ideal es necesario que haya seguridad psicológica de tal forma que los miembros del equipo se sientan seguros al hablar de problemas, de esta forma no solo se pueden resolver sino también prevenir. Resolver problemas requiere honestidad y la honestidad requiere ausencia de miedo a ser castigado.

Los _post mortem_ han de ser _blameless_ de lo contrario las personas tenderán a ocultar información o no visibilizar los problemas para tratar de evitar los castigos.

#### _The fifth ideal: customer focus_

El quinto ideal trata sobre cual es el objetivo de la organización y que parte de la misma es la esencial y cuál es contextual. La esencial del negocio es la diferenciadora y por la que están dispuestos a pagar los clientes, la contextual aunque importante no es relevante para el cliente. Los esfuerzos e iniciativas más importantes de una organización deben estar dirigidos a aportar valor al cliente.

{{< reference >}}
* [The Three Ways: The Principles Underpinning DevOps](https://itrevolution.com/the-three-ways-principles-underpinning-devops/)
* [Los principios DevOps: The Three Ways](https://www.ilimit.com/blog/principios-devops-the-three-ways/)
* [The Five Ideals of DevOps](https://itrevolution.com/five-ideals-of-devops/)
* [The Three Ways Explained](https://island94.org/2017/12/the-three-ways-explained)
* [The five ideals explained](https://island94.org/2020/11/the-five-ideals-explained)
* [The Unicorn Project turns DevOps into a rebellion](https://www.futuralistech.com/the-unicorn-project-turns-devops-into-a-rebellion/)
* [Resource Guide To The Unicorn Project ](https://itrevolution.com/resource-guide-to-the-unicorn-project-part-1/)
{{< /reference >}}

{{% /post %}}
