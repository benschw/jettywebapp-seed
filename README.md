# jetty webapp example

## bootstrap
https://devcenter.heroku.com/articles/deploy-a-java-web-application-that-launches-with-jetty-runner

wget http://repo2.maven.org/maven2/org/mortbay/jetty/jetty-runner/7.5.4.v20111024/jetty-runner-7.5.4.v20111024.jar


## build

	mvn package

## run

	java -jar jetty-runner-7.5.4.v20111024.jar build/libs/jettywebapp-seed.war 


and then go to localhost:8080
