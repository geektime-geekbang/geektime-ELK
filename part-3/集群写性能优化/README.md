## 课程demo
```
{
"template": "logs-*",
"settings": {
"index.indexing.slowlog.threshold.index.debug": "2s",
"index.indexing.slowlog.threshold.index.info": "5s",
"index.indexing.slowlog.threshold.index.trace": "500ms",
"index.indexing.slowlog.threshold.index.warn": "10s",
"index.merge.policy.max_merged_segment": "2gb",
"index.merge.policy.segments_per_tier": "24",
"index.number_of_replicas": "1",
"index.number_of_shards": "12",
"index.optimize_auto_generated_id": "true",
"index.refresh_interval": "600s",
"index.routing.allocation.total_shards_per_node": "-1",
"index.search.slowlog.threshold.fetch.debug": "500ms",
"index.search.slowlog.threshold.fetch.info": "800ms",
"index.search.slowlog.threshold.fetch.trace": "200ms",
"index.search.slowlog.threshold.fetch.warn": "1s",
"index.search.slowlog.threshold.query.debug": "2s",
"index.search.slowlog.threshold.query.info": "5s",
"index.search.slowlog.threshold.query.trace": "500ms",
"index.search.slowlog.threshold.query.warn": "10s",
"index.translog.durability": "async",
"index.translog.flush_threshold_size": "5000mb",
"index.translog.sync_interval": "120s",
"index.unassigned.node_left.delayed_timeout": "7200m"
},
"mappings": {
"_default_": {
"_all": {
"store": "false"
}
},
"typename": {
"dynamic": false,
"properties": {
"full_name": {
"type": "text"
}
}
}
}
}
```
