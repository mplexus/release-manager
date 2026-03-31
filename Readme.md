# Install
mvn install

# Run
mvn spring-boot:run

# Test
GET http://localhost:8080/api/v1/releases/services?systemVersion=1

POST http://localhost:8080/api/v1/releases/deploy
{
"name": "cramo",
"version": 3
}