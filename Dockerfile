FROM maven:3.8.3-openjdk-11-slim

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY . .

CMD ["mvn", "clean", "test", "-P${ENVIRONMENT_NAME}"]