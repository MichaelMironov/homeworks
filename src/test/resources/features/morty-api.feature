#language: ru
@api @morty
Функционал:  Проверка api rick and morty

  Сценарий:  Получение информации о Морти
    Дано создать запрос
      | method | url                                       |
      | GET    | https://rickandmortyapi.com/api/character |
    * добавить параметры запроса
      | name | Morty Smith |
    * добавить header
      | Content-type | application/json |
    И отправить запрос
    Тогда статус код 200
    Затем извлечь данные
      | morty_name      | $.results[0].name            |
      | morty_species   | $.results[0]species          |
      | morty_location  | $.results[0]location         |
      | morty_episodes  | $.results[0]episode          |
      | id_last_episode | $.results[0]episode.length() |

  Сценарий: Поиск персонажа в эпизоде
    Дано создать запрос
      | method | url                                                        |
      | GET    | https://rickandmortyapi.com/api/episode/${id_last_episode} |
    * добавить header
      | Content-type | application/json |
    И отправить запрос
    Тогда статус код 200
    * извлечь данные
      | last_char | $.characters[-1] |

  Сценарий:  Получение информации о Джерри
    Дано создать запрос
      | method | url          |
      | GET    | ${last_char} |
    * добавить header
      | Content-type | application/json |
    И отправить запрос
    Тогда статус код 200
    * извлечь данные
      | jerry_name     | $.name     |
      | jerry_species  | $.species  |
      | jerry_location | $.location |

  Сценарий: Сравнение персонажей
    Тогда сравнить значения
      | ${morty_species}  | == | ${jerry_species}  |
      | ${morty_location} | != | ${jerry_location} |
      | ${morty_name}     | != | ${jerry_name}     |


