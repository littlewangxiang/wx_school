package com.wx.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Product {
    public static void main(String[] args) {
        //1.创建连接工厂
        //2。通过连接工厂创建连接对象.Connection
        //3。通过连接对象创建channel
        //4。通过channel来发布消息
        //5。释放资源。channel和connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("test001");

        try {
            Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();
            /**
             * 第一个参数 交换机名称
             * 第二个参数  路由键
             * 第三个参数  消息的属性
             * 第四个参数 消息的内容
             *
             */
            for (int i = 2; i < 50; i++) {
                String msg = "hello message :" + i;
                if (i % 2 == 0) {
                    channel.basicPublish(MQConstant.DIRECT_EXCHANGE_NAME, MQConstant.DIRECT_ROUTE_KEY, null, msg.getBytes());
                } else {
                    channel.basicPublish(MQConstant.DIRECT_EXCHANGE_NAME, MQConstant.DIRECT_ROUTE_KEY_2, null, msg
                            .getBytes());
                }

            }

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
