$ vagrant up --provision consul-server-1
$ vagrant up --provision vault-server-1

$ export VAULT_ADDR="https://192.168.33.20:8200"
$ export VAULT_CLIENT_CERT="server-certs/certs/vault-agent.cert.pem"
$ export VAULT_CLIENT_KEY="server-certs/private/vault-agent.key.pem"
$ export VAULT_CACERT="ca/intermediate/certs/ca-chain.cert.pem"

$ vault operator init -key-shares=5 -key-threshold=2
Unseal Key 1: GmSsvWlRtimeVb4ikBYKGJeAWGgkB1h8NpLpGEu0oqTe
Unseal Key 2: zPBru7ivXOhOZmytYHN5gFhusX2kpNR5TOgvxrjxnL+B
Unseal Key 3: CZ4T0oqwLvniuufFimkpPLxSeo6nWAWpHrrJn+utDLlF
Unseal Key 4: aVkAonumA5ryLy7hpWDCG+bWb1RboxnhXfk7tvybmFpx
Unseal Key 5: a5mCVlK4E+wEtH2m7Tc+KMaXBYWVqSRv3HhmFs5yL93A

Initial Root Token: s.ltQXU2wZeWYNSEf946CefIuG

Vault initialized with 5 key shares and a key threshold of 2. Please securely
distribute the key shares printed above. When the Vault is re-sealed,
restarted, or stopped, you must supply at least 2 of these keys to unseal it
before it can start servicing requests.

Vault does not store the generated master key. Without at least 2 key to
reconstruct the master key, Vault will remain permanently sealed!

It is possible to generate new unseal keys, provided you have a quorum of
existing unseal keys shares. See "vault operator rekey" for more information.
$ vault status
Key                Value
---                -----
Seal Type          shamir
Initialized        true
Sealed             true
Total Shares       5
Threshold          2
Unseal Progress    0/2
Unseal Nonce       n/a
Version            1.5.0
HA Enabled         true

$ vault operator unseal GmSsvWlRtimeVb4ikBYKGJeAWGgkB1h8NpLpGEu0oqTe
$ vault operator unseal zPBru7ivXOhOZmytYHN5gFhusX2kpNR5TOgvxrjxnL+B
Key             Value
---             -----
Seal Type       shamir
Initialized     true
Sealed          false
Total Shares    5
Threshold       2
Version         1.5.0
Cluster Name    vault-cluster-28313ce3
Cluster ID      dcb97a2c-520c-2d41-9dc3-979b1cda5423
HA Enabled      true
HA Cluster      https://192.168.33.20:8201
HA Mode         active

$ vault login "s.ltQXU2wZeWYNSEf946CefIuG"
Success! You are now authenticated. The token information displayed below
is already stored in the token helper. You do NOT need to run "vault login"
again. Future Vault requests will automatically use this token.

Key                  Value
---                  -----
token                s.ltQXU2wZeWYNSEf946CefIuG
token_accessor       l2YGtM1V4z3Sv0Gey0l646BX
token_duration       ∞
token_renewable      false
token_policies       ["root"]
identity_policies    []
policies             ["root"]