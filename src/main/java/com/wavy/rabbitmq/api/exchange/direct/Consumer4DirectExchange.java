package com.wavy.rabbitmq.api.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer4DirectExchange {
    public static void main(String[] args)throws Exception {
        // 1.创建ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.233.200");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        // 2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 3.通过connection创建一个Channel
        Channel channel = connection.createChannel();

        // 4.声明队列
        String exchangeName = "test_direct_exchange";
        String exchangeType = "direct";
        String queueName = "test_direct_queue";
        String routingKey = "test.direct";
        // 声明交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        // 声明队列
        channel.queueDeclare(queueName, false, false, false, null);
        // 建立绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);

        // 5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6.设置Channel
        // 参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName, true, queueingConsumer);

        // 7.获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端：" + msg);
        }
    }
}