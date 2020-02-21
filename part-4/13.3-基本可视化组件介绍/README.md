# 基本可视化组件介绍
# 课程demo
```

PUT /shakespeare
{
  "mappings": {
    "properties": {
    "speaker": {"type": "keyword"},
    "play_name": {"type": "keyword"},
    "line_id": {"type": "integer"},
    "speech_number": {"type": "integer"}
    }
  }
}


# For Mac & Windows
curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/bank/account/_bulk?pretty' --data-binary @accounts.json
curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/shakespeare/_bulk?pretty' --data-binary @shakespeare.json

#For Windows
Invoke-RestMethod "http://localhost:9200/bank/account/_bulk?pretty" -Method Post -ContentType 'application/x-ndjson' -InFile "accounts.json"
Invoke-RestMethod "http://localhost:9200/shakespeare/_bulk?pretty" -Method Post -ContentType 'application/x-ndjson' -InFile "shakespeare.json"


```
