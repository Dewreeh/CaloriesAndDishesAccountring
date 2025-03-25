Запуск проекта

Клонируем:
  git clone https://github.com/Dewreeh/CaloriesAndDishesAccountring.git

Далее самый простой вариант:
  docker compose build
  docker compose up
Приложение и БД запустятся на 8080 и 5432 портах соответственно
В docker-compose я добавил postgres, который поднимается с нужной структурой БД и тестовыми данными. Так что можно их потестить postman-коллекцией.

Если не через docker, то в application.properties поменять:
    spring.datasource.url=jdbc:postgresql://db:5432/{ваше название БД}
  на 
    spring.datasource.url=jdbc:postgresql://localhost:5432/{ваше название БД}
  и поставить свои пароли.
    mvn clean install  
    mvn spring-boot:run
  
Но в таком случае не будет БД со структурой под которую делалось приложение и тестовыми данными


  
  
