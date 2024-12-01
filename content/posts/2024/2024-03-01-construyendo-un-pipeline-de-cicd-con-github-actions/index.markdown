---
pid: 697
type: "post"
title: "Construyendo un pipeline de CI/CD con Github Actions"
url: "/2024/03/construyendo-un-pipeline-de-cicd-con-github-actions/"
date: 2024-03-01T20:00:00+02:00
updated: 2024-11-30T22:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:github.svg"
tags: ["programacion"]
summary: "Hay mucha teoría sobre pipelines de CI/CD, en artículos y libros pero no me ha resultado sencillo encontrar un ejemplo de implementación con todas esas buenas prácticas lo suficientemente genérico para que se adapte en gran medida a cualquier organización. En este artículo muestro un ejemplo de _pipeline_ con _workflows_ reutilizables usando Github Actions, Task, rease-action y git-cliff junto con algún _script_ de bash. También comento algunos aspecto de contexto por el que surge la necesidad y los pasos hasta llegar a esta nueva implementación."
---

{{% post %}}

{{< logotype image1="github.svg" >}}

A lo largo del tiempo las empresas generan inevitablemente software heredado (o cantidades ingentes de software heredado) u obsoleto por falta de tiempo para su mantenimiento, conocimiento o personas que ya no están en la empresa.

Para algunas empresas fundamentalmente tecnológicas es un problema ya que su valor y competitividad se asienta en la tecnología, a día de hoy la totalidad de las empresas son en gran medida empresas de tecnología y dependen de ella, y o son buenas en desarrollar software o seguramente no prosperen.

No poseer una buena tecnología con la que ofrecer sus servicios puede significar no generar beneficios y no ser competitiva que mantenido en el tiempo de una forma u otra el fracaso como compañía seguramente después de momentos dolorosos con varios procesos de despidos. También puede significar la pérdida de personas y su talento o ser incapaz de atraerlo, profesionalmente es más difícil que alguien esté interesado en una empresa si no la considera un ámbito atractivo profesionalmente en la que pueda aprender y crecer, para muchas personas trabajar con herramientas actuales es un requisito. Trabajar con código heredado puede tener su atractivo siempre y cuando haya oportunidades y voluntad de modernizarlo.

Y dicho esto una de las oportunidades en las que he podido cambiar en un contexto de mucho código heredado ha sido el _pipeline_ de CI/CD, al menos para los proyectos modernos o en los que los cambios son posibles. Y después de leer el artículo si quieres comentar, ¿como es el _pipeline_ de CI/CD que usas en el trabajo? ¿que herramientas usas? ¿está completamente automatizado o hay pasos manuales? ¿hacéis teses funcionales una vez desplegado? ¿si haces algo diferente en tu empresa, que podría mejorar en este? Deja un comentario estaré encantado de leerlo y de aprender.

{{< tableofcontents >}}

## El pipeline de integración continua y despliegue continuo

A día de hoy un _pipeline_ de integración continua y despliegue es indispensable, su función es integrar los cambios de los desarrolladores y con pruebas unitarias validar que los cambios de código no introducen errores. Hay mucha teoría y libros explicando las importantes ventajas que proporciona esto. Además de descubrir errores, un _pipeline_ de CI permite hacerlo rápido y de forma automatizada, lo que mejora la eficiencia y rapidez en el desarrollo de software. Ciclos rápidos de desarrollo permiten experimentar y obtener _feedback_ antes.

El siguiente paso en la automatización es el despliegue continuo o _continous deploy_ con el que el despliegue en el entorno de producción queda también automatizado. Automatizar el despliegue permite reducir el tiempo en introducir cambios, de forma más eficiente con menos esfuerzo y más fiable con menos errores. Quizá requiera una aprobación o despliegue en el momento deseado pero mayormente el despliegue está automatizado.

El último paso es la entrega continua o _continous delivery_ con la que los cambios se despliegan en producción si todas las pruebas automatizadas validan el software correctamente, el software se despliega en producción totalmente automatizada sin aprobaciones. Esto requiere de pruebas automatizadas adicionales como teses de aceptación, funcionales, de seguridad o rendimiento.

