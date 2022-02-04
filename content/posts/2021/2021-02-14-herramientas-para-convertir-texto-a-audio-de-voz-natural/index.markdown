---
pid: 555
type: "post"
title: "Herramientas para convertir texto a audio de voz natural"
url: "/2021/02/herramientas-para-convertir-texto-a-audio-de-voz-natural/"
date: 2021-02-14T00:30:00+01:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imagePost: "image:mozilla-tts.png"
tags: ["gnu-linux", "planeta-codigo"]
summary: "Los archivos de audio son difíciles de editar, un cambio requiere volverlos a grabar de forma parcial o de forma completa lo que requiere mucho tiempo. Hay herramientas que permiten convertir texto a audio de voz sintetizada, el resultado de algunas herramientas es suficientemente bueno como para no distinguirse de una voz humana natural."
---

{{% post %}}

Al grabar un audio de voz se crea un archivo binario con la grabación en formato _wav_ o comprimido con _mp3_. Estos formatos de audio son archivos poco editables, no se puede sustituir una palabra, frase o realizar una corrección. La única edición que permite un audio de voz es cortar un trozo o añadir un trozo de audio nuevo. Sin embargo, la edición no es perfecta y el tono de la voz de la parte editada puede variar con la parte anterior y posterior. Además, grabar un audio de voz requiere el tiempo para realizar la grabación.

Hay herramientas que usando un sintetizador de voz permiten transformar un texto en un audio, dando solución a dos de los problemas de la grabación de voz a partir de texto. Al convertir un texto a voz es más fácil la edición ya que basta simplemente cambiar el texto y generar de nuevo el audio y es más rápido ya que la generación del audio es más rápido que grabarlo en tiempo real. Algunos sintetizadores y herramientas además permiten seleccionar el idioma de la voz o ser una voz masculina o femenina.

