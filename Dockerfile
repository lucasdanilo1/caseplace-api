FROM maven:latest as builder

ENV PROJECT_HOME /usr/src/desafioplace
ENV JAR_NAME desafioplace.jar

RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

COPY . .

RUN mvn clean package -DskipTests

RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "desafioplace.jar"]