Con todas las ventajas de la integración continua y el despliegue continuo el desarrollo de software actual se hace empleando estas técnicas de ingeniería de software.

## Contexto

Uno de los puntos en el que había mucho _legacy_ en la empresa en la que trabajo era el CI/CD, más que él es los varios que había (Jenkins y Concourse), bueno ahora hay uno más pero este más moderno que dedicando tiempo ya ha demostrado ser capaz de reemplazar Concourse con lo que es cuestión de tiempo que desaparezca. Y estoy vislumbrando alguna opción viable para reemplazar Jenkins que es una infraestructura más antigua.

El inicial era un [Jenkins][jenkins] y luego algunos proyecto más modernizados pasaron a [Concourse][concourse-ci] con ambos _pipelines_ de integración continua funcionando. Los despliegues con Jenkins requería de varios pasos manuales con mucho margen de mejora en la automatización, toma de 3 a 4 horas hacer un despliegue y esto ya es siendo bastante eficiente en el proceso. Esos Jenkins no eran un servicio administrado que había que mantener y dedicar tiempo a que sus instancias de computación funcionasen correctamente, además los _pipelines_ no están bajo el control de los servicios que los hace poco flexibles, impone limitaciones en los nuevos servicio o requiere seguir las convenciones. Su coste era fijo independientemente de si se usaba o no.

Con Concourse las cosas son un poco mejores pero no usa algunas buenas prácticas, los _pipelines_ de despliegue están separados de los proyectos y cada grupo de aplicaciones tiene su propio repositorio de _pipeline_ de CI/CD con lo que en realidad había varios _pipelines_ aún usando el mismo Concourse, que se ha convertido en código heredado difícil de mantener y aplicar cambios. Es un servicio administrado pero las alternativas son mejores ya no solo porque otras están mejor documentadas.

## Primera solución con Github Actions

Hace un tiempo [Github][github] añadió como complemento natural denominado [Github Actions][github-actions] que en esencia es una herramienta de CI/CD más moderna. Uno de sus atractivos es que es un servicio administrado con lo que no requiere dedicar tiempo ni personas a administrarlo y está integrado con los repositorios de git en Github, por otro lado cualquiera con cuenta en Github puede usarlo con lo que muchos profesionales ya tienen conocimiento avanzado de como usarlo, tiene un coste superado un límite de uso pero es un coste por uso y no fijo independientemente de si se usa o no.

En un primer momento el uso que le dábamos era para pasar los teses unitarios con integración continua para cada _push_ a un _pull request_ y en el _merge_ a la rama _main_. Luego para hacer el despliegue de algunos servicios en Google Kubernetes Engine, más tarde en Google App Engine. En un primer momento solo para los servicios de _backend_ y más tarde incorporando los servicios de frontend. Con lo que ahora no solo hay un único pipeline y este sirve tanto para los servicios de _backend_ como de _frontend_, un ahorro considerable en tiempo. Luego también para enviar notificaciones a [Slack][slack] en determinados canales para advertir de un nuevo despliegue y diversa información, integración con [SonarCloud][sonarcloud] con métricas de calidad de código y [Jira][jira] para extraer información de la petición asociada al _pull request_ y generar _releases_ de Github y archivos con lista de cambios.

[Github Actions tiene un marketplace][github-actions-marketplace] de acciones en el que hay integraciones para la totalidad de herramientas populares y muchas de las menos conocidas, con lo que usar algo es sencillo, rápido y da mucha flexibilidad en caso de surgir una nueva necesidad.

## Nuevo contexto

Usar Github Actions para la integración continua con teses unitarios y ser un servicio administrado ya era una mejora pero con una base de cientos de repositorios no es escalable ir replicando en cada repositorio el _pipeline_ de CI/CD. De forma que ahora surge la necesidad de construir un _pipeline_ reusable y suficientemente genérico para que cubra la necesidad como _pipeline_ de los servicios tanto de _backend_ como de _frontend_.

