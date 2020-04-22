# simple-web-app
 Creating a simple web app utilizing Java 11 and Spring.

 Story: I want to add, update, view, delete, or change what's in the fridge
 
 Constraint: implement a guardrail to prevent more than 12 cans of soda in the fridge at any time
 
 Constraint: there are multiple refrigerators
 
# Running the App
* Clone this project into your favorite IDE (I used IntelliJ)
* launch the SpringBoot app internally from the IDE, or...
* run a gradle build
* run the resulting jar file from the build

# Endpoints
There are 3 endpoints that perform 4 operations

GET - http://localhost:8080/fridges/food/{foodItemId}

POST

DELETE

# Database
Database used is a simple H2 database writing to a local file.
Requirements state that there are multiple refridgerators.
The refridgerators in the fridge repository then have a one to many relationship with the food items stored inside of them, using the FoodItem.fridgeId -> Fridge.id as the foreign key constraint.

### Fridge Repository

| id | fridge name |
| --- | --- | 
| Int | varchar |

### Food Item Repository

| foodId | fridgeId | type |
| --- | --- | --- | --- |
| Int | Int | Enum (beverage/food/soda can/other) |

To view the database, visit http://localhost:8080/h2-console
* username = sa
* password = password

# Would Have Liked to..
* Used Kotlin
* Used mockK as the test framework.
* have github actions set up and configured for automatic builds/tests
* Used Docker and PostgreSQL instead of H2 (Docker cannot be installed on a machine running Windows Home Edition)
* Understood the intricacies of project lombok more
* Used Maven instead of Gradle
    * I typically code on a Mac, but I did this challenge on a Windows box. I am used to using gradle so that is what I initially chose, however that has caused me a lot of trouble with setup.
* more robust edge case testing
* more generic requests (using food type only)
    * this seems like more of an actual use case: "Get me a cold can of soda"
    * currently I am doing: "Get me the can of soda with serial number x from fridge with the serial number y"
* more detailed logs. 
    * for instance, when updating a food item, the logs could say "updating food item" instead of "putting food item in the fridge"
* implement Swagger, or create proper RestDocs.
* not have used UUIDs for database
    * UUIDs are great for large scale applications, but it would have created an easier user experience to have simpler IDs for the Fridges and Food Items, or to even use Strings.  That way we could store food by foodName to fridgeName instead of by foodItemId to fridgeId.