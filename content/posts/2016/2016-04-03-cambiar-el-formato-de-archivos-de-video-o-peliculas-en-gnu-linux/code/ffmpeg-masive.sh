$ # conversión masiva
$ for f in *.mkv; do ffmpeg -i "$f" -c:v mpeg2video -c:a libmp3lame -b:v 2500K -b:a 192K -vf scale=720x406,setdar=16:9 "${f%.mkv}.mpg"; done;

$ # conversión masiva y recursiva en árbol de carpetas
$ find ./ -name "*.mkv" -exec ffmpeg -i {} -c:v mpeg2video -c:a libmp3lame -b:v 2500K -b:a 192K -vf scale=720x406,setdar=16:9 "${f%.mkv}.mpg"; -exec rename .mkv.mpg .mpg "{}.mpg" \;