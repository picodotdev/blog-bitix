---
pid: 245
title: "Iniciar rápido un proyecto de Java con Gradle o de Spring con Spring Initializr"
url: "/2017/07/iniciar-rapido-un-proyecto-de-java-con-gradle-o-de-spring-con-spring-initializr/"
date: 2017-07-09T13:00:00+02:00
language: "es"
sharing: true
comments: true
promoted: false
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="gradle.svg" title1="Gradle" width1="200" image2="spring.svg" title2="Spring" width2="200" image3="java.svg" title3="Java" width3="200" >}}

Cuando se empieza un proyecto nuevo desde cero se debe disponer de una herramienta de construcción, una estructura de directorios que siga las convenciones de la herramienta de construcción, añadir las dependencias que vayamos a utilizar y alguna clase que muestre algo al ejecutarlo para comprobar que disponemos de la infraestructura básica de compilación, teses, generación de artefactos e inicio de proyecto.

Dado que las clases Java hay que compilarlas para facilitar la tarea están las herramientas de construcción y estas siguen una serie de convenciones en la estructura de directorios además de requerir algunos archivos. En un proyecto de duración de varios meses o años el tiempo dedicado a crear esta infraestructura básica es despreciable y no complicado si nos basamos en un proyecto similar del que copiar, sin embargo, para hacer alguna prueba rápida es costoso. 

En [Maven][maven] existen los arquetipos que construyen el esqueleto básico del proyecto en base a unas plantillas. En [Gradle][gradle] el equivalente es el [_plugin_ init ](https://docs.gradle.org/current/userguide/build_init_plugin.html) pudiendo elegir crear la estructura de una librería o aplicación Java, [Groovy][groovy] o [Scala][scala] ejecutable. También se puede elegir el _framework_ para hacer las pruebas automatizadas unitarias, de integración o funcionales.

Al usar el _plugin_ _init_ de Gradle se especifica el tipo de artefacto, aplicación o librería, y el _framework_ para las pruebas unitarias en este caso [Spock][spock].

{{< gist picodotdev 195fab0e57a91bfd17328db9b24d84ef "gradle-init.sh" >}}

Para proyectos que usen [Spring Boot][spring-boot] está disponible la herramienta [Spring Initializr][spring-initializr] que en pocos minutos permite disponer de una aplicación funcional con las propiedades que se seleccionen. Se puede elegir la herramienta de construcción, Maven o Gradle, la versión de Spring Boot, los metadatos de la aplicación para el paquete de las clases y artefacto, las dependencias de otros módulos de Spring y otras librerías populares.

<div class="media" style="text-align: center;">
    {{< figure year="2017" pid="245"
        image1="spring-initalizr.png" thumb1="spring-initalizr-thumb.png" title1="Generador de proyectos Spring Initalizr" >}}
</div>

Cambiando a la versión completa del generador es posible cambiar el tipo de empaquetado (_jar_ o _war_), la versión de Java o el lenguaje de programación, además seleccionar aquellas dependencias que inicialmente sean necesarias, muchas son de otros proyectos de Spring. Las dependencias están agrupadas por categorías y van desde seguridad, _framework_ web, motores de plantillas, SQL, NoSQL, numerosas herramientas para la nube, integración con redes sociales, entrada/salida o utilidades para operaciones.

Una vez seleccionadas las opciones se genera el proyecto y descarga un archivo comprimido _zip_. Con el comando <code>gradlew bootRun</code> si inicia la aplicación, pero dependiendo de las dependencias incluidas quizá sea necesaria hacer alguna configuración adicional antes de poder iniciar la aplicación, por ejemplo si seleccionamos la dependencia de [jOOQ][jooq] hay que definir las propiedades para el [DataSource](https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html) en el archivo _application.properties_ con las que el contenedor inversión de control de Spring pueda crear las conexiones a la base de datos.

{{< gist picodotdev 195fab0e57a91bfd17328db9b24d84ef "tree-initializr.sh" >}}

Como con todos los generadores de código es recomendable saber suficientemente que es código que generan para en caso de modificaciones posteriores saber como aplicarlas. También es posible que no se adapte exactamente a lo que necesitamos, por ejemplo, si queremos hacer un [multiproyecto con Gradle][blogbitix-96] o si una dependencia necesaria no está incluida en la lista de categorías hay añadirla después. A partir de aquí se empieza a programar el proyecto.

En el libro [Spring Boot in Action](http://amzn.to/2tvCJMs) se comenta detalladamente Spring Boot que ofrece múltiples nuevas posibilidades que hace más fácil el desarrollo de un proyecto con Spring. Por otro lado para conocer más detalladamente y ampliamente el ecosistema de Spring está el libro [Mastering Spring 5.0](http://amzn.to/2tZInqM) que también inlcuye secciones sobre Spring Boot.

<div class="media-amazon" style="text-align: center;">
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=1617292540&linkId=86ce91eca968f6de1d0a777f48ca3069"></iframe>
    <iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="//rcm-eu.amazon-adsystem.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=blobit-21&o=30&p=8&l=as4&m=amazon&f=ifr&ref=as_ss_li_til&asins=B01N750Z7H&linkId=e4f7f44a8b854b00a547d526b83f6469"></iframe>
</div>

 En definitiva el _plugin_ _init_ de Gradle y la utilidad Spring Initializr son unas buenas herramientas para empezar un proyecto Java rápidamente y en pocos minutos disponer de un proyecto básico funcional y listo para implementar la funcionalidad de la aplicación.

{{% /post %}}
