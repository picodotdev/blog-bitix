$ curl -XPOST -H "Content-Type: application/json" -d '{"query": "query Books{books{none}}"}' http://localhost:8080/graphql
{
  "errors": [
    {
      "message": "Validation error of type FieldUndefined: Field 'none' in type 'Book' is undefined @ 'books/none'"
    }
  ],
  "data": null
}