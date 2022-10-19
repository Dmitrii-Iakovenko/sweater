# Sweater app

[Link youtube playlist](https://www.youtube.com/playlist?list=PLU2ftbIeotGoGSEUf54LQH-DgiQPF2XRO)

Проект созданный на основании плейлиста на ютубе с целью набить руку на создании контроллеров, энтити, тестов, 
документаци и так далее.

## Technologies Used
* Java 17
* Spring Boot, MVC, JPA, Security, Mail
* Freemarker/Mustache
* Lombok
* Maven
* Bootstrap 5

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

## Mail
Для рассылки используется smtp сервер gmail  
Настройки хранятся в application.yaml  
Нужно указать ваш логин и пароль от приложения (не путать с паролем от почты).  
[Настройки для почты Gmail](https://support.google.com/mail/answer/7104828)  
[Подробнее о пароле приложения](https://support.google.com/accounts/answer/185833)  


## Upload 
Картинки добавленные к приложениям сохраняются в папку:  
"C:\Git\sweater\upload"  
Если нужно поменять - application.yaml

## How to use
Link: http://localhost:8080/
