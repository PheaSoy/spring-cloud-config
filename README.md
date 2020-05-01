# Spring Cloud Config + Bus

## Use case
* book-service -> configure-server -> github
* book-service will pull the configuration on the startup

## Getting Start

- Clone or download the project
- Go to the spring-cloud-config and running following command
```shell script
docker-compose up -d
``` 
**book-service** is running on port 8082

**configure-server** is running on port 8081

* Accessing the book controller

```shell script
http http://localhost:8082/books 
```
* Result
```json
{
    "author": "Jose Long",
    "price": 10.0,
    "title": "Cloud-Native Java",
    "totalPrice": 11.0
}
```
On the BookController class we inject the tax from PropertySource to calculate the total price
```java
@Value("${tax:0.0}")
```
* Let's change the configuration book-service.yaml then commit the changes
```yaml
  
management:
  endpoints:
    web:
      exposure:
        include: '*'
  rabbitmq:
    username: guest
    password: guest
    port: 5672
    host: localhost
server:
  port: 8082

tax: 30
```
* After we changed and commit - we need to call the configure-server and tell we need to refresh the configuration.

```shell script
http POST http://localhost:8081/actuator/bus-refresh/book-service:**
```

* Let's access the book API again
```shell script
http http://localhost:8082/books 
```
* Result
```json
{
    "author": "Jose Long",
    "price": 10.0,
    "title": "Cloud-Native Java",
    "totalPrice": 13.0
}
```