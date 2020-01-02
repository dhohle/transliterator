FROM tomcat:8.0.51-jre8-alpine

MAINTAINER "daniel.hohle@gmail.com"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY ./target/transliterator.war /usr/local/tomcat/webapps/transliterator.war


CMD ["catalina.sh", "run"]
