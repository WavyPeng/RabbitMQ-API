package com.wavy.rabbitmq.api.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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

        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.save";


        for(int i=0;i<5;i++){
            String msg = "Hello RabbitMQ Ack Message"+i;
            // 设置消息属性
            Map<String,Object> headers = new HashMap<>();
            headers.put("num",i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .deliveryMode(2) //2-持久化（服务重启，消息依旧存在）
                    .contentEncoding("UTF-8")
                    .headers(headers)  // 自定义属性，存放在Map中
                    .build();

            channel.basicPublish(exchangeName,routingKey,true,properties,msg.getBytes());
        }
    }
}