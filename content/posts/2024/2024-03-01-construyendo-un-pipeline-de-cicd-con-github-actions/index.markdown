---
pid: 697
type: "post"
title: "Construyendo un pipeline de CI/CD con Github Actions"
url: "/2024/03/construyendo-un-pipeline-de-cicd-con-github-actions/"
date: 2024-03-01T20:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:github.svg"
tags: ["programacion"]
summary: "Hay mucha teoría sobre pipelines de CI/CD, en artículos y libros pero no me ha resultado sencillo encontrar un ejemplo de implementación con todas esas buenas prácticas lo suficientemente genérico para que se adapte en gran medida a cualquier organización. En este artículo muestro un ejemplo de _pipeline_ con _workflows_ reutilizables usando Github Actions, Task y semantic-release. También comento algunos aspecto de contexto por el que surge la necesidad y los pasos hasta llegar a esta nueva implementación."
---

{{% post %}}

{{< logotype image1="github.svg" >}}

A lo largo del tiempo las empresas generan inevitablemente software heredado (o cantidades ingentes de software heredado) u obsoleto que por falta de tiempo para el mantenimiento, conocimiento o personas que ya no están en la empresa.

Para algunas empresas fundamentalmente tecnológicas es un problema ya que su valor y competitividad se asienta en la tecnología, a día de hoy la totalidad de las empresas son en gran medida empresas de tecnología y dependen de ella.

No poseer una buena tecnología con la que ofrecer sus servicios puede significar no generar beneficios y no ser competitiva que mantenido en el tiempo de una forma u otra el fracaso como compañía seguramente después de momentos dolorosos con varios procesos de despidos. También puede significar la pérdida de personas y su talento o ser incapaz de atraerlo, profesionalmente es más difícil que alguien esté interesado en una empresa si no la considera un ámbito atractivo profesionalmente en la que pueda aprender y crecer, para muchas personas trabajar con herramientas actuales es un requisito. Trabajar con código heredado puede tener su atractivo siempre y cuando haya oportunidades y voluntad de modernizarlo.

Y dicho esto una de las oportunidades en las que he podido cambiar en un contexto de mucho código heredado ha sido el pipeline de CI/CD, al menos para los proyectos modernos o en los que los cambios son posibles. Y después de leer el artículo si quieres comentar, ¿como es el el _pipeline_ de CI/CD que esas en el trabajo? ¿que herramientas usa? ¿está completamente automatizado o hay pasos manuales? ¿hacéis teses funcionales una vez desplegado?.

{{< tableofcontents >}}

## El pipeline de integración continua y despliegue continuo

A día de hoy un _pipeline_ de integración continua es indispensable, su función es integrar los cambios de los desarrolladores y con pruebas unitarias validar que los cambios de código no introducen errores. Hay mucha teoría y libros explicando las importantes ventajas que proporciona esto. Además de descubrir errores, un _pipeline_ de CI permite hacerlo rápido y de forma automatizada, lo que mejora la eficiencia y rapidez en el desarrollo de software.

El siguiente paso en la automatización es el despliegue continuo con el que el despliegue en el entorno de producción queda también automatizado. Automatizar el despliegue permite reducir el tiempo en introducir cambios, de forma más eficiente con menos esfuerzo y más fiable con menos errores. Quizá requiera una aprobación o despliegue en el momento deseado pero mayormente el despliegue está automatizado.

El último paso es la entrega continua con la que los cambios se despliegan en producción si todas las pruebas automatizadas validan el software correctamente, el software se despliega en producción totalmente automatizada sin aprobaciones. Esto puede requiere de pruebas automatizadas adicionales como teses de aceptación, funcionales, de seguridad, rendimiento.

Con todas las ventajas de la integración continua y el despliegue continuo el desarrollo de software actual se hace empleando estas técnicas de ingeniería de software.

## Contexto

Uno de los puntos en el que había mucho _legacy_ en la empresa en la que trabajo era el CI/CD, más que él es los varios que había, bueno ahora hay uno más pero este más moderno.

