---
pid: 606
type: "post"
title: "Medidas de seguridad a implementar para prevenir y minimizar daños en ataques informáticos"
url: "/2021/10/medidas-de-seguridad-a-implementar-para-prevenir-y-minimizar-danos-en-ataques-informaticos/"
date: 2021-10-28T19:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:poster.jpg"
tags: ["planeta-codigo", "programacion", "seguridad"]
summary: "Los ataques informáticos dirigidos a los usuarios tienen el objetivo con una motivación económica del dlincuente de robarles las credenciales de sus cuentas, datos de tarjetas de crédito y datos personales, otros ataques están dirigidos a los servicios que usan los usuarios. De los ataques dirigidos a los usuarios implementando las medidas de seguridad adecuadas en el servicio varios tipos de ataques son evitables como el _phishing_ que es uno de los más comunes ofreciendo inicio de sesión con segundo factor de autenticación y con otras medidas los daños y riesgos son limitables. Otras medidas de seguridad implementadas en el servicio evita riesgos como usar _salted pasword hashing_. Como programadores los servicios deben hacer uso de buenas prácticas e implementar medidas adicionales para evitar riesgos y daños."
---

{{% post %}}

Algunos ataques informáticos están dirigidos a las empresas y sus sistemas de computación explotando alguna vulnerabilidad de seguridad. Tener un fallo de seguridad ya de por sí es un grave problema para la imagen de la empresa también con consecuencias económicas de pérdida de clientes o valor en la bolsa sino también legales con posibles cuantiosas multas , además de otras consecuencias intangibles como la reputación entre sus clientes y usuarios.

Otros ataques están dirigidos hacia los usuarios como el robo de datos personales, bancarios o credenciales con técnicas de suplantación de identidad o _phishing_. Aún hoy en día muchas personas apenas tienen unos conocimientos básicos de tecnología y la mayoría desconoce las medidas básicas de seguridad. Estas personas aún con una alfabetización digital muy básica están rodeadas de tecnología y realizan importantes tareas como compras y transacciones bancarias. Muchas personas son víctimas de fraudes y estafas por internet, a veces por su desconocimiento pero también a veces porque las empresas no implementan medidas de seguridad suficientes para que sus servicios sean más difícil de ser explotados.

Los informáticos tenemos una gran responsabilidad para diseñar sistemas seguros aplicando las mejores prácticas de seguridad no solo como algo adicional a la finalización de una funcionalidad, sino involucrando al rol de _security operations_  o _SecOps_ desde el inicio, al igual que no se deben dejar las pruebas de aseguramiento de calidad para el final la seguridad tampoco debería.
Los bancos utilizan medidas adicionales para realizar operaciones pero algunas medidas de seguridad dejan que desear en varios aspectos como requerir para todas las cuentas bancarias  iniciar sesión en la banca _online_ con el número del DNI y un código de seis dígitos, no implementar medidas de notificación de pagos, transferencias o falta un registro de actividad completo en la cuenta digital.

Sin embargo, me da miedo que un banco que debería ser un referente en medidas de seguridad incluyan en la página donde los usuarios introducen sus credenciales para el acceso a la banca online recursos JavaScript de [Facebook][facebook], [Twitter][twitter] y de dominios ajenos al banco que no controlan para hacer analítica web de seguimiento de usuarios.

{{< tableofcontents >}}

### Inyección de SQL, XSS, _tabnabbing_ y otras vulnerabilidades básicas

Hay errores de seguridad comunes identificados hace ya tiempo que aún no están obsoletos, no son errores de seguridad complejos de explotar y tampoco son errores complejos de evitar usando las técnicas adecuadas de programación o evitando utilizar ciertas operaciones en la manipulación de datos. Varios de los errores de seguridad son por usar datos provenientes de fuentes no confiables sin aplicar el tratamiento adecuado. Aún siendo errores simples son peligrosos en una aplicación vulnerable.

La fundación OWASP mantiene un registro detallado de los diferentes tipos de seguridad, explicando el problema de seguridad y cómo evitarlos. También publicar cada ciertos años una lista de los 10 errores más comunes durante ese año.

