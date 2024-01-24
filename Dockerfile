FROM maven:3.9.6-amazoncorretto-8-al2023 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true
