FROM openjdk:8
EXPOSE 8081
ADD target/mera-thar.jar mera-thar.jar
ENTRYPOINT [ "java", "-jar", "/mera-thar.jar"]