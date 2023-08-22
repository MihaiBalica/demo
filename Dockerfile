FROM maven:3.8.3-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY . .

#RUN mvn clean install

CMD ["mvn", "clean", "test", "-P${ENVIRONMENT_NAME}"]
# CMD ["mvn", "test"]

# CMD ["java", "-jar", "your-jar-file.jar"]