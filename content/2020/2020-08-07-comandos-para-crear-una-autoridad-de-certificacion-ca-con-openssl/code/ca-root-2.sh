$ openssl req -config openssl.conf \
      -key private/ca.key.pem \
      -new -x509 -days 7300 -sha256 -extensions v3_ca \
      -subj "/C=ES/ST=Spain/L=Madrid/O=Acme S.A./CN=Acme S.A (CA)" \
      -out certs/ca.cert.pem