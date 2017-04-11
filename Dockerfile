FROM maven

WORKDIR urlshortener

ADD . /urlshortener

RUN mvn verify

EXPOSE 8080

CMD mvn spring-boot:run