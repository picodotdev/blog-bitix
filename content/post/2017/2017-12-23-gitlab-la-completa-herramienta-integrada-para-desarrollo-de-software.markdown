---
pid: 290
title: "GitLab, la completa herramienta integrada para desarrollo de software"
url: "/2017/12/gitlab-la-completa-herramienta-integrada-para-desarrollo-de-software/"
date: 2017-12-23T10:00:00+01:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "planeta-codigo", "software", "software-libre"]
series: ["gitlab"]
summary: "Al desarrollar software una herramienta de control de versiones como Git y otras como un gestor de peticiones, una herramienta de integración continua o despliegue contínuo o una wiki para documentación son necesarias. Hay productos específicos para cada uno de ellos pero GitLab proporciona en una único producto todas estas facilitando su instalación y administración estando integradas en el mismo producto."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="gitlab.svg" title1="GitLab" width1="200" >}}

Usar una herramienta de control de versiones para el código fuente de un proyecto o programa es imprescindible para recuperar versiones anteriores de los archivos, comparar y compartir los cambios con el resto de desarrolladores del equipo. Otras herramientas imprescindibles son un gestor de peticiones para documentar cuál es el trabajo a realizar, ver en que se está trabajando, priorizar el trabajo inmediato que se realizará y el estado de cada petición. Por otro lado una herramienta donde recoger documentación del proyecto ayuda a compartir información y hacer que ese conocimiento quede recogido en algún lugar que pueda ser consultado con posterioridad o para futuros desarrolladores. También en estos días es imprescindible una herramienta para automatizar la ejecución de las pruebas unitarias y funcionales en cada _commit_ al repositorio de código fuente.

Para cada una de estas funcionalidades hay herramientas específicas: [Git][git] para la herramienta de control de versiones, [GitHub][github] con algunas funciones colaborativas como _pull request_ y revisiones de código, [Jira][jira] para la gestión de peticiones, [MediaWiki][mediawiki] para documentar cierta información o [Jenkins][jenkins] para la ejecución de las pruebas automatizadas. Instalar estas herramientas individualmente requiere tiempo y dedicación, además de mantenimiento posterior.

[GitLab][gitlab] es una herramienta que proporciona estas funcionalidades en un único producto y por tanto más fácil de instalar y administrar. Quizá su gestor de peticiones y wiki no tenga tantas opciones como sus respectivos productos específicos pero para la mayoría de casos son más que suficientes. Y tiene un desarrollo muy activo añadiendo características cada pocas semanas.

Hay varias formas de instalar GitLab, una de ellas es [instalandolo como un paquete de software](https://about.gitlab.com/installation/#ubuntu) en la propia máquina, usando una [imagen de Docker](https://docs.gitlab.com/ce/install/docker.html) o con una [imagen de Bitnami](https://bitnami.com/stack/gitlab). Siguiendo el artículo de [Introducción a Bitnami][blogbitix-54] puedes conocer como instalar GitLab en una máquina virtual con [VirtualBox][virtualbox] y con la [serie de artículos sobre Docker][blogbitix-serie-docker] como instalarlo en forma de contenedor y en la web de GitLab se explica como instalarlo como un software más de un sistema.

Usando [Docker][docker] y el siguiente archivo de [Docker Compose][docker-compose] basta el comando <code>docker-compose up</code> para iniciar GitLab. Instalado GitLab de alguna de las formas anteriores usando un navegador web y con la dirección _https\://localhost_ (configurados los certificados SSL y la ubicación de los volúmenes) se accede a la aplicación de GitLab, donde inicialmente se pide la contraseña del usuario administrador _root_ con la que iniciar sesión y realizar las tareas administrativas como crear otros usuarios y proyectos.

{{< gist picodotdev cedbdd0dc56baf73cb38a410fbd3c4c9 "docker-compose.yml" >}}

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="290"
        image1="change-password.png" thumb1="change-password-thumb.png" title1="Contraseña de root"
        image2="sing-in.png" thumb2="sing-in-thumb.png" title2="Inicio de sesión"
        caption="Contraseña e inicio de sesión" >}}
    {{< figure year="2017" pid="290"
        image1="welcome.png" thumb1="welcome-thumb.png" title1="Página de bienvenida"
        caption="Página de bienvenida" >}}
    {{< figure year="2017" pid="290"
        image1="profile.png" thumb1="profile-thumb.png" title1="Perfil de usuario"
        image2="admin-area.png" thumb2="admin-area-thumb.png" title2="Área de administración"
        caption="Perfil de usuario y área de administración" >}}
