---
pid: 734
type: "post"
title: "Cómo desacoplar despliegue y release con feature flags y tests A/B usando Flagsmith"
url: "/2026/06/como-desacoplar-despliegue-y-release-con-feature-flags-y-tests-a-b-usando-flagsmith/"
date: 2026-06-25T23:30:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:header.webp"
imagePost: "image:header.webp"
tags: ["programacion"]
summary: "Desplegar sin liberar y experimentar sin intuición. En sistemas de software complejos, las feature flags y los tests A/B son herramientas clave para separar el despliegue del release y tomar decisiones basadas en métricas reales. En este artículo exploramos ambos conceptos y cómo implementarlos con Flagsmith en una aplicación Java."
---

{{% post %}}

{{< logotype image1="flagsmith.svg" >}}

Un sistema de software complejo tiene muchas funcionalidades, para algunas de las cuales es casi seguro que surge la necesidad de activarla, desactivarla o cambiarla a demanda sin necesidad de hacer cambios de código simplemente por configuración o mediante un sistema externo que proporcione la configuración de las _features_.

Por otro lado, al liberar un cambio que pueda tener un impacto en el comportamiento de los usuarios es habitual realizar teses A/B para medir ese impacto y conocer si es positivo o negativo. En vez de guiarse por la intuición y sesgos es más objetivo guiarse por métricas.

{{< tableofcontents >}}

## Feature flags

Algunas de las _features_ de un producto de software no se desarrollan por completo y despliegan de forma atómica. Siguiendo los principios de _continuous delivery_ el despliegue o _deploy_ se separa de la liberación o _release_. Con este principio hay algunos cambios de código que están desplegados pero aún desactivados y no visibles funcionalmente. Para poder implementar el _continuous delivery_ una de las técnicas que se utilizan son las _feature flags_. Las _feature flags_ no son más que un booleano que indica si una feature está habilitada o no.

Los _feature flags_ son útiles también para cambiar el comportamiento del software sin necesidad de cambiar el código. Este cambio se hace a demanda por ejemplo a necesidades técnicas pero también por necesidades de negocio o legales.

Son una herramienta necesaria para servicios de backend, componentes de frontend y en las aplicaciones nativas donde liberar una nueva versión tarda varios días o hay usuarios que no las actualizan.

Un ejemplo de _feature flags_, podría ser si un determinado producto está disponible para la venta, una nueva forma de calcular los precios está activada o más sencillas como el clor de un botón.

## Teses A/B

Por otro lado, al liberar un cambio que pueda tener un impacto en el comportamiento de los usuarios es habitual realizar teses A/B para medir ese impacto y conocer si es positivo o negativo. Un experimento de un test A/B puede tener varias variantes el resultado se mide con referencia al comportamiento existente en una variante y las otras variantes introducen cambios de las que se quiere medir el impacto.

Los teses A/B requieren de hacer uso de cierto estado para devolver a cada usuario siempre la misma variante y durante toda su interacción con el producto de software. Para ello es necesario un identificativo del usuario o segmentar a los usuarios por algunos criterios que se proporcionan en cada llamada al servicio que devuelve la variante del test A/B.

Las variantes se asignan a los usuarios en el mismo espacio de tiemmpo para comprobar cual proporciona mejor rendimiento. Una prueba sencilla de test A/B puede tener diferentes variantes para el color de los botones, tipografía del texto o tamaño.

## Herramientas

No es necesario implementar un sistema personalizado y propio que permita hacer uso de _feature flags_ y teses A/B, hay herramientas específicas con estos propósitos. En el [landscape de CNCF](https://landscape.cncf.io/) hay varias opciones, el estándar de [open feature][openfeature] y entre otras dos soluciones específicas [Flagsmith][flagsmith] y [Flipt][flipt].

### Flagsmith

Flagsmith es una herramienta que proporciona ambas, _feature flags_ y teses A/B además de soportar el estándar open feature. Aunque ofrece un modelo SasS también puede ser hospedado en infraestructura propia, una versión _open source_ y una versión _enterprise_ con _features_ adicionales, un modelo de negocio similar a otros proyectos de software. Dispone de SDK para los lenguajes de programación más populares tanto de lado del cliente como del lado del servidor.

En la documentación de Flagsmith proporcionan una descripción de los _feature flags_, detallan que permiten, cuales son sus ventajas y cómo es un _workflow_ de desarrollo usándolos.

* [Feature Flags](https://docs.flagsmith.com/getting-started/feature-flags)
* [Flipping Out](https://code.flickr.net/2009/12/02/flipping-out/)

El comando de [Docker][docker] para iniciar una instancia de Flagsmith y su archivo de Docker Compose. Flagsmith usa una base de datos relacional en este caso [PostgreSQL][postgresql].

{{< code file="docker-compose-up.sh" language="bash" options="" >}}
{{< code file="docker-compose.yml" language="bash" options="" >}}

En su panel de administración accesible mediante un navegador en la dirección _http://localhost:8000_ es posible configurar los _feature flags_ y teses A/B de los que queremos hacer uso. Los teses A/B tienen la particularidad que requeiren segmetar a los usuarios en una de las variantes, para ello es necesario utilizar cierta información del ususario, esta información en Flagsmith se conocen como _traits_ como pudiera ser el navegador del usuario o país.

{{< image
    gallery="true"
    image1="image:flagsmith-1.webp" optionsthumb1="200x150" title1="Consola web de Flagsmith"
    image2="image:flagsmith-2.webp" optionsthumb2="200x150" title2="Consola web de Flagsmith"
    image3="image:flagsmith-3.webp" optionsthumb3="200x150" title3="Consola web de Flagsmith"
    caption="Consola web de Flagsmith" >}}

Para asignar los _feature flags_ y las variantes de los teses A/B a un usuario se utiliza la siguiente petición con un comando curl.

{{< code file="curl-feature-flags.sh" language="bash" options="" >}}
{{< code file="curl-test-ab.sh" language="bash" options="" >}}

La respuesta devuelta contiene los valores de todas las _feature flags_ y de los teses A/B con la variante seleccionada.

{{< code file="curl-test-ab-response.json" language="json" options="" >}}

En una aplicación Java y utilizando el SDK proporcionado por Flagsmith este es el código para obtener el valor de un _feature flag_.

{{< code file="Main-feature-flag.java" language="java" options="" >}}

Y de un test A/B.

{{< code file="Main-test-ab.java" language="java" options="" >}}

Finalmente, el código para crear la conexión entre el servicio como cliente y Flagsmith.

{{< code file="Main-init.java" language="java" options="" >}}

{{< youtube
    video="6D4gtW7XrTI" >}}

{{< youtube
    video="Ua1gp6N7qLs" >}}

{{% /post %}}

