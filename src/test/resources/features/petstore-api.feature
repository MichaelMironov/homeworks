#language: ru

@api @petstore
Функционал: Проверка api petstore

  @severity=critical
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
    Дано создать контекстные переменные
      | name   | Hatiko |
      | status | waits  |
    Тогда создать запрос
      | method | url                                | body     |
      | POST   | https://petstore.swagger.io/v2/pet | pet.json |
    И добавить header
      | Content-type | application/json |
      | Accept       | application/json |
    Когда отправить запрос
    Тогда статус код 200
    Затем извлечь данные
      | pet_id | $.id     |
      | status | $.status |
    И сравнить значения
      | ${pet_id} | != | null  |
      | ${status} | == | waits |

  Сценарий: Поиск созданного питомца
    Дано создать запрос
      | method | url                                          | body     |
      | GET    | https://petstore.swagger.io/v2/pet/${pet_id} | pet.json |
    * добавить header
      | Content-type | application/json |
      | Accept       | application/json |
    И отправить запрос
    Тогда статус код 200

  Сценарий: Поиск списка питомцев по статусу
    Дано создать запрос
      | method | url                                             |
      | GET    | https://petstore.swagger.io/v2/pet/findByStatus |
    Также добавить параметры запроса
      | status | sold |
    И добавить header
      | Content-type | application/json |
      | Accept       | application/json |
    Когда отправить запрос
    Тогда статус код 200
    Затем извлечь данные
      | sold_pets | $..status |
