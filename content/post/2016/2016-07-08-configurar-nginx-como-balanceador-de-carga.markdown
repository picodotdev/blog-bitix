---
pid: 157
title: "Configurar Nginx como balanceador de carga"
url: "/2016/07/configurar-nginx-como-balanceador-de-carga/"
date: 2016-07-08T17:00:00+02:00
updated: 2016-07-09T11:00:00+02:00
sharing: true
comments: true
language: "es"
tags: ["blog-stack", "planeta-codigo", "planeta-linux", "software", "software-libre"]
series: ["web"]
summary: "Para escalar horizontalmente los servidores de aplicaciones, aumentar el rendimiento, disminuir la latencia, conseguir tolerancia a fallos y aumentar la disponibilidad podemos usar el servidor web Nginx como balanceador de carga entre varios servidores de aplicaciones. En este ejemplo muestro la configuración necesaria para añadir la funcionalidad de balanceador de carga a Nginx entre varios servidores de aplicaciones Tomcat usando además Docker."
---

{{% post %}}
{{< links >}}
{{< postslinks >}}

{{< logotype image1="nginx.png" title1="Nginx" >}}

Un balanceador de carga distribuye las peticiones que llegan al servidor entre varios servidores para que las atiendan consiguiendo optimizar el uso de los recursos, aumentar el número de peticiones atendidas por unidad de tiempo, reducir la latencia y proporcionar tolerancia a fallos. Escalar un servidor que deba procesar un gran número de peticiones llegado un límite es más barato escalar horizontalmente añadiendo más servidores que escalar verticalmente usando servidores más potentes.

Distribuir las peticiones ha de hacerse eficientemente para que un servidor no reciba todas las peticiones pesadas y se sature mientras hay servidores que tienen poca carga. Usando varios servidores para atender las peticiones evita que haya un único punto de fallo en la aplicación proporcionando tolerancia a fallos, si un servidor sufre un fallo el resto de servidores se encargarán de atender las peticiones. Una mejor tolerancia a fallos aumentará la disponibilidad del servicio sin que haya caídas de servicio.

Hay soluciones específicas para balancear la carga como [HAProxy][haproxy] pero para los casos sencillos que son los más habituales y para no añadir una pieza más a la arquitectura de la aplicación [Nginx][nginx] es capaz de hacer las funciones de balanceo de carga entre varios servidores de aplicaciones.

Hay tres estrategias para balancear o distribuir la carga:

* _round-robin_: las peticiones son distribuidas entre los servidores de forma cíclica. Cabe la posibilidad de que las peticiones más pesadas sean procesadas por el mismo servidor, distribuye las peticiones de forma ecuánime pero la carga no.
* _least-connected_: la siguiente petición es atendida por el servidor con menos conexiones activas.
* _ip-hash_: se selecciona el servidor que atenderá la petición en base a algún dato como la dirección IP, de esta forma todas las peticiones de un usuario son atendidas por el mismo servidor.

Esta es la configuración básica con la estrategia _round-robin_. Los servidores balanceados se definen con la directiva _upstream_ a los que se hace de _proxy_ inverso con la directiva _proxy\_pass_.

{{< gist picodotdev c202119fe96523b3bc6db4a742fda55b "nginx.conf" >}}

Para usar la estrategia _least-coneccted_ hay que indicar la directiva _least\_conn_ en la directiva _upstream_.

{{< gist picodotdev c202119fe96523b3bc6db4a742fda55b "nginx-least_conn.conf" >}}

Hay que tener en cuenta que en las estrategias _round-robin_ y _least-conected_ cada petición probablemente sea atendida por un servidor diferente de modo que si los servidores no comparten las sesiones se producirán comportamientos erráticos. Usando la estrategia _ip\_hash_ se usará la dirección IP para redirigir todas las peticiones al mismo servidor que se conoce como _sticky session_.

{{< gist picodotdev c202119fe96523b3bc6db4a742fda55b "nginx-ip_hash.conf" >}}

