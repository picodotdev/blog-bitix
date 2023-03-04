---
pid: 613
type: "post"
title: "Configuración de una aplicación con Spring Boot y configuración centralizada con Spring Cloud Config"
url: "/2021/12/configuracion-de-una-aplicacion-con-spring-boot-y-configuracion-centralizada-con-spring-cloud-config/"
aliases: ["/2018/09/servicio-de-configuracion-para-microservicios-con-spring-cloud-config/"]
date: 2021-12-16T19:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:spring.svg"
tags: ["java", "planeta-codigo"]
series: ["spring-cloud"]
summary: "La configuración de una aplicación es indispensable para su funcionamiento, permite no hardcoredar ciertos valores en el código fuente al mismo tiempo que externalizarlos en archivos de más fácil edición. Externalizar la configuración de la aplicación permite utilizar el mismo artefacto binario en todos los entornos, los valores que cambian en cada entorno es posible proporcionarlos de diferentes formas y formatos desde archivos en el _classpath_ hasta variables de entorno o un servidor de configuración. Spring Boot permite obtener los valores de diferentes fuentes e implementa un mecanismo de prioridad para determinar el valor a usar."
---

{{% post %}}

{{< logotype image1="spring.svg" image2="java.svg" >}}

La configuración de [Spring Boot][spring-boot] proporciona un mecanismo muy flexible para la configuración de diferentes fuentes. Cada fuente tiene un orden de preferencia para establecer los valores de las propiedades, además se integra con el servidor de configuración centralizada de [Spring Cloud Config][spring-cloud-config].

La configuración permite cambiar el comportamiento de la aplicación sin cambiar el código ni generar un nuevo artefacto. No hardcodear los valores en el código y extraer la configuración permite utilizar el mismo artefacto en cualquier entorno, ya sea desarrollo, pruebas o producción. Utilizar el mismo artefacto para todos los entornos tiene la ventaja de no introducir un error en la construcción del artefacto como cabría la posibilidad generando un artefacto binario para cada uno de los entornos, usar el mismo artefacto es necesario para que las pruebas realizadas sobre el artefacto en el entorno de desarrollo o pruebas se consideren válidas para producción.

Aunque en algunos sitios se recomienda que la configuración de la aplicación esté separada de artefacto de despliegue, en realidad más que la configuración esté separada es necesario poder tener un mecanismo de orden de preferencia de los valores de configuración. La aplicación puede tener unos valores de configuración por defecto pero es necesario poder sobreescribirlos debido a que algunos no se desean incluir en el código fuente o en el propio artefacto, también es necesario para cambiar los valores por defecto incluidos en el artefacto si se desea corregir un error sin necesidad de generar un nuevo artefacto.

Por otro lado es aconsejable tener bajo el control de versiones los archivos de configuración como cualquier otro archivo de código fuente de la aplicación, para con el historial del control de versiones ver los cambios que se han hecho o volver a versiones anteriores.

{{< tableofcontents >}}

## Necesidades según el rol

Las diferentes personas cada una con su rol desea tener la capacidad de configurar la aplicación. A los desarrolladores nos interesa para poder externalizar ciertas variables del código de la aplicación para tener la capacidad de cambiar los valores sin modificar el código. Esto es más fácil que encontrar donde están los valores hardcodeados y modificar las diferentes coincidencias, y evitando tener que recompilar.

Aunque los archivos de configuración no son código ejecutable forman parte del código de la aplicación si la configuración se incluye dentro del artefacto, también por comodidad el desarrollador desea cambiarlos al mismo tiempo que el código para mantener la consistencia entre el código y la configuración, ya que todas la variables de configuración que requiere el código deben tener un valor sino se produce un error en tiempo de ejecución.

Las personas con el rol de sistemas o SRE y por las tareas de mantenimiento de sistemas y operaciones también requieren tener la capacidad de cambiar las propiedades ajustando los valores a los adecuados según el entorno de ejecución sin modificar el artefacto, quizá no sobrescribir los valores de todas las propiedades pero si las relevantes desde el punto de vista de sistemas.

Es necesario para ajustar los valores por defecto o hacer una corrección que no requiera generar un nuevo artefacto sino simplemente ajustar un valor de configuración. Por rapidez y porque hacer una corrección generando un nuevo artefacto requiere pasar todo el proceso de pruebas para asegurar que el nuevo artefacto no incluye algún cambio adicional no deseado.

También por motivos de seguridad es necesario externalizar los valores de algunas variables como contraseñas, claves y certificados, de modo que aunque alguien tenga acceso al artefacto no tenga acceso a las credenciales de los servicios que usa.

