FROM openjdk:11.0.4

ADD bin /home/abyss/bin
ADD conf /home/abyss/conf
ADD target/app.jar /home/abyss/target/app.jar

EXPOSE 8080

ENTRYPOINT ["bash","/home/abyss/bin/start.sh"]