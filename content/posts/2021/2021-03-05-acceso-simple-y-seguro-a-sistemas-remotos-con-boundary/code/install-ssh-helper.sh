$ ssh pi@192.168.1.101
$ wget https://releases.hashicorp.com/vault-ssh-helper/0.2.1/vault-ssh-helper_0.2.1_linux_amd64.zip
$ wget https://releases.hashicorp.com/vault-ssh-helper/0.2.1/vault-ssh-helper_0.2.1_linux_arm.zip
$ sudo unzip -q vault-ssh-helper_0.2.1_linux_arm.zip -d /usr/local/bin
$ sudo chmod 0755 /usr/local/bin/vault-ssh-helper
$ sudo chown root:root /usr/local/bin/vault-ssh-helper
$ sudo mkdir /etc/vault-ssh-helper.d/

$ sudo tee /etc/vault-ssh-helper.d/config.hcl <<EOF
vault_addr = "http://192.168.1.4:8200"
tls_skip_verify = false
ssh_mount_point = "ssh"
allowed_roles = "*"
EOF

$ vault-ssh-helper -verify-only -dev -config /etc/vault-ssh-helper.d/config.hcl
==> WARNING: Dev mode is enabled!
[INFO] using SSH mount point: ssh
[INFO] using namespace: us
[INFO] vault-ssh-helper verification successful!