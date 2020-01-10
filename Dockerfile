FROM openjdk:11
ADD target/medication_dispenser_server-1.0-SNAPSHOT.jar medication_dispenser.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "medication_dispenser.jar"]
