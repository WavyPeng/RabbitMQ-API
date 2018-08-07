package com.wavy.rabbitmq.api.dlx;

import com.rabbitmq.client.AMQP;
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

        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.save";

        String msg = "Hello RabbitMQ DLX Message";
//        for(int i=0;i<1;i++){

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) //2-持久化（服务重启，消息依旧存在）
                .contentEncoding("UTF-8")
                .expiration("10000") // 过期未被消费将会自动清除
                .build();

        channel.basicPublish(exchangeName,routingKey,true,properties,msg.getBytes());
//        }
    }
}