# 集群Backup & Restore
## 课程demo
```

#在 elasticsearch.yml 加入相关的配置
path.repo: ["/Users/yiruan/geektime/mount/my_backup"]

#创建一个 repositoty
PUT /_snapshot/my_fs_backup
{
    "type": "fs",
    "settings": {
        "location": "/Users/yiruan/geektime/mount/my_backup",
        "compress": true
    }
}

# 创建一个snapshot
PUT /_snapshot/my_fs_backup/snapshot_1?wait_for_completion=true

DELETE test
PUT test/_doc/1
{
  "key":"value1"
}


#指定索引创建快照
PUT /_snapshot/my_fs_backup/snapshot_2?wait_for_completion=true
{
  "indices": "test",
  "ignore_unavailable": true,
  "include_global_state": false,
  "metadata": {
    "taken_by": "yiming",
    "taken_because": "backup before upgrading"
  }
}

#查看所有的快照
GET /_snapshot/my_fs_backup/_all

# 删除快照
DELETE /_snapshot/my_fs_backup/snapshot_2


POST /_snapshot/my_fs_backup/snapshot_1/_restore
{
  
}

# 指定索引进行 restore
POST /_snapshot/my_fs_backup/snapshot_1/_restore
{
  "indices": "test",
  "index_settings": {
    "index.number_of_replicas": 5
  },
  "ignore_index_settings": [
    "index.refresh_interval"
  ]
}

DELETE test

# 删除快照
DELETE /_snapshot/my_fs_backup



```