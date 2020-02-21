POST elasticoffee/_search
{
  "size": 0, 
  "aggs": {
    "by": {
      "terms": {
        "field": "beverage.keyword",
        "size": 10
      }
    }
  }
}