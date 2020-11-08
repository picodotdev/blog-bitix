$ curl -s -XPOST -H "Content-Type: application/json" -H "User: admin" -d '{"query": "mutation AddBook($title: String, $author: Long){addBook(title: $title, author: $author){title}}", "variables": { "title": "El lazarillo de Tormes 2", "author": "999"}}' http://localhost:8080/graphql
{
  "errors": [
    {
      "message": "Invalid author",
      "locations": [],
      "extensions": {
        "source": {
          "line": 114,
          "column": -1,
          "sourceName": "io.github.picodotdev.blogbitix.graphql.repository.LibraryRepository.addBook(LibraryRepository.java:114)"
        },
        "classification": "ServerError"
      }
    }
  ],
  "data": {
    "addBook": null
  }
}

$ curl -s -XPOST -H "Content-Type: application/json" -d '{"query": "mutation AddBook($title: String, $author: Long){addBook(title: $title, author: $author){title}}", "variables": { "title": "El lazarillo de Tormes 2", "author": "6"}}' http://localhost:8080/graphql
{
  "errors": [
    {
      "message": "Invalid permissions",
      "locations": [],
      "extensions": {
        "source": {
          "line": 110,
          "column": -1,
          "sourceName": "io.github.picodotdev.blogbitix.graphql.repository.LibraryRepository.addBook(LibraryRepository.java:110)"
        },
        "foo": "bar",
        "fizz": "whizz",
        "classification": "ServerError"
      }
    }
  ],
  "data": {
    "addBook": null
  }
}