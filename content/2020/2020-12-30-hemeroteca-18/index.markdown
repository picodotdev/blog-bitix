---
pid: 542
type: "post"
title: "Hemeroteca #18"
url: "/2020/12/hemeroteca-18/"
date: 2020-12-30T12:00:00+01:00
language: "es"
rss: true
sharing: true
comments: true
promoted: false
imagePost: "logotype:blogbitix.svg"
tags: ["blog", "planeta-codigo"]
series: ["hemeroteca"]
library: "true"
---

{{% post %}}

{{< logotype image1="blogbitix.svg" >}}

Una cosa es escribir artículos de ejemplo y otra usarlos en una empresa que factura unos cuantos cientos de millones de euros cuyo servicio es considerado crítico en producción. Por ejemplo por citar algunos artículos de este año, algo como la autenticación de los usuarios con [Keycloak][keycloak] no es sencillo implementarlo e integrarlo en una organización con numerosos sistemas ya funcionando, o implementar un segundo factor de autenticación, o un sistema para realizar las traducciones, o trazabilidad con [Sleuth][spring-cloud-sleuth] y [Zipkin][zipkin], o tener una herramienta para consultas SQL en producción con [SQLPad][sqlpad], o definir una autoridad de certificación, o disponer de una arquitectura de referencia en grado de producción de las aplicaciones [Consul][consul], [Vault][vault] y [Nomad][nomad] de [HashiCorp][hashicorp]. No solo es usarlo hay que mantenerlo, definir un plan para intervenciones comunes como realizar copias de seguridad de las bases de datos o actualizaciones a nuevas versiones sin que afecte al servicio. Así que los artículos quizá estén muy bien pero hay algunas cosas más a tener en cuenta para aplicarlos en un caso de producción en un organización.

Quizá alguien piense que escribo únicamente sobre herramientas y las herramientas no son de interés. Pero realmente más que escribir sobre herramientas escribo sobre problemas, una herramienta concreta la veo como el medio para resolver un problema técnico o de negocio, el medio no es el fin pero no es irrelevante para conseguir el fin, tener y usar el medio adecuado influye en el éxito, fracaso u ocasionar otros problemas. Con lo que en algunos artículos escribo sobre una herramienta pero con la intención de dar solución a un problema.

Aunque se comenta en la frase corta lapidaria «con esfuerzo todo se consigue», el libro completo es que el esfuerzo y trabajo no siempre te permite conseguir lo que propongas, el fracaso o un éxito pobre es lo normal. El esfuerzo, trabajo, dedicación, constancia, perseverancia y cosas parecidas ayudan pero también está el factor suerte o simplemente tener el amigo, conocido, contacto, enchufe adecuado o simplemente caerle bien a la persona correcta. Algunas oportunidades no dependen de uno mismo ni del esfuerzo sino también de las oportunidades en una organización en la que esos artículos cobren vida.

Tendré varios defectos, pero también otras virtudes y tengo que valorar más de lo que soy capaz si tengo oportunidad. En lo que depende de mí dos ejemplos de eso que soy capaz es el propio blog que al menos lo considero cuanto menos uno bueno en habla hispana sobre Java, software libre y programación de lo que recibido algún comentario, otro ejemplo es el [_script_ alis][alis] para instalar [Arch Linux][archlinux] que es posiblemente (sino convenceme de lo contrario) el mejor _script_ en el mundo para instalar Arch Linux de forma sencilla y rápida.

En vez de compartir conocimiento podría estar leyendo libros y formándome más, cada artículo supone gran parte de mi tiempo libre fuera del horario de trabajo. En algún momento estar de vacaciones tener la [PlayStation][playstation] con algunos juegazos comprados y por jugar como _The Last of Us_, _God of War_, la serie _Uncharted_, los varios _Trine_, _The Witcher 3_, un gran libro por leer como _The Unicorn Project_, alguna película por ver del estudio Ghibli o dar un paseo durante algunas horas pero en algunos momentos casi me apetece más escribir algún artículo para el blog.

No recibo muchos, aunque algunos que recibo son [como en este artículo con un comentario de agradecimiento][blogbitix-113] que me alegra y me anima a seguir escribiendo. Algunos blogueros y más _youtubers_ comentan que en sus comentarios tiene algún _troll_ que comenta para criticar, que recuerde solo una persona me criticó algo por twitter con cierta parte de razón pero por otra no sobre algo del pirateo de Windows que comenté.

