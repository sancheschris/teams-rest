

1 - First of all, I created the structure of the project

- Creating a specific package for each responsability.

- It was implemented on the application the exceptions in order to give details. When we call the user client api if everything is ok it will continue, otherwise it will throw an
  specific excepetion for RestTeample and also a ResourceExceptionHandler to return all the created exceptions.

- Created the Response model that will receive the data from the users and teams api.

- Generated the datasource config to get values from the application.properties.

- The classes user and team were implemented and there are relationship many to many and then create another class called UserTeam where
  we get the primary key from team and user and in this table added a extra column roles which is an enum with the three values (Developer, Tester and Product Owner)

- In this project was used the swagger UI to show our endpoints better and which endpoints are available.


- The issue to get all the users is that we only get have two fields (id and display name) and after getting it we need to store this and then
  call the other method to get the users by id who will bring us more data about the user.
  
{
  "id":"fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
  "firstName":"Gianni",
  "lastName":"Wehner",
  "displayName":"gianniWehner",
  "avatarUrl":"https://cdn.fakercloud.com/avatars/rude_128.jpg",
  "location":"Brakusstad"
  }

  However to get all the users by id to get full details of it, unfourtunately that will be in a loop and will be slow to do it one at time.
  Also, it happens the same way with teams and teams by id.

In my case I getAllThe users and then call save it in a list and then I will loop this list and set the userId in other method who will getUserById.


**Assume this is an API you can not change, but if you could improve the Team or
User API what would you ask the developers for to aid you with your task.**

I would ask them to change the returning of getAllUser, to return all the field of this object or the most important and doing this
it will do a request to the API only once and then I can save it into my database and then would save more time and performance.
And this also applies to the teams api as well.
Other possibility is to do create a pagination on the api and then we can request from the size we want to.

**What happens if data you are using gets deleted?**
If I could not save it my database before and I will do a request, then the application
will throw an exception.


**In order to Run the application, first of all you need to run up the docker-compose.yaml file on the application's root.**

1 - run the command on the terminal docker-compose up -d

2 - to acess the database, go to http://localhost:15432 and type the e-mail: postgres@email.com and the password: postgres.

3 - before running the spring boot application, set the environment variable to spring.profiles.active=dev

4 - run the application.

5 - The application have two controllers on the swagger-ui: one to get the data from the api we consume and save it on a database,
and the other is the Teams Rest.