Los valores adicionales se proporcionan habitualmente como variables de entorno o con archivos externalizados del artefacto que se buscan en el sistema de archivos, de esta forma la configuración incluida en el artefacto por los desarrolladores es sobrescrita por la configuración por la proporcionada por las personas con el rol de sistemas.

La solución para estas diferentes necesidades de los diferentes roles es obtener los valores de las variables de configuración de varias fuentes junto un de orden de preferencia para determinar que valor se toma en caso de que esté definido en varias fuentes.

En las configuraciones más avanzadas es necesario un mecanismo para que las aplicaciones obtengan la configuración de un servidor donde esté centralizada. Al igual que un servicio de registro y descubrimiento es esencial para los microservicios un servicio de obtención de configuración de donde obtengan su configuración es también útil. Dado el gran número de microservicios de los que puede estar compuesto un sistema, su carácter efímero, los varios entornos de ejecución (desarrollo, pruebas, producción, ...) mantener centralizada la configuración en un único sitio hace las cosas mucho más sencillas cuando hay que cambiar el valor de alguna propiedad. En vez de las alternativas con un archivo de configuración, aún externalizado del artefacto, en el sistema de archivos del entorno de ejecución o a través de variables de entorno que deben ser aprovisionadas.

## Configuración en una aplicación de Spring Boot

Spring Boot integra la funcionalidad de obtener la configuración de varias fuente y define un orden de preferencias en caso de conflicto. Spring Cloud Config Server es un servicio que proporciona un mecanismo adicional para centralizar la configuración de las aplicaciones.

En una aplicación monolítica, un monolito modular o en un entorno donde no hay muchas aplicaciones el mecanismo de configuración proporcionado Spring Boot es suficiente. Sin embargo, en un entorno de microservicios o donde hay muchas aplicaciones tener una configuración centralizada proporciona varios beneficios. Los beneficios de un servidor de configuración es centralizar en un única fuente lo que facilita su ubicación, modificación y despliegue en las aplicaciones.

### Orden de preferencia de las propiedades

Spring Boot soporta varias fuentes de las que obtener la configuración desde archivos de configuración en el _classpath_, archivos externalizados en el sistemas de archivos, argumentos del programa, propiedades del sistema de la máquina virtual, variables de entorno e incluso otros mecanismos extensibles personalizados.

Cada una de estas fuentes tienen un orden de búsqueda y prioridad donde las fuentes posteriores sobrescriben los valores de las anteriores o se añaden nuevas variables.

En la documentación de Spring Boot están detalladas estas fuentes y prioridad entre ellas. Por ejemplo, la configuración establecida en los archivos de configuración es sobrescrita por la configuración proporcionada como variables de entorno.

* [Spring Boot, Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)

Con Spring Cloud Config las propiedades del servidor de configuración se cargan con posterioridad de los archivos de datos de configuración incluidos en el _classpath_ dentro del artefacto o de los archivos externalizados en el sistema de archivos. Sin embargo, la configuración establecida como variables de entorno siguen teniendo más preferencia.

### Archivos de datos de configuración

Los archivos de configuración entre ellos también tienen un orden de búsqueda en varios directorios y prioridad, iguamente detallado en la documentación de Spring Boot. Teniendo más preferencia los archivos externalizados y dentro de estos los más específicos para un entorno de ejecución. 

Las ubicaciones en las que Spring Boot archivos de configuración también tiene una preferencia además de ser a su vez configurable.

* [Spring Boot, External Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files)
* [Spring Boot, Importing Additional Data](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files.importing)

Los archivos de configuración se pueden definir en el formato _properties_ y _yaml_. La ventaja del formato _yaml_ es que permite agrupar las propiedades de forma jerárquica que es más legible que el formato _properties_ habitualmente utilizado en las aplicaciones Java por defecto. La desventaja de _yaml_ es que es un formato en el que una mala tabulación genera algún tipo de error o mal funcionamiento.

{{< code file="application-format.properties" language="plain" options="" >}}
{{< code file="application-format.yml" language="yaml" options="" >}}

### Propiedades que afectan a la configuración

En el sistema de configuración de Spring hay ciertas variables que afectan y permiten adaptar la configuración por defecto a las preferencias o necesidades de la aplicación.

Algunas de estas propiedades son el nombre del servicio, los perfiles activos o las ubicaciones de búsqueda de archivos de configuración.

{{< code file="spring-boot-config-properties.yml" language="yaml" options="" >}}

Estas otras propiedades se utilizan cuando la aplicación de Spring Boot obtiene la configuración adicionalmente de un servidor de configuración de Spring Cloud Config.

{{< code file="spring-boot-cloud-config-properties.yml" language="yaml" options="" >}}

