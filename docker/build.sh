#! /bin/bash

docker build -t planetek/eosai_activiti:base -f Dockerfile.base .
docker build --no-cache -t planetek/eosai_activiti:$1 --build-arg branch=$1 .
