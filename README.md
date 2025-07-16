Один из двух микросервисов приложения "Магазин". 
Для проверки работоспособности необходимо развернуть два репозитория.
Ссылка на второй репозиторий: https://github.com/holodno-mne/order-service.git
Настройка подключения к БД в application.properties. БД - PostgeSQL. 
Порт у inventory-service - 8080, у order-service - 8081.
Для проверки работы в Postman необходимо запустить оба микросервиса.
Команды для проверки inventory-service:
1. Получение списка всех продуктов - GET http://localhost:8080/products
2. Получить продукт по ID - GET http://localhost:8080/products/ваш_id
3. Создать новый продукт - POST http://localhost:8080/products
   Headers:
     Content-Type: application/json
   Body:
    {
      "name": "Monitor",
      "price": 250.0,
      "quantity": 20
    }
   (это пример, можете подставить свои значения)
5. Изменение продукта - PUT http://localhost:8080/products/ваш_id
   Headers:
     Content-Type: application/json
   Body:
     {
       "name": "Monitor UltraWide",
       "price": 350.0,
       "quantity": 10
     }
   (это пример, можете подставить свои значения)
7. Удаление продукта - DELETE http://localhost:8080/products/ваш_id
   
Команды для проверки order-service:
1. Получить список всех заказов - GET http://localhost:8081/orders
2. Создать заказ - POST http://localhost:8081/orders
   Headers:
     Content-Type: application/json
   Body:

      {
  "items": [
   
    {
      "productId": 8,
      "quantity": 1
    }
  ]
}
   
4. Получить заказ по id - GET http://localhost:8081/orders/ваш_id
5. Удалить заказ - DELETE http://localhost:8081/orders/ваш_id
