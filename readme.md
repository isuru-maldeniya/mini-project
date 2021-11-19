# mini-project

## introduction
#### here I have develop a pet store using quakus. 
#### I have used openApi specification to generate swagger documentation here and certain annotations of that to modify the swagger documentation.
#### you can find the swagger documentation from http://localhost:8080/q/swagger-ui/ url after running the project.
#### in this there are two cotrollers. 
#### in first controller PetController (mini-project/src/main/java/com/middleware/mini/project/pet/controller/PetController.java ) you can find these end points

## end points of PetController
####      1. 
####  curl -X 'GET' \
  'http://localhost:8080/data/pet' \
  -H 'accept: */*'
  ####  (get)- this is giving all pets
####      2. 

####  curl -X 'GET' \
  'http://localhost:8080/data/pet/1' \
  -H 'accept: */*'
  ####  (get)- this is giving certain pet with the petId
####      3. 

####  curl -X 'POST' \
  'http://localhost:8080/data/pet/add' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "name": "string",
  "color": "string",
  "owner": "string",
  "birthDate": "2021-11-19",
  "gender": true,
  "typeId": 0
}' 
####  (post)- this is adding the pet into database. here you should define a body with json like this {"name": "string","color":"string","owner": "string","birthDate": "2021-11-19","gender": true,"typeId": 0} gender is true for male false for female
####      4. 

####  curl -X 'DELETE' \
  'http://localhost:8080/data/pet/delete/1' \
  -H 'accept: */*' 
  ####  (delete)- this is deleting certain pet with the petId
####      5. 

####  curl -X 'PUT' \
  'http://localhost:8080/data/pet/update/12' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "name": "string",
  "color": "string",
  "owner": "string",
  "birthDate": "2021-11-19",
  "gender": true,
  "typeId": 0
} 
####  (put)- this is updating certain pet with the petId. here you should define a body with json like this {"name": "string","color":"string","owner": "string","birthDate": "2021-11-19","gender": true,"typeId": 0} gender is true for male false for female.
####      6.

####  curl -X 'GET' \
  'http://localhost:8080/data/pet/filter-search?age=12&name=name&type=1' \
  -H 'accept: */*'  
  ####  this a filter search. this is filtering according to the mane age and type id of pets 
####      7.

####  curl -X 'GET' \
  'http://localhost:8080/data/pet/common-search?content=content' \
  -H 'accept: */*'  
  ####  Here, Searching the pet by color, owner or name. you can give one of them as the parameter in the URL
  
#### in second controller PetTypeController (mini-project/src/main/java/com/middleware/mini/project/pet/controller/PetTypeController.java ) you can find these end points
## end points of PetTypeController
####      1.

####  curl -X 'GET' \
  'http://localhost:8080/data/pet-type' \
  -H 'accept: */*' 
  ####  here it is giving all the types
  
####      2.

####  curl -X 'POST' \
  'http://localhost:8080/data/pet-type/add-type' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "categoryName": "string",
  "family": "string",
  "environment": "string",
  "climate": "string"
}'
#### here this is adding a new category(pettype). In the body It should be specified a json like above

####      3. 

####  curl -X 'DELETE' \
  'http://localhost:8080/data/pet-type/delete/1' \
  -H 'accept: */*'
  #### here it is deleting the pet type by id.
  
####      4.

####  curl -X 'PUT' \
  'http://localhost:8080/data/pet-type/update/1' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "categoryName": "string",
  "family": "string",
  "environment": "string",
  "climate": "string"
}' 
####  here this is updating the category id should spesified in url and json body should be contains.

####      5. 
#### curl -X 'GET' \
  'http://localhost:8080/data/pet-type/1' \
  -H 'accept: */*'
####    Here it is essential to send the pet-category id in url

## how to deploy
#### first you have to clone the project on your local machine. 
#### Mysql server should run on your machine in port 3306. 
#### then open the project using the intellij idea or other ide.
#### now you can run with running button 

## how to test
#### first you should run the project
#### then you should go to mini-project/src/main/java/com/middleware/mini/project/pettype/controller/PetTypeControllerTest.java location in the project to test first test class
#### then you can run the test class with play mark near the left hand siide of the class definition
#### But dont execute deleteById() twise, because after deleting the pet with certain id=1 there is mo that user in the database.
#### then you should go to mini-project/src/main/java/com/middleware/mini/project/pet/controller/PetControllerTest.java location in the project to test second test class
#### then you can run the test class with play mark near the left hand siide of the class definition
#### But dont execute deleteById() twise, because after deleting the pet with certain id=1 there is mo that user in the database.


