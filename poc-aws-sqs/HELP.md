#### 1) Create queue -
```shell
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name my-test-queue
```
####  2) Get queue url -
```shell
aws --endpoint-url=http://localhost:4566 sqs get-queue-url --queue-name <queue-name>
```
####  3) list queue -
```shell
aws --endpoint-url=http://localhost:4566 sqs list-queues
```
####  4) send message -
```shell
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url <queue-url> --message-body <message>
```
####  5) receive message -
```shell
aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url <queue-url>
```
####  6) purge queue -
```shell
 aws --endpoint-url=http://localhost:4566 sqs purge-queue --queue-url <queue-url>
```
####  7) delete queue -
```shell
 aws --endpoint-url=http://localhost:4566 sqs delete-queue --queue-url <queue-url>
```
####  8) set attributes -
```shell
aws --endpoint-url=http://localhost:4566 sqs set-queue-attributes --queue-url=<queue-url> --attributes file://<file-name>.json
```
####  9) get attributes -
```shell
 aws --endpoint-url=http://localhost:4566 sqs  get-queue-attributes --queue-url=<queue-url>
```
##### ---list detter queue
```shell
aws --endpoint-url=http://localhost:4566 sqs  list-dead-letter-source-queues --queue-url=<queue-url>
```