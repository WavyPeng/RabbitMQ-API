package com.wavy.rabbitmq.api.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args)throws Exception {
        // 1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.233.200");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2.获取Connection
        Connection connection = connectionFactory.newConnection();
        // 3.通过Connection创建一个新的Channel
        Channel channel = connection.createChannel();

        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.save";

        String msg = "Hello RabbitMQ Consumer Message";
        for(int i=0;i<5;i++){
            channel.basicPublish(exchangeName,routingKey,true,null,msg.getBytes());
        }
    }
}