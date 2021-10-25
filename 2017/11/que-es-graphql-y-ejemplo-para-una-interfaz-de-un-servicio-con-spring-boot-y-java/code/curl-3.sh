$ curl -XPOST -H "Content-Type: application/json" -H "User: admin" -d '{"query": "mutation addBook($title: String, $author: Long){addBook(title: $title, author: $author){title}}", "variables": { "title": "El lazarillo de Tormes", "author": "6"}}' http://localhost:8080/graphql
{
  "data": {
    "addBook": {
      "title": "El lazarillo de Tormes"
    }
  }
}

$ curl -XPOST -H "Content-Type: application/json" -H "User: admin" -d '{"query": "mutation addBook($title: String, $author: Long){addBook(title: $title, author: $author){title}}", "variables": { "title": "El lazarillo de Tormes", "author": "999"}}' http://localhost:8080/graphql
{
  "data": {
    "addBook": null
  },
  "errors": [
    {
      "message": "Internal Server Error(s) while executing query",
      "extensions": null,
      "path": null
    }
  ]
}

$ curl -XPOST -H "Content-Type: application/json" -d '{"query": "mutation addBook($title: String, $author: Long){addBook(title: $title, author: $author){title}}", "variables": { "title": "El lazarillo de Tormes", "author": "6"}}' http://localhost:8080/graphql
{
  "data": {
    "addBook": null
  },
  "errors": [
    {
      "message": "Internal Server Error(s) while executing query",
      "extensions": null,
      "path": null
    }
  ]
}