$ nomad job run echo-bash.nomad
Job registration successful
Approximate next launch time: 2021-09-23T17:44:00Z (4s from now)

$ nomad job status echo-bash
ID                   = echo-bash
Name                 = echo-bash
Submit Date          = 2021-09-23T19:43:56+02:00
Type                 = batch
Priority             = 50
Datacenters          = dc1
Namespace            = default
Status               = running
Periodic             = true
Parameterized        = false
Next Periodic Launch = 2021-09-23T17:45:00Z (31s from now)

Children Job Summary
Pending  Running  Dead
1        0        1

Previously Launched Jobs
ID                             Status
echo-bash/periodic-1632419040  dead

$ nomad job status echo-bash/periodic-1632419040
ID            = echo-bash/periodic-1632419040
Name          = echo-bash/periodic-1632419040
Submit Date   = 2021-09-23T19:44:00+02:00
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
ID        Node ID   Task Group  Version  Desired  Status    Created  Modified
db6b9f1e  26b275d9  echo        0        run      complete  55s ago  55s ago

$ nomad alloc logs db6b9f1e
Hello Word! (bash)