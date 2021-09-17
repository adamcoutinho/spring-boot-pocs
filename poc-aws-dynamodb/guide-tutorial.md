
#### create table 
```
aws dynamodb create-table --cli-input-json file://person_table.json --endpoint-url=http://localhost:4566 
```
#### listing tables
```
aws dynamodb list-tables --endpoint-url=http://localhost:4566 
```