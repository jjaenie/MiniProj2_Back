FROM openjdk:17
VOLUME /tmp
COPY build/libs/MiniProj2BackApplication.jar MiniProj2BackApplication.jar
ENTRYPOINT ["java","-jar","/MiniProj2BackApplication.jar","--spring.profiles.active=prod"]