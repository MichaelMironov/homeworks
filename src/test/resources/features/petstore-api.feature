#language: ru
@api
Функционал: Проверка api petstore

  Сценарий: Авторизация в системе
    * создать контекстные переменные
      | username | mironov   |
      | password | Qwerty123 |
    Дано создать запрос
      | method | url                                       | body      |
      | GET    | https://petstore.swagger.io/v2/user/login | auth.json |
    * добавить header
      | Content-type | application/json |
      | Accept       | application/json |
    И отправить запрос
    Тогда статус код 200
    * извлечь данные
      | session | $.message |

  Сценарий: Создание питомца
    * создать контекстные переменные
      | name | Jack |
    Дано создать запрос
      | method | url                                | body     |
      | POST   | https://petstore.swagger.io/v2/pet | pet.json |
    * добавить header
      | Content-type | application/json |
      | Accept       | application/json |
    И отправить запрос
    Тогда статус код 200
    * извлечь данные
      | pet_id | $.id |

  Сценарий: Поиск созданного питомца
    Дано создать запрос
      | method | url                                          | body     |
      | GET   | https://petstore.swagger.io/v2/pet/${pet_id} | pet.json |
    * добавить header
      | Content-type | application/json |
      | Accept       | application/json |
    И отправить запрос
    Тогда статус код 200
