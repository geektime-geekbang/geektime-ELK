# 电影搜索服务
## 课程demo
```
安装配置
app-search.yml:
allow_es_settings_modification: true

#Allow Elasticsearch to create indexes automatically: Add the following line #within either Elasticsearch cluster settings or elasticsearch.yml:

action.auto_create_index: ".app-search-*-logs-*,-.app-search-*,+*"




准备数据，使用python 2.7
APP_SEARCH_NAME=tmdb APP_SEARCH_PWD=private-dtcda1pdruoq2hvwqe8rhz1x python ./ingest_tmdb_to_appserarch.py

# 使用最新版本的 node
nvm list
nvm use default

```

## 补充阅读
- APP Search Release - https://www.elastic.co/blog/elastic-app-search-is-now-generally-available
- Search UI - https://www.elastic.co/blog/search-ui-1-0-0-released?baymax=web&elektra=search-ui-webinar
