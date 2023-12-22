FROM rsunix/yourkit-openjdk17:2022.9

ADD target/FightCard.jar FightCard.jar
ENTRYPOINT ["java", "-jar", "FightCard.jar"]
EXPOSE 8080