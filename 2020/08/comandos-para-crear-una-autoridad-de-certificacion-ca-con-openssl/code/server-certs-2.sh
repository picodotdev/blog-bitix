$ openssl verify -CAfile $CA_PATH/intermediate/certs/ca-chain.cert.pem \
      certs/consul-server.cert.pem