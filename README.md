# demo-angular-withspring-boot
Small demo application with Angular, Spring Boot &amp; MongoDB

---
### Hints
* You need to run a MongoDB docker container
    * Forward ports `27017`, `27017`
* If you use Windows, you may have to start a VM

```bash
#!/bin/bash

# Run the docker container for MongoDB
echo -e "\\nStarting docker container MongoDB...\\n"
docker run --rm --name mongodb -d -p 27017:27017 mongo
echo -e "\\nTo stop the database, execute: docker stop mongodb\\n"
```
* In order to start the Angular app, make sure you have the Angular CLI installed
    * `npm install -g @angular/cli`
