ARG baseImageVersion
FROM eventuateio/eventuate-examples-docker-images-quarkus-example-base-image:$baseImageVersion
COPY build/customers-service-*.jar service.jar
COPY build/lib/ lib/