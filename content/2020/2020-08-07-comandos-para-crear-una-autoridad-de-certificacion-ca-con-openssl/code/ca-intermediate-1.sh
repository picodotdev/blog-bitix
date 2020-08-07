# Intermediate CA

mkdir -p intermediate/certs intermediate/crl intermediate/csr intermediate/newcerts intermediate/private
chmod 700 intermediate/private
touch intermediate/index.txt
echo 1000 > intermediate/serial
echo 1000 > intermediate/crlnumber

openssl genrsa -out intermediate/private/intermediate.key.pem 8192
chmod 400 intermediate/private/intermediate.key.pem