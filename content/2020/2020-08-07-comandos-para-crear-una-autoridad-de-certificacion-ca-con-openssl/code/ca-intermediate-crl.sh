openssl ca -config intermediate/openssl.conf \
    -gencrl -out intermediate/crl/intermediate.crl