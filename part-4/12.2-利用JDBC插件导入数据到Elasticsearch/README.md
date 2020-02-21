# Logstash插件及文档介绍
##课程demo
```
https://spring.io/guides/gs/accessing-data-mysql/
create database db_example;
use db_example;
show tables;
drop table user;
select * from user;



# 新增用户
curl localhost:8080/demo/add -d name=Mike -d email=mike@xyz.com -d tags=Elasticsearch,IntelliJ
curl localhost:8080/demo/add -d name=Jack -d email=jack@xyz.com -d tags=Mysql,IntelliJ
curl localhost:8080/demo/add -d name=Bob -d email=bob@xyz.com -d tags=Mysql,IntelliJ

#查看所有的用户
curl 'localhost:8080/demo/all'

# 更新用户
curl -X PUT localhost:8080/demo/update -d id=16 -d name=Bob2 -d email=bob2@xyz.com -d tags=Mysql,IntelliJ

# 删除用户
curl -X DELETE localhost:8080/demo/delete -d id=15



mysql-demo.conf

# 创建 alias，只显示没有被标记 deleted的用户
POST /_aliases
{
  "actions": [
    {
      "add": {
        "index": "users",
        "alias": "view_users",
         "filter" : { "term" : { "is_deleted" : false } }
      }
    }
  ]
}

# 通过 Alias查询，查不到被标记成 deleted的用户
POST view_users/_search
{

}


POST view_users/_search
{
  "query": {
    "term": {
      "name.keyword": {
        "value": "Jack"
      }
    }
  }
}

POST users/_search
{
  "query": {
    "term": {
      "name.keyword": {
        "value": "Jack"
      }
    }
  }
}

```
