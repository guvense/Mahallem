
FROM openjdk:11
VOLUME /tmp
ADD target/mahallem-0.0.1-SNAPSHOT.jar app.jar
COPY script/generate_cert.sh .
RUN sh generate_cert.sh
EXPOSE 8081 8787 8080 9200 9300
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar" ]