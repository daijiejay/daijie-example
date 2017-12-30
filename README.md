# 架构拓朴图
![架构拓朴图](http://m.qpic.cn/psb?/V14KUPlZ1oRvxL/J822aSfSQFFJm4Gxj9tS7URLXaYb*cgq6uIcKYmB6jQ!/b/dPMAAAAAAAAA&bo=*QKAAgAAAAADB18!&rf=viewer_4&t=5)
# 工程简介
---
## eureka-service
* eureka注册中心服务器。
## config
* 配置中心集中式配置文件管理。
## config-service
* 配置中心服务器。
## seluth-service
* 微服务监控服务器。
## admin-service
* 微服务监控与管理服务器。

---
## mybatis-model
* 集成mybatis-generator工具自动生成model和mapper的实例。
* 工程作用定义：与数据库对应实体统一管理，不做人工代码修改，利于数据库结构变动只需重新生成即可。
## mybatis-cloud & jpa-cloud & mybatis-shardingjdbc-cloud
* 对数据库进行crud操作实例。
* mybatis-shardingjdbc-cloud工程集成sharding-jdbc完成分表操作实例，sharding-jdbc对分库分表水平拆分做了很好支持，适当的解决了分布式事务。
* 工程作用定义：产品模块化的分布式直接访问数据库，可以有对业务性逻辑处理，但此工程不与客户端业务直接交互，比如获取客户端请求报文不在此工程处理。
## shiro-api
* 依赖shiro提供客服端RESTful api接口，完成单点登录，session集群，cookie安全实例。
* 完成在此工程操作集群session中的登录用户信息实例及redis单独使用实例。
* 完成一个简单的分布式锁实例。
* 工程作用定义：产品模块化的分布式接口提供，并形成swagger可视化接口文档，由此工程请求其它的cloud服务，注意的是此工程不提供给客户端直接访问，只提供给shiro-security工程访问。
## shiro-server
* 依赖shiro-security启动引用`@EnableShiroSecurityServer`注解安全控制实例。
* 工程作用定义：单点登录，shiro自定义请求权限统一管理，由客户端直接请求。
## oauth2-server
* 依赖shiro-oauth2启动引用`@EnableShiroOauth2SecurityServer`注解安全控制实例。
* 工程作用定义：oauth2协议服务，由第三方访问。
## elasticsearch-cloud
* 依赖elasticsearch完成搜索实例。
* 工程作用定义：数据搜索业务，搜索引擎通过api工程调用。
## rabbit-cloud
* 依赖rabbit完成生产消费消息实例。
* 工程作用定义：部分高并发，复杂算法，无需即时回馈的业务处理，自己生产自己消费，或其它集群rabbit服务消费，通过api工程调用。
## quaryz-cloud
* 依赖quaryz完成定时任务实例。
* 工程作用定义：处理系统自动执行的业务。
## activiti-cloud
* 依赖activiti完成流程实例。
* 工程作用定义：流程业务逻辑集中式处理，由api工程调用，此工程只处理流程中的业务逻辑和提供可视化的流程图，订单的处理人、处理结果、状态、历史记录等等需要维护到具体业务的数据库中。
## social-cloud
* 依赖social完成第三方登录实例。
* 工程作用定义：与第三方交互。
