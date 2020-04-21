# simple-web-app
 Creating a simple web app utilizing Java 11 and Spring

# Database
Database used is a simple H2 database writing to a local file.
Requirements state that there are multiple refridgerators.  One location where one might store multiple refridgerators is a garage, so the table that holds the refridgerators is called "garage".

The refridgerators in the garage then have a one to many relationship with the food items stored inside of them, using the FoodItem.fridgeId -> Garage.id as the foreign key constraint.

### Garage

| id | fridge name |
| --- | --- | 
| Int | varchar |

### Food Item

| foodId | fridgeId | type |
| --- | --- | --- | --- |
| Int | Int | Enum (beverage/food/soda can/other) |

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