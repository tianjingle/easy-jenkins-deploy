## easy-jenkins-deploy

```xml
easy-jenkins-deploy是为了方便将jenkins发布包发到目标服务器并执行脚本的程序，
原因在于jenkins使用ssh连接到window环境需要配置环境太多，比较麻烦。希望使用一种轻巧的部署方式。
```

```xml
使用easy-jenkins-deploy发包不限于和jenkins进行整合，
当然不使用ssh去执行命令具有一定的风险。所以该软件不适合做要求比较严格的项目。
```


## 与jenkins（http request plugins）的整合

- 1.在jenkins中安装Http Request插件

* Http Request的pipline语法如下：
```xml
pipeline {
    agent any
    triggers{
      GenericTrigger(
      token:'app-server'
      )
    }
    stages {
        stage('Build') {
            steps {

                echo '1.start build.....'
                bat 'mvn clean -Dmaven.test.skip=true package'
                echo 'end build'
                bat 'XCOPY *.bat D:\\pipline-test /y \n cd target\n dir \n XCOPY *.war D:\\pipline-test /y'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
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
                   def unregister_url= "http://localhost:8081/task/deploy?commandPath=D:_pipline-test_execute.bat&javafilePath=D:_pipline-test_appserver.war&saveOld=true&targetServer=http://localhost:8082/Jdeploy/upload"

                   response = httpRequest consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'GET', requestBody: toJson(body), url: unregister_url, validResponseCodes: '200'
                   println('Status: '+response.status)
                   println('Response: '+response.content)
               }
            }
        }
    }
}


```
- 在与Jenkinsfile平齐的目录创建execute.sh或者execute.bat，并编写相关脚本。其中必须要有set TARGET_PATH=老项目的地址。示例如下：
```shell script
for /f "tokens=1" %%a in ('jps ^| findstr appserver.war') do taskkill /f /pid %%a
cd JAVA_HOME
set TARGET_PATH=D:\okms-java\appserver\appserver.war
set javawpath=%JAVA_HOME%\bin\
cd %javawpath%
c:
start /b javaw -jar -Dserver.port=9309 -Dspring.profiles.active=prod %TARGET_PATH%
exit

```



- 2.启动客户端(部署在目标主机上)
```xml
java -jar -Dserver.port=8082 jclient.war

```
或者隐藏窗口启动
```xml
set path=%~dp0
set javawpath=%JAVA_HOME%\bin\
set javaPaht=%javawpath:~0,1%
cd %javawpath%
start /b javaw -jar -Dserver.port=8082 %path%jclient.jar

```

杀死进程

```xml
for /f "tokens=1" %%a in ('jps ^| findstr jclient.war') do taskkill /f /pid %%a

```


- 3.启动服务端（和jenkins部署在相同主机）


```xml
java -jar -Dserver.port=8081 -Dcom.scaffold.easy.jenkins.folder=D:/deploy -Dcom.scaffold.easy.jenkins.repostory=D:/deploy/repository jserver.war
```

或者隐藏窗口启动
```xml
set path=%~dp0
set javawpath=%JAVA_HOME%\bin\
set javaPaht=%javawpath:~0,1%
cd %javawpath%
start /b javaw -jar -Dserver.port=8081 %path%jserver.jar

```

杀死进程
```xml
for /f "tokens=1" %%a in ('jps ^| findstr jserver.war') do taskkill /f /pid %%a

```