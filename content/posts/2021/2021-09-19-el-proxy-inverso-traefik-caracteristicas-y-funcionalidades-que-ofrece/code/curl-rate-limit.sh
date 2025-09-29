$ curl -v http://nginx.127.0.0.1.sslip.io:8080/
*   Trying 127.0.0.1:8090...
* Connected to nginx.127.0.0.1.sslip.io (127.0.0.1) port 8090 (#0)
> GET / HTTP/1.1
> Host: nginx.127.0.0.1.sslip.io:8090
> User-Agent: curl/7.78.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 429 Too Many Requests
< Retry-After: 1
< X-Retry-In: 565.087221ms
< Date: Sun, 19 Sep 2021 12:32:41 GMT
< Content-Length: 17
< Content-Type: text/plain; charset=utf-8
< 
* Connection #0 to host nginx.127.0.0.1.sslip.io left intact