$ curl -v -X POST http://localhost:8080/__admin/mappings/import --data '{
    "mappings": [{
        "request": {
            "method": "GET",
            "url": "/message/1"
        },
        "response": {
            "status": 200,
            "jsonBody": {
                "id": 1,
                "text": "Hello World!"
            }
        }
    }]
}'

$ curl -v -X POST http://localhost:8080/__admin/mappings/import --data '{
    "mappings": [{
        "request": {
            "method": "POST",
            "url": "/message",
            "bodyPatterns" : [{
                "equalToJson" : {
                    "id": 1,
                    "text": "Hello World!"
                }
            }]
        },
        "response": {
            "status": 200
        }
    }]
}'