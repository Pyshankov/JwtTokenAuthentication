FROM java:8-jre
MAINTAINER Pavel Marchenko <pyshankov@gmail.com>

ADD ./target/server-0.0.1-SNAPSHOT.jar /home/
CMD ["java", "-Xmx200m", "-jar", "/home/account-service.jar"]