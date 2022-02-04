$ curl -v http://localhost:8080/message/1
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /message/1 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Matched-Stub-Id: 9acc8318-dff3-4d18-9522-56861eff0ca3
< Vary: Accept-Encoding, User-Agent
< Transfer-Encoding: chunked
< 
* Connection #0 to host localhost left intact
{"id":1,"text":"Hello World!"}

$ curl -v -X POST http://localhost:8080/message --data '{"id":1,"text":"Hello World!"}'
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /message HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
> Content-Length: 30
> Content-Type: application/x-www-form-urlencoded
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Matched-Stub-Id: 7ef3a405-4435-417d-8579-57497d149f29
< Vary: Accept-Encoding, User-Agent
< Transfer-Encoding: chunked
< 
* Connection #0 to host localhost left intact