package com.wavy.rabbitmq.api.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.Map;


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

        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.#";
        String queueName = "test_consumer_queue";

        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);

        // 自定义消费者，设为true表示自动签收
        channel.basicConsume(queueName,true,new MyConsumer(channel));
    }
}