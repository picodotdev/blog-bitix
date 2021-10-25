$ consul members
Node             Address             Status  Type    Build  Protocol  DC   Segment
consul-server-1  192.168.33.10:8301  alive   server  1.8.3  2         dc1  <all>
nomad-agent-1    192.168.33.40:8301  alive   client  1.8.3  2         dc1  <default>
nomad-server-1   192.168.33.30:8301  alive   client  1.8.3  2         dc1  <default>
ui-server-1      192.168.33.50:8301  alive   client  1.8.3  2         dc1  <default>
vault-server-1   192.168.33.20:8301  alive   client  1.8.3  2         dc1  <default>
$ consul catalog services
consul
nginx
nomad
nomad-client
vault
$ nomad server members
Name                   Address        Port  Status  Leader  Protocol  Build   Datacenter  Region
nomad-server-1.global  192.168.33.30  4648  alive   true    2         0.12.2  dc1         global
$ nomad node status
ID        DC   Name           Class   Drain  Eligibility  Status
7469d6e5  dc1  nomad-agent-1  <none>  false  eligible     ready
$ nomad job status
ID     Type     Priority  Status   Submit Date
nginx  service  50        running  2020-08-14T18:25:59+02:00