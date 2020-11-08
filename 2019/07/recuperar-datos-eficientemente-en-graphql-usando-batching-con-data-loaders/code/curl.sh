$ curl -XPOST -H 'Content-Type: application/json' -d '{"query":"query{books{id title dataLoaderIsbn}}"}' http://localhost:8080/graphql
{
  "data": {
    "books": [
      {
        "id": "7",
        "title": "Ojo en el cielo",
        "dataLoaderIsbn": "87c1342a-f46e-41ad-ad9a-dda88bb9d0dc"
      },
      {
        "id": "8",
        "title": "Muerte de la luz",
        "dataLoaderIsbn": "0e10de64-4b6e-403a-9cf0-082907525c7a"
      },
      {
        "id": "9",
        "title": "El nombre de la rosa",
        "dataLoaderIsbn": "ea04f2a6-3f1d-4e1a-a2ee-cd370cd2a222"
      },
      {
        "id": "10",
        "title": "Los tejedores de cabellos",
        "dataLoaderIsbn": "c7a83503-fcbd-477e-862b-ab72575f72a7"
      },
      {
        "id": "11",
        "title": "Ready Player One",
        "dataLoaderIsbn": "0fee8cbb-8c0f-4fef-b89d-e6ac236fc31c"
      }
    ]
  }
}