El inicial era un [Jenkins][jenkins] y luego algunos proyecto más modernizados pasaron a [Concourse][concourse-ci] con ambos _pipelines_ de integración continua funcionando. Los despliegues con Jenkins requería de varios pasos manuales con mucho margen de mejora en la automatización, toma una o dos horas hacer un despliegue. Esos Jenkins no eran un servicio administrado que había que mantener y dedicar tiempo a que sus instancias de computación funcionasen correctamente, además los _pipelines_ no están bajo el control de los servicios que los hace poco flexibles, impone limitaciones en los nuevos servicio o requiere seguir las convenciones. Su coste era fijo independientemente de si se usaba o no.

Con Concorse las cosas son un poco mejores pero no usa algunas buenas prácticas, los _pipelines_ de despliegue están separados de los proyectos y cada grupo de aplicaciones tiene su propio repositorio de _pipeline_ de CI/CD, que se ha convertido en código heredado difícil de mantener o que requiere una buena cantidad de tiempo para conocerlos que habiendo alternativas es preferible esas alternativas. Es un servicio administrado pero las alternativas son mejores ya no solo porque otras están mejor documentadas.

## Primera solución con Github Actions

Hace un tiempo [Github][github] añadió como complemento natural denominado [Github Actions][github-actions] que en esencia es una herramienta de CI/CD más moderna. Uno de sus atractivos es que es un servicio administrado con lo que no requiere dedicar tiempo ni personas a administrarlo, por otro lado cualquiera con cuenta en Github puede usarlo con lo que muchos profesionales ya tienen conocimiento avanzado de como usarlo, tiene un coste superado un límite de uso pero es un coste por uso y no fijo independientemente de si se usa o no.

En un primer momento el uso que le dábamos era para pasar los teses unitarios con integración continua para cada _push_ a un _pull request_ y en el _merge_ a la rama _main_. Luego para hacer el despliegue de algunos servicios en GKE. Luego también para enviar notificaciones a [Slack][slack] en determinados canales para advertir de un nuevo despliegue y diversa información.

[Github Actions tiene un marketplace][github-actions-marketplace] de acciones en el que hay integraciones para la totalidad de herramientas populares y muchas de las menos conocidas, con lo que usar algo es sencillo, rápido y da mucha flexibilidad en caso de surgir una nueva necesidad.

## Nuevo contexto

Usar Github Actions para la integración continua con teses unitarios y ser un servicio administrado ya era una mejora pero con una base de cientos de repositorios no es escalable ir replicando en cada repositorio el _pipeline_ de CI/CD. De forma que ahora surge la necesidad de construir un _pipeline_ reusable y suficientemente genérico para que cubra la necesidad como _pipeline_ de los servicios.

Esto significa separar los repositorios del código de los _pipeline_ nuevamente pero es una contrapartida opcional y preferible que copiar y pegar en la multitud de repositorios de código. Quizá con un monorepo sería otra la herramienta a utilizar pero en el contexto actual de multitud de repositorios es la opción viable.

Utilizando [Google Cloud][google-cloud] el nuevo _pipeline_ ha de soportar varios artefactos de construcción como Dockerfiles, librerías java y desplegar en GCP, Google App Engine (GAE), Google Functions o publicar las librerías en repositorios de Maven, de tal forma que el _pipeline_ lo permita y suficientemente flexible para que el código no sea demasiado complicado además se ser escalable y permitir en el futuro añadir nuevos lenguajes, artefactos y entornos de ejecución.

## Nueva solución con GitHub Actions

Así que toca buscar herramientas e implementar una solución usando Github Actions de tal forma que el nuevo pipeline sea reutilizable, suficientemente flexible para que cubra las diferentes necesidades y que sea suficientemente sencillo para que no sea un problema de mantenimiento.

Además de tener que construir diferentes artefactos, de poder desplegar en diferentes _runtimes_ ha de soportar repositorios en diferentes lenguajes como Javascript, [Typescript][typescript], [Node][nodejs] y Java en diferentes versiones cada uno.