* [List of Attacks](https://owasp.org/www-community/attacks/)
* [SQL Injection Prevention Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html)
* [XSS Filter Evasion Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/XSS_Filter_Evasion_Cheat_Sheet.html)
* [Reverse Tabnabbing](https://owasp.org/www-community/attacks/Reverse_Tabnabbing)
* [El problema de seguridad tabnabbing y phishing en los enlaces en nuevas pestañas a páginas externas y cómo solucionarlo][blogbitix-486]

### _Salted Password Hashing_

Una aplicación que guarda credenciales para autenticar a los usuarios habitualmente solicita dos datos, un identificador del usuario o de la cuenta y una contraseña que como solo conoce el legítimo usuario permite identificarle en el sistema al iniciar sesión. Los identificadores de los usuarios y las contraseñas la aplicación necesita guardarlas en una base de datos para que la aplicación las compare con las que proporciona un usuario al iniciar sesión.

Guardar la contraseña en la base de datos el valor original que el usuario ha proporcionado es un peligro ya que cualquier persona con acceso a la base de datos es capaz de conocer el valor de la contraseña. Para no guardar el valor original es posible utilizar una función de _hash_ de un solo sentido que permite transformar el valor original en otro derivador a partir del cual no es posible conocer el valor original pero permite conocer si es válido aplicando la función al dato proporcionado y comparándolo con el guardado en la base de datos. Sin embargo, usar una función _hash_ no es suficiente ya que utilizando un ataque de diccionario con valores precalculados de _hashes_ de  valores comunes de contraseñas permite identificar cuentas con contraseñas débiles.

La forma correcta de guardar contraseñas es utilizando cifrado o sin cifrado utilizando una versión modificada de _hash_ denominada _salted password hashing_ que evita ataques de diccionario.

* [Guardar contraseñas usando «Salted Password Hashing» y otras formas correctas][blogbitix-75]

### Implementación de segundo factor de autenticación

Muchos usuarios no tienen conocimientos de informática, ni una alfabetización digital avanzada. Estos usuarios con unos conocimientos mínimos utilizan la tecnología por ser necesaria ya para muchas tareas o permitir realizarlas de forma más rápida y sencilla. Pero incluso usuarios con muchos conocimientos avanzados de tecnología también son vulnerables a ciertos tipos de ataques como los de _phishing_ sin las precauciones adecuadas. Los ataques de suplantación de identidad o _phishing_ son empleados por los delincuentes para robar información personal, credenciales de acceso a cualquier tipo de servicio y datos bancarios como tarjetas de crédito.

Las aplicaciones que ofrecen el segundo factor de autenticación y los usuarios que lo emplean mantienen su cuenta a salvo incluso si son víctimas de un ataque de _phishing_. Con el segundo factor de autenticación además de la contraseña que es el algo que solo el usuario sabe se requiere algo que se tiene que sería un generador de códigos temporales. En un ataque de _phishing_ los delincuentes se hacen con lo que se sabe, si esto es lo único que permite el acceso a una cuenta es suficiente para que los delincuentes accedan a ella. Con el segundo factor de autenticación la contraseña no es suficiente se requiere un código adicional, en caso de que un ataque de _phishing_ robase el código temporal sólo permitiría iniciar sesión en la cuenta durante un breve periodo de tiempo habitualmente de 30 segundos.

Los servicios más conocidos implementan el segundo factor de autenticación que los usuarios deben activar, no es difícil de implementar en Java.

* [Qué es, por qué y cómo activar un segundo factor de autenticación en Google, Amazon, PayPal y otros servicios][blogbitix-530]
* [Implementar un segundo factor de autenticación en una aplicación web Java con Spring][blogbitix-445]

### Notificación de acciones relevantes

Algunas operaciones son relevantes desde el punto de vista de seguridad, como el inicio de sesión, un intento fallido de inicio de sesión, cambio de contraseña o realización de algunas operaciones en el ámbito de la aplicación. Para mejorar la seguridad conviene enviar en este tipo de acciones una notificación al usuario que permita advertir ante acciones que se puedan realizar sin su conocimiento. Las notificaciones permiten al usuario tomar medidas en caso de que detecte acciones fraudulentas. Las notificaciones pueden ser a través de un correo electrónico, mensaje SMS o a través de mensajería instantánea.

Estos son dos ejemplos de correos electrónicos que envía [Gmail][google-gmail] y [Wallapop][wallapop] para notificar al usuario de un nuevo inicio de sesión en su cuenta.

{{< image
    gallery="true"
    image1="image:gmail-inicio-sesion.png" optionsthumb1="300x200" title1="Notificación de Gmail de inicio de sesión"
    image2="image:wallapop-inicio-sesion.png" optionsthumb2="300x200" title2="Notificación de Wallapop de inicio de sesión"
    caption="Correos electrónicos de notificación de inicio de sesión" >}}

### Registro de actividad

Mantener un registro de la actividad es otra variante para dar visibilidad y permitirle al usuario identificar actividad fraudulenta en su cuenta en caso de que haya sido víctima de un ataque o sus credenciales hayan sido robadas. Algunos servicios mantienen un registro con las fechas de los últimos inicios de sesión, la dirección IP de la computadora junto con su geolocalización que permite conocer su ubicación aproximada, el navegador usado, sistema operativo o fecha de últimos cambios de contraseña.

{{< image
    gallery="true"
    image1="image:gmail-registro-actividad.png" optionsthumb1="300x200" title1="Registro de actividad en una cuenta de Gmail"
    caption="Registro de actividad en una cuenta de Gmail" >}}

### Establecer límites de uso

Ya sea por una fallo de seguridad en un servicio vulnerable que ha sufrido un ataque o por un ataque dirigido al usuario por ejemplo con un ataque de _phishing_ es posible implementar medidas no dirigidas a evitar los ataques sino a mitigar o reducir los posibles daños de un posible ataque.

Para mitigar los posibles daños un servicio puede implementar medidas para limitar los datos como establecer un límite de intentos fallidos de inicio de sesión antes de bloquear la cuenta de forma temporal. Los bancos por ejemplo permiten activar o desactivar las tarjetas de crédito a petición del cliente que impidan la autorización de operaciones, con esta medida aunque un usuario haya sido atacado y un delincuente haya robado los datos de su tarjeta de crédito este puede desactivarla para evitar operaciones fraudulentas con ella unido a la notificación de acciones relevante un usuario puede actuar para evitar más daños. Otras medidas que utilizan los bancos son establecer límites de disposición de efectivo diario, importe máximo de transferencias, importe máximo en comercios físicos y electrónicos y de compras por internet.

### Cifrar información personal

Los datos personales están protegidos por leyes, un fallo de seguridad con el resultado de robo de datos personales también tiene consecuencias legales para la empresa con importantes multas y económicas por pérdida de clientes y mala imagen.

Los datos a proteger de los usuario son aquellos que permita identificarlos como nombre y apellidos, DNI, dirección de correo electrónico, dirección del domicilio, número de teléfono y por supuesto datos bancarios como número de cuenta y tarjetas de crédito.

Para evitar el riesgo de un ataque dirigido al servicio que ocasione una fuga de datos es conveniente cifrarlos. Incluso para que los empleados que trabajan en la empresa no conozcan a quién corresponden esos datos.

Cifrados los datos aun teniendo acceso a ellos la información está protegida ya que es necesario descifrarlos para obtener el valor original.

Vault es una aplicación que ofrece el cifrado como servicio entre otras funcionalidades de seguridad.

* [Cifrado y descifrado como servicio con Vault][blogbitix-557]

### Informar a los usuarios

Para prevenir fraudes otras medidas sencillas son informar a los usuarios de unas medidas básicas de prevención de fraude y explicar cómo son los procedimientos de comunicación.

Algunos son desconfiar de mensajes inesperados, no acceder a enlaces de mensajes SMS, de mensajería instantánea y leer con atención la dirección en el navegador de los enlaces a los que se acceder.

Nunca facilitar datos personales, credenciales de acceso a una cuenta no datos de tarjetas bancarias. Tampoco descargar ni instalar aplicaciones si no provienen de las tiendas oficiales del dispositivo. Y activar las notificaciones para conocer la actividad en la cuenta del servicio.

### Comunicaciones entre servicios

La arquitectura de las aplicaciones tienden a diseñarse de forma que ofrezcan un medio de invocar sus funcionalidades mediante llamadas de red ya sea con el protocolo HTTP mediante REST, GraphQL o gRPC.

Para autorizar las llamadas a los servicios estos han de implementar alguna forma de autenticación y autorización por ejemplo con OAuth y JWT.

* [Autenticación con OAuth y Keycloak en un servicio REST con JAX-RS y Spring Boot][blogbitix-180]

Otra forma de proteger los servicios más sencilla sin que cada uno de ellos tenga que implementar las funcionalidades de autenticación y autorización es utilizar un _service mesh_. [Consul][consul] además de ser un servicio que ofrece registro y descubrimiento ofrece la funcionalidad de conexión entre servicios con cifrado, autenticación mutua y autorización especificando qué servicio está autorizado a comunicarse con cual. 

Con las funcionalidades de conexión de Consul los servicios no es necesario que implementen el cifrado, autenticación y autorización. Los servicios tienen menos responsabilidades, son más sencillos y la autorización de la comunicación es gestionada desde Consul.

* [Comunicaciones seguras, autenticación mutua y autorizaciones con intenciones entre servicios usando Consul Connect y Nomad][blogbitix-502]

### Contraseñas en repositorios de código fuente y artefactos compilados

La seguridad de las aplicaciones se basa en contraseñas, claves privadas y certificados que únicamente las aplicaciones y los administradores de los servicios deben tener acceso. 

Las credenciales deben ser guardadas aparte del código fuente de la aplicación, fuera de la herramienta de control de versiones. Incluso las credenciales en una clase Java compila a _bytecode_ es posible acceder a ellas.

Estos secretos deben proporcionarse a las aplicaciones en tiempo de ejecución por el orquestador de procesos, el entorno de ejecución en la nube o de un servicio específico de seguridad del que obtenerlo. Para mayor seguridad las propiedades con secretos de los archivos de configuración en el sistema de archivos deben evitarse.

* [Las contraseñas e información sensible en el código fuente o _bytecode_ de Java no son seguras][blogbitix-371]

### Otras

Otra medida de seguridad es ofrecer la posibilidad de eliminar una cuenta o desactivarla tras un periodo de inactividad prolongado.

* [Application Security Best Practices](https://www.whitesourcesoftware.com/resources/blog/application-security-best-practices/)
* [7 must-dos for delivering app-focused security](https://techbeacon.com/security/7-must-dos-delivering-app-focused-security)

{{< reference >}}
* [Application security](https://en.wikipedia.org/wiki/Application_security)
{{< /reference >}}

{{% /post %}}

