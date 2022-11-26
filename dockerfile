FROM java:19
EXPOSE 8081
ADD target/testapp.jar testapp.jar
ENTRYPOINT ["java","-jar","testapp.jar"]