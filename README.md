# Backend-Springboot

1) Make a new Spring Book project https://start.spring.io/
2) cd to Java Spring Boot project
3) mvn clean package (make sure you have Maven installed)
4) copy JAR file located in springBootProject/target/springBootProject-0.0.1-SNAPSHOT.jar to ec2 instance by running following command on local machine: scp -i  path_to_pem_file  path_to_JAR_file force@172.20.21.231:/home/force/path_you_want
5) ssh into ec2 instance
6) navigate to path where we copied JAR file to
7) run following command: java -jar name_of_JAR_file
8) server should be running on 172.20.21.231:8080
9) make calls by utilizing application such as Postman or Insomnia to endpoints written in Spring Boot Project, see below for example
If /api/data is a path, send a GET request to 172.20.21.231:8080/api/data
