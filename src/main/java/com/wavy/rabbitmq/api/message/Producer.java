package com.wavy.rabbitmq.api.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Producer {
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

        // 自定义属性
        Map<String,Object> headers = new HashMap<>();
        headers.put("my1","111");
        headers.put("my2","222");

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) //2-持久化（服务重启，消息依旧存在）
                .contentEncoding("UTF-8")
                .expiration("10000") // 过期未被消费将会自动清除
                .headers(headers)  // 自定义属性，存放在Map中
                .build();

        // 4.通过Channel发送数据
        for(int i=0;i<5;i++){
            String msg = "Hello RabbitMQ!";
            // 1.exchange 2. routingKey
            channel.basicPublish("","test001",properties,msg.getBytes());
        }

        // 5.记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}