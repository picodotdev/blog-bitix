---
pid: 659
type: "post"
title: "Realizar peticiones a APIs con herramientas de línea comandos e interfaz gráfica"
url: "/2022/11/realizar-peticiones-a-apis-con-herramientas-de-linea-comandos-e-interfaz-grafica/"
date: 2022-11-03T23:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:hoppscotch.svg"
tags: ["planeta-codigo", "programacion", "software"]
summary: "El comando curl es la herramienta de línea de comandos para realizar cualquier petición que requiera probar un _endpoint_ de una API o web que utilice el protocolo HTTP, tiene opciones para cualquier petición que sea requerida. Aunque curl es muy versátil y completa es una herramienta de línea de comandos sin algunas otras funcionalidades que las aplicaciones con interfaz gráfica son capaces de proporcionar. Postman es una aplicación con interfaz gráfica que cubre parte de las funcionalidades de curl y la mayoría que habitualmente un desarrollador necesita para probar una API, permite guardar colecciones y compartirlas con otros miembros de equipo de desarrollo asi como entornos y otras funcionalidades. Postman es la herramienta más conocida en su categoría y tiene una licencia comercial en base al numero de miembros de equipo que no es barata. Finalmente, Hoppscotch es una herramienta alternativa y equivalente a Postman de código abierto que no tiene coste de licencia y ofrece la mayoría de funcionalidades importantes como para sustituir a Postman."
---

{{% post %}}

{{< logotype image1="hoppscotch.svg" >}}

Las aplicaciones monolíticas son más simples y más sencillas de programar, la llamada de un módulo a otro de una aplicación consiste simplemente en una invocación de un método, sin embargo tiene sus propios problemas.

Las aplicaciones compuestas por múltiples microservicios, algunos de esos microservicios publica una API para que la invoquen otros microservicios. Los microservicios se comunican mediante llamadas remotas a través de la red, habitualmente utilizando el protocolo HTTP y REST aunque también es posible utilizar [GraphQL][graphql] y si se prefiere una API más RPC con [gRPC][grpc].

Para desarrollar y probar pero también para conocer los datos devueltos por un microservicio en alguna de sus _endpoints_ que expone en su API es muy útil utilizar alguna de las siguientes herramientas para hacer peticiones directamente al servicio sin tener que usar la interfaz web que utiliza el usuario hasta llegar al punto donde se realiza la petición.

{{< tableofcontents >}}

### El comando curl

La herramienta de línea de comandos curl permite realizar cualquier operación y obtener cualquier detalle tanto de la petición como de la respuesta del servidor del protocolo HTTP. Permite ver los certificados cuando se utiliza el protocolo HTTPS, las cabeceras enviadas y de respuesta de la petición, así como realizar peticiones utilizando cualquier verbo del protocolo HTTP.

El comando curl permite realizar una petición sin tener que implementar en código la llamada que se quiera probar. Cómo es un comando, no una aplicación, no permite guardar la colección de peticiones ni compartir con otros miembros del equipo de desarrollo una colección de peticiones ni mantener sincronizadas las colecciones y variables para los diferentes entornos de una aplicación.

Es posible utilizar un repositorio de código fuente como [Git][git] para guardar, compartir y mantener sincronizadas las colecciones peticiones pero el comando curl no ofrece ningún soporte para esto ni para utilizar variables y datos para diferentes entornos.

{{< code file="curl.sh" language="bash" options="" >}}
{{< code file="curl.out" language="plain" options="" >}}

### Postman

La aplicación con interfaz gráfica [Postman][postman] realiza en gran medida las mismas funciones que el comando curl pero con una aplicación de escritorio y con interfaz gráfica. Permite crear peticiones, añadir cabeceras en las peticiones y ver los resultados, cabeceras y código de estado devueltos en las peticiones HTTP.

Adicionalmente ofrece otras funcionalidades como soporte para trabajar en equipos compuesto por varios desarrolladores para compartir colecciones y entornos y mantenerlos sincronizados entre los diferentes miembros del equipo. Otras funciones que tiene es la de poder ejecutar _scripts_ de código antes de realizar la petición para calcular algún dato y enviarlo en la petición. Los entornos permiten crear colecciones de variables y cambiando de un entorno a otro utilizando en las peticiones diferentes valores, por ejemplo las credenciales de autorización para invocar un _endpoint_.

Postman es una herramienta muy conocida y utilizada, ofrece una aplicación de escritorio para los diferentes sistemas operativos incluyendo [GNU][gnu]/[Linux][linux] como una aplicación [Flatpak][flatpak]. Sin embargo, no es gratuita y en entornos profesionales requiere una licencia de uso que está en función del número de usuarios que la utilizan. Es cara y tiene un coste significativo para una herramienta aunque útil cuando se usa es una herramienta que se usa ocasionalmente.

{{< image
    gallery="true"
    image1="image:postman.png" optionsthumb1="650x450" title1="Postman"
    caption="Postman" >}}

### Hoppscotch, alternativa a Postman

Alternativas a Postman hay varias algunas como complementos para el editor de texto y código [Visual Studio Code][microsoft-visual-studio-code]. De las alternativas a Postman la más parecida y que tiene funcionalidades equivalentes y suficiente como para reemplazarla es [Hoppscotch][hoppscotch].

Al igual que Postman la aplicación Hoppscotch es una aplicación destinada a realizar peticiones a servicios utilizando HTTP y soportando otros protocolos como GraphQL, Websockets y MQTT. Una de las ventajas de Hoppscotch sobre Postman está en su licencia ya que es de código abierto y sin coste de licencia. La interfaz de usuario de Hoppscotch está muy cuidada y es similar a la de Postman permitiendo crear peticiones junto a sus cabeceras así como ver el resultado devuelto, cabeceras y código de estado.

Entre las funcionalidades que ofrece Hoppscotch están:

* Posibilidad de trabajar en equipos y compartir colecciones
* Scripts previos a la petición
* Soporta múltiples métodos de autenticación
* Entornos y variables
* Posibilidad de mostrar el curl e importar el curl
* Importar desde OpenAPI y Postman, exportar como JSON
* Posibilidad de ejecutarlo desde su web, en local como un contenedor [Docker][docker] o paquete npm

Aunque Hoppscotch tiene una licencia sin coste los desarrolladores ofrecen ser patrocinados en [su repositorio de GitHub](https://github.com/hoppscotch/hoppscotch) para seguir realizando cambios e implementando mejoras. Si es útil y se utiliza en un entorno profesional se anima a realizar en la medida de lo posible una contribución económica a los desarrolladores.

{{< image
    gallery="true"
    image1="image:hoppscotch.png" optionsthumb1="650x450" title1="Hoppscotch"
    caption="Hoppscotch" >}}

{{% /post %}}
