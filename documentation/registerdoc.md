# Register form
### Register form is on `/api/register` endpoint
### Required JSON as presented below
```
{
    "name": "user name",
    "email": "user email",
    "password": "user password"
}

```
### and response as follows
```
{
    "errorCode": "error code or null",
    "registered": "true or false"
}
```
### If registered field is true then user has been succesfully registered and saved into database (in this case errorCode is null)
### If registered field is false then in errorCode filed you get one of the following error codes:

name_missing - parameter name is not present  
email_missing - parameter email is not present  
password_missing - parameter password is not present  
name_occupied - parameter name is presented in database  
email_occupied - parameter email is presented in database  
email_incorrect - email has not correct structure  
weak_password - password is to weak.
###Strong password must have:
At least one small and big letter  
At least one digit  
At least one of special characters: !,@,#,$,%,^,&,*  
Cannot have white characters  
Cannot be shorter than 8 and longer than 15  
