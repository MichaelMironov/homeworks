#language: ru

@api @jira
@severity=critical
Функционал: Проверка api jira

  Сценарий: Авторизация в системе
  - Создание контекстные переменных для тела запроса
  - Отправка POST запроса с телом авторизации
  - Проверка успешной отправки по статус коду
    * создать контекстные переменные
      | username | mironov   |
      | password | Qwerty123 |
    Дано создать запрос
      | method | url                        | body      |
      | POST   | https://edujira.ifellow.ru | auth.json |
    * добавить header
      | Content-type | application/json |
    И отправить запрос
    Тогда статус код 200