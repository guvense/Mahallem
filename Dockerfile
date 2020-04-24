
FROM openjdk:11
VOLUME /tmp
ADD target/mahallem-0.0.1-SNAPSHOT.jar app.jar
COPY script/generate_cert.sh .
RUN sh generate_cert.sh
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
EXPOSE 8080 8787ex
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -jar /app.jar" ]