#!/bin/bash

sed   's/${REDIS_PASS}/'"${REDIS_PASS}"'/' docker-compose.yml
sed  's/${MONGODB_PASS}/'"${MONGODB_PASS}"'/' docker-compose.yml
sed  's/${IMAGE_NAME}/'"${IMAGE_NAME}"'/' docker-compose.yml



