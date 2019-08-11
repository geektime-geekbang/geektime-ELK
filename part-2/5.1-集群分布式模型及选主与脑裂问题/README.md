# 集群分布式模型及选主与脑裂问题

## 课程Demo
```
bin/elasticsearch -E node.name=node1 -E cluster.name=geektime -E path.data=node1_data
bin/elasticsearch -E node.name=node2 -E cluster.name=geektime -E path.data=node2_data
bin/elasticsearch -E node.name=node3 -E cluster.name=geektime -E path.data=node3_data

```
## 相关阅读
- https://www.elastic.co/cn/blog/a-new-era-for-cluster-coordination-in-elasticsearch
