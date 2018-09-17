FROM java:8-jdk-alpine
MAINTAINER shanhonghao <i@shanhh.com>

ENV SERVER_PORT 8081
ENV JAR_NAME gfs-demo-consumer.jar

ENV CONSUL_HOST: consul
ENV CONSUL_PORT: 8500

# init project folders
ENV DIST_DIR /var/lib/app
ENV LOGS_FOLDER ${DIST_DIR}/logs
ENV CONFIG_FOLDER ${DIST_CONFIG}/config
ENV APP_FOLDER ${DIST_DIR}/app
ENV TMP_FOLDER ${DIST_DIR}/tmp
ENV JARS_FOLDER ${DIST_DIR}/jars

ENV CLASSPATH ${JAVA_HOME}/lib/tools.jar:${JARS_FOLDER}/:${CONFIG_FOLDER}/

RUN mkdir -p ${DIST_DIR}
RUN mkdir ${LOGS_FOLDER} ${CONFIG_FOLDER} ${APP_FOLDER} ${TMP_FOLDER} ${JARS_FOLDER}

ADD ${JAR_NAME} ${APP_FOLDER}
RUN ls ${APP_FOLDER}

RUN echo 'Asia/Shanghai' > /etc/timezone

EXPOSE ${SERVER_PORT}
ENTRYPOINT ["/bin/sh", "-c", \
"java \
-Dfile.encoding=UTF8 -Duser.timezone=GMT+08 \
-Djava.io.tmpdir=${TMP_FOLDER} \
-Dserver.port=${SERVER_PORT} \
-Dspring.cloud.consul.host=${CONSUL_HOST} \
-Dspring.cloud.consul.port=${CONSUL_PORT} \
-Djava.security.egd=file:/dev/./urandom \
-jar ${APP_FOLDER}/${JAR_NAME} \
--logging.path=${LOGS_FOLDER}"]

