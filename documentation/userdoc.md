# User info documentation

## List of all endpoints:

| Id |URL|Description|Reference|
|:-:|:-:|:-:|:-:|
|1|`/api/user`|Endpoint for retrieving user name and email by token  |[click](#api/user-endpoint)|
|2|`/api/users`|Endpoint for retrieving list of users with name and slug | [click](#api/users-endpoint)|


## api/user endpoint
| Title | Get user email and name  |
|:-:|:-|
| __URL__  | `/api/user` |
| __Method__    | GET     |
| __URL PARAMS__ | none  |
|__Data Params__| none|
|__Notes__| This endpoint needs credentials so please allow them on client side |

### Success response
|Title|Success response|
|:-:|:-|
|Code|200|
|Body| <pre>{<br>&#9;"data": {<br>&#9;&#9;"name": "user_name",<br>&#9;&#9;"email":"user_email"<br>&#9;},<br>"metaData": null<br>}</pre>|
|Credentials| none |
|Notes| none |

### Error response
|Title|Error response|
|:-:|:-|
|Code|400 or 401 |
|Body|<pre>{<br>&#9;"errors": [<br>&#9;&#9;{<br>&#9;&#9;&#9;"status": 400,<br>&#9;&#9;&#9;"code": "error code",<br>&#9;&#9;&#9;"title": "short description of problem"<br>&#9;&#9;}<br>&#9;],<br>&#9;"metaData": null<br>}</pre>|
|Notes| Table will have only one element. List of possible error with short description is presented below |

#### All error codes list
|code|status|title|
|:-:|:-:|:-:|
|UNAUTHORIZED_USER|401|server dosen't recive cookie with token|
|USERNAME_NULL|400|decoded token has null username|
|UNKNOWN_USER|400|token is valid but user is not registered|

## api/users endpoint
| Title | Get user list with name nad slug for each user  |
|:-:|:-|
| __URL__  | `/api/users` |
| __Method__    | GET     |
| __URL PARAMS__ | page & size  |
|__Data Params__| none|
|__Notes__| none |

### Parameter description
|name| obligatory | default value | description
|:-:|:-:|:-:|:-:|
|page| no | 1 | number of page to view |
|size | no | 20| number of elements on page |

### Success response
|Title|Success response|
|:-:|:-|
|Code|200|
|Body| <pre>{<br>&#9;"data": {<br>&#9;&#9;"users": [<br>&#9;&#9;&#9;{<br>&#9;&#9;&#9;   "name": "first_user",<br>&#9;&#9;&#9;   "slug": "first-user"<br>&#9;&#9;&#9;},<br>&#9;&#9;&#9;{<br>&#9;&#9;&#9;   "name": "second_user",<br>&#9;&#9;&#9;   "slug": "second_user"<br>&#9;&#9;&#9;}<br>&#9;&#9;]<br>&#9;},<br>&#9;"metaData": {<br>&#9;&#9;"maxPages": 1<br>&#9;}<br>} </pre> |
|Credentials| none |
|Notes| none |