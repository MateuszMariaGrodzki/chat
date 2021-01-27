# User logout documentation

## List of all endpoints:

| Id |URL|Description|Reference|
|:-:|:-:|:-:|:-:|
|1|`/api/logout`|Endpoint for logout user from chat|[click](#logout-info)|


## Logout info
| Title | User logout  |
|:-:|:-:|
| __URL__  | `/api/logout` |
| __Method__    | POST      |
| __URL PARAMS__ | none      |
|__Data Params__| none |
|__Notes__|none |

### Succes response
|Title|Succes response|
|:-:|:-|
|Code|200|
|Body| <pre>{<br>&#9;"data":{<br>&#9;&#9;"message": "user has been succesfully logout"<br>&#9;},<br>&#9;"metaData":null<br>}</pre>|
|Credentials| none |
|Notes| none |

### Error response
|Title|Error response|
|:-:|:-|
|Code|400|
|Body|<pre>{<br>&#9;"errors": [<br>&#9;&#9;{<br>&#9;&#9;&#9;"status": 400,<br>&#9;&#9;&#9;"code": "error code",<br>&#9;&#9;&#9;"title": "short description of problem"<br>&#9;&#9;}<br>&#9;],<br>&#9;"metaData": null<br>}</pre>|
|Notes| Table has always one element. List of possible error with short description is presented below |

#### All error codes list
|code|status|title|
|:-:|:-:|:-:|
|COOKIES_NULL|400|server does not recive cookies from client|
|TOKEN_MISSING|400|token cooke is not present in request cookies|