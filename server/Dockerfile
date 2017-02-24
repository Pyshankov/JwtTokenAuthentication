FROM java:8-jre
FROM maven:3.3.9-jdk-8
MAINTAINER Pavel Marchenko <pyshankov@gmail.com>

CMD ["/usr/bin/mvn", "clean", "package", "-DskipTests"]

ADD ./server/target/server-1.0-SNAPSHOT.jar /server/server-1.0-SNAPSHOT.jar
CMD ["java", "-Xmx200m", "-jar", "/server/auth-service.jar"]

EXPOSE 8080