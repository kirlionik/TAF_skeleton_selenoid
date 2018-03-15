# Readme for QAs
This readme file is targeted for QA Automation engineers


### Table of contents

### Required Software and Tools

### Required Software and Tools
* Java Version: Oracle Java 1.8.0_31 and higher (Execute **_java -version_** in command line after installation)
* Docker
* Apache Maven Version: 3.2.3 and higher (Execute **_mvn -version_** in command line after installation)
* Git Client: 1.9.5 or higher (Execute **_git --version_** in command line after installation)
* Integrated Development Environment: Any version IntelliJ Idea or Eclipse
* Console utility JQ: jq-1.5 or higher  (Execute **_jq --version_** in command line after installation)
* All build commands should be execute from terminal with Bash


### Conventions followed in this guide
* **{ACCEPTANCE_SRC}** refers to top or root directory of this project
* Execute **mvn clean install -Dmaven.test.skip=true**



### Run acceptance tests

#### With Docker
 * Open a terminal or command prompt
 * Go to **{ACCEPTANCE_SRC}**
 * Execute **mvn clean install -Dmaven.test.skip=true && mvn dockerfile:build**
 * Go to **{ACCEPTANCE_SRC}/docker-compose/**
 * Execute **sh startAT.sh**(It will download all necessary docker images). Or execute **docker-compose up** if you have already pulled all necessary docker images
 * Allure tests report is available by link **http://127.0.0.1:8082/**
 
#### Without Docker
 * Open a terminal or command prompt
 * Go to **{ACCEPTANCE_SRC}**
 * Execute **mvn clean install -Dmaven.test.skip=true**
 * Go to **{ACCEPTANCE_SRC}/acceptance-tests/cucumber-acceptance-tests/**
 * Execute **mvn clean install**
 * Allure tests report is available at **{ACCEPTANCE_SRC}/acceptance-tests/cucumber-acceptance-tests/target/site/allure-maven-plugin/index.html**
 
 
### Configuration acceptance tests
#### Docker configuration
* Docker image creation : **{ACCEPTANCE_SRC}/Dockerfile**
* Docker compose : folder **{ACCEPTANCE_SRC}/docker-compose/**
#### Browsers configuration
* System variable: **WEBDRIVER_BROWSER**. There are four browsers available: "Firefox", "Chrome", "Chrome_Selenoid", "Firefox_Selenoid"

