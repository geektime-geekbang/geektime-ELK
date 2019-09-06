# Space Jam，一次全文搜索的实例

## 环境要求
- Python 2.7.15
- 可以使用pyenv管理多个python版本（可选）

## 进入 tmdb-search目录
Run
pip install -r requirements.txt
Run python ./ingest_tmdb_from_file.py
## 课程demo
```
POST tmdb/_search
{
"_source": ["title","overview"],
 "query": {
   "match_all": {}
 }
}

POST tmdb/_search
{
  "_source": ["title","overview"],
  "query": {
    "multi_match": {
      "query": "basketball with cartoon aliens",
      "fields": ["title","overview"]
    }
  },
  "highlight" : {
        "fields" : {
            "overview" : { "pre_tags" : ["\\033[0;32;40m"], "post_tags" : ["\\033[0m"] },
            "title" : { "pre_tags" : ["\\033[0;32;40m"], "post_tags" : ["\\033[0m"] }

        }
    }
}
```

## 相关
- Windows 安装 pyenv https://github.com/pyenv-win/pyenv-win
- Mac 安装pyenv https://segmentfault.com/a/1190000017403221
- Linux 安装 pyenv https://blog.csdn.net/GX_1_11_real/article/details/80237064
- Python.org https://www.python.org/
