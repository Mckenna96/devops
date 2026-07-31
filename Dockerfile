FROM amazoncorretto:17
COPY ./target/softwareEmethods-0.1.0.2-jar-with-dependencies.jar /tmp

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "softwareEmethods-0.1.0.2-jar-with-dependencies.jar"]