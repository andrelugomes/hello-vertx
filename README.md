# Repo for Vert.x POCs

## Core Sample

### build
```
mvn clean package
```

### run
```
mvn compile vertx:run
```
OR
```
java -jar target/core-sample-1.0.jar
```

### Docker
#### build
```
docker build -t br.com/vertx .
```

#### run
```
docker run -it -p 8080:8080 br.com/vertx
```

## Web Sample
```
mvn clean package
```
```
java -jar target/vertx-1.0-fat.jar
```

## Web Client
```
mvn clean package
```
```
mvn compile vertx:run
```
OR
```
java -jar target/web-client-1.0.jar
```