Una de estas herramientas es [TTS: Text-to-Speech](https://github.com/mozilla/TTS), un proyecto de [Mozilla][mozilla] y las ofrecidas por Amazon, Google y Microsoft. Esta y otras herramientas alternativas de conversión de texto a voz producen audios de voz bastante fieles a la voz humana natural. Para ayudar en la interpretación del texto se enriquece con un lenguaje de marcado que indica a la computadora como entonar e interpretar el texto.

En la actualidad hay herramientas de conversión de texto a voz natural que producen resultados difícilmente indisinguibles de si la voz ha sido producida por un humano por una computadora. Otra tarea para la que hay herramientas que producen buenos resultados es [reconocimiento de texto en imágenes con OCR][blogbitix-567]. Estas son tareas aparentemente simples pero significativamente complejas para una computadora que a pesar de su rapidez y capacidad enorme de cálculo no posee la inteligencia humana.

{{< tableofcontents >}}

### Convertir texto a voz natural

#### Herramientas web

Hay algunas páginas web que ofrecen el servicio de conversión de texto a audio sin necesidad de software adicional a un navegador web. Algunas con limitaciones de número de caracteres pero suficiente para un uso básico. También algunas permiten descargar el archivo de audio, en caso de que no ofrezcan la descargar se puede reproducir y capturar con el [reproductor multimedia VLC][vlc] en la opción _Captura de audio > Monitor de audio interno (HDMI) > Guardar_.

* [TextToSpeechFree](https://www.texttospeechfree.com/)
* [Free TTS](https://freetts.com/)
* [Free online Text To Speech](http://fromtexttospeech.com/)

#### Servicios de conversión

Para un uso más profesional y avanzado hay algunas aplicaciones para [Windows][windows] y entre los muchos servicios que ofrecen [Amazon AWS][amazon-ec2], [Google][google] y [Microsoft][microsoft] para hacer la operación de convertir audio a texto que realmente producen unos resultados muy buenos con una voz natural difícil de distinguir de una real.

Aplicaciones.

* [Any Text to Voice](https://www.microsoft.com/en-us/p/any-text-to-voice-convert-text-to-speech-text-to-audio-mp3-for-free/9n92n3shd1mv?activetab=pivot:overviewtab#)
* [Convert Text to Speech](https://www.microsoft.com/en-us/p/convert-text-to-speech/9wzdncrddlsc?activetab=pivot:overviewtab#)

Servicios.

* [Amazon Polly][amazon-polly]
* [Amazon Transcribe][amazon-transcribe]
* [Google Text‑to‑Speech][google-tts]
* [Microsoft Text to Speech][microsoft-tts]

#### Mozilla TTS

La herramienta TTS es capaz de generar un audio de voz sintética a partir de un texto de bastante buena calidad. El audio de voz sintética no es perfecta ni tiene todos los matices en tono, velocidad de habla, contiene algunos defectos y otros matices de las voces humanas pero es aceptablemente bueno.

TTS se ofrece como una imagen de [Docker][docker] que permite una instalación y uso sencillo de la herramienta. Previamente hay que [instalar Docker][blogbitix-50], una vez instalado descargar la [imagen del contenedor TTS](https://github.com/synesthesiam/docker-mozillatts) e iniciar un contenedor de la herramienta TTS.

Hay varias voces disponibles específicas para cada idioma, la del español se indica con _es_, la de inglés con _en_

{{< code file="docker.sh" language="bash" options="" >}}

Iniciado el servidor de TTS este ofrece dos interfaces una interfaz web en la dirección _http:\/\/localhost:5002/_ y una interfaz de linea de comandos con una API REST. Tanto en la interfaz web como en la interfaz de linea de comandos se ha de indicar el texto que convertir a audio. Esto genera como resultado un archivo de audio en formato _wav_. Para que ocupe menos es posible convertirlo a _mp3_.

{{< code file="curl.sh" language="bash" options="" >}}

{{< image
    gallery="true"
    image1="image:mozilla-tts.png" optionsthumb1="300x200" title1="Interfaz web de Mozilla TTS"
    caption="Interfaz web de Mozilla TTS" >}}

#### espeak

_espeak_ y _espeak-ng_ son dos herramientas de linea de comandos que aunque producen una voz sintetizada con un tono fácilmente reconocible como generado por computadora, robótico y metálico, son otras opciones conocidas.

{{< code file="espeak.sh" language="bash" options="" >}}

### Lenguaje de marcado de síntesis de voz (SSML)

Dependiendo de la herramienta de conversión de texto a voz la calidad de las voces de resultado son más fieles a la voz humana o son fácilmente reconocibles como haber sido generadas por una computadora. Algunas de estas herramientas ya producen conversiones de texto a voz que imitan con fidelidad las voces humanas haciendo uso de redes neuronales entrenadas. Un aspecto por el que son todavía fácilmente identificables es por la entonación y personalidad que los humanos imprimimos en el habla, sin ayuda las conversiones sintéticas de texto a voz son monótonas.

El [lenguaje de marcado de síntesis de voz](https://cloud.google.com/text-to-speech/docs/ssml) o SSML es un lenguaje que sirve de ayuda a la computadora para interpretar y dar entonación al texto a convertir a voz. Es un lenguaje similar al HTML, con etiquetas atributos y valores, utilizado en las páginas web pero con el propósito de la conversión a voz.

Hay aplicaciones que permite la edición del texto para añadirle el lenguaje de marcado SSML.

* [getwoord ssml-editor](https://www.getwoord.com/ssml-editor)
* [SSML Editor](https://www.ssml-editor.com/)
* [SSML Editor](https://www.amazon.com/-/es/dp/B07L64KVNR) para Alexa, [SSML WYSIWYG editor y probador](https://topvoiceapps.com/ssml)

### Resultado y ejemplo de conversión de texto a voz

El texto que he utilizado para hacer pruebas ha sido el de la descripción de mi blog. Con los siguientes resultados.

{{< code file="text.txt" language="plain" options="" >}}

Utilizando el servicio Microsoft Text to Speech.

{{< audio src="microsoft-alvaro.mp3" type="audio/mpeg" >}}
{{< audio src="microsoft-elvira.mp3" type="audio/mpeg" >}}

Utilizando el servicio Free TTS.

{{< audio src="freetts-enrique.mp3" type="audio/mpeg" >}}
{{< audio src="freetts-lucia.mp3" type="audio/mpeg" >}}

Utilizando Mozilla TTS.

{{< audio src="mozilla-tts.mp3" type="audio/mpeg" >}}

Utilizando espeak.

{{< audio src="espeak.mp3" type="audio/mpeg" >}}

### Convertir el audio en formato _wav_ a _mp3_

En el artículo cómo [Cambiar el formato de archivos de música o audio en GNU/Linux][blogbitix-134] comentaba cómo convertir diferentes tipos de audio entre formatos con la herramienta [FFmpeg][ffmpeg].

El comando para convertir un _wav_ a los formatos _mp3_ y _ogg_ comprimidos que ocupan significativamente menos sin pérdida de calidad perceptible son los siguientes. En función del formato

{{< code file="convert.sh" language="bash" options="" >}}

{{< reference >}}
* [From Text to Speech Alternatives](https://alternativeto.net/software/from-text-to-speech/)
* [Comparison of speech synthesizers](https://en.wikipedia.org/wiki/Comparison_of_speech_synthesizers)
{{< /reference >}}

{{% /post %}}
