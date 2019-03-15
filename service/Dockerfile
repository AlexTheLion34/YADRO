FROM java:8
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
RUN apt-get update && apt-get install net-tools
ENTRYPOINT ["java","-cp","app:app/lib/*","com.service.Application"]
