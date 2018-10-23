FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY . .

RUN ./gradlew bootJar

CMD [ "java", "-jar", "/application.jar" ]

EXPOSE 8080
