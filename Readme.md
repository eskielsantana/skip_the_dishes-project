## SkipTheDishes - Interview Challenge

## About

This is a project I created in **2018** as an applicant for the **Software Engineer** role at [SkipTheDishes](https://www.skipthedishes.com/). 

I kept this project untouched so far because it was a test and even nowadays I am just updating this readme

There is a project with some improvements added you can check at [this link](https://github.com/eskielsantana/skip_the_dishes-improved-project).

## Goal

- Create a microservice application containing an API for the following requests:

  - Receive a request with new customer data and register it on a database
  
  - Receive a customer request to log in and return its access token in case the given password matches the registered
  
  - Receive a request to return a registered order by the given id
  
  - Receive a request to return all the registered orders
  
  - Receive a request to register a new order
  
  - Receive a request to cancel an already existent order
  
  - Receive a request to return the status of the order by the given id
  
- All the order-related requests must receive a token used to authenticate if the user is allowed to execute the related action.

- The customer login request has a simple but efficient password encryption/encryption system so its password will not be stored in a raw format


## Stack for this Project

- Java - SpringBoot Framework (MVC Architecture)

- MySql + JPA - Database and data persist

- Swagger - Used for agile endpoint documentation and test

## Install

### Download the repository

  $ git clone https://github.com/eskielsantana/skip_the_dishes-improved-project.git

### Run the required Mysql statements

- Having a MySql instance running, execute the statements inside of the _skipthedisheschallenge.sql_ file located in the root of this project

### Update the properties if needed

- Update data source config on the _resources/application.properties_ file in case you are using a different setup than the one defined there.

### Generate the jar file

  $ cd skip_the_dishes-improved-project && mvn clean install

### Run the application (Windows or MacOS)

  $ java -jar SkipTheDishesChallenge-0.0.1-SNAPSHOT.jar

## Usage

### Swagger

- You can access the web service using the swagger page "[http://localhost:8080/](http://localhost:8080/)"

### Endpoints access

- You will need a token to interact with any of the order-related endpoints, so go through the following steps to get one:

  - Register a client by running the following post request:
  ```
  curl --location 'http://localhost:8080/api/v1/Customer' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "email": "email@example.com",
      "name": "jonhdoe",
      "address": "123 Street",
      "password": "password"
  }'
  ```
  
  - Log in with the email and password you used above on the following request:
  ```
  curl --location 'http://localhost:8080/api/v1/Customer/auth' \
  --header 'email: email@example.com' \
  --header 'password: password'
  ```
  
> **Note:**
>
> This is a project from **2018** and since I am not maintaining it since that year it might have dependencies issues nowadays and the build might fail but still a good content for study

  
## Author
- Ezequiel Sant'Ana Junior - [LinkedIn](https://www.linkedin.com/in/ezequiel-santana/)

> **Note I left during the fair:**
> 
> Finally, I would like to say thank you for the opportunity you gave to all of us and for the experience of being participated in this fair. I have met many awesome people here.
> I'm pretty sure I will always remember this day.
