# ITBC-Logger

About: ITBC-Logger application is about creating logs by Client and determine diffrent approach to data.

Technologies:

Java version 19

MS SQL Database

Spring Boot version 2.7.5Dependencies: Spring WEB, Spring Data JPA, Spring Lombok,Sring MS SQL Driver, Spring Security.

Client
1. Register
HTTP Method: POST
Endpoint URL: /api/clients/register
Request body:


{
"username": "string",
"password": "string",
"email": "string"
}


Responses:
201 - Registered
400 - Bad Request
email must be valid
username at least 3 characters
password at least 8 characters and one letter and one number
409 - Conflict
username already exists
email already exists


2. Login
HTTP Method: POST
Endpoint URL: /api/clients/login
Request body:


{
"account": "string", // email or username
"password": "string"
}


Responses:
200 - OK
{
"token": "string" // uuid* || JWT || username
}
400 - Bad Request
Email/Username or password incorrect



3. Create log
HTTP Method: POST
Endpoint URL: /api/logs/create
Request body:


{
"message": "string",
"logType": 0
}


Request headers:
Authorization - token
Responses:
201 - Created
400 - Bad Request
Incorrect logType
401 - Unauthorized
Incorrect token
413 - Payload too large
Message should be less than 1024
IT Bootcamp 10/20/2022

Admin

1. Get all clients
HTTP Method: GET
Endpoint URL: /api/clients
Request headers:
Authorization - token (Admin token)
Responses:
200 - OK


[
{
"id": "uuid",
"username": "string",
"email": "string",
"logCount": 0
}
]


401 - Unauthorized
Correct token, but not admin
403 - Forbidden
Incorrect token


2. Change client password
HTTP Method: PATCH
Endpoint URL: /api/clients/{clientId}/reset-password
Request body:

{
"password": "string"
}


Request headers:
Authorization - token (Admin token)
Responses:
204 - No content
401 - Unauthorized
Correct token, but not admin
403 - Forbidden
Incorrect token
