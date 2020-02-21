# 索引全生命周期管理及工具介绍
## 课程demo
```

# 运行三个节点，分片 将box_type设置成 hot，warm和cold
# 具体参考 github下，docker-hot-warm-cold 下的docker-compose 文件



DELETE *



# 设置 1秒刷新1次，生产环境10分种刷新一次
PUT _cluster/settings
{
  "persistent": {
    "indices.lifecycle.poll_interval":"1s"
  }
}

# 设置 Policy
PUT /_ilm/policy/log_ilm_policy
{
  "policy": {
    "phases": {
      "hot": {
        "actions": {
          "rollover": {
            "max_docs": 5
          }
        }
      },
      "warm": {
        "min_age": "10s",
        "actions": {
          "allocate": {
            "include": {
              "box_type": "warm"
            }
          }
        }
      },
      "cold": {
        "min_age": "15s",
        "actions": {
          "allocate": {
            "include": {
              "box_type": "cold"
            }
          }
        }
      },
      "delete": {
        "min_age": "20s",
        "actions": {
          "delete": {}
        }
      }
    }
  }
}



# 设置索引模版
PUT /_template/log_ilm_template
{
  "index_patterns" : [
      "ilm_index-*"
    ],
    "settings" : {
      "index" : {
        "lifecycle" : {
          "name" : "log_ilm_policy",
          "rollover_alias" : "ilm_alias"
        },
        "routing" : {
          "allocation" : {
            "include" : {
              "box_type" : "hot"
            }
          }
        },
        "number_of_shards" : "1",
        "number_of_replicas" : "0"
      }
    },
    "mappings" : { },
    "aliases" : { }
}



#创建索引
PUT ilm_index-000001
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 0,
    "index.lifecycle.name": "log_ilm_policy",
    "index.lifecycle.rollover_alias": "ilm_alias",
    "index.routing.allocation.include.box_type":"hot"
  },
  "aliases": {
    "ilm_alias": {
      "is_write_index": true
    }
  }
}

# 对 Alias写入文档
POST  ilm_alias/_doc
{
  "dfd":"dfdsf"
}

```
## 相关阅读
