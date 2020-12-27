# User doc
### To get user name and email you can use GET method on /api/user endpoint
### Response will be as below:
```
{
	"name": "user name",
	"email": "user email
}
```
### Please notice that you need cookie with token to get this info. 
### If cookie is no present or token has bad structure you will get null values
