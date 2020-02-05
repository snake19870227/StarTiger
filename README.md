# StarTiger
## 本项目旨在备忘 SpringBoot、SpringCloud 生态下的知识点

Github: [https://github.com/snake19870227/StarTiger.git](https://github.com/snake19870227/StarTiger.git)  
Gitee: [https://gitee.com/buhy/StarTiger.git](https://gitee.com/buhy/StarTiger.git)  

# 模块
## StarTiger-admin
构建简单权限模型, 验证 Spring Boot + Spring Security 组合  

## StarTiger-cloud
Spring Cloud 技术点备忘
### stiger-cloud-admin
Spring Boot Admin (SBA) 
### stiger-cloud-common
Demo基础模块,同时验证不变动业务的情况下,动态调整Spring Cloud生态下各个套件  
### stiger-cloud-eureka
Eureka作为注册中心  
[README](https://github.com/snake19870227/StarTiger/blob/master/StarTiger-cloud/stiger-cloud-eureka/README.md)
### stiger-cloud-hystrix-dashboard
Hystrix仪表盘
### stiger-cloud-nacos
Nacos作为注册中心、配置中心
### stiger-cloud-turbine
Turbine聚合Hystrix集群数据  (对接Eureka注册中心)
### stiger-cloud-zookeeper
Zookeeper作为注册中心