Todavía no tengo un _troll_ ni lo deseo, será que mi blog no es de política ni hablo de los fascistas dirigentes de V🤮X o los corruptos dirigentes del PP los de ambos miserables, hipócritas y mentirosos igual que sus lacayos mercenarios en las redes sociales u otros que se hacen llamar periodistas.

Como cada año hago un repaso del _blog_ con los artículos que he publicado, cifras de visitas, ingresos de AdSense y algunos otros asuntos.

{{< tableofcontents >}}

### Retrospectiva 2020

#### Evolución visitas

Durante este año atípico de confinamiento y en muchos casos trabajo desde casa las visitas se han mantenido constantes, alrededor de unas 50K visitas mensuales. A pesar de escribir nuevo contenido que debería añadir aún más tráfico el caso es que algunos artículos antiguos también lo pierden con lo que el tráfico de los nuevos artículos solo compensan la pérdida del tráfico de los antiguos. Algunos artículos van perdiendo visitas, porque hay otros _blogs_ que también crean contenido y los artículos van perdiendo posiciones en la clasificación de los buscadores.

Para este 2020 uno de mis objetivos para el blog era seguir aumentando el número de visitas, no se si lo que conseguido porque el tráfico de los artículos nuevos solo se empieza apreciar pasados unos meses e incluso el primer año, este año 2020 he intentando planificar y seleccionar mejor sobre lo que he ido escribiendo, intentando que fuesen artículos interesantes pero que pudieran atraer más visitas. El artículo arquitectura de referencia en grado de producción de las aplicaciones Consul, Vault y Nomad de HashiCorp puede estar muy bien y ser muy avanzado pero su cuota de búsqueda es muy pequeña, por lo que he escrito algunos artículos básicos que la gente busca más.

Tengo muy poco tráfico de referencia, la mayor parte viene del buscador Google con lo que también he seguido intentando mejorar el SEO, analizar mejor el contenido, incluir palabras clave, poner mejores títulos a los artículos y añadir títulos de sección a los artículos.

El tráfico de visitas durante este 2020 ha sido el siguiente.

{{< image
    gallery="true"
    image1="image:google-analytics.png" optionsthumb1="650x450" title1="Evolución visitas 2020"
    caption="Evolución visitas 2020" >}}

#### Evolución ingresos

Aunque los ingresos por publicidad AdSense, afiliados de Amazon y algunos enlaces patrocinados no compensa el tiempo que hay que invertir en el blog por cada artículo eso no quiere decir que sean despreciables. Mi blog no creo que sea muy grande, algunas páginas especializadas en noticias y tecnología con algún millón de visitas semanales o mensuales si creo que pueden generar unos ingresos respetables de las diferentes fuentes de ingresos que utilizan, como de esos artículos que dedican en exclusiva una selección de productos de Amazon y sus respectivos enlaces de afiliación. Noto en mi pequeño blog el _Black Friday_ y la época navideña esos medios lo deben notar aún más.

Por insertar un enlace contextual relevante para el contenido he recibido el pago de 20 €, de otros dos enlaces en otros dos artículos a razón de otros 20 € por enlace con un total de 40 € que inserté hace un par de años y que con cierto esfuerzo e insistencia por mi parte he conseguido finalmente cobrar con satisfacción. Por AdSense este 2020 he recibido 250 € y por Amazon con una facturación de 3160 € que les he generado a través de los enlaces que incluyo he recibido 110 €. En total unos 410 € de este 2020, cuando en el 2019 solo por AdSense fueron de 415 €, que me dará para comprar algún juego de la PlayStation y para hacer una donación a varios proyectos de software libre que uso o me parecen importantes. Me gustaría saber para comparar cuántos ingresos generan por publicidad algunos _youtubers_ con un canal medio o pequeño con unos 10K, 5K y 1K suscriptores.

