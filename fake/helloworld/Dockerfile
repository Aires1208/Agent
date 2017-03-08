FROM zenap/javaaf:v1.16.10.01

MAINTAINER "gordon" <qian.guodong5@zte.com.cn>

ENV AGENT_HOME /home/smartsight-agent

ENV APP_RUN /var/run/app

ENV HTTP_PORT 8080

ENV DEBUG_PORT 8777

ADD smartsight-helloworld.tar.gz ${APP_RUN}/

RUN mkdir -p ${AGENT_HOME}

RUN tar -zxf ${APP_RUN}/smartsight-agent-*.tar.gz -C ${AGENT_HOME}

RUN rm -rf ${APP_RUN}/smartsight-agent-*.tar.gz

RUN chmod 755 ${APP_RUN}/hello/*.sh

EXPOSE ${HTTP_PORT} ${DEBUG_PORT}

CMD ${APP_RUN}/hello/run.sh
