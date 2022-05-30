# suburb-postcode-api
**RestAPI :** code test for postcode search

A simple REST API in spring boot that accepts a list of Suburb Names and Postcodes in the HTTP request body, 
and persist the data in a database (used in memory database).

The API also have an endpoint that receives the postcode range and returns in the response body the list of Suburb Names belonging to that postcode range, 
sorted alphabetically as well as the total number of characters of all names combined.

**Build:**

>mvn clean package

**Run:**

>./mvnw spring-boot:run

**Info:**

Used in memeory database **(h2)**

This service port: 8080

Enabled **Actuator** and **Swagger 2.0**

Health check:http://localhost:8080/health

API Documentation (Using Swagger 2.0) URL: 

http://localhost:8080/v2/api-docs
http://localhost:8080/swagger-ui.html

Also added the temp data under src/main/resources/data.sql which will be picked and pushed to database during the Run phase

Testing the Restful Web Services:
**EndPoints:**

1.**Searching By PostCode Range:**

**Get Request:** http://localhost:8080/api/search?from=3000&to=3002

**Output:**

{
    "suburbNames": [
        "SUBURB6",
        "XSUBURB5",
        "ZSUBURB4"
    ],
    "totalCharsOfSuburbNames": 23
}


2.**Add Suburb PostCode:**

**Post request:** http://localhost:8080/api/add

**Input:** 

  {
        "postCode": "3000",
        "suburbName": "RSUBURB2"
    }
    
**Output:** (saved into DB)

  {
        "postCode": "3000",
        "suburbName": "RSUBURB2"
    }
 
