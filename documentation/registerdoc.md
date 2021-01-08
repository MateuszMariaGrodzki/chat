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

NAME_MISSING - parameter name is not present  
EMAIL_MISSING - parameter email is not present  
PASSWORD_MISSING - parameter password is not present  
NAME_INCORRECT - name does not satisfy certian conditions listed below (after error codes)  
NAME_OCCUPIED - parameter name is presented in database  
EMAIL_OCCUPIED - parameter email is presented in database  
EMAIL_INCORRECT - email has not correct structure  
WEAK_PASSWORD - password is to weak (below is list of all conditions)
### Name conditions:
Name cannot start or ends with space  
Only digits and letters are allowed
### Strong password must have:
At least one small and big letter  
At least one digit  
At least one of special characters: !,@,#,$,%,^,&,*  
Cannot have white characters  
Cannot be shorter than 8 and longer than 15 
### HTTP status codes for each response:
if user was succesfully registered and saved into database http status is 200  
otherwise http status is 422
