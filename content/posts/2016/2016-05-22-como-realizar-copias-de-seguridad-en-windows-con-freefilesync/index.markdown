---
pid: 144
type: "post"
title: "Cómo realizar copias de seguridad en Windows con FreeFileSync"
url: "/2016/05/como-realizar-copias-de-seguridad-en-windows-con-freefilesync/"
date: 2016-05-22T13:00:00+02:00
updated: 2020-10-18T20:00:00+02:00
index: true
rss: true
sharing: true
comments: true
language: "es"
imagePost: "logotype:freefilesync.webp"
tags: ["microsoft", "software", "software-libre"]
summary: "Realizar copias de seguridad cada cierta frecuencia nos salvará de perder los datos en algún momento, no sabemos cuando pero tarde o temprano algo del equipo que usemos fallará ya sea a causa de software o hardware. A nivel de usuario mantener una copia sincronizada en otro dispositivo de nuestros archivos seguramente sea suficiente para no perderlos a causa de un desastre, para ello podemos usar FreeFileSync."
---

{{% post %}}

{{< logotype image1="freefilesync.webp" title1="FreeFileSync" width1="200" >}}

Realizar copias de seguridad es algo imprescindible si no queremos perder los datos el día que el sistema falle. No sabemos cuando alguna de las piezas de las que se compone un sistema fallará pero es seguro que un día fallará, puede que sean en meses, años, un lustro o una década. El fallo puede producirse por múltiples motivos relacionados con el hardware en el que algún componente físico se ha estropeado por desgaste, un pico de tensión, un golpe o caída como el disco duro, SSD, la placa base, la pantalla de un portátil, la fuente de alimentación, la memoria, el microprocesador o puede ser que el software como el sistema operativo que no se inicia por una actualización problemática, algún controlador de dispositivo produce algún error fatal en forma de <abbr title="Blue Screen of Dead, Pantalla azúl de la muerte">BSOD</abbr>, algún virus que impide iniciar el sistema, ha cifrado los archivos o ha corrompido el sistema haciendo que no sea usable.

Nuestros datos en forma de documentos, fotos, música o vídeos son más importantes que el equipo estropeado ya que este se se puede reemplazar pero recuperar los datos será mas complicado o imposible dependiendo del fallo si no hemos realizado copias de seguridad. En GNU/Linux hay múltiples programas y formas de hacer las copias de seguridad, incrementales, comprimidas, cifradas, ... Un programa es [FreeFileSync][freefilesync] que está disponible para los sistemas operativos mayoritarios.

Las copias de seguridad deben guardase en un dispositivo diferente al origen ya que en caso de fallo del hardware origen la copia de seguridad no se podría recuperar, la función de la copia de seguridad es tener un respaldo de los datos en caso de una pérdida de los datos en el dispositivo origen, ya sea por un fallo en el hardware o por la eliminación accidental de los datos por software. Para mayor seguridad dependiendo de la importancia de los datos incluso es recomendable hacer copias de seguridad por duplicado en varios discos. En los datos más críticos y para proteger de otras forma de pérdidas de datos como un incendio o robo incluso almacenar las copias de seguridad en diferentes ubicaciones. Las copias de seguridad deben realizarse de forma periódica ya sea manualmente o con herramientas que permiten realizar las copias de seguridad de forma automatizada.

### Discos duros externos para hacer copia de seguridad de datos

Una opción sencilla sin más complicaciones de opciones más avanzadas es un disco duro externo USB de 2.5 pulgadas, estos discos son pequeños además de portables, en diferentes capacidades según la necesidad llegando incluso a 4 TB y a unos precios muy asequibles. Una buena opción son estos discos duros externos de 2.5" con conexión 3.0 USB en capacidades de [1 TB](https://amzn.to/31h8F7y), [2 TB](https://amzn.to/3j7ZAUY), [3 TB](https://amzn.to/346DnSW) y [4 TB](https://amzn.to/358X8Zf). Hay que tener en cuenta que la copia de seguridad requiere al menos tanto espacio como los datos originales.

{{< amazon
    tags="storage-usb-external" >}}

### Utilizar el programa FreeFileSync para hace copias de seguridad

FreeFileSync tiene una licencia de software libre y está disponible tanto para [Windows][windows], [GNU][gnu]/[Linux][linux] y [macOS][macos]. Ofrece una interfaz gráfica y su uso no es complicado. Primero deberemos seleccionar los directorios de los que hacer copia de seguridad y para cada uno de ellos el directorio destino en el que se guardará la copia de seguridad, FreeFileSync llama al origen y destino izquierdo y derecho ya que los presenta así en la interfaz y con la característica que cualquiera de ellos puede actuar como origen y destino. Habitualmente el directorio origen será uno del disco duro del sistema y el directorio destino un directorio de un disco duro o memoria USB externa.

{{< image
    gallery="true"
    image1="image:freefilesync.webp" optionsthumb1="300x200" title1="FreeFileSync" >}}

