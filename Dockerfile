FROM openjdk:17-alpine

COPY ./service-infrastructure/build/libs/*.jar app.jar

ENV TZ=Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]