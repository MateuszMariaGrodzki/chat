# Login form
### Login form is on `/api/authenticate` endpoint
### Required JSON as presented below:
```
{
    "username": "Maciek128",
    "password": "dzemmalinowy135"
}
```
### and response as follows
```
{
    "token": "token or error codes"
}
```
### Error codes for login form
username_missing - parameter username is not present  
password_missing - parameter password is not present  
bad_credentials - user with specified username and password dosen't exist in database
### Example of response when authentication was succesfull
```
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYWNpZWsxMjgiLCJpYXQiOjE1OTU4NDc1ODZ9.XztiRFOq-CpeKQY1t5kUediNzucNAJ1739C6-VUsb60"
}
```
