ENV APP_TARGET target
ENV APP invillia-application.jar

RUN mkdir -p /opt

COPY ${APP_TARGET}/${APP} /opt

ENTRYPOINT MEM_PARAMS=$(/param_tuning.sh ${HEAP_SIZE:-512}); exec java ${JAVA_AGENT} ${JAVA_OPTS} ${MEM_PARAMS} -jar /opt/${APP}
