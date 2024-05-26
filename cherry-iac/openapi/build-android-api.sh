#!/bin/bash

rm -rf ./android

for file in $(find ./ -name '*.yaml'); do
  if [[ $file == *"configs"* ]]; then
    continue
  fi

  echo Generating for $file
  java -jar ./generator.jar generate -i $file \
    -g android \
    -o ./android \
    -c ./configs/android.yaml \
    -t ./templates/android \
    --global-property models,apis,supportingFiles=pom.xml:ApiInvoker.java:HttpPatch.java:HttpPatch.java:ApiException.java:Pair.java:JsonUtil.java
done

echo Building java
(cd ./android && mvn clean package)
