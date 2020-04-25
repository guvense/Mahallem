#!/bin/bash

sed  -i 's/${REDIS_PASS}/'"${REDIS_PASS}"'/' docker-compose.yml
sed  -i 's/${MONGODB_PASS}/'"${MONGODB_PASS}"'/' docker-compose.yml
sed  -i 's/${IMAGE_NAME}/'"${IMAGE_NAME}"'/' docker-compose.yml



