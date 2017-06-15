#! /bin/bash

cd cmems_activiti_loader
mvn clean package
cd target 
java -jar cmems_acitiviti_loader.jar
