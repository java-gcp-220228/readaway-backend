FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
EXPOSE 8081
CMD java -jar ./build/libs/readaway-backend-0.0.1-SNAPSHOT-plain.jar
