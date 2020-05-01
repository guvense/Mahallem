#!/bin/bash


sed  -i 's/${IMAGE_NAME}/'"${IMAGE_NAME}"'/' docker-compose.yml
sed  -i 's/${IMAGE_NAME_HUB}/'"${IMAGE_NAME_HUB}"'/' docker-compose.yml



