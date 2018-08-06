package com.wavy.rabbitmq.api.returnListener;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by WavyPeng on 2018/8/6.
 */
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

        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";

        String msg = "Hello RabbitMQ ReturnListener";
        channel.addReturnListener(new ReturnListener() {

            /**
             * @param replyCode 响应码
             * @param replyText 响应文本
             * @param exchange  交换机
             * @param routingKey 路由键
             * @param properties 属性
             * @param body 消息体内容
             * @throws IOException
             */
            @Override
            public void handleReturn(
                    int replyCode,
                    String replyText,
                    String exchange,
                    String routingKey,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                System.out.println("----------handle return----------");
                System.out.println("replyCode: "+replyCode);
                System.out.println("replyText: "+replyText);
                System.out.println("exchange: "+exchange);
                System.out.println("routingKey: "+routingKey);
                System.out.println("properties: "+properties);
                System.out.println("body: "+new String(body));
            }
        });
//        channel.basicPublish(exchangeName,routingKey,true,null,msg.getBytes());

        channel.basicPublish(exchangeName,routingKeyError,false,null,msg.getBytes());
    }
}
