# User registration documentation

## List of all endpoints:

| Id |URL|Description|Reference|
|:-:|:-:|:-:|:-:|
|1|`/api/register`|Endpoint for user registration|[click](#apiregister-endpoint)|


## api/register endpoint
| Title | User logout  |
|:-:|:-|
| __URL__  | `/api/logout` |
| __Method__    | POST      |
| __URL PARAMS__ | none      |
|__Data Params__|Required json:<pre>{<br>&#9;"name": "user_name",<br>&#9;"email": "user_email",<br>&#9;"password": "user_password"<br>}<pre>|
|__Notes__|none |

### Succes response
|Title|Succes response|
|:-:|:-|
|Code|200|
|Body|<pre>{<br>&#9;"data":{<br>&#9;&#9;"message": "user has been succesfully registered and saved in database"<br>&#9;},<br>&#9;"metaData":null<br>}</pre>|
|Credentials| none |
|Notes| none |

### Error response
|Title|Error response|
|:-:|:-|
|Code|400 or 422|
|Body|<pre>{<br>&#9;"errors": [<br>&#9;&#9;{<br>&#9;&#9;&#9;"status": 400,<br>&#9;&#9;&#9;"code": "error code",<br>&#9;&#9;&#9;"title": "short description of problem"<br>&#9;&#9;}<br>&#9;],<br>&#9;"metaData": null<br>}</pre>|
|Notes| Table errors can have multiple elements. List of possible error with short description is presented below |

#### Critical  error codes list
|code|status|title|
|:-:|:-:|:-:|
|NAME_NULL|400|parameter name is null|
|EMAIL_NULL|400|parameter email is null|
|PASSWORD_NULL|400|parameter password is null|

If one of critical error codes appears then the response status will be 400.  \
These error codes are more likely appears when sent json has wrong typo.

#### Normal error codes list
|code|status|title|
|:-:|:-:|:-:|
|NAME_MISSING|422|parameter name isn't present|
|NAME_OCCUPIED|422|In database exist user with that name|
|NAME_INCORRECT|422|name can only have letters and digits|
|EMAIL_MISSING|422|parameter email isn't present|
|EMAIL_OCCUPIED|422|In database exist user with that email|
|EMAIL_INCORRECT|422|email has bad format|
|PASSWORD_MISSING|422|parameter password isn't present|
|WEAK_PASSWORD|422|password is too weak|
|SHORT_PASSWORD|422|password is too short (min: 8 characters)|
|LONG_PASSWORD|422|password is too long (max: 15 characters)|

__Conditions to valid name:__
- has only digits and letters
- dosen't start or ends with space

__Conditions to strong password:__
- at least one small and big letter
- at least one digit 
- at least one of special characters: !,@,#,$,%,^,&,*  
- cannot have whitespace characters  
