## easy-jenkins-deploy

```xml
easy-jenkins-deploy是为了方便将jenkins发布包发到目标服务器并执行脚本的程序，原因在于jenkins使用ssh连接到window环境需要配置环境太多，比较麻烦。希望使用一种轻巧的部署方式。
```

```xml
使用easy-jenkins-deploy发包不限于和jenkins进行整合，当然不使用ssh去执行命令具有一定的风险。所以该软件不适合做要求比较严格的项目。
```


## 与jenkins（http request plugins）的整合

- 1.在jenkins中安装http request组件

http request的pipline语法如下：
```xml
        stage('Deploy') {
            steps {

               script {
                   def toJson = {
                       input ->
                       groovy.json.JsonOutput.toJson(123)
                   }
                   def body = [
                       status: "DOWN"
                   ]
                   这里是客户端的地址，采用get上送部署信息
                   def unregister_url= "http://localhost:9904/appserver/version/info"
                   response = httpRequest consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'GET', requestBody: toJson(body), url: unregister_url, validResponseCodes: '200'
                   println('Status: '+response.status)
                   println('Response: '+response.content)
               }
            }
        }
```

- 2.启动客户端
```xml
java -jar client.war
```

- 3.启动服务端

```xml
java -jar server.war
```

