# Installation & Configuration
## MySQL
- Install  mysql - www.mysql.com
- MySQL configuration in src > main > resources > application.properties
## Setup APM
- Download APM 7.1.0 - https://www.elastic.co/downloads/past-releases/apm-server-7-1-0
- Run APM server 
```
./apm-server
```
- RUN Application with the following argument
```
mvn package


java -javaagent:./elastic-apm-agent-1.6.1.jar \
     -Delastic.apm.service_name=my-application \
     -Delastic.apm.server_url=http://localhost:8200 \
     -Delastic.apm.application_packages=hello \
     -jar ./target/gs-mysql-data-0.1.0.jar
     
``` 

## Run gatling load testing script 
- Download gatling - https://gatling.io/
- Download Scala - https://www.scala-lang.org/
- Put the MysqlApp.scala in
- Run gatling
```
#Linux
$GATLING_HOME/bin/gatling.sh

#On Windows
%GATLING_HOME%\bin\gatling.bat
```
- Select MysqlApp to run