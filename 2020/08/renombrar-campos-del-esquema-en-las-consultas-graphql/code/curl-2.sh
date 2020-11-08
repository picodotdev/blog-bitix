$ curl -s -XPOST -H "Content-Type: application/json" -d '{"query": "query Books{books{title date isbn:dataLoaderIsbn}}"}' http://localhost:8080/graphql
{
  "data": {
    "books": [
      {
        "title": "Ojo en el cielo",
        "date": "1957-01-01",
        "isbn": "87c1342a-f46e-41ad-ad9a-dda88bb9d0dc"
      },
      {
        "title": "Muerte de la luz",
        "date": "1977-01-01",
        "isbn": "0e10de64-4b6e-403a-9cf0-082907525c7a"
      },
      {
        "title": "El nombre de la rosa",
        "date": "1980-01-01",
        "isbn": "ea04f2a6-3f1d-4e1a-a2ee-cd370cd2a222"
      },
      {
        "title": "Los tejedores de cabellos",
        "date": "1995-01-01",
        "isbn": "c7a83503-fcbd-477e-862b-ab72575f72a7"
      },
      {
        "title": "Ready Player One",
        "date": "2011-01-01",
        "isbn": "0fee8cbb-8c0f-4fef-b89d-e6ac236fc31c"
      }
    ]
  }
}