FROM openjdk

COPY target/ToDoList-0.0.1-SNAPSHOT-jar-with-dependencies.jar /usr/local/ToDoList-0.0.1-SNAPSHOT-jar-with-dependencies.jar

CMD ["java", "-jar", "/usr/local/ToDoList-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
