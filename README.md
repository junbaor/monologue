### monologue

#### build jar

```shell
./mvnw clean package -Dmaven.test.skip=true
```

#### build docker

```shell
./mvnw spring-boot:build-image
```
