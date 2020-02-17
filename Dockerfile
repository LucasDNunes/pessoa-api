FROM openjdk:11-jdk
COPY ./target/pessoa-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch pessoa-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","pessoa-0.0.1-SNAPSHOT.jar"]
