
#### create table 
```
aws dynamodb create-table --cli-input-json file://person_table.json --endpoint-url=http://localhost:4566 
```
#### describe table
```
aws dynamodb describe-table --table-name="user" --endpoint-url=http://localhost:4566 
```
#### listing tables
```
aws dynamodb list-tables --endpoint-url=http://localhost:4566 
```
#### deleting table
```
aws dynamodb delete-table --table-name="user" --endpoint-url=http://localhost:4566
```
#### list all items
```
aws dynamodb scan --table-name="user" --endpoint-url="http://localhost:4566"
```
#### list data table
```
aws dynamodb scan  --table-name="user" --endpoint-url=http://localhost:4566
```