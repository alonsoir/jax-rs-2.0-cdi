Thanks to Andrey Redko. This is a good starting point to begin developing a RESTful web service using the latest version of apache-cxf, 3.0.X


How to use
==============
compile and package it and launch it with

 - mvn clean package
 - java -jar target/jax-rs-2.0-cdi-0.0.1-SNAPSHOT.jar

then you can try this commands:

- curl http://localhost:8080/rest/api/people -X POST -d "email=a@b.com&firstName=Tom&lastName=Knocker"
- curl http://localhost:8080/rest/api/people/a@b.com -X PUT -d "firstName=Tommy"
- curl http://localhost:8080/rest/api/people/a@b.com -X DELETE
- curl http://localhost:8080/rest/api/people
