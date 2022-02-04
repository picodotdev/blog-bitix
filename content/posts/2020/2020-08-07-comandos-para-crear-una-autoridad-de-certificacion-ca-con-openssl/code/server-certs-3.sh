$ openssl x509 -noout -text \
      -in certs/consul-server.cert.pem
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number: 4096 (0x1000)
        Signature Algorithm: sha256WithRSAEncryption
        Issuer: C = ES, ST = Spain, O = Acme S.A., CN = Acme S.A. (Intermediate CA)
        Validity
            Not Before: Aug  6 22:39:48 2020 GMT
            Not After : Aug  5 22:39:48 2025 GMT
        Subject: C = ES, ST = Spain, L = Madrid, O = Acme S.A., CN = Consul Server
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
                RSA Public-Key: (8192 bit)
                Modulus:
                    00:ef:5c:77:b4:e7:58:08:3f:d9:76:2c:67:52:f0:
                    37:c2:19:e6:e8:32:0e:b4:39:ea:77:b5:bb:e8:9c:
                    d0:75:62:90:d2:9d:53:bc:55:b1:4f:c8:09:69:41:
                    b1:8a:b4:39:5e:ba:8c:c5:b0:40:82:8c:4b:cb:f4:
                    b3:97:24:8d:3e:f6:81:24:80:7a:14:da:15:dc:8f:
                    ...
                Exponent: 65537 (0x10001)
        X509v3 extensions:
            X509v3 Basic Constraints: 
                CA:FALSE
            Netscape Cert Type: 
                SSL Client, SSL Server
            Netscape Comment: 
                OpenSSL Generated Server Certificate
            X509v3 Subject Key Identifier: 
                40:A7:07:BE:51:F7:20:E6:6B:5C:0F:22:93:13:FF:07:9A:11:E5:E8
            X509v3 Authority Key Identifier: 
                keyid:0F:13:28:6E:13:8F:9D:25:7A:89:07:38:CA:05:F8:CA:49:47:57:62
                DirName:/C=ES/ST=Spain/L=Madrid/O=Acme S.A./CN=Acme S.A (CA)
                serial:10:00

            X509v3 Key Usage: critical
                Digital Signature, Non Repudiation, Key Encipherment
            X509v3 Extended Key Usage: 
                TLS Web Client Authentication, TLS Web Server Authentication
            X509v3 Subject Alternative Name: 
                DNS:server.dc1.consul, DNS:consul.service.consul, DNS:localhost, DNS:consul.192.168.33.10.xip.io, DNS:consul.192.168.33.11.xip.io, DNS:consul.192.168.33.12.xip.io, DNS:consul.192.168.33.13.xip.io, DNS:consul.192.168.33.14.xip.io, IP Address:127.0.0.1, IP Address:192.168.33.10, IP Address:192.168.33.11, IP Address:192.168.33.12, IP Address:192.168.33.13, IP Address:192.168.33.14
    Signature Algorithm: sha256WithRSAEncryption
         12:15:c8:74:8d:e4:5b:8a:13:2c:ae:42:1a:ca:11:aa:c0:89:
         de:29:25:6c:4f:b4:52:24:1a:cd:25:18:55:dd:6d:9d:ea:12:
         d9:f1:5d:f5:46:75:39:43:b9:6d:c0:a3:49:a9:80:63:ba:ea:
         bc:24:3b:49:e8:c3:9b:60:51:fc:bb:52:a4:61:18:ac:fe:58:
         3c:61:2c:af:0d:83:fa:d9:e5:ad:fa:73:10:9c:5b:f1:72:13:
         ....