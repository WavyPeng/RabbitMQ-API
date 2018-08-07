package com.wavy.rabbitmq.api.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义消费者
 * Created by WavyPeng on 2018/8/7.
 */
public class MyConsumer extends DefaultConsumer {

    private Channel channel;
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
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
        System.out.println("body: " + new String(body));


        // 设置睡眠，便于观察
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if ((Integer) properties.getHeaders().get("num") == 0){
            // 第三个参数requeue设置为true，表示重回队列
            // 重回队列会将失败的消息重新添加到队列尾端
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        }else {
            channel.basicAck(envelope.getDeliveryTag(),false);
        }
    }
}
