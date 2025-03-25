# Запуск проекта

## Клонируем:
```sh
git clone https://github.com/Dewreeh/CaloriesAndDishesAccountring.git
```

## Далее самый простой вариант (делать в основной директории проекта):
```sh
docker compose build
docker compose up
```
Приложение и БД запустятся на 8080 и 5432 портах соответственно.  
В `docker-compose.yml` я добавил postgres, который поднимается с нужной структурой БД и тестовыми данными.  
Так что можно их потестить Postman-коллекцией.

## Если не через Docker, то в `application.properties` поменять:
### Было:
```properties
spring.datasource.url=jdbc:postgresql://db:5432/{ваше название БД}
```
### На:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/{ваше название БД}
```
и поставить свои пароли.

### Затем выполнить:
```sh
mvn clean install  
mvn spring-boot:run
```
Но в таком случае **не будет БД со структурой, под которую делалось приложение, и тестовыми данными**.

  
  
