FROM openjdk:11
WORKDIR /usr/src/app
COPY . .
RUN sh gradlew clean build
CMD ["java", "-jar", "./build/libs/jwebcrawler-0.0.1-SNAPSHOT.jar"]