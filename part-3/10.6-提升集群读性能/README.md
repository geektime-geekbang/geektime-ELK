# 提升集群读性能
## 课程demo
```
PUT blogs/_doc/1
{
  "title":"elasticsearch"
}
GET blogs/_search
{
  "query": {
    "bool": {
      "must": [
        {"match": {
          "title": "elasticsearch"
        }}
      ],
      
      "filter": {
        "script": {
          "script": {
            "source": "doc['title.keyword'].value.length()>5"
          }
        }
      }
    }
  }
}


GET blogs/_search
{
  "query": {
    "bool": {
      "must": [
        {"match": {"title": "elasticsearch"}},
        {
          "range": {
            "publish_date": {
              "gte": 2017,
              "lte": 2019
            }
          }
        }
      ]
    }
  }
}
```
## 相关阅读
- https://www.elastic.co/guide/en/elasticsearch/reference/current/tune-for-search-speed.html
