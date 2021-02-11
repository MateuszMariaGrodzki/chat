# User authenticate documentation

## List of all endpoints:

| Id |URL|Description|Reference|
|:-:|:-:|:-:|:-:|
|1|`/api/authenticate`|Endpoint for user authentication|[click](#apiauthenticate-endpoint)|


## api/authenticate endpoint
| Title | User logout  |
|:-:|:-|
| __URL__  | `/api/authenticate` |
| __Method__    | POST      |
| __URL PARAMS__ | none      |
|__Data Params__| Required json:<pre>{<br>&#9;"name": "user_name",<br>&#9;"password": "user_password"<br>}<pre>|
|__Notes__|none |

### Success response
|Title|Success response|
|:-:|:-|
|Code|200|
|Body| <pre>{<br>&#9;"data": {<br>&#9;&#9;"token":"jwt_token",<br>&#9;&#9;"name": "user_name",<br>&#9;&#9;"email":"user_email"<br>&#9;},<br>"metaData": null<br>}</pre>|
|Credentials| Cookie with token |
|Notes| Be sure to allow credentials from client side |

### Error response
|Title|Error response|
|:-:|:-|
|Code|400 or 401 or 422|
|Body|<pre>{<br>&#9;"errors": [<br>&#9;&#9;{<br>&#9;&#9;&#9;"status": 400,<br>&#9;&#9;&#9;"code": "error code",<br>&#9;&#9;&#9;"title": "short description of problem"<br>&#9;&#9;}<br>&#9;],<br>&#9;"metaData": null<br>}</pre>|
|Notes| Table can have multiple elements. List of possible error with short description is presented below |

#### Critical  error codes list
|code|status|title|
|:-:|:-:|:-:|
|NAME_NULL|400|parameter name is null|
|PASSWORD_NULL|400|parameter password is null|
If one of critical error codes appears then the response status will be 400.
These error codes are more likely appears when sent json has wrong typo.

#### Normal error codes list
|code|status|title|
|:-:|:-:|:-:|
|NAME_MISSING|422|parameter name is not present|
|PASSWORD_MISSING|422|parameter password is not present|
|BAD_CREDENTIALS|401|bad credentials|
If NAME_MISSING or PASSWORD_MISSING code appears then response status will be 422.
Otherwise response status is 401
