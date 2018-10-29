FROM openjdk:10-jre

ADD ./target/movie-service-0.0.1-SNAPSHOT.jar /usr/app/movie-service-0.0.1-SNAPSHOT.jar

WORKDIR usr/app

ENTRYPOINT ["java","-jar", "movie-service-0.0.1-SNAPSHOT.jar"]