FROM openjdk8-jdk-alpine
VOLUME tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT [java,-Djava.security.egd=filedev.urandom,-jar,app.jar]