FROM amazoncorretto:17-alpine3.20-jdk
WORKDIR /app
COPY /build/libs/ecoton-backend.jar ./
EXPOSE 8008
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "./ecoton-backend.jar"]
