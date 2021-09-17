## AMAZON - S3
#### create bucket
``` 
aws --endpoint-url=http://localhost:4566 s3 mb s3://folder-tests
``` 
#### remove bucket
``` 
aws --endpoint-url=http://localhost:4566 s3 rb s3://folder-tests
```
#### syncronized bucket with folder and files
``` 
aws --endpoint-url=http://localhost:4566 s3 sync s3-storage s3://folder-tests 
```