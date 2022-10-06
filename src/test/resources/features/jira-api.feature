#language: ru

Функционал: Проверка api cucumber

  Сценарий: Получение списка задач
    * сгенерировать переменные
      | username | mironov   |
      | password | Qwerty123 |
    Дано создать запрос
      | method | url                         | body      |
      | POST   | https://edujira.ifellow.ru | auth.json |
    * добавить header
      | Content-type | application/json |
    И отправить запрос
    Тогда статус код 200
    И извлечь данные
      | Set-Cookie | $.session |