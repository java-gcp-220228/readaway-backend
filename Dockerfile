FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
EXPOSE 8081
CMD ["java", "-jar", "readaway-backend-0.0.1-SNAPSHOT.jar"]