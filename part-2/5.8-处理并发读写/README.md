# 处理并发读写操作
## 课程DEMO
```

DELETE products
PUT products

PUT products/_doc/1
{
  "title":"iphone",
  "count":100
}



GET products/_doc/1

PUT products/_doc/1?if_seq_no=1&if_primary_term=1
{
  "title":"iphone",
  "count":100
}



PUT products/_doc/1?version=30000&version_type=external
{
  "title":"iphone",
  "count":100
}



```
