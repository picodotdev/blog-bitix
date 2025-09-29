$ vault read auth/approle/role/app/role-id
Key        Value
---        -----
role_id    c0b643d1-9507-dae1-ffbd-6e405e082f1d

$ vault write -f auth/approle/role/app/secret-id
Key                   Value
---                   -----
secret_id             9b1b6fe6-0ee5-4182-c08d-245d20a59351
secret_id_accessor    b9f5af8e-29ee-694c-2751-6f65d5361caf