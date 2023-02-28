# host-selector-api

An API to select the cooler host to handle a giving payload/job. 



# Testing 


# Running

```bash 
docker run -it -w /app \
        --name scala-sandbox \
        -e PROMETHEUS_HOST="127.0.0.1:9090" \
        -v $PWD:/app -p 0.0.0.0:8080:8080 \
        --rm sbtscala/scala-sbt:eclipse-temurin-jammy-8u352-b08_1.8.2_2.12.17 /bin/bash

```

```bash 
sbt run
```

```bash 
curl http://localhost:9090/test -X POST  -H "Content-Type: application/json"  -d '{"filter":"hhgtg","strategy":"42"}'
```


# How to collect smaple data

```bash 
http://prometheus:9090/api/v1/query?query=CPU_Temperature[10m]
```