# StarTiger

## 本项目旨在备忘 SpringBoot、SpringCloud 生态下的知识点

Github: [https://github.com/snake19870227/StarTiger.git](https://github.com/snake19870227/StarTiger.git)  

Gitee: [https://gitee.com/buhy/StarTiger.git](https://gitee.com/buhy/StarTiger.git)  

**特别感谢 [Jetbrains](https://www.jetbrains.com/?from=StarTiger) 提供的License**  

---

## StarTiger-dependencies

版本依赖定义
- StarTiger-framework
框架
- StarTiger-spring-boot-autoconfigure、StarTiger-spring-boot-starter
框架配套的springboot starter

---

## StarTiger-admin

构建权限模型, 验证 Spring Boot + Spring Security 组合  
整合 AdminLTE 前端框架，非前后端分离方式  
后端管理项目通用脚手架

## StarTiger-cloud

Spring Cloud 技术点备忘
- stiger-cloud-admin  
Spring Boot Admin (SBA)
- stiger-cloud-common  
Demo基础模块,同时验证不变动业务的情况下,动态调整Spring Cloud生态下各个套件
  - stiger-cloud-base  
  常量、模拟业务接口、通用配置......
  - stiger-cloud-config-git  
  git配置中心配置
  - stiger-cloud-consumer  
  消费业务代码
  - stiger-cloud-consumer-hystrix  
  整合**hystrix**的消费业务代码
  - stiger-cloud-producer
  提供服务业务代码
  - stiger-cloud-stream
  整合**spring-cloud-stream**所需的通道、消息体代码
- stiger-cloud-eureka  
Eureka作为注册中心
  - stiger-cloud-eureka-register-center  
  注册中心
  - stiger-cloud-eureka-config-git  
  git配置中心
  - stiger-cloud-eureka-config-git-bus  
  git配置中心，启用动态刷新功能
  - stiger-cloud-eureka-consumer  
  消费服务，使用`LoadBalancerClient`
  - stiger-cloud-eureka-consumer-feign  
  消费服务，使用**Feign**
  - stiger-cloud-eureka-consumer-ribbon  
  消费服务，使用`@LoadBalanced`标注`RestTemplate`
  - stiger-cloud-eureka-gateway-spring  
  网关，使用**spring-cloud-gateway**
  - stiger-cloud-eureka-gateway-zuul  
  网关，使用**zuul**
  - stiger-cloud-eureka-producer  
  提供服务
  - stiger-cloud-eureka-producer-bus  
  提供服务，接受配置动态刷新
  - stiger-cloud-eureka-zipkin  
    1. 使用**sleuth**集成**zipkin**  
    服务调用链路: 101 -> 211/212; 211 -> 311/312; 212 -> 321;  
    ![拓扑图](https://github.com/snake19870227/StarTiger/blob/master/StarTiger-cloud/stiger-cloud-eureka/doc/image/zipkin_router.jpg?raw=true)
    2. 211/212集成**spring-cloud-stream**
- stiger-cloud-hystrix-dashboard  
Hystrix仪表盘
- stiger-cloud-nacos  
Nacos作为注册中心、配置中心
- stiger-cloud-turbine  
Turbine聚合Hystrix集群数据  (对接Eureka注册中心)
- stiger-cloud-zookeeper  
Zookeeper作为注册中心
---
## StarTiger-cloud-mall
一个简单的销售模型 [README](./StarTiger-cloud-mall/README.md)
1. 整合**StarTiger-cloud**中所验证的技术点  
2. 整合**spring**、**spring-boot**使用技术点
当前技术栈  
工具包：hutool 
注册中心：nacos  
配置中心：nacos  
服务调用跟踪：zipkin  
熔断、降级：sentinel（弃用hystrix）  
安全：spring security  
微服务监控：spring-boot-admin  
网关：spring-cloud-gateway  
消息驱动：spring-cloud-stream  
全文检索：elasticsearch  
接口文档：swagger  
数据库：mysql  
orm：mybatis、mybatis-plus
---
## StarTiger-elasticsearch
springboot整合elasticsearch
## StarTiger-keycloak（暂停）
springboot整合keycloak
## StarTiger-oauth2
springboot整合oauth2
## StarTiger-webflux
spring5 webflux