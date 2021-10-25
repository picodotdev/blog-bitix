$ vault write transit/encrypt/app plaintext=$(base64 <<< "4111 1111 1111 1111")
Key            Value
---            -----
ciphertext     vault:v1:kYVkH1OxTEai1zjO+uQ9FKiHanlbaQ2bF5b5GYwUiEef5d31ProquZ5grVJfDWrc
key_version    1

$ vault write transit/decrypt/app ciphertext="vault:v1:kYVkH1OxTEai1zjO+uQ9FKiHanlbaQ2bF5b5GYwUiEef5d31ProquZ5grVJfDWrc"
Key          Value
---          -----
plaintext    NDExMSAxMTExIDExMTEgMTExMQo=

$ base64 --decode <<< "NDExMSAxMTExIDExMTEgMTExMQo="
4111 1111 1111 1111