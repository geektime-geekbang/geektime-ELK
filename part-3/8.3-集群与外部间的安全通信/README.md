

```
xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.keystore.path: certs/elastic-certificates.p12
xpack.security.http.ssl.truststore.path: certs/elastic-certificates.p12

```
```


# ES 启用 https
bin/elasticsearch -E node.name=node0 -E cluster.name=geektime -E path.data=node0_data -E http.port=9200 -E xpack.security.enabled=true -E xpack.security.transport.ssl.enabled=true -E xpack.security.transport.ssl.verification_mode=certificate -E xpack.security.transport.ssl.keystore.path=certs/elastic-certificates.p12 -E xpack.security.http.ssl.enabled=true -E xpack.security.http.ssl.keystore.path=certs/elastic-certificates.p12 -E xpack.security.http.ssl.truststore.path=certs/elastic-certificates.p12

```

```
#Kibana 连接 ES https



# 为kibana生成pem
openssl pkcs12 -in elastic-certificates.p12 -cacerts -nokeys -out elastic-ca.pem


elasticsearch.hosts: ["https://localhost:9200"]
elasticsearch.ssl.certificateAuthorities: [ "/Users/yiruan/geektime/kibana-7.1.0/config/certs/elastic-ca.pem" ]
elasticsearch.ssl.verificationMode: certificate



# 为 Kibna 配置 HTTPS
# 生成后解压，包含了instance.crt 和 instance.key
bin/elasticsearch-certutil ca --pem

server.ssl.enabled: true
server.ssl.certificate: config/certs/instance.crt
server.ssl.key: config/certs/instance.key


```


# 相关阅读
- https://www.elastic.co/guide/en/elasticsearch/reference/current/configuring-tls.html#tls-http
