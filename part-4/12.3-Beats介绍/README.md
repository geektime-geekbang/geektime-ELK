# Beats介绍
## 课程demo
```

##
# 查看 packetbeat 模块
# 设置 packetbeat 的mysql 模块
# 启动运行
#
./metricbeat modules list
./metricbeat modules enable mysql
./metricbeat setup --dashboards

# 安装mysql
create database db_example
use db_example;
show tables;
select * from user

curl localhost:8080/demo/add -d name=Mike -d email=mike@xyz.com -d tags=Elasticsearch,IntelliJ
curl localhost:8080/demo/add -d name=Jack -d email=jack@xyz.com -d tags=Mysql,IntelliJ
curl localhost:8080/demo/add -d name=Bob -d email=bob@xyz.com -d tags=Mysql,IntelliJ

curl 'localhost:8080/demo/all'


# 配置 packetbeat
# 启动
修改 packetbeat，打开 http 5601 9200 和 mysql 3306监控

sudo chown root packetbeat.yml
sudo ./packetbeat setup --dashboards
sudo ./packetbeat


# 查看所有 Filebeat 模块
# 查看所有的modules
./filebeat modules list

#
./filebeat modules enable mysql

```
