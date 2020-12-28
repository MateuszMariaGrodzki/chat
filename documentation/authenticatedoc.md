# Login form

### Login form is on `/api/authenticate` endpoint

### Required JSON as presented below:

```
{
    "username": "Maciek128",
    "password": "dzemmalinowy135"
}
```

### and you will get response as follows

```
{
    "errorCode": "error code or null",
    "token": "token or null",
    "name": "user name or null",
    "email": "user email or null"
}
```

### If something went wrong token will be null and list of possible error codes is presented below:

USERNAME_MISSING - parameter username is not present  
PASSWORD_MISSING - parameter password is not present  
BAD_CREDENTIALS - user with specified username and password doesn't exist in database

### Example of response when authentication was succesfull

```
{
    "errorCode": null,
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYWNpZWsxMjgiLCJpYXQiOjE1OTU4NDc1ODZ9.XztiRFOq-CpeKQY1t5kUediNzucNAJ1739C6-VUsb60",
    "name": "username",
    "email": "useremail"
}
```
### HTTP status code for each response:
When error code is USERNAME_MISSING oraz PASSWORD_MISSING http code is 422  
When error code is BAD_CREDENTIALS http status code is 401
Otherwise http status code is 200
