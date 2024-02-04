# Build
#FROM maven:3.9.6-amazoncorretto-8-al2023 as builder
# версию не указываем - стянет последнюю актуальную
FROM maven as build
WORKDIR /app
#копируем src в app/src в слое образа
COPY src /app/src
#копируем pom туда же (в слой образа)
COPY pom.xml /app
#запускаем билд в образе, на выходе jar
RUN mvn -f /app/pom.xml clean install -DskipTests=true

# Package
#FROM openjdk:21
FROM bellsoft/liberica-runtime-container:jdk-all-slim-glibc
# скопируем получившийся jar из target в app
COPY --from=build /app/target/lesson5-1.0.0.jar /app/MyApp-1.0.0.jar
# порт контейнера
EXPOSE 8080
# стартуем приложение внутри контейнера
ENTRYPOINT ["java","-jar","/app/MyApp-1.0.0.jar"]
