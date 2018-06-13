FROM openjdk:8-alpine

VOLUME /tmp

EXPOSE 8080

ENV JAVA_OPTS=""
ENV APPLICATION_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APPLICATION_OPTS" ]

# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar