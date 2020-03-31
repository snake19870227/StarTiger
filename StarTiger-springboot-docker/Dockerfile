FROM openjdk:8u242-jdk
MAINTAINER Snake
VOLUME ["/tmp"]
ARG jar_file
ARG builder_name
ENV BUILDER_NAME ${builder_name}
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /boot-app
WORKDIR /boot-app
ADD ${jar_file} ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","-Djava.security.egd=file:/dev/./urandom"]