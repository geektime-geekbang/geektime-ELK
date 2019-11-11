# 缓存及使用Circuit Breaker限制内存使用
## 课程demo
```
GET _cat/nodes?v

GET _nodes/stats/indices?pretty

GET _cat/nodes?v&h=name,queryCacheMemory,queryCacheEvictions,requestCacheMemory,requestCacheHitCount,request_cache.miss_count

GET _cat/nodes?h=name,port,segments.memory,segments.index_writer_memory,fielddata.memory_size,query_cache.memory_size,request_cache.memory_size&v


PUT /_cluster/settings
{
    "persistent" : {
       "indices.breaker.request.limit" : "90%"
    }
}

```
## 补充阅读
- https://www.elastic.co/blog/improving-node-resiliency-with-the-real-memory-circuit-breaker
