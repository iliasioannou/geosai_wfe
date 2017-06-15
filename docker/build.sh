#! /bin/bash

docker build --no-cache -t planetek/cmems_activiti:$1 --build-arg branch=$1 .
