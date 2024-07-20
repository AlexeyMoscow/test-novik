# Test-cases

[getUsers]: http://3.73.86.8:3333/user/get
[createUser]: http://3.73.86.8:3333/user/create

### 1. Создание нового пользователя
### Приоритет - 1

- Выполнить POST запрос к [createUser], передав в body валидные значения полей username, email, password
#### Ожидаемый результат:
- Статус код - 200
- Поле success в ответе содержит - true
- Поле details содержит объект с полями (username, password, email, created_at, updated_at, id)

### 2. Получение списка созданных юзеров
### Приоритет - 2

- Выполнить GET запрос к [getUsers]
#### Ожидаемый результат:
- Статус код - 200
- В ответе содержится массив объектов с полями (username, password, email, created_at, updated_at, id)