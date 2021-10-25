$ nomad job status service
...
Allocations
ID        Node ID   Task Group  Version  Desired  Status    Created     Modified
fc4fd574  5721462c  service     2        run      running   34m58s ago  34m43s ago
f7bd853e  5721462c  service     1        stop     complete  38m33s ago  34m57s ago
b51101cc  5721462c  service     0        stop     complete  40m6s ago   38m31s ago
$ nomad alloc logs fc4fd574
Version 1
Version 1
Version 1
Version 1
Version 1
Version 1
Version 1
Version 1
...