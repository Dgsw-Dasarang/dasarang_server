FROM openjdk:11-ea-11-jdk-slim
WORKDIR /app/dasarang
EXPOSE 8088
COPY build/libs/dasarang-0.0.1.jar Dasarang.jar
ENTRYPOINT ["java", "-jar", "Dasarang.jar"]