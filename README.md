# egen-be-challenge

# Class / Content Description
InitClass: - contains main method that is run by spark
UserController: - contains route information and corresponding callback functions
DataParser: - contains static helper methods that assist in  data conversion to / from Json
DataBaseConnect: -  Model Class having methods or services needed
    method description:
    1. createUser() - takes json input and returns a boolean value
    2. updateUser() - takes a Json input and returns boolean value
    3. getAllUsers() - returns a List object of user objects
User, Address, Company: - custon classes to hold user data
ResponseJson - An interface implementation for ResponseTransformer that helps converting list of user objects to json, thereby reducing the code for iterating through the objects.
pom.xml - contains all the dependencies

Features:
1. All the services asked were incorporated in the project.
2. createUser() makes sure only unique users are created and returns false from the model when duplicate user is sent for creation
3. updateUser() will be able to update all the modified fields of existing user only and returns false for non existing user.
3. Unique id is created by using SHA on firstName, lastName and email. So these three are used together to identify a document.
4. MongoDB as data store
5. JUnit 4.x for testing

Decisions made:
I decided to only return boolean values from model except for getAllUsers(), as I found it to be redundant to send the inserted/ updated data back to the user.
But, doing that would have helped me in writing more effective test cases.


 