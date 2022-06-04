FROM tomcat:9.0.63-jdk11-temurin
MAINTAINER authabc@163.com
COPY *.war /usr/local/tomcat/webapps

