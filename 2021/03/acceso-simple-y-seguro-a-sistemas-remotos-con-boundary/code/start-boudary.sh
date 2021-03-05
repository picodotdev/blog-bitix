$ boundary dev
==> Boundary server configuration:

        [Controller] AEAD Key Bytes: A7NiMQ0fNlBiCpFxMxg21k8TAdSNcui/okJGxBg6eZs=
          [Recovery] AEAD Key Bytes: H4U5vaCh1ZmgrRJXhlljvOqkLDzU9ZNy4Cn9Z4+a7Bo=
       [Worker-Auth] AEAD Key Bytes: rWZi4UxADHAlsqbhemdM6YIIyFG9+AuqIcCbAOZILtQ=
               [Recovery] AEAD Type: aes-gcm
                   [Root] AEAD Type: aes-gcm
            [Worker-Auth] AEAD Type: aes-gcm
                                Cgo: disabled
     Controller Public Cluster Addr: 127.0.0.1:9201
             Dev Database Container: confident_mcnulty
                   Dev Database Url: postgres://postgres:password@localhost:49153/boundary?sslmode=disable
         Generated Admin Login Name: admin
           Generated Admin Password: password
           Generated Auth Method Id: ampw_1234567890
          Generated Host Catalog Id: hcst_1234567890
                  Generated Host Id: hst_1234567890
              Generated Host Set Id: hsst_1234567890
             Generated Org Scope Id: o_1234567890
         Generated Project Scope Id: p_1234567890
                Generated Target Id: ttcp_1234567890
  Generated Unprivileged Login Name: user
    Generated Unprivileged Password: password
                         Listener 1: tcp (addr: "127.0.0.1:9200", cors_allowed_headers: "[]", cors_allowed_origins: "[*]", cors_enabled: "true", max_request_duration: "1m30s", purpose: "api")
                         Listener 2: tcp (addr: "127.0.0.1:9201", max_request_duration: "1m30s", purpose: "cluster")
                         Listener 3: tcp (addr: "127.0.0.1:9202", max_request_duration: "1m30s", purpose: "proxy")
                          Log Level: info
                              Mlock: supported: true, enabled: false
                            Version: Boundary v0.1.7
                        Version Sha: bc565922fbd3a18c9f6a22cd2e80a93df0d7cd45
           Worker Public Proxy Addr: 127.0.0.1:9202

==> Boundary server started! Log data will stream in below:

2021-03-05T15:08:05.051+0100 [INFO]  worker: connected to controller: address=127.0.0.1:9201
2021-03-05T15:08:05.054+0100 [INFO]  controller: worker successfully authed: name=dev-worker
2021-03-05T15:08:05.059+0100 [WARN]  worker: got no controller addresses from controller; possibly prior to first status save, not persisting