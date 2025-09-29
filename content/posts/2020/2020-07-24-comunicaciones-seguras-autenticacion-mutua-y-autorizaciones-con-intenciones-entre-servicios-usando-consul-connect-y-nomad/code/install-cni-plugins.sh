$ curl -L -o cni-plugins.tgz https://github.com/containernetworking/plugins/releases/download/v0.8.6/cni-plugins-linux-amd64-v0.8.6.tgz
$ sudo mkdir -p /opt/cni/bin
$ sudo tar -C /opt/cni/bin -xzf cni-plugins.tgz