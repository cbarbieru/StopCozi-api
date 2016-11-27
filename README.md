Build with

```
mvn clean install 
```

Run with 

```
java -jar ./target/StopCozi-api-1.0.0-SNAPSHOT.jar server server.yml
```


You can get to the swagger file at:

```
http://localhost:8080/api-spec/swagger.yaml
```

IDE instructions

Make sure you've added Lombok support (https://projectlombok.org/download.html). 
Also, source generation should be done directly from maven, as IDEs don't handle this well enough. Once generation is done, the '/target/generated-sources/swagger/src/gen/java' needs to be indicated as source folder in the IDE.

Have fun!

