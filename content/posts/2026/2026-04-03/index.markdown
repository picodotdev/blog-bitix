---
pid: 726
type: "post"
title: "AppSec vs. seguridad de cloud: El punto donde las responsabilidades se encuentran"
url: "/2026/04/appsec-vs-seguridad-de-cloud-el-punto-donde-las-responsabilidades-se-encuentran/"
aliases: ["/2026/03/appsec-vs-seguridad-de-cloud-el-punto-donde-las-responsabilidades-se-encuentran/"]
date: 2026-04-03T14:00:00+02:00
language: "es"
index: false
rss: false
sharing: true
comments: false
promoted: true
imageHead: "image:258fdd65-3b5d-4e70-bbf6-4cecabd18006.webp"
imagePost: "image:258fdd65-3b5d-4e70-bbf6-4cecabd18006.webp"
tags: ["patrocinado"]
---

{{% post %}}

La seguridad de aplicaciones y la seguridad de cloud a menudo se toman como dos frentes separados. Mientras el equipo de AppSec revisa el código, las dependencias y los pipelines, cloud security vigila identidades y configuraciones.

Aunque en el papel parece una división lógica, el informe State of AI in Security & Development 2026, elaborado a partir de una encuesta a 450 profesionales de Europa y Estados Unidos que usan herramientas de seguridad de aplicaciones ([application security tools](https://www.aikido.dev/blog/top-appsec-tools)), demuestra lo contrario. 

Los equipos que separan departamentos de seguridad de aplicaciones y de cloud tienen un 50% más probabilidades de atravesar un incidente. A continuación, los detalles. 

## 1 de cada 5 encuestados sufrió un incidente serio vinculado a código generado por IA

La encuesta de Aikido, una empresa de soluciones de desarrollo, marca que el 69% de las organizaciones detectó vulnerabilidades vinculadas al código generado con IA. La mayoría de estas dificultades fueron menores, pero 1 de cada 5 encuestados admite haber sufrido un incidente serio vinculado directamente a ese tipo de código. 

El 92% de los expertos en ciberseguridad admite estar preocupado por la filtración de datos a través de exposiciones accidentales, permisos mal definidos y superficies de ataque ampliadas, debido al uso excesivo de IA para crear código. 

La solución, de acuerdo a la empresa, parece ser la vinculación entre AppSec y Cloud Security. Mientras la primera detecta dónde nace el riesgo, la segunda observa los puntos donde las amenazas pueden escalar. Separar estos dos departamentos puede ser un riesgo en sí mismo, ya que no permite a los especialistas ver la película completa, sino pequeños fragmentos. Los datos actúan como un testimonio de este punto.

## Los equipos que usan AppSec y CloudSec separados tienen una tendencia 50% mayor a los incidentes

Uno de los descubrimientos de la encuesta es que los equipos que usan herramientas separadas para AppSec y cloud security tienen un 50% más de probabilidades de sufrir incidentes que aquellos que trabajan con un enfoque integral de la ciberseguridad. 

De la misma forma, el 93% de los empleados que operan con stacks separados reporta problemas de integración, como alertas duplicadas o datos inconsistentes.

Por ejemplo, cuando un hallazgo de SAST no conversa con una mala configuración en Kubernetes, o cuando una exposición en runtime no se conecta con una librería vulnerable detectada en SCA, el tiempo de respuesta se alarga. En ciberseguridad, retrasar la correlación suele equivaler a retrasar la remediación. 

Aquí cabe hacer una aclaración. La confianza en la IA no implica que no se revise el código. El 96% de los encuestados por Aikido cree que en el futuro las herramientas de inteligencia artificial crearán código "casi perfecto", pero el matiz es que solamente el 21% piensa que esto se logrará sin supervisión de un especialista humano. 

## Más vendors, más fricción y vulnerabilidades 

De lo dicho anteriormente se desprende que sumar vendors no siempre es sinónimo de generar un entorno más seguro. En la realidad, a veces simplemente suma fricciones, y esto queda respaldado con el dato de que aquellos equipos que sufrieron incidentes usaban, de media, más herramientas de seguridad que los que no los sufrieron: 5,1 frente a 4,2.

Asimismo, la separación entre AppSec y seguridad cloud aumenta el riesgo y castiga la operación diaria. Los ingenieros dedican una media de 6,1 horas semanales a revisar y priorizar alertas de seguridad, un volumen que crece a medida que aumenta el número de herramientas en uso. 

Los falsos positivos consumen unas 4,8 horas por semana. Por eso, añadir nuevas herramientas a un equipo ya saturado de alertas sin importancia es como seguir poniendo palos a la misma rueda.

Esta dificultad operativa [mina la productividad][blogbitix-718]. Los especialistas pueden pasar casi 5 días más en arreglar amenazas reales, una demora que pone en peligro la integridad del sistema. 

Así se explica por qué AppSec y cloud security ya no deberían pensarse como silos organizativos, sino como una cadena continua de señal. Desde el commit hasta el runtime, la prioridad no debería ser detectar más, sino mejor.

## Si la experiencia del desarrollador mejora, mejora también la ciberseguridad

La unión hace la fuerza y por eso la discusión en 2026 ya no se centra en quién manda más, si AppSec o CloudSec. Las organizaciones que quieran corregir el riesgo de extremo a extremo deberán buscar herramientas que prioricen la [experiencia del desarrollador](https://www.codemotion.com/magazine/es/carreras-tech/experiencia-del-desarrollador/) y que habiliten el trabajo entre equipos. 

La principal conclusión del informe realizado en base a la encuesta a más de 450 colaboradores es que las organizaciones que usan herramientas pensadas tanto para developers como para equipos de seguridad reportan menos incidentes materiales que aquellas que emplean soluciones orientadas a una sola audiencia. 

Un dato que no debe pasar inadvertido es que el 65% de los equipos acaba incurriendo en conductas arriesgadas (posponer fixes, descartar alertas o saltarse controles) debido a la fatiga provocada por falsos positivos.

Para quien sepa leerla, esta información es oro. En ciberseguridad no gana el que hace más rápido o con un mayor número de herramientas, sino el que actúa preventivamente y con cabeza fría desde el principio. 

{{% /post %}}

