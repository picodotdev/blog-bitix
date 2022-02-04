openssl ca -config intermediate/openssl.conf \
    -revoke intermediate/newcerts/1000.pem -crl_reason superseded