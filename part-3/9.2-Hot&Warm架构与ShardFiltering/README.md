# Hot & Warm 架构与 Shard Filtering
## 课程代码
```
# 标记一个 Hot 节点
bin/elasticsearch  -E node.name=hotnode -E cluster.name=geektime -E path.data=hot_data -E node.attr.my_node_type=hot

# 标记一个 warm 节点
bin/elasticsearch  -E node.name=warmnode -E cluster.name=geektime -E path.data=warm_data -E node.attr.my_node_type=warm

# 查看节点
GET /_cat/nodeattrs?v

# 配置到 Hot节点
PUT logs-2019-06-27
{
  "settings":{
    "number_of_shards":2,
    "number_of_replicas":0,
    "index.routing.allocation.require.my_node_type":"hot"
  }
}



PUT my_index1/_doc/1
{
  "key":"value"
}



GET _cat/shards?v


# 配置到 warm 节点
PUT PUT logs-2019-06-27/_settings
{  
  "index.routing.allocation.require.my_node_type":"warm"
}


# 标记一个 rack 1
bin/elasticsearch  -E node.name=node1 -E cluster.name=geektime -E path.data=node1_data -E node.attr.my_rack_id=rack1

# 标记一个 rack 2
bin/elasticsearch  -E node.name=node2 -E cluster.name=geektime -E path.data=node2_data -E node.attr.my_rack_id=rack2

PUT _cluster/settings
{
  "persistent": {
    "cluster.routing.allocation.awareness.attributes": "my_rack_id"
  }
}

PUT my_index1
{
  "settings":{
    "number_of_shards":2,
    "number_of_replicas":1
  }
}

PUT my_index1/_doc/1
{
  "key":"value"
}


GET _cat/shards?v
DELETE my_index1/_doc/1



# Fore awareness
# 标记一个 rack 1
bin/elasticsearch  -E node.name=node1 -E cluster.name=geektime -E path.data=node1_data -E node.attr.my_rack_id=rack1

# 标记一个 rack 2
bin/elasticsearch  -E node.name=node2 -E cluster.name=geektime -E path.data=node2_data -E node.attr.my_rack_id=rack1


PUT _cluster/settings
{
  "persistent": {
    "cluster.routing.allocation.awareness.attributes": "my_rack_id",
    "cluster.routing.allocation.awareness.force.my_rack_id.values": "rack1,rack2"
  }
}
GET _cluster/settings

# 集群黄色
GET _cluster/health

# 副本无法分配
GET _cat/shards?v


GET _cluster/allocation/explain?pretty
```
## 相关阅读
- https://www.elastic.co/cn/blog/sizing-hot-warm-architectures-for-logging-and-metrics-in-the-elasticsearch-service-on-elastic-cloud
- https://www.elastic.co/cn/blog/deploying-a-hot-warm-logging-cluster-on-the-elasticsearch-service