Esto significa separar los repositorios del código de los _pipeline_ nuevamente pero es una contrapartida opcional y preferible que copiar y pegar en la multitud de repositorios de código. Quizá con un monorepo sería otra la herramienta a utilizar pero en el contexto actual de multitud de repositorios es la opción viable, por tiempo, garantía de éxito y esfuerzo.

Utilizando [Google Cloud][google-cloud] el nuevo _pipeline_ ha de soportar varios artefactos de construcción como Dockerfiles, librerías java y desplegar en Google Cloud Platform, Google Kubernetes Engine (GKE), Google App Engine (GAE), Google Cloud Functions o publicar las librerías en repositorios de Maven o NPM en Google Artifact Registry, de tal forma que el _pipeline_ lo permita y suficientemente flexible para que el código no sea demasiado complicado además se ser escalable y permitir en el futuro añadir nuevos lenguajes, artefactos y entornos de ejecución si fuera necesario.

## Nueva solución con GitHub Actions

Así que toca buscar herramientas e implementar una solución usando Github Actions de tal forma que el nuevo pipeline sea reutilizable, suficientemente flexible para que cubra las diferentes necesidades y que sea suficientemente sencillo para que no sea un problema de mantenimiento.

Además de tener que construir diferentes artefactos, de poder desplegar en diferentes _runtimes_ ha de soportar repositorios en diferentes lenguajes como Javascript, [Typescript][typescript], [Node][nodejs] y Java en diferentes versiones cada uno.

Para soportar esta diversidad de lenguajes y versiones el _pipeline_ ha de independizarse y no acoplarse a cada lenguaje actual y los hipotéticos futuros, por ejemplo el comando de las tareas para los teses unitarios y análisis estático de código son diferentes para un proyecto de Node y otro de Java, [GNU Make][gnu-make] es una herramienta que proporciona esa abstracción pero finalmente opto por [Task][task] que es una equivalente que se define más simple y fácil de usar.

Otra de las necesidades es de realizar versionado, _bump commits_ que preparan la siguiente _release_ y crear _tags_ en el repositorio de git con cada nueva versión, para los proyectos Java estábamos usando el plugin de [Gradle Axion][gradle-axion] pero ahora necesitando soportar varios lenguajes para resolver este problema opto por desarrollar un script de bash a medida para hacer la _release_ ya que otras opciones no me convencieron puede porque no supe utilizar bien [semantic-release][semantic-release].

También me ha sido necesario buscar algo de información de buenas prácticas en un _pipeline_ en artículos y libros. Que sea sencillo y que sea rápido por ejemplo en el paso de integración continua, en los siguientes artículos y libros hay más información. Para las fases de este _pipeline_ me he basado en [Hashicorp Waypoint][waypoint] que define tres _build_, _deploy_ y _release_, separando el _deploy_ del _release_ con el cambio de tráfico a la nueva versión y ahí entrarían diferentes estrategias de _release_ con _canary_ o _blue-green_ además de como realizar las actualizaciones de las instancias o el _rollout_.

Enlaces.

