# Register form
## Register form is on `/api/register` endpoint
### Required JSON as presented below
```
{
    "name": "user name",
    "email": "user email",
    "password": "user password"
}
```
## Type of responses
- JSON data response
- JSON error response
## JSON data response:
If request satisfy all conditions then response body will be exactly like below:
```
{
    "data": {
        "message": "user has been succesfully registered and saved in database"
    },
    "metaData": null
}
```
HTTP status for data response: 200 
## JSON error response:
When some problems occured on server side the response will have an array of errors:
```
{
    "errors": [
    ],
    "metaData": null
}
```
The example of structure of each error:
```
{
    "status": 422,
    "code": "NAME_OCCUPIED",
    "title": "In database exist user with that name"
}
```
Short describe of fields:
- status: http status represented as integer and corresponding to each error
- code: an application-specific error code, expressed as a string value
- title: a short, human-readable summary of the problem  

### Name property error codes:
Critical errors: 
- NAME_NULL

Normal errors: 
- NAME_MISSING
- NAME_OCCUPIED 
- NAME_INCORRECT


##### NAME_NULL error description:
Structure:
```
{
    "status": 400,
    "code": "NAME_NULL",
    "title": "parameter name is null"
}
```
Description: Server dosen't recive name parameter value
Reasons:
- problems in communication client-server
- property name has wrong typo e.g. mame instead of name

If this error occurs there won't be more errors of name property and http status will be 400.
Please notice that error dosen't interference with errors from email and password properties.
##### NAME_MISSING error description:
Structure:
```
{
    "status": 422,
    "code": "NAME_MISSING",
    "title": "parameter name isn't present"
}
```
Description: Name cannot be empty string
##### NAME_OCCUPIED error description:
Structure:
```
{
    "status": 422,
    "code": "NAME_OCCUPIED",
    "title": "In database exist user with that name"
}
```
Description: User with that name already exists in database
##### NAME_INCORRECT error description:
Structure:
```
{
    "status": 422,
    "code": "NAME_INCORRECT",
    "title": "name can only have letters and digits"
}
```
Description: Name has special characters
Conditions to valid name:
- has only digits and letters
- dosen't start or ends with space

### Email property error codes:
Critical errors: 
- EMAIL_NULL

Normal errors: 
- EMAIL_MISSING
- EMAIL_OCCUPIED 
- EMAIL_INCORRECT

##### EMAIL_NULL error description:
Structure:
```
{
    "status": 400,
    "code": "EMAIL_NULL",
    "title": "parameter email is null"
}
```
Description: Server dosen't recive email parameter value
Reasons:
- problems in communication client-server
- property email has wrong typo e.g. enail instead of email

If this error occurs there won't be more errors of email property and http status will be 400.
Please notice that error dosen't interference with errors from name and password properties.
##### EMAIL_MISSING error description:
Structure:
```
{
    "status": 422,
    "code": "EMAIL_MISSING",
    "title": "parameter email isn't present"
}
```
Description: Email cannot be empty string
##### EMAIL_OCCUPIED error description:
Structure:
```
{
    "status": 422,
    "code": "EMAIL_OCCUPIED",
    "title": "In database exist user with that email"
}
```
Description: User with that email already exists in database
##### EMAIL_INCORRECT error description:
Structure:
```
{
    "status": 422,
    "code": "EMAIL_INCORRECT",
    "title": "email has bad format"
}
```
Description: Email has bad format
### Password property error codes:
Critical errors: 
- PASSWORD_NULL

Normal errors: 
- PASSWORD_MISSING
- WEAK_PASSWORD

##### PASSWORD_NULL error description:
Structure:
```
{
    "status": 400,
    "code": "PASSWORD_NULL",
    "title": "parameter password is null"
}
```
Description: Server dosen't recive password parameter value
Reasons:
- problems in communication client-server
- property password has wrong typo e.g. pasword instead of password

If this error occurs there won't be more errors of password property and http status will be 400.
Please notice that error dosen't interference with errors from name and email properties.
##### PASSWORD_MISSING error description:
Structure:
```
{
    "status": 422,
    "code": "PASSWORD_MISSING",
    "title": "parameter password isn't present"
}
```
Description: Password cannot be empty string
##### WEAK_PASSWORD error description:
Structure:
```
{
    "status": 422,
    "code": "WEAK_PASSWORD",
    "title": "password is too weak"
}
```
Description: Password dosen't satisfy below conditions:\
Conditions to valid password:
- at least one small and big letter
- at least one digit 
- at least one of special characters: !,@,#,$,%,^,&,*  
- cannot have white characters  
- cannot be shorter than 8 and longer than 15 

### HTTP status for error responses
If one of the critical errors occurs http status will be 400
If errors table has only normal errors then http status will be 422

### Example of error responses
##### Without critical errors:
JSON send:
```
{
    "name": "Mateusz^",
    "email": "",
    "password": "ImNotGoodPassword"
}
```
Response:
```
{
    "errors": [
        {
            "status": 422,
            "code": "NAME_INCORRECT",
            "title": "name can only have letters and digits"
        },
        {
            "status": 422,
            "code": "EMAIL_MISSING",
            "title": "parameter email isn't present"
        },
        {
            "status": 422,
            "code": "EMAIL_INCORRECT",
            "title": "email has bad format"
        },
        {
            "status": 422,
            "code": "WEAK_PASSWORD",
            "title": "password is too weak"
        }
    ],
    "metaData": null
}
```
HTTP status code: 422
##### With critical errors:
JSON send:
```
{
    "name": null,
    "email": "sdadsadsa@gmail.com",
    "password": "ImGoodPassword@3"
}
```
Response:
```
{
    "errors": [
        {
            "status": 400,
            "code": "NAME_NULL",
            "title": "parameter name is null"
        },
        {
            "status": 422,
            "code": "EMAIL_MISSING",
            "title": "parameter email isn't present"
        },
        {
            "status": 422,
            "code": "EMAIL_INCORRECT",
            "title": "email has bad format"
        },
        {
            "status": 422,
            "code": "WEAK_PASSWORD",
            "title": "password is too weak"
        }
    ],
    "metaData": null
}
```
HTTP status code: 400
