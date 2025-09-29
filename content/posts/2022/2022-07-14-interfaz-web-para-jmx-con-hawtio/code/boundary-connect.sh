$ boundary authenticate password -auth-method-id=ampw_1234567890 -login-name=admin -password=password
...
Authentication information:
  Account ID:      acctpw_1234567890
  Auth Method ID:  ampw_1234567890
  Expiration Time: Thu, 21 Jul 2022 19:03:46 CEST
  User ID:         u_1234567890
...

at_Gf2PG4GGcQ_s17U6Fz5B2DGh7EBdAuAGaifTLUCV1LSMRHZXL7vme1L6RpcroPW39DSEjAMtJcvEhN6otiod3RSMRKRX7RGUndqACd2jx2i2gHBMr7Rdih8yFF
...
$ export BOUNDARY_TOKEN="at_Gf2PG4GGcQ_s17U6Fz5B2DGh7EBdAuAGaifTLUCV1LSMRHZXL7vme1L6RpcroPW39DSEjAMtJcvEhN6otiod3RSMRKRX7RGUndqACd2jx2i2gHBMr7Rdih8yFF"
$ boundary connect -target-id ttcp_99NRL1z7Fw

Proxy listening information:
  Address:             127.0.0.1
  Connection Limit:    -1
  Expiration:          Fri, 15 Jul 2022 03:08:05 CEST
  Port:                43923
  Protocol:            tcp
  Session ID:          s_jL5oGhBjb2