Después de indicar orígenes y destinos debemos seleccionar el tipo sincronización entre ambos directorios. Las opciones que nos ofrece de sincronización son:

* Bidireccional: busca cambios en tanto en el lado izquierdo como del derecho y hace la sincronización en el otro lado.
* Espejo: hace una copia exacta del lado izquierdo al lado derecho haciendo las modificaciones necesarias para que el lado derecho sea exactamente igual que el izquierdo, elimina archivos del lado derecho eliminados del lado izquierdo.
* Actualizar: copia archivos nuevos y actualiza los modificados del lado izquierdo al lado derecho, no resolviendo los conflictos en caso de haber modificaciones en un archivo tanto del lado izquierdo y derecho, no elimina del lado derecho los archivos eliminados del lado izquierdo.
* Personalizada: para cada categoría de modificación podemos seleccionar la acción que queremos que realice FreeFileSync.

En cada opción de sincronización es posible personalizar las acciones a realizar según la categoría de modificación, que son:

* Copiar nuevo elemento a la derecha o a la izquierda.
* Eliminar elemento izquierdo o derecho.
* No hacer nada.
* Actualizar elemento izquierdo o derecho.

Las categorías de modificación son las siguientes:

* El elemento solo existe en el lado izquierdo.
* El lado izquierdo es más reciente.
* Hay un conflicto.
* El lado derecho es más reciente.
* El elemento solo existe en el lado derecho.

Antes de hacer la copia de seguridad podemos comparar el origen y destino viendo que diferencias hay entre los archivos y obteniendo unas estadísticas según  el número de archivos para los que es necesario una acción  de sincronización. También veremos que archivos se añaden, se eliminan o se modifican. FreeFileSync únicamente realiza la modificaciones imprescindibles para mantener la sincronización por lo que realizando la copia de seguridad con cierta frecuencia las acciones necesarias no serán muchas y se realizará rápidamente dependiendo del tamaño de los archivos.

Las opciones que ofrece son bastantes y seguro que cubren la mayoría de casos de uso que un usuario a nivel personal tiene. En el [manual de usuario de FreeFileSync](https://freefilesync.org/manual.php) están comentadas más detalladamente sus opciones.

* [Opciones de comparación](https://freefilesync.org/manual.php?topic=comparison-settings). Donde podremos indicar como detectar cambios entre los archivos, por fecha y tamaño, por contenido o por tamaño del archivo.
* [Opciones de filtrado](https://freefilesync.org/manual.php?topic=exclude-items). Con expresiones regulares indicamos los archivos a incluir y excluir en la sincronización, por ejemplo, _*.jpg_ para fotos, _*.docx_ para documentos de Microsoft Word, ... normalmente querremos seleccionar por extensión del archivo. También podremos excluir según el tamaño mínimo, máximo.
* [Opciones de sinconización](https://freefilesync.org/manual.php?topic=synchronization-settings). Es dónde seleccionaremos el tipo de sincronización y las acciones a realizar según el tipo de categoría de modificación. Para las eliminaciones podremos elegir entre eliminar de forma permanente, enviar a la papelera de reciclaje o versionar los archivos. También elegiremos la posibilidad de que nos muestre una ventana con los conflictos que se produzcan. Por último, la acción una vez terminada la sincronización en caso de tomar mucho tiempo que nos servirá para desatender la sincronización, por ejemplo, apagar el equipo al terminar.
* [Notas y trucos](https://freefilesync.org/manual.php?topic=tips-and-tricks).

{{< image
    gallery="true"
    image1="image:freefilesync-comparacion.webp" optionsthumb1="300x200" title1="Opciones de comparación de FreeFileSync"
    image2="image:freefilesync-filtro.webp" optionsthumb2="300x200" title2="Opciones de filtrado de FreeFileSync" >}}
{{< image
    gallery="true"
    image1="image:freefilesync-sincronizacion.webp" optionsthumb1="300x200" title1="Opciones de sincronización de FreeFileSync" >}}

La ventaja realizar un copia de los archivos directamente sin comprimir o cifrar es que no necesitaremos ningún programa adicional para acceder a ellos en caso de necesidad. Otros programas de copias de seguridad son capaces de guardar los datos cifrados y comprimidos pero para recuperar los datos necesitaremos esos programas. Dependiendo de los requerimientos para la copia de seguridad podemos [cifrar la unidad USB externa con BitLocker en Windows](http://windows.microsoft.com/es-es/windows/protect-files-bitlocker-drive-encryption#1TC=windows-8) y [cifrar la unidad USB externa con dm-crypt y LUKS en GNU/Linux][blogbitix-128].

{{< reference >}}
* [Cifrar unidad USB completamente con dm-crypt y LUKS en GNU/Linux][blogbitix-128]
* [Copia de seguridad con rsync][elblogdepicodev-156]
* [Cifrar archivos con EncFS en GNU/Linux][blogbitix-126]
{{< /reference >}}

{{% /post %}}
