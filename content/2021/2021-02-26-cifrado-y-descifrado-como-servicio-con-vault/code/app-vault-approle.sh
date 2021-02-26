$ vault auth enable approle

$ vault policy write app -<<EOF
path "transit/encrypt/app" {
   capabilities = [ "update" ]
}
path "transit/decrypt/app" {
   capabilities = [ "update" ]
}
EOF

$ vault write auth/approle/role/app \
    secret_id_ttl=10m \
    token_num_uses=10 \
    token_ttl=20m \
    token_max_ttl=30m \
    secret_id_num_uses=40 \
    policies=app