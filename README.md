# Chat

## Run server

### Requirements

- `JDK` **&ge;8**\
  **Download link:** [Java](https://www.oracle.com/pl/java/technologies/javase-jdk15-downloads.html)
- `MySQL`\
  **Download link:** [MySQL](https://dev.mysql.com/downloads/windows/installer/8.0.html)

### How to

- Create `application.properties` file in [server/src/main/resources](./server/src/main/resources) directory (based on [example file](./server/src/main/resources/application.properties.example))
- Update your database credentials (and port if using other than default one)
- In MySQL workbench create new schema: db_chat (you can change this name in your `application.properties` file)

### Dev mode

1. Go to [server](./server) directory
2. Run command:

```
mvnw spring-boot:run
```

3. Application will start on port 8080
4. To terminate proces press twice **Ctrl+C**

## Run client app

### Requirements

- `Node.js` **&ge;10.20.1**
- `npm` **&ge;6.14.4**

### Dev mode

1. Go to [client](./client) directory
2. Run command:

```
npm i && npm run start
```

3. Enjoy application running on port 8000!
