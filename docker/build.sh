#! /bin/bash

docker build -t planetek/cmems_activiti:base -f Dockerfile.base .
docker build --no-cache -t planetek/cmems_activiti:$1 --build-arg branch=$1 .
