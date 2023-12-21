FROM rsunix/yourkit-openjdk17

ADD target/MyAwesomeProject.jar MyAwesomeProject.jar
ENTRYPOINT ["java", "-jar", "MyAwesomeProject.jar"]
EXPOSE 8080