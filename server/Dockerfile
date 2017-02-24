FROM java:8-jre
MAINTAINER Pavel Marchenko <pyshankov@gmail.com>

CMD ["mvn", "clean", "package", "-DskipTests"]

ADD ./target/server-1.0-SNAPSHOT.jar /server/server-1.0-SNAPSHOT.jar
CMD ["java", "-Xmx200m", "-jar", "/server/auth-service.jar"]

EXPOSE 8080