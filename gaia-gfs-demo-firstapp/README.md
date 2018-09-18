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
    --logging.path=${LOGS_FOLDER}"]
```