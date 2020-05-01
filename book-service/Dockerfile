# Alpine Linux with OpenJDK JRE

FROM openjdk:8-jre-alpine

RUN apk update && apk add bash

COPY wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh

# copy jar into image
COPY target/book-service-0.0.1-SNAPSHOT.jar /book-service.jar
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/book-service.jar"]
