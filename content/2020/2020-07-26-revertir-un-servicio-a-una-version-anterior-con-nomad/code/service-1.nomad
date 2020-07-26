job "service" {
  datacenters = ["dc1"]

  group "service" {

    task "service" {
      driver = "docker"

      config {
        image = "busybox:latest"
        command = "ash"
        args = [
          "-c",
          "while true; do echo \"Version 1\"; sleep 1; done"
        ]
      }
    }
  }
}