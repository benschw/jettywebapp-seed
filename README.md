# jetty webapp example

## bootstrap
https://devcenter.heroku.com/articles/deploy-a-java-web-application-that-launches-with-jetty-runner


## build

	mvn package

## run

	java -jar target/dependency/jetty-runner.jar target/HelloServlet.war 


and then go to localhost:8080
