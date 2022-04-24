
消息治理平台

1、consumer消费哪一个topic，生产者向哪一个topic发送消息都需要在平台上配置，这样可以管理起来消费组和topic 
  topic需要在平台上创建，指定维护人
  consumerGroup需要在平台上创建，指定维护人
  consumerGroup和topic的绑定关系需要在平台上配置
  
2、rocketMQ相关的运维工具通过界面可视化操作
  例如：消息回溯；消息查看；broker信息等
  
方案：

修改该方法：
1、限制topic随意创建
   autoCreateTopicEnable=false
   
   RocketMQ中有一个默认的topic [TBW102] ,配置文件中 autoCreateTopicEnable=true 情况下会默认创建一个 TBW102 的topic，设置默认的读写队列和broker信息，
   开发人员发送消息时topic会自动创建，该topic会复用[TBW102]的相关配置信息（org.apache.rocketmq.broker.topic.TopicConfigManager.createTopicInSendMessageMethod ）
   
2、限制consumerGroup随意消费
   org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl.pullMessage
   入参org.apache.rocketmq.client.impl.consumer.PullRequest
   //TODO 绑定关系如何从治理平台上获取
   中有 consumerGroup 和 topic，在这里加相关代码，检查topic是否在平台配置，检查consumerGroup和topic的关系是否在平台配置
   
rocketMQ中Pull消费模式的使用场景  
   
rocketMQ顺序消费

rocketMQ事务消息
rocketMQ有序消息

Topic 队列

NameServer
   broker 与所有的nameServer建立连接
     broker启动时会拿到NameServer地址列表，循环所有nameServer 发送 register broker请求
   producer/consumer 与其中一个nameServer建立连接（客户端可能会存在很多，减少nameServer的压力）
     客户端启动时，org.apache.rocketmq.client.impl.MQClientAPIImpl#getTopicRouteInfoFromNameServer(java.lang.String, long, boolean)
     这里会带传一个空的地址，会走到 org.apache.rocketmq.remoting.netty.NettyRemotingClient#getAndCreateNameserverChannel
     这里会通过取模的算法拿一个nameServer地址进行channel连接
     producer 通过nameServer拿到了broker地址和broker的master节点建立连接
     consumer 通过nameServer拿到了broker地址和broker的namster|slave节点都建立连接
     
   

rocketMQ rebalance机制（触发原因：订阅Topic的队列数量编号；消费者组信息变化）
   队列信息变化：broker宕机、broker升级等运维操作、队列扩容/缩容
   消费者组信息变化：日常发布过程中的停止与启动、消费者异常宕机、网络异常导致消费者与broker断开连接、topic订阅信息发生变化
   带来的问题：新增consumer触发rebalance机制，重复消费问题（offset异步提交）；消费暂停

消费位点offset出现异常处理
org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl#pullMessage
   
 
  
  
