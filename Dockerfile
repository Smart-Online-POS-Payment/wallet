FROM gradle:8.5.0-jdk21

RUN mkdir /app
WORKDIR /app
COPY . /app/

COPY build/libs/wallet.jar wallet.jar

ENV SPRING_PROFILES_ACTIVE=container
ENV SERVER_PORT=8080
ENV MANAGEMENT_SERVER_PORT=9090

EXPOSE $SERVER_PORT $MANAGEMENT_SERVER_PORT

ENTRYPOINT ["java","-jar","wallet.jar"]