# What's in the Fridge? (a simple web app)
 Creating a simple web app utilizing Java 11 and Spring.

 **Story**: I want to add, update, view, delete, or change what's in the fridge
 
 **Constraint**: implement a guardrail to prevent more than 12 cans of soda in the fridge at any time
 
 **Constraint**: there are multiple refrigerators
 
# Running the App
* Clone this project into your favorite IDE (I used IntelliJ)
* launch the SpringBoot app internally from the IDE, or...
* run a gradle build
* run the resulting jar file from the build

# Usage
The app preloads an in memory database with a "Generic Fridge" that contains some food and a beverage, and also a "Beer Fridge" containing 12 soda cans. Therefore, all the endpoints can be used immediately.  Keep in mind though, you will have to remove some soda cans from the Beer Fridge before you're allowed to put any more in. (see resources/data.sql for more details)

## Endpoints
There are 3 endpoints that perform 4 operations

### GET - http://localhost:8080/fridges/food/{foodItemId}
* 200 = foodItem found, returns with foodItem in the body
* 204 = foodItem not found in any fridge
* example request param = 5f510e56-b8d8-4dbf-a79d-be5a640e2554

POST - http://localhost:8080/fridges/
* 204 = item stored to DB
* 400 = soda can guardrail
* example request body = `{
                             "id": "fc29daa0-0b16-4155-a6aa-b3cc4803aef5",
                             "fridgeName": "Beer Fridge",
                             "foodType": "SODA_CAN"
                         }`

DELETE - http://localhost:8080/fridges/food/{foodItemId}
* 204 = foodItem deleted if exists, or there is nothing to delete
* example request param = 5f510e56-b8d8-4dbf-a79d-be5a640e2554

_**Note**: The POST endpoint is intended to be used for both new data entries, and updates._

# Database
Database used is a simple H2 database writing to a local file.

Requirements state that there are multiple refridgerators.

Food Item Repository keeps track of multiple refridgerators by way of a column named "fridgeName".  I initially thought a relational database was the way to go on this, but that proved too cumbersome for such a simple relationship.
### Food Item Repository

| foodId | fridgeName | type |
| --- | --- | --- |
| UUID | varchar | Enum (beverage/food/soda can/other) |

To view the database, visit http://localhost:8080/h2-console
* username = sa
* password = password

# Would Have Liked to..
* Used Kotlin
* Used mockK as the test framework.
* Used Docker and PostgreSQL instead of H2 (Docker cannot be installed on a machine running Windows Home Edition)
* Understood the intricacies of project lombok more
* Used Maven instead of Gradle
    * I typically code on a Mac, but I did this challenge on a Windows box. I am used to using gradle so that is what I initially chose, however that has caused me a lot of trouble with setup.
* more robust edge case testing
* more generic requests (using food type only)
    * this seems like more of an actual use case: "Get me a cold can of soda"
    * currently I am doing: "Get me the can of soda with serial number x from the right fridge"
* more detailed logs. 
    * for instance, when updating a food item, the logs could say "updating food item" instead of "putting food item in the fridge"
* implement Swagger, or create proper RestDocs.
* not have used UUIDs for database
    * UUIDs are great for large scale applications with a lot of  data, but it would have created an easier user experience to have simpler IDs for the Food Items.
* more robust exception handling
* implement hateaus for POST responses, instead of sending back 204
