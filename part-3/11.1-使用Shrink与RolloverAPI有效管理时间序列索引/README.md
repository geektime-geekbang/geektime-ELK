# 使用 shrink与rolloverAPI有效的管理索引
#课程demo
```
# 打开关闭索引
DELETE test
#查看索引是否存在
HEAD test

PUT test/_doc/1
{
  "key":"value"
}

#关闭索引
POST /test/_close
#索引存在
HEAD test
# 无法查询
POST test/_count

#打开索引
POST /test/_open
POST test/_search
{
  "query": {
    "match_all": {}
  }
}
POST test/_count


# 在一个 hot-warm-cold的集群上进行测试
GET _cat/nodes
GET _cat/nodeattrs


PUT my_source_index
{
 "settings": {
   "number_of_shards": 4
 }
}

PUT my_source_index/_doc/1
{
  "key":"value"
}

GET _cat/shards

# 分片数3会失败
POST my_source_index/_shrink/my_target_index
{
  "settings": {
    "index.number_of_replicas": 1,
    "index.number_of_shards": 3,
    "index.codec": "best_compression"
  },
  "aliases": {
    "my_search_indices": {}
  }
}

# 所有分片必须在同一节点上
POST my_source_index/_shrink/my_target_index
{
  "settings": {
    "index.number_of_replicas": 1,
    "index.number_of_shards": 2,
    "index.codec": "best_compression"
  },
  "aliases": {
    "my_search_indices": {}
  }
}


DELETE my_source_index
PUT my_source_index
{
 "settings": {
   "number_of_shards": 4,
   "number_of_replicas": 0,
   "index.routing.allocation.include.box_type":"hot"
 }
}

PUT my_source_index/_doc/1
{
  "key":"value"
}

GET _cat/shards

# 必须设置成只读
POST my_source_index/_shrink/my_target_index
{
  "settings": {
    "index.number_of_replicas": 1,
    "index.number_of_shards": 2,
    "index.codec": "best_compression"
  },
  "aliases": {
    "my_search_indices": {}
  }
}

#设置为只读
PUT /my_source_index/_settings
{
  "settings": {
    "index.blocks.write": true
  }
}

PUT my_source_index/_doc/1
{
  "key":"value"
}


POST my_source_index/_shrink/my_target_index
{
  "settings": {
    "index.number_of_replicas": 0,
    "index.number_of_shards": 2,
    "index.codec": "best_compression"
  },
  "aliases": {
    "my_search_indices": {}
  }
}


GET _cat/shards

# My target_index状态为也只读
PUT my_target_index/_doc/1
{
  "key":"value"
}



# Split Index
DELETE my_source_index

PUT my_source_index
{
 "settings": {
   "number_of_shards": 4,
   "number_of_replicas": 0
 }
}

PUT my_source_index/_doc/1
{
  "key":"value"
}

GET _cat/shards

# 必须是倍数
POST my_source_index/_split/my_target
{
  "settings": {
    "index.number_of_shards": 10
  }
}

# 必须是只读
POST my_source_index/_split/my_target
{
  "settings": {
    "index.number_of_shards": 8
  }
}


#设置为只读
PUT /my_source_index/_settings
{
  "settings": {
    "index.blocks.write": true
  }
}


POST my_source_index/_split/my_target_index
{
  "settings": {
    "index.number_of_shards": 8
  }
}

DELETE my_target_index

POST my_source_index/_split/my_target_index
{
  "settings": {
    "index.number_of_shards": 8
  }
}

GET _cat/shards

# write block
PUT my_target_index/_doc/1
{
  "key":"value"
}



#Rollover API

# 不设定 is_write_true
# 名字符合命名规范
PUT /nginx-logs-000001
{
  "aliases": {
    "nginx_logs_write": {}
  }
}

# 多次写入文档
POST nginx_logs_write/_doc
{
  "log":"something"
}


POST /nginx_logs_write/_rollover
{
  "conditions": {
    "max_age":   "1d",
    "max_docs":  5,
    "max_size":  "5gb"
  }
}

GET /nginx_logs_write/_count
# 查看 Alias信息
GET /nginx_logs_write


# 设置 is_write_index
PUT apache-logs1
{
  "aliases": {
    "apache_logs": {
      "is_write_index":true
    }
  }
}

POST apache-logs1/_doc
{
  "key":"value"
}

# 需要指定 target 的名字
POST /apache_logs/_rollover/apache-logs2
{
  "conditions": {
    "max_age":   "1d",
    "max_docs":  1,
    "max_size":  "5gb"
  }
}

# 查看 Alias信息
GET /apache_logs



GET _cluster/health
GET _cat/allocation?v
GET _cat/allocation/_explain?v
GET /my_source_index/_settings

GET _cat/shards?h=index,shard,prirep,state,unassigned.reason| grepUNASSIGNED



DELETE *






GET _cat/recovery?v
GET _cat/recovery?v&h=i,s,t,ty,st,shost,thost,f,fp,b,bp
GET /_shard_stores
```
## 相关阅读
- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/indices-shrink-index.html
- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/indices-rollover-index.html
