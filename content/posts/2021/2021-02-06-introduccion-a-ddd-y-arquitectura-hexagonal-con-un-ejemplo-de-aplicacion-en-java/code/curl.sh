$ curl -v -X POST http://localhost:8080/event\?startDate\=2021-09-03T10:15:30\&endDate\=2021-12-03T22:00:00
*   Trying ::1:8080...
* Connected to localhost (::1) port 8080 (#0)
> POST /event?startDate=2021-09-03T10:15:30&endDate=2021-12-03T22:00:00 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Length: 0
< Date: Sun, 07 Feb 2021 00:49:29 GMT
< 
* Connection #0 to host localhost left intact

$ curl -v -X POST http://localhost:8080/event\?startDate\=aaa\&endDate\=bbb
*   Trying ::1:8080...
* Connected to localhost (::1) port 8080 (#0)
> POST /event?startDate=aaa&endDate=bbb HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 400 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 07 Feb 2021 00:51:01 GMT
< Connection: close
< 
{
  "status" : "BAD_REQUEST",
  "message" : "Invalid value aaa for startDate",
  "errors" : [ "Failed to convert value of type 'java.lang.String' to required type 'io.github.picodotdev.blogbitix.dddhexagonal.catalog.domain.model.event.EventDate'; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.RequestParam io.github.picodotdev.blogbitix.dddhexagonal.catalog.domain.model.event.EventDate] for value 'aaa'; nested exception is java.lang.IllegalArgumentException: io.github.picodotdev.blogbitix.dddhexagonal.catalog.domain.model.exceptions.InvalidDate" ]
* Closing connection 0

$ curl -X GET -H 'Accept: application/json' http://localhost:8080/event/1
{
  "id" : 1,
  "status" : "ACTIVE",
  "schedule" : {
    "startDate" : "2021-09-03T10:15:30",
    "endDate" : "2021-12-03T22:00:00"
  }
}
