# 集群健康与问题排查
## 课程demo
```
#案例1
DELETE mytest
PUT mytest
{
  "settings":{
    "number_of_shards":3,
    "number_of_replicas":0,
    "index.routing.allocation.require.box_type":"hott"
  }
}





# 检查集群状态，查看是否有节点丢失，有多少分片无法分配
GET /_cluster/health/

# 查看索引级别,找到红色的索引
GET /_cluster/health?level=indices


#查看索引的分片
GET _cluster/health?level=shards

# Explain 变红的原因
GET /_cluster/allocation/explain

GET /_cat/shards/mytest
GET _cat/nodeattrs

DELETE mytest
GET /_cluster/health/

PUT mytest
{
  "settings":{
    "number_of_shards":3,
    "number_of_replicas":0,
    "index.routing.allocation.require.box_type":"hot"
  }
}

GET /_cluster/health/

#案例2, Explain 看 hot 上的 explain
DELETE mytest
PUT mytest
{
  "settings":{
    "number_of_shards":2,
    "number_of_replicas":1,
    "index.routing.allocation.require.box_type":"hot"
  }
}

GET _cluster/health
GET _cat/shards/mytest
GET /_cluster/allocation/explain

PUT mytest/_settings
{
    "number_of_replicas": 0
}

```
## 相关阅读
