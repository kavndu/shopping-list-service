FROM openjdk:11
EXPOSE 8080
ADD target/shopping-list-service.jar shopping-list-service.jar
ENTRYPOINT ["java","-jar","/shopping-list-service.jar"]
