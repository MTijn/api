FROM circleci/openjdk:9-jdk
MAINTAINER Martijn Klene <schapie.nl@gmail.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/api/api.jar"]

EXPOSE 8080

# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/api/api.jar