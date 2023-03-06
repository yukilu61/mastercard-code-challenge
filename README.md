# (Mastercard)Code Challenge : City Connection

[![city connection]](https://github.com/yukilu61/mastercard-code-challenge)

## description
List of roads is available in a file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities. Provide two cities A and B, determine whether there is a road between city A and city B.

- respond **yes** means there is a road between city A and city B
- respond **no** means there is no road between city A and city B
- respond **city not found** means city A is not existed or city B is not existed

# configuration

City Connection Project is a microservice application based on Spring Boot and Java 8, it provides three main APIs and basic configuration information is listed below: 

## framework & version

- Java 1.8
- Spring Boot 2.7.9
	- spring boot actuator
	- spring boot web
	- spring boot devtools
	- spring boot test
	- lombok
- Junit 5

## APIs

- getAllCity API 
  
  getAllCity API is used to get all cities' name and connected cities, accepts no parameter and API path is:
  
  ```
  http://localhost:8080/api/city/allcity
  ```
  getAllCit API will return all cities in the City.txt file, the structure of return body contains cityName and connected cities:
  
  ```
  {
    "cityName": "Newark",
    "cityConnection": [
      "Philadelphia",
      "Boston"
      ]
  }
  ```
  
- getCity API

  getCity API is used to get one certain city information according to the cityName, it accepts one parameter: cityname, and API path for getting city Boston is following:
   
  ```
  http://localhost:8080/api/city/Boston
  ```
  getCity API will return city information if city existed, otherwise return error with **city: cityName not found** message.
   
  search New York city which is existed:
  ```
  {
    "cityName": "Boston",
    "cityConnection": [
      "New York"
      ]
  }
  ```
  search Orlando city which is not existed:
  ```
  {
     "timestamp": "2023-03-06T07:00:34.063+00:00",
     "status": 500,
     "error": "Internal Server Error",
     "message": "city: abc not found",
     "path": "/api/city/abc"
  }
  ```
   
- isTwoCityConnect API
   
  isTwoCItyConnect API is used to check whether two city have road between them, it accepts two parameter: origin, destination, and API path for checking connection of Boston and Philadelphia is following:
  ```
  http://localhost:8080/api/connection/connected?origin=Boston&destination=Philadelphia
  ```
     
  isTwoCityConnect API will return **yes** if two city are connected, return **no** if two city are not connected and return **city not found** if either origin city or destination city is not found.
     
## server port

the server port is 8080.

# Install & Steps to run application locally

1. install Java 8: [install java8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
2. git clone 
3. run application
4. input the path like [is Boston and Newark Connected](http://localhost:8080/api/connection/connected?origin=Boston&destination=Newark)
5. congrulations, you get the result!

# Structure

mastercard-code-challenge
|_README.md
|_codechallenge
| |_codechallenge.iml
| |_HELP.md
| |_mvnw  
| |_mvnw.cmd 
| |_pom.xml 
| |_src
| | |_main
| | | |_java.com.yuki.codechallenge
| | | | |_CodechallengeApplication.java
| | | | |_controller
| | | | | |_CityController.java
| | | | | |_ConnectionController.java
| | | | |_dto
| | | | | |_City.java
| | | | | |_Connection.java
| | | | |_repository
| | | | | |_CityConnectionRepository.java
| | | | | |_implementation
| | | | | | |_CityConnectionRepositoryImpl.java
| | | | |_service
| | | | | |_CityConnectionService.java
| | | | | |_implementation
| | | | | | |_CityConnectionServiceImpl.java
| | | |_resource
| | | | |_application.properties
| | | | |_City.txt
| | |_test
| | | |_java.com.yuki.codechallenge
| | | | |_CodechallengeApplicationTests.java
| | | | |_controllertest
| | | | | |_CityControllerTest.java
| | | | | |_ConnectionControllerTest.java
| | | | |_repositorytest
| | | | | |_CityConnectionRepositoryTest.java
| | | | |_servicetest
| | | | | |_CityConnectionServiceTest.java
| |_target



   
   
   
   
   
   
   
   
   
   
   
   
   
