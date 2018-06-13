# 目录
- [工程简介](#工程简介)
  - [架构拓朴图](#架构拓朴图)
  - [项目说明](#项目说明)
    - [配置目录](#配置目录)
    - [服务应用](#服务应用)
    - [功能模块](#功能模块)
  - [相关文档](#相关文档)
- [环境搭建](#环境搭建)
  - [项目环境](#项目环境)
  - [项目部署](#项目部署)
  

# 工程简介
## 架构拓朴图
![架构拓朴图](http://r.photo.store.qq.com/psb?/V14KUPlZ1oRvxL/l5lv9b9w9HD*Aft9lyW0aLS1vdMgYQQvTmhTFPE5g9k!/r/dDABAAAAAAAA)
## 项目说明
搭建java分布式微服务架构系统，集成第三方框架应用及实现初步的简单实例，跟进时代潮流，尽可能的用到项目中所有热门技术，从中学习与实现解决实际问题。

结构介绍：
> 控制层：控制层提供互联网访问，控制层的义务是控制访问请求跳转到接口业务层，监控用户状态，角色权限的拦截处理。  
> 接口业务层：利用防火墙限制只允许指定的内网机器访问，接口业务层的义务是处理具体业务实现，对数据层进行数据维护。  
> 业务层：利用防火墙限制只允许指定的内网机器访问，业务层的义务与接口业务层一样，它可以从某种特定的技术方案来更好的实现业务需求。  
> 数据层：利用防火墙限制只允许指定的内网机器访问，数据层的义务是接收业务层的业务处理对数据库增删改查。  
> 数据模型：sdk包，通常是一些实体类映射数据库表，及一些单表基础操作增删改查的接口方法。    
> feign接口：sdk包，提供微服务提供者实现接口和微服务消费者调用接口，微服务与微服务之间的数据通信。    
> 服务治理：对所有微服务监控、配置、权限、文档和日志统一管理。    
> 数据库：缓存数据库和持久化数据库的数据存储及查询。    

这样搭建的优点：
> 1、随着越来越大的业务量，分散在很多的机器上运行，对性能和容量是一个很大的提升。  
> 2、解决高并发的问题。  
> 3、系统的可用性提升，不会因为单点故障影响到整个系统。  
> 4、模块细分对系统重用性更高。  
> 5、模块细分对人员任务分配比较明确。  
> 6、模块细分解耦性对代码可维护性提高。  
> 7、单个模块重启只需要部署极少的服务。  
> 8、项目可灵活性的伸缩。  
> 9、项目开发、测试、部署可以同时进行，完全独立互不影响，提升生产效率。  
> 10、接口抽离成pom依赖调用，减少提供者与消费者编写重复代码。  

这样搭建的缺点：
> 1、项目之间的接口服务调用沟通成本提高。  
> 2、项目部署与维护难度提高。  
> 3、架构设计技术难度提高。  
> 4、在业务量小时响应时间会稍慢一些，且会增加系统的吞吐量。  
> 5、故障排查难度提高。  
> 6、需要对服务进行治理和调度维护。  

根据业务需求适当的伸缩搭建微服务，当一块业务不依赖或极少依赖其它服务，有独立的业务语义，为超过2个的其他服务或客户端提供数据，
那么它就应该被拆分成一个独立的服务模块。如果你的项目业务够大，就可以分的这么细。

### 配置目录
* config
> 配置中心集中式配置文件管理目录。
### 服务应用
* eureka-server
> 提供注册服务监控中心。
* config-server
> 提供分布式服务集中式属性配置中心。
* seluth-server
> 提供监控分布式服务状态情况。
* admin-server
> 提供微服务监控与管理。
* shiro-server [【daijie-shiro-security开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-shiro-security)
> 提供shiro自定义配置用户角色权限，访问路径统一管理的安全节点服务，所有被拦截的服务提供集中式swagger文档，通过跳转访问api登录接口授权，
> 授权完成后再验证跳转api接口访问服务，验证失败直接返回错误数据。
* oauth2-server [【daijie-shiro-oauth2开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-shiro-oauth2)
> 提供shiro自定义配置用户角色权限，访问路径统一管理的安全节点服务，通过oauth2协议访问授权接口，
> 其中主要授权需要配置api登录接口，授权完成后再验证跳转api接口访问服务，验证失败直接返回错误数据。
### 项目模块
* mybatis-model
> 1、集成mybatis-generator工具自动生成model和mapper文件的实例。  
> 2、与数据库对应实体统一管理，利于数据库结构变动只需重新生成即可。  
> 3、此项目不做人工代码修改，对数据库尽可能的单表操作，利用了缓存机制在多表连查时可以大大提升查询效率。  
* mybatis-cloud & jpa-cloud & mybatis-shardingjdbc-cloud  
[【多数据源、分布式事务开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-jdbc)
> 1、实现了数据库crud操作的实例，mybatis与jpa多数源配置，且实现了分布式事务，其中shardingjdbc官方自带实现了这些功能。  
> 2、三个项目用了三个不同的ORM框架实现，可以启用任意一个服务，因服务名配置一致，全部启动会起到微服务集群的效果。  
> 3、此项目可根据产品模块化细分为分布式的拆分为多个子项目，处理数据层的业务逻辑及数据库操作，做为资源服务的微服务提供者。  
* elasticsearch-cloud
> 1、提供数据库elasticsearch存储的搜索引擎实例。  
> 2、此项目可根据产品模块化细分为分布式的拆分为多个子项目，处理数据层的业务逻辑及数据库操作，做为资源服务的微服务提供者。  
* shiro-api  
[【swagger接口文档生成、分布式锁开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-core)  
[【shiro单点登录、角色权限配置开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-shiro)   
[【hadoop文件上传开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-hadoop)  
[【fastdfs文件上传开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-fastdfs)  
> 1、实现了简单的分布式锁实例。  
> 2、实现了生成二维码实例。  
> 3、实现了redis访问实例。  
> 4、实现了hadoop与fastdfs文件上传实例。  
> 5、提供单点登录接口，shiro sesssion+redis+kisso技术缓存用户状态。  
> 6、此项目可根据产品模块化细分为分布式的拆分为多个子项目，处理用户交互层的业务逻辑，调用所有的微服务提供者，提供对外资源接口调用。  
* rabbit-cloud
> 1、实现了消息生产与消费实例。  
> 2、处理高并发，复杂算法，无需即时回馈的业务。  
> 3、此项目可根据产品模块化细分为分布式的拆分为多个子项目，也可以拆分为生产消息的生产者和消费消息的消费者，调用资源服务的微服务提供者处理数据层的业务逻辑，做为消息服务的微服务提供者。  
* quaryz-cloud
> 1、实现了定时任务实例。  
> 2、此项目可根据产品模块化细分为分布式的拆分为多个子项目，处理业务层的业务逻辑，调用资源服务的微服务提供者。  
* activiti-cloud  
[【工作流开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-workflow)  
[【流程枚举存储开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-core)  
> 1、实现了请假、文物备案流程的实例。  
> 2、此项目可根据产品模块化细分为分布式的拆分为多个子项目，调用资源服务的微服务提供者处理流程业务逻辑，做为流程服务的微服务提供者。  
* social-cloud  
[【第三方登录开发文档】](https://github.com/daijiejay/daijie/tree/master/daijie-social)  
> 1、实现了第三方登录实例。  
> 2、此项目可根据产品模块化细分为分布式的拆分为多个子项目，调用第三方接口及数据回调，做为第三方服务调用的微服务提供者。  

## [相关文档](https://github.com/daijiejay/daijie)
# 环境搭建
## 项目环境
* 安装jdk1.8
* 安装maven
* 安装eclipse、myeclise或idea等编辑器
* 导入maven项目等下载完daijie-parent-spring-boot-starter的依赖包到本地库，最新版本的代码需要从github下载源码
## 项目部署
### 启动eureka-server步骤
* 执行eureka-server的main方法 
* 访问地址[http://localhost:8761](http://localhost:8761) 
### 启动config-server步骤
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 访问地址[http://localhost:12800/daijie](http://localhost:12800/daijie) 
### 启动seluth-server步骤
* 安装mysql  
* 创建zipkin库，相关表会在启动时自动创建  
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 执行seluth-server的main方法  
* 访问地址[http://localhost:12809](http://localhost:12809) 
### 启动admin-server步骤
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 执行admin-server的main方法  
* 访问地址[http://localhost:12812](http://localhost:12812) 
### 启动shiro-server步骤
* 安装redis3.0以上版本  
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 执行shiro-server的main方法  
* 配置host：127.0.0.1 daijie.org（因采用的是kisso cookie管理，访问localhost则不生效，或者在配置中关闭kisso）   
* 访问地址[http://daijie.org:12802/swagger-ui.html](http://daijie.org:12802/swagger-ui.html)   
* 分别启动mybatis-cloud和shiro-api   
* 通过swagger文档在线调用登录注册接口   
### 启动oauth2-server步骤
* 安装redis3.0以上版本  
* 安装mysql  
* 创建oauth2库，执行oauth2.sql脚本（在执行oauth2-server时会自动创建oauth2相关表，这里手动初始化了client_id数据）  
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 执行oauth2-server的main方法  
* 配置host：127.0.0.1 daijie.org（因采用的是kisso cookie管理，访问localhost则不生效，或者在配置中关闭kisso）  
* 分别启动mybatis-cloud和shiro-api  
* 通过访问接口的方式进行角色授权(用户注册可以访问shiro-api的swagger-ui.html调用注册接口进行注册，appid为test，密钥为daijie.org，同时client_id与client_secret在数据库oauth2.oauth_client_details表维护，密码存储用的是BCrypt加密)   

授权访问流程：  
> 1、http://daijie.org:12813/oauth/token?username=《你的用户名》&password=《你的密码》&grant_type=password&scope=select&client_id=《你的appid》&client_secret=《你的密钥》  
> 2、得到access_token  
> 3、访问资源接口http://daijie.org:12813/api/user?access_token=《你的回调access_token》    

在实际开发中outh2授权访问流程：  
> 1、http://daijie.org:12813/oauth/authorize?client_id=《你的appid》&redirect_uri=《你的回调地址》&response_type=code&state=《你的其它标识，用于回调接口接收》   
> 2、跳转登录页，输入用户名密码点登录（没有用户先调注册接口注册账号）  
> 3、得到code和state   
> 4、http://daijie.org:12813/oauth/token?client_id=《你的appid》&client_sercet=《你的密钥》&code=《你的回调code值》&grant_type=authorization_code  
> 5、得到access_token  
> 6、拿着access_token就可以有权限请求资源接口了  
### mybatis-model生成实体类和生成数据结构文档步骤
* 安装mysql  
* 创建demo库，执行demo.sql脚本  
* 配置generatorConfig.xml  
* 生成java文件执行maven命令：mvn mybatis-generator:generate  
* 生成数据结构文档执行maven命令：mvn daijie-jdbc:doc 
### 启动mybatis-cloud & jpa-cloud & mybatis-shardingjdbc-cloud步骤 (mybatis-shardingjdbc-cloud暂不支持spring boot2.0，1.1.X版本会启动失败)
* 安装mysql  
* 创建demo1和demo2库，执行demo1.sql与demo2.sql脚本   
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 执行mybatis-cloud或jpa-cloud或mybatis-shardingjdbc-cloud的main方法   
* 访问地址[http://localhost:12808/swagger-ui.html](http://localhost:12808/swagger-ui.html) 
### 启动elasticsearch-cloud步骤
* 安装mysql  
* 安装elasticsearch  
* 创建demo库，执行demo.sql脚本 
* 执行eureka-server的main方法  
* 执行config-server的main方法 
* 执行elasticsearch-cloud的main方法 
* 访问地址[http://localhost:12807/swagger-ui.html](http://localhost:12807/swagger-ui.html)   
### 启动shiro-api步骤
* 安装mysql  
* 安装redis3.0以上版本  
* 创建demo1和demo2库，执行demo1.sql与demo2.sql脚本   
* 执行eureka-server的main方法  
* 执行config-server的main方法  
* 执行mybatis-cloud或jpa-cloud或mybatis-shardingjdbc-cloud的main方法   
* 执行shiro-api的main方法    
* 访问地址[http://localhost:12803/swagger-ui.html](http://localhost:12803/swagger-ui.html)    
### 启动rabbit-cloud步骤
* 安装rabbit  
* 执行eureka-server的main方法  
* 执行config-server的main方法   
* 执行rabbit-cloud的main方法  
* 访问地址[http://localhost:12804/swagger-ui.html](http://localhost:12804/swagger-ui.html)     
### 启动quaryz-cloud步骤
* 执行eureka-server的main方法  
* 执行config-server的main方法   
* 执行quaryz-cloud的main方法  
* 访问地址[http://localhost:12805/swagger-ui.html](http://localhost:12805/swagger-ui.html)  
### 启动activiti-cloud步骤
* 安装mysql  
* 创建activiti库，相关表会在启动时自动创建  
* 执行eureka-server的main方法   
* 执行config-server的main方法    
* 执行activiti-cloud的main方法  
* 访问地址[http://localhost:12806/swagger-ui.html](http://localhost:12806/swagger-ui.html)   
### 启动social-cloud步骤
* 执行eureka-server的main方法  
* 执行config-server的main方法   
* 执行social-cloud的main方法  
* 访问地址[http://localhost:12811/swagger-ui.html](http://localhost:12811/swagger-ui.html)  

