### 这里主要记录了学习RabbitMQ的一些小例子

#### 开发环境
- IntelliJ IDEA 15.0.6
- SpringBoot 1.5.14 & 2.0
- RabbitMQ 3.6.5
- JDK8

#### 目录
- 交换机(Exchange)
  - [Direct Exchange](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/exchange/direct)
  - [Topic Exchange](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/exchange/topic)
  - [Fanout Exchange](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/exchange/fanout)
- 确认消息(ConfirmListener)
  - [ConfirmListener](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/confirm)
- Return消息机制(ReturnListener)
  - [ReturnListener](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/returnListener)
- 自定义消费者
  - [Consumer](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/consumer)
- 消费端限流
  - [limit](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/limit)
- 消费端ACK与重回队列
  - [ack](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/ack)
- 死信队列
  - [dlx](https://github.com/WavyPeng/RabbitMQ-API/tree/master/src/main/java/com/wavy/rabbitmq/api/dlx)