Para soportar esta diversidad de lenguajes y versiones el _pipeline_ ha de independizarse y no acoplarse a cada lenguaje actual y los hipotéticos futuros, por ejemplo el comando de las tareas para los teses unitarios y análisis estático de código son diferentes para un proyecto de Node y otro de Java, [GNU Make][gnu-make] es una herramienta que proporciona esa abstracción pero finalmente opto por [Task][task] que es una equivalente que se define más simple y fácil de usar.

Otra de la necesidades es de realizar versionado semántico y crear tags en el repositorio de git con cada nueva versión, para los proyectos Java estábamos usando el plugin de [Gradle Axion][gradle-axion] pero ahora necesitando soportar varios lenguajes para resolver este problema está [semantic-release][semantic-release] que además genera la nueva versión en función de los mensajes de _commit_.

También me ha sido necesario buscar algo de información de buenas prácticas en un _pipeline_. Que sea sencillo y que sea rápido por ejemplo en el paso de integración continua, en los siguientes artículos y libros hay más información.

Semantic release.

* [Commit message format](https://semantic-release.gitbook.io/semantic-release/)

Enlaces.

* [Continuous Delivery Patterns and Anti-Patterns](https://dzone.com/refcardz/continuous-delivery-patterns)
* [Software Delivery Guide](https://martinfowler.com/delivery.html)
* [Continuous delivery pipeline 101 ](https://www.atlassian.com/continuous-delivery/principles/pipeline)
* [What is Continuous Delivery?](https://continuousdelivery.com/)
* [CI/CD Process: Flow, Stages, and Critical Best Practices](https://codefresh.io/learn/ci-cd-pipelines/ci-cd-process-flow-stages-and-critical-best-practices/)
* [Semantic Versioning 2.0.0](https://semver.org/)

Libros.

* [Continuous Delivery: Reliable Software Releases through Build, Test, and Deployment Automation](https://amzn.to/4bX9bKQ)
* [Learning GitHub Actions: Automation and Integration of CI/CD With GitHub](https://amzn.to/48BCdg7)

También he necesitado leer algunas secciones de documentación de Github Actions para aprender, por ejemplo para ver como configurar las reglas de protección de las ramas y diferentes opciones de _merge_.

* [Reusing workflows](https://docs.github.com/en/actions/using-workflows/reusing-workflows)
* [Creating a composite action](https://docs.github.com/en/actions/creating-actions/creating-a-composite-action)
* [Using starter workflows](https://docs.github.com/en/actions/learn-github-actions/using-starter-workflows)
* [Creating starter workflows for your organization](https://docs.github.com/en/actions/using-workflows/creating-starter-workflows-for-your-organization)
* [Creating custom deployment protection rules](https://docs.github.com/en/actions/deployment/protecting-deployments/creating-custom-deployment-protection-rules)
* [Managing a merge queue](https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/configuring-pull-request-merges/managing-a-merge-queue)
* [starter-workflows](https://github.com/actions/starter-workflows)
* [starter-workflows/deployments](https://github.com/actions/starter-workflows/tree/main/deployments)
* [starter-workflows/ci](https://github.com/actions/starter-workflows/tree/main/ci)

### Implementación de _pipeline_ reutilizable con Github Actions

El _pipeline_ como el anterior consta de dos fases principales la de _build_ y la de _deploy_. La de _build_ se divide a su vez en una primera base de comprobaciones en la que están incluidos los teses unitarios y el análisis estático de código en un proyecto Java con [Gradle][gradle], [JUnit][junit], [PMD][pmd], [Checkstyle][checkstyle] y [Spotbugs][spotbugs]. Además de si las comprobaciones son correctas generar la _release_.

Como es una buena práctica que la fase de integración continua sea rápida para obtener _feedback_ lo más rápido posible cada una de esas comprobaciones se lanza en paralelo, esto tiene más coste pero el beneficio de la inmediatez es preferible (y estar esperando también tiene un coste). En los proyectos de Node las tareas de teses unitarios y análisis estático de código serán otros. Como he mencionado para esta abstracción he utilizado Task combinado con el concepto de _matrix_ de Github Actions para lanzarlos en paralelo y permitir configurar el _caller workflow_ cuales son las tareas de _check_.

Una vez la fase de check es correcta se lanza la fase de construcción del artefacto y de generación de la _release_ con varias tareas específicas para cada artefacto que se activan con condiciones _if_ en función de cómo ha de construirse el artefacto. Ya sea con un Dockerfile o una librería Java, o más que se añadan posteriormente con [Buildpacks][buildpacks] por ejemplo.

* [Construir la imagen del contenedor de la aplicación usando Buildpacks][blogbitix-669]

Construido el artefacto hay que generar la _release_ y publicarlo en el repositorio de artefactos, para una imagen de contenedor en un repositorio de Docker y para una librería en un repositorio de [Maven][maven]. Para etiquetar el repositorio de Git se utiliza _semantic-release_ que en función de los mensajes de commit genera el siguiente identificador de versión en función de la versión anterior, de los mensajes de commit y de si es una rama de _release_ (_main_) o no (otro _branch_ y trabajando con Github un _pull request_).

### El _workflow_ de la fase de _build_

Hay varios archivos de configuración importantes, el de las tareas de Task que proporcionará el repositorio que haga uso de los _workflows_, el de la configuración de _semantic-release_ que tiene una buena cantidad de opciones de configuración y el del propio _workflow_ de _build_.

{{< code file="taskfile.yml" language="yml" options="" >}}
{{< code file="miscellaneous/task/publish.sh" language="bash" options="" >}}
{{< code file=".releaserc" language="yml" options="" >}}
{{< code file=".github/workflows/build.yml" language="yml" options="" >}}

### El _workflow_ de la fase de _deploy_

La fase de deploy ha de desplegar el artefacto en el entorno de ejecución que necesite el servicio, puede ser un servicio que hace uso de GKE, de GAE o un Google Function. Hay dos entornos el de desarrollo que es un entorno de pruebas y el de producción, con reglas para requerir aprobaciones para hacer el despliegue que se han de configurar en cada repositorio.

Varios de los siguientes _steps_ del _workflow_ tienen _if_ condicionales para que se activen los _steps_ que corresponden. Añadir un nuevo entorno de ejecución sería añadir una nueva clave para identificar el entorno y nuevos _steps_ para hacer su despliegue como corresponde. Tanto el _workflow_ de _build_ como el de _deploy_ reciben una buena cantidad de parámetros con la que configurar el comportamiento de los _workflows_.

{{< code file=".github/workflows/deploy.yml" language="yml" options="" >}}

### El _workflow_ reutilizable del CI/CD

Estos son los workflows reutilizables del pipeline, cada repositorio tiene que definir un _workflow_ que los invoque y proporcione los argumentos necesarios. Este puede ser uno de ejemplo para un proyecto de Java que construye una imagen de Docker con un Dockerfile, lo publica en Google Artifact Repository y lo despliega en GKE.

{{< code file=".github/workflows/main.yml" language="yml" options="" >}}

Para reutilizar acciones comunes en varios jobs el pipeline incluye algunas acciones también reutilizables, para configurar Gradle, Node, Task, Google Cloud y notificaciones de Slack.

{{< code file=".github/actions/setup-java/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/setup-node/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/setup-gradle/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/setup-task/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/setup-semantic-release/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/slack-notification-deploy/action.yml" language="yml" options="" >}}

Este es el aspecto de la notificación en Slack.

{{< image
    gallery="true"
    image1="image:slack-notification.webp" optionsthumb1="650x450" title1="Notificación en Slack de un deploy"
    caption="Notificación en Slack de un deploy" >}}

### Cambios a futuro

Los _pipelines_ probablemente son trajes a medida según la organización cada una es diferente aunque todas comparten muchas necesidades. Este ejemplo tampoco es completo, por ejemplo podría añadirse la parte de pruebas una vez desplegado el artefacto en un entorno, pruebas funcionales, de rendimiento, seguridad, etc.

Es interesante también archivar los informes generados en caso de que un paso de _check_ falle en la integración continua. O crear algún _workflow_ periódico que no es útil ejecutar en cada _push_ a una rama como comprobaciones de seguridad en proyectos Java con [Owasp][owasp] para comprobar las dependencias o la cobertura de los teses unitarios.

Si me es posible a medida que realice estos cambios actualizaré el artículo.

{{% /post %}}
