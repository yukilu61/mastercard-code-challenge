# (Mastercard)Code Challenge : City Connection

[city connection](https://github.com/yukilu61/mastercard-code-challenge)

## Description
List of roads is available in a file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities. Provide two cities A and B, determine whether there is a road between city A and city B.

- respond **yes** means there is a road between city A and city B
- respond **no** means there is no road between city A and city B
- respond **city not found** means city A is not existed or city B is not existed

# Configuration

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
     "message": "city: Orlando not found",
     "path": "/api/city/abc"
  }
  ```
   
- isTwoCityConnect API
   
  isTwoCItyConnect API is used to check whether two city have road between them, it accepts two parameter: origin, destination, and API path for checking connection of Boston and Philadelphia is following:
  ```
  http://localhost:8080/api/connection/connected?origin=Boston&destination=Philadelphia
  ```
     
  isTwoCityConnect API will return **yes** if two city are connected, return **no** if two city are not connected and return **city not found** if either origin city or destination city is not found.

## code coverage

All unit tests are under src/test file, the overall coverage for application：

|  Class%   |  Method& |  Line%  |
|  ------   |  ------  |  -----  |
|   100%    |    85%   |   92%   |
     
## server

Currently, No cloud server involved and no Security applied, so only local server is available and local server port is 8080.

Use ```http://localhost:8080``` at first with certain api path to access the information you want!


# Install & Steps to run application

1. Install Java 8: [install java8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
2. use git clone command to clone application from [repository](https://github.com/yukilu61/mastercard-code-challenge.git)
```git clone https://github.com/yukilu61/mastercard-code-challenge.git```

3. open application in favority IDE, add [City.txt](https://github.com/yukilu61/mastercard-code-challenge/blob/main/codechallenge/src/main/resources/city.txt) file under resource file and run application locally
4. input the query path, try this link if application is running: [is Boston and Newark Connected](http://localhost:8080/api/connection/connected?origin=Boston&destination=Newark)
5. congratulations, you get the result!

# Project Structure

mastercard-code-challenge  
├── README.md  
├── codechallenge  
│   ├── pom.xml  
│   ├── src  
│   │   ├── main  
│   │   │   ├──java.com.yuki.codechallenge  
│   │   │   │  ├── CodechallengeApplication.java  
│   │   │   │  ├── controller  
│   │   │   │  │   ├── CityController.java  
│   │   │   │  │   └── ConnectionController.java  
│   │   │   │  ├── dto  
│   │   │   │  │   ├── City.java  
│   │   │   │  │   └── Connection.java  
│   │   │   │  ├── repository  
│   │   │   │  │   ├── CityConnectionRepository.java  
│   │   │   │  │   └── implementation  
│   │   │   │  │       └── CityConnectionRepositoryImpl.java  
│   │   │   │  └── service  
│   │   │   │  │   ├── CityConnectionService.java  
│   │   │   │  │   └── implementation  
│   │   │   │  │       └── CityConnectionServiceImpl.java  
│   │   │   ├──resource  
│   │   │   │  ├── application.properties  
│   │   │   │  └── City.txt  
│   │   └── test  
│   │       └──java.com.yuki.codechallenge  
│   │          ├── CodechallengeApplicationTests.java  
│   │          ├── controllertest  
│   │          │   ├── CityControllerTest.java  
│   │          │   └── ConnectionControllerTest.java  
│   │          ├── repositorytest  
│   │          │   └── CityConnectionRepositoryTest.java  
│   │          └── servicetest  
│   │              └── CityConnectionServiceTest.java  
└── └── target  

# Algorithm & Data Structure

The algorithm used to implement city connection is DFS(Depth First Search). 
Used a hash map to mark connections of current city, if two cities are connected, then mark connected city as **true**, otherwise, marked unconnected as **false**

## DTO

- City
  - (String) cityName: use to store current city name
  - (ArrayList<String>) cityConnection: used to store all connected cities of current city
- Connection
  - (Map<City, List<String>>) connections: KEY value is city, and VALUE value is a list of connected cityname of this city

## DFS(Depth First Search)

1.  check origin city and destination city are existed. 
2.  start from origin city, mark all connected city as true in the hash map: 

    ```Map<City, Boolean> markmap = new HashMap<>();```

    the KEY value is city, The VALUE value is **true** if connected with origin city, **false** if not connected with origin city.

3.  if connected city in the map is already marked as true which means it has already searched, then do nothing, otherwise, the connected city is the first time to be searched, so depth first search connected city and mark it's connected city.
4.  repeat the step2 recursively until all connected cities are marked as true
5.  return the VALUE value of destination city in the map
