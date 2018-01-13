---
pid: 294
title: "Integración y entrega continua con GitLab"
url: "/2018/01/integracion-y-entrega-continua-con-gitlab/"
date: 2018-01-13T10:30:00+01:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "planeta-codigo", "software", "software-libre"]
series: ["gitlab"]
draft: true
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="gitlab.svg" title1="GitLab" width1="200" >}}

[GitLab][gitlab] es una herramienta muy completa, una de las necesidades de los proyectos es tener teses y que estos se ejecuten con cada _commit_ para asegurar que los cambios no introducen errores en las funcionalidades cubiertas por los teses, esto es la integración continua. La entrega inmediata es una vez que los teses se han ejecutado correctamente hacer el despliegue en los servidores de forma pasa el tiempo mínimo entre que una funcionalidad está desarrollada y esta se puede utilizar.

GitLab proporciona integración continua (_Continuos Integration_, CI), entrega continua (_Continuos Delivery_, CD) sin necesidad de una herramienta externa más especializada como es [Jenkins][jenkins], se puede usar individualmente cada una de ellas. En el siguiente ejemplo muestro como configurar GitLab para crear un o varios _runner_ que son los que realizan las acciones de integración y entrega continua y el archivo descriptor _.gitlab-ci.yml_ en formato _yaml_ que define las acciones específicas del proyecto que se ejecutarán con cada _commit_ en el repositorio [Git][git].

Jenkins es una herramienta más especializada que también sirve para realizar integración y entrega continua. En la página [GitLab vs Jenkins](https://about.gitlab.com/comparison/gitlab-vs-jenkins.html) se comparan las características de ambas herramientas, aunque en esa página de comparación GitLab está indicando que tiene algunas características más y aunque no tuviera tantas para la mayoría de los casos de uso es más que suficiente con la ventaja de no necesitar una herramienta más al estar estar ya integrado en GitLab.

Para definir las acciones y sus _pipelines_ se utiliza un archivo _.gitlab-ci.yml_ que se añade en el directorio raíz del código fuente del proyecto en su repositorio de Git. El _pipeline_ según las necesidades del proyecto consta de varios pasos o _steps_ que se ejecutan de forma secuencial. Si en un mismo paso hay varias acciones estas se ejecutan de forma paralela. En el ejemplo de un proyecto con [Spring Boot][spring-boot] el _pipeline_ consta de las acciones de compilación y de ejecución de pruebas unitarias y [creación de documentación _javadoc_][blogbitix-260]. La compilación se realiza primero y posteriormente de forma paralela la ejecución de los teses y el _javadoc_.

[Archivo .gitlab-ci.yml]
[El javadoc genera un archivo subirlo y como obtenerlo]

Añadido el archivo del _pipeline_ al código fuente del proyecto hay que definir un _runner_ que se encargará de ejecutarlo con cada _commit_. Hay que registrarlo y se necesita un _token_ que se puede obtener desde la sección XXXX. También hay que especificar el tipo de _runner_, hay varios tipos, en este caso se usa el de _docker_ para ejecutar el _pipeline_ dentro de un contenedor de [Docker][docker]. En en archivo de [Docker Compose][docker-compose] es necesario que el _runner_ pueda comunicarse con el servicio de Docker, para ello como punto de montaje de _runner_ se especificar el archivo _docker.sock_.

[Archivo docker compose]
{{< gist picodotdev id "file" >}}

El comando para registrar un nuevo _runner_ es:

[Comando registrar _runner_]

La acción de generar el _javadoc_ genera un archivo que se puede obtener desde XXX.

[Imagen generar javadoc]
[Ejemplo código fuente, repositorio de git con dos upstream sources, subirlo a git]
<div class="media" style="text-align: center;">
    {{< figure year="2018" pid=""
        image1="" thumb1="-thumb.png" title1=""
        image2="" thumb2="-thumb.png" title2=""
        caption="" >}}
</div>

En la sección de referencia hay unos buenos enlaces de documentación de GitLab sobre la integración continua, despliegue continuo y entrega continua.

{{% code git="blog-ejemplos/tree/master/GitLab" command="docker-compose up" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [GitLab Continuous Integration (GitLab CI/CD)](https://docs.gitlab.com/ce/ci/)
* [Getting started with GitLab CI/CD](https://docs.gitlab.com/ce/ci/quick_start/README.html)
* [Configuration of your jobs with .gitlab-ci.yml](https://docs.gitlab.com/ce/ci/yaml/README.html)
* [A sample of .gitlab-ci.yml for a gradle project](https://gist.github.com/daicham/5ac8461b8b49385244aa0977638c3420)
{{% /reference %}}

---

Ver ejemplo gitlab-ci
docker exec -it gitlab-runner gitlab-runner register --url "http://gitlab" --registration-token "zsTuzFHQ5BiGaG6PoZJ5" --tag-list "gitlab-runner-01" --run-untagged "true" --locked "false" --executor "shell"
git init
git remote add origin git@172.17.0.2:root/gradle.git
git push origin master
vim /etc/host gitlab

{{% /post %}}
