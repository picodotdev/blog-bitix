$ ffmpeg -i voice.wav -codec:a libmp3lame voice.mp3
$ ffmpeg -i voice.wav -codec:a libvorbis voice.ogg
$ ffmpeg -i voice.mp3 -codec:a libvorbis voice.ogg
$ ffmpeg -i voice.mp3 -codec:a libmp3lame -b:a 128k voice.mp3