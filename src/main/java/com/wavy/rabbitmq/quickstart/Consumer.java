package com.wavy.rabbitmq.quickstart;

import com.rabbitmq.client.*;


public class Consumer {
    public static void main(String[] args)throws Exception {
        // 1.创建ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.233.200");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 3.通过connection创建一个Channel
        Channel channel = connection.createChannel();

        // 4.声明队列
        String queueName = "test001";
        channel.queueDeclare(queueName,true,false,false,null);

        // 5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6.设置Channel
        channel.basicConsume(queueName,true,queueingConsumer);

        // 7.获取消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端："+msg);
        }
    }
}