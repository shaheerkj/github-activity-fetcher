# Description
This project utilizes the Github API to track and display user activity
For now, it's quite simple, it fetches and displays data based on the type of event.

The API used.
```
https://api.github.com/users/<username>/events
```

### Usage:
1. You can run the .jar file:
```
java -jar .\target\githubUserActivity-0.0.1-SNAPSHOT.jar <username>
```
2. You can also make changes and build the package again.
    - `./mvnw clean compile`
    - `./mvnw package` makes a new .jar file.
