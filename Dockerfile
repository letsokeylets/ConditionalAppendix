FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.14_9
EXPOSE 8080
ADD target/ConditionalAppendix-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]