</div>

Iniciada sesión ya se puede empezar a usar GitLab a crear usuarios con sus claves SSH, repositorios de Git pudiendo importar otros existentes, crear nuevos, páginas de una wiki y peticiones, integración y entrega continua y personalizar el panel _kanban_ con el flujo que deseamos que sigan las peticiones en la organización, por ejemplo, _planning_, _ready_, _doing_, _review_, _merged_ y _deployed_.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="290"
        image1="new-proyect.png" thumb1="new-proyect-thumb.png" title1="Nuevo proyecto"
        caption="Nuevo proyecto" >}}
    {{< figure year="2017" pid="290"
        image1="new-issue.png" thumb1="new-issue-thumb.png" title1="Nueva petición"
        image2="issues-board.png" thumb2="issues-board-thumb.png" title2="Panel de peticiones"
        caption="Nueva petición y panel de peticiones" >}}
    {{< figure year="2017" pid="290"
        image1="new-wiki-page.png" thumb1="new-wiki-page-thumb.png" title1="Nueva página wiki"
        image2="wiki-page.png" thumb2="wiki-page-thumb.png" title2="Página wiki"
        caption="Página wiki" >}}
</div>

Creado un proyecto GitLab e introducida la clave pública SSH nos proporciona la dirección URL con la que se puede clonar o añadir un repositorio de Git y empezar a subir, modificar y eliminar archivos del repositorio de control de versiones. Hecho algún _commit_ y subido algún archivo desde GitLab se puede ver el contenido del repositorio de Git.

{{< gist picodotdev cedbdd0dc56baf73cb38a410fbd3c4c9 "clone-repository.yml" >}}
{{< gist picodotdev cedbdd0dc56baf73cb38a410fbd3c4c9 "existing-folder.yml" >}}

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="290"
        image1="project-details-1.png" thumb1="project-details-1-thumb.png" title1="Detalles de proyecto"
        image2="project-details-2.png" thumb2="project-details-2-thumb.png" title2="Detalles de proyecto"
        caption="Detalles de proyecto" >}}
    {{< figure year="2017" pid="290"
        image1="source-file.png" thumb1="source-file-thumb.png" title1="Contenido de archivo código fuente"
        caption="Contenido de archivo de código fuente" >}}
</div>

Otras formas de usar GitLab es desde la nube de [Amazon EC2][amazon-ec2] o de forma SaaS a un coste de entre 39€ y 199€ por usuario y mes pero si la nube no es una opción una de las ventajas de GitLab es que se puede instalar en algún sistema propio de cualquiera de las formas anteriores y tener proyecto privados. GitLab es otra herramienta que a añadiría a [Herramientas para un proyecto Java][blogbitix-84]. Hay alguna cosa adicional a conocer [como realizar una actualización](https://docs.gitlab.com/ce/update/README.html) cuando se publique una nueva versión o [como hacer copias de seguridad y restaurarlas](https://docs.gitlab.com/ce/raketasks/backup_restore.html#creating-a-backup-of-the-gitlab-system). Lo que he mostrado aqui son solo una introducción a unas pocas de las muchas posibilidades de GitLab.

{{% code git="blog-ejemplos/tree/master/GitLab" command="docker-compose up" %}}

{{% /post %}}