Hay quien cree que añadir un enlace a un artículo me lleva 20 minutos y que 20 € por ese tiempo equivalente a 60 € la hora es un buen precio. Pues no, no es un buen precio, cada artículo son entre 4 y 8+ horas de tiempo, multiplica esto por 550 artículos y 7 años de constancia escribiendo todas las semanas un artículo y muchas dos. Realmente el precio no es por el tiempo de insertar el enlace sino por conseguir en base a esfuerzo y trabajo que sea interesante insertarlo.

Recibir esos ingresos durante el 2020 es la parte buena, en este año especial han sido significativamente menores a los de años anteriores al menos por AdSense, la otra parte como digo es que para eso ha hecho falta una constancia de 7 años en los que he escrito casi 550 artículos a razón de entre 4 y 8+ horas por artículo, algunas semanas escribiendo dos artículos y otras cuantas horas más para pensar y organizar ideas de nuevos artículos.

A esa cantidad hay que sumarle la donación de una persona de 100 € 😱😱😱😱😱 por lo útil le ha resultado el mencionado _script_ alis que hice para instalar Arch Linux. No creo que las donaciones por este _script_ sea habitual, ni mucho menos en esas cifras pero muy esporádicamente también puedo recibir algún ingreso con el enlace de donación que añadí a través de [PayPal][paypal].

{{< image
    gallery="true"
    image1="image:google-adsense.png" optionsthumb1="650x450" title1="Evolución ingresos AdSense 2020" >}}
{{< image
    gallery="true"
    image1="image:amazon-afiliados.png" optionsthumb1="650x450" title1="Evolución ingresos Amazon Afiliados 2020"
    caption="Evolución ingresos AdSense y Amazon Afiliados 2020" >}}

Entre una cosa y otra el saldo de mi cuenta de PayPal ha llegado a 164 €.

{{< image
    gallery="true"
    image1="image:paypal-saldo.png" optionsthumb1="650x450" title1="Saldo cuenta PayPal"
    caption="Saldo cuenta PayPal" >}}

#### Mejoras realizadas

He seguido aplicando algunas mejoras al _blog_ como dividir y poner títulos a las secciones del contenido en el artículo. A mi me ayuda para estructurar el contenido del que voy a escribir, es mejor para el lector que puede ir directamente a la sección que más le interese, e incluso quizá también mejor en cuanto a SEO para los buscadores.

En muchos artículos he añadido un índice del contenido que quizá a algunos usuarios les sea de utilidad, el índice del artículo está basado en los títulos de las secciones. Los títulos de las secciones e índice del artículo creo que mejora los artículos que en vez de ser un bloque grande de texto sin forma está dividido en varias secciones con títulos destacados que resumen sus pocos párrafos.

Un objetivo que me he planteado para este año es hacer tareas de SEO y escribir algunos artículos con los que atraer más visitas, esto no quiere decir que no sean artículos interesantes pero sí algunos artículos básicos sobre programación o muy buscados. Principalmente programación sobre Java, un poco de [GNU][gnu]/[Linux][linux] y algunos relacionados con [Windows][windows]. El 2021 es mi intención escribir artículos que aumenten las visitas y si es posible también los ingresos por publicidad y afiliación, ver que estas métricas aumentan me anima y motiva para seguir escribiendo.

Otro objetivo ha sido reescribir y mejorar algunos artículos antiguos ya publicados empezando por los que reciben la mayor parte de visitas aunque también cualquier otro al que añadirle alguna información adicional que lo complete. Esto también en línea con tareas de hacer SEO, no solo en el contenido sino en el título de los artículos. Este es un objetivo para el 2021 ya que algunos se van quedando antiguos o simplemente cuando los escribí me faltaba algo de conocimiento.

Los artículos los escribo en primera instancia como un borrador que guardo en [Google Docs][google-docs] dónde esté ya marca varias correcciones en palabras mal escritas y con errores, al editar los artículos que hago en formato _markdown_ con el editor [Code][microsoft-visual-studio-code] reviso algunas partes para mejorar la calidad del artículo quitando estos errores. Un paso que realizo siempre antes de publicarlos es una corrección gramatical, la hacía con el editor de texto [Vim][vim] basado en consola porque no conocía otra forma mejor. Utilizar Vim para la corrección gramatical me suponía salir de Code entrar en Vim realizar la edición cuya navegación por el documento no es lo más rápido y volver al editor Code, esto en ocasiones lo tenía que hacer un par de veces.

