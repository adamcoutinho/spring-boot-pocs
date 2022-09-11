#!/bin/bash
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name queue-process-object
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name queue-process-list-object
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name queue-process-string
