# Проект с автотестами API для сервиса Яндекс.Самокат

---
В проекте добавлены автотесты на эндпоинты:
- создание курьера
- авторизация курьером
- создание заказа
- получение заказа по его номеру

TODO:  
- удаление курьера
- принятие заказа
---
## Технологии

| <img height="50" src="https://proxys.io/files/blog/Java/javalogo.png" width="50"/>  | Java 11            |
|-------------------------------------------------------------------------------------|--------------------|
|<img height="50" src="https://cdn.fs.teachablecdn.com/L2rtxPaRxa4am1VtNegg" width="50"/>| Maven 4.0.0        |
|<img height="50" src="https://avatars.githubusercontent.com/u/874086?s=200&amp;v=4" width="50"/>| JUnit 4.13.1       |
|<img height="50" src="https://avatars.githubusercontent.com/u/19369327?s=280&v=4" width="50"/>| REST-Assured 4.4.0 |
|<img height="50" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9HMmuigtfRA2I1XvPSNlRVjl3A4Za7GWZbQ&amp;usqp=CAU" width="50"/>| Allure 2.15.0      |
---
## Запуск тестов и просмотр Allure-отчета

1. Для запуска тестов выполнить команду: `mvn clean test`
2. После завершения прогона для просмотра отчета выполнить: `mvn allure:serve`

---
[Ссылка на сервис Яндекс.Самокат](http://qa-scooter.praktikum-services.ru/)