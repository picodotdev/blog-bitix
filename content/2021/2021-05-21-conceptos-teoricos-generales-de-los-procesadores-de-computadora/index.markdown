---
pid: 576
type: "post"
title: "Conceptos teóricos generales de los procesadores de computadora"
url: "/2021/05/conceptos-teoricos-generales-de-los-procesadores-de-computadora/"
date: 2021-05-20T22:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:processors-cpugalaxy.jpg"
imagePost: "image:intel-pentium.jpg"
tags: ["hardware", "planeta-codigo"]
summary: "Los procesadores es uno de los principales circuitos integrados que posee toda computadora. Es el encargado de ejecutar el código de los programas y gobierna el resto de componentes. A pesar de su pequeño tamaño son elementos con gran complejidad, para aumentar su rendimiento se aplican diferentes técnicas que los procesadores han incorporando de forma paulatina. Al hablar de procesadores hay varios conceptos que es necesario comprender."
---

{{% post %}}

{{< logotype image1="intel.svg" image2="amd.svg" image3="arm.svg" >}}

Los procesadores actuales tienen una capacidad de cómputo en varios órdenes de magnitud a la de tan toso unas décadas antes. Buena parte de ese rendimiento es conseguido a través de la mejora de los procesos litográficos que permiten fabricar transistores cada vez más pequeños. Con transistores más pequeños se mejoran varios aspectos vitales de los procesadores entre ellos se reduce su consumo de energía, permiten añadir más transistores en el mismo espacio físico para implementar cachés más grandes o implementar conceptos que mejoren el rendimiento y finalmente son más baratos de fabricar.

