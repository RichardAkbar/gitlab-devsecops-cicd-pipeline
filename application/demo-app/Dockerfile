FROM eclipse-temurin:21-jre

WORKDIR /app

RUN groupadd --system --gid 10001 app && \
    useradd --system --uid 10001 --gid 10001 --home-dir /app app

COPY --chown=10001:10001 target/demo-app-0.0.1-SNAPSHOT.jar app.jar

USER 10001:10001

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
