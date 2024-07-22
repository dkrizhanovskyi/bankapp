### BankApp 

BankApp is a web application for managing bank accounts. It allows users to create, view, update, and delete bank accounts through a REST API.

## Requirements

- Java 17
- Maven
- MySQL
- Redis

## Installation and Setup

1. Clone the repository:

   ```sh
   git clone https://github.com/your-repo/bankapp.git
   ```

2. Navigate to the project directory:

   ```sh
   cd bankapp
   ```

3. Configure MySQL and Redis databases, then update `application.properties` with your settings:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
   spring.datasource.username=root
   spring.datasource.password=1111
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.thymeleaf.cache=false
   spring.cache.type=redis
   spring.redis.host=localhost
   spring.redis.port=6379
   management.endpoints.web.exposure.include=*
   management.endpoint.prometheus.enabled=true
   ```

4. Build and run the application:

   ```sh
   ./mvnw spring-boot:run
   ```

5. The application will be available at `http://localhost:8080`.

## Usage

### Create an Account

```sh
curl -X POST http://localhost:8080/api/accounts \
     -H "Content-Type: application/json" \
     -d '{"name": "John Doe", "balance": 1000.0}'
```

### Get an Account by ID

```sh
curl http://localhost:8080/api/accounts/1
```

### Update an Account

```sh
curl -X PUT http://localhost:8080/api/accounts/1 \
     -H "Content-Type: application/json" \
     -d '{"name": "John Doe", "balance": 2000.0}'
```

### Delete an Account

```sh
curl -X DELETE http://localhost:8080/api/accounts/1
```

### Get All Accounts

```sh
curl http://localhost:8080/api/accounts
```

## Testing

To run the tests, use:

```sh
./mvnw test
```

## Contributing

Pull requests and suggestions for improvements are welcome.

## License

MIT

