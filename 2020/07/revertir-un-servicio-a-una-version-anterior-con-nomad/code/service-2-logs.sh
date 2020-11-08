$ nomad job status service
...
Allocations
ID        Node ID   Task Group  Version  Desired  Status    Created     Modified
f7bd853e  5721462c  service     1        stop     running   38m33s ago  34m57s ago
b51101cc  5721462c  service     0        stop     complete  40m6s ago   38m31s ago
$ nomad alloc logs f7bd853e
Version 2 (error)
Version 2 (error)
Version 2 (error)
Version 2 (error)
Version 2 (error)
Version 2 (error)
Version 2 (error)
Version 2 (error)
...