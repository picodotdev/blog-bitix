$ consul config write consul.d/config-entries/proxy-defaults.hcl
$ consul config write consul.d/config-entries/ingress-gateway.hcl
$ consul connect envoy -gateway=ingress -service ingress-gateway -admin-bind 127.0.0.1:19002 -address 127.0.0.1:20000