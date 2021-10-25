# Decodificación de base64
$ echo "dXNlcjpwYXNzd29yZA==" | base64 --decode
user:password

# Codificación en base64
$ echo "user:password" | base64
dXNlcjpwYXNzd29yZAo=