Para que los servidores compartan la sesión y evitar usar _sticky session_ podemos [usar Redis como sistema de información para guardar las sesiones de los servidores][blogbitix-70], si un servidor de aplicaciones deja de funcionar las sesiones que mantuviese no se perderán y las peticiones podrán ser atendida por cualquier servidor. Si hay un servidor que queremos procese más peticiones porque tiene más capacidad podemos dar más peso a este. En esta configuración de cada 5 peticiones 3 serán atendidas por el servidor _app1_, 1 por el _app2_ y otra por _app3_.

{{< gist picodotdev c202119fe96523b3bc6db4a742fda55b "nginx-weight.conf" >}}

Cuando un servidor falla al servir una petición Nginx lo marca como en estado erróneo y deja de enviarle peticiones, los chequeos de salud se hacen de forma pasiva según el resultado de las peticiones que se envían. Con _max\_fails_ se establece el máximo número de fallos antes de considerar un servidor con estado erróneo, tiene un valor por defecto de 1. Con _fail\_timeout_ se establece el tiempo que un servidor se considera que está en estado erróneo antes de enviar una nueva petición, si enviada una nueva petición responde correctamente se vuelve a considerar en estado correcto. Con la directiva _health\_check_ se puede configurar las comprobaciones de estado que hace Nginx para determinar si el servidor de aplicaciones está funcionando correctamente.

{{< gist picodotdev c202119fe96523b3bc6db4a742fda55b "nginx-misc.conf" >}}

Si queremos que el cliente conozca que servidor atendió la petición podemos añadir la [directiva _add\_header_](http://nginx.org/en/docs/http/ngx_http_headers_module.html#add_header) usando una de las [variables añadidas por el módulo  _ngx\_http\_upstream_](http://nginx.org/en/docs/http/ngx_http_upstream_module.html#variables), nos servirá para depurar la aplicación en tiempo de desarrollo.

<div class="media" style="text-align: center;">
    {{< figure year="2016" pid="157"
        image1="nginx-load-balancer-1.png" thumb1="nginx-load-balancer-1-thumb.png" title1="Servidor balanceado 172.17.0.2:8080"
        image2="nginx-load-balancer-2.png" thumb2="nginx-load-balancer-2-thumb.png" title2="Servidor balanceado 172.17.0.3:8080" >}}
    {{< figure year="2016" pid="157"
        image1="nginx-load-balancer-3.png" thumb1="nginx-load-balancer-3-thumb.png" title1="Servidor balanceado 172.17.0.4:8080"
        caption="Nginx balanceando la carga entre 3 servidores de aplicaciones Tomcat" >}}
</div>

En el ejemplo de configuración usaré [Docker][docker] para crear un servidor web Nginx que haga de balanceador de carga entre tres servidores de aplicaciones Tomcat. Con Docker hacer esta prueba es mucho más sencilla que instalar tres Tomcats y un servidor Nginx a travbés de los paquetes del sistema o descargando binarios, puedes leer los [artículos de la serie Docker][blogbitix-serie-docker] que he escrito para conocer como usarlo y que ofrece esta útil herramienta. El archivo de _docker-compose.yml_ completo es el siguiente:

{{< gist picodotdev c202119fe96523b3bc6db4a742fda55b "docker-compose.yml" >}}

{{% code git="blog-ejemplos/tree/master/NginxLoadBalancer" command="docker-compose up" %}}

{{% reference %}}
{{< links >}}
{{< postslinks >}}
* [Using NGINX as HTTP load balancer](http://nginx.org/en/docs/http/load_balancing.html)
* [Load Balancing with NGINX and NGINX Plus, Part 1](https://www.nginx.com/blog/load-balancing-with-nginx-plus/)
* [Load Balancing with NGINX and NGINX Plus, Part 2](https://www.nginx.com/blog/load-balancing-with-nginx-plus-part2/)
* [NGINX Load Balancing - HTTP Load Balancer](https://www.nginx.com/resources/admin-guide/load-balancer/)
{{% /reference %}}

{{% /post %}}
