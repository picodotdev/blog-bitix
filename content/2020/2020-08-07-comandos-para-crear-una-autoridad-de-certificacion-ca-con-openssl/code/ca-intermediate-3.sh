$ openssl ca -config openssl.conf \
      -extensions v3_intermediate_ca -days 3650 -batch -notext -md sha256 \
      -in intermediate/csr/intermediate.csr.pem \
      -out intermediate/certs/intermediate.cert.pem