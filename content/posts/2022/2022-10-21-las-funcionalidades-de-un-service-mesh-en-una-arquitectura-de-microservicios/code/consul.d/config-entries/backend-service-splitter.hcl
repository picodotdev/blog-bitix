Kind = "service-splitter"
Name = "backend"

Splits = [{
  Weight = 75
  ServiceSubset = "v1"
},
{
  Weight = 25
  ServiceSubset = "v2"
}]
