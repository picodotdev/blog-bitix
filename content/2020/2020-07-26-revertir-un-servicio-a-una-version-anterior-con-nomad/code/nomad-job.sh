$ nomad job status
ID       Type     Priority  Status   Submit Date
service  service  50        running  2020-07-26T10:07:59+02:00
$ nomad job deployments service
ID        Job ID   Job Version  Status      Description
9d44bbcc  service  1            successful  Deployment completed successfully
33154ac5  service  0            successful  Deployment completed successfully
$ nomad job history service
Version     = 1
Stable      = true
Submit Date = 2020-07-26T10:07:59+02:00

Version     = 0
Stable      = true
Submit Date = 2020-07-26T10:06:26+02:00