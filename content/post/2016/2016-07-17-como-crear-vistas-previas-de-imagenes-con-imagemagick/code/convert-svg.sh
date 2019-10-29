$ for f in *.svg; do convert -resize 750x750 "$f" "${f%.*}.jpg"; done;
$ for f in *.svg; do convert -resize 750x750 "$f" "${f%.*}.png"; done;