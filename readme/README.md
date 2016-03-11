> playboy

0. whole view
----------
![](http://i.imgur.com/VQSnmlt.png)

整体要求:

1. HA。high avaliability高可用。
2. easy to scale out。很容易横向扩展，支撑大量并发请求。
3. 可监控。一切都要可监控，要有足够的预警机制。
4. 编程模型足够简单。

1. load balance
----------
最稳妥的方案会使用三层负载均衡：
![](http://i.imgur.com/3EvmYkP.png)


1. DNS。DNS作为天生的一层无需太多操作的负载器，可以用来把请求分散到离玩家最近的节点ip。
2. LVS。linux virtual server工作在3-4层之前，除了转发之外几乎没有任何操作，功能强大性能好。
3. nginx。作为第7层的反向代理来进行负载均衡，淘宝开源的tengine据说可以做到极限情况下单机200w的链接，生产环境下30w链接。

前期人数不多的时候只在nginx这一层做一个代理集群就可以搞定流量转发。

2. web node cluster
----------
部分及时性不太强的业务会选择使用http协议。这类web服务器都会设计成无状态方便scale out横向扩展。目前的实现会选择vertx进行底层实现，说下vertx3的特点：

1. 底层使用netty进行驱动。
2. 全异步的写法。
3. java8函数式编程。
4. fluent流式写法。
5. 多语言支持：js,groovy等等。
6. 集群支持。

3. net node cluster
----------
聊天这类及时的需求会使用长链tcp解决，也会使用vertx作为解决方案。特点：

1. 基本无存储。
2. 会话期间文件可以缓存到client的方式。

4. cache cluster
----------
用户的照片数据会选择缓存起来，方便快速的访问。这里可以是一个集群，使用shard分片的方式，片键可以是区域号。可以选用redis最新版本做集群支持。

这里还有一个特点就是：读多写少。

5. how to storage photos
----------
图片如何持久化，这是一个问题，会参考facebook,weibo,weixin,instagram等方案：

1. db存储。mysql,mongodb等
2. tfs。淘宝开源文件系统。
3. 云服务。AWS，淘宝，又拍云，7N等。

6. db shards
----------
1. 如果使用Mysql的话就会用haproxy或者corba这类组件。
2. mongdb就直接使用mongos。
3. cassandra

7. safety and security
----------

1. nginx层的主备以及防洪水攻击和黑名单方案等。
2. 应用级别的验证加密等方案。





