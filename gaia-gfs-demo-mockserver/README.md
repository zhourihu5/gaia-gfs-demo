# gaia-gfs-demo-firstapp

---

基于 gaia gfs 框架的第一个 web app 应用.

## 构建

```shell
mvn clean package -U
```

## 运行

最简单的方式:

```shell
java -jar target/gaia-gfs-demo-firstapp.jar
```

通常, 我们会需要更多的启动参数, 如:

```shell
java \
    -Dfile.encoding=UTF8 -Duser.timezone=GMT+08 \
    -Djava.io.tmpdir=${TMP_FOLDER} \
    -Dserver.port=${SERVER_PORT} \
    -Djava.security.egd=file:/dev/./urandom \
    -jar ${APP_FOLDER}/${JAR_NAME} \
    --logging.path=${LOGS_FOLDER}
```

## 功能

下面我们看看这个最简单的 app 给我带来怎样的功能:

1. 项目以 8080 端口启动, 同时启动 9980 的管理端口
1. 默认的 health check url [http://127.0.0.1:9980/health](http://127.0.0.1:9980/health)
1. 默认支持了 prometheus 监控 [http://127.0.0.1:9980/prometheus](http://127.0.0.1:9980/prometheus)
1. 默认的 git info url [http://127.0.0.1:9980/gitInfo](http://127.0.0.1:9980/gitInfo)

我们进一步去修改

application.yml 中添加:

```yaml
longfor.web.swagger.enabled: true
```

来开启 swagger api ui 功能 [http://127.0.0.1:8080/swagger-ui.html](http://127.0.0.1:8080/swagger-ui.html)

至此, 我们已经完成了第一个 web app 应用.
