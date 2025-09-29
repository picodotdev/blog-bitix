$ openssl x509 -text -noout -in localhost.crt
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number:
            49:e5:32:9b:7f:be:bf:a6:90:14:34:5e:24:53:e8:96:51:8d:74:d4
        Signature Algorithm: sha256WithRSAEncryption
        Issuer: C = ES, ST = Spain, CN = localhost
        Validity
            Not Before: Jul 27 10:40:19 2020 GMT
            Not After : Jul 26 10:40:19 2025 GMT
        Subject: C = ES, ST = Spain, CN = localhost
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                RSA Public-Key: (8192 bit)
                Modulus:
                    00:c8:cb:8f:5a:69:93:23:82:24:7e:8a:90:d9:35:
                    a9:aa:8e:fd:18:46:ac:77:19:fd:31:c1:db:e0:8e:
                    ...
                Exponent: 65537 (0x10001)
        X509v3 extensions:
            X509v3 Subject Key Identifier: 
                07:A0:9B:0F:8F:22:9D:AE:CF:F7:46:11:82:B7:7A:0C:43:88:58:8D
            X509v3 Authority Key Identifier: 
                keyid:07:A0:9B:0F:8F:22:9D:AE:CF:F7:46:11:82:B7:7A:0C:43:88:58:8D

            X509v3 Basic Constraints: critical
                CA:TRUE
    Signature Algorithm: sha256WithRSAEncryption
         1b:9d:d1:a5:1a:2d:23:8c:09:8b:08:6c:fb:49:f6:88:e9:51:
         86:ce:19:53:74:ab:90:01:d9:ab:d1:9e:2b:56:ca:7c:a3:53:
         ...