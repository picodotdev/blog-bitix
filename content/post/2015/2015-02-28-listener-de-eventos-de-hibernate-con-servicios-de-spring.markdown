---
pid: 69
title: "Listener de eventos de Hibernate con servicios de Spring"
url: "/2015/02/listener-de-eventos-de-hibernate-con-servicios-de-spring/"
date: 2015-02-28T10:59:08+01:00
sharing: true
comments: true
tags: ["blog-stack", "java", "planeta-codigo", "programacion"]
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image="java.png" title="Java" >}}

En el anterior artículo explicaba como [crear un _listener_ que reciba eventos de Hibernate][blogbitix-68] pero quizá necesitemos en el _listener_ hacer uso de un servicio de [Spring][spring] si el proceso de la acción necesita aprovecharse de la funcionalidad proporcionada en los servicios. En este artículo mostraré como crear un _listener_ de [Hibernate][hibernate] que use un servicio de Spring, es decir, un _listener_ de Hibernate con la posibilidad de inyectar servicios de Spring.

Para hacer la integración de los _listeners_ con Spring debemos sustituir el _Interceptor_ por un servicio que haga lo mismo pero al inicio del contenedor de Spring con la anotación _@PostConstruct_. Para ello creamos una clase con el siguiente contenido:

{{% gist id="a1e629011771cb34ea05" file="HibernateConfigurer.java" %}}

Configurando Spring con anotaciones y código Java, como es recomendable en vez de xml, la configuración del ejemplo es la siguiente y un archivo xml casi testimonial de Spring. En esta configuraicón vemos el servicio _ProductoEventAdapter_ que usaremos para recibir los eventos y el servicio _DummyService_ que se inyectará en el anterior:

{{% gist id="a1e629011771cb34ea05" file="AppConfiguration.java" %}}
{{% gist id="a1e629011771cb34ea05" file="applicationContext.xml" %}}

En el _listener_ haremos uso de un servicio de Spring que podemos inyectar usando la anotación _@Autorwire_ tal y como hacemos normalmente usando el contenedor de depednecias de Spring. La implementación con respecto a usar un _listener_ con solo Hibernate varía ligeramente para adaptarse a los cambios de usar un servicio.

{{% gist id="a1e629011771cb34ea05" file="HibernateEventAdapter.java" %}}
{{% gist id="a1e629011771cb34ea05" file="ProductoEventAdapter.java" %}}
{{% gist id="a1e629011771cb34ea05" file="DummyService.java" %}}

En este ejemplo el resultado que veríamos en la consola sería el siguiente con las trazas _Action: preInsert, Id: null_ y _Action: postInsert, Id: 1_ antes y después de ejecutarse la sentencia SQL:

<div class="media" style="text-align: center;">
	<a href="assets/images/custom/posts/69/consola.png" title="Trazas de ejecución del listener con servicios de Spring" data-gallery><img src="assets/images/custom/posts/69/consola-thumb.png"></a>
</div>

Usando los _listeners_ de Hibernate con Spring no necesitamos el archivo que creábamos antes en _/META-INF/services/org.hibernate.integrator.spi.Integrator_. Esto es un ejemplo de prueba de concepto pero perfectamente podría ser aplicado a una necesidad real. En el ejemplo [PlugIn Tapestry][ejemplo-plugin-tapestry] que hice para un [libro sobre el framework de desarrollo web Apache Tapestry][blogbitix-12] puede verse el código completo y funcional de esta implementación.

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Autowiring Spring beans in Hibernate/JPA entity listeners](http://guylabs.ch/2014/02/22/autowiring-pring-beans-in-hibernate-jpa-entity-listeners/)
* [Spring-injected Beans in JPA EntityListeners](http://invariantproperties.com/2013/09/29/spring-injected-beans-in-jpa-entitylisteners/)
{{% /reference %}}

{{% /post %}}
