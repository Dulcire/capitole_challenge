# Capitole Challenge

# Description
Application to simulate a Capitole Response using Springboot

### Pre - requisites ###

* Git
* Install JDK 17
* Define JDK in JAVA_HOME
* Install Apache Maven 3
* Install Docker
    

***You can change DB properties in application.yml***
*** or If you are using the docker image you can set DB properties in docker-compose.yaml***

* ---------------------------------------------------------------------------------------------------------------
### Run Application ###
* Clone capitole-challenge to in/your/path:
*     https://github.com/Dulcire/capitole_challenge.git
***There are two ways to run the application***
### Docker ### 
* If you want to run the application by Docker follow this steps
* Go to your capitole-challenge:
*      cd capitole-challenge
*      docker build -t capitole-challenge:latest .
*      Option 1: docker compose up
*      Option 2: docker run --rm -p 8080:8080 --name capitole-challenge capitole-challenge

### Manually ###

* Go to your capitole-challenge: 
*     cd capitole-challenge
* execute:
*     mvn clean install  
* execute:
*     java -jar ./target/capitole-challenge-0.0.1.jar

####Swagger route ####
* If you want to test the application you can go to the following path in your browser: 
*     http://localhost:8080/swagger-ui/index.html
* if you start the application in other route should user this: 
*     http://{{yourserver}}:{{port}}/swagger-ui.html/index.html

# Test Cases #
### Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA) ###
* Input 
* applicationDate: 2020-06-14 10:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* Status 200
* {
*  "response": [
* {
* "productCode": "35455",
* "brandCode": "1",
* "priceList": 23.0,
* "date": "2020-06-14T10:00:00.000+00:00",
* "pvp": 25.45
* }
* ]
* }

### Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA) ###
* Input
* applicationDate: 2020-06-14 16:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* Status 200
* {
*  "response": [
* {
* "productCode": "35455",
* "brandCode": "1",
* "priceList": 23.0,
* "date": "2020-06-14T16:00:00.000+00:00",
* "pvp": 25.45
* }
* ]
* }

### Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA) ###
* Input
* applicationDate: 2020-06-14 21:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* {
* "title": "There are no registers of product 35455 and brand 1 at 2020-06-14 21:00:00.0",
* "detail": "com.challenge.capitole.capitolechallenge.exception.NotFoundException: There are no registers of product 35455 and brand 1 at 2020-06-14 21:00:00.0"
* }

### Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA) ###
* Input
* applicationDate: 2020-06-15 10:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* {
* "response": [
* {
* "productCode": "35455",
* "brandCode": "1",
* "priceList": 33.0,
* "date": "2020-06-14T10:00:00.000+00:00",
* "pvp": 35.5
* }
* ]
* }

### Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA) ##
* Input
* applicationDate: 2020-06-16 21:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* {
* "title": "There are no registers of product 35455 and brand 1 at 2020-06-14 21:00:00.0",
* "detail": "com.challenge.capitole.capitolechallenge.exception.NotFoundException: There are no registers of product 35455 and brand 1 at 2020-06-14 21:00:00.0"
* }

### Test 6: petición a las 21:00 del día 16 del producto 35460 (No Existe)  para la brand 1 (ZARA) ##
* Input
* applicationDate: 2020-06-16 21:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* {
* "title": "Product 35460 not found ",
* "detail": "com.challenge.capitole.capitolechallenge.exception.ValidationException: Product 35460 not found "
* }

### Test 7: petición a las 21:00 del día 16 del producto 35455   para la brand 2 (No Existe) ##
* Input
* applicationDate: 2020-06-16 21:00:00
* productCode: 35455
* brandCode: 1
* ------------------------------------
* Output
* {
* "title": "Brand 2 not found ",
* "detail": "com.challenge.capitole.capitolechallenge.exception.ValidationException: Brand 2 not found "
* }