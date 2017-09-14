# VertX

## Jar
### build:
```
mvn clean package
```

### run:
```
java -jar target/vertx-1.0-fat.jar
```

## Docker
### build:
```
 docker build -t br.com.s2it/vertx .
```

### run:
```
 docker run -it -p 8080:8080 br.com.s2it/vertx
```