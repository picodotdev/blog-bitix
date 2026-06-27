curl -L 'http://localhost:8000/api/v1/identities/' \
     -H 'Content-Type: application/json' \
     -H 'Accept: application/json' \
     -H 'X-Environment-Key: K5DcsiSmbvpSDFisFF9Yxt' \
-d '{
  "identifier": "d4cb38f6-9945-4730-b779-e699039592f0",
  "traits": [
    {
      "trait_key": "country",
      "trait_value": "spain"
    }
  ],
  "transient": false
}'

curl -L 'http://localhost:8000/api/v1/identities/' \
     -H 'Content-Type: application/json' \
     -H 'Accept: application/json' \
     -H 'X-Environment-Key: K5DcsiSmbvpSDFisFF9Yxt' \
-d '{
  "identifier": "53b0780f-a55c-4e09-8dab-78fbad3f9a3f",
  "traits": [
    {
      "trait_key": "country",
      "trait_value": "united-kingdom"
    }
  ],
  "transient": false
}'

curl -L 'http://localhost:8000/api/v1/identities/?identifier=d4cb38f6-9945-4730-b779-e699039592f0' \
     -H 'Content-Type: application/json' \
     -H 'Accept: application/json' \
     -H 'X-Environment-Key: K5DcsiSmbvpSDFisFF9Yxt'