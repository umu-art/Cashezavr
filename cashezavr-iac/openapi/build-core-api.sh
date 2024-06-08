#!/bin/bash

rm -rf ./server

for file in $(find ./ -name '*.yaml'); do
  if [[ $file == *"configs"* ]]; then
    continue
  fi

  echo Generating for $file
  java -jar ./generator.jar generate -i $file -g spring -o ./server -c ./configs/java.yaml
done

echo Building java
(cd ./server && mvn clean install)
