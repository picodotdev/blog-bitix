$ curl -v --insecure 'https://localhost/'
...
> GET / HTTP/2
> Host: localhost
> user-agent: curl/7.71.1
> accept: */*
> 
...
< HTTP/2 401 
< server: nginx/1.17.10
< date: Sun, 02 Aug 2020 10:38:14 GMT
< content-type: text/html
< content-length: 180
< www-authenticate: Basic realm="Restricted Area"
< 
<html>
<head><title>401 Authorization Required</title></head>
<body>
<center><h1>401 Authorization Required</h1></center>
<hr><center>nginx/1.17.10</center>
</body>
</html>
...

$ curl -v --insecure -H 'Authorization: Basic invalid' 'https://localhost/'
...
> GET / HTTP/2
> Host: localhost
> user-agent: curl/7.71.1
> accept: */*
> authorization: Basic invalid
> 
...
< HTTP/2 401 
< server: nginx/1.17.10
< date: Sun, 02 Aug 2020 10:37:03 GMT
< content-type: text/html
< content-length: 180
< www-authenticate: Basic realm="Restricted Area"
< 
<html>
<head><title>401 Authorization Required</title></head>
<body>
<center><h1>401 Authorization Required</h1></center>
<hr><center>nginx/1.17.10</center>
</body>
</html>
...