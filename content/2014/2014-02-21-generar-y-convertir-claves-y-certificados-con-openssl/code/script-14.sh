$ openssl s_client -showcerts -connect duckduckgo.com:443
CONNECTED(00000003)
depth=2 C = US, O = DigiCert Inc, OU = www.digicert.com, CN = DigiCert Global Root CA
verify return:1
depth=1 C = US, O = DigiCert Inc, CN = DigiCert SHA2 Secure Server CA
verify return:1
depth=0 C = US, ST = Pennsylvania, L = Paoli, O = "Duck Duck Go, Inc.", CN = *.duckduckgo.com
verify return:1
---
Certificate chain
 0 s:C = US, ST = Pennsylvania, L = Paoli, O = "Duck Duck Go, Inc.", CN = *.duckduckgo.com
   i:C = US, O = DigiCert Inc, CN = DigiCert SHA2 Secure Server CA
-----BEGIN CERTIFICATE-----
MIIGNTCCBR2gAwIBAgIQAx42ydSKbld6na9pgqdX4zANBgkqhkiG9w0BAQsFADBN
MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMScwJQYDVQQDEx5E
...
-----END CERTIFICATE-----
 1 s:C = US, O = DigiCert Inc, CN = DigiCert SHA2 Secure Server CA
   i:C = US, O = DigiCert Inc, OU = www.digicert.com, CN = DigiCert Global Root CA
-----BEGIN CERTIFICATE-----
MIIElDCCA3ygAwIBAgIQAf2j627KdciIQ4tyS8+8kTANBgkqhkiG9w0BAQsFADBh
MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3
...
-----END CERTIFICATE-----
---
Server certificate
subject=C = US, ST = Pennsylvania, L = Paoli, O = "Duck Duck Go, Inc.", CN = *.duckduckgo.com

issuer=C = US, O = DigiCert Inc, CN = DigiCert SHA2 Secure Server CA

---
...