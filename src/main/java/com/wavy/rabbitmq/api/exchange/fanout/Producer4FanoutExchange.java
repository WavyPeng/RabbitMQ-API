package com.wavy.rabbitmq.api.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4FanoutExchange {
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

        // 4.通过Channel发送数据
        String exchangeName = "test_fanout_exchange";

        for(int i=0;i<10;i++){
            String msg = "Hello World RabbitMQ 4 Fanout Exchange Message...";
            // 1.exchange 2. routingKey
            channel.basicPublish(exchangeName,"",null,msg.getBytes());
        }

        // 5.记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}