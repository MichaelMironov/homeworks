#language: ru
@api
Функционал: Проверка api reqres

  Сценарий: Отправка POST запроса json телом
    Дано создать контекстные переменные
      | name | Potato    |
      | job  | Eat Maket |
    Тогда создать запрос
      | method | url                         | body        |
      | POST   | https://reqres.in/api/users | potato.json |
    И добавить header
      | Content-Type | application/json |
    * отправить запрос
    Тогда статус код 201
    И извлечь данные
      | actual_name | $.name |
      | actual_job  | $.job  |
      | actual_id   | $.id   |
    Тогда сравнить значения
      | ${name} | == | ${actual_name} |
      | ${job}  | == | ${actual_job}  |
