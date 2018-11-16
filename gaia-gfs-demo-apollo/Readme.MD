### apollo客户端使用示例



##### 构建

```bash
mvn clean package -U
```



##### 运行

注意启动参数（修改$MODULE_Folder为本地路径）

```bash
java -Denv=SIT -Dapollo.meta=http://meta.apollo.longfor.sit -Dapollo.cluster=sit -Dapollo.private.key=${MODULE_Folder}/gaia-gfs-demo-apollo/apollo_key_gen/apollo_private_key -jar target/gaia-gfs-demo-apollo.jar
```



apollo portal中的项目配置（使用AD账号登陆）：

http://portal.apollo.longfor.sit/config.html#/appid=gaia-gfs-demo-apollo&env=SIT&cluster=sit


##### 运行结果

程序启动后访问：htttp://127.0.0.1:9000/v1/echo/hello  即可返回拉取到的部分配置信息

详情请参考：http://wiki.longhu.net:8090/pages/viewpage.action?pageId=4589936

