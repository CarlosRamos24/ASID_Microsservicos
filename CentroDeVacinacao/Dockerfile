FROM openjdk:21
ARG JAR_FILE=centro.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8090