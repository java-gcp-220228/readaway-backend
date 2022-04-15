FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
EXPOSE 8081
COPY readaway-backend-0.0.1-SNAPSHOT-plain.jar /home/readaway-backend-0.0.1-SNAPSHOT-plain.jar
CMD ["java","-jar","/home/readaway-backend-0.0.1-SNAPSHOT-plain.jar"]
