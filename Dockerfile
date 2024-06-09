FROM maven:latest as builder

ENV PROJECT_HOME /usr/src/desafioplace
ENV JAR_NAME desafioplace.jar

COPY . $PROJECT_HOME
WORKDIR $PROJECT_HOME

RUN ./mvnw clean package -DskipTests

RUN ls -la $PROJECT_HOME/target

RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "desafioplace.jar"]
