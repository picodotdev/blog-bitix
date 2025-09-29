$ nomad job run echo-busybox.nomad
Job registration successful
Approximate next launch time: 2021-09-23T17:38:00Z (37s from now)

$ nomad job status echo-busybox
ID                   = echo-busybox
Name                 = echo-busybox
Submit Date          = 2021-09-23T19:37:23+02:00
Type                 = batch
Priority             = 50
Datacenters          = dc1
Namespace            = default
Status               = running
Periodic             = true
Parameterized        = false
Next Periodic Launch = 2021-09-23T17:39:00Z (1s from now)

Children Job Summary
Pending  Running  Dead
1        0        1

Previously Launched Jobs
ID                                Status
echo-busybox/periodic-1632418680  dead

$ nomad job status echo-busybox/periodic-1632418680
ID            = echo-busybox/periodic-1632418680
Name          = echo-busybox/periodic-1632418680
Submit Date   = 2021-09-23T19:38:00+02:00
Type          = batch
Priority      = 50
Datacenters   = dc1
Namespace     = default
Status        = dead
Periodic      = false
Parameterized = false

Summary
Task Group  Queued  Starting  Running  Failed  Complete  Lost
echo        0       0         0        0       1         0

Allocations
ID        Node ID   Task Group  Version  Desired  Status    Created    Modified
be8e648a  26b275d9  echo        0        run      complete  1m20s ago  1m16s ago

$ nomad alloc logs be8e648a
Hello Word! (busybox)