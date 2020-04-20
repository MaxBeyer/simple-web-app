# simple-web-app
 Creating a simple web app utilizing Java 11 and Spring

# Database
Database used is a simple H2 database writing to a local file.
Requirements state that there are multiple refridgerators.  One location where one might store multiple refridgerators is a garage, so the table that holds the refridgerators is called "garage".

The refridgerators in the garage then have a one to many relationship with the food items stored inside of them, using the FoodItem.fridgeId -> Garage.id as the foreign key constraint.

####Garage

| id | fridge name |
| --- | --- | 
| Int | varchar |

####Food Item

| foodId | fridgeId | type | quantity |
| --- | --- | --- | --- |
| Int | Int | Enum (beverage/food/soda can/other) | Int |