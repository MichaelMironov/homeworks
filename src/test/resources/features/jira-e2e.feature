#language: ru
@jira
Функционал: домашнее задание

  Предыстория:
    Дано пользователь авторизован в системе как user

  Сценарий: Проверка количества заведенных задач
    Когда в разделе Задачи выбрать подраздел Поиск задач
    Тогда проверить общее количество заведенных задач в проекте

  Сценарий: Проверка статуса и версии исправления задачи
    Когда в разделе Задачи выбрать подраздел Поиск задач
    Затем перейти в задачу с названием - TestSelenium_bug и id - 21967
    Тогда статус задачи - Сделать, исправить в версиях - 2.0

  Сценарий: Проверка изменения статуса задачи на скрам доске
    Тогда в разделе Задачи выбрать подраздел Поиск задач
    Затем создать новую задачу с типом Ошибка, именем TestCucumber и описанием kent321
    И перенести новую задачу на скрам-доске в колонку Выполнено
    Тогда статус новой задачи - Готово