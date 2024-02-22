Детали реализации:

1. Ветка main:
   Реализована аутентификация - form login
   Доступ к страницам:
   /auth/login, /auth/registration - доступны всем пользователям
   /news - доступна зарегистрированным пользователям, руководителям и администраторам
   /person - доступна руководителям и администраторам
   /news/add - доступна администраторам
2. Ветка auth-basic:
   Реализована аутентификация - basic
   ПОСЛЕ СТАРТА ПРОГРАММЫ УЖЕ СУЩЕСТВУЕТ НЕСКОЛЬКО ЗАРЕГИСТРИРОВАННЫХ ПОЛЬЗОВАТЕЛЕЙ
   Доступ к страницам:
   /auth/login - доступна всем пользователям
   /news, / - доступны зарегистрированным пользователям, руководителям и администраторам
   /person - доступна руководителям и администраторам
   /news/add, /auth/registration - доступны администраторам
3. Ветка auth-JWT:
   Реализована аутентификация с помощью JWT токена
   /auth/login - доступна всем пользователям
   /news - доступна зарегистрированным пользователям, руководителям и администраторам
   /person - доступна руководителям и администраторам
   /auth/registration - доступна администраторам
   Для удобства тестирования, можно воспользоваться postman-коллекцией (сгенерированный токен нужно будет вставлять
   значением в заголовок Authorization). Коллекция находится в корне проекта (JWT токен.postman_collection.json)

Еще несколько моментов:

1. Тесты реализованы только для ветки main
2. Знаю, что лучше было создать три разных проекта)
3. В целях сокращения времени выполнения дз, использовала библиотеку lombok, не использовала классы DTO, сократила
   возможный для пользователя функционал до минимума