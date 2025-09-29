$ curl -ik http://localhost:8080/api/message?message=Hola
HTTP/1.1 401 
Set-Cookie: JSESSIONID=82E6871E2347D74639BD7F98494030CB; Path=/; HttpOnly
WWW-Authenticate: Bearer
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Fri, 16 Sep 2022 17:02:14 GMT
