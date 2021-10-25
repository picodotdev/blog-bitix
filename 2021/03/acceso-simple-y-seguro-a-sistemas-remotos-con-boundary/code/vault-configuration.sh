$ export VAULT_ADDR='http://127.0.0.1:8200'
$ vault secrets enable ssh
$ vault write ssh/roles/otp_ssh \
    key_type=otp \
    default_user=pi \
    cidr_list=192.168.1.0/24