Para la funcionalidad de corrección gramatical he instalado en Code una extensión con el diccionario de palabras en español que me permite corregir a medida que escribo, esto es más sencillo, me ahorra algo de tiempo y encuentro más errores que con Vim alguno se me escapaba. He revisado todos los artículos que ya tenía publicados, los 500, con esta extensión y la verdad es que en la mayoría tenía mínimo dos o tres gazapos que he corregido.

#### Otras contribuciones

También he seguido mejorando el _script_ alis de instalación automatizado, desatendido, y personalizable de Arch Linux. He añadido la posibilidad de instalar paquetes de [Flatpak][flatpak] y de [SDKMAN][sdkman], he añadido soporte para los sistemas de archivos F2FS, ReiserFS y btrfs con _subvols_ la posibilidad de configurar un intérprete de comandos alternativo a [bash][bash] como [zsh][zsh], [fish][fishshell] o [dash][dash], poder hacer que alis solicite las contraseñas durante la instalación en vez de _hardcodearlas_ en el archivo de configuración para evitar que alguien las ponga y suba a GitHub si hace un _fork_, añadido el soporte para los entornos de escritorios alternativos [i3-wm][i3wm] e i3m-gaps, soporte para _reflector_, posibilidad de habilitar o deshabilitar _units_ de [systemd][systemd], soporte para probar con [Packer][packer] que me permite automatizar las pruebas en [VirtualBox][virtualbox], además he añadido la posibilidad de hacer una pequeña donación a través de PayPal si alguien considera que el _script_ le es de utilidad.

También he seguido mejorando las traducciones al español de la aplicación gestor de contraseñas [KeePassXC][keepassxc], del cliente _torrent_ [Transmission][transmissionbt] y el reproductor multimedia [VLC][vlc].

### Propósitos para el 2021

Los últimos meses he estado publicando los artículos que he ido escribiendo esa misma semana casi con ideas sobre la marcha. En otros momentos entre que apuntaba la idea, escribía el borrador y lo publicaba pasaba incluso varios meses o algún año entre cada uno de esos pasos. Y aún tengo muchos artículos escritos como borrador hace algunos años pendientes de publicar.

En el 2021 no creo que cambie mucho, espero seguir publicando al menos un artículo por semana, continuar con la intención de este 2020 de publicar artículos que aumenten las visitas al mismo tiempo de que sean interesantes y cambiar, reescribir o actualizar algunos artículos antiguos. Aumentar las visitas es algo que no he conseguido durante este atípico 2020.

Alguna vez me he planteado migrar el blog a [AWS][amazon-aws], [Digital Ocean][digital-ocean] o [Linode][linode]. El blog me genera ingresos de sobra para cubrir el coste con cualquiera de esa infraestructura. Si lo hago será más que por necesidad por aprender y tener un dominio propio. No lo he hecho hasta el momento por la comodidad de [GitHub Pages][github-pages] ni tener que dedicar tiempo al mantenimiento de esa infraestructura. También requiere un plan de migración para que tenga el menor impacto posible en cuanto a tráfico o SEO, ya buscaría la mejor solución.

