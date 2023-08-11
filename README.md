# zipkin-poc

Basic post office microservice application to demo Zipkin.

### Local development
Build all modules:
```shell
mvn clean package -DskipTests
```
Start local environment
```shell
docker compose up --build
```

Send a request using Postman: 
```
POST http://localhost:9093/send-postcard
{
    "sender": "person2",
    "recipient": "person1",
    "cardText": "Hello world! :)"
}
```

Open the Zipkin UI in your browser: 
```shell
http://localhost:9411
```