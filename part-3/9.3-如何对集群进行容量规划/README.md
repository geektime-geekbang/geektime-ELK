# 如何对集群进行容量规划
## 代码Demo

```
PUT logs_2019-06-27
PUT logs_2019-06-26


POST _aliases
{
  "actions": [
    {
      "add": {
        "index": "logs_2019-06-27",
        "alias": "logs_write"
      }
    },
    {
      "remove": {
        "index": "logs_2019-06-26",
        "alias": "logs_write"
      }
    }
  ]
}


# POST /<logs-{now/d}/_search
POST /%3Clogs-%7Bnow%2Fd%7D%3E/_search

# POST /<logs-{now/w}/_search
POST /%3Clogs-%7Bnow%2Fw%7D%3E/_search

```
## 相关阅读
- https://www.elastic.co/guide/en/elasticsearch/guide/current/capacity-planning.html
- https://yq.aliyun.com/articles/670118
