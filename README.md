# daenerys3-demo

---

该项目用于演示基于 daenerys3 开发的案例

大致描述一下项目的调用关系: 

* daenerys3-demo-web (以下简称 web) 是一个 web application, 发布了两组 api (EnvController 和 GitController).
* daenerys3-demo-client (以下简称 client) 是 web 定义的一组调用的 sdk, 便于其它服务去调用自己, 只有两个代码文件: xxxDTO用于描述传输对象, xxxClient 用于定义调用接口.
* daenerys3-demo-consumer (以下简称 consumer) 通过 web 提供的 client, 调用 web 提供的服务.

## Quick Start

项目启动依赖如下组件

1. jdk 1.8
1. maven 3.3.9+
1. consul (默认 http://127.0.0.1:8500)
1. mysql (默认 127.0.0.1:3306 root/root)
1. redis (默认 127.0.0.1:6379)

### 本地环境启动

环境配置信息可在 项目的 application.yml 或 application-dev.yml 中修改

```bash
mvn clean package -U
nohup java -jar daenerys3-demo-consumer/target/daenerys3-demo-consumer.jar &
nohup java -jar daenerys3-demo-web/target/daenerys3-demo-web.jar & 
```

### docker 启动

```bash
mvn clean package -U
pushd daenerys3-demo-consumer && mvn docker:build && popd
pushd daenerys3-demo-web && mvn docker:build && popd
docker-compose up
```


项目展示了框架赋予的一些功能:

* [基于 consul 的服务发现](docs/consul.md)
* [基于 feignclient 的接口调用](docs/feignclient.md)
* [mybatis 的自动 mapper](docs/mybatis_mapper.md)
* [mybatis 的分页功能](docs/mybatis_pagehelper.md)
* [数据库多数据源](docs/db_multi_datasources.md)
* [redis 多数据源](docs/redis_multi_datasources.md)
* [基于 swagger 的自动api文档生成](docs/swagger.md)
* [统一异常处理](docs/exception_handler.md)
* [统一 metrics](docs/metrics.md)

## TODO

* sonarqube 标准模板
* 支持 jdbc-sharding 分库分表
