FROM openjdk:17

WORKDIR /app

COPY build/target/ms-turbo-worker.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]