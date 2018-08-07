package com.wavy.rabbitmq.api.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 自定义消费者
 * Created by WavyPeng on 2018/8/7.
 */
public class MyConsumer extends DefaultConsumer {

    public MyConsumer(Channel channel) {
        super(channel);
    }

    /**
     * @param consumerTag
     * @param envelope
     * @param properties
     * @param body
     * @throws IOException
     */
    @Override
    public void handleDelivery(String consumerTag,
                               Envelope envelope,
                               AMQP.BasicProperties properties,
                               byte[] body) throws IOException {
        System.out.println("----------consume message----------");
        System.out.println("consumerTag: " + consumerTag);
        System.out.println("envelope: " + envelope);
        System.out.println("properties: " + properties);
        System.out.println("body: "+ new String(body));
    }
}
