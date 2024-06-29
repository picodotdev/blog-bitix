---
pid: 701
type: "post"
title: "Eliminar todo el historial de la red social X"
url: "/2024/06/eliminar-todo-el-historial-de-la-red-social-x/"
date: 2024-06-30T01:00:00+02:00
language: "es"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "logotype:x.svg"
imagePost: "logotype:x.svg"
tags: ["blog", "seguridad"]
summary: "Ni Twitter antes ni X ahora permite eliminar los _post_ publicados en esta red social, ni siquiera como una opción de pago. Al igual que otras redes sociales se publica y se puede obtener toda o mucha de la información que una persona ha publicado. Hay un script de JavaScript que con Chrome permite eliminar todos los _post_ publicados en X."
---

{{% post %}}

{{< logotype image1="x.svg" >}}

Me hice una cuenta de Twitter en enero de 2011 hace ya más de trece años, en esos trece años publiqué unos 5K _tweets_ o _posts_ entre propios y _retweets_.

Algunos de esos _post_ para publicar en [X][x] los artículos del propio blog. En buena medida utilizaba X como un diario, ahora que utilizo [Obsidian][obsidian] en el que puedo escribir para mí mismo he llegado al momento en que publicar en X como lo hacía ha perdido interés para mi además de los motivos de privacidad.

* [Obsidian, una herramienta para almacenar conocimiento][blogbitix-695]

## Sobre X

En ciertos momentos sí que encuentro contenido que me resulta interesante en X, aún así creo que es el menos y para ese contenido que me resulta interesante he de descartar la mayoría del otro contenido que X muestra en mi linea de tiempo incluso para el contenido que sigo.

Una vez se publica contenido en X el contenido es más de X que de uno mismo y se pierde el control de ello, incluso editar un _post_ para corregir alguna errata es una opción de pago. Por otra parte tener una cuenta con los mensajes privados sólo accesibles para las personas a las que uno sigue puede ser demasiado público.

Solía decir que me gustaba [LinkedIn][linkedin] y eso que no tenía cuenta, pues con X se puede aplicar un poco lo mismo y de X si tengo cuenta.

## Privacidad

Por otro lado hay cierto contenido personal que he estado publicando que ya no me apetece compartir en una red social, demasiada información personal, prefiero tener más control de ese contenido compartido y aunque los blogs han sido sometidos los sigo prefiriendo por encima de las redes sociales y los _youtubers_. En un blog realmente tengo más propiedad sobre mi contenido, por lo menos hasta que sin permiso algún rastreador de IA lo absorba.

He decidido dejar de publicar en X y si publico algo más personal lo haré en el blog, otras cosas las compartiré directamente con las personas que quiero en persona y el resto las escribiré en mi diario de Obsidian. Por el momento ya he eliminado todos los _post_ que he podido de X y los que no he podido trataré de hacerlo.

X no ofrece ninguna herramienta para eliminar el contenido publicado que no sea hacerlo manualmente que es una tarea titánica incluso con solo 5K _posts_, la alternativa es eliminar la cuenta por completo.

## Script para eliminar todos los _post_ de X

Así que me he dispuesto a eliminar todo el contenido de mi X ya que este como comentaba no ofrece ninguna opción. Hay varias opciones algunas en páginas web como TweetDelete, TweetEraser y otras mencionadas en algunos artículos limitadas a únicamente eliminar los últimos 3000 _post_, para los demás hay que pagar por el servicio y se hace a través del archivo de datos que almacena X de la cuenta.

En cualquier caso antes de empezar a eliminar los datos he utilizado la opción de _Descargar un archivo con tus datos_ de mi cuenta por si en un futuro quisiera consultarlo. En este archivo zip descargado al descomprimirlo hay uno de nombre _tweets.js_ que contiene todos los _posts_ publicados y en otras carpetas todo el contenido multimedia.

Mejor que las opciones anteriores es el [script DeleteTweets](https://github.com/Lyfhael/DeleteTweets/blob/main/main.js) que funciona instalándolo en el navegador [Chrome][google-chrome] proporcionado los datos de las credenciales de X y unas opciones de configuración, entre las opciones de configuración está el rango de fechas de los _post_ a eliminar y cargando el archivo de _posts_ del archivo de datos realiza la eliminación de los _posts_. En el [README](https://github.com/Lyfhael/DeleteTweets/tree/main) del _script_ junto con algún vídeo está documentado como usarlo.

{{% /post %}}