Es posible que este 2021 me lo plantee en serio, administrar mi propio servidor en la nube con [Ubuntu][ubuntu], usar [Nginx][nginx], [CloudFront][amazon-cloudfront] o un CDN, [Let's Encrypt][letsencrypt], [Terraform][terraform], copias de seguridad, _deployments_ y actualizaciones de seguridad todos esos temas interesantes de DevOps en los que no tengo tanto conocimiento al menos en la práctica. ¿Un blog estático necesita todo esto? La respuesta es no, pero como digo el objetivo no es la solución mínima más sencilla para el problema que ya tengo resuelto con GitHub Pages sino aprender en la práctica de lo que me interesa de DevOps con este problema.

### Artículos publicados

Estos son los 45 artículos que he escrito durante el segundo semestre con un total de 90 artículos publicados durante el 2020, de las temáticas habituales Java, GNU/Linux o software libre.

¡Qué el 2021 sea mejor!

#### Artículos sobre Java

* [Tareas programadas de forma periódica con Quartz y Spring en Java][blogbitix-497]
* [Renombrar campos del esquema en las consultas GraphQL][blogbitix-510]
* [Añadir descripciones y documentar los campos de GraphQL][blogbitix-511]
* [Introducción a gRPC y ejemplo con Java][blogbitix-512]
* [Novedades de Java 15][blogbitix-515]
* [3 formas de gestionar errores en los lenguajes de programación][blogbitix-519]
* [Implementar un bus de eventos de dominio en Java][blogbitix-520]
* [Implementar un bus de comandos y consultas en Java][blogbitix-523]
* [Cómo deduplicar eventos de dominio][blogbitix-524]
* [Entorno de desarrollo Java para editar, compilar y ejecutar programas][blogbitix-525]
* [Cómo depurar una aplicación Java con un IDE][blogbitix-526]
* [Emitir trazas de las peticiones y respuestas HTTP con clientes Java][blogbitix-529]
* [Crear un archivo Zip con Java, comprimir y descomprimir datos][blogbitix-532]
* [Autenticación con OpenID/OAuth en cualquier web con Nginx y de forma nativa con Spring Boot][blogbitix-533]
* [5 formas de implementar el patrón Singleton en Java][blogbitix-534]
* [Las formas de guardar relaciones jerárquicas en bases de datos relacionales][blogbitix-535]
* [Tipos de arquitecturas de aplicaciones de software][blogbitix-537]
* [Internacionalizar, localizar y dar formato a cadenas, números, importes y fechas en Java][blogbitix-539]
* [Para qué sirve la palabra clave final en el código fuente Java][blogbitix-540]

#### Artículos sobre GNU/Linux o software libre

* [Las diferencias entre GNU/Linux, BSD y sus distribuciones][blogbitix-498]
* [Qué son, para qué sirven, formato y ejemplos de las expresiones cron][blogbitix-499]
* [Comunicaciones seguras, autenticación mutua y autorizaciones con intenciones entre servicios usando Consul Connect y Nomad][blogbitix-502]
* [Revertir un servicio a una versión anterior con Nomad][blogbitix-503]
* [Obtener un nombre de dominio para una dirección IP privada][blogbitix-504]
* [Configurar autenticación básica en los servidores web Nginx y Apache][blogbitix-505]
* [Comandos para crear una autoridad de certificación (CA) con OpenSSL][blogbitix-506]
* [Configurar un servidor web virtual en Nginx y Apache][blogbitix-507]
* [Arquitectura de referencia de Consul, Vault y Nomad para un centro de datos][blogbitix-508]
* [Centralizar y consultar las trazas de las aplicaciones con Elasticsearch, Logstash y Kibana][blogbitix-517]
* [Funcionalidades que necesitan las aplicaciones basadas en microservicios y herramientas que las proporcionan][blogbitix-516]
* [Trazabilidad en servicios distribuidos con Sleuth y Zipkin][blogbitix-518]
* [Herramienta para ejecutar consultas SQL en la base de datos de producción][blogbitix-531]
* [La herramienta Weblate para traducir una aplicación a múltiples idiomas][blogbitix-538]

#### Artículos sobre JavaScript y web

* [Las 3 formas de guardar datos en el navegador con JavaScript][blogbitix-500]
* [Un comando y aplicaciones gráficas para descargar todo el contenido de un sitio web][blogbitix-501]
* [Qué hace y ventajas de un preprocesador de estilos CSS][blogbitix-509]
* [Qué es, por qué y cómo activar un segundo factor de autenticación en Google, Amazon, PayPal y otros servicios][blogbitix-530]

#### Otros temas

* [1 década en la misma empresa, 20 años trabajando][blogbitix-513]
* [Cómo comprar una licencia y activar Windows 10 y Office 2019][blogbitix-514]
* [Sobre la generación de consolas PlayStation 5 y Xbox Series X/S antes de que se comercialice y la renovación del hardware de PC][blogbitix-521]
* [Análisis y guía completa del juego Horizon Zero Dawn][blogbitix-522]
* [Cómo encontrar las mejores ofertas de juegos para consola y PC][blogbitix-527]
* [Guía básica sobre teclados mecánicos para ordenador][blogbitix-536]
* [Artículo #8 de Yo apoyo al software libre][blogbitix-541]

{{% /post %}}
