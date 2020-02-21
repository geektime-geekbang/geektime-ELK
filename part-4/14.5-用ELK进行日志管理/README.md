# 用 ELK 来做日志管理
## 课程demo
```
./filebeat modules list
./filebeat modules enable system
./filebeat modules enable elasticsearch


## 进 modules.d 编辑相应的文件，修改log路径

./filebeat setup –dashboards

./filebeat export template | more

./filebeat -e

```