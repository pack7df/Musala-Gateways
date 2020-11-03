# Gateways Api
## Description
Management system for a local gategays and peripheral control. 
It's just CRUD services.

## Requirements 
- Crud for Gateways
- Crud for peripherals.

## API Definition
| Action                                               | URL                                        | Method | Parameters                       |
|------------------------------------------------------|--------------------------------------------|--------|----------------------------------|
| List all gateways                                    | /gateway                                   | GET    |                                  |
| Add a gateway                                        | /gateway                                   | POST   | serial, name, ip  				|
| Get a gateway information                            | /gateway/{gatewayId}	                    | GET    |                                  |
| Update a gateway information                         | /gateway			                        | PUT    | id, serial, name, ip				|
| Delete a gategay                                     | /gateway/{gatewayId}                       | DELETE |                                  |
| Add a peripheral                                     | /peripheral/{gatewayId}                    | POST   | uid, vendor, status			    |
| Delete a peripheral                                  | /peripheral/{gatewayId}/{uid}              | DELETE |                                  |
| Update a peripheral 		                           | /peripheral/{gatewayId}/{uid}              | PUT    | uid, vendor, status				|


## Database Model
The database model is a simple MongoDb Database with only one document type.
A Gateway with the following fields:
```
- serial : String
- name   : String
- ip     : String
- id     : String
- peripherals: []{
                  - uid : int
                  - vendor : string 
				  - created : date
                  - status : boolean
                 }
```

## Building the application
- Install JDK 8 or later
- Install a MongoDb server.
- Set environment variable JAVA_HOME pointing to java root directory
- Build the application JAR file

    With maven
    
        $> mvn clean package
        
    With maven wrapper
    
        $> mvnw clean package

The generated application JAR file will be ```./target/<name>-<version>.jar```

## Download packages and and run tests
	mvn clean install

## Running the application
- Set needed environment variables        
        MusalaChallengeMongoUri=mongodb://localhost:27017/Gateways

- Run the application with maven:

        $> mvn spring-boot:run

## postman collection for test:
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5dd2495ed61bd84b4852)