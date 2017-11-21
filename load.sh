#! /bin/bash

cd /src/eosai_activiti_loader
mvn clean package
cd target 
java -jar eosai_acitiviti_loader.jar
