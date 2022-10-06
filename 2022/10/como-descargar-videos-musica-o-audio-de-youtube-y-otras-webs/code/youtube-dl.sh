$ youtube-dl -f 'bestvideo,bestaudio' -o '%(title)s.f%(format_id)s.%(ext)s' [URL]
$ youtube-dl -f 'bestvideo' -o '%(title)s.f%(format_id)s.%(ext)s' "https://www.youtube.com/watch?v=NJe4rEyfQ3g"
$ youtube-dl -f 'bestaudio' -o '%(title)s.f%(format_id)s.%(ext)s' "https://www.youtube.com/watch?v=NJe4rEyfQ3g"