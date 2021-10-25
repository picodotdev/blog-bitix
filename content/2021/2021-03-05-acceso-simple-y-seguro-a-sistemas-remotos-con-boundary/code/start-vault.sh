$ vault server -dev -dev-listen-address "0.0.0.0:8200"
==> Vault server configuration:

             Api Address: http://0.0.0.0:8200
                     Cgo: disabled
         Cluster Address: https://0.0.0.0:8201
              Go Version: go1.15.8
              Listener 1: tcp (addr: "0.0.0.0:8200", cluster address: "0.0.0.0:8201", max_request_duration: "1m30s", max_request_size: "33554432", tls: "disabled")
               Log Level: info
                   Mlock: supported: true, enabled: false
           Recovery Mode: false
                 Storage: inmem
                 Version: Vault v1.5.7
             Version Sha: 81d55e35dbbe844a6feb4f52a9a3d072c052d228+CHANGES

WARNING! dev mode is enabled! In this mode, Vault runs entirely in-memory
and starts unsealed with a single unseal key. The root token is already
authenticated to the CLI, so you can immediately begin using Vault.

You may need to set the following environment variable:

    $ export VAULT_ADDR='http://0.0.0.0:8200'

The unseal key and root token are displayed below in case you want to
seal/unseal the Vault or re-authenticate.

Unseal Key: oqt+hwz0gOD9nxz8gc9C79B0M6mDx7rsbJelYZDsg5I=
Root Token: s.PAPTxv16g0o9amLr82NYcxBT

Development mode should NOT be used in production installations!

==> Vault server started! Log data will stream in below:

2021-03-05T15:07:47.964+0100 [INFO]  proxy environment: http_proxy= https_proxy= no_proxy=
2021-03-05T15:07:47.964+0100 [WARN]  no `api_addr` value specified in config or in VAULT_API_ADDR; falling back to detection if possible, but this value should be manually set
