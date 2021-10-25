$ vault write -f transit/keys/app/rotate
$ vault write transit/encrypt/app plaintext=$(base64 <<< "4111 1111 1111 1111")
Key            Value
---            -----
ciphertext     vault:v2:aVuvvlcyPX5J4sPYXFWHL53sIDx3HP9oBBTNjhY6NyshliMZzw8g8Ir9+BRpI8FJ
key_version    2

$ vault write transit/encrypt/app plaintext=$(base64 <<< "4111 1111 1111 1111")
Key            Value
---            -----
ciphertext     vault:v2:8coGwMB2WZsWb8Ogm4Fi8zGgzJq45V+VgYXYaMLHoVSCv9IJXs7Js6Jp5bqDGTUV
key_version    2

$ vault write transit/decrypt/app ciphertext="vault:v2:8coGwMB2WZsWb8Ogm4Fi8zGgzJq45V+VgYXYaMLHoVSCv9IJXs7Js6Jp5bqDGTUV"
Key          Value
---          -----
plaintext    NDExMSAxMTExIDExMTEgMTExMQo=

$ base64 --decode <<< "NDExMSAxMTExIDExMTEgMTExMQo="
4111 1111 1111 1111