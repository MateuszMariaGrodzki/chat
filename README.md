# Chat

## Run server

### Requirements

- `JDK` **&ge;8**\
**Download link:**   [Java](https://www.oracle.com/pl/java/technologies/javase-jdk15-downloads.html)
- `MySQL`
**Download link:**\   [MySQL](https://dev.mysql.com/downloads/windows/installer/8.0.html)

### How to

- Create `application.properties` file in `src/main/resources/` directory (based on `.example` file)
- Update your database credentials (and port if using other than default one)
- In MySQL workbench create new schema: db_chat (you can change this name in your application.properties file)

### Dev mode

1. Go to `/chat/` directory
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

1. Go to `/app/` directory
2. Run command:

```
npm i
```

3. After dependencies are installed, run:

```
npm run start
```

4. Enjoy application running on port 8000!
