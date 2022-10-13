#language: ru

@api @morty
@severity=normal
Функционал:  Проверка api rick and morty

  Сценарий:  Получение информации о Морти
  - Отправка GET запроса по имеющимся данным, а именно имя персонажа
  - Проверка успешного выполнения
  - Проверка полученных данных
  - Извлечение данных запроса для последующего использования
    Дано создать запрос
      | method | path       |
      | GET    | /character |
    Также добавить параметры запроса
      | name | Morty Smith |
    И добавить header
      | Content-type | application/json |
    Если отправить запрос
    То статус код 200
    Тогда извлечь данные
      | morty_id        | $.results[0].id              |
      | morty_name      | $.results[0].name            |
      | morty_species   | $.results[0]species          |
      | morty_location  | $.results[0]location         |
      | morty_episodes  | $.results[0]episode          |
      | id_last_episode | $.results[0]episode.length() |
    И сравнить значения
      | ${morty_id}      | != | null        |
      | ${morty_name}    | == | Morty Smith |
      | ${morty_species} | == | Human       |

  Сценарий: Поиск персонажа в эпизоде
  - Отправка GET запроса по id последнего эпизода
  - Проверка успешного выполнения
  - Проверка полученных данных
  - Извлечение последнего персонажа
    Дано создать запрос
      | method | path                        |
      | GET    | /episode/${id_last_episode} |
    Также добавить header
      | Content-type | application/json |
    Если отправить запрос
    То статус код 200
    Затем извлечь данные
      | last_char | $.characters[-1] |
    И сравнить значения
      | last_char | != | null |

  Сценарий: Получение информации о Джерри
  - Отправка GET запроса на URL последнего персонажа
  - Проверка успешного выполнения
  - Проверка полученных данных
  - Извлечение данных последнего персонажа
    Дано создать запрос
      | method | url          |
      | GET    | ${last_char} |
    Также добавить header
      | Content-type | application/json |
    Если отправить запрос
    Тогда статус код 200
    Затем извлечь данные
      | jerry_id       | $.id       |
      | jerry_name     | $.name     |
      | jerry_species  | $.species  |
      | jerry_location | $.location |
    И сравнить значения
      | $.{jerry_id}     | != | null        |
      | ${jerry_name}    | == | Young Jerry |
      | ${jerry_species} | == | Human       |

  Сценарий: Сравнение персонажей
  - Сравнение полученной информации по персонажам
    Тогда сравнить значения
      | ${morty_species}  | == | ${jerry_species}  |
      | ${morty_location} | != | ${jerry_location} |
      | ${morty_name}     | != | ${jerry_name}     |


