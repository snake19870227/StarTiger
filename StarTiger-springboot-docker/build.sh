#!/bin/bash

help() {
  echo "--------------------------------------------------------------------------"
  echo ""
  echo "usage: ./build.sh 打包并发布docker镜像"
  echo ""
  echo "--------------------------------------------------------------------------"
}

mvn clean package

while read line; do
  if [[ $line == version* ]]; then
    version=${line#*=}
  fi
done <./target/maven-archiver/pom.properties

if [[ -n $version ]]; then
  imageName=stiger-springboot-docker
  echo "镜像名称: $imageName"
  echo "构建版本: $version"
  jarFile="./target/StarTiger-springboot-docker-$version.jar"
  if [[ -f $jarFile ]]; then
    echo "jar文件: $jarFile"
    containerName="$imageName-$version-test"
    echo "测试容器: $containerName"
    docker build --build-arg jar_file="$jarFile" --build-arg "builder_name=Intellij IDEA" -t "$imageName" -t "$imageName:$version" . &&
      docker run --rm --name "$containerName" -p28080:8080 $imageName
  fi
fi
