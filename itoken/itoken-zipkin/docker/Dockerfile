FROM openjdk:8u302-jre

ENV APP_VERSION 1.0.0-SNAPSHOT
ENV DOCKERIZE_VERSION v0.6.1

WORKDIR /app

COPY itoken-zipkin-$APP_VERSION.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=dev"]

EXPOSE 8761