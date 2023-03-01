FROM sbtscala/scala-sbt:eclipse-temurin-jammy-8u352-b08_1.8.2_2.12.17 as builder
#CMD ["/bin/sh"]
WORKDIR /build
# Cache dependencies first
COPY host-selector-api host-selector-api
WORKDIR /build/host-selector-api
RUN sbt stage

FROM adoptopenjdk/openjdk8:jre8u352-b08-debian
WORKDIR /app
COPY --from=builder /build/host-selector-api/target/universal/stage/. .
RUN mv bin/$(ls bin | grep -v .bat) bin/start
EXPOSE 9090/tcp
CMD ["./bin/start"]