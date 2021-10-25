---
pid: 546
type: "post"
title: "Analítica web con Matomo como alternativa a Google Analytics"
url: "/2021/01/analitica-web-con-matomo-como-alternativa-a-google-analytics/"
date: 2021-01-15T17:00:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:matomo.svg"
tags: ["planeta-codigo", "web"]
summary: "Matomo es una alternativa a Google Analytics con funciones similares que cubren las necesidades de la mayoría de sitios web. Es software libre con algunas funciones _premium_ que requieren comprar una licencia anual de uso. Matomo se puede hospedar _on-premise_ que requiere administrar esa infraestructura o en la nube ofrecida por la propia Matomo. En el artículo muestro en un ejemplo como empezar a usar Matomo en un sitio web con Docker."
---

{{% post %}}

{{< logotype image1="matomo.svg" >}}

La analítica web permite obtener valiosa información y datos de un sitio web, de los usuarios y de su comportamiento. Estos datos e información sirven para tomar decisiones basadas en parte datos en vez de únicamente en opiniones e intuiciones. Cuantas son las visitas de un sitio web, qué páginas son las más visitadas, cuánto tiempo permanecen los usuarios en una página, que días de la semana y a que horas hay más visitas, que navegador, sistema operativo y dispositivo usan los usuarios, un ordenador o dispositivo móvil, cuáles son los sitios de referencia y mucha otra información.

La herramienta de analítica web más utilizada es [Google Analytics][google-analytics]. En el artículo [Analítica web en el servidor sin JavaScript en el cliente con GoAccess][blogbitix-544] comentaba algunos motivos para usar o no usar [Google Analytics][google-analytics].

Los motivos para usar Google Analytics están en que es gratuita, fácil de instalar en un sitio web, tiene gran cantidad de funcionalidades básicas y avanzadas que cubren las necesidades de cualesquiera usuarios y no requiere mantener infraestructura propia. Los motivos en contra de usar Google Analytics están en que los datos de un sitio web y de los usuarios es recogido por una tercera parte que establece su propia licencia al servicio y los datos, lo que afecta a la privacidad de los usuarios.

### Matomo, una alternativa a Google Analytics

[Matomo][matomo] es una de las herramientas como alternativa a Google Analytics. Tiene funciones equivalentes a las de Google Analytics, tiene una licencia software libre y es posible instalarlo en infraestructura propia para controlar los datos de los sitios web sin depender de una tercera parte. Matomo también ofrece la posibilidad de usarlo hospedado y administrado en su modelo _cloud_ ofrecido por Matomo.

