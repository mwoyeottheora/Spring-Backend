FROM openjdk:17.0-jdk-oraclelinux8

COPY ./service-infrastructure/build/libs/*.jar app.jar

ENV TZ=Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]