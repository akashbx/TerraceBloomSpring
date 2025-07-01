FROM openjdk:8
COPY target/TerraceBloom-0.0.1-SNAPSHOT.jar TerraceBloom-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java","-jar","TerraceBloom-0.0.1-SNAPSHOT.jar"]