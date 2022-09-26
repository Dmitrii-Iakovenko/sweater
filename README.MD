# Sweater app

[Link youtube playlist](https://www.youtube.com/playlist?list=PLU2ftbIeotGoGSEUf54LQH-DgiQPF2XRO)

Проект созданный на основании плейлиста на ютубе с целью набить руку на создании контроллеров, энтити, тестов, 
документаци и так далее.

## Technologies Used
* Java 17
* Spring Boot, MVC, JPA, Security
* Freemarker/Mustache
* Lombok
* Maven

## Install and Run
```bash
git clone https://github.com/Dmitrii-Iakovenko/sweater.git
cd sweater/
chmod +x mvnw
./mvnw spring-boot:run 
```
http://localhost:8080/

## Postgres
По умолчанию используется база данных:  
jdbc:postgresql://localhost:5432/sweater  
login:  postgres  
password:   postgres  

Если надо поменять, правим файл:
src/main/resources/application.yaml
