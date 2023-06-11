FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecommerce-0.0.1-SNAPSHOT.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/ecommerce-0.0.1-SNAPSHOT.jar"]