Desde la aparición de los circuitos integrados que sustituyeron a las válvulas de vacío, según la [ley de Moore](https://es.wikipedia.org/wiki/Ley_de_Moore) cada dos año en número de transistores que incluye un procesador se duplica, esta ley se ha estado cumpliendo hasta ahora y por consiguiente la potencia de los procesadores. Uno de los primeros microprocesadores de Intel como el 4004 tenía únicamente 2300 transistores a 10 micrómetros, un Intel Core o un Ryzen llegan superan los 2 mil millones de transistores a 7 nanómetros.

Varios de los conceptos teóricos aplicados en los procesadores está destinado a aumentar el rendimiento en monohilo, así ha sido hasta el desarrollo de los procesadores con múltiples núcleos tanto de Intel como de AMD y también de ARM. Desde el año 2010 el aumento de rendimiento de los procesadores también se consigue mediante la inclusión de mayor número de núcleos, para tareas paralelizables. Un amyor número de núcleos consigue un aumento de rendimiento en una proporcion similar a la proporción de aumento de núcleos. Crear procesadores multinúcleo es otra forma de seguir aumentando el rendimiento de forma más sencilla que mejorando aún más el rendimiento en monohilo. Los procesadores actuales van desde los 4 núcleos hasta cantidades como 16 para ordenadores de escritorio y hasta 64 o más para computadoras destinadas a hacer funciones de servidor.

{{< image
    gallery="true"
    image1="image:intel-P4004.jpg" optionsthumb1="200x150" title1="Procesador Intel 4004"
    image2="image:intel-D8086.jpg" optionsthumb2="200x150" title2="Procesador Intel 8086"
    image3="image:intel-pentium.jpg" optionsthumb3="200x150" title3="Procesador Pentium"
    caption="Procesadores Intel 4004, 8086 y Pentium" >}}

{{< image
    gallery="true"
    image1="image:amd-athlon-xp.jpg" optionsthumb1="200x150" title1="Procesador AMD Athlon XP"
    image2="image:sun-ultra-sparc-II.jpg" optionsthumb2="200x150" title2="Procesador Sun UltraSparc II"
    image3="image:risc-v.jpg" optionsthumb3="200x150" title3="Procesador RISC-V"
    caption="Procesadores de AMD, Sun Ultra Sparc y SiFive RISC-V" >}}

Los siguientes apartados son una explicación sencilla, básica y resumida de los conceptos que se utilizan en los procesadores de los que se puede. Esta información está ampliada en la wikipedia.

{{< tableofcontents >}}

### Arquitectura

Los computadores siguen una organización definida en el [modelo de von Neumann](https://en.wikipedia.org/wiki/Von_Neumann_architecture) en el que una computadora consta de:

* Una unidad de procesamiento que contiene lógica de aritmética y los registros del procesador.
* Una unidad de control que contiene un registro de instrucción y un contador de programa.
* Memoria que almacena los datos y las instrucciones del programa.
* Almacenamiento externo.
* Mecanismos de entrada y salida.

{{< image
    gallery="true"
    image1="image:a-basic-computer.gif" optionsthumb1="300x200" title1="Esquema de computadora"
    image2="image:von-neumann-architecture.svg" optionsthumb2="300x200" title2="Arquitectura de Von Neumann"
    caption="Esquema de computadora y arquitectura de Von Neumann" >}}

#### Componentes de un procesador

Los componentes son los siguientes:

* Caché: es una memoria pequeña comparada con la memoria principal o el almacenamiento secundario pero mucho más rápida que estas.
* Unidades funcionales: la unidad de lógica aritmética o ALU permite realizar operaciones del cálculo matemático y binarias, la unidad de coma flotante o FPU permite realizar operaciones en coma flotante. La unidad de predicción de de saltos permite predecir cuál será el resultado de una condición, otras unidades son la unidad de generación de direcciones o AGU, la _load–store unit translation lookaside buffer_ o TLB permite traducir direcciones virtuales a direcciones físicas y es parte de la unidad de gestión de memoria o MMU. El controlador integrado de memoria o IMC gestiona las transferencias entre la memoria principal y el procesador.
* Registros: son unas pequeñas zonas de memoria en las que operan las instrucciones del procesador.
* Unidad de control: es la encargada de enviar las señales a las otras unidades del procesador.

#### Tamaño de palabra

El tamaño de palabra es la unidad de datos nativa del procesador media en número bits. Los registros del procesador suelen tener tener el mismo tamaño de palabra que el procesador y la máxima cantidad de información que se suele poder transferir desde y a la memoria en una única operación coincide con el tamaño de palabra. Los procesadores x86-64 tiene un tamaño de palabra de 64 bits.

#### _Endianness_

La arquitectura de un procesador define cómo se almacenan los datos en memoria, puede ser de dos formas _litte-endian_ o _big-endian_. En la primera el byte de datos menos significativo se almacena en la dirección de memoria más baja, por el contrario en _big-endian_ el byte más significativo se almacena en la dirección más baja.

Que una arquitectura de procesador elija entre estos dos tipos de _endianness_ no es significativo, simplemente se ha de mantener de forma consistente al almacenar, cargar los datos y transmitirlos a otras computadoras.

En el protocolo IP se utiliza _big-endian_, por el contrario el la mayoría de procesadores de arquitectura de procesadores como x86, ARM y RISC-V utilizan _litte-endian_.

* [Endianness](https://en.wikipedia.org/wiki/Endianness)

{{< image
    gallery="true"
    image1="image:big-endian.svg" optionsthumb1="300x200" title1="Big endian"
    image2="image:little-endian.svg" optionsthumb2="300x200" title2="Little endian"
    caption="Big y little endian" >}}

#### Almacenamiento de computadora, NUMA y UMA

El significado de las siglas de NUMA son _Non-uniform memory access_ y de UMA son _Uniform memory access_, en el primer caso el tiempo de acceso  a memoria no es uniforme y depende de la ubicación del la memoria relativa al procesador, en este caso para un procesador es más rápido acceder a su memoria local que a la memoria de otro procesador.

Los procesadores actuales son significativamente más rápidos que la memoria principal por ello suelen tener pequeñas cachés de memoria más rápidas, al mismo tiempo se evita que varios procesadores compitan por el acceso a la memoria principal. Este modelo NUMA mejora el rendimiento pero ocasiona el problema que cuando un procesador requiere los datos de otro ocasiona que los datos han de ser movidos con una penalización en la latencia.

AMD lo implementó el almacenamiento NUMA en los procesadores Opteron con [HyperTransport](https://en.wikipedia.org/wiki/HyperTransport) e Intel con [QPI](https://en.wikipedia.org/wiki/Intel_QuickPath_Interconnect).

* [Non-uniform memory access](https://en.wikipedia.org/wiki/Non-uniform_memory_access)
* [Uniform memory access](https://en.wikipedia.org/wiki/Uniform_memory_access)

#### Memoria y jerarquía de memoria

La memoria contiene los datos y las instrucciones de los programas, el procesador lee los datos de la memoria los carga en las cachés y los registros del procesador, realiza el cálculo deseado y los resultados son almacenados de nuevo en la memoria principal.

Hay cuatro niveles de almacenamiento.

* Interno: formado por los registros del procesador y sus cachés internas.
* Memoria principal: formada por la memoria RAM volátil cuyo contenido se pierde si se apaga la computadora.
* Secundaria: es una memoria persistente conectada a la computadora y que conserva los datos aún después de apagar la computadora.
* Terciaria:  es una memoria que se puede extraer y conectar del equipo bajo demanda.

La memoria se puede diferenciar en las siguientes propiedades:

* Volatilidad: si persiste al apagado de la computadora.
* Mutabilidad: si es posible cambiar su contenido.
* Accesibilidad: el modo de lectura permitido si es aleatorio o ha de ser secuencial.
* Direccionamiento.
* Capacidad: el tamaño de la memoria.
* Rendimiento: el tiempo de acceso necesario.
* Uso de energía.
* Seguridad.
* Vulnerabilidad y confiabilidad.

La diferencia de velocidad de procesamiento de la CPU y la velocidad de acceso a la memoria principal y secundaria hace que para aumentar el rendimiento de la CPU sea necesario una memoria pequeña pero más rápida que la memoria principal en el procesador. En la jerarquía de memoria en el nivel superior su latencia es inferior, su velocidad de transferencia es mayor pero su tamaño es menor. Si la tasa de aciertos en la caché es alta el procesador aumenta significativamente al no ser penalizado por la latencia de acceso a la memoria principal.

* [Computer data storage](https://en.wikipedia.org/wiki/Computer_data_storage)
* [Memory hierarchy](https://en.wikipedia.org/wiki/Memory_hierarchy)

{{< image
    gallery="true"
    image1="image:cache-hierarchy.png" optionsthumb1="300x200" title1="Jerarquía de caché"
    image2="image:computer-memory-hierarchy.svg" optionsthumb2="300x200" title2="Jerarquía de caché"
    caption="Jerarquía de caché" >}}

#### Memoria virtual

La memoria principal del sistema es limitada aun siendo de varias decenas de GB. La memoria virtual es una técnica que permite ampliar la memoria del sistema utilizando el siguiente nivel de la jerarquía de memoria , normalmente el almacenamiento persistente de mayor capacidad.

El sistema operativo con ayuda de funciones de gestión de memoria implementadas en el procesador se encarga de mover fuera de la memoria principal la que no se está utilizando y de cargar en la memoria principal la que se necesite.

#### Microcódigo

El microcódigo es un software que traduce las instrucciones en operaciones a ejecutar en el _chip_. Esta traducción de instrucciones permite los desarrolladores de _chips_ flexibilidad en la implementación. Por ejemplo, en una versión mejorada del _chip_ se puede soportar el mismo conjunto de instrucciones que versiones anteriores o soportar diferentes conjuntos de instrucciones en el mismo _chip_.

* [Microcode](https://en.wikipedia.org/wiki/Microcode)

### Arquitecturas de conjunto de instrucciones

La arquitectura del conjunto de instrucciones o ISA es el modelo abstracto de una computadora, define los tipos de datos soportados, los registros, el hardware de soporte para la memoria principal y el modelo de entrada y salida. También forma parte el conjunto de instrucciones soportadas y su codificación binaria.

El conjunto de instrucciones es una de las características mas relevantes de un procesador, los programas compilados a código máquina están codificados en forma binarias según las instrucciones de una ISA.

* [Instruction set architecture](https://en.wikipedia.org/wiki/Instruction_set_architecture)
* [Microarchitecture](https://en.wikipedia.org/wiki/Microarchitecture)

{{< image
    gallery="true"
    image1="image:mips32-addi.svg" optionsthumb1="300x200" title1="Instrucción addi de un procesador MIPS"
    caption="Instrucción addi de un procesador MIPS" >}}

#### Conjuntos de instrucciones

El conjunto de instrucciones soportados por un procesador depende de su arquitectura. La arquitectura más popular en ordenadores de escritorio es la x86, en los dispositivos móviles como teléfonos inteligentes es ARM, otras arquitecturas alternativas son RISC-V que es una arquitectura abierta sin costes de licencia, otras son MIPS, POWER y Sparc.

Los lenguajes de programación de alto nivel como C traducen los programas a un binario ejecutable por la computadora, Java traduce los programas en la compilación a un _bytecode_ independendiente de la arquitectura del procesador y es en tiempo de ejecución cuando se realiza la traducción del _bytecode_ a las instrucciones que entiende el procesador.

Dos aproximaciones al conjunto de instrucciones son CISC que proporciona un conjunto amplio de instrucciones complejas y RISC que proporciona un conjunto reducido de instrucciones más simples. El equivalente de una instrucción CISC pueden ser varias RISC. Los procesadores CISC tienen la ventaja de hacer que los programas tengan menor tamaño pero hace que los procesadores sean más complejos, por el contrario en los procesadores RISC los programas son más grandes al necesitar más instrucciones pero los procesadores son más sencillos. La diferencia entre un procesador CISC y RISC no es muy significativa con la utilización de microcódigo.

Las instrucciones se agrupan en las siguientes categorías:

* Establecimiento de datos y operaciones con memoria cómo establecer un registro con un valor y mover datos de un registro a otro.
* Operaciones aritméticas y lógicas como sumar, restar, multiplicar o dividir entre los valores de dos registros, realizar operaciones binarias o comprar dos valores.
* Control de flujo como saltar a otro punto del programa, salto condicional o llamada a una función.
* Instrucciones de coprocesador como cargar o almacenar datos en los registros del procesador.

Las instrucciones _single instruction multiple data_ o SIMD operan múltiples datos de forma paralela con una única instrucciones, esto mejora significativamente el rendimiento respecto si hubiera que ejecutar una instrucción por cada dato. Es una forma diferente de conseguir paralelismo.

En la arquitectura x86 las instrucciones MMX, SSE y AVX son variantes de instrucciones SIMD. El primer procesador con instrucciones MMX fabricado por Intel fue el Pentium MMX. El primer procesador de Intel con instrucciones SSE fue el Pentium 3 y el primero con AVX fue a partir de la familia Sandy Bridge.

* [Complex instruction set computer](https://en.wikipedia.org/wiki/Complex_instruction_set_computer)
* [Reduced instruction set computer](https://en.wikipedia.org/wiki/Reduced_instruction_set_computer)
* [Advanced Vector Extensions](https://en.wikipedia.org/wiki/Advanced_Vector_Extensions)
* [x86 architecture](https://en.wikipedia.org/wiki/X86)
* [ARM architecture](https://en.wikipedia.org/wiki/ARM_architecture)
* [IBM POWER instruction set architecture](https://en.wikipedia.org/wiki/IBM_POWER_instruction_set_architecture)
* [PowerPC](https://en.wikipedia.org/wiki/PowerPC)
* [RISC-V](https://en.wikipedia.org/wiki/RISC-V)
* [SPARC](https://en.wikipedia.org/wiki/SPARC)

### Ciclo de ejecución del procesador

La principal tarea de un procesador es ejecutar instrucciones, para ello el procesador sigue un ciclo de obtener la siguiente instrucción de la memoria principal según el contador del programa, decodificación en la que se determina que instrucción se ejecuta y ejecución en la que se utilizan los diferentes componentes del procesador como la unidad de lógica aritmética.

* [Instruction cycle](https://en.wikipedia.org/wiki/Instruction_cycle)

#### _Pipelining_ de instrucciones

Los procesadores tiene varios componentes, para mantenerlos todos en funcionamiento con el objetivo de maximizar el rendimiento las instrucciones se ejecutan en varias etapas. En un momento determinado el procesador posee varias instrucciones en diferentes etapas de ejecución. Cada uno de estos pasos de ejecución es un segmento de ejecución, denominando a estos procesadores como segmentados.

El primer procesador segmentado fabricado por Intel fue el Intel 386 con un _pipeline_ de tres etapas.

Un procesador como los Intel Pentium 4 llegaba a tener un _pipeline_ de ejecución de hasta 31 etapas. El problema es que un _pipeline_ tan largo no mejora el rendimiento cuando se producen saltos al ejecutar instrucciones condicionales y el predictor de bifurcaciones falla en el predicción, en un salto el _pipeline_ se vacía y comienza en la primera etapa con la primera instrucción a la que se ha saltado.

* [Instruction pipelining](https://en.wikipedia.org/wiki/Instruction_pipelining)

{{< image
    gallery="true"
    image1="image:pipeline-4-stage-with-bubble.svg" optionsthumb1="300x200" title1="Pipeline de 4 segmentos"
    caption="Pipeline de 4 segmentos" >}}

#### _Hazards_

Hay varios peligros en la ejecución paralela que ha de tenerse en cuenta para un buen funcionamiento de los procesadores segmentados. Los riesgos son de tres tipos, de datos, estructurales y de control.

En los riegos de datos una instrucción de lectura posterior a una instrucción de escritura para un dato ha de producirse después, una instrucción de lectura posterior a una de escritura para un dato ha de producirse después y dos instrucciones de escritura sobre el mismo dato ha de producirse en el mismo orden del programa.

Los riesgos estructurales consisten en que dos instrucciones en el _pipeline_ necesiten el mismo recurso como la ALU, en este caso las instrucciones han de esperar.

Los riesgos de control consisten en que se hayan insertado en el _pipeline_ instrucciones que finalmente han de descartarse por una mala predicción en un salto en el programa.

* [Hazard (computer architecture)](https://en.wikipedia.org/wiki/Hazard_(computer_architecture))

#### Ejecución fuera de orden

La ejecución fuera de orden permite aprovechar los ciclos de CPU que de otra manera no se utilizarían. Con este paradigma, el procesador ejecuta las instrucciones según la disponibilidad de los datos de entrada y las unidades de ejecución, en vez de en el orden original del programa. Esto permite evitar tiempos inactivos esperando a que la instrucción precedente se complete en el caso de que sean independientes.

* [Out-of-order execution](https://en.wikipedia.org/wiki/Out-of-order_execution)

#### Ejecución especulativa

La ejecución especulativa es otra optimización que permite aumentar el rendimiento que consiste en ejecutar instrucciones aún antes de saber si son necesarias.

Los programas incluyen sentencias condicionales, en función de su evaluación el flujo del programa continúa por una rama de la condición u otra, hasta no evaluarse la condición no se sabe porque rama se continúa. La ejecución especulativa ejecuta las instrucciones de ambas ramas descartando los resultados de una cuando se conoce que rama finalmente se ha seguido.

Esta técnica aumenta el rendimiento pero con la cual se han descubierto varios problemas relativos a la seguridad con su [Explicación del fallo de seguridad Meltdown y Spectre en los microprocesadores Intel][blogbitix-293] que han afectado a los procesadores que la implementaron tanto de Intel, de AMD como de ARM.

* [Speculative execution](https://en.wikipedia.org/wiki/Speculative_execution)

### Paralelismo 

Para aumentar el rendimiento los procesadores intentan ejecutar las operaciones de forma paralela.

* [Parallel computing](https://en.wikipedia.org/wiki/Parallel_computing)

#### _Bit_

Una forma de aumentar el paralelismo es aumentar el tamaño de palabra del procesador. Un procesador con una palabra de 8 bits necesita dos ciclos para realizar la suma de dos números de 16 bits, un procesador de 16 bits solo necesita un ciclo con lo que en la práctica es duplicar el rendimiento del procesador en este tipos de operaciones.

#### Escalar y superescalar

Un procesador escalar en todo momento solo ejecuta una instrucción, en cambio un procesador superescalar ejecutar múltiples instrucciones usando varias unidades de ejecución. Los procesadores superescalares tienen varias unidades de ejecución del mismo tipo, por ejemplo un procesador superescalar tiene varias unidades ALU o de FPU. El procesador trata de mantener cada una de estas diferentes unidades con instrucciones a ejecutar. Un procesador superescalar con dos ALU es capaz de realizar el doble de operaciones por ciclo de reloj y por tanto supone duplicar el rendimiento en este tipos de operaciones.

El primer procesador superescalar fabricado por Intel fue el Pentium.

* [Scalar processor](https://en.wikipedia.org/wiki/Scalar_processor)
* [Superscalar processor](https://en.wikipedia.org/wiki/Superscalar_processor)

#### Multihilo

Un procesador con procesamiento multihilo o SMT trata de mejorar el rendimiento del procesador superescalar. Cada núcleo físico es visto por el sistema operativo como dos o más núcleos lógicos, cada procesador lógico tiene sus propios registros de control sin embargo las unidades de ejecución son compartidas por los hilos. Cada hilo puede ser detenido o interrumpido de forma independiente al resto de hilos.

El número de hilos que permite un procesador por cada cada núcleo puede ser tan simple como dos, dependiendo de la microarquitectura del procesador pueden ser un número más grande como cuatro u ocho.

El primer procesador con hyper-threading fabricado por fue el Intel Pentium 4.

Los procesadores y sistemas operativos conceden a los programas un tiempo de ejecución en el procesador, transcurrido el tiempo sin interrumpidos y se continua con otro proceso, el proceso no tiene el control de cuando se interrumpe su ejecución, a este estilo de compartir el recurso del procesador se le denomina [multitarea preemtiva](https://en.wikipedia.org/wiki/Preemption_(computing)). Incluso con la velocidad de los procesadores de mononucleo en los que solo se ejecutan un solo programa la sensación es que los programas se están ejecutando de forma paralela.


#### Multinúcleo

Un procesador con procesamiento paralelo o SMP incorpora varios núcleos físicos en el mismo procesador. Cada núcleo es independiente y ejecuta un proceso en cada núcleo de forma simultánea. En un procesador mononúcleo la programación simultánea era simulada mediante la apropiación preemptiva junto con la rapidez del procesador para en la ejecución de instrucciones y cambios de contexto de un procesador a otro.

El primer procesador multinúcleo fabricado por Intel fue el Pentium D.

#### Número de núcleos

Los primeros procesadores tenían un único núcleo o unidad de cómputo denominados _single-core_, posteriormente se ha implementado en un mismo procesador varios núcleos denominados _multi-core_.

Los procesadores _multi-core_ de escritorio consumno están diseñados para ejecutar eficientemente tanto código en serie como en paralelo poniendo gran énfasis en la ejecución de un único hilo, para ello dedican espacio en el procesador para implementar ejecución fuera de orden, pipelines profundos, son superescalares y tienen cachés grandes.

Los procesadores _many-core_ están especializados en ejecutar gran cantidad de operaciones en paralelo, para ello tienen una gran cantidad de núcleos de computación. Si un procesador _muti-core_ tiene unos pocos núcleos al estar limitados el número de ellos por la coherencia de la caché los procesadores _many-core_ se cuentan por miles. Las GPU son un ejemplo del procesador _many-core_.

### Seguridad

Los procesadores incluyen funcionalidades con la colaboración del sistema operativo para que la computación de los programas sea segura, con el objetivo de que un programa no se apropie de toda la capacidad de computación o que la memoria de un programa esté aislada de otros programas.

* [Trusted Execution Technology](https://en.wikipedia.org/wiki/Trusted_Execution_Technology)

{{< image
    gallery="true"
    image1="image:processor-security-rings.svg" optionsthumb1="300x200" title1="Anillos de seguridad de un procesador"
    caption="Anillos de seguridad de un procesador" >}}

#### Modo protegido

Los procesadores incluyen un modo de ejecución con privilegios especiales que son exclusiva del sistema operativo. Algunas instrucciones solo pueden ser ejecutadas en el modo protegido por seguridad.

El primer procesador con un modo protegido fabricado por Intel fue el 386.

* [Protected mode](https://en.wikipedia.org/wiki/Protected_mode)

#### Cifrado de memoria

Para minimizar las consecuencias de una fallo de seguridad los procesadores más recientes destinados a la computación en la nube, donde el entorno de computación es compatido aún estando en ciertamedia aislado lógicamente, también incluyen funcionalidades para cifrar completamente la memoria de los datos y programas, de modo que aunque un programa tenga acceso a la memoria de otro la información al estar cifrada no sea legible.

### Rendimiento de procesador

El rendimiento de un procesador se mide en instrucciones por ciclo o IPC, instrucciones por segundo o IPS, operaciones de coma flotante por segundo FLOPS, rendimiento por vatio o PPW.

Una variable que impacta en el rendimiento es el número de transistores con el que está construido el procesador, a mayor número de transistores es posible incluir cachés más grandes, mayor número de núcleos y mayor número de componentes. Sin embargo, un mayor número de transistores implica una mayor cantidad de energía consumida.

Los diseñadores de procesadores han de encontrar un equilibrio óptimo entre número de transistores, tamaño del _chip_, coste de fabricación, energía consumida y disipada en forma de calor y precio del procesador.

Una mejora de los procesos litográficos que permiten crear transistores de menor tamaño es beneficioso para los procesadores ya que mejoran el número de transistores que se pueden incluir en el procesador en el mismo espacio, hacen los procesadores más pequeños, la energía consumida y el calor disipado es menor y el precio de fabricación del procesador se abarata.

### Tipos de procesadores

La CPU es el principal componente de una computadora que se encarga de ejecutar las instrucciones del programa, otro tipo de procesador especializado son las GPU dedicadas al procesamiento de gráficos o en tareas de inteligencia artificial. Las GPU están especializadas en ejecutar gran cantidad de operaciones de forma paralela.

Un _system on chip_ o SoC incluye la mayoría de componentes de un sistema es un único _chip_ en vez de estar separados. Un SoC puede incluir tanto la CPU, la memoria, los puertos de entrada/salida y otros elementos como almacenamiento secundario, la GPU y otros elementos especializados en comunicaciones y procesamiento de datos. Esto les hace más baratos que un sistema en el que los componentes están formados por _chips_ individuales.

* [Processor](https://en.wikipedia.org/wiki/Processor_(computing))

### Gestión de energía

Los procesadores para reducir su consumo de energía y el calor que dispan emplean diferetnes técnicas como variar el voltaje de funcionamiento o la frecuencia de funcionamiento.

{{% /post %}}
