#### 1) - for each command sqs for send message the according queue corresponding
```shell
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/queue-process-string  --message-body "teste message"

aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/queue-process-object --message-body=file://./sqs-scripts/jsons/user-message.json

aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/queue-process-list-object --message-body=file://./sqs-scripts/jsons/users-message.json
```
#### 2) - search message for each queue corresponding
```shell
aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/queue-process-object

aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/queue-process-list-object

aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/queue-process-string
```
#### 3) - purge for each queue message corresponding
```shell
aws --endpoint-url=http://localhost:4566 sqs purge-queue --queue-url http://localhost:4566/000000000000/queue-process-object

aws --endpoint-url=http://localhost:4566 sqs purge-queue --queue-url http://localhost:4566/000000000000/queue-process-list-object

aws --endpoint-url=http://localhost:4566 sqs purge-queue --queue-url http://localhost:4566/000000000000/queue-process-string
```