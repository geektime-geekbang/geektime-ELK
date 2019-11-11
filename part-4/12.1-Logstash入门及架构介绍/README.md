# Logstash 入门及架构介绍
## 课程demo
```


# 一个 Demo， demo 运行
sudo bin/logstash -f logstash-filter.conf

# demo数据
127.0.0.1 - - [11/Dec/2013:00:01:45 -0800] "GET /xampp/status.php HTTP/1.1" 200 3891 "http://cadenza/xampp/navi.php" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"


# codec demo


sudo bin/logstash -e "input{stdin{codec=>line}}output{stdout{codec=> rubydebug}}"
sudo bin/logstash -e "input{stdin{codec=>json}}output{stdout{codec=> rubydebug}}"
sudo bin/logstash -e "input{stdin{codec=>line}}output{stdout{codec=> dots}}"


sudo bin/logstash -f multiline-exception.conf

# 多行数据，异常
Exception in thread "main" java.lang.NullPointerException
        at com.example.myproject.Book.getTitle(Book.java:16)
        at com.example.myproject.Author.getBookTitles(Author.java:25)
        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)



# 一个实例
https://github.com/onebirdrocks/geektime-ELK/blob/master/part-1/2.4-Logstash%E5%AE%89%E8%A3%85%E4%B8%8E%E5%AF%BC%E5%85%A5%E6%95%B0%E6%8D%AE/movielens/logstash.conf

```
