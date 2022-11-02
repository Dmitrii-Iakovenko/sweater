# Sweater app

Проект - представляет из себя очередной клон Twitter.  
Основная цель - набить руку в основных технологиях популярного стека.  


## Example running application
http://help.wutreg.com  
login: admin  
pass: 123


## Technologies Used
* Java 17
* Spring Boot, MVC, JPA, Security, Mail, Flyway, Validation, Session
* Freemarker/Mustache
* Lombok
* Maven
* Bootstrap 5
* BCrypt
* reCAPTCHA
* nginx


## Deploy (Prod mode)
```bash
./scripts/deploy.sh
```

## Install and Run (Dev mode)
```bash
git clone https://github.com/Dmitrii-Iakovenko/sweater.git
cd sweater/
chmod +x mvnw
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
http://localhost:8080/


## Run with IDEA (Dev mode)
Run \ Edit Configuration \ Program arguments:  
--spring.profiles.active=dev


## Postgres
По умолчанию используется база данных:  
jdbc:postgresql://localhost:5432/sweater  
login:  postgres  
password:   postgres  

Если надо поменять, правим файл:
src/main/resources/application.yaml (для прода)
src/main/resources/application-dev.yaml (для dev)


## Mail
Для рассылки используется smtp сервер gmail  
Настройки хранятся в application.yaml  
Нужно указать ваш логин и пароль от приложения (не путать с паролем от почты).  
[Настройки для почты Gmail](https://support.google.com/mail/answer/7104828)  
[Подробнее о пароле приложения](https://support.google.com/accounts/answer/185833)  


## Upload 
Картинки добавленные к приложениям сохраняются в папку:  
'/upload' (prod)  
'C:\Git\sweater\upload' (dev)  
Должно совпадат с alias в nginx!!!  
Если нужно поменять - application.yaml  


## reCAPTCHA
[admins panel](https://www.google.com/recaptcha/admin)  
Ключи сайта и секретный - можно посмотреть в настройках  
Для работы лучше подходит reCAPTCHA v2  


## nginx
config: /etc/nginx/sites-enabled/default  
alias - должен совпадать с upload.path в application.yaml  
reload config: nginx -s reload  
если файлы не видны в свиттере - chmod -R 777 . (на весь путь)  


