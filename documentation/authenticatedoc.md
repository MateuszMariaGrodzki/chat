# Login form

## Login form is on `/api/authenticate` endpoint

### Required JSON as presented below:

```
{
    "name": "user name",
    "password": "user password"
}
```
## Types of response:
- JSON data response
- JSON error response

## JSON data response:
If request satisfy all conditions then response json structure will be like below:
```
{
    "data": {
        "token": "user token",
        "name": "user name",
        "email": "user email"
    },
    "metaData": null
}
```
HTTP status code for this response: 200
Moreover with data response server also sends cookie with acces token so please allow credentials on client side.
## JSON error response:
When some problems occured on server side the response will have a table of errors:
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

##### NAME_NULL error description
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
Please notice that error dosen't interference with errors from password property.
##### NAME_MISSING error description:
Structure:
```
{
    "status": 422,
    "code": "NAME_MISSING",
    "title": "parameter name is not present"
}
```
Description: Name cannot be empty string
### Password property error codes:
Critical errors: 
- PASSWORD_NULL

Normal errors: 
- PASSWORD_MISSING

##### PASSWORD_NULL error description
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
- property name has wrong typo e.g. mame instead of name

If this error occurs there won't be more errors of password property and http status will be 400.
Please notice that error dosen't interference with errors from name property.
##### PASSWORD_MISSING error description:
Structure:
```
{
    "status": 422,
    "code": "PASSWORD_MISSING",
    "title": "parameter password is not present"
}
```
Description: password cannot be empty string
### Authentication error code:
- BAD_CREDENTIALS

##### BAD_CREDENTIALS error description:
Structure:
```
{
    "status": 401,
    "code": "BAD_CREDENTIALS",
    "title": "bad credentials"
}
```
Description: server cannot authenticate user due to invalid name or password

### HTTP status for error responses
If one of the critical errors occurs http status will be 400 \
If bad credentials error occurs http status will be 401 \
Else http status will be 422

### Example of error responses
##### Without crirital errors
JSON send:
```
{
    "name": "",
    "password": "ImF1u9n5k^yPassword"
}
```
Response:
```
{
    "errors": [
        {
            "status": 422,
            "code": "NAME_MISSING",
            "title": "parameter name is not present"
        }
    ],
    "metaData": null
}
```
##### With crirital errors
JSON send:
```
{
    "name": null,
    "password": ""
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
            "code": "PASSWORD_MISSING",
            "title": "parameter password is not present"
        }
    ],
    "metaData": null
}
```
##### With bad credentials
JSON send:
```
{
    "name": "Monday",
    "password": "123"
}
```
Response:
```
{
    "errors": [
        {
            "status": 401,
            "code": "BAD_CREDENTIALS",
            "title": "bad credentials"
        }
    ],
    "metaData": null
}
```
