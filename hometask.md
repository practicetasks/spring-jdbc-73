

1. Создать класс `Product`
    1. добавьте необходимые поля (`id`, `name`, `price`, `category`)
2. Создать интерфейс `ProductDao`
    - добавить методы
      1. findById
      2. findAll
      3. create
      4. update
      5. delete
3. Создать реализацию `ProductDaoImpl`, аналогично реализовать все методы.
4. Создать контроллер `ProductController`
    - добавить эндпойнты
        1. `GET /products`
        2. `GET /products/{id}`
        3. `POST /products`
        4. `PUT /products`
        5. `DELETE /products/{id}`