En las rutas de búsqueda con el prefijo _optional:_ en caso de no encontrarse la fuente el inicio de la aplicación en vez de fallar con una excepción se ignora y se continúa a riesgo de utilizar los valores de las fuentes anteriores e ignorando lo que tuviese esa fuente opcional.

{{< code file="spring-boot-optional.yml" language="yaml" options="" >}}

## El servidor de configuración centralizada Spring Cloud Config Server

Un servidor de configuración permite cambiar o proporcionar una forma adicional de la que la aplicación obtiene propiedades y valores de configuración. La aplicación al iniciar realiza una petición al servidor de configuración y obtiene las propiedades adicionales de configuración. En el caso de Spring Cloud Config Server ofrece una interfaz REST que usa las aplicaciones para realizar la petición.

Por otro lado, la forma de configurar la aplicación cambia, en vez de proporcionar la configuración a la aplicación a cada una de las instancias de su servicio en variables de entorno o en archivos estáticos es cada instancia de la aplicación la que obtiene la configuración de un servidor. Es muy interesante para las personas con el rol de operaciones o SRE y para la arquitectura del sistema.

Otra de sus utilidades es una forma de que ciertos servicios obtengan la configuración cuando sus entornos y sistemas de archivos son efímeros como es el caso de las [funciones de Google Cloud](https://cloud.google.com/functions/) o [lambdas de AWS](https://aws.amazon.com/es/lambda/).

Dado que este servicio de configuración es esencial para que los microservicios puedan obtener su configuración sin el cual no pueden proporcionar su funcionalidad hay que configurarlo de tal manera que sea  tolerante a fallos. Una de las medidas para hacerlo tolerante a fallos es iniciar varias instancias de servidores de configuración, estas instancias se autorregistran en el servicio de descubrimiento para que  los microservicios puedan descubrirlos y obtener su configuración al iniciarse.

### Fuentes de configuración

El servidor de configuración centralizada Spring Cloud Config soporta varios sistemas diferentes en los que almacenar las propiedades de configuración o _backends_ para recuperarlos cuando una instancia del servicio la solicite.

Una opción es utilizar un repositorio de [Git][git] con las ventajas asociadas del control de versiones como historial para mantener un registro de los cambios o volver a una versión anterior. Otros son un sistema de archivos, en una base de datos relacional con JDBC, [Redis][redis], [Vault][vault] y algunos otros específicos más.

* [Spring Cloud Config Server, Environment Repository](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_environment_repository)

### Propiedades que afectan a la configuración de Spring Cloud Config Server

El servidor de configuración de Spring Cloud Config también tiene variables de configuración, varias según el sistema de almacenamiento o _backend_ donde se persisten las propiedades de configuración de los servicios. Otras propiedades son para proporcionar las credenciales de autenticación de los _backends_.

* [Spring Cloud Config, Quick Start](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_quick_start)
* [Spring Cloud Config Server, Git Backend](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_git_backend)
* [Spring Cloud Config Server, File System Backend](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_file_system_backend)

En el caso de Git la propiedad _label_ del servicio en Git puede ser la huella o _hash_ del _commit_, una rama o una etiqueta.

{{< code file="spring-cloud-config-server-git-properties.yml" language="yaml" options="" >}}

En el caso del sistema de archivos como _backend_ esta propiedad permite configurar las rutas en las que buscar los archivos de configuración y la disposición de los archivos de configuración en la estructura de directorios. En este caso la propiedad _label_ es la versión de la aplicación.

Las propiedades _application_, _profile_  y _label_ permiten identificar la configuración de un servicio, para un entorno y  de una versión específica.

{{< code file="spring-cloud-config-server-filesystem-properties.yml" language="yaml" options="" >}}

## Ejemplo de configuración en aplicación de Spring Boot

Esta aplicación de Spring Boot tiene varias propiedades de configuración. Para mostrar el mecanismo de preferencia en la resolución de los valores cada una de las propiedades se obtiene de una fuente distinta. En esta lista de menor preferencia a mayor preferencia, desde un archivo de configuración en el _classpath_, archivo externalizado, servidor de configuración, argumento de programa y variable de entorno.

{{< code file="Main-client.java" language="java" options="" >}}

Este es el archivo de configuración que se incluye en el _classpath_ y como parte del artefacto, no está externalizado.

{{< code file="application-classpath.yml" language="yaml" options="" >}}

Este archivo de configuración externo al artefacto proporciona el valor de una propiedad.

{{< code file="application-external.yml" language="yaml" options="" >}}

Sin ninguna configuración adicional y con el servidor de configuración no iniciado estos son lo valores que toman las propiedades en la aplicación.

{{< code file="gradle-run-1.sh" language="bash" options="" >}}
{{< code file="System.out-1" language="plain" options="" >}}

Añadiendo al iniciar el programa un argumento o variable de entorno para configurar el valor de una propiedad la aplicación toma el valor proporcionado.

{{< code file="gradle-run-2.sh" language="bash" options="" >}}
{{< code file="System.out-2" language="plain" options="" >}}

{{< code file="gradle-run-3.sh" language="bash" options="" >}}
{{< code file="System.out-3" language="plain" options="" >}}

Con el servidor de configuración iniciado la aplicación en este caso adicionalmente toma el valor de la configuración para la aplicación del servidor. En casos casos anteriores la aplicación en el inicio no falla porque la fuente del servidor de Spring Cloud Config Server se considera opcional.

{{< code file="gradle-run-4.sh" language="bash" options="" >}}
{{< code file="System.out-4" language="plain" options="" >}}

Cambiando la propiedad _label_ o como variable de entorno a través de los argumentos en el inicio del servicio es posible cambiar la versión que el servidor de configuración devuelve para el servicio.

{{< code file="gradle-run-5.sh" language="bash" options="" >}}
{{< code file="System.out-5" language="plain" options="" >}}

{{< code file="gradle-run-6.sh" language="bash" options="" >}}
{{< code file="System.out-6" language="plain" options="" >}}

Las dependencias en el archivo de construcción con [Gradle][gradle] son las siguientes.

{{< code file="build-client.gradle" language="groovy" options="" >}}

## Ejemplo de configuración centralizada con Spring Cloud Config Server

El servidor de configuración de Spring Cloud Config es posible implementarlo como una aplicación de Spring Boot. La aplicación de Spring Boot simplemente requiere utilizar la anotación _@EnableConfigServer_ y configurar el almacenamiento del _backend_ para las propiedades de configuración, en el ejemplo utilizando el sistema de archivos.

{{< code file="Main-server.java" language="java" options="" >}}

Los archivos de configuración para los microservicios en este ejemplo están en el directorio _misc/config_ donde siguiendo algunas convenciones para asignar el nombre a los archivos se pueden personalizar las configuraciones de los microservicios según el entorno y perfil con el que se active. Spring Cloud Config denomina un _backend_ como el sistema de almacenamiento de los datos de configuración en este caso se utiliza el sistema de archivos, sin embargo, hay otras disponibles como un repositorio de git el cual ofrece varias ventajas propias de un repositorio de código como historial, ramas de trabajo y hacer cambios con un _commit_.

{{< code file="application-server.yml" language="yaml" options="" >}}

Con los siguientes archivos de configuración en el servidor para el servicio, en función de la versión de la aplicación solicitada las propiedades devueltas cambian. Estos comandos solicitan al servidor la configuración de la aplicación a través de una petición de red con la interfaz REST, lo datos se devuelven en formato JSON.

{{< code file="application-1.0.yml" language="yaml" options="" >}}
{{< code file="application-2.0.yml" language="yaml" options="" >}}
{{< code file="application-default.yml" language="yaml" options="" >}}

{{< code file="curl-spring-cloud-config-server-1.0.sh" language="bash" options="" >}}
{{< code file="curl-spring-cloud-config-server-1.0.out" language="bash" options="" >}}

{{< code file="curl-spring-cloud-config-server-2.0.sh" language="bash" options="" >}}
{{< code file="curl-spring-cloud-config-server-2.0.out" language="bash" options="" >}}

{{< code file="curl-spring-cloud-config-server-default.sh" language="bash" options="" >}}
{{< code file="curl-spring-cloud-config-server-default.out" language="bash" options="" >}}

Dado que el servicio de configuración se convierte en crítico para el inicio de las aplicaciones es recomendable tener varias instancias del mismo para proporcionar tolerancia a fallos. Y en una arquitectura de microservicios quizá utilizando registro y descubrimiento de servicios.

* [Registro y descubrimiento de servicios con Spring Cloud y Consul][blogbitix-206]

Otra necesidad es cifrar algunas propiedades, para ello el servidor de configuración también proporciona dos _endpoints_ uno para hacer el cifrado y otro para hacer el descifrado.

* [Almacenar cifrados los valores de configuración sensibles en Spring Cloud Config][blogbitix-351]

Incluso es posible recargar la configuración de una aplicación de Spring Boot sin reiniciarla.

* [Recargar sin reiniciar la configuración de una aplicación Spring Boot con Spring Cloud Config][blogbitix-349]

{{% sourcecode git="blog-ejemplos/tree/master/SpringCloudConfig" command="./gradlew service:run" %}}

{{% /post %}}