Algunas [funciones de Matomo](https://matomo.org/features/) son métricas en tiempo real, seguimiento con eventos y de contenido, informes personalizados, dimensiones y variables personalizadas, geolocalización, segmentación de usuarios, transiciones de páginas y estadísticas sobre la página, velocidad el sitio web y páginas, seguimiento de campañas, _tracking_ de eventos y _tag manager_ entre otras

Aunque Matomo es software libre con las funciones anteriores incorporadas para tener acceso a otras funciones requieren una suscripción para usu uso. Algunas de las funciones _premium_ son mapas de calor o _heatmaps_ en la interacción en una página, pruebas A/B, informes personalizados, analítica de formularios y medios, _funnels_, flujo de usuarios o autenticación con SAML. Al igual que Google Analytics para realizar la analítica web Matomo requiere insertar un pequeño _script_ de JavaScript en el HTML de todas las páginas de los sitios sitio web. El _script_ es proporcionado por Matomo al crear el sitio web.

Las funciones _premium_ se pueden adquirir de forma individual las que se usen o todas en forma de paquete en la [tienda de Matomo](https://plugins.matomo.org/?matomoversion=4). El [paquete de funciones _premium_](https://plugins.matomo.org/PremiumBundle?matomoversion=4) tienen un coste de algo más de 1K €/año para 4 usuarios. El coste de la versión _cloud_ varía en función del número de visitas partiendo de los 29 €/mes hasta las 50K páginas vistas a los 239 €/mes para 1M de páginas vistas. Hay precios para webs con más tráfico y se ofrece casi un 20% de descuento al pagar de forma anual.

* [A Tour of Matomo](https://matomo.org/docs/matomo-tour/)
* [Precios de Matomo](https://matomo.org/pricing/)

### Ejemplo de sitio web con Matomo

Matomo requiere una base de datos [MySQL][mysql] o [MariaDB][mariadb] para persistir los datos de analítica. En este ejemplo uso [Docker][docker] con un archivo de [Docker Compose][docker-compose] para iniciar los contenedores de una base de datos MySQL, el servidor de Matomo y un servidor _proxy_ con [Nginx][nginx]. La versión de MySQL con la que es compatible Matomo es la 5.7 o superior.

En el archivo de Docker Compose se define los tres servicios, se configuran unos volúmenes de datos compartidos entre el servidor web y el servidor de Matomo y dos archivos donde se definen algunas variables de entorno tanto para la base de datos MySQL como para la configuración de Matomo.

{{< code file="docker-compose.yml" language="yaml" options="" >}}
{{< code file="database-mysql.env" language="bash" options="" >}}
{{< code file="database-matomo.env" language="bash" options="" >}}

En Nginx se configuran dos sitios web virtuales, un sitio web en el que realizar la analítica web y otro para el sitio web para la herramienta Matomo.

{{< code file="www.127.0.0.1.xip.io.conf" language="nginx" options="" >}}
{{< code file="matomo.127.0.0.1.xip.io.conf" language="nginx" options="" >}}

Matomo requiere una configuración inicial, en el ejemplo se accede a Matomo en la dirección _http:\/\/matomo.127.0.0.1.xip.io_. En la configuración se solicita configurar un sitio web y se proporciona el _script_ a insertar en las páginas del sitio web.

{{< image
    gallery="true"
    image1="image:matomo-configuracion.png" optionsthumb1="300x200" title1="Configuración inicial de Matomo"
    caption="Configuración inicial de Matomo" >}}

Para que tome los datos de analítica del sitio web hay que insertar el _script_ proporcionado en todas la páginas devueltas por el sitio web. Este es la página HTML de ejemplo mínima con el script insertado y el envío de un evento cuando se pulsa un botón.

{{< code file="index.xhtml" language="html" options="" >}}
{{< image
    gallery="true"
    image1="image:web.png" optionsthumb1="300x200" title1="Web con analítica de Matomo"
    caption="Web con analítica de Matomo" >}}

Matomo recibe los datos de analítica de las páginas desde el navegador de los usuarios cuando las páginas web son accedidas. Entre las funciones incluidas en Matomo están los paneles de Visitantes, Adquisición, Comportamiento, Objetivos y Mercado.

{{< image
    gallery="true"
    image1="image:matomo-tablero.png" optionsthumb1="300x200" title1="Analítica web con Matomo"
    image2="image:matomo-visitantes.png" optionsthumb2="300x200" title2="Analítica web con Matomo" >}}
{{< image
    gallery="true"
    image1="image:matomo-eventos.png" optionsthumb1="300x200" title1="Analítica web con Matomo"
    image2="image:matomo-adquisicion.png" optionsthumb2="300x200" title2="Analítica web con Matomo"
    caption="Analítica web con Matomo" >}}

{{% sourcecode git="blog-ejemplos/tree/master/Matomo" command="docker-compose up" %}}

{{< reference >}}
* [Event Tracking](https://matomo.org/docs/event-tracking/)
* [Features](https://matomo.org/features/)
* [Product Features](https://matomo.org/product-features/)
* [Feature Overview](https://matomo.org/feature-overview/)
* [Licenses](https://matomo.org/licences/)
{{< /reference >}}

{{% /post %}}
