FROM maven
LABEL Author="Shapin Anton"
COPY target/lib /root/.m2/repository
COPY docker-compose/settings.xml /root/.m2

ADD . /usr/src/mytests/
WORKDIR /usr/src/mytests
RUN entrypointFile="$PWD/docker-compose/entrypoint.sh"

RUN mvn install -Dmaven.test.skip=true
WORKDIR /usr/src/mytests/acceptance-tests/cucumber-acceptance-tests
COPY docker-compose/entrypoint.sh /usr/src/mytests/acceptance-tests/cucumber-acceptance-tests
COPY docker-compose/testReport.txt /usr/src/mytests/acceptance-tests/cucumber-acceptance-tests

RUN apt-get update && apt-get -y install lighttpd
RUN sed -i "s|/var/www/html|$PWD/target/site/allure-maven-plugin|g" /etc/lighttpd/lighttpd.conf

ENTRYPOINT sh entrypoint.sh