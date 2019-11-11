# stackoverflow 用户调查问卷分析
## 课程demo
```
sudo bin/logstash -f ./logstash-stackoverflow-survey.conf


PUT final-stackoverflow-survey
{
  "mappings": {
    "dynamic_templates": [
      {
        "strings_as_keywords": {
          "match_mapping_type": "string",
          "mapping": {
            "type": "keyword"
          }
        }
      }
    ]
  },
  "settings": {
    "number_of_replicas": 0
  }
}



数字相关
YearsCode
WorkWeekHrs
Age
Age1stCode 16
YearsCodePro



PUT _ingest/pipeline/stackoverflow_pipeline
{
  "description": "Pipeline for stackoverflow survey",
  "processors": [
    {
      "split": {
        "field": "DatabaseDesireNextYear",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "DatabaseWorkedWith",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "DevEnviron",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "LanguageWorkedWith",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "MiscTechDesireNextYear",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "PlatformWorkedWith",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "PlatformDesireNextYear",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "WebFrameWorkedWith",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "WebFrameDesireNextYear",
        "separator": ";"
      }
    },
    {
      "split": {
        "field": "Containers",
        "separator": ";"
      }
    },
    {
      "script": {
        "source": """
          try{
	          ctx.YearsCode = Integer.parseInt(ctx.YearsCode);
          	}catch(Exception e){
          		ctx.YearsCode = 0;
          	}
"""
      }
    },
    {
      "script": {
        "source": """
          try{
	          ctx.WorkWeekHrs = Integer.parseInt(ctx.WorkWeekHrs);
          	}catch(Exception e){
          		ctx.WorkWeekHrs = 0;
          	}
"""
      }
    },
    {
      "script": {
        "source": """
          try{
	          ctx.Age = Integer.parseInt(ctx.Age);
          	}catch(Exception e){
          		ctx.Age = 0;
          	}
"""
      }
    },
    {
      "script": {
        "source": """
          try{
	          ctx.Age1stCode = Integer.parseInt(ctx.Age1stCode);
          	}catch(Exception e){
          		ctx.Age1stCode = 0;
          	}
"""
      }
    },
    {
      "script": {
        "source": """
          try{
	          ctx.YearsCodePro = Integer.parseInt(ctx.YearsCodePro);
          	}catch(Exception e){
          		ctx.YearsCodePro = 0;
          	}
"""
      }
    }
  ]
}



POST _reindex?wait_for_completion=false
{
  "source": {
    "index": "stackoverflow-survey-raw"
  },
  "dest": {
    "index": "final-stackoverflow-survey",
    "pipeline": "stackoverflow_pipeline"
  }
}

GET final-stackoverflow-survey/_mapping

```
## 参考链接
http://stackoverflow.com/research/ 
