$ openssl req -config intermediate/openssl.conf -new -sha256 \
      -key intermediate/private/intermediate.key.pem \
      -subj "/C=ES/ST=Spain/L=Madrid/O=Acme S.A./CN=Acme S.A. (Intermediate CA)" \
      -out intermediate/csr/intermediate.csr.pem