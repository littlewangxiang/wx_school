package com.wx.mq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wx.mq.MQConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SiXinProduct {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost(MQConstant.VIRTUAL_HOST);

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String exchange = "test_dlx_exchange";
            String routingKey = "dlx.save";

            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .build();
            channel.basicPublish(exchange, routingKey, true, properties, "test dlx".getBytes());
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
