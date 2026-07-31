FROM amazoncorretto:17
COPY ./target/softwareEmethods-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "softwareEmethods-1.0-SNAPSHOT-jar-with-dependencies.jar"]