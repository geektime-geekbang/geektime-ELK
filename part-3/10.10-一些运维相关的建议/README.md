# 一些运维相关的建议
## 课程demo
```
# 移动一个分片从一个节点到另外一个节点

POST _cluster/reroute
{
  "commands": [
    {
      "move": {
        "index": "index_name",
        "shard": 0,
        "from_node": "node_name_1",
        "to_node": "node_name_2"
      }
    }
  ]
}


# Fore the allocation of an unassinged shard with a reason

POST _cluster/reroute?explain
{
  "commands": [
    {
      "allocate": {
        "index": "index_name",
        "shard": 0,
        "node": "nodename"
      }
    }
  ]
}


# remove the nodes from cluster 
PUT _cluster/settings
{
  "transient": {
    "cluster.routing.allocation.exclude._ip":"the_IP_of_your_node"
  }
}

# Force a synced flush
POST _flush/synced


# change the number of moving shards to balance the cluster
PUT /_cluster/settings
{
  "transient": {"cluster.routing.allocation.cluster_concurrent_rebalance":2}
}

# change the number of shards being recovered simultanceously per node
PUT _cluster/settings
{
  "transient": {"cluster.routing.allocation.node_concurrent_recoveries":5}
}

# Change the recovery speed
PUT /_cluster/settings
{
  "transient": {"indices.recovery.max_bytes_per_sec": "80mb"}
}

# Change the number of concurrent streams for a recovery on a single node
PUT _cluster/settings
{
  "transient": {"indices.recovery.concurrent_streams":6}
}


# Change the sinze of the search queue
PUT _cluster/settings
{
  "transient": {
    "threadpool.search.queue_size":2000
  }
}

# Clear the cache on a node
POST _cache/clear


#Adjust the circuit breakers
PUT _cluster/settings
{
  "persistent": {
    "indices.breaker.total.limit":"40%"
  }
}


```