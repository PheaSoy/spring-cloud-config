# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# copy jar into image
COPY target/book-service-0.0.1-SNAPSHOT.jar /book-service.jar
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/book-service.jar"]