* [Continuous Delivery Patterns and Anti-Patterns](https://dzone.com/refcardz/continuous-delivery-patterns)
* [Software Delivery Guide](https://martinfowler.com/delivery.html)
* [Continuous delivery pipeline 101 ](https://www.atlassian.com/continuous-delivery/principles/pipeline)
* [What is Continuous Delivery?](https://continuousdelivery.com/)
* [CI/CD Process: Flow, Stages, and Critical Best Practices](https://codefresh.io/learn/ci-cd-pipelines/ci-cd-process-flow-stages-and-critical-best-practices/)
* [Semantic Versioning 2.0.0](https://semver.org/)

Libros.

* [Continuous Delivery: Reliable Software Releases through Build, Test, and Deployment Automation](https://amzn.to/4bX9bKQ)
* [Continuous Delivery Pipelines: How To Build Better Software Faster](https://amzn.to/4igyURt)
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

El _pipeline_ como el anterior consta de tres fases principales la de _build_, _release_ y _deploy_. La _build_ realiza la integración continua con los teses unitarios y el análisis estático de código en un proyecto Java con [Gradle][gradle], [JUnit][junit], [PMD][pmd], [Checkstyle][checkstyle], [Spotbugs][spotbugs] y SonarCloud. La fase de _release_ genera el artefacto a desplegar y genera la release en caso de un merge a la rama principal o _main_. Y la fase de _deploy_ despliega en artefacto en el runtime que corresponde y envía la notificación del despliegue a Slack que en el caso a producción sirve para propósitos de auditoría.

Como es una buena práctica que la fase de integración continua sea rápida para obtener _feedback_ lo más rápido posible cada una de esas comprobaciones se lanza en paralelo, esto tiene más coste pero el beneficio de la inmediatez es preferible (y estar esperando también tiene un coste). En los proyectos de Node las tareas de teses unitarios y análisis estático de código serán otros. Como he mencionado para esta abstracción he utilizado Task combinado con el concepto de _matrix_ de Github Actions para lanzarlos en paralelo y permitir configurar el _caller workflow_ cuales son las tareas de _check_.

Una vez la fase de check es correcta se lanza la fase de construcción del artefacto y de generación de la _release_ con varias tareas específicas para cada artefacto que se activan con condiciones _if_ en función de cómo ha de construirse el artefacto. Ya sea con un Dockerfile o una librería Java, o más que se añadan posteriormente con [Buildpacks][buildpacks] por ejemplo.

* [Construir la imagen del contenedor de la aplicación usando Buildpacks][blogbitix-669]

Construido el artefacto hay que generar la _release_ y publicarlo en el repositorio de artefactos, para una imagen de contenedor en un repositorio de Docker y para una librería en un repositorio de [Maven][maven] o repositorio de NPM. Para etiquetar el repositorio de Git he utilizado un _script_ de bash y para generar la _release_ en Github [release-action](https://github.com/ncipollo/release-action).

### El _workflow_ de la fase de _build_

Hay varios archivos de configuración importantes, el de las tareas de Task que proporcionará el repositorio que haga uso de los _workflows_ y el del propio _workflow_ de _build_.

{{< code file="taskfile.yml" language="yml" options="" >}}
{{< code file=".github/workflows/build-check-task.yml" language="yml" options="" >}}

### El _workflow_ de la fase de _release_

En realidad no hay un único _workflow_ de _release_ sino que hay varios para segregar en los aspectos principales y no incluir demasiados condicionales. Hay un workflow de _release_ que genera una imagen de contenedor, genera la release utilizando tareas de Task y genera como resultado un paquete de NPM. El artefacto generado es subido al repositorio de artefactos.

{{< code file=".github/workflows/build-release-dockerfile.yml" language="yml" options="" >}}
{{< code file="miscellaneous/docker/Dockerfile" language="plain" options="" >}}

### El _workflow_ de la fase de _deploy_

Al igual que el _workflow_ de _release_ no se compone de un único _workflow_ sino de varios para segregar en función del _runtime_ de ejecución, segregar en varios workflows tiene la ventaja de que la cantidad de _inputs_ que recibe son menores lo que facilita su uso y complejidad. La fase de _deploy_ ha de desplegar el artefacto en el entorno de ejecución o _runtime_ que necesite el servicio, puede ser un servicio que hace uso de GKE, de GAE o un Google Function. Hay dos entornos el de desarrollo y el de producción, con reglas para requerir aprobaciones para hacer el despliegue que se han de configurar en cada repositorio.

Varios de los siguientes _steps_ del _workflow_ tienen _if_ condicionales en función de sus condiciones. Añadir un nuevo entorno de ejecución sería añadir un nuevo _workflow_ con sus _steps_ específicos para ese entorno y varios _steps_ iguales que el resto de entorno. Tanto el _workflow_ de _build_ como el de _deploy_ reciben una buena cantidad de parámetros con la que configurar el comportamiento del _workflow_.

{{< code file="github/workflows/deploy-gke.yml" language="yml" options="" >}}

### El _workflow_ reutilizable del CI/CD

Estos son los workflows reutilizables del pipeline, cada repositorio tiene que definir un _workflow_ que los invoque y proporcione los argumentos necesarios, en realidad los _workflows_ que define cada repositorio son simplemente una colección de propiedades proporcionados a los _workflows_ reutilizables. Este puede ser uno de ejemplo para un proyecto de Java que construye una imagen de Docker con un Dockerfile, lo publica en Google Artifact Repository y lo despliega en GKE.

{{< code file=".github/workflows/main.yml" language="yml" options="" >}}
{{< code file=".github/workflows/build-check-task.yml" language="yml" options="" >}}
{{< code file=".github/workflows/build-release-dockerfile.yml" language="yml" options="" >}}
{{< code file=".github/workflows/deploy-gke.yml" language="yml" options="" >}}

### Las acciones de soporte para los _workflows_ del CI/CD

Para reutilizar funciones en varios _jobs_ el _pipeline_ incluye algunas acciones también reutilizables, para configurar Gradle, Node, Task, Google Cloud y notificaciones de Slack entre otras. 

Un aspecto importante es que como regla los repositorios de los servicios solo han de usar _workflows_ reutilizables no _actions_. Esta regla tiene el objetivo facilitar el mantenimiento de los _workflows_ y poder hacer cambios manteniendo la compatibilidad hacia atrás.

{{< code file=".github/actions/build-information/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/build-release-dockerfile/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/build-release-git/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/build-release-git/git-release.sh" language="bash" options="" >}}
{{< code file=".github/actions/deploy-gke/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/deploy-notification-slack/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/setup-gradle/action.yml" language="yml" options="" >}}
{{< code file=".github/actions/setup-java/action.yml" language="yml" options="" >}}

Este es el aspecto de la notificación en Slack.

{{< image
    gallery="true"
    image1="image:github-actions-workflow-run.webp" optionsthumb1="650x450" title1="Workflow de Github Actions"
    image2="image:deploy-notification-slack.webp" optionsthumb1="650x450" title2="Notificación de deploy en Slack"
    caption="Workflow de Github Actions y notificación de deploy en Slack" >}}

### Cambios a futuro

Los _pipelines_ probablemente son trajes a medida según la organización cada una es diferente aunque todas comparten muchas necesidades. Este ejemplo tampoco es completo, por ejemplo podría añadirse la parte de pruebas una vez desplegado el artefacto en un entorno, pruebas funcionales, de rendimiento, seguridad, etc.

Como ideas para futuro tengo el integrar en los _workflows_ las _labels_ que se pueden asociar a los _pull request_ para modificar de alguna forma útil el comportamiento. Activar [Dependabot][github-dependabot] sin que sea demasiado pelmazo con las alertas. E incluso como en realidad los _workflows_ en los repositorios son una colección de propiedades podría mover esa configuración dentro del repositorio de los _workflows_, aunque tiene la desventaja que al desarrollador tendría que hacer commits en diferentes repositorios, asi que igual no es buena idea.

En realidad creo que estoy en una _quest_ de sacar el máximo partido de Github, tiene otras opciones interesantes como [Code scanning][github-code-scanning], [Secret scanning][github-secret-scanning] y [Github Copilot][github-copilot]. La otra quest es intentar reemplazar Jenkins y Nexus para la que ya tengo unas ideas que explorar, y esto sería una enorme mejora que permitiría reemplazar una infraestructura muy antigua con su consiguiente ahorro en costes y mantenimiento.

Es interesante también archivar los informes generados en caso de que un paso de _check_ falle en la integración continua. O crear algún _workflow_ periódico que no es útil ejecutar en cada _push_ a una rama como comprobaciones de seguridad en proyectos Java con [Owasp][owasp] para comprobar las dependencias o la cobertura de los teses unitarios.

Si me es posible a medida que realice estos cambios actualizaré el artículo.

{{% /post %}}
