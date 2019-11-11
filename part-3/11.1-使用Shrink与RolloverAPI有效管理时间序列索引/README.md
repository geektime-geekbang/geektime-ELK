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

DELETE my_source_index
DELETE my_target_index
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

GET _cat/shards/my_source_index

# 分片数3，会失败
POST my_source_index/_shrink/my_target_index
{
  "settings": {
    "index.number_of_replicas": 0,
    "index.number_of_shards": 3,
    "index.codec": "best_compression"
  },
  "aliases": {
    "my_search_indices": {}
  }
}



# 报错，因为没有置成 readonly
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

#将 my_source_index 设置为只读
PUT /my_source_index/_settings
{
  "settings": {
    "index.blocks.write": true
  }
}

# 报错，必须都在一个节点
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

DELETE my_source_index
## 确保分片都在 hot
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

GET _cat/shards/my_source_index

#设置为只读
PUT /my_source_index/_settings
{
  "settings": {
    "index.blocks.write": true
  }
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


GET _cat/shards/my_target_index

# My target_index状态为也只读
PUT my_target_index/_doc/1
{
  "key":"value"
}



# Split Index
DELETE my_source_index
DELETE my_target_index

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

GET _cat/shards/my_source_index

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
    "index.number_of_shards": 8,
    "index.number_of_replicas":0
  }
}

GET _cat/shards/my_target_index



# write block
PUT my_target_index/_doc/1
{
  "key":"value"
}



#Rollover API
DELETE nginx-logs*
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


DELETE apache-logs*


# 设置 is_write_index
PUT apache-logs1
{
  "aliases": {
    "apache_logs": {
      "is_write_index":true
    }
  }
}
POST apache_logs/_count

POST apache_logs/_doc
{
  "key":"value"
}

# 需要指定 target 的名字
POST /apache_logs/_rollover/apache-logs8xxxx
{
  "conditions": {
    "max_age":   "1d",
    "max_docs":  1,
    "max_size":  "5gb"
  }
}


# 查看 Alias信息
GET /apache_logs


```
## 相关阅读
- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/indices-shrink-index.html
- https://www.elastic.co/guide/en/elasticsearch/reference/7.1/indices-rollover-index.html
